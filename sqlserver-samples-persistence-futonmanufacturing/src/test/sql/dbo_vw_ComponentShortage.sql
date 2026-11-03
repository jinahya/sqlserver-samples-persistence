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
-- Profiling queries on the view dbo.vw_ComponentShortage, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is a row per component the open production orders (Planned, Released, InProgress) need more of than is
-- available, exploded through vw_BOMExplosion. It is empty on the installed data: the load scripts insert no
-- production order, so nothing is needed. GETDATE() only computes DaysUntilNeeded, UrgencyLevel, PossibleArrival
-- and SupplyStatus; it does not filter, so the rows depend on the data, not on the clock.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_ComponentShortage.sql
--


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows; 0: the view is empty.
SELECT COUNT(*)
FROM dbo.vw_ComponentShortage
;

-- why the view is empty: the production orders it explodes, the open ones of ProductionOrder; 0 of 0, the table
-- is empty.
SELECT COUNT(CASE WHEN Status IN ('Planned', 'Released', 'InProgress') THEN 1 END), COUNT(*)
FROM dbo.ProductionOrder
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- how many ItemName values; 0: the view is empty.
SELECT COUNT(ItemName)
FROM dbo.vw_ComponentShortage
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- how many ItemType values; 0: the view is empty.
SELECT COUNT(ItemType)
FROM dbo.vw_ComponentShortage
;


-- ---------------------------------------------------------------------------------------------------- RequiredQuantity
-- how many RequiredQuantity values; 0: the view is empty.
SELECT COUNT(RequiredQuantity)
FROM dbo.vw_ComponentShortage
;


-- --------------------------------------------------------------------------------------------------- AvailableQuantity
-- how many AvailableQuantity values; 0: the view is empty.
SELECT COUNT(AvailableQuantity)
FROM dbo.vw_ComponentShortage
;


-- ---------------------------------------------------------------------------------------------------- ShortageQuantity
-- how many ShortageQuantity values; 0: the view is empty.
SELECT COUNT(ShortageQuantity)
FROM dbo.vw_ComponentShortage
;


-- ---------------------------------------------------------------------------------------------------- EarliestNeedDate
-- how many EarliestNeedDate values; 0: the view is empty.
SELECT COUNT(EarliestNeedDate)
FROM dbo.vw_ComponentShortage
;


-- ----------------------------------------------------------------------------------------------------- DaysUntilNeeded
-- how many DaysUntilNeeded values; 0: the view is empty.
SELECT COUNT(DaysUntilNeeded)
FROM dbo.vw_ComponentShortage
;


-- ------------------------------------------------------------------------------------------------------ AffectedOrders
-- how many AffectedOrders values; 0: the view is empty.
SELECT COUNT(AffectedOrders)
FROM dbo.vw_ComponentShortage
;


-- -------------------------------------------------------------------------------------------------------- UrgencyLevel
-- how many UrgencyLevel values; 0: the view is empty.
SELECT COUNT(UrgencyLevel)
FROM dbo.vw_ComponentShortage
;


-- --------------------------------------------------------------------------------------------------- PreferredSupplier
-- how many PreferredSupplier values; 0: the view is empty.
SELECT COUNT(PreferredSupplier)
FROM dbo.vw_ComponentShortage
;


-- -------------------------------------------------------------------------------------------------------- LeadTimeDays
-- how many LeadTimeDays values; 0: the view is empty.
SELECT COUNT(LeadTimeDays)
FROM dbo.vw_ComponentShortage
;


-- ----------------------------------------------------------------------------------------------------- PossibleArrival
-- how many PossibleArrival values; 0: the view is empty.
SELECT COUNT(PossibleArrival)
FROM dbo.vw_ComponentShortage
;


-- -------------------------------------------------------------------------------------------------------- SupplyStatus
-- how many SupplyStatus values; 0: the view is empty.
SELECT COUNT(SupplyStatus)
FROM dbo.vw_ComponentShortage
;

