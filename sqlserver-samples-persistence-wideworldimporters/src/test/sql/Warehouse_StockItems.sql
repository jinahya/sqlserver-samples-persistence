-- -
-- #%L
-- sqlserver-samples-persistence-wideworldimporters
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
-- Profiling queries on Warehouse.StockItems, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_StockItems.sql
--


-- --------------------------------------------------------------------------------------------------------- StockItemID
-- how many rows, and the smallest and the largest StockItemID; 227 stock items, 1 to 227.
SELECT COUNT(*), MIN(StockItemID), MAX(StockItemID)
FROM Warehouse.StockItems
;

-- a StockItemID whose next value is missing, below the largest.
-- no rows: StockItemID runs from 1 without a gap.
SELECT a.StockItemID + 1 AS missing_from
FROM Warehouse.StockItems a
WHERE a.StockItemID < (SELECT MAX(StockItemID) FROM Warehouse.StockItems)
  AND NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItems b
                  WHERE b.StockItemID = a.StockItemID + 1)
;


-- ------------------------------------------------------------------------------------------------------- StockItemName
-- the shortest and the longest StockItemName; 20 and 85 characters, of the 100 the column allows.
SELECT MIN(LEN(StockItemName)), MAX(LEN(StockItemName))
FROM Warehouse.StockItems
;

-- a StockItemName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every StockItemName is trimmed and not blank.
SELECT StockItemID, '[' + StockItemName + ']' AS StockItemName
FROM Warehouse.StockItems
WHERE TRIM(StockItemName) = ''
   OR DATALENGTH(StockItemName) <> DATALENGTH(TRIM(StockItemName))
;

-- UQ_Warehouse_StockItems_StockItemName keeps StockItemName unique.
-- no rows: no two stock items share a name.
SELECT StockItemName, COUNT(*) AS stock_items
FROM Warehouse.StockItems
GROUP BY StockItemName
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- the stock items of each supplier; 7 suppliers, Fabrikam, Inc. (4) the largest with 74.
SELECT s.SupplierID, p.SupplierName, COUNT(*) AS stock_items
FROM Warehouse.StockItems s
         JOIN Purchasing.Suppliers p ON p.SupplierID = s.SupplierID
GROUP BY s.SupplierID, p.SupplierName
ORDER BY s.SupplierID ASC
;


-- ------------------------------------------------------------------------------------------------------------- ColorID
-- the stock items with no color, and how many colors the others have; 99 without, 7 colors.
SELECT COUNT(*) - COUNT(ColorID) AS without_color, COUNT(DISTINCT ColorID) AS colors
FROM Warehouse.StockItems
;

-- how many stock items do not name their color in StockItemName, in parentheses; 9, a finding: the Steel Gray
-- ones, still named (Gray) since the color was renamed.
SELECT COUNT(*)
FROM Warehouse.StockItems s
         JOIN Warehouse.Colors c ON c.ColorID = s.ColorID
WHERE s.StockItemName NOT LIKE '%(' + c.ColorName + ')%'
;


-- ------------------------------------------------------------------------------------------------------- UnitPackageID
-- the stock items of each unit package; 210 Each, 8 Bag, 5 Packet, 4 Pair.
SELECT s.UnitPackageID, p.PackageTypeName, COUNT(*) AS stock_items
FROM Warehouse.StockItems s
         JOIN Warehouse.PackageTypes p ON p.PackageTypeID = s.UnitPackageID
GROUP BY s.UnitPackageID, p.PackageTypeName
ORDER BY s.UnitPackageID ASC
;


-- ------------------------------------------------------------------------------------------------------ OuterPackageID
-- the stock items of each outer package; 175 Each, 43 Carton, 9 Packet.
SELECT s.OuterPackageID, p.PackageTypeName, COUNT(*) AS stock_items
FROM Warehouse.StockItems s
         JOIN Warehouse.PackageTypes p ON p.PackageTypeID = s.OuterPackageID
GROUP BY s.OuterPackageID, p.PackageTypeName
ORDER BY s.OuterPackageID ASC
;


-- --------------------------------------------------------------------------------------------------------------- Brand
-- the distinct brands, NULL for none; 209 have none, 18 are Northwind.
SELECT Brand, COUNT(*) AS stock_items
FROM Warehouse.StockItems
GROUP BY Brand
ORDER BY Brand ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Size
-- the stock items with no Size, and how many distinct sizes the others have,
-- and the shortest and the longest; 64 without, 43 sizes, 1 to 13 characters of the 20 the column allows.
SELECT COUNT(*) - COUNT(Size) AS without_size, COUNT(DISTINCT Size) AS sizes, MIN(LEN(Size)), MAX(LEN(Size))
FROM Warehouse.StockItems
;

-- a Size which is blank, or carries leading or trailing spaces.
-- no rows: every Size is trimmed and not blank.
SELECT StockItemID, '[' + Size + ']' AS Size
FROM Warehouse.StockItems
WHERE TRIM(Size) = ''
   OR DATALENGTH(Size) <> DATALENGTH(TRIM(Size))
;


-- -------------------------------------------------------------------------------------------------------- LeadTimeDays
-- the distinct lead times; 2 to 20 days, 14 the most frequent with 96.
SELECT LeadTimeDays, COUNT(*) AS stock_items
FROM Warehouse.StockItems
GROUP BY LeadTimeDays
ORDER BY LeadTimeDays ASC
;


-- ---------------------------------------------------------------------------------------------------- QuantityPerOuter
-- the smallest and the largest QuantityPerOuter; 1 and 36.
SELECT MIN(QuantityPerOuter), MAX(QuantityPerOuter)
FROM Warehouse.StockItems
;


-- ------------------------------------------------------------------------------------------------------ IsChillerStock
-- the chiller stock items, and those not; 8 chiller, 219 not.
SELECT IsChillerStock, COUNT(*) AS stock_items
FROM Warehouse.StockItems
GROUP BY IsChillerStock
;


-- ------------------------------------------------------------------------------------------------------------- Barcode
-- the stock items with a Barcode, and the shortest and the longest; 8, all 13
-- characters.
SELECT COUNT(Barcode), MIN(LEN(Barcode)), MAX(LEN(Barcode))
FROM Warehouse.StockItems
;

-- Barcode has no unique key; a barcode shared by more than one stock item.
-- no rows: the 8 barcodes differ.
SELECT Barcode, COUNT(*) AS stock_items
FROM Warehouse.StockItems
WHERE Barcode IS NOT NULL
GROUP BY Barcode
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------- TaxRate
-- the distinct tax rates; 10.000 for 8, 15.000 for 219.
SELECT TaxRate, COUNT(*) AS stock_items
FROM Warehouse.StockItems
GROUP BY TaxRate
ORDER BY TaxRate ASC
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- the range of UnitPrice; .66 to 1899.00.
SELECT MIN(UnitPrice), MAX(UnitPrice)
FROM Warehouse.StockItems
;


-- ---------------------------------------------------------------------------------------------- RecommendedRetailPrice
-- the stock items with no RecommendedRetailPrice, and the range of the ratio to UnitPrice; none is
-- NULL, 1.43 to 1.50 times the UnitPrice.
SELECT COUNT(*) - COUNT(RecommendedRetailPrice), MIN(RecommendedRetailPrice / UnitPrice),
       MAX(RecommendedRetailPrice / UnitPrice)
FROM Warehouse.StockItems
;


-- ------------------------------------------------------------------------------------------------ TypicalWeightPerUnit
-- the range of TypicalWeightPerUnit; .050 to 21.000.
SELECT MIN(TypicalWeightPerUnit), MAX(TypicalWeightPerUnit)
FROM Warehouse.StockItems
;


-- --------------------------------------------------------------------------------------------------- MarketingComments
-- the stock items with MarketingComments, and the shortest and the longest; 28,
-- 20 to 63 characters.
SELECT COUNT(MarketingComments), MIN(LEN(MarketingComments)), MAX(LEN(MarketingComments))
FROM Warehouse.StockItems
;


-- ---------------------------------------------------------------------------------------------------- InternalComments
-- the stock items with InternalComments; 0, every one is NULL.
SELECT COUNT(InternalComments)
FROM Warehouse.StockItems
;


-- --------------------------------------------------------------------------------------------------------------- Photo
-- the stock items with a Photo, and the smallest and the largest DATALENGTH; none, every Photo is NULL.
SELECT COUNT(Photo), MIN(DATALENGTH(Photo)), MAX(DATALENGTH(Photo))
FROM Warehouse.StockItems
;


-- -------------------------------------------------------------------------------------------------------- CustomFields
-- the stock items with CustomFields, and those whose CustomFields are not valid JSON; all 227 have them, all
-- valid.
SELECT COUNT(CustomFields), SUM(CASE WHEN ISJSON(CustomFields) = 0 THEN 1 ELSE 0 END) AS invalid
FROM Warehouse.StockItems
;

-- the countries of manufacture in CustomFields; China 207, Japan 12, USA 8.
SELECT JSON_VALUE(CustomFields, '$.CountryOfManufacture') AS country, COUNT(*) AS stock_items
FROM Warehouse.StockItems
GROUP BY JSON_VALUE(CustomFields, '$.CountryOfManufacture')
ORDER BY country ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Tags
-- Tags is computed from $.Tags of CustomFields; the stock items by the number of tags;
-- 55 with none, 129 with 1, 41 with 2, 2 with 3.
SELECT t.tags, COUNT(*) AS stock_items
FROM Warehouse.StockItems s
         CROSS APPLY (SELECT COUNT(*) AS tags
                      FROM OPENJSON(s.Tags)) t
GROUP BY t.tags
ORDER BY t.tags ASC
;

-- the most frequent tags; Limited Stock 67, Comedy 42, Comfortable 20.
SELECT TOP (3) j.value AS tag, COUNT(*) AS stock_items
FROM Warehouse.StockItems s
         CROSS APPLY OPENJSON(s.Tags) j
GROUP BY j.value
ORDER BY stock_items DESC, tag ASC
;


-- ------------------------------------------------------------------------------------------------------- SearchDetails
-- SearchDetails is computed from StockItemName and MarketingComments; the shortest and the
-- longest; 20 and 104 characters.
SELECT MIN(LEN(SearchDetails)), MAX(LEN(SearchDetails))
FROM Warehouse.StockItems
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the rows; person 1, for all 227.
SELECT LastEditedBy, COUNT(*) AS stock_items
FROM Warehouse.StockItems
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of the moments the current versions began; 2016-05-31 23:00 to 23:12.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Warehouse.StockItems
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the end of each current version; 9999-12-31 23:59:59.9999999, the largest datetime2, for all 227.
SELECT ValidTo, COUNT(*) AS stock_items
FROM Warehouse.StockItems
GROUP BY ValidTo
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the past versions kept in Warehouse.StockItems_Archive; 444 versions, of all 227 stock items.
SELECT COUNT(*), COUNT(DISTINCT StockItemID)
FROM Warehouse.StockItems_Archive
;

-- a stock item in no stock group.
-- no rows: every stock item is in at least one stock group.
SELECT s.StockItemID, s.StockItemName
FROM Warehouse.StockItems s
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItemStockGroups g
                  WHERE g.StockItemID = s.StockItemID)
;

-- a stock item with no holding.
-- no rows: every stock item has a row in Warehouse.StockItemHoldings.
SELECT s.StockItemID
FROM Warehouse.StockItems s
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItemHoldings h
                  WHERE h.StockItemID = s.StockItemID)
;

