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
---
--
-- Exercises on the Sales schema of WideWorldImporters, and on the Website view over it.
--
-- These are the SQL solutions of the 'Sales' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- ------------------------------------------------------------------------------------------------------ WWI-SALES-B-01
-- The sales manager counts the customers of each buying group, and those who belong to none, the most first; a tie
-- by the name of the group.
SELECT bg.BuyingGroupName, COUNT(*) AS Customers
FROM Sales.Customers c
         LEFT JOIN Sales.BuyingGroups bg ON bg.BuyingGroupID = c.BuyingGroupID
GROUP BY bg.BuyingGroupName
ORDER BY Customers DESC, bg.BuyingGroupName
;


-- ------------------------------------------------------------------------------------------------------ WWI-SALES-B-02
-- Accounts receivable wants the customers who are billed for others: each bill-to customer, with the number of other
-- customers it is billed for, the most first; a tie by the identifier.
SELECT b.CustomerID, b.CustomerName, COUNT(*) AS Billed
FROM Sales.Customers c
         JOIN Sales.Customers b ON b.CustomerID = c.BillToCustomerID
WHERE c.BillToCustomerID <> c.CustomerID
GROUP BY b.CustomerID, b.CustomerName
ORDER BY Billed DESC, b.CustomerID
;


-- ------------------------------------------------------------------------------------------------------ WWI-SALES-B-03
-- The web team lists, from the Website.Customers view, the first five customers by name who belong to no buying
-- group, with their category, city and delivery method.
SELECT TOP (5) CustomerID, CustomerName, CustomerCategoryName, CityName, DeliveryMethod
FROM Website.Customers
WHERE BuyingGroupName IS NULL
ORDER BY CustomerName
;


-- ------------------------------------------------------------------------------------------------------ WWI-SALES-I-01
-- The sales manager wants the five stock items which earned the most in a year, by the invoiced extended price, with
-- the quantity sold.
DECLARE @from date = '2015-01-01', @to date = '2016-01-01';
SELECT TOP (5) si.StockItemID, si.StockItemName, SUM(il.Quantity) AS Quantity, SUM(il.ExtendedPrice) AS Revenue
FROM Sales.InvoiceLines il
         JOIN Sales.Invoices i ON i.InvoiceID = il.InvoiceID
         JOIN Warehouse.StockItems si ON si.StockItemID = il.StockItemID
WHERE i.InvoiceDate >= @from
  AND i.InvoiceDate < @to
GROUP BY si.StockItemID, si.StockItemName
ORDER BY Revenue DESC
;


-- ------------------------------------------------------------------------------------------------------ WWI-SALES-I-02
-- Finance wants the sales of a year by month: the number of invoices and the invoiced extended price of each month.
DECLARE @from date = '2015-01-01', @to date = '2016-01-01';
SELECT MONTH(i.InvoiceDate) AS InvoiceMonth, COUNT(DISTINCT i.InvoiceID) AS Invoices, SUM(il.ExtendedPrice) AS Revenue
FROM Sales.Invoices i
         JOIN Sales.InvoiceLines il ON il.InvoiceID = i.InvoiceID
WHERE i.InvoiceDate >= @from
  AND i.InvoiceDate < @to
GROUP BY MONTH(i.InvoiceDate)
ORDER BY InvoiceMonth
;


-- ------------------------------------------------------------------------------------------------------ WWI-SALES-I-03
-- Customer care checks the deliveries of a customer since a date: each invoice with when the delivery was confirmed
-- and who received it. Both are computed columns, derived from the ReturnedDeliveryData JSON document.
DECLARE @customerId int = 1, @from date = '2016-04-01';
SELECT InvoiceID, InvoiceDate, ConfirmedDeliveryTime, ConfirmedReceivedBy
FROM Sales.Invoices
WHERE CustomerID = @customerId
  AND InvoiceDate >= @from
ORDER BY InvoiceID
;


-- ------------------------------------------------------------------------------------------------------ WWI-SALES-I-04
-- Accounts receivable wants the history of a customer's credit limit: every version of the row, with the period it
-- was current, the oldest first. FOR SYSTEM_TIME ALL reads the current row and the history together; it is SQL only.
DECLARE @customerId int = 890;
SELECT CustomerID, CreditLimit, ValidFrom, ValidTo
FROM Sales.Customers FOR SYSTEM_TIME ALL
WHERE CustomerID = @customerId
ORDER BY ValidFrom
;


-- ------------------------------------------------------------------------------------------------------ WWI-SALES-A-01
-- The sales manager ranks the salespeople of each year by the invoiced extended price, and wants the top three of
-- each year. RANK() OVER (...), a window function, is SQL only.
WITH yearly AS (SELECT YEAR(i.InvoiceDate) AS SalesYear, i.SalespersonPersonID, SUM(il.ExtendedPrice) AS Revenue
                FROM Sales.Invoices i
                         JOIN Sales.InvoiceLines il ON il.InvoiceID = i.InvoiceID
                GROUP BY YEAR(i.InvoiceDate), i.SalespersonPersonID),
     ranked AS (SELECT y.*, RANK() OVER (PARTITION BY y.SalesYear ORDER BY y.Revenue DESC) AS SalesRank
                FROM yearly y)
SELECT r.SalesYear, r.SalesRank, p.FullName, r.Revenue
FROM ranked r
         JOIN Application.People p ON p.PersonID = r.SalespersonPersonID
WHERE r.SalesRank <= 3
ORDER BY r.SalesYear, r.SalesRank
;


-- ------------------------------------------------------------------------------------------------------ WWI-SALES-A-02
-- Accounts receivable wants the customers whose credit limit is not what it was at a past moment, with the limit then
-- and now. FOR SYSTEM_TIME AS OF reads the table as it was; it is SQL only.
DECLARE @asOf datetime2 = '2015-01-01';
SELECT c.CustomerID, c.CustomerName, h.CreditLimit AS CreditLimitThen, c.CreditLimit AS CreditLimitNow
FROM Sales.Customers c
         JOIN Sales.Customers FOR SYSTEM_TIME AS OF @asOf h ON h.CustomerID = c.CustomerID
WHERE h.CreditLimit <> c.CreditLimit
ORDER BY c.CustomerID
;


-- ------------------------------------------------------------------------------------------------------ WWI-SALES-A-03
-- The warehouse wants the five oldest backorders which were never invoiced, with the customer and the order which
-- backordered them.
SELECT TOP (5) o.OrderID, o.OrderDate, c.CustomerName, p.OrderID AS BackorderedBy
FROM Sales.Orders p
         JOIN Sales.Orders o ON o.OrderID = p.BackorderOrderID
         JOIN Sales.Customers c ON c.CustomerID = o.CustomerID
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Invoices i
                  WHERE i.OrderID = o.OrderID)
ORDER BY o.OrderDate, o.OrderID
;
