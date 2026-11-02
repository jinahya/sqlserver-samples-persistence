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
-- Profiling queries on the view dbo.vw_ScrapWasteAnalysis, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is empty on the installed data: dbo.ProductionCompletion has no rows.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_ScrapWasteAnalysis.sql
--


-- ------------------------------------------------------------------------------------------------------ CompletionDate
-- how many rows; 0. The view reads ProductionCompletion, which is empty.
SELECT COUNT(*)
FROM dbo.vw_ScrapWasteAnalysis
;

-- how many production completions there are; 0, no production order having been planned.
SELECT COUNT(*), (SELECT COUNT(*) FROM dbo.ProductionOrder)
FROM dbo.ProductionCompletion
;


-- ---------------------------------------------------------------------------------------------------------------- Year
-- how many rows carry a Year; 0, the view being empty.
SELECT COUNT(Year)
FROM dbo.vw_ScrapWasteAnalysis
;


-- --------------------------------------------------------------------------------------------------------------- Month
-- how many rows carry a Month; 0, the view being empty.
SELECT COUNT(Month)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ------------------------------------------------------------------------------------------------------ WorkCenterName
-- how many rows carry a WorkCenterName; 0, the view being empty.
SELECT COUNT(WorkCenterName)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows carry a ItemCode; 0, the view being empty.
SELECT COUNT(ItemCode)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- how many rows carry a ItemName; 0, the view being empty.
SELECT COUNT(ItemName)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- how many rows carry a ItemType; 0, the view being empty.
SELECT COUNT(ItemType)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ------------------------------------------------------------------------------------------------------ TotalCompleted
-- how many rows carry a TotalCompleted; 0, the view being empty.
SELECT COUNT(TotalCompleted)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ------------------------------------------------------------------------------------------------------- TotalScrapped
-- how many rows carry a TotalScrapped; 0, the view being empty.
SELECT COUNT(TotalScrapped)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ------------------------------------------------------------------------------------------------------- TotalProduced
-- how many rows carry a TotalProduced; 0, the view being empty.
SELECT COUNT(TotalProduced)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ----------------------------------------------------------------------------------------------------------- ScrapRate
-- how many rows carry a ScrapRate; 0, the view being empty.
SELECT COUNT(ScrapRate)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ---------------------------------------------------------------------------------------------------------- ScrapValue
-- how many rows carry a ScrapValue; 0, the view being empty.
SELECT COUNT(ScrapValue)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ------------------------------------------------------------------------------------------------------ NumberOfOrders
-- how many rows carry a NumberOfOrders; 0, the view being empty.
SELECT COUNT(NumberOfOrders)
FROM dbo.vw_ScrapWasteAnalysis
;


-- --------------------------------------------------------------------------------------------------------- AvgUnitCost
-- how many rows carry a AvgUnitCost; 0, the view being empty.
SELECT COUNT(AvgUnitCost)
FROM dbo.vw_ScrapWasteAnalysis
;


-- ---------------------------------------------------------------------------------------------------------- ScrapLevel
-- how many rows carry a ScrapLevel; 0, the view being empty.
SELECT COUNT(ScrapLevel)
FROM dbo.vw_ScrapWasteAnalysis
;

