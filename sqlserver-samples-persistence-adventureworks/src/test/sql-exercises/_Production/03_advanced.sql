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
-- Exercises at the Advanced level on the Production schema, a problem per query.
--
-- These are the SQL solutions of the 'Production' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- -------------------------------------------------------------------------------------------------- AW_PRODUCTION_A_01
-- Show every part of a product from its bill of materials effective now, down to the raw materials, with the quantity
-- its immediate assembly takes and its depth; a row is effective from its `StartDate` through its `EndDate`, or with no
-- end.
DECLARE @productId int = 818;
DECLARE @asOf datetime = GETDATE();
WITH Parts (AssemblyID, ComponentID, PerAssemblyQty, BomLevel) AS (SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty, 1
                                                                   FROM Production.BillOfMaterials b
                                                                   WHERE b.ProductAssemblyID = @productId
                                                                     AND b.StartDate <= @asOf
                                                                     AND (b.EndDate IS NULL OR b.EndDate >= @asOf)
                                                                   UNION ALL
                                                                   SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty, p.BomLevel + 1
                                                                   FROM Production.BillOfMaterials b
                                                                            JOIN Parts p ON p.ComponentID = b.ProductAssemblyID
                                                                   WHERE b.StartDate <= @asOf
                                                                     AND (b.EndDate IS NULL OR b.EndDate >= @asOf))
SELECT p.AssemblyID, p.ComponentID, c.Name, p.PerAssemblyQty, p.BomLevel
FROM Parts p
         JOIN Production.Product c ON c.ProductID = p.ComponentID
ORDER BY p.BomLevel, p.AssemblyID, p.ComponentID
;
GO

-- -------------------------------------------------------------------------------------------------- AW_PRODUCTION_A_02
-- Document control audits a folder. Show the documents under a folder of the document tree, with how many products
-- each one is attached to.
DECLARE
@folder hierarchyid = hierarchyid::Parse('/3/');
SELECT d.DocumentNode.ToString() AS Node, d.Title, COUNT(pd.ProductID) AS Products
FROM Production.Document d
         LEFT JOIN Production.ProductDocument pd ON pd.DocumentNode = d.DocumentNode
WHERE d.DocumentNode.IsDescendantOf(@folder) = 1
  AND d.FolderFlag = 0
GROUP BY d.DocumentNode, d.Title
ORDER BY d.DocumentNode
;
GO

