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
-- Profiling queries on Person.EmailAddress, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_EmailAddress.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and how many persons they cover; 19972 rows, one for each of the 19972 persons.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM Person.EmailAddress
;


-- ------------------------------------------------------------------------------------------------------ EmailAddressID
-- the smallest and the largest EmailAddressID; 1 to 19972.
SELECT MIN(EmailAddressID), MAX(EmailAddressID)
FROM Person.EmailAddress
;

-- an EmailAddressID whose next value is missing, below the largest.
-- no rows: EmailAddressID runs from 1 without a gap.
SELECT a.EmailAddressID + 1 AS missing_from
FROM Person.EmailAddress a
WHERE a.EmailAddressID < (SELECT MAX(EmailAddressID) FROM Person.EmailAddress)
  AND NOT EXISTS (SELECT 1
                  FROM Person.EmailAddress b
                  WHERE b.EmailAddressID = a.EmailAddressID + 1)
;

-- EmailAddressID is an identity, but the key is the pair; an EmailAddressID more than one row has.
-- no rows: EmailAddressID is unique on its own.
SELECT EmailAddressID, COUNT(*) AS email_addresses
FROM Person.EmailAddress
GROUP BY EmailAddressID
HAVING COUNT(*) > 1
;

-- EmailAddressID does not follow BusinessEntityID; how many rows have the two differ, and by how much. 19681, the
-- id 1 to 805 below the person's.
SELECT COUNT(*) AS differ, MIN(BusinessEntityID - EmailAddressID), MAX(BusinessEntityID - EmailAddressID)
FROM Person.EmailAddress
WHERE EmailAddressID <> BusinessEntityID
;


-- -------------------------------------------------------------------------------------------------------- EmailAddress
-- EmailAddress is nullable; how many rows have none, and how long the others are. None; 22 to 43 characters, of the
-- 50 the column allows.
SELECT COUNT(*) - COUNT(EmailAddress) AS without, MIN(LEN(EmailAddress)), MAX(LEN(EmailAddress))
FROM Person.EmailAddress
;

-- an EmailAddress not shaped as local@domain.tld, with a space, or with a second @.
-- no rows: every address is well formed.
SELECT BusinessEntityID, EmailAddress
FROM Person.EmailAddress
WHERE EmailAddress NOT LIKE '_%@_%._%'
   OR EmailAddress LIKE '% %'
   OR EmailAddress LIKE '%@%@%'
;

-- the domains; adventure-works.com only, on all 19972.
SELECT SUBSTRING(EmailAddress, CHARINDEX('@', EmailAddress) + 1, 50) AS domain, COUNT(*) AS email_addresses
FROM Person.EmailAddress
GROUP BY SUBSTRING(EmailAddress, CHARINDEX('@', EmailAddress) + 1, 50)
;

-- an EmailAddress with an upper case letter.
-- no rows: every address is lower case.
SELECT BusinessEntityID, EmailAddress
FROM Person.EmailAddress
WHERE EmailAddress COLLATE Latin1_General_BIN <> LOWER(EmailAddress)
;

-- IX_EmailAddress_EmailAddress is not unique; an EmailAddress more than one row has.
-- no rows: every address is unique.
SELECT EmailAddress, COUNT(*) AS email_addresses
FROM Person.EmailAddress
GROUP BY EmailAddress
HAVING COUNT(*) > 1
;

-- the local part is the person's FirstName in lower case, with spaces and periods dropped, and a number; a local
-- part which is not.
-- no rows: every address follows the first name.
SELECT e.BusinessEntityID, e.EmailAddress, p.FirstName
FROM Person.EmailAddress e
         JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
WHERE LEFT(e.EmailAddress, CHARINDEX('@', e.EmailAddress) - 1) COLLATE Latin1_General_BIN
          NOT LIKE LOWER(REPLACE(REPLACE(p.FirstName, ' ', ''), '.', '')) + '[0-9]%'
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- the schema has no unique key on rowguid here; a rowguid more than one row has.
-- no rows: every rowguid is unique.
SELECT rowguid, COUNT(*) AS email_addresses
FROM Person.EmailAddress
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2015-04-15 16:33:33.060 to 2025-06-29 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.EmailAddress
;

-- a row whose ModifiedDate is not its person's.
-- no rows: every address was written with its person.
SELECT e.BusinessEntityID, e.ModifiedDate, p.ModifiedDate AS PersonModifiedDate
FROM Person.EmailAddress e
         JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
WHERE e.ModifiedDate <> p.ModifiedDate
;

