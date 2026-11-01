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
-- Profiling queries on the view dbo.vw_LateProductionOrders, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is empty on the installed data: dbo.ProductionOrder has no rows.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_LateProductionOrders.sql
--


-- ----------------------------------------------------------------------------------------------------- WorkOrderNumber
-- how many rows; 0. The view reads ProductionOrder, which is empty.
SELECT COUNT(*)
FROM dbo.vw_LateProductionOrders
;

-- how many production orders there are; 0, none has been planned.
SELECT COUNT(*)
FROM dbo.ProductionOrder
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- how many rows carry a Status; 0, the view being empty.
SELECT COUNT(Status)
FROM dbo.vw_LateProductionOrders
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows carry a ItemCode; 0, the view being empty.
SELECT COUNT(ItemCode)
FROM dbo.vw_LateProductionOrders
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- how many rows carry a ItemName; 0, the view being empty.
SELECT COUNT(ItemName)
FROM dbo.vw_LateProductionOrders
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- how many rows carry a ItemType; 0, the view being empty.
SELECT COUNT(ItemType)
FROM dbo.vw_LateProductionOrders
;


-- ------------------------------------------------------------------------------------------------------- OrderQuantity
-- how many rows carry a OrderQuantity; 0, the view being empty.
SELECT COUNT(OrderQuantity)
FROM dbo.vw_LateProductionOrders
;


-- --------------------------------------------------------------------------------------------------- QuantityCompleted
-- how many rows carry a QuantityCompleted; 0, the view being empty.
SELECT COUNT(QuantityCompleted)
FROM dbo.vw_LateProductionOrders
;


-- --------------------------------------------------------------------------------------------------- QuantityRemaining
-- how many rows carry a QuantityRemaining; 0, the view being empty.
SELECT COUNT(QuantityRemaining)
FROM dbo.vw_LateProductionOrders
;


-- ------------------------------------------------------------------------------------------------------ WorkCenterName
-- how many rows carry a WorkCenterName; 0, the view being empty.
SELECT COUNT(WorkCenterName)
FROM dbo.vw_LateProductionOrders
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- how many rows carry a StartDate; 0, the view being empty.
SELECT COUNT(StartDate)
FROM dbo.vw_LateProductionOrders
;


-- ----------------------------------------------------------------------------------------------- PlannedCompletionDate
-- how many rows carry a PlannedCompletionDate; 0, the view being empty.
SELECT COUNT(PlannedCompletionDate)
FROM dbo.vw_LateProductionOrders
;


-- ------------------------------------------------------------------------------------------------------------ DaysLate
-- how many rows carry a DaysLate; 0, the view being empty.
SELECT COUNT(DaysLate)
FROM dbo.vw_LateProductionOrders
;


-- ------------------------------------------------------------------------------------------------------------ Priority
-- how many rows carry a Priority; 0, the view being empty.
SELECT COUNT(Priority)
FROM dbo.vw_LateProductionOrders
;


-- ---------------------------------------------------------------------------------------------------- LatenessSeverity
-- how many rows carry a LatenessSeverity; 0, the view being empty.
SELECT COUNT(LatenessSeverity)
FROM dbo.vw_LateProductionOrders
;


-- --------------------------------------------------------------------------------------------------------- ValueAtRisk
-- how many rows carry a ValueAtRisk; 0, the view being empty.
SELECT COUNT(ValueAtRisk)
FROM dbo.vw_LateProductionOrders
;

