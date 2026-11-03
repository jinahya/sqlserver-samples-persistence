-- -
-- #%L
-- sqlserver-samples-persistence-adventureworks
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
-- Profiling queries on Sales.SalesTerritory, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SalesTerritory.sql
--


-- --------------------------------------------------------------------------------------------------------- TerritoryID
-- how many rows, and the smallest and the largest TerritoryID; 10 territories, 1 to 10.
SELECT COUNT(*), MIN(TerritoryID), MAX(TerritoryID)
FROM Sales.SalesTerritory
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 6 and 14 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Sales.SalesTerritory
;

-- a Name which is blank, or carries leading or trailing spaces.
-- no rows: every Name is trimmed and not blank.
SELECT TerritoryID, '[' + Name + ']' AS Name
FROM Sales.SalesTerritory
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_SalesTerritory_Name keeps Name unique.
-- no rows: no two territories share a Name.
SELECT Name, COUNT(*) AS territories
FROM Sales.SalesTerritory
GROUP BY Name
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------- CountryRegionCode
-- how many territories each country has; US 5, and AU, CA, DE, FR and GB 1 each.
SELECT CountryRegionCode, COUNT(*) AS territories
FROM Sales.SalesTerritory
GROUP BY CountryRegionCode
ORDER BY CountryRegionCode ASC
;


-- --------------------------------------------------------------------------------------------------------------- Group
-- how many territories each Group has; Europe 3, North America 6 and Pacific 1.
SELECT [
Group], COUNT (*) AS territories
FROM Sales.SalesTerritory
GROUP BY [
Group]
ORDER BY [
Group] ASC
;

-- a country whose territories fall in more than one Group.
-- no rows: a country is in a single Group.
SELECT CountryRegionCode, COUNT(DISTINCT [Group]) AS groups
FROM Sales.SalesTerritory
GROUP BY CountryRegionCode
HAVING COUNT(DISTINCT [Group]) > 1
;


-- ------------------------------------------------------------------------------------------------------------ SalesYTD
-- the range of SalesYTD; 2402176.8476 (2, Northeast) to 10510853.8739 (4, Southwest).
SELECT MIN(SalesYTD), MAX(SalesYTD)
FROM Sales.SalesTerritory
;

-- a territory whose SalesYTD is not the sum of its sales persons'; all 10, the territory holding more than its sales
-- persons in all but 2 (Northeast) and 3 (Central).
SELECT t.TerritoryID, t.SalesYTD, SUM(p.SalesYTD) AS sales_persons
FROM Sales.SalesTerritory t
         LEFT JOIN Sales.SalesPerson p ON p.TerritoryID = t.TerritoryID
GROUP BY t.TerritoryID, t.SalesYTD
HAVING t.SalesYTD <> COALESCE(SUM(p.SalesYTD), 0)
ORDER BY t.TerritoryID ASC
;


-- ------------------------------------------------------------------------------------------------------- SalesLastYear
-- the range of SalesLastYear; 1307949.7917 to 5693988.86.
SELECT MIN(SalesLastYear), MAX(SalesLastYear)
FROM Sales.SalesTerritory
;


-- ------------------------------------------------------------------------------------------------------------- CostYTD
-- the range of CostYTD; 0 for all 10.
SELECT MIN(CostYTD), MAX(CostYTD)
FROM Sales.SalesTerritory
;


-- -------------------------------------------------------------------------------------------------------- CostLastYear
-- the range of CostLastYear; 0 for all 10.
SELECT MIN(CostLastYear), MAX(CostLastYear)
FROM Sales.SalesTerritory
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_SalesTerritory_rowguid keeps rowguid unique.
-- no rows: no two territories share a rowguid.
SELECT rowguid, COUNT(*) AS territories
FROM Sales.SalesTerritory
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Sales.SalesTerritory
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a territory with no state or province.
-- no rows: every territory has at least one Person.StateProvince.
SELECT t.TerritoryID, t.Name
FROM Sales.SalesTerritory t
WHERE NOT EXISTS (SELECT 1
                  FROM Person.StateProvince s
                  WHERE s.TerritoryID = t.TerritoryID)
;

-- the customers, orders and sales persons of each territory; 113 (2) to 4696 (4) customers, 352 (2) to 6843 (9) orders.
SELECT t.TerritoryID,
       t.Name,
       (SELECT COUNT(*) FROM Sales.Customer c WHERE c.TerritoryID = t.TerritoryID)         AS customers,
       (SELECT COUNT(*)
        FROM Sales.SalesOrderHeader h
        WHERE h.TerritoryID = t.TerritoryID)                                               AS orders,
       (SELECT COUNT(*)
        FROM Sales.SalesPerson p
        WHERE p.TerritoryID = t.TerritoryID)                                               AS sales_persons
FROM Sales.SalesTerritory t
ORDER BY t.TerritoryID ASC
;

