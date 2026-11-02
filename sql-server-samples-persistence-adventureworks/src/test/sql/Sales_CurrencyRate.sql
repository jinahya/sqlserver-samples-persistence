-- -
-- #%L
-- sql-server-samples-persistence-adventureworks
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
-- Profiling queries on Sales.CurrencyRate, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_CurrencyRate.sql
--


-- ------------------------------------------------------------------------------------------------------ CurrencyRateID
-- how many rows, and the smallest and the largest CurrencyRateID; 13532 rates, 1 to 13532.
SELECT COUNT(*), MIN(CurrencyRateID), MAX(CurrencyRateID)
FROM Sales.CurrencyRate
;

-- a CurrencyRateID whose next value is missing, below the largest.
-- no rows: CurrencyRateID runs from 1 without a gap.
SELECT a.CurrencyRateID + 1 AS missing_from
FROM Sales.CurrencyRate a
WHERE a.CurrencyRateID < (SELECT MAX(CurrencyRateID) FROM Sales.CurrencyRate)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.CurrencyRate b
                  WHERE b.CurrencyRateID = a.CurrencyRateID + 1)
;


-- ---------------------------------------------------------------------------------------------------- CurrencyRateDate
-- the range of CurrencyRateDate, and how many days it covers; 2022-05-30 to 2025-05-30, 1097 days, every day of it.
SELECT MIN(CurrencyRateDate), MAX(CurrencyRateDate), COUNT(DISTINCT CurrencyRateDate) AS days
FROM Sales.CurrencyRate
;

-- a CurrencyRateDate with a time of day.
-- no rows: every CurrencyRateDate is a midnight.
SELECT CurrencyRateID, CurrencyRateDate
FROM Sales.CurrencyRate
WHERE CAST(CurrencyRateDate AS time) <> '00:00'
;

-- AK_CurrencyRate_CurrencyRateDate_FromCurrencyCode_ToCurrencyCode keeps one rate per day and pair.
-- no rows: no two rates share a day and a pair.
SELECT CurrencyRateDate, FromCurrencyCode, ToCurrencyCode, COUNT(*) AS rates
FROM Sales.CurrencyRate
GROUP BY CurrencyRateDate, FromCurrencyCode, ToCurrencyCode
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------- FromCurrencyCode
-- how many rates each FromCurrencyCode has; all 13532 are from USD.
SELECT FromCurrencyCode, COUNT(*) AS rates
FROM Sales.CurrencyRate
GROUP BY FromCurrencyCode
ORDER BY FromCurrencyCode ASC
;


-- ------------------------------------------------------------------------------------------------------ ToCurrencyCode
-- how many rates each ToCurrencyCode has, and over which days; 14 currencies, 1097 rates each over the whole range,
-- but DEM and FRF with 184, from 2022-05-30 to 2022-11-29.
SELECT ToCurrencyCode, COUNT(*) AS rates, MIN(CurrencyRateDate), MAX(CurrencyRateDate)
FROM Sales.CurrencyRate
GROUP BY ToCurrencyCode
ORDER BY ToCurrencyCode ASC
;

-- a rate from a currency to itself; 1097 rates from USD to USD, one a day, each of 1.0000.
SELECT FromCurrencyCode, ToCurrencyCode, COUNT(*) AS rates, MIN(AverageRate), MAX(AverageRate),
       MIN(EndOfDayRate), MAX(EndOfDayRate)
FROM Sales.CurrencyRate
WHERE FromCurrencyCode = ToCurrencyCode
GROUP BY FromCurrencyCode, ToCurrencyCode
;


-- --------------------------------------------------------------------------------------------------------- AverageRate
-- a rate which is not positive.
-- no rows: every AverageRate and EndOfDayRate is above zero.
SELECT CurrencyRateID, AverageRate, EndOfDayRate
FROM Sales.CurrencyRate
WHERE AverageRate <= 0
   OR EndOfDayRate <= 0
;

-- the range of AverageRate per ToCurrencyCode; GBP the lowest, .6046 to .7295, and VEB the highest, 634.25 to 1500.
SELECT ToCurrencyCode, MIN(AverageRate), MAX(AverageRate)
FROM Sales.CurrencyRate
GROUP BY ToCurrencyCode
ORDER BY ToCurrencyCode ASC
;


-- -------------------------------------------------------------------------------------------------------- EndOfDayRate
-- how far EndOfDayRate strays from AverageRate, the largest five; under 0.7 percent, all five of CAD.
SELECT TOP (5) CurrencyRateID, ToCurrencyCode, AverageRate, EndOfDayRate,
       ABS(EndOfDayRate - AverageRate) / AverageRate AS deviation
FROM Sales.CurrencyRate
ORDER BY deviation DESC, CurrencyRateID ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- a ModifiedDate which is not the CurrencyRateDate; the 1097 USD to USD rates, all of 2025-01-01 17:39:45.260.
SELECT FromCurrencyCode, ToCurrencyCode, COUNT(*) AS rates, MIN(ModifiedDate), MAX(ModifiedDate)
FROM Sales.CurrencyRate
WHERE ModifiedDate <> CurrencyRateDate
GROUP BY FromCurrencyCode, ToCurrencyCode
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many rates no SalesOrderHeader uses; 11018 of the 13532.
SELECT COUNT(*) AS rates
FROM Sales.CurrencyRate r
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderHeader h
                  WHERE h.CurrencyRateID = r.CurrencyRateID)
;

-- an order whose rate is of another day than its OrderDate.
-- no rows: every order uses the rate of the day it was placed.
SELECT h.SalesOrderID, h.OrderDate, r.CurrencyRateDate
FROM Sales.SalesOrderHeader h
         JOIN Sales.CurrencyRate r ON r.CurrencyRateID = h.CurrencyRateID
WHERE r.CurrencyRateDate <> h.OrderDate
;

