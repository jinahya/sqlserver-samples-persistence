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
-- Profiling queries on dbo.SalesOrderDetail, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_SalesOrderDetail.sql
--


-- ---------------------------------------------------------------------------------------------------------- SODetailID
-- how many rows, and the smallest and the largest SODetailID; 23 lines, 1 to 23.
SELECT COUNT(*), MIN(SODetailID), MAX(SODetailID)
FROM dbo.SalesOrderDetail
;


-- -------------------------------------------------------------------------------------------------------- SalesOrderID
-- the orders by how many lines they have; 8 with 1, 6 with 2 and 1 with 3.
SELECT lines, COUNT(*) AS orders
FROM (SELECT COUNT(*) AS lines
      FROM dbo.SalesOrderDetail
      GROUP BY SalesOrderID) c
GROUP BY lines
ORDER BY lines ASC
;


-- ---------------------------------------------------------------------------------------------------------- LineNumber
-- an order whose LineNumbers do not run 1, 2, ... without a gap, or repeat one.
-- no rows: every order numbers its lines from 1.
SELECT SalesOrderID, MIN(LineNumber), MAX(LineNumber), COUNT(*) AS lines, COUNT(DISTINCT LineNumber) AS numbers
FROM dbo.SalesOrderDetail
GROUP BY SalesOrderID
HAVING MIN(LineNumber) <> 1
    OR MAX(LineNumber) <> COUNT(*)
    OR COUNT(DISTINCT LineNumber) <> COUNT(*)
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- the types of the items sold; only finished goods (3), 23 lines of all 6 of them.
SELECT i.ItemTypeID, COUNT(*) AS lines, COUNT(DISTINCT d.ItemID) AS items
FROM dbo.SalesOrderDetail d
         JOIN dbo.Items i ON i.ItemID = d.ItemID
GROUP BY i.ItemTypeID
;

-- nothing keeps an item to one line per order.
-- no rows: no order sells an item twice.
SELECT SalesOrderID, ItemID, COUNT(*) AS lines
FROM dbo.SalesOrderDetail
GROUP BY SalesOrderID, ItemID
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the smallest and the largest Quantity, and how many are zero or less; 1.00, 20.00 and none.
SELECT MIN(Quantity), MAX(Quantity), SUM(CASE WHEN Quantity <= 0 THEN 1 ELSE 0 END)
FROM dbo.SalesOrderDetail
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- a UnitPrice other than the item's ListPrice; the price lists of the channels are not applied.
-- no rows: every line sells at the ListPrice.
SELECT d.SODetailID, d.ItemID, d.UnitPrice, i.ListPrice
FROM dbo.SalesOrderDetail d
         JOIN dbo.Items i ON i.ItemID = d.ItemID
WHERE d.UnitPrice <> i.ListPrice
;


-- ----------------------------------------------------------------------------------------------------- QuantityShipped
-- how many QuantityShipped values are NULL, how many are zero and how many are the whole Quantity; none, 7 and 16,
-- nothing shipped in part.
SELECT SUM(CASE WHEN QuantityShipped IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN QuantityShipped = 0 THEN 1 ELSE 0 END),
       SUM(CASE WHEN QuantityShipped = Quantity THEN 1 ELSE 0 END)
FROM dbo.SalesOrderDetail
;


-- ----------------------------------------------------------------------------------------------------------- LineTotal
-- LineTotal is computed, the Quantity at the UnitPrice.
-- no rows: every line computes it.
SELECT SODetailID, Quantity, UnitPrice, LineTotal
FROM dbo.SalesOrderDetail
WHERE LineTotal IS NULL
   OR LineTotal <> Quantity * UnitPrice
;


-- ----------------------------------------------------------------------------------------------------- DiscountPercent
-- the values DiscountPercent takes, by channel; .00 or 5.00 for RETAIL (1) and ONLINE (2), 10.00 for every WHOLESALE
-- (3) line.
SELECT o.SalesChannelID, d.DiscountPercent, COUNT(*) AS lines
FROM dbo.SalesOrderDetail d
         JOIN dbo.SalesOrder o ON o.SalesOrderID = d.SalesOrderID
GROUP BY o.SalesChannelID, d.DiscountPercent
ORDER BY o.SalesChannelID ASC, d.DiscountPercent ASC
;


-- ------------------------------------------------------------------------------------------------------ DiscountAmount
-- DiscountAmount is computed, the DiscountPercent of the LineTotal.
-- no rows: every line computes it.
SELECT SODetailID, LineTotal, DiscountPercent, DiscountAmount
FROM dbo.SalesOrderDetail
WHERE DiscountAmount IS NULL
   OR DiscountAmount <> LineTotal * DiscountPercent / 100
;

-- the orders whose DiscountAmount, rounded to the cent, is not the sum of the DiscountAmount of their lines, and the
-- smallest and the largest difference; 7 of the 15, off by -360.005 to 100.025.
SELECT COUNT(*), MIN(difference), MAX(difference)
FROM (SELECT o.SalesOrderID, o.DiscountAmount - SUM(d.DiscountAmount) AS difference
      FROM dbo.SalesOrder o
               JOIN dbo.SalesOrderDetail d ON d.SalesOrderID = o.SalesOrderID
      GROUP BY o.SalesOrderID, o.DiscountAmount
      HAVING o.DiscountAmount <> ROUND(SUM(d.DiscountAmount), 2)) s
;


-- ----------------------------------------------------------------------------------------------------------- NetAmount
-- NetAmount is computed, the LineTotal less the DiscountAmount.
-- no rows: every line computes it.
SELECT SODetailID, LineTotal, DiscountAmount, NetAmount
FROM dbo.SalesOrderDetail
WHERE NetAmount IS NULL
   OR NetAmount <> LineTotal - DiscountAmount
;

-- the smallest and the largest NetAmount, and their sum; 299.990000, 5399.910000 and 59173.635000.
SELECT MIN(NetAmount), MAX(NetAmount), SUM(NetAmount)
FROM dbo.SalesOrderDetail
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the lines returned, and the quantity; lines 1 and 5, 1.00 of 2.00 each.
SELECT d.SODetailID, d.Quantity, SUM(r.QuantityReturned) AS returned
FROM dbo.SalesOrderDetail d
         JOIN dbo.SalesReturnDetail r ON r.SODetailID = d.SODetailID
GROUP BY d.SODetailID, d.Quantity
ORDER BY d.SODetailID ASC
;

-- the items sold the most, by quantity; FG-FUT-002 with 57.00, FG-FUT-001 with 49.00 and FG-FUT-005 with 18.00.
SELECT TOP (3) d.ItemID, i.ItemCode, SUM(d.Quantity) AS quantity
FROM dbo.SalesOrderDetail d
         JOIN dbo.Items i ON i.ItemID = d.ItemID
GROUP BY d.ItemID, i.ItemCode
ORDER BY quantity DESC, d.ItemID ASC
;

