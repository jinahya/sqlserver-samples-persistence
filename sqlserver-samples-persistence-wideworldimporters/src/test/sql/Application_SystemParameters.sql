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
-- Profiling queries on Application.SystemParameters, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_SystemParameters.sql
--


-- --------------------------------------------------------------------------------------------------- SystemParameterID
-- how many rows, and the SystemParameterID; a single row, 1.
SELECT COUNT(*), MIN(SystemParameterID), MAX(SystemParameterID)
FROM Application.SystemParameters
;


-- ------------------------------------------------------------------------------------------------ DeliveryAddressLine1
-- the delivery address of the company; Suite 14, 1968 Martin Luther King Junior Drive, 94129.
SELECT DeliveryAddressLine1, DeliveryAddressLine2, DeliveryPostalCode
FROM Application.SystemParameters
;

-- a DeliveryAddressLine1 which is blank, or carries leading or trailing spaces.
-- no rows: it is trimmed and not blank.
SELECT SystemParameterID, '[' + DeliveryAddressLine1 + ']' AS DeliveryAddressLine1
FROM Application.SystemParameters
WHERE TRIM(DeliveryAddressLine1) = ''
   OR DATALENGTH(DeliveryAddressLine1) <> DATALENGTH(TRIM(DeliveryAddressLine1))
;


-- ------------------------------------------------------------------------------------------------ DeliveryAddressLine2
-- DeliveryAddressLine2 is nullable; a row without one.
-- no rows: the single row has one.
SELECT SystemParameterID
FROM Application.SystemParameters
WHERE DeliveryAddressLine2 IS NULL
;


-- ------------------------------------------------------------------------------------------------------ DeliveryCityID
-- the city the company delivers from; San Francisco (30378), California.
SELECT p.DeliveryCityID, c.CityName, s.StateProvinceName
FROM Application.SystemParameters p
         JOIN Application.Cities c ON c.CityID = p.DeliveryCityID
         JOIN Application.StateProvinces s ON s.StateProvinceID = c.StateProvinceID
;


-- -------------------------------------------------------------------------------------------------- DeliveryPostalCode
-- a DeliveryPostalCode which is not five digits.
-- no rows: it is 94129.
SELECT SystemParameterID, DeliveryPostalCode
FROM Application.SystemParameters
WHERE DeliveryPostalCode NOT LIKE '[0-9][0-9][0-9][0-9][0-9]'
;


-- ---------------------------------------------------------------------------------------------------- DeliveryLocation
-- DeliveryLocation is a geography; what it is, and how far it is from the point of its city.
-- POINT (-122.504086 37.765786), 7529 meters from the point of San Francisco.
SELECT p.DeliveryLocation.ToString() AS location, ROUND(p.DeliveryLocation.STDistance(c.Location), 0) AS meters
FROM Application.SystemParameters p
         JOIN Application.Cities c ON c.CityID = p.DeliveryCityID
;


-- -------------------------------------------------------------------------------------------------- PostalAddressLine1
-- the postal address of the company; PO Box 201158, Golden Gate Park, 94129.
SELECT PostalAddressLine1, PostalAddressLine2, PostalPostalCode
FROM Application.SystemParameters
;


-- -------------------------------------------------------------------------------------------------- PostalAddressLine2
-- PostalAddressLine2 is nullable; a row without one.
-- no rows: the single row has one.
SELECT SystemParameterID
FROM Application.SystemParameters
WHERE PostalAddressLine2 IS NULL
;


-- -------------------------------------------------------------------------------------------------------- PostalCityID
-- a PostalCityID other than the DeliveryCityID.
-- no rows: the company posts and delivers in the same city.
SELECT SystemParameterID, DeliveryCityID, PostalCityID
FROM Application.SystemParameters
WHERE PostalCityID <> DeliveryCityID
;


-- ---------------------------------------------------------------------------------------------------- PostalPostalCode
-- a PostalPostalCode which is not five digits.
-- no rows: it is 94129.
SELECT SystemParameterID, PostalPostalCode
FROM Application.SystemParameters
WHERE PostalPostalCode NOT LIKE '[0-9][0-9][0-9][0-9][0-9]'
;


-- ------------------------------------------------------------------------------------------------- ApplicationSettings
-- ApplicationSettings is a JSON text; whether it is valid JSON, and how long. Valid; 3298 characters.
SELECT ISJSON(ApplicationSettings) AS json, LEN(ApplicationSettings)
FROM Application.SystemParameters
;

-- the site title the settings carry; WWI | Site.
SELECT JSON_VALUE(ApplicationSettings, '$.Site.SEO.Title') AS title
FROM Application.SystemParameters
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who last edited the row; person 1, Data Conversion Only.
SELECT s.LastEditedBy, p.FullName
FROM Application.SystemParameters s
         JOIN Application.People p ON p.PersonID = s.LastEditedBy
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- when the row was last edited; 2013-01-01 00:00.
SELECT LastEditedWhen
FROM Application.SystemParameters
;

