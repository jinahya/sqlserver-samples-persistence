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
-- Profiling queries on the view Website.VehicleTemperatures, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Website_VehicleTemperatures.sql
--


-- ------------------------------------------------------------------------------------------------ VehicleTemperatureID
-- how many rows, and the smallest and the largest VehicleTemperatureID; 65998 readings, 1 to 65998.
SELECT COUNT(*), MIN(VehicleTemperatureID), MAX(VehicleTemperatureID)
FROM Website.VehicleTemperatures
;


-- ------------------------------------------------------------------------------------------------- VehicleRegistration
-- the readings of each vehicle; one vehicle, WWI-321-A, with all 65998.
SELECT VehicleRegistration, COUNT(*) AS readings
FROM Website.VehicleTemperatures
GROUP BY VehicleRegistration
ORDER BY VehicleRegistration ASC
;


-- ------------------------------------------------------------------------------------------------- ChillerSensorNumber
-- the readings of each sensor; 32999 for each of 1 and 2.
SELECT ChillerSensorNumber, COUNT(*) AS readings
FROM Website.VehicleTemperatures
GROUP BY ChillerSensorNumber
ORDER BY ChillerSensorNumber ASC
;


-- -------------------------------------------------------------------------------------------------------- RecordedWhen
-- the range of RecordedWhen; 2016-01-01 07:00 to 2016-05-31 15:57:19.
SELECT MIN(RecordedWhen), MAX(RecordedWhen)
FROM Website.VehicleTemperatures
;


-- --------------------------------------------------------------------------------------------------------- Temperature
-- the range of Temperature; 3.00 to 5.00.
SELECT MIN(Temperature), MAX(Temperature)
FROM Website.VehicleTemperatures
;


-- ------------------------------------------------------------------------------------------------------ FullSensorData
-- the view takes FullSensorData as stored, or decompresses CompressedSensorData; the readings with none, and the
-- shortest and the longest of the others; none is NULL, 195 and 196 characters.
SELECT COUNT(*) - COUNT(FullSensorData), MIN(LEN(FullSensorData)), MAX(LEN(FullSensorData))
FROM Website.VehicleTemperatures
;

-- how many readings differ from Warehouse.VehicleTemperatures in FullSensorData; 0, as no reading is compressed.
SELECT COUNT(*)
FROM Website.VehicleTemperatures v
         JOIN Warehouse.VehicleTemperatures w ON w.VehicleTemperatureID = v.VehicleTemperatureID
WHERE EXISTS (SELECT v.FullSensorData EXCEPT SELECT w.FullSensorData)
;

