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
-- Profiling queries on HumanResources.Shift, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_Shift.sql
--


-- ------------------------------------------------------------------------------------------------------------- ShiftID
-- how many rows, and the smallest and the largest ShiftID; 3 shifts, 1 to 3.
SELECT COUNT(*), MIN(ShiftID), MAX(ShiftID)
FROM HumanResources.Shift
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shifts, in ShiftID order; Day, Evening and Night, of 3 to 7 characters of the 50 the column allows.
SELECT ShiftID, Name, LEN(Name) AS length
FROM HumanResources.Shift
ORDER BY ShiftID ASC
;

-- a Name which is blank, or carries leading or trailing spaces.
-- no rows: every Name is trimmed and not blank.
SELECT ShiftID, '[' + Name + ']' AS Name
FROM HumanResources.Shift
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_Shift_Name keeps Name unique.
-- no rows: no two shifts share a Name.
SELECT Name, COUNT(*) AS shifts
FROM HumanResources.Shift
GROUP BY Name
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- StartTime
-- the start and the end of each shift, and its length in hours; 07:00, 15:00 and 23:00, each 8 hours long; the shifts
-- cover the whole day without overlap.
SELECT ShiftID, StartTime, EndTime,
       DATEDIFF(MINUTE, StartTime, EndTime) / 60.0 + IIF(EndTime < StartTime, 24, 0) AS hours
FROM HumanResources.Shift
ORDER BY StartTime ASC
;


-- ------------------------------------------------------------------------------------------------------------- EndTime
-- a shift which ends on the day after it starts; Night (3), from 23:00 to 07:00.
SELECT ShiftID, Name, StartTime, EndTime
FROM HumanResources.Shift
WHERE EndTime < StartTime
;

-- AK_Shift_StartTime_EndTime keeps the pair unique.
-- no rows: no two shifts share their hours.
SELECT StartTime, EndTime, COUNT(*) AS shifts
FROM HumanResources.Shift
GROUP BY StartTime, EndTime
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2008-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM HumanResources.Shift
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the department histories in each shift; Day has 182 and 176 current, Evening 62 and Night 52, all current.
SELECT s.ShiftID, s.Name, COUNT(h.BusinessEntityID) AS histories,
       COUNT(IIF(h.EndDate IS NULL, 1, NULL)) AS current_histories
FROM HumanResources.Shift s
         LEFT JOIN HumanResources.EmployeeDepartmentHistory h ON h.ShiftID = s.ShiftID
GROUP BY s.ShiftID, s.Name
ORDER BY s.ShiftID ASC
;

