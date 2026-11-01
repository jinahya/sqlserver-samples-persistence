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
-- Profiling queries on dbo.Warehouse, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_Warehouse.sql
--


-- --------------------------------------------------------------------------------------------------------- WarehouseID
-- how many rows, and the smallest and the largest WarehouseID; 3 warehouses, 1 to 3.
SELECT COUNT(*), MIN(WarehouseID), MAX(WarehouseID)
FROM dbo.Warehouse
;

-- a WarehouseID whose next value is missing, below the largest; the column is an identity column.
-- no rows: WarehouseID runs from 1 without a gap.
SELECT a.WarehouseID + 1 AS missing_from
FROM dbo.Warehouse a
WHERE a.WarehouseID < (SELECT MAX(WarehouseID) FROM dbo.Warehouse)
  AND NOT EXISTS (SELECT 1
                  FROM dbo.Warehouse b
                  WHERE b.WarehouseID = a.WarehouseID + 1)
;


-- ------------------------------------------------------------------------------------------------------- WarehouseCode
-- the shortest and the longest WarehouseCode; 7 and 7 characters, of the 20 the column allows.
SELECT MIN(LEN(WarehouseCode)), MAX(LEN(WarehouseCode))
FROM dbo.Warehouse
;

-- a WarehouseCode which is not WH- and four upper-case letters.
-- no rows: WH-MAIN, WH-WEST and WH-EAST all have that shape.
SELECT WarehouseID, WarehouseCode
FROM dbo.Warehouse
WHERE WarehouseCode NOT LIKE 'WH-[A-Z][A-Z][A-Z][A-Z]' COLLATE Latin1_General_BIN
;

-- a unique constraint keeps WarehouseCode unique.
-- no rows: no two warehouses share a WarehouseCode.
SELECT WarehouseCode, COUNT(*) AS warehouses
FROM dbo.Warehouse
GROUP BY WarehouseCode
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------- WarehouseName
-- the shortest and the longest WarehouseName; 23 and 27 characters, of the 100 the column allows.
SELECT MIN(LEN(WarehouseName)), MAX(LEN(WarehouseName))
FROM dbo.Warehouse
;

-- a WarehouseName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every WarehouseName is trimmed and not blank.
SELECT WarehouseID, '[' + WarehouseName + ']' AS WarehouseName
FROM dbo.Warehouse
WHERE TRIM(WarehouseName) = ''
   OR DATALENGTH(WarehouseName) <> DATALENGTH(TRIM(WarehouseName))
;

-- no constraint keeps WarehouseName unique.
-- no rows: no two warehouses share a WarehouseName all the same.
SELECT WarehouseName, COUNT(*) AS warehouses
FROM dbo.Warehouse
GROUP BY WarehouseName
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------- Address
-- how many Address are NULL, and the shortest and the longest; none, 18 and 23 characters, of the 255 allowed.
SELECT SUM(CASE WHEN Address IS NULL THEN 1 ELSE 0 END), MIN(LEN(Address)), MAX(LEN(Address))
FROM dbo.Warehouse
;

-- an Address which is blank, or carries leading or trailing spaces.
-- no rows: every Address is trimmed and not blank.
SELECT WarehouseID, '[' + Address + ']' AS Address
FROM dbo.Warehouse
WHERE TRIM(Address) = ''
   OR DATALENGTH(Address) <> DATALENGTH(TRIM(Address))
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- how many City are NULL, and the shortest and the longest; none, 8 and 11 characters, of the 100 allowed.
SELECT SUM(CASE WHEN City IS NULL THEN 1 ELSE 0 END), MIN(LEN(City)), MAX(LEN(City))
FROM dbo.Warehouse
;

-- a City which is blank, or carries leading or trailing spaces.
-- no rows: every City is trimmed and not blank.
SELECT WarehouseID, '[' + City + ']' AS City
FROM dbo.Warehouse
WHERE TRIM(City) = ''
   OR DATALENGTH(City) <> DATALENGTH(TRIM(City))
;


-- --------------------------------------------------------------------------------------------------------------- State
-- the states, a warehouse each; CA, NC and OR.
SELECT State, COUNT(*) AS warehouses
FROM dbo.Warehouse
GROUP BY State
ORDER BY State ASC
;

-- a State which is not two upper-case letters; the column allows 50 characters, yet holds codes.
-- no rows: every State is a two-letter code.
SELECT WarehouseID, State
FROM dbo.Warehouse
WHERE State IS NULL
   OR State NOT LIKE '[A-Z][A-Z]' COLLATE Latin1_General_BIN
;


-- ------------------------------------------------------------------------------------------------------------- ZipCode
-- a ZipCode which is not five digits; the column allows 20 characters, room for ZIP+4.
-- no rows: every ZipCode is five digits, 97201, 90001 and 28201.
SELECT WarehouseID, ZipCode
FROM dbo.Warehouse
WHERE ZipCode IS NULL
   OR ZipCode NOT LIKE '[0-9][0-9][0-9][0-9][0-9]'
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values of IsActive; 1 on all 3, the default.
SELECT IsActive, COUNT(*) AS warehouses
FROM dbo.Warehouse
GROUP BY IsActive
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the rows each warehouse is referred to by, from the tables whose foreign keys point at it. WH-MAIN holds all 45
-- Inventory rows, all 5 purchase orders and 11 sales orders; WH-WEST has 4 sales orders alone; WH-EAST nothing.
-- InventoryTransaction and ProductionOrder are empty.
SELECT w.WarehouseCode,
       (SELECT COUNT(*) FROM dbo.Inventory x WHERE x.WarehouseID = w.WarehouseID)            AS inventory,
       (SELECT COUNT(*) FROM dbo.InventoryTransaction x WHERE x.WarehouseID = w.WarehouseID) AS transactions,
       (SELECT COUNT(*) FROM dbo.PurchaseOrder x WHERE x.WarehouseID = w.WarehouseID)        AS purchase_orders,
       (SELECT COUNT(*) FROM dbo.ProductionOrder x WHERE x.WarehouseID = w.WarehouseID)      AS production_orders,
       (SELECT COUNT(*) FROM dbo.SalesOrder x WHERE x.WarehouseID = w.WarehouseID)           AS sales_orders
FROM dbo.Warehouse w
ORDER BY w.WarehouseID ASC
;

-- a warehouse which nothing refers to.
-- WH-EAST (3), East Coast Distribution, alone: no stock, no order of any kind.
SELECT w.WarehouseID, w.WarehouseCode, w.WarehouseName
FROM dbo.Warehouse w
WHERE NOT EXISTS (SELECT 1 FROM dbo.Inventory x WHERE x.WarehouseID = w.WarehouseID)
  AND NOT EXISTS (SELECT 1 FROM dbo.InventoryTransaction x WHERE x.WarehouseID = w.WarehouseID)
  AND NOT EXISTS (SELECT 1 FROM dbo.PurchaseOrder x WHERE x.WarehouseID = w.WarehouseID)
  AND NOT EXISTS (SELECT 1 FROM dbo.ProductionOrder x WHERE x.WarehouseID = w.WarehouseID)
  AND NOT EXISTS (SELECT 1 FROM dbo.SalesOrder x WHERE x.WarehouseID = w.WarehouseID)
;

-- a sales order shipping from a warehouse which holds no stock of the item it sells.
-- 4 rows, the WH-WEST orders SO-2024-1002, -1003, -1105 and -1106; the stock is all at WH-MAIN.
SELECT DISTINCT so.OrderNumber, w.WarehouseCode
FROM dbo.SalesOrder so
         JOIN dbo.Warehouse w ON w.WarehouseID = so.WarehouseID
         JOIN dbo.SalesOrderDetail sod ON sod.SalesOrderID = so.SalesOrderID
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.Inventory inv
                  WHERE inv.WarehouseID = so.WarehouseID
                    AND inv.ItemID = sod.ItemID)
ORDER BY so.OrderNumber ASC
;

