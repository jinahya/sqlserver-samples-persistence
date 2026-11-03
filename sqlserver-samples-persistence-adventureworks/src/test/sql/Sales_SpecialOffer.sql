-- -
-- #%L
-- sqlserver-samples-persistence-adventureworks
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
-- Profiling queries on Sales.SpecialOffer, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SpecialOffer.sql
--


-- ------------------------------------------------------------------------------------------------------ SpecialOfferID
-- how many rows, and the smallest and the largest SpecialOfferID; 16 offers, 1 to 16.
SELECT COUNT(*), MIN(SpecialOfferID), MAX(SpecialOfferID)
FROM Sales.SpecialOffer
;


-- --------------------------------------------------------------------------------------------------------- Description
-- the shortest and the longest Description; 11 and 34 characters, of the 255 the column allows.
SELECT MIN(LEN(Description)), MAX(LEN(Description))
FROM Sales.SpecialOffer
;

-- a Description which is blank, or carries leading or trailing spaces.
-- no rows: every Description is trimmed and not blank.
SELECT SpecialOfferID, '[' + Description + ']' AS Description
FROM Sales.SpecialOffer
WHERE TRIM(Description) = ''
   OR DATALENGTH(Description) <> DATALENGTH(TRIM(Description))
;

-- the schema has no unique key on Description; one more than one offer has.
-- no rows: every Description is unique.
SELECT Description, COUNT(*) AS offers
FROM Sales.SpecialOffer
GROUP BY Description
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- DiscountPct
-- how many offers each DiscountPct has, 0 or more by CK_SpecialOffer_DiscountPct; 10 values, 0 for 1 (No Discount)
-- alone, up to .50 for 10 and 15.
SELECT DiscountPct, COUNT(*) AS offers
FROM Sales.SpecialOffer
GROUP BY DiscountPct
ORDER BY DiscountPct ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Type
-- how many offers each Type has; 6 types, Volume Discount the most with 5.
SELECT Type, COUNT(*) AS offers
FROM Sales.SpecialOffer
GROUP BY Type
ORDER BY Type ASC
;


-- ------------------------------------------------------------------------------------------------------------ Category
-- how many offers each Category has; Customer 2, No Discount 1 and Reseller 13.
SELECT Category, COUNT(*) AS offers
FROM Sales.SpecialOffer
GROUP BY Category
ORDER BY Category ASC
;

-- a line of a Customer offer on an order not online, or of a Reseller offer on an online order; 2151 Reseller lines
-- online, of offers 2, 13 and 14 with no UnitPriceDiscount. The Customer offers, 10 and 15, have no line at all.
SELECT o.Category, h.OnlineOrderFlag, COUNT(*) AS lines
FROM Sales.SalesOrderDetail d
         JOIN Sales.SalesOrderHeader h ON h.SalesOrderID = d.SalesOrderID
         JOIN Sales.SpecialOffer o ON o.SpecialOfferID = d.SpecialOfferID
WHERE (o.Category = 'Customer' AND h.OnlineOrderFlag = 0)
   OR (o.Category = 'Reseller' AND h.OnlineOrderFlag = 1)
GROUP BY o.Category, h.OnlineOrderFlag
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- the range of StartDate; 2022-04-30 (1, No Discount) to 2025-03-30.
SELECT MIN(StartDate), MAX(StartDate)
FROM Sales.SpecialOffer
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- the range of EndDate, on or after StartDate by CK_SpecialOffer_EndDate, and of how long an offer runs; 2023-05-29
-- to 2025-11-29, 30 to 1309 days.
SELECT MIN(EndDate), MAX(EndDate), MIN(DATEDIFF(day, StartDate, EndDate)), MAX(DATEDIFF(day, StartDate, EndDate))
FROM Sales.SpecialOffer
;


-- -------------------------------------------------------------------------------------------------------------- MinQty
-- the MinQty and MaxQty of the offers; 0 with no MaxQty for all but the five Volume Discounts, 11 to 14, 15 to 24,
-- 25 to 40, 41 to 60, and 61 with no MaxQty.
SELECT SpecialOfferID, Type, MinQty, MaxQty
FROM Sales.SpecialOffer
WHERE MinQty <> 0
   OR MaxQty IS NOT NULL
ORDER BY SpecialOfferID ASC
;


-- -------------------------------------------------------------------------------------------------------------- MaxQty
-- a MaxQty below its MinQty.
-- no rows: every range is ordered.
SELECT SpecialOfferID, MinQty, MaxQty
FROM Sales.SpecialOffer
WHERE MaxQty < MinQty
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_SpecialOffer_rowguid keeps rowguid unique.
-- no rows: no two offers share a rowguid.
SELECT rowguid, COUNT(*) AS offers
FROM Sales.SpecialOffer
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- how many days before its StartDate an offer was modified; 30 for all 16.
SELECT DATEDIFF(day, ModifiedDate, StartDate) AS days, COUNT(*) AS offers
FROM Sales.SpecialOffer
GROUP BY DATEDIFF(day, ModifiedDate, StartDate)
ORDER BY days ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the offers with no line; 6, 10, 12 and 15.
SELECT o.SpecialOfferID, o.Description
FROM Sales.SpecialOffer o
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderDetail d
                  WHERE d.SpecialOfferID = o.SpecialOfferID)
ORDER BY o.SpecialOfferID ASC
;

