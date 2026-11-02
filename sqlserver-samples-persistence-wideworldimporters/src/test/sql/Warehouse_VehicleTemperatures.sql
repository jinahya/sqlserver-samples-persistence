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
-- Profiling queries on Warehouse.VehicleTemperatures, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_VehicleTemperatures.sql
--


-- ------------------------------------------------------------------------------------------------ VehicleTemperatureID
-- an identity column; how many rows, and the smallest and the largest VehicleTemperatureID;
-- 65998 readings, 1 to 65998.
SELECT COUNT(*), MIN(VehicleTemperatureID), MAX(VehicleTemperatureID)
FROM Warehouse.VehicleTemperatures
;


-- ------------------------------------------------------------------------------------------------- VehicleRegistration
-- the readings of each vehicle; one vehicle, WWI-321-A, with all 65998.
SELECT VehicleRegistration, COUNT(*) AS readings
FROM Warehouse.VehicleTemperatures
GROUP BY VehicleRegistration
ORDER BY VehicleRegistration ASC
;


-- ------------------------------------------------------------------------------------------------- ChillerSensorNumber
-- the readings of each sensor; 32999 for each of 1 and 2.
SELECT ChillerSensorNumber, COUNT(*) AS readings
FROM Warehouse.VehicleTemperatures
GROUP BY ChillerSensorNumber
ORDER BY ChillerSensorNumber ASC
;


-- -------------------------------------------------------------------------------------------------------- RecordedWhen
-- the range of RecordedWhen; 2016-01-01 07:00 to 2016-05-31 15:57:19.
SELECT MIN(RecordedWhen), MAX(RecordedWhen)
FROM Warehouse.VehicleTemperatures
;

-- the schema has no unique key on vehicle, sensor and moment; how many readings share all three;
-- 0.
SELECT COUNT(*)
FROM (SELECT VehicleRegistration, ChillerSensorNumber, RecordedWhen
      FROM Warehouse.VehicleTemperatures
      GROUP BY VehicleRegistration, ChillerSensorNumber, RecordedWhen
      HAVING COUNT(*) > 1) d
;


-- --------------------------------------------------------------------------------------------------------- Temperature
-- the range of Temperature, by sensor; 3.00 to 5.00 for each, averaging 4.00.
SELECT ChillerSensorNumber, MIN(Temperature), MAX(Temperature), AVG(Temperature)
FROM Warehouse.VehicleTemperatures
GROUP BY ChillerSensorNumber
ORDER BY ChillerSensorNumber ASC
;


-- -------------------------------------------------------------------------------------------------------- IsCompressed
-- the readings compressed, and those not; none is, all 65998 have 0.
SELECT IsCompressed, COUNT(*) AS readings
FROM Warehouse.VehicleTemperatures
GROUP BY IsCompressed
;


-- ------------------------------------------------------------------------------------------------------ FullSensorData
-- the readings with no FullSensorData, and the shortest and the longest of the others; none is NULL,
-- 195 and 196 characters, of the 1000 the column allows.
SELECT COUNT(*) - COUNT(FullSensorData), MIN(LEN(FullSensorData)), MAX(LEN(FullSensorData))
FROM Warehouse.VehicleTemperatures
;

-- how many FullSensorData are valid JSON; 0, a finding: every one is malformed.
SELECT COUNT(*)
FROM Warehouse.VehicleTemperatures
WHERE ISJSON(FullSensorData) = 1
;

-- how many FullSensorData write the sensor with no closing quote, as "sensor":"1,"when"; all 65998, what breaks
-- the JSON.
SELECT COUNT(*)
FROM Warehouse.VehicleTemperatures
WHERE FullSensorData LIKE '%"sensor":"[0-9],"when"%'
;

-- how many FullSensorData do not name their vehicle and their moment; 0.
SELECT COUNT(*)
FROM Warehouse.VehicleTemperatures
WHERE FullSensorData NOT LIKE '%"rego":"' + VehicleRegistration + '"%'
   OR FullSensorData NOT LIKE '%"when":"' + CONVERT(nvarchar(19), RecordedWhen, 126) + '"%'
;


-- ------------------------------------------------------------------------------------------------ CompressedSensorData
-- the readings with CompressedSensorData, and the smallest and the largest DATALENGTH; none,
-- every one is NULL.
SELECT COUNT(CompressedSensorData), MIN(DATALENGTH(CompressedSensorData)), MAX(DATALENGTH(CompressedSensorData))
FROM Warehouse.VehicleTemperatures
;

-- a reading which is compressed with no CompressedSensorData, or not compressed with no FullSensorData.
-- no rows: the data is where IsCompressed says.
SELECT VehicleTemperatureID
FROM Warehouse.VehicleTemperatures
WHERE (IsCompressed = 1 AND CompressedSensorData IS NULL)
   OR (IsCompressed = 0 AND FullSensorData IS NULL)
;

