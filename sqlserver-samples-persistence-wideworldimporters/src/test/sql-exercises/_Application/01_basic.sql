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
-- Exercises at the Basic level on the Application schema of WideWorldImporters.
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
ORDER BY FullName, PersonID
;

-- ------------------------------------------------------------------------------------------------ WWI_APPLICATION_B_02
-- The sales manager sizes the sales territories of a country: for each territory, the number of states or provinces and
-- their total population, the most populous first, a tie by territory.
DECLARE @countryName nvarchar(60) = N'United States';
SELECT sp.SalesTerritory, COUNT(*) AS StateProvinces, SUM(sp.LatestRecordedPopulation) AS Population
FROM Application.StateProvinces sp
         JOIN Application.Countries c ON c.CountryID = sp.CountryID
WHERE c.CountryName = @countryName
GROUP BY sp.SalesTerritory
ORDER BY Population DESC, sp.SalesTerritory
;

-- ------------------------------------------------------------------------------------------------ WWI_APPLICATION_B_03
-- List a country's cities with their state or province and their latest recorded population, by state or province, city
-- name and `CityID`.
DECLARE @countryId int = 230;
SELECT c.CityID, c.CityName, sp.StateProvinceName, co.CountryName, c.LatestRecordedPopulation
FROM Application.Cities c
         JOIN Application.StateProvinces sp ON sp.StateProvinceID = c.StateProvinceID
         JOIN Application.Countries co ON co.CountryID = sp.CountryID
WHERE co.CountryID = @countryId
ORDER BY sp.StateProvinceName, c.CityName, c.CityID
;

