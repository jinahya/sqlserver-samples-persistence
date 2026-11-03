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
-- Profiling queries on the view Website.Suppliers, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Website_Suppliers.sql
--


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- how many rows, and the smallest and the largest SupplierID; 13 suppliers, 1 to 13.
SELECT COUNT(*), MIN(SupplierID), MAX(SupplierID)
FROM Website.Suppliers
;

-- the view left joins every lookup to Purchasing.Suppliers; a supplier it repeats.
-- no rows: one row per supplier.
SELECT SupplierID, COUNT(*) AS supplier_rows
FROM Website.Suppliers
GROUP BY SupplierID
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- SupplierName
-- the shortest and the longest SupplierName; 13 and 24 characters.
SELECT MIN(LEN(SupplierName)), MAX(LEN(SupplierName))
FROM Website.Suppliers
;


-- ------------------------------------------------------------------------------------------------ SupplierCategoryName
-- the suppliers with no SupplierCategoryName, and how many distinct the others have; none, 8 categories.
SELECT COUNT(*) - COUNT(SupplierCategoryName), COUNT(DISTINCT SupplierCategoryName)
FROM Website.Suppliers
;


-- ------------------------------------------------------------------------------------------------------ PrimaryContact
-- the suppliers with no PrimaryContact, and how many distinct the others have; none, 13 names.
SELECT COUNT(*) - COUNT(PrimaryContact), COUNT(DISTINCT PrimaryContact)
FROM Website.Suppliers
;


-- ---------------------------------------------------------------------------------------------------- AlternateContact
-- the suppliers with no AlternateContact, and how many distinct the others have; none, 13 names.
SELECT COUNT(*) - COUNT(AlternateContact), COUNT(DISTINCT AlternateContact)
FROM Website.Suppliers
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- how many distinct phone numbers; 13, one per supplier.
SELECT COUNT(DISTINCT PhoneNumber)
FROM Website.Suppliers
;


-- ----------------------------------------------------------------------------------------------------------- FaxNumber
-- how many distinct fax numbers; 13, one per supplier.
SELECT COUNT(DISTINCT FaxNumber)
FROM Website.Suppliers
;


-- ---------------------------------------------------------------------------------------------------------- WebsiteURL
-- the shortest and the longest WebsiteURL; 21 and 37 characters.
SELECT MIN(LEN(WebsiteURL)), MAX(LEN(WebsiteURL))
FROM Website.Suppliers
;


-- ------------------------------------------------------------------------------------------------------ DeliveryMethod
-- the suppliers with no DeliveryMethod, and how many distinct the others have; 4, 5 methods.
SELECT COUNT(*) - COUNT(DeliveryMethod), COUNT(DISTINCT DeliveryMethod)
FROM Website.Suppliers
;


-- ------------------------------------------------------------------------------------------------------------ CityName
-- the suppliers with no CityName, and how many distinct the others have; none, 12 cities.
SELECT COUNT(*) - COUNT(CityName), COUNT(DISTINCT CityName)
FROM Website.Suppliers
;


-- ---------------------------------------------------------------------------------------------------- DeliveryLocation
-- the suppliers with no DeliveryLocation, and the kinds of the others; none is NULL, all 13 are a Point.
SELECT DeliveryLocation.STGeometryType() AS geometry_type, COUNT(*) AS suppliers
FROM Website.Suppliers
GROUP BY DeliveryLocation.STGeometryType()
;


-- --------------------------------------------------------------------------------------------------- SupplierReference
-- the suppliers with no SupplierReference, and how many distinct the others have; none, 13
-- references.
SELECT COUNT(*) - COUNT(SupplierReference), COUNT(DISTINCT SupplierReference)
FROM Website.Suppliers
;

