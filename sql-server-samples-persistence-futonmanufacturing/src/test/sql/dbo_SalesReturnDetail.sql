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
-- Profiling queries on dbo.SalesReturnDetail, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_SalesReturnDetail.sql
--


-- ------------------------------------------------------------------------------------------------------ ReturnDetailID
-- how many rows, and the smallest and the largest ReturnDetailID; 2 lines, 1 and 2.
SELECT COUNT(*), MIN(ReturnDetailID), MAX(ReturnDetailID)
FROM dbo.SalesReturnDetail
;


-- ------------------------------------------------------------------------------------------------------------ ReturnID
-- the lines of each return; 1 each.
SELECT ReturnID, COUNT(*) AS lines
FROM dbo.SalesReturnDetail
GROUP BY ReturnID
ORDER BY ReturnID ASC
;


-- ---------------------------------------------------------------------------------------------------------- SODetailID
-- a line returning an order line of another order than its return's.
-- no rows: every line returns a line of the order its return is of.
SELECT d.ReturnDetailID, r.SalesOrderID, s.SalesOrderID AS line_order
FROM dbo.SalesReturnDetail d
         JOIN dbo.SalesReturn r ON r.ReturnID = d.ReturnID
         JOIN dbo.SalesOrderDetail s ON s.SODetailID = d.SODetailID
WHERE r.SalesOrderID <> s.SalesOrderID
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- a line whose ItemID is not the item of the order line it returns; both are kept, nothing ties them.
-- no rows: every line returns the item of its order line.
SELECT d.ReturnDetailID, d.ItemID, s.ItemID AS line_item
FROM dbo.SalesReturnDetail d
         JOIN dbo.SalesOrderDetail s ON s.SODetailID = d.SODetailID
WHERE d.ItemID <> s.ItemID
;


-- ---------------------------------------------------------------------------------------------------- QuantityReturned
-- a line returning more than was shipped, or nothing.
-- no rows: every QuantityReturned is above zero and at most the QuantityShipped.
SELECT d.ReturnDetailID, d.QuantityReturned, s.QuantityShipped
FROM dbo.SalesReturnDetail d
         JOIN dbo.SalesOrderDetail s ON s.SODetailID = d.SODetailID
WHERE d.QuantityReturned <= 0
   OR d.QuantityReturned > s.QuantityShipped
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- a UnitPrice other than the one of the order line.
-- no rows: every line refunds at the price it was sold at.
SELECT d.ReturnDetailID, d.UnitPrice, s.UnitPrice AS line_price
FROM dbo.SalesReturnDetail d
         JOIN dbo.SalesOrderDetail s ON s.SODetailID = d.SODetailID
WHERE d.UnitPrice <> s.UnitPrice
;


-- -------------------------------------------------------------------------------------------------------- RefundAmount
-- a RefundAmount other than the QuantityReturned at the UnitPrice.
-- no rows: every line refunds the full price.
SELECT ReturnDetailID, QuantityReturned, UnitPrice, RefundAmount
FROM dbo.SalesReturnDetail
WHERE RefundAmount <> ROUND(QuantityReturned * UnitPrice, 2)
;

-- the RefundAmount against what the customer paid for the units, the line's NetAmount for each unit; both lines
-- refund the full price, 399.99 and 899.99, of units sold at 5.00 percent off, for 379.99 and 854.99.
SELECT d.ReturnDetailID, d.RefundAmount, s.DiscountPercent,
       CAST(s.NetAmount / s.Quantity * d.QuantityReturned AS decimal(18, 2)) AS paid
FROM dbo.SalesReturnDetail d
         JOIN dbo.SalesOrderDetail s ON s.SODetailID = d.SODetailID
ORDER BY d.ReturnDetailID ASC
;


-- --------------------------------------------------------------------------------------------------------- Disposition
-- the values Disposition takes, NULL for none; Restock and Scrap, one each.
SELECT Disposition, COUNT(*) AS lines
FROM dbo.SalesReturnDetail
GROUP BY Disposition
ORDER BY Disposition ASC
;

