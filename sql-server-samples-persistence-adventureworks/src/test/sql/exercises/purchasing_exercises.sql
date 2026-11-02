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
---
--
-- Exercises on the Purchasing schema, a problem per query, from Basic to Advanced.
--
-- These are the SQL solutions of the 'Purchasing' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- -------------------------------------------------------------------------------------------------- AW_PURCHASING_B_01
-- A buyer orders a part. Show the vendors who supply a product, the cheapest first.
DECLARE
@productId int = 322;
SELECT v.Name, pv.StandardPrice, pv.AverageLeadTime, pv.MinOrderQty
FROM Purchasing.ProductVendor pv
         JOIN Purchasing.Vendor v ON v.BusinessEntityID = pv.BusinessEntityID
WHERE pv.ProductID = @productId
ORDER BY pv.StandardPrice, v.Name
;
GO

-- -------------------------------------------------------------------------------------------------- AW_PURCHASING_I_01
-- The purchasing manager prepares the vendor reviews. Show the five vendors the company spent the most with, and how
-- many purchase orders each one had.
SELECT TOP(5) v.AccountNumber, v.Name, COUNT(*) AS Orders, SUM(h.TotalDue) AS TotalDue
FROM Purchasing.PurchaseOrderHeader h
         JOIN Purchasing.Vendor v ON v.BusinessEntityID = h.VendorID
GROUP BY v.BusinessEntityID, v.AccountNumber, v.Name
ORDER BY TotalDue DESC
;
GO

-- -------------------------------------------------------------------------------------------------- AW_PURCHASING_I_02
-- Quality assurance looks for unreliable vendors. Show the five vendors whose received goods were rejected the most, by
-- the share of the quantity received.
SELECT TOP(5) v.Name, SUM(d.ReceivedQty) AS ReceivedQty,
       SUM(d.RejectedQty)                                                   AS RejectedQty,
       CAST(SUM(d.RejectedQty) * 100 / SUM(d.ReceivedQty) AS decimal(5, 2)) AS RejectedPercent
FROM Purchasing.PurchaseOrderDetail d
         JOIN Purchasing.PurchaseOrderHeader h ON h.PurchaseOrderID = d.PurchaseOrderID
         JOIN Purchasing.Vendor v ON v.BusinessEntityID = h.VendorID
GROUP BY v.BusinessEntityID, v.Name
ORDER BY SUM(d.RejectedQty) / SUM(d.ReceivedQty) DESC, v.Name
;
GO
