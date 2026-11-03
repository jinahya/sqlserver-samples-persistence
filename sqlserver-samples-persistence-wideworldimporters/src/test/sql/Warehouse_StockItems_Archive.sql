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
-- Profiling queries on Warehouse.StockItems_Archive, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_StockItems_Archive.sql
--


-- --------------------------------------------------------------------------------------------------------- StockItemID
-- how many past versions, and of how many stock items; 444 versions, of all 227.
SELECT COUNT(*), COUNT(DISTINCT StockItemID)
FROM Warehouse.StockItems_Archive
;

-- the stock items by the number of their past versions; 55 with 1, 129 with 2, 41 with 3, 2 with 4.
SELECT versions, COUNT(*) AS stock_items
FROM (SELECT StockItemID, COUNT(*) AS versions
      FROM Warehouse.StockItems_Archive
      GROUP BY StockItemID) v
GROUP BY versions
ORDER BY versions ASC
;

-- a past version of a stock item which is not in Warehouse.StockItems; the history table has no foreign key.
-- no rows: every archived StockItemID is still current.
SELECT a.StockItemID
FROM Warehouse.StockItems_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItems s
                  WHERE s.StockItemID = a.StockItemID)
;


-- ------------------------------------------------------------------------------------------------------- StockItemName
-- how many past versions carry a StockItemName other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.StockItemName EXCEPT SELECT s.StockItemName)
;


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- how many past versions carry a SupplierID other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.SupplierID EXCEPT SELECT s.SupplierID)
;


-- ------------------------------------------------------------------------------------------------------------- ColorID
-- how many past versions carry a ColorID other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.ColorID EXCEPT SELECT s.ColorID)
;


-- ------------------------------------------------------------------------------------------------------- UnitPackageID
-- how many past versions carry a UnitPackageID other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.UnitPackageID EXCEPT SELECT s.UnitPackageID)
;


-- ------------------------------------------------------------------------------------------------------ OuterPackageID
-- how many past versions carry an OuterPackageID other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.OuterPackageID EXCEPT SELECT s.OuterPackageID)
;


-- --------------------------------------------------------------------------------------------------------------- Brand
-- how many past versions carry a Brand other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.Brand EXCEPT SELECT s.Brand)
;


-- ---------------------------------------------------------------------------------------------------------------- Size
-- how many past versions carry a Size other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.Size EXCEPT SELECT s.Size)
;


-- -------------------------------------------------------------------------------------------------------- LeadTimeDays
-- how many past versions carry a LeadTimeDays other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.LeadTimeDays EXCEPT SELECT s.LeadTimeDays)
;


-- ---------------------------------------------------------------------------------------------------- QuantityPerOuter
-- how many past versions carry a QuantityPerOuter other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.QuantityPerOuter EXCEPT SELECT s.QuantityPerOuter)
;


-- ------------------------------------------------------------------------------------------------------ IsChillerStock
-- how many past versions carry an IsChillerStock other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.IsChillerStock EXCEPT SELECT s.IsChillerStock)
;


-- ------------------------------------------------------------------------------------------------------------- Barcode
-- how many past versions carry a Barcode other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.Barcode EXCEPT SELECT s.Barcode)
;


-- ------------------------------------------------------------------------------------------------------------- TaxRate
-- how many past versions carry a TaxRate other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.TaxRate EXCEPT SELECT s.TaxRate)
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- how many past versions carry a UnitPrice other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.UnitPrice EXCEPT SELECT s.UnitPrice)
;


-- ---------------------------------------------------------------------------------------------- RecommendedRetailPrice
-- how many past versions carry a RecommendedRetailPrice other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.RecommendedRetailPrice EXCEPT SELECT s.RecommendedRetailPrice)
;


-- ------------------------------------------------------------------------------------------------ TypicalWeightPerUnit
-- how many past versions carry a TypicalWeightPerUnit other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.TypicalWeightPerUnit EXCEPT SELECT s.TypicalWeightPerUnit)
;


-- --------------------------------------------------------------------------------------------------- MarketingComments
-- how many past versions carry a MarketingComments other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.MarketingComments EXCEPT SELECT s.MarketingComments)
;


-- ---------------------------------------------------------------------------------------------------- InternalComments
-- how many past versions carry an InternalComments other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.InternalComments EXCEPT SELECT s.InternalComments)
;


-- --------------------------------------------------------------------------------------------------------------- Photo
-- the past versions with a Photo, and the smallest and the largest DATALENGTH; none, every Photo is NULL.
SELECT COUNT(Photo), MIN(DATALENGTH(Photo)), MAX(DATALENGTH(Photo))
FROM Warehouse.StockItems_Archive
;


-- -------------------------------------------------------------------------------------------------------- CustomFields
-- how many past versions carry a CustomFields other than the current one; all 444, CustomFields is
-- the column the past versions differ in.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.CustomFields EXCEPT SELECT s.CustomFields)
;


-- ---------------------------------------------------------------------------------------------------------------- Tags
-- how many past versions carry a Tags other than the current one; all 444, Tags being computed from
-- CustomFields.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.Tags EXCEPT SELECT s.Tags)
;


-- ------------------------------------------------------------------------------------------------------- SearchDetails
-- how many past versions carry a SearchDetails other than the current one; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive a
         JOIN Warehouse.StockItems s ON s.StockItemID = a.StockItemID
WHERE EXISTS (SELECT a.SearchDetails EXCEPT SELECT s.SearchDetails)
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who edited the past versions; person 1, for all 444.
SELECT LastEditedBy, COUNT(*) AS versions
FROM Warehouse.StockItems_Archive
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of the moments the past versions began; 2013-01-01 to 2016-05-31 23:09.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Warehouse.StockItems_Archive
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the range of the moments the past versions ended; 2016-05-31 23:00 to 23:12.
SELECT MIN(ValidTo), MAX(ValidTo)
FROM Warehouse.StockItems_Archive
;

-- how many versions do not end after they began; 0.
SELECT COUNT(*)
FROM Warehouse.StockItems_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a past version whose end is not the start of the next version, past or current.
-- no rows: the versions of each stock item follow one another without a gap or an overlap.
SELECT a.StockItemID, a.ValidFrom, a.ValidTo
FROM Warehouse.StockItems_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItems s
                  WHERE s.StockItemID = a.StockItemID
                    AND s.ValidFrom = a.ValidTo)
  AND NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItems_Archive b
                  WHERE b.StockItemID = a.StockItemID
                    AND b.ValidFrom = a.ValidTo)
;

