-- -
-- #%L
-- sqlserver-samples-persistence-futonmanufacturing
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
-- Exercises at the Advanced level on the dbo schema of FutonManufacturing: the bill of
-- materials, the inventory, purchasing and sales.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql-exercises/_dbo/03_advanced.sql
--


-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_A_01
-- The raw materials, `RAW`, ten FG-FUT-003 futons need through every active level of the bill of materials, each
-- level's scrap included, and how much of each is available across the warehouses, `0` where none is stocked.
WITH Explosion AS (SELECT b.ComponentItemID, CAST(10 * b.Quantity * (1 + b.ScrapRate / 100) AS DECIMAL(28, 8)) AS Quantity
                   FROM BillOfMaterials b
                            JOIN Items p ON p.ItemID = b.ParentItemID
                   WHERE p.ItemCode = 'FG-FUT-003'
                     AND b.IsActive = 1
                   UNION ALL
                   SELECT b.ComponentItemID, CAST(e.Quantity * b.Quantity * (1 + b.ScrapRate / 100) AS DECIMAL(28, 8))
                   FROM Explosion e
                            JOIN BillOfMaterials b ON b.ParentItemID = e.ComponentItemID
                   WHERE b.IsActive = 1)
SELECT i.ItemCode,
       i.ItemName,
       CAST(SUM(e.Quantity) AS DECIMAL(18, 4))                                          AS Quantity,
       u.UnitCode,
       COALESCE((SELECT SUM(v.QuantityAvailable) FROM Inventory v WHERE v.ItemID = i.ItemID), 0) AS Available
FROM Explosion e
         JOIN Items i ON i.ItemID = e.ComponentItemID
         JOIN ItemType t ON t.ItemTypeID = i.ItemTypeID
         JOIN UnitOfMeasure u ON u.UnitID = i.UnitID
WHERE t.TypeCode = 'RAW'
GROUP BY i.ItemID, i.ItemCode, i.ItemName, u.UnitCode
ORDER BY i.ItemCode
OPTION (MAXRECURSION 0)
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

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_A_05
-- the items in a cycle of the bill of materials.
WITH Walk AS (SELECT b.ParentItemID AS StartItemID,
                     b.ComponentItemID AS CurrentItemID,
                     CAST('/' + CAST(b.ParentItemID AS VARCHAR(11)) + '/' + CAST(b.ComponentItemID AS VARCHAR(11)) + '/' AS VARCHAR(MAX)) AS ItemPath,
                     0 AS IsCycle
              FROM BillOfMaterials b
              WHERE b.IsActive = 1
              UNION ALL
              SELECT w.StartItemID,
                     b.ComponentItemID,
                     CAST(w.ItemPath + CAST(b.ComponentItemID AS VARCHAR(11)) + '/' AS VARCHAR(MAX)),
                     CASE WHEN CHARINDEX('/' + CAST(b.ComponentItemID AS VARCHAR(11)) + '/', w.ItemPath) > 0 THEN 1 ELSE 0 END
              FROM Walk w
                       JOIN BillOfMaterials b ON b.ParentItemID = w.CurrentItemID
              WHERE b.IsActive = 1
                AND w.IsCycle = 0)
SELECT DISTINCT i.ItemCode, i.ItemName
FROM Walk w
         JOIN Items i ON i.ItemID = w.StartItemID
WHERE w.IsCycle = 1
  AND w.CurrentItemID = w.StartItemID
ORDER BY i.ItemCode
OPTION (MAXRECURSION 0)
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_A_03
-- How many of each finished futon the components available in WH-MAIN can build: for each direct component, how many
-- times its quantity is available, none where WH-MAIN does not stock it, and the smallest of those; scrap is not
-- counted. A futon without an active bill of materials shows NULL.
SELECT p.ItemCode,
       p.ItemName,
       MIN(FLOOR(CASE WHEN v.QuantityAvailable > 0 THEN v.QuantityAvailable ELSE 0 END / b.Quantity)) AS Buildable
FROM Items p
         JOIN ItemType t ON t.ItemTypeID = p.ItemTypeID
         LEFT JOIN BillOfMaterials b ON b.ParentItemID = p.ItemID AND b.IsActive = 1
         LEFT JOIN Inventory v ON v.ItemID = b.ComponentItemID
    AND v.WarehouseID = (SELECT w.WarehouseID FROM Warehouse w WHERE w.WarehouseCode = 'WH-MAIN')
WHERE t.TypeCode = 'FG'
GROUP BY p.ItemID, p.ItemCode, p.ItemName
ORDER BY Buildable DESC, p.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_A_06
-- For each item in WH-MAIN, its last inventory transaction: the type, the quantity, what it refers to, and when.
-- `ROW_NUMBER` over each item, the latest first, keeps one row; `TransactionID` breaks a tie, and a transaction with no
-- date comes last.
WITH Ranked AS (SELECT t.ItemID,
                       t.TransactionTypeID,
                       t.Quantity,
                       t.ReferenceType,
                       t.ReferenceNumber,
                       t.TransactionDate,
                       ROW_NUMBER() OVER (PARTITION BY t.ItemID ORDER BY t.TransactionDate DESC, t.TransactionID DESC) AS Rn
                FROM InventoryTransaction t
                         JOIN Warehouse w ON w.WarehouseID = t.WarehouseID
                WHERE w.WarehouseCode = 'WH-MAIN')
SELECT i.ItemCode, i.ItemName, tt.TypeCode, r.Quantity, r.ReferenceType, r.ReferenceNumber, r.TransactionDate
FROM Ranked r
         JOIN Items i ON i.ItemID = r.ItemID
         JOIN TransactionType tt ON tt.TransactionTypeID = r.TransactionTypeID
WHERE r.Rn = 1
ORDER BY i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_A_04
-- The orders of the wholesale customers, cancelled ones left out, each with the running total of its customer's net
-- amount, in the order of their dates.
SELECT c.CustomerCode,
       so.OrderNumber,
       so.OrderDate,
       so.NetAmount,
       SUM(so.NetAmount) OVER (PARTITION BY so.CustomerID ORDER BY so.OrderDate, so.SalesOrderID ROWS UNBOUNDED PRECEDING) AS RunningTotal
FROM SalesOrder so
         JOIN Customer c ON c.CustomerID = so.CustomerID
WHERE c.CustomerType = 'Wholesale'
  AND so.Status <> 'Cancelled'
ORDER BY c.CustomerCode, so.OrderDate, so.SalesOrderID
;

