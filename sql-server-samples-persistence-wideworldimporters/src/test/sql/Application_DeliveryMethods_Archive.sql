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
-- Profiling queries on Application.DeliveryMethods_Archive, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_DeliveryMethods_Archive.sql
--


-- ---------------------------------------------------------------------------------------------------- DeliveryMethodID
-- how many rows, and the DeliveryMethodIDs they keep the history of; a single row, of Chilled Van (5).
SELECT COUNT(*), MIN(DeliveryMethodID), MAX(DeliveryMethodID)
FROM Application.DeliveryMethods_Archive
;

-- the archive has no primary key; a DeliveryMethodID it keeps more than one row of.
-- no rows: each DeliveryMethodID has a single archived version.
SELECT DeliveryMethodID, COUNT(*) AS versions
FROM Application.DeliveryMethods_Archive
GROUP BY DeliveryMethodID
HAVING COUNT(*) > 1
;

-- an archived DeliveryMethodID with no current row; the history here is only of rows still current.
-- no rows: every archived DeliveryMethodID is still current.
SELECT a.DeliveryMethodID
FROM Application.DeliveryMethods_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Application.DeliveryMethods m
                  WHERE m.DeliveryMethodID = a.DeliveryMethodID)
;


-- -------------------------------------------------------------------------------------------------- DeliveryMethodName
-- the archived DeliveryMethodName, next to the current one; Van with Chiller, now Chilled Van.
SELECT a.DeliveryMethodID, a.DeliveryMethodName, m.DeliveryMethodName AS current_name
FROM Application.DeliveryMethods_Archive a
         JOIN Application.DeliveryMethods m ON m.DeliveryMethodID = a.DeliveryMethodID
ORDER BY a.DeliveryMethodID ASC, a.ValidFrom ASC
;

-- a DeliveryMethodName which is blank, or carries leading or trailing spaces.
-- no rows: every DeliveryMethodName is trimmed and not blank.
SELECT DeliveryMethodID, '[' + DeliveryMethodName + ']' AS DeliveryMethodName
FROM Application.DeliveryMethods_Archive
WHERE TRIM(DeliveryMethodName) = ''
   OR DATALENGTH(DeliveryMethodName) <> DATALENGTH(TRIM(DeliveryMethodName))
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who edited the archived versions; person 1, Data Conversion Only.
SELECT a.LastEditedBy, p.FullName, COUNT(*) AS versions
FROM Application.DeliveryMethods_Archive a
         JOIN Application.People p ON p.PersonID = a.LastEditedBy
GROUP BY a.LastEditedBy, p.FullName
ORDER BY a.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the period each archived version was valid for; 2013-01-01 to 2015-01-01 16:00.
SELECT DeliveryMethodID, ValidFrom, ValidTo
FROM Application.DeliveryMethods_Archive
ORDER BY DeliveryMethodID ASC, ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a version which ends before it begins.
-- no rows: every ValidTo is after its ValidFrom.
SELECT DeliveryMethodID, ValidFrom, ValidTo
FROM Application.DeliveryMethods_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the latest archived version of a DeliveryMethodID ends where its current row begins.
-- no rows: every chain of versions meets the current row without a gap or an overlap.
SELECT a.DeliveryMethodID, a.ValidTo, m.ValidFrom
FROM Application.DeliveryMethods_Archive a
         JOIN Application.DeliveryMethods m ON m.DeliveryMethodID = a.DeliveryMethodID
WHERE a.ValidTo = (SELECT MAX(b.ValidTo)
                   FROM Application.DeliveryMethods_Archive b
                   WHERE b.DeliveryMethodID = a.DeliveryMethodID)
  AND a.ValidTo <> m.ValidFrom
;

