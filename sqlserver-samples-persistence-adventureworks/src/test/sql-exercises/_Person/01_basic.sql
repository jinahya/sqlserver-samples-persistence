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
-- Exercises at the Basic level on the Person schema, a problem per query.
--
-- These are the SQL solutions of the 'Person' section of EXERCISES.asciidoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Each problem is a batch of its own, ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- ------------------------------------------------------------------------------------------------------ AW_PERSON_B_01
-- The data steward checks who the database holds. Show how many persons there are of each type, the most first.
SELECT p.PersonType, COUNT(*) AS Persons
FROM Person.Person p
GROUP BY p.PersonType
ORDER BY Persons DESC
;
GO


-- ------------------------------------------------------------------------------------------------------ AW_PERSON_B_02
-- The data steward checks how the addresses are used. Show how many addresses are held for each address type, the most
-- first.
SELECT t.Name, COUNT(*) AS Addresses
FROM Person.BusinessEntityAddress bea
         JOIN Person.AddressType t ON t.AddressTypeID = bea.AddressTypeID
GROUP BY t.AddressTypeID, t.Name
ORDER BY Addresses DESC, t.Name
;
GO


-- ------------------------------------------------------------------------------------------------------ AW_PERSON_B_03
-- The account manager wants the contact persons of a business entity and the role of each, ordered by last name, first
-- name, person and role.
DECLARE @businessEntityId int = 1492;
SELECT p.BusinessEntityID AS PersonID, p.FirstName, p.LastName, t.Name AS ContactType
FROM Person.BusinessEntityContact c
         JOIN Person.Person p ON p.BusinessEntityID = c.PersonID
         JOIN Person.ContactType t ON t.ContactTypeID = c.ContactTypeID
WHERE c.BusinessEntityID = @businessEntityId
ORDER BY p.LastName, p.FirstName, p.BusinessEntityID, t.ContactTypeID
;
GO

