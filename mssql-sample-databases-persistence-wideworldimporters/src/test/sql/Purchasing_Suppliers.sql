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
-- Profiling queries on Purchasing.Suppliers, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Purchasing_Suppliers.sql
--


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- how many rows, and the smallest and the largest SupplierID; 13 suppliers, 1 to 13.
SELECT COUNT(*), MIN(SupplierID), MAX(SupplierID)
FROM Purchasing.Suppliers
;


-- -------------------------------------------------------------------------------------------------------- SupplierName
-- the shortest and the longest SupplierName; 13 and 24 characters, of the 100 the column allows.
SELECT MIN(LEN(SupplierName)), MAX(LEN(SupplierName))
FROM Purchasing.Suppliers
;

-- a SupplierName which is blank, or carries leading or trailing spaces.
-- no rows: every SupplierName is trimmed and not blank.
SELECT SupplierID, '[' + SupplierName + ']' AS SupplierName
FROM Purchasing.Suppliers
WHERE TRIM(SupplierName) = ''
   OR DATALENGTH(SupplierName) <> DATALENGTH(TRIM(SupplierName))
;

-- UQ_Purchasing_Suppliers_SupplierName keeps SupplierName unique.
-- no rows: no two suppliers share a SupplierName.
SELECT SupplierName, COUNT(*) AS suppliers
FROM Purchasing.Suppliers
GROUP BY SupplierName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------- SupplierCategoryID
-- the categories the suppliers are of; Novelty Goods Supplier (2) with 6, 7 other categories with 1 each.
SELECT s.SupplierCategoryID, c.SupplierCategoryName, COUNT(*) AS suppliers
FROM Purchasing.Suppliers s
         JOIN Purchasing.SupplierCategories c ON c.SupplierCategoryID = s.SupplierCategoryID
GROUP BY s.SupplierCategoryID, c.SupplierCategoryName
ORDER BY s.SupplierCategoryID ASC
;


-- ---------------------------------------------------------------------------------------------- PrimaryContactPersonID
-- the primary contacts run from person 21 to 45, and the alternate ones from 22 to 46, two people per supplier.
SELECT MIN(PrimaryContactPersonID), MAX(PrimaryContactPersonID),
       MIN(AlternateContactPersonID), MAX(AlternateContactPersonID)
FROM Purchasing.Suppliers
;

-- a person who is a contact of more than one supplier, or both contacts of one.
-- no rows: each supplier has two contacts of its own.
SELECT c.PersonID, COUNT(*) AS contacts
FROM (SELECT PrimaryContactPersonID AS PersonID FROM Purchasing.Suppliers
      UNION ALL
      SELECT AlternateContactPersonID FROM Purchasing.Suppliers) c
GROUP BY c.PersonID
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------- AlternateContactPersonID
-- an AlternateContactPersonID which is not the next PersonID after the primary contact.
-- no rows: the alternate contact is always the primary one plus 1.
SELECT SupplierID, PrimaryContactPersonID, AlternateContactPersonID
FROM Purchasing.Suppliers
WHERE AlternateContactPersonID <> PrimaryContactPersonID + 1
;


-- ---------------------------------------------------------------------------------------------------- DeliveryMethodID
-- DeliveryMethodID is nullable; the methods the suppliers deliver by. 4 have none, Road Freight (7) and
-- Refrigerated Air Freight (10) 3 each, Courier (2), Air Freight (8) and Refrigerated Road Freight (9) 1 each.
SELECT s.DeliveryMethodID, m.DeliveryMethodName, COUNT(*) AS suppliers
FROM Purchasing.Suppliers s
         LEFT JOIN Application.DeliveryMethods m ON m.DeliveryMethodID = s.DeliveryMethodID
GROUP BY s.DeliveryMethodID, m.DeliveryMethodName
ORDER BY s.DeliveryMethodID ASC
;


-- ------------------------------------------------------------------------------------------------------ DeliveryCityID
-- how many distinct cities the suppliers deliver from; 12, San Francisco (30378) for 2 of them.
SELECT COUNT(DISTINCT DeliveryCityID)
FROM Purchasing.Suppliers
;


-- -------------------------------------------------------------------------------------------------------- PostalCityID
-- a PostalCityID other than the DeliveryCityID.
-- no rows: every supplier posts and delivers in the same city.
SELECT SupplierID, DeliveryCityID, PostalCityID
FROM Purchasing.Suppliers
WHERE PostalCityID <> DeliveryCityID
;


-- --------------------------------------------------------------------------------------------------- SupplierReference
-- SupplierReference is nullable; how many suppliers have none, and how many distinct references. None; 13.
SELECT COUNT(*) - COUNT(SupplierReference) AS without, COUNT(DISTINCT SupplierReference) AS refs
FROM Purchasing.Suppliers
;


-- ----------------------------------------------------------------------------------------------------- BankAccountName
-- a BankAccountName which is not the SupplierName, but for the punctuation.
-- no rows: every account is in the name of its supplier, Contoso Ltd for Contoso, Ltd.
SELECT SupplierID, SupplierName, BankAccountName
FROM Purchasing.Suppliers
WHERE REPLACE(REPLACE(SupplierName, ',', ''), '.', '') <> BankAccountName
;


-- --------------------------------------------------------------------------------------------------- BankAccountBranch
-- the banks and branches the suppliers bank with; every one a Woodgrove Bank branch, 12 distinct branches.
SELECT COUNT(*) - COUNT(BankAccountBranch) AS without, COUNT(DISTINCT BankAccountBranch) AS branches,
       SUM(CASE WHEN BankAccountBranch LIKE 'Woodgrove Bank %' THEN 1 ELSE 0 END) AS woodgrove
FROM Purchasing.Suppliers
;


-- ----------------------------------------------------------------------------------------------------- BankAccountCode
-- a BankAccountCode which is not six digits.
-- no rows: every code is six digits.
SELECT SupplierID, BankAccountCode
FROM Purchasing.Suppliers
WHERE BankAccountCode IS NULL
   OR BankAccountCode NOT LIKE '[0-9][0-9][0-9][0-9][0-9][0-9]'
;


-- --------------------------------------------------------------------------------------------------- BankAccountNumber
-- a BankAccountNumber which is not ten digits.
-- no rows: every number is ten digits.
SELECT SupplierID, BankAccountNumber
FROM Purchasing.Suppliers
WHERE BankAccountNumber IS NULL
   OR BankAccountNumber NOT LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'
;


-- ----------------------------------------------------------------------------------------------- BankInternationalCode
-- a BankInternationalCode which is not five digits.
-- no rows: every code is five digits.
SELECT SupplierID, BankInternationalCode
FROM Purchasing.Suppliers
WHERE BankInternationalCode IS NULL
   OR BankInternationalCode NOT LIKE '[0-9][0-9][0-9][0-9][0-9]'
;


-- --------------------------------------------------------------------------------------------------------- PaymentDays
-- how many suppliers have each PaymentDays; 7, 14 and 30 days.
SELECT PaymentDays, COUNT(*) AS suppliers
FROM Purchasing.Suppliers
GROUP BY PaymentDays
ORDER BY PaymentDays ASC
;


-- ---------------------------------------------------------------------------------------------------- InternalComments
-- InternalComments is a nullable text; the suppliers which have one. Nod Publishers (9) and Woodgrove Bank (13).
SELECT SupplierID, SupplierName, InternalComments
FROM Purchasing.Suppliers
WHERE InternalComments IS NOT NULL
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- a PhoneNumber which is not of the form (999) 999-9999.
-- no rows: every PhoneNumber has that form.
SELECT SupplierID, PhoneNumber
FROM Purchasing.Suppliers
WHERE PhoneNumber NOT LIKE '([0-9][0-9][0-9]) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'
;


-- ----------------------------------------------------------------------------------------------------------- FaxNumber
-- a FaxNumber which is not of the form (999) 999-9999.
-- no rows: every FaxNumber has that form.
SELECT SupplierID, FaxNumber
FROM Purchasing.Suppliers
WHERE FaxNumber NOT LIKE '([0-9][0-9][0-9]) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'
;

-- a supplier whose FaxNumber is its PhoneNumber.
-- one row: The Phone Company (12), (218) 555-0105 for both.
SELECT SupplierID, SupplierName, PhoneNumber, FaxNumber
FROM Purchasing.Suppliers
WHERE FaxNumber = PhoneNumber
;


-- ---------------------------------------------------------------------------------------------------------- WebsiteURL
-- a WebsiteURL which does not start with http://www.
-- no rows: every one does.
SELECT SupplierID, WebsiteURL
FROM Purchasing.Suppliers
WHERE WebsiteURL NOT LIKE 'http://www.%'
;


-- ------------------------------------------------------------------------------------------------ DeliveryAddressLine1
-- DeliveryAddressLine1 is NOT NULL; a supplier whose line is empty.
-- 4 rows: Consolidated Messenger (3), Graphic Design Institute (5), Humongous Insurance (6) and Northwind Electric
-- Cars (10), with an empty string for the line they do not have.
SELECT SupplierID, SupplierName, '[' + DeliveryAddressLine1 + ']' AS DeliveryAddressLine1
FROM Purchasing.Suppliers
WHERE TRIM(DeliveryAddressLine1) = ''
;

-- a DeliveryAddressLine1 which carries leading or trailing spaces.
-- no rows: every DeliveryAddressLine1 is trimmed.
SELECT SupplierID, '[' + DeliveryAddressLine1 + ']' AS DeliveryAddressLine1
FROM Purchasing.Suppliers
WHERE DATALENGTH(DeliveryAddressLine1) <> DATALENGTH(TRIM(DeliveryAddressLine1))
;


-- ------------------------------------------------------------------------------------------------ DeliveryAddressLine2
-- DeliveryAddressLine2 is nullable; how many suppliers have none. None.
SELECT COUNT(*) - COUNT(DeliveryAddressLine2) AS without
FROM Purchasing.Suppliers
;


-- -------------------------------------------------------------------------------------------------- DeliveryPostalCode
-- a DeliveryPostalCode which is not five digits.
-- no rows: every code is five digits.
SELECT SupplierID, DeliveryPostalCode
FROM Purchasing.Suppliers
WHERE DeliveryPostalCode NOT LIKE '[0-9][0-9][0-9][0-9][0-9]'
;


-- ---------------------------------------------------------------------------------------------------- DeliveryLocation
-- DeliveryLocation is a nullable geography; how many suppliers have none, and how far the others are from the point
-- of their DeliveryCityID. None; every one is exactly the point of its city.
SELECT COUNT(*) - COUNT(s.DeliveryLocation) AS without, MAX(s.DeliveryLocation.STDistance(c.Location)) AS meters
FROM Purchasing.Suppliers s
         JOIN Application.Cities c ON c.CityID = s.DeliveryCityID
;


-- -------------------------------------------------------------------------------------------------- PostalAddressLine1
-- a PostalAddressLine1 which is not of the form PO Box 9...
-- one row: Trey Research (11), PO  Box 595, with two spaces.
SELECT SupplierID, SupplierName, PostalAddressLine1
FROM Purchasing.Suppliers
WHERE PostalAddressLine1 NOT LIKE 'PO Box [0-9]%'
;


-- -------------------------------------------------------------------------------------------------- PostalAddressLine2
-- PostalAddressLine2 is nullable; how many suppliers have none. None.
SELECT COUNT(*) - COUNT(PostalAddressLine2) AS without
FROM Purchasing.Suppliers
;


-- ---------------------------------------------------------------------------------------------------- PostalPostalCode
-- a PostalPostalCode other than the DeliveryPostalCode.
-- no rows: every supplier posts and delivers under the same code.
SELECT SupplierID, DeliveryPostalCode, PostalPostalCode
FROM Purchasing.Suppliers
WHERE PostalPostalCode <> DeliveryPostalCode
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who last edited the suppliers; person 1, Data Conversion Only, for all 13.
SELECT s.LastEditedBy, p.FullName, COUNT(*) AS suppliers
FROM Purchasing.Suppliers s
         JOIN Application.People p ON p.PersonID = s.LastEditedBy
GROUP BY s.LastEditedBy, p.FullName
ORDER BY s.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- when the current rows begin; 2013-01-01 00:05 for all 13.
SELECT ValidFrom, COUNT(*) AS suppliers
FROM Purchasing.Suppliers
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a current row of a system-versioned table is valid until the end of time.
-- no rows: every ValidTo is 9999-12-31 23:59:59.9999999.
SELECT SupplierID, ValidTo
FROM Purchasing.Suppliers
WHERE ValidTo <> '9999-12-31 23:59:59.9999999'
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the purchase orders, transactions and stock items of each supplier; 6 suppliers have none of any, and
-- Fabrikam, Inc. (4) and Litware, Inc. (7) have 1055 and 985 of the 2074 orders.
SELECT s.SupplierID, s.SupplierName,
       (SELECT COUNT(*) FROM Purchasing.PurchaseOrders o WHERE o.SupplierID = s.SupplierID) AS orders,
       (SELECT COUNT(*) FROM Purchasing.SupplierTransactions t WHERE t.SupplierID = s.SupplierID) AS transactions,
       (SELECT COUNT(*) FROM Warehouse.StockItems i WHERE i.SupplierID = s.SupplierID) AS stock_items
FROM Purchasing.Suppliers s
ORDER BY s.SupplierID ASC
;

