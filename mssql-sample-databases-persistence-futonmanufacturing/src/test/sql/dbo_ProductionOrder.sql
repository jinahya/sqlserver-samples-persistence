-- -
-- #%L
-- mssql-sample-databases-persistence-futonmanufacturing
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
-- Profiling queries on dbo.ProductionOrder, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The load scripts insert no production order, so the table is empty and every check below finds nothing. They
-- are kept for the rows an application would add.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_ProductionOrder.sql
--


-- --------------------------------------------------------------------------------------------------- ProductionOrderID
-- how many rows, and the smallest and the largest ProductionOrderID; 0 orders, NULL and NULL.
SELECT COUNT(*), MIN(ProductionOrderID), MAX(ProductionOrderID)
FROM dbo.ProductionOrder
;


-- ----------------------------------------------------------------------------------------------------- WorkOrderNumber
-- a WorkOrderNumber which is blank, or carries leading or trailing spaces.
-- no rows: the table is empty.
SELECT ProductionOrderID, '[' + WorkOrderNumber + ']' AS WorkOrderNumber
FROM dbo.ProductionOrder
WHERE TRIM(WorkOrderNumber) = ''
   OR DATALENGTH(WorkOrderNumber) <> DATALENGTH(TRIM(WorkOrderNumber))
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- an order for an item which is not made here, a raw material, or which has no bill of materials.
-- no rows: the table is empty.
SELECT po.ProductionOrderID, po.ItemID
FROM dbo.ProductionOrder po
         JOIN dbo.Items i ON i.ItemID = po.ItemID
WHERE i.ItemTypeID = 1
   OR NOT EXISTS (SELECT 1
                  FROM dbo.BillOfMaterials b
                  WHERE b.ParentItemID = po.ItemID)
;


-- --------------------------------------------------------------------------------------------------------- WarehouseID
-- the orders of each warehouse.
-- no rows: the table is empty.
SELECT WarehouseID, COUNT(*) AS orders
FROM dbo.ProductionOrder
GROUP BY WarehouseID
ORDER BY WarehouseID ASC
;


-- -------------------------------------------------------------------------------------------------------- WorkCenterID
-- the orders of each work center, NULL for none.
-- no rows: the table is empty.
SELECT WorkCenterID, COUNT(*) AS orders
FROM dbo.ProductionOrder
GROUP BY WorkCenterID
ORDER BY WorkCenterID ASC
;


-- ------------------------------------------------------------------------------------------------------- OrderQuantity
-- an OrderQuantity of zero or less.
-- no rows: the table is empty.
SELECT ProductionOrderID, OrderQuantity
FROM dbo.ProductionOrder
WHERE OrderQuantity <= 0
;


-- --------------------------------------------------------------------------------------------------- QuantityCompleted
-- an order which has completed more than it was for.
-- no rows: the table is empty.
SELECT ProductionOrderID, OrderQuantity, QuantityCompleted
FROM dbo.ProductionOrder
WHERE QuantityCompleted > OrderQuantity
;

-- a QuantityCompleted other than the sum of the order's completions.
-- no rows: the table is empty.
SELECT po.ProductionOrderID, po.QuantityCompleted, SUM(c.QuantityCompleted) AS completions
FROM dbo.ProductionOrder po
         JOIN dbo.ProductionCompletion c ON c.ProductionOrderID = po.ProductionOrderID
GROUP BY po.ProductionOrderID, po.QuantityCompleted
HAVING ISNULL(po.QuantityCompleted, 0) <> SUM(c.QuantityCompleted)
;


-- ---------------------------------------------------------------------------------------------------- QuantityScrapped
-- a QuantityScrapped which is negative.
-- no rows: the table is empty.
SELECT ProductionOrderID, QuantityScrapped
FROM dbo.ProductionOrder
WHERE QuantityScrapped < 0
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- an order planned to complete before it starts.
-- no rows: the table is empty.
SELECT ProductionOrderID, StartDate, PlannedCompletionDate
FROM dbo.ProductionOrder
WHERE PlannedCompletionDate < StartDate
;


-- ----------------------------------------------------------------------------------------------- PlannedCompletionDate
-- how many PlannedCompletionDate values are NULL, and the earliest and the latest; 0, NULL and NULL.
SELECT COUNT(*) - COUNT(PlannedCompletionDate), MIN(PlannedCompletionDate), MAX(PlannedCompletionDate)
FROM dbo.ProductionOrder
;


-- ------------------------------------------------------------------------------------------------ ActualCompletionDate
-- an order completed before it started, or with an ActualCompletionDate but not Completed.
-- no rows: the table is empty.
SELECT ProductionOrderID, Status, StartDate, ActualCompletionDate
FROM dbo.ProductionOrder
WHERE ActualCompletionDate < StartDate
   OR (ActualCompletionDate IS NOT NULL AND Status <> 'Completed')
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- the values Status takes, the first key of IX_ProdOrder_Status; the views look for Planned, Released and
-- InProgress.
-- no rows: the table is empty.
SELECT Status, COUNT(*) AS orders
FROM dbo.ProductionOrder
GROUP BY Status
ORDER BY Status ASC
;


-- ------------------------------------------------------------------------------------------------------------ Priority
-- the values Priority takes, NULL for none.
-- no rows: the table is empty.
SELECT Priority, COUNT(*) AS orders
FROM dbo.ProductionOrder
GROUP BY Priority
ORDER BY Priority ASC
;


-- --------------------------------------------------------------------------------------------------------------- Notes
-- a Notes which is blank.
-- no rows: the table is empty.
SELECT ProductionOrderID
FROM dbo.ProductionOrder
WHERE TRIM(Notes) = ''
;


-- ----------------------------------------------------------------------------------------------------------- CreatedBy
-- the values CreatedBy takes, NULL for none.
-- no rows: the table is empty.
SELECT CreatedBy, COUNT(*) AS orders
FROM dbo.ProductionOrder
GROUP BY CreatedBy
ORDER BY CreatedBy ASC
;


-- --------------------------------------------------------------------------------------------------------- CreatedDate
-- how many CreatedDate values are NULL, and the earliest and the latest; 0, NULL and NULL.
SELECT COUNT(*) - COUNT(CreatedDate), MIN(CreatedDate), MAX(CreatedDate)
FROM dbo.ProductionOrder
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- an order modified before it was created.
-- no rows: the table is empty.
SELECT ProductionOrderID, CreatedDate, ModifiedDate
FROM dbo.ProductionOrder
WHERE ModifiedDate < CreatedDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an order with no material.
-- no rows: the table is empty.
SELECT po.ProductionOrderID
FROM dbo.ProductionOrder po
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.ProductionOrderMaterial m
                  WHERE m.ProductionOrderID = po.ProductionOrderID)
;

