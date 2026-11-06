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
-- Exercises at the Advanced level on the Warehouse schema of WideWorldImporters.
--
-- These are the SQL solutions of the 'Warehouse' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- -------------------------------------------------------------------------------------------------- WWI_WAREHOUSE_A_01
-- Quality control checks the cold rooms over a period: for each sensor, the number of readings recorded in the period
-- and the lowest, highest and average temperature. Each reading is a version of a row of a system-versioned table, so
-- the query reads them all with `FOR SYSTEM_TIME ALL` and keeps those recorded in the period by `RecordedWhen`; `FROM
-- ... TO` would also return the reading still in force when the period began. This is SQL only.
DECLARE @from datetime2 = '2016-05-01', @to datetime2 = '2016-05-02';
SELECT ColdRoomSensorNumber,
       COUNT(*)                                 AS Readings,
       MIN(Temperature)                         AS Lowest,
       MAX(Temperature)                         AS Highest,
       CAST(AVG(Temperature) AS decimal(10, 2)) AS Average
FROM Warehouse.ColdRoomTemperatures FOR SYSTEM_TIME ALL
WHERE RecordedWhen >= @from
  AND RecordedWhen < @to
GROUP BY ColdRoomSensorNumber
ORDER BY ColdRoomSensorNumber
;

