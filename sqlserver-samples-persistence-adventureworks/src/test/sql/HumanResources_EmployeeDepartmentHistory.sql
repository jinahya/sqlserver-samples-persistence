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
-- Profiling queries on HumanResources.EmployeeDepartmentHistory, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_EmployeeDepartmentHistory.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and how many employees; 296 histories of 290 employees.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM HumanResources.EmployeeDepartmentHistory
;

-- the employees who moved, with their histories in order; 11 rows of 5 employees; 4, 16, 224 and 234 moved once, 250
-- twice, all on the day shift.
SELECT h.BusinessEntityID, h.DepartmentID, h.ShiftID, h.StartDate, h.EndDate
FROM HumanResources.EmployeeDepartmentHistory h
WHERE h.BusinessEntityID IN (SELECT BusinessEntityID
                             FROM HumanResources.EmployeeDepartmentHistory
                             GROUP BY BusinessEntityID
                             HAVING COUNT(*) > 1)
ORDER BY h.BusinessEntityID ASC, h.StartDate ASC
;


-- -------------------------------------------------------------------------------------------------------- DepartmentID
-- how many departments the histories cover; all 16, 1 to 16.
SELECT COUNT(DISTINCT DepartmentID), MIN(DepartmentID), MAX(DepartmentID)
FROM HumanResources.EmployeeDepartmentHistory
;


-- ------------------------------------------------------------------------------------------------------------- ShiftID
-- the shifts the histories fall in; Day (1) 182, Evening (2) 62 and Night (3) 52.
SELECT ShiftID, COUNT(*) AS histories
FROM HumanResources.EmployeeDepartmentHistory
GROUP BY ShiftID
ORDER BY ShiftID ASC
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- the range of StartDate; 2006-06-30 to 2013-11-14.
SELECT MIN(StartDate), MAX(StartDate)
FROM HumanResources.EmployeeDepartmentHistory
;

-- an employee whose first StartDate is not the HireDate.
-- no rows: every employee starts on the day of its hire.
SELECT h.BusinessEntityID, h.first_start, e.HireDate
FROM (SELECT BusinessEntityID, MIN(StartDate) AS first_start
      FROM HumanResources.EmployeeDepartmentHistory
      GROUP BY BusinessEntityID) h
         JOIN HumanResources.Employee e ON e.BusinessEntityID = h.BusinessEntityID
WHERE h.first_start <> e.HireDate
ORDER BY h.BusinessEntityID ASC
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- how many histories are open, a NULL EndDate, and the range of the closed ones; 290 open; the closed ones end from
-- 2009-07-14 to 2013-11-13.
SELECT COUNT(*) - COUNT(EndDate) AS open_histories, MIN(EndDate), MAX(EndDate)
FROM HumanResources.EmployeeDepartmentHistory
;

-- an employee with two open histories, or none.
-- no rows: every employee has exactly one open history.
SELECT e.BusinessEntityID, COUNT(h.BusinessEntityID) AS open_histories
FROM HumanResources.Employee e
         LEFT JOIN HumanResources.EmployeeDepartmentHistory h
                   ON h.BusinessEntityID = e.BusinessEntityID AND h.EndDate IS NULL
GROUP BY e.BusinessEntityID
HAVING COUNT(h.BusinessEntityID) <> 1
;

-- CK_EmployeeDepartmentHistory_EndDate keeps EndDate from coming before StartDate.
-- no rows: no history ends before it starts.
SELECT BusinessEntityID, StartDate, EndDate
FROM HumanResources.EmployeeDepartmentHistory
WHERE EndDate < StartDate
;

-- a closed history whose next one does not start the day after it ends.
-- no rows: each closed history is followed the next day by another; no employee leaves or has a break.
SELECT h.BusinessEntityID, h.EndDate, h.next_start
FROM (SELECT BusinessEntityID,
             EndDate,
             LEAD(StartDate) OVER (PARTITION BY BusinessEntityID ORDER BY StartDate) AS next_start
      FROM HumanResources.EmployeeDepartmentHistory) h
WHERE h.EndDate IS NOT NULL
  AND (h.next_start IS NULL OR h.next_start <> DATEADD(DAY, 1, h.EndDate))
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range of ModifiedDate; 2006-06-29 00:00:00.000 to 2013-11-13 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM HumanResources.EmployeeDepartmentHistory
;

-- how many days ModifiedDate comes before the StartDate of an open history, and before the EndDate of a closed one; the
-- 6 closed ones 2 days before the EndDate, the 290 open ones the day before the StartDate. No history was modified
-- after it took effect.
SELECT IIF(EndDate IS NULL, 'open', 'closed') AS history,
       COUNT(*)                               AS histories,
       MIN(DATEDIFF(DAY, ModifiedDate, COALESCE(EndDate, StartDate))),
       MAX(DATEDIFF(DAY, ModifiedDate, COALESCE(EndDate, StartDate)))
FROM HumanResources.EmployeeDepartmentHistory
GROUP BY IIF(EndDate IS NULL, 'open', 'closed')
ORDER BY history ASC
;

