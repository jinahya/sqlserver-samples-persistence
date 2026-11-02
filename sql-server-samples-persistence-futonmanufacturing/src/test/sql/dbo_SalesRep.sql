-- -
-- #%L
-- sql-server-samples-persistence-futonmanufacturing
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
-- Profiling queries on dbo.SalesRep, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_SalesRep.sql
--


-- ---------------------------------------------------------------------------------------------------------- SalesRepID
-- how many rows, and the smallest and the largest SalesRepID; 8 reps, 1 to 8.
SELECT COUNT(*), MIN(SalesRepID), MAX(SalesRepID)
FROM dbo.SalesRep
;


-- -------------------------------------------------------------------------------------------------------- EmployeeCode
-- an EmployeeCode not in the form SR-NNN, or whose number is not the SalesRepID.
-- no rows: every code is SR- and the SalesRepID in three digits, SR-001 to SR-008.
SELECT SalesRepID, EmployeeCode
FROM dbo.SalesRep
WHERE EmployeeCode NOT LIKE 'SR-[0-9][0-9][0-9]'
   OR CAST(RIGHT(EmployeeCode, 3) AS int) <> SalesRepID
;

-- the shortest and the longest EmployeeCode; 6 and 6 characters, of the 20 the column allows.
SELECT MIN(LEN(EmployeeCode)), MAX(LEN(EmployeeCode))
FROM dbo.SalesRep
;

-- an EmployeeCode which is blank, or carries leading or trailing spaces.
-- no rows: every EmployeeCode is trimmed and not blank.
SELECT SalesRepID, '[' + EmployeeCode + ']' AS EmployeeCode
FROM dbo.SalesRep
WHERE TRIM(EmployeeCode) = ''
   OR DATALENGTH(EmployeeCode) <> DATALENGTH(TRIM(EmployeeCode))
;

-- a unique constraint keeps EmployeeCode unique.
-- no rows: every EmployeeCode is distinct.
SELECT EmployeeCode, COUNT(*) AS reps
FROM dbo.SalesRep
GROUP BY EmployeeCode
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- FirstName
-- the shortest and the longest FirstName; 5 and 7 characters, of the 50 the column allows.
SELECT MIN(LEN(FirstName)), MAX(LEN(FirstName))
FROM dbo.SalesRep
;

-- a FirstName which is blank, or carries leading or trailing spaces.
-- no rows: every FirstName is trimmed and not blank.
SELECT SalesRepID, '[' + FirstName + ']' AS FirstName
FROM dbo.SalesRep
WHERE TRIM(FirstName) = ''
   OR DATALENGTH(FirstName) <> DATALENGTH(TRIM(FirstName))
;


-- ------------------------------------------------------------------------------------------------------------ LastName
-- the shortest and the longest LastName; 5 and 8 characters, of the 50 the column allows.
SELECT MIN(LEN(LastName)), MAX(LEN(LastName))
FROM dbo.SalesRep
;

-- a LastName which is blank, or carries leading or trailing spaces.
-- no rows: every LastName is trimmed and not blank.
SELECT SalesRepID, '[' + LastName + ']' AS LastName
FROM dbo.SalesRep
WHERE TRIM(LastName) = ''
   OR DATALENGTH(LastName) <> DATALENGTH(TRIM(LastName))
;

-- nothing keeps a full name unique, yet no two reps share one.
-- no rows: every FirstName and LastName pair is distinct.
SELECT FirstName, LastName, COUNT(*) AS reps
FROM dbo.SalesRep
GROUP BY FirstName, LastName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------------- Email
-- how many Email values are NULL, and the shortest and the longest of the rest; none, 19 and 22 characters, of the 100
-- the column allows.
SELECT SUM(CASE WHEN Email IS NULL THEN 1 ELSE 0 END), MIN(LEN(Email)), MAX(LEN(Email))
FROM dbo.SalesRep
;

-- an Email which is blank, or carries leading or trailing spaces.
-- no rows: every Email is trimmed and not blank.
SELECT SalesRepID, '[' + Email + ']' AS Email
FROM dbo.SalesRep
WHERE TRIM(Email) = ''
   OR DATALENGTH(Email) <> DATALENGTH(TRIM(Email))
;

-- nothing keeps Email unique, yet no two rows share one.
-- no rows: every Email is distinct.
SELECT Email, COUNT(*) AS reps
FROM dbo.SalesRep
GROUP BY Email
HAVING COUNT(*) > 1
;

-- an Email other than the first initial and the last name, in lower case, at futonmfg.com.
-- no rows: every Email is built that way, mjohnson@futonmfg.com for Michael Johnson.
SELECT SalesRepID, FirstName, LastName, Email
FROM dbo.SalesRep
WHERE Email <> LOWER(LEFT(FirstName, 1) + LastName) + '@futonmfg.com'
;


-- --------------------------------------------------------------------------------------------------------------- Phone
-- how many Phone values are NULL, and how many do not read NNN-555-NNNN; none, and none.
SELECT SUM(CASE WHEN Phone IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN Phone NOT LIKE '[0-9][0-9][0-9]-555-[0-9][0-9][0-9][0-9]' THEN 1 ELSE 0 END)
FROM dbo.SalesRep
;


-- --------------------------------------------------------------------------------------------------------- TerritoryID
-- the reps of each territory, NULL for none; territory 1 with 3 and the other five with 1 each, none NULL.
SELECT TerritoryID, COUNT(*) AS reps
FROM dbo.SalesRep
GROUP BY TerritoryID
ORDER BY TerritoryID ASC
;


-- ------------------------------------------------------------------------------------------------------------ HireDate
-- how many HireDate values are NULL, and the earliest and the latest; none, 2018-11-12 and 2022-02-14.
SELECT SUM(CASE WHEN HireDate IS NULL THEN 1 ELSE 0 END), MIN(HireDate), MAX(HireDate)
FROM dbo.SalesRep
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes; all 8 reps are active.
SELECT IsActive, COUNT(*) AS reps
FROM dbo.SalesRep
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the customers, the quotes and the sales orders of each rep; SR-001 with 3 customers, 1 quote and 5 orders the
-- busiest, SR-003 with an order and no customer, and SR-004 to SR-006 with nothing at all.
SELECT r.SalesRepID, r.EmployeeCode,
       (SELECT COUNT(*) FROM dbo.Customer c WHERE c.SalesRepID = r.SalesRepID) AS customers,
       (SELECT COUNT(*) FROM dbo.SalesQuote q WHERE q.SalesRepID = r.SalesRepID) AS quotes,
       (SELECT COUNT(*) FROM dbo.SalesOrder o WHERE o.SalesRepID = r.SalesRepID) AS orders
FROM dbo.SalesRep r
ORDER BY r.SalesRepID ASC
;

-- a sales order taken by a rep other than the one the customer is assigned to; 2 orders of CUST-007, whose rep is 2,
-- order 3 taken by rep 3 and order 14 by rep 8.
SELECT o.SalesOrderID, o.CustomerID, c.SalesRepID AS customer_rep, o.SalesRepID AS order_rep
FROM dbo.SalesOrder o
         JOIN dbo.Customer c ON c.CustomerID = o.CustomerID
WHERE o.SalesRepID <> c.SalesRepID
ORDER BY o.SalesOrderID ASC
;

