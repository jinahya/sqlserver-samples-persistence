-- -
-- #%L
-- mssql-sample-databases-persistence-wideworldimporters
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
-- Profiling queries on Application.Countries, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_Countries.sql
--


-- ----------------------------------------------------------------------------------------------------------- CountryID
-- how many rows, and the smallest and the largest CountryID; 190 countries, 1 to 241.
SELECT COUNT(*), MIN(CountryID), MAX(CountryID)
FROM Application.Countries
;

-- CountryID comes from Sequences.CountryID and leaves gaps; how many values below the largest are unused. 51.
SELECT MAX(CountryID) - COUNT(*) AS unused
FROM Application.Countries
;


-- --------------------------------------------------------------------------------------------------------- CountryName
-- the shortest and the longest CountryName; 4 and 21 characters, of the 60 the column allows.
SELECT MIN(LEN(CountryName)), MAX(LEN(CountryName))
FROM Application.Countries
;

-- a CountryName which is blank, or carries leading or trailing spaces.
-- no rows: every CountryName is trimmed and not blank.
SELECT CountryID, '[' + CountryName + ']' AS CountryName
FROM Application.Countries
WHERE TRIM(CountryName) = ''
   OR DATALENGTH(CountryName) <> DATALENGTH(TRIM(CountryName))
;

-- UQ_Application_Countries_CountryName keeps CountryName unique.
-- no rows: no two countries share a CountryName.
SELECT CountryName, COUNT(*) AS countries
FROM Application.Countries
GROUP BY CountryName
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------------- FormalName
-- the shortest and the longest FormalName; 5 and 52 characters, of the 60 the column allows.
SELECT MIN(LEN(FormalName)), MAX(LEN(FormalName))
FROM Application.Countries
;

-- a FormalName which is blank, or carries leading or trailing spaces.
-- no rows: every FormalName is trimmed and not blank.
SELECT CountryID, '[' + FormalName + ']' AS FormalName
FROM Application.Countries
WHERE TRIM(FormalName) = ''
   OR DATALENGTH(FormalName) <> DATALENGTH(TRIM(FormalName))
;

-- UQ_Application_Countries_FormalName keeps FormalName unique.
-- no rows: no two countries share a FormalName.
SELECT FormalName, COUNT(*) AS countries
FROM Application.Countries
GROUP BY FormalName
HAVING COUNT(*) > 1
;

-- how many countries have a FormalName other than their CountryName; 169 of the 190.
SELECT COUNT(*) AS countries
FROM Application.Countries
WHERE FormalName <> CountryName
;


-- ------------------------------------------------------------------------------------------------------- IsoAlpha3Code
-- IsoAlpha3Code is nullable; how many countries have none, and how many distinct codes the others have. None; 190.
SELECT COUNT(*) - COUNT(IsoAlpha3Code) AS without, COUNT(DISTINCT IsoAlpha3Code) AS codes
FROM Application.Countries
;

-- an IsoAlpha3Code which is not three upper case letters.
-- no rows: every code is three letters, A to Z.
SELECT CountryID, IsoAlpha3Code
FROM Application.Countries
WHERE IsoAlpha3Code COLLATE Latin1_General_BIN NOT LIKE '[A-Z][A-Z][A-Z]'
;


-- ------------------------------------------------------------------------------------------------------ IsoNumericCode
-- IsoNumericCode is nullable; how many countries have none, how many distinct codes, and the smallest and the
-- largest. None; 190 distinct codes, 4 to 894.
SELECT COUNT(*) - COUNT(IsoNumericCode) AS without, COUNT(DISTINCT IsoNumericCode) AS codes,
       MIN(IsoNumericCode), MAX(IsoNumericCode)
FROM Application.Countries
;


-- --------------------------------------------------------------------------------------------------------- CountryType
-- the types the countries are of; every one a UN Member State, and none NULL.
SELECT CountryType, COUNT(*) AS countries
FROM Application.Countries
GROUP BY CountryType
ORDER BY CountryType ASC
;


-- -------------------------------------------------------------------------------------------- LatestRecordedPopulation
-- LatestRecordedPopulation is nullable; how many countries have none, and the smallest and the largest.
-- None; 14019 (Nauru) to 1392157488 (China).
SELECT COUNT(*) - COUNT(LatestRecordedPopulation) AS without,
       MIN(LatestRecordedPopulation), MAX(LatestRecordedPopulation)
FROM Application.Countries
;


-- ----------------------------------------------------------------------------------------------------------- Continent
-- the continents the countries are on; 7, Africa the largest with 50, and 3 in the Seven seas (open ocean).
SELECT Continent, COUNT(*) AS countries
FROM Application.Countries
GROUP BY Continent
ORDER BY Continent ASC
;


-- -------------------------------------------------------------------------------------------------------------- Region
-- the regions the countries are in; 5, from Africa with 52 to Oceania with 13.
SELECT Region, COUNT(*) AS countries
FROM Application.Countries
GROUP BY Region
ORDER BY Region ASC
;

-- Region does not follow Continent; the pairs that occur, and how many countries each.
-- 8 pairs; Region Africa holds 50 countries of Africa and 2 of the Seven seas, Asia 46 of Asia and 1 of the Seven seas.
SELECT Continent, Region, COUNT(*) AS countries
FROM Application.Countries
GROUP BY Continent, Region
ORDER BY Continent ASC, Region ASC
;


-- ----------------------------------------------------------------------------------------------------------- Subregion
-- how many subregions, and the shortest and the longest; 22, 9 and 25 characters, of the 30 the column allows.
SELECT COUNT(DISTINCT Subregion), MIN(LEN(Subregion)), MAX(LEN(Subregion))
FROM Application.Countries
;

-- a Subregion which falls in more than one Region.
-- no rows: every Subregion is in a single Region.
SELECT Subregion, COUNT(DISTINCT Region) AS regions
FROM Application.Countries
GROUP BY Subregion
HAVING COUNT(DISTINCT Region) > 1
;


-- -------------------------------------------------------------------------------------------------------------- Border
-- Border is a nullable geography; how many countries have none, and how large the others are in bytes.
-- None; 144 to 187169 bytes.
SELECT COUNT(*) - COUNT(Border) AS without, MIN(DATALENGTH(Border)), MAX(DATALENGTH(Border))
FROM Application.Countries
;

-- a Border which is not a valid geography; methods such as STGeometryType() fail on it.
-- one row: Russia (177).
SELECT CountryID, CountryName
FROM Application.Countries
WHERE Border.STIsValid() = 0
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who last edited the countries; person 1, Data Conversion Only, for 155, the rest by 8, 15 and 20.
SELECT c.LastEditedBy, p.FullName, COUNT(*) AS countries
FROM Application.Countries c
         JOIN Application.People p ON p.PersonID = c.LastEditedBy
GROUP BY c.LastEditedBy, p.FullName
ORDER BY c.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- when the current rows begin; 2013-01-01 for 154 countries, then 1 July of 2013, 2014 and 2015 for 10, 7 and 18,
-- and 2022-10-07 for Türkiye (222), renamed from Turkey.
SELECT ValidFrom, COUNT(*) AS countries
FROM Application.Countries
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a current row of a system-versioned table is valid until the end of time.
-- no rows: every ValidTo is 9999-12-31 23:59:59.9999999.
SELECT CountryID, ValidTo
FROM Application.Countries
WHERE ValidTo <> '9999-12-31 23:59:59.9999999'
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the states and provinces of each country that has any; only United States (230), with 53.
SELECT c.CountryID, c.CountryName, COUNT(*) AS state_provinces
FROM Application.Countries c
         JOIN Application.StateProvinces s ON s.CountryID = c.CountryID
GROUP BY c.CountryID, c.CountryName
ORDER BY c.CountryID ASC
;

-- the countries whose history the archive keeps, and how many versions; 36 countries, 37 versions.
SELECT COUNT(DISTINCT a.CountryID) AS countries, COUNT(*) AS versions
FROM Application.Countries_Archive a
;

