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
-- Exercises on the Sales schema, a problem per query, from Basic to Advanced; some read the other schemas too.
--
-- These are the SQL solutions of the 'Sales' section of EXERCISES.asciidoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- ------------------------------------------------------------------------------------------------------- AW_SALES_B_01
-- A customer calls with an order number. Show the order, with its amounts.
DECLARE
@salesOrderNumber nvarchar(25) = N'SO43659';
SELECT h.SalesOrderID, h.SalesOrderNumber, h.SubTotal, h.TaxAmt, h.Freight, h.TotalDue
FROM Sales.SalesOrderHeader h
WHERE h.SalesOrderNumber = @salesOrderNumber
;
GO

-- ------------------------------------------------------------------------------------------------------- AW_SALES_B_02
-- Sales counts its customers. Show how many customers there are, how many of them are persons, and how many are
-- stores.
SELECT COUNT(*) AS Customers, COUNT(c.PersonID) AS WithPerson, COUNT(c.StoreID) AS WithStore
FROM Sales.Customer c
;
GO

-- ------------------------------------------------------------------------------------------------------- AW_SALES_I_01
-- The sales director compares the territories. Show, for each territory, its group, how many orders it had and how
-- much they came to, the largest first.
SELECT t.Name, t.[
Group], COUNT (*) AS Orders, SUM (h.TotalDue) AS TotalDue
FROM Sales.SalesOrderHeader h
    JOIN Sales.SalesTerritory t
ON t.TerritoryID = h.TerritoryID
GROUP BY t.TerritoryID, t.Name, t.[
Group]
ORDER BY TotalDue DESC
;
GO

-- ------------------------------------------------------------------------------------------------------- AW_SALES_I_02
-- The product manager looks for the best sellers. Show the five products that brought in the most, with the quantity
-- sold.
SELECT TOP(5) p.ProductNumber, p.Name, SUM(d.OrderQty) AS OrderQty, SUM(d.LineTotal) AS LineTotal
FROM Sales.SalesOrderDetail d
         JOIN Production.Product p ON p.ProductID = d.ProductID
GROUP BY p.ProductID, p.ProductNumber, p.Name
ORDER BY LineTotal DESC
;
GO

-- ------------------------------------------------------------------------------------------------------- AW_SALES_I_03
-- Finance weighs the promotions. Show, for each special offer that was applied, how many order lines took it and how
-- much discount it gave, the most first.
SELECT o.SpecialOfferID,
       o.Description,
       o.DiscountPct,
       COUNT(*)                                            AS Lines,
       SUM(d.UnitPrice * d.UnitPriceDiscount * d.OrderQty) AS Discount
FROM Sales.SalesOrderDetail d
         JOIN Sales.SpecialOffer o ON o.SpecialOfferID = d.SpecialOfferID
WHERE d.UnitPriceDiscount > 0
GROUP BY o.SpecialOfferID, o.Description, o.DiscountPct
ORDER BY Discount DESC
;
GO

-- ------------------------------------------------------------------------------------------------------- AW_SALES_I_04
-- The sales director names the top sellers of the year. Show the five sales persons with the most sales this year,
-- with their names and territories.
SELECT TOP(5) p.FirstName, p.LastName, t.Name AS Territory, sp.SalesYTD
FROM Sales.SalesPerson sp
         JOIN HumanResources.Employee e ON e.BusinessEntityID = sp.BusinessEntityID
         JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
         LEFT JOIN Sales.SalesTerritory t ON t.TerritoryID = sp.TerritoryID
ORDER BY sp.SalesYTD DESC
;
GO

-- ------------------------------------------------------------------------------------------------------- AW_SALES_A_01
-- The sales director compares the sales persons over the years. Show each sales person's sales, a column per fiscal
-- year; Adventure Works' fiscal year starts on the 1st of July.
SELECT pvt.LastName, pvt.[2022], pvt.[2023], pvt.[2024], pvt.[2025]
FROM (SELECT p.LastName, h.SubTotal, YEAR (DATEADD(MONTH, 6, h.OrderDate)) AS FiscalYear
      FROM Sales.SalesOrderHeader h
          JOIN Person.Person p
      ON p.BusinessEntityID = h.SalesPersonID) AS s PIVOT (SUM(s.SubTotal) FOR s.FiscalYear IN ([2022], [2023], [2024], [2025])) AS pvt
ORDER BY pvt.[2025] DESC
;
GO

-- ------------------------------------------------------------------------------------------------------- AW_SALES_A_02
-- The sales director ranks the sales persons of each territory group. Show every sales person with a territory, ranked
-- by this year's sales within the group of their territory.
SELECT t.[
Group], t.Name AS Territory, p.LastName, sp.SalesYTD,
    RANK() OVER (PARTITION BY t.[Group] ORDER BY sp.SalesYTD DESC) AS GroupRank
FROM Sales.SalesPerson sp
    JOIN Person.Person p
ON p.BusinessEntityID = sp.BusinessEntityID
    JOIN Sales.SalesTerritory t ON t.TerritoryID = sp.TerritoryID
ORDER BY t.[
Group], GroupRank
;
GO
