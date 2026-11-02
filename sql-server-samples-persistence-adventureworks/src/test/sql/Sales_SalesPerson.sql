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
-- Profiling queries on Sales.SalesPerson, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SalesPerson.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and the smallest and the largest BusinessEntityID; 17 sales persons, 274 to 290.
SELECT COUNT(*), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM Sales.SalesPerson
;

-- the job titles of the sales persons; 14 Sales Representatives and 3 sales managers, European, North American and
-- Pacific.
SELECT e.JobTitle, COUNT(*) AS sales_persons
FROM Sales.SalesPerson s
         JOIN HumanResources.Employee e ON e.BusinessEntityID = s.BusinessEntityID
GROUP BY e.JobTitle
ORDER BY e.JobTitle ASC
;

-- an employee titled a sales representative or manager who is no sales person.
-- no rows: every one of them is a Sales.SalesPerson.
SELECT e.BusinessEntityID, e.JobTitle
FROM HumanResources.Employee e
WHERE (e.JobTitle LIKE '%Sales Representative%' OR e.JobTitle LIKE '%Sales Manager%')
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesPerson s
                  WHERE s.BusinessEntityID = e.BusinessEntityID)
;


-- --------------------------------------------------------------------------------------------------------- TerritoryID
-- the sales persons with no territory; the 3 sales managers, with no SalesQuota, Bonus and CommissionPct.
SELECT s.BusinessEntityID, e.JobTitle, s.SalesQuota, s.Bonus, s.CommissionPct
FROM Sales.SalesPerson s
         JOIN HumanResources.Employee e ON e.BusinessEntityID = s.BusinessEntityID
WHERE s.TerritoryID IS NULL
ORDER BY s.BusinessEntityID ASC
;

-- how many sales persons each territory has; all 10, 3 in 1 (Northwest), 2 in 4 and 6, and 1 in each other.
SELECT TerritoryID, COUNT(*) AS sales_persons
FROM Sales.SalesPerson
WHERE TerritoryID IS NOT NULL
GROUP BY TerritoryID
ORDER BY TerritoryID ASC
;


-- ---------------------------------------------------------------------------------------------------------- SalesQuota
-- SalesQuota is nullable, above 0 by CK_SalesPerson_SalesQuota; how many have none, and the range of the others.
-- 3; 250000 to 300000.
SELECT COUNT(*) - COUNT(SalesQuota) AS without, MIN(SalesQuota), MAX(SalesQuota)
FROM Sales.SalesPerson
;

-- a SalesQuota which is none of the sales person's SalesPersonQuotaHistory; all 14 of them, the table and the history
-- disagree.
SELECT COUNT(*) AS sales_persons
FROM Sales.SalesPerson s
WHERE s.SalesQuota IS NOT NULL
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesPersonQuotaHistory q
                  WHERE q.BusinessEntityID = s.BusinessEntityID
                    AND q.SalesQuota = s.SalesQuota)
;


-- --------------------------------------------------------------------------------------------------------------- Bonus
-- the range of Bonus; 0 to 6700, 0 only for the 3 with no territory.
SELECT MIN(Bonus), MAX(Bonus), SUM(CASE WHEN Bonus = 0 THEN 1 ELSE 0 END) AS without
FROM Sales.SalesPerson
;


-- ------------------------------------------------------------------------------------------------------- CommissionPct
-- the range of CommissionPct; 0 to .02, 0 only for the 3 with no territory.
SELECT MIN(CommissionPct), MAX(CommissionPct), SUM(CASE WHEN CommissionPct = 0 THEN 1 ELSE 0 END) AS without
FROM Sales.SalesPerson
;


-- ------------------------------------------------------------------------------------------------------------ SalesYTD
-- the range of SalesYTD; 172524.4512 to 4251368.5497.
SELECT MIN(SalesYTD), MAX(SalesYTD)
FROM Sales.SalesPerson
;


-- ------------------------------------------------------------------------------------------------------- SalesLastYear
-- the range of SalesLastYear, and how many are 0; 0 to 2396539.7601, 0 for 4.
SELECT MIN(SalesLastYear), MAX(SalesLastYear), SUM(CASE WHEN SalesLastYear = 0 THEN 1 ELSE 0 END) AS zero
FROM Sales.SalesPerson
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_SalesPerson_rowguid keeps rowguid unique.
-- no rows: no two sales persons share a rowguid.
SELECT rowguid, COUNT(*) AS sales_persons
FROM Sales.SalesPerson
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2021-12-27 to 2024-05-22.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Sales.SalesPerson
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the orders of each sales person, the five most; 277 with 473, 275 with 450, 279, 276 and 289 with 348.
SELECT TOP (5) s.BusinessEntityID, COUNT(h.SalesOrderID) AS orders
FROM Sales.SalesPerson s
         LEFT JOIN Sales.SalesOrderHeader h ON h.SalesPersonID = s.BusinessEntityID
GROUP BY s.BusinessEntityID
ORDER BY orders DESC, s.BusinessEntityID ASC
;

-- a sales person who sold nothing.
-- no rows: every sales person has orders.
SELECT s.BusinessEntityID
FROM Sales.SalesPerson s
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderHeader h
                  WHERE h.SalesPersonID = s.BusinessEntityID)
;

-- how many stores each sales person looks after; 38 to 80 each, none for the 3 managers and for 284.
SELECT s.BusinessEntityID, COUNT(t.BusinessEntityID) AS stores
FROM Sales.SalesPerson s
         LEFT JOIN Sales.Store t ON t.SalesPersonID = s.BusinessEntityID
GROUP BY s.BusinessEntityID
ORDER BY s.BusinessEntityID ASC
;

