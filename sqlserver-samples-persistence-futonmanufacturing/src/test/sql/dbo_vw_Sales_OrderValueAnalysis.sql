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
-- Profiling queries on the view dbo.vw_Sales_OrderValueAnalysis, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_OrderValueAnalysis.sql
--


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- how many rows; 6.
SELECT COUNT(*)
FROM dbo.vw_Sales_OrderValueAnalysis
;

-- the view groups by channel, customer type, territory and region; those four make a row on their own.
-- no rows: no two rows share them.
SELECT ChannelName, CustomerType, TerritoryName, Region, COUNT(*) AS rows
FROM dbo.vw_Sales_OrderValueAnalysis
GROUP BY ChannelName, CustomerType, TerritoryName, Region
HAVING COUNT(*) > 1
;

-- the channels, how many rows and how many orders each; Online/E-Commerce 2 and 4, Retail Store 3 and 6,
-- Wholesale 1 and 5.
SELECT ChannelName, COUNT(*) AS rows, SUM(OrderCount) AS orders
FROM dbo.vw_Sales_OrderValueAnalysis
GROUP BY ChannelName
ORDER BY ChannelName ASC
;


-- -------------------------------------------------------------------------------------------------------- CustomerType
-- the customer types and how many orders each; Online 3, Retail 7, Wholesale 5, none NULL.
SELECT CustomerType, SUM(OrderCount) AS orders
FROM dbo.vw_Sales_OrderValueAnalysis
GROUP BY CustomerType
ORDER BY CustomerType ASC
;


-- ------------------------------------------------------------------------------------------------------- TerritoryName
-- the territories and how many orders each; the view takes the territory of the order's rep, not the customer's.
-- Northern California 2, Pacific Northwest 12, Southern California 1, none NULL.
SELECT TerritoryName, SUM(OrderCount) AS orders
FROM dbo.vw_Sales_OrderValueAnalysis
GROUP BY TerritoryName
ORDER BY TerritoryName ASC
;

-- the orders whose rep is not the customer's, and so may land in another territory than the customer's.
-- 2, both of CUST-007, a customer of rep 2 in Northern California: order 3 taken by rep 3 of Southern California,
-- order 14 by rep 8 of Pacific Northwest.
SELECT so.SalesOrderID, so.SalesRepID, sr.TerritoryID, c.CustomerCode, c.SalesRepID AS customer_rep,
       c.TerritoryID AS customer_territory
FROM dbo.SalesOrder so
         JOIN dbo.Customer c ON c.CustomerID = so.CustomerID
         LEFT OUTER JOIN dbo.SalesRep sr ON sr.SalesRepID = so.SalesRepID
WHERE so.SalesRepID <> c.SalesRepID
ORDER BY so.SalesOrderID ASC
;


-- -------------------------------------------------------------------------------------------------------------- Region
-- the regions and how many orders each; West 15, every order.
SELECT Region, SUM(OrderCount) AS orders
FROM dbo.vw_Sales_OrderValueAnalysis
GROUP BY Region
ORDER BY Region ASC
;


-- ---------------------------------------------------------------------------------------------------------- OrderCount
-- the smallest and the largest OrderCount, and their sum against the orders; 1 and 5, and 15 of 15.
SELECT MIN(OrderCount), MAX(OrderCount), SUM(OrderCount), (SELECT COUNT(*) FROM dbo.SalesOrder) AS orders
FROM dbo.vw_Sales_OrderValueAnalysis
;


-- --------------------------------------------------------------------------------------------------------- AvgSubtotal
-- AvgSubtotal times OrderCount over the view against Subtotal over the orders; 60097.74 both, to the cent.
SELECT CAST(SUM(AvgSubtotal * OrderCount) AS DECIMAL(19, 2)), (SELECT SUM(Subtotal) FROM dbo.SalesOrder) AS subtotal
FROM dbo.vw_Sales_OrderValueAnalysis
;


-- --------------------------------------------------------------------------------------------------------- AvgDiscount
-- AvgDiscount times OrderCount over the view against DiscountAmount over the orders; 5114.96 both.
SELECT CAST(SUM(AvgDiscount * OrderCount) AS DECIMAL(19, 2)),
       (SELECT SUM(DiscountAmount) FROM dbo.SalesOrder) AS discounts
FROM dbo.vw_Sales_OrderValueAnalysis
;


-- -------------------------------------------------------------------------------------------------------- AvgNetAmount
-- AvgNetAmount times OrderCount over the view against NetAmount over the orders; 53911.82 both.
SELECT CAST(SUM(AvgNetAmount * OrderCount) AS DECIMAL(19, 2)), (SELECT SUM(NetAmount) FROM dbo.SalesOrder) AS net
FROM dbo.vw_Sales_OrderValueAnalysis
;


-- -------------------------------------------------------------------------------------------------------------- AvgTax
-- the rows with no tax; Wholesale, whose 5 orders carry an AvgTax of 0.
SELECT ChannelName, CustomerType, TerritoryName, OrderCount, AvgTax
FROM dbo.vw_Sales_OrderValueAnalysis
WHERE AvgTax = 0
;


-- --------------------------------------------------------------------------------------------------------- AvgShipping
-- the smallest and the largest AvgShipping; 33.333333 and 460.000000, the latter Wholesale.
SELECT MIN(AvgShipping), MAX(AvgShipping)
FROM dbo.vw_Sales_OrderValueAnalysis
;


-- ------------------------------------------------------------------------------------------------------- MinOrderValue
-- the smallest MinOrderValue against the smallest NetAmount; 672.99 both.
SELECT MIN(MinOrderValue), (SELECT MIN(NetAmount) FROM dbo.SalesOrder) AS smallest
FROM dbo.vw_Sales_OrderValueAnalysis
;

-- a row whose MinOrderValue is above its AvgNetAmount, or its MaxOrderValue below.
-- no rows: the average lies between the two.
SELECT ChannelName, CustomerType, TerritoryName, MinOrderValue, AvgNetAmount, MaxOrderValue
FROM dbo.vw_Sales_OrderValueAnalysis
WHERE MinOrderValue > AvgNetAmount
   OR MaxOrderValue < AvgNetAmount
;


-- ------------------------------------------------------------------------------------------------------- MaxOrderValue
-- the largest MaxOrderValue against the largest NetAmount; 10199.40 both.
SELECT MAX(MaxOrderValue), (SELECT MAX(NetAmount) FROM dbo.SalesOrder) AS largest
FROM dbo.vw_Sales_OrderValueAnalysis
;


-- ---------------------------------------------------------------------------------------------------- StdDevOrderValue
-- a row whose StdDevOrderValue is NULL other than for a single order; STDEV needs two values.
-- no rows: the 2 NULLs are the rows of one order, and only those.
SELECT ChannelName, CustomerType, TerritoryName, OrderCount, StdDevOrderValue
FROM dbo.vw_Sales_OrderValueAnalysis
WHERE (StdDevOrderValue IS NULL AND OrderCount <> 1)
   OR (StdDevOrderValue IS NOT NULL AND OrderCount = 1)
;


-- --------------------------------------------------------------------------------------------------------- SmallOrders
-- the orders under 500; 0, the smallest order being 672.99.
SELECT SUM(SmallOrders)
FROM dbo.vw_Sales_OrderValueAnalysis
;


-- -------------------------------------------------------------------------------------------------------- MediumOrders
-- the orders from 500 to 1999; 8.
SELECT SUM(MediumOrders)
FROM dbo.vw_Sales_OrderValueAnalysis
;


-- --------------------------------------------------------------------------------------------------------- LargeOrders
-- the orders from 2000 to 4999; 2.
SELECT SUM(LargeOrders)
FROM dbo.vw_Sales_OrderValueAnalysis
;


-- ---------------------------------------------------------------------------------------------------- EnterpriseOrders
-- the orders of 5000 or more; 5, the Wholesale orders.
SELECT SUM(EnterpriseOrders)
FROM dbo.vw_Sales_OrderValueAnalysis
;

-- a row whose four order sizes do not add up to its OrderCount. The sizes use BETWEEN on whole numbers, so an
-- amount such as 1999.50 or 4999.50 falls in none.
-- no rows: no order lands in those gaps.
SELECT ChannelName, CustomerType, TerritoryName, OrderCount,
       SmallOrders + MediumOrders + LargeOrders + EnterpriseOrders AS sizes
FROM dbo.vw_Sales_OrderValueAnalysis
WHERE OrderCount <> SmallOrders + MediumOrders + LargeOrders + EnterpriseOrders
;

