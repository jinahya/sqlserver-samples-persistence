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
-- Profiling queries on Production.ProductDocument, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductDocument.sql
--


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- how many rows, and how many products they cover; 32 rows, on 31 products, 317 to 999.
SELECT COUNT(*), COUNT(DISTINCT ProductID), MIN(ProductID), MAX(ProductID)
FROM Production.ProductDocument
;

-- the products with more than one document; 506 with 2.
SELECT ProductID, COUNT(*) AS documents
FROM Production.ProductDocument
GROUP BY ProductID
HAVING COUNT(*) > 1
ORDER BY ProductID ASC
;


-- -------------------------------------------------------------------------------------------------------- DocumentNode
-- the documents the rows cover, and the products of each; 6 of the 8 files; /1/1/ 4, /2/1/ 10, /3/1/ and /3/2/ 1 each,
-- /3/3/ 7, /3/4/ 9.
SELECT DocumentNode.ToString() AS DocumentNode, COUNT(*) AS products
FROM Production.ProductDocument
GROUP BY DocumentNode
ORDER BY DocumentNode ASC
;

-- a row which points to a folder, not a file.
-- no rows: every row points to a file.
SELECT pd.ProductID, pd.DocumentNode.ToString() AS DocumentNode
FROM Production.ProductDocument pd
         JOIN Production.Document d ON d.DocumentNode = pd.DocumentNode
WHERE d.FolderFlag = 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2024-12-28 13:51:58.103 to 2024-12-28 13:51:58.120.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductDocument
;

