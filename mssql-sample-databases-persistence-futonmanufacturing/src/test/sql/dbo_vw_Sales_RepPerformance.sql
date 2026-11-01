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
-- Profiling queries on the view dbo.vw_Sales_RepPerformance, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_RepPerformance.sql
--


-- -------------------------------------------------------------------------------------------------------- EmployeeCode
-- how many rows, and how many distinct EmployeeCode; 8 and 8, a row per active rep.
SELECT COUNT(*), COUNT(DISTINCT EmployeeCode)
FROM dbo.vw_Sales_RepPerformance
;

-- an active rep the view leaves out.
-- no rows: every active SalesRep has its row.
SELECT sr.EmployeeCode
FROM dbo.SalesRep sr
WHERE sr.IsActive = 1
  AND NOT EXISTS (SELECT 1
                  FROM dbo.vw_Sales_RepPerformance v
                  WHERE v.EmployeeCode = sr.EmployeeCode)
;


-- -------------------------------------------------------------------------------------------------------- SalesRepName
-- the shortest and the longest SalesRepName; 11 and 15 characters, of the 101 the column allows.
SELECT MIN(LEN(SalesRepName)), MAX(LEN(SalesRepName))
FROM dbo.vw_Sales_RepPerformance
;


-- ------------------------------------------------------------------------------------------------------- TerritoryName
-- the reps of each territory; Pacific Northwest 3, every other territory 1.
SELECT TerritoryName, COUNT(*) AS reps
FROM dbo.vw_Sales_RepPerformance
GROUP BY TerritoryName
ORDER BY reps DESC, TerritoryName ASC
;


-- -------------------------------------------------------------------------------------------------------------- Region
-- the reps of each region; West 5, Central 1, East 2.
SELECT Region, COUNT(*) AS reps
FROM dbo.vw_Sales_RepPerformance
GROUP BY Region
ORDER BY Region ASC
;


-- --------------------------------------------------------------------------------------------------------- TotalOrders
-- a TotalOrders which is not the rep's orders.
-- no rows: every count matches SalesOrder.
SELECT v.EmployeeCode, v.TotalOrders
FROM dbo.vw_Sales_RepPerformance v
         JOIN dbo.SalesRep sr ON sr.EmployeeCode = v.EmployeeCode
WHERE v.TotalOrders <> (SELECT COUNT(*) FROM dbo.SalesOrder so WHERE so.SalesRepID = sr.SalesRepID)
;

-- the reps with no order; 3, SR-004, SR-005 and SR-006, the reps outside the West.
SELECT EmployeeCode, Region
FROM dbo.vw_Sales_RepPerformance
WHERE TotalOrders = 0
ORDER BY EmployeeCode ASC
;


-- ----------------------------------------------------------------------------------------------------- UniqueCustomers
-- the smallest and the largest UniqueCustomers; 0 and 3 (SR-001).
SELECT MIN(UniqueCustomers), MAX(UniqueCustomers)
FROM dbo.vw_Sales_RepPerformance
;


-- ---------------------------------------------------------------------------------------------------------- GrossSales
-- a GrossSales which is not the Subtotal of the rep's orders. The view joins both the orders and the quotes of a
-- rep, so a rep with two quotes would count every order twice.
-- no rows: no rep has more than one quote, so the sums still hold.
SELECT v.EmployeeCode, v.GrossSales
FROM dbo.vw_Sales_RepPerformance v
         JOIN dbo.SalesRep sr ON sr.EmployeeCode = v.EmployeeCode
WHERE v.GrossSales <> (SELECT SUM(so.Subtotal) FROM dbo.SalesOrder so WHERE so.SalesRepID = sr.SalesRepID)
;

-- how many reps have no GrossSales; 3, the reps with no order, SUM over no rows being NULL.
SELECT COUNT(*)
FROM dbo.vw_Sales_RepPerformance
WHERE GrossSales IS NULL
;


-- ------------------------------------------------------------------------------------------------------ TotalDiscounts
-- a TotalDiscounts which is not the DiscountAmount of the rep's orders.
-- no rows: every sum matches SalesOrder.
SELECT v.EmployeeCode, v.TotalDiscounts
FROM dbo.vw_Sales_RepPerformance v
         JOIN dbo.SalesRep sr ON sr.EmployeeCode = v.EmployeeCode
WHERE v.TotalDiscounts <> (SELECT SUM(so.DiscountAmount) FROM dbo.SalesOrder so WHERE so.SalesRepID = sr.SalesRepID)
;


-- ------------------------------------------------------------------------------------------------------------ NetSales
-- a NetSales which is not the NetAmount of the rep's orders.
-- no rows: every sum matches SalesOrder.
SELECT v.EmployeeCode, v.NetSales
FROM dbo.vw_Sales_RepPerformance v
         JOIN dbo.SalesRep sr ON sr.EmployeeCode = v.EmployeeCode
WHERE v.NetSales <> (SELECT SUM(so.NetAmount) FROM dbo.SalesOrder so WHERE so.SalesRepID = sr.SalesRepID)
;

-- a rep whose NetSales is above GrossSales less TotalDiscounts; an order's NetAmount is its TotalAmount, tax and
-- shipping included, less its DiscountAmount, a second time.
-- 3 rows: SR-002, SR-003 and SR-008; for SR-001 and SR-007 the discount taken twice outweighs the tax and the shipping.
SELECT EmployeeCode, GrossSales, TotalDiscounts, NetSales
FROM dbo.vw_Sales_RepPerformance
WHERE NetSales > GrossSales - TotalDiscounts
ORDER BY EmployeeCode ASC
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not NetSales over TotalOrders.
-- no rows: the average follows the total.
SELECT EmployeeCode, AvgOrderValue
FROM dbo.vw_Sales_RepPerformance
WHERE ABS(AvgOrderValue - NetSales / NULLIF(TotalOrders, 0)) > 0.000001
;


-- ------------------------------------------------------------------------------------------------------ DeliveredSales
-- a DeliveredSales above NetSales.
-- no rows: delivered orders are a part of the rep's orders.
SELECT EmployeeCode, DeliveredSales, NetSales
FROM dbo.vw_Sales_RepPerformance
WHERE DeliveredSales > NetSales
;

-- the DeliveredSales of the reps with no order; .00 for all 3, the CASE giving 0 to the NULL order of the LEFT JOIN.
SELECT EmployeeCode, DeliveredSales
FROM dbo.vw_Sales_RepPerformance
WHERE TotalOrders = 0
ORDER BY EmployeeCode ASC
;


-- ------------------------------------------------------------------------------------------------------- AvgDaysToShip
-- the smallest and the largest AvgDaysToShip; 3.0 (SR-008) and 12.0 (SR-007). Only shipped orders with a
-- ShipDate count; AVG is of whole days, an int.
SELECT MIN(AvgDaysToShip), MAX(AvgDaysToShip)
FROM dbo.vw_Sales_RepPerformance
;


-- ------------------------------------------------------------------------------------------------------- QuotesCreated
-- a QuotesCreated which is not the rep's quotes.
-- no rows: SR-001 and SR-007 have 1 each, the rest none.
SELECT v.EmployeeCode, v.QuotesCreated
FROM dbo.vw_Sales_RepPerformance v
         JOIN dbo.SalesRep sr ON sr.EmployeeCode = v.EmployeeCode
WHERE v.QuotesCreated <> (SELECT COUNT(*) FROM dbo.SalesQuote sq WHERE sq.SalesRepID = sr.SalesRepID)
;


-- ------------------------------------------------------------------------------------------------------ QuotesAccepted
-- the accepted quotes; 0 for every rep. The view counts joined rows, not quotes, so an accepted quote would count
-- once per order of its rep.
SELECT SUM(QuotesAccepted), MAX(QuotesAccepted)
FROM dbo.vw_Sales_RepPerformance
;


-- -------------------------------------------------------------------------------------------------------- QuoteWinRate
-- the win rates; .00 for the 2 reps with a quote, NULL for the 6 without.
SELECT QuoteWinRate, COUNT(*) AS reps
FROM dbo.vw_Sales_RepPerformance
GROUP BY QuoteWinRate
;


-- ----------------------------------------------------------------------------------------------------------- SalesRank
-- the ranks; 1 to 5 by NetSales, then 6 shared by the 3 reps with no sales, their NULL ranked last.
SELECT SalesRank, COUNT(*) AS reps
FROM dbo.vw_Sales_RepPerformance
GROUP BY SalesRank
ORDER BY SalesRank ASC
;

