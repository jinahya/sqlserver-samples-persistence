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
-- Profiling queries on the view dbo.vw_RawMaterialUsage, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is empty on the installed data: dbo.InventoryTransaction has no rows.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_RawMaterialUsage.sql
--


-- ---------------------------------------------------------------------------------------------------------------- Year
-- how many rows; 0. The view reads InventoryTransaction, which is empty.
SELECT COUNT(*)
FROM dbo.vw_RawMaterialUsage
;

-- how many inventory transactions there are; 0. The stock in Inventory was loaded as it is, with no transaction
-- behind it, and the 5 received purchase orders posted none.
SELECT COUNT(*)
FROM dbo.InventoryTransaction
;


-- --------------------------------------------------------------------------------------------------------------- Month
-- how many rows carry a Month; 0, the view being empty.
SELECT COUNT(Month)
FROM dbo.vw_RawMaterialUsage
;


-- ------------------------------------------------------------------------------------------------------------- Quarter
-- how many rows carry a Quarter; 0, the view being empty.
SELECT COUNT(Quarter)
FROM dbo.vw_RawMaterialUsage
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- how many rows carry a ItemType; 0, the view being empty.
SELECT COUNT(ItemType)
FROM dbo.vw_RawMaterialUsage
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows carry a ItemCode; 0, the view being empty.
SELECT COUNT(ItemCode)
FROM dbo.vw_RawMaterialUsage
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- how many rows carry a ItemName; 0, the view being empty.
SELECT COUNT(ItemName)
FROM dbo.vw_RawMaterialUsage
;


-- ------------------------------------------------------------------------------------------------------------ UnitCode
-- how many rows carry a UnitCode; 0, the view being empty.
SELECT COUNT(UnitCode)
FROM dbo.vw_RawMaterialUsage
;


-- ---------------------------------------------------------------------------------------------------------- TotalUsage
-- how many rows carry a TotalUsage; 0, the view being empty.
SELECT COUNT(TotalUsage)
FROM dbo.vw_RawMaterialUsage
;


-- ------------------------------------------------------------------------------------------------------- TotalReceipts
-- how many rows carry a TotalReceipts; 0, the view being empty.
SELECT COUNT(TotalReceipts)
FROM dbo.vw_RawMaterialUsage
;


-- ------------------------------------------------------------------------------------------------------ NumberOfIssues
-- how many rows carry a NumberOfIssues; 0, the view being empty.
SELECT COUNT(NumberOfIssues)
FROM dbo.vw_RawMaterialUsage
;


-- ---------------------------------------------------------------------------------------------------- AvgIssueQuantity
-- how many rows carry a AvgIssueQuantity; 0, the view being empty.
SELECT COUNT(AvgIssueQuantity)
FROM dbo.vw_RawMaterialUsage
;


-- ----------------------------------------------------------------------------------------------------- TotalUsageValue
-- how many rows carry a TotalUsageValue; 0, the view being empty.
SELECT COUNT(TotalUsageValue)
FROM dbo.vw_RawMaterialUsage
;


-- --------------------------------------------------------------------------------------------------------- AvgUnitCost
-- how many rows carry a AvgUnitCost; 0, the view being empty.
SELECT COUNT(AvgUnitCost)
FROM dbo.vw_RawMaterialUsage
;

