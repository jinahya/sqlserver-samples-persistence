-- -
-- #%L
-- mssql-sample-databases-persistence-futonmanufacturing
-- %%
-- Copyright (C) 2026 Jinahya, Inc.
-- %%
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--      http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
-- #L%
-- -
--
-- Exercises on the bill of materials: dbo.BillOfMaterials, whose two foreign keys both reference dbo.Items. Each
-- poses a question, and answers it in Transact-SQL against the installed FutonManufacturing database.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/exercises/bom_exercises.sql
--


-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_01
-- the direct components of a finished futon, FG-FUT-003, with the quantity, the unit and the scrap rate of each.
SELECT c.ItemCode, c.ItemName, b.Quantity, u.UnitCode, b.ScrapRate
FROM BillOfMaterials b
         JOIN Items p ON p.ItemID = b.ParentItemID
         JOIN Items c ON c.ItemID = b.ComponentItemID
         JOIN UnitOfMeasure u ON u.UnitID = b.UnitID
WHERE p.ItemCode = 'FG-FUT-003'
  AND b.IsActive = 1
ORDER BY c.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_02
-- the raw materials which no item uses: bought, and stocked, for nothing.
SELECT i.ItemCode, i.ItemName
FROM Items i
         JOIN ItemType t ON t.ItemTypeID = i.ItemTypeID
WHERE t.TypeCode = 'RAW'
  AND NOT EXISTS (SELECT 1
                  FROM BillOfMaterials b
                  WHERE b.ComponentItemID = i.ItemID
                    AND b.IsActive = 1)
ORDER BY i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_01
-- the raw materials which four or more items use directly, with the number of those items, the most used first.
SELECT c.ItemCode, c.ItemName, COUNT(*) AS Parents
FROM BillOfMaterials b
         JOIN Items c ON c.ItemID = b.ComponentItemID
         JOIN ItemType t ON t.ItemTypeID = c.ItemTypeID
WHERE t.TypeCode = 'RAW'
  AND b.IsActive = 1
GROUP BY c.ItemID, c.ItemCode, c.ItemName
HAVING COUNT(*) >= 4
ORDER BY Parents DESC, c.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_02
-- the material cost of each finished futon, from the standard costs of its direct components and their scrap rates,
-- and what is left of its list price.
SELECT p.ItemCode,
       p.ItemName,
       CAST(SUM(b.Quantity * (1 + b.ScrapRate / 100) * c.StandardCost) AS DECIMAL(18, 4))               AS MaterialCost,
       p.ListPrice,
       CAST(p.ListPrice - SUM(b.Quantity * (1 + b.ScrapRate / 100) * c.StandardCost) AS DECIMAL(18, 4)) AS Margin
FROM BillOfMaterials b
         JOIN Items p ON p.ItemID = b.ParentItemID
         JOIN Items c ON c.ItemID = b.ComponentItemID
         JOIN ItemType t ON t.ItemTypeID = p.ItemTypeID
WHERE t.TypeCode = 'FG'
  AND b.IsActive = 1
GROUP BY p.ItemID, p.ItemCode, p.ItemName, p.ListPrice
ORDER BY p.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_A_01
-- the raw materials which ten of FG-FUT-003 need, through every level of the bill of materials, with the scrap of
-- each level, beside what is available of each.
WITH Explosion AS (SELECT b.ComponentItemID,
                          CAST(10 * b.Quantity * (1 + b.ScrapRate / 100) AS DECIMAL(28, 8)) AS Quantity
                   FROM BillOfMaterials b
                            JOIN Items p ON p.ItemID = b.ParentItemID
                   WHERE p.ItemCode = 'FG-FUT-003'
                     AND b.IsActive = 1
                   UNION ALL
                   SELECT b.ComponentItemID,
                          CAST(e.Quantity * b.Quantity * (1 + b.ScrapRate / 100) AS DECIMAL(28, 8))
                   FROM Explosion e
                            JOIN BillOfMaterials b ON b.ParentItemID = e.ComponentItemID
                   WHERE b.IsActive = 1)
SELECT i.ItemCode,
       i.ItemName,
       CAST(SUM(e.Quantity) AS DECIMAL(18, 4))                                      AS Quantity,
       u.UnitCode,
       (SELECT SUM(v.QuantityAvailable) FROM Inventory v WHERE v.ItemID = i.ItemID) AS Available
FROM Explosion e
         JOIN Items i ON i.ItemID = e.ComponentItemID
         JOIN UnitOfMeasure u ON u.UnitID = i.UnitID
WHERE NOT EXISTS (SELECT 1
                  FROM BillOfMaterials b
                  WHERE b.ParentItemID = e.ComponentItemID
                    AND b.IsActive = 1)
GROUP BY i.ItemID, i.ItemCode, i.ItemName, u.UnitCode
ORDER BY i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_A_02
-- the finished goods which use RM-FILL-001, at any level, a row per path, with the quantity along the path.
WITH WhereUsed AS (SELECT b.ParentItemID,
                          CAST(b.Quantity AS DECIMAL(18, 4))                           AS Quantity,
                          1                                                            AS Level,
                          CAST(c.ItemCode + ' used in ' + p.ItemCode AS NVARCHAR(MAX)) AS UsagePath
                   FROM BillOfMaterials b
                            JOIN Items c ON c.ItemID = b.ComponentItemID
                            JOIN Items p ON p.ItemID = b.ParentItemID
                   WHERE c.ItemCode = 'RM-FILL-001'
                     AND b.IsActive = 1
                   UNION ALL
                   SELECT b.ParentItemID,
                          CAST(w.Quantity * b.Quantity AS DECIMAL(18, 4)),
                          w.Level + 1,
                          CAST(w.UsagePath + ' > ' + p.ItemCode AS NVARCHAR(MAX))
                   FROM WhereUsed w
                            JOIN BillOfMaterials b ON b.ComponentItemID = w.ParentItemID
                            JOIN Items p ON p.ItemID = b.ParentItemID
                   WHERE b.IsActive = 1)
SELECT i.ItemCode, w.Level, w.Quantity, w.UsagePath
FROM WhereUsed w
         JOIN Items i ON i.ItemID = w.ParentItemID
         JOIN ItemType t ON t.ItemTypeID = i.ItemTypeID
WHERE t.TypeCode = 'FG'
ORDER BY i.ItemCode, w.UsagePath
;

