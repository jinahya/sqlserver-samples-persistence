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
-- Profiling queries on the view dbo.vw_BOMExplosion, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view walks BillOfMaterials recursively from every parent, active lines only: a row per path from a parent down
-- to a component, at any depth.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_BOMExplosion.sql
--


-- -------------------------------------------------------------------------------------------------------- ParentItemID
-- how many rows, and how many parents; 144 rows of 21 parents, the 15 components and the 6 finished goods.
SELECT COUNT(*), COUNT(DISTINCT ParentItemID)
FROM dbo.vw_BOMExplosion
;


-- ------------------------------------------------------------------------------------------------------ ParentItemCode
-- a ParentItemCode other than the code of the ParentItemID.
-- no rows: the view copies it from Items.
SELECT DISTINCT v.ParentItemID, v.ParentItemCode
FROM dbo.vw_BOMExplosion v
         JOIN dbo.Items i ON i.ItemID = v.ParentItemID
WHERE v.ParentItemCode <> i.ItemCode
;


-- ------------------------------------------------------------------------------------------------------ ParentItemName
-- how many ParentItemName values are NULL, and the shortest and the longest; none, 17 and 36 characters.
SELECT SUM(CASE WHEN ParentItemName IS NULL THEN 1 ELSE 0 END), MIN(LEN(ParentItemName)), MAX(LEN(ParentItemName))
FROM dbo.vw_BOMExplosion
;


-- ----------------------------------------------------------------------------------------------------- ComponentItemID
-- the components reached, by their type; 14 components in 18 rows and 21 raw materials in 126.
SELECT ComponentType, COUNT(*) AS rows, COUNT(DISTINCT ComponentItemID) AS components
FROM dbo.vw_BOMExplosion
GROUP BY ComponentType
ORDER BY ComponentType ASC
;

-- a parent which reaches the same component by two paths; 7 pairs, each a finished good whose two components use
-- the same fabric or fill, FG-FUT-001 reaching RM-FAB-001 through its pillow and its mattress.
SELECT ParentItemCode, ComponentItemCode, COUNT(*) AS paths
FROM dbo.vw_BOMExplosion
GROUP BY ParentItemCode, ComponentItemCode
HAVING COUNT(*) > 1
ORDER BY paths DESC, ParentItemCode ASC, ComponentItemCode ASC
;


-- --------------------------------------------------------------------------------------------------- ComponentItemCode
-- a ComponentItemCode other than the code of the ComponentItemID.
-- no rows: the view copies it from Items.
SELECT DISTINCT v.ComponentItemID, v.ComponentItemCode
FROM dbo.vw_BOMExplosion v
         JOIN dbo.Items i ON i.ItemID = v.ComponentItemID
WHERE v.ComponentItemCode <> i.ItemCode
;


-- --------------------------------------------------------------------------------------------------- ComponentItemName
-- how many ComponentItemName values are NULL, and the shortest and the longest; none, 11 and 32 characters.
SELECT SUM(CASE WHEN ComponentItemName IS NULL THEN 1 ELSE 0 END), MIN(LEN(ComponentItemName)),
       MAX(LEN(ComponentItemName))
FROM dbo.vw_BOMExplosion
;


-- ------------------------------------------------------------------------------------------------------- ComponentType
-- the types of the parent and of the component, by Level; at Level 1 components to raw materials (57) and finished
-- goods to components (18), at Level 2 finished goods to raw materials (69).
SELECT v.Level, t.TypeName AS parent_type, v.ComponentType, COUNT(*) AS rows
FROM dbo.vw_BOMExplosion v
         JOIN dbo.Items p ON p.ItemID = v.ParentItemID
         JOIN dbo.ItemType t ON t.ItemTypeID = p.ItemTypeID
GROUP BY v.Level, t.TypeName, v.ComponentType
ORDER BY v.Level ASC, parent_type ASC
;


-- --------------------------------------------------------------------------------------------------------------- Level
-- the rows of each Level; 75 at 1 and 69 at 2, the bills are two levels deep.
SELECT Level, COUNT(*) AS rows
FROM dbo.vw_BOMExplosion
GROUP BY Level
ORDER BY Level ASC
;

-- the rows of Level 1 against the active lines of BillOfMaterials; 75 and 75, a row per line.
SELECT (SELECT COUNT(*) FROM dbo.vw_BOMExplosion WHERE Level = 1) AS level_1,
       (SELECT COUNT(*) FROM dbo.BillOfMaterials WHERE IsActive = 1) AS active_lines
;


-- --------------------------------------------------------------------------------------------------- EffectiveQuantity
-- a Level 1 row whose EffectiveQuantity is not its line's Quantity grossed up by the ScrapRate.
-- no rows: the view computes it so.
SELECT v.ParentItemID, v.ComponentItemID, v.EffectiveQuantity, b.Quantity, b.ScrapRate
FROM dbo.vw_BOMExplosion v
         JOIN dbo.BillOfMaterials b ON b.ParentItemID = v.ParentItemID AND b.ComponentItemID = v.ComponentItemID
WHERE v.Level = 1
  AND v.EffectiveQuantity <> CAST(b.Quantity * (1 + b.ScrapRate / 100) AS decimal(18, 4))
;

-- the smallest and the largest EffectiveQuantity, by Level; .5500 to 28.8400 at 1, .5528 to 28.9842 at 2.
SELECT Level, MIN(EffectiveQuantity), MAX(EffectiveQuantity)
FROM dbo.vw_BOMExplosion
GROUP BY Level
ORDER BY Level ASC
;


-- ------------------------------------------------------------------------------------------------------------ UnitCode
-- the units, by the type of the component; EA for the components, EA, LB and YD for the raw materials.
SELECT ComponentType, UnitCode, COUNT(*) AS rows
FROM dbo.vw_BOMExplosion
GROUP BY ComponentType, UnitCode
ORDER BY ComponentType ASC, UnitCode ASC
;


-- -------------------------------------------------------------------------------------------------------- StandardCost
-- a StandardCost other than the component's own.
-- no rows: the view copies it from Items.
SELECT DISTINCT v.ComponentItemID, v.StandardCost
FROM dbo.vw_BOMExplosion v
         JOIN dbo.Items i ON i.ItemID = v.ComponentItemID
WHERE v.StandardCost <> i.StandardCost
;


-- -------------------------------------------------------------------------------------------------------- ExtendedCost
-- the smallest and the largest ExtendedCost, by Level; 8.0000 to 380.2418 at 1, 8.0400 to 253.6118 at 2.
SELECT Level, MIN(ExtendedCost), MAX(ExtendedCost)
FROM dbo.vw_BOMExplosion
GROUP BY Level
ORDER BY Level ASC
;

-- a row whose ExtendedCost is more than a cent off the EffectiveQuantity at the StandardCost; the two are rounded
-- separately.
-- no rows: every ExtendedCost agrees.
SELECT ParentItemCode, ComponentItemCode, EffectiveQuantity, StandardCost, ExtendedCost
FROM dbo.vw_BOMExplosion
WHERE ABS(ExtendedCost - EffectiveQuantity * StandardCost) > 0.01
;


-- ------------------------------------------------------------------------------------------------------------- BOMPath
-- a BOMPath which does not start at the parent, end at the component, or has not Level + 1 codes.
-- no rows: every path reads parent > ... > component, Level steps long.
SELECT ParentItemCode, ComponentItemCode, Level, BOMPath
FROM dbo.vw_BOMExplosion
WHERE BOMPath NOT LIKE ParentItemCode + ' > %'
   OR BOMPath NOT LIKE '% > ' + ComponentItemCode
   OR (LEN(BOMPath) - LEN(REPLACE(BOMPath, ' > ', ''))) / 3 <> Level
;

-- the longest BOMPath; FG-FUT-001 > COMP-FRM-001 > RM-METAL-001, 40 characters.
SELECT TOP (1) BOMPath, LEN(BOMPath) AS length
FROM dbo.vw_BOMExplosion
ORDER BY LEN(BOMPath) DESC, BOMPath ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the material cost of each finished good, the ExtendedCost of its raw materials at Level 2, against what its
-- components cost at Level 1; the two agree to a few ten-thousandths, 314.3468 against 314.3469 for FG-FUT-001,
-- so a sum over every Level, as vw_CostRollUp takes, counts the material twice.
SELECT ParentItemCode,
       SUM(CASE WHEN Level = 1 THEN ExtendedCost END) AS components,
       SUM(CASE WHEN Level = 2 THEN ExtendedCost END) AS raw_materials
FROM dbo.vw_BOMExplosion
WHERE ParentItemCode LIKE 'FG-%'
GROUP BY ParentItemCode
ORDER BY ParentItemCode ASC
;

