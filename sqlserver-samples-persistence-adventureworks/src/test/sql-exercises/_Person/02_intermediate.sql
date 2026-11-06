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
-- Exercises at the Intermediate level on the Person schema, a problem per query.
--
-- These are the SQL solutions of the 'Person' section of EXERCISES.asciidoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Each problem is a batch of its own, ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- ------------------------------------------------------------------------------------------------------ AW_PERSON_I_01
-- Marketing picks the countries for a mailing. Show the five countries with the most addresses; `CountryRegionCode`
-- breaks a tie.
SELECT TOP (5) c.CountryRegionCode, c.Name, COUNT(*) AS Addresses
FROM Person.Address a
         JOIN Person.StateProvince s ON s.StateProvinceID = a.StateProvinceID
         JOIN Person.CountryRegion c ON c.CountryRegionCode = s.CountryRegionCode
GROUP BY c.CountryRegionCode, c.Name
ORDER BY Addresses DESC, c.CountryRegionCode
;
GO

