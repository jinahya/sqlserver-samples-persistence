-- -
-- #%L
-- sql-server-samples-persistence-futonmanufacturing
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
-- Profiling queries on dbo.Customer, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_Customer.sql
--


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many rows, and the smallest and the largest CustomerID; 8 customers, 1 to 8.
SELECT COUNT(*), MIN(CustomerID), MAX(CustomerID)
FROM dbo.Customer
;


-- -------------------------------------------------------------------------------------------------------- CustomerCode
-- a CustomerCode not in the form CUST-NNN, or whose number is not the CustomerID.
-- no rows: every code is CUST- and the CustomerID in three digits, CUST-001 to CUST-008.
SELECT CustomerID, CustomerCode
FROM dbo.Customer
WHERE CustomerCode NOT LIKE 'CUST-[0-9][0-9][0-9]'
   OR CAST(RIGHT(CustomerCode, 3) AS int) <> CustomerID
;

-- the shortest and the longest CustomerCode; 8 and 8 characters, of the 20 the column allows.
SELECT MIN(LEN(CustomerCode)), MAX(LEN(CustomerCode))
FROM dbo.Customer
;

-- a CustomerCode which is blank, or carries leading or trailing spaces.
-- no rows: every CustomerCode is trimmed and not blank.
SELECT CustomerID, '[' + CustomerCode + ']' AS CustomerCode
FROM dbo.Customer
WHERE TRIM(CustomerCode) = ''
   OR DATALENGTH(CustomerCode) <> DATALENGTH(TRIM(CustomerCode))
;

-- a unique constraint keeps CustomerCode unique.
-- no rows: every CustomerCode is distinct.
SELECT CustomerCode, COUNT(*) AS customers
FROM dbo.Customer
GROUP BY CustomerCode
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- the shortest and the longest CustomerName; 17 and 26 characters, of the 255 the column allows.
SELECT MIN(LEN(CustomerName)), MAX(LEN(CustomerName))
FROM dbo.Customer
;

-- a CustomerName which is blank, or carries leading or trailing spaces.
-- no rows: every CustomerName is trimmed and not blank.
SELECT CustomerID, '[' + CustomerName + ']' AS CustomerName
FROM dbo.Customer
WHERE TRIM(CustomerName) = ''
   OR DATALENGTH(CustomerName) <> DATALENGTH(TRIM(CustomerName))
;

-- nothing keeps CustomerName unique, yet no two rows share one.
-- no rows: every CustomerName is distinct.
SELECT CustomerName, COUNT(*) AS customers
FROM dbo.Customer
GROUP BY CustomerName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- ContactName
-- how many ContactName values are NULL, and the shortest and the longest of the rest; none, 9 and 16 characters, of the
-- 100 the column allows.
SELECT SUM(CASE WHEN ContactName IS NULL THEN 1 ELSE 0 END), MIN(LEN(ContactName)), MAX(LEN(ContactName))
FROM dbo.Customer
;

-- a ContactName which is blank, or carries leading or trailing spaces.
-- no rows: every ContactName is trimmed and not blank.
SELECT CustomerID, '[' + ContactName + ']' AS ContactName
FROM dbo.Customer
WHERE TRIM(ContactName) = ''
   OR DATALENGTH(ContactName) <> DATALENGTH(TRIM(ContactName))
;


-- --------------------------------------------------------------------------------------------------------------- Email
-- how many Email values are NULL, and the shortest and the longest of the rest; none, 17 and 29 characters, of the 100
-- the column allows.
SELECT SUM(CASE WHEN Email IS NULL THEN 1 ELSE 0 END), MIN(LEN(Email)), MAX(LEN(Email))
FROM dbo.Customer
;

-- an Email which is blank, or carries leading or trailing spaces.
-- no rows: every Email is trimmed and not blank.
SELECT CustomerID, '[' + Email + ']' AS Email
FROM dbo.Customer
WHERE TRIM(Email) = ''
   OR DATALENGTH(Email) <> DATALENGTH(TRIM(Email))
;

-- nothing keeps Email unique, yet no two rows share one.
-- no rows: every Email is distinct.
SELECT Email, COUNT(*) AS customers
FROM dbo.Customer
GROUP BY Email
HAVING COUNT(*) > 1
;

-- an Email which does not look like an address: something, an @, something, a dot and something.
-- no rows: every Email has the form.
SELECT CustomerID, Email
FROM dbo.Customer
WHERE Email NOT LIKE '_%@_%._%'
;


-- --------------------------------------------------------------------------------------------------------------- Phone
-- how many Phone values are NULL, and how many do not read NNN-555-NNNN; none, and none.
SELECT SUM(CASE WHEN Phone IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN Phone NOT LIKE '[0-9][0-9][0-9]-555-[0-9][0-9][0-9][0-9]' THEN 1 ELSE 0 END)
FROM dbo.Customer
;


-- ------------------------------------------------------------------------------------------------------------- Address
-- how many Address values are NULL, and the shortest and the longest of the rest; none, 14 and 20 characters, of the
-- 255 the column allows.
SELECT SUM(CASE WHEN Address IS NULL THEN 1 ELSE 0 END), MIN(LEN(Address)), MAX(LEN(Address))
FROM dbo.Customer
;

-- an Address which is blank, or carries leading or trailing spaces.
-- no rows: every Address is trimmed and not blank.
SELECT CustomerID, '[' + Address + ']' AS Address
FROM dbo.Customer
WHERE TRIM(Address) = ''
   OR DATALENGTH(Address) <> DATALENGTH(TRIM(Address))
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- the cities, the most customers first; none is NULL, Portland with 2, and six others with 1 each.
SELECT City, COUNT(*) AS customers
FROM dbo.Customer
GROUP BY City
ORDER BY customers DESC, City ASC
;


-- --------------------------------------------------------------------------------------------------------------- State
-- the states, written as two-letter codes; none is NULL, OR with 4, WA with 3 and CA with 1.
SELECT State, COUNT(*) AS customers
FROM dbo.Customer
GROUP BY State
ORDER BY customers DESC, State ASC
;


-- ------------------------------------------------------------------------------------------------------------- ZipCode
-- how many ZipCode values are NULL, and how many are not five digits; none, and none.
SELECT SUM(CASE WHEN ZipCode IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN ZipCode NOT LIKE '[0-9][0-9][0-9][0-9][0-9]' THEN 1 ELSE 0 END)
FROM dbo.Customer
;


-- ------------------------------------------------------------------------------------------------------------- Country
-- the countries; USA for all 8, none NULL.
SELECT Country, COUNT(*) AS customers
FROM dbo.Customer
GROUP BY Country
ORDER BY Country ASC
;


-- --------------------------------------------------------------------------------------------------------- CreditLimit
-- how many CreditLimit values are NULL, and the smallest and the largest; none, 30000.00 and 100000.00.
SELECT SUM(CASE WHEN CreditLimit IS NULL THEN 1 ELSE 0 END), MIN(CreditLimit), MAX(CreditLimit)
FROM dbo.Customer
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes; all 8 customers are active.
SELECT IsActive, COUNT(*) AS customers
FROM dbo.Customer
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- --------------------------------------------------------------------------------------------------------- CreatedDate
-- how many CreatedDate values are NULL, and how many fall outside the five minutes after the database was created;
-- none, and none: the default GETDATE() filled every one in while the load script ran, so it is the moment of
-- the load, not of a business event, and moves with every load.
SELECT SUM(CASE WHEN x.CreatedDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.CreatedDate NOT BETWEEN d.create_date AND DATEADD(MINUTE, 5, d.create_date) THEN 1 ELSE 0 END)
FROM dbo.Customer x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;


-- -------------------------------------------------------------------------------------------------------- CustomerType
-- the types IX_Customer_Type indexes; none is NULL, Online with 1, Retail with 4 and Wholesale with 3.
SELECT CustomerType, COUNT(*) AS customers
FROM dbo.Customer
GROUP BY CustomerType
ORDER BY CustomerType ASC
;


-- ---------------------------------------------------------------------------------------------------------- SalesRepID
-- the customers of each sales rep, NULL for none; reps 1 with 3, 2 with 2, 7 with 2 and 8 with 1, none NULL.
SELECT SalesRepID, COUNT(*) AS customers
FROM dbo.Customer
GROUP BY SalesRepID
ORDER BY SalesRepID ASC
;


-- --------------------------------------------------------------------------------------------------------- TerritoryID
-- the customers of each territory, NULL for none; territory 1 with 6 and 2 with 2, none NULL.
SELECT TerritoryID, COUNT(*) AS customers
FROM dbo.Customer
GROUP BY TerritoryID
ORDER BY TerritoryID ASC
;

-- a customer served by a sales rep of another territory.
-- no rows: every customer's rep works the customer's territory.
SELECT c.CustomerID, c.TerritoryID, r.SalesRepID, r.TerritoryID AS rep_territory
FROM dbo.Customer c
         JOIN dbo.SalesRep r ON r.SalesRepID = c.SalesRepID
WHERE c.TerritoryID <> r.TerritoryID
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a customer with no sales order.
-- no rows: every customer has ordered.
SELECT c.CustomerID, c.CustomerCode
FROM dbo.Customer c
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.SalesOrder o
                  WHERE o.CustomerID = c.CustomerID)
;

-- the orders of each customer and their total, the largest total first; CUST-004 with 2 orders for 20899.04,
-- CUST-002 with 1 for 1355.98 the smallest.
SELECT c.CustomerID, c.CustomerCode, COUNT(*) AS orders, SUM(o.TotalAmount) AS total
FROM dbo.Customer c
         JOIN dbo.SalesOrder o ON o.CustomerID = c.CustomerID
GROUP BY c.CustomerID, c.CustomerCode
ORDER BY total DESC, c.CustomerID ASC
;

-- a customer whose open orders, the ones not yet delivered, add up to more than the CreditLimit.
-- no rows: CUST-004 owes the most, 11399.40 of its 40000.00.
SELECT c.CustomerID, c.CreditLimit, SUM(o.TotalAmount) AS open_total
FROM dbo.Customer c
         JOIN dbo.SalesOrder o ON o.CustomerID = c.CustomerID
WHERE o.Status <> 'Delivered'
GROUP BY c.CustomerID, c.CreditLimit
HAVING SUM(o.TotalAmount) > c.CreditLimit
;

