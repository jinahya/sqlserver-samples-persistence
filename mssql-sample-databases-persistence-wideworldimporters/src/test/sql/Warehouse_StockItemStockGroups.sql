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
-- Profiling queries on Warehouse.StockItemStockGroups, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_StockItemStockGroups.sql
--


-- ----------------------------------------------------------------------------------------------- StockItemStockGroupID
-- how many rows, and the smallest and the largest StockItemStockGroupID; 442 links, 1 to 442.
SELECT COUNT(*), MIN(StockItemStockGroupID), MAX(StockItemStockGroupID)
FROM Warehouse.StockItemStockGroups
;

-- a StockItemStockGroupID whose next value is missing, below the largest.
-- no rows: StockItemStockGroupID runs from 1 without a gap.
SELECT a.StockItemStockGroupID + 1 AS missing_from
FROM Warehouse.StockItemStockGroups a
WHERE a.StockItemStockGroupID < (SELECT MAX(StockItemStockGroupID) FROM Warehouse.StockItemStockGroups)
  AND NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItemStockGroups b
                  WHERE b.StockItemStockGroupID = a.StockItemStockGroupID + 1)
;


-- --------------------------------------------------------------------------------------------------------- StockItemID
-- the stock items by the number of their stock groups; 94 in 1, 51 in 2, 82 in 3.
SELECT stock_groups, COUNT(*) AS stock_items
FROM (SELECT StockItemID, COUNT(*) AS stock_groups
      FROM Warehouse.StockItemStockGroups
      GROUP BY StockItemID) g
GROUP BY stock_groups
ORDER BY stock_groups ASC
;


-- -------------------------------------------------------------------------------------------------------- StockGroupID
-- the stock items of each stock group; Novelty Items (1) the largest with 91, none in Airline
-- Novelties (5).
SELECT g.StockGroupID, s.StockGroupName, COUNT(*) AS stock_items
FROM Warehouse.StockItemStockGroups g
         JOIN Warehouse.StockGroups s ON s.StockGroupID = g.StockGroupID
GROUP BY g.StockGroupID, s.StockGroupName
ORDER BY g.StockGroupID ASC
;

-- UQ_StockItemStockGroups_StockItemID_Lookup and UQ_StockItemStockGroups_StockGroupID_Lookup keep each pair once.
-- no rows: no stock item is in a stock group twice.
SELECT StockItemID, StockGroupID, COUNT(*) AS links
FROM Warehouse.StockItemStockGroups
GROUP BY StockItemID, StockGroupID
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the rows; person 1, for all 442.
SELECT LastEditedBy, COUNT(*) AS links
FROM Warehouse.StockItemStockGroups
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- the range of LastEditedWhen; 2013-01-01 to 2016-01-05.
SELECT MIN(LastEditedWhen), MAX(LastEditedWhen)
FROM Warehouse.StockItemStockGroups
;

