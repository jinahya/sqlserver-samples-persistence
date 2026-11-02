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
-- Profiling queries on Application.StateProvinces_Archive, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_StateProvinces_Archive.sql
--


-- ----------------------------------------------------------------------------------------------------- StateProvinceID
-- how many rows, and how many states and provinces they keep the history of; 104 versions of 52.
SELECT COUNT(*), COUNT(DISTINCT StateProvinceID)
FROM Application.StateProvinces_Archive
;

-- how many states and provinces have how many archived versions; 18 have 1, 21 have 2, 9 have 3, 3 have 4, and
-- Montana (27) has 5.
SELECT v.versions, COUNT(*) AS state_provinces
FROM (SELECT StateProvinceID, COUNT(*) AS versions
      FROM Application.StateProvinces_Archive
      GROUP BY StateProvinceID) v
GROUP BY v.versions
ORDER BY v.versions ASC
;

-- a current state or province the archive keeps no version of; Virgin Islands (US Territory) (48).
SELECT s.StateProvinceID, s.StateProvinceName
FROM Application.StateProvinces s
WHERE NOT EXISTS (SELECT 1
                  FROM Application.StateProvinces_Archive a
                  WHERE a.StateProvinceID = s.StateProvinceID)
;

-- an archived StateProvinceID with no current row.
-- no rows: every archived StateProvinceID is still current.
SELECT a.StateProvinceID
FROM Application.StateProvinces_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Application.StateProvinces s
                  WHERE s.StateProvinceID = a.StateProvinceID)
;


-- --------------------------------------------------------------------------------------------------- StateProvinceCode
-- an archived version whose code, name, country or sales territory differs from the current row.
-- no rows: only populations and borders changed.
SELECT a.StateProvinceID, a.StateProvinceCode, a.StateProvinceName, a.CountryID, a.SalesTerritory
FROM Application.StateProvinces_Archive a
         JOIN Application.StateProvinces s ON s.StateProvinceID = a.StateProvinceID
WHERE a.StateProvinceCode <> s.StateProvinceCode
   OR a.StateProvinceName <> s.StateProvinceName
   OR a.CountryID <> s.CountryID
   OR a.SalesTerritory <> s.SalesTerritory
;


-- --------------------------------------------------------------------------------------------------- StateProvinceName
-- a StateProvinceName which is blank, or carries leading or trailing spaces.
-- no rows: every StateProvinceName is trimmed and not blank.
SELECT StateProvinceID, '[' + StateProvinceName + ']' AS StateProvinceName
FROM Application.StateProvinces_Archive
WHERE TRIM(StateProvinceName) = ''
   OR DATALENGTH(StateProvinceName) <> DATALENGTH(TRIM(StateProvinceName))
;


-- ----------------------------------------------------------------------------------------------------------- CountryID
-- the countries the versions are of; all 104 are of United States (230).
SELECT CountryID, COUNT(*) AS versions
FROM Application.StateProvinces_Archive
GROUP BY CountryID
ORDER BY CountryID ASC
;


-- ------------------------------------------------------------------------------------------------------ SalesTerritory
-- how many sales territories the versions fall in; all 9.
SELECT COUNT(DISTINCT SalesTerritory)
FROM Application.StateProvinces_Archive
;


-- -------------------------------------------------------------------------------------------------------------- Border
-- Border is a nullable geography; how many versions have none, and how large the others are in bytes.
-- 52 have none; 192 to 8864 bytes.
SELECT COUNT(*) - COUNT(Border) AS without, MIN(DATALENGTH(Border)), MAX(DATALENGTH(Border))
FROM Application.StateProvinces_Archive
;

-- the versions without a Border are the first one of each state or province, from 2013-01-01 00:00.
-- 52 versions, all with no Border.
SELECT COUNT(*) AS versions, COUNT(Border) AS with_border
FROM Application.StateProvinces_Archive
WHERE ValidFrom = '2013-01-01 00:00:00'
;


-- -------------------------------------------------------------------------------------------- LatestRecordedPopulation
-- an archived version whose population differs from the current row; 86 of the 104.
SELECT COUNT(*) AS versions
FROM Application.StateProvinces_Archive a
         JOIN Application.StateProvinces s ON s.StateProvinceID = a.StateProvinceID
WHERE a.LatestRecordedPopulation <> s.LatestRecordedPopulation
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who edited the archived versions; person 1, Data Conversion Only, for 86, then 20, Jack Potter, for 11, 8 for 4
-- and 15 for 3.
SELECT a.LastEditedBy, p.FullName, COUNT(*) AS versions
FROM Application.StateProvinces_Archive a
         JOIN Application.People p ON p.PersonID = a.LastEditedBy
GROUP BY a.LastEditedBy, p.FullName
ORDER BY a.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- when the archived versions begin; 52 at 2013-01-01 00:00, then within the next five minutes, and on 1 July of 2013,
-- 2014 and 2015.
SELECT ValidFrom, COUNT(*) AS versions
FROM Application.StateProvinces_Archive
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a version which ends no later than it begins.
-- 7 rows: each ends at the moment it begins, on 1 July of 2013, 2014 or 2015; a row updated twice in one
-- transaction leaves such an empty version behind.
SELECT StateProvinceID, ValidFrom, ValidTo
FROM Application.StateProvinces_Archive
WHERE ValidTo <= ValidFrom
ORDER BY StateProvinceID ASC, ValidFrom ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an archived version not followed, at its ValidTo, by the next version of the state or province, archived or
-- current.
-- no rows: the versions of each chain into its current row without a gap or an overlap.
SELECT v.StateProvinceID, v.ValidTo, v.next_valid_from
FROM (SELECT u.StateProvinceID, u.ValidTo, u.archived,
             LEAD(u.ValidFrom) OVER (PARTITION BY u.StateProvinceID ORDER BY u.ValidFrom, u.ValidTo)
                 AS next_valid_from
      FROM (SELECT StateProvinceID, ValidFrom, ValidTo, 1 AS archived
            FROM Application.StateProvinces_Archive
            UNION ALL
            SELECT StateProvinceID, ValidFrom, ValidTo, 0
            FROM Application.StateProvinces) u) v
WHERE v.archived = 1
  AND (v.next_valid_from IS NULL OR v.next_valid_from <> v.ValidTo)
;

