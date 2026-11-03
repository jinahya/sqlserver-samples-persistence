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
-- Profiling queries on dbo.SupplierItem, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_SupplierItem.sql
--


-- ------------------------------------------------------------------------------------------------------ SupplierItemID
-- how many rows, and the smallest and the largest SupplierItemID; 24 rows, 1 to 24.
SELECT COUNT(*), MIN(SupplierItemID), MAX(SupplierItemID)
FROM dbo.SupplierItem
;


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- the items of each supplier; 1 with 4, 2 with 5, 3 with 7, 4 and 5 with 4 each.
SELECT SupplierID, COUNT(*) AS items
FROM dbo.SupplierItem
GROUP BY SupplierID
ORDER BY SupplierID ASC
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- the types of the items supplied; only raw materials (1), all 24 of them.
SELECT i.ItemTypeID, COUNT(*) AS items
FROM dbo.SupplierItem si
         JOIN dbo.Items i ON i.ItemID = si.ItemID
GROUP BY i.ItemTypeID
;

-- nothing keeps an item to one supplier, or to one row per supplier.
-- no rows: every item has a single supplier.
SELECT ItemID, COUNT(*) AS suppliers
FROM dbo.SupplierItem
GROUP BY ItemID
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------- SupplierPartNumber
-- how many SupplierPartNumber values are NULL, and the shortest and the longest of the rest; none, 11 and 15
-- characters, of the 50 the column allows.
SELECT SUM(CASE WHEN SupplierPartNumber IS NULL THEN 1 ELSE 0 END),
       MIN(LEN(SupplierPartNumber)), MAX(LEN(SupplierPartNumber))
FROM dbo.SupplierItem
;

-- a SupplierPartNumber which is blank, or carries leading or trailing spaces.
-- no rows: every SupplierPartNumber is trimmed and not blank.
SELECT SupplierItemID, '[' + SupplierPartNumber + ']' AS SupplierPartNumber
FROM dbo.SupplierItem
WHERE TRIM(SupplierPartNumber) = ''
   OR DATALENGTH(SupplierPartNumber) <> DATALENGTH(TRIM(SupplierPartNumber))
;

-- nothing keeps SupplierPartNumber unique, yet no two rows share one.
-- no rows: every SupplierPartNumber is distinct.
SELECT SupplierPartNumber, COUNT(*) AS items
FROM dbo.SupplierItem
GROUP BY SupplierPartNumber
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- the smallest and the largest UnitPrice; 2.5000 and 20.0000.
SELECT MIN(UnitPrice), MAX(UnitPrice)
FROM dbo.SupplierItem
;

-- the UnitPrice as a share of the item's StandardCost; .88 to .93, every item is bought below its standard cost.
SELECT MIN(si.UnitPrice / i.StandardCost) AS lowest, MAX(si.UnitPrice / i.StandardCost) AS highest
FROM dbo.SupplierItem si
         JOIN dbo.Items i ON i.ItemID = si.ItemID
;


-- ------------------------------------------------------------------------------------------------ MinimumOrderQuantity
-- how many MinimumOrderQuantity values are NULL, and the smallest and the largest; none, 12.00 and 500.00.
SELECT SUM(CASE WHEN MinimumOrderQuantity IS NULL THEN 1 ELSE 0 END), MIN(MinimumOrderQuantity),
       MAX(MinimumOrderQuantity)
FROM dbo.SupplierItem
;

-- a MinimumOrderQuantity above the item's ReorderPoint, so that an order at the reorder point could not be placed.
-- no rows: every MinimumOrderQuantity is at most the ReorderPoint, 7 of the 24 equal to it.
SELECT si.SupplierItemID, i.ItemCode, si.MinimumOrderQuantity, i.ReorderPoint
FROM dbo.SupplierItem si
         JOIN dbo.Items i ON i.ItemID = si.ItemID
WHERE si.MinimumOrderQuantity > i.ReorderPoint
ORDER BY si.SupplierItemID ASC
;


-- -------------------------------------------------------------------------------------------------------- LeadTimeDays
-- the values LeadTimeDays takes; 7 for 6 items, 14 for 12, 21 for 4 and 28 for 2.
SELECT LeadTimeDays, COUNT(*) AS items
FROM dbo.SupplierItem
GROUP BY LeadTimeDays
ORDER BY LeadTimeDays ASC
;

-- a LeadTimeDays other than the item's own.
-- no rows: every supplier quotes the lead time the item carries.
SELECT si.SupplierItemID, si.LeadTimeDays, i.LeadTimeDays AS item_lead_time
FROM dbo.SupplierItem si
         JOIN dbo.Items i ON i.ItemID = si.ItemID
WHERE si.LeadTimeDays <> i.LeadTimeDays
;


-- --------------------------------------------------------------------------------------------------------- IsPreferred
-- the values IsPreferred takes; 1 for all 24, the only supplier of each item.
SELECT IsPreferred, COUNT(*) AS items
FROM dbo.SupplierItem
GROUP BY IsPreferred
ORDER BY IsPreferred ASC
;


-- ------------------------------------------------------------------------------------------------------- EffectiveDate
-- how many EffectiveDate values are NULL, and how many are not the day the database was created; none, and none:
-- the default CONVERT(date, GETDATE()) filled every one in while the load script ran.
SELECT SUM(CASE WHEN x.EffectiveDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.EffectiveDate <> CAST(d.create_date AS date) THEN 1 ELSE 0 END)
FROM dbo.SupplierItem x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- how many EndDate values are NULL, of all the rows; 24 of 24.
SELECT SUM(CASE WHEN EndDate IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.SupplierItem
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a purchase order line bought from a supplier which does not offer the item.
-- no rows: every line is of an item its supplier offers.
SELECT d.PODetailID, po.SupplierID, d.ItemID
FROM dbo.PurchaseOrderDetail d
         JOIN dbo.PurchaseOrder po ON po.PurchaseOrderID = d.PurchaseOrderID
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.SupplierItem si
                  WHERE si.SupplierID = po.SupplierID
                    AND si.ItemID = d.ItemID)
;

-- a purchase order line priced at other than the supplier's UnitPrice.
-- no rows: every line pays the supplier's price.
SELECT d.PODetailID, d.ItemID, d.UnitPrice, si.UnitPrice AS supplier_price
FROM dbo.PurchaseOrderDetail d
         JOIN dbo.PurchaseOrder po ON po.PurchaseOrderID = d.PurchaseOrderID
         JOIN dbo.SupplierItem si ON si.SupplierID = po.SupplierID AND si.ItemID = d.ItemID
WHERE d.UnitPrice <> si.UnitPrice
;

