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
-- Profiling queries on dbo.UnitOfMeasure, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_UnitOfMeasure.sql
--


-- -------------------------------------------------------------------------------------------------------------- UnitID
-- how many rows, and the smallest and the largest UnitID; 8 units, 1 to 8.
SELECT COUNT(*), MIN(UnitID), MAX(UnitID)
FROM dbo.UnitOfMeasure
;


-- ------------------------------------------------------------------------------------------------------------ UnitCode
-- the codes, in the order of UnitID; EA, YD, LB, FT, PC, SET, BOX and ROLL.
SELECT UnitID, UnitCode
FROM dbo.UnitOfMeasure
ORDER BY UnitID ASC
;

-- the shortest and the longest UnitCode; 2 and 4 characters, of the 10 the column allows.
SELECT MIN(LEN(UnitCode)), MAX(LEN(UnitCode))
FROM dbo.UnitOfMeasure
;

-- an UnitCode which is blank, or carries leading or trailing spaces.
-- no rows: every UnitCode is trimmed and not blank.
SELECT UnitID, '[' + UnitCode + ']' AS UnitCode
FROM dbo.UnitOfMeasure
WHERE TRIM(UnitCode) = ''
   OR DATALENGTH(UnitCode) <> DATALENGTH(TRIM(UnitCode))
;

-- a unique constraint keeps UnitCode unique.
-- no rows: every UnitCode is distinct.
SELECT UnitCode, COUNT(*) AS units
FROM dbo.UnitOfMeasure
GROUP BY UnitCode
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ UnitName
-- the shortest and the longest UnitName; 3 and 5 characters, of the 50 the column allows.
SELECT MIN(LEN(UnitName)), MAX(LEN(UnitName))
FROM dbo.UnitOfMeasure
;

-- an UnitName which is blank, or carries leading or trailing spaces.
-- no rows: every UnitName is trimmed and not blank.
SELECT UnitID, '[' + UnitName + ']' AS UnitName
FROM dbo.UnitOfMeasure
WHERE TRIM(UnitName) = ''
   OR DATALENGTH(UnitName) <> DATALENGTH(TRIM(UnitName))
;

-- nothing keeps UnitName unique, yet no two rows share one.
-- no rows: every UnitName is distinct.
SELECT UnitName, COUNT(*) AS units
FROM dbo.UnitOfMeasure
GROUP BY UnitName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- Description
-- how many Description values are NULL, and the shortest and the longest of the rest; none, 3 and 16 characters, of the
-- 255 the column allows.
SELECT SUM(CASE WHEN Description IS NULL THEN 1 ELSE 0 END), MIN(LEN(Description)), MAX(LEN(Description))
FROM dbo.UnitOfMeasure
;

-- a Description which is blank, or carries leading or trailing spaces.
-- no rows: every Description is trimmed and not blank.
SELECT UnitID, '[' + Description + ']' AS Description
FROM dbo.UnitOfMeasure
WHERE TRIM(Description) = ''
   OR DATALENGTH(Description) <> DATALENGTH(TRIM(Description))
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many items and bill of materials lines measure in each unit; EA with 32 items and 55 lines, YD with 8 and
-- 10, LB with 5 and 10, and FT, PC, SET, BOX and ROLL unused.
SELECT u.UnitID, u.UnitCode,
       (SELECT COUNT(*) FROM dbo.Items i WHERE i.UnitID = u.UnitID) AS items,
       (SELECT COUNT(*) FROM dbo.BillOfMaterials b WHERE b.UnitID = u.UnitID) AS lines
FROM dbo.UnitOfMeasure u
ORDER BY u.UnitID ASC
;

