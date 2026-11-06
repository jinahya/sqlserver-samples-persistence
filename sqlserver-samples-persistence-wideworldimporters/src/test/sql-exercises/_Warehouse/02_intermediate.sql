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
---
--
-- Exercises at the Intermediate level on the Warehouse schema of WideWorldImporters.
--
-- These are the SQL solutions of the 'Warehouse' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_I_01
-- Count the stock items in every stock group, a group without items included, the most first; a tie by the group's
-- name.
SELECT sg.StockGroupName, COUNT(sisg.StockItemStockGroupID) AS StockItems
FROM Warehouse.StockGroups sg
         LEFT JOIN Warehouse.StockItemStockGroups sisg ON sisg.StockGroupID = sg.StockGroupID
GROUP BY sg.StockGroupID, sg.StockGroupName
ORDER BY StockItems DESC, sg.StockGroupName
;

-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_I_02
-- Compliance counts the stock items by country of manufacture, which is in the CustomFields JSON document, the most
-- first, a tie by country; the items with no country recorded count as one group. `JSON_VALUE` is SQL only.
SELECT JSON_VALUE(CustomFields, '$.CountryOfManufacture') AS CountryOfManufacture, COUNT(*) AS StockItems
FROM Warehouse.StockItems
GROUP BY JSON_VALUE(CustomFields, '$.CountryOfManufacture')
ORDER BY StockItems DESC, CountryOfManufacture
;

-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_I_03
-- The warehouse manager wants the movements of a stock item in a year: for each transaction type, the number of
-- transactions and the net quantity, by type name.
DECLARE @stockItemId int = 1, @from datetime2 = '2015-01-01', @to datetime2 = '2016-01-01';
SELECT tt.TransactionTypeName, COUNT(*) AS Transactions, SUM(sit.Quantity) AS Quantity
FROM Warehouse.StockItemTransactions sit
         JOIN Application.TransactionTypes tt ON tt.TransactionTypeID = sit.TransactionTypeID
WHERE sit.StockItemID = @stockItemId
  AND sit.TransactionOccurredWhen >= @from
  AND sit.TransactionOccurredWhen < @to
GROUP BY tt.TransactionTypeID, tt.TransactionTypeName
ORDER BY tt.TransactionTypeName
;

-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_I_04
-- For each stock item with movements in a period, the quantity in, the quantity out as a positive amount, and the net
-- movement, by item name.
DECLARE @from datetime2 = '2016-05-01', @to datetime2 = '2016-06-01';
SELECT si.StockItemID,
       si.StockItemName,
       SUM(CASE WHEN st.Quantity > 0 THEN st.Quantity ELSE 0 END)  AS Incoming,
       SUM(CASE WHEN st.Quantity < 0 THEN -st.Quantity ELSE 0 END) AS Outgoing,
       SUM(st.Quantity)                                             AS NetMovement
FROM Warehouse.StockItemTransactions st
         JOIN Warehouse.StockItems si ON si.StockItemID = st.StockItemID
WHERE st.TransactionOccurredWhen >= @from
  AND st.TransactionOccurredWhen < @to
GROUP BY si.StockItemID, si.StockItemName
ORDER BY si.StockItemName
;

