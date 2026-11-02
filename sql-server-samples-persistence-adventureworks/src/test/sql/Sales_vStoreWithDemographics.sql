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
-- Profiling queries on the view Sales.vStoreWithDemographics, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_vStoreWithDemographics.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows and stores; 701 of each, one row per Sales.Store.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM Sales.vStoreWithDemographics
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- a Name which is not that of Sales.Store.
-- no rows: the view copies Name.
SELECT v.BusinessEntityID, v.Name, s.Name
FROM Sales.vStoreWithDemographics v
         JOIN Sales.Store s ON s.BusinessEntityID = v.BusinessEntityID
WHERE v.Name <> s.Name
;


-- --------------------------------------------------------------------------------------------------------- AnnualSales
-- how many rows each pair of AnnualSales and AnnualRevenue has; 5 pairs, 300000 to 3000000, the revenue a tenth of
-- the sales in each.
SELECT AnnualSales, AnnualRevenue, COUNT(*) AS stores
FROM Sales.vStoreWithDemographics
GROUP BY AnnualSales, AnnualRevenue
ORDER BY AnnualSales ASC
;


-- ------------------------------------------------------------------------------------------------------- AnnualRevenue
-- an AnnualRevenue which is not a tenth of AnnualSales.
-- no rows: every revenue is a tenth.
SELECT BusinessEntityID, AnnualSales, AnnualRevenue
FROM Sales.vStoreWithDemographics
WHERE AnnualRevenue * 10 <> AnnualSales
;


-- ------------------------------------------------------------------------------------------------------------ BankName
-- how many rows each BankName has; 7 banks, 100 stores each but Guardian Bank with 101.
SELECT BankName, COUNT(*) AS stores
FROM Sales.vStoreWithDemographics
GROUP BY BankName
ORDER BY BankName ASC
;


-- -------------------------------------------------------------------------------------------------------- BusinessType
-- how many rows each BusinessType has; BM 238, BS 231 and OS 232.
SELECT BusinessType, COUNT(*) AS stores
FROM Sales.vStoreWithDemographics
GROUP BY BusinessType
ORDER BY BusinessType ASC
;


-- ---------------------------------------------------------------------------------------------------------- YearOpened
-- how many rows have no YearOpened, and the range of the others; none; 1970 to 2001.
SELECT COUNT(*) - COUNT(YearOpened) AS without, MIN(YearOpened), MAX(YearOpened)
FROM Sales.vStoreWithDemographics
;


-- ----------------------------------------------------------------------------------------------------------- Specialty
-- how many rows each Specialty has; Mountain 246, Road 300 and Touring 155.
SELECT Specialty, COUNT(*) AS stores
FROM Sales.vStoreWithDemographics
GROUP BY Specialty
ORDER BY Specialty ASC
;


-- ---------------------------------------------------------------------------------------------------------- SquareFeet
-- how many rows have no SquareFeet, and the range of the others; none; 6000 to 80000.
SELECT COUNT(*) - COUNT(SquareFeet) AS without, MIN(SquareFeet), MAX(SquareFeet)
FROM Sales.vStoreWithDemographics
;


-- -------------------------------------------------------------------------------------------------------------- Brands
-- how many rows each Brands has; 2 for 143, 3 for 198, 4+ for 314 and AW for 46.
SELECT Brands, COUNT(*) AS stores
FROM Sales.vStoreWithDemographics
GROUP BY Brands
ORDER BY Brands ASC
;


-- ------------------------------------------------------------------------------------------------------------ Internet
-- how many rows each Internet has; DSL 378, ISDN 49, T1 196, T2 70 and T3 8.
SELECT Internet, COUNT(*) AS stores
FROM Sales.vStoreWithDemographics
GROUP BY Internet
ORDER BY Internet ASC
;


-- ----------------------------------------------------------------------------------------------------- NumberEmployees
-- how many rows have no NumberEmployees, and the range of the others; none; 2 to 100.
SELECT COUNT(*) - COUNT(NumberEmployees) AS without, MIN(NumberEmployees), MAX(NumberEmployees)
FROM Sales.vStoreWithDemographics
;

