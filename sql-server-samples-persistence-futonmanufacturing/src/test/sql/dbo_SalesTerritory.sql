-- -
-- #%L
-- sql-server-samples-persistence-futonmanufacturing
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
-- Profiling queries on dbo.SalesTerritory, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_SalesTerritory.sql
--


-- --------------------------------------------------------------------------------------------------------- TerritoryID
-- how many rows, and the smallest and the largest TerritoryID; 6 territories, 1 to 6.
SELECT COUNT(*), MIN(TerritoryID), MAX(TerritoryID)
FROM dbo.SalesTerritory
;


-- ------------------------------------------------------------------------------------------------------- TerritoryCode
-- the codes, in the order of TerritoryID; NW-01, CA-01, CA-02, MW-01, SE-01 and NE-01.
SELECT TerritoryID, TerritoryCode
FROM dbo.SalesTerritory
ORDER BY TerritoryID ASC
;

-- the shortest and the longest TerritoryCode; 5 and 5 characters, of the 20 the column allows.
SELECT MIN(LEN(TerritoryCode)), MAX(LEN(TerritoryCode))
FROM dbo.SalesTerritory
;

-- a TerritoryCode which is blank, or carries leading or trailing spaces.
-- no rows: every TerritoryCode is trimmed and not blank.
SELECT TerritoryID, '[' + TerritoryCode + ']' AS TerritoryCode
FROM dbo.SalesTerritory
WHERE TRIM(TerritoryCode) = ''
   OR DATALENGTH(TerritoryCode) <> DATALENGTH(TRIM(TerritoryCode))
;

-- a unique constraint keeps TerritoryCode unique.
-- no rows: every TerritoryCode is distinct.
SELECT TerritoryCode, COUNT(*) AS territories
FROM dbo.SalesTerritory
GROUP BY TerritoryCode
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------- TerritoryName
-- the shortest and the longest TerritoryName; 9 and 19 characters, of the 100 the column allows.
SELECT MIN(LEN(TerritoryName)), MAX(LEN(TerritoryName))
FROM dbo.SalesTerritory
;

-- a TerritoryName which is blank, or carries leading or trailing spaces.
-- no rows: every TerritoryName is trimmed and not blank.
SELECT TerritoryID, '[' + TerritoryName + ']' AS TerritoryName
FROM dbo.SalesTerritory
WHERE TRIM(TerritoryName) = ''
   OR DATALENGTH(TerritoryName) <> DATALENGTH(TRIM(TerritoryName))
;

-- nothing keeps TerritoryName unique, yet no two rows share one.
-- no rows: every TerritoryName is distinct.
SELECT TerritoryName, COUNT(*) AS territories
FROM dbo.SalesTerritory
GROUP BY TerritoryName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------------- Region
-- the regions the territories fall in; none is NULL, Central with 1, East with 2 and West with 3.
SELECT Region, COUNT(*) AS territories
FROM dbo.SalesTerritory
GROUP BY Region
ORDER BY Region ASC
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes; all 6 territories are active.
SELECT IsActive, COUNT(*) AS territories
FROM dbo.SalesTerritory
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the sales reps and the customers of each territory; NW-01 with 3 reps and 6 customers, CA-01 with 1 and 2, and
-- the other four with a rep each and no customer.
SELECT t.TerritoryID, t.TerritoryCode,
       (SELECT COUNT(*) FROM dbo.SalesRep r WHERE r.TerritoryID = t.TerritoryID) AS reps,
       (SELECT COUNT(*) FROM dbo.Customer c WHERE c.TerritoryID = t.TerritoryID) AS customers
FROM dbo.SalesTerritory t
ORDER BY t.TerritoryID ASC
;

