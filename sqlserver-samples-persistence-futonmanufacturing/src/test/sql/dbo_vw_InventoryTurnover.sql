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
-- Profiling queries on the view dbo.vw_InventoryTurnover, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is a row per active item in stock, by Inventory row, with what InventoryTransaction moved in the last 12
-- months before GETDATE(). InventoryTransaction is empty, so every column taken from it is NULL, whatever the day.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_InventoryTurnover.sql
--


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows, and how many distinct ItemCodes; 45 and 45, every item, all in stock in the one warehouse.
SELECT COUNT(*), COUNT(DISTINCT ItemCode)
FROM dbo.vw_InventoryTurnover
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- how many ItemName values are NULL, and the shortest and the longest; none, 11 and 36 characters.
SELECT SUM(CASE WHEN ItemName IS NULL THEN 1 ELSE 0 END), MIN(LEN(ItemName)), MAX(LEN(ItemName))
FROM dbo.vw_InventoryTurnover
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- the rows of each type; 15 components, 6 finished goods and 24 raw materials.
SELECT ItemType, COUNT(*) AS items
FROM dbo.vw_InventoryTurnover
GROUP BY ItemType
ORDER BY ItemType ASC
;


-- ------------------------------------------------------------------------------------------------------ QuantityOnHand
-- the smallest and the largest QuantityOnHand; 7.00 and 1500.00.
SELECT MIN(QuantityOnHand), MAX(QuantityOnHand)
FROM dbo.vw_InventoryTurnover
;


-- --------------------------------------------------------------------------------------------------- QuantityAvailable
-- a QuantityAvailable other than the QuantityOnHand; nothing is allocated.
-- no rows: the two are the same everywhere.
SELECT ItemCode, QuantityOnHand, QuantityAvailable
FROM dbo.vw_InventoryTurnover
WHERE QuantityAvailable <> QuantityOnHand
;


-- -------------------------------------------------------------------------------------------------------- Annual Usage
-- the one column whose name has a space, so it is bracketed; how many are NULL, of all the rows; 45 of 45.
SELECT COUNT(*) - COUNT([Annual Usage]), COUNT(*)
FROM dbo.vw_InventoryTurnover
;


-- ------------------------------------------------------------------------------------------------------ AnnualReceipts
-- how many AnnualReceipts values are NULL, of all the rows; 45 of 45.
SELECT COUNT(*) - COUNT(AnnualReceipts), COUNT(*)
FROM dbo.vw_InventoryTurnover
;


-- ---------------------------------------------------------------------------------------------------- TransactionCount
-- how many TransactionCount values are NULL, of all the rows; 45 of 45, not 0: the view leaves the NULL of its outer
-- join.
SELECT COUNT(*) - COUNT(TransactionCount), COUNT(*)
FROM dbo.vw_InventoryTurnover
;


-- ------------------------------------------------------------------------------------------------------- TurnoverRatio
-- how many TurnoverRatio values are NULL, of all the rows; 45 of 45.
SELECT COUNT(*) - COUNT(TurnoverRatio), COUNT(*)
FROM dbo.vw_InventoryTurnover
;


-- ---------------------------------------------------------------------------------------------------------- DaysOnHand
-- how many DaysOnHand values are NULL, of all the rows; 45 of 45.
SELECT COUNT(*) - COUNT(DaysOnHand), COUNT(*)
FROM dbo.vw_InventoryTurnover
;


-- ------------------------------------------------------------------------------------------------------ InventoryValue
-- an InventoryValue other than the QuantityOnHand at the item's StandardCost.
-- no rows: the view computes it so.
SELECT v.ItemCode, v.QuantityOnHand, i.StandardCost, v.InventoryValue
FROM dbo.vw_InventoryTurnover v
         JOIN dbo.Items i ON i.ItemCode = v.ItemCode
WHERE v.InventoryValue <> v.QuantityOnHand * i.StandardCost
;

-- the InventoryValue of each type; 82137.6375 of components, 80175.0000 of raw materials, and .0000 of finished
-- goods, whose StandardCost is .0000.
SELECT ItemType, SUM(InventoryValue) AS value
FROM dbo.vw_InventoryTurnover
GROUP BY ItemType
ORDER BY ItemType ASC
;


-- ---------------------------------------------------------------------------------------------------- FirstTransaction
-- how many FirstTransaction values are NULL, of all the rows; 45 of 45.
SELECT COUNT(*) - COUNT(FirstTransaction), COUNT(*)
FROM dbo.vw_InventoryTurnover
;


-- ----------------------------------------------------------------------------------------------------- LastTransaction
-- how many LastTransaction values are NULL, of all the rows; 45 of 45.
SELECT COUNT(*) - COUNT(LastTransaction), COUNT(*)
FROM dbo.vw_InventoryTurnover
;


-- ----------------------------------------------------------------------------------------------- DaysSinceLastActivity
-- how many DaysSinceLastActivity values are NULL, of all the rows; 45 of 45.
SELECT COUNT(*) - COUNT(DaysSinceLastActivity), COUNT(*)
FROM dbo.vw_InventoryTurnover
;


-- ------------------------------------------------------------------------------------------------------- MovementClass
-- the values MovementClass takes; Non-Moving for all 45, where the CASE falls through on a NULL TurnoverRatio.
SELECT MovementClass, COUNT(*) AS items
FROM dbo.vw_InventoryTurnover
GROUP BY MovementClass
ORDER BY MovementClass ASC
;

