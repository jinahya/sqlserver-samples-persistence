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
-- Profiling queries on the view Sales.vPersonDemographics, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_vPersonDemographics.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows and persons; 19972 rows, one for every person, 1 to 20777.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM Sales.vPersonDemographics
;

-- the types of the persons, and how many of them have a survey; all 19972, and only the 18484 individual customers
-- (IN) a filled one. Every column but TotalPurchaseYTD is NULL for the other 1488.
SELECT p.PersonType, COUNT(*) AS persons, COUNT(v.BirthDate) AS surveyed
FROM Sales.vPersonDemographics v
         JOIN Person.Person p ON p.BusinessEntityID = v.BusinessEntityID
GROUP BY p.PersonType
ORDER BY p.PersonType ASC
;


-- ---------------------------------------------------------------------------------------------------- TotalPurchaseYTD
-- how many rows have no TotalPurchaseYTD, its range, and how many are negative; none; -48895.7939 to 7214.5636, 11262
-- below zero.
SELECT COUNT(*) - COUNT(TotalPurchaseYTD) AS without, MIN(TotalPurchaseYTD), MAX(TotalPurchaseYTD),
       SUM(CASE WHEN TotalPurchaseYTD < 0 THEN 1 ELSE 0 END) AS negative
FROM Sales.vPersonDemographics
;


-- --------------------------------------------------------------------------------------------------- DateFirstPurchase
-- how many rows have no DateFirstPurchase, and the range of the others; 1488; 2001-07-01 to 2004-07-31, while the
-- installed orders run from 2022-05-30.
SELECT COUNT(*) - COUNT(DateFirstPurchase) AS without, MIN(DateFirstPurchase), MAX(DateFirstPurchase)
FROM Sales.vPersonDemographics
;


-- ----------------------------------------------------------------------------------------------------------- BirthDate
-- how many rows have no BirthDate, and the range of the others; 1488; 1910-08-13 to 1980-12-26.
SELECT COUNT(*) - COUNT(BirthDate) AS without, MIN(BirthDate), MAX(BirthDate)
FROM Sales.vPersonDemographics
;


-- ------------------------------------------------------------------------------------------------------- MaritalStatus
-- how many rows each MaritalStatus has; M 10011, S 8473, and NULL 1488.
SELECT MaritalStatus, COUNT(*) AS persons
FROM Sales.vPersonDemographics
GROUP BY MaritalStatus
ORDER BY MaritalStatus ASC
;


-- -------------------------------------------------------------------------------------------------------- YearlyIncome
-- how many rows each YearlyIncome has; 5 bands, from 0-25000 to greater than 100000, 25001-50000 the largest.
SELECT YearlyIncome, COUNT(*) AS persons
FROM Sales.vPersonDemographics
GROUP BY YearlyIncome
ORDER BY YearlyIncome ASC
;


-- -------------------------------------------------------------------------------------------------------------- Gender
-- how many rows each Gender has; F 9133, M 9351, and NULL 1488.
SELECT Gender, COUNT(*) AS persons
FROM Sales.vPersonDemographics
GROUP BY Gender
ORDER BY Gender ASC
;


-- ------------------------------------------------------------------------------------------------------- TotalChildren
-- how many rows each TotalChildren has; 0 to 5, 0 the most with 5165.
SELECT TotalChildren, COUNT(*) AS persons
FROM Sales.vPersonDemographics
GROUP BY TotalChildren
ORDER BY TotalChildren ASC
;


-- ------------------------------------------------------------------------------------------------ NumberChildrenAtHome
-- how many rows each NumberChildrenAtHome has; 0 to 5, 0 the most with 11116.
SELECT NumberChildrenAtHome, COUNT(*) AS persons
FROM Sales.vPersonDemographics
GROUP BY NumberChildrenAtHome
ORDER BY NumberChildrenAtHome ASC
;

-- a person with more children at home than children; 1431.
SELECT COUNT(*) AS persons
FROM Sales.vPersonDemographics
WHERE NumberChildrenAtHome > TotalChildren
;


-- ----------------------------------------------------------------------------------------------------------- Education
-- how many rows each Education has; 5 values, 'Bachelors ' with a trailing space the most with 5356.
SELECT '[' + Education + ']' AS Education, COUNT(*) AS persons
FROM Sales.vPersonDemographics
GROUP BY Education
ORDER BY Education ASC
;


-- ---------------------------------------------------------------------------------------------------------- Occupation
-- how many rows each Occupation has; 5 values, Professional the most with 5520.
SELECT Occupation, COUNT(*) AS persons
FROM Sales.vPersonDemographics
GROUP BY Occupation
ORDER BY Occupation ASC
;


-- ------------------------------------------------------------------------------------------------------- HomeOwnerFlag
-- how many rows each HomeOwnerFlag has; 1 for 12502, 0 for 5982, and NULL 1488.
SELECT HomeOwnerFlag, COUNT(*) AS persons
FROM Sales.vPersonDemographics
GROUP BY HomeOwnerFlag
ORDER BY HomeOwnerFlag ASC
;


-- ----------------------------------------------------------------------------------------------------- NumberCarsOwned
-- how many rows each NumberCarsOwned has; 0 to 4, 2 the most with 6457.
SELECT NumberCarsOwned, COUNT(*) AS persons
FROM Sales.vPersonDemographics
GROUP BY NumberCarsOwned
ORDER BY NumberCarsOwned ASC
;

