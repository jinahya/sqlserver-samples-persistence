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
-- Exercises at the Intermediate level on the Production schema, a problem per query.
--
-- These are the SQL solutions of the 'Production' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- -------------------------------------------------------------------------------------------------- AW_PRODUCTION_I_01
-- The warehouse refills the shelves. Show the products whose stock, over every location, is less than half their safety
-- stock level, a product with no inventory row counting as 0, the lowest stock first; `ProductID` breaks a tie.
SELECT p.ProductID, p.Name, p.SafetyStockLevel, COALESCE(SUM(i.Quantity), 0) AS Quantity
FROM Production.Product p
         LEFT JOIN Production.ProductInventory i ON i.ProductID = p.ProductID
GROUP BY p.ProductID, p.Name, p.SafetyStockLevel
HAVING COALESCE(SUM(i.Quantity), 0) * 2 < p.SafetyStockLevel
ORDER BY Quantity, p.ProductID
;
GO

-- -------------------------------------------------------------------------------------------------- AW_PRODUCTION_I_02
-- The plant manager looks for the costliest failures. Show the five scrap reasons with the most scrapped quantity, and
-- how many work orders scrapped parts for each one; `ScrapReasonID` breaks a tie.
SELECT TOP (5) r.Name, COUNT(*) AS WorkOrders, SUM(w.ScrappedQty) AS ScrappedQty
FROM Production.WorkOrder w
         JOIN Production.ScrapReason r ON r.ScrapReasonID = w.ScrapReasonID
GROUP BY r.ScrapReasonID, r.Name
ORDER BY ScrappedQty DESC, r.ScrapReasonID
;
GO


-- -------------------------------------------------------------------------------------------------- AW_PRODUCTION_I_03
-- The plant manager looks for late production. Show the ten products with the most work orders finished after their due
-- date, and by how many days they were late on average; `ProductID` breaks a tie. `AVG` of the integer `DATEDIFF`
-- returns would truncate, so it averages a decimal.
SELECT TOP (10) p.ProductID, p.Name, COUNT(*) AS LateWorkOrders, AVG(1.0 * DATEDIFF(DAY, w.DueDate, w.EndDate)) AS AverageDaysLate
FROM Production.WorkOrder w
         JOIN Production.Product p ON p.ProductID = w.ProductID
WHERE w.EndDate > w.DueDate
GROUP BY p.ProductID, p.Name
ORDER BY LateWorkOrders DESC, p.ProductID
;
GO

