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
-- Profiling queries on the view HumanResources.vJobCandidateEmployment, a section per column. Each says what it
-- looks for and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_vJobCandidateEmployment.sql
--


-- ------------------------------------------------------------------------------------------------------ JobCandidateID
-- how many rows, how many distinct JobCandidateID, and the smallest and the largest; 30 jobs of the 13 candidates, 1 to
-- 13.
SELECT COUNT(*), COUNT(DISTINCT JobCandidateID), MIN(JobCandidateID), MAX(JobCandidateID)
FROM HumanResources.vJobCandidateEmployment
;


-- ------------------------------------------------------------------------------------------------------- Emp.StartDate
-- how many rows leave Emp.StartDate NULL, and the smallest and the largest; no NULL; 1990-06-10 to 2000-06-15.
SELECT COUNT(*) - COUNT([Emp.StartDate]) AS nulls, MIN([Emp.StartDate]), MAX([Emp.StartDate])
FROM HumanResources.vJobCandidateEmployment
;


-- --------------------------------------------------------------------------------------------------------- Emp.EndDate
-- how many rows leave Emp.EndDate NULL, and the smallest and the largest; 3 NULLs; the others 1992-02-01 to 2004-05-15.
SELECT COUNT(*) - COUNT([Emp.EndDate]) AS nulls, MIN([Emp.EndDate]), MAX([Emp.EndDate])
FROM HumanResources.vJobCandidateEmployment
;

-- an Emp.EndDate earlier than its Emp.StartDate.
-- no rows: every job ends after it starts.
SELECT JobCandidateID, [Emp.StartDate], [Emp.EndDate]
FROM HumanResources.vJobCandidateEmployment
WHERE [Emp.EndDate] < [Emp.StartDate]
;

-- the candidates with an open job, a NULL Emp.EndDate; 2, 5 and 8, one job each.
SELECT JobCandidateID, COUNT(*) AS jobs
FROM HumanResources.vJobCandidateEmployment
WHERE [Emp.EndDate] IS NULL
GROUP BY JobCandidateID
ORDER BY JobCandidateID ASC
;


-- --------------------------------------------------------------------------------------------------------- Emp.OrgName
-- how many rows leave Emp.OrgName NULL, blank or padded with spaces, and the shortest and the longest; no NULL or
-- blank; 5 padded, 9 to 28 characters.
SELECT COUNT(*) - COUNT(v)                                                         AS nulls,
       COUNT(IIF(TRIM(v) = '', 1, NULL))                                           AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)),
       MAX(LEN(v))
FROM (SELECT [Emp.OrgName] AS v
      FROM HumanResources.vJobCandidateEmployment) c
;


-- -------------------------------------------------------------------------------------------------------- Emp.JobTitle
-- how many rows leave Emp.JobTitle NULL, blank or padded with spaces, and the shortest and the longest; no NULL or
-- blank; 6 padded, 3 to 27 characters.
SELECT COUNT(*) - COUNT(v)                                                         AS nulls,
       COUNT(IIF(TRIM(v) = '', 1, NULL))                                           AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)),
       MAX(LEN(v))
FROM (SELECT [Emp.JobTitle] AS v
      FROM HumanResources.vJobCandidateEmployment) c
;


-- -------------------------------------------------------------------------------------------------- Emp.Responsibility
-- how many rows leave Emp.Responsibility NULL, blank or padded with spaces, and the shortest and the longest; no NULL
-- or blank; all 30 padded, 38 to 745 characters.
SELECT COUNT(*) - COUNT(v)                                                         AS nulls,
       COUNT(IIF(TRIM(v) = '', 1, NULL))                                           AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)),
       MAX(LEN(v))
FROM (SELECT [Emp.Responsibility] AS v
      FROM HumanResources.vJobCandidateEmployment) c
;


-- ------------------------------------------------------------------------------------------------ Emp.FunctionCategory
-- how many rows leave Emp.FunctionCategory NULL, blank or padded with spaces, and the shortest and the longest; no NULL
-- or blank; 5 padded, 2 to 10 characters.
SELECT COUNT(*) - COUNT(v)                                                         AS nulls,
       COUNT(IIF(TRIM(v) = '', 1, NULL))                                           AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)),
       MAX(LEN(v))
FROM (SELECT [Emp.FunctionCategory] AS v
      FROM HumanResources.vJobCandidateEmployment) c
;

-- how many distinct values, and how many once trimmed; 12 values, 9 once trimmed.
SELECT COUNT(DISTINCT '[' + [Emp.FunctionCategory] + ']'), COUNT(DISTINCT TRIM([Emp.FunctionCategory]))
FROM HumanResources.vJobCandidateEmployment
;


-- ------------------------------------------------------------------------------------------------ Emp.IndustryCategory
-- how many rows leave Emp.IndustryCategory NULL, blank or padded with spaces, and the shortest and the longest; no NULL
-- or blank; 7 padded, 2 to 19 characters.
SELECT COUNT(*) - COUNT(v)                                                         AS nulls,
       COUNT(IIF(TRIM(v) = '', 1, NULL))                                           AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)),
       MAX(LEN(v))
FROM (SELECT [Emp.IndustryCategory] AS v
      FROM HumanResources.vJobCandidateEmployment) c
;

-- how many distinct values, and how many once trimmed; 14 and 14.
SELECT COUNT(DISTINCT '[' + [Emp.IndustryCategory] + ']'), COUNT(DISTINCT TRIM([Emp.IndustryCategory]))
FROM HumanResources.vJobCandidateEmployment
;


-- ----------------------------------------------------------------------------------------------- Emp.Loc.CountryRegion
-- how many rows leave Emp.Loc.CountryRegion NULL, blank or padded with spaces, and the shortest and the longest; no
-- NULL or blank; 20 padded, 2 to 9 characters.
SELECT COUNT(*) - COUNT(v)                                                         AS nulls,
       COUNT(IIF(TRIM(v) = '', 1, NULL))                                           AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)),
       MAX(LEN(v))
FROM (SELECT [Emp.Loc.CountryRegion] AS v
      FROM HumanResources.vJobCandidateEmployment) c
;

-- the values, bracketed to show the spaces; France 7, US 16 with a trailing space, and ประเทศไทย 4
-- with a trailing space and 3 without.
SELECT '[' + [Emp.Loc.CountryRegion] + ']' AS [Emp.Loc.CountryRegion], COUNT (*) AS jobs
FROM HumanResources.vJobCandidateEmployment
GROUP BY '[' + [Emp.Loc.CountryRegion] + ']'
ORDER BY [Emp.Loc.CountryRegion] ASC
;


-- ------------------------------------------------------------------------------------------------------- Emp.Loc.State
-- how many rows leave Emp.Loc.State NULL, blank or padded with spaces, and the shortest and the longest; no NULL or
-- blank; 20 padded, 2 to 19 characters.
SELECT COUNT(*) - COUNT(v)                                                         AS nulls,
       COUNT(IIF(TRIM(v) = '', 1, NULL))                                           AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)),
       MAX(LEN(v))
FROM (SELECT [Emp.Loc.State] AS v
      FROM HumanResources.vJobCandidateEmployment) c
;

-- how many distinct values, and how many once trimmed; 13 values, 12 once trimmed.
SELECT COUNT(DISTINCT '[' + [Emp.Loc.State] + ']'), COUNT(DISTINCT TRIM([Emp.Loc.State]))
FROM HumanResources.vJobCandidateEmployment
;


-- -------------------------------------------------------------------------------------------------------- Emp.Loc.City
-- how many rows leave Emp.Loc.City NULL, blank or padded with spaces, and the shortest and the longest; no NULL or
-- blank; 4 padded, 4 to 15 characters.
SELECT COUNT(*) - COUNT(v)                                                         AS nulls,
       COUNT(IIF(TRIM(v) = '', 1, NULL))                                           AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)),
       MAX(LEN(v))
FROM (SELECT [Emp.Loc.City] AS v
      FROM HumanResources.vJobCandidateEmployment) c
;

