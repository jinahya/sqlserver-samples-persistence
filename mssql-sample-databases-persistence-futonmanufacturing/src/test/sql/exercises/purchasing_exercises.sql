-- -
-- #%L
-- mssql-sample-databases-persistence-futonmanufacturing
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
-- -
--
-- Exercises on purchasing: dbo.Supplier, dbo.SupplierItem, dbo.PurchaseOrder and dbo.QualityInspection. Each poses a
-- question, and answers it in Transact-SQL against the installed FutonManufacturing database.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/exercises/purchasing_exercises.sql
--


-- ----------------------------------------------------------------------------------------------- FUTON-PURCHASING-B-01
-- the items a supplier, SUP-002, supplies, with its price beside the item's standard cost.
SELECT i.ItemCode, i.ItemName, si.UnitPrice, i.StandardCost, si.MinimumOrderQuantity, si.LeadTimeDays
FROM SupplierItem si
         JOIN Supplier s ON s.SupplierID = si.SupplierID
         JOIN Items i ON i.ItemID = si.ItemID
WHERE s.SupplierCode = 'SUP-002'
ORDER BY i.ItemCode
;

-- ----------------------------------------------------------------------------------------------- FUTON-PURCHASING-I-01
-- the share of what was inspected on receipt that was rejected, for each supplier, the worst first; an inspection
-- names its purchase order by number, not by a foreign key.
SELECT s.SupplierCode,
       s.SupplierName,
       SUM(q.QuantityInspected) AS Inspected,
       SUM(q.QuantityRejected) AS Rejected,
       CAST(SUM(q.QuantityRejected) * 100 / SUM(q.QuantityInspected) AS DECIMAL(5, 2)) AS RejectedPercent
FROM PurchaseOrder po
         JOIN Supplier s ON s.SupplierID = po.SupplierID
         JOIN QualityInspection q ON q.ReferenceType = 'PO' AND q.ReferenceNumber = po.PONumber
GROUP BY s.SupplierID, s.SupplierCode, s.SupplierName
ORDER BY RejectedPercent DESC, s.SupplierCode
;

