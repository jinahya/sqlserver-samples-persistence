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
-- Profiling queries on the view HumanResources.vJobCandidate, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_vJobCandidate.sql
--


-- ------------------------------------------------------------------------------------------------------ JobCandidateID
-- how many rows, how many distinct JobCandidateID, and the smallest and the largest; 13 rows, one per candidate, 1 to
-- 13.
SELECT COUNT(*), COUNT(DISTINCT JobCandidateID), MIN(JobCandidateID), MAX(JobCandidateID)
FROM HumanResources.vJobCandidate
;


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows leave BusinessEntityID NULL, and the smallest and the largest; 11 NULLs; the 2 hired candidates are 212
-- and 274.
SELECT COUNT(*) - COUNT(BusinessEntityID) AS nulls, MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM HumanResources.vJobCandidate
;


-- --------------------------------------------------------------------------------------------------------- Name.Prefix
-- how many rows leave Name.Prefix NULL, blank or padded with spaces, and the shortest and the longest; no NULL; 5 blank
-- and 1 padded, 0 to 3 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Name.Prefix] AS v
      FROM HumanResources.vJobCandidate) c
;

-- the values, bracketed to show the spaces; 5 blank, M. 3, Mr. 3, and นาย twice, once with a trailing space.
SELECT '[' + [Name.Prefix] + ']' AS [Name.Prefix], COUNT(*) AS candidates
FROM HumanResources.vJobCandidate
GROUP BY '[' + [Name.Prefix] + ']'
ORDER BY [Name.Prefix] ASC
;


-- ---------------------------------------------------------------------------------------------------------- Name.First
-- how many rows leave Name.First NULL, blank or padded with spaces, and the shortest and the longest; no NULL or blank;
-- 1 padded, 3 to 9 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Name.First] AS v
      FROM HumanResources.vJobCandidate) c
;


-- --------------------------------------------------------------------------------------------------------- Name.Middle
-- how many rows leave Name.Middle NULL, blank or padded with spaces, and the shortest and the longest; no NULL; 12
-- blank and 1 padded, Y with a trailing space.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Name.Middle] AS v
      FROM HumanResources.vJobCandidate) c
;


-- ----------------------------------------------------------------------------------------------------------- Name.Last
-- how many rows leave Name.Last NULL, blank or padded with spaces, and the shortest and the longest; no NULL or blank;
-- 2 padded, 2 to 12 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Name.Last] AS v
      FROM HumanResources.vJobCandidate) c
;


-- --------------------------------------------------------------------------------------------------------- Name.Suffix
-- how many rows leave Name.Suffix NULL, blank or padded with spaces, and the shortest and the longest; all 13 blank,
-- never NULL.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Name.Suffix] AS v
      FROM HumanResources.vJobCandidate) c
;


-- -------------------------------------------------------------------------------------------------------------- Skills
-- how many rows leave Skills NULL, blank or padded with spaces, and the shortest and the longest; no NULL; 1 blank and
-- 12 padded, up to 844 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT Skills AS v
      FROM HumanResources.vJobCandidate) c
;


-- ----------------------------------------------------------------------------------------------------------- Addr.Type
-- how many rows leave Addr.Type NULL, blank or padded with spaces, and the shortest and the longest; no NULL or blank;
-- 3 padded, 2 to 4 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Addr.Type] AS v
      FROM HumanResources.vJobCandidate) c
;

-- the values, bracketed to show the spaces; Home 7, and บ้าน 3, with a trailing space, and 家庭 3.
SELECT '[' + [Addr.Type] + ']' AS [Addr.Type], COUNT(*) AS candidates
FROM HumanResources.vJobCandidate
GROUP BY '[' + [Addr.Type] + ']'
ORDER BY [Addr.Type] ASC
;


-- ---------------------------------------------------------------------------------------------- Addr.Loc.CountryRegion
-- how many rows leave Addr.Loc.CountryRegion NULL, blank or padded with spaces, and the shortest and the longest; no
-- NULL or blank; 10 padded, 2 to 9 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Addr.Loc.CountryRegion] AS v
      FROM HumanResources.vJobCandidate) c
;

-- the values, bracketed to show the spaces; France 3, US 7 and ประเทศไทย 3, both with a trailing
-- space.
SELECT '[' + [Addr.Loc.CountryRegion] + ']' AS [Addr.Loc.CountryRegion], COUNT(*) AS candidates
FROM HumanResources.vJobCandidate
GROUP BY '[' + [Addr.Loc.CountryRegion] + ']'
ORDER BY [Addr.Loc.CountryRegion] ASC
;


-- ------------------------------------------------------------------------------------------------------ Addr.Loc.State
-- how many rows leave Addr.Loc.State NULL, blank or padded with spaces, and the shortest and the longest; no NULL or
-- blank; 8 padded, 2 to 19 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Addr.Loc.State] AS v
      FROM HumanResources.vJobCandidate) c
;

-- how many distinct values, and how many once trimmed; 9 values, 8 once trimmed.
SELECT COUNT(DISTINCT '[' + [Addr.Loc.State] + ']'), COUNT(DISTINCT TRIM([Addr.Loc.State]))
FROM HumanResources.vJobCandidate
;


-- ------------------------------------------------------------------------------------------------------- Addr.Loc.City
-- how many rows leave Addr.Loc.City NULL, blank or padded with spaces, and the shortest and the longest; no NULL or
-- blank; 2 padded, 5 to 11 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Addr.Loc.City] AS v
      FROM HumanResources.vJobCandidate) c
;


-- ----------------------------------------------------------------------------------------------------- Addr.PostalCode
-- how many rows leave Addr.PostalCode NULL, blank or padded with spaces, and the shortest and the longest; none; every
-- PostalCode has 5 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT [Addr.PostalCode] AS v
      FROM HumanResources.vJobCandidate) c
;


-- --------------------------------------------------------------------------------------------------------------- EMail
-- how many rows leave EMail NULL, blank or padded with spaces, and the shortest and the longest; 1 NULL and 7 blank,
-- none padded, up to 24 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT EMail AS v
      FROM HumanResources.vJobCandidate) c
;


-- ------------------------------------------------------------------------------------------------------------- WebSite
-- how many rows leave WebSite NULL, blank or padded with spaces, and the shortest and the longest; 1 NULL and 10 blank,
-- none padded, up to 27 characters.
SELECT COUNT(*) - COUNT(v) AS nulls, COUNT(IIF(TRIM(v) = '', 1, NULL)) AS blanks,
       COUNT(IIF(TRIM(v) <> '' AND DATALENGTH(v) <> DATALENGTH(TRIM(v)), 1, NULL)) AS padded,
       MIN(LEN(v)), MAX(LEN(v))
FROM (SELECT WebSite AS v
      FROM HumanResources.vJobCandidate) c
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the smallest and the largest ModifiedDate; 2007-06-23 00:00:00.000 to 2013-12-22 18:32:21.313, those of
-- HumanResources.JobCandidate.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM HumanResources.vJobCandidate
;

