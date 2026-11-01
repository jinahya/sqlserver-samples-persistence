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
-- Profiling queries on Application.DeliveryMethods, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_DeliveryMethods.sql
--


-- ---------------------------------------------------------------------------------------------------- DeliveryMethodID
-- how many rows, and the smallest and the largest DeliveryMethodID; 10 methods, 1 to 10.
SELECT COUNT(*), MIN(DeliveryMethodID), MAX(DeliveryMethodID)
FROM Application.DeliveryMethods
;


-- -------------------------------------------------------------------------------------------------- DeliveryMethodName
-- the shortest and the longest DeliveryMethodName; 4 and 27 characters, of the 50 the column allows.
SELECT MIN(LEN(DeliveryMethodName)), MAX(LEN(DeliveryMethodName))
FROM Application.DeliveryMethods
;

-- a DeliveryMethodName which is blank, or carries leading or trailing spaces.
-- no rows: every DeliveryMethodName is trimmed and not blank.
SELECT DeliveryMethodID, '[' + DeliveryMethodName + ']' AS DeliveryMethodName
FROM Application.DeliveryMethods
WHERE TRIM(DeliveryMethodName) = ''
   OR DATALENGTH(DeliveryMethodName) <> DATALENGTH(TRIM(DeliveryMethodName))
;

-- UQ_Application_DeliveryMethods_DeliveryMethodName keeps DeliveryMethodName unique.
-- no rows: no two methods share a name.
SELECT DeliveryMethodName, COUNT(*) AS methods
FROM Application.DeliveryMethods
GROUP BY DeliveryMethodName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who last edited the methods; person 1, Data Conversion Only, for 9 of them, and 16, Archer Lamble, for Chilled Van.
SELECT m.LastEditedBy, p.FullName, COUNT(*) AS methods
FROM Application.DeliveryMethods m
         JOIN Application.People p ON p.PersonID = m.LastEditedBy
GROUP BY m.LastEditedBy, p.FullName
ORDER BY m.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of ValidFrom; 2013-01-01 for 9 methods, and 2015-01-01 16:00 for Chilled Van (5), renamed then.
SELECT ValidFrom, COUNT(*) AS methods
FROM Application.DeliveryMethods
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a current row of a system-versioned table is valid until the end of time.
-- no rows: every ValidTo is 9999-12-31 23:59:59.9999999.
SELECT DeliveryMethodID, ValidTo
FROM Application.DeliveryMethods
WHERE ValidTo <> '9999-12-31 23:59:59.9999999'
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a method no supplier, purchase order, customer or invoice uses; 4 of them, Post (1), Customer Collect (4),
-- Chilled Van (5) and Customer Courier to Collect (6).
SELECT m.DeliveryMethodID, m.DeliveryMethodName
FROM Application.DeliveryMethods m
WHERE NOT EXISTS (SELECT 1 FROM Purchasing.Suppliers s WHERE s.DeliveryMethodID = m.DeliveryMethodID)
  AND NOT EXISTS (SELECT 1 FROM Purchasing.PurchaseOrders o WHERE o.DeliveryMethodID = m.DeliveryMethodID)
  AND NOT EXISTS (SELECT 1 FROM Sales.Customers c WHERE c.DeliveryMethodID = m.DeliveryMethodID)
  AND NOT EXISTS (SELECT 1 FROM Sales.Invoices i WHERE i.DeliveryMethodID = m.DeliveryMethodID)
ORDER BY m.DeliveryMethodID ASC
;

-- the purchase orders of each method, the most used first; 5 methods, Road Freight (7) with 1065 and Courier (2)
-- with 985, the other three 13 orders and fewer.
SELECT m.DeliveryMethodID, m.DeliveryMethodName, COUNT(*) AS orders
FROM Application.DeliveryMethods m
         JOIN Purchasing.PurchaseOrders o ON o.DeliveryMethodID = m.DeliveryMethodID
GROUP BY m.DeliveryMethodID, m.DeliveryMethodName
ORDER BY orders DESC, m.DeliveryMethodID ASC
;

-- the history the archive keeps; Chilled Van (5) was Van with Chiller until 2015-01-01 16:00, where its row begins.
SELECT a.DeliveryMethodID, a.DeliveryMethodName, a.ValidFrom, a.ValidTo, m.DeliveryMethodName AS now, m.ValidFrom
FROM Application.DeliveryMethods_Archive a
         JOIN Application.DeliveryMethods m ON m.DeliveryMethodID = a.DeliveryMethodID
ORDER BY a.DeliveryMethodID ASC, a.ValidFrom ASC
;

