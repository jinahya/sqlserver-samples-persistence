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
-- Profiling queries on Warehouse.Colors, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_Colors.sql
--


-- ------------------------------------------------------------------------------------------------------------- ColorID
-- how many rows, and the smallest and the largest ColorID; 36 colors, 1 to 36.
SELECT COUNT(*), MIN(ColorID), MAX(ColorID)
FROM Warehouse.Colors
;

-- a ColorID whose next value is missing, below the largest.
-- no rows: ColorID runs from 1 without a gap.
SELECT a.ColorID + 1 AS missing_from
FROM Warehouse.Colors a
WHERE a.ColorID < (SELECT MAX(ColorID) FROM Warehouse.Colors)
  AND NOT EXISTS (SELECT 1
                  FROM Warehouse.Colors b
                  WHERE b.ColorID = a.ColorID + 1)
;


-- ----------------------------------------------------------------------------------------------------------- ColorName
-- the shortest and the longest ColorName; 3 and 11 characters, of the 20 the column allows.
SELECT MIN(LEN(ColorName)), MAX(LEN(ColorName))
FROM Warehouse.Colors
;

-- a ColorName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every ColorName is trimmed and not blank.
SELECT ColorID, '[' + ColorName + ']' AS ColorName
FROM Warehouse.Colors
WHERE TRIM(ColorName) = ''
   OR DATALENGTH(ColorName) <> DATALENGTH(TRIM(ColorName))
;

-- UQ_Warehouse_Colors_ColorName keeps ColorName unique.
-- no rows: no two colors share a name.
SELECT ColorName, COUNT(*) AS colors
FROM Warehouse.Colors
GROUP BY ColorName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the rows; person 1 for 35, person 9 for Steel Gray, the one renamed.
SELECT LastEditedBy, COUNT(*) AS colors
FROM Warehouse.Colors
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the moments the current versions began; 2013-01-01 for 35, 2016-01-01 16:00 for Steel Gray.
SELECT ValidFrom, COUNT(*) AS colors
FROM Warehouse.Colors
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the end of each current version; 9999-12-31 23:59:59.9999999, the largest datetime2, for all 36.
SELECT ValidTo, COUNT(*) AS colors
FROM Warehouse.Colors
GROUP BY ValidTo
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the past versions kept in Warehouse.Colors_Archive; 1, Steel Gray (12) as it was named before, Gray.
SELECT COUNT(*)
FROM Warehouse.Colors_Archive
;

-- how many colors no stock item has; 29 of the 36, only 7 are in use.
SELECT COUNT(*)
FROM Warehouse.Colors a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItems r
                  WHERE r.ColorID = a.ColorID)
;

-- the stock items of each color in use, the largest first; Black (3) with 51, Yellow (36) with 2.
SELECT a.ColorID, a.ColorName, COUNT(*) AS stock_items
FROM Warehouse.Colors a
         JOIN Warehouse.StockItems r ON r.ColorID = a.ColorID
GROUP BY a.ColorID, a.ColorName
ORDER BY stock_items DESC, a.ColorID ASC
;

