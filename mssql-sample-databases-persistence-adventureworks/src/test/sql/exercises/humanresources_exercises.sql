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
---
--
-- Exercises on the HumanResources schema, a problem per query, from Basic to Advanced.
--
-- These are the SQL solutions of the 'HumanResources' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- ---------------------------------------------------------------------------------------------------------- AW-HR-B-01
-- HR plans next year's budget. Show how many employees each department has now, the largest first.
SELECT d.Name, d.GroupName, COUNT(*) AS Employees
FROM HumanResources.EmployeeDepartmentHistory h
         JOIN HumanResources.Department d ON d.DepartmentID = h.DepartmentID
WHERE h.EndDate IS NULL
GROUP BY d.DepartmentID, d.Name, d.GroupName
ORDER BY Employees DESC, d.Name
;
GO

-- ---------------------------------------------------------------------------------------------------------- AW-HR-B-02
-- HR draws the organization chart. Show how many employees are at each level of the organization.
SELECT e.OrganizationLevel, COUNT(*) AS Employees
FROM HumanResources.Employee e
GROUP BY e.OrganizationLevel
ORDER BY e.OrganizationLevel
;
GO

-- ---------------------------------------------------------------------------------------------------------- AW-HR-I-03
-- Payroll reviews the highest pay. Show the ten employees with the highest current hourly rate, the rate of each one's
-- latest change.
SELECT TOP (10) p.FirstName, p.LastName, e.JobTitle, ph.Rate, CAST(ph.RateChangeDate AS date) AS RateChangeDate
FROM HumanResources.EmployeePayHistory ph
         JOIN HumanResources.Employee e ON e.BusinessEntityID = ph.BusinessEntityID
         JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
WHERE ph.RateChangeDate = (SELECT MAX(ph2.RateChangeDate)
                           FROM HumanResources.EmployeePayHistory ph2
                           WHERE ph2.BusinessEntityID = ph.BusinessEntityID)
ORDER BY ph.Rate DESC, e.BusinessEntityID
;
GO

-- ---------------------------------------------------------------------------------------------------------- AW-HR-I-04
-- A manager asks who reports to them directly. Show the direct reports of an employee, by the organization chart.
DECLARE @managerId int = 25;
SELECT e.OrganizationNode.ToString() AS Node, e.JobTitle, p.FirstName, p.LastName
FROM HumanResources.Employee e
         JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
WHERE e.OrganizationNode.GetAncestor(1) = (SELECT m.OrganizationNode
                                           FROM HumanResources.Employee m
                                           WHERE m.BusinessEntityID = @managerId)
ORDER BY e.OrganizationNode
;
GO

-- ---------------------------------------------------------------------------------------------------------- AW-HR-A-05
-- An employee asks who is above them. Show the chain of managers of an employee, from the top down to the employee.
DECLARE @employeeId int = 100;
SELECT m.OrganizationNode.ToString() AS Node, m.OrganizationLevel, m.JobTitle, p.FirstName, p.LastName
FROM HumanResources.Employee e
         JOIN HumanResources.Employee m ON e.OrganizationNode.IsDescendantOf(m.OrganizationNode) = 1
         JOIN Person.Person p ON p.BusinessEntityID = m.BusinessEntityID
WHERE e.BusinessEntityID = @employeeId
ORDER BY m.OrganizationLevel
;
GO
