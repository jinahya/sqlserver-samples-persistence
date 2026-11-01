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
-- Profiling queries on Purchasing.PurchaseOrderLines, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Purchasing_PurchaseOrderLines.sql
--


-- ------------------------------------------------------------------------------------------------- PurchaseOrderLineID
-- how many rows, and the smallest and the largest PurchaseOrderLineID; 8367 lines, 1 to 8367.
SELECT COUNT(*), MIN(PurchaseOrderLineID), MAX(PurchaseOrderLineID)
FROM Purchasing.PurchaseOrderLines
;

-- a PurchaseOrderLineID whose next value is missing, below the largest.
-- no rows: PurchaseOrderLineID runs from 1 without a gap.
SELECT a.PurchaseOrderLineID + 1 AS missing_from
FROM Purchasing.PurchaseOrderLines a
WHERE a.PurchaseOrderLineID < (SELECT MAX(PurchaseOrderLineID) FROM Purchasing.PurchaseOrderLines)
  AND NOT EXISTS (SELECT 1
                  FROM Purchasing.PurchaseOrderLines b
                  WHERE b.PurchaseOrderLineID = a.PurchaseOrderLineID + 1)
;


-- ----------------------------------------------------------------------------------------------------- PurchaseOrderID
-- how many orders the lines belong to; all 2074.
SELECT COUNT(DISTINCT PurchaseOrderID)
FROM Purchasing.PurchaseOrderLines
;


-- --------------------------------------------------------------------------------------------------------- StockItemID
-- how many stock items the lines order; all 227.
SELECT COUNT(DISTINCT StockItemID)
FROM Purchasing.PurchaseOrderLines
;

-- the schema has no unique key on (PurchaseOrderID, StockItemID); a stock item more than one line of an order has.
-- no rows: an order has a single line per stock item.
SELECT PurchaseOrderID, StockItemID, COUNT(*) AS lines
FROM Purchasing.PurchaseOrderLines
GROUP BY PurchaseOrderID, StockItemID
HAVING COUNT(*) > 1
;

-- a line ordering a stock item of another supplier than the order's.
-- no rows: every stock item is ordered from its own supplier.
SELECT l.PurchaseOrderLineID, o.SupplierID, i.SupplierID AS item_supplier
FROM Purchasing.PurchaseOrderLines l
         JOIN Purchasing.PurchaseOrders o ON o.PurchaseOrderID = l.PurchaseOrderID
         JOIN Warehouse.StockItems i ON i.StockItemID = l.StockItemID
WHERE i.SupplierID <> o.SupplierID
;


-- ------------------------------------------------------------------------------------------------------- OrderedOuters
-- the smallest and the largest OrderedOuters; 1 to 3768.
SELECT MIN(OrderedOuters), MAX(OrderedOuters)
FROM Purchasing.PurchaseOrderLines
;


-- --------------------------------------------------------------------------------------------------------- Description
-- the shortest and the longest Description; 20 and 85 characters, of the 100 the column allows.
SELECT MIN(LEN(Description)), MAX(LEN(Description))
FROM Purchasing.PurchaseOrderLines
;

-- a Description which is not the StockItemName of its stock item.
-- no rows: every line is described by the name of its item.
SELECT l.PurchaseOrderLineID, l.Description, i.StockItemName
FROM Purchasing.PurchaseOrderLines l
         JOIN Warehouse.StockItems i ON i.StockItemID = l.StockItemID
WHERE l.Description <> i.StockItemName
;


-- ------------------------------------------------------------------------------------------------------ ReceivedOuters
-- how the received outers compare with the ordered ones; 8358 lines received in full, 9 short, none beyond.
SELECT SUM(CASE WHEN ReceivedOuters = OrderedOuters THEN 1 ELSE 0 END) AS in_full,
       SUM(CASE WHEN ReceivedOuters < OrderedOuters THEN 1 ELSE 0 END) AS short,
       SUM(CASE WHEN ReceivedOuters > OrderedOuters THEN 1 ELSE 0 END) AS beyond
FROM Purchasing.PurchaseOrderLines
;

-- the lines received short; the 9 lines of orders 2073 and 2074, nothing received yet.
SELECT PurchaseOrderID, COUNT(*) AS lines, SUM(ReceivedOuters) AS received
FROM Purchasing.PurchaseOrderLines
WHERE ReceivedOuters < OrderedOuters
GROUP BY PurchaseOrderID
ORDER BY PurchaseOrderID ASC
;


-- ------------------------------------------------------------------------------------------------------- PackageTypeID
-- the package types the lines order in; Carton (6) 5773, Each (7) 2585, Packet (9) 9.
SELECT l.PackageTypeID, p.PackageTypeName, COUNT(*) AS lines
FROM Purchasing.PurchaseOrderLines l
         JOIN Warehouse.PackageTypes p ON p.PackageTypeID = l.PackageTypeID
GROUP BY l.PackageTypeID, p.PackageTypeName
ORDER BY l.PackageTypeID ASC
;

-- a line whose PackageTypeID is not the OuterPackageID of its stock item.
-- no rows: every line orders in the outer package of its item.
SELECT l.PurchaseOrderLineID, l.PackageTypeID, i.OuterPackageID
FROM Purchasing.PurchaseOrderLines l
         JOIN Warehouse.StockItems i ON i.StockItemID = l.StockItemID
WHERE l.PackageTypeID <> i.OuterPackageID
;


-- ------------------------------------------------------------------------------------------- ExpectedUnitPricePerOuter
-- ExpectedUnitPricePerOuter is nullable; how many lines have none, and the range. None; 4.50 to 1140.00.
SELECT COUNT(*) - COUNT(ExpectedUnitPricePerOuter) AS without,
       MIN(ExpectedUnitPricePerOuter), MAX(ExpectedUnitPricePerOuter)
FROM Purchasing.PurchaseOrderLines
;

-- a stock item ordered at more than one expected price.
-- no rows: each stock item has a single price across its lines.
SELECT StockItemID, COUNT(DISTINCT ExpectedUnitPricePerOuter) AS prices
FROM Purchasing.PurchaseOrderLines
GROUP BY StockItemID
HAVING COUNT(DISTINCT ExpectedUnitPricePerOuter) > 1
;


-- ----------------------------------------------------------------------------------------------------- LastReceiptDate
-- LastReceiptDate is nullable; how many lines have none, and the range. 9, the lines not received; 2013-01-02 to
-- 2016-05-31.
SELECT COUNT(*) - COUNT(LastReceiptDate) AS without, MIN(LastReceiptDate), MAX(LastReceiptDate)
FROM Purchasing.PurchaseOrderLines
;

-- how many days after the order the line was last received; 1 to 3, a day after for 6215 lines, and 9 not yet.
SELECT DATEDIFF(day, o.OrderDate, l.LastReceiptDate) AS days, COUNT(*) AS lines
FROM Purchasing.PurchaseOrderLines l
         JOIN Purchasing.PurchaseOrders o ON o.PurchaseOrderID = l.PurchaseOrderID
GROUP BY DATEDIFF(day, o.OrderDate, l.LastReceiptDate)
ORDER BY days ASC
;


-- ------------------------------------------------------------------------------------------------ IsOrderLineFinalized
-- how many lines are finalized; 8358, all but the 9 not received.
SELECT IsOrderLineFinalized, COUNT(*) AS lines, SUM(CASE WHEN LastReceiptDate IS NULL THEN 1 ELSE 0 END) AS unreceived
FROM Purchasing.PurchaseOrderLines
GROUP BY IsOrderLineFinalized
ORDER BY IsOrderLineFinalized ASC
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- a line last edited by another person, or at another time, than its order.
-- 9 rows: the lines of orders 2073 and 2074, edited by person 17 where the orders were by 1, at the same time.
SELECT l.PurchaseOrderLineID, l.PurchaseOrderID, l.LastEditedBy, o.LastEditedBy AS order_edited_by
FROM Purchasing.PurchaseOrderLines l
         JOIN Purchasing.PurchaseOrders o ON o.PurchaseOrderID = l.PurchaseOrderID
WHERE l.LastEditedBy <> o.LastEditedBy
   OR l.LastEditedWhen <> o.LastEditedWhen
ORDER BY l.PurchaseOrderLineID ASC
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- a received line last edited on another day than its LastReceiptDate.
-- no rows: the receipt is the last edit.
SELECT PurchaseOrderLineID, LastReceiptDate, LastEditedWhen
FROM Purchasing.PurchaseOrderLines
WHERE LastReceiptDate <> CAST(LastEditedWhen AS date)
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a received line with no stock receipt transaction of its item and order.
-- no rows: each of the 8358 received lines has one.
SELECT l.PurchaseOrderLineID
FROM Purchasing.PurchaseOrderLines l
WHERE l.ReceivedOuters > 0
  AND NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItemTransactions t
                  WHERE t.PurchaseOrderID = l.PurchaseOrderID
                    AND t.StockItemID = l.StockItemID)
;

-- a stock receipt whose Quantity is not the received outers times the QuantityPerOuter of the item.
-- no rows: every receipt matches its line.
SELECT l.PurchaseOrderLineID, l.ReceivedOuters, i.QuantityPerOuter, t.Quantity
FROM Purchasing.PurchaseOrderLines l
         JOIN Warehouse.StockItems i ON i.StockItemID = l.StockItemID
         JOIN Warehouse.StockItemTransactions t
              ON t.PurchaseOrderID = l.PurchaseOrderID AND t.StockItemID = l.StockItemID
WHERE t.Quantity <> l.ReceivedOuters * i.QuantityPerOuter
;

