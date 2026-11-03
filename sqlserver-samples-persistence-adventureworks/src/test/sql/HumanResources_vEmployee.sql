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
-- Profiling queries on the view HumanResources.vEmployee, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_vEmployee.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, how many distinct BusinessEntityID, and the smallest and the largest; 290, one per employee, 1 to 290;
-- every employee has a single address, phone and email address.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM HumanResources.vEmployee
;


-- --------------------------------------------------------------------------------------------------------------- Title
-- the values; NULL 282, Mr. 5 and Ms. 3.
SELECT Title, COUNT(*) AS employees
FROM HumanResources.vEmployee
GROUP BY Title
ORDER BY Title ASC
;


-- ----------------------------------------------------------------------------------------------------------- FirstName
-- the shortest and the longest FirstName; 2 and 11 characters, of the 50 the column allows.
SELECT MIN(LEN(FirstName)), MAX(LEN(FirstName))
FROM HumanResources.vEmployee
;

-- a FirstName which is blank, or carries leading or trailing spaces.
-- no rows: every FirstName is trimmed and not blank.
SELECT BusinessEntityID, '[' + FirstName + ']' AS FirstName
FROM HumanResources.vEmployee
WHERE TRIM(FirstName) = ''
   OR DATALENGTH(FirstName) <> DATALENGTH(TRIM(FirstName))
;


-- ---------------------------------------------------------------------------------------------------------- MiddleName
-- how many rows leave MiddleName NULL, and the shortest and the longest; 12 NULLs; 1 to 16 characters.
SELECT COUNT(*) - COUNT(MiddleName) AS nulls, MIN(LEN(MiddleName)), MAX(LEN(MiddleName))
FROM HumanResources.vEmployee
;

-- a MiddleName which is blank, or carries leading or trailing spaces.
-- no rows: every MiddleName is trimmed and not blank.
SELECT BusinessEntityID, '[' + MiddleName + ']' AS MiddleName
FROM HumanResources.vEmployee
WHERE TRIM(MiddleName) = ''
   OR DATALENGTH(MiddleName) <> DATALENGTH(TRIM(MiddleName))
;


-- ------------------------------------------------------------------------------------------------------------ LastName
-- the shortest and the longest LastName; 2 and 17 characters, of the 50 the column allows.
SELECT MIN(LEN(LastName)), MAX(LEN(LastName))
FROM HumanResources.vEmployee
;

-- a LastName which is blank, or carries leading or trailing spaces.
-- no rows: every LastName is trimmed and not blank.
SELECT BusinessEntityID, '[' + LastName + ']' AS LastName
FROM HumanResources.vEmployee
WHERE TRIM(LastName) = ''
   OR DATALENGTH(LastName) <> DATALENGTH(TRIM(LastName))
;


-- -------------------------------------------------------------------------------------------------------------- Suffix
-- the values; NULL 288, III 1 and Jr. 1.
SELECT Suffix, COUNT(*) AS employees
FROM HumanResources.vEmployee
GROUP BY Suffix
ORDER BY Suffix ASC
;


-- ------------------------------------------------------------------------------------------------------------ JobTitle
-- the shortest and the longest JobTitle; 5 and 40 characters.
SELECT MIN(LEN(JobTitle)), MAX(LEN(JobTitle))
FROM HumanResources.vEmployee
;

-- a JobTitle which is blank, or carries leading or trailing spaces.
-- no rows: every JobTitle is trimmed and not blank.
SELECT BusinessEntityID, '[' + JobTitle + ']' AS JobTitle
FROM HumanResources.vEmployee
WHERE TRIM(JobTitle) = ''
   OR DATALENGTH(JobTitle) <> DATALENGTH(TRIM(JobTitle))
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- how many rows leave PhoneNumber NULL, and the shortest and the longest; no NULL; 12 to 19 characters.
SELECT COUNT(*) - COUNT(PhoneNumber) AS nulls, MIN(LEN(PhoneNumber)), MAX(LEN(PhoneNumber))
FROM HumanResources.vEmployee
;

-- a PhoneNumber which is blank, or carries leading or trailing spaces.
-- no rows: every PhoneNumber is trimmed and not blank.
SELECT BusinessEntityID, '[' + PhoneNumber + ']' AS PhoneNumber
FROM HumanResources.vEmployee
WHERE TRIM(PhoneNumber) = ''
   OR DATALENGTH(PhoneNumber) <> DATALENGTH(TRIM(PhoneNumber))
;


-- ----------------------------------------------------------------------------------------------------- PhoneNumberType
-- the values; Cell 141 and Work 149.
SELECT PhoneNumberType, COUNT(*) AS employees
FROM HumanResources.vEmployee
GROUP BY PhoneNumberType
ORDER BY PhoneNumberType ASC
;


-- -------------------------------------------------------------------------------------------------------- EmailAddress
-- how many rows leave EmailAddress NULL, and the shortest and the longest; no NULL; 23 to 32 characters.
SELECT COUNT(*) - COUNT(EmailAddress) AS nulls, MIN(LEN(EmailAddress)), MAX(LEN(EmailAddress))
FROM HumanResources.vEmployee
;

-- an EmailAddress which is blank, or carries leading or trailing spaces.
-- no rows: every EmailAddress is trimmed and not blank.
SELECT BusinessEntityID, '[' + EmailAddress + ']' AS EmailAddress
FROM HumanResources.vEmployee
WHERE TRIM(EmailAddress) = ''
   OR DATALENGTH(EmailAddress) <> DATALENGTH(TRIM(EmailAddress))
;


-- ------------------------------------------------------------------------------------------------------ EmailPromotion
-- the values; 0 162, 1 61 and 2 67.
SELECT EmailPromotion, COUNT(*) AS employees
FROM HumanResources.vEmployee
GROUP BY EmailPromotion
ORDER BY EmailPromotion ASC
;


-- -------------------------------------------------------------------------------------------------------- AddressLine1
-- the shortest and the longest AddressLine1; 9 and 26 characters.
SELECT MIN(LEN(AddressLine1)), MAX(LEN(AddressLine1))
FROM HumanResources.vEmployee
;

-- an AddressLine1 which is blank, or carries leading or trailing spaces.
-- no rows: every AddressLine1 is trimmed and not blank.
SELECT BusinessEntityID, '[' + AddressLine1 + ']' AS AddressLine1
FROM HumanResources.vEmployee
WHERE TRIM(AddressLine1) = ''
   OR DATALENGTH(AddressLine1) <> DATALENGTH(TRIM(AddressLine1))
;


-- -------------------------------------------------------------------------------------------------------- AddressLine2
-- how many rows leave AddressLine2 NULL, and the shortest and the longest; 282 NULLs; 3 to 6 characters.
SELECT COUNT(*) - COUNT(AddressLine2) AS nulls, MIN(LEN(AddressLine2)), MAX(LEN(AddressLine2))
FROM HumanResources.vEmployee
;

-- an AddressLine2 which is blank, or carries leading or trailing spaces.
-- no rows: every AddressLine2 is trimmed and not blank.
SELECT BusinessEntityID, '[' + AddressLine2 + ']' AS AddressLine2
FROM HumanResources.vEmployee
WHERE TRIM(AddressLine2) = ''
   OR DATALENGTH(AddressLine2) <> DATALENGTH(TRIM(AddressLine2))
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- the shortest and the longest City; 4 and 13 characters.
SELECT MIN(LEN(City)), MAX(LEN(City))
FROM HumanResources.vEmployee
;

-- a City which is blank, or carries leading or trailing spaces.
-- no rows: every City is trimmed and not blank.
SELECT BusinessEntityID, '[' + City + ']' AS City
FROM HumanResources.vEmployee
WHERE TRIM(City) = ''
   OR DATALENGTH(City) <> DATALENGTH(TRIM(City))
;


-- --------------------------------------------------------------------------------------------------- StateProvinceName
-- how many states and provinces, and the most common; 14; Washington with 275, then California and Minnesota with 2.
SELECT TOP (3) StateProvinceName, COUNT(*) AS employees, COUNT(*) OVER () AS provinces
FROM HumanResources.vEmployee
GROUP BY StateProvinceName
ORDER BY employees DESC, StateProvinceName ASC
;


-- ---------------------------------------------------------------------------------------------------------- PostalCode
-- the shortest and the longest PostalCode; 4 and 7 characters.
SELECT MIN(LEN(PostalCode)), MAX(LEN(PostalCode))
FROM HumanResources.vEmployee
;

-- a PostalCode which is blank, or carries leading or trailing spaces.
-- no rows: every PostalCode is trimmed and not blank.
SELECT BusinessEntityID, '[' + PostalCode + ']' AS PostalCode
FROM HumanResources.vEmployee
WHERE TRIM(PostalCode) = ''
   OR DATALENGTH(PostalCode) <> DATALENGTH(TRIM(PostalCode))
;


-- --------------------------------------------------------------------------------------------------- CountryRegionName
-- the values; 6 countries; United States with 284, Canada 2, the other four 1 each.
SELECT CountryRegionName, COUNT(*) AS employees
FROM HumanResources.vEmployee
GROUP BY CountryRegionName
ORDER BY CountryRegionName ASC
;


-- ----------------------------------------------------------------------------------------------- AdditionalContactInfo
-- how many rows leave AdditionalContactInfo NULL, and the smallest and the largest in bytes; all 290 NULL: no employee
-- has additional contact info.
SELECT COUNT(*) - COUNT(AdditionalContactInfo) AS nulls,
       MIN(DATALENGTH(AdditionalContactInfo)), MAX(DATALENGTH(AdditionalContactInfo))
FROM HumanResources.vEmployee
;

