-- #%L
-- sqlserver-samples-persistence-wideworldimporters
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
-- Exercises at the Intermediate level on the Application schema of WideWorldImporters.
--
-- These are the SQL solutions of the 'Application' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- ------------------------------------------------------------------------------------------------ WWI_APPLICATION_I_01
-- Marketing sizes the markets: for each continent, the number of countries and their total population, the most
-- populous continent first.
SELECT Continent, COUNT(*) AS Countries, SUM(LatestRecordedPopulation) AS Population
FROM Application.Countries
GROUP BY Continent
ORDER BY Population DESC
;

-- ------------------------------------------------------------------------------------------------ WWI_APPLICATION_I_02
-- Human resources counts the employees by title, with the earliest hire date of each title. The title and the hire
-- date are in the CustomFields JSON document; JSON_VALUE is SQL only.
SELECT JSON_VALUE(CustomFields, '$.Title')                       AS Title,
       COUNT(*)                                                  AS Employees,
       MIN(CAST(JSON_VALUE(CustomFields, '$.HireDate') AS date)) AS FirstHired
FROM Application.People
WHERE IsEmployee = 1
GROUP BY JSON_VALUE(CustomFields, '$.Title')
ORDER BY Employees DESC, Title
;

