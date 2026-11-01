-- #%L
-- mssql-sample-databases-persistence-wideworldimporters
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
-- Exercises on the Warehouse schema of WideWorldImporters.
--
-- These are the SQL solutions of the 'Warehouse' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_B_01
-- The warehouse manager wants the stock items whose quantity on hand is below the reorder level, by name.
SELECT si.StockItemID, si.StockItemName, h.QuantityOnHand, h.ReorderLevel
FROM Warehouse.StockItemHoldings h
         JOIN Warehouse.StockItems si ON si.StockItemID = h.StockItemID
WHERE h.QuantityOnHand < h.ReorderLevel
ORDER BY si.StockItemName
;


-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_B_02
-- The cold room keeps the chiller stock: list those stock items by name, with their supplier and unit price.
SELECT si.StockItemID, si.StockItemName, s.SupplierName, si.UnitPrice
FROM Warehouse.StockItems si
         JOIN Purchasing.Suppliers s ON s.SupplierID = si.SupplierID
WHERE si.IsChillerStock = 1
ORDER BY si.StockItemName
;


-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_I_01
-- The catalogue team counts the stock items of each stock group, the largest group first.
SELECT sg.StockGroupName, COUNT(*) AS StockItems
FROM Warehouse.StockItemStockGroups sisg
         JOIN Warehouse.StockGroups sg ON sg.StockGroupID = sisg.StockGroupID
GROUP BY sg.StockGroupID, sg.StockGroupName
ORDER BY StockItems DESC, sg.StockGroupName
;


-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_I_02
-- Compliance counts the stock items by country of manufacture, which is in the CustomFields JSON document; JSON_VALUE
-- is SQL only.
SELECT JSON_VALUE(CustomFields, '$.CountryOfManufacture') AS CountryOfManufacture, COUNT(*) AS StockItems
FROM Warehouse.StockItems
GROUP BY JSON_VALUE(CustomFields, '$.CountryOfManufacture')
ORDER BY StockItems DESC
;


-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_A_01
-- Quality control checks the cold rooms over a period: for each sensor, the number of readings and the lowest,
-- highest and average temperature. The readings of the period are former versions of the rows of a system-versioned
-- table, read with FOR SYSTEM_TIME FROM ... TO, which is SQL only.
DECLARE
@from datetime2 = '2016-05-01', @to datetime2 = '2016-05-02';
SELECT ColdRoomSensorNumber,
       COUNT(*)                                 AS Readings,
       MIN(Temperature)                         AS Lowest,
       MAX(Temperature)                         AS Highest,
       CAST(AVG(Temperature) AS decimal(10, 2)) AS Average
FROM Warehouse.ColdRoomTemperatures FOR SYSTEM_TIME FROM @from TO @to
GROUP BY ColdRoomSensorNumber
ORDER BY ColdRoomSensorNumber
;
