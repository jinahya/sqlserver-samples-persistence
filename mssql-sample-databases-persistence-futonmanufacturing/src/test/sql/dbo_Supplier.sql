-- -
-- #%L
-- mssql-sample-databases-persistence-futonmanufacturing
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
-- Profiling queries on dbo.Supplier, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_Supplier.sql
--


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- how many rows, and the smallest and the largest SupplierID; 5 suppliers, 1 to 5.
SELECT COUNT(*), MIN(SupplierID), MAX(SupplierID)
FROM dbo.Supplier
;


-- -------------------------------------------------------------------------------------------------------- SupplierCode
-- a SupplierCode not in the form SUP-NNN, or whose number is not the SupplierID.
-- no rows: every code is SUP- and the SupplierID in three digits, SUP-001 to SUP-005.
SELECT SupplierID, SupplierCode
FROM dbo.Supplier
WHERE SupplierCode NOT LIKE 'SUP-[0-9][0-9][0-9]'
   OR CAST(RIGHT(SupplierCode, 3) AS int) <> SupplierID
;

-- the shortest and the longest SupplierCode; 7 and 7 characters, of the 20 the column allows.
SELECT MIN(LEN(SupplierCode)), MAX(LEN(SupplierCode))
FROM dbo.Supplier
;

-- a SupplierCode which is blank, or carries leading or trailing spaces.
-- no rows: every SupplierCode is trimmed and not blank.
SELECT SupplierID, '[' + SupplierCode + ']' AS SupplierCode
FROM dbo.Supplier
WHERE TRIM(SupplierCode) = ''
   OR DATALENGTH(SupplierCode) <> DATALENGTH(TRIM(SupplierCode))
;

-- a unique constraint keeps SupplierCode unique.
-- no rows: every SupplierCode is distinct.
SELECT SupplierCode, COUNT(*) AS suppliers
FROM dbo.Supplier
GROUP BY SupplierCode
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- SupplierName
-- the shortest and the longest SupplierName; 21 and 27 characters, of the 255 the column allows.
SELECT MIN(LEN(SupplierName)), MAX(LEN(SupplierName))
FROM dbo.Supplier
;

-- a SupplierName which is blank, or carries leading or trailing spaces.
-- no rows: every SupplierName is trimmed and not blank.
SELECT SupplierID, '[' + SupplierName + ']' AS SupplierName
FROM dbo.Supplier
WHERE TRIM(SupplierName) = ''
   OR DATALENGTH(SupplierName) <> DATALENGTH(TRIM(SupplierName))
;

-- nothing keeps SupplierName unique, yet no two rows share one.
-- no rows: every SupplierName is distinct.
SELECT SupplierName, COUNT(*) AS suppliers
FROM dbo.Supplier
GROUP BY SupplierName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- ContactName
-- how many ContactName values are NULL, and the shortest and the longest of the rest; none, 11 and 13 characters, of
-- the 100 the column allows.
SELECT SUM(CASE WHEN ContactName IS NULL THEN 1 ELSE 0 END), MIN(LEN(ContactName)), MAX(LEN(ContactName))
FROM dbo.Supplier
;

-- a ContactName which is blank, or carries leading or trailing spaces.
-- no rows: every ContactName is trimmed and not blank.
SELECT SupplierID, '[' + ContactName + ']' AS ContactName
FROM dbo.Supplier
WHERE TRIM(ContactName) = ''
   OR DATALENGTH(ContactName) <> DATALENGTH(TRIM(ContactName))
;


-- --------------------------------------------------------------------------------------------------------------- Email
-- how many Email values are NULL, and the shortest and the longest of the rest; none, 19 and 26 characters, of the 100
-- the column allows.
SELECT SUM(CASE WHEN Email IS NULL THEN 1 ELSE 0 END), MIN(LEN(Email)), MAX(LEN(Email))
FROM dbo.Supplier
;

-- an Email which is blank, or carries leading or trailing spaces.
-- no rows: every Email is trimmed and not blank.
SELECT SupplierID, '[' + Email + ']' AS Email
FROM dbo.Supplier
WHERE TRIM(Email) = ''
   OR DATALENGTH(Email) <> DATALENGTH(TRIM(Email))
;

-- nothing keeps Email unique, yet no two rows share one.
-- no rows: every Email is distinct.
SELECT Email, COUNT(*) AS suppliers
FROM dbo.Supplier
GROUP BY Email
HAVING COUNT(*) > 1
;

-- an Email which does not look like an address: something, an @, something, a dot and something.
-- no rows: every Email has the form.
SELECT SupplierID, Email
FROM dbo.Supplier
WHERE Email NOT LIKE '_%@_%._%'
;


-- --------------------------------------------------------------------------------------------------------------- Phone
-- how many Phone values are NULL, and how many do not read NNN-555-NNNN; none, and none.
SELECT SUM(CASE WHEN Phone IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN Phone NOT LIKE '[0-9][0-9][0-9]-555-[0-9][0-9][0-9][0-9]' THEN 1 ELSE 0 END)
FROM dbo.Supplier
;


-- ------------------------------------------------------------------------------------------------------------- Address
-- how many Address values are NULL, and the shortest and the longest of the rest; none, 15 and 22 characters, of the
-- 255 the column allows.
SELECT SUM(CASE WHEN Address IS NULL THEN 1 ELSE 0 END), MIN(LEN(Address)), MAX(LEN(Address))
FROM dbo.Supplier
;

-- an Address which is blank, or carries leading or trailing spaces.
-- no rows: every Address is trimmed and not blank.
SELECT SupplierID, '[' + Address + ']' AS Address
FROM dbo.Supplier
WHERE TRIM(Address) = ''
   OR DATALENGTH(Address) <> DATALENGTH(TRIM(Address))
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- the cities and their states; Eugene and Portland, OR, San Francisco, CA, and Seattle and Tacoma, WA, one each.
SELECT City, State, COUNT(*) AS suppliers
FROM dbo.Supplier
GROUP BY City, State
ORDER BY suppliers DESC, City ASC
;


-- --------------------------------------------------------------------------------------------------------------- State
-- the states, written as two-letter codes; none is NULL, OR and WA with 2 each and CA with 1.
SELECT State, COUNT(*) AS suppliers
FROM dbo.Supplier
GROUP BY State
ORDER BY suppliers DESC, State ASC
;


-- ------------------------------------------------------------------------------------------------------------- ZipCode
-- how many ZipCode values are NULL, and how many are not five digits; none, and none.
SELECT SUM(CASE WHEN ZipCode IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN ZipCode NOT LIKE '[0-9][0-9][0-9][0-9][0-9]' THEN 1 ELSE 0 END)
FROM dbo.Supplier
;


-- ------------------------------------------------------------------------------------------------------------- Country
-- the countries; USA for all 5, none NULL.
SELECT Country, COUNT(*) AS suppliers
FROM dbo.Supplier
GROUP BY Country
ORDER BY Country ASC
;


-- -------------------------------------------------------------------------------------------------------- PaymentTerms
-- the payment terms; none is NULL, Net 30 with 3, Net 45 and Net 60 with 1 each.
SELECT PaymentTerms, COUNT(*) AS suppliers
FROM dbo.Supplier
GROUP BY PaymentTerms
ORDER BY PaymentTerms ASC
;


-- -------------------------------------------------------------------------------------------------------------- Rating
-- how many Rating values are NULL, and the lowest and the highest, of the 9.99 a decimal(3, 2) holds; none, 4.30 and
-- 4.80.
SELECT SUM(CASE WHEN Rating IS NULL THEN 1 ELSE 0 END), MIN(Rating), MAX(Rating)
FROM dbo.Supplier
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes; all 5 suppliers are active.
SELECT IsActive, COUNT(*) AS suppliers
FROM dbo.Supplier
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- --------------------------------------------------------------------------------------------------------- CreatedDate
-- how many CreatedDate values are NULL, and how many fall outside the five minutes after the database was created;
-- none, and none: the default GETDATE() filled every one in while the load script ran, so it is the moment of
-- the load, not of a business event, and moves with every load.
SELECT SUM(CASE WHEN x.CreatedDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.CreatedDate NOT BETWEEN d.create_date AND DATEADD(MINUTE, 5, d.create_date) THEN 1 ELSE 0 END)
FROM dbo.Supplier x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the items each supplier offers, how many of them it is the preferred supplier for, and its purchase orders;
-- SUP-003 offers 7, SUP-002 5 and the others 4, each the preferred supplier of all it offers, and each has 1 order.
SELECT s.SupplierID, s.SupplierCode,
       COUNT(si.SupplierItemID) AS items,
       SUM(CASE WHEN si.IsPreferred = 1 THEN 1 ELSE 0 END) AS preferred,
       (SELECT COUNT(*) FROM dbo.PurchaseOrder po WHERE po.SupplierID = s.SupplierID) AS orders
FROM dbo.Supplier s
         LEFT JOIN dbo.SupplierItem si ON si.SupplierID = s.SupplierID
GROUP BY s.SupplierID, s.SupplierCode
ORDER BY s.SupplierID ASC
;

