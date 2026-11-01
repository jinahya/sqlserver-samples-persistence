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
-- Profiling queries on the view dbo.vw_Sales_QuoteConversion, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_QuoteConversion.sql
--


-- --------------------------------------------------------------------------------------------------------- QuoteNumber
-- how many rows, and how many distinct QuoteNumber, against the quotes; 2, 2 and 2, a row per quote.
SELECT COUNT(*), COUNT(DISTINCT QuoteNumber), (SELECT COUNT(*) FROM dbo.SalesQuote)
FROM dbo.vw_Sales_QuoteConversion
;


-- --------------------------------------------------------------------------------------------------------- QuoteStatus
-- the statuses and how many quotes in each; Sent 2, no quote accepted, declined or expired yet.
SELECT QuoteStatus, COUNT(*) AS quotes
FROM dbo.vw_Sales_QuoteConversion
GROUP BY QuoteStatus
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- the customers quoted; Budget Furniture Outlet and University Dorm Supplies, one quote each.
SELECT CustomerName, COUNT(*) AS quotes
FROM dbo.vw_Sales_QuoteConversion
GROUP BY CustomerName
ORDER BY CustomerName ASC
;


-- -------------------------------------------------------------------------------------------------------- CustomerType
-- the customer types; Wholesale alone.
SELECT CustomerType, COUNT(*) AS quotes
FROM dbo.vw_Sales_QuoteConversion
GROUP BY CustomerType
;


-- --------------------------------------------------------------------------------------------------------- ChannelName
-- a quote with no ChannelName; the view joins the channel with LEFT JOIN.
-- no rows: both quotes come through Wholesale.
SELECT QuoteNumber
FROM dbo.vw_Sales_QuoteConversion
WHERE ChannelName IS NULL
;


-- ------------------------------------------------------------------------------------------------------------ SalesRep
-- a SalesRep which is not the FirstName and the LastName of the quote's rep.
-- no rows: Michael Johnson and Robert Moore, each built from SalesRep.
SELECT v.QuoteNumber, v.SalesRep
FROM dbo.vw_Sales_QuoteConversion v
         JOIN dbo.SalesQuote sq ON sq.QuoteNumber = v.QuoteNumber
         JOIN dbo.SalesRep sr ON sr.SalesRepID = sq.SalesRepID
WHERE v.SalesRep <> sr.FirstName + ' ' + sr.LastName
;


-- ------------------------------------------------------------------------------------------------------- TerritoryName
-- the territories of the reps; Pacific Northwest for both.
SELECT TerritoryName, COUNT(*) AS quotes
FROM dbo.vw_Sales_QuoteConversion
GROUP BY TerritoryName
;


-- ----------------------------------------------------------------------------------------------------------- QuoteDate
-- the range of QuoteDate; 2024-11-05 to 2024-11-06.
SELECT MIN(QuoteDate), MAX(QuoteDate)
FROM dbo.vw_Sales_QuoteConversion
;


-- ------------------------------------------------------------------------------------------------------ ExpirationDate
-- a quote which expires before it is made, or has no ExpirationDate.
-- no rows: both expire after their QuoteDate, 2024-12-05 and 2024-12-06.
SELECT QuoteNumber, QuoteDate, ExpirationDate
FROM dbo.vw_Sales_QuoteConversion
WHERE ExpirationDate IS NULL
   OR ExpirationDate < QuoteDate
;


-- -------------------------------------------------------------------------------------------------------- DaysToExpire
-- a DaysToExpire which is not the days from QuoteDate to ExpirationDate.
-- no rows: both are 30.
SELECT QuoteNumber, DaysToExpire
FROM dbo.vw_Sales_QuoteConversion
WHERE DaysToExpire <> DATEDIFF(DAY, QuoteDate, ExpirationDate)
;


-- --------------------------------------------------------------------------------------------------------- QuoteAmount
-- the smallest and the largest QuoteAmount; 10799.40 and 13499.25.
SELECT MIN(QuoteAmount), MAX(QuoteAmount)
FROM dbo.vw_Sales_QuoteConversion
;


-- ---------------------------------------------------------------------------------------------------- ConversionStatus
-- the conversion statuses; Open 2, the Sent quotes falling to the ELSE of the CASE.
SELECT ConversionStatus, COUNT(*) AS quotes
FROM dbo.vw_Sales_QuoteConversion
GROUP BY ConversionStatus
;


-- ------------------------------------------------------------------------------------------------ ConvertedOrderNumber
-- a quote converted to an order.
-- no rows: no quote has a ConvertedToOrderID.
SELECT QuoteNumber, ConvertedOrderNumber
FROM dbo.vw_Sales_QuoteConversion
WHERE ConvertedOrderNumber IS NOT NULL
;


-- --------------------------------------------------------------------------------------------------------- OrderAmount
-- how many quotes have an OrderAmount; 0, with no converted order.
SELECT COUNT(OrderAmount)
FROM dbo.vw_Sales_QuoteConversion
;


-- ------------------------------------------------------------------------------------------------------- ValueVariance
-- a ValueVariance which is not QuoteAmount less OrderAmount, a missing order counting as 0.
-- no rows: with no order, each equals its QuoteAmount.
SELECT QuoteNumber, ValueVariance
FROM dbo.vw_Sales_QuoteConversion
WHERE ValueVariance <> QuoteAmount - ISNULL(OrderAmount, 0)
;


-- ------------------------------------------------------------------------------------------------------- DaysToConvert
-- how many quotes have a DaysToConvert; 0, the order date being NULL with no converted order.
SELECT COUNT(DaysToConvert)
FROM dbo.vw_Sales_QuoteConversion
;

