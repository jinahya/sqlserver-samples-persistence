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
-- Profiling queries on the view dbo.vw_Sales_CustomerAnalysis, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_CustomerAnalysis.sql
--


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many rows, and the smallest and the largest CustomerID; 8 customers, 1 to 8.
SELECT COUNT(*), MIN(CustomerID), MAX(CustomerID)
FROM dbo.vw_Sales_CustomerAnalysis
;

-- the view groups by customer; a customer makes a row on its own.
-- no rows: no two rows share a CustomerID.
SELECT CustomerID, COUNT(*) AS rows
FROM dbo.vw_Sales_CustomerAnalysis
GROUP BY CustomerID
HAVING COUNT(*) > 1
;

-- an active customer with no row.
-- no rows: all 8 customers are active, and each has its row.
SELECT c.CustomerID, c.CustomerCode
FROM dbo.Customer c
WHERE c.IsActive = 1
  AND NOT EXISTS (SELECT 1
                  FROM dbo.vw_Sales_CustomerAnalysis v
                  WHERE v.CustomerID = c.CustomerID)
;


-- -------------------------------------------------------------------------------------------------------- CustomerCode
-- a CustomerCode which is not CUST- and three digits.
-- no rows: every code has that shape, CUST-001 to CUST-008.
SELECT CustomerID, CustomerCode
FROM dbo.vw_Sales_CustomerAnalysis
WHERE CustomerCode NOT LIKE 'CUST-[0-9][0-9][0-9]'
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- the shortest and the longest CustomerName; 17 and 26 characters, of the 255 the column allows.
SELECT MIN(LEN(CustomerName)), MAX(LEN(CustomerName))
FROM dbo.vw_Sales_CustomerAnalysis
;


-- -------------------------------------------------------------------------------------------------------- CustomerType
-- the customer types and how many customers in each; Online 1, Retail 4, Wholesale 3, none NULL.
SELECT CustomerType, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerAnalysis
GROUP BY CustomerType
ORDER BY CustomerType ASC
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- how many cities, and how many NULLs; 7 cities, Portland twice, no NULL.
SELECT COUNT(DISTINCT City), COUNT(*) - COUNT(City)
FROM dbo.vw_Sales_CustomerAnalysis
;


-- --------------------------------------------------------------------------------------------------------------- State
-- the states and how many customers in each; CA 1, OR 4, WA 3.
SELECT State, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerAnalysis
GROUP BY State
ORDER BY State ASC
;


-- ------------------------------------------------------------------------------------------------------------ SalesRep
-- a SalesRep which is not the first and the last name of the customer's rep.
-- no rows: every customer has a rep, and the name agrees.
SELECT v.CustomerID, v.SalesRep
FROM dbo.vw_Sales_CustomerAnalysis v
         JOIN dbo.Customer c ON c.CustomerID = v.CustomerID
         LEFT OUTER JOIN dbo.SalesRep sr ON sr.SalesRepID = c.SalesRepID
WHERE v.SalesRep IS NULL
   OR v.SalesRep <> sr.FirstName + ' ' + sr.LastName
;


-- ------------------------------------------------------------------------------------------------------- TerritoryName
-- the territories and how many customers in each; Northern California 2, Pacific Northwest 6.
SELECT TerritoryName, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerAnalysis
GROUP BY TerritoryName
ORDER BY TerritoryName ASC
;


-- --------------------------------------------------------------------------------------------------------- TotalOrders
-- a customer whose TotalOrders is not the count of its orders.
-- no rows: every count agrees, 1 to 3 orders a customer.
SELECT v.CustomerID, v.TotalOrders
FROM dbo.vw_Sales_CustomerAnalysis v
WHERE v.TotalOrders <> (SELECT COUNT(*)
                        FROM dbo.SalesOrder so
                        WHERE so.CustomerID = v.CustomerID)
;


-- ---------------------------------------------------------------------------------------------------------- TotalSales
-- a customer whose TotalSales is not the NetAmount of its orders. The view joins SalesReturn beside SalesOrder,
-- which would repeat the orders of a customer with two returns or more; no customer has.
-- no rows: every total agrees, 53911.82 in all.
SELECT v.CustomerID, v.TotalSales
FROM dbo.vw_Sales_CustomerAnalysis v
WHERE v.TotalSales <> (SELECT SUM(so.NetAmount)
                       FROM dbo.SalesOrder so
                       WHERE so.CustomerID = v.CustomerID)
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not TotalSales over TotalOrders.
-- no rows: every average agrees, to the cent.
SELECT CustomerID, TotalSales, TotalOrders, AvgOrderValue
FROM dbo.vw_Sales_CustomerAnalysis
WHERE ABS(AvgOrderValue - TotalSales / TotalOrders) >= 0.01
;


-- ------------------------------------------------------------------------------------------------------- LastOrderDate
-- the earliest and the latest LastOrderDate; 2024-10-12 and 2024-11-07.
SELECT MIN(LastOrderDate), MAX(LastOrderDate)
FROM dbo.vw_Sales_CustomerAnalysis
;


-- ------------------------------------------------------------------------------------------------------ FirstOrderDate
-- the earliest and the latest FirstOrderDate; 2024-10-01 and 2024-11-02.
SELECT MIN(FirstOrderDate), MAX(FirstOrderDate)
FROM dbo.vw_Sales_CustomerAnalysis
;

-- a FirstOrderDate after the LastOrderDate.
-- no rows: the first comes first.
SELECT CustomerID, FirstOrderDate, LastOrderDate
FROM dbo.vw_Sales_CustomerAnalysis
WHERE FirstOrderDate > LastOrderDate
;


-- ------------------------------------------------------------------------------------------------ CustomerLifespanDays
-- a CustomerLifespanDays which is not the days from the first order to the last.
-- no rows: every span agrees, 0 to 34 days.
SELECT CustomerID, FirstOrderDate, LastOrderDate, CustomerLifespanDays
FROM dbo.vw_Sales_CustomerAnalysis
WHERE CustomerLifespanDays <> DATEDIFF(DAY, FirstOrderDate, LastOrderDate)
;


-- ------------------------------------------------------------------------------------------------------ DeliveredSales
-- DeliveredSales over the view against NetAmount over the Delivered orders; 28293.01 both.
SELECT SUM(DeliveredSales), (SELECT SUM(NetAmount) FROM dbo.SalesOrder WHERE Status = 'Delivered') AS delivered
FROM dbo.vw_Sales_CustomerAnalysis
;


-- -------------------------------------------------------------------------------------------------------- PendingSales
-- a customer whose DeliveredSales and PendingSales do not make its TotalSales. PendingSales counts Confirmed and
-- InProduction only, so a Shipped order falls in neither.
-- CUST-002, whose single order of 1355.98 is Shipped, with 0 in both.
SELECT CustomerCode, TotalSales, DeliveredSales, PendingSales
FROM dbo.vw_Sales_CustomerAnalysis
WHERE TotalSales <> DeliveredSales + PendingSales
;


-- -------------------------------------------------------------------------------------------------------- TotalReturns
-- the customers with a return; CUST-001 and CUST-007, 1 each.
SELECT CustomerCode, TotalReturns
FROM dbo.vw_Sales_CustomerAnalysis
WHERE TotalReturns > 0
ORDER BY CustomerCode ASC
;


-- -------------------------------------------------------------------------------------------------------- TotalRefunds
-- a customer whose TotalRefunds is not the RefundAmount of its returns. The view joins SalesReturn beside
-- SalesOrder, so a refund repeats once per order of the customer.
-- CUST-001 799.98 against 399.99, CUST-007 1799.98 against 899.99: two orders each, twice the refund.
SELECT v.CustomerCode, v.TotalOrders, v.TotalRefunds, r.refunds
FROM dbo.vw_Sales_CustomerAnalysis v
         JOIN (SELECT CustomerID, SUM(RefundAmount) AS refunds
               FROM dbo.SalesReturn
               GROUP BY CustomerID) r ON r.CustomerID = v.CustomerID
WHERE v.TotalRefunds <> r.refunds
ORDER BY v.CustomerCode ASC
;


-- ----------------------------------------------------------------------------------------------------- CustomerSegment
-- the segments and how many customers in each; Occasional 6, One-Time 2.
SELECT CustomerSegment, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerAnalysis
GROUP BY CustomerSegment
ORDER BY CustomerSegment ASC
;

-- a CustomerSegment which does not follow from TotalOrders.
-- no rows: every segment agrees.
SELECT CustomerID, TotalOrders, CustomerSegment
FROM dbo.vw_Sales_CustomerAnalysis
WHERE CustomerSegment <> CASE
                             WHEN TotalOrders = 0 THEN 'No Orders'
                             WHEN TotalOrders = 1 THEN 'One-Time'
                             WHEN TotalOrders BETWEEN 2 AND 5 THEN 'Occasional'
                             WHEN TotalOrders BETWEEN 6 AND 10 THEN 'Regular'
                             ELSE 'VIP'
                         END
;


-- ---------------------------------------------------------------------------------------------------------- ReturnRate
-- the customers with a ReturnRate above 0; CUST-001 37.09 and CUST-007 42.19, both on the doubled refund.
SELECT CustomerCode, TotalRefunds, TotalSales, ReturnRate
FROM dbo.vw_Sales_CustomerAnalysis
WHERE ReturnRate > 0
ORDER BY CustomerCode ASC
;


-- -------------------------------------------------------------------------------------------------- DaysSinceLastOrder
-- a DaysSinceLastOrder which is not the days from the last order to today; the value grows with the clock.
-- no rows: every value agrees.
SELECT CustomerID, LastOrderDate, DaysSinceLastOrder
FROM dbo.vw_Sales_CustomerAnalysis
WHERE DaysSinceLastOrder <> DATEDIFF(DAY, LastOrderDate, GETDATE())
;


-- ------------------------------------------------------------------------------------------------------ CustomerStatus
-- the statuses and how many customers in each; Dormant 8, every last order more than 180 days ago.
SELECT CustomerStatus, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerAnalysis
GROUP BY CustomerStatus
ORDER BY CustomerStatus ASC
;


-- ----------------------------------------------------------------------------------------------------- AvgMonthlyValue
-- the customers with no AvgMonthlyValue; CUST-002 and CUST-008, a single order each and so a span of 0 days.
SELECT CustomerCode, CustomerLifespanDays
FROM dbo.vw_Sales_CustomerAnalysis
WHERE AvgMonthlyValue IS NULL
ORDER BY CustomerCode ASC
;

-- an AvgMonthlyValue which is not TotalSales over the span in 30-day months.
-- no rows: every value agrees, to the cent.
SELECT CustomerID, TotalSales, CustomerLifespanDays, AvgMonthlyValue
FROM dbo.vw_Sales_CustomerAnalysis
WHERE ABS(AvgMonthlyValue - TotalSales / NULLIF(CustomerLifespanDays / 30.0, 0)) >= 0.01
;

