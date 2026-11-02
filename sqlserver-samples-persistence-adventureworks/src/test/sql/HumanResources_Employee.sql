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
-- Profiling queries on HumanResources.Employee, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_Employee.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and the smallest and the largest BusinessEntityID; 290 employees, 1 to 290.
SELECT COUNT(*), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM HumanResources.Employee
;

-- the kind of the Person each employee is; 273 are EM, employees, and 17 are SP, sales people.
SELECT p.PersonType, COUNT(*) AS employees
FROM HumanResources.Employee e
         JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
GROUP BY p.PersonType
ORDER BY p.PersonType ASC
;


-- ---------------------------------------------------------------------------------------------------- NationalIDNumber
-- the shortest and the longest NationalIDNumber; 5 and 9 characters, of the 15 the column allows.
SELECT MIN(LEN(NationalIDNumber)), MAX(LEN(NationalIDNumber))
FROM HumanResources.Employee
;

-- a NationalIDNumber with a character other than a digit.
-- no rows: every NationalIDNumber is digits only.
SELECT BusinessEntityID, NationalIDNumber
FROM HumanResources.Employee
WHERE NationalIDNumber LIKE '%[^0-9]%'
;

-- AK_Employee_NationalIDNumber keeps NationalIDNumber unique.
-- no rows: no two employees share a NationalIDNumber.
SELECT NationalIDNumber, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY NationalIDNumber
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------- LoginID
-- the shortest and the longest LoginID; 19 and 28 characters, of the 256 the column allows.
SELECT MIN(LEN(LoginID)), MAX(LEN(LoginID))
FROM HumanResources.Employee
;

-- a LoginID outside the adventure-works domain.
-- no rows: every LoginID is in adventure-works\.
SELECT BusinessEntityID, LoginID
FROM HumanResources.Employee
WHERE LoginID NOT LIKE 'adventure-works\%'
;

-- a LoginID which is not the FirstName followed by a number; 2 rows, where the FirstName has a space or a period; 146
-- Jian Shuo is jianshuo0 and 222 A. Scott is ascott0. The rest are the FirstName in lower case and a digit.
SELECT e.BusinessEntityID, e.LoginID, p.FirstName
FROM HumanResources.Employee e
         JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
WHERE e.LoginID NOT LIKE 'adventure-works\' + p.FirstName + '[0-9]'
ORDER BY e.BusinessEntityID ASC
;

-- AK_Employee_LoginID keeps LoginID unique.
-- no rows: no two employees share a LoginID.
SELECT LoginID, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY LoginID
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------- OrganizationNode
-- how many rows leave OrganizationNode NULL; 1.
SELECT COUNT(*) - COUNT(OrganizationNode) AS nulls
FROM HumanResources.Employee
;

-- the employee who leaves OrganizationNode NULL; 1, the Chief Executive Officer; the root / is held by no one, the NULL
-- stands in its place.
SELECT BusinessEntityID, JobTitle
FROM HumanResources.Employee
WHERE OrganizationNode IS NULL
;

-- a node whose parent is no employee; the 6 employees on level 1 (2, 16, 25, 234, 263 and 273), all children of the
-- root /, which no employee holds, since the Chief Executive Officer leaves OrganizationNode NULL.
SELECT e.BusinessEntityID, e.OrganizationNode.ToString() AS OrganizationNode
FROM HumanResources.Employee e
WHERE e.OrganizationNode <> hierarchyid::GetRoot()
  AND NOT EXISTS (SELECT 1
                  FROM HumanResources.Employee m
                  WHERE m.OrganizationNode = e.OrganizationNode.GetAncestor(1))
;

-- two employees on the same node.
-- no rows: no two employees share a node.
SELECT OrganizationNode.ToString() AS OrganizationNode, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY OrganizationNode
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------- OrganizationLevel
-- the levels, computed from OrganizationNode; NULL for the Chief Executive Officer, then 6, 27, 66 and 190 on levels 1
-- to 4.
SELECT OrganizationLevel, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY OrganizationLevel
ORDER BY OrganizationLevel ASC
;


-- ------------------------------------------------------------------------------------------------------------ JobTitle
-- the shortest and the longest JobTitle, and how many titles; 5 and 40 characters, of the 50 the column allows; 67
-- titles.
SELECT MIN(LEN(JobTitle)), MAX(LEN(JobTitle)), COUNT(DISTINCT JobTitle)
FROM HumanResources.Employee
;

-- a JobTitle which is blank, or carries leading or trailing spaces.
-- no rows: every JobTitle is trimmed and not blank.
SELECT BusinessEntityID, '[' + JobTitle + ']' AS JobTitle
FROM HumanResources.Employee
WHERE TRIM(JobTitle) = ''
   OR DATALENGTH(JobTitle) <> DATALENGTH(TRIM(JobTitle))
;

-- the most common titles; Production Technician - WC40, WC50 and WC60, 26 each.
SELECT TOP (3) JobTitle, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY JobTitle
ORDER BY employees DESC, JobTitle ASC
;


-- ----------------------------------------------------------------------------------------------------------- BirthDate
-- the range of BirthDate; CK_Employee_BirthDate holds it from 1930-01-01 to 18 years ago; 1951-10-17 to 1991-05-31.
SELECT MIN(BirthDate), MAX(BirthDate)
FROM HumanResources.Employee
;

-- an employee hired before turning 18; 2 rows, a finding: 69 born 1991-04-06 and hired 2009-02-15, and 115 born
-- 1991-05-31 and hired 2009-01-20, both at 17.
SELECT BusinessEntityID, BirthDate, HireDate
FROM HumanResources.Employee
WHERE HireDate < DATEADD(YEAR, 18, BirthDate)
;


-- ------------------------------------------------------------------------------------------------------- MaritalStatus
-- the values; CK_Employee_MaritalStatus allows S and M, in either case; M 146 and S 144.
SELECT MaritalStatus, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY MaritalStatus
ORDER BY MaritalStatus ASC
;

-- a MaritalStatus in lower case, which the check lets through; 0: every MaritalStatus is in upper case.
SELECT COUNT(*)
FROM HumanResources.Employee
WHERE MaritalStatus COLLATE Latin1_General_CS_AS <> UPPER(MaritalStatus)
;


-- -------------------------------------------------------------------------------------------------------------- Gender
-- the values; CK_Employee_Gender allows F and M, in either case; F 84 and M 206.
SELECT Gender, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY Gender
ORDER BY Gender ASC
;

-- a Gender in lower case, which the check lets through; 0: every Gender is in upper case.
SELECT COUNT(*)
FROM HumanResources.Employee
WHERE Gender COLLATE Latin1_General_CS_AS <> UPPER(Gender)
;


-- ------------------------------------------------------------------------------------------------------------ HireDate
-- the range of HireDate; CK_Employee_HireDate holds it from 1996-07-01 to tomorrow; 2006-06-30 to 2013-05-30.
SELECT MIN(HireDate), MAX(HireDate)
FROM HumanResources.Employee
;

-- the hires of each year; 8 years; 2009 the busiest with 148, 2006 a single hire.
SELECT YEAR(HireDate) AS year, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY YEAR(HireDate)
ORDER BY year ASC
;


-- -------------------------------------------------------------------------------------------------------- SalariedFlag
-- the values; 238 hourly (0) and 52 salaried (1).
SELECT SalariedFlag, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY SalariedFlag
ORDER BY SalariedFlag ASC
;


-- ------------------------------------------------------------------------------------------------------- VacationHours
-- the smallest and the largest VacationHours; CK_Employee_VacationHours holds it between -40 and 240; 0 and 99.
SELECT MIN(VacationHours), MAX(VacationHours)
FROM HumanResources.Employee
;


-- ------------------------------------------------------------------------------------------------------ SickLeaveHours
-- the smallest and the largest SickLeaveHours; CK_Employee_SickLeaveHours holds it between 0 and 120; 20 and 80.
SELECT MIN(SickLeaveHours), MAX(SickLeaveHours)
FROM HumanResources.Employee
;


-- --------------------------------------------------------------------------------------------------------- CurrentFlag
-- the values; all 290 are current (1).
SELECT CurrentFlag, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY CurrentFlag
ORDER BY CurrentFlag ASC
;

-- a current employee with no current department, or the other way round.
-- no rows: an employee is current exactly when one of its department histories is open.
SELECT e.BusinessEntityID, e.CurrentFlag
FROM HumanResources.Employee e
WHERE e.CurrentFlag <> IIF(EXISTS (SELECT 1
                                   FROM HumanResources.EmployeeDepartmentHistory h
                                   WHERE h.BusinessEntityID = e.BusinessEntityID
                                     AND h.EndDate IS NULL), 1, 0)
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_Employee_rowguid keeps rowguid unique.
-- no rows: no two employees share a rowguid.
SELECT rowguid, COUNT(*) AS employees
FROM HumanResources.Employee
GROUP BY rowguid
HAVING COUNT(*) > 1
;

-- a rowguid which is also the rowguid of a Person.Person row.
-- no rows: no employee shares its rowguid with its Person.
SELECT e.BusinessEntityID, e.rowguid
FROM HumanResources.Employee e
         JOIN Person.Person p ON p.rowguid = e.rowguid
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range of ModifiedDate; 2014-06-30 00:00:00.000 to 2014-12-26 09:17:08.637.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM HumanResources.Employee
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an employee with no department history.
-- no rows: every employee has at least one department.
SELECT e.BusinessEntityID, e.JobTitle
FROM HumanResources.Employee e
WHERE NOT EXISTS (SELECT 1
                  FROM HumanResources.EmployeeDepartmentHistory h
                  WHERE h.BusinessEntityID = e.BusinessEntityID)
;

-- the employees with the most direct reports; 26, the Production Control Manager, with 22; then 166 with 13 and 27 with
-- 12.
SELECT TOP (3) m.BusinessEntityID, m.JobTitle, COUNT(*) AS reports
FROM HumanResources.Employee m
         JOIN HumanResources.Employee e ON e.OrganizationNode.GetAncestor(1) = m.OrganizationNode
GROUP BY m.BusinessEntityID, m.JobTitle
ORDER BY reports DESC, m.BusinessEntityID ASC
;

