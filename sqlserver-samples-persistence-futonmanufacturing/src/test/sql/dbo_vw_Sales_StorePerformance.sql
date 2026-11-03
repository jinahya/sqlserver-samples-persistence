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
-- Profiling queries on the view dbo.vw_Sales_StorePerformance, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_StorePerformance.sql
--


-- ----------------------------------------------------------------------------------------------------------- StoreCode
-- how many rows, and how many distinct StoreCode, against the stores; 7, 7 and 7, a row per store.
SELECT COUNT(*), COUNT(DISTINCT StoreCode), (SELECT COUNT(*) FROM dbo.Store)
FROM dbo.vw_Sales_StorePerformance
;


-- ----------------------------------------------------------------------------------------------------------- StoreName
-- the shortest and the longest StoreName; 17 and 23 characters, of the 100 the column allows.
SELECT MIN(LEN(StoreName)), MAX(LEN(StoreName))
FROM dbo.vw_Sales_StorePerformance
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- the stores of each city; Portland 4, Los Angeles, San Francisco and Seattle 1 each.
SELECT City, COUNT(*) AS stores
FROM dbo.vw_Sales_StorePerformance
GROUP BY City
ORDER BY stores DESC, City ASC
;


-- --------------------------------------------------------------------------------------------------------------- State
-- the stores of each state; OR 4, CA 2, WA 1.
SELECT State, COUNT(*) AS stores
FROM dbo.vw_Sales_StorePerformance
GROUP BY State
ORDER BY stores DESC, State ASC
;


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- the stores of each channel; Retail Store 5, Online/E-Commerce 1 and Wholesale 1.
SELECT ChannelName, COUNT(*) AS stores
FROM dbo.vw_Sales_StorePerformance
GROUP BY ChannelName
ORDER BY stores DESC, ChannelName ASC
;

-- an order of a store whose channel is not the store's; the view takes the channel from the store.
-- no rows: every order goes through its store's channel.
SELECT so.OrderNumber, s.StoreCode
FROM dbo.SalesOrder so
         JOIN dbo.Store s ON s.StoreID = so.StoreID
WHERE so.SalesChannelID <> s.SalesChannelID
;


-- ------------------------------------------------------------------------------------------------------------- Manager
-- a Manager which is missing, or shared by two stores.
-- no rows: every store has a manager of its own.
SELECT Manager, COUNT(*) AS stores
FROM dbo.vw_Sales_StorePerformance
GROUP BY Manager
HAVING Manager IS NULL
    OR COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- TotalOrders
-- the orders the rows add up to, against the orders; 15 and 15, every order having a store.
SELECT SUM(TotalOrders), (SELECT COUNT(*) FROM dbo.SalesOrder)
FROM dbo.vw_Sales_StorePerformance
;

-- the smallest and the largest TotalOrders; 1 and 5 (WHSL-01). No store is without an order.
SELECT MIN(TotalOrders), MAX(TotalOrders)
FROM dbo.vw_Sales_StorePerformance
;


-- ----------------------------------------------------------------------------------------------------- UniqueCustomers
-- the smallest and the largest UniqueCustomers; 1 and 3 (WHSL-01).
SELECT MIN(UniqueCustomers), MAX(UniqueCustomers)
FROM dbo.vw_Sales_StorePerformance
;


-- ---------------------------------------------------------------------------------------------------------- GrossSales
-- the gross sales the rows add up to, against the Subtotal of the orders; 60097.74 each.
SELECT SUM(GrossSales), (SELECT SUM(Subtotal) FROM dbo.SalesOrder)
FROM dbo.vw_Sales_StorePerformance
;


-- ------------------------------------------------------------------------------------------------------ TotalDiscounts
-- the discounts the rows add up to, against the DiscountAmount of the orders; 5114.96 each.
SELECT SUM(TotalDiscounts), (SELECT SUM(DiscountAmount) FROM dbo.SalesOrder)
FROM dbo.vw_Sales_StorePerformance
;


-- ------------------------------------------------------------------------------------------------------------ NetSales
-- a store whose NetSales is above GrossSales less TotalDiscounts; an order's NetAmount is its TotalAmount, tax and
-- shipping included, less its DiscountAmount, a second time.
-- 6 rows: every store but WHSL-01, which charges no tax, and all six come out above GrossSales itself.
SELECT StoreCode, GrossSales, TotalDiscounts, NetSales
FROM dbo.vw_Sales_StorePerformance
WHERE NetSales > GrossSales - TotalDiscounts
ORDER BY StoreCode ASC
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not NetSales over TotalOrders.
-- no rows: the average follows the total.
SELECT StoreCode, AvgOrderValue
FROM dbo.vw_Sales_StorePerformance
WHERE ABS(AvgOrderValue - NetSales / TotalOrders) > 0.000001
;


-- ----------------------------------------------------------------------------------------------------- CompletedOrders
-- the completed orders the rows add up to, against the delivered orders; 9 and 9.
SELECT SUM(CompletedOrders), (SELECT COUNT(*) FROM dbo.SalesOrder WHERE Status = 'Delivered')
FROM dbo.vw_Sales_StorePerformance
;


-- ------------------------------------------------------------------------------------------------------ CompletionRate
-- a CompletionRate which is not CompletedOrders over TotalOrders, in percent.
-- no rows: every rate follows its formula, .00 (STR-SEA-01) to 100.00.
SELECT StoreCode, CompletionRate
FROM dbo.vw_Sales_StorePerformance
WHERE CompletionRate <> CAST(CompletedOrders * 100.0 / TotalOrders AS DECIMAL(5, 2))
;


-- ----------------------------------------------------------------------------------------------------------- FirstSale
-- the range of FirstSale; 2024-10-01 (STR-PDX-01) to 2024-11-02 (STR-SEA-01).
SELECT MIN(FirstSale), MAX(FirstSale)
FROM dbo.vw_Sales_StorePerformance
;


-- ------------------------------------------------------------------------------------------------------------ LastSale
-- a LastSale before its FirstSale.
-- no rows: the range runs forward, to 2024-11-07 at the latest.
SELECT StoreCode, FirstSale, LastSale
FROM dbo.vw_Sales_StorePerformance
WHERE LastSale < FirstSale
;


-- ---------------------------------------------------------------------------------------------------------- DaysActive
-- a DaysActive which is not the days from FirstSale to LastSale, both counted.
-- no rows: 1 for the four stores of one order, 31 to 35 for the others.
SELECT StoreCode, DaysActive
FROM dbo.vw_Sales_StorePerformance
WHERE DaysActive <> DATEDIFF(DAY, FirstSale, LastSale) + 1
;


-- ------------------------------------------------------------------------------------------------------- AvgDailySales
-- an AvgDailySales which is not NetSales over DaysActive.
-- no rows: the average follows the total.
SELECT StoreCode, AvgDailySales
FROM dbo.vw_Sales_StorePerformance
WHERE ABS(AvgDailySales - NetSales / DaysActive) > 0.000001
;

