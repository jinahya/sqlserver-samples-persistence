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
-- Profiling queries on the view dbo.vw_WhereUsed, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_WhereUsed.sql
--


-- ----------------------------------------------------------------------------------------------------- ComponentItemID
-- how many rows, and how many distinct UsagePath; 144 and 144, a row per path from a component up to an item using
-- it.
SELECT COUNT(*), COUNT(DISTINCT UsagePath)
FROM dbo.vw_WhereUsed
;

-- how many ComponentItemID are NULL, and how many distinct; none, 35 of the 45 items.
SELECT SUM(CASE WHEN ComponentItemID IS NULL THEN 1 ELSE 0 END), COUNT(DISTINCT ComponentItemID)
FROM dbo.vw_WhereUsed
;

-- an item which is not a finished good, yet is used in nothing.
-- 4 rows: COMP-FRM-003, RM-FAB-007, RM-FAB-008 and RM-HARD-002.
SELECT i.ItemCode, t.TypeName
FROM dbo.Items i
         JOIN dbo.ItemType t ON t.ItemTypeID = i.ItemTypeID
WHERE t.TypeName <> 'Finished Goods'
  AND NOT EXISTS (SELECT 1
                  FROM dbo.vw_WhereUsed v
                  WHERE v.ComponentItemID = i.ItemID)
ORDER BY i.ItemCode ASC
;

-- a component and a parent which come in more than one row; a component reaching its parent by more than one path.
-- 7 rows, each with 2 paths; for example RM-FILL-001 into FG-FUT-001 through COMP-MAT-001 and COMP-PIL-001.
SELECT ComponentItemCode, ParentItemCode, COUNT(*) AS paths
FROM dbo.vw_WhereUsed
GROUP BY ComponentItemCode, ParentItemCode
HAVING COUNT(*) > 1
ORDER BY ComponentItemCode ASC, ParentItemCode ASC
;


-- --------------------------------------------------------------------------------------------------- ComponentItemCode
-- the components used in the most rows; RM-FIN-003, RM-HARD-001 and RM-METAL-001 with 11 each.
SELECT TOP (3) ComponentItemCode, COUNT(*) AS rows
FROM dbo.vw_WhereUsed
GROUP BY ComponentItemCode
ORDER BY rows DESC, ComponentItemCode ASC
;


-- --------------------------------------------------------------------------------------------------- ComponentItemName
-- a ComponentItemCode or ComponentItemName which is not the one of its ComponentItemID.
-- no rows: each matches Items.
SELECT v.ComponentItemID, v.ComponentItemCode, v.ComponentItemName
FROM dbo.vw_WhereUsed v
         JOIN dbo.Items i ON i.ItemID = v.ComponentItemID
WHERE v.ComponentItemCode <> i.ItemCode
   OR v.ComponentItemName <> i.ItemName
;


-- -------------------------------------------------------------------------------------------------------- ParentItemID
-- how many ParentItemID are NULL, and how many distinct; none, 21.
SELECT SUM(CASE WHEN ParentItemID IS NULL THEN 1 ELSE 0 END), COUNT(DISTINCT ParentItemID)
FROM dbo.vw_WhereUsed
;

-- a row whose component is its own parent; the recursion would not end on a cycle.
-- no rows: no item uses itself.
SELECT ComponentItemCode, ParentItemCode, UsagePath
FROM dbo.vw_WhereUsed
WHERE ComponentItemID = ParentItemID
;


-- ------------------------------------------------------------------------------------------------------ ParentItemCode
-- the parents with the most rows; FG-FUT-002, FG-FUT-003 and FG-FUT-005 with 15 each.
SELECT TOP (3) ParentItemCode, COUNT(*) AS rows
FROM dbo.vw_WhereUsed
GROUP BY ParentItemCode
ORDER BY rows DESC, ParentItemCode ASC
;


-- ------------------------------------------------------------------------------------------------------ ParentItemName
-- a ParentItemCode or ParentItemName which is not the one of its ParentItemID.
-- no rows: each matches Items.
SELECT v.ParentItemID, v.ParentItemCode, v.ParentItemName
FROM dbo.vw_WhereUsed v
         JOIN dbo.Items i ON i.ItemID = v.ParentItemID
WHERE v.ParentItemCode <> i.ItemCode
   OR v.ParentItemName <> i.ItemName
;


-- ---------------------------------------------------------------------------------------------------------- ParentType
-- the types of the parents; Finished Goods 87, Component 57.
SELECT ParentType, COUNT(*) AS rows
FROM dbo.vw_WhereUsed
GROUP BY ParentType
ORDER BY ParentType ASC
;

-- a ParentType which is not the type of the parent.
-- no rows: each matches ItemType.
SELECT v.ParentItemCode, v.ParentType
FROM dbo.vw_WhereUsed v
         JOIN dbo.Items p ON p.ItemID = v.ParentItemID
         JOIN dbo.ItemType t ON t.ItemTypeID = p.ItemTypeID
WHERE v.ParentType <> t.TypeName
;


-- --------------------------------------------------------------------------------------------------------------- Level
-- the levels; 75 rows at 1, a row per active BillOfMaterials line, and 69 at 2. The bill is two levels deep.
SELECT Level, COUNT(*) AS rows
FROM dbo.vw_WhereUsed
GROUP BY Level
ORDER BY Level ASC
;

-- a row at level 1 which is not an active BillOfMaterials line with the same quantity.
-- no rows: level 1 is the bill itself.
SELECT v.ComponentItemCode, v.ParentItemCode, v.Quantity
FROM dbo.vw_WhereUsed v
WHERE v.Level = 1
  AND NOT EXISTS (SELECT 1
                  FROM dbo.BillOfMaterials b
                  WHERE b.ComponentItemID = v.ComponentItemID
                    AND b.ParentItemID = v.ParentItemID
                    AND b.IsActive = 1
                    AND b.Quantity = v.Quantity)
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the smallest and the largest Quantity; .5000 and 28.0000.
SELECT MIN(Quantity), MAX(Quantity)
FROM dbo.vw_WhereUsed
;

-- a row at level 2 whose Quantity is not the product of the quantities of its two bill lines.
-- no rows: the quantities multiply down the path.
SELECT v.UsagePath, v.Quantity
FROM dbo.vw_WhereUsed v
WHERE v.Level = 2
  AND NOT EXISTS (SELECT 1
                  FROM dbo.BillOfMaterials b1
                           JOIN dbo.Items m ON m.ItemID = b1.ParentItemID
                           JOIN dbo.BillOfMaterials b2 ON b2.ComponentItemID = b1.ParentItemID
                  WHERE b1.ComponentItemID = v.ComponentItemID
                    AND b2.ParentItemID = v.ParentItemID
                    AND v.UsagePath = v.ComponentItemCode + ' used in ' + m.ItemCode + ' > ' + v.ParentItemCode
                    AND v.Quantity = CAST(b1.Quantity * b2.Quantity AS DECIMAL(18, 4)))
;


-- ------------------------------------------------------------------------------------------------------------ UnitCode
-- the units; EA 124, LB 10 and YD 10.
SELECT UnitCode, COUNT(*) AS rows
FROM dbo.vw_WhereUsed
GROUP BY UnitCode
ORDER BY rows DESC, UnitCode ASC
;

-- a UnitCode which is not the unit of the component. At level 2 the view takes the unit of the upper bill line,
-- the one of the intermediate component, so a fabric in yards or a fill in pounds shows as EA.
-- 2 rows, both at level 2: LB shown as EA on 12 rows, YD shown as EA on 12. For example, RM-FAB-001 used in
-- COMP-MAT-001 > FG-FUT-001 reads 6.5000 EA, meaning yards.
SELECT v.Level, cu.UnitCode AS component_unit, v.UnitCode, COUNT(*) AS rows
FROM dbo.vw_WhereUsed v
         JOIN dbo.Items c ON c.ItemID = v.ComponentItemID
         JOIN dbo.UnitOfMeasure cu ON cu.UnitID = c.UnitID
WHERE cu.UnitCode <> v.UnitCode
GROUP BY v.Level, cu.UnitCode, v.UnitCode
ORDER BY cu.UnitCode ASC
;


-- ----------------------------------------------------------------------------------------------------------- UsagePath
-- the shortest and the longest UsagePath; 31 and 46 characters.
SELECT MIN(LEN(UsagePath)), MAX(LEN(UsagePath))
FROM dbo.vw_WhereUsed
;

-- a UsagePath which does not start with its ComponentItemCode and end with its ParentItemCode.
-- no rows: every path runs from the component to the parent.
SELECT ComponentItemCode, ParentItemCode, UsagePath
FROM dbo.vw_WhereUsed
WHERE UsagePath NOT LIKE ComponentItemCode + ' used in %'
   OR UsagePath NOT LIKE '%' + ParentItemCode
;

