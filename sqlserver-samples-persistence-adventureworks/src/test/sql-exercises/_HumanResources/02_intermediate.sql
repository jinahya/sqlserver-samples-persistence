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
---
--
-- Exercises at the Intermediate level on the HumanResources schema, a problem per query.
--
-- These are the SQL solutions of the 'HumanResources' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- ---------------------------------------------------------------------------------------------- AW_HUMANRESOURCES_I_01
-- Payroll reviews the highest pay. Show the ten employees with the highest current hourly rate, the rate of each one's
-- latest change.
SELECT TOP(10) p.FirstName, p.LastName,
       e.JobTitle,
       ph.Rate,
       CAST(ph.RateChangeDate AS date) AS RateChangeDate
FROM HumanResources.EmployeePayHistory ph
         JOIN HumanResources.Employee e ON e.BusinessEntityID = ph.BusinessEntityID
         JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
WHERE ph.RateChangeDate = (SELECT MAX(ph2.RateChangeDate)
                           FROM HumanResources.EmployeePayHistory ph2
                           WHERE ph2.BusinessEntityID = ph.BusinessEntityID)
ORDER BY ph.Rate DESC, e.BusinessEntityID
;
GO

-- ---------------------------------------------------------------------------------------------- AW_HUMANRESOURCES_I_02
-- A manager asks who reports to them directly. Show the direct reports of an employee, by the organization chart.
DECLARE
@managerId int = 25;
SELECT e.OrganizationNode.ToString() AS Node, e.JobTitle, p.FirstName, p.LastName
FROM HumanResources.Employee e
         JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
WHERE e.OrganizationNode.GetAncestor(1) = (SELECT m.OrganizationNode
                                           FROM HumanResources.Employee m
                                           WHERE m.BusinessEntityID = @managerId)
ORDER BY e.OrganizationNode
;
GO


-- ---------------------------------------------------------------------------------------------- AW_HUMANRESOURCES_I_03
-- HR looks at internal mobility. Show the employees who have worked in more than one department, with how many
-- departments each one worked in.
SELECT p.FirstName, p.LastName, COUNT(DISTINCT h.DepartmentID) AS Departments
FROM HumanResources.EmployeeDepartmentHistory h
         JOIN Person.Person p ON p.BusinessEntityID = h.BusinessEntityID
GROUP BY h.BusinessEntityID, p.FirstName, p.LastName
HAVING COUNT(DISTINCT h.DepartmentID) > 1
ORDER BY p.LastName, p.FirstName, h.BusinessEntityID
;
GO


-- ---------------------------------------------------------------------------------------------- AW_HUMANRESOURCES_I_04
-- HR wants each shift's start, end and length in minutes; a shift that ends at or before its start ends the next day.
SELECT s.ShiftID, s.Name, s.StartTime, s.EndTime,
       DATEDIFF(MINUTE, s.StartTime, s.EndTime) + CASE WHEN s.EndTime <= s.StartTime THEN 1440 ELSE 0 END AS DurationMinutes
FROM HumanResources.Shift s
ORDER BY s.ShiftID
;
GO

