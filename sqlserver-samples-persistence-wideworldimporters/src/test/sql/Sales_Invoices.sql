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
-- Profiling queries on Sales.Invoices, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_Invoices.sql
--


-- ----------------------------------------------------------------------------------------------------------- InvoiceID
-- how many rows, and the smallest and the largest InvoiceID; 70510 invoices, 1 to 70510.
SELECT COUNT(*), MIN(InvoiceID), MAX(InvoiceID)
FROM Sales.Invoices
;

-- an InvoiceID whose next value is missing, below the largest.
-- no rows: InvoiceID runs from 1 without a gap.
SELECT a.InvoiceID + 1 AS missing_from
FROM Sales.Invoices a
WHERE a.InvoiceID < (SELECT MAX(InvoiceID) FROM Sales.Invoices)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.Invoices b
                  WHERE b.InvoiceID = a.InvoiceID + 1)
;


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many customers are invoiced; all 663.
SELECT COUNT(DISTINCT CustomerID)
FROM Sales.Invoices
;

-- how many invoices have a customer other than the customer of their order; 0.
SELECT COUNT(*)
FROM Sales.Invoices i
         JOIN Sales.Orders o ON o.OrderID = i.OrderID
WHERE i.CustomerID <> o.CustomerID
;


-- ---------------------------------------------------------------------------------------------------- BillToCustomerID
-- how many invoices are billed to other than the BillToCustomerID of their customer; 0.
SELECT COUNT(*)
FROM Sales.Invoices i
         JOIN Sales.Customers c ON c.CustomerID = i.CustomerID
WHERE i.BillToCustomerID <> c.BillToCustomerID
;


-- ------------------------------------------------------------------------------------------------------------- OrderID
-- the invoices with no order; 0, every invoice has one.
SELECT COUNT(*) - COUNT(OrderID)
FROM Sales.Invoices
;

-- OrderID has no unique key; an order invoiced more than once.
-- no rows: an order has one invoice at most.
SELECT OrderID, COUNT(*) AS invoices
FROM Sales.Invoices
WHERE OrderID IS NOT NULL
GROUP BY OrderID
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------- DeliveryMethodID
-- the invoices of each delivery method; all 70510 by Delivery Van (3).
SELECT DeliveryMethodID, COUNT(*) AS invoices
FROM Sales.Invoices
GROUP BY DeliveryMethodID
ORDER BY DeliveryMethodID ASC
;


-- ----------------------------------------------------------------------------------------------------- ContactPersonID
-- how many invoices have a contact other than the contact of their order; 0.
SELECT COUNT(*)
FROM Sales.Invoices i
         JOIN Sales.Orders o ON o.OrderID = i.OrderID
WHERE i.ContactPersonID <> o.ContactPersonID
;


-- ---------------------------------------------------------------------------------------------------- AccountsPersonID
-- how many invoices have an accounts person other than the contact; 44245, those billed to another customer.
SELECT COUNT(*)
FROM Sales.Invoices
WHERE AccountsPersonID <> ContactPersonID
;

-- how many invoices have an accounts person other than the primary contact of the customer billed; 0.
SELECT COUNT(*)
FROM Sales.Invoices i
         JOIN Sales.Customers b ON b.CustomerID = i.BillToCustomerID
WHERE i.AccountsPersonID <> b.PrimaryContactPersonID
;


-- ------------------------------------------------------------------------------------------------- SalespersonPersonID
-- how many invoices have a salesperson other than the salesperson of their order; 0.
SELECT COUNT(*)
FROM Sales.Invoices i
         JOIN Sales.Orders o ON o.OrderID = i.OrderID
WHERE i.SalespersonPersonID <> o.SalespersonPersonID
;


-- ---------------------------------------------------------------------------------------------------- PackedByPersonID
-- the invoices packed by each person; 19 packers, 2 to 20, 2717 (19) to 4817 (15) each.
SELECT PackedByPersonID, COUNT(*) AS invoices
FROM Sales.Invoices
GROUP BY PackedByPersonID
ORDER BY PackedByPersonID ASC
;


-- --------------------------------------------------------------------------------------------------------- InvoiceDate
-- the range of the invoice dates; 2013-01-01 to 2016-05-31.
SELECT MIN(InvoiceDate), MAX(InvoiceDate)
FROM Sales.Invoices
;

-- the fewest and the most days from the order to the invoice, and the invoices of the same day; 0 to 1116 days,
-- 66037 on the day of the order.
SELECT MIN(DATEDIFF(DAY, o.OrderDate, i.InvoiceDate)), MAX(DATEDIFF(DAY, o.OrderDate, i.InvoiceDate)),
       SUM(CASE WHEN i.InvoiceDate = o.OrderDate THEN 1 ELSE 0 END) AS same_day
FROM Sales.Invoices i
         JOIN Sales.Orders o ON o.OrderID = i.OrderID
;


-- ----------------------------------------------------------------------------------------- CustomerPurchaseOrderNumber
-- how many invoices have a CustomerPurchaseOrderNumber other than the one of their order; 0.
SELECT COUNT(*)
FROM Sales.Invoices i
         JOIN Sales.Orders o ON o.OrderID = i.OrderID
WHERE EXISTS (SELECT i.CustomerPurchaseOrderNumber EXCEPT SELECT o.CustomerPurchaseOrderNumber)
;


-- -------------------------------------------------------------------------------------------------------- IsCreditNote
-- the invoices and the credit notes; all 70510 are invoices, none a credit note.
SELECT IsCreditNote, COUNT(*) AS invoices
FROM Sales.Invoices
GROUP BY IsCreditNote
;


-- ---------------------------------------------------------------------------------------------------- CreditNoteReason
-- the invoices with a CreditNoteReason; 0, as there is no credit note.
SELECT COUNT(CreditNoteReason)
FROM Sales.Invoices
;


-- ------------------------------------------------------------------------------------------------------------ Comments
-- the invoices with Comments; 0, every Comments is NULL.
SELECT COUNT(Comments)
FROM Sales.Invoices
;


-- ------------------------------------------------------------------------------------------------ DeliveryInstructions
-- the invoices with no DeliveryInstructions, and the shortest and the longest of the others; none is NULL,
-- 21 and 36 characters.
SELECT COUNT(*) - COUNT(DeliveryInstructions), MIN(LEN(DeliveryInstructions)), MAX(LEN(DeliveryInstructions))
FROM Sales.Invoices
;

-- how many invoices have DeliveryInstructions other than the delivery address lines of their customer; 0.
SELECT COUNT(*)
FROM Sales.Invoices i
         JOIN Sales.Customers c ON c.CustomerID = i.CustomerID
WHERE i.DeliveryInstructions <> c.DeliveryAddressLine1 + ', ' + c.DeliveryAddressLine2
;


-- ---------------------------------------------------------------------------------------------------- InternalComments
-- the invoices with InternalComments; 0, every InternalComments is NULL.
SELECT COUNT(InternalComments)
FROM Sales.Invoices
;


-- ------------------------------------------------------------------------------------------------------- TotalDryItems
-- the smallest and the largest TotalDryItems; 0 and 5.
SELECT MIN(TotalDryItems), MAX(TotalDryItems)
FROM Sales.Invoices
;


-- --------------------------------------------------------------------------------------------------- TotalChillerItems
-- the smallest and the largest TotalChillerItems; 0 and 3.
SELECT MIN(TotalChillerItems), MAX(TotalChillerItems)
FROM Sales.Invoices
;

-- how many invoices have no item at all; 0.
SELECT COUNT(*)
FROM Sales.Invoices
WHERE TotalDryItems + TotalChillerItems = 0
;


-- --------------------------------------------------------------------------------------------------------- DeliveryRun
-- the distinct delivery runs, bracketed to show blanks; a finding, as in Sales.Customers: no invoice
-- has a run, 66652 carry the empty string and 3858 NULL.
SELECT '[' + DeliveryRun + ']' AS DeliveryRun, COUNT(*) AS invoices
FROM Sales.Invoices
GROUP BY DeliveryRun
ORDER BY DeliveryRun ASC
;


-- --------------------------------------------------------------------------------------------------------- RunPosition
-- the distinct run positions, bracketed to show blanks; like DeliveryRun, 66652 carry the empty
-- string and 3858 NULL.
SELECT '[' + RunPosition + ']' AS RunPosition, COUNT(*) AS invoices
FROM Sales.Invoices
GROUP BY RunPosition
ORDER BY RunPosition ASC
;


-- ------------------------------------------------------------------------------------------------ ReturnedDeliveryData
-- the invoices with no ReturnedDeliveryData, and the shortest and the longest of the others; none is NULL,
-- 107 and 373 characters.
SELECT COUNT(*) - COUNT(ReturnedDeliveryData), MIN(LEN(ReturnedDeliveryData)), MAX(LEN(ReturnedDeliveryData))
FROM Sales.Invoices
;

-- CK_Sales_Invoices_ReturnedDeliveryData_Must_Be_Valid_JSON keeps it JSON.
-- no rows: every ReturnedDeliveryData is valid JSON.
SELECT InvoiceID
FROM Sales.Invoices
WHERE ISJSON(ReturnedDeliveryData) = 0
;

-- the number of events an invoice records; 1 for 84, 2 for 70426.
SELECT e.events, COUNT(*) AS invoices
FROM Sales.Invoices i
         CROSS APPLY (SELECT COUNT(*) AS events
                      FROM OPENJSON(i.ReturnedDeliveryData, '$.Events')) e
GROUP BY e.events
ORDER BY e.events ASC
;


-- ----------------------------------------------------------------------------------------------- ConfirmedDeliveryTime
-- ConfirmedDeliveryTime is computed from $.DeliveredWhen; the invoices with none, and the range of the
-- others; 84 with none, the others from 2013-01-02 07:05 to 2016-05-31 13:30.
SELECT COUNT(*) - COUNT(ConfirmedDeliveryTime), MIN(ConfirmedDeliveryTime), MAX(ConfirmedDeliveryTime)
FROM Sales.Invoices
;

-- an invoice delivered before it was invoiced.
-- no rows: no ConfirmedDeliveryTime is earlier than its InvoiceDate.
SELECT InvoiceID, InvoiceDate, ConfirmedDeliveryTime
FROM Sales.Invoices
WHERE ConfirmedDeliveryTime < CAST(InvoiceDate AS datetime2)
;


-- ------------------------------------------------------------------------------------------------- ConfirmedReceivedBy
-- ConfirmedReceivedBy is computed from $.ReceivedBy; the invoices with none, and the shortest and the longest of
-- the others; 84 with none, the others 5 to 29 characters.
SELECT COUNT(*) - COUNT(ConfirmedReceivedBy), MIN(LEN(ConfirmedReceivedBy)), MAX(LEN(ConfirmedReceivedBy))
FROM Sales.Invoices
;

-- how many invoices are confirmed as delivered with no receiver, or the other way around; 0.
SELECT COUNT(*)
FROM Sales.Invoices
WHERE (ConfirmedDeliveryTime IS NULL AND ConfirmedReceivedBy IS NOT NULL)
   OR (ConfirmedDeliveryTime IS NOT NULL AND ConfirmedReceivedBy IS NULL)
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the most rows; 2 with 4652, 4 with 4523, 11 with 4470.
SELECT TOP (3) LastEditedBy, COUNT(*) AS invoices
FROM Sales.Invoices
GROUP BY LastEditedBy
ORDER BY invoices DESC, LastEditedBy ASC
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- the range of LastEditedWhen; 2013-01-02 07:00 to 2016-05-31 12:00.
SELECT MIN(LastEditedWhen), MAX(LastEditedWhen)
FROM Sales.Invoices
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many invoices have no line; 0.
SELECT COUNT(*)
FROM Sales.Invoices i
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.InvoiceLines l
                  WHERE l.InvoiceID = i.InvoiceID)
;

-- how many invoices have TotalDryItems and TotalChillerItems other than the number of their lines of dry and of
-- chiller stock; 0, the totals count lines, not quantities.
SELECT COUNT(*)
FROM Sales.Invoices i
WHERE i.TotalDryItems <> (SELECT COUNT(*)
                          FROM Sales.InvoiceLines l
                                   JOIN Warehouse.StockItems s ON s.StockItemID = l.StockItemID
                          WHERE l.InvoiceID = i.InvoiceID
                            AND s.IsChillerStock = 0)
   OR i.TotalChillerItems <> (SELECT COUNT(*)
                              FROM Sales.InvoiceLines l
                                       JOIN Warehouse.StockItems s ON s.StockItemID = l.StockItemID
                              WHERE l.InvoiceID = i.InvoiceID
                                AND s.IsChillerStock = 1)
;

