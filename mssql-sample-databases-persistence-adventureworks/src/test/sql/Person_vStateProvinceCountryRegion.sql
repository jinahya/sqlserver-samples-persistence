-- -
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
-- -
--
-- Profiling queries on the view Person.vStateProvinceCountryRegion, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_vStateProvinceCountryRegion.sql
--


-- ----------------------------------------------------------------------------------------------------- StateProvinceID
-- how many rows, and the smallest and the largest StateProvinceID; 181 rows, 1 to 181, one for each StateProvince.
SELECT COUNT(*), MIN(StateProvinceID), MAX(StateProvinceID)
FROM Person.vStateProvinceCountryRegion
;

-- the view is indexed; IX_vStateProvinceCountryRegion keeps the pair of StateProvinceID and CountryRegionCode
-- unique. A StateProvinceID more than one row has.
-- no rows: every StateProvinceID is unique on its own.
SELECT StateProvinceID, COUNT(*) AS state_provinces
FROM Person.vStateProvinceCountryRegion
GROUP BY StateProvinceID
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------- StateProvinceCode
-- the shortest and the longest StateProvinceCode, the nchar padding aside; 2 and 3 characters.
SELECT MIN(LEN(StateProvinceCode)), MAX(LEN(StateProvinceCode))
FROM Person.vStateProvinceCountryRegion
;


-- --------------------------------------------------------------------------------------------- IsOnlyStateProvinceFlag
-- how many rows carry each flag; 0 on 173, 1 on 8.
SELECT IsOnlyStateProvinceFlag, COUNT(*) AS state_provinces
FROM Person.vStateProvinceCountryRegion
GROUP BY IsOnlyStateProvinceFlag
ORDER BY IsOnlyStateProvinceFlag ASC
;


-- --------------------------------------------------------------------------------------------------- StateProvinceName
-- the shortest and the longest StateProvinceName; 3 and 24 characters, of the 50 the column allows.
SELECT MIN(LEN(StateProvinceName)), MAX(LEN(StateProvinceName))
FROM Person.vStateProvinceCountryRegion
;


-- --------------------------------------------------------------------------------------------------------- TerritoryID
-- the sales territories the rows fall in; all 10, 1 to 10.
SELECT COUNT(DISTINCT TerritoryID), MIN(TerritoryID), MAX(TerritoryID)
FROM Person.vStateProvinceCountryRegion
;


-- --------------------------------------------------------------------------------------------------- CountryRegionCode
-- the countries and regions the rows fall in; 12 of the 238 CountryRegion has.
SELECT COUNT(DISTINCT CountryRegionCode)
FROM Person.vStateProvinceCountryRegion
;


-- --------------------------------------------------------------------------------------------------- CountryRegionName
-- the shortest and the longest CountryRegionName; 5 and 24 characters, of the 50 the column allows.
SELECT MIN(LEN(CountryRegionName)), MAX(LEN(CountryRegionName))
FROM Person.vStateProvinceCountryRegion
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a row which does not repeat its StateProvince and CountryRegion, column by column.
-- no rows: the view is the join of the two tables, unchanged.
SELECT v.StateProvinceID
FROM Person.vStateProvinceCountryRegion v
         JOIN Person.StateProvince s ON s.StateProvinceID = v.StateProvinceID
         JOIN Person.CountryRegion c ON c.CountryRegionCode = s.CountryRegionCode
WHERE v.StateProvinceCode <> s.StateProvinceCode
   OR v.IsOnlyStateProvinceFlag <> s.IsOnlyStateProvinceFlag
   OR v.StateProvinceName <> s.Name
   OR v.TerritoryID <> s.TerritoryID
   OR v.CountryRegionCode <> c.CountryRegionCode
   OR v.CountryRegionName <> c.Name
;

-- a StateProvince the view leaves out.
-- no rows: every state province is in the view.
SELECT s.StateProvinceID
FROM Person.StateProvince s
WHERE NOT EXISTS (SELECT 1
                  FROM Person.vStateProvinceCountryRegion v
                  WHERE v.StateProvinceID = s.StateProvinceID)
;

