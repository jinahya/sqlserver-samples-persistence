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
-- Profiling queries on the view dbo.vw_QualityInspectionSummary, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_QualityInspectionSummary.sql
--


-- ------------------------------------------------------------------------------------------------------ InspectionDate
-- how many rows, against the inspections; 5 and 5, a row per inspection, no two falling in the same group.
SELECT COUNT(*), (SELECT COUNT(*) FROM dbo.QualityInspection)
FROM dbo.vw_QualityInspectionSummary
;

-- the grain of the view; the day, the InspectionType and the item.
-- no rows: no two rows share them.
SELECT InspectionDate, InspectionType, ItemCode, COUNT(*) AS rows
FROM dbo.vw_QualityInspectionSummary
GROUP BY InspectionDate, InspectionType, ItemCode
HAVING COUNT(*) > 1
;

-- the range of InspectionDate; 2024-11-18 to 2024-11-29, none NULL.
SELECT SUM(CASE WHEN InspectionDate IS NULL THEN 1 ELSE 0 END), MIN(InspectionDate), MAX(InspectionDate)
FROM dbo.vw_QualityInspectionSummary
;


-- ---------------------------------------------------------------------------------------------------------------- Year
-- a Year which is not the one of InspectionDate.
-- no rows: they agree; 2024 throughout.
SELECT InspectionDate, Year
FROM dbo.vw_QualityInspectionSummary
WHERE Year <> YEAR(InspectionDate)
;


-- --------------------------------------------------------------------------------------------------------------- Month
-- a Month which is not the one of InspectionDate.
-- no rows: they agree; 11 throughout.
SELECT InspectionDate, Month
FROM dbo.vw_QualityInspectionSummary
WHERE Month <> MONTH(InspectionDate)
;


-- ------------------------------------------------------------------------------------------------------ InspectionType
-- the inspection types; Incoming on all 5, receipts of purchase orders.
SELECT InspectionType, COUNT(*) AS rows
FROM dbo.vw_QualityInspectionSummary
GROUP BY InspectionType
;


-- ------------------------------------------------------------------------------------------------------------ ItemCode
-- the items inspected; 5, RM-FAB-001 to RM-WOOD-001.
SELECT COUNT(DISTINCT ItemCode), MIN(ItemCode), MAX(ItemCode)
FROM dbo.vw_QualityInspectionSummary
;


-- ------------------------------------------------------------------------------------------------------------ ItemName
-- an ItemName which is not the one of its ItemCode.
-- no rows: each matches Items.
SELECT v.ItemCode, v.ItemName
FROM dbo.vw_QualityInspectionSummary v
         JOIN dbo.Items i ON i.ItemCode = v.ItemCode
WHERE v.ItemName <> i.ItemName
;


-- ------------------------------------------------------------------------------------------------------------ ItemType
-- the item types; Raw Material on all 5.
SELECT ItemType, COUNT(*) AS rows
FROM dbo.vw_QualityInspectionSummary
GROUP BY ItemType
;


-- ------------------------------------------------------------------------------------------------- NumberOfInspections
-- the values of NumberOfInspections; 1 on all 5.
SELECT NumberOfInspections, COUNT(*) AS rows
FROM dbo.vw_QualityInspectionSummary
GROUP BY NumberOfInspections
;


-- ------------------------------------------------------------------------------------------------------ TotalInspected
-- the smallest and the largest TotalInspected, and the sum; 50.00, 500.00 and 930.00.
SELECT MIN(TotalInspected), MAX(TotalInspected), SUM(TotalInspected)
FROM dbo.vw_QualityInspectionSummary
;


-- ------------------------------------------------------------------------------------------------------- TotalAccepted
-- a row whose TotalAccepted and TotalRejected do not add up to its TotalInspected; no check constraint holds it.
-- no rows: every unit inspected is accepted or rejected.
SELECT InspectionDate, ItemCode, TotalInspected, TotalAccepted, TotalRejected
FROM dbo.vw_QualityInspectionSummary
WHERE TotalAccepted + TotalRejected <> TotalInspected
;

-- the sum of TotalAccepted; 919.00 of the 930.00.
SELECT SUM(TotalAccepted)
FROM dbo.vw_QualityInspectionSummary
;


-- ------------------------------------------------------------------------------------------------------- TotalRejected
-- the smallest and the largest TotalRejected, and the sum; 1.00, 5.00 and 11.00.
SELECT MIN(TotalRejected), MAX(TotalRejected), SUM(TotalRejected)
FROM dbo.vw_QualityInspectionSummary
;


-- ------------------------------------------------------------------------------------------------------ AcceptanceRate
-- an AcceptanceRate which is not TotalAccepted over TotalInspected, in percent to two places.
-- no rows: it is the ratio throughout.
SELECT InspectionDate, ItemCode, AcceptanceRate
FROM dbo.vw_QualityInspectionSummary
WHERE AcceptanceRate <> CAST(TotalAccepted * 100.0 / TotalInspected AS DECIMAL(5, 2))
;

-- the smallest and the largest AcceptanceRate; 98.00 and 99.00.
SELECT MIN(AcceptanceRate), MAX(AcceptanceRate)
FROM dbo.vw_QualityInspectionSummary
;


-- ------------------------------------------------------------------------------------------------------- RejectionRate
-- a RejectionRate which does not make 100 with the AcceptanceRate.
-- no rows: the two rates add up to 100.00 on every row.
SELECT InspectionDate, ItemCode, AcceptanceRate, RejectionRate
FROM dbo.vw_QualityInspectionSummary
WHERE AcceptanceRate + RejectionRate <> 100
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an incoming inspection which does not match its purchase order: no line of the item on the PONumber it refers to,
-- an InspectionDate other than the delivery day, or a QuantityInspected other than the quantity received.
-- no rows: each of the 5 inspects a whole line, on the day the order arrived.
SELECT qi.InspectionID, qi.ReferenceNumber, qi.ItemID, qi.QuantityInspected, d.QuantityReceived
FROM dbo.QualityInspection qi
         LEFT OUTER JOIN dbo.PurchaseOrder po ON po.PONumber = qi.ReferenceNumber
         LEFT OUTER JOIN dbo.PurchaseOrderDetail d ON d.PurchaseOrderID = po.PurchaseOrderID AND d.ItemID = qi.ItemID
WHERE qi.InspectionType = 'Incoming'
  AND (d.PODetailID IS NULL
    OR CAST(qi.InspectionDate AS DATE) <> po.ActualDeliveryDate
    OR qi.QuantityInspected <> d.QuantityReceived)
;

