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
-- Profiling queries on Production.ProductSubcategory, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductSubcategory.sql
--


-- ------------------------------------------------------------------------------------------------ ProductSubcategoryID
-- how many rows, and the smallest and the largest ProductSubcategoryID; 37 subcategories, 1 to 37.
SELECT COUNT(*), MIN(ProductSubcategoryID), MAX(ProductSubcategoryID)
FROM Production.ProductSubcategory
;


-- --------------------------------------------------------------------------------------------------- ProductCategoryID
-- the subcategories of each category; Bikes (1) 3, Components (2) 14, Clothing (3) 8, Accessories (4) 12.
SELECT ProductCategoryID, COUNT(*) AS subcategories
FROM Production.ProductSubcategory
GROUP BY ProductCategoryID
ORDER BY ProductCategoryID ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 4 and 17 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Production.ProductSubcategory
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT ProductSubcategoryID, '[' + Name + ']' AS Name
FROM Production.ProductSubcategory
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_ProductSubcategory_Name keeps Name unique across the categories.
-- no rows: no two subcategories share a Name.
SELECT Name, COUNT(*) AS subcategories
FROM Production.ProductSubcategory
GROUP BY Name
HAVING COUNT(*) > 1
;

-- a subcategory named the same as a category.
-- no rows: no subcategory repeats a category's name.
SELECT s.ProductSubcategoryID, s.Name
FROM Production.ProductSubcategory s
         JOIN Production.ProductCategory c ON c.Name = s.Name
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_ProductSubcategory_rowguid keeps rowguid unique.
-- no rows: no two subcategories share a rowguid.
SELECT rowguid, COUNT(*) AS subcategories
FROM Production.ProductSubcategory
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductSubcategory
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a subcategory no product falls in.
-- no rows: every subcategory has at least one product.
SELECT s.ProductSubcategoryID, s.Name
FROM Production.ProductSubcategory s
WHERE NOT EXISTS (SELECT 1
                  FROM Production.Product p
                  WHERE p.ProductSubcategoryID = s.ProductSubcategoryID)
;

-- the subcategories with the most products; Road Bikes (2) with 43, Road Frames (14) with 33, Mountain Bikes (1) with
-- 32.
SELECT TOP (3) s.ProductSubcategoryID, s.Name, COUNT(*) AS products
FROM Production.ProductSubcategory s
         JOIN Production.Product p ON p.ProductSubcategoryID = s.ProductSubcategoryID
GROUP BY s.ProductSubcategoryID, s.Name
ORDER BY products DESC, s.ProductSubcategoryID ASC
;

