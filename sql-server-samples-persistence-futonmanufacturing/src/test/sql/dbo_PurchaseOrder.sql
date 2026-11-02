-- -
-- #%L
-- sql-server-samples-persistence-futonmanufacturing
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
-- Profiling queries on dbo.PurchaseOrder, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_PurchaseOrder.sql
--


-- ----------------------------------------------------------------------------------------------------- PurchaseOrderID
-- how many rows, and the smallest and the largest PurchaseOrderID; 5 orders, 1 to 5.
SELECT COUNT(*), MIN(PurchaseOrderID), MAX(PurchaseOrderID)
FROM dbo.PurchaseOrder
;


-- ------------------------------------------------------------------------------------------------------------ PONumber
-- a PONumber not in the form PO-YYYY-NNN, the year of the OrderDate, or whose number is not the PurchaseOrderID.
-- no rows: every number is PO-2024- and the PurchaseOrderID in three digits.
SELECT PurchaseOrderID, PONumber, OrderDate
FROM dbo.PurchaseOrder
WHERE PONumber NOT LIKE 'PO-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]'
   OR SUBSTRING(PONumber, 4, 4) <> CAST(YEAR(OrderDate) AS char(4))
   OR CAST(RIGHT(PONumber, 3) AS int) <> PurchaseOrderID
;

-- the shortest and the longest PONumber; 11 and 11 characters, of the 50 the column allows.
SELECT MIN(LEN(PONumber)), MAX(LEN(PONumber))
FROM dbo.PurchaseOrder
;

-- a PONumber which is blank, or carries leading or trailing spaces.
-- no rows: every PONumber is trimmed and not blank.
SELECT PurchaseOrderID, '[' + PONumber + ']' AS PONumber
FROM dbo.PurchaseOrder
WHERE TRIM(PONumber) = ''
   OR DATALENGTH(PONumber) <> DATALENGTH(TRIM(PONumber))
;

-- a unique constraint keeps PONumber unique.
-- no rows: every PONumber is distinct.
SELECT PONumber, COUNT(*) AS orders
FROM dbo.PurchaseOrder
GROUP BY PONumber
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- the orders of each supplier; one each of the 5.
SELECT SupplierID, COUNT(*) AS orders
FROM dbo.PurchaseOrder
GROUP BY SupplierID
ORDER BY SupplierID ASC
;


-- --------------------------------------------------------------------------------------------------------- WarehouseID
-- the orders of each warehouse; all 5 to warehouse 1, the main one.
SELECT WarehouseID, COUNT(*) AS orders
FROM dbo.PurchaseOrder
GROUP BY WarehouseID
ORDER BY WarehouseID ASC
;


-- ----------------------------------------------------------------------------------------------------------- OrderDate
-- the earliest and the latest OrderDate; 2024-11-04 and 2024-11-08, a day apart each.
SELECT MIN(OrderDate), MAX(OrderDate)
FROM dbo.PurchaseOrder
;


-- ------------------------------------------------------------------------------------------------ ExpectedDeliveryDate
-- how many ExpectedDeliveryDate values are NULL, and the fewest and the most days after the OrderDate; none, 14 and
-- 21.
SELECT SUM(CASE WHEN ExpectedDeliveryDate IS NULL THEN 1 ELSE 0 END),
       MIN(DATEDIFF(DAY, OrderDate, ExpectedDeliveryDate)), MAX(DATEDIFF(DAY, OrderDate, ExpectedDeliveryDate))
FROM dbo.PurchaseOrder
;


-- -------------------------------------------------------------------------------------------------- ActualDeliveryDate
-- how many ActualDeliveryDate values are NULL, and how many differ from the ExpectedDeliveryDate; none, and none:
-- every order arrived the day it was expected.
SELECT SUM(CASE WHEN ActualDeliveryDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN ActualDeliveryDate <> ExpectedDeliveryDate THEN 1 ELSE 0 END)
FROM dbo.PurchaseOrder
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- the values Status takes, the first key of IX_PO_Status; Received for all 5.
SELECT Status, COUNT(*) AS orders
FROM dbo.PurchaseOrder
GROUP BY Status
ORDER BY Status ASC
;

-- a Received order with no ActualDeliveryDate, or one delivered but not Received.
-- no rows: the two agree.
SELECT PurchaseOrderID, Status, ActualDeliveryDate
FROM dbo.PurchaseOrder
WHERE (Status = 'Received' AND ActualDeliveryDate IS NULL)
   OR (Status <> 'Received' AND ActualDeliveryDate IS NOT NULL)
;


-- ------------------------------------------------------------------------------------------------------------ Subtotal
-- how many Subtotal values are NULL, and the smallest and the largest; none, 1240.00 and 4025.00.
SELECT SUM(CASE WHEN Subtotal IS NULL THEN 1 ELSE 0 END), MIN(Subtotal), MAX(Subtotal)
FROM dbo.PurchaseOrder
;

-- an order whose Subtotal is not the sum of its lines.
-- no rows: every Subtotal adds up.
SELECT po.PurchaseOrderID, po.Subtotal, SUM(d.LineTotal) AS lines
FROM dbo.PurchaseOrder po
         JOIN dbo.PurchaseOrderDetail d ON d.PurchaseOrderID = po.PurchaseOrderID
GROUP BY po.PurchaseOrderID, po.Subtotal
HAVING po.Subtotal <> SUM(d.LineTotal)
;


-- ----------------------------------------------------------------------------------------------------------- TaxAmount
-- the TaxAmount as a share of the Subtotal; .08 for every order.
SELECT MIN(TaxAmount / Subtotal), MAX(TaxAmount / Subtotal)
FROM dbo.PurchaseOrder
;


-- ------------------------------------------------------------------------------------------------------ ShippingAmount
-- the values ShippingAmount takes; 50.00 for all 5.
SELECT ShippingAmount, COUNT(*) AS orders
FROM dbo.PurchaseOrder
GROUP BY ShippingAmount
ORDER BY ShippingAmount ASC
;


-- --------------------------------------------------------------------------------------------------------- TotalAmount
-- an order whose TotalAmount is not the Subtotal, the TaxAmount and the ShippingAmount together.
-- no rows: every TotalAmount adds up.
SELECT PurchaseOrderID, Subtotal, TaxAmount, ShippingAmount, TotalAmount
FROM dbo.PurchaseOrder
WHERE TotalAmount IS NULL
   OR TotalAmount <> Subtotal + TaxAmount + ShippingAmount
;


-- --------------------------------------------------------------------------------------------------------------- Notes
-- how many Notes values are NULL, and the shortest and the longest of the rest; none, 29 and 63 characters.
SELECT SUM(CASE WHEN Notes IS NULL THEN 1 ELSE 0 END), MIN(LEN(Notes)), MAX(LEN(Notes))
FROM dbo.PurchaseOrder
;


-- ----------------------------------------------------------------------------------------------------------- CreatedBy
-- the values CreatedBy takes; SampleData for all 5.
SELECT CreatedBy, COUNT(*) AS orders
FROM dbo.PurchaseOrder
GROUP BY CreatedBy
;


-- --------------------------------------------------------------------------------------------------------- CreatedDate
-- how many CreatedDate values are NULL, and how many fall outside the five minutes after the database was created;
-- none, and none: the default GETDATE() filled every one in while the load script ran, so it is the moment of
-- the load, not of a business event, and moves with every load.
SELECT SUM(CASE WHEN x.CreatedDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.CreatedDate NOT BETWEEN d.create_date AND DATEADD(MINUTE, 5, d.create_date) THEN 1 ELSE 0 END)
FROM dbo.PurchaseOrder x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- an order modified at another moment than it was created.
-- no rows: no order has been modified since the load.
SELECT PurchaseOrderID, CreatedDate, ModifiedDate
FROM dbo.PurchaseOrder
WHERE ModifiedDate IS NULL
   OR ModifiedDate <> CreatedDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an order with no line.
-- no rows: every order has lines.
SELECT po.PurchaseOrderID
FROM dbo.PurchaseOrder po
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.PurchaseOrderDetail d
                  WHERE d.PurchaseOrderID = po.PurchaseOrderID)
;

