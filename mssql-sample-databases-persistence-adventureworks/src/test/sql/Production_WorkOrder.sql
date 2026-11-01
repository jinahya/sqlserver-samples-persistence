-- -
-- #%L
-- mssql-sample-databases-persistence-adventureworks
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
-- Profiling queries on Production.WorkOrder, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_WorkOrder.sql
--


-- --------------------------------------------------------------------------------------------------------- WorkOrderID
-- how many rows, and the smallest and the largest WorkOrderID; 72591 work orders, 1 to 72591.
SELECT COUNT(*), MIN(WorkOrderID), MAX(WorkOrderID)
FROM Production.WorkOrder
;

-- the gaps in WorkOrderID, below the largest: how many values are missing; 0: WorkOrderID runs from 1 without a gap.
SELECT MAX(WorkOrderID) - MIN(WorkOrderID) + 1 - COUNT(*) AS missing
FROM Production.WorkOrder
;


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- the products worked on; 238 products, 3 to 999.
SELECT COUNT(DISTINCT ProductID), MIN(ProductID), MAX(ProductID)
FROM Production.WorkOrder
;

-- a work order for a product which is not made in house.
-- no rows: every work order is for a product with MakeFlag 1.
SELECT TOP (3) w.WorkOrderID, w.ProductID
FROM Production.WorkOrder w
         JOIN Production.Product p ON p.ProductID = w.ProductID
WHERE p.MakeFlag = 0
;


-- ------------------------------------------------------------------------------------------------------------ OrderQty
-- the smallest and the largest OrderQty, which CK_WorkOrder_OrderQty keeps above 0; 1 and 39570, 62 on average.
SELECT MIN(OrderQty), MAX(OrderQty), AVG(OrderQty)
FROM Production.WorkOrder
;


-- ---------------------------------------------------------------------------------------------------------- StockedQty
-- StockedQty is computed, isnull(OrderQty - ScrappedQty, 0); a row where it is not.
-- no rows: every StockedQty is OrderQty less ScrappedQty.
SELECT TOP (3) WorkOrderID, OrderQty, ScrappedQty, StockedQty
FROM Production.WorkOrder
WHERE StockedQty <> OrderQty - ScrappedQty
;

-- the smallest and the largest StockedQty, and how many stock nothing; 1 and 39570; every order stocks something.
SELECT MIN(StockedQty), MAX(StockedQty), SUM(CASE WHEN StockedQty = 0 THEN 1 ELSE 0 END) AS none_
FROM Production.WorkOrder
;


-- --------------------------------------------------------------------------------------------------------- ScrappedQty
-- how many orders scrap anything, and the most scrapped, which CK_WorkOrder_ScrappedQty keeps at least 0; 729 orders
-- scrap, 673 at most.
SELECT SUM(CASE WHEN ScrappedQty > 0 THEN 1 ELSE 0 END) AS scrapping, MAX(ScrappedQty)
FROM Production.WorkOrder
;

-- a work order which scraps more than it orders.
-- no rows: ScrappedQty never exceeds OrderQty.
SELECT TOP (3) WorkOrderID, OrderQty, ScrappedQty
FROM Production.WorkOrder
WHERE ScrappedQty > OrderQty
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- the range of StartDate; 2022-06-02 00:00:00.000 to 2025-06-01 00:00:00.000.
SELECT MIN(StartDate), MAX(StartDate)
FROM Production.WorkOrder
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- how many orders have not ended, and the range of the others' EndDate; every order has ended; 2022-06-12 00:00:00.000
-- to 2025-06-16 00:00:00.000.
SELECT COUNT(*) - COUNT(EndDate) AS open_, MIN(EndDate), MAX(EndDate)
FROM Production.WorkOrder
;

-- how long the orders took, in days, from StartDate to EndDate; 10 to 32 days, 10 for 29966, 11 for 19835, then even
-- numbers from 12 to 32.
SELECT DATEDIFF(DAY, StartDate, EndDate) AS days, COUNT(*) AS workOrders
FROM Production.WorkOrder
GROUP BY DATEDIFF(DAY, StartDate, EndDate)
ORDER BY days ASC
;


-- ------------------------------------------------------------------------------------------------------------- DueDate
-- how long the orders were given, in days, from StartDate to DueDate; 11 days for all 72591.
SELECT DATEDIFF(DAY, StartDate, DueDate) AS days, COUNT(*) AS workOrders
FROM Production.WorkOrder
GROUP BY DATEDIFF(DAY, StartDate, DueDate)
ORDER BY days ASC
;

-- the orders which ended after they were due; 22790 of the 72591.
SELECT COUNT(*) AS late
FROM Production.WorkOrder
WHERE EndDate > DueDate
;


-- ------------------------------------------------------------------------------------------------------- ScrapReasonID
-- a ScrapReasonID without scrap, or scrap without a ScrapReasonID.
-- no rows: an order names a reason if and only if it scraps.
SELECT TOP (3) WorkOrderID, ScrappedQty, ScrapReasonID
FROM Production.WorkOrder
WHERE (ScrappedQty = 0 AND ScrapReasonID IS NOT NULL)
   OR (ScrappedQty > 0 AND ScrapReasonID IS NULL)
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- a ModifiedDate other than the EndDate.
-- 0: ModifiedDate is EndDate on every order.
SELECT COUNT(*) AS other
FROM Production.WorkOrder
WHERE ModifiedDate <> EndDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the orders by how many routing steps they have; 0 for 29966, 1 for 28512, 2 for 8190, 3 for 4433, 6 for 1490.
SELECT x.steps, COUNT(*) AS workOrders
FROM (SELECT w.WorkOrderID, COUNT(r.OperationSequence) AS steps
      FROM Production.WorkOrder w
               LEFT JOIN Production.WorkOrderRouting r ON r.WorkOrderID = w.WorkOrderID
      GROUP BY w.WorkOrderID) x
GROUP BY x.steps
ORDER BY x.steps ASC
;

-- a work order with no W transaction, in the history or in the archive; how many.
-- 0: every work order has its W transaction.
SELECT COUNT(*) AS untracked
FROM Production.WorkOrder w
WHERE NOT EXISTS (SELECT 1
                  FROM Production.TransactionHistory h
                  WHERE h.TransactionType = 'W' AND h.ReferenceOrderID = w.WorkOrderID)
  AND NOT EXISTS (SELECT 1
                  FROM Production.TransactionHistoryArchive a
                  WHERE a.TransactionType = 'W' AND a.ReferenceOrderID = w.WorkOrderID)
;

