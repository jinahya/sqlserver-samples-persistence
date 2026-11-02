-- -
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
-- -
--
-- Profiling queries on the view Purchasing.vVendorWithAddresses, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Purchasing_vVendorWithAddresses.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, how many distinct BusinessEntityID, and the smallest and the largest; 104, one per vendor, 1492 to
-- 1698.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM Purchasing.vVendorWithAddresses
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 5 and 33 characters.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Purchasing.vVendorWithAddresses
;

-- a Name which is blank, or carries leading or trailing spaces.
-- no rows: every Name is trimmed and not blank.
SELECT BusinessEntityID, '[' + Name + ']' AS Name
FROM Purchasing.vVendorWithAddresses
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;


-- --------------------------------------------------------------------------------------------------------- AddressType
-- the values; Main Office, all 104.
SELECT AddressType, COUNT(*) AS addresses
FROM Purchasing.vVendorWithAddresses
GROUP BY AddressType
ORDER BY AddressType ASC
;


-- -------------------------------------------------------------------------------------------------------- AddressLine1
-- the shortest and the longest AddressLine1; 7 and 23 characters.
SELECT MIN(LEN(AddressLine1)), MAX(LEN(AddressLine1))
FROM Purchasing.vVendorWithAddresses
;

-- an AddressLine1 which is blank, or carries leading or trailing spaces.
-- no rows: every AddressLine1 is trimmed and not blank.
SELECT BusinessEntityID, '[' + AddressLine1 + ']' AS AddressLine1
FROM Purchasing.vVendorWithAddresses
WHERE TRIM(AddressLine1) = ''
   OR DATALENGTH(AddressLine1) <> DATALENGTH(TRIM(AddressLine1))
;


-- -------------------------------------------------------------------------------------------------------- AddressLine2
-- how many rows leave AddressLine2 NULL, and the shortest and the longest; 97 NULLs; 2 to 10 characters.
SELECT COUNT(*) - COUNT(AddressLine2) AS nulls, MIN(LEN(AddressLine2)), MAX(LEN(AddressLine2))
FROM Purchasing.vVendorWithAddresses
;

-- an AddressLine2 which is blank, or carries leading or trailing spaces.
-- no rows: every AddressLine2 is trimmed and not blank.
SELECT BusinessEntityID, '[' + AddressLine2 + ']' AS AddressLine2
FROM Purchasing.vVendorWithAddresses
WHERE TRIM(AddressLine2) = ''
   OR DATALENGTH(AddressLine2) <> DATALENGTH(TRIM(AddressLine2))
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- the shortest and the longest City; 5 and 14 characters.
SELECT MIN(LEN(City)), MAX(LEN(City))
FROM Purchasing.vVendorWithAddresses
;

-- a City which is blank, or carries leading or trailing spaces.
-- no rows: every City is trimmed and not blank.
SELECT BusinessEntityID, '[' + City + ']' AS City
FROM Purchasing.vVendorWithAddresses
WHERE TRIM(City) = ''
   OR DATALENGTH(City) <> DATALENGTH(TRIM(City))
;


-- --------------------------------------------------------------------------------------------------- StateProvinceName
-- how many states, and the most common; 21; California with 39, Washington 25 and Oregon 13.
SELECT TOP (3) StateProvinceName, COUNT(*) AS addresses, COUNT(*) OVER () AS provinces
FROM Purchasing.vVendorWithAddresses
GROUP BY StateProvinceName
ORDER BY addresses DESC, StateProvinceName ASC
;


-- ---------------------------------------------------------------------------------------------------------- PostalCode
-- the shortest and the longest PostalCode; 5 and 5 characters.
SELECT MIN(LEN(PostalCode)), MAX(LEN(PostalCode))
FROM Purchasing.vVendorWithAddresses
;

-- a PostalCode which is blank, or carries leading or trailing spaces.
-- no rows: every PostalCode is trimmed and not blank.
SELECT BusinessEntityID, '[' + PostalCode + ']' AS PostalCode
FROM Purchasing.vVendorWithAddresses
WHERE TRIM(PostalCode) = ''
   OR DATALENGTH(PostalCode) <> DATALENGTH(TRIM(PostalCode))
;


-- --------------------------------------------------------------------------------------------------- CountryRegionName
-- the values; United States, all 104.
SELECT CountryRegionName, COUNT(*) AS addresses
FROM Purchasing.vVendorWithAddresses
GROUP BY CountryRegionName
ORDER BY CountryRegionName ASC
;

