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
-- Profiling queries on dbo.ItemType, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_ItemType.sql
--


-- ---------------------------------------------------------------------------------------------------------- ItemTypeID
-- how many rows, and the smallest and the largest ItemTypeID; 3 types, 1 to 3.
SELECT COUNT(*), MIN(ItemTypeID), MAX(ItemTypeID)
FROM dbo.ItemType
;


-- ------------------------------------------------------------------------------------------------------------ TypeCode
-- the codes, in the order of ItemTypeID; RAW, COMP and FG.
SELECT ItemTypeID, TypeCode
FROM dbo.ItemType
ORDER BY ItemTypeID ASC
;

-- a TypeCode which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH
-- does not.
-- no rows: every TypeCode is trimmed and not blank.
SELECT ItemTypeID, '[' + TypeCode + ']' AS TypeCode
FROM dbo.ItemType
WHERE TRIM(TypeCode) = ''
   OR DATALENGTH(TypeCode) <> DATALENGTH(TRIM(TypeCode))
;

-- a TypeCode which is not all upper case; the column's collation ignores case, so compare in a binary one.
-- no rows: every TypeCode is upper case.
SELECT ItemTypeID, TypeCode
FROM dbo.ItemType
WHERE TypeCode COLLATE Latin1_General_BIN2 <> UPPER(TypeCode)
;

-- a unique constraint keeps TypeCode unique.
-- no rows: no two types share a TypeCode.
SELECT TypeCode, COUNT(*) AS types
FROM dbo.ItemType
GROUP BY TypeCode
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ TypeName
-- the shortest and the longest TypeName; 9 and 14 characters, of the 100 the column allows.
SELECT MIN(LEN(TypeName)), MAX(LEN(TypeName))
FROM dbo.ItemType
;

-- a TypeName which is blank, or carries leading or trailing spaces.
-- no rows: every TypeName is trimmed and not blank.
SELECT ItemTypeID, '[' + TypeName + ']' AS TypeName
FROM dbo.ItemType
WHERE TRIM(TypeName) = ''
   OR DATALENGTH(TypeName) <> DATALENGTH(TRIM(TypeName))
;

-- nothing keeps TypeName unique, yet no two types share one.
-- no rows: every TypeName is distinct.
SELECT TypeName, COUNT(*) AS types
FROM dbo.ItemType
GROUP BY TypeName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------- Description
-- how many Descriptions are NULL, and the shortest and the longest of the rest; none, 32 and 42 characters, of 255.
SELECT SUM(CASE WHEN Description IS NULL THEN 1 ELSE 0 END), MIN(LEN(Description)), MAX(LEN(Description))
FROM dbo.ItemType
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the items of each type; Raw Material with 24, Component with 15 and Finished Goods with 6.
SELECT t.ItemTypeID, t.TypeName, COUNT(i.ItemID) AS items
FROM dbo.ItemType t
         LEFT JOIN dbo.Items i ON i.ItemTypeID = t.ItemTypeID
GROUP BY t.ItemTypeID, t.TypeName
ORDER BY t.ItemTypeID ASC
;

