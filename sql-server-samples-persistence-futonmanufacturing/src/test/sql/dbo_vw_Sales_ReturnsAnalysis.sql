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
-- Profiling queries on the view dbo.vw_Sales_ReturnsAnalysis, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_ReturnsAnalysis.sql
--


-- ------------------------------------------------------------------------------------------------------------ ReturnID
-- how many rows, and how many distinct ReturnID, against the returns; 2, 2 and 2, a row per return, each of one item.
SELECT COUNT(*), COUNT(DISTINCT ReturnID), (SELECT COUNT(*) FROM dbo.SalesReturn)
FROM dbo.vw_Sales_ReturnsAnalysis
;

-- the grain of the view; the return, the item and the Disposition.
-- no rows: no two rows share them.
SELECT ReturnID, ItemCode, Disposition, COUNT(*) AS rows
FROM dbo.vw_Sales_ReturnsAnalysis
GROUP BY ReturnID, ItemCode, Disposition
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------------------- Year
-- the years; 2024 alone.
SELECT Year, COUNT(*) AS returns
FROM dbo.vw_Sales_ReturnsAnalysis
GROUP BY Year
;


-- --------------------------------------------------------------------------------------------------------------- Month
-- the months; 10 alone, both returns made in October.
SELECT Month, COUNT(*) AS returns
FROM dbo.vw_Sales_ReturnsAnalysis
GROUP BY Month
;


-- ---------------------------------------------------------------------------------------------------------- ReasonCode
-- the reasons; CHANGE 1 and DEFECT 1.
SELECT ReasonCode, COUNT(*) AS returns
FROM dbo.vw_Sales_ReturnsAnalysis
GROUP BY ReasonCode
ORDER BY ReasonCode ASC
;


-- --------------------------------------------------------------------------------------------------- ReasonDescription
-- a ReasonDescription which is not the one of its ReasonCode.
-- no rows: each matches ReturnReason.
SELECT v.ReturnID, v.ReasonCode, v.ReasonDescription
FROM dbo.vw_Sales_ReturnsAnalysis v
         JOIN dbo.ReturnReason rr ON rr.ReasonCode = v.ReasonCode
WHERE v.ReasonDescription <> rr.ReasonDescription
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- the customers who returned; Home Comfort Retailers and Luxury Living Inc, once each.
SELECT CustomerName, COUNT(*) AS returns
FROM dbo.vw_Sales_ReturnsAnalysis
GROUP BY CustomerName
ORDER BY CustomerName ASC
;


-- -------------------------------------------------------------------------------------------------------- CustomerType
-- the customer types; Retail alone.
SELECT CustomerType, COUNT(*) AS returns
FROM dbo.vw_Sales_ReturnsAnalysis
GROUP BY CustomerType
;


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- the channels of the returned orders; Retail Store alone.
SELECT ChannelName, COUNT(*) AS returns
FROM dbo.vw_Sales_ReturnsAnalysis
GROUP BY ChannelName
;


-- -------------------------------------------------------------------------------------------------------- TotalRefunds
-- the refunds the rows add up to, against the RefundAmount of the returns; 1299.98 each.
SELECT SUM(TotalRefunds), (SELECT SUM(RefundAmount) FROM dbo.SalesReturn)
FROM dbo.vw_Sales_ReturnsAnalysis
;


-- ----------------------------------------------------------------------------------------------------- AvgRefundAmount
-- an AvgRefundAmount which is not the TotalRefunds; with one detail line a return, the average is the total.
-- no rows: the two agree.
SELECT ReturnID, AvgRefundAmount, TotalRefunds
FROM dbo.vw_Sales_ReturnsAnalysis
WHERE AvgRefundAmount <> TotalRefunds
;


-- ------------------------------------------------------------------------------------------------- TotalRestockingFees
-- the restocking fees; .00 on both returns.
SELECT MIN(TotalRestockingFees), MAX(TotalRestockingFees)
FROM dbo.vw_Sales_ReturnsAnalysis
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- the items returned; FG-FUT-002 and FG-FUT-006, once each.
SELECT ItemCode, COUNT(*) AS returns
FROM dbo.vw_Sales_ReturnsAnalysis
GROUP BY ItemCode
ORDER BY ItemCode ASC
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- an ItemName which is not the one of its ItemCode.
-- no rows: each matches Items.
SELECT v.ReturnID, v.ItemCode, v.ItemName
FROM dbo.vw_Sales_ReturnsAnalysis v
         JOIN dbo.Items i ON i.ItemCode = v.ItemCode
WHERE v.ItemName <> i.ItemName
;


-- ------------------------------------------------------------------------------------------------------- UnitsReturned
-- the units returned; 1.00 on both returns.
SELECT MIN(UnitsReturned), MAX(UnitsReturned)
FROM dbo.vw_Sales_ReturnsAnalysis
;


-- --------------------------------------------------------------------------------------------------------- Disposition
-- the dispositions; Restock for the changed mind, Scrap for the defect.
SELECT ReasonCode, Disposition
FROM dbo.vw_Sales_ReturnsAnalysis
ORDER BY ReasonCode ASC
;


-- --------------------------------------------------------------------------------------------------- ReturnRatePercent
-- a ReturnRatePercent which is not 1 return over the delivered orders, in percent; each row holds one return.
-- no rows: both are 11.111111111111, 1 of 9.
SELECT ReturnID, ReturnRatePercent
FROM dbo.vw_Sales_ReturnsAnalysis
WHERE ReturnRatePercent <> 1 * 100.0 / (SELECT COUNT(*) FROM dbo.SalesOrder WHERE Status = 'Delivered')
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a return whose order was not delivered; the rate divides by the delivered orders alone.
-- no rows: both returns are of delivered orders, SO-2024-1001 and SO-2024-1003.
SELECT sr.ReturnNumber, so.OrderNumber, so.Status
FROM dbo.SalesReturn sr
         JOIN dbo.SalesOrder so ON so.SalesOrderID = sr.SalesOrderID
WHERE so.Status <> 'Delivered'
;

