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
-- Profiling queries on Application.People_Archive, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_People_Archive.sql
--


-- ------------------------------------------------------------------------------------------------------------ PersonID
-- how many rows, how many people they keep the history of, and the smallest and the largest PersonID;
-- 961 versions of 182 people, 1 to 3237.
SELECT COUNT(*), COUNT(DISTINCT PersonID), MIN(PersonID), MAX(PersonID)
FROM Application.People_Archive
;

-- the people with the most archived versions; 14 and 1358 with 17 each, 19 with 16.
SELECT TOP (3) PersonID, COUNT(*) AS versions
FROM Application.People_Archive
GROUP BY PersonID
ORDER BY versions DESC, PersonID ASC
;

-- an archived PersonID with no current row; the history here is only of people still current.
-- no rows: every archived PersonID is still current.
SELECT a.PersonID
FROM Application.People_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Application.People p
                  WHERE p.PersonID = a.PersonID)
;


-- ------------------------------------------------------------------------------------------------------------ FullName
-- a FullName which is blank, carries leading or trailing spaces, or two spaces in a row.
-- 6 rows: 5 versions of David Novacek (3217), ending with a space, and one of Madelaine Cartier (31), with two
-- spaces between the names.
SELECT PersonID, '[' + FullName + ']' AS FullName
FROM Application.People_Archive
WHERE TRIM(FullName) = ''
   OR DATALENGTH(FullName) <> DATALENGTH(TRIM(FullName))
   OR FullName LIKE '%  %'
ORDER BY PersonID ASC
;

-- how many versions have a FullName with a character outside printable ASCII; 16, mojibake as in the current rows.
SELECT COUNT(*) AS versions
FROM Application.People_Archive
WHERE FullName COLLATE Latin1_General_BIN LIKE N'%[^ -~]%'
;


-- ------------------------------------------------------------------------------------------------------- PreferredName
-- a PreferredName which is blank, or carries leading or trailing spaces.
-- no rows: every archived PreferredName is trimmed and not blank.
SELECT PersonID, '[' + PreferredName + ']' AS PreferredName
FROM Application.People_Archive
WHERE TRIM(PreferredName) = ''
   OR DATALENGTH(PreferredName) <> DATALENGTH(TRIM(PreferredName))
;


-- ---------------------------------------------------------------------------------------------------------- SearchName
-- SearchName is a plain column here, not computed; a version where it is not CONCAT(PreferredName, N' ', FullName).
-- no rows: every SearchName was kept as computed.
SELECT PersonID, SearchName
FROM Application.People_Archive
WHERE SearchName <> CONCAT(PreferredName, N' ', FullName)
;


-- -------------------------------------------------------------------------------------------------- IsPermittedToLogon
-- how many versions may log on; 794 of the 961.
SELECT IsPermittedToLogon, COUNT(*) AS versions
FROM Application.People_Archive
GROUP BY IsPermittedToLogon
ORDER BY IsPermittedToLogon ASC
;


-- ----------------------------------------------------------------------------------------------------------- LogonName
-- a version permitted to log on whose LogonName is not its EmailAddress.
-- no rows: the e-mail address is the logon.
SELECT PersonID, LogonName, EmailAddress
FROM Application.People_Archive
WHERE IsPermittedToLogon = 1
  AND LogonName <> EmailAddress
;


-- --------------------------------------------------------------------------------------------- IsExternalLogonProvider
-- how many versions log on through an external provider; none.
SELECT IsExternalLogonProvider, COUNT(*) AS versions
FROM Application.People_Archive
GROUP BY IsExternalLogonProvider
ORDER BY IsExternalLogonProvider ASC
;


-- ------------------------------------------------------------------------------------------------------ HashedPassword
-- HashedPassword is a nullable varbinary; how many versions have none, and how long the others are. 163; 32 bytes.
SELECT COUNT(*) - COUNT(HashedPassword) AS without, MIN(DATALENGTH(HashedPassword)), MAX(DATALENGTH(HashedPassword))
FROM Application.People_Archive
;

-- a version permitted to log on with no HashedPassword.
-- no rows: every version which may log on has a password.
SELECT PersonID, ValidFrom
FROM Application.People_Archive
WHERE IsPermittedToLogon = 1
  AND HashedPassword IS NULL
;


-- -------------------------------------------------------------------------------------------------------- IsSystemUser
-- how many versions, and of how many people, are of system users; 221 versions of 39 people.
SELECT IsSystemUser, COUNT(*) AS versions, COUNT(DISTINCT PersonID) AS people
FROM Application.People_Archive
GROUP BY IsSystemUser
ORDER BY IsSystemUser ASC
;


-- ---------------------------------------------------------------------------------------------------------- IsEmployee
-- how many versions, and of how many people, are of employees; 193 versions of 19 people.
SELECT IsEmployee, COUNT(*) AS versions, COUNT(DISTINCT PersonID) AS people
FROM Application.People_Archive
GROUP BY IsEmployee
ORDER BY IsEmployee ASC
;


-- ------------------------------------------------------------------------------------------------------- IsSalesperson
-- how many versions, and of how many people, are of salespeople; 113 versions of 10 people.
SELECT IsSalesperson, COUNT(*) AS versions, COUNT(DISTINCT PersonID) AS people
FROM Application.People_Archive
GROUP BY IsSalesperson
ORDER BY IsSalesperson ASC
;


-- ----------------------------------------------------------------------------------------------------- UserPreferences
-- UserPreferences is a nullable JSON text; how many versions have it, how many of those are valid JSON, and how long.
-- 825, all valid; 254 bytes each.
SELECT COUNT(UserPreferences) AS with_preferences, SUM(ISJSON(UserPreferences)) AS json,
       MIN(DATALENGTH(UserPreferences)), MAX(DATALENGTH(UserPreferences))
FROM Application.People_Archive
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- PhoneNumber is nullable; a version without one. One row: Data Conversion Only (1).
SELECT PersonID, FullName
FROM Application.People_Archive
WHERE PhoneNumber IS NULL
;


-- ----------------------------------------------------------------------------------------------------------- FaxNumber
-- FaxNumber is nullable; how many versions have none, and how many distinct numbers the others have. 1; 55.
SELECT COUNT(*) - COUNT(FaxNumber) AS without, COUNT(DISTINCT FaxNumber) AS numbers
FROM Application.People_Archive
;


-- -------------------------------------------------------------------------------------------------------- EmailAddress
-- an EmailAddress with a space, or a character outside printable ASCII.
-- no rows: the broken addresses of the current rows are not in their history.
SELECT PersonID, EmailAddress
FROM Application.People_Archive
WHERE EmailAddress LIKE '% %'
   OR EmailAddress COLLATE Latin1_General_BIN LIKE N'%[^ -~]%'
;


-- --------------------------------------------------------------------------------------------------------------- Photo
-- Photo is a nullable varbinary; how many versions have one. None.
SELECT COUNT(Photo) AS with_photo
FROM Application.People_Archive
;


-- -------------------------------------------------------------------------------------------------------- CustomFields
-- CustomFields is a nullable JSON text; how many versions have it, and how many of those are valid JSON. 19, all.
SELECT COUNT(CustomFields) AS with_fields, SUM(ISJSON(CustomFields)) AS json
FROM Application.People_Archive
;


-- ------------------------------------------------------------------------------------------------------ OtherLanguages
-- OtherLanguages is a plain column here; a version where it is not JSON_QUERY(CustomFields, N'$.OtherLanguages').
-- no rows: every OtherLanguages was kept as computed.
SELECT PersonID, OtherLanguages
FROM Application.People_Archive
WHERE ISNULL(OtherLanguages, N'') <> ISNULL(JSON_QUERY(CustomFields, N'$.OtherLanguages'), N'')
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who edited the archived versions; person 1, Data Conversion Only, for all 961.
SELECT a.LastEditedBy, p.FullName, COUNT(*) AS versions
FROM Application.People_Archive a
         JOIN Application.People p ON p.PersonID = a.LastEditedBy
GROUP BY a.LastEditedBy, p.FullName
ORDER BY a.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range the versions span; from 2013-01-01 to 2016-05-31 23:14.
SELECT MIN(ValidFrom), MAX(ValidTo)
FROM Application.People_Archive
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a version which ends no later than it begins.
-- 3 rows: each ends at the moment it begins, people 8, 1339 and 2373; a row updated twice in one transaction leaves
-- such an empty version behind.
SELECT PersonID, ValidFrom, ValidTo
FROM Application.People_Archive
WHERE ValidTo <= ValidFrom
ORDER BY PersonID ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an archived version not followed, at its ValidTo, by the next version of the person, archived or current.
-- no rows: the versions of each person chain into the current row without a gap or an overlap.
SELECT v.PersonID, v.ValidTo, v.next_valid_from
FROM (SELECT u.PersonID, u.ValidTo, u.archived,
             LEAD(u.ValidFrom) OVER (PARTITION BY u.PersonID ORDER BY u.ValidFrom, u.ValidTo) AS next_valid_from
      FROM (SELECT PersonID, ValidFrom, ValidTo, 1 AS archived
            FROM Application.People_Archive
            UNION ALL
            SELECT PersonID, ValidFrom, ValidTo, 0
            FROM Application.People) u) v
WHERE v.archived = 1
  AND (v.next_valid_from IS NULL OR v.next_valid_from <> v.ValidTo)
;

