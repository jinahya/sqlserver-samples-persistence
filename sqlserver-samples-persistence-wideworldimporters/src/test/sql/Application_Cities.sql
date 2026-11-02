-- -
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
-- -
--
-- Profiling queries on Application.Cities, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_Cities.sql
--


-- -------------------------------------------------------------------------------------------------------------- CityID
-- how many rows, and the smallest and the largest CityID; 37940 cities, 1 to 38186.
SELECT COUNT(*), MIN(CityID), MAX(CityID)
FROM Application.Cities
;

-- CityID comes from Sequences.CityID and leaves gaps; how many values below the largest are unused. 246.
SELECT MAX(CityID) - COUNT(*) AS unused
FROM Application.Cities
;


-- ------------------------------------------------------------------------------------------------------------ CityName
-- the shortest and the longest CityName; 3 and 35 characters, of the 50 the column allows.
SELECT MIN(LEN(CityName)), MAX(LEN(CityName))
FROM Application.Cities
;

-- a CityName which is blank, or carries leading or trailing spaces.
-- no rows: every CityName is trimmed and not blank.
SELECT CityID, '[' + CityName + ']' AS CityName
FROM Application.Cities
WHERE TRIM(CityName) = ''
   OR DATALENGTH(CityName) <> DATALENGTH(TRIM(CityName))
;

-- how many CityNames have a character other than an ASCII letter, a space, a period, an apostrophe or a hyphen;
-- 73, Spanish names such as Añasco and Bayamón.
SELECT COUNT(*) AS cities
FROM Application.Cities
WHERE CityName COLLATE Latin1_General_BIN LIKE '%[^A-Za-z .''-]%'
;

-- the schema has no unique key on CityName; the names most cities share. Franklin 32, Clinton 29, Marion 28.
SELECT TOP (3) CityName, COUNT(*) AS cities
FROM Application.Cities
GROUP BY CityName
ORDER BY cities DESC, CityName ASC
;

-- a CityName which more than one city of the same state or province has.
-- no rows: a CityName is unique within its state or province.
SELECT StateProvinceID, CityName, COUNT(*) AS cities
FROM Application.Cities
GROUP BY StateProvinceID, CityName
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------- StateProvinceID
-- how many states and provinces the cities are in; all 53.
SELECT COUNT(DISTINCT StateProvinceID)
FROM Application.Cities
;


-- ------------------------------------------------------------------------------------------------------------ Location
-- Location is a nullable geography; how many cities have none, and what the others are.
-- None; every Location is a Point of SRID 4326, 22 bytes.
SELECT Location.STGeometryType() AS type, Location.STSrid AS srid, MIN(DATALENGTH(Location)),
       MAX(DATALENGTH(Location)), COUNT(*) AS cities
FROM Application.Cities
GROUP BY Location.STGeometryType(), Location.STSrid
;

-- the latitudes and longitudes the points span; 17.71 to 71.29 north, -176.66 to 178.88 east.
SELECT MIN(Location.Lat), MAX(Location.Lat), MIN(Location.Long), MAX(Location.Long)
FROM Application.Cities
;


-- -------------------------------------------------------------------------------------------- LatestRecordedPopulation
-- LatestRecordedPopulation is nullable; how many cities have none, how many have 0, and the largest.
-- 11048 have none, 14 have 0, and New York has the largest, 8175133.
SELECT COUNT(*) - COUNT(LatestRecordedPopulation) AS without,
       SUM(CASE WHEN LatestRecordedPopulation = 0 THEN 1 ELSE 0 END) AS zero,
       MAX(LatestRecordedPopulation)
FROM Application.Cities
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who last edited the cities; person 1, Data Conversion Only, for 37912, the rest by 8, 15 and 20.
SELECT c.LastEditedBy, p.FullName, COUNT(*) AS cities
FROM Application.Cities c
         JOIN Application.People p ON p.PersonID = c.LastEditedBy
GROUP BY c.LastEditedBy, p.FullName
ORDER BY c.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- when the current rows begin; 2013-01-01 for 37912 cities, then 10, 5 and 13 on 1 July of 2013, 2014 and 2015.
SELECT ValidFrom, COUNT(*) AS cities
FROM Application.Cities
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a current row of a system-versioned table is valid until the end of time.
-- no rows: every ValidTo is 9999-12-31 23:59:59.9999999.
SELECT CityID, ValidTo
FROM Application.Cities
WHERE ValidTo <> '9999-12-31 23:59:59.9999999'
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many distinct cities customers, suppliers and the system parameters deliver or post to; 667.
SELECT COUNT(DISTINCT u.CityID) AS cities
FROM (SELECT DeliveryCityID AS CityID FROM Sales.Customers
      UNION ALL
      SELECT PostalCityID FROM Sales.Customers
      UNION ALL
      SELECT DeliveryCityID FROM Purchasing.Suppliers
      UNION ALL
      SELECT PostalCityID FROM Purchasing.Suppliers
      UNION ALL
      SELECT DeliveryCityID FROM Application.SystemParameters
      UNION ALL
      SELECT PostalCityID FROM Application.SystemParameters) u
;

-- the cities whose history the archive keeps, and how many versions; 28 cities, one version each.
SELECT COUNT(DISTINCT CityID) AS cities, COUNT(*) AS versions
FROM Application.Cities_Archive
;

