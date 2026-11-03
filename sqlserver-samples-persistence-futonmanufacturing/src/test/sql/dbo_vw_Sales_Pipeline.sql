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
-- Profiling queries on the view dbo.vw_Sales_Pipeline, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_Pipeline.sql
--


-- --------------------------------------------------------------------------------------------------------------- Stage
-- how many rows, and how many distinct stages; 6 rows, one per stage.
SELECT COUNT(*), COUNT(DISTINCT Stage)
FROM dbo.vw_Sales_Pipeline
;

-- the stages in their order; Quotes, Quotes - Accepted, then the four order stages, Orders - Confirmed to
-- Orders - Delivered.
SELECT StageOrder, Stage
FROM dbo.vw_Sales_Pipeline
ORDER BY StageOrder ASC
;


-- ---------------------------------------------------------------------------------------------------------- StageOrder
-- the smallest and the largest StageOrder, and how many distinct; 1 to 6, each once.
SELECT MIN(StageOrder), MAX(StageOrder), COUNT(DISTINCT StageOrder)
FROM dbo.vw_Sales_Pipeline
;


-- --------------------------------------------------------------------------------------------------------------- Count
-- a stage whose Count differs from the quotes or the orders in that status; the Quotes stage counts every quote
-- neither Expired nor Declined.
-- no rows: every Count matches its base table.
SELECT p.Stage, p.Count, b.Count AS expected
FROM dbo.vw_Sales_Pipeline p
         JOIN (SELECT 'Quotes' AS Stage, COUNT(*) AS Count
               FROM dbo.SalesQuote
               WHERE Status NOT IN ('Expired', 'Declined')
               UNION ALL
               SELECT 'Quotes - Accepted', COUNT(*)
               FROM dbo.SalesQuote
               WHERE Status = 'Accepted'
               UNION ALL
               SELECT CASE Status
                          WHEN 'Confirmed' THEN 'Orders - Confirmed'
                          WHEN 'InProduction' THEN 'Orders - In Production'
                          WHEN 'Shipped' THEN 'Orders - Shipped'
                          WHEN 'Delivered' THEN 'Orders - Delivered'
                          END, COUNT(*)
               FROM dbo.SalesOrder
               GROUP BY Status) b ON b.Stage = p.Stage
WHERE p.Count <> b.Count
;

-- the counts, stage by stage; 2 quotes, 0 accepted, then 4 confirmed, 1 in production, 1 shipped and 9 delivered
-- orders.
SELECT StageOrder, Stage, Count
FROM dbo.vw_Sales_Pipeline
ORDER BY StageOrder ASC
;


-- --------------------------------------------------------------------------------------------------------------- Value
-- a stage with no Value; SUM over no rows is NULL, so a stage with a Count of 0 has none.
-- 1 row: Quotes - Accepted, with no accepted quote.
SELECT Stage, Count, Value
FROM dbo.vw_Sales_Pipeline
WHERE Value IS NULL
;

-- the smallest and the largest Value; 1355.98 (Orders - Shipped) and 28293.01 (Orders - Delivered).
SELECT MIN(Value), MAX(Value)
FROM dbo.vw_Sales_Pipeline
;


-- ----------------------------------------------------------------------------------------------------- PriorStageCount
-- a PriorStageCount which is not the Count of the stage before; the first stage has none.
-- no rows: each stage carries the Count of the one before it.
SELECT p.Stage, p.PriorStageCount, b.Count
FROM dbo.vw_Sales_Pipeline p
         LEFT JOIN dbo.vw_Sales_Pipeline b ON b.StageOrder = p.StageOrder - 1
WHERE ISNULL(p.PriorStageCount, -1) <> ISNULL(b.Count, -1)
;


-- ----------------------------------------------------------------------------------------------------- PriorStageValue
-- a PriorStageValue which is not the Value of the stage before.
-- no rows: each stage carries the Value of the one before it.
SELECT p.Stage, p.PriorStageValue, b.Value
FROM dbo.vw_Sales_Pipeline p
         LEFT JOIN dbo.vw_Sales_Pipeline b ON b.StageOrder = p.StageOrder - 1
WHERE ISNULL(p.PriorStageValue, -1) <> ISNULL(b.Value, -1)
;

-- the stages with no PriorStageValue; 2: Quotes, the first, and Orders - Confirmed, after the empty accepted stage.
SELECT StageOrder, Stage
FROM dbo.vw_Sales_Pipeline
WHERE PriorStageValue IS NULL
ORDER BY StageOrder ASC
;


-- ------------------------------------------------------------------------------------------------------ ConversionRate
-- a ConversionRate which is not Count over PriorStageCount, in percent.
-- no rows: every rate follows its formula.
SELECT Stage, ConversionRate
FROM dbo.vw_Sales_Pipeline
WHERE ISNULL(ConversionRate, -1)
          <> ISNULL(CAST(Count * 100.0 / NULLIF(PriorStageCount, 0) AS DECIMAL(10, 2)), -1)
;

-- the rates, stage by stage; NULL, .00, NULL, 25.00, 100.00 and 900.00. The stages are not a funnel: 9 delivered
-- orders against 1 shipped make 900 percent.
SELECT StageOrder, Stage, ConversionRate
FROM dbo.vw_Sales_Pipeline
ORDER BY StageOrder ASC
;


-- -------------------------------------------------------------------------------------------------- ValueRetentionRate
-- a ValueRetentionRate which is not Value over PriorStageValue, in percent.
-- no rows: every rate follows its formula.
SELECT Stage, ValueRetentionRate
FROM dbo.vw_Sales_Pipeline
WHERE ISNULL(ValueRetentionRate, -1)
          <> ISNULL(CAST(Value * 100.0 / NULLIF(PriorStageValue, 0) AS DECIMAL(10, 2)), -1)
;

-- the smallest and the largest ValueRetentionRate; 7.15 (Orders - In Production) and 2086.54 (Orders - Delivered).
SELECT MIN(ValueRetentionRate), MAX(ValueRetentionRate)
FROM dbo.vw_Sales_Pipeline
;

