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
-- Profiling queries on the view dbo.vw_ItemsBelowReorderPoint, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is empty on the installed data: the closest item, FG-FUT-004, is still 3.00 above its reorder point.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_ItemsBelowReorderPoint.sql
--


-- ------------------------------------------------------------------------------------------------------- WarehouseCode
-- how many rows; 0, no active item being below its reorder point.
SELECT COUNT(*)
FROM dbo.vw_ItemsBelowReorderPoint
;

-- the stocked items closest to their ReorderPoint; FG-FUT-004 3.00 above (8.00 against 5.00), FG-FUT-006 3.00 above
-- (7.00 against 4.00) and FG-FUT-003 6.00 above (12.00 against 6.00).
SELECT TOP (3) i.ItemCode, inv.QuantityAvailable, i.ReorderPoint, inv.QuantityAvailable - i.ReorderPoint AS margin
FROM dbo.Inventory inv
         JOIN dbo.Items i ON i.ItemID = inv.ItemID
WHERE i.IsActive = 1
ORDER BY margin ASC, i.ItemCode ASC
;


-- ------------------------------------------------------------------------------------------------------- WarehouseName
-- how many rows carry a WarehouseName; 0, the view being empty.
SELECT COUNT(WarehouseName)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- how many rows carry a ItemType; 0, the view being empty.
SELECT COUNT(ItemType)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows carry a ItemCode; 0, the view being empty.
SELECT COUNT(ItemCode)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- how many rows carry a ItemName; 0, the view being empty.
SELECT COUNT(ItemName)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- --------------------------------------------------------------------------------------------------- QuantityAvailable
-- how many rows carry a QuantityAvailable; 0, the view being empty.
SELECT COUNT(QuantityAvailable)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- -------------------------------------------------------------------------------------------------------- ReorderPoint
-- how many rows carry a ReorderPoint; 0, the view being empty.
SELECT COUNT(ReorderPoint)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- --------------------------------------------------------------------------------------------------------- SafetyStock
-- how many rows carry a SafetyStock; 0, the view being empty.
SELECT COUNT(SafetyStock)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- ---------------------------------------------------------------------------------------------------- ShortageQuantity
-- how many rows carry a ShortageQuantity; 0, the view being empty.
SELECT COUNT(ShortageQuantity)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- ------------------------------------------------------------------------------------------------------------ UnitCode
-- how many rows carry a UnitCode; 0, the view being empty.
SELECT COUNT(UnitCode)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- -------------------------------------------------------------------------------------------------------- LeadTimeDays
-- how many rows carry a LeadTimeDays; 0, the view being empty.
SELECT COUNT(LeadTimeDays)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- --------------------------------------------------------------------------------------------------- PreferredSupplier
-- how many rows carry a PreferredSupplier; 0, the view being empty.
SELECT COUNT(PreferredSupplier)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- ------------------------------------------------------------------------------------------------------ PreferredPrice
-- how many rows carry a PreferredPrice; 0, the view being empty.
SELECT COUNT(PreferredPrice)
FROM dbo.vw_ItemsBelowReorderPoint
;


-- ----------------------------------------------------------------------------------------------------- ExpectedArrival
-- how many rows carry a ExpectedArrival; 0, the view being empty.
SELECT COUNT(ExpectedArrival)
FROM dbo.vw_ItemsBelowReorderPoint
;

