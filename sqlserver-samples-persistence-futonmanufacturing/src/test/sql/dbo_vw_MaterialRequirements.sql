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
-- Profiling queries on the view dbo.vw_MaterialRequirements, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is empty on the installed data: dbo.ProductionOrder has no rows.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_MaterialRequirements.sql
--


-- ----------------------------------------------------------------------------------------------------- WorkOrderNumber
-- how many rows; 0. The view reads the open orders of ProductionOrder, which is empty.
SELECT COUNT(*)
FROM dbo.vw_MaterialRequirements
;

-- how many production orders there are; 0, none has been planned.
SELECT COUNT(*)
FROM dbo.ProductionOrder
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- how many rows carry a Status; 0, the view being empty.
SELECT COUNT(Status)
FROM dbo.vw_MaterialRequirements
;


-- ----------------------------------------------------------------------------------------------- PlannedCompletionDate
-- how many rows carry a PlannedCompletionDate; 0, the view being empty.
SELECT COUNT(PlannedCompletionDate)
FROM dbo.vw_MaterialRequirements
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows carry a ItemCode; 0, the view being empty.
SELECT COUNT(ItemCode)
FROM dbo.vw_MaterialRequirements
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- how many rows carry a ItemName; 0, the view being empty.
SELECT COUNT(ItemName)
FROM dbo.vw_MaterialRequirements
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- how many rows carry a ItemType; 0, the view being empty.
SELECT COUNT(ItemType)
FROM dbo.vw_MaterialRequirements
;


-- ---------------------------------------------------------------------------------------------------- RequiredQuantity
-- how many rows carry a RequiredQuantity; 0, the view being empty.
SELECT COUNT(RequiredQuantity)
FROM dbo.vw_MaterialRequirements
;


-- --------------------------------------------------------------------------------------------------- AvailableQuantity
-- how many rows carry a AvailableQuantity; 0, the view being empty.
SELECT COUNT(AvailableQuantity)
FROM dbo.vw_MaterialRequirements
;


-- ---------------------------------------------------------------------------------------------------- ShortageQuantity
-- how many rows carry a ShortageQuantity; 0, the view being empty.
SELECT COUNT(ShortageQuantity)
FROM dbo.vw_MaterialRequirements
;


-- -------------------------------------------------------------------------------------------------- AvailabilityStatus
-- how many rows carry a AvailabilityStatus; 0, the view being empty.
SELECT COUNT(AvailabilityStatus)
FROM dbo.vw_MaterialRequirements
;


-- ------------------------------------------------------------------------------------------------------------ UnitCode
-- how many rows carry a UnitCode; 0, the view being empty.
SELECT COUNT(UnitCode)
FROM dbo.vw_MaterialRequirements
;

