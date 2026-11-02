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
-- Profiling queries on the view dbo.vw_SalesOrderBacklog, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_SalesOrderBacklog.sql
--


-- --------------------------------------------------------------------------------------------------------- OrderNumber
-- how many rows, and how many distinct OrderNumber, against the orders neither Delivered nor Cancelled; 6, 6 and 6,
-- a row per open order.
SELECT COUNT(*),
       COUNT(DISTINCT OrderNumber),
       (SELECT COUNT(*) FROM dbo.SalesOrder WHERE Status NOT IN ('Delivered', 'Cancelled'))
FROM dbo.vw_SalesOrderBacklog
;

-- the smallest and the largest OrderNumber; SO-2024-1102 and SO-2024-1107.
SELECT MIN(OrderNumber), MAX(OrderNumber)
FROM dbo.vw_SalesOrderBacklog
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- the statuses; Confirmed 4, InProduction 1 and Shipped 1.
SELECT Status, COUNT(*) AS orders
FROM dbo.vw_SalesOrderBacklog
GROUP BY Status
ORDER BY Status ASC
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- a CustomerName which is not the one of its CustomerCode.
-- no rows: each matches Customer.
SELECT v.OrderNumber, v.CustomerCode, v.CustomerName
FROM dbo.vw_SalesOrderBacklog v
         JOIN dbo.Customer c ON c.CustomerCode = v.CustomerCode
WHERE v.CustomerName <> c.CustomerName
;


-- -------------------------------------------------------------------------------------------------------- CustomerCode
-- the customers with an open order; 6, an order each, CUST-002 to CUST-007.
SELECT COUNT(DISTINCT CustomerCode), MIN(CustomerCode), MAX(CustomerCode)
FROM dbo.vw_SalesOrderBacklog
;


-- ------------------------------------------------------------------------------------------------------- WarehouseName
-- the warehouses the orders ship from; Main Manufacturing Facility 4, West Coast Distribution 2.
SELECT WarehouseName, COUNT(*) AS orders
FROM dbo.vw_SalesOrderBacklog
GROUP BY WarehouseName
ORDER BY WarehouseName ASC
;


-- ----------------------------------------------------------------------------------------------------------- OrderDate
-- the range of OrderDate; 2024-11-02 to 2024-11-07, an order a day.
SELECT MIN(OrderDate), MAX(OrderDate), COUNT(DISTINCT OrderDate)
FROM dbo.vw_SalesOrderBacklog
;


-- ----------------------------------------------------------------------------------------------- RequestedDeliveryDate
-- how many RequestedDeliveryDate are NULL, and the days the customers allow after the order; none, 8 to 18.
SELECT SUM(CASE WHEN RequestedDeliveryDate IS NULL THEN 1 ELSE 0 END),
       MIN(DATEDIFF(DAY, OrderDate, RequestedDeliveryDate)),
       MAX(DATEDIFF(DAY, OrderDate, RequestedDeliveryDate))
FROM dbo.vw_SalesOrderBacklog
;


-- ------------------------------------------------------------------------------------------------------------ ShipDate
-- an order whose ShipDate does not go with its Status; only a Shipped order should have one.
-- 1 row: SO-2024-1102 is Shipped, yet has no ShipDate.
SELECT OrderNumber, Status, ShipDate
FROM dbo.vw_SalesOrderBacklog
WHERE (Status = 'Shipped' AND ShipDate IS NULL)
   OR (Status <> 'Shipped' AND ShipDate IS NOT NULL)
;


-- ------------------------------------------------------------------------------------------------------------ DaysOpen
-- a DaysOpen which is not the days from OrderDate to today; it grows by one a day, so the file checks the formula.
-- no rows: it is the difference throughout.
SELECT OrderNumber, OrderDate, DaysOpen
FROM dbo.vw_SalesOrderBacklog
WHERE DaysOpen IS NULL
   OR DaysOpen <> DATEDIFF(DAY, OrderDate, GETDATE())
;


-- -------------------------------------------------------------------------------------------------------- DaysUntilDue
-- a DaysUntilDue which is not the days from today to RequestedDeliveryDate.
-- no rows: it is the difference throughout.
SELECT OrderNumber, RequestedDeliveryDate, DaysUntilDue
FROM dbo.vw_SalesOrderBacklog
WHERE DaysUntilDue IS NULL
   OR DaysUntilDue <> DATEDIFF(DAY, GETDATE(), RequestedDeliveryDate)
;

-- an order still due, DaysUntilDue at zero or above.
-- no rows: every requested date, the last 2024-11-25, has passed.
SELECT OrderNumber, RequestedDeliveryDate, DaysUntilDue
FROM dbo.vw_SalesOrderBacklog
WHERE DaysUntilDue >= 0
;


-- ----------------------------------------------------------------------------------------------------------- LineItems
-- the values of LineItems; 1 on 4 orders, 2 on SO-2024-1104 and SO-2024-1107.
SELECT LineItems, COUNT(*) AS orders
FROM dbo.vw_SalesOrderBacklog
GROUP BY LineItems
ORDER BY LineItems ASC
;


-- ------------------------------------------------------------------------------------------------ TotalQuantityOrdered
-- the smallest and the largest TotalQuantityOrdered, and the sum; 2.00, 28.00 and 64.00.
SELECT MIN(TotalQuantityOrdered), MAX(TotalQuantityOrdered), SUM(TotalQuantityOrdered)
FROM dbo.vw_SalesOrderBacklog
;


-- ------------------------------------------------------------------------------------------------ TotalQuantityShipped
-- the orders with something shipped; SO-2024-1102 alone, all 3.00 of it. Nothing else has left.
SELECT OrderNumber, Status, TotalQuantityOrdered, TotalQuantityShipped
FROM dbo.vw_SalesOrderBacklog
WHERE TotalQuantityShipped > 0
;


-- ----------------------------------------------------------------------------------------------------- QuantityBacklog
-- a QuantityBacklog which is not TotalQuantityOrdered less TotalQuantityShipped.
-- no rows: it is the difference throughout.
SELECT OrderNumber, QuantityBacklog
FROM dbo.vw_SalesOrderBacklog
WHERE QuantityBacklog <> TotalQuantityOrdered - TotalQuantityShipped
;


-- --------------------------------------------------------------------------------------------------------- TotalAmount
-- a TotalAmount which is not the order's Subtotal less DiscountAmount plus TaxAmount and ShippingAmount.
-- no rows: it is that sum on every open order, 1355.98 to 11399.40.
SELECT v.OrderNumber, v.TotalAmount, so.Subtotal, so.DiscountAmount, so.TaxAmount, so.ShippingAmount
FROM dbo.vw_SalesOrderBacklog v
         JOIN dbo.SalesOrder so ON so.OrderNumber = v.OrderNumber
WHERE v.TotalAmount <> so.Subtotal - so.DiscountAmount + so.TaxAmount + so.ShippingAmount
;


-- ---------------------------------------------------------------------------------------------------------- OrderValue
-- an OrderValue, the LineTotal of the lines before their discounts, which is not the order's Subtotal.
-- 4 rows: SO-2024-1102 (1199.97, 1199.98), SO-2024-1104 (10999.75, 11999.40), SO-2024-1105 (2399.97, 2399.96) and
-- SO-2024-1107 (9599.72, 9599.52); the lines and the order header disagree.
SELECT v.OrderNumber, v.OrderValue, so.Subtotal
FROM dbo.vw_SalesOrderBacklog v
         JOIN dbo.SalesOrder so ON so.OrderNumber = v.OrderNumber
WHERE v.OrderValue <> so.Subtotal
ORDER BY v.OrderNumber ASC
;


-- -------------------------------------------------------------------------------------------------------- BacklogValue
-- a BacklogValue which is not the OrderValue on an order with nothing shipped, or not zero on one shipped in full;
-- it prices the unshipped units at UnitPrice, before the line discounts.
-- no rows: it is the OrderValue on the 5 unshipped orders, and .000000 on SO-2024-1102.
SELECT OrderNumber, OrderValue, BacklogValue
FROM dbo.vw_SalesOrderBacklog
WHERE BacklogValue <> CASE WHEN TotalQuantityShipped = 0 THEN OrderValue ELSE 0 END
;

-- the value of the backlog; 26399.390000 in all.
SELECT SUM(BacklogValue)
FROM dbo.vw_SalesOrderBacklog
;


-- ----------------------------------------------------------------------------------------------------- PercentComplete
-- the values of PercentComplete; .00 on 5 orders, 100.00 on SO-2024-1102.
SELECT PercentComplete, COUNT(*) AS orders
FROM dbo.vw_SalesOrderBacklog
GROUP BY PercentComplete
ORDER BY PercentComplete ASC
;


-- --------------------------------------------------------------------------------------------------- FulfillmentStatus
-- the values of FulfillmentStatus; Overdue 5 and Due Soon 1. These stay so as days pass, every requested date being
-- behind.
SELECT FulfillmentStatus, COUNT(*) AS orders
FROM dbo.vw_SalesOrderBacklog
GROUP BY FulfillmentStatus
ORDER BY FulfillmentStatus ASC
;

-- an order called Due Soon whose requested date has passed. The Overdue branch leaves out Shipped orders, and the
-- next branch takes any date up to 5 days ahead, a past one included.
-- 1 row: SO-2024-1102, Shipped and due 2024-11-10.
SELECT OrderNumber, Status, RequestedDeliveryDate, FulfillmentStatus
FROM dbo.vw_SalesOrderBacklog
WHERE FulfillmentStatus = 'Due Soon'
  AND RequestedDeliveryDate < CAST(GETDATE() AS DATE)
;

