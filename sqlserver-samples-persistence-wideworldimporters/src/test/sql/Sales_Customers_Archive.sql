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
-- Profiling queries on Sales.Customers_Archive, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_Customers_Archive.sql
--


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many past versions, and of how many customers; 51 versions, of 46 customers.
SELECT COUNT(*), COUNT(DISTINCT CustomerID)
FROM Sales.Customers_Archive
;

-- the customers with more than one past version; 874, 915 and 976 with 2, 890 with 3.
SELECT CustomerID, COUNT(*) AS versions
FROM Sales.Customers_Archive
GROUP BY CustomerID
HAVING COUNT(*) > 1
ORDER BY CustomerID ASC
;

-- a past version of a customer which is not in Sales.Customers; the history table has no foreign key.
-- no rows: every archived CustomerID is still current.
SELECT a.CustomerID
FROM Sales.Customers_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Customers c
                  WHERE c.CustomerID = a.CustomerID)
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- the shortest and the longest CustomerName; 7 and 23 characters, of the 100 the column allows.
SELECT MIN(LEN(CustomerName)), MAX(LEN(CustomerName))
FROM Sales.Customers_Archive
;

-- a past version whose CustomerName is not the current one.
-- no rows: no customer has been renamed.
SELECT a.CustomerID, a.CustomerName, c.CustomerName AS current_name
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE a.CustomerName <> c.CustomerName
;


-- ---------------------------------------------------------------------------------------------------- BillToCustomerID
-- how many past versions carry a BillToCustomerID other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.BillToCustomerID EXCEPT SELECT c.BillToCustomerID)
;


-- -------------------------------------------------------------------------------------------------- CustomerCategoryID
-- how many past versions carry a CustomerCategoryID other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.CustomerCategoryID EXCEPT SELECT c.CustomerCategoryID)
;


-- ------------------------------------------------------------------------------------------------------- BuyingGroupID
-- how many past versions carry a BuyingGroupID other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.BuyingGroupID EXCEPT SELECT c.BuyingGroupID)
;


-- ---------------------------------------------------------------------------------------------- PrimaryContactPersonID
-- how many past versions carry a PrimaryContactPersonID other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.PrimaryContactPersonID EXCEPT SELECT c.PrimaryContactPersonID)
;


-- -------------------------------------------------------------------------------------------- AlternateContactPersonID
-- how many past versions carry an AlternateContactPersonID other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.AlternateContactPersonID EXCEPT SELECT c.AlternateContactPersonID)
;


-- ---------------------------------------------------------------------------------------------------- DeliveryMethodID
-- how many past versions carry a DeliveryMethodID other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.DeliveryMethodID EXCEPT SELECT c.DeliveryMethodID)
;


-- ------------------------------------------------------------------------------------------------------ DeliveryCityID
-- how many past versions carry a DeliveryCityID other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.DeliveryCityID EXCEPT SELECT c.DeliveryCityID)
;


-- -------------------------------------------------------------------------------------------------------- PostalCityID
-- how many past versions carry a PostalCityID other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.PostalCityID EXCEPT SELECT c.PostalCityID)
;


-- --------------------------------------------------------------------------------------------------------- CreditLimit
-- the past versions with no limit, and the range of the limits set; every version has a limit, 1100.00 to 4410.00.
SELECT COUNT(*) - COUNT(CreditLimit) AS unlimited, MIN(CreditLimit), MAX(CreditLimit)
FROM Sales.Customers_Archive
;

-- how many past versions carry a CreditLimit other than the current one; all 51, the credit limit is the
-- only column the past versions differ in.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.CreditLimit EXCEPT SELECT c.CreditLimit)
;


-- --------------------------------------------------------------------------------------------------- AccountOpenedDate
-- how many past versions carry an AccountOpenedDate other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.AccountOpenedDate EXCEPT SELECT c.AccountOpenedDate)
;


-- ------------------------------------------------------------------------------------------ StandardDiscountPercentage
-- how many past versions carry a StandardDiscountPercentage other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.StandardDiscountPercentage EXCEPT SELECT c.StandardDiscountPercentage)
;


-- ----------------------------------------------------------------------------------------------------- IsStatementSent
-- how many past versions carry an IsStatementSent other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.IsStatementSent EXCEPT SELECT c.IsStatementSent)
;


-- ------------------------------------------------------------------------------------------------------ IsOnCreditHold
-- how many past versions carry an IsOnCreditHold other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.IsOnCreditHold EXCEPT SELECT c.IsOnCreditHold)
;


-- --------------------------------------------------------------------------------------------------------- PaymentDays
-- how many past versions carry a PaymentDays other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.PaymentDays EXCEPT SELECT c.PaymentDays)
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- how many past versions carry a PhoneNumber other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.PhoneNumber EXCEPT SELECT c.PhoneNumber)
;


-- ----------------------------------------------------------------------------------------------------------- FaxNumber
-- how many past versions carry a FaxNumber other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.FaxNumber EXCEPT SELECT c.FaxNumber)
;


-- --------------------------------------------------------------------------------------------------------- DeliveryRun
-- how many past versions carry a DeliveryRun other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.DeliveryRun EXCEPT SELECT c.DeliveryRun)
;


-- --------------------------------------------------------------------------------------------------------- RunPosition
-- how many past versions carry a RunPosition other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.RunPosition EXCEPT SELECT c.RunPosition)
;


-- ---------------------------------------------------------------------------------------------------------- WebsiteURL
-- how many past versions carry a WebsiteURL other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.WebsiteURL EXCEPT SELECT c.WebsiteURL)
;


-- ------------------------------------------------------------------------------------------------ DeliveryAddressLine1
-- how many past versions carry a DeliveryAddressLine1 other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.DeliveryAddressLine1 EXCEPT SELECT c.DeliveryAddressLine1)
;


-- ------------------------------------------------------------------------------------------------ DeliveryAddressLine2
-- how many past versions carry a DeliveryAddressLine2 other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.DeliveryAddressLine2 EXCEPT SELECT c.DeliveryAddressLine2)
;


-- -------------------------------------------------------------------------------------------------- DeliveryPostalCode
-- how many past versions carry a DeliveryPostalCode other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.DeliveryPostalCode EXCEPT SELECT c.DeliveryPostalCode)
;


-- ---------------------------------------------------------------------------------------------------- DeliveryLocation
-- the past versions with no DeliveryLocation, and those whose location is not the current one; none
-- is NULL, and none differs.
SELECT COUNT(*) - COUNT(a.DeliveryLocation) AS without_location,
       SUM(CASE WHEN a.DeliveryLocation.STEquals(c.DeliveryLocation) = 0 THEN 1 ELSE 0 END) AS moved
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
;


-- -------------------------------------------------------------------------------------------------- PostalAddressLine1
-- how many past versions carry a PostalAddressLine1 other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.PostalAddressLine1 EXCEPT SELECT c.PostalAddressLine1)
;


-- -------------------------------------------------------------------------------------------------- PostalAddressLine2
-- how many past versions carry a PostalAddressLine2 other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.PostalAddressLine2 EXCEPT SELECT c.PostalAddressLine2)
;


-- ---------------------------------------------------------------------------------------------------- PostalPostalCode
-- how many past versions carry a PostalPostalCode other than the current one; 0.
SELECT COUNT(*)
FROM Sales.Customers_Archive a
         JOIN Sales.Customers c ON c.CustomerID = a.CustomerID
WHERE EXISTS (SELECT a.PostalPostalCode EXCEPT SELECT c.PostalPostalCode)
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who edited the past versions; person 1 for 46, 8 for 3, 20 for 2.
SELECT LastEditedBy, COUNT(*) AS versions
FROM Sales.Customers_Archive
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of the moments the past versions began; 2013-01-01 to 2015-06-12.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Sales.Customers_Archive
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the range of the moments the past versions ended; 2013-07-01 16:00 to 2015-07-01 16:00.
SELECT MIN(ValidTo), MAX(ValidTo)
FROM Sales.Customers_Archive
;

-- a version which does not end after it began.
-- 1 row, a finding: a version of 890 begins and ends at 2014-07-01 16:00, the same moment.
SELECT CustomerID, ValidFrom, ValidTo
FROM Sales.Customers_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a past version whose end is not the start of the next version, past or current.
-- no rows: the versions of each customer follow one another without a gap or an overlap.
SELECT a.CustomerID, a.ValidFrom, a.ValidTo
FROM Sales.Customers_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Customers c
                  WHERE c.CustomerID = a.CustomerID
                    AND c.ValidFrom = a.ValidTo)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.Customers_Archive b
                  WHERE b.CustomerID = a.CustomerID
                    AND b.ValidFrom = a.ValidTo)
;

