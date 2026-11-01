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
-- Profiling queries on the view dbo.vw_CustomerFulfillmentRate, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The view is a row per active customer with at least one sales order, counting the orders Delivered against all of
-- them, and those shipped by the RequestedDeliveryDate.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_CustomerFulfillmentRate.sql
--


-- -------------------------------------------------------------------------------------------------------- CustomerCode
-- how many rows, and how many distinct CustomerCodes; 8 and 8, every customer is active and has ordered.
SELECT COUNT(*), COUNT(DISTINCT CustomerCode)
FROM dbo.vw_CustomerFulfillmentRate
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- a CustomerName other than the one of the CustomerCode.
-- no rows: the view copies it from Customer.
SELECT v.CustomerCode, v.CustomerName
FROM dbo.vw_CustomerFulfillmentRate v
         JOIN dbo.Customer c ON c.CustomerCode = v.CustomerCode
WHERE v.CustomerName <> c.CustomerName
;


-- --------------------------------------------------------------------------------------------------------- TotalOrders
-- a TotalOrders other than the customer's count of sales orders.
-- no rows: the view counts them all.
SELECT v.CustomerCode, v.TotalOrders, COUNT(*) AS orders
FROM dbo.vw_CustomerFulfillmentRate v
         JOIN dbo.Customer c ON c.CustomerCode = v.CustomerCode
         JOIN dbo.SalesOrder o ON o.CustomerID = c.CustomerID
GROUP BY v.CustomerCode, v.TotalOrders
HAVING v.TotalOrders <> COUNT(*)
;

-- the smallest and the largest TotalOrders, and their sum; 1, 3 and 15, every sales order.
SELECT MIN(TotalOrders), MAX(TotalOrders), SUM(TotalOrders)
FROM dbo.vw_CustomerFulfillmentRate
;


-- ----------------------------------------------------------------------------------------------------- TotalOrderValue
-- the smallest and the largest TotalOrderValue, and their sum against the TotalAmount of every sales order;
-- 1355.98, 20899.04, and 59026.78 against 59026.78.
SELECT MIN(TotalOrderValue), MAX(TotalOrderValue), SUM(TotalOrderValue),
       (SELECT SUM(TotalAmount) FROM dbo.SalesOrder) AS all_orders
FROM dbo.vw_CustomerFulfillmentRate
;


-- ----------------------------------------------------------------------------------------------------- DeliveredOrders
-- the sum of DeliveredOrders against the Delivered sales orders; 9 and 9.
SELECT SUM(DeliveredOrders), (SELECT COUNT(*) FROM dbo.SalesOrder WHERE Status = 'Delivered') AS delivered
FROM dbo.vw_CustomerFulfillmentRate
;


-- ---------------------------------------------------------------------------------------------------- OnTimeDeliveries
-- a customer with more OnTimeDeliveries than DeliveredOrders.
-- no rows: OnTimeDeliveries is a part of DeliveredOrders.
SELECT CustomerCode, DeliveredOrders, OnTimeDeliveries
FROM dbo.vw_CustomerFulfillmentRate
WHERE OnTimeDeliveries > DeliveredOrders
;

-- the sum of OnTimeDeliveries, against the sum of DeliveredOrders; 9 and 9, every delivery was on time.
SELECT SUM(OnTimeDeliveries), SUM(DeliveredOrders)
FROM dbo.vw_CustomerFulfillmentRate
;


-- ------------------------------------------------------------------------------------------------------ DeliveredValue
-- the sum of DeliveredValue; 30957.97.
SELECT SUM(DeliveredValue)
FROM dbo.vw_CustomerFulfillmentRate
;


-- ------------------------------------------------------------------------------------------------------- PendingOrders
-- a PendingOrders other than the TotalOrders less the DeliveredOrders.
-- no rows: the view computes it so.
SELECT CustomerCode, TotalOrders, DeliveredOrders, PendingOrders
FROM dbo.vw_CustomerFulfillmentRate
WHERE PendingOrders <> TotalOrders - DeliveredOrders
;

-- the customers by how many orders are pending; 2 with none, 6 with 1.
SELECT PendingOrders, COUNT(*) AS customers
FROM dbo.vw_CustomerFulfillmentRate
GROUP BY PendingOrders
ORDER BY PendingOrders ASC
;


-- -------------------------------------------------------------------------------------------------------- PendingValue
-- a PendingValue other than the TotalOrderValue less the DeliveredValue.
-- no rows: the view computes it so.
SELECT CustomerCode, TotalOrderValue, DeliveredValue, PendingValue
FROM dbo.vw_CustomerFulfillmentRate
WHERE PendingValue <> TotalOrderValue - DeliveredValue
;

-- the largest PendingValue; CUST-004 with 11399.40.
SELECT TOP (1) CustomerCode, PendingValue
FROM dbo.vw_CustomerFulfillmentRate
ORDER BY PendingValue DESC, CustomerCode ASC
;


-- ----------------------------------------------------------------------------------------------------- FulfillmentRate
-- the FulfillmentRate of each customer, the highest first; CUST-001 and CUST-008 at 100.00, CUST-005 at 66.67, four
-- at 50.00 and CUST-002, with its one order Shipped but not Delivered, at .00.
SELECT CustomerCode, DeliveredOrders, TotalOrders, FulfillmentRate
FROM dbo.vw_CustomerFulfillmentRate
ORDER BY FulfillmentRate DESC, CustomerCode ASC
;


-- -------------------------------------------------------------------------------------------------- OnTimeDeliveryRate
-- the values OnTimeDeliveryRate takes, NULL where nothing has been delivered; 100.00 for 7, NULL for 1.
SELECT OnTimeDeliveryRate, COUNT(*) AS customers
FROM dbo.vw_CustomerFulfillmentRate
GROUP BY OnTimeDeliveryRate
ORDER BY OnTimeDeliveryRate ASC
;


-- ------------------------------------------------------------------------------------------------------- AvgDaysToShip
-- the smallest and the largest AvgDaysToShip, an integer average, and how many are NULL; 3, 16 and 1.
SELECT MIN(AvgDaysToShip), MAX(AvgDaysToShip), COUNT(*) - COUNT(AvgDaysToShip)
FROM dbo.vw_CustomerFulfillmentRate
;


-- ------------------------------------------------------------------------------------------------- AvgDeliveryVariance
-- the smallest and the largest AvgDeliveryVariance, days shipped after the requested date, so negative is early,
-- and how many are NULL; -4, -1 and 1, every delivery came early.
SELECT MIN(AvgDeliveryVariance), MAX(AvgDeliveryVariance), COUNT(*) - COUNT(AvgDeliveryVariance)
FROM dbo.vw_CustomerFulfillmentRate
;


-- -------------------------------------------------------------------------------------------------------- ServiceLevel
-- the values ServiceLevel takes; Excellent for 7 and Poor for 1.
SELECT ServiceLevel, COUNT(*) AS customers
FROM dbo.vw_CustomerFulfillmentRate
GROUP BY ServiceLevel
ORDER BY ServiceLevel ASC
;

-- a customer with nothing delivered, whose OnTimeDeliveryRate is NULL, so the CASE falls through to Poor; CUST-002,
-- rated Poor for an order not yet delivered.
SELECT CustomerCode, DeliveredOrders, OnTimeDeliveryRate, ServiceLevel
FROM dbo.vw_CustomerFulfillmentRate
WHERE OnTimeDeliveryRate IS NULL
ORDER BY CustomerCode ASC
;

