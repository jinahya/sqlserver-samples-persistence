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
-- Profiling queries on the view dbo.vw_Sales_CustomerLifetimeValue, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_CustomerLifetimeValue.sql
--


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many rows, and the smallest and the largest CustomerID; 8 customers, 1 to 8.
SELECT COUNT(*), MIN(CustomerID), MAX(CustomerID)
FROM dbo.vw_Sales_CustomerLifetimeValue
;

-- the view groups by customer; a customer makes a row on its own.
-- no rows: no two rows share a CustomerID.
SELECT CustomerID, COUNT(*) AS rows
FROM dbo.vw_Sales_CustomerLifetimeValue
GROUP BY CustomerID
HAVING COUNT(*) > 1
;

-- a customer with an order but no row; the view keeps the customers with TotalOrders above 0.
-- no rows: every customer has ordered, and each has its row.
SELECT c.CustomerID, c.CustomerCode
FROM dbo.Customer c
WHERE EXISTS (SELECT 1
              FROM dbo.SalesOrder so
              WHERE so.CustomerID = c.CustomerID)
  AND NOT EXISTS (SELECT 1
                  FROM dbo.vw_Sales_CustomerLifetimeValue v
                  WHERE v.CustomerID = c.CustomerID)
;


-- -------------------------------------------------------------------------------------------------------- CustomerCode
-- a CustomerCode which is not the customer's.
-- no rows: every code agrees.
SELECT v.CustomerID, v.CustomerCode
FROM dbo.vw_Sales_CustomerLifetimeValue v
         JOIN dbo.Customer c ON c.CustomerID = v.CustomerID
WHERE v.CustomerCode <> c.CustomerCode
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- the shortest and the longest CustomerName; 17 and 26 characters, of the 255 the column allows.
SELECT MIN(LEN(CustomerName)), MAX(LEN(CustomerName))
FROM dbo.vw_Sales_CustomerLifetimeValue
;


-- -------------------------------------------------------------------------------------------------------- CustomerType
-- the customer types and how many customers in each; Online 1, Retail 4, Wholesale 3, none NULL.
SELECT CustomerType, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerLifetimeValue
GROUP BY CustomerType
ORDER BY CustomerType ASC
;


-- ------------------------------------------------------------------------------------------------------------ SalesRep
-- the reps and how many customers each has; Amanda Taylor 1, Emily Williams 2, Michael Johnson 3, Robert Moore 2.
SELECT SalesRep, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerLifetimeValue
GROUP BY SalesRep
ORDER BY SalesRep ASC
;


-- --------------------------------------------------------------------------------------------------- FirstPurchaseDate
-- the earliest and the latest FirstPurchaseDate; 2024-10-01 and 2024-11-02.
SELECT MIN(FirstPurchaseDate), MAX(FirstPurchaseDate)
FROM dbo.vw_Sales_CustomerLifetimeValue
;


-- ---------------------------------------------------------------------------------------------------- LastPurchaseDate
-- the earliest and the latest LastPurchaseDate; 2024-10-12 and 2024-11-07.
SELECT MIN(LastPurchaseDate), MAX(LastPurchaseDate)
FROM dbo.vw_Sales_CustomerLifetimeValue
;

-- a LastPurchaseDate before the FirstPurchaseDate.
-- no rows: the first comes first.
SELECT CustomerID, FirstPurchaseDate, LastPurchaseDate
FROM dbo.vw_Sales_CustomerLifetimeValue
WHERE LastPurchaseDate < FirstPurchaseDate
;


-- ---------------------------------------------------------------------------------------------- CustomerLifetimeMonths
-- a CustomerLifetimeMonths which is not the month boundaries crossed from the first purchase to the last, plus one.
-- no rows: every value agrees; 1 for CUST-002 and CUST-008, 2 for the rest.
SELECT CustomerID, FirstPurchaseDate, LastPurchaseDate, CustomerLifetimeMonths
FROM dbo.vw_Sales_CustomerLifetimeValue
WHERE CustomerLifetimeMonths <> DATEDIFF(MONTH, FirstPurchaseDate, LastPurchaseDate) + 1
;


-- --------------------------------------------------------------------------------------------------------- TotalOrders
-- a customer whose TotalOrders is not the count of its orders.
-- no rows: every count agrees, 15 orders in all.
SELECT v.CustomerID, v.TotalOrders
FROM dbo.vw_Sales_CustomerLifetimeValue v
WHERE v.TotalOrders <> (SELECT COUNT(*)
                        FROM dbo.SalesOrder so
                        WHERE so.CustomerID = v.CustomerID)
;


-- -------------------------------------------------------------------------------------------------------- TotalRevenue
-- a customer whose TotalRevenue is not the NetAmount of its orders. The view joins the order lines and sums the
-- order's NetAmount on each of them, so an order of several lines counts several times.
-- 5 customers: CUST-001 3381.93 against 2156.96, CUST-003 6057.92 against 4254.94, CUST-004 45897.84 against
-- 18699.08, CUST-006 29958.40 against 14979.20, CUST-007 6717.91 against 4265.94.
SELECT v.CustomerCode, v.TotalRevenue, o.net
FROM dbo.vw_Sales_CustomerLifetimeValue v
         JOIN (SELECT CustomerID, SUM(NetAmount) AS net
               FROM dbo.SalesOrder
               GROUP BY CustomerID) o ON o.CustomerID = v.CustomerID
WHERE v.TotalRevenue <> o.net
ORDER BY v.CustomerCode ASC
;


-- --------------------------------------------------------------------------------------------------------- TotalProfit
-- a TotalProfit other than TotalRevenue; the profit takes off Quantity times StandardCost, and every item sold has
-- a StandardCost of 0.
-- no rows: TotalProfit is TotalRevenue on every row.
SELECT CustomerID, TotalRevenue, TotalProfit
FROM dbo.vw_Sales_CustomerLifetimeValue
WHERE TotalProfit <> TotalRevenue
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not the average NetAmount of the customer's orders. The view averages over the lines,
-- so an order weighs as many times as it has lines.
-- 4 customers: CUST-001 1127.310000 against 1078.480000, CUST-003 2019.306666 against 2127.470000, CUST-004
-- 9179.568000 against 9349.540000, CUST-007 2239.303333 against 2132.970000. CUST-006's two orders, two lines
-- each, weigh alike.
SELECT v.CustomerCode, v.AvgOrderValue, o.average
FROM dbo.vw_Sales_CustomerLifetimeValue v
         JOIN (SELECT CustomerID, AVG(NetAmount) AS average
               FROM dbo.SalesOrder
               GROUP BY CustomerID) o ON o.CustomerID = v.CustomerID
WHERE ABS(v.AvgOrderValue - o.average) >= 0.01
ORDER BY v.CustomerCode ASC
;


-- --------------------------------------------------------------------------------------------------- AvgMonthlyRevenue
-- an AvgMonthlyRevenue which is not TotalRevenue over CustomerLifetimeMonths.
-- no rows: the formula holds, on the repeated TotalRevenue.
SELECT CustomerID, TotalRevenue, CustomerLifetimeMonths, AvgMonthlyRevenue
FROM dbo.vw_Sales_CustomerLifetimeValue
WHERE ABS(AvgMonthlyRevenue - TotalRevenue / CustomerLifetimeMonths) >= 0.01
;


-- -------------------------------------------------------------------------------------------------------- CustomerTier
-- the tiers and how many customers in each; Bronze 3, Platinum 2, Silver 3, on the repeated TotalRevenue.
SELECT CustomerTier, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerLifetimeValue
GROUP BY CustomerTier
ORDER BY CustomerTier ASC
;

-- a CustomerTier which does not follow from TotalRevenue.
-- no rows: every tier agrees.
SELECT CustomerID, TotalRevenue, CustomerTier
FROM dbo.vw_Sales_CustomerLifetimeValue
WHERE CustomerTier <> CASE
                         WHEN TotalRevenue >= 20000 THEN 'Platinum'
                         WHEN TotalRevenue >= 10000 THEN 'Gold'
                         WHEN TotalRevenue >= 5000 THEN 'Silver'
                         WHEN TotalRevenue >= 1000 THEN 'Bronze'
                         ELSE 'Standard'
                     END
;


-- ----------------------------------------------------------------------------------------------- DaysSinceLastPurchase
-- a DaysSinceLastPurchase which is not the days from the last purchase to today; the value grows with the clock.
-- no rows: every value agrees.
SELECT CustomerID, LastPurchaseDate, DaysSinceLastPurchase
FROM dbo.vw_Sales_CustomerLifetimeValue
WHERE DaysSinceLastPurchase <> DATEDIFF(DAY, LastPurchaseDate, GETDATE())
;


-- ------------------------------------------------------------------------------------------------------ ActivityStatus
-- the statuses and how many customers in each; Churned 8, every last purchase more than 180 days ago.
SELECT ActivityStatus, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerLifetimeValue
GROUP BY ActivityStatus
ORDER BY ActivityStatus ASC
;


-- ---------------------------------------------------------------------------------------------- ProjectedAnnualRevenue
-- a ProjectedAnnualRevenue which is not twelve times AvgMonthlyRevenue.
-- no rows: the formula holds.
SELECT CustomerID, AvgMonthlyRevenue, ProjectedAnnualRevenue
FROM dbo.vw_Sales_CustomerLifetimeValue
WHERE ProjectedAnnualRevenue <> AvgMonthlyRevenue * 12
;


-- ----------------------------------------------------------------------------------------------------------- ValueRank
-- the smallest and the largest ValueRank, and how many distinct; 1, 8 and 8, no tie.
SELECT MIN(ValueRank), MAX(ValueRank), COUNT(DISTINCT ValueRank)
FROM dbo.vw_Sales_CustomerLifetimeValue
;

-- the first three by ValueRank; CUST-004, CUST-006 and CUST-007.
SELECT TOP (3) ValueRank, CustomerCode, TotalRevenue
FROM dbo.vw_Sales_CustomerLifetimeValue
ORDER BY ValueRank ASC
;

