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
-- Profiling queries on Person.PersonPhone, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_PersonPhone.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and how many persons they cover; 19972 rows, one for each of the 19972 persons.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM Person.PersonPhone
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- the shortest and the longest PhoneNumber; 12 and 19 characters, of the 25 the column allows.
SELECT MIN(LEN(PhoneNumber)), MAX(LEN(PhoneNumber))
FROM Person.PersonPhone
;

-- a PhoneNumber which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH
-- does not.
-- no rows: every PhoneNumber is trimmed and not blank.
SELECT BusinessEntityID, '[' + PhoneNumber + ']' AS PhoneNumber
FROM Person.PersonPhone
WHERE TRIM(PhoneNumber) = ''
   OR DATALENGTH(PhoneNumber) <> DATALENGTH(TRIM(PhoneNumber))
;

-- the two shapes of PhoneNumber; 1 (99) 500 555-9999 on 9187, 999-999-9999 on 10784, and one of neither.
SELECT CASE
           WHEN PhoneNumber LIKE '[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]' THEN '999-999-9999'
           WHEN PhoneNumber LIKE '1 ([0-9][0-9]) 500 555-[0-9][0-9][0-9][0-9]' THEN '1 (99) 500 555-9999'
           ELSE 'other'
           END  AS shape,
       COUNT(*) AS phone_numbers
FROM Person.PersonPhone
GROUP BY CASE
             WHEN PhoneNumber LIKE '[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]' THEN '999-999-9999'
             WHEN PhoneNumber LIKE '1 ([0-9][0-9]) 500 555-[0-9][0-9][0-9][0-9]' THEN '1 (99) 500 555-9999'
             ELSE 'other'
             END
ORDER BY shape ASC
;

-- a PhoneNumber of neither shape; one, 55-2555-0100 of 2090.
SELECT BusinessEntityID, PhoneNumber
FROM Person.PersonPhone
WHERE PhoneNumber NOT LIKE '[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'
  AND PhoneNumber NOT LIKE '1 ([0-9][0-9]) 500 555-[0-9][0-9][0-9][0-9]'
;

-- the numbers are fictional, 555-0100 to 555-0199; a PhoneNumber which does not end so. 3, 330-555-2568 of 10,
-- 810-939-7555 of 2176 and 779-443-5553 of 2266.
SELECT BusinessEntityID, PhoneNumber
FROM Person.PersonPhone
WHERE PhoneNumber NOT LIKE '%555-01[0-9][0-9]'
ORDER BY BusinessEntityID ASC
;

-- IX_PersonPhone_PhoneNumber is not unique; how many numbers more than one person has, and by how many persons the
-- most shared one is. 939; 209.
SELECT COUNT(*) AS shared, MAX(persons) AS most
FROM (SELECT PhoneNumber, COUNT(*) AS persons
      FROM Person.PersonPhone
      GROUP BY PhoneNumber
      HAVING COUNT(*) > 1) s
;


-- --------------------------------------------------------------------------------------------------- PhoneNumberTypeID
-- the phone number types the rows use; Cell (1) on 10096, Home (2) on 9140 and Work (3) on 736.
SELECT PhoneNumberTypeID, COUNT(*) AS phone_numbers
FROM Person.PersonPhone
GROUP BY PhoneNumberTypeID
ORDER BY PhoneNumberTypeID ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2015-04-15 16:33:33.060 to 2025-06-29 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.PersonPhone
;

-- a row whose ModifiedDate is not its person's.
-- no rows: every phone number was written with its person.
SELECT h.BusinessEntityID, h.ModifiedDate, p.ModifiedDate AS PersonModifiedDate
FROM Person.PersonPhone h
         JOIN Person.Person p ON p.BusinessEntityID = h.BusinessEntityID
WHERE h.ModifiedDate <> p.ModifiedDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the shape of PhoneNumber follows the country of the person's address; by country, the persons with an address.
-- 999-999-9999 (12) only in US and CA; 1 (99) 500 555-9999 (19) elsewhere, but for 15 to 26 numbers in each.
SELECT s.CountryRegionCode, LEN(h.PhoneNumber) AS length, COUNT(*) AS phone_numbers
FROM Person.PersonPhone h
         JOIN Person.BusinessEntityAddress b ON b.BusinessEntityID = h.BusinessEntityID
         JOIN Person.Address a ON a.AddressID = b.AddressID
         JOIN Person.StateProvince s ON s.StateProvinceID = a.StateProvinceID
GROUP BY s.CountryRegionCode, LEN(h.PhoneNumber)
ORDER BY s.CountryRegionCode ASC, length ASC
;

