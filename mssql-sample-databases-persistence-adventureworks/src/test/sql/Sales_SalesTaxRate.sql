-- -
-- #%L
-- mssql-sample-databases-persistence-adventureworks
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
-- Profiling queries on Sales.SalesTaxRate, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SalesTaxRate.sql
--


-- ------------------------------------------------------------------------------------------------------ SalesTaxRateID
-- how many rows, and the smallest and the largest SalesTaxRateID; 29 rates, 1 to 31.
SELECT COUNT(*), MIN(SalesTaxRateID), MAX(SalesTaxRateID)
FROM Sales.SalesTaxRate
;

-- a SalesTaxRateID whose next value is missing, below the largest; 14, with 15 missing too.
SELECT a.SalesTaxRateID + 1 AS missing_from
FROM Sales.SalesTaxRate a
WHERE a.SalesTaxRateID < (SELECT MAX(SalesTaxRateID) FROM Sales.SalesTaxRate)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesTaxRate b
                  WHERE b.SalesTaxRateID = a.SalesTaxRateID + 1)
;


-- ----------------------------------------------------------------------------------------------------- StateProvinceID
-- how many rates and states or provinces each country has; 26 states and provinces, CA 15 rates in 12, US 10 in
-- 10, and AU, DE, FR and GB 1 each.
SELECT s.CountryRegionCode, COUNT(*) AS rates, COUNT(DISTINCT t.StateProvinceID) AS states
FROM Sales.SalesTaxRate t
         JOIN Person.StateProvince s ON s.StateProvinceID = t.StateProvinceID
GROUP BY s.CountryRegionCode
ORDER BY s.CountryRegionCode ASC
;

-- AK_SalesTaxRate_StateProvinceID_TaxType keeps one rate per state and TaxType.
-- no rows: no two rates share a state and a TaxType.
SELECT StateProvinceID, TaxType, COUNT(*) AS rates
FROM Sales.SalesTaxRate
GROUP BY StateProvinceID, TaxType
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------- TaxType
-- how many rates each TaxType has, of the 1 to 3 CK_SalesTaxRate_TaxType allows, and their range; 1 for 13, 5 to
-- 14.25, 2 for 3, 7 each, and 3 for 13, 7 to 19.6.
SELECT TaxType, COUNT(*) AS rates, MIN(TaxRate), MAX(TaxRate)
FROM Sales.SalesTaxRate
GROUP BY TaxType
ORDER BY TaxType ASC
;


-- ------------------------------------------------------------------------------------------------------------- TaxRate
-- the range of TaxRate, a percentage; 5 (Utah) to 19.6 (France).
SELECT MIN(TaxRate), MAX(TaxRate)
FROM Sales.SalesTaxRate
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 12 and 37 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Sales.SalesTaxRate
;

-- a Name which is blank, or carries leading or trailing spaces.
-- no rows: every Name is trimmed and not blank.
SELECT SalesTaxRateID, '[' + Name + ']' AS Name
FROM Sales.SalesTaxRate
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- the schema has no unique key on Name; one more than one rate has. Canadian GST, 12 times.
SELECT Name, COUNT(*) AS rates
FROM Sales.SalesTaxRate
GROUP BY Name
HAVING COUNT(*) > 1
;

-- a US state sales tax whose Name does not start with the state's; 24, 'Tennessese State Sales Tax', misspelled.
SELECT t.SalesTaxRateID, t.Name, s.Name
FROM Sales.SalesTaxRate t
         JOIN Person.StateProvince s ON s.StateProvinceID = t.StateProvinceID
WHERE s.CountryRegionCode = 'US'
  AND t.Name NOT LIKE s.Name + ' %'
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_SalesTaxRate_rowguid keeps rowguid unique.
-- no rows: no two rates share a rowguid.
SELECT rowguid, COUNT(*) AS rates
FROM Sales.SalesTaxRate
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Sales.SalesTaxRate
;

