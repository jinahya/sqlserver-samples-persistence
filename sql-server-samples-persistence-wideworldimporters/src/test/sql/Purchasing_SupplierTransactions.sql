-- -
-- #%L
-- sql-server-samples-persistence-wideworldimporters
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
-- Profiling queries on Purchasing.SupplierTransactions, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Purchasing_SupplierTransactions.sql
--


-- ----------------------------------------------------------------------------------------------- SupplierTransactionID
-- how many rows, and the smallest and the largest SupplierTransactionID; 2438 transactions, 134 to 335847.
SELECT COUNT(*), MIN(SupplierTransactionID), MAX(SupplierTransactionID)
FROM Purchasing.SupplierTransactions
;

-- SupplierTransactionID comes from Sequences.TransactionID, which the customer and stock item transactions share;
-- an ID also used by one of them.
-- no rows: the sequence hands each ID to a single table.
SELECT t.SupplierTransactionID
FROM Purchasing.SupplierTransactions t
WHERE EXISTS (SELECT 1
              FROM Sales.CustomerTransactions c
              WHERE c.CustomerTransactionID = t.SupplierTransactionID)
   OR EXISTS (SELECT 1
              FROM Warehouse.StockItemTransactions w
              WHERE w.StockItemTransactionID = t.SupplierTransactionID)
;


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- the transactions of each supplier; the 7 suppliers with orders, Fabrikam, Inc. (4) 1232 and Litware, Inc. (7) 1160.
SELECT t.SupplierID, s.SupplierName, COUNT(*) AS transactions
FROM Purchasing.SupplierTransactions t
         JOIN Purchasing.Suppliers s ON s.SupplierID = t.SupplierID
GROUP BY t.SupplierID, s.SupplierName
ORDER BY t.SupplierID ASC
;


-- --------------------------------------------------------------------------------------------------- TransactionTypeID
-- the types of the transactions; Supplier Invoice (5) 2072 and Supplier Payment Issued (7) 366.
SELECT t.TransactionTypeID, y.TransactionTypeName, COUNT(*) AS transactions
FROM Purchasing.SupplierTransactions t
         JOIN Application.TransactionTypes y ON y.TransactionTypeID = t.TransactionTypeID
GROUP BY t.TransactionTypeID, y.TransactionTypeName
ORDER BY t.TransactionTypeID ASC
;


-- ----------------------------------------------------------------------------------------------------- PurchaseOrderID
-- PurchaseOrderID is nullable; how many transactions of each type have one. Every invoice, and no payment.
SELECT TransactionTypeID, COUNT(*) AS transactions, COUNT(PurchaseOrderID) AS with_order
FROM Purchasing.SupplierTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;

-- the schema has no unique key on PurchaseOrderID; an order more than one transaction is of.
-- no rows: an order has a single invoice.
SELECT PurchaseOrderID, COUNT(*) AS transactions
FROM Purchasing.SupplierTransactions
WHERE PurchaseOrderID IS NOT NULL
GROUP BY PurchaseOrderID
HAVING COUNT(*) > 1
;

-- a transaction of another supplier than its order's.
-- no rows: every invoice is of the supplier of its order.
SELECT t.SupplierTransactionID, t.SupplierID, o.SupplierID AS order_supplier
FROM Purchasing.SupplierTransactions t
         JOIN Purchasing.PurchaseOrders o ON o.PurchaseOrderID = t.PurchaseOrderID
WHERE o.SupplierID <> t.SupplierID
;


-- ----------------------------------------------------------------------------------------------------- PaymentMethodID
-- the payment methods of the transactions; EFT (4) for all 2438.
SELECT t.PaymentMethodID, m.PaymentMethodName, COUNT(*) AS transactions
FROM Purchasing.SupplierTransactions t
         JOIN Application.PaymentMethods m ON m.PaymentMethodID = t.PaymentMethodID
GROUP BY t.PaymentMethodID, m.PaymentMethodName
;


-- ----------------------------------------------------------------------------------------------- SupplierInvoiceNumber
-- SupplierInvoiceNumber is nullable; how many transactions of each type have one, and how long. Every invoice, and
-- no payment; 1 to 4 characters, all digits.
SELECT TransactionTypeID, COUNT(SupplierInvoiceNumber) AS with_number,
       MIN(LEN(SupplierInvoiceNumber)), MAX(LEN(SupplierInvoiceNumber)),
       SUM(CASE WHEN SupplierInvoiceNumber LIKE '%[^0-9]%' THEN 1 ELSE 0 END) AS non_digit
FROM Purchasing.SupplierTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;

-- the schema has no unique key on SupplierInvoiceNumber; how many numbers more than one invoice of the same supplier
-- carries. 96; supplier invoice numbers repeat even within a supplier.
SELECT COUNT(*) AS numbers
FROM (SELECT SupplierID, SupplierInvoiceNumber
      FROM Purchasing.SupplierTransactions
      WHERE SupplierInvoiceNumber IS NOT NULL
      GROUP BY SupplierID, SupplierInvoiceNumber
      HAVING COUNT(*) > 1) d
;


-- ----------------------------------------------------------------------------------------------------- TransactionDate
-- the range of TransactionDate; 2013-01-02 to 2016-05-31.
SELECT MIN(TransactionDate), MAX(TransactionDate)
FROM Purchasing.SupplierTransactions
;

-- the weekdays of the transactions; invoices Monday to Friday, 641 of them on Monday, and payments only on Monday.
SELECT TransactionTypeID, DATENAME(weekday, TransactionDate) AS weekday, COUNT(*) AS transactions
FROM Purchasing.SupplierTransactions
GROUP BY TransactionTypeID, DATENAME(weekday, TransactionDate), DATEPART(weekday, TransactionDate)
ORDER BY TransactionTypeID ASC, DATEPART(weekday, TransactionDate) ASC
;

-- how many days after the order the invoice is dated; 1 to 3, a day after for 1432.
SELECT DATEDIFF(day, o.OrderDate, t.TransactionDate) AS days, COUNT(*) AS invoices
FROM Purchasing.SupplierTransactions t
         JOIN Purchasing.PurchaseOrders o ON o.PurchaseOrderID = t.PurchaseOrderID
GROUP BY DATEDIFF(day, o.OrderDate, t.TransactionDate)
ORDER BY days ASC
;


-- -------------------------------------------------------------------------------------------------- AmountExcludingTax
-- the range of AmountExcludingTax by type; 76.80 to 1627734.00 for the invoices, and 0.00 for every payment.
SELECT TransactionTypeID, MIN(AmountExcludingTax), MAX(AmountExcludingTax)
FROM Purchasing.SupplierTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;

-- an invoice whose AmountExcludingTax is not the sum of the received outers of its order at the expected price.
-- no rows: every invoice charges exactly its order lines.
SELECT t.SupplierTransactionID, t.AmountExcludingTax, l.amount
FROM Purchasing.SupplierTransactions t
         JOIN (SELECT PurchaseOrderID, SUM(ReceivedOuters * ExpectedUnitPricePerOuter) AS amount
               FROM Purchasing.PurchaseOrderLines
               GROUP BY PurchaseOrderID) l ON l.PurchaseOrderID = t.PurchaseOrderID
WHERE t.AmountExcludingTax <> l.amount
;


-- ----------------------------------------------------------------------------------------------------------- TaxAmount
-- the tax rates of the invoices, to two places; 15% for 2067, and 10% for 5, all of A Datum Corporation (1).
SELECT ROUND(TaxAmount / AmountExcludingTax, 2) AS rate, COUNT(*) AS invoices,
       MIN(SupplierID), MAX(SupplierID)
FROM Purchasing.SupplierTransactions
WHERE TransactionTypeID = 5
GROUP BY ROUND(TaxAmount / AmountExcludingTax, 2)
ORDER BY rate ASC
;

-- an invoice at 15% whose TaxAmount is not 15% rounded to the cent.
-- 8 rows, each a cent to three cents off, such as 186 with 411.11 for 411.08.
SELECT SupplierTransactionID, AmountExcludingTax, TaxAmount, ROUND(AmountExcludingTax * 0.15, 2) AS expected
FROM Purchasing.SupplierTransactions
WHERE TransactionTypeID = 5
  AND ROUND(TaxAmount / AmountExcludingTax, 2) = 0.15
  AND TaxAmount <> ROUND(AmountExcludingTax * 0.15, 2)
ORDER BY SupplierTransactionID ASC
;

-- the range of TaxAmount by type; 11.52 to 244160.10 for the invoices, and 0.00 for every payment.
SELECT TransactionTypeID, MIN(TaxAmount), MAX(TaxAmount)
FROM Purchasing.SupplierTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;


-- --------------------------------------------------------------------------------------------------- TransactionAmount
-- a transaction whose TransactionAmount is not AmountExcludingTax plus TaxAmount, by type.
-- 366 rows, every payment: a payment carries its amount, negative, in TransactionAmount alone.
SELECT TransactionTypeID, COUNT(*) AS transactions
FROM Purchasing.SupplierTransactions
WHERE TransactionAmount <> AmountExcludingTax + TaxAmount
GROUP BY TransactionTypeID
;

-- the range of TransactionAmount by type; 88.32 to 1871894.10 for the invoices, -9313012.80 to -195.50 for the
-- payments.
SELECT TransactionTypeID, MIN(TransactionAmount), MAX(TransactionAmount)
FROM Purchasing.SupplierTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;


-- -------------------------------------------------------------------------------------------------- OutstandingBalance
-- a transaction with an OutstandingBalance; 2 invoices of 2016-05-31, 335843 and 335847, owed in full.
SELECT SupplierTransactionID, SupplierID, TransactionDate, TransactionAmount, OutstandingBalance
FROM Purchasing.SupplierTransactions
WHERE OutstandingBalance <> 0
;

-- a supplier whose transactions do not add up to its outstanding balance.
-- no rows: the payments settle all but the 2 open invoices.
SELECT SupplierID, SUM(TransactionAmount) AS total, SUM(OutstandingBalance) AS outstanding
FROM Purchasing.SupplierTransactions
GROUP BY SupplierID
HAVING SUM(TransactionAmount) <> SUM(OutstandingBalance)
;


-- ---------------------------------------------------------------------------------------------------- FinalizationDate
-- FinalizationDate is nullable; how many transactions have none. 2, the open invoices.
SELECT COUNT(*) - COUNT(FinalizationDate) AS without
FROM Purchasing.SupplierTransactions
;

-- how many days after the transaction it was finalized, by type; invoices the same day or 3 to 6 days later, and
-- payments the same day.
SELECT TransactionTypeID, DATEDIFF(day, TransactionDate, FinalizationDate) AS days, COUNT(*) AS transactions
FROM Purchasing.SupplierTransactions
WHERE FinalizationDate IS NOT NULL
GROUP BY TransactionTypeID, DATEDIFF(day, TransactionDate, FinalizationDate)
ORDER BY TransactionTypeID ASC, days ASC
;


-- --------------------------------------------------------------------------------------------------------- IsFinalized
-- IsFinalized is computed, 1 when FinalizationDate is not NULL; a row where it is not.
-- no rows: IsFinalized follows FinalizationDate.
SELECT SupplierTransactionID, FinalizationDate, IsFinalized
FROM Purchasing.SupplierTransactions
WHERE IsFinalized <> CASE WHEN FinalizationDate IS NULL THEN 0 ELSE 1 END
;

-- how many transactions are finalized; 2436, all but the 2 open invoices.
SELECT IsFinalized, COUNT(*) AS transactions
FROM Purchasing.SupplierTransactions
GROUP BY IsFinalized
ORDER BY IsFinalized ASC
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- how many people last edited the transactions; 19, persons 2 to 20.
SELECT COUNT(DISTINCT LastEditedBy), MIN(LastEditedBy), MAX(LastEditedBy)
FROM Purchasing.SupplierTransactions
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- a finalized transaction last edited on another day than its FinalizationDate.
-- no rows: the finalization is the last edit.
SELECT SupplierTransactionID, FinalizationDate, LastEditedWhen
FROM Purchasing.SupplierTransactions
WHERE FinalizationDate <> CAST(LastEditedWhen AS date)
;

