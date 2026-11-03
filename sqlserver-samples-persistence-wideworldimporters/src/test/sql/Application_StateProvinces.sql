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
-- Profiling queries on Application.StateProvinces, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_StateProvinces.sql
--


-- ----------------------------------------------------------------------------------------------------- StateProvinceID
-- how many rows, and the smallest and the largest StateProvinceID; 53 states and provinces, 1 to 53.
SELECT COUNT(*), MIN(StateProvinceID), MAX(StateProvinceID)
FROM Application.StateProvinces
;


-- --------------------------------------------------------------------------------------------------- StateProvinceCode
-- a StateProvinceCode which is not two upper case letters.
-- no rows: every code is two letters, A to Z.
SELECT StateProvinceID, StateProvinceCode
FROM Application.StateProvinces
WHERE StateProvinceCode COLLATE Latin1_General_BIN NOT LIKE '[A-Z][A-Z]'
;

-- the schema has no unique key on StateProvinceCode; a code more than one row has.
-- no rows: every StateProvinceCode is unique.
SELECT StateProvinceCode, COUNT(*) AS state_provinces
FROM Application.StateProvinces
GROUP BY StateProvinceCode
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------- StateProvinceName
-- the shortest and the longest StateProvinceName; 4 and 29 characters, of the 50 the column allows.
SELECT MIN(LEN(StateProvinceName)), MAX(LEN(StateProvinceName))
FROM Application.StateProvinces
;

-- a StateProvinceName which is blank, or carries leading or trailing spaces.
-- no rows: every StateProvinceName is trimmed and not blank.
SELECT StateProvinceID, '[' + StateProvinceName + ']' AS StateProvinceName
FROM Application.StateProvinces
WHERE TRIM(StateProvinceName) = ''
   OR DATALENGTH(StateProvinceName) <> DATALENGTH(TRIM(StateProvinceName))
;

-- a StateProvinceName with a character other than a letter, a space or a parenthesis.
-- one row: Massachusetts[E] (22), a footnote marker left in the name.
SELECT StateProvinceID, StateProvinceName
FROM Application.StateProvinces
WHERE StateProvinceName COLLATE Latin1_General_BIN LIKE '%[^A-Za-z ()]%'
;

-- UQ_Application_StateProvinces_StateProvinceName keeps StateProvinceName unique.
-- no rows: no two rows share a StateProvinceName.
SELECT StateProvinceName, COUNT(*) AS state_provinces
FROM Application.StateProvinces
GROUP BY StateProvinceName
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- CountryID
-- the countries the rows are of; all 53 are of United States (230).
SELECT s.CountryID, c.CountryName, COUNT(*) AS state_provinces
FROM Application.StateProvinces s
         JOIN Application.Countries c ON c.CountryID = s.CountryID
GROUP BY s.CountryID, c.CountryName
ORDER BY s.CountryID ASC
;


-- ------------------------------------------------------------------------------------------------------ SalesTerritory
-- the sales territories the rows fall in; 9, Southeast the largest with 12, and 2 External.
SELECT SalesTerritory, COUNT(*) AS state_provinces
FROM Application.StateProvinces
GROUP BY SalesTerritory
ORDER BY SalesTerritory ASC
;


-- -------------------------------------------------------------------------------------------------------------- Border
-- Border is a nullable geography; how many rows have none, and how large the others are in bytes.
-- One, Virgin Islands (US Territory) (48); 192 to 36957 bytes.
SELECT COUNT(*) - COUNT(Border) AS without, MIN(DATALENGTH(Border)), MAX(DATALENGTH(Border))
FROM Application.StateProvinces
;

-- a Border which is not a valid geography.
-- no rows: every Border is valid.
SELECT StateProvinceID, StateProvinceName
FROM Application.StateProvinces
WHERE Border.STIsValid() = 0
;


-- -------------------------------------------------------------------------------------------- LatestRecordedPopulation
-- LatestRecordedPopulation is nullable; how many rows have none, and the smallest and the largest.
-- None; 104737 (Virgin Islands (US Territory)) to 41460453 (California).
SELECT COUNT(*) - COUNT(LatestRecordedPopulation) AS without,
       MIN(LatestRecordedPopulation), MAX(LatestRecordedPopulation)
FROM Application.StateProvinces
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who last edited the rows; person 1, Data Conversion Only, for 19, the rest by 8, 15 and 20.
SELECT s.LastEditedBy, p.FullName, COUNT(*) AS state_provinces
FROM Application.StateProvinces s
         JOIN Application.People p ON p.PersonID = s.LastEditedBy
GROUP BY s.LastEditedBy, p.FullName
ORDER BY s.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- when the current rows begin; 19 in the first five minutes of 2013-01-01, then 9, 15 and 10 on 1 July of 2013,
-- 2014 and 2015.
SELECT ValidFrom, COUNT(*) AS state_provinces
FROM Application.StateProvinces
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a current row of a system-versioned table is valid until the end of time.
-- no rows: every ValidTo is 9999-12-31 23:59:59.9999999.
SELECT StateProvinceID, ValidTo
FROM Application.StateProvinces
WHERE ValidTo <> '9999-12-31 23:59:59.9999999'
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a state or province with no city.
-- no rows: every one has at least one city.
SELECT s.StateProvinceID, s.StateProvinceName
FROM Application.StateProvinces s
WHERE NOT EXISTS (SELECT 1
                  FROM Application.Cities c
                  WHERE c.StateProvinceID = s.StateProvinceID)
;

-- the states and provinces with the most cities; Texas (45) with 2395, Pennsylvania (39) 1830, New York (33) 1650.
SELECT TOP (3) s.StateProvinceID, s.StateProvinceName, COUNT(*) AS cities
FROM Application.StateProvinces s
         JOIN Application.Cities c ON c.StateProvinceID = s.StateProvinceID
GROUP BY s.StateProvinceID, s.StateProvinceName
ORDER BY cities DESC, s.StateProvinceID ASC
;

-- the states and provinces with the fewest cities; District of Columbia (9) with 1, Virgin Islands (US Territory)
-- (48) 4, Rhode Island (41) 60.
SELECT TOP (3) s.StateProvinceID, s.StateProvinceName, COUNT(*) AS cities
FROM Application.StateProvinces s
         JOIN Application.Cities c ON c.StateProvinceID = s.StateProvinceID
GROUP BY s.StateProvinceID, s.StateProvinceName
ORDER BY cities ASC, s.StateProvinceID ASC
;

