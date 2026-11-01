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
-- Profiling queries on Sales.CustomerTransactions, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_CustomerTransactions.sql
--


-- ----------------------------------------------------------------------------------------------- CustomerTransactionID
-- how many rows, and the smallest and the largest CustomerTransactionID; 97147 transactions, 2 to 336252,
-- with the gaps the shared sequence leaves.
SELECT COUNT(*), MIN(CustomerTransactionID), MAX(CustomerTransactionID)
FROM Sales.CustomerTransactions
;

-- CustomerTransactionID comes from Sequences.TransactionID, which the supplier and the stock item transactions draw
-- from too; an id also taken by one of those.
-- no rows: the sequence hands each id to one table only.
SELECT t.CustomerTransactionID
FROM Sales.CustomerTransactions t
WHERE EXISTS (SELECT 1
              FROM Purchasing.SupplierTransactions s
              WHERE s.SupplierTransactionID = t.CustomerTransactionID)
   OR EXISTS (SELECT 1
              FROM Warehouse.StockItemTransactions w
              WHERE w.StockItemTransactionID = t.CustomerTransactionID)
;


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many customers have transactions; 263, the customers billed to themselves.
SELECT COUNT(DISTINCT CustomerID)
FROM Sales.CustomerTransactions
;

-- the customers with the most transactions; 1 with 23384, 401 with 23233, 831 with 283.
SELECT TOP (3) CustomerID, COUNT(*) AS transactions
FROM Sales.CustomerTransactions
GROUP BY CustomerID
ORDER BY transactions DESC, CustomerID ASC
;


-- --------------------------------------------------------------------------------------------------- TransactionTypeID
-- the transactions of each type; 70510 Customer Invoice (1), 26637 Customer Payment Received (3).
SELECT t.TransactionTypeID, y.TransactionTypeName, COUNT(*) AS transactions
FROM Sales.CustomerTransactions t
         JOIN Application.TransactionTypes y ON y.TransactionTypeID = t.TransactionTypeID
GROUP BY t.TransactionTypeID, y.TransactionTypeName
ORDER BY t.TransactionTypeID ASC
;


-- ----------------------------------------------------------------------------------------------------------- InvoiceID
-- the transactions with an invoice, and those without, by type; every invoice transaction has one, no payment does.
SELECT TransactionTypeID, COUNT(InvoiceID) AS with_invoice, COUNT(*) - COUNT(InvoiceID) AS without_invoice
FROM Sales.CustomerTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;

-- InvoiceID has no unique key; an invoice with more than one transaction.
-- no rows: an invoice has one transaction at most.
SELECT InvoiceID, COUNT(*) AS transactions
FROM Sales.CustomerTransactions
WHERE InvoiceID IS NOT NULL
GROUP BY InvoiceID
HAVING COUNT(*) > 1
;

-- a transaction whose customer is not the one billed on its invoice.
-- no rows: the transaction belongs to the customer the invoice bills.
SELECT t.CustomerTransactionID, t.CustomerID, i.CustomerID AS invoice_customer, i.BillToCustomerID
FROM Sales.CustomerTransactions t
         JOIN Sales.Invoices i ON i.InvoiceID = t.InvoiceID
WHERE t.CustomerID <> i.BillToCustomerID
;


-- ----------------------------------------------------------------------------------------------------- PaymentMethodID
-- the transactions of each payment method, NULL for none; invoices have none, every payment is EFT (4).
SELECT TransactionTypeID, PaymentMethodID, COUNT(*) AS transactions
FROM Sales.CustomerTransactions
GROUP BY TransactionTypeID, PaymentMethodID
ORDER BY TransactionTypeID ASC, PaymentMethodID ASC
;


-- ----------------------------------------------------------------------------------------------------- TransactionDate
-- the range of the transaction dates; 2013-01-01 to 2016-05-31.
SELECT MIN(TransactionDate), MAX(TransactionDate)
FROM Sales.CustomerTransactions
;

-- how many transactions are dated other than their invoice; 0.
SELECT COUNT(*)
FROM Sales.CustomerTransactions t
         JOIN Sales.Invoices i ON i.InvoiceID = t.InvoiceID
WHERE t.TransactionDate <> i.InvoiceDate
;


-- -------------------------------------------------------------------------------------------------- AmountExcludingTax
-- the range of AmountExcludingTax, by type; 4.80 to 32026.00 for invoices, .00 for every payment.
SELECT TransactionTypeID, MIN(AmountExcludingTax), MAX(AmountExcludingTax)
FROM Sales.CustomerTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;


-- ----------------------------------------------------------------------------------------------------------- TaxAmount
-- the range of TaxAmount, by type; .72 to 4803.90 for invoices, .00 for every payment.
SELECT TransactionTypeID, MIN(TaxAmount), MAX(TaxAmount)
FROM Sales.CustomerTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;


-- --------------------------------------------------------------------------------------------------- TransactionAmount
-- how many TransactionAmount are not AmountExcludingTax plus TaxAmount; 26637, a finding: every payment, which
-- carries its amount in TransactionAmount alone and .00 in the other two.
SELECT COUNT(*)
FROM Sales.CustomerTransactions
WHERE TransactionAmount <> AmountExcludingTax + TaxAmount
;

-- the range of TransactionAmount, by type; invoices are positive, payments negative; 5.52 to
-- 36829.90, and -169387.42 to -13.80.
SELECT TransactionTypeID, MIN(TransactionAmount), MAX(TransactionAmount)
FROM Sales.CustomerTransactions
GROUP BY TransactionTypeID
ORDER BY TransactionTypeID ASC
;

-- how many invoice transactions differ from the total of their invoice lines' ExtendedPrice; 0.
SELECT COUNT(*)
FROM Sales.CustomerTransactions t
WHERE t.InvoiceID IS NOT NULL
  AND t.TransactionAmount <> (SELECT SUM(l.ExtendedPrice)
                              FROM Sales.InvoiceLines l
                              WHERE l.InvoiceID = t.InvoiceID)
;


-- -------------------------------------------------------------------------------------------------- OutstandingBalance
-- the transactions still owing, and those settled; 84 owe 267011.44 in all, 97063 are settled.
SELECT CASE WHEN OutstandingBalance = 0 THEN 'settled' ELSE 'owing' END AS state, COUNT(*) AS transactions,
       SUM(OutstandingBalance) AS outstanding
FROM Sales.CustomerTransactions
GROUP BY CASE WHEN OutstandingBalance = 0 THEN 'settled' ELSE 'owing' END
;


-- ---------------------------------------------------------------------------------------------------- FinalizationDate
-- the transactions with no FinalizationDate, and the range of the others; 84 open, the others
-- finalized from 2013-01-02 to 2016-05-31.
SELECT COUNT(*) - COUNT(FinalizationDate) AS open_transactions, MIN(FinalizationDate), MAX(FinalizationDate)
FROM Sales.CustomerTransactions
;

-- a transaction finalized before it was made.
-- no rows: no FinalizationDate is earlier than its TransactionDate.
SELECT CustomerTransactionID, TransactionDate, FinalizationDate
FROM Sales.CustomerTransactions
WHERE FinalizationDate < TransactionDate
;

-- a transaction finalized while still owing, or open with nothing owing; neither: the 84 open ones
-- are the 84 owing, the 97063 finalized are settled.
SELECT CASE WHEN FinalizationDate IS NULL THEN 'open' ELSE 'finalized' END AS state,
       CASE WHEN OutstandingBalance = 0 THEN 'settled' ELSE 'owing' END AS balance, COUNT(*) AS transactions
FROM Sales.CustomerTransactions
GROUP BY CASE WHEN FinalizationDate IS NULL THEN 'open' ELSE 'finalized' END,
         CASE WHEN OutstandingBalance = 0 THEN 'settled' ELSE 'owing' END
;


-- --------------------------------------------------------------------------------------------------------- IsFinalized
-- IsFinalized is computed from FinalizationDate; the values it takes; 0 for the 84 open, 1 for
-- the 97063 finalized.
SELECT IsFinalized, COUNT(*) AS transactions
FROM Sales.CustomerTransactions
GROUP BY IsFinalized
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the most rows; 2 with 6127, 8 with 6100, 10 with 5941.
SELECT TOP (3) LastEditedBy, COUNT(*) AS transactions
FROM Sales.CustomerTransactions
GROUP BY LastEditedBy
ORDER BY transactions DESC, LastEditedBy ASC
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- the range of LastEditedWhen; 2013-01-02 11:30 to 2016-05-31 12:00.
SELECT MIN(LastEditedWhen), MAX(LastEditedWhen)
FROM Sales.CustomerTransactions
;

-- how many transactions were last edited before their date; 0.
SELECT COUNT(*)
FROM Sales.CustomerTransactions
WHERE LastEditedWhen < CAST(TransactionDate AS datetime2)
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many invoices have no customer transaction; 0, every invoice has one.
SELECT COUNT(*)
FROM Sales.Invoices i
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.CustomerTransactions t
                  WHERE t.InvoiceID = i.InvoiceID)
;

-- the balance owed per customer, the largest first; 401 owes 97053.58, 1 56435.84, 995 13212.12.
SELECT TOP (3) CustomerID, SUM(OutstandingBalance) AS outstanding
FROM Sales.CustomerTransactions
GROUP BY CustomerID
ORDER BY outstanding DESC, CustomerID ASC
;

