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
-- Profiling queries on dbo.SalesQuoteDetail, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_SalesQuoteDetail.sql
--


-- ------------------------------------------------------------------------------------------------------- QuoteDetailID
-- how many rows, and the smallest and the largest QuoteDetailID; 4 lines, 1 to 4.
SELECT COUNT(*), MIN(QuoteDetailID), MAX(QuoteDetailID)
FROM dbo.SalesQuoteDetail
;


-- ------------------------------------------------------------------------------------------------------------- QuoteID
-- the lines of each quote; 2 each.
SELECT QuoteID, COUNT(*) AS lines
FROM dbo.SalesQuoteDetail
GROUP BY QuoteID
ORDER BY QuoteID ASC
;


-- ---------------------------------------------------------------------------------------------------------- LineNumber
-- a quote whose LineNumbers do not run 1, 2, ... without a gap, or repeat one.
-- no rows: every quote numbers its lines from 1.
SELECT QuoteID, MIN(LineNumber), MAX(LineNumber), COUNT(*) AS lines, COUNT(DISTINCT LineNumber) AS numbers
FROM dbo.SalesQuoteDetail
GROUP BY QuoteID
HAVING MIN(LineNumber) <> 1
    OR MAX(LineNumber) <> COUNT(*)
    OR COUNT(DISTINCT LineNumber) <> COUNT(*)
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- the types of the items quoted; only finished goods (3), all 4 lines.
SELECT i.ItemTypeID, COUNT(*) AS lines
FROM dbo.SalesQuoteDetail d
         JOIN dbo.Items i ON i.ItemID = d.ItemID
GROUP BY i.ItemTypeID
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the smallest and the largest Quantity; 5.00 and 25.00.
SELECT MIN(Quantity), MAX(Quantity)
FROM dbo.SalesQuoteDetail
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- a UnitPrice other than the item's ListPrice.
-- no rows: every line quotes the ListPrice, though both quotes are for WHOLESALE.
SELECT d.QuoteDetailID, d.ItemID, d.UnitPrice, i.ListPrice
FROM dbo.SalesQuoteDetail d
         JOIN dbo.Items i ON i.ItemID = d.ItemID
WHERE d.UnitPrice <> i.ListPrice
;


-- ----------------------------------------------------------------------------------------------------- DiscountPercent
-- the values DiscountPercent takes, NULL for none; 10.00 for all 4.
SELECT DiscountPercent, COUNT(*) AS lines
FROM dbo.SalesQuoteDetail
GROUP BY DiscountPercent
;


-- ----------------------------------------------------------------------------------------------------------- LineTotal
-- LineTotal is computed, the Quantity at the UnitPrice less the DiscountPercent.
-- no rows: every line computes it.
SELECT QuoteDetailID, Quantity, UnitPrice, DiscountPercent, LineTotal
FROM dbo.SalesQuoteDetail
WHERE LineTotal IS NULL
   OR LineTotal <> Quantity * UnitPrice * (1 - DiscountPercent / 100)
;

-- the smallest and the largest LineTotal; 2699.955000 and 11249.775000.
SELECT MIN(LineTotal), MAX(LineTotal)
FROM dbo.SalesQuoteDetail
;

