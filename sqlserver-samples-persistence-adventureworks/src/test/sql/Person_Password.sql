-- -
-- #%L
-- sqlserver-samples-persistence-adventureworks
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
-- Profiling queries on Person.Password, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_Password.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows; 19972, one for each person.
SELECT COUNT(*)
FROM Person.Password
;


-- -------------------------------------------------------------------------------------------------------- PasswordHash
-- the shortest and the longest PasswordHash; 44 characters each, of the 128 the column allows: a 32 byte hash in
-- Base64.
SELECT MIN(LEN(PasswordHash)), MAX(LEN(PasswordHash))
FROM Person.Password
;

-- a PasswordHash with a character outside the Base64 alphabet.
-- no rows: every hash is Base64.
SELECT BusinessEntityID, PasswordHash
FROM Person.Password
WHERE PasswordHash COLLATE Latin1_General_BIN LIKE '%[^A-Za-z0-9+/=]%'
;

-- a PasswordHash more than one person has.
-- no rows: every hash is unique.
SELECT PasswordHash, COUNT(*) AS passwords
FROM Person.Password
GROUP BY PasswordHash
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- PasswordSalt
-- the shortest and the longest PasswordSalt; 8 characters each, of the 10 the column allows: a 4 byte salt in
-- Base64, padded with a single =.
SELECT MIN(LEN(PasswordSalt)), MAX(LEN(PasswordSalt)), SUM(CASE WHEN PasswordSalt LIKE '%[^=]=' THEN 1 END)
FROM Person.Password
;

-- a PasswordSalt with a character outside the Base64 alphabet.
-- no rows: every salt is Base64.
SELECT BusinessEntityID, PasswordSalt
FROM Person.Password
WHERE PasswordSalt COLLATE Latin1_General_BIN LIKE '%[^A-Za-z0-9+/=]%'
;

-- a PasswordSalt more than one person has.
-- no rows: every salt is unique.
SELECT PasswordSalt, COUNT(*) AS passwords
FROM Person.Password
GROUP BY PasswordSalt
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- the schema has no unique key on rowguid here; a rowguid more than one row has.
-- no rows: every rowguid is unique.
SELECT rowguid, COUNT(*) AS passwords
FROM Person.Password
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2015-04-15 16:33:33.060 to 2025-06-29 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.Password
;

-- a row whose ModifiedDate is not its person's.
-- no rows: every password was written with its person.
SELECT w.BusinessEntityID, w.ModifiedDate, p.ModifiedDate AS PersonModifiedDate
FROM Person.Password w
         JOIN Person.Person p ON p.BusinessEntityID = w.BusinessEntityID
WHERE w.ModifiedDate <> p.ModifiedDate
;

