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
-- Profiling queries on the view dbo.vw_ProductionOrderStatus, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is empty on the installed data: dbo.ProductionOrder has no rows.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_ProductionOrderStatus.sql
--


-- ----------------------------------------------------------------------------------------------------- WorkOrderNumber
-- how many rows; 0. The view reads ProductionOrder, which is empty.
SELECT COUNT(*)
FROM dbo.vw_ProductionOrderStatus
;

-- how many production orders there are; 0, none has been planned.
SELECT COUNT(*)
FROM dbo.ProductionOrder
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- how many rows carry a Status; 0, the view being empty.
SELECT COUNT(Status)
FROM dbo.vw_ProductionOrderStatus
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows carry a ItemCode; 0, the view being empty.
SELECT COUNT(ItemCode)
FROM dbo.vw_ProductionOrderStatus
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- how many rows carry a ItemName; 0, the view being empty.
SELECT COUNT(ItemName)
FROM dbo.vw_ProductionOrderStatus
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- how many rows carry a ItemType; 0, the view being empty.
SELECT COUNT(ItemType)
FROM dbo.vw_ProductionOrderStatus
;


-- ------------------------------------------------------------------------------------------------------- OrderQuantity
-- how many rows carry a OrderQuantity; 0, the view being empty.
SELECT COUNT(OrderQuantity)
FROM dbo.vw_ProductionOrderStatus
;


-- --------------------------------------------------------------------------------------------------- QuantityCompleted
-- how many rows carry a QuantityCompleted; 0, the view being empty.
SELECT COUNT(QuantityCompleted)
FROM dbo.vw_ProductionOrderStatus
;


-- ---------------------------------------------------------------------------------------------------- QuantityScrapped
-- how many rows carry a QuantityScrapped; 0, the view being empty.
SELECT COUNT(QuantityScrapped)
FROM dbo.vw_ProductionOrderStatus
;


-- --------------------------------------------------------------------------------------------------- QuantityRemaining
-- how many rows carry a QuantityRemaining; 0, the view being empty.
SELECT COUNT(QuantityRemaining)
FROM dbo.vw_ProductionOrderStatus
;


-- ----------------------------------------------------------------------------------------------------- PercentComplete
-- how many rows carry a PercentComplete; 0, the view being empty.
SELECT COUNT(PercentComplete)
FROM dbo.vw_ProductionOrderStatus
;


-- ------------------------------------------------------------------------------------------------------ WorkCenterName
-- how many rows carry a WorkCenterName; 0, the view being empty.
SELECT COUNT(WorkCenterName)
FROM dbo.vw_ProductionOrderStatus
;


-- ------------------------------------------------------------------------------------------------------- WarehouseName
-- how many rows carry a WarehouseName; 0, the view being empty.
SELECT COUNT(WarehouseName)
FROM dbo.vw_ProductionOrderStatus
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- how many rows carry a StartDate; 0, the view being empty.
SELECT COUNT(StartDate)
FROM dbo.vw_ProductionOrderStatus
;


-- ----------------------------------------------------------------------------------------------- PlannedCompletionDate
-- how many rows carry a PlannedCompletionDate; 0, the view being empty.
SELECT COUNT(PlannedCompletionDate)
FROM dbo.vw_ProductionOrderStatus
;


-- ------------------------------------------------------------------------------------------------ ActualCompletionDate
-- how many rows carry a ActualCompletionDate; 0, the view being empty.
SELECT COUNT(ActualCompletionDate)
FROM dbo.vw_ProductionOrderStatus
;


-- -------------------------------------------------------------------------------------------------------- DaysVariance
-- how many rows carry a DaysVariance; 0, the view being empty.
SELECT COUNT(DaysVariance)
FROM dbo.vw_ProductionOrderStatus
;


-- ------------------------------------------------------------------------------------------------------ ScheduleStatus
-- how many rows carry a ScheduleStatus; 0, the view being empty.
SELECT COUNT(ScheduleStatus)
FROM dbo.vw_ProductionOrderStatus
;


-- ------------------------------------------------------------------------------------------------------------ Priority
-- how many rows carry a Priority; 0, the view being empty.
SELECT COUNT(Priority)
FROM dbo.vw_ProductionOrderStatus
;


-- ----------------------------------------------------------------------------------------------------------- CreatedBy
-- how many rows carry a CreatedBy; 0, the view being empty.
SELECT COUNT(CreatedBy)
FROM dbo.vw_ProductionOrderStatus
;


-- --------------------------------------------------------------------------------------------------------- CreatedDate
-- how many rows carry a CreatedDate; 0, the view being empty.
SELECT COUNT(CreatedDate)
FROM dbo.vw_ProductionOrderStatus
;

