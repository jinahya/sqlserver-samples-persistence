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
-- Profiling queries on Sales.BuyingGroups_Archive, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_BuyingGroups_Archive.sql
--


-- ------------------------------------------------------------------------------------------------------- BuyingGroupID
-- how many past versions, and of how many buying groups; none, the table is empty: no buying group has been changed.
SELECT COUNT(*), COUNT(DISTINCT BuyingGroupID)
FROM Sales.BuyingGroups_Archive
;

-- a past version of a buying group which is not in Sales.BuyingGroups; the history table has no foreign key.
-- no rows: every archived BuyingGroupID is still current.
SELECT a.BuyingGroupID
FROM Sales.BuyingGroups_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.BuyingGroups t
                  WHERE t.BuyingGroupID = a.BuyingGroupID)
;


-- ----------------------------------------------------------------------------------------------------- BuyingGroupName
-- the shortest and the longest BuyingGroupName; NULL and NULL, the table is empty.
SELECT MIN(LEN(BuyingGroupName)), MAX(LEN(BuyingGroupName))
FROM Sales.BuyingGroups_Archive
;

-- a BuyingGroupName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every BuyingGroupName is trimmed and not blank.
SELECT BuyingGroupID, '[' + BuyingGroupName + ']' AS BuyingGroupName
FROM Sales.BuyingGroups_Archive
WHERE TRIM(BuyingGroupName) = ''
   OR DATALENGTH(BuyingGroupName) <> DATALENGTH(TRIM(BuyingGroupName))
;

-- each past name next to the current one; no rows, the table is empty.
SELECT a.BuyingGroupID, a.BuyingGroupName, t.BuyingGroupName AS current_name
FROM Sales.BuyingGroups_Archive a
         JOIN Sales.BuyingGroups t ON t.BuyingGroupID = a.BuyingGroupID
ORDER BY a.BuyingGroupID ASC, a.ValidFrom ASC
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who edited the past versions; no rows, the table is empty.
SELECT LastEditedBy, COUNT(*) AS versions
FROM Sales.BuyingGroups_Archive
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of the moments the past versions began; NULL and NULL, the table is empty.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Sales.BuyingGroups_Archive
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the range of the moments the past versions ended; NULL and NULL, the table is empty.
SELECT MIN(ValidTo), MAX(ValidTo)
FROM Sales.BuyingGroups_Archive
;

-- a version which does not end after it began.
-- no rows: every ValidTo is later than its ValidFrom.
SELECT BuyingGroupID, ValidFrom, ValidTo
FROM Sales.BuyingGroups_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a past version whose end is not the start of the next version, past or current.
-- no rows: the versions of each buying group follow one another without a gap or an overlap.
SELECT a.BuyingGroupID, a.ValidFrom, a.ValidTo
FROM Sales.BuyingGroups_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.BuyingGroups t
                  WHERE t.BuyingGroupID = a.BuyingGroupID
                    AND t.ValidFrom = a.ValidTo)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.BuyingGroups_Archive b
                  WHERE b.BuyingGroupID = a.BuyingGroupID
                    AND b.ValidFrom = a.ValidTo)
;

