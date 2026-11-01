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
-- Profiling queries on Production.ProductModelProductDescriptionCulture, a section per column. Each says what it looks
-- for and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductModelProductDescriptionCulture.sql
--


-- ------------------------------------------------------------------------------------------------------ ProductModelID
-- how many rows, and how many models they cover; 762 rows, on 127 models.
SELECT COUNT(*), COUNT(DISTINCT ProductModelID)
FROM Production.ProductModelProductDescriptionCulture
;

-- a model described in other than the six cultures.
-- no rows: every model is described in ar, en, fr, he, th and zh-cht.
SELECT ProductModelID, COUNT(*) AS cultures
FROM Production.ProductModelProductDescriptionCulture
GROUP BY ProductModelID
HAVING COUNT(*) <> 6
;


-- ------------------------------------------------------------------------------------------------ ProductDescriptionID
-- the schema does not keep ProductDescriptionID unique here; a description more than one row uses.
-- no rows: each description belongs to one model and one culture.
SELECT ProductDescriptionID, COUNT(*) AS rowsWith
FROM Production.ProductModelProductDescriptionCulture
GROUP BY ProductDescriptionID
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- CultureID
-- the cultures, and the rows of each; ar, en, fr, he, th and zh-cht, with 127 each.
SELECT '[' + CultureID + ']' AS CultureID, COUNT(*) AS descriptions
FROM Production.ProductModelProductDescriptionCulture
GROUP BY CultureID
ORDER BY CultureID ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2024-04-29 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductModelProductDescriptionCulture
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a model with two descriptions in one culture; the key is the three columns, so the schema does not keep it out.
-- no rows: a model has at most one description per culture.
SELECT ProductModelID, CultureID, COUNT(*) AS descriptions
FROM Production.ProductModelProductDescriptionCulture
GROUP BY ProductModelID, CultureID
HAVING COUNT(*) > 1
;

-- an English description which is the same text in another culture.
-- no rows: no other culture copies the English text.
SELECT TOP (3) e.ProductModelID, o.CultureID, LEFT(d.Description, 40) AS Description
FROM Production.ProductModelProductDescriptionCulture e
         JOIN Production.ProductModelProductDescriptionCulture o
              ON o.ProductModelID = e.ProductModelID AND o.CultureID <> e.CultureID
         JOIN Production.ProductDescription d ON d.ProductDescriptionID = e.ProductDescriptionID
         JOIN Production.ProductDescription od ON od.ProductDescriptionID = o.ProductDescriptionID
WHERE e.CultureID = 'en'
  AND od.Description = d.Description
ORDER BY e.ProductModelID ASC, o.CultureID ASC
;

