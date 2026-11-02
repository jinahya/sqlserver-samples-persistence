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
-- Profiling queries on the view dbo.vw_CostRollUp, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is a row per active item, its StandardCost against the material cost vw_BOMExplosion rolls up from every
-- level beneath it.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_CostRollUp.sql
--


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows, against the active items, and how many distinct ItemCodes; 45, 45 and 45, a row per item.
SELECT (SELECT COUNT(*) FROM dbo.vw_CostRollUp) AS rows,
       (SELECT COUNT(*) FROM dbo.Items WHERE IsActive = 1) AS active_items,
       (SELECT COUNT(DISTINCT ItemCode) FROM dbo.vw_CostRollUp) AS codes
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- how many ItemName values are NULL, and the shortest and the longest; none, 11 and 36 characters.
SELECT SUM(CASE WHEN ItemName IS NULL THEN 1 ELSE 0 END), MIN(LEN(ItemName)), MAX(LEN(ItemName))
FROM dbo.vw_CostRollUp
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- the rows of each type; 15 components, 6 finished goods and 24 raw materials.
SELECT ItemType, COUNT(*) AS items
FROM dbo.vw_CostRollUp
GROUP BY ItemType
ORDER BY ItemType ASC
;


-- ------------------------------------------------------------------------------------------------- CurrentStandardCost
-- the smallest and the largest CurrentStandardCost of each type; components 19.6350 to 378.3500, finished goods
-- .0000, raw materials 2.7500 to 22.0000.
SELECT ItemType, MIN(CurrentStandardCost), MAX(CurrentStandardCost)
FROM dbo.vw_CostRollUp
GROUP BY ItemType
ORDER BY ItemType ASC
;


-- ---------------------------------------------------------------------------------------------- CalculatedMaterialCost
-- the smallest and the largest CalculatedMaterialCost of each type; components 19.6350 to 378.3500, their
-- StandardCost exactly, finished goods 628.6937 to 1449.1838, raw materials .0000.
SELECT ItemType, MIN(CalculatedMaterialCost), MAX(CalculatedMaterialCost)
FROM dbo.vw_CostRollUp
GROUP BY ItemType
ORDER BY ItemType ASC
;


-- ---------------------------------------------------------------------------------------------------- LaborAndOverhead
-- the smallest and the largest LaborAndOverhead of each type, the CurrentStandardCost less the
-- CalculatedMaterialCost; .0000 for the components, -1449.1838 to -628.6937 for the finished goods, and the whole
-- StandardCost, 2.7500 to 22.0000, for the raw materials, which have no bill.
SELECT ItemType, MIN(LaborAndOverhead), MAX(LaborAndOverhead)
FROM dbo.vw_CostRollUp
GROUP BY ItemType
ORDER BY ItemType ASC
;

-- an item whose LaborAndOverhead is negative; all 6 finished goods, whose StandardCost is .0000.
SELECT ItemCode, CurrentStandardCost, CalculatedMaterialCost, LaborAndOverhead
FROM dbo.vw_CostRollUp
WHERE LaborAndOverhead < 0
ORDER BY ItemCode ASC
;


-- ------------------------------------------------------------------------------------------------------ ComponentCount
-- the ComponentCount of each type, every distinct component at any level; 2 to 8 for the components, 12 to 15 for
-- the finished goods, 0 for the raw materials.
SELECT ItemType, MIN(ComponentCount), MAX(ComponentCount)
FROM dbo.vw_CostRollUp
GROUP BY ItemType
ORDER BY ItemType ASC
;


-- ----------------------------------------------------------------------------------------------------------- ListPrice
-- the smallest and the largest ListPrice of each type; .0000 for the components and the raw materials, 299.9900 to
-- 899.9900 for the finished goods.
SELECT ItemType, MIN(ListPrice), MAX(ListPrice)
FROM dbo.vw_CostRollUp
GROUP BY ItemType
ORDER BY ItemType ASC
;


-- --------------------------------------------------------------------------------------------------------- GrossProfit
-- the smallest and the largest GrossProfit of each type, the ListPrice less the CurrentStandardCost; negative for
-- the components and the raw materials, which are not sold, and the whole ListPrice for the finished goods.
SELECT ItemType, MIN(GrossProfit), MAX(GrossProfit)
FROM dbo.vw_CostRollUp
GROUP BY ItemType
ORDER BY ItemType ASC
;


-- -------------------------------------------------------------------------------------------------- GrossMarginPercent
-- the GrossMarginPercent of each type, NULL where the ListPrice is zero; NULL for the 15 components and the 24 raw
-- materials, 100.00 for every finished good, whose StandardCost is .0000.
SELECT ItemType, COUNT(*) - COUNT(GrossMarginPercent) AS nulls, MIN(GrossMarginPercent), MAX(GrossMarginPercent)
FROM dbo.vw_CostRollUp
GROUP BY ItemType
ORDER BY ItemType ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the CalculatedMaterialCost of each finished good against the ExtendedCost of its raw materials alone; the view
-- sums vw_BOMExplosion over every level, the components and the raw materials they are made of, so the material is
-- counted twice; 2.00 times for all 6, 628.6937 against 314.3469 for FG-FUT-001.
SELECT c.ItemCode, c.CalculatedMaterialCost, SUM(b.ExtendedCost) AS raw_materials,
       CAST(c.CalculatedMaterialCost / SUM(b.ExtendedCost) AS decimal(5, 2)) AS ratio
FROM dbo.vw_CostRollUp c
         JOIN dbo.vw_BOMExplosion b ON b.ParentItemCode = c.ItemCode AND b.ComponentType = 'Raw Material'
WHERE c.ItemType = 'Finished Goods'
GROUP BY c.ItemCode, c.CalculatedMaterialCost
ORDER BY c.ItemCode ASC
;

