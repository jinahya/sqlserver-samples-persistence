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
-- Exercises at the Basic level on the Purchasing schema, a problem per query.
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


-- -------------------------------------------------------------------------------------------------- AW_PURCHASING_B_02
-- The purchasing manager checks the pipeline. Show, for each status of the purchase orders, how many orders there are
-- and what they come to: 1 Pending, 2 Approved, 3 Rejected, 4 Complete.
SELECT h.Status,
       CASE h.Status WHEN 1 THEN 'Pending' WHEN 2 THEN 'Approved' WHEN 3 THEN 'Rejected' WHEN 4 THEN 'Complete' END AS StatusName,
       COUNT(*)        AS Orders,
       SUM(h.TotalDue) AS TotalDue
FROM Purchasing.PurchaseOrderHeader h
GROUP BY h.Status
ORDER BY h.Status
;
GO

