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
-- Profiling queries on Purchasing.PurchaseOrderDetail, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Purchasing_PurchaseOrderDetail.sql
--


-- ----------------------------------------------------------------------------------------------------- PurchaseOrderID
-- how many rows, and how many orders; 8845 lines of the 4012 orders.
SELECT COUNT(*), COUNT(DISTINCT PurchaseOrderID)
FROM Purchasing.PurchaseOrderDetail
;

-- how many lines an order has, at least and at most; 1 and 30.
SELECT MIN(lines), MAX(lines)
FROM (SELECT PurchaseOrderID, COUNT(*) AS lines
      FROM Purchasing.PurchaseOrderDetail
      GROUP BY PurchaseOrderID) d
;


-- ----------------------------------------------------------------------------------------------- PurchaseOrderDetailID
-- the smallest and the largest PurchaseOrderDetailID, and how many distinct; an identity, unique across orders though
-- the key pairs it with PurchaseOrderID; 1 to 8845, all distinct.
SELECT MIN(PurchaseOrderDetailID),
       MAX(PurchaseOrderDetailID),
       COUNT(DISTINCT PurchaseOrderDetailID)
FROM Purchasing.PurchaseOrderDetail
;


-- ------------------------------------------------------------------------------------------------------------- DueDate
-- the range of DueDate; 2022-04-29 to 2025-10-21.
SELECT MIN(DueDate), MAX(DueDate)
FROM Purchasing.PurchaseOrderDetail
;

-- how many days after the OrderDate a line is due, by the status of the order; 14 days for every status but approved
-- (2); its 57 lines, of orders 4001 to 4012, run from -162 to 223.
SELECT h.Status,
       COUNT(*) AS lines,
       MIN(DATEDIFF(DAY, h.OrderDate, d.DueDate)),
       MAX(DATEDIFF(DAY, h.OrderDate, d.DueDate))
FROM Purchasing.PurchaseOrderDetail d
         JOIN Purchasing.PurchaseOrderHeader h ON h.PurchaseOrderID = d.PurchaseOrderID
GROUP BY h.Status
ORDER BY h.Status ASC
;

-- a line due before its order was placed; a finding: 4003 has 1 line due 2024-12-02 for an order of 2025-05-13, and
-- 4006 has 6 lines due 2025-01-10 for an order of 2025-01-17.
SELECT d.PurchaseOrderID, COUNT(*) AS lines, MIN(h.OrderDate), MIN(d.DueDate)
FROM Purchasing.PurchaseOrderDetail d
         JOIN Purchasing.PurchaseOrderHeader h ON h.PurchaseOrderID = d.PurchaseOrderID
WHERE d.DueDate < h.OrderDate
GROUP BY d.PurchaseOrderID
ORDER BY d.PurchaseOrderID ASC
;


-- ------------------------------------------------------------------------------------------------------------ OrderQty
-- the smallest and the largest OrderQty; CK_PurchaseOrderDetail_OrderQty keeps it above 0; 3 and 8000.
SELECT MIN(OrderQty), MAX(OrderQty)
FROM Purchasing.PurchaseOrderDetail
;

-- an OrderQty outside MinOrderQty and MaxOrderQty of the product and the vendor; 0 and 0: every OrderQty lies between
-- the two.
SELECT COUNT(*)                                         AS lines,
       COUNT(IIF(d.OrderQty < pv.MinOrderQty, 1, NULL)) AS below_min,
       COUNT(IIF(d.OrderQty > pv.MaxOrderQty, 1, NULL)) AS above_max
FROM Purchasing.PurchaseOrderDetail d
         JOIN Purchasing.PurchaseOrderHeader h ON h.PurchaseOrderID = d.PurchaseOrderID
         JOIN Purchasing.ProductVendor pv
              ON pv.ProductID = d.ProductID AND pv.BusinessEntityID = h.VendorID
;


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- how many products are ordered; 265, every product a vendor supplies.
SELECT COUNT(DISTINCT ProductID)
FROM Purchasing.PurchaseOrderDetail
;

-- a product ordered twice in the same order; a finding: order 4008 has product 865 on 3 lines and 866 on 2.
SELECT PurchaseOrderID, ProductID, COUNT(*) AS lines
FROM Purchasing.PurchaseOrderDetail
GROUP BY PurchaseOrderID, ProductID
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- the smallest and the largest UnitPrice; CK_PurchaseOrderDetail_UnitPrice keeps it at 0.00 or more; 0.2100 and
-- 82.8345.
SELECT MIN(UnitPrice), MAX(UnitPrice)
FROM Purchasing.PurchaseOrderDetail
;

-- UnitPrice against StandardPrice of the product and the vendor; only 6 of the 8845 lines are at the StandardPrice; the
-- ratio runs from 0.3055 to 1.2822, the same as LastReceiptCost against StandardPrice.
SELECT COUNT(*)                                            AS lines,
       COUNT(IIF(d.UnitPrice = pv.StandardPrice, 1, NULL)) AS at_standard,
       MIN(d.UnitPrice / pv.StandardPrice),
       MAX(d.UnitPrice / pv.StandardPrice)
FROM Purchasing.PurchaseOrderDetail d
         JOIN Purchasing.PurchaseOrderHeader h ON h.PurchaseOrderID = d.PurchaseOrderID
         JOIN Purchasing.ProductVendor pv
              ON pv.ProductID = d.ProductID AND pv.BusinessEntityID = h.VendorID
;


-- ----------------------------------------------------------------------------------------------------------- LineTotal
-- the smallest and the largest LineTotal, computed as OrderQty * UnitPrice; 37.0755 and 249420.0000.
SELECT MIN(LineTotal), MAX(LineTotal)
FROM Purchasing.PurchaseOrderDetail
;


-- --------------------------------------------------------------------------------------------------------- ReceivedQty
-- the smallest and the largest ReceivedQty; CK_PurchaseOrderDetail_ReceivedQty keeps it at 0.00 or more; 2.00 and
-- 8000.00.
SELECT MIN(ReceivedQty), MAX(ReceivedQty)
FROM Purchasing.PurchaseOrderDetail
;

-- ReceivedQty against OrderQty; 8465 in full, 380 short, none over.
SELECT COUNT(IIF(ReceivedQty = OrderQty, 1, NULL)) AS in_full,
       COUNT(IIF(ReceivedQty < OrderQty, 1, NULL)) AS below,
       COUNT(IIF(ReceivedQty > OrderQty, 1, NULL)) AS above
FROM Purchasing.PurchaseOrderDetail
;

-- a ReceivedQty with a fraction, which decimal(8, 2) allows; 0: every ReceivedQty is whole.
SELECT COUNT(*)
FROM Purchasing.PurchaseOrderDetail
WHERE ReceivedQty <> FLOOR(ReceivedQty)
;


-- --------------------------------------------------------------------------------------------------------- RejectedQty
-- the smallest and the largest RejectedQty; CK_PurchaseOrderDetail_RejectedQty keeps it at 0.00 or more; 0.00 and
-- 1250.00; 563 lines reject some.
SELECT MIN(RejectedQty), MAX(RejectedQty), COUNT(IIF(RejectedQty > 0, 1, NULL)) AS rejecting
FROM Purchasing.PurchaseOrderDetail
;

-- a RejectedQty above the ReceivedQty, which no check forbids.
-- no rows: no line rejects more than it received.
SELECT PurchaseOrderID, PurchaseOrderDetailID, ReceivedQty, RejectedQty
FROM Purchasing.PurchaseOrderDetail
WHERE RejectedQty > ReceivedQty
;


-- ---------------------------------------------------------------------------------------------------------- StockedQty
-- the smallest and the largest StockedQty, computed as ReceivedQty - RejectedQty; 0.00 and 8000.00.
SELECT MIN(StockedQty), MAX(StockedQty)
FROM Purchasing.PurchaseOrderDetail
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range of ModifiedDate; 2022-04-22 00:00:00.000 to 2026-08-11 12:25:46.483.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Purchasing.PurchaseOrderDetail
;

-- how many days a line was modified before its order, by the status of the order; 2 days before for every status but
-- approved (2), whose 57 lines were modified with their orders, 16 of them a few milliseconds later.
SELECT h.Status,
       COUNT(*)                                             AS lines,
       MIN(DATEDIFF(DAY, d.ModifiedDate, h.ModifiedDate)),
       MAX(DATEDIFF(DAY, d.ModifiedDate, h.ModifiedDate)),
       COUNT(IIF(d.ModifiedDate > h.ModifiedDate, 1, NULL)) AS later
FROM Purchasing.PurchaseOrderDetail d
         JOIN Purchasing.PurchaseOrderHeader h ON h.PurchaseOrderID = d.PurchaseOrderID
GROUP BY h.Status
ORDER BY h.Status ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the receipts against the status of the order; rejected (3) orders receive 46746 and reject 46745 of the 46747
-- ordered; approved (2) ones reject nothing; pending (1) and complete (4) reject 1510 and 24445.
SELECT h.Status,
       COUNT(*)           AS lines,
       SUM(d.OrderQty)    AS ordered,
       SUM(d.ReceivedQty) AS received,
       SUM(d.RejectedQty) AS rejected
FROM Purchasing.PurchaseOrderDetail d
         JOIN Purchasing.PurchaseOrderHeader h ON h.PurchaseOrderID = d.PurchaseOrderID
GROUP BY h.Status
ORDER BY h.Status ASC
;

