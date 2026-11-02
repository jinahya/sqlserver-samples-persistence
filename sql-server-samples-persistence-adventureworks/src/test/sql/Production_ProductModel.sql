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
-- Profiling queries on Production.ProductModel, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductModel.sql
--


-- ------------------------------------------------------------------------------------------------------ ProductModelID
-- how many rows, and the smallest and the largest ProductModelID; 128 models, 1 to 128.
SELECT COUNT(*), MIN(ProductModelID), MAX(ProductModelID)
FROM Production.ProductModel
;

-- a ProductModelID whose next value is missing, below the largest.
-- no rows: ProductModelID runs from 1 without a gap.
SELECT a.ProductModelID + 1 AS missingFrom
FROM Production.ProductModel a
WHERE a.ProductModelID < (SELECT MAX(ProductModelID) FROM Production.ProductModel)
  AND NOT EXISTS (SELECT 1
                  FROM Production.ProductModel b
                  WHERE b.ProductModelID = a.ProductModelID + 1)
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 5 and 27 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Production.ProductModel
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT ProductModelID, '[' + Name + ']' AS Name
FROM Production.ProductModel
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_ProductModel_Name keeps Name unique.
-- no rows: no two models share a Name.
SELECT Name, COUNT(*) AS productModels
FROM Production.ProductModel
GROUP BY Name
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------- CatalogDescription
-- CatalogDescription is a nullable xml; how many models have none, and how large the others are, in bytes; 122 have
-- none; 4352 to 4773 bytes.
SELECT COUNT(*) - COUNT(CatalogDescription) AS without,
       MIN(DATALENGTH(CatalogDescription)),
       MAX(DATALENGTH(CatalogDescription))
FROM Production.ProductModel
;


-- -------------------------------------------------------------------------------------------------------- Instructions
-- Instructions is a nullable xml; how many models have none, and how large the others are, in bytes; 119 have none;
-- 3301 to 10697 bytes.
SELECT COUNT(*) - COUNT(Instructions) AS without, MIN(DATALENGTH(Instructions)), MAX(DATALENGTH(Instructions))
FROM Production.ProductModel
;

-- a model with both a catalog description and instructions.
-- no rows: the 6 described for the catalog and the 9 with instructions are different models.
SELECT ProductModelID, Name
FROM Production.ProductModel
WHERE CatalogDescription IS NOT NULL
  AND Instructions IS NOT NULL
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_ProductModel_rowguid keeps rowguid unique.
-- no rows: no two models share a rowguid.
SELECT rowguid, COUNT(*) AS productModels
FROM Production.ProductModel
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2015-04-15 16:34:28.980 to 2024-04-29 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductModel
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many models no product is built on; 9.
SELECT COUNT(*) AS unused
FROM Production.ProductModel m
WHERE NOT EXISTS (SELECT 1
                  FROM Production.Product p
                  WHERE p.ProductModelID = m.ProductModelID)
;

-- how many models have no description, in any culture; 1.
SELECT COUNT(*) AS undescribed
FROM Production.ProductModel m
WHERE NOT EXISTS (SELECT 1
                  FROM Production.ProductModelProductDescriptionCulture d
                  WHERE d.ProductModelID = m.ProductModelID)
;

-- the models with the most products; LL Road Frame (9) and Road-650 (30) with 12 each, HL Road Frame (6) with 11.
SELECT TOP (3) m.ProductModelID, m.Name, COUNT(*) AS products
FROM Production.ProductModel m
         JOIN Production.Product p ON p.ProductModelID = m.ProductModelID
GROUP BY m.ProductModelID, m.Name
ORDER BY products DESC, m.ProductModelID ASC
;

