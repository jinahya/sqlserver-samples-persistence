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
-- Profiling queries on the view dbo.vw_InventoryValuation, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_InventoryValuation.sql
--


-- ------------------------------------------------------------------------------------------------------- WarehouseCode
-- how many rows, against the Inventory rows of active items; 45 and 45, a row per stock record.
SELECT COUNT(*),
       (SELECT COUNT(*)
        FROM dbo.Inventory inv
                 JOIN dbo.Items i ON i.ItemID = inv.ItemID
        WHERE i.IsActive = 1)
FROM dbo.vw_InventoryValuation
;

-- the grain of the view; the warehouse and the item, Inventory having one row for each.
-- no rows: no two rows share them.
SELECT WarehouseCode, ItemCode, COUNT(*) AS rows
FROM dbo.vw_InventoryValuation
GROUP BY WarehouseCode, ItemCode
HAVING COUNT(*) > 1
;

-- the warehouses; WH-MAIN alone, with all 45. WH-WEST and WH-EAST hold no stock.
SELECT WarehouseCode, COUNT(*) AS items
FROM dbo.vw_InventoryValuation
GROUP BY WarehouseCode
;


-- ------------------------------------------------------------------------------------------------------- WarehouseName
-- a WarehouseName which is not the one of its WarehouseCode.
-- no rows: each matches Warehouse.
SELECT v.WarehouseCode, v.ItemCode, v.WarehouseName
FROM dbo.vw_InventoryValuation v
         JOIN dbo.Warehouse w ON w.WarehouseCode = v.WarehouseCode
WHERE v.WarehouseName <> w.WarehouseName
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- the item types, with what they are worth; Component 15 items at 82137.637500, Finished Goods 6 at .000000, Raw
-- Material 24 at 80175.000000.
SELECT ItemType, COUNT(*) AS items, SUM(InventoryValue) AS value
FROM dbo.vw_InventoryValuation
GROUP BY ItemType
ORDER BY ItemType ASC
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- the shortest and the longest ItemCode; 10 and 12 characters, of the 50 the column allows.
SELECT MIN(LEN(ItemCode)), MAX(LEN(ItemCode))
FROM dbo.vw_InventoryValuation
;

-- an active item with no row in the view, having no Inventory row.
-- no rows: every one of the 45 items is stocked somewhere.
SELECT i.ItemID, i.ItemCode
FROM dbo.Items i
WHERE i.IsActive = 1
  AND NOT EXISTS (SELECT 1
                  FROM dbo.vw_InventoryValuation v
                  WHERE v.ItemCode = i.ItemCode)
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- the shortest and the longest ItemName; 11 and 36 characters, of the 255 the column allows.
SELECT MIN(LEN(ItemName)), MAX(LEN(ItemName))
FROM dbo.vw_InventoryValuation
;

-- an ItemName which is not the one of its ItemCode.
-- no rows: each matches Items.
SELECT v.ItemCode, v.ItemName
FROM dbo.vw_InventoryValuation v
         JOIN dbo.Items i ON i.ItemCode = v.ItemCode
WHERE v.ItemName <> i.ItemName
;


-- ------------------------------------------------------------------------------------------------------ QuantityOnHand
-- the smallest and the largest QuantityOnHand; 7.00 (FG-FUT-006) and 1500.00 (RM-FILL-001).
SELECT MIN(QuantityOnHand), MAX(QuantityOnHand)
FROM dbo.vw_InventoryValuation
;

-- a QuantityOnHand which is zero or negative.
-- no rows: every item has stock.
SELECT WarehouseCode, ItemCode, QuantityOnHand
FROM dbo.vw_InventoryValuation
WHERE QuantityOnHand <= 0
;


-- --------------------------------------------------------------------------------------------------- QuantityAllocated
-- the values of QuantityAllocated; .00 on all 45, though four sales orders are Confirmed and unshipped.
SELECT QuantityAllocated, COUNT(*) AS items
FROM dbo.vw_InventoryValuation
GROUP BY QuantityAllocated
;


-- --------------------------------------------------------------------------------------------------- QuantityAvailable
-- a QuantityAvailable which is NULL or not QuantityOnHand less QuantityAllocated, the computed column it comes from.
-- no rows: it is the difference throughout.
SELECT WarehouseCode, ItemCode, QuantityOnHand, QuantityAllocated, QuantityAvailable
FROM dbo.vw_InventoryValuation
WHERE QuantityAvailable IS NULL
   OR QuantityAvailable <> QuantityOnHand - QuantityAllocated
;


-- ------------------------------------------------------------------------------------------------------------ UnitCode
-- the units; EA 32, YD 8 and LB 5.
SELECT UnitCode, COUNT(*) AS items
FROM dbo.vw_InventoryValuation
GROUP BY UnitCode
ORDER BY items DESC, UnitCode ASC
;


-- -------------------------------------------------------------------------------------------------------- StandardCost
-- an item whose StandardCost is zero.
-- 6 rows: the finished goods FG-FUT-001 to FG-FUT-006, so their stock is valued at nothing.
SELECT ItemCode, ItemType, StandardCost
FROM dbo.vw_InventoryValuation
WHERE StandardCost = 0
ORDER BY ItemCode ASC
;

-- the smallest and the largest StandardCost above zero; 2.7500 (RM-METAL-001) and 378.3500 (COMP-MAT-003).
SELECT MIN(StandardCost), MAX(StandardCost)
FROM dbo.vw_InventoryValuation
WHERE StandardCost > 0
;


-- ------------------------------------------------------------------------------------------------------ InventoryValue
-- an InventoryValue which is not QuantityOnHand times StandardCost.
-- no rows: it is the product throughout.
SELECT WarehouseCode, ItemCode, InventoryValue
FROM dbo.vw_InventoryValuation
WHERE InventoryValue IS NULL
   OR InventoryValue <> QuantityOnHand * StandardCost
;

-- what the stock is worth; 162312.637500 in all.
SELECT SUM(InventoryValue)
FROM dbo.vw_InventoryValuation
;


-- ------------------------------------------------------------------------------------------------------ AvailableValue
-- an AvailableValue which is not QuantityAvailable times StandardCost, or not the InventoryValue; with nothing
-- allocated, the two are the same.
-- no rows: it is the product, and the InventoryValue, throughout.
SELECT WarehouseCode, ItemCode, AvailableValue, InventoryValue
FROM dbo.vw_InventoryValuation
WHERE AvailableValue IS NULL
   OR AvailableValue <> QuantityAvailable * StandardCost
   OR AvailableValue <> InventoryValue
;


-- ------------------------------------------------------------------------------------------------------- LastCountDate
-- how many LastCountDate are NULL, and the range; none, 2024-11-01 00:00:00.0000000 on every row.
SELECT SUM(CASE WHEN LastCountDate IS NULL THEN 1 ELSE 0 END), MIN(LastCountDate), MAX(LastCountDate)
FROM dbo.vw_InventoryValuation
;


-- ------------------------------------------------------------------------------------------------------ DaysSinceCount
-- a DaysSinceCount which is not the days from LastCountDate to today; it grows by one a day, so the file checks the
-- formula rather than a value.
-- no rows: it is the difference throughout.
SELECT WarehouseCode, ItemCode, LastCountDate, DaysSinceCount
FROM dbo.vw_InventoryValuation
WHERE DaysSinceCount IS NULL
   OR DaysSinceCount <> DATEDIFF(DAY, LastCountDate, GETDATE())
;

