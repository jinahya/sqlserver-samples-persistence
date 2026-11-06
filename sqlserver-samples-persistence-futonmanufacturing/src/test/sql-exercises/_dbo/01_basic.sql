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
-- Exercises at the Basic level on the dbo schema of FutonManufacturing: the bill of
-- materials, the inventory, purchasing and sales.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql-exercises/_dbo/01_basic.sql
--


-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_01
-- the direct components of a finished futon, FG-FUT-003, with the quantity, the unit and the scrap rate of each.
SELECT c.ItemCode, c.ItemName, b.Quantity, u.UnitCode, b.ScrapRate
FROM BillOfMaterials b
         JOIN Items p ON p.ItemID = b.ParentItemID
         JOIN Items c ON c.ItemID = b.ComponentItemID
         JOIN UnitOfMeasure u ON u.UnitID = b.UnitID
WHERE p.ItemCode = 'FG-FUT-003'
  AND b.IsActive = 1
ORDER BY c.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_02
-- the raw materials which no item uses: bought, and stocked, for nothing.
SELECT i.ItemCode, i.ItemName
FROM Items i
         JOIN ItemType t ON t.ItemTypeID = i.ItemTypeID
WHERE t.TypeCode = 'RAW'
  AND NOT EXISTS (SELECT 1
                  FROM BillOfMaterials b
                  WHERE b.ComponentItemID = i.ItemID
                    AND b.IsActive = 1)
ORDER BY i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_03
-- the finished goods in the main warehouse, WH-MAIN, the fewest available first.
SELECT i.ItemCode, i.ItemName, v.QuantityAvailable, i.ReorderPoint
FROM Inventory v
         JOIN Items i ON i.ItemID = v.ItemID
         JOIN ItemType t ON t.ItemTypeID = i.ItemTypeID
         JOIN Warehouse w ON w.WarehouseID = v.WarehouseID
WHERE t.TypeCode = 'FG'
  AND w.WarehouseCode = 'WH-MAIN'
ORDER BY v.QuantityAvailable, i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_06
-- an item's inventory transactions in a month.
SELECT it.TransactionID, it.TransactionDate, w.WarehouseCode, tt.TypeCode, it.Quantity, it.UnitCost, it.ReferenceType, it.ReferenceNumber
FROM InventoryTransaction it
         JOIN Items i ON i.ItemID = it.ItemID
         JOIN Warehouse w ON w.WarehouseID = it.WarehouseID
         JOIN TransactionType tt ON tt.TransactionTypeID = it.TransactionTypeID
WHERE i.ItemCode = 'RM-FILL-001'
  AND it.TransactionDate >= '20261101'
  AND it.TransactionDate < '20261201'
ORDER BY it.TransactionDate, it.TransactionID
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_04
-- The items a supplier, SUP-002, supplies now, its offers not ended, with its price beside the item's standard cost.
SELECT i.ItemCode, i.ItemName, si.UnitPrice, i.StandardCost, si.MinimumOrderQuantity, si.LeadTimeDays
FROM SupplierItem si
         JOIN Supplier s ON s.SupplierID = si.SupplierID
         JOIN Items i ON i.ItemID = si.ItemID
WHERE s.SupplierCode = 'SUP-002'
  AND si.EndDate IS NULL
ORDER BY i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_07
-- The lines of the purchase orders not cancelled whose quantity received is below the quantity ordered, or not
-- recorded, with the supplier, the receiving warehouse and the expected delivery date, the earliest first and the
-- undated last.
SELECT po.PONumber, d.LineNumber, d.PODetailID, s.SupplierCode, w.WarehouseCode, i.ItemCode, d.Quantity, d.QuantityReceived,
       d.Quantity - d.QuantityReceived AS Outstanding, po.ExpectedDeliveryDate
FROM PurchaseOrder po
         JOIN PurchaseOrderDetail d ON d.PurchaseOrderID = po.PurchaseOrderID
         JOIN Supplier s ON s.SupplierID = po.SupplierID
         JOIN Warehouse w ON w.WarehouseID = po.WarehouseID
         JOIN Items i ON i.ItemID = d.ItemID
WHERE po.Status <> 'Cancelled'
  AND (d.QuantityReceived < d.Quantity OR d.QuantityReceived IS NULL)
ORDER BY CASE WHEN po.ExpectedDeliveryDate IS NULL THEN 1 ELSE 0 END, po.ExpectedDeliveryDate, po.PONumber, d.LineNumber, d.PODetailID
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_05
-- the orders not delivered yet, the earliest requested delivery date first.
SELECT so.OrderNumber, c.CustomerName, so.Status, so.RequestedDeliveryDate, so.NetAmount
FROM SalesOrder so
         JOIN Customer c ON c.CustomerID = so.CustomerID
WHERE so.Status NOT IN ('Delivered', 'Cancelled')
ORDER BY CASE WHEN so.RequestedDeliveryDate IS NULL THEN 1 ELSE 0 END, so.RequestedDeliveryDate, so.OrderNumber
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_08
-- the accepted quotes not turned into orders.
SELECT q.QuoteNumber, c.CustomerName, q.QuoteDate, q.ExpirationDate, q.TotalAmount
FROM SalesQuote q
         JOIN Customer c ON c.CustomerID = q.CustomerID
WHERE q.Status = 'Accepted'
  AND q.ConvertedToOrderID IS NULL
ORDER BY q.QuoteDate, q.QuoteNumber
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_09
-- the production orders completed late.
SELECT po.WorkOrderNumber, i.ItemCode, wc.WorkCenterCode, po.PlannedCompletionDate, po.ActualCompletionDate
FROM ProductionOrder po
         JOIN Items i ON i.ItemID = po.ItemID
         LEFT JOIN WorkCenter wc ON wc.WorkCenterID = po.WorkCenterID
WHERE po.Status = 'Completed'
  AND po.ActualCompletionDate > po.PlannedCompletionDate
ORDER BY po.PlannedCompletionDate, po.WorkOrderNumber
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_10
-- the open production schedule.
SELECT po.WorkOrderNumber, i.ItemCode, w.WarehouseCode, wc.WorkCenterCode, po.Status, po.Priority, po.PlannedCompletionDate
FROM ProductionOrder po
         JOIN Items i ON i.ItemID = po.ItemID
         JOIN Warehouse w ON w.WarehouseID = po.WarehouseID
         LEFT JOIN WorkCenter wc ON wc.WorkCenterID = po.WorkCenterID
WHERE po.Status IN ('Planned', 'Released', 'InProgress')
ORDER BY CASE WHEN po.PlannedCompletionDate IS NULL THEN 1 ELSE 0 END, po.PlannedCompletionDate,
         CASE WHEN po.Priority IS NULL THEN 1 ELSE 0 END, po.Priority, po.WorkOrderNumber
;

