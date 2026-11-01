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
-- Profiling queries on Production.Location, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_Location.sql
--


-- ---------------------------------------------------------------------------------------------------------- LocationID
-- how many rows, and the smallest and the largest LocationID; 14 locations, 1 to 60.
SELECT COUNT(*), MIN(LocationID), MAX(LocationID)
FROM Production.Location
;

-- a LocationID whose next value is missing, below the largest.
-- 7 rows: LocationID counts in steps, 1 to 7 for the storages, then 10, 20, 30, 40, 45, 50 and 60 for the shop floor.
SELECT a.LocationID + 1 AS missingFrom
FROM Production.Location a
WHERE a.LocationID < (SELECT MAX(LocationID) FROM Production.Location)
  AND NOT EXISTS (SELECT 1
                  FROM Production.Location b
                  WHERE b.LocationID = a.LocationID + 1)
ORDER BY missingFrom ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 5 and 22 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Production.Location
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT LocationID, '[' + Name + ']' AS Name
FROM Production.Location
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_Location_Name keeps Name unique.
-- no rows: no two locations share a Name.
SELECT Name, COUNT(*) AS locations
FROM Production.Location
GROUP BY Name
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ CostRate
-- the cost rates the locations charge; 7 rates; 0.0000 for the 7 storages, 12.2500 to 25.0000 for the others.
SELECT CostRate, COUNT(*) AS locations
FROM Production.Location
GROUP BY CostRate
ORDER BY CostRate ASC
;


-- -------------------------------------------------------------------------------------------------------- Availability
-- the smallest and the largest Availability, and how many locations have none; 0.00 and 120.00; 7 with none.
SELECT MIN(Availability), MAX(Availability), SUM(CASE WHEN Availability = 0 THEN 1 ELSE 0 END) AS zero
FROM Production.Location
;

-- a location with a cost rate but no availability, or the other way round.
-- no rows: a location charges if and only if it has availability.
SELECT LocationID, Name, CostRate, Availability
FROM Production.Location
WHERE (CostRate = 0 AND Availability <> 0)
   OR (CostRate <> 0 AND Availability = 0)
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.Location
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the inventory rows and the work order routings at each location; every location holds inventory; only 10 to 60, the
-- shop floor, route work orders, Subassembly (50) the most with 30107.
SELECT l.LocationID, l.Name,
       (SELECT COUNT(*) FROM Production.ProductInventory i WHERE i.LocationID = l.LocationID) AS inventories,
       (SELECT COUNT(*) FROM Production.WorkOrderRouting r WHERE r.LocationID = l.LocationID) AS routings
FROM Production.Location l
ORDER BY l.LocationID ASC
;

