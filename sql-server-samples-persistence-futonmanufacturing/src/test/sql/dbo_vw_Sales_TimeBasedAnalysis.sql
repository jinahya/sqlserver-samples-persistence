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
-- Profiling queries on the view dbo.vw_Sales_TimeBasedAnalysis, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_TimeBasedAnalysis.sql
--


-- ----------------------------------------------------------------------------------------------------------- DayOfWeek
-- how many rows, and how many days of the week; 13 rows over all 7 days.
SELECT COUNT(*), COUNT(DISTINCT DayOfWeek)
FROM dbo.vw_Sales_TimeBasedAnalysis
;

-- the grain of the view; the day, the hour and the channel.
-- no rows: no two rows share them.
SELECT DayOfWeek, HourOfDay, ChannelName, COUNT(*) AS rows
FROM dbo.vw_Sales_TimeBasedAnalysis
GROUP BY DayOfWeek, HourOfDay, ChannelName
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- DayNumber
-- a DayNumber which is not the number of its DayOfWeek. Both follow the session's language and DATEFIRST; under
-- us_english, the default of sqlcmd, Sunday is 1.
-- no rows: the name and the number go together, Sunday 1 to Saturday 7.
SELECT DayOfWeek, DayNumber
FROM dbo.vw_Sales_TimeBasedAnalysis
WHERE DayNumber <> CASE DayOfWeek
                       WHEN 'Sunday' THEN 1
                       WHEN 'Monday' THEN 2
                       WHEN 'Tuesday' THEN 3
                       WHEN 'Wednesday' THEN 4
                       WHEN 'Thursday' THEN 5
                       WHEN 'Friday' THEN 6
                       WHEN 'Saturday' THEN 7
                       END
;


-- ----------------------------------------------------------------------------------------------------------- HourOfDay
-- how many distinct HourOfDay, against the distinct CreatedDate of the orders; 1 and 1, a finding: the hour comes
-- from CreatedDate, which every order took at the moment the database was installed, not at the time of the order.
-- Which hour it is depends on the installation.
SELECT COUNT(DISTINCT HourOfDay), (SELECT COUNT(DISTINCT CreatedDate) FROM dbo.SalesOrder)
FROM dbo.vw_Sales_TimeBasedAnalysis
;


-- ----------------------------------------------------------------------------------------------------------- TimeOfDay
-- a TimeOfDay which is not the band of its HourOfDay.
-- no rows: every band follows the CASE.
SELECT DayOfWeek, HourOfDay, TimeOfDay
FROM dbo.vw_Sales_TimeBasedAnalysis
WHERE TimeOfDay <> CASE
                       WHEN HourOfDay BETWEEN 6 AND 11 THEN 'Morning (6-11)'
                       WHEN HourOfDay BETWEEN 12 AND 17 THEN 'Afternoon (12-17)'
                       WHEN HourOfDay BETWEEN 18 AND 21 THEN 'Evening (18-21)'
                       ELSE 'Night (22-5)'
                       END
;


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- the rows and the orders of each channel; Online/E-Commerce 4 and 4, Retail Store 5 and 6, Wholesale 4 and 5.
SELECT ChannelName, COUNT(*) AS rows, SUM(OrderCount) AS orders
FROM dbo.vw_Sales_TimeBasedAnalysis
GROUP BY ChannelName
ORDER BY ChannelName ASC
;


-- ---------------------------------------------------------------------------------------------------------- OrderCount
-- the orders the rows add up to, against the orders; 15 and 15.
SELECT SUM(OrderCount), (SELECT COUNT(*) FROM dbo.SalesOrder)
FROM dbo.vw_Sales_TimeBasedAnalysis
;

-- the smallest and the largest OrderCount; 1 and 2.
SELECT MIN(OrderCount), MAX(OrderCount)
FROM dbo.vw_Sales_TimeBasedAnalysis
;


-- -------------------------------------------------------------------------------------------------------- TotalRevenue
-- the revenue the rows add up to, against the NetAmount of the orders; 53911.82 each.
SELECT SUM(TotalRevenue), (SELECT SUM(NetAmount) FROM dbo.SalesOrder)
FROM dbo.vw_Sales_TimeBasedAnalysis
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not TotalRevenue over OrderCount.
-- no rows: the average follows the total.
SELECT DayOfWeek, ChannelName, AvgOrderValue
FROM dbo.vw_Sales_TimeBasedAnalysis
WHERE ABS(AvgOrderValue - TotalRevenue / OrderCount) > 0.000001
;


-- ----------------------------------------------------------------------------------------------------- UniqueCustomers
-- a row with more customers than orders.
-- no rows: UniqueCustomers is 1 or 2, never above OrderCount.
SELECT DayOfWeek, ChannelName, OrderCount, UniqueCustomers
FROM dbo.vw_Sales_TimeBasedAnalysis
WHERE UniqueCustomers > OrderCount
;

-- the rows with fewer customers than orders; 1: Thursday Wholesale, 2 orders of one customer.
SELECT DayOfWeek, ChannelName, OrderCount, UniqueCustomers
FROM dbo.vw_Sales_TimeBasedAnalysis
WHERE UniqueCustomers < OrderCount
;

