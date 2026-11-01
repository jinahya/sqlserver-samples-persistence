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
-- Profiling queries on Warehouse.StockItemTransactions, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_StockItemTransactions.sql
--


-- ---------------------------------------------------------------------------------------------- StockItemTransactionID
-- how many rows, and the smallest and the largest StockItemTransactionID; 236667 transactions,
-- 1 to 336251, with the gaps the shared sequence leaves.
SELECT COUNT(*), MIN(StockItemTransactionID), MAX(StockItemTransactionID)
FROM Warehouse.StockItemTransactions
;

-- StockItemTransactionID comes from Sequences.TransactionID, which the customer and the supplier transactions draw
-- from too; an id also taken by one of those.
-- no rows: the sequence hands each id to one table only.
SELECT w.StockItemTransactionID
FROM Warehouse.StockItemTransactions w
WHERE EXISTS (SELECT 1
              FROM Sales.CustomerTransactions c
              WHERE c.CustomerTransactionID = w.StockItemTransactionID)
   OR EXISTS (SELECT 1
              FROM Purchasing.SupplierTransactions s
              WHERE s.SupplierTransactionID = w.StockItemTransactionID)
;


-- --------------------------------------------------------------------------------------------------------- StockItemID
-- how many stock items have transactions; all 227.
SELECT COUNT(DISTINCT StockItemID)
FROM Warehouse.StockItemTransactions
;


-- --------------------------------------------------------------------------------------------------- TransactionTypeID
-- the transactions of each type; 228265 Stock Issue (10), 8358 Stock Receipt (11), 44 Stock
-- Adjustment at Stocktake (12).
SELECT t.TransactionTypeID, y.TransactionTypeName, COUNT(*) AS transactions
FROM Warehouse.StockItemTransactions t
         JOIN Application.TransactionTypes y ON y.TransactionTypeID = t.TransactionTypeID
GROUP BY t.TransactionTypeID, y.TransactionTypeName
ORDER BY t.TransactionTypeID ASC
;


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- the transactions with a customer, by type; every issue has one, no receipt or adjustment does.
SELECT TransactionTypeID, COUNT(CustomerID) AS with_customer, COUNT(*) - COUNT(CustomerID) AS without_customer
FROM Warehouse.StockItemTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;

-- how many transactions have a customer other than the customer of their invoice; 0.
SELECT COUNT(*)
FROM Warehouse.StockItemTransactions t
         JOIN Sales.Invoices i ON i.InvoiceID = t.InvoiceID
WHERE t.CustomerID <> i.CustomerID
;


-- ----------------------------------------------------------------------------------------------------------- InvoiceID
-- the transactions with an invoice, by type; every issue has one, no receipt or adjustment does.
SELECT TransactionTypeID, COUNT(InvoiceID) AS with_invoice, COUNT(*) - COUNT(InvoiceID) AS without_invoice
FROM Warehouse.StockItemTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;

-- how many issues match no invoice line by invoice, stock item and quantity; 0, an issue is an invoice line
-- with its quantity negated.
SELECT COUNT(*)
FROM Warehouse.StockItemTransactions t
WHERE t.InvoiceID IS NOT NULL
  AND NOT EXISTS (SELECT 1
                  FROM Sales.InvoiceLines l
                  WHERE l.InvoiceID = t.InvoiceID
                    AND l.StockItemID = t.StockItemID
                    AND l.Quantity = -t.Quantity)
;


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- the transactions with a supplier, by type; every receipt has one, no issue or adjustment does.
SELECT TransactionTypeID, COUNT(SupplierID) AS with_supplier, COUNT(*) - COUNT(SupplierID) AS without_supplier
FROM Warehouse.StockItemTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;

-- how many receipts come from a supplier other than the one of their stock item; 0.
SELECT COUNT(*)
FROM Warehouse.StockItemTransactions t
         JOIN Warehouse.StockItems s ON s.StockItemID = t.StockItemID
WHERE t.SupplierID <> s.SupplierID
;


-- ----------------------------------------------------------------------------------------------------- PurchaseOrderID
-- the transactions with a purchase order, by type; every receipt has one, no issue or adjustment
-- does.
SELECT TransactionTypeID, COUNT(PurchaseOrderID) AS with_order, COUNT(*) - COUNT(PurchaseOrderID) AS without_order
FROM Warehouse.StockItemTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;

-- how many receipts come from a supplier other than the one of their purchase order; 0.
SELECT COUNT(*)
FROM Warehouse.StockItemTransactions t
         JOIN Purchasing.PurchaseOrders p ON p.PurchaseOrderID = t.PurchaseOrderID
WHERE t.SupplierID <> p.SupplierID
;


-- --------------------------------------------------------------------------------------------- TransactionOccurredWhen
-- the range of TransactionOccurredWhen; 2013-01-01 12:00 to 2016-05-31 12:00.
SELECT MIN(TransactionOccurredWhen), MAX(TransactionOccurredWhen)
FROM Warehouse.StockItemTransactions
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the range of Quantity, by type, and the total; issues -360 to -1, -8950628 in all,
-- receipts 6 to 67368, 139418326 in all, adjustments -5 to 4, -18 in all.
SELECT TransactionTypeID, MIN(Quantity), MAX(Quantity), SUM(Quantity)
FROM Warehouse.StockItemTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;

-- how many quantities have a fractional part, though the column allows three decimals; 0.
SELECT COUNT(*)
FROM Warehouse.StockItemTransactions
WHERE Quantity <> ROUND(Quantity, 0)
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the most rows; 4 with 15224, 16 with 14688, 6 with 14396.
SELECT TOP (3) LastEditedBy, COUNT(*) AS transactions
FROM Warehouse.StockItemTransactions
GROUP BY LastEditedBy
ORDER BY transactions DESC, LastEditedBy ASC
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- the range of LastEditedWhen; 2013-01-01 12:00 to 2016-05-31 12:00.
SELECT MIN(LastEditedWhen), MAX(LastEditedWhen)
FROM Warehouse.StockItemTransactions
;

-- how many transactions were last edited other than when they occurred; 0.
SELECT COUNT(*)
FROM Warehouse.StockItemTransactions
WHERE LastEditedWhen <> TransactionOccurredWhen
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many invoice lines have no stock item transaction; 0, every invoice line has its issue.
SELECT COUNT(*)
FROM Sales.InvoiceLines l
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItemTransactions t
                  WHERE t.InvoiceID = l.InvoiceID
                    AND t.StockItemID = l.StockItemID)
;

