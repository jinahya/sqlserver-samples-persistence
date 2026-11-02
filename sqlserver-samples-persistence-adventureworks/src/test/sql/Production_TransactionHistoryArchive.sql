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
-- Profiling queries on Production.TransactionHistoryArchive, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_TransactionHistoryArchive.sql
--


-- ------------------------------------------------------------------------------------------------------- TransactionID
-- how many rows, and the smallest and the largest TransactionID; 89253 transactions, 1 to 89253.
SELECT COUNT(*), MIN(TransactionID), MAX(TransactionID)
FROM Production.TransactionHistoryArchive
;

-- the gaps in TransactionID, below the largest: how many values are missing; 0: TransactionID runs from 1 without a
-- gap.
SELECT MAX(TransactionID) - MIN(TransactionID) + 1 - COUNT(*) AS missing
FROM Production.TransactionHistoryArchive
;


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- the products the transactions are of; 497 products, 1 to 999.
SELECT COUNT(DISTINCT ProductID), MIN(ProductID), MAX(ProductID)
FROM Production.TransactionHistoryArchive
;


-- ---------------------------------------------------------------------------------------------------- ReferenceOrderID
-- the orders referred to, by TransactionType; P 416 purchase orders, 1 to 416; S 9791 sales orders, 43659 to 53449; W
-- 41589 work orders, 1 to 41589.
SELECT TransactionType, COUNT(DISTINCT ReferenceOrderID) AS orders, MIN(ReferenceOrderID), MAX(ReferenceOrderID)
FROM Production.TransactionHistoryArchive
GROUP BY TransactionType
ORDER BY TransactionType ASC
;


-- ------------------------------------------------------------------------------------------------ ReferenceOrderLineID
-- the order lines referred to, by TransactionType; P 1 to 26, S 1 to 72, and W 0 only, a work order having no lines.
SELECT TransactionType, MIN(ReferenceOrderLineID), MAX(ReferenceOrderLineID)
FROM Production.TransactionHistoryArchive
GROUP BY TransactionType
ORDER BY TransactionType ASC
;

-- an order line referred to more than once, within a type; how many.
-- 0: no order line is referred to twice.
SELECT COUNT(*) AS lines
FROM (SELECT TransactionType, ReferenceOrderID, ReferenceOrderLineID
      FROM Production.TransactionHistoryArchive
      GROUP BY TransactionType, ReferenceOrderID, ReferenceOrderLineID
      HAVING COUNT(*) > 1) x
;


-- ----------------------------------------------------------------------------------------------------- TransactionDate
-- the range of TransactionDate; 2022-04-15 00:00:00.000 to 2024-07-29 00:00:00.000.
SELECT MIN(TransactionDate), MAX(TransactionDate)
FROM Production.TransactionHistoryArchive
;

-- a TransactionDate with a time of day.
-- no rows: every TransactionDate is midnight.
SELECT TOP (3) TransactionID, TransactionDate
FROM Production.TransactionHistoryArchive
WHERE TransactionDate <> CAST(CAST(TransactionDate AS date) AS datetime)
ORDER BY TransactionID ASC
;


-- ----------------------------------------------------------------------------------------------------- TransactionType
-- the types CK_TransactionHistoryArchive_TransactionType allows, P, S or W; P 922, S 46742, W 41589.
SELECT TransactionType, COUNT(*) AS transactions
FROM Production.TransactionHistoryArchive
GROUP BY TransactionType
ORDER BY TransactionType ASC
;

-- a TransactionType in lower case; the check constraint compares UPPER, so it lets one in.
-- no rows: every type is upper case.
SELECT TOP (3) TransactionID, TransactionType
FROM Production.TransactionHistoryArchive
WHERE TransactionType COLLATE Latin1_General_BIN <> UPPER(TransactionType)
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the smallest and the largest Quantity, by TransactionType; P 3 to 1250, S 1 to 44, W 1 to 39570.
SELECT TransactionType, MIN(Quantity), MAX(Quantity)
FROM Production.TransactionHistoryArchive
GROUP BY TransactionType
ORDER BY TransactionType ASC
;


-- ---------------------------------------------------------------------------------------------------------- ActualCost
-- the smallest and the largest ActualCost, and how many are zero, by TransactionType; P 0.2100 to 82.8345 and S 1.3740
-- to 3578.2700, none zero; W 0.0000 for all 41589.
SELECT TransactionType, MIN(ActualCost), MAX(ActualCost), SUM(CASE WHEN ActualCost = 0 THEN 1 ELSE 0 END) AS zero
FROM Production.TransactionHistoryArchive
GROUP BY TransactionType
ORDER BY TransactionType ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2022-04-15 00:00:00.000 to 2024-07-29 00:00:00.000, the same range as
-- TransactionDate.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.TransactionHistoryArchive
;

-- a row modified before its transaction; how many.
-- 0.
SELECT COUNT(*) AS earlier
FROM Production.TransactionHistoryArchive
WHERE ModifiedDate < TransactionDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the archive has no foreign key on ProductID; a product which does not exist.
-- 0: every product exists.
SELECT COUNT(*) AS orphans
FROM Production.TransactionHistoryArchive a
WHERE NOT EXISTS (SELECT 1
                  FROM Production.Product p
                  WHERE p.ProductID = a.ProductID)
;

-- the latest archived transaction against the earliest current one; 2024-07-29 00:00:00.000 and 2024-07-30
-- 00:00:00.000; the archive ends the day before the history starts.
SELECT (SELECT MAX(TransactionDate) FROM Production.TransactionHistoryArchive) AS archivedUntil,
       (SELECT MIN(TransactionDate) FROM Production.TransactionHistory) AS currentFrom
;

-- a sale (S) whose ReferenceOrderLineID names no SalesOrderDetailID of its order.
-- 46729 of the 46742; the line is not a SalesOrderDetailID, so the checks below match on the product.
SELECT COUNT(*) AS unmatched
FROM Production.TransactionHistoryArchive h
WHERE h.TransactionType = 'S'
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderDetail d
                  WHERE d.SalesOrderID = h.ReferenceOrderID
                    AND d.SalesOrderDetailID = h.ReferenceOrderLineID)
;

-- a sale (S) whose order has no line of its product.
-- no rows: every sale matches a line of its order by the product.
SELECT COUNT(*) AS orphans
FROM Production.TransactionHistoryArchive h
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
FROM Production.TransactionHistoryArchive h
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
FROM Production.TransactionHistoryArchive h
WHERE h.TransactionType = 'W'
  AND NOT EXISTS (SELECT 1
                  FROM Production.WorkOrder w
                  WHERE w.WorkOrderID = h.ReferenceOrderID
                    AND w.ProductID = h.ProductID)
HAVING COUNT(*) > 0
;

