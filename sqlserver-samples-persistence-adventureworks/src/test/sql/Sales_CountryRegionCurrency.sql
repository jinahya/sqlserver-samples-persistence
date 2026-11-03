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
-- Profiling queries on Sales.CountryRegionCurrency, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_CountryRegionCurrency.sql
--


-- --------------------------------------------------------------------------------------------------- CountryRegionCode
-- how many rows, and how many countries and currencies they pair; 109 pairs of 96 countries and 97 currencies.
SELECT COUNT(*), COUNT(DISTINCT CountryRegionCode), COUNT(DISTINCT CurrencyCode)
FROM Sales.CountryRegionCurrency
;

-- the shortest and the longest CountryRegionCode; 2 characters each, of the 3 the column allows.
SELECT MIN(LEN(CountryRegionCode)), MAX(LEN(CountryRegionCode))
FROM Sales.CountryRegionCurrency
;

-- a CountryRegionCode which refers to no country; the foreign key forbids it.
-- no rows: every CountryRegionCode refers to a Person.CountryRegion.
SELECT t.CountryRegionCode, t.CurrencyCode
FROM Sales.CountryRegionCurrency t
WHERE NOT EXISTS (SELECT 1
                  FROM Person.CountryRegion p
                  WHERE p.CountryRegionCode = t.CountryRegionCode)
;

-- a country with more than one currency; 13 countries with 2 each: the eleven that keep their old currency beside EUR
-- (AT, BE, DE, ES, FI, FR, GR, IE, IT, NL, PT), PL with PLN and PLZ, and RU with RUB and RUR.
SELECT CountryRegionCode, COUNT(*) AS currencies
FROM Sales.CountryRegionCurrency
GROUP BY CountryRegionCode
HAVING COUNT(*) > 1
ORDER BY CountryRegionCode ASC
;


-- -------------------------------------------------------------------------------------------------------- CurrencyCode
-- a CurrencyCode which is not three upper case letters.
-- no rows: every CurrencyCode is an ISO 4217 code.
SELECT CountryRegionCode, CurrencyCode
FROM Sales.CountryRegionCurrency
WHERE CurrencyCode COLLATE Latin1_General_BIN NOT LIKE '[A-Z][A-Z][A-Z]'
;

-- a currency more than one country uses; EUR with 12, and USD with 2 (EC and US).
SELECT CurrencyCode, COUNT(*) AS countries
FROM Sales.CountryRegionCurrency
GROUP BY CurrencyCode
HAVING COUNT(*) > 1
ORDER BY countries DESC, CurrencyCode ASC
;

-- a CurrencyCode which refers to no currency; the foreign key forbids it.
-- no rows: every CurrencyCode refers to a Sales.Currency.
SELECT t.CountryRegionCode, t.CurrencyCode
FROM Sales.CountryRegionCurrency t
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Currency p
                  WHERE p.CurrencyCode = t.CurrencyCode)
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the moments the installed rows carry; 10 rows of 2019-04-30 00:00:00.000 and 99 of 2025-02-07 10:17:21.510.
SELECT ModifiedDate, COUNT(*) AS pairs
FROM Sales.CountryRegionCurrency
GROUP BY ModifiedDate
ORDER BY ModifiedDate ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many countries have no currency; 142 of the 238 in Person.CountryRegion.
SELECT COUNT(*) AS countries
FROM Person.CountryRegion c
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.CountryRegionCurrency x
                  WHERE x.CountryRegionCode = c.CountryRegionCode)
;

-- the currencies no country uses; 8 of the 105, AFA, ALL, AMD, ANG, AOA, AWG, AZM and XOF.
SELECT c.CurrencyCode, c.Name
FROM Sales.Currency c
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.CountryRegionCurrency x
                  WHERE x.CurrencyCode = c.CurrencyCode)
ORDER BY c.CurrencyCode ASC
;

