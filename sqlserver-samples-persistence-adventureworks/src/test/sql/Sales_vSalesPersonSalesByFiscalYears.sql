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
-- Profiling queries on the view Sales.vSalesPersonSalesByFiscalYears, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_vSalesPersonSalesByFiscalYears.sql
--


-- ------------------------------------------------------------------------------------------------------- SalesPersonID
-- how many rows, and which sales persons are missing; 14 rows, the 3 managers, who have no territory, left out.
SELECT COUNT(*)                      AS sales_persons,
       (SELECT COUNT(*)
        FROM Sales.SalesPerson s
        WHERE NOT EXISTS (SELECT 1
                          FROM Sales.vSalesPersonSalesByFiscalYears v
                          WHERE v.SalesPersonID = s.BusinessEntityID)
          AND s.TerritoryID IS NULL) AS managers_missing
FROM Sales.vSalesPersonSalesByFiscalYears
;


-- ------------------------------------------------------------------------------------------------------------ FullName
-- a FullName with two spaces in a row; 277, 'Jillian  Carson', who has no MiddleName.
SELECT SalesPersonID, '[' + FullName + ']' AS FullName
FROM Sales.vSalesPersonSalesByFiscalYears
WHERE FullName LIKE '%  %'
;


-- ------------------------------------------------------------------------------------------------------------ JobTitle
-- how many rows each JobTitle has; all 14 Sales Representative.
SELECT JobTitle, COUNT(*) AS sales_persons
FROM Sales.vSalesPersonSalesByFiscalYears
GROUP BY JobTitle
;


-- ------------------------------------------------------------------------------------------------------ SalesTerritory
-- a SalesTerritory which is not the sales person's current territory.
-- no rows: every row names the sales person's current territory.
SELECT v.SalesPersonID, v.SalesTerritory, t.Name
FROM Sales.vSalesPersonSalesByFiscalYears v
         JOIN Sales.SalesPerson s ON s.BusinessEntityID = v.SalesPersonID
         JOIN Sales.SalesTerritory t ON t.TerritoryID = s.TerritoryID
WHERE t.Name <> v.SalesTerritory
;


-- ---------------------------------------------------------------------------------------------------------------- 2002
-- the view pivots the fiscal years 2002 to 2004, but the installed orders run from 2022; how many rows have a sum
-- in each. None in any.
SELECT COUNT([2002]) AS [2002], COUNT([2003]) AS [2003], COUNT([2004]) AS [2004]
FROM Sales.vSalesPersonSalesByFiscalYears
;


-- ---------------------------------------------------------------------------------------------------------------- 2003
-- see 2002; the column is NULL for every row.
SELECT COUNT(*) - COUNT([2003]) AS without
FROM Sales.vSalesPersonSalesByFiscalYears
;


-- ---------------------------------------------------------------------------------------------------------------- 2004
-- see 2002; the column is NULL for every row.
SELECT COUNT(*) - COUNT([2004]) AS without
FROM Sales.vSalesPersonSalesByFiscalYears
;

