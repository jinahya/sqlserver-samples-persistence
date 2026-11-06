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
-- Exercises at the Basic level on the Production schema, a problem per query.
--
-- These are the SQL solutions of the 'Production' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- -------------------------------------------------------------------------------------------------- AW_PRODUCTION_B_01
-- The catalog manager reviews the range. Show, for each category, how many products are on sale, and their lowest and
-- highest list prices.
SELECT c.Name           AS Category,
       COUNT(*)         AS Products,
       MIN(p.ListPrice) AS MinListPrice,
       MAX(p.ListPrice) AS MaxListPrice
FROM Production.Product p
         JOIN Production.ProductSubcategory s ON s.ProductSubcategoryID = p.ProductSubcategoryID
         JOIN Production.ProductCategory c ON c.ProductCategoryID = s.ProductCategoryID
WHERE p.SellEndDate IS NULL
GROUP BY c.ProductCategoryID, c.Name
ORDER BY c.Name
;
GO


-- -------------------------------------------------------------------------------------------------- AW_PRODUCTION_B_02
-- A customer disputes an old invoice. Show the list price a product had on a given date; a price runs from its
-- `StartDate` through its `EndDate`, or with no end.
DECLARE @productId int = 707;
DECLARE @onDate date = '2024-01-01';
SELECT h.ProductID, h.ListPrice, CAST(h.StartDate AS date) AS StartDate, CAST(h.EndDate AS date) AS EndDate
FROM Production.ProductListPriceHistory h
WHERE h.ProductID = @productId
  AND h.StartDate <= @onDate
  AND (h.EndDate IS NULL OR h.EndDate >= @onDate)
;
GO

