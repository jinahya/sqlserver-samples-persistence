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
-- Profiling queries on the view dbo.vw_Sales_ProductPerformance, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_ProductPerformance.sql
--


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- how many rows, and how many distinct ItemCode; 6 and 6, a row per active finished good.
SELECT COUNT(*), COUNT(DISTINCT ItemCode)
FROM dbo.vw_Sales_ProductPerformance
;

-- an active finished good the view leaves out; it joins the details with LEFT JOIN, so an unsold item stays.
-- no rows: every active FG item has its row.
SELECT i.ItemCode
FROM dbo.Items i
         JOIN dbo.ItemType t ON t.ItemTypeID = i.ItemTypeID
WHERE t.TypeCode = 'FG'
  AND i.IsActive = 1
  AND NOT EXISTS (SELECT 1
                  FROM dbo.vw_Sales_ProductPerformance p
                  WHERE p.ItemCode = i.ItemCode)
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- the shortest and the longest ItemName; 32 and 36 characters, of the 255 the column allows.
SELECT MIN(LEN(ItemName)), MAX(LEN(ItemName))
FROM dbo.vw_Sales_ProductPerformance
;


-- ---------------------------------------------------------------------------------------------------------- OrderCount
-- the smallest and the largest OrderCount; 2 (FG-FUT-005) and 6 (FG-FUT-002).
SELECT MIN(OrderCount), MAX(OrderCount)
FROM dbo.vw_Sales_ProductPerformance
;

-- an OrderCount which is not the number of orders with a detail line of the item.
-- no rows: every count matches SalesOrderDetail.
SELECT p.ItemCode, p.OrderCount
FROM dbo.vw_Sales_ProductPerformance p
WHERE p.OrderCount <> (SELECT COUNT(DISTINCT sod.SalesOrderID)
                       FROM dbo.SalesOrderDetail sod
                                JOIN dbo.Items i ON i.ItemID = sod.ItemID
                       WHERE i.ItemCode = p.ItemCode)
;


-- ------------------------------------------------------------------------------------------------------ TotalUnitsSold
-- the units the rows add up to; 150, the Quantity of every detail line.
SELECT SUM(TotalUnitsSold), (SELECT SUM(Quantity) FROM dbo.SalesOrderDetail)
FROM dbo.vw_Sales_ProductPerformance
;

-- a TotalUnitsSold which is not the sum of the units of the three channels.
-- no rows: every order comes through one of RETAIL, ONLINE and WHOLESALE.
SELECT ItemCode, TotalUnitsSold, RetailUnits, OnlineUnits, WholesaleUnits
FROM dbo.vw_Sales_ProductPerformance
WHERE TotalUnitsSold <> RetailUnits + OnlineUnits + WholesaleUnits
;


-- -------------------------------------------------------------------------------------------------------- TotalRevenue
-- the revenue the rows add up to; 59173.635, the NetAmount of every detail line.
SELECT SUM(TotalRevenue), (SELECT SUM(NetAmount) FROM dbo.SalesOrderDetail)
FROM dbo.vw_Sales_ProductPerformance
;


-- ----------------------------------------------------------------------------------------------------- AvgSellingPrice
-- an AvgSellingPrice which is not the item's one UnitPrice; every line of an item sells at the same price.
-- no rows: the average is the price, 299.99 to 899.99.
SELECT p.ItemCode, p.AvgSellingPrice
FROM dbo.vw_Sales_ProductPerformance p
WHERE EXISTS (SELECT 1
              FROM dbo.SalesOrderDetail sod
                       JOIN dbo.Items i ON i.ItemID = sod.ItemID
              WHERE i.ItemCode = p.ItemCode
                AND sod.UnitPrice <> p.AvgSellingPrice)
;

-- the smallest and the largest AvgSellingPrice; 299.99 (FG-FUT-001) and 899.99 (FG-FUT-006).
SELECT MIN(AvgSellingPrice), MAX(AvgSellingPrice)
FROM dbo.vw_Sales_ProductPerformance
;


-- ------------------------------------------------------------------------------------------------------------ UnitCost
-- the distinct UnitCost, the item's StandardCost; .0000 alone, a finding: no finished good carries a cost, so
-- every profit below equals its revenue.
SELECT UnitCost, COUNT(*) AS items
FROM dbo.vw_Sales_ProductPerformance
GROUP BY UnitCost
;


-- ----------------------------------------------------------------------------------------------- AvgGrossProfitPerUnit
-- an AvgGrossProfitPerUnit which is not AvgSellingPrice less UnitCost.
-- no rows: every value follows its formula.
SELECT ItemCode, AvgGrossProfitPerUnit
FROM dbo.vw_Sales_ProductPerformance
WHERE AvgGrossProfitPerUnit <> AvgSellingPrice - UnitCost
;


-- ---------------------------------------------------------------------------------------------------- TotalGrossProfit
-- a TotalGrossProfit which is not TotalRevenue less TotalUnitsSold at UnitCost.
-- no rows: every value follows its formula, and with no cost it equals TotalRevenue.
SELECT ItemCode, TotalGrossProfit
FROM dbo.vw_Sales_ProductPerformance
WHERE TotalGrossProfit <> TotalRevenue - TotalUnitsSold * UnitCost
;


-- -------------------------------------------------------------------------------------------------- GrossMarginPercent
-- the distinct GrossMarginPercent; 100.00 alone, the cost being zero.
SELECT GrossMarginPercent, COUNT(*) AS items
FROM dbo.vw_Sales_ProductPerformance
GROUP BY GrossMarginPercent
;


-- -------------------------------------------------------------------------------------------------- AvgDiscountPercent
-- the smallest and the largest AvgDiscountPercent; 3.333333 (FG-FUT-006) and 10 (FG-FUT-005, sold only wholesale).
SELECT MIN(AvgDiscountPercent), MAX(AvgDiscountPercent)
FROM dbo.vw_Sales_ProductPerformance
;


-- --------------------------------------------------------------------------------------------------------- RetailUnits
-- the units sold in retail stores; 15, and FG-FUT-005 has none.
SELECT SUM(RetailUnits), SUM(CASE WHEN RetailUnits = 0 THEN 1 ELSE 0 END) AS unsold
FROM dbo.vw_Sales_ProductPerformance
;


-- --------------------------------------------------------------------------------------------------------- OnlineUnits
-- the units sold online; 7, of FG-FUT-003 and FG-FUT-004 only, the other four have none.
SELECT SUM(OnlineUnits), SUM(CASE WHEN OnlineUnits = 0 THEN 1 ELSE 0 END) AS unsold
FROM dbo.vw_Sales_ProductPerformance
;


-- ------------------------------------------------------------------------------------------------------ WholesaleUnits
-- the units sold wholesale; 128, FG-FUT-004 and FG-FUT-006 have none.
SELECT SUM(WholesaleUnits), SUM(CASE WHEN WholesaleUnits = 0 THEN 1 ELSE 0 END) AS unsold
FROM dbo.vw_Sales_ProductPerformance
;


-- ------------------------------------------------------------------------------------------------------- UnitSalesRank
-- a UnitSalesRank which is not 1 more than the items selling more units.
-- no rows: the ranks follow TotalUnitsSold.
SELECT p.ItemCode, p.UnitSalesRank
FROM dbo.vw_Sales_ProductPerformance p
WHERE p.UnitSalesRank <> 1 + (SELECT COUNT(*)
                              FROM dbo.vw_Sales_ProductPerformance q
                              WHERE q.TotalUnitsSold > p.TotalUnitsSold)
;


-- --------------------------------------------------------------------------------------------------------- RevenueRank
-- a RevenueRank which is not 1 more than the items earning more.
-- no rows: the ranks follow TotalRevenue.
SELECT p.ItemCode, p.RevenueRank
FROM dbo.vw_Sales_ProductPerformance p
WHERE p.RevenueRank <> 1 + (SELECT COUNT(*)
                            FROM dbo.vw_Sales_ProductPerformance q
                            WHERE q.TotalRevenue > p.TotalRevenue)
;


-- ---------------------------------------------------------------------------------------------------------- ProfitRank
-- an item whose ProfitRank differs from its RevenueRank; with no cost, profit ranks as revenue does.
-- no rows: the two ranks agree.
SELECT ItemCode, RevenueRank, ProfitRank
FROM dbo.vw_Sales_ProductPerformance
WHERE ProfitRank <> RevenueRank
;

