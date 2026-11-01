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
-- Profiling queries on Application.PaymentMethods_Archive, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_PaymentMethods_Archive.sql
--


-- ----------------------------------------------------------------------------------------------------- PaymentMethodID
-- how many rows, and the PaymentMethodIDs they keep the history of; a single row, of Credit-Card (3).
SELECT COUNT(*), MIN(PaymentMethodID), MAX(PaymentMethodID)
FROM Application.PaymentMethods_Archive
;

-- the archive has no primary key; a PaymentMethodID it keeps more than one row of.
-- no rows: each PaymentMethodID has a single archived version.
SELECT PaymentMethodID, COUNT(*) AS versions
FROM Application.PaymentMethods_Archive
GROUP BY PaymentMethodID
HAVING COUNT(*) > 1
;

-- an archived PaymentMethodID with no current row; the history here is only of rows still current.
-- no rows: every archived PaymentMethodID is still current.
SELECT a.PaymentMethodID
FROM Application.PaymentMethods_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Application.PaymentMethods m
                  WHERE m.PaymentMethodID = a.PaymentMethodID)
;


-- --------------------------------------------------------------------------------------------------- PaymentMethodName
-- the archived PaymentMethodName, next to the current one; Credit Card, now Credit-Card.
SELECT a.PaymentMethodID, a.PaymentMethodName, m.PaymentMethodName AS current_name
FROM Application.PaymentMethods_Archive a
         JOIN Application.PaymentMethods m ON m.PaymentMethodID = a.PaymentMethodID
ORDER BY a.PaymentMethodID ASC, a.ValidFrom ASC
;

-- a PaymentMethodName which is blank, or carries leading or trailing spaces.
-- no rows: every PaymentMethodName is trimmed and not blank.
SELECT PaymentMethodID, '[' + PaymentMethodName + ']' AS PaymentMethodName
FROM Application.PaymentMethods_Archive
WHERE TRIM(PaymentMethodName) = ''
   OR DATALENGTH(PaymentMethodName) <> DATALENGTH(TRIM(PaymentMethodName))
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who edited the archived versions; person 1, Data Conversion Only.
SELECT a.LastEditedBy, p.FullName, COUNT(*) AS versions
FROM Application.PaymentMethods_Archive a
         JOIN Application.People p ON p.PersonID = a.LastEditedBy
GROUP BY a.LastEditedBy, p.FullName
ORDER BY a.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the period each archived version was valid for; 2013-01-01 to 2016-01-01 16:00.
SELECT PaymentMethodID, ValidFrom, ValidTo
FROM Application.PaymentMethods_Archive
ORDER BY PaymentMethodID ASC, ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a version which ends before it begins.
-- no rows: every ValidTo is after its ValidFrom.
SELECT PaymentMethodID, ValidFrom, ValidTo
FROM Application.PaymentMethods_Archive
WHERE ValidTo <= ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the latest archived version of a PaymentMethodID ends where its current row begins.
-- no rows: every chain of versions meets the current row without a gap or an overlap.
SELECT a.PaymentMethodID, a.ValidTo, m.ValidFrom
FROM Application.PaymentMethods_Archive a
         JOIN Application.PaymentMethods m ON m.PaymentMethodID = a.PaymentMethodID
WHERE a.ValidTo = (SELECT MAX(b.ValidTo)
                   FROM Application.PaymentMethods_Archive b
                   WHERE b.PaymentMethodID = a.PaymentMethodID)
  AND a.ValidTo <> m.ValidFrom
;

