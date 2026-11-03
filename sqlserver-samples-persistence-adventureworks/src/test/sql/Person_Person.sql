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
-- Profiling queries on Person.Person, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_Person.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and the smallest and the largest BusinessEntityID; 19972 persons, 1 to 20777.
SELECT COUNT(*), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM Person.Person
;


-- ---------------------------------------------------------------------------------------------------------- PersonType
-- the person types, CK_Person_PersonType allows GC, SP, EM, IN, VC and SC; all six are used, IN (individual
-- customer) the most with 18484, SP (sales person) the fewest with 17.
SELECT PersonType, COUNT(*) AS persons
FROM Person.Person
GROUP BY PersonType
ORDER BY PersonType ASC
;

-- the check compares UPPER(PersonType), so it lets lower case in; a PersonType not in upper case.
-- no rows: every PersonType is upper case.
SELECT BusinessEntityID, PersonType
FROM Person.Person
WHERE PersonType COLLATE Latin1_General_BIN <> UPPER(PersonType)
;

-- an employee, EM or SP, which is not in HumanResources.Employee; or an Employee whose person is neither.
-- no rows: the 273 EM and the 17 SP are the 290 employees.
SELECT p.BusinessEntityID, p.PersonType
FROM Person.Person p
         FULL JOIN HumanResources.Employee e ON e.BusinessEntityID = p.BusinessEntityID
WHERE (p.PersonType IN ('EM', 'SP') AND e.BusinessEntityID IS NULL)
   OR (e.BusinessEntityID IS NOT NULL AND (p.PersonType IS NULL OR p.PersonType NOT IN ('EM', 'SP')))
;


-- ----------------------------------------------------------------------------------------------------------- NameStyle
-- NameStyle tells the eastern order of the names, last name first; 0 on all 19972 rows.
SELECT NameStyle, COUNT(*) AS persons
FROM Person.Person
GROUP BY NameStyle
;


-- --------------------------------------------------------------------------------------------------------------- Title
-- the titles, NULL on 18963 rows; Mr. on 577, Ms. on 415, Sr. on 11, Sra. on 3, Mrs. on 2, and Ms, without the period,
-- on 1.
SELECT Title, COUNT(*) AS persons
FROM Person.Person
GROUP BY Title
ORDER BY persons DESC, Title ASC
;


-- ----------------------------------------------------------------------------------------------------------- FirstName
-- the shortest and the longest FirstName, and how many distinct; 2 and 24 characters, of the 50 the column allows,
-- 1018 names.
SELECT MIN(LEN(FirstName)), MAX(LEN(FirstName)), COUNT(DISTINCT FirstName)
FROM Person.Person
;

-- a FirstName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH
-- does not.
-- no rows: every FirstName is trimmed and not blank.
SELECT BusinessEntityID, '[' + FirstName + ']' AS FirstName
FROM Person.Person
WHERE TRIM(FirstName) = ''
   OR DATALENGTH(FirstName) <> DATALENGTH(TRIM(FirstName))
;


-- ---------------------------------------------------------------------------------------------------------- MiddleName
-- MiddleName is nullable; how many persons have one, and how long it is. 11473; 1 to 16 characters, of the 50.
SELECT COUNT(MiddleName) AS with_middle_name, MIN(LEN(MiddleName)), MAX(LEN(MiddleName))
FROM Person.Person
;

-- a MiddleName which is blank, or carries leading or trailing spaces.
-- no rows: every MiddleName is trimmed and not blank; a person without one has NULL.
SELECT BusinessEntityID, '[' + MiddleName + ']' AS MiddleName
FROM Person.Person
WHERE TRIM(MiddleName) = ''
   OR DATALENGTH(MiddleName) <> DATALENGTH(TRIM(MiddleName))
;

-- a MiddleName is mostly an initial; 10894 of 1 character, 551 of 2, each an initial with a period (J.), and 28
-- longer.
SELECT CASE WHEN LEN(MiddleName) > 2 THEN 'longer' ELSE CAST(LEN(MiddleName) AS varchar) END AS length,
       COUNT(*) AS persons
FROM Person.Person
WHERE MiddleName IS NOT NULL
GROUP BY CASE WHEN LEN(MiddleName) > 2 THEN 'longer' ELSE CAST(LEN(MiddleName) AS varchar) END
ORDER BY length ASC
;


-- ------------------------------------------------------------------------------------------------------------ LastName
-- the shortest and the longest LastName, and how many distinct; 2 and 22 characters, of the 50 the column allows,
-- 1206 names.
SELECT MIN(LEN(LastName)), MAX(LEN(LastName)), COUNT(DISTINCT LastName)
FROM Person.Person
;

-- a LastName which is blank, or carries leading or trailing spaces.
-- no rows: every LastName is trimmed and not blank.
SELECT BusinessEntityID, '[' + LastName + ']' AS LastName
FROM Person.Person
WHERE TRIM(LastName) = ''
   OR DATALENGTH(LastName) <> DATALENGTH(TRIM(LastName))
;

-- the three most common last names; Diaz with 211, Hernandez with 188 and Sanchez with 175.
SELECT TOP (3) LastName, COUNT(*) AS persons
FROM Person.Person
GROUP BY LastName
ORDER BY persons DESC, LastName ASC
;

-- IX_Person_LastName_FirstName_MiddleName is not unique; how many full names more than one person has. 142.
SELECT COUNT(*) AS shared_names
FROM (SELECT LastName, FirstName, MiddleName
      FROM Person.Person
      GROUP BY LastName, FirstName, MiddleName
      HAVING COUNT(*) > 1) n
;


-- -------------------------------------------------------------------------------------------------------------- Suffix
-- the suffixes, NULL on 19919 rows; Jr. on 33, II on 6, Sr. on 6, III on 3, IV on 3 and PhD on 2.
SELECT Suffix, COUNT(*) AS persons
FROM Person.Person
GROUP BY Suffix
ORDER BY persons DESC, Suffix ASC
;


-- ------------------------------------------------------------------------------------------------------ EmailPromotion
-- the values CK_Person_EmailPromotion allows, 0 to 2; 0 on 11158, 1 on 5044 and 2 on 3770.
SELECT EmailPromotion, COUNT(*) AS persons
FROM Person.Person
GROUP BY EmailPromotion
ORDER BY EmailPromotion ASC
;


-- ----------------------------------------------------------------------------------------------- AdditionalContactInfo
-- AdditionalContactInfo is a nullable xml; how many persons have one, and how large it is. 10; 1170 to 2973 bytes.
SELECT COUNT(AdditionalContactInfo), MIN(DATALENGTH(AdditionalContactInfo)), MAX(DATALENGTH(AdditionalContactInfo))
FROM Person.Person
;


-- -------------------------------------------------------------------------------------------------------- Demographics
-- Demographics is a nullable xml; how many persons have none, and how large the others are. None; 472 to 1327 bytes.
SELECT COUNT(*) - COUNT(Demographics) AS without, MIN(DATALENGTH(Demographics)), MAX(DATALENGTH(Demographics))
FROM Person.Person
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_Person_rowguid keeps rowguid unique.
-- no rows: no two persons share a rowguid.
SELECT rowguid, COUNT(*) AS persons
FROM Person.Person
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2015-04-15 16:33:33.060 to 2025-06-29 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.Person
;

-- how many rows each year; most in the last years, 8822 in 2024 and 5989 in 2025, 10 in 2015.
SELECT YEAR(ModifiedDate) AS year, COUNT(*) AS persons
FROM Person.Person
GROUP BY YEAR(ModifiedDate)
ORDER BY year ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a person without an EmailAddress, a Password or a PersonPhone.
-- no rows: every person has all three.
SELECT p.BusinessEntityID
FROM Person.Person p
WHERE NOT EXISTS (SELECT 1 FROM Person.EmailAddress e WHERE e.BusinessEntityID = p.BusinessEntityID)
   OR NOT EXISTS (SELECT 1 FROM Person.Password w WHERE w.BusinessEntityID = p.BusinessEntityID)
   OR NOT EXISTS (SELECT 1 FROM Person.PersonPhone h WHERE h.BusinessEntityID = p.BusinessEntityID)
;

-- the persons Sales.Customer refers to, by PersonType; 18484 IN, every individual customer, and 635 SC.
SELECT p.PersonType, COUNT(*) AS customers
FROM Person.Person p
         JOIN Sales.Customer c ON c.PersonID = p.BusinessEntityID
GROUP BY p.PersonType
ORDER BY p.PersonType ASC
;

