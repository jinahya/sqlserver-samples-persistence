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
-- Profiling queries on dbo.AWBuildVersion, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/dbo_AWBuildVersion.sql
--


-- ------------------------------------------------------------------------------------------------- SystemInformationID
-- how many rows, and the SystemInformationID; a single row, 1.
SELECT COUNT(*), MIN(SystemInformationID), MAX(SystemInformationID)
FROM dbo.AWBuildVersion
;


-- ---------------------------------------------------------------------------------------------------- Database Version
-- the version the sample carries, and its length; 17.0.1000.3, 11 characters of the 25 the column allows.
SELECT [Database Version], LEN([Database Version]) AS length
FROM dbo.AWBuildVersion
;

-- a Database Version which is blank, or carries leading or trailing spaces.
-- no rows: the version is trimmed and not blank.
SELECT SystemInformationID, '[' + [Database Version] + ']' AS [Database Version]
FROM dbo.AWBuildVersion
WHERE TRIM([Database Version]) = ''
   OR DATALENGTH([Database Version]) <> DATALENGTH(TRIM([Database Version]))
;


-- --------------------------------------------------------------------------------------------------------- VersionDate
-- the date the version was built; 2025-10-06 21:25:57.990.
SELECT VersionDate
FROM dbo.AWBuildVersion
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the date the row was last modified, against VersionDate; 2025-11-14 13:01:07.887, 39 days after the version, when the
-- sample was installed.
SELECT ModifiedDate, DATEDIFF(DAY, VersionDate, ModifiedDate) AS days_after_version
FROM dbo.AWBuildVersion
;

