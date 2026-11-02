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
-- Profiling queries on Sales.InvoiceLines, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_InvoiceLines.sql
--


-- ------------------------------------------------------------------------------------------------------- InvoiceLineID
-- how many rows, and the smallest and the largest InvoiceLineID; 228265 lines, 1 to 228265.
SELECT COUNT(*), MIN(InvoiceLineID), MAX(InvoiceLineID)
FROM Sales.InvoiceLines
;

-- an InvoiceLineID whose next value is missing, below the largest.
-- no rows: InvoiceLineID runs from 1 without a gap.
SELECT a.InvoiceLineID + 1 AS missing_from
FROM Sales.InvoiceLines a
WHERE a.InvoiceLineID < (SELECT MAX(InvoiceLineID) FROM Sales.InvoiceLines)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.InvoiceLines b
                  WHERE b.InvoiceLineID = a.InvoiceLineID + 1)
;


-- ----------------------------------------------------------------------------------------------------------- InvoiceID
-- the fewest and the most lines an invoice has, and the average; 1 to 5, 3.24 on average.
SELECT MIN(lines), MAX(lines), AVG(lines * 1.0)
FROM (SELECT InvoiceID, COUNT(*) AS lines
      FROM Sales.InvoiceLines
      GROUP BY InvoiceID) i
;


-- --------------------------------------------------------------------------------------------------------- StockItemID
-- how many stock items are invoiced, of how many there are; all 227.
SELECT COUNT(DISTINCT l.StockItemID), (SELECT COUNT(*) FROM Warehouse.StockItems)
FROM Sales.InvoiceLines l
;

-- how many invoices have the same stock item on more than one line; 0.
SELECT COUNT(*)
FROM (SELECT InvoiceID, StockItemID
      FROM Sales.InvoiceLines
      GROUP BY InvoiceID, StockItemID
      HAVING COUNT(*) > 1) d
;

-- how many invoice lines match no line of the invoice's order by stock item and quantity; 0, an invoice line
-- carries the PickedQuantity of its order line.
SELECT COUNT(*)
FROM Sales.InvoiceLines l
         JOIN Sales.Invoices i ON i.InvoiceID = l.InvoiceID
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.OrderLines o
                  WHERE o.OrderID = i.OrderID
                    AND o.StockItemID = l.StockItemID
                    AND o.PickedQuantity = l.Quantity)
;


-- --------------------------------------------------------------------------------------------------------- Description
-- the shortest and the longest Description; 20 and 85 characters, of the 100 the column allows.
SELECT MIN(LEN(Description)), MAX(LEN(Description))
FROM Sales.InvoiceLines
;

-- how many Description are blank, or carry leading or trailing spaces; 0. LEN and = ignore trailing
-- spaces, DATALENGTH does not.
SELECT COUNT(*)
FROM Sales.InvoiceLines
WHERE TRIM(Description) = ''
   OR DATALENGTH(Description) <> DATALENGTH(TRIM(Description))
;

-- how many lines have a Description other than the name of their stock item; 0.
SELECT COUNT(*)
FROM Sales.InvoiceLines l
         JOIN Warehouse.StockItems s ON s.StockItemID = l.StockItemID
WHERE l.Description <> s.StockItemName
;


-- ------------------------------------------------------------------------------------------------------- PackageTypeID
-- the lines of each package type; 217808 Each, 5209 Packet, 4212 Pair, 1036 Bag.
SELECT l.PackageTypeID, p.PackageTypeName, COUNT(*) AS lines
FROM Sales.InvoiceLines l
         JOIN Warehouse.PackageTypes p ON p.PackageTypeID = l.PackageTypeID
GROUP BY l.PackageTypeID, p.PackageTypeName
ORDER BY l.PackageTypeID ASC
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the smallest and the largest Quantity; 1 and 360.
SELECT MIN(Quantity), MAX(Quantity)
FROM Sales.InvoiceLines
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- the lines with no UnitPrice, and the range of the others; none is NULL, .66 to 1899.00.
SELECT COUNT(*) - COUNT(UnitPrice), MIN(UnitPrice), MAX(UnitPrice)
FROM Sales.InvoiceLines
;


-- ------------------------------------------------------------------------------------------------------------- TaxRate
-- the distinct tax rates; 10.000 for 1036 lines, 15.000 for 227229.
SELECT TaxRate, COUNT(*) AS lines
FROM Sales.InvoiceLines
GROUP BY TaxRate
ORDER BY TaxRate ASC
;


-- ----------------------------------------------------------------------------------------------------------- TaxAmount
-- how many lines have a TaxAmount other than Quantity * UnitPrice * TaxRate / 100, rounded to cents; 0.
SELECT COUNT(*)
FROM Sales.InvoiceLines
WHERE TaxAmount <> ROUND(Quantity * UnitPrice * TaxRate / 100, 2)
;


-- ---------------------------------------------------------------------------------------------------------- LineProfit
-- the range of LineProfit; -645.00 to 9200.00.
SELECT MIN(LineProfit), MAX(LineProfit)
FROM Sales.InvoiceLines
;

-- how many lines are sold at a loss; 4626.
SELECT COUNT(*)
FROM Sales.InvoiceLines
WHERE LineProfit < 0
;


-- ------------------------------------------------------------------------------------------------------- ExtendedPrice
-- how many lines have an ExtendedPrice other than Quantity * UnitPrice plus TaxAmount; 0.
SELECT COUNT(*)
FROM Sales.InvoiceLines
WHERE ExtendedPrice <> Quantity * UnitPrice + TaxAmount
;

-- the range of ExtendedPrice; 2.88 to 21838.50.
SELECT MIN(ExtendedPrice), MAX(ExtendedPrice)
FROM Sales.InvoiceLines
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the most rows; 4 with 14674, 16 with 14302, 3 with 13796.
SELECT TOP (3) LastEditedBy, COUNT(*) AS lines
FROM Sales.InvoiceLines
GROUP BY LastEditedBy
ORDER BY lines DESC, LastEditedBy ASC
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- the range of LastEditedWhen; 2013-01-01 12:00 to 2016-05-31 12:00.
SELECT MIN(LastEditedWhen), MAX(LastEditedWhen)
FROM Sales.InvoiceLines
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the stock items with the most profit; 161 with 5293680.00, 215 with 4439391.00, 173 with 3526400.00.
SELECT TOP (3) StockItemID, SUM(LineProfit) AS profit
FROM Sales.InvoiceLines
GROUP BY StockItemID
ORDER BY profit DESC, StockItemID ASC
;

