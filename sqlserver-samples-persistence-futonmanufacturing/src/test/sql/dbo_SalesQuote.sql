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
-- Profiling queries on dbo.SalesQuote, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_SalesQuote.sql
--


-- ------------------------------------------------------------------------------------------------------------- QuoteID
-- how many rows, and the smallest and the largest QuoteID; 2 quotes, 1 and 2.
SELECT COUNT(*), MIN(QuoteID), MAX(QuoteID)
FROM dbo.SalesQuote
;


-- --------------------------------------------------------------------------------------------------------- QuoteNumber
-- a QuoteNumber not in the form QT-YYYY-NNN, the year of the QuoteDate, or whose number is not the QuoteID.
-- no rows: every number is QT-2024- and the QuoteID in three digits.
SELECT QuoteID, QuoteNumber, QuoteDate
FROM dbo.SalesQuote
WHERE QuoteNumber NOT LIKE 'QT-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]'
   OR SUBSTRING(QuoteNumber, 4, 4) <> CAST(YEAR(QuoteDate) AS char(4))
   OR CAST(RIGHT(QuoteNumber, 3) AS int) <> QuoteID
;

-- the shortest and the longest QuoteNumber; 11 and 11 characters, of the 50 the column allows.
SELECT MIN(LEN(QuoteNumber)), MAX(LEN(QuoteNumber))
FROM dbo.SalesQuote
;

-- a QuoteNumber which is blank, or carries leading or trailing spaces.
-- no rows: every QuoteNumber is trimmed and not blank.
SELECT QuoteID, '[' + QuoteNumber + ']' AS QuoteNumber
FROM dbo.SalesQuote
WHERE TRIM(QuoteNumber) = ''
   OR DATALENGTH(QuoteNumber) <> DATALENGTH(TRIM(QuoteNumber))
;

-- a unique constraint keeps QuoteNumber unique.
-- no rows: every QuoteNumber is distinct.
SELECT QuoteNumber, COUNT(*) AS quotes
FROM dbo.SalesQuote
GROUP BY QuoteNumber
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- the customer of each quote; CUST-004 and CUST-006, both Wholesale.
SELECT q.QuoteID, c.CustomerCode, c.CustomerType
FROM dbo.SalesQuote q
         JOIN dbo.Customer c ON c.CustomerID = q.CustomerID
ORDER BY q.QuoteID ASC
;


-- ------------------------------------------------------------------------------------------------------ SalesChannelID
-- the quotes of each channel, NULL for none; both WHOLESALE (3).
SELECT SalesChannelID, COUNT(*) AS quotes
FROM dbo.SalesQuote
GROUP BY SalesChannelID
;


-- ---------------------------------------------------------------------------------------------------------- SalesRepID
-- a quote made by a rep other than the one the customer is assigned to.
-- no rows: every quote comes from the customer's own rep.
SELECT q.QuoteID, q.SalesRepID, c.SalesRepID AS customer_rep
FROM dbo.SalesQuote q
         JOIN dbo.Customer c ON c.CustomerID = q.CustomerID
WHERE q.SalesRepID IS NULL
   OR q.SalesRepID <> c.SalesRepID
;


-- ----------------------------------------------------------------------------------------------------------- QuoteDate
-- the earliest and the latest QuoteDate; 2024-11-05 and 2024-11-06.
SELECT MIN(QuoteDate), MAX(QuoteDate)
FROM dbo.SalesQuote
;


-- ------------------------------------------------------------------------------------------------------ ExpirationDate
-- how many ExpirationDate values are NULL, and the fewest and the most days after the QuoteDate; none, 30 and 30.
SELECT SUM(CASE WHEN ExpirationDate IS NULL THEN 1 ELSE 0 END),
       MIN(DATEDIFF(DAY, QuoteDate, ExpirationDate)), MAX(DATEDIFF(DAY, QuoteDate, ExpirationDate))
FROM dbo.SalesQuote
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- the values Status takes, the first key of IX_SalesQuote_Status; Sent for both.
SELECT Status, COUNT(*) AS quotes
FROM dbo.SalesQuote
GROUP BY Status
;


-- ------------------------------------------------------------------------------------------------------------ Subtotal
-- a quote whose Subtotal is not the sum of its lines at the UnitPrice, before the discount; both, 14999.25 against
-- 15499.70 and 11999.40 against 15999.70.
SELECT q.QuoteID, q.Subtotal, SUM(d.Quantity * d.UnitPrice) AS lines
FROM dbo.SalesQuote q
         JOIN dbo.SalesQuoteDetail d ON d.QuoteID = q.QuoteID
GROUP BY q.QuoteID, q.Subtotal
HAVING q.Subtotal <> SUM(d.Quantity * d.UnitPrice)
ORDER BY q.QuoteID ASC
;


-- ------------------------------------------------------------------------------------------------------ DiscountAmount
-- the DiscountAmount as a share of the Subtotal; about .10 for both, 1500.00 and 1200.00.
SELECT QuoteID, DiscountAmount, DiscountAmount / Subtotal AS share
FROM dbo.SalesQuote
ORDER BY QuoteID ASC
;


-- ----------------------------------------------------------------------------------------------------------- TaxAmount
-- the values TaxAmount takes; .00 for both, as for every WHOLESALE order.
SELECT TaxAmount, COUNT(*) AS quotes
FROM dbo.SalesQuote
GROUP BY TaxAmount
;


-- --------------------------------------------------------------------------------------------------------- TotalAmount
-- a quote whose TotalAmount is not the Subtotal and the TaxAmount less the DiscountAmount.
-- no rows: every TotalAmount adds up.
SELECT QuoteID, Subtotal, DiscountAmount, TaxAmount, TotalAmount
FROM dbo.SalesQuote
WHERE TotalAmount IS NULL
   OR TotalAmount <> Subtotal - DiscountAmount + TaxAmount
;

-- a quote whose TotalAmount is not the sum of the LineTotal of its lines, the discount taken off each; both,
-- 13499.25 against 13949.73 and 10799.40 against 14399.73.
SELECT q.QuoteID, q.TotalAmount, SUM(d.LineTotal) AS lines
FROM dbo.SalesQuote q
         JOIN dbo.SalesQuoteDetail d ON d.QuoteID = q.QuoteID
GROUP BY q.QuoteID, q.TotalAmount
HAVING q.TotalAmount <> SUM(d.LineTotal)
ORDER BY q.QuoteID ASC
;


-- -------------------------------------------------------------------------------------------------- ConvertedToOrderID
-- how many ConvertedToOrderID values are NULL, of all the rows; 2 of 2, neither quote became an order.
SELECT SUM(CASE WHEN ConvertedToOrderID IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.SalesQuote
;


-- --------------------------------------------------------------------------------------------------------------- Notes
-- how many Notes values are NULL, of all the rows; 2 of 2.
SELECT SUM(CASE WHEN Notes IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.SalesQuote
;


-- ----------------------------------------------------------------------------------------------------------- CreatedBy
-- how many CreatedBy values are NULL, of all the rows; 2 of 2.
SELECT SUM(CASE WHEN CreatedBy IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.SalesQuote
;


-- --------------------------------------------------------------------------------------------------------- CreatedDate
-- how many CreatedDate values are NULL, and how many fall outside the five minutes after the database was created;
-- none, and none: the default GETDATE() filled every one in while the load script ran, so it is the moment of
-- the load, not of a business event, and moves with every load.
SELECT SUM(CASE WHEN x.CreatedDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.CreatedDate NOT BETWEEN d.create_date AND DATEADD(MINUTE, 5, d.create_date) THEN 1 ELSE 0 END)
FROM dbo.SalesQuote x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;

