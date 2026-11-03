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
-- Profiling queries on Warehouse.ColdRoomTemperatures, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_ColdRoomTemperatures.sql
--


-- ----------------------------------------------------------------------------------------------- ColdRoomTemperatureID
-- an identity column; how many rows, and the smallest and the largest ColdRoomTemperatureID;
-- 4 rows, 3654737 to 3654740, right after the ids of the past readings.
SELECT COUNT(*), MIN(ColdRoomTemperatureID), MAX(ColdRoomTemperatureID)
FROM Warehouse.ColdRoomTemperatures
;

-- an id taken by a past version too.
-- no rows: a new reading is a new row, not a new version of the old one.
SELECT t.ColdRoomTemperatureID
FROM Warehouse.ColdRoomTemperatures t
WHERE EXISTS (SELECT 1
              FROM Warehouse.ColdRoomTemperatures_Archive a
              WHERE a.ColdRoomTemperatureID = t.ColdRoomTemperatureID)
;


-- ------------------------------------------------------------------------------------------------ ColdRoomSensorNumber
-- the rows of each sensor; 4 sensors, 1 to 4, a row each: the table keeps the latest reading.
SELECT ColdRoomSensorNumber, COUNT(*) AS readings
FROM Warehouse.ColdRoomTemperatures
GROUP BY ColdRoomSensorNumber
ORDER BY ColdRoomSensorNumber ASC
;


-- -------------------------------------------------------------------------------------------------------- RecordedWhen
-- the range of RecordedWhen; 2016-05-31 23:59:24.0000000, a single moment.
SELECT MIN(RecordedWhen), MAX(RecordedWhen)
FROM Warehouse.ColdRoomTemperatures
;


-- --------------------------------------------------------------------------------------------------------- Temperature
-- the range of Temperature; 3.00 to 4.70.
SELECT MIN(Temperature), MAX(Temperature)
FROM Warehouse.ColdRoomTemperatures
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of ValidFrom; 2016-05-31 23:59:24.0000000, a single moment.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Warehouse.ColdRoomTemperatures
;

-- how many rows have a ValidFrom other than their RecordedWhen; 0.
SELECT COUNT(*)
FROM Warehouse.ColdRoomTemperatures
WHERE ValidFrom <> RecordedWhen
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the end of each current version; 9999-12-31 23:59:59.9999999, the largest datetime2, for all 4.
SELECT ValidTo, COUNT(*) AS readings
FROM Warehouse.ColdRoomTemperatures
GROUP BY ValidTo
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the past readings kept in Warehouse.ColdRoomTemperatures_Archive, by sensor; 913684 for each.
SELECT ColdRoomSensorNumber, COUNT(*) AS readings
FROM Warehouse.ColdRoomTemperatures_Archive
GROUP BY ColdRoomSensorNumber
ORDER BY ColdRoomSensorNumber ASC
;

