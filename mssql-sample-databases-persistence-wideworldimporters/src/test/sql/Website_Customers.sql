-- -
-- #%L
-- mssql-sample-databases-persistence-wideworldimporters
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
-- Profiling queries on the view Website.Customers, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Website_Customers.sql
--


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many rows, and the smallest and the largest CustomerID; 663 customers, 1 to 1061, as in Sales.Customers.
SELECT COUNT(*), MIN(CustomerID), MAX(CustomerID)
FROM Website.Customers
;

-- the view left joins every lookup to Sales.Customers; a customer it repeats.
-- no rows: one row per customer.
SELECT CustomerID, COUNT(*) AS customer_rows
FROM Website.Customers
GROUP BY CustomerID
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- the shortest and the longest CustomerName; 6 and 38 characters.
SELECT MIN(LEN(CustomerName)), MAX(LEN(CustomerName))
FROM Website.Customers
;


-- ------------------------------------------------------------------------------------------------ CustomerCategoryName
-- the customers with no CustomerCategoryName, and how many distinct the others have; none, 5 categories.
SELECT COUNT(*) - COUNT(CustomerCategoryName), COUNT(DISTINCT CustomerCategoryName)
FROM Website.Customers
;


-- ------------------------------------------------------------------------------------------------------ PrimaryContact
-- the customers with no PrimaryContact, and how many distinct the others have; none, 663 names.
SELECT COUNT(*) - COUNT(PrimaryContact), COUNT(DISTINCT PrimaryContact)
FROM Website.Customers
;


-- ---------------------------------------------------------------------------------------------------- AlternateContact
-- the customers with no AlternateContact, and how many distinct the others have; 261, 402 names.
SELECT COUNT(*) - COUNT(AlternateContact), COUNT(DISTINCT AlternateContact)
FROM Website.Customers
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- how many distinct phone numbers; 49, for 663 customers.
SELECT COUNT(DISTINCT PhoneNumber)
FROM Website.Customers
;


-- ----------------------------------------------------------------------------------------------------------- FaxNumber
-- how many distinct fax numbers; 49.
SELECT COUNT(DISTINCT FaxNumber)
FROM Website.Customers
;


-- ----------------------------------------------------------------------------------------------------- BuyingGroupName
-- the customers with no BuyingGroupName, and how many distinct the others have; 261, 2 groups.
SELECT COUNT(*) - COUNT(BuyingGroupName), COUNT(DISTINCT BuyingGroupName)
FROM Website.Customers
;


-- ---------------------------------------------------------------------------------------------------------- WebsiteURL
-- the shortest and the longest WebsiteURL; 25 and 52 characters.
SELECT MIN(LEN(WebsiteURL)), MAX(LEN(WebsiteURL))
FROM Website.Customers
;


-- ------------------------------------------------------------------------------------------------------ DeliveryMethod
-- the customers with no DeliveryMethod, and how many distinct the others have; none, 1 method.
SELECT COUNT(*) - COUNT(DeliveryMethod), COUNT(DISTINCT DeliveryMethod)
FROM Website.Customers
;


-- ------------------------------------------------------------------------------------------------------------ CityName
-- the customers with no CityName, and how many distinct the others have; none, 655 cities.
SELECT COUNT(*) - COUNT(CityName), COUNT(DISTINCT CityName)
FROM Website.Customers
;


-- ---------------------------------------------------------------------------------------------------- DeliveryLocation
-- the customers with no DeliveryLocation, and the kinds of the others; none is NULL, all 663 are a Point.
SELECT DeliveryLocation.STGeometryType() AS geometry_type, COUNT(*) AS customers
FROM Website.Customers
GROUP BY DeliveryLocation.STGeometryType()
;


-- --------------------------------------------------------------------------------------------------------- DeliveryRun
-- the distinct delivery runs, bracketed to show blanks; 602 carry the empty string, 61 NULL.
SELECT '[' + DeliveryRun + ']' AS DeliveryRun, COUNT(*) AS customers
FROM Website.Customers
GROUP BY DeliveryRun
ORDER BY DeliveryRun ASC
;


-- --------------------------------------------------------------------------------------------------------- RunPosition
-- the distinct run positions, bracketed to show blanks; 602 carry the empty string, 61 NULL.
SELECT '[' + RunPosition + ']' AS RunPosition, COUNT(*) AS customers
FROM Website.Customers
GROUP BY RunPosition
ORDER BY RunPosition ASC
;

