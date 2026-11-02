-- -
-- #%L
-- sqlserver-samples-persistence-adventureworks
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
-- Profiling queries on Production.Product, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_Product.sql
--


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- how many rows, and the smallest and the largest ProductID; 504 products, 1 to 999.
SELECT COUNT(*), MIN(ProductID), MAX(ProductID)
FROM Production.Product
;

-- the gaps in ProductID, below the largest: how many values are missing; 495; 504 of the 999 values are used.
SELECT MAX(ProductID) - MIN(ProductID) + 1 - COUNT(*) AS missing
FROM Production.Product
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 4 and 32 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Production.Product
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT ProductID, '[' + Name + ']' AS Name
FROM Production.Product
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_Product_Name keeps Name unique.
-- no rows: no two products share a Name.
SELECT Name, COUNT(*) AS products
FROM Production.Product
GROUP BY Name
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------- ProductNumber
-- the shortest and the longest ProductNumber; 7 and 10 characters, of the 25 the column allows.
SELECT MIN(LEN(ProductNumber)), MAX(LEN(ProductNumber))
FROM Production.Product
;

-- a ProductNumber which is not two upper case letters, a dash, and more.
-- no rows: every ProductNumber starts with two upper case letters and a dash.
SELECT ProductID, ProductNumber
FROM Production.Product
WHERE ProductNumber COLLATE Latin1_General_BIN NOT LIKE '[A-Z][A-Z]-_%'
;

-- AK_Product_ProductNumber keeps ProductNumber unique.
-- no rows: no two products share a ProductNumber.
SELECT ProductNumber, COUNT(*) AS products
FROM Production.Product
GROUP BY ProductNumber
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ MakeFlag
-- the products made in house (1) and the bought ones (0); 265 bought, 239 made.
SELECT MakeFlag, COUNT(*) AS products
FROM Production.Product
GROUP BY MakeFlag
ORDER BY MakeFlag ASC
;


-- --------------------------------------------------------------------------------------------------- FinishedGoodsFlag
-- the products for sale (1) and the others (0), against MakeFlag; 182 bought parts, 83 bought goods, 27 made parts, 212
-- made goods.
SELECT MakeFlag, FinishedGoodsFlag, COUNT(*) AS products
FROM Production.Product
GROUP BY MakeFlag, FinishedGoodsFlag
ORDER BY MakeFlag ASC, FinishedGoodsFlag ASC
;


-- --------------------------------------------------------------------------------------------------------------- Color
-- the colors, NULL for none; 248 with none; Black 93, Blue 26, Grey 1, Multi 8, Red 38, Silver 43, Silver/Black 7,
-- White 4, Yellow 36.
SELECT Color, COUNT(*) AS products
FROM Production.Product
GROUP BY Color
ORDER BY Color ASC
;


-- ---------------------------------------------------------------------------------------------------- SafetyStockLevel
-- the safety stock levels CK_Product_SafetyStockLevel keeps above 0; 4 for 54, 60 for 5, 100 for 97, 500 for 167, 800
-- for 25, 1000 for 156.
SELECT SafetyStockLevel, COUNT(*) AS products
FROM Production.Product
GROUP BY SafetyStockLevel
ORDER BY SafetyStockLevel ASC
;


-- -------------------------------------------------------------------------------------------------------- ReorderPoint
-- a ReorderPoint other than three quarters of SafetyStockLevel.
-- no rows: ReorderPoint is always three quarters of SafetyStockLevel.
SELECT ProductID, SafetyStockLevel, ReorderPoint
FROM Production.Product
WHERE ReorderPoint * 4 <> SafetyStockLevel * 3
;


-- -------------------------------------------------------------------------------------------------------- StandardCost
-- the smallest and the largest StandardCost, and how many cost nothing; 0.0000 and 2171.2942; 200 cost nothing.
SELECT MIN(StandardCost), MAX(StandardCost), SUM(CASE WHEN StandardCost = 0 THEN 1 ELSE 0 END) AS free
FROM Production.Product
;


-- ----------------------------------------------------------------------------------------------------------- ListPrice
-- the smallest and the largest ListPrice, and how many are priced at nothing; 0.0000 and 3578.2700; 200 are priced at
-- nothing.
SELECT MIN(ListPrice), MAX(ListPrice), SUM(CASE WHEN ListPrice = 0 THEN 1 ELSE 0 END) AS free
FROM Production.Product
;

-- a product with a price but no cost, or the other way round; or priced below its cost.
-- no rows: a product has both a cost and a price or neither, and is never priced below its cost.
SELECT ProductID, Name, StandardCost, ListPrice
FROM Production.Product
WHERE (StandardCost = 0 AND ListPrice <> 0)
   OR (StandardCost <> 0 AND ListPrice = 0)
   OR ListPrice < StandardCost
;

-- a priced product which is not a finished good, or a finished good with no price.
-- 9 rows: the LL, ML and HL Mountain, Road and Touring Seat Assemblies, 514 to 522, are not finished goods but carry a
-- ListPrice.
SELECT ProductID, Name, FinishedGoodsFlag, ListPrice
FROM Production.Product
WHERE (FinishedGoodsFlag = 0 AND ListPrice <> 0)
   OR (FinishedGoodsFlag = 1 AND ListPrice = 0)
;


-- ---------------------------------------------------------------------------------------------------------------- Size
-- how many products have no Size, and the sizes the others have; 293 have none; 18 sizes.
SELECT COUNT(*) - COUNT(Size) AS without, COUNT(DISTINCT Size) AS sizes
FROM Production.Product
;

-- the sizes which are not numbers, and the products of each; L for 11, M for 11, S for 9, XL for 3.
SELECT Size, COUNT(*) AS products
FROM Production.Product
WHERE TRY_CAST(Size AS int) IS NULL
  AND Size IS NOT NULL
GROUP BY Size
ORDER BY Size ASC
;


-- ------------------------------------------------------------------------------------------------- SizeUnitMeasureCode
-- the size units, NULL for none, against whether the size is a number; 328 with no unit, the 293 with no size, the 34
-- letter sizes and 880 (Hydration Pack - 70 oz.), a size of 70; CM for the other 176 numeric sizes.
SELECT SizeUnitMeasureCode, CASE WHEN TRY_CAST(Size AS int) IS NULL THEN 0 ELSE 1 END AS numeric_, COUNT(*) AS products
FROM Production.Product
GROUP BY SizeUnitMeasureCode, CASE WHEN TRY_CAST(Size AS int) IS NULL THEN 0 ELSE 1 END
ORDER BY SizeUnitMeasureCode ASC, numeric_ ASC
;


-- ----------------------------------------------------------------------------------------------- WeightUnitMeasureCode
-- the weight units, NULL for none, against whether a Weight is given; 299 with no unit and no weight; G for 29, LB for
-- 176, each with a weight.
SELECT WeightUnitMeasureCode, COUNT(Weight) AS weighed, COUNT(*) - COUNT(Weight) AS unweighed
FROM Production.Product
GROUP BY WeightUnitMeasureCode
ORDER BY WeightUnitMeasureCode ASC
;


-- -------------------------------------------------------------------------------------------------------------- Weight
-- the smallest and the largest Weight in each unit, which CK_Product_Weight keeps above 0; G 88.00 to 1050.00, LB 2.12
-- to 30.00.
SELECT WeightUnitMeasureCode, MIN(Weight), MAX(Weight)
FROM Production.Product
WHERE Weight IS NOT NULL
GROUP BY WeightUnitMeasureCode
ORDER BY WeightUnitMeasureCode ASC
;


-- --------------------------------------------------------------------------------------------------- DaysToManufacture
-- the days to manufacture, against MakeFlag; the bought products in 0 or 1 day, 246 and 19; the made ones in 1, 2 or 4
-- days, 135, 7 and 97.
SELECT MakeFlag, DaysToManufacture, COUNT(*) AS products
FROM Production.Product
GROUP BY MakeFlag, DaysToManufacture
ORDER BY MakeFlag ASC, DaysToManufacture ASC
;


-- --------------------------------------------------------------------------------------------------------- ProductLine
-- the product lines CK_Product_ProductLine allows, R, M, T, S or NULL; 226 with none; M 91, R 100, S 35, T 52.
SELECT '[' + ProductLine + ']' AS ProductLine, COUNT(*) AS products
FROM Production.Product
GROUP BY ProductLine
ORDER BY ProductLine ASC
;


-- --------------------------------------------------------------------------------------------------------------- Class
-- the classes CK_Product_Class allows, H, M, L or NULL, each letter padded by nchar(2); 257 with none; H 82, L 97,
-- M 68.
SELECT '[' + Class + ']' AS Class, COUNT(*) AS products
FROM Production.Product
GROUP BY Class
ORDER BY Class ASC
;


-- --------------------------------------------------------------------------------------------------------------- Style
-- the styles CK_Product_Style allows, U, M, W or NULL; 293 with none; M 7, U 176, W 28.
SELECT '[' + Style + ']' AS Style, COUNT(*) AS products
FROM Production.Product
GROUP BY Style
ORDER BY Style ASC
;

-- a ProductLine, Class or Style in lower case; the check constraints compare UPPER of each, so they let it in.
-- no rows: every letter is upper case.
SELECT ProductID, ProductLine, Class, Style
FROM Production.Product
WHERE ProductLine COLLATE Latin1_General_BIN <> UPPER(ProductLine)
   OR Class COLLATE Latin1_General_BIN <> UPPER(Class)
   OR Style COLLATE Latin1_General_BIN <> UPPER(Style)
;


-- ------------------------------------------------------------------------------------------------ ProductSubcategoryID
-- how many products have no subcategory, and how many subcategories the others fall in; 209 have none; 37
-- subcategories, all of them.
SELECT COUNT(*) - COUNT(ProductSubcategoryID) AS without, COUNT(DISTINCT ProductSubcategoryID) AS subcategories
FROM Production.Product
;

-- a product with no subcategory which is a finished good.
-- no rows: only the parts, not for sale, lack a subcategory.
SELECT ProductID, Name
FROM Production.Product
WHERE ProductSubcategoryID IS NULL
  AND FinishedGoodsFlag = 1
;


-- ------------------------------------------------------------------------------------------------------ ProductModelID
-- how many products have no model, and how many models the others are built on; 209 have none; 119 models.
SELECT COUNT(*) - COUNT(ProductModelID) AS without, COUNT(DISTINCT ProductModelID) AS productModels
FROM Production.Product
;

-- a product with a model but no subcategory, or the other way round.
-- no rows: a product has both or neither.
SELECT ProductID, Name, ProductSubcategoryID, ProductModelID
FROM Production.Product
WHERE (ProductSubcategoryID IS NULL AND ProductModelID IS NOT NULL)
   OR (ProductSubcategoryID IS NOT NULL AND ProductModelID IS NULL)
;


-- ------------------------------------------------------------------------------------------------------- SellStartDate
-- the dates sales started, and the products of each; 2019-04-30 for 211, 2022-05-30 for 72, 2023-05-30 for 85,
-- 2024-05-29 for 136.
SELECT SellStartDate, COUNT(*) AS products
FROM Production.Product
GROUP BY SellStartDate
ORDER BY SellStartDate ASC
;


-- --------------------------------------------------------------------------------------------------------- SellEndDate
-- the dates sales ended, NULL for the products still sold; 406 still sold; 2023-05-29 for 29, 2024-05-28 for 69.
SELECT SellEndDate, COUNT(*) AS products
FROM Production.Product
GROUP BY SellEndDate
ORDER BY SellEndDate ASC
;

-- CK_Product_SellEndDate keeps SellEndDate on or after SellStartDate.
-- no rows: every product stops selling after it starts.
SELECT ProductID, SellStartDate, SellEndDate
FROM Production.Product
WHERE SellEndDate < SellStartDate
;


-- ---------------------------------------------------------------------------------------------------- DiscontinuedDate
-- how many products are discontinued; 0: DiscontinuedDate is NULL for every product.
SELECT COUNT(DiscontinuedDate) AS discontinued
FROM Production.Product
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_Product_rowguid keeps rowguid unique.
-- no rows: no two products share a rowguid.
SELECT rowguid, COUNT(*) AS products
FROM Production.Product
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2025-02-07 10:01:36.827 to 2025-02-07 10:03:55.510.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.Product
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a finished good never sold.
-- 29 of the 295.
SELECT COUNT(*) AS unsold
FROM Production.Product p
WHERE p.FinishedGoodsFlag = 1
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderDetail d
                  WHERE d.ProductID = p.ProductID)
;

-- a part, not a finished good, which is a component of no bill of materials.
-- 124 of the 209.
SELECT COUNT(*) AS unused
FROM Production.Product p
WHERE p.FinishedGoodsFlag = 0
  AND NOT EXISTS (SELECT 1
                  FROM Production.BillOfMaterials b
                  WHERE b.ComponentID = p.ProductID)
;

-- a finished good with no cost history; the parts, all 209 of them, have none.
-- 2 rows: 680 and 706, HL Road Frame - Black, 58 and HL Road Frame - Red, 58, on sale since 2019-04-30.
SELECT p.ProductID, p.Name, p.SellStartDate, p.SellEndDate
FROM Production.Product p
WHERE p.FinishedGoodsFlag = 1
  AND NOT EXISTS (SELECT 1
                  FROM Production.ProductCostHistory h
                  WHERE h.ProductID = p.ProductID)
;

