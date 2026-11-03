-- -
-- #%L
-- sqlserver-samples-persistence-futonmanufacturing
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
-- Profiling queries on the view dbo.vw_WorkCenterCapacity, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_WorkCenterCapacity.sql
--


-- ------------------------------------------------------------------------------------------------------ WorkCenterCode
-- how many rows, and how many distinct WorkCenterCode, against the active work centers; 5, 5 and 5, a row each.
SELECT COUNT(*), COUNT(DISTINCT WorkCenterCode), (SELECT COUNT(*) FROM dbo.WorkCenter WHERE IsActive = 1)
FROM dbo.vw_WorkCenterCapacity
;


-- ------------------------------------------------------------------------------------------------------ WorkCenterName
-- a WorkCenterName which is not the one of its WorkCenterCode.
-- no rows: each matches WorkCenter.
SELECT v.WorkCenterCode, v.WorkCenterName
FROM dbo.vw_WorkCenterCapacity v
         JOIN dbo.WorkCenter wc ON wc.WorkCenterCode = v.WorkCenterCode
WHERE v.WorkCenterName <> wc.WorkCenterName
;


-- ------------------------------------------------------------------------------------------------------- DailyCapacity
-- a DailyCapacity which is not the Capacity of the work center.
-- no rows: they agree, 30.00 to 60.00.
SELECT v.WorkCenterCode, v.DailyCapacity, wc.Capacity
FROM dbo.vw_WorkCenterCapacity v
         JOIN dbo.WorkCenter wc ON wc.WorkCenterCode = v.WorkCenterCode
WHERE v.DailyCapacity <> wc.Capacity
;


-- -------------------------------------------------------------------------------------------------------- ActiveOrders
-- the values of ActiveOrders, against the open production orders; 0 on all 5, ProductionOrder being empty.
SELECT ActiveOrders, COUNT(*) AS work_centers, (SELECT COUNT(*) FROM dbo.ProductionOrder)
FROM dbo.vw_WorkCenterCapacity
GROUP BY ActiveOrders
;


-- ---------------------------------------------------------------------------------------------------- InProgressOrders
-- the values of InProgressOrders; 0 on all 5. The left join leaves a row of NULLs, which the CASE counts as 0.
SELECT InProgressOrders, COUNT(*) AS work_centers
FROM dbo.vw_WorkCenterCapacity
GROUP BY InProgressOrders
;


-- ------------------------------------------------------------------------------------------------ TotalQuantityPending
-- how many TotalQuantityPending are NULL; all 5, the SUM of the NULLs the left join leaves.
SELECT SUM(CASE WHEN TotalQuantityPending IS NULL THEN 1 ELSE 0 END)
FROM dbo.vw_WorkCenterCapacity
;


-- ---------------------------------------------------------------------------------------------------------- DaysOfWork
-- how many DaysOfWork are NULL; all 5, following TotalQuantityPending.
SELECT SUM(CASE WHEN DaysOfWork IS NULL THEN 1 ELSE 0 END)
FROM dbo.vw_WorkCenterCapacity
;


-- ------------------------------------------------------------------------------------------------ PercentOfTotalOrders
-- how many PercentOfTotalOrders are NULL; all 5, the NULLIF on the count of open orders, 0, turning it to NULL.
SELECT SUM(CASE WHEN PercentOfTotalOrders IS NULL THEN 1 ELSE 0 END)
FROM dbo.vw_WorkCenterCapacity
;


-- ----------------------------------------------------------------------------------------------------- EarliestDueDate
-- how many EarliestDueDate are NULL; all 5.
SELECT SUM(CASE WHEN EarliestDueDate IS NULL THEN 1 ELSE 0 END)
FROM dbo.vw_WorkCenterCapacity
;


-- ------------------------------------------------------------------------------------------------------- LatestDueDate
-- how many LatestDueDate are NULL; all 5.
SELECT SUM(CASE WHEN LatestDueDate IS NULL THEN 1 ELSE 0 END)
FROM dbo.vw_WorkCenterCapacity
;

