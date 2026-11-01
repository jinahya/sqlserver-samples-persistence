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
---
--
-- Exercises on the Production schema, a problem per query, from Basic to Advanced.
--
-- These are the SQL solutions of the 'Production' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- -------------------------------------------------------------------------------------------------- AW-PRODUCTION-B-01
-- The catalog manager reviews the range. Show, for each category, how many products are on sale, and their lowest and
-- highest list prices.
SELECT c.Name AS Category, COUNT(*) AS Products, MIN(p.ListPrice) AS MinListPrice, MAX(p.ListPrice) AS MaxListPrice
FROM Production.Product p
         JOIN Production.ProductSubcategory s ON s.ProductSubcategoryID = p.ProductSubcategoryID
         JOIN Production.ProductCategory c ON c.ProductCategoryID = s.ProductCategoryID
WHERE p.SellEndDate IS NULL
GROUP BY c.ProductCategoryID, c.Name
ORDER BY c.Name
;
GO

-- -------------------------------------------------------------------------------------------------- AW-PRODUCTION-I-02
-- The warehouse refills the shelves. Show the products whose stock, over every location, is less than half their safety
-- stock level, the lowest stock first.
SELECT p.ProductID, p.Name, p.SafetyStockLevel, SUM(i.Quantity) AS Quantity
FROM Production.ProductInventory i
         JOIN Production.Product p ON p.ProductID = i.ProductID
GROUP BY p.ProductID, p.Name, p.SafetyStockLevel
HAVING SUM(i.Quantity) * 2 < p.SafetyStockLevel
ORDER BY Quantity, p.ProductID
;
GO

-- -------------------------------------------------------------------------------------------------- AW-PRODUCTION-I-03
-- The plant manager looks for the costliest failures. Show the five scrap reasons with the most scrapped quantity, and
-- how many work orders each one stopped.
SELECT TOP (5) r.Name, COUNT(*) AS WorkOrders, SUM(w.ScrappedQty) AS ScrappedQty
FROM Production.WorkOrder w
         JOIN Production.ScrapReason r ON r.ScrapReasonID = w.ScrapReasonID
GROUP BY r.ScrapReasonID, r.Name
ORDER BY ScrappedQty DESC
;
GO

-- -------------------------------------------------------------------------------------------------- AW-PRODUCTION-A-04
-- Production plans an assembly. Show every part of a product, down to the raw materials, from its current bill of
-- materials, with the quantity each assembly takes and the level it is at.
DECLARE @productId int = 818;
WITH Parts (AssemblyID, ComponentID, PerAssemblyQty, BomLevel) AS (
    SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty, 1
    FROM Production.BillOfMaterials b
    WHERE b.ProductAssemblyID = @productId
      AND b.EndDate IS NULL
    UNION ALL
    SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty, p.BomLevel + 1
    FROM Production.BillOfMaterials b
             JOIN Parts p ON p.ComponentID = b.ProductAssemblyID
    WHERE b.EndDate IS NULL
)
SELECT p.AssemblyID, p.ComponentID, c.Name, p.PerAssemblyQty, p.BomLevel
FROM Parts p
         JOIN Production.Product c ON c.ProductID = p.ComponentID
ORDER BY p.BomLevel, p.AssemblyID, p.ComponentID
;
GO

-- -------------------------------------------------------------------------------------------------- AW-PRODUCTION-A-05
-- Document control audits a folder. Show the documents under a folder of the document tree, with how many products
-- each one is attached to.
DECLARE @folder hierarchyid = hierarchyid::Parse('/3/');
SELECT d.DocumentNode.ToString() AS Node, d.Title, COUNT(pd.ProductID) AS Products
FROM Production.Document d
         LEFT JOIN Production.ProductDocument pd ON pd.DocumentNode = d.DocumentNode
WHERE d.DocumentNode.IsDescendantOf(@folder) = 1
  AND d.FolderFlag = 0
GROUP BY d.DocumentNode, d.Title
ORDER BY d.DocumentNode
;
GO
