-- #%L
-- sql-server-samples-persistence-wideworldimporters
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
-- Exercises on the Application schema of WideWorldImporters.
--
-- These are the SQL solutions of the 'Application' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- ------------------------------------------------------------------------------------------------ WWI_APPLICATION_B_01
-- The sales manager wants the salespeople, by name, with their phone number and email address.
SELECT PersonID, FullName, PhoneNumber, EmailAddress
FROM Application.People
WHERE IsSalesperson = 1
ORDER BY FullName
;


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


-- ------------------------------------------------------------------------------------------------ WWI_APPLICATION_A_01
-- Logistics plans a route from one city: the five cities nearest to it, with their state code and the distance in
-- kilometres. Location is a geography; its methods, STDistance among them, are SQL only.
DECLARE
@cityId int = 30378; -- San Francisco
SELECT TOP(5) c.CityName, sp.StateProvinceCode,
       CAST(c.Location.STDistance(o.Location) / 1000 AS decimal(10, 1)) AS Kilometres
FROM Application.Cities c
         JOIN Application.StateProvinces sp ON sp.StateProvinceID = c.StateProvinceID
         JOIN Application.Cities o ON o.CityID = @cityId
WHERE c.CityID <> o.CityID
  AND c.Location IS NOT NULL
ORDER BY c.Location.STDistance(o.Location)
;
