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
-- Profiling queries on dbo.TransactionType, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_TransactionType.sql
--


-- --------------------------------------------------------------------------------------------------- TransactionTypeID
-- how many rows, and the smallest and the largest TransactionTypeID; 9 types, 1 to 9.
SELECT COUNT(*), MIN(TransactionTypeID), MAX(TransactionTypeID)
FROM dbo.TransactionType
;


-- ------------------------------------------------------------------------------------------------------------ TypeCode
-- the codes, in the order of TransactionTypeID; PO-RCV, PO-RET, WO-ISS, WO-CMP, SO-SHP, SO-RET, ADJ-POS, ADJ-NEG and
-- CYC-CNT.
SELECT TransactionTypeID, TypeCode
FROM dbo.TransactionType
ORDER BY TransactionTypeID ASC
;

-- the shortest and the longest TypeCode; 6 and 7 characters, of the 20 the column allows.
SELECT MIN(LEN(TypeCode)), MAX(LEN(TypeCode))
FROM dbo.TransactionType
;

-- a TypeCode which is blank, or carries leading or trailing spaces.
-- no rows: every TypeCode is trimmed and not blank.
SELECT TransactionTypeID, '[' + TypeCode + ']' AS TypeCode
FROM dbo.TransactionType
WHERE TRIM(TypeCode) = ''
   OR DATALENGTH(TypeCode) <> DATALENGTH(TRIM(TypeCode))
;

-- a unique constraint keeps TypeCode unique.
-- no rows: every TypeCode is distinct.
SELECT TypeCode, COUNT(*) AS types
FROM dbo.TransactionType
GROUP BY TypeCode
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ TypeName
-- the shortest and the longest TypeName; 11 and 22 characters, of the 100 the column allows.
SELECT MIN(LEN(TypeName)), MAX(LEN(TypeName))
FROM dbo.TransactionType
;

-- a TypeName which is blank, or carries leading or trailing spaces.
-- no rows: every TypeName is trimmed and not blank.
SELECT TransactionTypeID, '[' + TypeName + ']' AS TypeName
FROM dbo.TransactionType
WHERE TRIM(TypeName) = ''
   OR DATALENGTH(TypeName) <> DATALENGTH(TRIM(TypeName))
;

-- nothing keeps TypeName unique, yet no two rows share one.
-- no rows: every TypeName is distinct.
SELECT TypeName, COUNT(*) AS types
FROM dbo.TransactionType
GROUP BY TypeName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- Description
-- how many Description values are NULL, and the shortest and the longest of the rest; none, 15 and 30 characters, of
-- the 255 the column allows.
SELECT SUM(CASE WHEN Description IS NULL THEN 1 ELSE 0 END), MIN(LEN(Description)), MAX(LEN(Description))
FROM dbo.TransactionType
;

-- a Description which is blank, or carries leading or trailing spaces.
-- no rows: every Description is trimmed and not blank.
SELECT TransactionTypeID, '[' + Description + ']' AS Description
FROM dbo.TransactionType
WHERE TRIM(Description) = ''
   OR DATALENGTH(Description) <> DATALENGTH(TRIM(Description))
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the inventory transactions of each type.
-- no rows: InventoryTransaction is empty, so no type is in use yet.
SELECT t.TransactionTypeID, t.TypeCode, COUNT(*) AS transactions
FROM dbo.TransactionType t
         JOIN dbo.InventoryTransaction it ON it.TransactionTypeID = t.TransactionTypeID
GROUP BY t.TransactionTypeID, t.TypeCode
;

