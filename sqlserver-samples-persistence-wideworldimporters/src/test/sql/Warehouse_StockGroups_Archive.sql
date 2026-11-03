-- -
-- #%L
-- sqlserver-samples-persistence-wideworldimporters
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
-- Profiling queries on Warehouse.StockGroups_Archive, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_StockGroups_Archive.sql
--


-- -------------------------------------------------------------------------------------------------------- StockGroupID
-- how many past versions, and of how many stock groups; 1 version, of Furry Footwear (8).
SELECT COUNT(*), COUNT(DISTINCT StockGroupID)
FROM Warehouse.StockGroups_Archive
;

-- a past version of a stock group which is not in Warehouse.StockGroups; the history table has no foreign key.
-- no rows: every archived StockGroupID is still current.
SELECT a.StockGroupID
FROM Warehouse.StockGroups_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.StockGroups t
                  WHERE t.StockGroupID = a.StockGroupID)
;


-- ------------------------------------------------------------------------------------------------------ StockGroupName
-- the shortest and the longest StockGroupName; 8 and 8 characters, of the 50 the column allows.
SELECT MIN(LEN(StockGroupName)), MAX(LEN(StockGroupName))
FROM Warehouse.StockGroups_Archive
;

-- a StockGroupName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every StockGroupName is trimmed and not blank.
SELECT StockGroupID, '[' + StockGroupName + ']' AS StockGroupName
FROM Warehouse.StockGroups_Archive
WHERE TRIM(StockGroupName) = ''
   OR DATALENGTH(StockGroupName) <> DATALENGTH(TRIM(StockGroupName))
;

-- each past name next to the current one; 8, Footwear, now Furry Footwear.
SELECT a.StockGroupID, a.StockGroupName, t.StockGroupName AS current_name
FROM Warehouse.StockGroups_Archive a
         JOIN Warehouse.StockGroups t ON t.StockGroupID = a.StockGroupID
ORDER BY a.StockGroupID ASC, a.ValidFrom ASC
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who edited the past versions; person 1, for the 1 version.
SELECT LastEditedBy, COUNT(*) AS versions
FROM Warehouse.StockGroups_Archive
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of the moments the past versions began; 2013-01-01 00:00:00.0000000.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Warehouse.StockGroups_Archive
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the range of the moments the past versions ended; 2016-01-01 16:00:00.0000000.
SELECT MIN(ValidTo), MAX(ValidTo)
FROM Warehouse.StockGroups_Archive
;

-- a version which does not end after it began.
-- no rows: every ValidTo is later than its ValidFrom.
SELECT StockGroupID, ValidFrom, ValidTo
FROM Warehouse.StockGroups_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a past version whose end is not the start of the next version, past or current.
-- no rows: the versions of each stock group follow one another without a gap or an overlap.
SELECT a.StockGroupID, a.ValidFrom, a.ValidTo
FROM Warehouse.StockGroups_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.StockGroups t
                  WHERE t.StockGroupID = a.StockGroupID
                    AND t.ValidFrom = a.ValidTo)
  AND NOT EXISTS (SELECT 1
                  FROM Warehouse.StockGroups_Archive b
                  WHERE b.StockGroupID = a.StockGroupID
                    AND b.ValidFrom = a.ValidTo)
;

