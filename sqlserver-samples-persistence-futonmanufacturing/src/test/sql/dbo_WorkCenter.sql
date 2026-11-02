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
-- Profiling queries on dbo.WorkCenter, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_WorkCenter.sql
--


-- -------------------------------------------------------------------------------------------------------- WorkCenterID
-- how many rows, and the smallest and the largest WorkCenterID; 5 work centers, 1 to 5.
SELECT COUNT(*), MIN(WorkCenterID), MAX(WorkCenterID)
FROM dbo.WorkCenter
;

-- a WorkCenterID whose next value is missing, below the largest; the column is an identity column.
-- no rows: WorkCenterID runs from 1 without a gap.
SELECT a.WorkCenterID + 1 AS missing_from
FROM dbo.WorkCenter a
WHERE a.WorkCenterID < (SELECT MAX(WorkCenterID) FROM dbo.WorkCenter)
  AND NOT EXISTS (SELECT 1
                  FROM dbo.WorkCenter b
                  WHERE b.WorkCenterID = a.WorkCenterID + 1)
;


-- ------------------------------------------------------------------------------------------------------ WorkCenterCode
-- the shortest and the longest WorkCenterCode; 5 and 7 characters, of the 20 the column allows.
SELECT MIN(LEN(WorkCenterCode)), MAX(LEN(WorkCenterCode))
FROM dbo.WorkCenter
;

-- a WorkCenterCode which is not WC- and upper-case letters.
-- no rows: WC-SEW, WC-FILL, WC-WOOD, WC-ASSY and WC-QC all have that shape.
SELECT WorkCenterID, WorkCenterCode
FROM dbo.WorkCenter
WHERE WorkCenterCode NOT LIKE 'WC-[A-Z]%' COLLATE Latin1_General_BIN
   OR SUBSTRING(WorkCenterCode, 4, 20) LIKE '%[^A-Z]%' COLLATE Latin1_General_BIN
;

-- a unique constraint keeps WorkCenterCode unique.
-- no rows: no two work centers share a WorkCenterCode.
SELECT WorkCenterCode, COUNT(*) AS work_centers
FROM dbo.WorkCenter
GROUP BY WorkCenterCode
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------ WorkCenterName
-- the shortest and the longest WorkCenterName; 14 and 17 characters, of the 100 the column allows.
SELECT MIN(LEN(WorkCenterName)), MAX(LEN(WorkCenterName))
FROM dbo.WorkCenter
;

-- a WorkCenterName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every WorkCenterName is trimmed and not blank.
SELECT WorkCenterID, '[' + WorkCenterName + ']' AS WorkCenterName
FROM dbo.WorkCenter
WHERE TRIM(WorkCenterName) = ''
   OR DATALENGTH(WorkCenterName) <> DATALENGTH(TRIM(WorkCenterName))
;

-- no constraint keeps WorkCenterName unique, and the views report by it.
-- no rows: no two work centers share a WorkCenterName all the same.
SELECT WorkCenterName, COUNT(*) AS work_centers
FROM dbo.WorkCenter
GROUP BY WorkCenterName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- Description
-- how many Description are NULL, and the shortest and the longest; none, 27 and 34 characters, of the 255 allowed.
SELECT SUM(CASE WHEN Description IS NULL THEN 1 ELSE 0 END), MIN(LEN(Description)), MAX(LEN(Description))
FROM dbo.WorkCenter
;

-- a Description which is blank, or carries leading or trailing spaces.
-- no rows: every Description is trimmed and not blank.
SELECT WorkCenterID, '[' + Description + ']' AS Description
FROM dbo.WorkCenter
WHERE TRIM(Description) = ''
   OR DATALENGTH(Description) <> DATALENGTH(TRIM(Description))
;


-- ------------------------------------------------------------------------------------------------------------ Capacity
-- the capacities, the units a day vw_WorkCenterCapacity divides the pending quantity by; 30.00 (WC-WOOD) to 60.00
-- (WC-FILL).
SELECT WorkCenterCode, Capacity
FROM dbo.WorkCenter
ORDER BY Capacity ASC, WorkCenterCode ASC
;

-- a Capacity which is NULL, zero or negative; no check constraint forbids it.
-- no rows: every Capacity is positive.
SELECT WorkCenterID, WorkCenterCode, Capacity
FROM dbo.WorkCenter
WHERE Capacity IS NULL
   OR Capacity <= 0
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values of IsActive; 1 on all 5, the default.
SELECT IsActive, COUNT(*) AS work_centers
FROM dbo.WorkCenter
GROUP BY IsActive
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the rows each work center is referred to by.
-- 0 everywhere: ProductionOrder and ProductionCompletion are both empty, so no work center has been used.
SELECT wc.WorkCenterCode,
       (SELECT COUNT(*) FROM dbo.ProductionOrder x WHERE x.WorkCenterID = wc.WorkCenterID)      AS production_orders,
       (SELECT COUNT(*) FROM dbo.ProductionCompletion x WHERE x.WorkCenterID = wc.WorkCenterID) AS completions
FROM dbo.WorkCenter wc
ORDER BY wc.WorkCenterID ASC
;

