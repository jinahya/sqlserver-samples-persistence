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
-- Profiling queries on dbo.PriceListDetail, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_PriceListDetail.sql
--


-- --------------------------------------------------------------------------------------------------- PriceListDetailID
-- how many rows, and the smallest and the largest PriceListDetailID; 18 rows, 1 to 18.
SELECT COUNT(*), MIN(PriceListDetailID), MAX(PriceListDetailID)
FROM dbo.PriceListDetail
;


-- --------------------------------------------------------------------------------------------------------- PriceListID
-- the rows of each price list; 6 each.
SELECT PriceListID, COUNT(*) AS items
FROM dbo.PriceListDetail
GROUP BY PriceListID
ORDER BY PriceListID ASC
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- the types of the items priced; only finished goods (3), 18 rows of 6 items.
SELECT i.ItemTypeID, COUNT(*) AS rows, COUNT(DISTINCT d.ItemID) AS items
FROM dbo.PriceListDetail d
         JOIN dbo.Items i ON i.ItemID = d.ItemID
GROUP BY i.ItemTypeID
;

-- nothing keeps an item to one row per price list.
-- no rows: no list prices an item twice.
SELECT PriceListID, ItemID, COUNT(*) AS rows
FROM dbo.PriceListDetail
GROUP BY PriceListID, ItemID
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- the smallest and the largest UnitPrice; 254.9915 and 899.9900.
SELECT MIN(UnitPrice), MAX(UnitPrice)
FROM dbo.PriceListDetail
;

-- the UnitPrice of each list as a share of the item's ListPrice; the retail list (1) at the ListPrice, the online
-- one (2) at .98 of it and the wholesale one (3) at .85, for every item.
SELECT d.PriceListID, MIN(d.UnitPrice / i.ListPrice) AS lowest, MAX(d.UnitPrice / i.ListPrice) AS highest
FROM dbo.PriceListDetail d
         JOIN dbo.Items i ON i.ItemID = d.ItemID
GROUP BY d.PriceListID
ORDER BY d.PriceListID ASC
;


-- ----------------------------------------------------------------------------------------------------- MinimumQuantity
-- the MinimumQuantity of each list, NULL for none; 1.00 on the retail and the online lists, 10.00 on the wholesale.
SELECT PriceListID, MinimumQuantity, COUNT(*) AS items
FROM dbo.PriceListDetail
GROUP BY PriceListID, MinimumQuantity
ORDER BY PriceListID ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the sales order lines of each channel, and how many are priced at the channel's own list and how many at the
-- retail one; the 9 RETAIL lines at both, the same price, but none of the 4 ONLINE or the 10 WHOLESALE lines at
-- its own list: every order line is priced at the retail list.
SELECT o.SalesChannelID, COUNT(*) AS lines,
       SUM(CASE WHEN l.UnitPrice = d.UnitPrice THEN 1 ELSE 0 END) AS at_own_list,
       SUM(CASE WHEN l.UnitPrice = r.UnitPrice THEN 1 ELSE 0 END) AS at_retail_list
FROM dbo.SalesOrderDetail l
         JOIN dbo.SalesOrder o ON o.SalesOrderID = l.SalesOrderID
         JOIN dbo.PriceList p ON p.SalesChannelID = o.SalesChannelID
         JOIN dbo.PriceListDetail d ON d.PriceListID = p.PriceListID AND d.ItemID = l.ItemID
         JOIN dbo.PriceListDetail r ON r.PriceListID = 1 AND r.ItemID = l.ItemID
GROUP BY o.SalesChannelID
ORDER BY o.SalesChannelID ASC
;

