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
-- Profiling queries on Sales.Customers, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_Customers.sql
--


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many rows, and the smallest and the largest CustomerID; 663 customers, 1 to 1061.
SELECT COUNT(*), MIN(CustomerID), MAX(CustomerID)
FROM Sales.Customers
;

-- the runs of consecutive CustomerID; 3 runs, 1 to 201, 401 to 601 and 801 to 1061, of 201, 201 and 261 customers.
SELECT MIN(CustomerID) AS first_id, MAX(CustomerID) AS last_id, COUNT(*) AS customers
FROM (SELECT CustomerID, CustomerID - ROW_NUMBER() OVER (ORDER BY CustomerID) AS run
      FROM Sales.Customers) r
GROUP BY run
ORDER BY first_id ASC
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- the shortest and the longest CustomerName; 6 and 38 characters, of the 100 the column allows.
SELECT MIN(LEN(CustomerName)), MAX(LEN(CustomerName))
FROM Sales.Customers
;

-- a CustomerName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- 1 row, a finding: David Novacek (1017) is stored as [David Novacek ], with a trailing space.
SELECT CustomerID, '[' + CustomerName + ']' AS CustomerName
FROM Sales.Customers
WHERE TRIM(CustomerName) = ''
   OR DATALENGTH(CustomerName) <> DATALENGTH(TRIM(CustomerName))
;

-- UQ_Sales_Customers_CustomerName keeps CustomerName unique.
-- no rows: no two customers share a name.
SELECT CustomerName, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY CustomerName
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------- BillToCustomerID
-- the customers billed to themselves, and those billed to another; 263 to themselves, 400 to another.
SELECT CASE WHEN BillToCustomerID = CustomerID THEN 'self' ELSE 'other' END AS billed_to, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY CASE WHEN BillToCustomerID = CustomerID THEN 'self' ELSE 'other' END
;

-- the customers others are billed to, with how many each; 1 and 401, 200 each.
SELECT BillToCustomerID, COUNT(*) AS customers
FROM Sales.Customers
WHERE BillToCustomerID <> CustomerID
GROUP BY BillToCustomerID
ORDER BY BillToCustomerID ASC
;

-- a customer billed to a customer who is billed to yet another.
-- no rows: billing goes one level deep.
SELECT c.CustomerID, c.BillToCustomerID, b.BillToCustomerID AS billed_on_to
FROM Sales.Customers c
         JOIN Sales.Customers b ON b.CustomerID = c.BillToCustomerID
WHERE b.BillToCustomerID <> b.CustomerID
;


-- -------------------------------------------------------------------------------------------------- CustomerCategoryID
-- the customers of each category; 459 in Novelty Shop (3), 58, 51, 48 and 47 in 4 to 7, none in 1, 2 or 8.
SELECT CustomerCategoryID, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY CustomerCategoryID
ORDER BY CustomerCategoryID ASC
;


-- ------------------------------------------------------------------------------------------------------- BuyingGroupID
-- the customers of each buying group, NULL for none; 261 in none, 201 in each of the two.
SELECT BuyingGroupID, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY BuyingGroupID
ORDER BY BuyingGroupID ASC
;


-- ---------------------------------------------------------------------------------------------- PrimaryContactPersonID
-- PrimaryContactPersonID has no unique key; a person who is the primary contact of more than one customer.
-- no rows: each customer has a primary contact of its own.
SELECT PrimaryContactPersonID, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY PrimaryContactPersonID
HAVING COUNT(*) > 1
;

-- the primary contacts by whether they are employees; none is, all 663 have IsEmployee = 0.
SELECT p.IsEmployee, COUNT(*) AS customers
FROM Sales.Customers c
         JOIN Application.People p ON p.PersonID = c.PrimaryContactPersonID
GROUP BY p.IsEmployee
;


-- -------------------------------------------------------------------------------------------- AlternateContactPersonID
-- the customers with no alternate contact; 261 without, 402 with.
SELECT COUNT(*) - COUNT(AlternateContactPersonID) AS without_alternate,
       COUNT(AlternateContactPersonID)            AS with_alternate
FROM Sales.Customers
;

-- a customer whose alternate contact is the primary one.
-- no rows: the two contacts always differ.
SELECT CustomerID, PrimaryContactPersonID, AlternateContactPersonID
FROM Sales.Customers
WHERE AlternateContactPersonID = PrimaryContactPersonID
;


-- ---------------------------------------------------------------------------------------------------- DeliveryMethodID
-- the customers of each delivery method; all 663 take Delivery Van (3).
SELECT DeliveryMethodID, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY DeliveryMethodID
ORDER BY DeliveryMethodID ASC
;


-- ------------------------------------------------------------------------------------------------------ DeliveryCityID
-- how many cities the customers take delivery in; 655, so a few share a city.
SELECT COUNT(DISTINCT DeliveryCityID)
FROM Sales.Customers
;


-- -------------------------------------------------------------------------------------------------------- PostalCityID
-- how many customers have a postal city other than the delivery city; 0, the two are the same for every customer.
SELECT COUNT(*)
FROM Sales.Customers
WHERE PostalCityID <> DeliveryCityID
;


-- --------------------------------------------------------------------------------------------------------- CreditLimit
-- NULL means no limit; the customers with none, and the range of the limits set; 402 without a limit,
-- the others from 1100.00 to 4630.50.
SELECT COUNT(*) - COUNT(CreditLimit) AS unlimited, MIN(CreditLimit), MAX(CreditLimit)
FROM Sales.Customers
;


-- --------------------------------------------------------------------------------------------------- AccountOpenedDate
-- the range of the dates the accounts were opened; 2013-01-01 to 2016-05-07.
SELECT MIN(AccountOpenedDate), MAX(AccountOpenedDate)
FROM Sales.Customers
;


-- ------------------------------------------------------------------------------------------ StandardDiscountPercentage
-- the distinct standard discounts; .000 for all 663.
SELECT StandardDiscountPercentage, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY StandardDiscountPercentage
;


-- ----------------------------------------------------------------------------------------------------- IsStatementSent
-- the customers sent a statement, and those not; none is, all 663 have 0.
SELECT IsStatementSent, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY IsStatementSent
;


-- ------------------------------------------------------------------------------------------------------ IsOnCreditHold
-- the customers on credit hold, and those not; none is, all 663 have 0.
SELECT IsOnCreditHold, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY IsOnCreditHold
;


-- --------------------------------------------------------------------------------------------------------- PaymentDays
-- the distinct payment terms; 7 days for all 663.
SELECT PaymentDays, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY PaymentDays
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- how many phone numbers do not follow the pattern (nnn) nnn-nnnn; 0.
SELECT COUNT(*)
FROM Sales.Customers
WHERE PhoneNumber NOT LIKE '([0-9][0-9][0-9]) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'
;

-- PhoneNumber has no unique key; a phone number shared by more than one customer; the three
-- most shared, (210) 555-0100 by 46, (215) 555-0100 by 37 and (212) 555-0100 by 35.
SELECT TOP (3) PhoneNumber, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY PhoneNumber
HAVING COUNT(*) > 1
ORDER BY customers DESC, PhoneNumber ASC
;


-- ----------------------------------------------------------------------------------------------------------- FaxNumber
-- how many fax numbers do not follow the pattern (nnn) nnn-nnnn; 0.
SELECT COUNT(*)
FROM Sales.Customers
WHERE FaxNumber NOT LIKE '([0-9][0-9][0-9]) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'
;

-- how many customers have the phone number as fax number; 0.
SELECT COUNT(*)
FROM Sales.Customers
WHERE FaxNumber = PhoneNumber
;


-- --------------------------------------------------------------------------------------------------------- DeliveryRun
-- the distinct delivery runs, bracketed to show blanks; a finding: no customer has a run, 602 carry the empty
-- string and 61 NULL.
SELECT '[' + DeliveryRun + ']' AS DeliveryRun, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY DeliveryRun
ORDER BY DeliveryRun ASC
;


-- --------------------------------------------------------------------------------------------------------- RunPosition
-- the distinct run positions, bracketed to show blanks; like DeliveryRun, 602 carry the empty string and 61 NULL.
SELECT '[' + RunPosition + ']' AS RunPosition, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY RunPosition
ORDER BY RunPosition ASC
;

-- a customer whose RunPosition is NULL and DeliveryRun is not, or the other way around.
-- no rows: the two are NULL together.
SELECT CustomerID, DeliveryRun, RunPosition
FROM Sales.Customers
WHERE (DeliveryRun IS NULL AND RunPosition IS NOT NULL)
   OR (DeliveryRun IS NOT NULL AND RunPosition IS NULL)
;


-- ---------------------------------------------------------------------------------------------------------- WebsiteURL
-- the shortest and the longest WebsiteURL; 25 and 52 characters, of the 256 the column allows.
SELECT MIN(LEN(WebsiteURL)), MAX(LEN(WebsiteURL))
FROM Sales.Customers
;

-- how many WebsiteURL do not start with http://www.; 0.
SELECT COUNT(*)
FROM Sales.Customers
WHERE WebsiteURL NOT LIKE 'http://www.%'
;


-- ------------------------------------------------------------------------------------------------ DeliveryAddressLine1
-- the shortest and the longest DeliveryAddressLine1; 6 and 9 characters, of the 60 the column allows.
SELECT MIN(LEN(DeliveryAddressLine1)), MAX(LEN(DeliveryAddressLine1))
FROM Sales.Customers
;

-- a DeliveryAddressLine1 which is blank, or carries leading or trailing spaces.
-- no rows: every DeliveryAddressLine1 is trimmed and not blank.
SELECT CustomerID, '[' + DeliveryAddressLine1 + ']' AS DeliveryAddressLine1
FROM Sales.Customers
WHERE TRIM(DeliveryAddressLine1) = ''
   OR DATALENGTH(DeliveryAddressLine1) <> DATALENGTH(TRIM(DeliveryAddressLine1))
;


-- ------------------------------------------------------------------------------------------------ DeliveryAddressLine2
-- the customers with no DeliveryAddressLine2, and the shortest and the longest of the others; none is NULL,
-- 11 to 26 characters, of the 60 the column allows.
SELECT COUNT(*) - COUNT(DeliveryAddressLine2), MIN(LEN(DeliveryAddressLine2)), MAX(LEN(DeliveryAddressLine2))
FROM Sales.Customers
;

-- a DeliveryAddressLine2 which is blank, or carries leading or trailing spaces.
-- no rows: every DeliveryAddressLine2 is trimmed and not blank.
SELECT CustomerID, '[' + DeliveryAddressLine2 + ']' AS DeliveryAddressLine2
FROM Sales.Customers
WHERE TRIM(DeliveryAddressLine2) = ''
   OR DATALENGTH(DeliveryAddressLine2) <> DATALENGTH(TRIM(DeliveryAddressLine2))
;


-- -------------------------------------------------------------------------------------------------- DeliveryPostalCode
-- the shortest and the longest DeliveryPostalCode; 5 and 5 characters, of the 10 the column allows.
SELECT MIN(LEN(DeliveryPostalCode)), MAX(LEN(DeliveryPostalCode))
FROM Sales.Customers
;

-- how many DeliveryPostalCode are not all digits; 0.
SELECT COUNT(*)
FROM Sales.Customers
WHERE DeliveryPostalCode LIKE '%[^0-9]%'
;


-- ---------------------------------------------------------------------------------------------------- DeliveryLocation
-- the customers with no DeliveryLocation, and the kinds of the others; none is NULL, all 663 are a Point.
SELECT DeliveryLocation.STGeometryType() AS geometry_type, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY DeliveryLocation.STGeometryType()
;

-- the spatial reference of the locations; 4326, WGS 84, for all 663.
SELECT DeliveryLocation.STSrid AS srid, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY DeliveryLocation.STSrid
;


-- -------------------------------------------------------------------------------------------------- PostalAddressLine1
-- the shortest and the longest PostalAddressLine1; 9 and 11 characters, of the 60 the column allows.
SELECT MIN(LEN(PostalAddressLine1)), MAX(LEN(PostalAddressLine1))
FROM Sales.Customers
;

-- a PostalAddressLine1 which is blank, or carries leading or trailing spaces.
-- no rows: every PostalAddressLine1 is trimmed and not blank.
SELECT CustomerID, '[' + PostalAddressLine1 + ']' AS PostalAddressLine1
FROM Sales.Customers
WHERE TRIM(PostalAddressLine1) = ''
   OR DATALENGTH(PostalAddressLine1) <> DATALENGTH(TRIM(PostalAddressLine1))
;


-- -------------------------------------------------------------------------------------------------- PostalAddressLine2
-- the customers with no PostalAddressLine2, and the shortest and the longest of the others; none is NULL,
-- 7 to 21 characters, of the 60 the column allows.
SELECT COUNT(*) - COUNT(PostalAddressLine2), MIN(LEN(PostalAddressLine2)), MAX(LEN(PostalAddressLine2))
FROM Sales.Customers
;

-- a PostalAddressLine2 which is blank, or carries leading or trailing spaces.
-- no rows: every PostalAddressLine2 is trimmed and not blank.
SELECT CustomerID, '[' + PostalAddressLine2 + ']' AS PostalAddressLine2
FROM Sales.Customers
WHERE TRIM(PostalAddressLine2) = ''
   OR DATALENGTH(PostalAddressLine2) <> DATALENGTH(TRIM(PostalAddressLine2))
;


-- ---------------------------------------------------------------------------------------------------- PostalPostalCode
-- the shortest and the longest PostalPostalCode; 5 and 5 characters, of the 10 the column allows.
SELECT MIN(LEN(PostalPostalCode)), MAX(LEN(PostalPostalCode))
FROM Sales.Customers
;

-- how many customers have a PostalPostalCode other than the DeliveryPostalCode; 0.
SELECT COUNT(*)
FROM Sales.Customers
WHERE PostalPostalCode <> DeliveryPostalCode
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the rows; person 1 for 617, 8 for 14, 15 for 16, 20 for 16.
SELECT LastEditedBy, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of the moments the current versions began; 2013-01-01 to 2016-05-07.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Sales.Customers
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the end of each current version; 9999-12-31 23:59:59.9999999, the largest datetime2, for all 663.
SELECT ValidTo, COUNT(*) AS customers
FROM Sales.Customers
GROUP BY ValidTo
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the past versions kept in Sales.Customers_Archive; 51 versions, of 46 customers.
SELECT COUNT(*), COUNT(DISTINCT CustomerID)
FROM Sales.Customers_Archive
;

-- a customer who has never ordered.
-- no rows: every customer has at least one order.
SELECT c.CustomerID, c.CustomerName
FROM Sales.Customers c
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Orders o
                  WHERE o.CustomerID = c.CustomerID)
ORDER BY c.CustomerID ASC
;

