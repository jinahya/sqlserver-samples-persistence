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
-- Profiling queries on Person.StateProvince, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_StateProvince.sql
--


-- ----------------------------------------------------------------------------------------------------- StateProvinceID
-- how many rows, and the smallest and the largest StateProvinceID; 181 state provinces, 1 to 181.
SELECT COUNT(*), MIN(StateProvinceID), MAX(StateProvinceID)
FROM Person.StateProvince
;

-- a StateProvinceID whose next value is missing, below the largest.
-- no rows: StateProvinceID runs from 1 without a gap.
SELECT a.StateProvinceID + 1 AS missing_from
FROM Person.StateProvince a
WHERE a.StateProvinceID < (SELECT MAX(StateProvinceID) FROM Person.StateProvince)
  AND NOT EXISTS (SELECT 1
                  FROM Person.StateProvince b
                  WHERE b.StateProvinceID = a.StateProvinceID + 1)
;


-- --------------------------------------------------------------------------------------------------- StateProvinceCode
-- StateProvinceCode is an nchar(3), so a shorter code is padded with spaces; how many codes of each length.
-- 176 of 2 characters and 5 of 3: ENG (GB), and NSW, QLD, TAS and VIC (AU).
SELECT LEN(StateProvinceCode) AS length, COUNT(*) AS state_provinces
FROM Person.StateProvince
GROUP BY LEN(StateProvinceCode)
ORDER BY length ASC
;

-- a StateProvinceCode with a character other than an upper case letter or a digit, the padding aside.
-- no rows: every code is upper case letters and digits.
SELECT StateProvinceID, '[' + StateProvinceCode + ']' AS StateProvinceCode
FROM Person.StateProvince
WHERE RTRIM(StateProvinceCode) COLLATE Latin1_General_BIN LIKE '%[^A-Z0-9]%'
   OR RTRIM(StateProvinceCode) = ''
;

-- AK_StateProvince_StateProvinceCode_CountryRegionCode keeps the pair unique; the schema does not keep the code
-- unique alone. A code more than one state province has.
-- no rows: every code is unique, across the countries too.
SELECT StateProvinceCode, COUNT(*) AS state_provinces
FROM Person.StateProvince
GROUP BY StateProvinceCode
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------- CountryRegionCode
-- the countries and regions the state provinces fall in, the largest first; 12, France (FR) with 96, United States
-- (US) with 53, Canada (CA) with 13, Germany (DE) with 7, Australia (AU) with 5, and seven with 1 each.
SELECT CountryRegionCode, COUNT(*) AS state_provinces
FROM Person.StateProvince
GROUP BY CountryRegionCode
ORDER BY state_provinces DESC, CountryRegionCode ASC
;


-- --------------------------------------------------------------------------------------------- IsOnlyStateProvinceFlag
-- how many rows carry each flag; 0 on 173, 1 on 8.
SELECT IsOnlyStateProvinceFlag, COUNT(*) AS state_provinces
FROM Person.StateProvince
GROUP BY IsOnlyStateProvinceFlag
ORDER BY IsOnlyStateProvinceFlag ASC
;

-- the flag says the row stands for its whole country or region; a country or region whose flagged row is not alone.
-- France (FR): its flagged row, France (84), sits among 95 departments.
SELECT CountryRegionCode, COUNT(*) AS state_provinces
FROM Person.StateProvince
GROUP BY CountryRegionCode
HAVING MAX(CAST(IsOnlyStateProvinceFlag AS int)) = 1
   AND COUNT(*) > 1
;

-- a flagged row whose Name is not the Name of its country or region; England (14) for United Kingdom and Virgin
-- Islands (76) for Virgin Islands, U.S.
SELECT s.StateProvinceID, s.Name, c.Name AS CountryRegionName
FROM Person.StateProvince s
         JOIN Person.CountryRegion c ON c.CountryRegionCode = s.CountryRegionCode
WHERE s.IsOnlyStateProvinceFlag = 1
  AND s.Name <> c.Name
ORDER BY s.StateProvinceID ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 3 and 24 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Person.StateProvince
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT StateProvinceID, '[' + Name + ']' AS Name
FROM Person.StateProvince
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_StateProvince_Name keeps Name unique, across the countries too.
-- no rows: no two state provinces share a Name.
SELECT Name, COUNT(*) AS state_provinces
FROM Person.StateProvince
GROUP BY Name
HAVING COUNT(*) > 1
;

-- a Name with a character outside printable ASCII, which the nvarchar column keeps; 21 names.
SELECT COUNT(*) AS state_provinces
FROM Person.StateProvince
WHERE Name COLLATE Latin1_General_BIN LIKE N'%[^ -~]%'
;


-- --------------------------------------------------------------------------------------------------------- TerritoryID
-- the sales territories the state provinces fall in; all 10, 1 to 10, France (7) the largest with 96.
SELECT TerritoryID, COUNT(*) AS state_provinces
FROM Person.StateProvince
GROUP BY TerritoryID
ORDER BY TerritoryID ASC
;

-- a state province whose territory sells in another country or region; 6, the islands the US and Australia
-- territories take in: American Samoa (5), Micronesia (16), Marshall Islands (34), Northern Mariana Islands (38),
-- Palau (62) and Virgin Islands (76).
SELECT s.StateProvinceID, s.Name, s.CountryRegionCode, t.TerritoryID, t.CountryRegionCode AS TerritoryCountryRegionCode
FROM Person.StateProvince s
         JOIN Sales.SalesTerritory t ON t.TerritoryID = s.TerritoryID
WHERE s.CountryRegionCode <> t.CountryRegionCode
ORDER BY s.StateProvinceID ASC
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_StateProvince_rowguid keeps rowguid unique.
-- no rows: no two state provinces share a rowguid.
SELECT rowguid, COUNT(*) AS state_provinces
FROM Person.StateProvince
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the moments the installed rows carry; 2019-04-30 00:00:00.000 on 98, and 2025-02-07 10:17:21.587 on 83, the rows
-- 1 to 83.
SELECT ModifiedDate, COUNT(*) AS state_provinces, MIN(StateProvinceID), MAX(StateProvinceID)
FROM Person.StateProvince
GROUP BY ModifiedDate
ORDER BY ModifiedDate ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a state province no Address is in; 107 of the 181.
SELECT COUNT(*) AS without_address
FROM Person.StateProvince s
WHERE NOT EXISTS (SELECT 1
                  FROM Person.Address a
                  WHERE a.StateProvinceID = s.StateProvinceID)
;

-- the three state provinces with the most addresses; California (9) with 4564, Washington (79) with 2636 and England
-- (14) with 1954.
SELECT TOP (3) s.StateProvinceID, s.Name, COUNT(*) AS addresses
FROM Person.StateProvince s
         JOIN Person.Address a ON a.StateProvinceID = s.StateProvinceID
GROUP BY s.StateProvinceID, s.Name
ORDER BY addresses DESC, s.StateProvinceID ASC
;

