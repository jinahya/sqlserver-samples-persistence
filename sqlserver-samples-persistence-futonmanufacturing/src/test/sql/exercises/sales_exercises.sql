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
-- Exercises on sales: dbo.SalesOrder, dbo.SalesOrderDetail, dbo.Customer and dbo.SalesChannel. Each poses a question,
-- and answers it in Transact-SQL against the installed FutonManufacturing database.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/exercises/sales_exercises.sql
--


-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_B_05
-- the orders not delivered yet, the earliest requested delivery date first.
SELECT so.OrderNumber, c.CustomerName, so.Status, so.RequestedDeliveryDate, so.NetAmount
FROM SalesOrder so
         JOIN Customer c ON c.CustomerID = so.CustomerID
WHERE so.Status <> 'Delivered'
ORDER BY so.RequestedDeliveryDate, so.OrderNumber
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_05
-- the number of orders and the net amount of each sales channel, the largest first.
SELECT ch.ChannelCode, ch.ChannelName, COUNT(*) AS Orders, SUM(so.NetAmount) AS NetAmount
FROM SalesOrder so
         JOIN SalesChannel ch ON ch.SalesChannelID = so.SalesChannelID
GROUP BY ch.SalesChannelID, ch.ChannelCode, ch.ChannelName
ORDER BY NetAmount DESC
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_I_06
-- the three futons sold the most, by quantity, with the net amount of their lines.
SELECT TOP(3) i.ItemCode, i.ItemName, SUM(d.Quantity) AS Quantity, SUM(d.NetAmount) AS NetAmount
FROM SalesOrderDetail d
         JOIN Items i ON i.ItemID = d.ItemID
GROUP BY i.ItemID, i.ItemCode, i.ItemName
ORDER BY Quantity DESC, i.ItemCode
;

-- ------------------------------------------------------------------------------------------------------ FUTON_DBO_A_04
-- the orders of the wholesale customers, each with the running total of its customer's net amount, in the order of
-- their dates.
SELECT c.CustomerCode,
       so.OrderNumber,
       so.OrderDate,
       so.NetAmount,
       SUM(so.NetAmount) OVER (PARTITION BY so.CustomerID
                               ORDER BY so.OrderDate, so.SalesOrderID
                               ROWS UNBOUNDED PRECEDING) AS RunningTotal
FROM SalesOrder so
         JOIN Customer c ON c.CustomerID = so.CustomerID
WHERE c.CustomerType = 'Wholesale'
ORDER BY c.CustomerCode, so.OrderDate, so.SalesOrderID
;

