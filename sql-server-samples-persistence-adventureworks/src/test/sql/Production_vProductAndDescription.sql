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
-- Profiling queries on the view Production.vProductAndDescription, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_vProductAndDescription.sql
--


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- how many rows, and how many products they show; 1764 rows, on 294 products, 680 to 999.
SELECT COUNT(*), COUNT(DISTINCT ProductID), MIN(ProductID), MAX(ProductID)
FROM Production.vProductAndDescription
;

-- the products by the cultures they are shown in; all 294 in 6 cultures.
SELECT x.cultures, COUNT(*) AS products
FROM (SELECT ProductID, COUNT(*) AS cultures
      FROM Production.vProductAndDescription
      GROUP BY ProductID) x
GROUP BY x.cultures
ORDER BY x.cultures ASC
;

-- IX_vProductAndDescription, the view's unique clustered index, keeps CultureID and ProductID unique.
-- no rows: no product is shown twice in a culture.
SELECT CultureID, ProductID, COUNT(*) AS rowsWith
FROM Production.vProductAndDescription
GROUP BY CultureID, ProductID
HAVING COUNT(*) > 1
;

-- a product with a model which the view leaves out; the view joins the model's descriptions.
-- one row: 907 (Rear Brakes), whose model 128 has no description.
SELECT p.ProductID, p.Name
FROM Production.Product p
WHERE p.ProductModelID IS NOT NULL
  AND NOT EXISTS (SELECT 1
                  FROM Production.vProductAndDescription v
                  WHERE v.ProductID = p.ProductID)
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 5 and 32 characters.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Production.vProductAndDescription
;


-- -------------------------------------------------------------------------------------------------------- ProductModel
-- the models the products are built on; 118 models; 5 to 27 characters.
SELECT COUNT(DISTINCT ProductModel), MIN(LEN(ProductModel)), MAX(LEN(ProductModel))
FROM Production.vProductAndDescription
;


-- ----------------------------------------------------------------------------------------------------------- CultureID
-- the cultures, and the rows of each; ar, en, fr, he, th and zh-cht, with 294 each.
SELECT '[' + CultureID + ']' AS CultureID, COUNT(*) AS products
FROM Production.vProductAndDescription
GROUP BY CultureID
ORDER BY CultureID ASC
;


-- --------------------------------------------------------------------------------------------------------- Description
-- the shortest and the longest Description, by culture; ar 17 to 255, en 15 to 221, fr 13 to 269, he 16 to 203, th 11
-- to 196, zh-cht 3 to 83.
SELECT CultureID, MIN(LEN(Description)), MAX(LEN(Description))
FROM Production.vProductAndDescription
GROUP BY CultureID
ORDER BY CultureID ASC
;

