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
-- Profiling queries on dbo.BillOfMaterials, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_BillOfMaterials.sql
--


-- --------------------------------------------------------------------------------------------------------------- BOMID
-- how many rows, and the smallest and the largest BOMID; 75 lines, 1 to 75.
SELECT COUNT(*), MIN(BOMID), MAX(BOMID)
FROM dbo.BillOfMaterials
;


-- -------------------------------------------------------------------------------------------------------- ParentItemID
-- the parents by their type, and how many lines each has; 15 components with 57 lines, 2 to 8 each, and 6 finished
-- goods with 18, 3 each.
SELECT p.ItemTypeID, COUNT(DISTINCT b.ParentItemID) AS parents, COUNT(*) AS lines,
       MIN(c.n) AS fewest, MAX(c.n) AS most
FROM dbo.BillOfMaterials b
         JOIN dbo.Items p ON p.ItemID = b.ParentItemID
         JOIN (SELECT ParentItemID, COUNT(*) AS n FROM dbo.BillOfMaterials GROUP BY ParentItemID) c
              ON c.ParentItemID = b.ParentItemID
GROUP BY p.ItemTypeID
ORDER BY p.ItemTypeID ASC
;


-- ----------------------------------------------------------------------------------------------------- ComponentItemID
-- the types of the parent and of the component a line joins; a component is made of raw materials (57 lines), a
-- finished good of components (18), so the bills are two levels deep.
SELECT p.ItemTypeID AS parent_type, c.ItemTypeID AS component_type, COUNT(*) AS lines
FROM dbo.BillOfMaterials b
         JOIN dbo.Items p ON p.ItemID = b.ParentItemID
         JOIN dbo.Items c ON c.ItemID = b.ComponentItemID
GROUP BY p.ItemTypeID, c.ItemTypeID
ORDER BY p.ItemTypeID ASC, c.ItemTypeID ASC
;

-- CHK_BOM_NotSelf keeps an item out of its own bill.
-- no rows: no line has the parent as its component.
SELECT BOMID, ParentItemID, ComponentItemID
FROM dbo.BillOfMaterials
WHERE ParentItemID = ComponentItemID
;

-- nothing keeps a component to one line per parent.
-- no rows: no parent lists a component twice.
SELECT ParentItemID, ComponentItemID, COUNT(*) AS lines
FROM dbo.BillOfMaterials
GROUP BY ParentItemID, ComponentItemID
HAVING COUNT(*) > 1
;

-- a raw material never used as a component; RM-FAB-007, RM-FAB-008 and RM-HARD-002.
SELECT i.ItemID, i.ItemCode
FROM dbo.Items i
WHERE i.ItemTypeID = 1
  AND NOT EXISTS (SELECT 1
                  FROM dbo.BillOfMaterials b
                  WHERE b.ComponentItemID = i.ItemID)
ORDER BY i.ItemID ASC
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the smallest and the largest Quantity, and how many are zero or less; .5000, 28.0000 and none.
SELECT MIN(Quantity), MAX(Quantity), SUM(CASE WHEN Quantity <= 0 THEN 1 ELSE 0 END)
FROM dbo.BillOfMaterials
;


-- -------------------------------------------------------------------------------------------------------------- UnitID
-- a line measured in another unit than its component.
-- no rows: every line takes the component's own unit.
SELECT b.BOMID, b.UnitID, c.ItemID, c.UnitID AS component_unit
FROM dbo.BillOfMaterials b
         JOIN dbo.Items c ON c.ItemID = b.ComponentItemID
WHERE b.UnitID <> c.UnitID
;


-- ----------------------------------------------------------------------------------------------------------- ScrapRate
-- the values ScrapRate takes, a percentage; seven values from .00 to 10.00, 5.00 the most common with 22 lines,
-- none NULL.
SELECT ScrapRate, COUNT(*) AS lines
FROM dbo.BillOfMaterials
GROUP BY ScrapRate
ORDER BY ScrapRate ASC
;


-- ------------------------------------------------------------------------------------------------------- EffectiveDate
-- how many EffectiveDate values are NULL, and how many are not the day the database was created; none, and none:
-- the default CONVERT(date, GETDATE()) filled every one in while the load script ran.
SELECT SUM(CASE WHEN x.EffectiveDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.EffectiveDate <> CAST(d.create_date AS date) THEN 1 ELSE 0 END)
FROM dbo.BillOfMaterials x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- how many EndDate values are NULL, of all the rows; 75 of 75, no line has ended.
SELECT SUM(CASE WHEN EndDate IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.BillOfMaterials
;


-- ------------------------------------------------------------------------------------------------------------ BOMLevel
-- the values BOMLevel takes, against the type of the parent; 0 for the 18 lines of finished goods, 1 for the 57 of
-- components.
SELECT b.BOMLevel, p.ItemTypeID, COUNT(*) AS lines
FROM dbo.BillOfMaterials b
         JOIN dbo.Items p ON p.ItemID = b.ParentItemID
GROUP BY b.BOMLevel, p.ItemTypeID
ORDER BY b.BOMLevel ASC, p.ItemTypeID ASC
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes; all 75 lines are active.
SELECT IsActive, COUNT(*) AS lines
FROM dbo.BillOfMaterials
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- --------------------------------------------------------------------------------------------------------------- Notes
-- how many Notes values are NULL, of all the rows; 75 of 75.
SELECT SUM(CASE WHEN Notes IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.BillOfMaterials
;


-- --------------------------------------------------------------------------------------------------------- CreatedDate
-- how many CreatedDate values are NULL, and how many fall outside the five minutes after the database was created;
-- none, and none: the default GETDATE() filled every one in while the load script ran, so it is the moment of
-- the load, not of a business event, and moves with every load.
SELECT SUM(CASE WHEN x.CreatedDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.CreatedDate NOT BETWEEN d.create_date AND DATEADD(MINUTE, 5, d.create_date) THEN 1 ELSE 0 END)
FROM dbo.BillOfMaterials x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- a line modified at another moment than it was created.
-- no rows: no line has been modified since the load.
SELECT BOMID, CreatedDate, ModifiedDate
FROM dbo.BillOfMaterials
WHERE ModifiedDate IS NULL
   OR ModifiedDate <> CreatedDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a component's StandardCost against the sum of its lines, each Quantity grossed up by the ScrapRate at the
-- StandardCost of the raw material.
-- no rows: the StandardCost of every component is exactly its rolled-up material cost.
SELECT p.ItemID, p.ItemCode, p.StandardCost,
       CAST(SUM(b.Quantity * (1 + b.ScrapRate / 100) * c.StandardCost) AS decimal(18, 4)) AS rolled_up
FROM dbo.BillOfMaterials b
         JOIN dbo.Items p ON p.ItemID = b.ParentItemID
         JOIN dbo.Items c ON c.ItemID = b.ComponentItemID
WHERE p.ItemTypeID = 2
GROUP BY p.ItemID, p.ItemCode, p.StandardCost
HAVING p.StandardCost <> CAST(SUM(b.Quantity * (1 + b.ScrapRate / 100) * c.StandardCost) AS decimal(18, 4))
ORDER BY p.ItemID ASC
;

