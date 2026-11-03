-- -
-- #%L
-- sqlserver-samples-persistence-adventureworks
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
-- Profiling queries on Production.TransactionHistory, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_TransactionHistory.sql
--


-- ------------------------------------------------------------------------------------------------------- TransactionID
-- how many rows, and the smallest and the largest TransactionID; 113443 transactions, 100000 to 213442.
SELECT COUNT(*), MIN(TransactionID), MAX(TransactionID)
FROM Production.TransactionHistory
;

-- the gaps in TransactionID, below the largest: how many values are missing; 0: TransactionID runs from 100000 without
-- a gap.
SELECT MAX(TransactionID) - MIN(TransactionID) + 1 - COUNT(*) AS missing
FROM Production.TransactionHistory
;


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- the products the transactions are of; 441 products, 1 to 999.
SELECT COUNT(DISTINCT ProductID), MIN(ProductID), MAX(ProductID)
FROM Production.TransactionHistory
;


-- ---------------------------------------------------------------------------------------------------- ReferenceOrderID
-- the orders referred to, by TransactionType; P 3584 purchase orders, 417 to 4000; S 21674 sales orders, 53450 to
-- 75123; W 31002 work orders, 41590 to 72591.
SELECT TransactionType,
       COUNT(DISTINCT ReferenceOrderID) AS orders,
       MIN(ReferenceOrderID),
       MAX(ReferenceOrderID)
FROM Production.TransactionHistory
GROUP BY TransactionType
ORDER BY TransactionType ASC
;


-- ------------------------------------------------------------------------------------------------ ReferenceOrderLineID
-- the order lines referred to, by TransactionType; P 1 to 30, S 1 to 71, and W 0 only, a work order having no lines.
SELECT TransactionType, MIN(ReferenceOrderLineID), MAX(ReferenceOrderLineID)
FROM Production.TransactionHistory
GROUP BY TransactionType
ORDER BY TransactionType ASC
;

-- an order line referred to more than once, within a type; how many.
-- 0: no order line is referred to twice.
SELECT COUNT(*) AS lines
FROM (SELECT TransactionType, ReferenceOrderID, ReferenceOrderLineID
      FROM Production.TransactionHistory
      GROUP BY TransactionType, ReferenceOrderID, ReferenceOrderLineID
      HAVING COUNT(*) > 1) x
;


-- ----------------------------------------------------------------------------------------------------- TransactionDate
-- the range of TransactionDate; 2024-07-30 00:00:00.000 to 2025-08-02 00:00:00.000.
SELECT MIN(TransactionDate), MAX(TransactionDate)
FROM Production.TransactionHistory
;

-- a TransactionDate with a time of day.
-- no rows: every TransactionDate is midnight.
SELECT TOP(3) TransactionID, TransactionDate
FROM Production.TransactionHistory
WHERE TransactionDate <> CAST(CAST(TransactionDate AS date) AS datetime)
ORDER BY TransactionID ASC
;


-- ----------------------------------------------------------------------------------------------------- TransactionType
-- the types CK_TransactionHistory_TransactionType allows, P, S or W; P 7866, S 74575, W 31002.
SELECT TransactionType, COUNT(*) AS transactions
FROM Production.TransactionHistory
GROUP BY TransactionType
ORDER BY TransactionType ASC
;

-- a TransactionType in lower case; the check constraint compares UPPER, so it lets one in.
-- no rows: every type is upper case.
SELECT TOP(3) TransactionID, TransactionType
FROM Production.TransactionHistory
WHERE TransactionType COLLATE Latin1_General_BIN <> UPPER(TransactionType)
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the smallest and the largest Quantity, by TransactionType; P 3 to 1250, S 1 to 41, W 1 to 39270.
SELECT TransactionType, MIN(Quantity), MAX(Quantity)
FROM Production.TransactionHistory
GROUP BY TransactionType
ORDER BY TransactionType ASC
;


-- ---------------------------------------------------------------------------------------------------------- ActualCost
-- the smallest and the largest ActualCost, and how many are zero, by TransactionType; P 0.2100 to 82.8345 and S 1.3740
-- to 2443.3500, none zero; W 0.0000 for all 31002.
SELECT TransactionType,
       MIN(ActualCost),
       MAX(ActualCost),
       SUM(CASE WHEN ActualCost = 0 THEN 1 ELSE 0 END) AS zero
FROM Production.TransactionHistory
GROUP BY TransactionType
ORDER BY TransactionType ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2024-07-30 00:00:00.000 to 2025-08-02 00:00:00.000, the same range as
-- TransactionDate.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.TransactionHistory
;

-- a row modified before its transaction; how many.
-- 0.
SELECT COUNT(*) AS earlier
FROM Production.TransactionHistory
WHERE ModifiedDate < TransactionDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a transaction also in the archive, by TransactionID.
-- no rows: the archive holds 1 to 89253, the history starts at 100000.
SELECT TOP(3) h.TransactionID
FROM Production.TransactionHistory h
         JOIN Production.TransactionHistoryArchive a ON a.TransactionID = h.TransactionID
;

-- a sale (S) whose ReferenceOrderLineID names no SalesOrderDetailID of its order.
-- all 74575; the line is not a SalesOrderDetailID, so the checks below match on the product.
SELECT COUNT(*) AS unmatched
FROM Production.TransactionHistory h
WHERE h.TransactionType = 'S'
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderDetail d
                  WHERE d.SalesOrderID = h.ReferenceOrderID
                    AND d.SalesOrderDetailID = h.ReferenceOrderLineID)
;

-- a sale (S) whose order has no line of its product.
-- no rows: every sale matches a line of its order by the product.
SELECT COUNT(*) AS orphans
FROM Production.TransactionHistory h
WHERE h.TransactionType = 'S'
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderDetail d
                  WHERE d.SalesOrderID = h.ReferenceOrderID
                    AND d.ProductID = h.ProductID)
HAVING COUNT(*) > 0
;

-- a purchase (P) whose order has no line of its product.
-- no rows: every purchase matches a line of its order by the product.
SELECT COUNT(*) AS orphans
FROM Production.TransactionHistory h
WHERE h.TransactionType = 'P'
  AND NOT EXISTS (SELECT 1
                  FROM Purchasing.PurchaseOrderDetail d
                  WHERE d.PurchaseOrderID = h.ReferenceOrderID
                    AND d.ProductID = h.ProductID)
HAVING COUNT(*) > 0
;

-- a work order (W) which does not exist, or is of another product.
-- no rows: every W transaction is its work order's, of the same product.
SELECT COUNT(*) AS orphans
FROM Production.TransactionHistory h
WHERE h.TransactionType = 'W'
  AND NOT EXISTS (SELECT 1
                  FROM Production.WorkOrder w
                  WHERE w.WorkOrderID = h.ReferenceOrderID
                    AND w.ProductID = h.ProductID)
HAVING COUNT(*) > 0
;

