-- -
-- #%L
-- sql-server-samples-persistence-futonmanufacturing
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
-- Profiling queries on the view dbo.vw_DailyProductionSchedule, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is a row per open production order (Planned, Released, InProgress) planned to complete between today
-- and 14 days on. It is empty on the installed data: the load scripts insert no production order. The window is
-- taken from GETDATE(), so even with orders the rows would depend on the clock, not only on the data.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_DailyProductionSchedule.sql
--


-- ------------------------------------------------------------------------------------------------------- ScheduledDate
-- how many rows; 0: the view is empty.
SELECT COUNT(*)
FROM dbo.vw_DailyProductionSchedule
;

-- why the view is empty: the open production orders, and those of them due in the next 14 days; 0 and 0, the
-- table is empty.
SELECT COUNT(CASE WHEN Status IN ('Planned', 'Released', 'InProgress') THEN 1 END),
       COUNT(CASE
                 WHEN Status IN ('Planned', 'Released', 'InProgress')
                     AND PlannedCompletionDate BETWEEN CAST(GETDATE() AS date)
                         AND DATEADD(DAY, 14, CAST(GETDATE() AS date)) THEN 1
             END)
FROM dbo.ProductionOrder
;


-- ----------------------------------------------------------------------------------------------------------- DayOfWeek
-- how many DayOfWeek values; 0: the view is empty.
SELECT COUNT(DayOfWeek)
FROM dbo.vw_DailyProductionSchedule
;


-- ------------------------------------------------------------------------------------------------------ WorkCenterName
-- how many WorkCenterName values; 0: the view is empty.
SELECT COUNT(WorkCenterName)
FROM dbo.vw_DailyProductionSchedule
;


-- ----------------------------------------------------------------------------------------------------- WorkOrderNumber
-- how many WorkOrderNumber values; 0: the view is empty.
SELECT COUNT(WorkOrderNumber)
FROM dbo.vw_DailyProductionSchedule
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- how many Status values; 0: the view is empty.
SELECT COUNT(Status)
FROM dbo.vw_DailyProductionSchedule
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many ItemCode values; 0: the view is empty.
SELECT COUNT(ItemCode)
FROM dbo.vw_DailyProductionSchedule
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- how many ItemName values; 0: the view is empty.
SELECT COUNT(ItemName)
FROM dbo.vw_DailyProductionSchedule
;


-- --------------------------------------------------------------------------------------------------- QuantityToProduce
-- how many QuantityToProduce values; 0: the view is empty.
SELECT COUNT(QuantityToProduce)
FROM dbo.vw_DailyProductionSchedule
;


-- ------------------------------------------------------------------------------------------------------------ Priority
-- how many Priority values; 0: the view is empty.
SELECT COUNT(Priority)
FROM dbo.vw_DailyProductionSchedule
;


-- ------------------------------------------------------------------------------------------------------- EstimatedDays
-- how many EstimatedDays values; 0: the view is empty.
SELECT COUNT(EstimatedDays)
FROM dbo.vw_DailyProductionSchedule
;


-- ----------------------------------------------------------------------------------------------------- ProductionValue
-- how many ProductionValue values; 0: the view is empty.
SELECT COUNT(ProductionValue)
FROM dbo.vw_DailyProductionSchedule
;


-- ---------------------------------------------------------------------------------------------------- ProductionStatus
-- how many ProductionStatus values; 0: the view is empty.
SELECT COUNT(ProductionStatus)
FROM dbo.vw_DailyProductionSchedule
;

