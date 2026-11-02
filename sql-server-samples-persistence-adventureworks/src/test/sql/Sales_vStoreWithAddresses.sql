-- -
-- #%L
-- sql-server-samples-persistence-adventureworks
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
-- Profiling queries on the view Sales.vStoreWithAddresses, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_vStoreWithAddresses.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows and stores; 712 rows of all 701 stores, the view having a row per address.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM Sales.vStoreWithAddresses
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- a Name which is not that of Sales.Store.
-- no rows: the view copies Name.
SELECT v.BusinessEntityID, v.Name, s.Name
FROM Sales.vStoreWithAddresses v
         JOIN Sales.Store s ON s.BusinessEntityID = v.BusinessEntityID
WHERE v.Name <> s.Name
;


-- --------------------------------------------------------------------------------------------------------- AddressType
-- how many rows each AddressType has; Main Office 701, one per store, and Shipping 11.
SELECT AddressType, COUNT(*) AS addresses
FROM Sales.vStoreWithAddresses
GROUP BY AddressType
ORDER BY AddressType ASC
;


-- -------------------------------------------------------------------------------------------------------- AddressLine1
-- the shortest and the longest AddressLine1; 5 and 39 characters.
SELECT MIN(LEN(AddressLine1)), MAX(LEN(AddressLine1))
FROM Sales.vStoreWithAddresses
;


-- -------------------------------------------------------------------------------------------------------- AddressLine2
-- how many rows have an AddressLine2; 33.
SELECT COUNT(AddressLine2) AS addresses
FROM Sales.vStoreWithAddresses
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- how many cities, and the shortest and the longest; 451, 4 and 20 characters.
SELECT COUNT(DISTINCT City), MIN(LEN(City)), MAX(LEN(City))
FROM Sales.vStoreWithAddresses
;


-- --------------------------------------------------------------------------------------------------- StateProvinceName
-- how many states and provinces; 65.
SELECT COUNT(DISTINCT StateProvinceName)
FROM Sales.vStoreWithAddresses
;


-- ---------------------------------------------------------------------------------------------------------- PostalCode
-- how many postal codes, and the shortest and the longest; 501, 2 and 10 characters.
SELECT COUNT(DISTINCT PostalCode), MIN(LEN(PostalCode)), MAX(LEN(PostalCode))
FROM Sales.vStoreWithAddresses
;


-- --------------------------------------------------------------------------------------------------- CountryRegionName
-- how many rows each CountryRegionName has; United States 437, Canada 115, and 40 each for the other four.
SELECT CountryRegionName, COUNT(*) AS addresses
FROM Sales.vStoreWithAddresses
GROUP BY CountryRegionName
ORDER BY CountryRegionName ASC
;

