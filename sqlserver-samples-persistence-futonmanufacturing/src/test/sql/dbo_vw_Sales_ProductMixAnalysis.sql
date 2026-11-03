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
-- Profiling queries on the view dbo.vw_Sales_ProductMixAnalysis, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_ProductMixAnalysis.sql
--


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- how many rows, and how many channels; 14 mixes over 3 channels.
SELECT COUNT(*), COUNT(DISTINCT ChannelName)
FROM dbo.vw_Sales_ProductMixAnalysis
;

-- the rows and the orders of each channel; Online/E-Commerce 3 and 4, Retail Store 6 and 6, Wholesale 5 and 5.
SELECT ChannelName, COUNT(*) AS mixes, SUM(OrderCount) AS orders
FROM dbo.vw_Sales_ProductMixAnalysis
GROUP BY ChannelName
ORDER BY ChannelName ASC
;


-- -------------------------------------------------------------------------------------------------------- CustomerType
-- the customer types of each channel; Online/E-Commerce sells to Online and Retail customers, Retail Store to
-- Retail and Wholesale to Wholesale.
SELECT ChannelName, CustomerType, COUNT(*) AS mixes
FROM dbo.vw_Sales_ProductMixAnalysis
GROUP BY ChannelName, CustomerType
ORDER BY ChannelName ASC, CustomerType ASC
;


-- ---------------------------------------------------------------------------------------------------------- ProductMix
-- the grain of the view; the channel, the customer type and the mix.
-- no rows: no two rows share them.
SELECT ChannelName, CustomerType, ProductMix, COUNT(*) AS mixes
FROM dbo.vw_Sales_ProductMixAnalysis
GROUP BY ChannelName, CustomerType, ProductMix
HAVING COUNT(*) > 1
;

-- the shortest and the longest ProductMix; 10 characters, a single item code, and 34, three of them.
SELECT MIN(LEN(ProductMix)), MAX(LEN(ProductMix))
FROM dbo.vw_Sales_ProductMixAnalysis
;


-- ------------------------------------------------------------------------------------------------------ UniqueProducts
-- a UniqueProducts which is not the number of codes in the ProductMix.
-- no rows: the count and the list agree.
SELECT ProductMix, UniqueProducts
FROM dbo.vw_Sales_ProductMixAnalysis
WHERE UniqueProducts <> LEN(ProductMix) - LEN(REPLACE(ProductMix, ',', '')) + 1
;

-- the mixes by the number of products; 7 of 1, 6 of 2 and 1 of 3.
SELECT UniqueProducts, COUNT(*) AS mixes
FROM dbo.vw_Sales_ProductMixAnalysis
GROUP BY UniqueProducts
ORDER BY UniqueProducts ASC
;


-- ---------------------------------------------------------------------------------------------------------- OrderCount
-- the orders the rows add up to, against the orders with a detail line; 15 and 15.
SELECT (SELECT SUM(OrderCount) FROM dbo.vw_Sales_ProductMixAnalysis),
       (SELECT COUNT(DISTINCT SalesOrderID) FROM dbo.SalesOrderDetail)
;

-- the smallest and the largest OrderCount; 1 and 2, FG-FUT-004 alone, twice, online.
SELECT MIN(OrderCount), MAX(OrderCount)
FROM dbo.vw_Sales_ProductMixAnalysis
;


-- -------------------------------------------------------------------------------------------------------- TotalRevenue
-- the revenue the rows add up to, against the NetAmount of every detail line; 59173.635 each.
SELECT (SELECT SUM(TotalRevenue) FROM dbo.vw_Sales_ProductMixAnalysis),
       (SELECT SUM(NetAmount) FROM dbo.SalesOrderDetail)
;

-- the smallest and the largest TotalRevenue; 599.99 and 12239.685.
SELECT MIN(TotalRevenue), MAX(TotalRevenue)
FROM dbo.vw_Sales_ProductMixAnalysis
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not TotalRevenue over OrderCount.
-- no rows: the average follows the total.
SELECT ProductMix, AvgOrderValue, TotalRevenue, OrderCount
FROM dbo.vw_Sales_ProductMixAnalysis
WHERE ABS(AvgOrderValue - TotalRevenue / OrderCount) > 0.000001
;


-- ------------------------------------------------------------------------------------------------------------ AvgUnits
-- the smallest and the largest AvgUnits; 1 and 35, a wholesale order of three products.
SELECT MIN(AvgUnits), MAX(AvgUnits)
FROM dbo.vw_Sales_ProductMixAnalysis
;


-- ------------------------------------------------------------------------------------------------------ PopularityRank
-- the ranks of each channel; only Online/E-Commerce has a mix ordered twice, so it alone ranks 1 and 2, the others
-- all tie at 1.
SELECT ChannelName, PopularityRank, COUNT(*) AS mixes
FROM dbo.vw_Sales_ProductMixAnalysis
GROUP BY ChannelName, PopularityRank
ORDER BY ChannelName ASC, PopularityRank ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an order whose detail lines, to the cent, do not add up to its Subtotal less its DiscountAmount; the view sums
-- the lines, the header carries figures of its own.
-- 10 rows, a finding: the header and the lines disagree on 10 of the 15 orders, by a cent (SO-2024-1102) to
-- 3240.05 (SO-2024-1006, 12239.69 against 8999.64).
SELECT so.OrderNumber, ROUND(SUM(sod.NetAmount), 2) AS lines, so.Subtotal - so.DiscountAmount AS header
FROM dbo.SalesOrder so
         JOIN dbo.SalesOrderDetail sod ON sod.SalesOrderID = so.SalesOrderID
GROUP BY so.OrderNumber, so.Subtotal, so.DiscountAmount
HAVING ROUND(SUM(sod.NetAmount), 2) <> so.Subtotal - so.DiscountAmount
ORDER BY so.OrderNumber ASC
;

