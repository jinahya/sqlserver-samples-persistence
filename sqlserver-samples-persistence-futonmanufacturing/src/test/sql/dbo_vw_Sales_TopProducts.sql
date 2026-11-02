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
-- Profiling queries on the view dbo.vw_Sales_TopProducts, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_TopProducts.sql
--


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows, and how many items; 19 rows over the 6 finished goods.
SELECT COUNT(*), COUNT(DISTINCT ItemCode)
FROM dbo.vw_Sales_TopProducts
;

-- the grain of the view; the item, the channel, the year and the month.
-- no rows: no two rows share them.
SELECT ItemCode, ChannelName, Year, Month, COUNT(*) AS rows
FROM dbo.vw_Sales_TopProducts
GROUP BY ItemCode, ChannelName, Year, Month
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- an ItemName which is not the one of its ItemCode.
-- no rows: each matches Items.
SELECT v.ItemCode, v.ItemName
FROM dbo.vw_Sales_TopProducts v
         JOIN dbo.Items i ON i.ItemCode = v.ItemCode
WHERE v.ItemName <> i.ItemName
;


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- the rows of each channel; Online/E-Commerce 4, Retail Store 8, Wholesale 7.
SELECT ChannelName, COUNT(*) AS rows
FROM dbo.vw_Sales_TopProducts
GROUP BY ChannelName
ORDER BY ChannelName ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Year
-- the years; 2024 alone.
SELECT Year, COUNT(*) AS rows
FROM dbo.vw_Sales_TopProducts
GROUP BY Year
;


-- --------------------------------------------------------------------------------------------------------------- Month
-- the months; 10 with 11 rows, 11 with 8.
SELECT Month, COUNT(*) AS rows
FROM dbo.vw_Sales_TopProducts
GROUP BY Month
ORDER BY Month ASC
;


-- ----------------------------------------------------------------------------------------------------------- UnitsSold
-- the units the rows add up to, against the Quantity of every detail line; 150 each, every line being of an FG item.
SELECT SUM(UnitsSold), (SELECT SUM(Quantity) FROM dbo.SalesOrderDetail)
FROM dbo.vw_Sales_TopProducts
;

-- the smallest and the largest UnitsSold; 1 and 32 (FG-FUT-001, wholesale, 2024-10).
SELECT MIN(UnitsSold), MAX(UnitsSold)
FROM dbo.vw_Sales_TopProducts
;


-- ------------------------------------------------------------------------------------------------------------- Revenue
-- the revenue the rows add up to, against the NetAmount of every detail line; 59173.635 each.
SELECT SUM(Revenue), (SELECT SUM(NetAmount) FROM dbo.SalesOrderDetail)
FROM dbo.vw_Sales_TopProducts
;


-- --------------------------------------------------------------------------------------------------------- GrossProfit
-- a GrossProfit which differs from Revenue; no finished good carries a StandardCost, so the cost is zero.
-- no rows: every GrossProfit equals its Revenue.
SELECT ItemCode, ChannelName, Year, Month, Revenue, GrossProfit
FROM dbo.vw_Sales_TopProducts
WHERE GrossProfit <> Revenue
;


-- ----------------------------------------------------------------------------------------------------- UniqueCustomers
-- the smallest and the largest UniqueCustomers; 1 and 2.
SELECT MIN(UniqueCustomers), MAX(UniqueCustomers)
FROM dbo.vw_Sales_TopProducts
;


-- ------------------------------------------------------------------------------------------------------------ UnitRank
-- a UnitRank which is not 1 more than the items of the same channel and month selling more units.
-- no rows: the ranks follow UnitsSold within the partition.
SELECT p.ItemCode, p.ChannelName, p.Year, p.Month, p.UnitRank
FROM dbo.vw_Sales_TopProducts p
WHERE p.UnitRank <> 1 + (SELECT COUNT(*)
                         FROM dbo.vw_Sales_TopProducts q
                         WHERE q.ChannelName = p.ChannelName
                           AND q.Year = p.Year
                           AND q.Month = p.Month
                           AND q.UnitsSold > p.UnitsSold)
;


-- --------------------------------------------------------------------------------------------------------- RevenueRank
-- a RevenueRank which is not 1 more than the items of the same channel and month earning more.
-- no rows: the ranks follow Revenue within the partition.
SELECT p.ItemCode, p.ChannelName, p.Year, p.Month, p.RevenueRank
FROM dbo.vw_Sales_TopProducts p
WHERE p.RevenueRank <> 1 + (SELECT COUNT(*)
                            FROM dbo.vw_Sales_TopProducts q
                            WHERE q.ChannelName = p.ChannelName
                              AND q.Year = p.Year
                              AND q.Month = p.Month
                              AND q.Revenue > p.Revenue)
;


-- ---------------------------------------------------------------------------------------------------------- ProfitRank
-- a ProfitRank which differs from its RevenueRank; with no cost, profit ranks as revenue does.
-- no rows: the two ranks agree.
SELECT ItemCode, ChannelName, Year, Month, RevenueRank, ProfitRank
FROM dbo.vw_Sales_TopProducts
WHERE ProfitRank <> RevenueRank
;

