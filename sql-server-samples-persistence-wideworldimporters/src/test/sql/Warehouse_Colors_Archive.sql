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
-- Profiling queries on Warehouse.Colors_Archive, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_Colors_Archive.sql
--


-- ------------------------------------------------------------------------------------------------------------- ColorID
-- how many past versions, and of how many colors; 1 version, of Steel Gray (12).
SELECT COUNT(*), COUNT(DISTINCT ColorID)
FROM Warehouse.Colors_Archive
;

-- a past version of a color which is not in Warehouse.Colors; the history table has no foreign key.
-- no rows: every archived ColorID is still current.
SELECT a.ColorID
FROM Warehouse.Colors_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.Colors t
                  WHERE t.ColorID = a.ColorID)
;


-- ----------------------------------------------------------------------------------------------------------- ColorName
-- the shortest and the longest ColorName; 4 and 4 characters, of the 20 the column allows.
SELECT MIN(LEN(ColorName)), MAX(LEN(ColorName))
FROM Warehouse.Colors_Archive
;

-- a ColorName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every ColorName is trimmed and not blank.
SELECT ColorID, '[' + ColorName + ']' AS ColorName
FROM Warehouse.Colors_Archive
WHERE TRIM(ColorName) = ''
   OR DATALENGTH(ColorName) <> DATALENGTH(TRIM(ColorName))
;

-- each past name next to the current one; 12, Gray, now Steel Gray.
SELECT a.ColorID, a.ColorName, t.ColorName AS current_name
FROM Warehouse.Colors_Archive a
         JOIN Warehouse.Colors t ON t.ColorID = a.ColorID
ORDER BY a.ColorID ASC, a.ValidFrom ASC
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who edited the past versions; person 1, for the 1 version.
SELECT LastEditedBy, COUNT(*) AS versions
FROM Warehouse.Colors_Archive
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of the moments the past versions began; 2013-01-01 00:00:00.0000000.
SELECT MIN(ValidFrom), MAX(ValidFrom)
FROM Warehouse.Colors_Archive
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the range of the moments the past versions ended; 2016-01-01 16:00:00.0000000.
SELECT MIN(ValidTo), MAX(ValidTo)
FROM Warehouse.Colors_Archive
;

-- a version which does not end after it began.
-- no rows: every ValidTo is later than its ValidFrom.
SELECT ColorID, ValidFrom, ValidTo
FROM Warehouse.Colors_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a past version whose end is not the start of the next version, past or current.
-- no rows: the versions of each color follow one another without a gap or an overlap.
SELECT a.ColorID, a.ValidFrom, a.ValidTo
FROM Warehouse.Colors_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.Colors t
                  WHERE t.ColorID = a.ColorID
                    AND t.ValidFrom = a.ValidTo)
  AND NOT EXISTS (SELECT 1
                  FROM Warehouse.Colors_Archive b
                  WHERE b.ColorID = a.ColorID
                    AND b.ValidFrom = a.ValidTo)
;

