-- -
-- #%L
-- mssql-sample-databases-persistence-wideworldimporters
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
-- Profiling queries on Warehouse.StockGroups, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_StockGroups.sql
--


-- -------------------------------------------------------------------------------------------------------- StockGroupID
-- how many rows, and the smallest and the largest StockGroupID; 10 stock groups, 1 to 10.
SELECT COUNT(*), MIN(StockGroupID), MAX(StockGroupID)
FROM Warehouse.StockGroups
;

-- a StockGroupID whose next value is missing, below the largest.
-- no rows: StockGroupID runs from 1 without a gap.
SELECT a.StockGroupID + 1 AS missing_from
FROM Warehouse.StockGroups a
WHERE a.StockGroupID < (SELECT MAX(StockGroupID) FROM Warehouse.StockGroups)
  AND NOT EXISTS (SELECT 1
                  FROM Warehouse.StockGroups b
                  WHERE b.StockGroupID = a.StockGroupID + 1)
;


-- ------------------------------------------------------------------------------------------------------ StockGroupName
-- the shortest and the longest StockGroupName; 4 and 19 characters, of the 50 the column allows.
SELECT MIN(LEN(StockGroupName)), MAX(LEN(StockGroupName))
FROM Warehouse.StockGroups
;

-- a StockGroupName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every StockGroupName is trimmed and not blank.
SELECT StockGroupID, '[' + StockGroupName + ']' AS StockGroupName
FROM Warehouse.StockGroups
WHERE TRIM(StockGroupName) = ''
   OR DATALENGTH(StockGroupName) <> DATALENGTH(TRIM(StockGroupName))
;

-- UQ_Warehouse_StockGroups_StockGroupName keeps StockGroupName unique.
-- no rows: no two stock groups share a name.
SELECT StockGroupName, COUNT(*) AS stock_groups
FROM Warehouse.StockGroups
GROUP BY StockGroupName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the rows; person 1 for 9, person 9 for Furry Footwear, the one renamed.
SELECT LastEditedBy, COUNT(*) AS stock_groups
FROM Warehouse.StockGroups
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the moments the current versions began; 2013-01-01 for 9, 2016-01-01 16:00 for Furry Footwear.
SELECT ValidFrom, COUNT(*) AS stock_groups
FROM Warehouse.StockGroups
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the end of each current version; 9999-12-31 23:59:59.9999999, the largest datetime2, for all 10.
SELECT ValidTo, COUNT(*) AS stock_groups
FROM Warehouse.StockGroups
GROUP BY ValidTo
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the past versions kept in Warehouse.StockGroups_Archive; 1, Furry Footwear (8) as it was named before, Footwear.
SELECT COUNT(*)
FROM Warehouse.StockGroups_Archive
;

-- a stock group no row of Warehouse.StockItemStockGroups refers to by StockGroupID.
-- 1 row: no stock item is in Airline Novelties (5).
SELECT a.StockGroupID, a.StockGroupName
FROM Warehouse.StockGroups a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItemStockGroups r
                  WHERE r.StockGroupID = a.StockGroupID)
ORDER BY a.StockGroupID ASC
;

