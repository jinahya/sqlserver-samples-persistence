-- #%L
-- mssql-sample-databases-persistence-wideworldimporters
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
-- Exercises on the Purchasing schema of WideWorldImporters.
--
-- These are the SQL solutions of the 'Purchasing' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- ------------------------------------------------------------------------------------------------- WWI-PURCHASING-B-01
-- The buyer wants the suppliers with their category and payment days, by category and then by name.
SELECT s.SupplierID, s.SupplierName, sc.SupplierCategoryName, s.PaymentDays
FROM Purchasing.Suppliers s
         JOIN Purchasing.SupplierCategories sc ON sc.SupplierCategoryID = s.SupplierCategoryID
ORDER BY sc.SupplierCategoryName, s.SupplierName
;


-- ------------------------------------------------------------------------------------------------- WWI-PURCHASING-B-02
-- Accounts payable wants what is still owed to each supplier: the transactions not finalized, their number and the
-- outstanding balance, the largest first. IsFinalized is a computed column.
SELECT s.SupplierName, COUNT(*) AS Transactions, SUM(st.OutstandingBalance) AS Outstanding
FROM Purchasing.SupplierTransactions st
         JOIN Purchasing.Suppliers s ON s.SupplierID = st.SupplierID
WHERE st.IsFinalized = 0
GROUP BY s.SupplierID, s.SupplierName
ORDER BY Outstanding DESC
;


-- ------------------------------------------------------------------------------------------------- WWI-PURCHASING-I-01
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
ORDER BY Amount DESC
;
