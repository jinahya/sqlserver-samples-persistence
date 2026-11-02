-- -
-- #%L
-- sql-server-samples-persistence-wideworldimporters
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
-- Profiling queries on Sales.BuyingGroups, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_BuyingGroups.sql
--


-- ------------------------------------------------------------------------------------------------------- BuyingGroupID
-- how many rows, and the smallest and the largest BuyingGroupID; 2 buying groups, 1 and 2.
SELECT COUNT(*), MIN(BuyingGroupID), MAX(BuyingGroupID)
FROM Sales.BuyingGroups
;


-- ----------------------------------------------------------------------------------------------------- BuyingGroupName
-- the shortest and the longest BuyingGroupName; 12 and 13 characters, of the 50 the column allows.
SELECT MIN(LEN(BuyingGroupName)), MAX(LEN(BuyingGroupName))
FROM Sales.BuyingGroups
;

-- a BuyingGroupName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every BuyingGroupName is trimmed and not blank.
SELECT BuyingGroupID, '[' + BuyingGroupName + ']' AS BuyingGroupName
FROM Sales.BuyingGroups
WHERE TRIM(BuyingGroupName) = ''
   OR DATALENGTH(BuyingGroupName) <> DATALENGTH(TRIM(BuyingGroupName))
;

-- UQ_Sales_BuyingGroups_BuyingGroupName keeps BuyingGroupName unique.
-- no rows: no two buying groups share a name.
SELECT BuyingGroupName, COUNT(*) AS buying_groups
FROM Sales.BuyingGroups
GROUP BY BuyingGroupName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the rows; person 1, for both.
SELECT LastEditedBy, COUNT(*) AS buying_groups
FROM Sales.BuyingGroups
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of the moments the current versions began; 2013-01-01 00:00:00.0000000, a single moment.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Sales.BuyingGroups
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a current version ends at the largest datetime2; both carry 9999-12-31 23:59:59.9999999.
SELECT ValidTo, COUNT(*) AS buying_groups
FROM Sales.BuyingGroups
GROUP BY ValidTo
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the past versions kept in Sales.BuyingGroups_Archive; none, no buying group has been changed.
SELECT COUNT(*)
FROM Sales.BuyingGroups_Archive
;

-- a buying group no customer belongs to.
-- no rows: both buying groups have customers.
SELECT b.BuyingGroupID, b.BuyingGroupName
FROM Sales.BuyingGroups b
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Customers c
                  WHERE c.BuyingGroupID = b.BuyingGroupID)
;

-- the customers of each buying group; 201 each.
SELECT b.BuyingGroupID, b.BuyingGroupName, COUNT(*) AS customers
FROM Sales.BuyingGroups b
         JOIN Sales.Customers c ON c.BuyingGroupID = b.BuyingGroupID
GROUP BY b.BuyingGroupID, b.BuyingGroupName
ORDER BY b.BuyingGroupID ASC
;

