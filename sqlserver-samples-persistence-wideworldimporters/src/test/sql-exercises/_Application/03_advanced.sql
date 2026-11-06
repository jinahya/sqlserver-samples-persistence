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
-- Exercises at the Advanced level on the Application schema of WideWorldImporters.
--
-- These are the SQL solutions of the 'Application' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- ------------------------------------------------------------------------------------------------ WWI_APPLICATION_A_01
-- Find the five other cities nearest to a given city, with their state code and the distance in kilometres; a city
-- whose distance cannot be computed is left out, and `CityID` breaks a tie.
DECLARE @cityId int = 30378; -- San Francisco
SELECT TOP (5) c.CityID, c.CityName, sp.StateProvinceCode, CAST(c.Location.STDistance(o.Location) / 1000 AS decimal(10, 1)) AS Kilometres
FROM Application.Cities c
         JOIN Application.StateProvinces sp ON sp.StateProvinceID = c.StateProvinceID
         JOIN Application.Cities o ON o.CityID = @cityId
WHERE c.CityID <> o.CityID
  AND c.Location.STDistance(o.Location) IS NOT NULL
ORDER BY c.Location.STDistance(o.Location), c.CityID
;

