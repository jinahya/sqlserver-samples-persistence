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
-- Profiling queries on dbo.SalesChannel, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_SalesChannel.sql
--


-- ------------------------------------------------------------------------------------------------------ SalesChannelID
-- how many rows, and the smallest and the largest SalesChannelID; 3 channels, 1 to 3.
SELECT COUNT(*), MIN(SalesChannelID), MAX(SalesChannelID)
FROM dbo.SalesChannel
;


-- --------------------------------------------------------------------------------------------------------- ChannelCode
-- the codes, in the order of SalesChannelID; RETAIL, ONLINE and WHOLESALE.
SELECT SalesChannelID, ChannelCode
FROM dbo.SalesChannel
ORDER BY SalesChannelID ASC
;

-- the shortest and the longest ChannelCode; 6 and 9 characters, of the 20 the column allows.
SELECT MIN(LEN(ChannelCode)), MAX(LEN(ChannelCode))
FROM dbo.SalesChannel
;

-- a ChannelCode which is blank, or carries leading or trailing spaces.
-- no rows: every ChannelCode is trimmed and not blank.
SELECT SalesChannelID, '[' + ChannelCode + ']' AS ChannelCode
FROM dbo.SalesChannel
WHERE TRIM(ChannelCode) = ''
   OR DATALENGTH(ChannelCode) <> DATALENGTH(TRIM(ChannelCode))
;

-- a unique constraint keeps ChannelCode unique.
-- no rows: every ChannelCode is distinct.
SELECT ChannelCode, COUNT(*) AS channels
FROM dbo.SalesChannel
GROUP BY ChannelCode
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- the shortest and the longest ChannelName; 9 and 17 characters, of the 100 the column allows.
SELECT MIN(LEN(ChannelName)), MAX(LEN(ChannelName))
FROM dbo.SalesChannel
;

-- a ChannelName which is blank, or carries leading or trailing spaces.
-- no rows: every ChannelName is trimmed and not blank.
SELECT SalesChannelID, '[' + ChannelName + ']' AS ChannelName
FROM dbo.SalesChannel
WHERE TRIM(ChannelName) = ''
   OR DATALENGTH(ChannelName) <> DATALENGTH(TRIM(ChannelName))
;

-- nothing keeps ChannelName unique, yet no two rows share one.
-- no rows: every ChannelName is distinct.
SELECT ChannelName, COUNT(*) AS channels
FROM dbo.SalesChannel
GROUP BY ChannelName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- Description
-- how many Description values are NULL, and the shortest and the longest of the rest; none, 27 and 40 characters, of
-- the 255 the column allows.
SELECT SUM(CASE WHEN Description IS NULL THEN 1 ELSE 0 END), MIN(LEN(Description)), MAX(LEN(Description))
FROM dbo.SalesChannel
;

-- a Description which is blank, or carries leading or trailing spaces.
-- no rows: every Description is trimmed and not blank.
SELECT SalesChannelID, '[' + Description + ']' AS Description
FROM dbo.SalesChannel
WHERE TRIM(Description) = ''
   OR DATALENGTH(Description) <> DATALENGTH(TRIM(Description))
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes; all 3 channels are active.
SELECT IsActive, COUNT(*) AS channels
FROM dbo.SalesChannel
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the stores, the price lists and the sales orders of each channel; RETAIL with 5 stores, 1 price list and 6 orders,
-- ONLINE with 1, 1 and 4, WHOLESALE with 1, 1 and 5.
SELECT c.SalesChannelID, c.ChannelCode,
       (SELECT COUNT(*) FROM dbo.Store s WHERE s.SalesChannelID = c.SalesChannelID) AS stores,
       (SELECT COUNT(*) FROM dbo.PriceList p WHERE p.SalesChannelID = c.SalesChannelID) AS price_lists,
       (SELECT COUNT(*) FROM dbo.SalesOrder o WHERE o.SalesChannelID = c.SalesChannelID) AS orders
FROM dbo.SalesChannel c
ORDER BY c.SalesChannelID ASC
;

-- a sales order sold through one channel at a store of another.
-- no rows: every order with a store shares the store's channel.
SELECT o.SalesOrderID, o.SalesChannelID, s.StoreID, s.SalesChannelID AS store_channel
FROM dbo.SalesOrder o
         JOIN dbo.Store s ON s.StoreID = o.StoreID
WHERE o.SalesChannelID <> s.SalesChannelID
;

