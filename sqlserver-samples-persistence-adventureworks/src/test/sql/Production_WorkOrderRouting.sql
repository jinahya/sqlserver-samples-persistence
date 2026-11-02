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
-- Profiling queries on Production.WorkOrderRouting, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_WorkOrderRouting.sql
--


-- --------------------------------------------------------------------------------------------------------- WorkOrderID
-- how many rows, and how many work orders they cover; 67131 rows, on 42625 work orders, 13 to 72587.
SELECT COUNT(*), COUNT(DISTINCT WorkOrderID), MIN(WorkOrderID), MAX(WorkOrderID)
FROM Production.WorkOrderRouting
;


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- a routing whose ProductID is not its work order's; the key repeats it, and nothing keeps the two the same.
-- no rows: every routing carries its work order's product.
SELECT TOP (3) r.WorkOrderID, r.ProductID, w.ProductID AS workOrderProductID
FROM Production.WorkOrderRouting r
         JOIN Production.WorkOrder w ON w.WorkOrderID = r.WorkOrderID
WHERE r.ProductID <> w.ProductID
;

-- the products routed; 149 products, 514 to 999.
SELECT COUNT(DISTINCT ProductID), MIN(ProductID), MAX(ProductID)
FROM Production.WorkOrderRouting
;


-- --------------------------------------------------------------------------------------------------- OperationSequence
-- the operation steps, and the rows of each; 1 for 9567, 2 for 5923, 3 for 1490, 4 for 816, 5 for 674, 6 for 30107, 7
-- for 18554.
SELECT OperationSequence, COUNT(*) AS routings
FROM Production.WorkOrderRouting
GROUP BY OperationSequence
ORDER BY OperationSequence ASC
;


-- ---------------------------------------------------------------------------------------------------------- LocationID
-- a step at a location other than the one its number names, 1 at 10, 2 at 20, ... 7 at 60.
-- none: step 1 is at 10, 2 at 20, 3 at 30, 4 at 40, 5 at 45, 6 at 50 and 7 at 60, always.
SELECT OperationSequence, LocationID, COUNT(*) AS routings
FROM Production.WorkOrderRouting
GROUP BY OperationSequence, LocationID
ORDER BY OperationSequence ASC, LocationID ASC
;


-- -------------------------------------------------------------------------------------------------- ScheduledStartDate
-- the range of ScheduledStartDate; 2022-06-02 00:00:00.000 to 2025-06-01 00:00:00.000.
SELECT MIN(ScheduledStartDate), MAX(ScheduledStartDate)
FROM Production.WorkOrderRouting
;


-- ---------------------------------------------------------------------------------------------------- ScheduledEndDate
-- how long the steps were scheduled, in days, which CK_WorkOrderRouting_ScheduledEndDate keeps at least 0; 11 days for
-- all 67131.
SELECT DATEDIFF(DAY, ScheduledStartDate, ScheduledEndDate) AS days, COUNT(*) AS routings
FROM Production.WorkOrderRouting
GROUP BY DATEDIFF(DAY, ScheduledStartDate, ScheduledEndDate)
ORDER BY days ASC
;


-- ----------------------------------------------------------------------------------------------------- ActualStartDate
-- how many steps have not started, and how far the others started from their schedule, in days; none unstarted; 0 to 21
-- days late.
SELECT COUNT(*) - COUNT(ActualStartDate) AS unstarted,
       MIN(DATEDIFF(DAY, ScheduledStartDate, ActualStartDate)),
       MAX(DATEDIFF(DAY, ScheduledStartDate, ActualStartDate))
FROM Production.WorkOrderRouting
;


-- ------------------------------------------------------------------------------------------------------- ActualEndDate
-- how many steps have not ended, and how long the others took, in days; none unended; 0 to 32 days.
SELECT COUNT(*) - COUNT(ActualEndDate) AS unended,
       MIN(DATEDIFF(DAY, ActualStartDate, ActualEndDate)),
       MAX(DATEDIFF(DAY, ActualStartDate, ActualEndDate))
FROM Production.WorkOrderRouting
;


-- --------------------------------------------------------------------------------------------------- ActualResourceHrs
-- how many steps have no hours, and the smallest and the largest of the others; none without; 1.0000 to 4.1000 hours.
SELECT COUNT(*) - COUNT(ActualResourceHrs) AS without, MIN(ActualResourceHrs), MAX(ActualResourceHrs)
FROM Production.WorkOrderRouting
;


-- --------------------------------------------------------------------------------------------------------- PlannedCost
-- the planned costs, by step, which CK_WorkOrderRouting_PlannedCost keeps above 0; a single cost per step: 92.2500,
-- 87.5000, 14.5000, 31.5000, 36.0000, 36.7500 and 49.0000 for 1 to 7.
SELECT OperationSequence, MIN(PlannedCost), MAX(PlannedCost)
FROM Production.WorkOrderRouting
GROUP BY OperationSequence
ORDER BY OperationSequence ASC
;


-- ---------------------------------------------------------------------------------------------------------- ActualCost
-- how many steps have no actual cost, and how many cost other than planned; 0 and 0: every step costs what it planned.
SELECT COUNT(*) - COUNT(ActualCost) AS without, SUM(CASE WHEN ActualCost <> PlannedCost THEN 1 ELSE 0 END) AS offPlan
FROM Production.WorkOrderRouting
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- a ModifiedDate other than the ActualEndDate.
-- 0.
SELECT COUNT(*) AS other
FROM Production.WorkOrderRouting
WHERE ModifiedDate <> ActualEndDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a routing step outside its work order's span: started before the order, or ended after it; and the latest, in days.
-- none early, but 4369 end after their work order does, at most 21 days after.
SELECT SUM(CASE WHEN r.ActualStartDate < w.StartDate THEN 1 ELSE 0 END) AS early,
       SUM(CASE WHEN r.ActualEndDate > w.EndDate THEN 1 ELSE 0 END) AS late,
       MAX(DATEDIFF(DAY, w.EndDate, r.ActualEndDate)) AS mostDaysLate
FROM Production.WorkOrderRouting r
         JOIN Production.WorkOrder w ON w.WorkOrderID = r.WorkOrderID
;

