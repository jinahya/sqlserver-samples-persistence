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
-- Profiling queries on the view dbo.vw_PurchaseOrderStatus, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_PurchaseOrderStatus.sql
--


-- ------------------------------------------------------------------------------------------------------------ PONumber
-- how many rows, and how many distinct PONumber, against the purchase orders; 5, 5 and 5, a row per order.
SELECT COUNT(*), COUNT(DISTINCT PONumber), (SELECT COUNT(*) FROM dbo.PurchaseOrder)
FROM dbo.vw_PurchaseOrderStatus
;

-- the smallest and the largest PONumber; PO-2024-001 and PO-2024-005.
SELECT MIN(PONumber), MAX(PONumber)
FROM dbo.vw_PurchaseOrderStatus
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- the statuses; Received on all 5.
SELECT Status, COUNT(*) AS orders
FROM dbo.vw_PurchaseOrderStatus
GROUP BY Status
;


-- -------------------------------------------------------------------------------------------------------- SupplierName
-- the suppliers; 5, an order each.
SELECT COUNT(DISTINCT SupplierName), MAX(orders)
FROM (SELECT SupplierName, COUNT(*) AS orders
      FROM dbo.vw_PurchaseOrderStatus
      GROUP BY SupplierName) s
;

-- a SupplierName which is not the one of the order's supplier.
-- no rows: each matches Supplier.
SELECT v.PONumber, v.SupplierName
FROM dbo.vw_PurchaseOrderStatus v
         JOIN dbo.PurchaseOrder po ON po.PONumber = v.PONumber
         JOIN dbo.Supplier s ON s.SupplierID = po.SupplierID
WHERE v.SupplierName <> s.SupplierName
;


-- ------------------------------------------------------------------------------------------------------- WarehouseName
-- the warehouses received into; Main Manufacturing Facility on all 5.
SELECT WarehouseName, COUNT(*) AS orders
FROM dbo.vw_PurchaseOrderStatus
GROUP BY WarehouseName
;


-- ----------------------------------------------------------------------------------------------------------- OrderDate
-- the range of OrderDate; 2024-11-04 to 2024-11-08, an order a day.
SELECT MIN(OrderDate), MAX(OrderDate), COUNT(DISTINCT OrderDate)
FROM dbo.vw_PurchaseOrderStatus
;


-- ------------------------------------------------------------------------------------------------ ExpectedDeliveryDate
-- how many ExpectedDeliveryDate are NULL, and the lead times they promise; none, 14 to 21 days after the order.
SELECT SUM(CASE WHEN ExpectedDeliveryDate IS NULL THEN 1 ELSE 0 END),
       MIN(DATEDIFF(DAY, OrderDate, ExpectedDeliveryDate)),
       MAX(DATEDIFF(DAY, OrderDate, ExpectedDeliveryDate))
FROM dbo.vw_PurchaseOrderStatus
;


-- -------------------------------------------------------------------------------------------------- ActualDeliveryDate
-- an order received on another day than expected, or not received at all.
-- no rows: every order arrived on its ExpectedDeliveryDate.
SELECT PONumber, ExpectedDeliveryDate, ActualDeliveryDate
FROM dbo.vw_PurchaseOrderStatus
WHERE ActualDeliveryDate IS NULL
   OR ActualDeliveryDate <> ExpectedDeliveryDate
;


-- ------------------------------------------------------------------------------------------------------ DaysSinceOrder
-- a DaysSinceOrder which is not the days from OrderDate to ActualDeliveryDate, or to today while undelivered.
-- no rows: it is the difference throughout.
SELECT PONumber, DaysSinceOrder
FROM dbo.vw_PurchaseOrderStatus
WHERE DaysSinceOrder <> DATEDIFF(DAY, OrderDate, ISNULL(ActualDeliveryDate, GETDATE()))
;

-- the values of DaysSinceOrder; 14 on 3 orders, 21 on 2, fixed now that every order is delivered.
SELECT DaysSinceOrder, COUNT(*) AS orders
FROM dbo.vw_PurchaseOrderStatus
GROUP BY DaysSinceOrder
ORDER BY DaysSinceOrder ASC
;


-- -------------------------------------------------------------------------------------------------------- DaysVariance
-- the values of DaysVariance; 0 on all 5, every order on time.
SELECT DaysVariance, COUNT(*) AS orders
FROM dbo.vw_PurchaseOrderStatus
GROUP BY DaysVariance
;


-- ----------------------------------------------------------------------------------------------------------- LineItems
-- the values of LineItems; 2 on all 5.
SELECT LineItems, COUNT(*) AS orders
FROM dbo.vw_PurchaseOrderStatus
GROUP BY LineItems
;


-- --------------------------------------------------------------------------------------------------------- TotalAmount
-- a TotalAmount which is not the order's Subtotal plus TaxAmount plus ShippingAmount.
-- no rows: it is the sum throughout, 1389.20 to 4397.00.
SELECT v.PONumber, v.TotalAmount, po.Subtotal, po.TaxAmount, po.ShippingAmount
FROM dbo.vw_PurchaseOrderStatus v
         JOIN dbo.PurchaseOrder po ON po.PONumber = v.PONumber
WHERE v.TotalAmount <> po.Subtotal + po.TaxAmount + po.ShippingAmount
;


-- ---------------------------------------------------------------------------------------------------------- LinesTotal
-- a LinesTotal which is not the order's Subtotal; the lines carry no tax or shipping.
-- no rows: the lines add up to the Subtotal on every order.
SELECT v.PONumber, v.LinesTotal, po.Subtotal
FROM dbo.vw_PurchaseOrderStatus v
         JOIN dbo.PurchaseOrder po ON po.PONumber = v.PONumber
WHERE v.LinesTotal <> po.Subtotal
;

-- the LinesTotal against the TotalAmount; the lines add up to 10395.00 and the orders to 11476.60, the difference
-- being tax and shipping.
SELECT SUM(LinesTotal), SUM(TotalAmount)
FROM dbo.vw_PurchaseOrderStatus
;


-- ------------------------------------------------------------------------------------------------------- ReceivedValue
-- a ReceivedValue which is not the LinesTotal; every line is received in full.
-- no rows: the two agree on every order.
SELECT PONumber, ReceivedValue, LinesTotal
FROM dbo.vw_PurchaseOrderStatus
WHERE ReceivedValue <> LinesTotal
;


-- ----------------------------------------------------------------------------------------------------- PercentReceived
-- the values of PercentReceived; 100.00 on all 5.
SELECT PercentReceived, COUNT(*) AS orders
FROM dbo.vw_PurchaseOrderStatus
GROUP BY PercentReceived
;


-- ------------------------------------------------------------------------------------------------------ DeliveryStatus
-- the values of DeliveryStatus; Complete on all 5, as every order is Received.
SELECT DeliveryStatus, COUNT(*) AS orders
FROM dbo.vw_PurchaseOrderStatus
GROUP BY DeliveryStatus
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a purchase order line whose QuantityReceived is not its Quantity, or whose LineTotal is not Quantity times
-- UnitPrice.
-- no rows: all 10 lines are received in full and priced right.
SELECT po.PONumber, d.PODetailID, d.Quantity, d.QuantityReceived, d.UnitPrice, d.LineTotal
FROM dbo.PurchaseOrder po
         JOIN dbo.PurchaseOrderDetail d ON d.PurchaseOrderID = po.PurchaseOrderID
WHERE d.QuantityReceived <> d.Quantity
   OR d.LineTotal <> d.Quantity * d.UnitPrice
;

