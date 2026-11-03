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
-- Profiling queries on Production.Illustration, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_Illustration.sql
--


-- ------------------------------------------------------------------------------------------------------ IllustrationID
-- how many rows, and the smallest and the largest IllustrationID; 5 illustrations, 3 to 7.
SELECT COUNT(*), MIN(IllustrationID), MAX(IllustrationID)
FROM Production.Illustration
;


-- ------------------------------------------------------------------------------------------------------------- Diagram
-- Diagram is a nullable xml; how many illustrations have none, and how large the others are, in bytes; none; 26295 to
-- 76100 bytes.
SELECT COUNT(*) - COUNT(Diagram) AS without,
       MIN(DATALENGTH(Diagram)),
       MAX(DATALENGTH(Diagram))
FROM Production.Illustration
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2025-01-08 13:06:11.780 to 2025-01-08 13:06:12.080, the moments the rows were
-- loaded.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.Illustration
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the product models each illustration is used by; 3, 4 and 5 two models each, 6 one, and 7 none.
SELECT i.IllustrationID, COUNT(m.ProductModelID) AS productModels
FROM Production.Illustration i
         LEFT JOIN Production.ProductModelIllustration m ON m.IllustrationID = i.IllustrationID
GROUP BY i.IllustrationID
ORDER BY i.IllustrationID ASC
;

