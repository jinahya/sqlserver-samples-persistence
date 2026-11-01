-- -
-- #%L
-- mssql-sample-databases-persistence-adventureworks
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
-- Profiling queries on Person.AddressType, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_AddressType.sql
--


-- ------------------------------------------------------------------------------------------------------- AddressTypeID
-- how many rows, and the smallest and the largest AddressTypeID; 6 address types, 1 to 6.
SELECT COUNT(*), MIN(AddressTypeID), MAX(AddressTypeID)
FROM Person.AddressType
;

-- an AddressTypeID whose next value is missing, below the largest.
-- no rows: AddressTypeID runs from 1 without a gap.
SELECT a.AddressTypeID + 1 AS missing_from
FROM Person.AddressType a
WHERE a.AddressTypeID < (SELECT MAX(AddressTypeID) FROM Person.AddressType)
  AND NOT EXISTS (SELECT 1
                  FROM Person.AddressType b
                  WHERE b.AddressTypeID = a.AddressTypeID + 1)
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 4 and 11 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Person.AddressType
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT AddressTypeID, '[' + Name + ']' AS Name
FROM Person.AddressType
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_AddressType_Name keeps Name unique.
-- no rows: no two address types share a Name.
SELECT Name, COUNT(*) AS address_types
FROM Person.AddressType
GROUP BY Name
HAVING COUNT(*) > 1
;

-- every Name; Billing, Home, Main Office, Primary, Shipping and Archive, 1 to 6.
SELECT AddressTypeID, Name
FROM Person.AddressType
ORDER BY AddressTypeID ASC
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_AddressType_rowguid keeps rowguid unique.
-- no rows: no two address types share a rowguid.
SELECT rowguid, COUNT(*) AS address_types
FROM Person.AddressType
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.AddressType
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the addresses of each address type, the most used first; Home (2) with 18774, Main Office (3) with 805,
-- Shipping (5) with 35, and Billing (1), Primary (4) and Archive (6) with none.
SELECT t.AddressTypeID, t.Name, COUNT(a.AddressTypeID) AS addresses
FROM Person.AddressType t
         LEFT JOIN Person.BusinessEntityAddress a ON a.AddressTypeID = t.AddressTypeID
GROUP BY t.AddressTypeID, t.Name
ORDER BY addresses DESC, t.AddressTypeID ASC
;

