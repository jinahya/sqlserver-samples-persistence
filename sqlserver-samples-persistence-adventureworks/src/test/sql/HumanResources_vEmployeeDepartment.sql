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
-- Profiling queries on the view HumanResources.vEmployeeDepartment, a section per column. Each says what it looks
-- for and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_vEmployeeDepartment.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, how many distinct BusinessEntityID, and the smallest and the largest; 290, one per employee, 1 to 290:
-- the open history of each.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM HumanResources.vEmployeeDepartment
;


-- --------------------------------------------------------------------------------------------------------------- Title
-- the values; NULL 282, Mr. 5 and Ms. 3.
SELECT Title, COUNT(*) AS employees
FROM HumanResources.vEmployeeDepartment
GROUP BY Title
ORDER BY Title ASC
;


-- ----------------------------------------------------------------------------------------------------------- FirstName
-- the shortest and the longest FirstName; 2 and 11 characters, of the 50 the column allows.
SELECT MIN(LEN(FirstName)), MAX(LEN(FirstName))
FROM HumanResources.vEmployeeDepartment
;

-- a FirstName which is blank, or carries leading or trailing spaces.
-- no rows: every FirstName is trimmed and not blank.
SELECT BusinessEntityID, '[' + FirstName + ']' AS FirstName
FROM HumanResources.vEmployeeDepartment
WHERE TRIM(FirstName) = ''
   OR DATALENGTH(FirstName) <> DATALENGTH(TRIM(FirstName))
;


-- ---------------------------------------------------------------------------------------------------------- MiddleName
-- how many rows leave MiddleName NULL, and the shortest and the longest; 12 NULLs; 1 to 16 characters.
SELECT COUNT(*) - COUNT(MiddleName) AS nulls, MIN(LEN(MiddleName)), MAX(LEN(MiddleName))
FROM HumanResources.vEmployeeDepartment
;

-- a MiddleName which is blank, or carries leading or trailing spaces.
-- no rows: every MiddleName is trimmed and not blank.
SELECT BusinessEntityID, '[' + MiddleName + ']' AS MiddleName
FROM HumanResources.vEmployeeDepartment
WHERE TRIM(MiddleName) = ''
   OR DATALENGTH(MiddleName) <> DATALENGTH(TRIM(MiddleName))
;


-- ------------------------------------------------------------------------------------------------------------ LastName
-- the shortest and the longest LastName; 2 and 17 characters, of the 50 the column allows.
SELECT MIN(LEN(LastName)), MAX(LEN(LastName))
FROM HumanResources.vEmployeeDepartment
;

-- a LastName which is blank, or carries leading or trailing spaces.
-- no rows: every LastName is trimmed and not blank.
SELECT BusinessEntityID, '[' + LastName + ']' AS LastName
FROM HumanResources.vEmployeeDepartment
WHERE TRIM(LastName) = ''
   OR DATALENGTH(LastName) <> DATALENGTH(TRIM(LastName))
;


-- -------------------------------------------------------------------------------------------------------------- Suffix
-- the values; NULL 288, III 1 and Jr. 1.
SELECT Suffix, COUNT(*) AS employees
FROM HumanResources.vEmployeeDepartment
GROUP BY Suffix
ORDER BY Suffix ASC
;


-- ------------------------------------------------------------------------------------------------------------ JobTitle
-- the shortest and the longest JobTitle; 5 and 40 characters.
SELECT MIN(LEN(JobTitle)), MAX(LEN(JobTitle))
FROM HumanResources.vEmployeeDepartment
;

-- a JobTitle which is blank, or carries leading or trailing spaces.
-- no rows: every JobTitle is trimmed and not blank.
SELECT BusinessEntityID, '[' + JobTitle + ']' AS JobTitle
FROM HumanResources.vEmployeeDepartment
WHERE TRIM(JobTitle) = ''
   OR DATALENGTH(JobTitle) <> DATALENGTH(TRIM(JobTitle))
;


-- ---------------------------------------------------------------------------------------------------------- Department
-- the shortest and the longest Department; 5 and 26 characters.
SELECT MIN(LEN(Department)), MAX(LEN(Department))
FROM HumanResources.vEmployeeDepartment
;

-- a Department which is blank, or carries leading or trailing spaces.
-- no rows: every Department is trimmed and not blank.
SELECT BusinessEntityID, '[' + Department + ']' AS Department
FROM HumanResources.vEmployeeDepartment
WHERE TRIM(Department) = ''
   OR DATALENGTH(Department) <> DATALENGTH(TRIM(Department))
;


-- ----------------------------------------------------------------------------------------------------------- GroupName
-- the values; 6 groups; Manufacturing with 185, Quality Assurance the smallest with 11.
SELECT GroupName, COUNT(*) AS employees
FROM HumanResources.vEmployeeDepartment
GROUP BY GroupName
ORDER BY GroupName ASC
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- the smallest and the largest StartDate; 2006-06-30 to 2013-11-14.
SELECT MIN(StartDate), MAX(StartDate)
FROM HumanResources.vEmployeeDepartment
;

