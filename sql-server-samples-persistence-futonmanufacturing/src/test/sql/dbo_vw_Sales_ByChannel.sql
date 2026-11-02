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
-- Profiling queries on the view dbo.vw_Sales_ByChannel, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_ByChannel.sql
--


-- --------------------------------------------------------------------------------------------------------- ChannelCode
-- how many rows, and how many channels; 6 rows, 3 channels, a row per channel and month.
SELECT COUNT(*), COUNT(DISTINCT ChannelCode)
FROM dbo.vw_Sales_ByChannel
;

-- the view groups by channel, year, month and quarter; a channel and a month make a row on their own.
-- no rows: no two rows share a ChannelCode, a Year and a Month.
SELECT ChannelCode, Year, Month, COUNT(*) AS rows
FROM dbo.vw_Sales_ByChannel
GROUP BY ChannelCode, Year, Month
HAVING COUNT(*) > 1
;

-- a channel with no row; the view joins SalesOrder with an INNER JOIN, so a channel without an order drops out.
-- no rows: every channel has sold.
SELECT sc.SalesChannelID, sc.ChannelCode
FROM dbo.SalesChannel sc
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.vw_Sales_ByChannel v
                  WHERE v.ChannelCode = sc.ChannelCode)
;


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- a ChannelCode with more than one ChannelName.
-- no rows: a code goes with a single name.
SELECT ChannelCode, COUNT(DISTINCT ChannelName) AS names
FROM dbo.vw_Sales_ByChannel
GROUP BY ChannelCode
HAVING COUNT(DISTINCT ChannelName) > 1
;

-- the channels and their names; ONLINE Online/E-Commerce, RETAIL Retail Store, WHOLESALE Wholesale.
SELECT DISTINCT ChannelCode, ChannelName
FROM dbo.vw_Sales_ByChannel
ORDER BY ChannelCode ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Year
-- the years the orders fall in; 2024 only, on all 6 rows.
SELECT Year, COUNT(*) AS rows
FROM dbo.vw_Sales_ByChannel
GROUP BY Year
ORDER BY Year ASC
;


-- --------------------------------------------------------------------------------------------------------------- Month
-- the months the orders fall in; 10 and 11, 3 rows each, a row per channel.
SELECT Month, COUNT(*) AS rows
FROM dbo.vw_Sales_ByChannel
GROUP BY Month
ORDER BY Month ASC
;


-- ------------------------------------------------------------------------------------------------------------- Quarter
-- a Quarter which does not follow from the Month.
-- no rows: every Quarter is (Month + 2) / 3, 4 on every row.
SELECT ChannelCode, Year, Month, Quarter
FROM dbo.vw_Sales_ByChannel
WHERE Quarter <> (Month + 2) / 3
;


-- ---------------------------------------------------------------------------------------------------------- OrderCount
-- the smallest and the largest OrderCount, and their sum against the orders; 2 and 3, and 15 of 15 orders.
SELECT MIN(OrderCount), MAX(OrderCount), SUM(OrderCount), (SELECT COUNT(*) FROM dbo.SalesOrder) AS orders
FROM dbo.vw_Sales_ByChannel
;


-- ----------------------------------------------------------------------------------------------------- UniqueCustomers
-- the smallest and the largest UniqueCustomers; 1 and 3.
SELECT MIN(UniqueCustomers), MAX(UniqueCustomers)
FROM dbo.vw_Sales_ByChannel
;

-- a row with more customers than orders.
-- no rows: a customer places at least one order.
SELECT ChannelCode, Year, Month, OrderCount, UniqueCustomers
FROM dbo.vw_Sales_ByChannel
WHERE UniqueCustomers > OrderCount
;


-- ---------------------------------------------------------------------------------------------------------- GrossSales
-- GrossSales over the view against Subtotal over the orders; 60097.74 both.
SELECT SUM(GrossSales), (SELECT SUM(Subtotal) FROM dbo.SalesOrder) AS subtotal
FROM dbo.vw_Sales_ByChannel
;


-- ------------------------------------------------------------------------------------------------------ TotalDiscounts
-- TotalDiscounts over the view against DiscountAmount over the orders; 5114.96 both.
SELECT SUM(TotalDiscounts), (SELECT SUM(DiscountAmount) FROM dbo.SalesOrder) AS discounts
FROM dbo.vw_Sales_ByChannel
;

-- a row whose discounts exceed its GrossSales.
-- no rows: no discount is larger than what it discounts.
SELECT ChannelCode, Year, Month, GrossSales, TotalDiscounts
FROM dbo.vw_Sales_ByChannel
WHERE TotalDiscounts > GrossSales
;


-- ------------------------------------------------------------------------------------------------------------ NetSales
-- NetSales over the view against NetAmount over the orders; 53911.82 both.
SELECT SUM(NetSales), (SELECT SUM(NetAmount) FROM dbo.SalesOrder) AS net
FROM dbo.vw_Sales_ByChannel
;

-- a row whose NetSales is not DeliveredSales and PipelineSales together; the orders are Delivered, Confirmed,
-- InProduction or Shipped, so the two cover every order.
-- no rows: NetSales splits into the two.
SELECT ChannelCode, Year, Month, NetSales, DeliveredSales, PipelineSales
FROM dbo.vw_Sales_ByChannel
WHERE NetSales <> DeliveredSales + PipelineSales
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not NetSales over OrderCount; the view groups an order into a single row, so the
-- average of NetAmount is the plain quotient.
-- no rows: every average agrees, to the cent.
SELECT ChannelCode, Year, Month, AvgOrderValue, NetSales, OrderCount
FROM dbo.vw_Sales_ByChannel
WHERE ABS(AvgOrderValue - NetSales / OrderCount) >= 0.01
;


-- ------------------------------------------------------------------------------------------------------ DeliveredSales
-- DeliveredSales over the view against NetAmount over the Delivered orders; 28293.01 both.
SELECT SUM(DeliveredSales), (SELECT SUM(NetAmount) FROM dbo.SalesOrder WHERE Status = 'Delivered') AS delivered
FROM dbo.vw_Sales_ByChannel
;


-- ------------------------------------------------------------------------------------------------------- PipelineSales
-- PipelineSales over the view against NetAmount over the Confirmed, InProduction and Shipped orders; 25618.81 both.
SELECT SUM(PipelineSales),
       (SELECT SUM(NetAmount)
        FROM dbo.SalesOrder
        WHERE Status IN ('Confirmed', 'InProduction', 'Shipped')) AS pipeline
FROM dbo.vw_Sales_ByChannel
;

-- the rows with nothing in the pipeline; the 3 of October, every October order delivered.
SELECT ChannelCode, Year, Month, DeliveredSales
FROM dbo.vw_Sales_ByChannel
WHERE PipelineSales = 0
ORDER BY ChannelCode ASC, Year ASC, Month ASC
;


-- -------------------------------------------------------------------------------------------------- AvgDiscountPercent
-- the smallest and the largest AvgDiscountPercent; 2.50 and 10.00, the latter WHOLESALE in both months.
SELECT MIN(AvgDiscountPercent), MAX(AvgDiscountPercent)
FROM dbo.vw_Sales_ByChannel
;

-- the rows at 10 percent or more.
-- WHOLESALE in October and November, at 10.00 each.
SELECT ChannelCode, Year, Month, AvgDiscountPercent
FROM dbo.vw_Sales_ByChannel
WHERE AvgDiscountPercent >= 10
ORDER BY ChannelCode ASC, Year ASC, Month ASC
;

