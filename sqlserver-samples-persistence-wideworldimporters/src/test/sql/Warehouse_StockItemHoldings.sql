-- -
-- #%L
-- sqlserver-samples-persistence-wideworldimporters
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
-- Profiling queries on Warehouse.StockItemHoldings, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_StockItemHoldings.sql
--


-- --------------------------------------------------------------------------------------------------------- StockItemID
-- the primary key, and a foreign key to Warehouse.StockItems; how many rows, and the smallest and the
-- largest; 227 rows, 1 to 227, one per stock item.
SELECT COUNT(*), MIN(StockItemID), MAX(StockItemID)
FROM Warehouse.StockItemHoldings
;


-- ------------------------------------------------------------------------------------------------------ QuantityOnHand
-- the smallest and the largest QuantityOnHand; 3 and 1034169.
SELECT MIN(QuantityOnHand), MAX(QuantityOnHand)
FROM Warehouse.StockItemHoldings
;

-- how many stock items are on hand below their ReorderLevel; 2, 86 and 184.
SELECT COUNT(*)
FROM Warehouse.StockItemHoldings
WHERE QuantityOnHand < ReorderLevel
;

-- how many stock items have a QuantityOnHand other than the sum of their stock item transactions; all 227, a
-- finding: the transactions do not add up to the stock on hand.
SELECT COUNT(*)
FROM Warehouse.StockItemHoldings h
WHERE h.QuantityOnHand <> (SELECT SUM(t.Quantity)
                           FROM Warehouse.StockItemTransactions t
                           WHERE t.StockItemID = h.StockItemID)
;


-- --------------------------------------------------------------------------------------------------------- BinLocation
-- the shortest and the longest BinLocation, and how many distinct; 3 and 4 characters, 31 bins.
SELECT MIN(LEN(BinLocation)), MAX(LEN(BinLocation)), COUNT(DISTINCT BinLocation)
FROM Warehouse.StockItemHoldings
;

-- a BinLocation which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every BinLocation is trimmed and not blank.
SELECT StockItemID, '[' + BinLocation + ']' AS BinLocation
FROM Warehouse.StockItemHoldings
WHERE TRIM(BinLocation) = ''
   OR DATALENGTH(BinLocation) <> DATALENGTH(TRIM(BinLocation))
;

-- BinLocation has no unique key; the bins shared by the most stock items; K-9 with 42, D-1 with
-- 21, K-5 with 20.
SELECT TOP (3) BinLocation, COUNT(*) AS stock_items
FROM Warehouse.StockItemHoldings
GROUP BY BinLocation
ORDER BY stock_items DESC, BinLocation ASC
;


-- ----------------------------------------------------------------------------------------------- LastStocktakeQuantity
-- the smallest and the largest LastStocktakeQuantity; 1 and 1009444.
SELECT MIN(LastStocktakeQuantity), MAX(LastStocktakeQuantity)
FROM Warehouse.StockItemHoldings
;

-- how many stock items have more on hand than at the last stocktake, fewer, or the same; 217 more, 8 fewer, 2 the same.
SELECT SIGN(QuantityOnHand - LastStocktakeQuantity) AS since_stocktake, COUNT(*) AS stock_items
FROM Warehouse.StockItemHoldings
GROUP BY SIGN(QuantityOnHand - LastStocktakeQuantity)
ORDER BY since_stocktake ASC
;


-- ------------------------------------------------------------------------------------------------------- LastCostPrice
-- the range of LastCostPrice; .36 to 1140.00.
SELECT MIN(LastCostPrice), MAX(LastCostPrice)
FROM Warehouse.StockItemHoldings
;

-- how many stock items cost more than their UnitPrice; 4, a finding: 142 to 145 cost 19.00 and sell for 18.00.
SELECT COUNT(*)
FROM Warehouse.StockItemHoldings h
         JOIN Warehouse.StockItems s ON s.StockItemID = h.StockItemID
WHERE h.LastCostPrice > s.UnitPrice
;


-- -------------------------------------------------------------------------------------------------------- ReorderLevel
-- the smallest and the largest ReorderLevel; 1 and 240.
SELECT MIN(ReorderLevel), MAX(ReorderLevel)
FROM Warehouse.StockItemHoldings
;


-- ---------------------------------------------------------------------------------------------------- TargetStockLevel
-- the smallest and the largest TargetStockLevel; 2 and 500.
SELECT MIN(TargetStockLevel), MAX(TargetStockLevel)
FROM Warehouse.StockItemHoldings
;

-- how many stock items have a TargetStockLevel not above their ReorderLevel; 0.
SELECT COUNT(*)
FROM Warehouse.StockItemHoldings
WHERE TargetStockLevel <= ReorderLevel
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the rows; person 3 for 164, 16 for 63.
SELECT LastEditedBy, COUNT(*) AS stock_items
FROM Warehouse.StockItemHoldings
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- the range of LastEditedWhen; 2016-05-31 07:00 to 12:00.
SELECT MIN(LastEditedWhen), MAX(LastEditedWhen)
FROM Warehouse.StockItemHoldings
;

