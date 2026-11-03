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
-- Profiling queries on the view Purchasing.vVendorWithContacts, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Purchasing_vVendorWithContacts.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, how many distinct BusinessEntityID, and the smallest and the largest; 156 contacts of the 104 vendors,
-- 1492 to 1698.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM Purchasing.vVendorWithContacts
;

-- how many contacts a vendor has, and how many vendors have that many; 74 vendors have 1, 8 have 2 and 22 have 3.
SELECT contacts, COUNT(*) AS vendors
FROM (SELECT BusinessEntityID, COUNT(*) AS contacts
      FROM Purchasing.vVendorWithContacts
      GROUP BY BusinessEntityID) c
GROUP BY contacts
ORDER BY contacts ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 5 and 33 characters.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Purchasing.vVendorWithContacts
;

-- a Name which is blank, or carries leading or trailing spaces.
-- no rows: every Name is trimmed and not blank.
SELECT BusinessEntityID, '[' + Name + ']' AS Name
FROM Purchasing.vVendorWithContacts
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;


-- --------------------------------------------------------------------------------------------------------- ContactType
-- the values; Assistant Sales Agent 18, Sales Agent 54, Sales Associate 44 and Sales Manager 40.
SELECT ContactType, COUNT(*) AS contacts
FROM Purchasing.vVendorWithContacts
GROUP BY ContactType
ORDER BY ContactType ASC
;


-- --------------------------------------------------------------------------------------------------------------- Title
-- the values; NULL 4, Mr. 90 and Ms. 62.
SELECT Title, COUNT(*) AS contacts
FROM Purchasing.vVendorWithContacts
GROUP BY Title
ORDER BY Title ASC
;


-- ----------------------------------------------------------------------------------------------------------- FirstName
-- the shortest and the longest FirstName; 2 and 9 characters.
SELECT MIN(LEN(FirstName)), MAX(LEN(FirstName))
FROM Purchasing.vVendorWithContacts
;

-- a FirstName which is blank, or carries leading or trailing spaces.
-- no rows: every FirstName is trimmed and not blank.
SELECT BusinessEntityID, '[' + FirstName + ']' AS FirstName
FROM Purchasing.vVendorWithContacts
WHERE TRIM(FirstName) = ''
   OR DATALENGTH(FirstName) <> DATALENGTH(TRIM(FirstName))
;


-- ---------------------------------------------------------------------------------------------------------- MiddleName
-- how many rows leave MiddleName NULL, and the shortest and the longest; 70 NULLs; 1 to 15 characters.
SELECT COUNT(*) - COUNT(MiddleName) AS nulls, MIN(LEN(MiddleName)), MAX(LEN(MiddleName))
FROM Purchasing.vVendorWithContacts
;

-- a MiddleName which is blank, or carries leading or trailing spaces.
-- no rows: every MiddleName is trimmed and not blank.
SELECT BusinessEntityID, '[' + MiddleName + ']' AS MiddleName
FROM Purchasing.vVendorWithContacts
WHERE TRIM(MiddleName) = ''
   OR DATALENGTH(MiddleName) <> DATALENGTH(TRIM(MiddleName))
;


-- ------------------------------------------------------------------------------------------------------------ LastName
-- the shortest and the longest LastName; 2 and 11 characters.
SELECT MIN(LEN(LastName)), MAX(LEN(LastName))
FROM Purchasing.vVendorWithContacts
;

-- a LastName which is blank, or carries leading or trailing spaces.
-- no rows: every LastName is trimmed and not blank.
SELECT BusinessEntityID, '[' + LastName + ']' AS LastName
FROM Purchasing.vVendorWithContacts
WHERE TRIM(LastName) = ''
   OR DATALENGTH(LastName) <> DATALENGTH(TRIM(LastName))
;


-- -------------------------------------------------------------------------------------------------------------- Suffix
-- the values; NULL 152, Jr. 3 and Sr. 1.
SELECT Suffix, COUNT(*) AS contacts
FROM Purchasing.vVendorWithContacts
GROUP BY Suffix
ORDER BY Suffix ASC
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- how many rows leave PhoneNumber NULL, and the shortest and the longest; no NULL; 12 characters each.
SELECT COUNT(*) - COUNT(PhoneNumber) AS nulls, MIN(LEN(PhoneNumber)), MAX(LEN(PhoneNumber))
FROM Purchasing.vVendorWithContacts
;

-- a PhoneNumber which is blank, or carries leading or trailing spaces.
-- no rows: every PhoneNumber is trimmed and not blank.
SELECT BusinessEntityID, '[' + PhoneNumber + ']' AS PhoneNumber
FROM Purchasing.vVendorWithContacts
WHERE TRIM(PhoneNumber) = ''
   OR DATALENGTH(PhoneNumber) <> DATALENGTH(TRIM(PhoneNumber))
;


-- ----------------------------------------------------------------------------------------------------- PhoneNumberType
-- the values; Cell 69 and Work 87.
SELECT PhoneNumberType, COUNT(*) AS contacts
FROM Purchasing.vVendorWithContacts
GROUP BY PhoneNumberType
ORDER BY PhoneNumberType ASC
;


-- -------------------------------------------------------------------------------------------------------- EmailAddress
-- how many rows leave EmailAddress NULL, and the shortest and the longest; no NULL; 23 to 30 characters.
SELECT COUNT(*) - COUNT(EmailAddress) AS nulls, MIN(LEN(EmailAddress)), MAX(LEN(EmailAddress))
FROM Purchasing.vVendorWithContacts
;

-- an EmailAddress which is blank, or carries leading or trailing spaces.
-- no rows: every EmailAddress is trimmed and not blank.
SELECT BusinessEntityID, '[' + EmailAddress + ']' AS EmailAddress
FROM Purchasing.vVendorWithContacts
WHERE TRIM(EmailAddress) = ''
   OR DATALENGTH(EmailAddress) <> DATALENGTH(TRIM(EmailAddress))
;


-- ------------------------------------------------------------------------------------------------------ EmailPromotion
-- the values; 0 88, 1 45 and 2 23.
SELECT EmailPromotion, COUNT(*) AS contacts
FROM Purchasing.vVendorWithContacts
GROUP BY EmailPromotion
ORDER BY EmailPromotion ASC
;

