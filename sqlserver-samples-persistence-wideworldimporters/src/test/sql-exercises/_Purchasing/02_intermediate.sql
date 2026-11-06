-- #%L
-- sqlserver-samples-persistence-wideworldimporters
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
-- Exercises at the Intermediate level on the Purchasing schema of WideWorldImporters.
--
-- These are the SQL solutions of the 'Purchasing' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- ------------------------------------------------------------------------------------------------- WWI_PURCHASING_I_01
-- The buyer reviews a year of purchasing: for each supplier, the purchase orders placed in the year and the amount
-- ordered at the expected prices, the largest amount first.
DECLARE @from date = '2015-01-01', @to date = '2016-01-01';
SELECT s.SupplierName,
       COUNT(DISTINCT po.PurchaseOrderID)                     AS Orders,
       SUM(pol.OrderedOuters * pol.ExpectedUnitPricePerOuter) AS Amount
FROM Purchasing.PurchaseOrders po
         JOIN Purchasing.PurchaseOrderLines pol ON pol.PurchaseOrderID = po.PurchaseOrderID
         JOIN Purchasing.Suppliers s ON s.SupplierID = po.SupplierID
WHERE po.OrderDate >= @from
  AND po.OrderDate < @to
GROUP BY s.SupplierID, s.SupplierName
ORDER BY Amount DESC, s.SupplierID
;

-- ------------------------------------------------------------------------------------------------- WWI_PURCHASING_I_02
-- WWI_PURCHASING_I_01 sums the lines of each order at their expected prices, which assumes every order has a line and
-- every line an expected price; the schema enforces neither. Count the purchase orders with no line, and the lines with
-- no expected price; the check passes when both are 0.
SELECT (SELECT COUNT(*)
        FROM Purchasing.PurchaseOrders po
        WHERE NOT EXISTS (SELECT 1 FROM Purchasing.PurchaseOrderLines pol WHERE pol.PurchaseOrderID = po.PurchaseOrderID)) AS OrdersWithoutLines,
       (SELECT COUNT(*) FROM Purchasing.PurchaseOrderLines WHERE ExpectedUnitPricePerOuter IS NULL)              AS LinesWithoutPrice
;

