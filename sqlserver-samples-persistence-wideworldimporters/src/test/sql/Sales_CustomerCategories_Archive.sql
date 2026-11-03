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
-- Profiling queries on Sales.CustomerCategories_Archive, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_CustomerCategories_Archive.sql
--


-- -------------------------------------------------------------------------------------------------- CustomerCategoryID
-- how many past versions, and of how many customer categories; 1 version, of General Retailer (8).
SELECT COUNT(*), COUNT(DISTINCT CustomerCategoryID)
FROM Sales.CustomerCategories_Archive
;

-- a past version of a customer category which is not in Sales.CustomerCategories; the history table has no foreign key.
-- no rows: every archived CustomerCategoryID is still current.
SELECT a.CustomerCategoryID
FROM Sales.CustomerCategories_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.CustomerCategories t
                  WHERE t.CustomerCategoryID = a.CustomerCategoryID)
;


-- ------------------------------------------------------------------------------------------------ CustomerCategoryName
-- the shortest and the longest CustomerCategoryName; 8 and 8 characters, of the 50 the column allows.
SELECT MIN(LEN(CustomerCategoryName)), MAX(LEN(CustomerCategoryName))
FROM Sales.CustomerCategories_Archive
;

-- a CustomerCategoryName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every CustomerCategoryName is trimmed and not blank.
SELECT CustomerCategoryID, '[' + CustomerCategoryName + ']' AS CustomerCategoryName
FROM Sales.CustomerCategories_Archive
WHERE TRIM(CustomerCategoryName) = ''
   OR DATALENGTH(CustomerCategoryName) <> DATALENGTH(TRIM(CustomerCategoryName))
;

-- each past name next to the current one; 8, Retailer, now General Retailer.
SELECT a.CustomerCategoryID, a.CustomerCategoryName, t.CustomerCategoryName AS current_name
FROM Sales.CustomerCategories_Archive a
         JOIN Sales.CustomerCategories t ON t.CustomerCategoryID = a.CustomerCategoryID
ORDER BY a.CustomerCategoryID ASC, a.ValidFrom ASC
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who edited the past versions; person 1, for the 1 version.
SELECT LastEditedBy, COUNT(*) AS versions
FROM Sales.CustomerCategories_Archive
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of the moments the past versions began; 2014-01-01 16:00:00.0000000.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Sales.CustomerCategories_Archive
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the range of the moments the past versions ended; 2014-01-01 16:15:00.0000000, a quarter of an hour later.
SELECT MIN(ValidTo), MAX(ValidTo)
FROM Sales.CustomerCategories_Archive
;

-- a version which does not end after it began.
-- no rows: every ValidTo is later than its ValidFrom.
SELECT CustomerCategoryID, ValidFrom, ValidTo
FROM Sales.CustomerCategories_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a past version whose end is not the start of the next version, past or current.
-- no rows: the versions of each customer category follow one another without a gap or an overlap.
SELECT a.CustomerCategoryID, a.ValidFrom, a.ValidTo
FROM Sales.CustomerCategories_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.CustomerCategories t
                  WHERE t.CustomerCategoryID = a.CustomerCategoryID
                    AND t.ValidFrom = a.ValidTo)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.CustomerCategories_Archive b
                  WHERE b.CustomerCategoryID = a.CustomerCategoryID
                    AND b.ValidFrom = a.ValidTo)
;

