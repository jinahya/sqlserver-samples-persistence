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
-- Exercises at the Intermediate level on the dbo schema of FutonManufacturing: the bill of
-- materials, the inventory, purchasing and sales.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql-exercises/_dbo/02_intermediate.sql
--


-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_01
-- the raw materials which four or more items use directly, with the number of those items, the most used first.
SELECT c.ItemCode, c.ItemName, COUNT(*) AS Parents
FROM BillOfMaterials b
         JOIN Items c ON c.ItemID = b.ComponentItemID
         JOIN ItemType t ON t.ItemTypeID = c.ItemTypeID
WHERE t.TypeCode = 'RAW'
  AND b.IsActive = 1
GROUP BY c.ItemID, c.ItemCode, c.ItemName
HAVING COUNT(*) >= 4
ORDER BY Parents DESC, c.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_02
-- Each finished futon's direct material cost, scrap included, and what remains of its list price; a futon without an
-- active bill of materials shows NULL for both.
SELECT p.ItemCode,
       p.ItemName,
       CAST(SUM(b.Quantity * (1 + b.ScrapRate / 100) * c.StandardCost) AS DECIMAL(18, 4))               AS MaterialCost,
       p.ListPrice,
       CAST(p.ListPrice - SUM(b.Quantity * (1 + b.ScrapRate / 100) * c.StandardCost) AS DECIMAL(18, 4)) AS Margin
FROM Items p
         JOIN ItemType t ON t.ItemTypeID = p.ItemTypeID
         LEFT JOIN BillOfMaterials b ON b.ParentItemID = p.ItemID AND b.IsActive = 1
         LEFT JOIN Items c ON c.ItemID = b.ComponentItemID
WHERE t.TypeCode = 'FG'
GROUP BY p.ItemID, p.ItemCode, p.ItemName, p.ListPrice
ORDER BY p.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_07
-- The exercises on the bill of materials assume an active link has a positive quantity, a known scrap rate not below 0,
-- the component's own unit, no twin, and a parent that is not a raw material; the schema enforces none of it. Find the
-- active links that break one of these; the check passes when nothing comes back.
SELECT b.BOMID, p.ItemCode AS ParentCode, c.ItemCode AS ComponentCode, b.Quantity, b.ScrapRate, bu.UnitCode AS BOMUnit, cu.UnitCode AS StockUnit
FROM BillOfMaterials b
         JOIN Items p ON p.ItemID = b.ParentItemID
         JOIN ItemType pt ON pt.ItemTypeID = p.ItemTypeID
         JOIN Items c ON c.ItemID = b.ComponentItemID
         JOIN UnitOfMeasure bu ON bu.UnitID = b.UnitID
         JOIN UnitOfMeasure cu ON cu.UnitID = c.UnitID
WHERE b.IsActive = 1
  AND (b.Quantity <= 0
    OR b.ScrapRate IS NULL
    OR b.ScrapRate < 0
    OR b.UnitID <> c.UnitID
    OR pt.TypeCode = 'RAW'
    OR EXISTS (SELECT 1
               FROM BillOfMaterials other
               WHERE other.ParentItemID = b.ParentItemID
                 AND other.ComponentItemID = b.ComponentItemID
                 AND other.IsActive = 1
                 AND other.BOMID <> b.BOMID))
ORDER BY p.ItemCode, c.ItemCode, b.BOMID
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_03
-- the five stocks nearest to their reorder points: the ratio of the quantity available to the reorder point, the
-- smallest first.
SELECT TOP (5) i.ItemCode,
               i.ItemName,
               w.WarehouseCode,
               v.QuantityAvailable,
               i.ReorderPoint,
               CAST(v.QuantityAvailable / i.ReorderPoint AS DECIMAL(18, 2)) AS Ratio
FROM Inventory v
         JOIN Items i ON i.ItemID = v.ItemID
         JOIN Warehouse w ON w.WarehouseID = v.WarehouseID
WHERE i.ReorderPoint > 0
ORDER BY v.QuantityAvailable / i.ReorderPoint, i.ItemCode, w.WarehouseCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_04
-- The share of what was inspected on receipt, `InspectionType` `Incoming`, that was rejected, for each supplier, the
-- worst first; an inspection names its purchase order by number, not by a foreign key, and a share is NULL where
-- nothing was inspected.
SELECT s.SupplierCode,
       s.SupplierName,
       SUM(q.QuantityInspected) AS Inspected,
       SUM(q.QuantityRejected) AS Rejected,
       CAST(SUM(q.QuantityRejected) * 100 / NULLIF(SUM(q.QuantityInspected), 0) AS DECIMAL(9, 2)) AS RejectedPercent
FROM PurchaseOrder po
         JOIN Supplier s ON s.SupplierID = po.SupplierID
         JOIN QualityInspection q ON q.ReferenceType = 'PO' AND q.ReferenceNumber = po.PONumber AND q.InspectionType = 'Incoming'
GROUP BY s.SupplierID, s.SupplierCode, s.SupplierName
ORDER BY RejectedPercent DESC, s.SupplierCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_05
-- The number of orders not cancelled and their net amount, for every sales channel, `0` for a channel without one, the
-- largest first; channels with the same amount are ordered by code.
SELECT ch.ChannelCode,
       ch.ChannelName,
       COUNT(so.SalesOrderID)         AS Orders,
       COALESCE(SUM(so.NetAmount), 0) AS NetAmount
FROM SalesChannel ch
         LEFT JOIN SalesOrder so ON so.SalesChannelID = ch.SalesChannelID AND so.Status <> 'Cancelled'
GROUP BY ch.SalesChannelID, ch.ChannelCode, ch.ChannelName
ORDER BY NetAmount DESC, ch.ChannelCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_06
-- the three futons sold the most, by quantity, with the net amount of their lines.
SELECT TOP (3) i.ItemCode, i.ItemName, SUM(d.Quantity) AS Quantity, SUM(d.NetAmount) AS NetAmount
FROM SalesOrderDetail d
         JOIN SalesOrder so ON so.SalesOrderID = d.SalesOrderID
         JOIN Items i ON i.ItemID = d.ItemID
         JOIN ItemType t ON t.ItemTypeID = i.ItemTypeID
WHERE t.TypeCode = 'FG'
  AND so.Status <> 'Cancelled'
GROUP BY i.ItemID, i.ItemCode, i.ItemName
ORDER BY Quantity DESC, i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_08
-- the share of each item returned.
SELECT i.ItemCode,
       i.ItemName,
       SUM(d.Quantity)                                                       AS Sold,
       SUM(COALESCE(r.Returned, 0))                                          AS Returned,
       CAST(SUM(COALESCE(r.Returned, 0)) * 100 / SUM(d.Quantity) AS DECIMAL(9, 2)) AS ReturnedPercent
FROM SalesOrderDetail d
         JOIN SalesOrder so ON so.SalesOrderID = d.SalesOrderID
         JOIN Items i ON i.ItemID = d.ItemID
         LEFT JOIN (SELECT rd.SODetailID, SUM(rd.QuantityReturned) AS Returned
                    FROM SalesReturnDetail rd
                             JOIN SalesReturn sr ON sr.ReturnID = rd.ReturnID
                    WHERE sr.Status <> 'Denied'
                    GROUP BY rd.SODetailID) r ON r.SODetailID = d.SODetailID
WHERE so.Status <> 'Cancelled'
GROUP BY i.ItemID, i.ItemCode, i.ItemName
ORDER BY ReturnedPercent DESC, i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_09
-- the quantities returned and refunded, by reason and item.
SELECT rr.ReasonCode, rr.ReasonDescription, i.ItemCode, i.ItemName,
       COUNT(DISTINCT d.SODetailID) AS SalesLines,
       SUM(d.QuantityReturned)      AS QuantityReturned,
       SUM(d.RefundAmount)          AS RefundAmount
FROM SalesReturn r
         JOIN ReturnReason rr ON rr.ReturnReasonID = r.ReturnReasonID
         JOIN SalesReturnDetail d ON d.ReturnID = r.ReturnID
         JOIN Items i ON i.ItemID = d.ItemID
WHERE r.Status IN ('Received', 'Refunded')
GROUP BY rr.ReturnReasonID, rr.ReasonCode, rr.ReasonDescription, i.ItemID, i.ItemCode, i.ItemName
ORDER BY RefundAmount DESC, rr.ReasonCode, i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_10
-- the scrap rate of each work center.
SELECT wc.WorkCenterCode,
       wc.WorkCenterName,
       SUM(pc.QuantityCompleted)             AS Completed,
       SUM(COALESCE(pc.QuantityScrapped, 0)) AS Scrapped,
       CAST(SUM(COALESCE(pc.QuantityScrapped, 0)) * 100
           / NULLIF(SUM(pc.QuantityCompleted + COALESCE(pc.QuantityScrapped, 0)), 0) AS DECIMAL(9, 2)) AS ScrapPercent
FROM ProductionCompletion pc
         JOIN ProductionOrder po ON po.ProductionOrderID = pc.ProductionOrderID
         JOIN WorkCenter wc ON wc.WorkCenterID = COALESCE(pc.WorkCenterID, po.WorkCenterID)
GROUP BY wc.WorkCenterID, wc.WorkCenterCode, wc.WorkCenterName
ORDER BY ScrapPercent DESC, wc.WorkCenterCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_11
-- the production materials still to issue.
WITH Materials AS (SELECT m.ProductionOrderID,
                          m.ItemID,
                          SUM(m.RequiredQuantity - m.IssuedQuantity)               AS Remaining,
                          SUM(CASE WHEN m.IssuedQuantity IS NULL THEN 1 ELSE 0 END) AS UnknownIssued
                   FROM ProductionOrderMaterial m
                   GROUP BY m.ProductionOrderID, m.ItemID)
SELECT po.WorkOrderNumber,
       i.ItemCode,
       w.WarehouseCode,
       CASE WHEN m.UnknownIssued = 0 THEN m.Remaining END AS Remaining,
       COALESCE(v.QuantityAvailable, 0)                  AS Available,
       m.UnknownIssued
FROM Materials m
         JOIN ProductionOrder po ON po.ProductionOrderID = m.ProductionOrderID
         JOIN Items i ON i.ItemID = m.ItemID
         JOIN Warehouse w ON w.WarehouseID = po.WarehouseID
         LEFT JOIN Inventory v ON v.ItemID = m.ItemID AND v.WarehouseID = po.WarehouseID
WHERE po.Status IN ('Planned', 'Released', 'InProgress')
  AND (m.Remaining > 0 OR m.UnknownIssued > 0)
ORDER BY po.WorkOrderNumber, i.ItemCode
;

