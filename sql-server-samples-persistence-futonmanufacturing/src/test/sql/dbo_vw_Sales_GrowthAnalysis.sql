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
-- Profiling queries on the view dbo.vw_Sales_GrowthAnalysis, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_GrowthAnalysis.sql
--


-- ---------------------------------------------------------------------------------------------------------------- Year
-- how many rows, and the years the orders fall in; 6 rows, 2024 only.
SELECT COUNT(*), MIN(Year), MAX(Year)
FROM dbo.vw_Sales_GrowthAnalysis
;

-- the view groups by year, month and channel; those three make a row on their own.
-- no rows: no two rows share them.
SELECT Year, Month, ChannelName, COUNT(*) AS rows
FROM dbo.vw_Sales_GrowthAnalysis
GROUP BY Year, Month, ChannelName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------------- Month
-- the months and how many rows each; 10 and 11, 3 each, a row per channel.
SELECT Month, COUNT(*) AS rows
FROM dbo.vw_Sales_GrowthAnalysis
GROUP BY Month
ORDER BY Month ASC
;


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- the channels and how many months each; Online/E-Commerce, Retail Store and Wholesale, 2 months each.
SELECT ChannelName, COUNT(*) AS months
FROM dbo.vw_Sales_GrowthAnalysis
GROUP BY ChannelName
ORDER BY ChannelName ASC
;


-- -------------------------------------------------------------------------------------------------------------- Orders
-- the smallest and the largest Orders, and their sum against the orders; 2 and 3, and 15 of 15.
SELECT MIN(Orders), MAX(Orders), SUM(Orders), (SELECT COUNT(*) FROM dbo.SalesOrder) AS orders
FROM dbo.vw_Sales_GrowthAnalysis
;


-- ----------------------------------------------------------------------------------------------------------- Customers
-- a row with more customers than orders.
-- no rows: a customer places at least one order; 1 to 3 customers a row.
SELECT Year, Month, ChannelName, Orders, Customers
FROM dbo.vw_Sales_GrowthAnalysis
WHERE Customers > Orders
;


-- ------------------------------------------------------------------------------------------------------------- Revenue
-- Revenue over the view against NetAmount over the orders; 53911.82 both.
SELECT SUM(Revenue), (SELECT SUM(NetAmount) FROM dbo.SalesOrder) AS net
FROM dbo.vw_Sales_GrowthAnalysis
;


-- --------------------------------------------------------------------------------------------------- PriorMonthRevenue
-- the rows with no PriorMonthRevenue; the first month of each channel, October 2024, 3 rows.
SELECT Year, Month, ChannelName
FROM dbo.vw_Sales_GrowthAnalysis
WHERE PriorMonthRevenue IS NULL
ORDER BY ChannelName ASC
;

-- a PriorMonthRevenue which is not the Revenue of the channel's month before.
-- no rows: every November row carries its October.
SELECT v.Year, v.Month, v.ChannelName, v.PriorMonthRevenue, p.Revenue
FROM dbo.vw_Sales_GrowthAnalysis v
         JOIN dbo.vw_Sales_GrowthAnalysis p
              ON p.ChannelName = v.ChannelName
                  AND p.Year * 12 + p.Month = v.Year * 12 + v.Month - 1
WHERE v.PriorMonthRevenue <> p.Revenue
;


-- ------------------------------------------------------------------------------------------------------- RevenueGrowth
-- a RevenueGrowth which is not Revenue less PriorMonthRevenue.
-- no rows: the formula holds; Online/E-Commerce grew by 1949.97, Retail Store and Wholesale shrank.
SELECT Year, Month, ChannelName, Revenue, PriorMonthRevenue, RevenueGrowth
FROM dbo.vw_Sales_GrowthAnalysis
WHERE RevenueGrowth <> Revenue - PriorMonthRevenue
;


-- ------------------------------------------------------------------------------------------------------- GrowthPercent
-- a GrowthPercent which is not RevenueGrowth over PriorMonthRevenue.
-- no rows: the formula holds; 131.58, -13.50 and -9.90.
SELECT Year, Month, ChannelName, RevenueGrowth, PriorMonthRevenue, GrowthPercent
FROM dbo.vw_Sales_GrowthAnalysis
WHERE ABS(GrowthPercent - RevenueGrowth * 100.0 / PriorMonthRevenue) >= 0.01
;


-- ------------------------------------------------------------------------------------------------- PriorMonthCustomers
-- a PriorMonthCustomers which is not the Customers of the channel's month before.
-- no rows: every November row carries its October; the October rows have NULL.
SELECT v.Year, v.Month, v.ChannelName, v.PriorMonthCustomers, p.Customers
FROM dbo.vw_Sales_GrowthAnalysis v
         JOIN dbo.vw_Sales_GrowthAnalysis p
              ON p.ChannelName = v.ChannelName
                  AND p.Year * 12 + p.Month = v.Year * 12 + v.Month - 1
WHERE v.PriorMonthCustomers <> p.Customers
;


-- ------------------------------------------------------------------------------------------------------ CustomerGrowth
-- a CustomerGrowth which is not Customers less PriorMonthCustomers.
-- no rows: the formula holds; 1, 0 and -1.
SELECT Year, Month, ChannelName, Customers, PriorMonthCustomers, CustomerGrowth
FROM dbo.vw_Sales_GrowthAnalysis
WHERE CustomerGrowth <> Customers - PriorMonthCustomers
;


-- --------------------------------------------------------------------------------------------------- CumulativeRevenue
-- a CumulativeRevenue which is not the channel's Revenue up to and including the month.
-- no rows: the running total holds.
SELECT v.Year, v.Month, v.ChannelName, v.CumulativeRevenue
FROM dbo.vw_Sales_GrowthAnalysis v
WHERE v.CumulativeRevenue <> (SELECT SUM(p.Revenue)
                              FROM dbo.vw_Sales_GrowthAnalysis p
                              WHERE p.ChannelName = v.ChannelName
                                AND p.Year * 12 + p.Month <= v.Year * 12 + v.Month)
;


-- ------------------------------------------------------------------------------------------------------- ThreeMonthAvg
-- a ThreeMonthAvg which is not the average Revenue of the month and the two before; with two months on hand, the
-- average takes what there is.
-- no rows: every average agrees, to the cent.
SELECT v.Year, v.Month, v.ChannelName, v.ThreeMonthAvg
FROM dbo.vw_Sales_GrowthAnalysis v
WHERE ABS(v.ThreeMonthAvg - (SELECT AVG(p.Revenue)
                             FROM dbo.vw_Sales_GrowthAnalysis p
                             WHERE p.ChannelName = v.ChannelName
                               AND p.Year * 12 + p.Month BETWEEN v.Year * 12 + v.Month - 2
                                                             AND v.Year * 12 + v.Month)) >= 0.01
;

