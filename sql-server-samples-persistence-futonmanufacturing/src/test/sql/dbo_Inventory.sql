-- -
-- #%L
-- sql-server-samples-persistence-futonmanufacturing
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
-- Profiling queries on dbo.Inventory, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_Inventory.sql
--


-- --------------------------------------------------------------------------------------------------------- InventoryID
-- how many rows, and the smallest and the largest InventoryID; 45 rows, 1 to 45.
SELECT COUNT(*), MIN(InventoryID), MAX(InventoryID)
FROM dbo.Inventory
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- an InventoryID other than the ItemID.
-- no rows: the rows were inserted in the order of the items, one each.
SELECT InventoryID, ItemID
FROM dbo.Inventory
WHERE InventoryID <> ItemID
;


-- --------------------------------------------------------------------------------------------------------- WarehouseID
-- the rows of each warehouse; all 45 in warehouse 1, the main one.
SELECT WarehouseID, COUNT(*) AS items
FROM dbo.Inventory
GROUP BY WarehouseID
ORDER BY WarehouseID ASC
;

-- UQ_Inventory_Item_Warehouse keeps an item to one row per warehouse.
-- no rows: no item is counted twice in a warehouse.
SELECT ItemID, WarehouseID, COUNT(*) AS rows
FROM dbo.Inventory
GROUP BY ItemID, WarehouseID
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------ QuantityOnHand
-- the smallest and the largest QuantityOnHand, and how many are zero or less; 7.00, 1500.00 and none.
SELECT MIN(QuantityOnHand), MAX(QuantityOnHand), SUM(CASE WHEN QuantityOnHand <= 0 THEN 1 ELSE 0 END)
FROM dbo.Inventory
;

-- an item whose QuantityOnHand is below its ReorderPoint.
-- no rows: every item is stocked at or above it.
SELECT v.ItemID, i.ItemCode, v.QuantityOnHand, i.ReorderPoint
FROM dbo.Inventory v
         JOIN dbo.Items i ON i.ItemID = v.ItemID
WHERE v.QuantityOnHand < i.ReorderPoint
ORDER BY v.ItemID ASC
;


-- --------------------------------------------------------------------------------------------------- QuantityAllocated
-- the smallest and the largest QuantityAllocated; .00 and .00, nothing is allocated.
SELECT MIN(QuantityAllocated), MAX(QuantityAllocated)
FROM dbo.Inventory
;


-- --------------------------------------------------------------------------------------------------- QuantityAvailable
-- QuantityAvailable is computed, QuantityOnHand less QuantityAllocated.
-- no rows: every row computes it.
SELECT InventoryID, QuantityOnHand, QuantityAllocated, QuantityAvailable
FROM dbo.Inventory
WHERE QuantityAvailable IS NULL
   OR QuantityAvailable <> QuantityOnHand - QuantityAllocated
;


-- ------------------------------------------------------------------------------------------------------- LastCountDate
-- the values LastCountDate takes; 2024-11-01 for all 45, a literal of the load script.
SELECT LastCountDate, COUNT(*) AS items
FROM dbo.Inventory
GROUP BY LastCountDate
ORDER BY LastCountDate ASC
;


-- --------------------------------------------------------------------------------------------------------- LastUpdated
-- how many LastUpdated values are NULL, and how many fall outside the five minutes after the database was created;
-- none, and none: the default GETDATE() filled every one in while the load script ran, so it is the moment of
-- the load, not of a business event, and moves with every load.
SELECT SUM(CASE WHEN x.LastUpdated IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.LastUpdated NOT BETWEEN d.create_date AND DATEADD(MINUTE, 5, d.create_date) THEN 1 ELSE 0 END)
FROM dbo.Inventory x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the stock value of each item type, QuantityOnHand at the StandardCost; 80175.00 of raw materials, 82137.64 of
-- components, and .00 of finished goods, whose StandardCost is .0000.
SELECT i.ItemTypeID, COUNT(*) AS items, SUM(v.QuantityOnHand * i.StandardCost) AS value
FROM dbo.Inventory v
         JOIN dbo.Items i ON i.ItemID = v.ItemID
GROUP BY i.ItemTypeID
ORDER BY i.ItemTypeID ASC
;

