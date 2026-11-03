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
-- Profiling queries on the view HumanResources.vJobCandidateEducation, a section per column. Each says what it looks
-- for and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_vJobCandidateEducation.sql
--


-- ------------------------------------------------------------------------------------------------------ JobCandidateID
-- how many rows, how many distinct JobCandidateID, and the smallest and the largest; 16 educations of the 13
-- candidates, 1 to 13.
SELECT COUNT(*), COUNT(DISTINCT JobCandidateID), MIN(JobCandidateID), MAX(JobCandidateID)
FROM HumanResources.vJobCandidateEducation
;


-- ----------------------------------------------------------------------------------------------------------- Edu.Level
-- how many rows leave Edu.Level NULL, blank or padded with spaces, and the shortest and the longest; no NULL or blank;
-- 2 padded, 2 to 11 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Edu.Level] AS v
      FROM HumanResources.vJobCandidateEducation) c
;

-- how many distinct values, and how many once trimmed; 7 and 7.
SELECT COUNT(DISTINCT '[' + [Edu.Level] + ']'), COUNT(DISTINCT TRIM([Edu.Level]))
FROM HumanResources.vJobCandidateEducation
;


-- ------------------------------------------------------------------------------------------------------- Edu.StartDate
-- how many rows leave Edu.StartDate NULL, and the smallest and the largest; no NULL; 1986-09-03 to 1993-09-04.
SELECT COUNT(*) - COUNT([Edu.StartDate]) AS nulls, MIN([Edu.StartDate]), MAX([Edu.StartDate])
FROM HumanResources.vJobCandidateEducation
;


-- --------------------------------------------------------------------------------------------------------- Edu.EndDate
-- how many rows leave Edu.EndDate NULL, and the smallest and the largest; no NULL; 1990-05-10 to 1997-06-03.
SELECT COUNT(*) - COUNT([Edu.EndDate]) AS nulls, MIN([Edu.EndDate]), MAX([Edu.EndDate])
FROM HumanResources.vJobCandidateEducation
;

-- an Edu.EndDate earlier than its Edu.StartDate.
-- no rows: every education ends after it starts.
SELECT JobCandidateID, [Edu.StartDate], [Edu.EndDate]
FROM HumanResources.vJobCandidateEducation
WHERE [Edu.EndDate] < [Edu.StartDate]
;


-- ---------------------------------------------------------------------------------------------------------- Edu.Degree
-- how many rows leave Edu.Degree NULL, blank or padded with spaces, and the shortest and the longest; no NULL or blank;
-- 1 padded, 2 to 50 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Edu.Degree] AS v
      FROM HumanResources.vJobCandidateEducation) c
;

-- how many distinct values, and how many once trimmed; 12 values, 11 once trimmed.
SELECT COUNT(DISTINCT '[' + [Edu.Degree] + ']'), COUNT(DISTINCT TRIM([Edu.Degree]))
FROM HumanResources.vJobCandidateEducation
;


-- ----------------------------------------------------------------------------------------------------------- Edu.Major
-- how many rows leave Edu.Major NULL, blank or padded with spaces, and the shortest and the longest; no NULL; 2 blank
-- and 1 padded, up to 23 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Edu.Major] AS v
      FROM HumanResources.vJobCandidateEducation) c
;


-- ----------------------------------------------------------------------------------------------------------- Edu.Minor
-- how many rows leave Edu.Minor NULL, blank or padded with spaces, and the shortest and the longest; all 16 blank,
-- never NULL.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Edu.Minor] AS v
      FROM HumanResources.vJobCandidateEducation) c
;


-- ------------------------------------------------------------------------------------------------------------- Edu.GPA
-- how many rows leave Edu.GPA NULL, blank or padded with spaces, and the shortest and the longest; no NULL, blank or
-- padded; 3 to 5 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Edu.GPA] AS v
      FROM HumanResources.vJobCandidateEducation) c
;

-- the values, bracketed to show the spaces; 3.2 to 3.8 on 12 rows, and Menti, not a number, on 4.
SELECT '[' + [Edu.GPA] + ']' AS [Edu.GPA], COUNT(*) AS educations
FROM HumanResources.vJobCandidateEducation
GROUP BY '[' + [Edu.GPA] + ']'
ORDER BY [Edu.GPA] ASC
;


-- -------------------------------------------------------------------------------------------------------- Edu.GPAScale
-- how many rows leave Edu.GPAScale NULL, blank or padded with spaces, and the shortest and the longest; no NULL, blank
-- or padded; a single character.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Edu.GPAScale] AS v
      FROM HumanResources.vJobCandidateEducation) c
;

-- the values, bracketed to show the spaces; 4 on all 16.
SELECT '[' + [Edu.GPAScale] + ']' AS [Edu.GPAScale], COUNT(*) AS educations
FROM HumanResources.vJobCandidateEducation
GROUP BY '[' + [Edu.GPAScale] + ']'
ORDER BY [Edu.GPAScale] ASC
;


-- ---------------------------------------------------------------------------------------------------------- Edu.School
-- how many rows leave Edu.School NULL, blank or padded with spaces, and the shortest and the longest; no NULL or blank;
-- 2 padded, 15 to 41 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Edu.School] AS v
      FROM HumanResources.vJobCandidateEducation) c
;


-- ----------------------------------------------------------------------------------------------- Edu.Loc.CountryRegion
-- how many rows leave Edu.Loc.CountryRegion NULL, blank or padded with spaces, and the shortest and the longest; no
-- NULL or blank; 10 padded, 2 to 9 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Edu.Loc.CountryRegion] AS v
      FROM HumanResources.vJobCandidateEducation) c
;

-- the values, bracketed to show the spaces; France 4, US 9 with a trailing space, and Thailand three ways,
-- ประเทศไท cut short, ประเทศไทย with a trailing space and ประเทศไทย
-- without, 1 each.
SELECT '[' + [Edu.Loc.CountryRegion] + ']' AS [Edu.Loc.CountryRegion], COUNT(*) AS educations
FROM HumanResources.vJobCandidateEducation
GROUP BY '[' + [Edu.Loc.CountryRegion] + ']'
ORDER BY [Edu.Loc.CountryRegion] ASC
;


-- ------------------------------------------------------------------------------------------------------- Edu.Loc.State
-- how many rows leave Edu.Loc.State NULL, blank or padded with spaces, and the shortest and the longest; no NULL or
-- blank; 10 padded, 2 to 19 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Edu.Loc.State] AS v
      FROM HumanResources.vJobCandidateEducation) c
;

-- how many distinct values, and how many once trimmed; 10 and 10.
SELECT COUNT(DISTINCT '[' + [Edu.Loc.State] + ']'), COUNT(DISTINCT TRIM([Edu.Loc.State]))
FROM HumanResources.vJobCandidateEducation
;


-- -------------------------------------------------------------------------------------------------------- Edu.Loc.City
-- how many rows leave Edu.Loc.City NULL, blank or padded with spaces, and the shortest and the longest; no NULL or
-- blank; 1 padded, 4 to 11 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Edu.Loc.City] AS v
      FROM HumanResources.vJobCandidateEducation) c
;

