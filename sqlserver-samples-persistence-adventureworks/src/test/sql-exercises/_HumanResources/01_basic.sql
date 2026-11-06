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
-- Exercises at the Basic level on the HumanResources schema, a problem per query.
--
-- These are the SQL solutions of the 'HumanResources' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- ---------------------------------------------------------------------------------------------- AW_HUMANRESOURCES_B_01
-- HR plans next year's budget. Show how many employees each department has now, the largest first.
SELECT d.Name, d.GroupName, COUNT(h.BusinessEntityID) AS Employees
FROM HumanResources.Department d
         LEFT JOIN HumanResources.EmployeeDepartmentHistory h ON h.DepartmentID = d.DepartmentID AND h.EndDate IS NULL
GROUP BY d.DepartmentID, d.Name, d.GroupName
ORDER BY Employees DESC, d.Name
;
GO

-- ---------------------------------------------------------------------------------------------- AW_HUMANRESOURCES_B_02
-- HR draws the organization chart. Show how many employees are at each level of the organization.
SELECT e.OrganizationLevel, COUNT(*) AS Employees
FROM HumanResources.Employee e
GROUP BY e.OrganizationLevel
ORDER BY e.OrganizationLevel
;
GO

