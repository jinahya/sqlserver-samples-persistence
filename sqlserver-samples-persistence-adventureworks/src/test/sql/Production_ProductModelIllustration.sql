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
-- Profiling queries on Production.ProductModelIllustration, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductModelIllustration.sql
--


-- ------------------------------------------------------------------------------------------------------ ProductModelID
-- how many rows, and how many models they cover; 7 rows, on 5 models.
SELECT COUNT(*), COUNT(DISTINCT ProductModelID)
FROM Production.ProductModelIllustration
;

-- every row, by the key; 7 and 10 with 3, 47 and 48 with 4 and 5, 67 with 6.
SELECT ProductModelID, IllustrationID
FROM Production.ProductModelIllustration
ORDER BY ProductModelID ASC, IllustrationID ASC
;


-- ------------------------------------------------------------------------------------------------------ IllustrationID
-- the illustrations the rows cover; 4 illustrations, 3 to 6; 7 illustrates no model.
SELECT COUNT(DISTINCT IllustrationID), MIN(IllustrationID), MAX(IllustrationID)
FROM Production.ProductModelIllustration
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2025-01-08 14:41:02.167 to 2025-01-08 14:41:02.200.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductModelIllustration
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an illustrated model with no instructions, the illustrations being those of the assembly steps.
-- no rows: every illustrated model has instructions.
SELECT pmi.ProductModelID, m.Name
FROM Production.ProductModelIllustration pmi
         JOIN Production.ProductModel m ON m.ProductModelID = pmi.ProductModelID
WHERE m.Instructions IS NULL
;

