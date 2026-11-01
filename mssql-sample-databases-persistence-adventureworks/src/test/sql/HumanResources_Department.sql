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
-- Profiling queries on HumanResources.Department, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_Department.sql
--


-- -------------------------------------------------------------------------------------------------------- DepartmentID
-- how many rows, and the smallest and the largest DepartmentID; 16 departments, 1 to 16.
SELECT COUNT(*), MIN(DepartmentID), MAX(DepartmentID)
FROM HumanResources.Department
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 5 and 26 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM HumanResources.Department
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT DepartmentID, '[' + Name + ']' AS Name
FROM HumanResources.Department
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_Department_Name keeps Name unique.
-- no rows: no two departments share a Name.
SELECT Name, COUNT(*) AS departments
FROM HumanResources.Department
GROUP BY Name
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- GroupName
-- the shortest and the longest GroupName; 13 and 36 characters, of the 50 the column allows.
SELECT MIN(LEN(GroupName)), MAX(LEN(GroupName))
FROM HumanResources.Department
;

-- a GroupName which is blank, or carries leading or trailing spaces.
-- no rows: every GroupName is trimmed and not blank.
SELECT DepartmentID, '[' + GroupName + ']' AS GroupName
FROM HumanResources.Department
WHERE TRIM(GroupName) = ''
   OR DATALENGTH(GroupName) <> DATALENGTH(TRIM(GroupName))
;

-- the groups the departments fall in; 6 groups, Executive General and Administration the largest with 5.
SELECT GroupName, COUNT(*) AS departments
FROM HumanResources.Department
GROUP BY GroupName
ORDER BY GroupName ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2008-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM HumanResources.Department
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a department with no employee, past or present.
-- no rows: every department has at least one EmployeeDepartmentHistory row.
SELECT d.DepartmentID, d.Name
FROM HumanResources.Department d
WHERE NOT EXISTS (SELECT 1
                  FROM HumanResources.EmployeeDepartmentHistory h
                  WHERE h.DepartmentID = d.DepartmentID)
;

-- the current employees of each department, the largest first; Production (7) with 179, Executive (16) with 2.
SELECT d.DepartmentID, d.Name, COUNT(*) AS employees
FROM HumanResources.Department d
         JOIN HumanResources.EmployeeDepartmentHistory h ON h.DepartmentID = d.DepartmentID
WHERE h.EndDate IS NULL
GROUP BY d.DepartmentID, d.Name
ORDER BY employees DESC, d.DepartmentID ASC
;

