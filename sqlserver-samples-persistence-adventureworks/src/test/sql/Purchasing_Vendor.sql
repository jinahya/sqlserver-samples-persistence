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
-- Profiling queries on Purchasing.Vendor, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Purchasing_Vendor.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and the smallest and the largest BusinessEntityID; 104 vendors, 1492 to 1698.
SELECT COUNT(*), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM Purchasing.Vendor
;

-- a vendor whose BusinessEntityID is also a Person or a Store.
-- no rows: a vendor is a business entity of its own.
SELECT v.BusinessEntityID
FROM Purchasing.Vendor v
WHERE EXISTS (SELECT 1 FROM Person.Person p WHERE p.BusinessEntityID = v.BusinessEntityID)
   OR EXISTS (SELECT 1 FROM Sales.Store s WHERE s.BusinessEntityID = v.BusinessEntityID)
;


-- ------------------------------------------------------------------------------------------------------- AccountNumber
-- the shortest and the longest AccountNumber; 9 and 12 characters, of the 15 the column allows.
SELECT MIN(LEN(AccountNumber)), MAX(LEN(AccountNumber))
FROM Purchasing.Vendor
;

-- an AccountNumber which is not upper-case letters followed by a four-digit number; 1520, G&KBI0001, with an ampersand.
SELECT BusinessEntityID, AccountNumber
FROM Purchasing.Vendor
WHERE AccountNumber COLLATE Latin1_General_CS_AS NOT LIKE '%[A-Z][0-9][0-9][0-9][0-9]'
   OR LEFT(AccountNumber, LEN(AccountNumber) - 4) COLLATE Latin1_General_CS_AS LIKE '%[^A-Z]%'
;

-- AK_Vendor_AccountNumber keeps AccountNumber unique.
-- no rows: no two vendors share an AccountNumber.
SELECT AccountNumber, COUNT(*) AS vendors
FROM Purchasing.Vendor
GROUP BY AccountNumber
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 5 and 33 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Purchasing.Vendor
;

-- a Name which is blank, or carries leading or trailing spaces.
-- no rows: every Name is trimmed and not blank.
SELECT BusinessEntityID, '[' + Name + ']' AS Name
FROM Purchasing.Vendor
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- two vendors which share a Name; no index keeps Name unique.
-- no rows: every Name is unique in fact.
SELECT Name, COUNT(*) AS vendors
FROM Purchasing.Vendor
GROUP BY Name
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- CreditRating
-- the ratings; CK_Vendor_CreditRating holds it between 1 and 5; 1 84, 2 9, 3 7, 4 2 and 5 2.
SELECT CreditRating, COUNT(*) AS vendors
FROM Purchasing.Vendor
GROUP BY CreditRating
ORDER BY CreditRating ASC
;


-- ----------------------------------------------------------------------------------------------- PreferredVendorStatus
-- the values; 0 11 and 1 93.
SELECT PreferredVendorStatus, COUNT(*) AS vendors
FROM Purchasing.Vendor
GROUP BY PreferredVendorStatus
ORDER BY PreferredVendorStatus ASC
;

-- PreferredVendorStatus against CreditRating; the non-preferred vendors are 9 of rating 1, 1 of 2 and 1 of 4; every
-- vendor rated 3 or 5 is preferred.
SELECT CreditRating, PreferredVendorStatus, COUNT(*) AS vendors
FROM Purchasing.Vendor
GROUP BY CreditRating, PreferredVendorStatus
ORDER BY CreditRating ASC, PreferredVendorStatus ASC
;


-- ---------------------------------------------------------------------------------------------------------- ActiveFlag
-- the values; 0 4 and 1 100.
SELECT ActiveFlag, COUNT(*) AS vendors
FROM Purchasing.Vendor
GROUP BY ActiveFlag
ORDER BY ActiveFlag ASC
;

-- the inactive vendors, and their purchase orders; 4 rows, a finding: 1516, 1544, 1614 and 1678, inactive yet with 51,
-- 51, 50 and 51 purchase orders.
SELECT v.BusinessEntityID, v.Name, COUNT(h.PurchaseOrderID) AS orders
FROM Purchasing.Vendor v
         LEFT JOIN Purchasing.PurchaseOrderHeader h ON h.VendorID = v.BusinessEntityID
WHERE v.ActiveFlag = 0
GROUP BY v.BusinessEntityID, v.Name
ORDER BY v.BusinessEntityID ASC
;


-- --------------------------------------------------------------------------------------------- PurchasingWebServiceURL
-- how many rows leave PurchasingWebServiceURL NULL; 98 NULLs; 6 vendors have one.
SELECT COUNT(*) - COUNT(PurchasingWebServiceURL) AS nulls
FROM Purchasing.Vendor
;

-- the vendors with a PurchasingWebServiceURL; 1580, 1584, 1596, 1606, 1648 and 1678, each a bare host such as
-- www.adatum.com/, with no scheme.
SELECT BusinessEntityID, PurchasingWebServiceURL
FROM Purchasing.Vendor
WHERE PurchasingWebServiceURL IS NOT NULL
ORDER BY BusinessEntityID ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range of ModifiedDate; 2022-04-24 to 2023-02-17.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Purchasing.Vendor
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a vendor with no product; 18 vendors supply no product.
SELECT COUNT(*)
FROM Purchasing.Vendor v
WHERE NOT EXISTS (SELECT 1
                  FROM Purchasing.ProductVendor pv
                  WHERE pv.BusinessEntityID = v.BusinessEntityID)
;

-- a vendor with no product, but with a purchase order.
-- no rows: the vendors with no product are among those with no purchase order.
SELECT v.BusinessEntityID, v.Name
FROM Purchasing.Vendor v
WHERE NOT EXISTS (SELECT 1
                  FROM Purchasing.ProductVendor pv
                  WHERE pv.BusinessEntityID = v.BusinessEntityID)
  AND EXISTS (SELECT 1
              FROM Purchasing.PurchaseOrderHeader h
              WHERE h.VendorID = v.BusinessEntityID)
;

-- a vendor with no purchase order; 18, the same 18 that supply no product.
SELECT COUNT(*)
FROM Purchasing.Vendor v
WHERE NOT EXISTS (SELECT 1
                  FROM Purchasing.PurchaseOrderHeader h
                  WHERE h.VendorID = v.BusinessEntityID)
;

-- how many purchase orders a vendor has, and how many vendors have that many; 50 vendors have 51, 29 have 50, then 1
-- with 4, 2 with 2 and 4 with 1.
SELECT orders, COUNT(*) AS vendors
FROM (SELECT VendorID, COUNT(*) AS orders
      FROM Purchasing.PurchaseOrderHeader
      GROUP BY VendorID) h
GROUP BY orders
ORDER BY orders DESC
;

