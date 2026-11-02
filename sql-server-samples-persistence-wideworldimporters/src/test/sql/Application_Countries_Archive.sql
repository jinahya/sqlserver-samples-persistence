-- -
-- #%L
-- sql-server-samples-persistence-wideworldimporters
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
-- Profiling queries on Application.Countries_Archive, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_Countries_Archive.sql
--


-- ----------------------------------------------------------------------------------------------------------- CountryID
-- how many rows, how many countries they keep the history of, and the smallest and the largest CountryID;
-- 37 versions of 36 countries, 3 to 222.
SELECT COUNT(*), COUNT(DISTINCT CountryID), MIN(CountryID), MAX(CountryID)
FROM Application.Countries_Archive
;

-- the archive has no primary key; a CountryID it keeps more than one version of.
-- one row: Australia (15), with 2.
SELECT CountryID, COUNT(*) AS versions
FROM Application.Countries_Archive
GROUP BY CountryID
HAVING COUNT(*) > 1
;

-- an archived CountryID with no current row; the history here is only of countries still current.
-- no rows: every archived CountryID is still current.
SELECT a.CountryID
FROM Application.Countries_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Application.Countries c
                  WHERE c.CountryID = a.CountryID)
;


-- --------------------------------------------------------------------------------------------------------- CountryName
-- an archived version whose names or ISO codes differ from the current row; one row, Turkey (222), now Türkiye,
-- its FormalName Republic of Turkey, now Republic of Türkiye.
SELECT a.CountryID, a.CountryName, c.CountryName AS current_name, a.FormalName, c.FormalName AS current_formal_name
FROM Application.Countries_Archive a
         JOIN Application.Countries c ON c.CountryID = a.CountryID
WHERE a.CountryName <> c.CountryName
   OR a.FormalName <> c.FormalName
   OR a.IsoAlpha3Code <> c.IsoAlpha3Code
   OR a.IsoNumericCode <> c.IsoNumericCode
;

-- a CountryName which is blank, or carries leading or trailing spaces.
-- no rows: every CountryName is trimmed and not blank.
SELECT CountryID, '[' + CountryName + ']' AS CountryName
FROM Application.Countries_Archive
WHERE TRIM(CountryName) = ''
   OR DATALENGTH(CountryName) <> DATALENGTH(TRIM(CountryName))
;


-- ---------------------------------------------------------------------------------------------------------- FormalName
-- a FormalName which is blank, or carries leading or trailing spaces.
-- no rows: every FormalName is trimmed and not blank.
SELECT CountryID, '[' + FormalName + ']' AS FormalName
FROM Application.Countries_Archive
WHERE TRIM(FormalName) = ''
   OR DATALENGTH(FormalName) <> DATALENGTH(TRIM(FormalName))
;


-- ------------------------------------------------------------------------------------------------------- IsoAlpha3Code
-- IsoAlpha3Code is nullable; how many versions have none. None.
SELECT COUNT(*) - COUNT(IsoAlpha3Code) AS without
FROM Application.Countries_Archive
;


-- ------------------------------------------------------------------------------------------------------ IsoNumericCode
-- IsoNumericCode is nullable; how many versions have none. None.
SELECT COUNT(*) - COUNT(IsoNumericCode) AS without
FROM Application.Countries_Archive
;


-- --------------------------------------------------------------------------------------------------------- CountryType
-- the types the versions are of; every one a UN Member State, and none NULL.
SELECT CountryType, COUNT(*) AS versions
FROM Application.Countries_Archive
GROUP BY CountryType
ORDER BY CountryType ASC
;


-- -------------------------------------------------------------------------------------------- LatestRecordedPopulation
-- an archived version whose population differs from the current row; 36 of the 37, all but Turkey's.
SELECT COUNT(*) AS versions
FROM Application.Countries_Archive a
         JOIN Application.Countries c ON c.CountryID = a.CountryID
WHERE a.LatestRecordedPopulation <> c.LatestRecordedPopulation
;

-- the populations Australia (15) went through; 21262641, then 22113146, now 22997671.
SELECT a.ValidFrom, a.ValidTo, a.LatestRecordedPopulation
FROM Application.Countries_Archive a
WHERE a.CountryID = 15
UNION ALL
SELECT c.ValidFrom, c.ValidTo, c.LatestRecordedPopulation
FROM Application.Countries c
WHERE c.CountryID = 15
ORDER BY ValidFrom ASC
;


-- ----------------------------------------------------------------------------------------------------------- Continent
-- an archived version whose Continent, Region or Subregion differs from the current row.
-- no rows: no country moved.
SELECT a.CountryID, a.Continent, a.Region, a.Subregion
FROM Application.Countries_Archive a
         JOIN Application.Countries c ON c.CountryID = a.CountryID
WHERE a.Continent <> c.Continent
   OR a.Region <> c.Region
   OR a.Subregion <> c.Subregion
;


-- -------------------------------------------------------------------------------------------------------------- Region
-- the regions of the archived versions; 5, Europe the largest with 12, the others 6 or 7.
SELECT Region, COUNT(*) AS versions
FROM Application.Countries_Archive
GROUP BY Region
ORDER BY Region ASC
;


-- ----------------------------------------------------------------------------------------------------------- Subregion
-- how many subregions the versions are in; 19.
SELECT COUNT(DISTINCT Subregion)
FROM Application.Countries_Archive
;


-- -------------------------------------------------------------------------------------------------------------- Border
-- Border is a nullable geography; how many versions have none, and how large the others are in bytes.
-- None; 311 to 187169 bytes.
SELECT COUNT(*) - COUNT(Border) AS without, MIN(DATALENGTH(Border)), MAX(DATALENGTH(Border))
FROM Application.Countries_Archive
;

-- a Border which is not a valid geography.
-- no rows: every archived Border is valid.
SELECT CountryID, CountryName
FROM Application.Countries_Archive
WHERE Border.STIsValid() = 0
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who edited the archived versions; person 1, Data Conversion Only, for 36, and 20, Jack Potter, for 1.
SELECT a.LastEditedBy, p.FullName, COUNT(*) AS versions
FROM Application.Countries_Archive a
         JOIN Application.People p ON p.PersonID = a.LastEditedBy
GROUP BY a.LastEditedBy, p.FullName
ORDER BY a.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the periods the archived versions were valid for; all but one begin on 2013-01-01, and they end on 1 July of 2013,
-- 2014 or 2015, but for Turkey's, which ends on 2022-10-07.
SELECT ValidFrom, ValidTo, COUNT(*) AS versions
FROM Application.Countries_Archive
GROUP BY ValidFrom, ValidTo
ORDER BY ValidFrom ASC, ValidTo ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a version which ends no later than it begins.
-- no rows: every ValidTo is after its ValidFrom.
SELECT CountryID, ValidFrom, ValidTo
FROM Application.Countries_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an archived version not followed, at its ValidTo, by the next version of the country, archived or current.
-- no rows: the versions of each country chain into its current row without a gap or an overlap.
SELECT v.CountryID, v.ValidTo, v.next_valid_from
FROM (SELECT u.CountryID, u.ValidTo, u.archived,
             LEAD(u.ValidFrom) OVER (PARTITION BY u.CountryID ORDER BY u.ValidFrom, u.ValidTo) AS next_valid_from
      FROM (SELECT CountryID, ValidFrom, ValidTo, 1 AS archived
            FROM Application.Countries_Archive
            UNION ALL
            SELECT CountryID, ValidFrom, ValidTo, 0
            FROM Application.Countries) u) v
WHERE v.archived = 1
  AND (v.next_valid_from IS NULL OR v.next_valid_from <> v.ValidTo)
;

