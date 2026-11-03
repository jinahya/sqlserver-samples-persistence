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
-- Profiling queries on Sales.Orders, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_Orders.sql
--


-- ------------------------------------------------------------------------------------------------------------- OrderID
-- how many rows, and the smallest and the largest OrderID; 73595 orders, 1 to 73595.
SELECT COUNT(*), MIN(OrderID), MAX(OrderID)
FROM Sales.Orders
;

-- an OrderID whose next value is missing, below the largest.
-- no rows: OrderID runs from 1 without a gap.
SELECT a.OrderID + 1 AS missing_from
FROM Sales.Orders a
WHERE a.OrderID < (SELECT MAX(OrderID) FROM Sales.Orders)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.Orders b
                  WHERE b.OrderID = a.OrderID + 1)
;


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many customers have ordered; all 663.
SELECT COUNT(DISTINCT CustomerID)
FROM Sales.Orders
;

-- the customers with the most orders; 90 with 150, 831 with 147, 968 with 146.
SELECT TOP (3) CustomerID, COUNT(*) AS orders
FROM Sales.Orders
GROUP BY CustomerID
ORDER BY orders DESC, CustomerID ASC
;


-- ------------------------------------------------------------------------------------------------- SalespersonPersonID
-- the orders of each salesperson; 10 salespeople, 7257 (8) to 7532 (16) orders each.
SELECT o.SalespersonPersonID, COUNT(*) AS orders
FROM Sales.Orders o
GROUP BY o.SalespersonPersonID
ORDER BY o.SalespersonPersonID ASC
;

-- a salesperson who is not marked IsSalesperson in Application.People.
-- no rows: every SalespersonPersonID is a salesperson.
SELECT DISTINCT o.SalespersonPersonID
FROM Sales.Orders o
         JOIN Application.People p ON p.PersonID = o.SalespersonPersonID
WHERE p.IsSalesperson = 0
;


-- ---------------------------------------------------------------------------------------------------- PickedByPersonID
-- the orders with no picker, and how many people picked the others; 10623 unpicked, 19 pickers.
SELECT COUNT(*) - COUNT(PickedByPersonID) AS unpicked, COUNT(DISTINCT PickedByPersonID) AS pickers
FROM Sales.Orders
;

-- a picker who is not an employee.
-- no rows: every PickedByPersonID is marked IsEmployee.
SELECT DISTINCT o.PickedByPersonID
FROM Sales.Orders o
         JOIN Application.People p ON p.PersonID = o.PickedByPersonID
WHERE p.IsEmployee = 0
;


-- ----------------------------------------------------------------------------------------------------- ContactPersonID
-- an order whose contact is neither the primary nor the alternate contact of its customer.
-- 0: the contact is always one of the two.
SELECT COUNT(*)
FROM Sales.Orders o
         JOIN Sales.Customers c ON c.CustomerID = o.CustomerID
WHERE o.ContactPersonID <> c.PrimaryContactPersonID
  AND (c.AlternateContactPersonID IS NULL OR o.ContactPersonID <> c.AlternateContactPersonID)
;


-- ---------------------------------------------------------------------------------------------------- BackorderOrderID
-- the orders with a backorder, and those without; 7538 with, 66057 without.
SELECT COUNT(BackorderOrderID) AS with_backorder, COUNT(*) - COUNT(BackorderOrderID) AS without_backorder
FROM Sales.Orders
;

-- a backorder taken by more than one order.
-- no rows: each backorder belongs to one order.
SELECT BackorderOrderID, COUNT(*) AS orders
FROM Sales.Orders
WHERE BackorderOrderID IS NOT NULL
GROUP BY BackorderOrderID
HAVING COUNT(*) > 1
;

-- an order whose backorder is for another customer, or is not later than the order.
-- 0: a backorder is a later order of the same customer.
SELECT COUNT(*)
FROM Sales.Orders o
         JOIN Sales.Orders b ON b.OrderID = o.BackorderOrderID
WHERE b.CustomerID <> o.CustomerID
   OR b.OrderID <= o.OrderID
;


-- ----------------------------------------------------------------------------------------------------------- OrderDate
-- the range of the order dates; 2013-01-01 to 2016-05-31.
SELECT MIN(OrderDate), MAX(OrderDate)
FROM Sales.Orders
;

-- the orders of each weekday; 13208 to 13737 a weekday, 6564 on Saturday, none on Sunday.
SELECT DATENAME(WEEKDAY, OrderDate) AS weekday, COUNT(*) AS orders
FROM Sales.Orders
GROUP BY DATENAME(WEEKDAY, OrderDate), DATEPART(WEEKDAY, OrderDate)
ORDER BY DATEPART(WEEKDAY, OrderDate) ASC
;


-- ------------------------------------------------------------------------------------------------ ExpectedDeliveryDate
-- the days from the order to the expected delivery; 1 day for 53823, 2 for 6564 (Saturday orders),
-- 3 for 13208 (Friday orders).
SELECT DATEDIFF(DAY, OrderDate, ExpectedDeliveryDate) AS days, COUNT(*) AS orders
FROM Sales.Orders
GROUP BY DATEDIFF(DAY, OrderDate, ExpectedDeliveryDate)
ORDER BY days ASC
;


-- ----------------------------------------------------------------------------------------- CustomerPurchaseOrderNumber
-- the orders with no CustomerPurchaseOrderNumber, and the shortest and the longest of the others; none is NULL,
-- 5 and 5 characters, of the 20 the column allows.
SELECT COUNT(*) - COUNT(CustomerPurchaseOrderNumber), MIN(LEN(CustomerPurchaseOrderNumber)),
       MAX(LEN(CustomerPurchaseOrderNumber))
FROM Sales.Orders
;

-- how many CustomerPurchaseOrderNumber are not all digits; 0.
SELECT COUNT(*)
FROM Sales.Orders
WHERE CustomerPurchaseOrderNumber LIKE '%[^0-9]%'
;


-- -------------------------------------------------------------------------------------------- IsUndersupplyBackordered
-- the orders whose undersupply is backordered, and those not; all 73595 are 1.
SELECT IsUndersupplyBackordered, COUNT(*) AS orders
FROM Sales.Orders
GROUP BY IsUndersupplyBackordered
;


-- ------------------------------------------------------------------------------------------------------------ Comments
-- the orders with no Comments, and the shortest and the longest of the others; all 73595 are NULL,
-- the installed data writes no Comments on an order.
SELECT COUNT(*) - COUNT(Comments), MIN(LEN(Comments)), MAX(LEN(Comments))
FROM Sales.Orders
;


-- ------------------------------------------------------------------------------------------------ DeliveryInstructions
-- the orders with no DeliveryInstructions, and the shortest and the longest of the others; all 73595 are NULL,
-- the installed data writes no DeliveryInstructions on an order.
SELECT COUNT(*) - COUNT(DeliveryInstructions), MIN(LEN(DeliveryInstructions)), MAX(LEN(DeliveryInstructions))
FROM Sales.Orders
;


-- ---------------------------------------------------------------------------------------------------- InternalComments
-- the orders with no InternalComments, and the shortest and the longest of the others; all 73595 are NULL,
-- the installed data writes no InternalComments on an order.
SELECT COUNT(*) - COUNT(InternalComments), MIN(LEN(InternalComments)), MAX(LEN(InternalComments))
FROM Sales.Orders
;


-- ------------------------------------------------------------------------------------------------ PickingCompletedWhen
-- the orders not picked yet, and the range of the others; 3085 not picked,
-- the others from 2013-01-01 11:00 to 2016-05-31 12:00.
SELECT COUNT(*) - COUNT(PickingCompletedWhen) AS unpicked, MIN(PickingCompletedWhen), MAX(PickingCompletedWhen)
FROM Sales.Orders
;

-- the orders by whether they have a picker, a completed picking and a backorder; 62972 picked and completed with
-- no backorder, 3085 neither picked nor completed, and 7538, every order with a backorder, completed with no picker.
SELECT CASE WHEN PickedByPersonID IS NULL THEN 'no' ELSE 'yes' END AS picker,
       CASE WHEN PickingCompletedWhen IS NULL THEN 'no' ELSE 'yes' END AS completed,
       CASE WHEN BackorderOrderID IS NULL THEN 'no' ELSE 'yes' END AS backorder, COUNT(*) AS orders
FROM Sales.Orders
GROUP BY CASE WHEN PickedByPersonID IS NULL THEN 'no' ELSE 'yes' END,
         CASE WHEN PickingCompletedWhen IS NULL THEN 'no' ELSE 'yes' END,
         CASE WHEN BackorderOrderID IS NULL THEN 'no' ELSE 'yes' END
ORDER BY orders DESC
;

-- an order picked before it was placed.
-- no rows: no PickingCompletedWhen is earlier than its OrderDate.
SELECT OrderID, OrderDate, PickingCompletedWhen
FROM Sales.Orders
WHERE PickingCompletedWhen < CAST(OrderDate AS datetime2)
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the most rows; 7 with 4633, 4 with 4531, 17 with 4437.
SELECT TOP (3) LastEditedBy, COUNT(*) AS orders
FROM Sales.Orders
GROUP BY LastEditedBy
ORDER BY orders DESC, LastEditedBy ASC
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- the range of LastEditedWhen; 2013-01-01 11:00 to 2016-05-31 12:00.
SELECT MIN(LastEditedWhen), MAX(LastEditedWhen)
FROM Sales.Orders
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many orders have no line; 0.
SELECT COUNT(*)
FROM Sales.Orders o
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.OrderLines l
                  WHERE l.OrderID = o.OrderID)
;

-- how many orders have never been invoiced; 3085, the ones not picked yet.
SELECT COUNT(*)
FROM Sales.Orders o
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Invoices i
                  WHERE i.OrderID = o.OrderID)
;

