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
-- Exercises on the inventory: dbo.Inventory, a row per item and warehouse, with the items and their bill of
-- materials. Each poses a question, and answers it in Transact-SQL against the installed FutonManufacturing database.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/exercises/inventory_exercises.sql
--


-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_03
-- the finished goods in the main warehouse, WH-MAIN, the fewest available first.
SELECT i.ItemCode, i.ItemName, v.QuantityAvailable, i.ReorderPoint
FROM Inventory v
         JOIN Items i ON i.ItemID = v.ItemID
         JOIN ItemType t ON t.ItemTypeID = i.ItemTypeID
         JOIN Warehouse w ON w.WarehouseID = v.WarehouseID
WHERE t.TypeCode = 'FG'
  AND w.WarehouseCode = 'WH-MAIN'
ORDER BY v.QuantityAvailable, i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_03
-- the five stocks nearest to their reorder points: the ratio of the quantity available to the reorder point, the
-- smallest first.
SELECT TOP(5) i.ItemCode, i.ItemName,
       v.QuantityAvailable,
       i.ReorderPoint,
       CAST(v.QuantityAvailable / i.ReorderPoint AS DECIMAL(9, 2)) AS Ratio
FROM Inventory v
         JOIN Items i ON i.ItemID = v.ItemID
WHERE i.ReorderPoint > 0
ORDER BY v.QuantityAvailable / i.ReorderPoint, i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_A_03
-- how many of each finished futon the components available in WH-MAIN can build: for each component, how many
-- times its quantity is available, and the smallest of those.
SELECT p.ItemCode, p.ItemName, MIN(FLOOR(v.QuantityAvailable / b.Quantity)) AS Buildable
FROM BillOfMaterials b
         JOIN Items p ON p.ItemID = b.ParentItemID
         JOIN ItemType t ON t.ItemTypeID = p.ItemTypeID
         JOIN Inventory v ON v.ItemID = b.ComponentItemID
         JOIN Warehouse w ON w.WarehouseID = v.WarehouseID
WHERE t.TypeCode = 'FG'
  AND b.IsActive = 1
  AND w.WarehouseCode = 'WH-MAIN'
GROUP BY p.ItemID, p.ItemCode, p.ItemName
ORDER BY Buildable DESC, p.ItemCode
;

