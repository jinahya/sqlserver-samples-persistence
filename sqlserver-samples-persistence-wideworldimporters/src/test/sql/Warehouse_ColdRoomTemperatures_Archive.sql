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
-- Profiling queries on Warehouse.ColdRoomTemperatures_Archive, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_ColdRoomTemperatures_Archive.sql
--


-- ----------------------------------------------------------------------------------------------- ColdRoomTemperatureID
-- how many past versions, and the smallest and the largest ColdRoomTemperatureID; 3654736 rows,
-- 1 to 3654736.
SELECT COUNT(*), MIN(ColdRoomTemperatureID), MAX(ColdRoomTemperatureID)
FROM Warehouse.ColdRoomTemperatures_Archive
;

-- the history table has no key; an id kept more than once.
-- 0: each past reading is kept once.
SELECT COUNT(*)
FROM (SELECT ColdRoomTemperatureID
      FROM Warehouse.ColdRoomTemperatures_Archive
      GROUP BY ColdRoomTemperatureID
      HAVING COUNT(*) > 1) d
;


-- ------------------------------------------------------------------------------------------------ ColdRoomSensorNumber
-- the past readings of each sensor; 913684 for each of the 4.
SELECT ColdRoomSensorNumber, COUNT(*) AS readings
FROM Warehouse.ColdRoomTemperatures_Archive
GROUP BY ColdRoomSensorNumber
ORDER BY ColdRoomSensorNumber ASC
;


-- -------------------------------------------------------------------------------------------------------- RecordedWhen
-- the range of RecordedWhen; 2015-12-20 00:00:00 to 2016-05-31 23:59:00.
SELECT MIN(RecordedWhen), MAX(RecordedWhen)
FROM Warehouse.ColdRoomTemperatures_Archive
;

-- the fewest, the most and the average seconds between two readings of a sensor; 1 to 57, 15.5 on average.
SELECT MIN(seconds), MAX(seconds), AVG(seconds * 1.0)
FROM (SELECT DATEDIFF(SECOND, LAG(RecordedWhen) OVER (PARTITION BY ColdRoomSensorNumber ORDER BY RecordedWhen),
                      RecordedWhen) AS seconds
      FROM Warehouse.ColdRoomTemperatures_Archive) r
;


-- --------------------------------------------------------------------------------------------------------- Temperature
-- the range of Temperature, by sensor; 3.00 to 5.00 for each, averaging 4.00.
SELECT ColdRoomSensorNumber, MIN(Temperature), MAX(Temperature), AVG(Temperature)
FROM Warehouse.ColdRoomTemperatures_Archive
GROUP BY ColdRoomSensorNumber
ORDER BY ColdRoomSensorNumber ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- how many past versions have a ValidFrom other than their RecordedWhen; 0.
SELECT COUNT(*)
FROM Warehouse.ColdRoomTemperatures_Archive
WHERE ValidFrom <> RecordedWhen
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the range of ValidTo; 2015-12-20 00:00:27 to 2016-05-31 23:59:24.
SELECT MIN(ValidTo), MAX(ValidTo)
FROM Warehouse.ColdRoomTemperatures_Archive
;

-- how many versions do not end after they began; 0.
SELECT COUNT(*)
FROM Warehouse.ColdRoomTemperatures_Archive
WHERE ValidTo <= ValidFrom
;

