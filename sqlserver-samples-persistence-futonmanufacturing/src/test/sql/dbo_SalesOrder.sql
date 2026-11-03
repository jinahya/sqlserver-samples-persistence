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
-- Profiling queries on dbo.SalesOrder, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_SalesOrder.sql
--


-- -------------------------------------------------------------------------------------------------------- SalesOrderID
-- how many rows, and the smallest and the largest SalesOrderID; 15 orders, 1 to 15.
SELECT COUNT(*), MIN(SalesOrderID), MAX(SalesOrderID)
FROM dbo.SalesOrder
;


-- --------------------------------------------------------------------------------------------------------- OrderNumber
-- an OrderNumber not in the form SO-YYYY-MMNN, the year and the month of the OrderDate and a running number.
-- no rows: every number reads SO-2024-, the month, and 01 to 08 within it, SO-2024-1001 to SO-2024-1107.
SELECT SalesOrderID, OrderNumber, OrderDate
FROM dbo.SalesOrder
WHERE OrderNumber NOT LIKE 'SO-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'
   OR SUBSTRING(OrderNumber, 4, 4) <> CAST(YEAR(OrderDate) AS char(4))
   OR CAST(SUBSTRING(OrderNumber, 9, 2) AS int) <> MONTH(OrderDate)
;

-- the shortest and the longest OrderNumber; 12 and 12 characters, of the 50 the column allows.
SELECT MIN(LEN(OrderNumber)), MAX(LEN(OrderNumber))
FROM dbo.SalesOrder
;

-- an OrderNumber which is blank, or carries leading or trailing spaces.
-- no rows: every OrderNumber is trimmed and not blank.
SELECT SalesOrderID, '[' + OrderNumber + ']' AS OrderNumber
FROM dbo.SalesOrder
WHERE TRIM(OrderNumber) = ''
   OR DATALENGTH(OrderNumber) <> DATALENGTH(TRIM(OrderNumber))
;

-- a unique constraint keeps OrderNumber unique.
-- no rows: every OrderNumber is distinct.
SELECT OrderNumber, COUNT(*) AS orders
FROM dbo.SalesOrder
GROUP BY OrderNumber
HAVING COUNT(*) > 1
;


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- the fewest and the most orders of a customer; 1 and 3.
SELECT MIN(orders), MAX(orders)
FROM (SELECT COUNT(*) AS orders
      FROM dbo.SalesOrder
      GROUP BY CustomerID) c
;


-- --------------------------------------------------------------------------------------------------------- WarehouseID
-- the orders shipped from each warehouse; 11 from warehouse 1 and 4 from 2.
SELECT WarehouseID, COUNT(*) AS orders
FROM dbo.SalesOrder
GROUP BY WarehouseID
ORDER BY WarehouseID ASC
;


-- ----------------------------------------------------------------------------------------------------------- OrderDate
-- the earliest and the latest OrderDate; 2024-10-01 and 2024-11-07.
SELECT MIN(OrderDate), MAX(OrderDate)
FROM dbo.SalesOrder
;


-- ----------------------------------------------------------------------------------------------- RequestedDeliveryDate
-- how many RequestedDeliveryDate values are NULL, and the fewest and the most days after the OrderDate; none, 4 and
-- 18.
SELECT SUM(CASE WHEN RequestedDeliveryDate IS NULL THEN 1 ELSE 0 END),
       MIN(DATEDIFF(DAY, OrderDate, RequestedDeliveryDate)), MAX(DATEDIFF(DAY, OrderDate, RequestedDeliveryDate))
FROM dbo.SalesOrder
;


-- ------------------------------------------------------------------------------------------------------------ ShipDate
-- the ShipDate of each Status, NULL for none; all 9 Delivered orders have one, and none of the others, not even
-- the Shipped one.
SELECT Status, SUM(CASE WHEN ShipDate IS NULL THEN 1 ELSE 0 END) AS not_shipped, COUNT(ShipDate) AS shipped
FROM dbo.SalesOrder
GROUP BY Status
ORDER BY Status ASC
;

-- an order shipped before it was placed, or after the requested delivery date.
-- no rows: every ShipDate falls between the two.
SELECT SalesOrderID, OrderDate, ShipDate, RequestedDeliveryDate
FROM dbo.SalesOrder
WHERE ShipDate < OrderDate
   OR ShipDate > RequestedDeliveryDate
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- the values Status takes, the first key of IX_SO_Status; Delivered 9, Confirmed 4, InProduction 1 and Shipped 1.
SELECT Status, COUNT(*) AS orders
FROM dbo.SalesOrder
GROUP BY Status
ORDER BY orders DESC, Status ASC
;


-- ------------------------------------------------------------------------------------------------------------ Subtotal
-- how many Subtotal values are NULL, and the smallest and the largest; none, 599.99 and 11999.40.
SELECT SUM(CASE WHEN Subtotal IS NULL THEN 1 ELSE 0 END), MIN(Subtotal), MAX(Subtotal)
FROM dbo.SalesOrder
;

-- the orders whose Subtotal is not the sum of the LineTotal of their lines, and the smallest and the largest
-- difference; 10 of the 15, off by -3600.05 to 999.65: the Subtotal was written by hand, not summed.
SELECT COUNT(*), MIN(difference), MAX(difference)
FROM (SELECT o.SalesOrderID, o.Subtotal - SUM(d.LineTotal) AS difference
      FROM dbo.SalesOrder o
               JOIN dbo.SalesOrderDetail d ON d.SalesOrderID = o.SalesOrderID
      GROUP BY o.SalesOrderID, o.Subtotal
      HAVING o.Subtotal <> SUM(d.LineTotal)) s
;


-- ----------------------------------------------------------------------------------------------------------- TaxAmount
-- the TaxAmount as a share of the Subtotal, by channel; about .08 for RETAIL (1) and ONLINE (2), rounded to the cent,
-- and none for WHOLESALE (3).
SELECT SalesChannelID, MIN(TaxAmount / Subtotal) AS lowest, MAX(TaxAmount / Subtotal) AS highest
FROM dbo.SalesOrder
GROUP BY SalesChannelID
ORDER BY SalesChannelID ASC
;


-- ------------------------------------------------------------------------------------------------------ ShippingAmount
-- how many ShippingAmount values are NULL, and the smallest and the largest; none, 25.00 and 600.00.
SELECT SUM(CASE WHEN ShippingAmount IS NULL THEN 1 ELSE 0 END), MIN(ShippingAmount), MAX(ShippingAmount)
FROM dbo.SalesOrder
;


-- --------------------------------------------------------------------------------------------------------- TotalAmount
-- an order whose TotalAmount is not the Subtotal, the TaxAmount and the ShippingAmount less the DiscountAmount.
-- no rows: TotalAmount is already net of the discount.
SELECT SalesOrderID, Subtotal, TaxAmount, ShippingAmount, DiscountAmount, TotalAmount
FROM dbo.SalesOrder
WHERE TotalAmount IS NULL
   OR TotalAmount <> Subtotal + TaxAmount + ShippingAmount - DiscountAmount
;


-- --------------------------------------------------------------------------------------------------------------- Notes
-- how many Notes values are NULL, of all the rows; 15 of 15.
SELECT SUM(CASE WHEN Notes IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.SalesOrder
;


-- ----------------------------------------------------------------------------------------------------------- CreatedBy
-- the values CreatedBy takes; system for all 15.
SELECT CreatedBy, COUNT(*) AS orders
FROM dbo.SalesOrder
GROUP BY CreatedBy
;


-- --------------------------------------------------------------------------------------------------------- CreatedDate
-- how many CreatedDate values are NULL, and how many fall outside the five minutes after the database was created;
-- none, and none: the default GETDATE() filled every one in while the load script ran, so it is the moment of
-- the load, not of a business event, and moves with every load.
SELECT SUM(CASE WHEN x.CreatedDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.CreatedDate NOT BETWEEN d.create_date AND DATEADD(MINUTE, 5, d.create_date) THEN 1 ELSE 0 END)
FROM dbo.SalesOrder x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- an order modified at another moment than it was created.
-- no rows: no order has been modified since the load.
SELECT SalesOrderID, CreatedDate, ModifiedDate
FROM dbo.SalesOrder
WHERE ModifiedDate IS NULL
   OR ModifiedDate <> CreatedDate
;


-- ------------------------------------------------------------------------------------------------------ SalesChannelID
-- the orders of each channel, NULL for none; RETAIL (1) 6, ONLINE (2) 4 and WHOLESALE (3) 5, none NULL.
SELECT SalesChannelID, COUNT(*) AS orders
FROM dbo.SalesOrder
GROUP BY SalesChannelID
ORDER BY SalesChannelID ASC
;


-- ------------------------------------------------------------------------------------------------------------- StoreID
-- how many StoreID values are NULL, of all the rows; none of the 15.
SELECT SUM(CASE WHEN StoreID IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.SalesOrder
;


-- ---------------------------------------------------------------------------------------------------------- SalesRepID
-- how many SalesRepID values are NULL, of all the rows; none of the 15.
SELECT SUM(CASE WHEN SalesRepID IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.SalesOrder
;


-- ------------------------------------------------------------------------------------------------------ DiscountAmount
-- the DiscountAmount as a share of the Subtotal, by channel; none or about .05 for RETAIL (1) and ONLINE (2), about
-- .10 for every WHOLESALE (3) order.
SELECT SalesChannelID, MIN(DiscountAmount / Subtotal) AS lowest, MAX(DiscountAmount / Subtotal) AS highest
FROM dbo.SalesOrder
GROUP BY SalesChannelID
ORDER BY SalesChannelID ASC
;


-- ----------------------------------------------------------------------------------------------------------- NetAmount
-- NetAmount is computed, the TotalAmount less the DiscountAmount.
-- no rows: every row computes it.
SELECT SalesOrderID, TotalAmount, DiscountAmount, NetAmount
FROM dbo.SalesOrder
WHERE NetAmount IS NULL
   OR NetAmount <> TotalAmount - DiscountAmount
;

-- the orders with a discount, whose NetAmount takes it off a second time, the TotalAmount being net of it already;
-- 12 orders, 5114.96 taken off twice.
SELECT COUNT(*), SUM(DiscountAmount)
FROM dbo.SalesOrder
WHERE DiscountAmount > 0
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an order with no line.
-- no rows: every order has lines.
SELECT o.SalesOrderID
FROM dbo.SalesOrder o
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.SalesOrderDetail d
                  WHERE d.SalesOrderID = o.SalesOrderID)
;

-- the lines of each Status, and how many have been shipped in full; all 15 Delivered and the 1 Shipped, none of the
-- 6 Confirmed or the 1 InProduction.
SELECT o.Status, COUNT(*) AS lines, SUM(CASE WHEN d.QuantityShipped = d.Quantity THEN 1 ELSE 0 END) AS shipped_in_full
FROM dbo.SalesOrder o
         JOIN dbo.SalesOrderDetail d ON d.SalesOrderID = o.SalesOrderID
GROUP BY o.Status
ORDER BY o.Status ASC
;

