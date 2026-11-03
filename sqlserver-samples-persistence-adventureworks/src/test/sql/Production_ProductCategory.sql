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
-- Profiling queries on Production.ProductCategory, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductCategory.sql
--


-- --------------------------------------------------------------------------------------------------- ProductCategoryID
-- how many rows, and the smallest and the largest ProductCategoryID; 4 categories, 1 to 4.
SELECT COUNT(*), MIN(ProductCategoryID), MAX(ProductCategoryID)
FROM Production.ProductCategory
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- every category, by ProductCategoryID; Bikes, Components, Clothing and Accessories.
SELECT ProductCategoryID, Name
FROM Production.ProductCategory
ORDER BY ProductCategoryID ASC
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT ProductCategoryID, '[' + Name + ']' AS Name
FROM Production.ProductCategory
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_ProductCategory_rowguid keeps rowguid unique.
-- no rows: no two categories share a rowguid.
SELECT rowguid, COUNT(*) AS categories
FROM Production.ProductCategory
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductCategory
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the subcategories and the products of each category; Bikes 3 and 97, Components 14 and 134, Clothing 8 and 35,
-- Accessories 12 and 29.
SELECT c.ProductCategoryID,
       c.Name,
       COUNT(DISTINCT s.ProductSubcategoryID) AS subcategories,
       COUNT(p.ProductID)                     AS products
FROM Production.ProductCategory c
         LEFT JOIN Production.ProductSubcategory s ON s.ProductCategoryID = c.ProductCategoryID
         LEFT JOIN Production.Product p ON p.ProductSubcategoryID = s.ProductSubcategoryID
GROUP BY c.ProductCategoryID, c.Name
ORDER BY c.ProductCategoryID ASC
;

