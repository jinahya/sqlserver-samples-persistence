-- -
-- #%L
-- sql-server-samples-persistence-futonmanufacturing
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
-- Profiling queries on the view dbo.vw_SupplierPerformance, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_SupplierPerformance.sql
--


-- -------------------------------------------------------------------------------------------------------- SupplierCode
-- how many rows, and how many distinct SupplierCode, against the active suppliers; 5, 5 and 5, a row per supplier.
SELECT COUNT(*), COUNT(DISTINCT SupplierCode), (SELECT COUNT(*) FROM dbo.Supplier WHERE IsActive = 1)
FROM dbo.vw_SupplierPerformance
;

-- the smallest and the largest SupplierCode; SUP-001 and SUP-005.
SELECT MIN(SupplierCode), MAX(SupplierCode)
FROM dbo.vw_SupplierPerformance
;


-- -------------------------------------------------------------------------------------------------------- SupplierName
-- a SupplierName which is not the one of its SupplierCode.
-- no rows: each matches Supplier.
SELECT v.SupplierCode, v.SupplierName
FROM dbo.vw_SupplierPerformance v
         JOIN dbo.Supplier s ON s.SupplierCode = v.SupplierCode
WHERE v.SupplierName <> s.SupplierName
;


-- ------------------------------------------------------------------------------------------------------ SupplierRating
-- how many SupplierRating are NULL, and the range; none, 4.30 (SUP-003) to 4.80 (SUP-002).
SELECT SUM(CASE WHEN SupplierRating IS NULL THEN 1 ELSE 0 END), MIN(SupplierRating), MAX(SupplierRating)
FROM dbo.vw_SupplierPerformance
;


-- --------------------------------------------------------------------------------------------------------- TotalOrders
-- the values of TotalOrders; 1 on all 5, a purchase order each.
SELECT TotalOrders, COUNT(*) AS suppliers
FROM dbo.vw_SupplierPerformance
GROUP BY TotalOrders
;


-- -------------------------------------------------------------------------------------------------- TotalPurchaseValue
-- a TotalPurchaseValue which is not the TotalAmount of the supplier's purchase orders.
-- no rows: they agree, 11476.60 in all.
SELECT v.SupplierCode, v.TotalPurchaseValue
FROM dbo.vw_SupplierPerformance v
         JOIN dbo.Supplier s ON s.SupplierCode = v.SupplierCode
WHERE v.TotalPurchaseValue <> (SELECT SUM(po.TotalAmount)
                               FROM dbo.PurchaseOrder po
                               WHERE po.SupplierID = s.SupplierID)
;

-- the sum of TotalPurchaseValue; 11476.60.
SELECT SUM(TotalPurchaseValue)
FROM dbo.vw_SupplierPerformance
;


-- ----------------------------------------------------------------------------------------------------- AvgDeliveryDays
-- the values of AvgDeliveryDays; 14 on 3 suppliers, 21 on SUP-002 and SUP-005. AVG of an int drops the fraction;
-- with one order a supplier, there is none to drop.
SELECT AvgDeliveryDays, COUNT(*) AS suppliers
FROM dbo.vw_SupplierPerformance
GROUP BY AvgDeliveryDays
ORDER BY AvgDeliveryDays ASC
;


-- ---------------------------------------------------------------------------------------------------- OnTimeDeliveries
-- an OnTimeDeliveries above CompletedDeliveries.
-- no rows: an on-time delivery is a completed one.
SELECT SupplierCode, OnTimeDeliveries, CompletedDeliveries
FROM dbo.vw_SupplierPerformance
WHERE OnTimeDeliveries > CompletedDeliveries
;


-- ------------------------------------------------------------------------------------------------- CompletedDeliveries
-- the values of CompletedDeliveries and OnTimeDeliveries; 1 and 1 on all 5.
SELECT CompletedDeliveries, OnTimeDeliveries, COUNT(*) AS suppliers
FROM dbo.vw_SupplierPerformance
GROUP BY CompletedDeliveries, OnTimeDeliveries
;


-- -------------------------------------------------------------------------------------------------- OnTimeDeliveryRate
-- an OnTimeDeliveryRate which is not OnTimeDeliveries over CompletedDeliveries, in percent to two places.
-- no rows: it is the ratio throughout, 100.00 on all 5.
SELECT SupplierCode, OnTimeDeliveryRate
FROM dbo.vw_SupplierPerformance
WHERE OnTimeDeliveryRate <> CAST(OnTimeDeliveries * 100.0 / NULLIF(CompletedDeliveries, 0) AS DECIMAL(5, 2))
   OR OnTimeDeliveryRate IS NULL
;


-- ---------------------------------------------------------------------------------------------------- PerformanceGrade
-- the values of PerformanceGrade; Excellent on all 5. A supplier with no completed delivery would get Poor, its rate
-- being NULL.
SELECT PerformanceGrade, COUNT(*) AS suppliers
FROM dbo.vw_SupplierPerformance
GROUP BY PerformanceGrade
;

