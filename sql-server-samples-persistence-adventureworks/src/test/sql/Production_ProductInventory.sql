-- -
-- #%L
-- sql-server-samples-persistence-adventureworks
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
-- Profiling queries on Production.ProductInventory, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductInventory.sql
--


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- how many rows, and how many products they cover; 1069 rows, on 432 products, 1 to 999.
SELECT COUNT(*), COUNT(DISTINCT ProductID), MIN(ProductID), MAX(ProductID)
FROM Production.ProductInventory
;

-- the products by the locations they are kept at; 54 at 1 location, 140 at 2, 231 at 3, 7 at 6.
SELECT x.locations, COUNT(*) AS products
FROM (SELECT ProductID, COUNT(*) AS locations
      FROM Production.ProductInventory
      GROUP BY ProductID) x
GROUP BY x.locations
ORDER BY x.locations ASC
;


-- ---------------------------------------------------------------------------------------------------------- LocationID
-- the locations, and the products at each; all 14; Subassembly (50) the most with 251, Specialized Paint (45) the least
-- with 3.
SELECT LocationID, COUNT(*) AS products
FROM Production.ProductInventory
GROUP BY LocationID
ORDER BY LocationID ASC
;


-- --------------------------------------------------------------------------------------------------------------- Shelf
-- the shelves CK_ProductInventory_Shelf allows, a letter or N/A; 20 letters, A to Y, and N/A, the most with 290.
SELECT Shelf, COUNT(*) AS inventories
FROM Production.ProductInventory
GROUP BY Shelf
ORDER BY Shelf ASC
;

-- a Shelf in lower case.
-- no rows: every letter is upper case.
SELECT ProductID, LocationID, Shelf
FROM Production.ProductInventory
WHERE Shelf COLLATE Latin1_General_BIN <> UPPER(Shelf)
;


-- ----------------------------------------------------------------------------------------------------------------- Bin
-- the smallest and the largest Bin, which CK_ProductInventory_Bin keeps within 0 to 100; 0 and 61, 62 bins in use.
SELECT MIN(Bin), MAX(Bin), COUNT(DISTINCT Bin)
FROM Production.ProductInventory
;

-- the bins on the N/A shelf; 0 for all 290.
SELECT Bin, COUNT(*) AS inventories
FROM Production.ProductInventory
WHERE Shelf = 'N/A'
GROUP BY Bin
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the smallest and the largest Quantity, and how many rows hold none; 0 and 924; 4 rows hold none.
SELECT MIN(Quantity), MAX(Quantity), SUM(CASE WHEN Quantity = 0 THEN 1 ELSE 0 END) AS empty
FROM Production.ProductInventory
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- the schema has no unique key on rowguid here; a rowguid more than one row has.
-- no rows: every rowguid is unique.
SELECT rowguid, COUNT(*) AS inventories
FROM Production.ProductInventory
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-03-31 00:00:00.000 to 2025-08-11 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductInventory
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many products are kept nowhere; 72.
SELECT COUNT(*) AS without
FROM Production.Product p
WHERE NOT EXISTS (SELECT 1
                  FROM Production.ProductInventory i
                  WHERE i.ProductID = p.ProductID)
;

-- a product with fewer units in stock, over all locations, than its SafetyStockLevel; how many.
-- 69.
SELECT COUNT(*) AS below
FROM Production.Product p
         JOIN (SELECT ProductID, SUM(Quantity) AS quantity
               FROM Production.ProductInventory
               GROUP BY ProductID) i ON i.ProductID = p.ProductID
WHERE i.quantity < p.SafetyStockLevel
;

