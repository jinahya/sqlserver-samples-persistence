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
-- Profiling queries on Purchasing.SupplierCategories_Archive, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Purchasing_SupplierCategories_Archive.sql
--


-- -------------------------------------------------------------------------------------------------- SupplierCategoryID
-- how many rows, and the SupplierCategoryIDs they keep the history of; a single row, of Courier Services Supplier (6).
SELECT COUNT(*), MIN(SupplierCategoryID), MAX(SupplierCategoryID)
FROM Purchasing.SupplierCategories_Archive
;

-- the archive has no primary key; a SupplierCategoryID it keeps more than one row of.
-- no rows: each SupplierCategoryID has a single archived version.
SELECT SupplierCategoryID, COUNT(*) AS versions
FROM Purchasing.SupplierCategories_Archive
GROUP BY SupplierCategoryID
HAVING COUNT(*) > 1
;

-- an archived SupplierCategoryID with no current row; the history here is only of rows still current.
-- no rows: every archived SupplierCategoryID is still current.
SELECT a.SupplierCategoryID
FROM Purchasing.SupplierCategories_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Purchasing.SupplierCategories m
                  WHERE m.SupplierCategoryID = a.SupplierCategoryID)
;


-- ------------------------------------------------------------------------------------------------ SupplierCategoryName
-- the archived SupplierCategoryName, next to the current one; Courier, now Courier Services Supplier.
SELECT a.SupplierCategoryID, a.SupplierCategoryName, m.SupplierCategoryName AS current_name
FROM Purchasing.SupplierCategories_Archive a
         JOIN Purchasing.SupplierCategories m ON m.SupplierCategoryID = a.SupplierCategoryID
ORDER BY a.SupplierCategoryID ASC, a.ValidFrom ASC
;

-- a SupplierCategoryName which is blank, or carries leading or trailing spaces.
-- no rows: every SupplierCategoryName is trimmed and not blank.
SELECT SupplierCategoryID, '[' + SupplierCategoryName + ']' AS SupplierCategoryName
FROM Purchasing.SupplierCategories_Archive
WHERE TRIM(SupplierCategoryName) = ''
   OR DATALENGTH(SupplierCategoryName) <> DATALENGTH(TRIM(SupplierCategoryName))
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who edited the archived versions; person 1, Data Conversion Only.
SELECT a.LastEditedBy, p.FullName, COUNT(*) AS versions
FROM Purchasing.SupplierCategories_Archive a
         JOIN Application.People p ON p.PersonID = a.LastEditedBy
GROUP BY a.LastEditedBy, p.FullName
ORDER BY a.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the period each archived version was valid for; 2013-01-01 to 2015-01-01 16:00.
SELECT SupplierCategoryID, ValidFrom, ValidTo
FROM Purchasing.SupplierCategories_Archive
ORDER BY SupplierCategoryID ASC, ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a version which ends before it begins.
-- no rows: every ValidTo is after its ValidFrom.
SELECT SupplierCategoryID, ValidFrom, ValidTo
FROM Purchasing.SupplierCategories_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the latest archived version of a SupplierCategoryID ends where its current row begins.
-- no rows: every chain of versions meets the current row without a gap or an overlap.
SELECT a.SupplierCategoryID, a.ValidTo, m.ValidFrom
FROM Purchasing.SupplierCategories_Archive a
         JOIN Purchasing.SupplierCategories m ON m.SupplierCategoryID = a.SupplierCategoryID
WHERE a.ValidTo = (SELECT MAX(b.ValidTo)
                   FROM Purchasing.SupplierCategories_Archive b
                   WHERE b.SupplierCategoryID = a.SupplierCategoryID)
  AND a.ValidTo <> m.ValidFrom
;

