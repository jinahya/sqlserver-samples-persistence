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
-- Exercises at the advanced level on the Purchasing schema of WideWorldImporters.
--
-- These are the SQL solutions of the 'Purchasing' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- ------------------------------------------------------------------------------------------------- WWI_PURCHASING_A_01
-- The buyer rates the suppliers on delivery: for each supplier, the finalized purchase orders received in full, how
-- many of them arrived after the expected delivery date, and the average days late; the most late orders first, a tie
-- by name.
WITH received AS (SELECT po.PurchaseOrderID, po.SupplierID, po.ExpectedDeliveryDate, MAX(pol.LastReceiptDate) AS ReceivedDate
                  FROM Purchasing.PurchaseOrders po
                           JOIN Purchasing.PurchaseOrderLines pol ON pol.PurchaseOrderID = po.PurchaseOrderID
                  WHERE po.IsOrderFinalized = 1
                    AND po.ExpectedDeliveryDate IS NOT NULL
                  GROUP BY po.PurchaseOrderID, po.SupplierID, po.ExpectedDeliveryDate
                  HAVING COUNT(pol.LastReceiptDate) = COUNT(*))
SELECT s.SupplierName,
       COUNT(*)                                                                                AS Orders,
       SUM(CASE WHEN r.ReceivedDate > r.ExpectedDeliveryDate THEN 1 ELSE 0 END)                AS Late,
       CAST(AVG(CAST(DATEDIFF(day, r.ExpectedDeliveryDate, r.ReceivedDate) AS decimal(10, 1))) AS decimal(10, 1)) AS AverageDaysLate
FROM received r
         JOIN Purchasing.Suppliers s ON s.SupplierID = r.SupplierID
GROUP BY s.SupplierID, s.SupplierName
ORDER BY Late DESC, s.SupplierName
;

-- ------------------------------------------------------------------------------------------------- WWI_PURCHASING_A_02
-- For each supplier, up to three purchase orders not finalized whose expected delivery date is before a cutoff and
-- which have a line not finalized and short-received, the earliest expected first; `PurchaseOrderID` breaks a tie.
DECLARE @asOf date = '2016-06-01';
WITH overdue AS (SELECT po.PurchaseOrderID,
                        po.SupplierID,
                        po.ExpectedDeliveryDate,
                        ROW_NUMBER() OVER (PARTITION BY po.SupplierID ORDER BY po.ExpectedDeliveryDate, po.PurchaseOrderID) AS Position
                 FROM Purchasing.PurchaseOrders po
                 WHERE po.IsOrderFinalized = 0
                   AND po.ExpectedDeliveryDate < @asOf
                   AND EXISTS (SELECT 1
                               FROM Purchasing.PurchaseOrderLines pol
                               WHERE pol.PurchaseOrderID = po.PurchaseOrderID
                                 AND pol.IsOrderLineFinalized = 0
                                 AND pol.ReceivedOuters < pol.OrderedOuters))
SELECT s.SupplierID, s.SupplierName, o.PurchaseOrderID, o.ExpectedDeliveryDate
FROM overdue o
         JOIN Purchasing.Suppliers s ON s.SupplierID = o.SupplierID
WHERE o.Position <= 3
ORDER BY s.SupplierName, o.Position
;
