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
-- Profiling queries on Production.ScrapReason, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ScrapReason.sql
--


-- ------------------------------------------------------------------------------------------------------- ScrapReasonID
-- how many rows, and the smallest and the largest ScrapReasonID; 16 reasons, 1 to 16.
SELECT COUNT(*), MIN(ScrapReasonID), MAX(ScrapReasonID)
FROM Production.ScrapReason
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 14 and 31 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Production.ScrapReason
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT ScrapReasonID, '[' + Name + ']' AS Name
FROM Production.ScrapReason
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_ScrapReason_Name keeps Name unique.
-- no rows: no two reasons share a Name.
SELECT Name, COUNT(*) AS reasons
FROM Production.ScrapReason
GROUP BY Name
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ScrapReason
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a reason no work order is scrapped for.
-- no rows: every reason has scrapped at least one work order.
SELECT s.ScrapReasonID, s.Name
FROM Production.ScrapReason s
WHERE NOT EXISTS (SELECT 1
                  FROM Production.WorkOrder w
                  WHERE w.ScrapReasonID = s.ScrapReasonID)
;

-- the reasons work orders are scrapped for, the most frequent first; 13 (Thermoform temperature too low) with 63, 3
-- (Gouge in metal) with 54, 11 (Stress test failed) with 52.
SELECT TOP (3) s.ScrapReasonID, s.Name, COUNT(*) AS workOrders
FROM Production.ScrapReason s
         JOIN Production.WorkOrder w ON w.ScrapReasonID = s.ScrapReasonID
GROUP BY s.ScrapReasonID, s.Name
ORDER BY workOrders DESC, s.ScrapReasonID ASC
;

