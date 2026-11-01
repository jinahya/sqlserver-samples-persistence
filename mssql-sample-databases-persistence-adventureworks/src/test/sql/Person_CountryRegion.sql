-- -
-- #%L
-- mssql-sample-databases-persistence-adventureworks
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
-- Profiling queries on Person.CountryRegion, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_CountryRegion.sql
--


-- --------------------------------------------------------------------------------------------------- CountryRegionCode
-- how many rows, and the shortest and the longest CountryRegionCode; 238 countries and regions, every code 2
-- characters, of the 3 the column allows.
SELECT COUNT(*), MIN(LEN(CountryRegionCode)), MAX(LEN(CountryRegionCode))
FROM Person.CountryRegion
;

-- a CountryRegionCode which is not two or three upper case letters, or carries leading or trailing spaces.
-- no rows: every code is upper case ASCII letters, trimmed.
SELECT '[' + CountryRegionCode + ']' AS CountryRegionCode, Name
FROM Person.CountryRegion
WHERE (CountryRegionCode COLLATE Latin1_General_BIN NOT LIKE '[A-Z][A-Z]'
    AND CountryRegionCode COLLATE Latin1_General_BIN NOT LIKE '[A-Z][A-Z][A-Z]')
   OR DATALENGTH(CountryRegionCode) <> DATALENGTH(TRIM(CountryRegionCode))
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 4 and 44 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Person.CountryRegion
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT CountryRegionCode, '[' + Name + ']' AS Name
FROM Person.CountryRegion
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_CountryRegion_Name keeps Name unique.
-- no rows: no two countries or regions share a Name.
SELECT Name, COUNT(*) AS country_regions
FROM Person.CountryRegion
GROUP BY Name
HAVING COUNT(*) > 1
;

-- a Name with a character outside printable ASCII, which the nvarchar column keeps; 4 names, Côte d'Ivoire (CI),
-- Réunion (RE), São Tomé and Príncipe (ST) and Türkiye (TR).
SELECT CountryRegionCode, Name
FROM Person.CountryRegion
WHERE Name COLLATE Latin1_General_BIN LIKE N'%[^ -~]%'
ORDER BY CountryRegionCode ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.CountryRegion
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the countries and regions StateProvince divides; 12 of the 238.
SELECT COUNT(DISTINCT CountryRegionCode) AS country_regions
FROM Person.StateProvince
;

-- the five countries and regions with the most state provinces; France (FR) with 96, United States (US) with 53,
-- Canada (CA) with 13, Germany (DE) with 7 and Australia (AU) with 5.
SELECT TOP (5) c.CountryRegionCode, c.Name, COUNT(*) AS state_provinces
FROM Person.CountryRegion c
         JOIN Person.StateProvince s ON s.CountryRegionCode = c.CountryRegionCode
GROUP BY c.CountryRegionCode, c.Name
ORDER BY state_provinces DESC, c.CountryRegionCode ASC
;

-- a country or region no SalesTerritory sells in; 232 of the 238, every one but the 6 the territories name.
SELECT COUNT(*) AS without_territory
FROM Person.CountryRegion c
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SalesTerritory t
                  WHERE t.CountryRegionCode = c.CountryRegionCode)
;

