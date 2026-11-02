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
-- Profiling queries on the view Sales.vSalesPerson, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_vSalesPerson.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows and sales persons; 17 of each, one row per Sales.SalesPerson.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM Sales.vSalesPerson
;

-- a sales person missing from the view.
-- no rows: every Sales.SalesPerson is in it.
SELECT s.BusinessEntityID
FROM Sales.SalesPerson s
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.vSalesPerson v
                  WHERE v.BusinessEntityID = s.BusinessEntityID)
;


-- --------------------------------------------------------------------------------------------------------------- Title
-- how many rows have a Title; 2.
SELECT COUNT(Title) AS sales_persons
FROM Sales.vSalesPerson
;


-- ----------------------------------------------------------------------------------------------------------- FirstName
-- the shortest and the longest FirstName; 3 and 7 characters.
SELECT MIN(LEN(FirstName)), MAX(LEN(FirstName))
FROM Sales.vSalesPerson
;


-- ---------------------------------------------------------------------------------------------------------- MiddleName
-- how many rows have a MiddleName; 16.
SELECT COUNT(MiddleName) AS sales_persons
FROM Sales.vSalesPerson
;


-- ------------------------------------------------------------------------------------------------------------ LastName
-- the shortest and the longest LastName; 3 and 17 characters.
SELECT MIN(LEN(LastName)), MAX(LEN(LastName))
FROM Sales.vSalesPerson
;


-- -------------------------------------------------------------------------------------------------------------- Suffix
-- how many rows have a Suffix; none.
SELECT COUNT(Suffix) AS sales_persons
FROM Sales.vSalesPerson
;


-- ------------------------------------------------------------------------------------------------------------ JobTitle
-- how many rows each JobTitle has; 14 Sales Representatives and 3 sales managers.
SELECT JobTitle, COUNT(*) AS sales_persons
FROM Sales.vSalesPerson
GROUP BY JobTitle
ORDER BY JobTitle ASC
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- how many rows have no PhoneNumber, and how long the others are; none; 12 to 19 characters.
SELECT COUNT(*) - COUNT(PhoneNumber) AS without, MIN(LEN(PhoneNumber)), MAX(LEN(PhoneNumber))
FROM Sales.vSalesPerson
;


-- ----------------------------------------------------------------------------------------------------- PhoneNumberType
-- how many rows each PhoneNumberType has; Cell 7 and Work 10.
SELECT PhoneNumberType, COUNT(*) AS sales_persons
FROM Sales.vSalesPerson
GROUP BY PhoneNumberType
ORDER BY PhoneNumberType ASC
;


-- -------------------------------------------------------------------------------------------------------- EmailAddress
-- how many rows have no EmailAddress, and how long the others are; none; 24 to 28 characters.
SELECT COUNT(*) - COUNT(EmailAddress) AS without, MIN(LEN(EmailAddress)), MAX(LEN(EmailAddress))
FROM Sales.vSalesPerson
;


-- ------------------------------------------------------------------------------------------------------ EmailPromotion
-- how many rows each EmailPromotion has; 0 for 9, 1 for 6 and 2 for 2.
SELECT EmailPromotion, COUNT(*) AS sales_persons
FROM Sales.vSalesPerson
GROUP BY EmailPromotion
ORDER BY EmailPromotion ASC
;


-- -------------------------------------------------------------------------------------------------------- AddressLine1
-- the shortest and the longest AddressLine1; 13 and 26 characters.
SELECT MIN(LEN(AddressLine1)), MAX(LEN(AddressLine1))
FROM Sales.vSalesPerson
;


-- -------------------------------------------------------------------------------------------------------- AddressLine2
-- how many rows have an AddressLine2; none.
SELECT COUNT(AddressLine2) AS sales_persons
FROM Sales.vSalesPerson
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- how many cities; 16.
SELECT COUNT(DISTINCT City)
FROM Sales.vSalesPerson
;


-- --------------------------------------------------------------------------------------------------- StateProvinceName
-- how many states and provinces; 14.
SELECT COUNT(DISTINCT StateProvinceName)
FROM Sales.vSalesPerson
;


-- ---------------------------------------------------------------------------------------------------------- PostalCode
-- how many postal codes; 17, one per sales person.
SELECT COUNT(DISTINCT PostalCode)
FROM Sales.vSalesPerson
;


-- --------------------------------------------------------------------------------------------------- CountryRegionName
-- how many rows each CountryRegionName has; United States 11, Canada 2, and Australia, France, Germany and United
-- Kingdom 1 each.
SELECT CountryRegionName, COUNT(*) AS sales_persons
FROM Sales.vSalesPerson
GROUP BY CountryRegionName
ORDER BY CountryRegionName ASC
;

-- a sales person whose territory lies in another country than they live in.
-- no rows: each lives in the country of their territory.
SELECT v.BusinessEntityID, v.CountryRegionName, v.TerritoryName
FROM Sales.vSalesPerson v
         JOIN Sales.SalesTerritory t ON t.Name = v.TerritoryName
         JOIN Person.CountryRegion c ON c.CountryRegionCode = t.CountryRegionCode
WHERE c.Name <> v.CountryRegionName
;


-- ------------------------------------------------------------------------------------------------------- TerritoryName
-- how many rows have no TerritoryName; 3, the managers.
SELECT COUNT(*) - COUNT(TerritoryName) AS without
FROM Sales.vSalesPerson
;


-- ------------------------------------------------------------------------------------------------------ TerritoryGroup
-- how many rows each TerritoryGroup has; North America 10, Europe 3, Pacific 1, and NULL 3.
SELECT TerritoryGroup, COUNT(*) AS sales_persons
FROM Sales.vSalesPerson
GROUP BY TerritoryGroup
ORDER BY sales_persons DESC, TerritoryGroup ASC
;


-- ---------------------------------------------------------------------------------------------------------- SalesQuota
-- a SalesQuota, SalesYTD or SalesLastYear which is not that of Sales.SalesPerson.
-- no rows: the view copies the three.
SELECT v.BusinessEntityID
FROM Sales.vSalesPerson v
         JOIN Sales.SalesPerson s ON s.BusinessEntityID = v.BusinessEntityID
WHERE ISNULL(v.SalesQuota, -1) <> ISNULL(s.SalesQuota, -1)
   OR v.SalesYTD <> s.SalesYTD
   OR v.SalesLastYear <> s.SalesLastYear
;


-- ------------------------------------------------------------------------------------------------------------ SalesYTD
-- the range of SalesYTD; 172524.4512 to 4251368.5497.
SELECT MIN(SalesYTD), MAX(SalesYTD)
FROM Sales.vSalesPerson
;


-- ------------------------------------------------------------------------------------------------------- SalesLastYear
-- the range of SalesLastYear; 0 to 2396539.7601.
SELECT MIN(SalesLastYear), MAX(SalesLastYear)
FROM Sales.vSalesPerson
;

