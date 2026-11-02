-- -
-- #%L
-- sqlserver-samples-persistence-futonmanufacturing
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
-- Profiling queries on dbo.ProductionCompletion, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The load scripts insert no production order, nor its completions, so the table is empty and every check below
-- finds nothing. They are kept for the rows an application would add.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_ProductionCompletion.sql
--


-- -------------------------------------------------------------------------------------------------------- CompletionID
-- how many rows, and the smallest and the largest CompletionID; 0 completions, NULL and NULL.
SELECT COUNT(*), MIN(CompletionID), MAX(CompletionID)
FROM dbo.ProductionCompletion
;


-- --------------------------------------------------------------------------------------------------- ProductionOrderID
-- the orders whose completions add up to more than the OrderQuantity.
-- no rows: the table is empty.
SELECT c.ProductionOrderID, po.OrderQuantity, SUM(c.QuantityCompleted) AS completed
FROM dbo.ProductionCompletion c
         JOIN dbo.ProductionOrder po ON po.ProductionOrderID = c.ProductionOrderID
GROUP BY c.ProductionOrderID, po.OrderQuantity
HAVING SUM(c.QuantityCompleted) > po.OrderQuantity
;


-- --------------------------------------------------------------------------------------------------- QuantityCompleted
-- a QuantityCompleted of zero or less.
-- no rows: the table is empty.
SELECT CompletionID, QuantityCompleted
FROM dbo.ProductionCompletion
WHERE QuantityCompleted <= 0
;


-- ---------------------------------------------------------------------------------------------------- QuantityScrapped
-- a QuantityScrapped which is negative.
-- no rows: the table is empty.
SELECT CompletionID, QuantityScrapped
FROM dbo.ProductionCompletion
WHERE QuantityScrapped < 0
;


-- ------------------------------------------------------------------------------------------------------ CompletionDate
-- a completion dated before its order started.
-- no rows: the table is empty.
SELECT c.CompletionID, c.CompletionDate, po.StartDate
FROM dbo.ProductionCompletion c
         JOIN dbo.ProductionOrder po ON po.ProductionOrderID = c.ProductionOrderID
WHERE CAST(c.CompletionDate AS date) < po.StartDate
;


-- -------------------------------------------------------------------------------------------------------- WorkCenterID
-- a completion at another work center than its order's.
-- no rows: the table is empty.
SELECT c.CompletionID, c.WorkCenterID, po.WorkCenterID AS order_work_center
FROM dbo.ProductionCompletion c
         JOIN dbo.ProductionOrder po ON po.ProductionOrderID = c.ProductionOrderID
WHERE c.WorkCenterID <> po.WorkCenterID
;


-- --------------------------------------------------------------------------------------------------------------- Notes
-- a Notes which is blank.
-- no rows: the table is empty.
SELECT CompletionID
FROM dbo.ProductionCompletion
WHERE TRIM(Notes) = ''
;


-- --------------------------------------------------------------------------------------------------------- CompletedBy
-- the values CompletedBy takes, NULL for none.
-- no rows: the table is empty.
SELECT CompletedBy, COUNT(*) AS completions
FROM dbo.ProductionCompletion
GROUP BY CompletedBy
ORDER BY CompletedBy ASC
;

