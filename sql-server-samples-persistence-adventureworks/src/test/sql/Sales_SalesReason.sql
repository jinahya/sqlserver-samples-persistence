-- -
-- #%L
-- sql-server-samples-persistence-adventureworks
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
-- Profiling queries on Sales.SalesReason, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SalesReason.sql
--


-- ------------------------------------------------------------------------------------------------------- SalesReasonID
-- how many rows, and the smallest and the largest SalesReasonID; 10 reasons, 1 to 10.
SELECT COUNT(*), MIN(SalesReasonID), MAX(SalesReasonID)
FROM Sales.SalesReason
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 5 and 25 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Sales.SalesReason
;

-- a Name which is blank, or carries leading or trailing spaces.
-- no rows: every Name is trimmed and not blank.
SELECT SalesReasonID, '[' + Name + ']' AS Name
FROM Sales.SalesReason
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- a Name with two spaces in a row; 4, 'Television  Advertisement'.
SELECT SalesReasonID, '[' + Name + ']' AS Name
FROM Sales.SalesReason
WHERE Name LIKE '%  %'
;

-- the schema has no unique key on Name; one more than one reason has.
-- no rows: every Name is unique.
SELECT Name, COUNT(*) AS reasons
FROM Sales.SalesReason
GROUP BY Name
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------------- ReasonType
-- the reasons of each ReasonType; Marketing 4, Other 5 and Promotion 1.
SELECT ReasonType, COUNT(*) AS reasons
FROM Sales.SalesReason
GROUP BY ReasonType
ORDER BY ReasonType ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Sales.SalesReason
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many orders give each reason; Price the most with 17473, and none give 3, 7 and 8.
SELECT s.SalesReasonID, s.Name, COUNT(r.SalesOrderID) AS orders
FROM Sales.SalesReason s
         LEFT JOIN Sales.SalesOrderHeaderSalesReason r ON r.SalesReasonID = s.SalesReasonID
GROUP BY s.SalesReasonID, s.Name
ORDER BY s.SalesReasonID ASC
;

