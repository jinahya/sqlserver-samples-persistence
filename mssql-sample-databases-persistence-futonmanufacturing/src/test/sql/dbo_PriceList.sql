-- -
-- #%L
-- mssql-sample-databases-persistence-futonmanufacturing
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
-- Profiling queries on dbo.PriceList, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_PriceList.sql
--


-- --------------------------------------------------------------------------------------------------------- PriceListID
-- how many rows, and the smallest and the largest PriceListID; 3 price lists, 1 to 3.
SELECT COUNT(*), MIN(PriceListID), MAX(PriceListID)
FROM dbo.PriceList
;


-- ------------------------------------------------------------------------------------------------------- PriceListCode
-- the codes, in the order of PriceListID; PL-RETAIL, PL-ONLINE and PL-WHOLESALE.
SELECT PriceListID, PriceListCode
FROM dbo.PriceList
ORDER BY PriceListID ASC
;

-- the shortest and the longest PriceListCode; 9 and 12 characters, of the 20 the column allows.
SELECT MIN(LEN(PriceListCode)), MAX(LEN(PriceListCode))
FROM dbo.PriceList
;

-- a PriceListCode which is blank, or carries leading or trailing spaces.
-- no rows: every PriceListCode is trimmed and not blank.
SELECT PriceListID, '[' + PriceListCode + ']' AS PriceListCode
FROM dbo.PriceList
WHERE TRIM(PriceListCode) = ''
   OR DATALENGTH(PriceListCode) <> DATALENGTH(TRIM(PriceListCode))
;

-- a unique constraint keeps PriceListCode unique.
-- no rows: every PriceListCode is distinct.
SELECT PriceListCode, COUNT(*) AS lists
FROM dbo.PriceList
GROUP BY PriceListCode
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------- PriceListName
-- the shortest and the longest PriceListName; 17 and 20 characters, of the 100 the column allows.
SELECT MIN(LEN(PriceListName)), MAX(LEN(PriceListName))
FROM dbo.PriceList
;

-- a PriceListName which is blank, or carries leading or trailing spaces.
-- no rows: every PriceListName is trimmed and not blank.
SELECT PriceListID, '[' + PriceListName + ']' AS PriceListName
FROM dbo.PriceList
WHERE TRIM(PriceListName) = ''
   OR DATALENGTH(PriceListName) <> DATALENGTH(TRIM(PriceListName))
;

-- nothing keeps PriceListName unique, yet no two rows share one.
-- no rows: every PriceListName is distinct.
SELECT PriceListName, COUNT(*) AS lists
FROM dbo.PriceList
GROUP BY PriceListName
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------ SalesChannelID
-- the channel of each price list, NULL for none; PL-RETAIL for RETAIL, PL-ONLINE for ONLINE and PL-WHOLESALE for
-- WHOLESALE, none NULL.
SELECT p.PriceListID, p.PriceListCode, c.ChannelCode
FROM dbo.PriceList p
         LEFT JOIN dbo.SalesChannel c ON c.SalesChannelID = p.SalesChannelID
ORDER BY p.PriceListID ASC
;

-- nothing keeps a channel to one price list.
-- no rows: no channel has two.
SELECT SalesChannelID, COUNT(*) AS lists
FROM dbo.PriceList
GROUP BY SalesChannelID
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------- EffectiveDate
-- the earliest and the latest EffectiveDate; 2024-01-01 for all 3.
SELECT MIN(EffectiveDate), MAX(EffectiveDate)
FROM dbo.PriceList
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- how many EndDate values are NULL, of all the rows; 3 of 3, no price list has ended.
SELECT SUM(CASE WHEN EndDate IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.PriceList
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes; all 3 price lists are active.
SELECT IsActive, COUNT(*) AS lists
FROM dbo.PriceList
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the items on each price list; 6 each, the 6 finished goods.
SELECT p.PriceListID, p.PriceListCode, COUNT(d.PriceListDetailID) AS items
FROM dbo.PriceList p
         LEFT JOIN dbo.PriceListDetail d ON d.PriceListID = p.PriceListID
GROUP BY p.PriceListID, p.PriceListCode
ORDER BY p.PriceListID ASC
;

