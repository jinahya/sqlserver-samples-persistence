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
-- Profiling queries on Production.ProductDescription, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductDescription.sql
--


-- ------------------------------------------------------------------------------------------------ ProductDescriptionID
-- how many rows, and the smallest and the largest ProductDescriptionID; 762 descriptions, 3 to 2010.
SELECT COUNT(*), MIN(ProductDescriptionID), MAX(ProductDescriptionID)
FROM Production.ProductDescription
;

-- the gaps in ProductDescriptionID, below the largest: how many values are missing; 1246; the ids are sparse, 762 of
-- the 2008 values.
SELECT MAX(ProductDescriptionID) - MIN(ProductDescriptionID) + 1 - COUNT(*) AS missing
FROM Production.ProductDescription
;


-- --------------------------------------------------------------------------------------------------------- Description
-- the shortest and the longest Description; 3 and 269 characters, of the 400 the column allows.
SELECT MIN(LEN(Description)), MAX(LEN(Description))
FROM Production.ProductDescription
;

-- the shortest descriptions, all of them Chinese; 1855 with 3 characters, 1943 with 5, 1882 with 6.
SELECT TOP (3) ProductDescriptionID, Description
FROM Production.ProductDescription
ORDER BY LEN(Description) ASC, ProductDescriptionID ASC
;

-- a Description which is blank, or carries leading or trailing spaces.
-- no rows: every Description is trimmed and not blank.
SELECT ProductDescriptionID, '[' + Description + ']' AS Description
FROM Production.ProductDescription
WHERE TRIM(Description) = ''
   OR DATALENGTH(Description) <> DATALENGTH(TRIM(Description))
ORDER BY ProductDescriptionID ASC
;

-- the schema has no unique key on Description; how many Descriptions more than one row has, and how many rows.
-- 29 Descriptions, on 58 rows, two each.
SELECT COUNT(*) AS descriptions, SUM(x.copies) AS rowsWith
FROM (SELECT COUNT(*) AS copies
      FROM Production.ProductDescription
      GROUP BY Description
      HAVING COUNT(*) > 1) x
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_ProductDescription_rowguid keeps rowguid unique.
-- no rows: no two descriptions share a rowguid.
SELECT rowguid, COUNT(*) AS descriptions
FROM Production.ProductDescription
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2024-04-29 00:00:00.000 to 2025-02-07 10:32:17.973.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductDescription
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a description no product model uses.
-- 0: every description is used.
SELECT COUNT(*) AS unused
FROM Production.ProductDescription d
WHERE NOT EXISTS (SELECT 1
                  FROM Production.ProductModelProductDescriptionCulture m
                  WHERE m.ProductDescriptionID = d.ProductDescriptionID)
;

-- a description used by more than one model or culture.
-- no rows: each description is used by exactly one model and culture.
SELECT d.ProductDescriptionID, COUNT(*) AS uses
FROM Production.ProductDescription d
         JOIN Production.ProductModelProductDescriptionCulture m ON m.ProductDescriptionID = d.ProductDescriptionID
GROUP BY d.ProductDescriptionID
HAVING COUNT(*) > 1
;

