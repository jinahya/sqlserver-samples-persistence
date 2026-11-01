-- -
-- #%L
-- mssql-sample-databases-persistence-wideworldimporters
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
-- Profiling queries on Warehouse.PackageTypes_Archive, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_PackageTypes_Archive.sql
--


-- ------------------------------------------------------------------------------------------------------- PackageTypeID
-- how many past versions, and of how many package types; none, the table is empty: no package type has been changed.
SELECT COUNT(*), COUNT(DISTINCT PackageTypeID)
FROM Warehouse.PackageTypes_Archive
;

-- a past version of a package type which is not in Warehouse.PackageTypes; the history table has no foreign key.
-- no rows: every archived PackageTypeID is still current.
SELECT a.PackageTypeID
FROM Warehouse.PackageTypes_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.PackageTypes t
                  WHERE t.PackageTypeID = a.PackageTypeID)
;


-- ----------------------------------------------------------------------------------------------------- PackageTypeName
-- the shortest and the longest PackageTypeName; NULL and NULL, the table is empty.
SELECT MIN(LEN(PackageTypeName)), MAX(LEN(PackageTypeName))
FROM Warehouse.PackageTypes_Archive
;

-- a PackageTypeName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every PackageTypeName is trimmed and not blank.
SELECT PackageTypeID, '[' + PackageTypeName + ']' AS PackageTypeName
FROM Warehouse.PackageTypes_Archive
WHERE TRIM(PackageTypeName) = ''
   OR DATALENGTH(PackageTypeName) <> DATALENGTH(TRIM(PackageTypeName))
;

-- each past name next to the current one; no rows, the table is empty.
SELECT a.PackageTypeID, a.PackageTypeName, t.PackageTypeName AS current_name
FROM Warehouse.PackageTypes_Archive a
         JOIN Warehouse.PackageTypes t ON t.PackageTypeID = a.PackageTypeID
ORDER BY a.PackageTypeID ASC, a.ValidFrom ASC
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who edited the past versions; no rows, the table is empty.
SELECT LastEditedBy, COUNT(*) AS versions
FROM Warehouse.PackageTypes_Archive
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of the moments the past versions began; NULL and NULL, the table is empty.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Warehouse.PackageTypes_Archive
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the range of the moments the past versions ended; NULL and NULL, the table is empty.
SELECT MIN(ValidTo), MAX(ValidTo)
FROM Warehouse.PackageTypes_Archive
;

-- a version which does not end after it began.
-- no rows: every ValidTo is later than its ValidFrom.
SELECT PackageTypeID, ValidFrom, ValidTo
FROM Warehouse.PackageTypes_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a past version whose end is not the start of the next version, past or current.
-- no rows: the versions of each package type follow one another without a gap or an overlap.
SELECT a.PackageTypeID, a.ValidFrom, a.ValidTo
FROM Warehouse.PackageTypes_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.PackageTypes t
                  WHERE t.PackageTypeID = a.PackageTypeID
                    AND t.ValidFrom = a.ValidTo)
  AND NOT EXISTS (SELECT 1
                  FROM Warehouse.PackageTypes_Archive b
                  WHERE b.PackageTypeID = a.PackageTypeID
                    AND b.ValidFrom = a.ValidTo)
;

