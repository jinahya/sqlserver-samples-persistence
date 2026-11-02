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
-- Profiling queries on dbo.Promotion, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_Promotion.sql
--


-- --------------------------------------------------------------------------------------------------------- PromotionID
-- how many rows, and the smallest and the largest PromotionID; 4 promotions, 1 to 4.
SELECT COUNT(*), MIN(PromotionID), MAX(PromotionID)
FROM dbo.Promotion
;


-- ------------------------------------------------------------------------------------------------------- PromotionCode
-- the codes, in the order of PromotionID; FALL2024, BULK10, BLACKFRI and HOLIDAY24.
SELECT PromotionID, PromotionCode
FROM dbo.Promotion
ORDER BY PromotionID ASC
;

-- the shortest and the longest PromotionCode; 6 and 9 characters, of the 50 the column allows.
SELECT MIN(LEN(PromotionCode)), MAX(LEN(PromotionCode))
FROM dbo.Promotion
;

-- a PromotionCode which is blank, or carries leading or trailing spaces.
-- no rows: every PromotionCode is trimmed and not blank.
SELECT PromotionID, '[' + PromotionCode + ']' AS PromotionCode
FROM dbo.Promotion
WHERE TRIM(PromotionCode) = ''
   OR DATALENGTH(PromotionCode) <> DATALENGTH(TRIM(PromotionCode))
;

-- a unique constraint keeps PromotionCode unique.
-- no rows: every PromotionCode is distinct.
SELECT PromotionCode, COUNT(*) AS promotions
FROM dbo.Promotion
GROUP BY PromotionCode
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------- PromotionName
-- the shortest and the longest PromotionName; 19 and 23 characters, of the 255 the column allows.
SELECT MIN(LEN(PromotionName)), MAX(LEN(PromotionName))
FROM dbo.Promotion
;

-- a PromotionName which is blank, or carries leading or trailing spaces.
-- no rows: every PromotionName is trimmed and not blank.
SELECT PromotionID, '[' + PromotionName + ']' AS PromotionName
FROM dbo.Promotion
WHERE TRIM(PromotionName) = ''
   OR DATALENGTH(PromotionName) <> DATALENGTH(TRIM(PromotionName))
;

-- nothing keeps PromotionName unique, yet no two rows share one.
-- no rows: every PromotionName is distinct.
SELECT PromotionName, COUNT(*) AS promotions
FROM dbo.Promotion
GROUP BY PromotionName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- Description
-- how many Description values are NULL, and the shortest and the longest of the rest; none, 22 and 29
-- characters, in an nvarchar(max).
SELECT SUM(CASE WHEN Description IS NULL THEN 1 ELSE 0 END), MIN(LEN(Description)), MAX(LEN(Description))
FROM dbo.Promotion
;


-- ----------------------------------------------------------------------------------------------------- DiscountPercent
-- how many DiscountPercent values are NULL, and the smallest and the largest; none, 10.00 and 25.00.
SELECT SUM(CASE WHEN DiscountPercent IS NULL THEN 1 ELSE 0 END), MIN(DiscountPercent), MAX(DiscountPercent)
FROM dbo.Promotion
;


-- ------------------------------------------------------------------------------------------------------ DiscountAmount
-- how many DiscountAmount values are NULL, of all the rows; 4 of 4, every promotion is a percent off.
SELECT SUM(CASE WHEN DiscountAmount IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.Promotion
;

-- a promotion with both a percent and an amount off, or with neither.
-- no rows: each gives exactly one of the two.
SELECT PromotionID, DiscountPercent, DiscountAmount
FROM dbo.Promotion
WHERE (DiscountPercent IS NULL AND DiscountAmount IS NULL)
   OR (DiscountPercent IS NOT NULL AND DiscountAmount IS NOT NULL)
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- the earliest and the latest StartDate; 2024-01-01 and 2024-12-01.
SELECT MIN(StartDate), MAX(StartDate)
FROM dbo.Promotion
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- a promotion which ends before it starts.
-- no rows: every EndDate is on or after the StartDate.
SELECT PromotionID, StartDate, EndDate
FROM dbo.Promotion
WHERE EndDate < StartDate
;

-- the days each promotion runs, both ends counted; FALL2024 77, BULK10 366, the whole of 2024, BLACKFRI 1 and
-- HOLIDAY24 31.
SELECT PromotionID, PromotionCode, DATEDIFF(DAY, StartDate, EndDate) + 1 AS days
FROM dbo.Promotion
ORDER BY PromotionID ASC
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes; all 4 are active, though every one ended in 2024.
SELECT IsActive, COUNT(*) AS promotions
FROM dbo.Promotion
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- ----------------------------------------------------------------------------------------------------- MinimumPurchase
-- how many MinimumPurchase values are NULL, and the smallest and the largest; none, .00 and 5000.00.
SELECT SUM(CASE WHEN MinimumPurchase IS NULL THEN 1 ELSE 0 END), MIN(MinimumPurchase), MAX(MinimumPurchase)
FROM dbo.Promotion
;


-- -------------------------------------------------------------------------------------------------- ApplicableChannels
-- the channel lists, comma-separated ChannelCodes; RETAIL,ONLINE for 3 and WHOLESALE for 1.
SELECT ApplicableChannels, COUNT(*) AS promotions
FROM dbo.Promotion
GROUP BY ApplicableChannels
ORDER BY ApplicableChannels ASC
;

-- a channel named in ApplicableChannels which is not a ChannelCode; the list is plain text, not a key.
-- no rows: every name is a code of SalesChannel.
SELECT p.PromotionID, s.value AS channel
FROM dbo.Promotion p
         CROSS APPLY STRING_SPLIT(p.ApplicableChannels, ',') s
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.SalesChannel c
                  WHERE c.ChannelCode = TRIM(s.value))
;


-- ---------------------------------------------------------------------------------------------------------------------
-- nothing ties a sales order to a promotion; the sales orders placed while each ran; all 15 under FALL2024 and
-- BULK10, none under BLACKFRI or HOLIDAY24.
SELECT p.PromotionID, p.PromotionCode, COUNT(o.SalesOrderID) AS orders
FROM dbo.Promotion p
         LEFT JOIN dbo.SalesOrder o ON o.OrderDate BETWEEN p.StartDate AND p.EndDate
GROUP BY p.PromotionID, p.PromotionCode
ORDER BY p.PromotionID ASC
;

