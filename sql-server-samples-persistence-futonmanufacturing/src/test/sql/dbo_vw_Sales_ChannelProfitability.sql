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
-- Profiling queries on the view dbo.vw_Sales_ChannelProfitability, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_ChannelProfitability.sql
--


-- --------------------------------------------------------------------------------------------------------- ChannelCode
-- how many rows, and how many channels; 3 and 3, a row per channel.
SELECT COUNT(*), COUNT(DISTINCT ChannelCode)
FROM dbo.vw_Sales_ChannelProfitability
;

-- a channel with no row; the view starts from SalesChannel with LEFT JOINs.
-- no rows: every channel has its row.
SELECT sc.SalesChannelID, sc.ChannelCode
FROM dbo.SalesChannel sc
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.vw_Sales_ChannelProfitability v
                  WHERE v.ChannelCode = sc.ChannelCode)
;


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- the channels and their names; ONLINE Online/E-Commerce, RETAIL Retail Store, WHOLESALE Wholesale.
SELECT ChannelCode, ChannelName
FROM dbo.vw_Sales_ChannelProfitability
ORDER BY ChannelCode ASC
;


-- ---------------------------------------------------------------------------------------------------------- OrderCount
-- a channel whose OrderCount is not the count of its orders.
-- no rows: ONLINE 4, RETAIL 6, WHOLESALE 5; 15 in all.
SELECT v.ChannelCode, v.OrderCount
FROM dbo.vw_Sales_ChannelProfitability v
         JOIN dbo.SalesChannel sc ON sc.ChannelCode = v.ChannelCode
WHERE v.OrderCount <> (SELECT COUNT(*)
                       FROM dbo.SalesOrder so
                       WHERE so.SalesChannelID = sc.SalesChannelID)
;


-- ---------------------------------------------------------------------------------------------------------- TotalUnits
-- TotalUnits over the view against Quantity over the order lines; 150.00 both.
SELECT SUM(TotalUnits), (SELECT SUM(Quantity) FROM dbo.SalesOrderDetail) AS units
FROM dbo.vw_Sales_ChannelProfitability
;


-- ---------------------------------------------------------------------------------------------------------- GrossSales
-- a channel whose GrossSales is not the Subtotal of its orders. The view sums the order's Subtotal on each of its
-- lines, so an order of several lines counts several times.
-- RETAIL 14899.77 against 9699.85, WHOLESALE 95195.76 against 45597.96. ONLINE, a line an order, agrees.
SELECT v.ChannelCode, v.GrossSales, o.subtotal
FROM dbo.vw_Sales_ChannelProfitability v
         JOIN dbo.SalesChannel sc ON sc.ChannelCode = v.ChannelCode
         JOIN (SELECT SalesChannelID, SUM(Subtotal) AS subtotal
               FROM dbo.SalesOrder
               GROUP BY SalesChannelID) o ON o.SalesChannelID = sc.SalesChannelID
WHERE v.GrossSales <> o.subtotal
ORDER BY v.ChannelCode ASC
;


-- ----------------------------------------------------------------------------------------------------------- Discounts
-- a channel whose Discounts is not the DiscountAmount of its orders; the same repetition.
-- RETAIL 525.00 against 345.00, WHOLESALE 9519.88 against 4559.96.
SELECT v.ChannelCode, v.Discounts, o.discounts
FROM dbo.vw_Sales_ChannelProfitability v
         JOIN dbo.SalesChannel sc ON sc.ChannelCode = v.ChannelCode
         JOIN (SELECT SalesChannelID, SUM(DiscountAmount) AS discounts
               FROM dbo.SalesOrder
               GROUP BY SalesChannelID) o ON o.SalesChannelID = sc.SalesChannelID
WHERE v.Discounts <> o.discounts
ORDER BY v.ChannelCode ASC
;


-- ------------------------------------------------------------------------------------------------------------ NetSales
-- a channel whose NetSales is not the NetAmount of its orders; the same repetition.
-- RETAIL 15699.77 against 10219.85, WHOLESALE 80956.00 against 38778.04.
SELECT v.ChannelCode, v.NetSales, o.net
FROM dbo.vw_Sales_ChannelProfitability v
         JOIN dbo.SalesChannel sc ON sc.ChannelCode = v.ChannelCode
         JOIN (SELECT SalesChannelID, SUM(NetAmount) AS net
               FROM dbo.SalesOrder
               GROUP BY SalesChannelID) o ON o.SalesChannelID = sc.SalesChannelID
WHERE v.NetSales <> o.net
ORDER BY v.ChannelCode ASC
;


-- ----------------------------------------------------------------------------------------------------- ShippingRevenue
-- a channel whose ShippingRevenue is not the ShippingAmount of its orders; the same repetition.
-- RETAIL 660.00 against 435.00, WHOLESALE 4800.00 against 2300.00.
SELECT v.ChannelCode, v.ShippingRevenue, o.shipping
FROM dbo.vw_Sales_ChannelProfitability v
         JOIN dbo.SalesChannel sc ON sc.ChannelCode = v.ChannelCode
         JOIN (SELECT SalesChannelID, SUM(ShippingAmount) AS shipping
               FROM dbo.SalesOrder
               GROUP BY SalesChannelID) o ON o.SalesChannelID = sc.SalesChannelID
WHERE v.ShippingRevenue <> o.shipping
ORDER BY v.ChannelCode ASC
;


-- ---------------------------------------------------------------------------------------------------------------- COGS
-- the smallest and the largest COGS; .000000 both: every item sold has a StandardCost of 0.
SELECT MIN(COGS), MAX(COGS)
FROM dbo.vw_Sales_ChannelProfitability
;

-- the items sold with a StandardCost other than 0.
-- no rows: the 6 items on the order lines all cost 0.
SELECT DISTINCT i.ItemID, i.ItemCode, i.StandardCost
FROM dbo.SalesOrderDetail sod
         JOIN dbo.Items i ON i.ItemID = sod.ItemID
WHERE i.StandardCost <> 0
;


-- --------------------------------------------------------------------------------------------------------- GrossProfit
-- a GrossProfit which is not NetSales less COGS.
-- no rows: with no cost, GrossProfit is NetSales on every row.
SELECT ChannelCode, NetSales, COGS, GrossProfit
FROM dbo.vw_Sales_ChannelProfitability
WHERE GrossProfit <> NetSales - COGS
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not the average NetAmount of the channel's orders. The view averages over the lines,
-- so an order weighs as many times as it has lines.
-- RETAIL 1744.418888 against 1703.308333, WHOLESALE 8095.600000 against 7755.608000. ONLINE agrees.
SELECT v.ChannelCode, v.AvgOrderValue, o.average
FROM dbo.vw_Sales_ChannelProfitability v
         JOIN dbo.SalesChannel sc ON sc.ChannelCode = v.ChannelCode
         JOIN (SELECT SalesChannelID, AVG(NetAmount) AS average
               FROM dbo.SalesOrder
               GROUP BY SalesChannelID) o ON o.SalesChannelID = sc.SalesChannelID
WHERE ABS(v.AvgOrderValue - o.average) >= 0.01
ORDER BY v.ChannelCode ASC
;


-- -------------------------------------------------------------------------------------------------- GrossMarginPercent
-- the smallest and the largest GrossMarginPercent; 100.00 both, the cost being 0.
SELECT MIN(GrossMarginPercent), MAX(GrossMarginPercent)
FROM dbo.vw_Sales_ChannelProfitability
;


-- ----------------------------------------------------------------------------------------------------- DiscountPercent
-- a DiscountPercent which is not Discounts over GrossSales.
-- no rows: ONLINE 4.38, RETAIL 3.52, WHOLESALE 10.00.
SELECT ChannelCode, Discounts, GrossSales, DiscountPercent
FROM dbo.vw_Sales_ChannelProfitability
WHERE ABS(DiscountPercent - Discounts * 100.0 / GrossSales) >= 0.01
;


-- ------------------------------------------------------------------------------------------------------ ProfitPerOrder
-- a ProfitPerOrder which is not GrossProfit over OrderCount.
-- no rows: the formula holds, on the repeated GrossProfit.
SELECT ChannelCode, GrossProfit, OrderCount, ProfitPerOrder
FROM dbo.vw_Sales_ChannelProfitability
WHERE ABS(ProfitPerOrder - GrossProfit / OrderCount) >= 0.01
;


-- ------------------------------------------------------------------------------------------------------- ProfitPerUnit
-- a ProfitPerUnit which is not GrossProfit over TotalUnits.
-- no rows: the formula holds, on the repeated GrossProfit.
SELECT ChannelCode, GrossProfit, TotalUnits, ProfitPerUnit
FROM dbo.vw_Sales_ChannelProfitability
WHERE ABS(ProfitPerUnit - GrossProfit / TotalUnits) >= 0.01
;


-- --------------------------------------------------------------------------------------------------- ProfitabilityRank
-- the channels by ProfitabilityRank; WHOLESALE 1, RETAIL 2, ONLINE 3, no tie.
SELECT ProfitabilityRank, ChannelCode, GrossProfit
FROM dbo.vw_Sales_ChannelProfitability
ORDER BY ProfitabilityRank ASC
;

