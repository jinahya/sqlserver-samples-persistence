-- -
-- #%L
-- sqlserver-samples-persistence-futonmanufacturing
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
-- Profiling queries on dbo.QualityInspection, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_QualityInspection.sql
--


-- -------------------------------------------------------------------------------------------------------- InspectionID
-- how many rows, and the smallest and the largest InspectionID; 5 inspections, 1 to 5.
SELECT COUNT(*), MIN(InspectionID), MAX(InspectionID)
FROM dbo.QualityInspection
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- the types of the items inspected; only raw materials (1), all 5.
SELECT i.ItemTypeID, COUNT(*) AS inspections
FROM dbo.QualityInspection q
         JOIN dbo.Items i ON i.ItemID = q.ItemID
GROUP BY i.ItemTypeID
;


-- ------------------------------------------------------------------------------------------------------ InspectionType
-- the values InspectionType takes; Incoming for all 5.
SELECT InspectionType, COUNT(*) AS inspections
FROM dbo.QualityInspection
GROUP BY InspectionType
ORDER BY InspectionType ASC
;


-- ------------------------------------------------------------------------------------------------------- ReferenceType
-- the values ReferenceType takes, NULL for none; PO for all 5.
SELECT ReferenceType, COUNT(*) AS inspections
FROM dbo.QualityInspection
GROUP BY ReferenceType
ORDER BY ReferenceType ASC
;


-- ----------------------------------------------------------------------------------------------------- ReferenceNumber
-- a PO reference which is not the PONumber of a purchase order, or whose order has no line of the item; nothing but
-- the text ties the two.
-- no rows: every reference names an order with a line of the inspected item.
SELECT q.InspectionID, q.ReferenceNumber, q.ItemID
FROM dbo.QualityInspection q
WHERE q.ReferenceType = 'PO'
  AND NOT EXISTS (SELECT 1
                  FROM dbo.PurchaseOrder po
                           JOIN dbo.PurchaseOrderDetail d ON d.PurchaseOrderID = po.PurchaseOrderID
                  WHERE po.PONumber = q.ReferenceNumber
                    AND d.ItemID = q.ItemID)
;


-- --------------------------------------------------------------------------------------------------- QuantityInspected
-- the smallest and the largest QuantityInspected; 50.00 and 500.00.
SELECT MIN(QuantityInspected), MAX(QuantityInspected)
FROM dbo.QualityInspection
;

-- a QuantityInspected other than the QuantityReceived of the line it refers to.
-- no rows: every inspection covers the whole line.
SELECT q.InspectionID, q.QuantityInspected, d.QuantityReceived
FROM dbo.QualityInspection q
         JOIN dbo.PurchaseOrder po ON po.PONumber = q.ReferenceNumber
         JOIN dbo.PurchaseOrderDetail d ON d.PurchaseOrderID = po.PurchaseOrderID AND d.ItemID = q.ItemID
WHERE q.QuantityInspected <> d.QuantityReceived
;


-- ---------------------------------------------------------------------------------------------------- QuantityAccepted
-- an inspection whose QuantityAccepted and QuantityRejected do not add up to the QuantityInspected.
-- no rows: every inspection adds up.
SELECT InspectionID, QuantityInspected, QuantityAccepted, QuantityRejected
FROM dbo.QualityInspection
WHERE QuantityAccepted + QuantityRejected <> QuantityInspected
;


-- ---------------------------------------------------------------------------------------------------- QuantityRejected
-- the rejected share of each inspection, the largest first; 2.00 percent for inspections 1 and 3, down to 1.00 for 2
-- and 4.
SELECT InspectionID, QuantityRejected, QuantityInspected,
       CAST(QuantityRejected * 100 / QuantityInspected AS decimal(5, 2)) AS percent_rejected
FROM dbo.QualityInspection
ORDER BY percent_rejected DESC, InspectionID ASC
;


-- ------------------------------------------------------------------------------------------------------ InspectionDate
-- an inspection dated other than the day its purchase order was delivered.
-- no rows: every inspection took place on the day of delivery.
SELECT q.InspectionID, q.InspectionDate, po.ActualDeliveryDate
FROM dbo.QualityInspection q
         JOIN dbo.PurchaseOrder po ON po.PONumber = q.ReferenceNumber
WHERE q.InspectionDate IS NULL
   OR CAST(q.InspectionDate AS date) <> po.ActualDeliveryDate
;


-- --------------------------------------------------------------------------------------------------------- InspectedBy
-- the values InspectedBy takes; SampleData for all 5.
SELECT InspectedBy, COUNT(*) AS inspections
FROM dbo.QualityInspection
GROUP BY InspectedBy
;


-- --------------------------------------------------------------------------------------------------------------- Notes
-- how many Notes values are NULL, and the shortest and the longest of the rest; none, 30 and 63 characters.
SELECT SUM(CASE WHEN Notes IS NULL THEN 1 ELSE 0 END), MIN(LEN(Notes)), MAX(LEN(Notes))
FROM dbo.QualityInspection
;

