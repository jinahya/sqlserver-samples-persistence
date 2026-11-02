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
-- Profiling queries on Sales.Currency, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_Currency.sql
--


-- -------------------------------------------------------------------------------------------------------- CurrencyCode
-- how many rows, and the smallest and the largest CurrencyCode; 105 currencies, AED to ZWD.
SELECT COUNT(*), MIN(CurrencyCode), MAX(CurrencyCode)
FROM Sales.Currency
;

-- a CurrencyCode which is not three upper case letters.
-- no rows: every CurrencyCode is an ISO 4217 code.
SELECT CurrencyCode
FROM Sales.Currency
WHERE CurrencyCode COLLATE Latin1_General_BIN NOT LIKE '[A-Z][A-Z][A-Z]'
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 3 and 29 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Sales.Currency
;

-- a Name which is blank, or carries leading or trailing spaces.
-- no rows: every Name is trimmed and not blank.
SELECT CurrencyCode, '[' + Name + ']' AS Name
FROM Sales.Currency
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_Currency_Name keeps Name unique.
-- no rows: no two currencies share a Name.
SELECT Name, COUNT(*) AS currencies
FROM Sales.Currency
GROUP BY Name
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Sales.Currency
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many currencies a CurrencyRate converts from, and how many it converts to; only USD, to 14 currencies.
SELECT COUNT(DISTINCT FromCurrencyCode) AS from_currencies, COUNT(DISTINCT ToCurrencyCode) AS to_currencies
FROM Sales.CurrencyRate
;

-- how many currencies no CurrencyRate mentions, on either side; 91 of the 105.
SELECT COUNT(*) AS currencies
FROM Sales.Currency c
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.CurrencyRate r
                  WHERE c.CurrencyCode IN (r.FromCurrencyCode, r.ToCurrencyCode))
;

