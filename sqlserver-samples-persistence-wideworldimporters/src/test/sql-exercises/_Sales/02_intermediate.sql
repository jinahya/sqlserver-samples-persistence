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
---
--
-- Exercises at the Intermediate level on the Sales schema of WideWorldImporters, and on the Website view over it.
--
-- These are the SQL solutions of the 'Sales' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- ------------------------------------------------------------------------------------------------------ WWI_SALES_I_01
-- The sales manager wants the five stock items which earned the most in a year, by the invoiced extended price, with
-- the quantity sold; `StockItemID` breaks a tie.
DECLARE @from date = '2015-01-01', @to date = '2016-01-01';
SELECT TOP (5) si.StockItemID, si.StockItemName, SUM(il.Quantity) AS Quantity, SUM(il.ExtendedPrice) AS Revenue
FROM Sales.InvoiceLines il
         JOIN Sales.Invoices i ON i.InvoiceID = il.InvoiceID
         JOIN Warehouse.StockItems si ON si.StockItemID = il.StockItemID
WHERE i.InvoiceDate >= @from
  AND i.InvoiceDate < @to
GROUP BY si.StockItemID, si.StockItemName
ORDER BY Revenue DESC, si.StockItemID
;

-- ------------------------------------------------------------------------------------------------------ WWI_SALES_I_02
-- Finance wants the sales of a year by month: the number of invoices and the invoiced extended price of each month.
DECLARE
@from date = '2015-01-01', @to date = '2016-01-01';
SELECT MONTH (i.InvoiceDate) AS InvoiceMonth, COUNT (DISTINCT i.InvoiceID) AS Invoices, SUM (il.ExtendedPrice) AS Revenue
FROM Sales.Invoices i
    JOIN Sales.InvoiceLines il
ON il.InvoiceID = i.InvoiceID
WHERE i.InvoiceDate >= @from
  AND i.InvoiceDate
    < @to
GROUP BY MONTH (i.InvoiceDate)
ORDER BY InvoiceMonth
;

-- ------------------------------------------------------------------------------------------------------ WWI_SALES_I_03
-- Show a customer's deliveries confirmed on or after a date, with the invoice, the confirmation time and who received
-- it, the earliest confirmation first.
DECLARE @customerId int = 1, @from date = '2016-04-01';
SELECT InvoiceID, InvoiceDate, ConfirmedDeliveryTime, ConfirmedReceivedBy
FROM Sales.Invoices
WHERE CustomerID = @customerId
  AND ConfirmedDeliveryTime >= @from
ORDER BY ConfirmedDeliveryTime, InvoiceID
;

-- ------------------------------------------------------------------------------------------------------ WWI_SALES_I_04
-- Accounts receivable wants the history of a customer's credit limit: each limit it has had, from when it was set, the
-- oldest first. `FOR SYSTEM_TIME ALL` reads the current row and the history together, and `LAG` keeps the versions
-- whose limit changed, NULLs compared as equal; this is SQL only.
DECLARE @customerId int = 890;
WITH versions AS (SELECT CustomerID,
                         CreditLimit,
                         ValidFrom,
                         LAG(CreditLimit) OVER (ORDER BY ValidFrom) AS PreviousLimit,
                         ROW_NUMBER() OVER (ORDER BY ValidFrom)     AS Version
                  FROM Sales.Customers FOR SYSTEM_TIME ALL
                  WHERE CustomerID = @customerId)
SELECT CustomerID, CreditLimit, ValidFrom AS SetFrom
FROM versions
WHERE Version = 1
   OR NOT EXISTS (SELECT CreditLimit
                  INTERSECT
                  SELECT PreviousLimit)
ORDER BY ValidFrom
;

-- ------------------------------------------------------------------------------------------------------ WWI_SALES_I_05
-- The sales desk wants the special deals in force on a date: each deal, what it applies to (stock item or stock group,
-- customer, buying group or customer category) and its discount or price, by deal.
DECLARE @on date = '2016-01-01';
SELECT d.SpecialDealID, d.DealDescription, d.StartDate, d.EndDate,
       si.StockItemName, sg.StockGroupName, c.CustomerName, bg.BuyingGroupName, cc.CustomerCategoryName,
       d.DiscountAmount, d.DiscountPercentage, d.UnitPrice
FROM Sales.SpecialDeals d
         LEFT JOIN Warehouse.StockItems si ON si.StockItemID = d.StockItemID
         LEFT JOIN Warehouse.StockGroups sg ON sg.StockGroupID = d.StockGroupID
         LEFT JOIN Sales.Customers c ON c.CustomerID = d.CustomerID
         LEFT JOIN Sales.BuyingGroups bg ON bg.BuyingGroupID = d.BuyingGroupID
         LEFT JOIN Sales.CustomerCategories cc ON cc.CustomerCategoryID = d.CustomerCategoryID
WHERE d.StartDate <= @on
  AND d.EndDate >= @on
ORDER BY d.SpecialDealID
;

-- ------------------------------------------------------------------------------------------------------ WWI_SALES_I_06
-- List the special deals that apply to a given customer and stock item on a date, a NULL target meaning no restriction
-- and both dates included, with their three pricing options, without choosing one deal over another.
DECLARE @customerId int = 1, @stockItemId int = 1, @on date = '2016-05-01';
SELECT sd.SpecialDealID, sd.DealDescription, sd.StartDate, sd.EndDate, sd.DiscountAmount, sd.DiscountPercentage, sd.UnitPrice
FROM Sales.SpecialDeals sd
         JOIN Sales.Customers c ON c.CustomerID = @customerId
         JOIN Warehouse.StockItems si ON si.StockItemID = @stockItemId
WHERE sd.StartDate <= @on
  AND sd.EndDate >= @on
  AND (sd.CustomerID IS NULL OR sd.CustomerID = c.CustomerID)
  AND (sd.BuyingGroupID IS NULL OR sd.BuyingGroupID = c.BuyingGroupID)
  AND (sd.CustomerCategoryID IS NULL OR sd.CustomerCategoryID = c.CustomerCategoryID)
  AND (sd.StockItemID IS NULL OR sd.StockItemID = si.StockItemID)
  AND (sd.StockGroupID IS NULL
    OR EXISTS (SELECT 1 FROM Warehouse.StockItemStockGroups sisg WHERE sisg.StockItemID = si.StockItemID AND sisg.StockGroupID = sd.StockGroupID))
ORDER BY sd.SpecialDealID
;

-- ------------------------------------------------------------------------------------------------------ WWI_SALES_I_07
-- Accounts receivable wants the ten customers with a credit limit whose outstanding balance uses the most of it: the
-- limit, the balance of the transactions not finalized, and the share of the limit used; `CustomerID` breaks a tie.
SELECT TOP (10) c.CustomerID,
                c.CustomerName,
                c.CreditLimit,
                SUM(ct.OutstandingBalance)                                               AS Outstanding,
                CAST(SUM(ct.OutstandingBalance) * 100 / c.CreditLimit AS decimal(10, 1)) AS PercentUsed
FROM Sales.CustomerTransactions ct
         JOIN Sales.Customers c ON c.CustomerID = ct.CustomerID
WHERE ct.IsFinalized = 0
  AND c.CreditLimit > 0
GROUP BY c.CustomerID, c.CustomerName, c.CreditLimit
ORDER BY PercentUsed DESC, c.CustomerID
;

