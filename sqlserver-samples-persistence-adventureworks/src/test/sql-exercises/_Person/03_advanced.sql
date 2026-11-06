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
-- Exercises at the Advanced level on the Person schema, a problem per query.
--
-- These are the SQL solutions of the 'Person' section of EXERCISES.asciidoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Each problem is a batch of its own, ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- ------------------------------------------------------------------------------------------------------ AW_PERSON_A_01
-- Marketing segments the individual customers. Show how many of them answered each yearly income band of the survey
-- stored in their Demographics document, the customers who did not answer left out.
WITH XMLNAMESPACES (DEFAULT 'http://schemas.microsoft.com/sqlserver/2004/07/adventure-works/IndividualSurvey')
SELECT i.YearlyIncome, COUNT(*) AS Persons
FROM (SELECT p.Demographics.value('(/IndividualSurvey/YearlyIncome)[1]', 'nvarchar(30)') AS YearlyIncome
      FROM Person.Person p
      WHERE p.PersonType = 'IN'
        AND p.Demographics.exist('/IndividualSurvey/YearlyIncome') = 1) AS i
GROUP BY i.YearlyIncome
ORDER BY i.YearlyIncome
;
GO

