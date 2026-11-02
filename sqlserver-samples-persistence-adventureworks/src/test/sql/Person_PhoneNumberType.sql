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
-- Profiling queries on Person.PhoneNumberType, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_PhoneNumberType.sql
--


-- --------------------------------------------------------------------------------------------------- PhoneNumberTypeID
-- how many rows, and the smallest and the largest PhoneNumberTypeID; 3 phone number types, 1 to 3.
SELECT COUNT(*), MIN(PhoneNumberTypeID), MAX(PhoneNumberTypeID)
FROM Person.PhoneNumberType
;

-- a PhoneNumberTypeID whose next value is missing, below the largest.
-- no rows: PhoneNumberTypeID runs from 1 without a gap.
SELECT a.PhoneNumberTypeID + 1 AS missing_from
FROM Person.PhoneNumberType a
WHERE a.PhoneNumberTypeID < (SELECT MAX(PhoneNumberTypeID) FROM Person.PhoneNumberType)
  AND NOT EXISTS (SELECT 1
                  FROM Person.PhoneNumberType b
                  WHERE b.PhoneNumberTypeID = a.PhoneNumberTypeID + 1)
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 4 characters each, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Person.PhoneNumberType
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT PhoneNumberTypeID, '[' + Name + ']' AS Name
FROM Person.PhoneNumberType
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- the schema has no unique key on Name; a Name more than one phone number type has.
-- no rows: every Name is unique.
SELECT Name, COUNT(*) AS phone_number_types
FROM Person.PhoneNumberType
GROUP BY Name
HAVING COUNT(*) > 1
;

-- every Name; Cell, Home and Work, 1 to 3.
SELECT PhoneNumberTypeID, Name
FROM Person.PhoneNumberType
ORDER BY PhoneNumberTypeID ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2017-12-13 13:19:22.273, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.PhoneNumberType
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the phone numbers of each phone number type; Cell (1) with 10096, Home (2) with 9140 and Work (3) with 736.
SELECT t.PhoneNumberTypeID, t.Name, COUNT(p.PhoneNumberTypeID) AS phone_numbers
FROM Person.PhoneNumberType t
         LEFT JOIN Person.PersonPhone p ON p.PhoneNumberTypeID = t.PhoneNumberTypeID
GROUP BY t.PhoneNumberTypeID, t.Name
ORDER BY t.PhoneNumberTypeID ASC
;

