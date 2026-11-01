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
-- Profiling queries on the view dbo.vw_Sales_TrendAnalysis, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_TrendAnalysis.sql
--


-- ---------------------------------------------------------------------------------------------------------------- Year
-- how many rows, and the years; 2 months, both of 2024.
SELECT COUNT(*), MIN(Year), MAX(Year)
FROM dbo.vw_Sales_TrendAnalysis
;


-- --------------------------------------------------------------------------------------------------------------- Month
-- the months; 10 and 11, a row each.
SELECT Year, Month
FROM dbo.vw_Sales_TrendAnalysis
ORDER BY Year ASC, Month ASC
;

-- the grain of the view; the year and the month.
-- no rows: no two rows share them.
SELECT Year, Month, COUNT(*) AS rows
FROM dbo.vw_Sales_TrendAnalysis
GROUP BY Year, Month
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------- Quarter
-- a Quarter which is not the quarter of its Month.
-- no rows: both months fall in quarter 4.
SELECT Year, Month, Quarter
FROM dbo.vw_Sales_TrendAnalysis
WHERE Quarter <> (Month - 1) / 3 + 1
;


-- ---------------------------------------------------------------------------------------------------------- MonthStart
-- a MonthStart which is not the first day of its Year and Month.
-- no rows: 2024-10-01 and 2024-11-01.
SELECT Year, Month, MonthStart
FROM dbo.vw_Sales_TrendAnalysis
WHERE MonthStart <> DATEFROMPARTS(Year, Month, 1)
;


-- ---------------------------------------------------------------------------------------------------------- OrderCount
-- the orders the rows add up to, against the orders; 15 and 15, 8 in October and 7 in November.
SELECT SUM(OrderCount), (SELECT COUNT(*) FROM dbo.SalesOrder)
FROM dbo.vw_Sales_TrendAnalysis
;


-- ----------------------------------------------------------------------------------------------------- UniqueCustomers
-- the customers of each month; 7 and 7, each of the 8 customers but one buying in a month.
SELECT Month, UniqueCustomers
FROM dbo.vw_Sales_TrendAnalysis
ORDER BY Month ASC
;


-- ---------------------------------------------------------------------------------------------------------- GrossSales
-- the gross sales the rows add up to, against the Subtotal of the orders; 60097.74 each.
SELECT SUM(GrossSales), (SELECT SUM(Subtotal) FROM dbo.SalesOrder)
FROM dbo.vw_Sales_TrendAnalysis
;


-- ----------------------------------------------------------------------------------------------------------- Discounts
-- the discounts the rows add up to, against the DiscountAmount of the orders; 5114.96 each.
SELECT SUM(Discounts), (SELECT SUM(DiscountAmount) FROM dbo.SalesOrder)
FROM dbo.vw_Sales_TrendAnalysis
;


-- ------------------------------------------------------------------------------------------------------------ NetSales
-- the net sales the rows add up to, against the NetAmount of the orders; 53911.82 each.
SELECT SUM(NetSales), (SELECT SUM(NetAmount) FROM dbo.SalesOrder)
FROM dbo.vw_Sales_TrendAnalysis
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not NetSales over OrderCount.
-- no rows: the average follows the total.
SELECT Month, AvgOrderValue
FROM dbo.vw_Sales_TrendAnalysis
WHERE ABS(AvgOrderValue - NetSales / OrderCount) > 0.000001
;


-- ----------------------------------------------------------------------------------------------------- PriorMonthSales
-- a PriorMonthSales which is not the NetSales of the month before; the first month has none.
-- no rows: November carries October's 27361.02.
SELECT p.Month, p.PriorMonthSales, b.NetSales
FROM dbo.vw_Sales_TrendAnalysis p
         LEFT JOIN dbo.vw_Sales_TrendAnalysis b ON b.MonthStart = DATEADD(MONTH, -1, p.MonthStart)
WHERE ISNULL(p.PriorMonthSales, -1) <> ISNULL(b.NetSales, -1)
;


-- ----------------------------------------------------------------------------------------------------------- MoMChange
-- a MoMChange which is not NetSales less PriorMonthSales.
-- no rows: November's is -810.22, October's NULL.
SELECT Month, MoMChange
FROM dbo.vw_Sales_TrendAnalysis
WHERE ISNULL(MoMChange, -1) <> ISNULL(NetSales - PriorMonthSales, -1)
;


-- ---------------------------------------------------------------------------------------------------- MoMChangePercent
-- a MoMChangePercent which is not MoMChange over PriorMonthSales, in percent.
-- no rows: November's is -2.96, October's NULL.
SELECT Month, MoMChangePercent
FROM dbo.vw_Sales_TrendAnalysis
WHERE ISNULL(MoMChangePercent, -1)
          <> ISNULL(CAST(MoMChange * 100.0 / NULLIF(PriorMonthSales, 0) AS DECIMAL(5, 2)), -1)
;


-- ------------------------------------------------------------------------------------------------------ PriorYearSales
-- how many months have a PriorYearSales; 0, the view holding 2 months, not the 13 a LAG of 12 needs.
SELECT COUNT(PriorYearSales)
FROM dbo.vw_Sales_TrendAnalysis
;


-- ----------------------------------------------------------------------------------------------------------- YoYChange
-- how many months have a YoYChange; 0, with no PriorYearSales.
SELECT COUNT(YoYChange)
FROM dbo.vw_Sales_TrendAnalysis
;


-- ---------------------------------------------------------------------------------------------------- YoYChangePercent
-- how many months have a YoYChangePercent; 0, with no PriorYearSales.
SELECT COUNT(YoYChangePercent)
FROM dbo.vw_Sales_TrendAnalysis
;


-- ------------------------------------------------------------------------------------------------------- ThreeMonthAvg
-- a ThreeMonthAvg which is not the average NetSales of the month and the two before it.
-- no rows: 27361.02 for October, 26955.91 for November.
SELECT p.Month, p.ThreeMonthAvg
FROM dbo.vw_Sales_TrendAnalysis p
WHERE p.ThreeMonthAvg <> (SELECT AVG(q.NetSales)
                          FROM dbo.vw_Sales_TrendAnalysis q
                          WHERE q.MonthStart BETWEEN DATEADD(MONTH, -2, p.MonthStart) AND p.MonthStart)
;


-- --------------------------------------------------------------------------------------------------------- SixMonthAvg
-- a SixMonthAvg which differs from ThreeMonthAvg; with 2 months, both windows hold the same rows.
-- no rows: the two averages agree.
SELECT Month, ThreeMonthAvg, SixMonthAvg
FROM dbo.vw_Sales_TrendAnalysis
WHERE SixMonthAvg <> ThreeMonthAvg
;

