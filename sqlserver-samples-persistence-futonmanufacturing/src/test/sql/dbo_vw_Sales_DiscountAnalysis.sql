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
-- Profiling queries on the view dbo.vw_Sales_DiscountAnalysis, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_DiscountAnalysis.sql
--


-- ---------------------------------------------------------------------------------------------------------------- Year
-- how many rows, and the years the orders fall in; 7 rows, 2024 only.
SELECT COUNT(*), MIN(Year), MAX(Year)
FROM dbo.vw_Sales_DiscountAnalysis
;

-- the view groups by year, month, channel and customer type; those four make a row on their own.
-- no rows: no two rows share them.
SELECT Year, Month, ChannelName, CustomerType, COUNT(*) AS rows
FROM dbo.vw_Sales_DiscountAnalysis
GROUP BY Year, Month, ChannelName, CustomerType
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------------- Month
-- the months and how many rows each; 10 with 3, 11 with 4.
SELECT Month, COUNT(*) AS rows
FROM dbo.vw_Sales_DiscountAnalysis
GROUP BY Month
ORDER BY Month ASC
;


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- the channels and how many rows each; Online/E-Commerce 3, Retail Store 2, Wholesale 2.
SELECT ChannelName, COUNT(*) AS rows
FROM dbo.vw_Sales_DiscountAnalysis
GROUP BY ChannelName
ORDER BY ChannelName ASC
;


-- -------------------------------------------------------------------------------------------------------- CustomerType
-- the channels each customer type buys through; Online and Retail customers on Online/E-Commerce, Retail customers
-- in the Retail Store, Wholesale customers on Wholesale. A Retail customer is the only one to cross over.
SELECT DISTINCT CustomerType, ChannelName
FROM dbo.vw_Sales_DiscountAnalysis
ORDER BY CustomerType ASC, ChannelName ASC
;


-- ---------------------------------------------------------------------------------------------------------- OrderCount
-- OrderCount over the view against the orders; 15 both.
SELECT SUM(OrderCount), (SELECT COUNT(*) FROM dbo.SalesOrder) AS orders
FROM dbo.vw_Sales_DiscountAnalysis
;


-- ---------------------------------------------------------------------------------------------------------- GrossSales
-- GrossSales over the view against Subtotal over the orders. The view joins the order lines and sums the order's
-- Subtotal on each of them, so an order of several lines counts several times.
-- 114895.46 against 60097.74.
SELECT SUM(GrossSales), (SELECT SUM(Subtotal) FROM dbo.SalesOrder) AS subtotal
FROM dbo.vw_Sales_DiscountAnalysis
;


-- ------------------------------------------------------------------------------------------------------ TotalDiscounts
-- TotalDiscounts over the view against DiscountAmount over the orders; the same repetition.
-- 10254.88 against 5114.96.
SELECT SUM(TotalDiscounts), (SELECT SUM(DiscountAmount) FROM dbo.SalesOrder) AS discounts
FROM dbo.vw_Sales_DiscountAnalysis
;


-- ------------------------------------------------------------------------------------------------------------ NetSales
-- NetSales over the view against NetAmount over the orders; the same repetition.
-- 101569.70 against 53911.82.
SELECT SUM(NetSales), (SELECT SUM(NetAmount) FROM dbo.SalesOrder) AS net
FROM dbo.vw_Sales_DiscountAnalysis
;


-- ----------------------------------------------------------------------------------------------------- DiscountPercent
-- a DiscountPercent which is not TotalDiscounts over GrossSales.
-- no rows: the formula holds, 2.86 to 10.00.
SELECT Year, Month, ChannelName, CustomerType, DiscountPercent
FROM dbo.vw_Sales_DiscountAnalysis
WHERE ABS(DiscountPercent - TotalDiscounts * 100.0 / GrossSales) >= 0.01
;


-- ------------------------------------------------------------------------------------------------- AvgDiscountPerOrder
-- the smallest and the largest AvgDiscountPerOrder; 20.000000 and 1080.000000, an average over the lines, not the
-- orders.
SELECT MIN(AvgDiscountPerOrder), MAX(AvgDiscountPerOrder)
FROM dbo.vw_Sales_DiscountAnalysis
;


-- ---------------------------------------------------------------------------------------------------- NoDiscountOrders
-- NoDiscountOrders over the view against the orders with no discount.
-- 4 against 3: Retail Store in October counts an order of two lines twice.
SELECT SUM(NoDiscountOrders), (SELECT COUNT(*) FROM dbo.SalesOrder WHERE DiscountAmount = 0) AS orders
FROM dbo.vw_Sales_DiscountAnalysis
;


-- --------------------------------------------------------------------------------------------------- LowDiscountOrders
-- LowDiscountOrders over the view against the orders with a discount above 0 and up to 100.
-- 6 against 5: Retail Store in October counts an order of two lines twice.
SELECT SUM(LowDiscountOrders),
       (SELECT COUNT(*) FROM dbo.SalesOrder WHERE DiscountAmount > 0 AND DiscountAmount <= 100) AS orders
FROM dbo.vw_Sales_DiscountAnalysis
;


-- ------------------------------------------------------------------------------------------------ MediumDiscountOrders
-- MediumDiscountOrders over the view against the orders with a discount above 100 and up to 500.
-- 3 against 2: the same, for another Retail Store order of October.
SELECT SUM(MediumDiscountOrders),
       (SELECT COUNT(*) FROM dbo.SalesOrder WHERE DiscountAmount > 100 AND DiscountAmount <= 500) AS orders
FROM dbo.vw_Sales_DiscountAnalysis
;


-- -------------------------------------------------------------------------------------------------- HighDiscountOrders
-- HighDiscountOrders over the view against the orders with a discount above 500.
-- 10 against 5: the five Wholesale orders have 10 lines between them.
SELECT SUM(HighDiscountOrders), (SELECT COUNT(*) FROM dbo.SalesOrder WHERE DiscountAmount > 500) AS orders
FROM dbo.vw_Sales_DiscountAnalysis
;

-- a row whose four discount levels do not add up to its OrderCount; the levels count lines.
-- Retail Store in October 6 against 3, Wholesale in October 6 against 3 and in November 4 against 2.
SELECT Year, Month, ChannelName, CustomerType, OrderCount,
       NoDiscountOrders + LowDiscountOrders + MediumDiscountOrders + HighDiscountOrders AS levels
FROM dbo.vw_Sales_DiscountAnalysis
WHERE OrderCount <> NoDiscountOrders + LowDiscountOrders + MediumDiscountOrders + HighDiscountOrders
ORDER BY Year ASC, Month ASC, ChannelName ASC
;


-- --------------------------------------------------------------------------------------------------------- GrossProfit
-- a GrossProfit other than NetSales; the cost is Quantity times StandardCost, and every item sold costs 0.
-- no rows: GrossProfit is NetSales on every row.
SELECT Year, Month, ChannelName, CustomerType, NetSales, GrossProfit
FROM dbo.vw_Sales_DiscountAnalysis
WHERE GrossProfit <> NetSales
;


-- -------------------------------------------------------------------------------------------------- GrossMarginPercent
-- the smallest and the largest GrossMarginPercent; 100.00 both, the cost being 0.
SELECT MIN(GrossMarginPercent), MAX(GrossMarginPercent)
FROM dbo.vw_Sales_DiscountAnalysis
;

