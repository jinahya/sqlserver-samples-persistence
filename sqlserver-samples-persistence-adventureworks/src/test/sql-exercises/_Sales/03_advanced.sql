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
---
--
-- Exercises at the Advanced level on the Sales schema, a problem per query; some read the other schemas too.
--
-- These are the SQL solutions of the 'Sales' section of EXERCISES.asciidoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- ------------------------------------------------------------------------------------------------------- AW_SALES_A_01
-- The sales director compares the sales persons over the years. Show every sales person's order subtotals for the
-- fiscal years ending in 2022, 2023, 2024 and 2025, a column per year, `0` for a year without sales; Adventure Works'
-- fiscal year starts on the 1st of July. `SalesPersonID` goes through the pivot, so two sales persons of one last name
-- stay apart; the rows are ordered by the 2025 sales, the most first, then by `SalesPersonID`.
SELECT pvt.SalesPersonID,
       pvt.LastName,
       COALESCE(pvt.[2022], 0) AS [2022],
       COALESCE(pvt.[2023], 0) AS [2023],
       COALESCE(pvt.[2024], 0) AS [2024],
       COALESCE(pvt.[2025], 0) AS [2025]
FROM (SELECT sp.BusinessEntityID AS SalesPersonID, p.LastName, h.SubTotal, YEAR(DATEADD(MONTH, 6, h.OrderDate)) AS FiscalYear
      FROM Sales.SalesPerson sp
               JOIN Person.Person p ON p.BusinessEntityID = sp.BusinessEntityID
               LEFT JOIN Sales.SalesOrderHeader h ON h.SalesPersonID = sp.BusinessEntityID
          AND h.OrderDate >= '20210701'
          AND h.OrderDate < '20250701') AS s
         PIVOT (SUM(s.SubTotal) FOR s.FiscalYear IN ([2022], [2023], [2024], [2025])) AS pvt
ORDER BY COALESCE(pvt.[2025], 0) DESC, pvt.SalesPersonID
;
GO

-- ------------------------------------------------------------------------------------------------------- AW_SALES_A_02
-- The sales director ranks the sales persons of each territory group. Show every sales person with a territory, ranked
-- by this year's sales within the group of their territory.
SELECT t.[Group], t.Name AS Territory, p.LastName, sp.SalesYTD,
       RANK() OVER (PARTITION BY t.[Group] ORDER BY sp.SalesYTD DESC) AS GroupRank
FROM Sales.SalesPerson sp
         JOIN Person.Person p ON p.BusinessEntityID = sp.BusinessEntityID
         JOIN Sales.SalesTerritory t ON t.TerritoryID = sp.TerritoryID
ORDER BY t.[Group], GroupRank
;
GO

