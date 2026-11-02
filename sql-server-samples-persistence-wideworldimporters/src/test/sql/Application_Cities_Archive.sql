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
-- Profiling queries on Application.Cities_Archive, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_Cities_Archive.sql
--


-- -------------------------------------------------------------------------------------------------------------- CityID
-- how many rows, how many cities they keep the history of, and the smallest and the largest CityID;
-- 28 versions of 28 cities, 164 to 37232.
SELECT COUNT(*), COUNT(DISTINCT CityID), MIN(CityID), MAX(CityID)
FROM Application.Cities_Archive
;

-- an archived CityID with no current row; the history here is only of cities still current.
-- no rows: every archived CityID is still current.
SELECT a.CityID
FROM Application.Cities_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Application.Cities c
                  WHERE c.CityID = a.CityID)
;


-- ------------------------------------------------------------------------------------------------------------ CityName
-- the shortest and the longest CityName; 6 and 16 characters.
SELECT MIN(LEN(CityName)), MAX(LEN(CityName))
FROM Application.Cities_Archive
;

-- an archived version whose name, state or province, or location differs from the current row.
-- no rows: a city's history differs, if at all, only in its population.
SELECT a.CityID, a.CityName, a.StateProvinceID
FROM Application.Cities_Archive a
         JOIN Application.Cities c ON c.CityID = a.CityID
WHERE a.CityName <> c.CityName
   OR a.StateProvinceID <> c.StateProvinceID
   OR a.Location.STEquals(c.Location) = 0
;


-- ----------------------------------------------------------------------------------------------------- StateProvinceID
-- how many states and provinces the archived cities are in; 25.
SELECT COUNT(DISTINCT StateProvinceID)
FROM Application.Cities_Archive
;


-- ------------------------------------------------------------------------------------------------------------ Location
-- Location is a nullable geography; how many versions have none, and how large the others are in bytes.
-- None; every one 22 bytes, a point.
SELECT COUNT(*) - COUNT(Location) AS without, MIN(DATALENGTH(Location)), MAX(DATALENGTH(Location))
FROM Application.Cities_Archive
;


-- -------------------------------------------------------------------------------------------- LatestRecordedPopulation
-- how the archived population compares with the current one; 17 versions had a smaller population, none a larger,
-- and 11 had none and still have none.
SELECT SUM(CASE WHEN a.LatestRecordedPopulation < c.LatestRecordedPopulation THEN 1 ELSE 0 END) AS smaller,
       SUM(CASE WHEN a.LatestRecordedPopulation > c.LatestRecordedPopulation THEN 1 ELSE 0 END) AS larger,
       SUM(CASE WHEN a.LatestRecordedPopulation IS NULL AND c.LatestRecordedPopulation IS NULL THEN 1 ELSE 0 END)
           AS both_null
FROM Application.Cities_Archive a
         JOIN Application.Cities c ON c.CityID = a.CityID
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who edited the archived versions; person 1, Data Conversion Only, for all 28.
SELECT a.LastEditedBy, p.FullName, COUNT(*) AS versions
FROM Application.Cities_Archive a
         JOIN Application.People p ON p.PersonID = a.LastEditedBy
GROUP BY a.LastEditedBy, p.FullName
ORDER BY a.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the periods the archived versions were valid for; all begin on 2013-01-01, and 10, 5 and 13 end on 1 July of
-- 2013, 2014 and 2015.
SELECT ValidFrom, ValidTo, COUNT(*) AS versions
FROM Application.Cities_Archive
GROUP BY ValidFrom, ValidTo
ORDER BY ValidFrom ASC, ValidTo ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a version which ends no later than it begins.
-- no rows: every ValidTo is after its ValidFrom.
SELECT CityID, ValidFrom, ValidTo
FROM Application.Cities_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an archived version whose ValidTo is not where the current row of its city begins.
-- no rows: each version meets its current row without a gap or an overlap.
SELECT a.CityID, a.ValidTo, c.ValidFrom
FROM Application.Cities_Archive a
         JOIN Application.Cities c ON c.CityID = a.CityID
WHERE a.ValidTo <> c.ValidFrom
;

