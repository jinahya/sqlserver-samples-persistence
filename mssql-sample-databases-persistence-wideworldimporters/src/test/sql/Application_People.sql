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
-- Profiling queries on Application.People, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_People.sql
--


-- ------------------------------------------------------------------------------------------------------------ PersonID
-- how many rows, and the smallest and the largest PersonID; 1111 people, 1 to 3261.
SELECT COUNT(*), MIN(PersonID), MAX(PersonID)
FROM Application.People
;

-- PersonID comes from Sequences.PersonID and leaves gaps; how many values below the largest are unused. 2150.
SELECT MAX(PersonID) - COUNT(*) AS unused
FROM Application.People
;

-- person 1 is not a person but the author of the converted rows.
-- one row: Data Conversion Only, with no logon, phone, fax or e-mail.
SELECT PersonID, FullName, LogonName, PhoneNumber, FaxNumber, EmailAddress
FROM Application.People
WHERE PersonID = 1
;


-- ------------------------------------------------------------------------------------------------------------ FullName
-- the shortest and the longest FullName; 5 and 29 characters, of the 50 the column allows.
SELECT MIN(LEN(FullName)), MAX(LEN(FullName))
FROM Application.People
;

-- a FullName which is blank, carries leading or trailing spaces, or two spaces in a row.
-- 10 rows: David Novacek (3217) ends with a space, and 9 others have two or more spaces between their names.
SELECT PersonID, '[' + FullName + ']' AS FullName
FROM Application.People
WHERE TRIM(FullName) = ''
   OR DATALENGTH(FullName) <> DATALENGTH(TRIM(FullName))
   OR FullName LIKE '%  %'
ORDER BY PersonID ASC
;

-- a FullName with a character outside printable ASCII.
-- 15 rows, every one mojibake, UTF-8 read as Windows-1252: ChÃ¡vez for Chávez, VÄ›ra for Věra.
SELECT PersonID, FullName
FROM Application.People
WHERE FullName COLLATE Latin1_General_BIN LIKE N'%[^ -~]%'
ORDER BY PersonID ASC
;

-- IX_Application_People_FullName is not unique; a FullName more than one person has.
-- no rows: every FullName is unique.
SELECT FullName, COUNT(*) AS people
FROM Application.People
GROUP BY FullName
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------- PreferredName
-- the shortest and the longest PreferredName; 2 and 20 characters, of the 50 the column allows.
SELECT MIN(LEN(PreferredName)), MAX(LEN(PreferredName))
FROM Application.People
;

-- a PreferredName which is blank, or carries leading or trailing spaces.
-- 8 rows, 8 of the 9 whose FullName has the extra spaces; each PreferredName ends with them.
SELECT PersonID, '[' + PreferredName + ']' AS PreferredName
FROM Application.People
WHERE TRIM(PreferredName) = ''
   OR DATALENGTH(PreferredName) <> DATALENGTH(TRIM(PreferredName))
ORDER BY PersonID ASC
;

-- a PreferredName which is not how the FullName begins.
-- one row: Olly, for Oliver Kivi (22).
SELECT PersonID, FullName, PreferredName
FROM Application.People
WHERE CHARINDEX(PreferredName, FullName) <> 1
;


-- ---------------------------------------------------------------------------------------------------------- SearchName
-- SearchName is computed, CONCAT(PreferredName, N' ', FullName); a row where it is not.
-- no rows: every SearchName is the PreferredName, a space and the FullName.
SELECT PersonID, SearchName
FROM Application.People
WHERE SearchName <> CONCAT(PreferredName, N' ', FullName)
;

-- the shortest and the longest SearchName; 8 and 46 characters, of the 101 the column allows.
SELECT MIN(LEN(SearchName)), MAX(LEN(SearchName))
FROM Application.People
;


-- -------------------------------------------------------------------------------------------------- IsPermittedToLogon
-- how many people may log on; 155 of the 1111.
SELECT IsPermittedToLogon, COUNT(*) AS people
FROM Application.People
GROUP BY IsPermittedToLogon
ORDER BY IsPermittedToLogon ASC
;


-- ----------------------------------------------------------------------------------------------------------- LogonName
-- LogonName is nullable; how many people have none, and how many have the placeholder NO LOGON. None; 954.
SELECT COUNT(*) - COUNT(LogonName) AS without, SUM(CASE WHEN LogonName = 'NO LOGON' THEN 1 ELSE 0 END) AS no_logon
FROM Application.People
;

-- a person not permitted to log on whose LogonName is not NO LOGON.
-- 2 rows: Eva Muirden (5) and Archer Lamble (16), employees with a logon of their own.
SELECT PersonID, FullName, LogonName
FROM Application.People
WHERE IsPermittedToLogon = 0
  AND LogonName <> 'NO LOGON'
;

-- a person permitted to log on whose LogonName is not their EmailAddress.
-- no rows: the e-mail address is the logon.
SELECT PersonID, LogonName, EmailAddress
FROM Application.People
WHERE IsPermittedToLogon = 1
  AND LogonName <> EmailAddress
;

-- the schema has no unique key on LogonName; a real LogonName more than one person has.
-- 2 rows: cezar@tailspintoys.com and david@example.com, each for 2 people allowed to log on.
SELECT LogonName, COUNT(*) AS people
FROM Application.People
WHERE LogonName <> 'NO LOGON'
GROUP BY LogonName
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------- IsExternalLogonProvider
-- how many people log on through an external provider; none.
SELECT IsExternalLogonProvider, COUNT(*) AS people
FROM Application.People
GROUP BY IsExternalLogonProvider
ORDER BY IsExternalLogonProvider ASC
;


-- ------------------------------------------------------------------------------------------------------ HashedPassword
-- HashedPassword is a nullable varbinary; how many people have one, and how long it is. 157; 32 bytes each.
SELECT COUNT(HashedPassword) AS with_password, MIN(DATALENGTH(HashedPassword)), MAX(DATALENGTH(HashedPassword))
FROM Application.People
;

-- a person permitted to log on with no HashedPassword.
-- no rows: everyone who may log on has a password.
SELECT PersonID, FullName
FROM Application.People
WHERE IsPermittedToLogon = 1
  AND HashedPassword IS NULL
;


-- -------------------------------------------------------------------------------------------------------- IsSystemUser
-- how many people are system users; 39.
SELECT IsSystemUser, COUNT(*) AS people
FROM Application.People
GROUP BY IsSystemUser
ORDER BY IsSystemUser ASC
;


-- ---------------------------------------------------------------------------------------------------------- IsEmployee
-- how many people are employees; 19, every one a system user.
SELECT IsEmployee, IsSystemUser, COUNT(*) AS people
FROM Application.People
GROUP BY IsEmployee, IsSystemUser
ORDER BY IsEmployee ASC, IsSystemUser ASC
;


-- ------------------------------------------------------------------------------------------------------- IsSalesperson
-- a salesperson who is not an employee.
-- no rows: the 10 salespeople are employees.
SELECT PersonID, FullName
FROM Application.People
WHERE IsSalesperson = 1
  AND IsEmployee = 0
;

-- how many people are salespeople; 10.
SELECT IsSalesperson, COUNT(*) AS people
FROM Application.People
GROUP BY IsSalesperson
ORDER BY IsSalesperson ASC
;


-- ----------------------------------------------------------------------------------------------------- UserPreferences
-- UserPreferences is a nullable JSON text; how many people have it, how many of those are valid JSON, and how long.
-- 182, all valid; 272 to 308 bytes.
SELECT COUNT(UserPreferences) AS with_preferences, SUM(ISJSON(UserPreferences)) AS json,
       MIN(DATALENGTH(UserPreferences)), MAX(DATALENGTH(UserPreferences))
FROM Application.People
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- a PhoneNumber which is not of the form (999) 999-9999.
-- no rows: every PhoneNumber has that form.
SELECT PersonID, PhoneNumber
FROM Application.People
WHERE PhoneNumber NOT LIKE '([0-9][0-9][0-9]) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'
;

-- PhoneNumber is nullable; how many people have none, and how many distinct numbers the others have. 1; 71.
SELECT COUNT(*) - COUNT(PhoneNumber) AS without, COUNT(DISTINCT PhoneNumber) AS numbers
FROM Application.People
;


-- ----------------------------------------------------------------------------------------------------------- FaxNumber
-- a FaxNumber which is not of the form (999) 999-9999.
-- no rows: every FaxNumber has that form.
SELECT PersonID, FaxNumber
FROM Application.People
WHERE FaxNumber NOT LIKE '([0-9][0-9][0-9]) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'
;

-- FaxNumber is nullable; how many people have none, and how many distinct numbers the others have. 1; 61.
SELECT COUNT(*) - COUNT(FaxNumber) AS without, COUNT(DISTINCT FaxNumber) AS numbers
FROM Application.People
;


-- -------------------------------------------------------------------------------------------------------- EmailAddress
-- EmailAddress is nullable; how many people have none, and the shortest and the longest. 1; 14 and 33 characters.
SELECT COUNT(*) - COUNT(EmailAddress) AS without, MIN(LEN(EmailAddress)), MAX(LEN(EmailAddress))
FROM Application.People
;

-- an EmailAddress with a space, or a character outside printable ASCII.
-- 19 rows: 14 carry a space, such as an dung@tailspintoys.com, and 5 are mojibake, such as vä›ra@wingtiptoys.com.
SELECT PersonID, EmailAddress
FROM Application.People
WHERE EmailAddress LIKE '% %'
   OR EmailAddress COLLATE Latin1_General_BIN LIKE N'%[^ -~]%'
ORDER BY PersonID ASC
;

-- the schema has no unique key on EmailAddress; how many addresses more than one person has, and how many people.
-- 77 addresses, 158 people.
SELECT COUNT(*) AS addresses, SUM(d.people) AS people
FROM (SELECT EmailAddress, COUNT(*) AS people
      FROM Application.People
      GROUP BY EmailAddress
      HAVING COUNT(*) > 1) d
;


-- --------------------------------------------------------------------------------------------------------------- Photo
-- Photo is a nullable varbinary; how many people have one. None.
SELECT COUNT(Photo) AS with_photo
FROM Application.People
;


-- -------------------------------------------------------------------------------------------------------- CustomFields
-- CustomFields is a nullable JSON text; how many people have it, how many of those are valid JSON, and how many are
-- employees. 19, all valid, all of the 19 employees.
SELECT COUNT(CustomFields) AS with_fields, SUM(ISJSON(CustomFields)) AS json,
       SUM(CASE WHEN CustomFields IS NOT NULL AND IsEmployee = 1 THEN 1 ELSE 0 END) AS employees
FROM Application.People
;


-- ------------------------------------------------------------------------------------------------------ OtherLanguages
-- OtherLanguages is computed, JSON_QUERY(CustomFields, N'$.OtherLanguages'); a row where it is not.
-- no rows: OtherLanguages is the array in CustomFields, or NULL with it.
SELECT PersonID, OtherLanguages
FROM Application.People
WHERE ISNULL(OtherLanguages, N'') <> ISNULL(JSON_QUERY(CustomFields, N'$.OtherLanguages'), N'')
;

-- how many people have OtherLanguages, and how long they are; 19, 2 to 32 characters.
SELECT COUNT(OtherLanguages), MIN(LEN(OtherLanguages)), MAX(LEN(OtherLanguages))
FROM Application.People
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who last edited the people; person 1, Data Conversion Only, for all 1111.
SELECT p.LastEditedBy, e.FullName, COUNT(*) AS people
FROM Application.People p
         JOIN Application.People e ON e.PersonID = p.LastEditedBy
GROUP BY p.LastEditedBy, e.FullName
ORDER BY p.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- when the current rows begin; 873 on 2013-01-01, 182 at 2016-05-31 23:14, and 56 on other days between.
SELECT SUM(CASE WHEN ValidFrom = '2013-01-01' THEN 1 ELSE 0 END) AS first_day,
       SUM(CASE WHEN ValidFrom = '2016-05-31 23:14' THEN 1 ELSE 0 END) AS last_minute,
       SUM(CASE WHEN ValidFrom NOT IN ('2013-01-01', '2016-05-31 23:14') THEN 1 ELSE 0 END) AS others
FROM Application.People
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a current row of a system-versioned table is valid until the end of time.
-- no rows: every ValidTo is 9999-12-31 23:59:59.9999999.
SELECT PersonID, ValidTo
FROM Application.People
WHERE ValidTo <> '9999-12-31 23:59:59.9999999'
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the people whose history the archive keeps, and how many versions; 182 people, 961 versions.
SELECT COUNT(DISTINCT PersonID) AS people, COUNT(*) AS versions
FROM Application.People_Archive
;

-- a person neither a system user nor referred to as a contact by a customer or a supplier, nor as a salesperson or
-- a salesperson, contact or picker of an order.
-- one row: Data Conversion Only (1).
SELECT p.PersonID, p.FullName
FROM Application.People p
WHERE p.IsSystemUser = 0
  AND NOT EXISTS (SELECT 1
                  FROM Sales.Customers c
                  WHERE p.PersonID IN (c.PrimaryContactPersonID, c.AlternateContactPersonID))
  AND NOT EXISTS (SELECT 1
                  FROM Purchasing.Suppliers s
                  WHERE p.PersonID IN (s.PrimaryContactPersonID, s.AlternateContactPersonID))
  AND NOT EXISTS (SELECT 1
                  FROM Sales.Orders o
                  WHERE p.PersonID IN (o.SalespersonPersonID, o.ContactPersonID, o.PickedByPersonID))
;

