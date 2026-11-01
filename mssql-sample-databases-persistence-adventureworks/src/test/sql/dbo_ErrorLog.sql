-- -
-- #%L
-- mssql-sample-databases-persistence-adventureworks
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
-- Profiling queries on dbo.ErrorLog, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/dbo_ErrorLog.sql
--


-- ---------------------------------------------------------------------------------------------------------- ErrorLogID
-- how many rows, and the smallest and the largest ErrorLogID; 0, NULL and NULL: the table is empty, as installed.
SELECT COUNT(*), MIN(ErrorLogID), MAX(ErrorLogID)
FROM dbo.ErrorLog
;


-- ----------------------------------------------------------------------------------------------------------- ErrorTime
-- the range of ErrorTime; NULL and NULL: no error was ever logged.
SELECT MIN(ErrorTime), MAX(ErrorTime)
FROM dbo.ErrorLog
;


-- ------------------------------------------------------------------------------------------------------------ UserName
-- the users who caused an error.
-- no rows: the table is empty.
SELECT UserName, COUNT(*) AS errors
FROM dbo.ErrorLog
GROUP BY UserName
ORDER BY UserName ASC
;


-- --------------------------------------------------------------------------------------------------------- ErrorNumber
-- the error numbers logged.
-- no rows: the table is empty.
SELECT ErrorNumber, COUNT(*) AS errors
FROM dbo.ErrorLog
GROUP BY ErrorNumber
ORDER BY ErrorNumber ASC
;


-- ------------------------------------------------------------------------------------------------------- ErrorSeverity
-- how many rows leave ErrorSeverity NULL, and its range; 0, NULL and NULL: the table is empty.
SELECT COUNT(*) - COUNT(ErrorSeverity) AS nulls, MIN(ErrorSeverity), MAX(ErrorSeverity)
FROM dbo.ErrorLog
;


-- ---------------------------------------------------------------------------------------------------------- ErrorState
-- how many rows leave ErrorState NULL, and its range; 0, NULL and NULL: the table is empty.
SELECT COUNT(*) - COUNT(ErrorState) AS nulls, MIN(ErrorState), MAX(ErrorState)
FROM dbo.ErrorLog
;


-- ------------------------------------------------------------------------------------------------------ ErrorProcedure
-- how many rows leave ErrorProcedure NULL; 0: the table is empty.
SELECT COUNT(*) - COUNT(ErrorProcedure) AS nulls
FROM dbo.ErrorLog
;


-- ----------------------------------------------------------------------------------------------------------- ErrorLine
-- how many rows leave ErrorLine NULL, and its range; 0, NULL and NULL: the table is empty.
SELECT COUNT(*) - COUNT(ErrorLine) AS nulls, MIN(ErrorLine), MAX(ErrorLine)
FROM dbo.ErrorLog
;


-- -------------------------------------------------------------------------------------------------------- ErrorMessage
-- the shortest and the longest ErrorMessage; NULL and NULL: the table is empty.
SELECT MIN(LEN(ErrorMessage)), MAX(LEN(ErrorMessage))
FROM dbo.ErrorLog
;

