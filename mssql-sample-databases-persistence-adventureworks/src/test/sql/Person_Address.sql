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
-- Profiling queries on Person.Address, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_Address.sql
--


-- ----------------------------------------------------------------------------------------------------------- AddressID
-- how many rows, and the smallest and the largest AddressID; 19614 addresses, 1 to 32521.
SELECT COUNT(*), MIN(AddressID), MAX(AddressID)
FROM Person.Address
;

-- the runs of AddressID missing below the largest; 3 runs, 1106 to 11379, 11388 to 11399 and 29884 to 32504, 12907
-- values in all.
SELECT a.AddressID + 1 AS missing_from,
       (SELECT MIN(b.AddressID) FROM Person.Address b WHERE b.AddressID > a.AddressID) - 1 AS missing_to
FROM Person.Address a
WHERE a.AddressID < (SELECT MAX(AddressID) FROM Person.Address)
  AND NOT EXISTS (SELECT 1
                  FROM Person.Address b
                  WHERE b.AddressID = a.AddressID + 1)
ORDER BY missing_from ASC
;


-- -------------------------------------------------------------------------------------------------------- AddressLine1
-- the shortest and the longest AddressLine1; 5 and 39 characters, of the 60 the column allows.
SELECT MIN(LEN(AddressLine1)), MAX(LEN(AddressLine1))
FROM Person.Address
;

-- an AddressLine1 which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every AddressLine1 is trimmed and not blank.
SELECT AddressID, '[' + AddressLine1 + ']' AS AddressLine1
FROM Person.Address
WHERE TRIM(AddressLine1) = ''
   OR DATALENGTH(AddressLine1) <> DATALENGTH(TRIM(AddressLine1))
;


-- -------------------------------------------------------------------------------------------------------- AddressLine2
-- AddressLine2 is nullable; how many addresses have one, and how long it is. 362; 1 to 28 characters, of the 60.
SELECT COUNT(AddressLine2) AS with_line2, MIN(LEN(AddressLine2)), MAX(LEN(AddressLine2))
FROM Person.Address
;

-- an AddressLine2 which is blank, or carries leading or trailing spaces.
-- no rows: every AddressLine2 is trimmed and not blank; an address without one has NULL.
SELECT AddressID, '[' + AddressLine2 + ']' AS AddressLine2
FROM Person.Address
WHERE TRIM(AddressLine2) = ''
   OR DATALENGTH(AddressLine2) <> DATALENGTH(TRIM(AddressLine2))
;

-- an address whose AddressLine2 repeats its AddressLine1.
-- no rows: the two lines always differ.
SELECT AddressID, AddressLine1
FROM Person.Address
WHERE AddressLine2 = AddressLine1
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- the shortest and the longest City; 3 and 21 characters, of the 30 the column allows.
SELECT MIN(LEN(City)), MAX(LEN(City))
FROM Person.Address
;

-- a City which is blank, or carries leading or trailing spaces.
-- no rows: every City is trimmed and not blank.
SELECT AddressID, '[' + City + ']' AS City
FROM Person.Address
WHERE TRIM(City) = ''
   OR DATALENGTH(City) <> DATALENGTH(TRIM(City))
;

-- how many cities the addresses name; 575.
SELECT COUNT(DISTINCT City) AS cities
FROM Person.Address
;

-- the four cities with the most addresses; London with 434, Paris with 398, Burien with 215 and Concord with 214.
SELECT TOP (4) City, COUNT(*) AS addresses
FROM Person.Address
GROUP BY City
ORDER BY addresses DESC, City ASC
;


-- ----------------------------------------------------------------------------------------------------- StateProvinceID
-- the state provinces the addresses are in; 74 of the 181.
SELECT COUNT(DISTINCT StateProvinceID) AS state_provinces
FROM Person.Address
;


-- ---------------------------------------------------------------------------------------------------------- PostalCode
-- the shortest and the longest PostalCode; 2 and 10 characters, of the 15 the column allows.
SELECT MIN(LEN(PostalCode)), MAX(LEN(PostalCode))
FROM Person.Address
;

-- a PostalCode which is blank, or carries leading or trailing spaces.
-- no rows: every PostalCode is trimmed and not blank.
SELECT AddressID, '[' + PostalCode + ']' AS PostalCode
FROM Person.Address
WHERE TRIM(PostalCode) = ''
   OR DATALENGTH(PostalCode) <> DATALENGTH(TRIM(PostalCode))
;

-- the length of PostalCode in each country or region; AU 4, CA 2 to 7, DE 5, FR 5, GB 3 to 8 and US 5 to 10.
SELECT s.CountryRegionCode, MIN(LEN(a.PostalCode)), MAX(LEN(a.PostalCode)), COUNT(*) AS addresses
FROM Person.Address a
         JOIN Person.StateProvince s ON s.StateProvinceID = a.StateProvinceID
GROUP BY s.CountryRegionCode
ORDER BY s.CountryRegionCode ASC
;

-- a Canadian postal code is 7 characters, A9A 9A9; how many of each length the Canadian addresses carry. 1000 of 7,
-- 90 of 6, without the space (V2L3W8); and 293 of 3 (V8P) and 305 of 2 (R3, T5), 598 codes cut short.
SELECT LEN(a.PostalCode) AS length, COUNT(*) AS addresses
FROM Person.Address a
         JOIN Person.StateProvince s ON s.StateProvinceID = a.StateProvinceID
WHERE s.CountryRegionCode = 'CA'
GROUP BY LEN(a.PostalCode)
ORDER BY length ASC
;


-- ----------------------------------------------------------------------------------------------------- SpatialLocation
-- SpatialLocation is a nullable geography; how many addresses have none, and how large the others are. None; 22
-- bytes each.
SELECT COUNT(*) - COUNT(SpatialLocation) AS without, MIN(DATALENGTH(SpatialLocation)), MAX(DATALENGTH(SpatialLocation))
FROM Person.Address
;

-- the kinds of geography and the spatial reference systems; Point, in 4326 (WGS 84) only.
SELECT SpatialLocation.STGeometryType() AS type, SpatialLocation.STSrid AS srid, COUNT(*) AS addresses
FROM Person.Address
GROUP BY SpatialLocation.STGeometryType(), SpatialLocation.STSrid
;

-- the range of the points; latitude -42.90 to 55.06, longitude -124.03 to 153.64.
SELECT MIN(SpatialLocation.Lat), MAX(SpatialLocation.Lat), MIN(SpatialLocation.Long), MAX(SpatialLocation.Long)
FROM Person.Address
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_Address_rowguid keeps rowguid unique.
-- no rows: no two addresses share a rowguid.
SELECT rowguid, COUNT(*) AS addresses
FROM Person.Address
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2017-06-22 00:00:00.000 to 2025-06-29 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.Address
;

-- a ModifiedDate not at midnight, by country or region; the 1851 French addresses, all 2024-12-20 10:09:29.423.
SELECT s.CountryRegionCode, COUNT(*) AS addresses, MIN(a.ModifiedDate), MAX(a.ModifiedDate)
FROM Person.Address a
         JOIN Person.StateProvince s ON s.StateProvinceID = a.StateProvinceID
WHERE CAST(a.ModifiedDate AS time) <> '00:00'
GROUP BY s.CountryRegionCode
;

-- how many rows each year; most in the last years, 9821 in 2024 and 5326 in 2025, a single one in 2017.
SELECT YEAR(ModifiedDate) AS year, COUNT(*) AS addresses
FROM Person.Address
GROUP BY YEAR(ModifiedDate)
ORDER BY year ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- IX_Address_AddressLine1_AddressLine2_City_StateProvinceID_PostalCode keeps an address unique.
-- no rows: no two rows share all five columns.
SELECT AddressLine1, AddressLine2, City, StateProvinceID, PostalCode, COUNT(*) AS addresses
FROM Person.Address
GROUP BY AddressLine1, AddressLine2, City, StateProvinceID, PostalCode
HAVING COUNT(*) > 1
;

-- an address no business entity has; and how many business entities each address has.
-- one row: every address belongs to exactly one business entity, 19614.
SELECT b.business_entities, COUNT(*) AS addresses
FROM Person.Address a
         CROSS APPLY (SELECT COUNT(*) AS business_entities
                      FROM Person.BusinessEntityAddress e
                      WHERE e.AddressID = a.AddressID) b
GROUP BY b.business_entities
;

