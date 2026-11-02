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
-- Profiling queries on the view Production.vProductModelInstructions, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_vProductModelInstructions.sql
--


-- ------------------------------------------------------------------------------------------------------ ProductModelID
-- how many rows, and the models they show, a row per step of a location; 131 rows, on 9 models, 7 to 67.
SELECT COUNT(*), COUNT(DISTINCT ProductModelID), MIN(ProductModelID), MAX(ProductModelID)
FROM Production.vProductModelInstructions
;

-- the steps of each model; 7 and 10 with 28, 43 with 12, 44 with 11, 47 and 48 with 18, 53 with 8, 66 and 67 with 4.
SELECT ProductModelID, COUNT(*) AS steps
FROM Production.vProductModelInstructions
GROUP BY ProductModelID
ORDER BY ProductModelID ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the models the view shows, by name; the touring frames, wheels, handlebars, pedal and seats; HL Touring Frame (7) to
-- HL Touring Seat/Saddle (67).
SELECT DISTINCT ProductModelID, Name
FROM Production.vProductModelInstructions
ORDER BY ProductModelID ASC
;


-- -------------------------------------------------------------------------------------------------------- Instructions
-- Instructions is the text of the XML's root, the same for every step of a model; how many models have none, and
-- how long the others are; none, 481 to 538 characters.
SELECT COUNT(DISTINCT ProductModelID) - COUNT(DISTINCT CASE WHEN Instructions IS NOT NULL THEN ProductModelID END),
       MIN(LEN(Instructions)),
       MAX(LEN(Instructions))
FROM Production.vProductModelInstructions
;


-- ---------------------------------------------------------------------------------------------------------- LocationID
-- the locations the steps are at, and the steps at each; 7 locations, 4 to 60; Subassembly (50) the most with 67 steps,
-- in all 9 models.
SELECT LocationID, COUNT(*) AS steps, COUNT(DISTINCT ProductModelID) AS productModels
FROM Production.vProductModelInstructions
GROUP BY LocationID
ORDER BY LocationID ASC
;

-- a LocationID which names no location.
-- no rows: every step is at a location of the table.
SELECT DISTINCT v.LocationID
FROM Production.vProductModelInstructions v
WHERE NOT EXISTS (SELECT 1
                  FROM Production.Location l
                  WHERE l.LocationID = v.LocationID)
;


-- ---------------------------------------------------------------------------------------------------------- SetupHours
-- how many steps have no SetupHours, and the smallest and the largest; 81 have none; 0.1000 to 0.5000.
SELECT COUNT(*) - COUNT(SetupHours) AS without, MIN(SetupHours), MAX(SetupHours)
FROM Production.vProductModelInstructions
;


-- -------------------------------------------------------------------------------------------------------- MachineHours
-- how many steps have no MachineHours, and the smallest and the largest; 86 have none; 0.6500 to 3.0000.
SELECT COUNT(*) - COUNT(MachineHours) AS without, MIN(MachineHours), MAX(MachineHours)
FROM Production.vProductModelInstructions
;


-- ---------------------------------------------------------------------------------------------------------- LaborHours
-- how many steps have no LaborHours, and the smallest and the largest; none; 0.5000 to 4.0000.
SELECT COUNT(*) - COUNT(LaborHours) AS without, MIN(LaborHours), MAX(LaborHours)
FROM Production.vProductModelInstructions
;


-- ------------------------------------------------------------------------------------------------------------- LotSize
-- how many steps have no LotSize, and the lot sizes of the others; every step has one; 1 for 89, 20 for 18, 100 for 24.
SELECT LotSize, COUNT(*) AS steps
FROM Production.vProductModelInstructions
GROUP BY LotSize
ORDER BY LotSize ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Step
-- how many steps have no text, and the shortest and the longest; none; 17 to 240 characters, the line breaks and tabs
-- counted.
SELECT COUNT(*) - COUNT(Step) AS without, MIN(LEN(Step)), MAX(LEN(Step))
FROM Production.vProductModelInstructions
;

-- a Step which keeps the line breaks and the tabs of the XML it is read from; how many.
-- 86 of the 131.
SELECT COUNT(*) AS laidOut
FROM Production.vProductModelInstructions
WHERE Step LIKE '%' + CHAR(10) + '%'
   OR Step LIKE '%' + CHAR(9) + '%'
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- a rowguid other than the model's.
-- no rows: the view carries ProductModel.rowguid.
SELECT DISTINCT v.ProductModelID
FROM Production.vProductModelInstructions v
         JOIN Production.ProductModel m ON m.ProductModelID = v.ProductModelID
WHERE v.rowguid <> m.rowguid
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the rows carry; 2015-04-15 16:34:28.980 to 2015-04-15 16:34:29.043.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.vProductModelInstructions
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a model with Instructions the view leaves out.
-- no rows: the view shows the 9 models with Instructions.
SELECT m.ProductModelID, m.Name
FROM Production.ProductModel m
WHERE m.Instructions IS NOT NULL
  AND NOT EXISTS (SELECT 1
                  FROM Production.vProductModelInstructions v
                  WHERE v.ProductModelID = m.ProductModelID)
;

