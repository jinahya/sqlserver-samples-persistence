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
-- Exercises at the Advanced level on the HumanResources schema, a problem per query.
--
-- These are the SQL solutions of the 'HumanResources' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- ---------------------------------------------------------------------------------------------- AW_HUMANRESOURCES_A_01
-- An employee asks who is above them. Show the chain of managers of an employee, from the top down to the employee.
DECLARE
@employeeId int = 100;
SELECT m.OrganizationNode.ToString() AS Node,
       m.OrganizationLevel,
       m.JobTitle,
       p.FirstName,
       p.LastName
FROM HumanResources.Employee e
         JOIN HumanResources.Employee m ON e.OrganizationNode.IsDescendantOf(m.OrganizationNode) = 1
         JOIN Person.Person p ON p.BusinessEntityID = m.BusinessEntityID
WHERE e.BusinessEntityID = @employeeId
ORDER BY m.OrganizationLevel
;
GO

