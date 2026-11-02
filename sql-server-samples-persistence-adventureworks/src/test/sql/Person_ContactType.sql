-- -
-- #%L
-- sql-server-samples-persistence-adventureworks
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
-- Profiling queries on Person.ContactType, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_ContactType.sql
--


-- ------------------------------------------------------------------------------------------------------- ContactTypeID
-- how many rows, and the smallest and the largest ContactTypeID; 20 contact types, 1 to 20.
SELECT COUNT(*), MIN(ContactTypeID), MAX(ContactTypeID)
FROM Person.ContactType
;

-- a ContactTypeID whose next value is missing, below the largest.
-- no rows: ContactTypeID runs from 1 without a gap.
SELECT a.ContactTypeID + 1 AS missing_from
FROM Person.ContactType a
WHERE a.ContactTypeID < (SELECT MAX(ContactTypeID) FROM Person.ContactType)
  AND NOT EXISTS (SELECT 1
                  FROM Person.ContactType b
                  WHERE b.ContactTypeID = a.ContactTypeID + 1)
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 5 and 31 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Person.ContactType
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT ContactTypeID, '[' + Name + ']' AS Name
FROM Person.ContactType
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_ContactType_Name keeps Name unique.
-- no rows: no two contact types share a Name.
SELECT Name, COUNT(*) AS contact_types
FROM Person.ContactType
GROUP BY Name
HAVING COUNT(*) > 1
;

-- the installed rows are in the alphabetical order of Name; a row whose Name sorts before the previous one's.
-- no rows: ContactTypeID follows Name, from Accounting Manager (1) to Sales Representative (20).
SELECT b.ContactTypeID, b.Name, a.Name AS previous
FROM Person.ContactType a
         JOIN Person.ContactType b ON b.ContactTypeID = a.ContactTypeID + 1
WHERE b.Name < a.Name
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.ContactType
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the contact types BusinessEntityContact uses, and how many contacts it has; 7 of the 20, over 909 contacts.
SELECT COUNT(DISTINCT ContactTypeID) AS contact_types, COUNT(*) AS contacts
FROM Person.BusinessEntityContact
;

-- the three contact types with the most contacts; Owner (11) with 266, Purchasing Manager (15) with 245 and
-- Purchasing Agent (14) with 242.
SELECT TOP (3) t.ContactTypeID, t.Name, COUNT(*) AS contacts
FROM Person.ContactType t
         JOIN Person.BusinessEntityContact c ON c.ContactTypeID = t.ContactTypeID
GROUP BY t.ContactTypeID, t.Name
ORDER BY contacts DESC, t.ContactTypeID ASC
;

-- a contact type no BusinessEntityContact uses; 13 of the 20, Accounting Manager (1) among them.
SELECT COUNT(*) AS unused, MIN(t.ContactTypeID) AS smallest
FROM Person.ContactType t
WHERE NOT EXISTS (SELECT 1
                  FROM Person.BusinessEntityContact c
                  WHERE c.ContactTypeID = t.ContactTypeID)
;

