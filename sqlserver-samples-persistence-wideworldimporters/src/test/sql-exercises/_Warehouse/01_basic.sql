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
-- Exercises at the Basic level on the Warehouse schema of WideWorldImporters.
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

-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_B_03
-- The transport team wants the ten latest chiller readings of the delivery vehicles, from the
-- Website.VehicleTemperatures view, with the vehicle, sensor, temperature and full sensor data; `VehicleTemperatureID`
-- breaks a tie.
SELECT TOP (10) VehicleTemperatureID, VehicleRegistration, ChillerSensorNumber, RecordedWhen, Temperature, FullSensorData
FROM Website.VehicleTemperatures
ORDER BY RecordedWhen DESC, VehicleTemperatureID DESC
;

