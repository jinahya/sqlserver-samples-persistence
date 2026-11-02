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
-- Profiling queries on dbo.Items, a section per column. Each says what it looks for and what the result should be; 'no
-- rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_Items.sql
--


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- how many rows, and the smallest and the largest ItemID; 45 items, 1 to 45.
SELECT COUNT(*), MIN(ItemID), MAX(ItemID)
FROM dbo.Items
;

-- an ItemID whose next value is missing, below the largest.
-- no rows: ItemID runs from 1 without a gap.
SELECT a.ItemID + 1 AS missing_from
FROM dbo.Items a
WHERE a.ItemID < (SELECT MAX(ItemID) FROM dbo.Items)
  AND NOT EXISTS (SELECT 1
                  FROM dbo.Items b
                  WHERE b.ItemID = a.ItemID + 1)
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- the shortest and the longest ItemCode; 10 and 12 characters, of the 50 the column allows.
SELECT MIN(LEN(ItemCode)), MAX(LEN(ItemCode))
FROM dbo.Items
;

-- an ItemCode which is blank, or carries leading or trailing spaces.
-- no rows: every ItemCode is trimmed and not blank.
SELECT ItemID, '[' + ItemCode + ']' AS ItemCode
FROM dbo.Items
WHERE TRIM(ItemCode) = ''
   OR DATALENGTH(ItemCode) <> DATALENGTH(TRIM(ItemCode))
;

-- a unique constraint keeps ItemCode unique.
-- no rows: every ItemCode is distinct.
SELECT ItemCode, COUNT(*) AS items
FROM dbo.Items
GROUP BY ItemCode
HAVING COUNT(*) > 1
;

-- the prefix of the ItemCode, up to the first dash, against the type; RM for the 24 Raw Materials, COMP for the 15
-- Components and FG for the 6 Finished Goods, so the prefix follows the type.
SELECT LEFT(i.ItemCode, CHARINDEX('-', i.ItemCode) - 1) AS prefix, t.TypeCode, COUNT(*) AS items
FROM dbo.Items i
         JOIN dbo.ItemType t ON t.ItemTypeID = i.ItemTypeID
GROUP BY LEFT(i.ItemCode, CHARINDEX('-', i.ItemCode) - 1), t.TypeCode
ORDER BY t.TypeCode ASC
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- the shortest and the longest ItemName; 11 and 36 characters, of the 255 the column allows.
SELECT MIN(LEN(ItemName)), MAX(LEN(ItemName))
FROM dbo.Items
;

-- an ItemName which is blank, or carries leading or trailing spaces.
-- no rows: every ItemName is trimmed and not blank.
SELECT ItemID, '[' + ItemName + ']' AS ItemName
FROM dbo.Items
WHERE TRIM(ItemName) = ''
   OR DATALENGTH(ItemName) <> DATALENGTH(TRIM(ItemName))
;

-- nothing keeps ItemName unique, yet no two rows share one.
-- no rows: every ItemName is distinct.
SELECT ItemName, COUNT(*) AS items
FROM dbo.Items
GROUP BY ItemName
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------------- ItemTypeID
-- the items of each type; 24 raw materials (1), 15 components (2) and 6 finished goods (3).
SELECT ItemTypeID, COUNT(*) AS items
FROM dbo.Items
GROUP BY ItemTypeID
ORDER BY ItemTypeID ASC
;


-- -------------------------------------------------------------------------------------------------------------- UnitID
-- the units the items of each type measure in; the raw materials in EA (11), LB (5) and YD (8), the components and
-- the finished goods all in EA.
SELECT i.ItemTypeID, u.UnitCode, COUNT(*) AS items
FROM dbo.Items i
         JOIN dbo.UnitOfMeasure u ON u.UnitID = i.UnitID
GROUP BY i.ItemTypeID, u.UnitCode
ORDER BY i.ItemTypeID ASC, u.UnitCode ASC
;


-- --------------------------------------------------------------------------------------------------------- Description
-- how many Description values are NULL, and the shortest and the longest of the rest; none, 22 and 68
-- characters, in an nvarchar(max).
SELECT SUM(CASE WHEN Description IS NULL THEN 1 ELSE 0 END), MIN(LEN(Description)), MAX(LEN(Description))
FROM dbo.Items
;

-- a Description which is blank, or carries leading or trailing spaces.
-- no rows: every Description is trimmed and not blank.
SELECT ItemID, '[' + Description + ']' AS Description
FROM dbo.Items
WHERE TRIM(Description) = ''
   OR DATALENGTH(Description) <> DATALENGTH(TRIM(Description))
;


-- -------------------------------------------------------------------------------------------------------- StandardCost
-- the smallest and the largest StandardCost of each type, and how many are zero; raw materials 2.7500 to 22.0000
-- and components 19.6350 to 378.3500, while all 6 finished goods cost .0000: their cost is never rolled up.
SELECT ItemTypeID, MIN(StandardCost), MAX(StandardCost), SUM(CASE WHEN StandardCost = 0 THEN 1 ELSE 0 END) AS zero
FROM dbo.Items
GROUP BY ItemTypeID
ORDER BY ItemTypeID ASC
;


-- ----------------------------------------------------------------------------------------------------------- ListPrice
-- the smallest and the largest ListPrice of each type, and how many are zero; .0000 for all the raw materials and the
-- components, which are not sold, and 299.9900 to 899.9900 for the finished goods.
SELECT ItemTypeID, MIN(ListPrice), MAX(ListPrice), SUM(CASE WHEN ListPrice = 0 THEN 1 ELSE 0 END) AS zero
FROM dbo.Items
GROUP BY ItemTypeID
ORDER BY ItemTypeID ASC
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes, the filter of IX_Items_Active; all 45 items are active.
SELECT IsActive, COUNT(*) AS items
FROM dbo.Items
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- -------------------------------------------------------------------------------------------------------- LeadTimeDays
-- the lead times of each type, NULL for none; raw materials 7 to 28 days, components 3, 5 or 7, finished goods all
-- 10, none NULL.
SELECT ItemTypeID, LeadTimeDays, COUNT(*) AS items
FROM dbo.Items
GROUP BY ItemTypeID, LeadTimeDays
ORDER BY ItemTypeID ASC, LeadTimeDays ASC
;


-- -------------------------------------------------------------------------------------------------------- ReorderPoint
-- how many ReorderPoint values are NULL, and the smallest and the largest; none, 4.00 and 500.00.
SELECT SUM(CASE WHEN ReorderPoint IS NULL THEN 1 ELSE 0 END), MIN(ReorderPoint), MAX(ReorderPoint)
FROM dbo.Items
;


-- --------------------------------------------------------------------------------------------------------- SafetyStock
-- how many SafetyStock values are NULL, and the smallest and the largest; none, 2.00 and 250.00.
SELECT SUM(CASE WHEN SafetyStock IS NULL THEN 1 ELSE 0 END), MIN(SafetyStock), MAX(SafetyStock)
FROM dbo.Items
;

-- an item whose SafetyStock is more than half its ReorderPoint.
-- no rows: every SafetyStock is half the ReorderPoint, rounded down where the half is not whole.
SELECT ItemID, ItemCode, ReorderPoint, SafetyStock
FROM dbo.Items
WHERE SafetyStock * 2 > ReorderPoint
ORDER BY ItemID ASC
;


-- --------------------------------------------------------------------------------------------------------- CreatedDate
-- how many CreatedDate values are NULL, and how many fall outside the five minutes after the database was created;
-- none, and none: the default GETDATE() filled every one in while the load script ran, so it is the moment of
-- the load, not of a business event, and moves with every load.
SELECT SUM(CASE WHEN x.CreatedDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.CreatedDate NOT BETWEEN d.create_date AND DATEADD(MINUTE, 5, d.create_date) THEN 1 ELSE 0 END)
FROM dbo.Items x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- an item modified at another moment than it was created.
-- no rows: no item has been modified since the load.
SELECT ItemID, CreatedDate, ModifiedDate
FROM dbo.Items
WHERE ModifiedDate IS NULL
   OR ModifiedDate <> CreatedDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an item with no Inventory row.
-- no rows: every item has one.
SELECT i.ItemID, i.ItemCode
FROM dbo.Items i
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.Inventory v
                  WHERE v.ItemID = i.ItemID)
;

-- a raw material no supplier offers.
-- no rows: every raw material has a SupplierItem.
SELECT i.ItemID, i.ItemCode
FROM dbo.Items i
WHERE i.ItemTypeID = 1
  AND NOT EXISTS (SELECT 1
                  FROM dbo.SupplierItem si
                  WHERE si.ItemID = i.ItemID)
;

-- a component or a finished good with no bill of materials.
-- no rows: every item of the two is a parent in BillOfMaterials.
SELECT i.ItemID, i.ItemCode
FROM dbo.Items i
WHERE i.ItemTypeID IN (2, 3)
  AND NOT EXISTS (SELECT 1
                  FROM dbo.BillOfMaterials b
                  WHERE b.ParentItemID = i.ItemID)
;

-- a finished good missing from a price list.
-- no rows: each of the 6 is on all 3 price lists.
SELECT i.ItemID, i.ItemCode, p.PriceListCode
FROM dbo.Items i
         CROSS JOIN dbo.PriceList p
WHERE i.ItemTypeID = 3
  AND NOT EXISTS (SELECT 1
                  FROM dbo.PriceListDetail d
                  WHERE d.PriceListID = p.PriceListID
                    AND d.ItemID = i.ItemID)
;

