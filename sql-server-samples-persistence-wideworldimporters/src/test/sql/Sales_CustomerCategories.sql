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
-- Profiling queries on Sales.CustomerCategories, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_CustomerCategories.sql
--


-- -------------------------------------------------------------------------------------------------- CustomerCategoryID
-- how many rows, and the smallest and the largest CustomerCategoryID; 8 categories, 1 to 8.
SELECT COUNT(*), MIN(CustomerCategoryID), MAX(CustomerCategoryID)
FROM Sales.CustomerCategories
;

-- a CustomerCategoryID whose next value is missing, below the largest.
-- no rows: CustomerCategoryID runs from 1 without a gap.
SELECT a.CustomerCategoryID + 1 AS missing_from
FROM Sales.CustomerCategories a
WHERE a.CustomerCategoryID < (SELECT MAX(CustomerCategoryID) FROM Sales.CustomerCategories)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.CustomerCategories b
                  WHERE b.CustomerCategoryID = a.CustomerCategoryID + 1)
;


-- ------------------------------------------------------------------------------------------------ CustomerCategoryName
-- the shortest and the longest CustomerCategoryName; 5 and 16 characters, of the 50 the column allows.
SELECT MIN(LEN(CustomerCategoryName)), MAX(LEN(CustomerCategoryName))
FROM Sales.CustomerCategories
;

-- a CustomerCategoryName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every CustomerCategoryName is trimmed and not blank.
SELECT CustomerCategoryID, '[' + CustomerCategoryName + ']' AS CustomerCategoryName
FROM Sales.CustomerCategories
WHERE TRIM(CustomerCategoryName) = ''
   OR DATALENGTH(CustomerCategoryName) <> DATALENGTH(TRIM(CustomerCategoryName))
;

-- UQ_Sales_CustomerCategories_CustomerCategoryName keeps CustomerCategoryName unique.
-- no rows: no two categories share a name.
SELECT CustomerCategoryName, COUNT(*) AS customer_categories
FROM Sales.CustomerCategories
GROUP BY CustomerCategoryName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the rows; person 1 for 7, person 9 for General Retailer, the one renamed.
SELECT LastEditedBy, COUNT(*) AS customer_categories
FROM Sales.CustomerCategories
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the moments the current versions began; 2013-01-01 for 7, 2014-01-01 16:15 for General Retailer.
SELECT ValidFrom, COUNT(*) AS customer_categories
FROM Sales.CustomerCategories
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the end of each current version; 9999-12-31 23:59:59.9999999, the largest datetime2, for all 8.
SELECT ValidTo, COUNT(*) AS customer_categories
FROM Sales.CustomerCategories
GROUP BY ValidTo
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the past versions kept in Sales.CustomerCategories_Archive; 1, General Retailer as it was named before.
SELECT COUNT(*)
FROM Sales.CustomerCategories_Archive
;

-- a customer category no row of Sales.Customers refers to by CustomerCategoryID.
-- 3 rows: no customer is an Agent (1), a Wholesaler (2) or a General Retailer (8).
SELECT a.CustomerCategoryID, a.CustomerCategoryName
FROM Sales.CustomerCategories a
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Customers r
                  WHERE r.CustomerCategoryID = a.CustomerCategoryID)
ORDER BY a.CustomerCategoryID ASC
;

