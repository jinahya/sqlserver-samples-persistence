-- -
-- #%L
-- sql-server-samples-persistence-wideworldimporters
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
-- Profiling queries on Sales.OrderLines, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_OrderLines.sql
--


-- --------------------------------------------------------------------------------------------------------- OrderLineID
-- how many rows, and the smallest and the largest OrderLineID; 231412 lines, 1 to 231412.
SELECT COUNT(*), MIN(OrderLineID), MAX(OrderLineID)
FROM Sales.OrderLines
;

-- an OrderLineID whose next value is missing, below the largest.
-- no rows: OrderLineID runs from 1 without a gap.
SELECT a.OrderLineID + 1 AS missing_from
FROM Sales.OrderLines a
WHERE a.OrderLineID < (SELECT MAX(OrderLineID) FROM Sales.OrderLines)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.OrderLines b
                  WHERE b.OrderLineID = a.OrderLineID + 1)
;


-- ------------------------------------------------------------------------------------------------------------- OrderID
-- the fewest and the most lines an order has, and the average; 1 to 5, 3.14 on average.
SELECT MIN(lines), MAX(lines), AVG(lines * 1.0)
FROM (SELECT OrderID, COUNT(*) AS lines
      FROM Sales.OrderLines
      GROUP BY OrderID) o
;


-- --------------------------------------------------------------------------------------------------------- StockItemID
-- how many stock items are ordered, of how many there are; all 227.
SELECT COUNT(DISTINCT l.StockItemID), (SELECT COUNT(*) FROM Warehouse.StockItems)
FROM Sales.OrderLines l
;

-- how many orders have the same stock item on more than one line; 0.
SELECT COUNT(*)
FROM (SELECT OrderID, StockItemID
      FROM Sales.OrderLines
      GROUP BY OrderID, StockItemID
      HAVING COUNT(*) > 1) d
;


-- --------------------------------------------------------------------------------------------------------- Description
-- the shortest and the longest Description; 20 and 85 characters, of the 100 the column allows.
SELECT MIN(LEN(Description)), MAX(LEN(Description))
FROM Sales.OrderLines
;

-- a Description which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- 0: every Description is trimmed and not blank.
SELECT COUNT(*)
FROM Sales.OrderLines
WHERE TRIM(Description) = ''
   OR DATALENGTH(Description) <> DATALENGTH(TRIM(Description))
;

-- how many lines have a Description other than the name of their stock item; 0.
SELECT COUNT(*)
FROM Sales.OrderLines l
         JOIN Warehouse.StockItems s ON s.StockItemID = l.StockItemID
WHERE l.Description <> s.StockItemName
;


-- ------------------------------------------------------------------------------------------------------- PackageTypeID
-- the lines of each package type; 220955 Each, 5209 Packet, 4212 Pair, 1036 Bag.
SELECT l.PackageTypeID, p.PackageTypeName, COUNT(*) AS lines
FROM Sales.OrderLines l
         JOIN Warehouse.PackageTypes p ON p.PackageTypeID = l.PackageTypeID
GROUP BY l.PackageTypeID, p.PackageTypeName
ORDER BY l.PackageTypeID ASC
;

-- how many lines have a package type other than the unit package of their stock item; 0.
SELECT COUNT(*)
FROM Sales.OrderLines l
         JOIN Warehouse.StockItems s ON s.StockItemID = l.StockItemID
WHERE l.PackageTypeID <> s.UnitPackageID
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the smallest and the largest Quantity; 1 and 360.
SELECT MIN(Quantity), MAX(Quantity)
FROM Sales.OrderLines
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- the lines with no UnitPrice, and the range of the others; none is NULL,
-- .66 to 1899.00.
SELECT COUNT(*) - COUNT(UnitPrice), MIN(UnitPrice), MAX(UnitPrice)
FROM Sales.OrderLines
;

-- how many lines have a UnitPrice other than the current UnitPrice of their stock item; 512, a finding: lines of
-- stock items 1 to 15, priced at 10 or 15 percent of the current price.
SELECT COUNT(*)
FROM Sales.OrderLines l
         JOIN Warehouse.StockItems s ON s.StockItemID = l.StockItemID
WHERE l.UnitPrice <> s.UnitPrice
;


-- ------------------------------------------------------------------------------------------------------------- TaxRate
-- the distinct tax rates; 10.000 for 1036 lines, 15.000 for 230376.
SELECT TaxRate, COUNT(*) AS lines
FROM Sales.OrderLines
GROUP BY TaxRate
ORDER BY TaxRate ASC
;


-- ------------------------------------------------------------------------------------------------------ PickedQuantity
-- a line picked more than ordered.
-- no rows: no PickedQuantity exceeds its Quantity.
SELECT OrderLineID, Quantity, PickedQuantity
FROM Sales.OrderLines
WHERE PickedQuantity > Quantity
;

-- the lines picked in full, in part and not at all; 228265 in full, 3147 not at all, none in part.
SELECT CASE WHEN PickedQuantity = Quantity THEN 'full' WHEN PickedQuantity = 0 THEN 'none' ELSE 'part' END AS picked,
       COUNT(*) AS lines
FROM Sales.OrderLines
GROUP BY CASE WHEN PickedQuantity = Quantity THEN 'full' WHEN PickedQuantity = 0 THEN 'none' ELSE 'part' END
;


-- ------------------------------------------------------------------------------------------------ PickingCompletedWhen
-- the lines not picked yet, and the range of the others; 3147 not picked,
-- the others from 2013-01-01 11:00 to 2016-05-31 11:00.
SELECT COUNT(*) - COUNT(PickingCompletedWhen), MIN(PickingCompletedWhen), MAX(PickingCompletedWhen)
FROM Sales.OrderLines
;

-- how many lines are picked when their order is not, or the other way around; 0.
SELECT COUNT(*)
FROM Sales.OrderLines l
         JOIN Sales.Orders o ON o.OrderID = l.OrderID
WHERE (l.PickingCompletedWhen IS NULL AND o.PickingCompletedWhen IS NOT NULL)
   OR (l.PickingCompletedWhen IS NOT NULL AND o.PickingCompletedWhen IS NULL)
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the most rows; 7 with 14952, 17 with 14215, 4 with 14014.
SELECT TOP (3) LastEditedBy, COUNT(*) AS lines
FROM Sales.OrderLines
GROUP BY LastEditedBy
ORDER BY lines DESC, LastEditedBy ASC
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- the range of LastEditedWhen; 2013-01-01 11:00 to 2016-05-31 12:00.
SELECT MIN(LastEditedWhen), MAX(LastEditedWhen)
FROM Sales.OrderLines
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the stock items ordered most, by quantity; 191 with 207324, 192 with 193680, 189 with 158626.
SELECT TOP (3) l.StockItemID, SUM(l.Quantity) AS quantity
FROM Sales.OrderLines l
GROUP BY l.StockItemID
ORDER BY quantity DESC, l.StockItemID ASC
;

