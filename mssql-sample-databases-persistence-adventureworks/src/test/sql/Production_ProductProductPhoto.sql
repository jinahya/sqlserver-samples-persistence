-- -
-- #%L
-- mssql-sample-databases-persistence-adventureworks
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
-- Profiling queries on Production.ProductProductPhoto, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductProductPhoto.sql
--


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- how many rows, and how many products they cover; 504 rows, on 504 products, 1 to 999; a photo per product.
SELECT COUNT(*), COUNT(DISTINCT ProductID), MIN(ProductID), MAX(ProductID)
FROM Production.ProductProductPhoto
;

-- a product with no photo.
-- 0: every product has a photo.
SELECT COUNT(*) AS without
FROM Production.Product p
WHERE NOT EXISTS (SELECT 1
                  FROM Production.ProductProductPhoto pp
                  WHERE pp.ProductID = p.ProductID)
;


-- ------------------------------------------------------------------------------------------------------ ProductPhotoID
-- the photos the rows cover, and the most shown, with the products of each; 42 photos; 1 (no_image_available) shows 346
-- products, 160 shows 15, 87 shows 13.
SELECT TOP (3) ProductPhotoID, COUNT(*) AS products, (SELECT COUNT(DISTINCT ProductPhotoID)
                                                       FROM Production.ProductProductPhoto) AS photos
FROM Production.ProductProductPhoto
GROUP BY ProductPhotoID
ORDER BY products DESC, ProductPhotoID ASC
;


-- ------------------------------------------------------------------------------------------------------------- Primary
-- the primary photos and the others; all 504 are primary.
SELECT [Primary], COUNT(*) AS photos
FROM Production.ProductProductPhoto
GROUP BY [Primary]
ORDER BY [Primary] ASC
;

-- a product with no primary photo, or with more than one; the schema does not keep it out.
-- no rows: every product has exactly one primary photo, its only one.
SELECT ProductID, SUM(CAST([Primary] AS int)) AS primaries
FROM Production.ProductProductPhoto
GROUP BY ProductID
HAVING SUM(CAST([Primary] AS int)) <> 1
ORDER BY ProductID ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-03-31 00:00:00.000 to 2024-04-29 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductProductPhoto
;

