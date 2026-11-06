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
-- Exercises at the advanced level on the Purchasing schema, a problem per query.
--
-- These are the SQL solutions of the 'Purchasing' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- -------------------------------------------------------------------------------------------------- AW_PURCHASING_A_01
-- The purchasing manager watches how the spend with each vendor moves. Show, for each vendor and year, what was spent
-- on its approved and complete purchase orders, the spend of the vendor's previous year with orders, and the change.
WITH Yearly (VendorID, OrderYear, TotalDue) AS (SELECT h.VendorID, YEAR(h.OrderDate), SUM(h.TotalDue)
                                                FROM Purchasing.PurchaseOrderHeader h
                                                WHERE h.Status IN (2, 4)
                                                GROUP BY h.VendorID, YEAR(h.OrderDate))
SELECT v.Name,
       y.OrderYear,
       y.TotalDue,
       LAG(y.TotalDue) OVER (PARTITION BY y.VendorID ORDER BY y.OrderYear)              AS PreviousTotalDue,
       y.TotalDue - LAG(y.TotalDue) OVER (PARTITION BY y.VendorID ORDER BY y.OrderYear) AS Change
FROM Yearly y
         JOIN Purchasing.Vendor v ON v.BusinessEntityID = y.VendorID
ORDER BY v.Name, y.VendorID, y.OrderYear
;
GO
