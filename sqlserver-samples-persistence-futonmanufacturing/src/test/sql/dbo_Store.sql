-- -
-- #%L
-- sqlserver-samples-persistence-futonmanufacturing
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
-- Profiling queries on dbo.Store, a section per column. Each says what it looks for and what the result should be; 'no
-- rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_Store.sql
--


-- ------------------------------------------------------------------------------------------------------------- StoreID
-- how many rows, and the smallest and the largest StoreID; 7 stores, 1 to 7.
SELECT COUNT(*), MIN(StoreID), MAX(StoreID)
FROM dbo.Store
;


-- ----------------------------------------------------------------------------------------------------------- StoreCode
-- the codes, in the order of StoreID; STR-PDX-01, STR-PDX-02, STR-SEA-01, STR-SF-01, STR-LA-01, ONLINE-01 and WHSL-01.
SELECT StoreID, StoreCode
FROM dbo.Store
ORDER BY StoreID ASC
;

-- the shortest and the longest StoreCode; 7 and 10 characters, of the 20 the column allows.
SELECT MIN(LEN(StoreCode)), MAX(LEN(StoreCode))
FROM dbo.Store
;

-- a StoreCode which is blank, or carries leading or trailing spaces.
-- no rows: every StoreCode is trimmed and not blank.
SELECT StoreID, '[' + StoreCode + ']' AS StoreCode
FROM dbo.Store
WHERE TRIM(StoreCode) = ''
   OR DATALENGTH(StoreCode) <> DATALENGTH(TRIM(StoreCode))
;

-- a unique constraint keeps StoreCode unique.
-- no rows: every StoreCode is distinct.
SELECT StoreCode, COUNT(*) AS stores
FROM dbo.Store
GROUP BY StoreCode
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- StoreName
-- the shortest and the longest StoreName; 17 and 23 characters, of the 100 the column allows.
SELECT MIN(LEN(StoreName)), MAX(LEN(StoreName))
FROM dbo.Store
;

-- a StoreName which is blank, or carries leading or trailing spaces.
-- no rows: every StoreName is trimmed and not blank.
SELECT StoreID, '[' + StoreName + ']' AS StoreName
FROM dbo.Store
WHERE TRIM(StoreName) = ''
   OR DATALENGTH(StoreName) <> DATALENGTH(TRIM(StoreName))
;

-- nothing keeps StoreName unique, yet no two rows share one.
-- no rows: every StoreName is distinct.
SELECT StoreName, COUNT(*) AS stores
FROM dbo.Store
GROUP BY StoreName
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------ SalesChannelID
-- the stores of each channel; RETAIL (1) with 5, ONLINE (2) and WHOLESALE (3) with 1 each.
SELECT SalesChannelID, COUNT(*) AS stores
FROM dbo.Store
GROUP BY SalesChannelID
ORDER BY SalesChannelID ASC
;


-- ------------------------------------------------------------------------------------------------------------- Manager
-- how many Manager values are NULL, and the shortest and the longest of the rest; none, 10 and 17 characters, of the
-- 100 the column allows.
SELECT SUM(CASE WHEN Manager IS NULL THEN 1 ELSE 0 END), MIN(LEN(Manager)), MAX(LEN(Manager))
FROM dbo.Store
;

-- a Manager which is blank, or carries leading or trailing spaces.
-- no rows: every Manager is trimmed and not blank.
SELECT StoreID, '[' + Manager + ']' AS Manager
FROM dbo.Store
WHERE TRIM(Manager) = ''
   OR DATALENGTH(Manager) <> DATALENGTH(TRIM(Manager))
;

-- nothing keeps Manager unique, yet no two rows share one.
-- no rows: every Manager is distinct.
SELECT Manager, COUNT(*) AS stores
FROM dbo.Store
GROUP BY Manager
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------------- Phone
-- how many Phone values are NULL, and how many do not read NNN-555-NNNN; none, and none.
SELECT SUM(CASE WHEN Phone IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN Phone NOT LIKE '[0-9][0-9][0-9]-555-[0-9][0-9][0-9][0-9]' THEN 1 ELSE 0 END)
FROM dbo.Store
;


-- --------------------------------------------------------------------------------------------------------------- Email
-- how many Email values are NULL, of all the rows; 7 of 7, no store has one.
SELECT SUM(CASE WHEN Email IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.Store
;


-- ------------------------------------------------------------------------------------------------------------- Address
-- how many Address values are NULL, and the shortest and the longest of the rest; none, 13 and 23 characters, of the
-- 255 the column allows.
SELECT SUM(CASE WHEN Address IS NULL THEN 1 ELSE 0 END), MIN(LEN(Address)), MAX(LEN(Address))
FROM dbo.Store
;

-- an Address which is blank, or carries leading or trailing spaces.
-- no rows: every Address is trimmed and not blank.
SELECT StoreID, '[' + Address + ']' AS Address
FROM dbo.Store
WHERE TRIM(Address) = ''
   OR DATALENGTH(Address) <> DATALENGTH(TRIM(Address))
;

-- nothing keeps Address unique, and two stores share one; 1200 Industrial Parkway, of ONLINE-01 and WHSL-01, the
-- address of the company itself.
SELECT Address, COUNT(*) AS stores
FROM dbo.Store
GROUP BY Address
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- the cities and their states, the most stores first; Portland, OR with 4, and Los Angeles, San Francisco and Seattle
-- with 1 each.
SELECT City, State, COUNT(*) AS stores
FROM dbo.Store
GROUP BY City, State
ORDER BY stores DESC, City ASC
;


-- --------------------------------------------------------------------------------------------------------------- State
-- the states, written as two-letter codes; none is NULL, OR with 4, CA with 2 and WA with 1.
SELECT State, COUNT(*) AS stores
FROM dbo.Store
GROUP BY State
ORDER BY stores DESC, State ASC
;


-- ------------------------------------------------------------------------------------------------------------- ZipCode
-- how many ZipCode values are NULL, and how many are not five digits; none, and none.
SELECT SUM(CASE WHEN ZipCode IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN ZipCode NOT LIKE '[0-9][0-9][0-9][0-9][0-9]' THEN 1 ELSE 0 END)
FROM dbo.Store
;


-- ------------------------------------------------------------------------------------------------------------ OpenDate
-- how many OpenDate values are NULL, and the earliest and the latest; none, 2015-01-01 and 2019-11-10.
SELECT SUM(CASE WHEN OpenDate IS NULL THEN 1 ELSE 0 END), MIN(OpenDate), MAX(OpenDate)
FROM dbo.Store
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes; all 7 stores are active.
SELECT IsActive, COUNT(*) AS stores
FROM dbo.Store
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the sales orders of each store; WHSL-01 with 5, ONLINE-01 with 4, STR-SF-01 with 2 and the other four with 1 each.
SELECT s.StoreID, s.StoreCode, COUNT(o.SalesOrderID) AS orders
FROM dbo.Store s
         LEFT JOIN dbo.SalesOrder o ON o.StoreID = s.StoreID
GROUP BY s.StoreID, s.StoreCode
ORDER BY s.StoreID ASC
;

-- a sales order placed before its store opened.
-- no rows: every store had opened years before the orders of 2024.
SELECT o.SalesOrderID, o.OrderDate, s.StoreID, s.OpenDate
FROM dbo.SalesOrder o
         JOIN dbo.Store s ON s.StoreID = o.StoreID
WHERE o.OrderDate < s.OpenDate
;

