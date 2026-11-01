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
-- Profiling queries on the view dbo.vw_Sales_OrderSizeDistribution, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_OrderSizeDistribution.sql
--


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- how many rows; 9.
SELECT COUNT(*)
FROM dbo.vw_Sales_OrderSizeDistribution
;

-- the view groups by channel, customer type, order size and basket size; those four make a row on their own.
-- no rows: no two rows share them.
SELECT ChannelName, CustomerType, OrderSizeBucket, BasketSize, COUNT(*) AS rows
FROM dbo.vw_Sales_OrderSizeDistribution
GROUP BY ChannelName, CustomerType, OrderSizeBucket, BasketSize
HAVING COUNT(*) > 1
;

-- the channels, how many rows and how many orders each; Online/E-Commerce 3 and 4, Retail Store 4 and 6,
-- Wholesale 2 and 5.
SELECT ChannelName, COUNT(*) AS rows, SUM(OrderCount) AS orders
FROM dbo.vw_Sales_OrderSizeDistribution
GROUP BY ChannelName
ORDER BY ChannelName ASC
;


-- -------------------------------------------------------------------------------------------------------- CustomerType
-- the customer types and how many orders each; Online 3, Retail 7, Wholesale 5.
SELECT CustomerType, SUM(OrderCount) AS orders
FROM dbo.vw_Sales_OrderSizeDistribution
GROUP BY CustomerType
ORDER BY CustomerType ASC
;


-- ----------------------------------------------------------------------------------------------------- OrderSizeBucket
-- the order sizes and how many orders each; Enterprise 5, Large 5, Medium 5, and no Small order.
SELECT OrderSizeBucket, SUM(OrderCount) AS orders
FROM dbo.vw_Sales_OrderSizeDistribution
GROUP BY OrderSizeBucket
ORDER BY OrderSizeBucket ASC
;

-- an order size whose count is not that of the orders whose NetAmount falls in it.
-- no rows: every count agrees.
SELECT v.OrderSizeBucket, v.orders, o.orders
FROM (SELECT OrderSizeBucket, SUM(OrderCount) AS orders
      FROM dbo.vw_Sales_OrderSizeDistribution
      GROUP BY OrderSizeBucket) v
         JOIN (SELECT CASE
                          WHEN NetAmount < 500 THEN 'Small (< $500)'
                          WHEN NetAmount < 1500 THEN 'Medium ($500-$1,499)'
                          WHEN NetAmount < 5000 THEN 'Large ($1,500-$4,999)'
                          ELSE 'Enterprise (>= $5,000)'
                      END AS OrderSizeBucket,
                      COUNT(*) AS orders
               FROM dbo.SalesOrder
               GROUP BY CASE
                            WHEN NetAmount < 500 THEN 'Small (< $500)'
                            WHEN NetAmount < 1500 THEN 'Medium ($500-$1,499)'
                            WHEN NetAmount < 5000 THEN 'Large ($1,500-$4,999)'
                            ELSE 'Enterprise (>= $5,000)'
                        END) o ON o.OrderSizeBucket = v.OrderSizeBucket
WHERE v.orders <> o.orders
;


-- ---------------------------------------------------------------------------------------------------------- BasketSize
-- the basket sizes and how many orders each; Single Item 8, Small Basket 7. No order has more than 3 lines.
SELECT BasketSize, SUM(OrderCount) AS orders
FROM dbo.vw_Sales_OrderSizeDistribution
GROUP BY BasketSize
ORDER BY BasketSize ASC
;


-- ---------------------------------------------------------------------------------------------------------- OrderCount
-- the smallest and the largest OrderCount, and their sum against the orders; 1 and 4, and 15 of 15.
SELECT MIN(OrderCount), MAX(OrderCount), SUM(OrderCount), (SELECT COUNT(*) FROM dbo.SalesOrder) AS orders
FROM dbo.vw_Sales_OrderSizeDistribution
;


-- -------------------------------------------------------------------------------------------------------- TotalRevenue
-- TotalRevenue over the view against NetAmount over the orders; 53911.82 both.
SELECT SUM(TotalRevenue), (SELECT SUM(NetAmount) FROM dbo.SalesOrder) AS net
FROM dbo.vw_Sales_OrderSizeDistribution
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not TotalRevenue over OrderCount.
-- no rows: every average agrees, to the cent.
SELECT ChannelName, CustomerType, OrderSizeBucket, BasketSize, AvgOrderValue
FROM dbo.vw_Sales_OrderSizeDistribution
WHERE ABS(AvgOrderValue - TotalRevenue / OrderCount) >= 0.01
;


-- ---------------------------------------------------------------------------------------------------- AvgUnitsPerOrder
-- AvgUnitsPerOrder times OrderCount over the view against Quantity over the order lines; 150.000000 and 150.00.
SELECT SUM(AvgUnitsPerOrder * OrderCount), (SELECT SUM(Quantity) FROM dbo.SalesOrderDetail) AS units
FROM dbo.vw_Sales_OrderSizeDistribution
;


-- -------------------------------------------------------------------------------------------------------- AvgLineItems
-- AvgLineItems times OrderCount over the view against the order lines. The column averages an int, and so drops
-- the fraction.
-- 22 against 23: Wholesale's four Small Basket orders have 9 lines, 2.25 an order, shown as 2.
SELECT SUM(AvgLineItems * OrderCount), (SELECT COUNT(*) FROM dbo.SalesOrderDetail) AS lines
FROM dbo.vw_Sales_OrderSizeDistribution
;


-- ---------------------------------------------------------------------------------------------- PercentOfChannelOrders
-- a channel whose PercentOfChannelOrders does not add up to 100.
-- no rows: every channel adds up, to the rounding.
SELECT ChannelName, SUM(PercentOfChannelOrders) AS percents
FROM dbo.vw_Sales_OrderSizeDistribution
GROUP BY ChannelName
HAVING ABS(SUM(PercentOfChannelOrders) - 100) >= 0.05
;

