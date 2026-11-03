-- -
-- #%L
-- sqlserver-samples-persistence-wideworldimporters
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
-- Profiling queries on Application.TransactionTypes_Archive, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_TransactionTypes_Archive.sql
--


-- --------------------------------------------------------------------------------------------------- TransactionTypeID
-- how many rows, and the TransactionTypeIDs they keep the history of; a single row, of Customer Contra (13).
SELECT COUNT(*), MIN(TransactionTypeID), MAX(TransactionTypeID)
FROM Application.TransactionTypes_Archive
;

-- the archive has no primary key; a TransactionTypeID it keeps more than one row of.
-- no rows: each TransactionTypeID has a single archived version.
SELECT TransactionTypeID, COUNT(*) AS versions
FROM Application.TransactionTypes_Archive
GROUP BY TransactionTypeID
HAVING COUNT(*) > 1
;

-- an archived TransactionTypeID with no current row; the history here is only of rows still current.
-- no rows: every archived TransactionTypeID is still current.
SELECT a.TransactionTypeID
FROM Application.TransactionTypes_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Application.TransactionTypes m
                  WHERE m.TransactionTypeID = a.TransactionTypeID)
;


-- ------------------------------------------------------------------------------------------------- TransactionTypeName
-- the archived TransactionTypeName, next to the current one; Contra, now Customer Contra.
SELECT a.TransactionTypeID, a.TransactionTypeName, m.TransactionTypeName AS current_name
FROM Application.TransactionTypes_Archive a
         JOIN Application.TransactionTypes m ON m.TransactionTypeID = a.TransactionTypeID
ORDER BY a.TransactionTypeID ASC, a.ValidFrom ASC
;

-- a TransactionTypeName which is blank, or carries leading or trailing spaces.
-- no rows: every TransactionTypeName is trimmed and not blank.
SELECT TransactionTypeID, '[' + TransactionTypeName + ']' AS TransactionTypeName
FROM Application.TransactionTypes_Archive
WHERE TRIM(TransactionTypeName) = ''
   OR DATALENGTH(TransactionTypeName) <> DATALENGTH(TRIM(TransactionTypeName))
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who edited the archived versions; person 9, Alica Fatnowna.
SELECT a.LastEditedBy, p.FullName, COUNT(*) AS versions
FROM Application.TransactionTypes_Archive a
         JOIN Application.People p ON p.PersonID = a.LastEditedBy
GROUP BY a.LastEditedBy, p.FullName
ORDER BY a.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the period each archived version was valid for; 2016-01-01 16:00 to 16:05, five minutes.
SELECT TransactionTypeID, ValidFrom, ValidTo
FROM Application.TransactionTypes_Archive
ORDER BY TransactionTypeID ASC, ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a version which ends before it begins.
-- no rows: every ValidTo is after its ValidFrom.
SELECT TransactionTypeID, ValidFrom, ValidTo
FROM Application.TransactionTypes_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the latest archived version of a TransactionTypeID ends where its current row begins.
-- no rows: every chain of versions meets the current row without a gap or an overlap.
SELECT a.TransactionTypeID, a.ValidTo, m.ValidFrom
FROM Application.TransactionTypes_Archive a
         JOIN Application.TransactionTypes m ON m.TransactionTypeID = a.TransactionTypeID
WHERE a.ValidTo = (SELECT MAX(b.ValidTo)
                   FROM Application.TransactionTypes_Archive b
                   WHERE b.TransactionTypeID = a.TransactionTypeID)
  AND a.ValidTo <> m.ValidFrom
;

