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
-- Profiling queries on the view Person.vAdditionalContactInfo, a section per column. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_vAdditionalContactInfo.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and how many persons they cover; 10 rows, 10 persons, 291 to 309.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM Person.vAdditionalContactInfo
;

-- the view reads Person.AdditionalContactInfo; a person with one which the view leaves out, or a row of a person
-- without one.
-- no rows: the view has a row for each of the 10 persons with AdditionalContactInfo, and no other.
SELECT p.BusinessEntityID, v.BusinessEntityID
FROM (SELECT BusinessEntityID FROM Person.Person WHERE AdditionalContactInfo IS NOT NULL) p
         FULL JOIN Person.vAdditionalContactInfo v ON v.BusinessEntityID = p.BusinessEntityID
WHERE p.BusinessEntityID IS NULL
   OR v.BusinessEntityID IS NULL
;

-- the PersonType of the persons; SC (store contact) for all 10.
SELECT p.PersonType, COUNT(*) AS persons
FROM Person.vAdditionalContactInfo v
         JOIN Person.Person p ON p.BusinessEntityID = v.BusinessEntityID
GROUP BY p.PersonType
;


-- ----------------------------------------------------------------------------------------------------------- FirstName
-- a FirstName which is not the person's.
-- no rows: the view repeats Person.FirstName.
SELECT v.BusinessEntityID, v.FirstName, p.FirstName
FROM Person.vAdditionalContactInfo v
         JOIN Person.Person p ON p.BusinessEntityID = v.BusinessEntityID
WHERE v.FirstName <> p.FirstName
;


-- ---------------------------------------------------------------------------------------------------------- MiddleName
-- how many rows have a MiddleName; 5 of the 10.
SELECT COUNT(MiddleName)
FROM Person.vAdditionalContactInfo
;


-- ------------------------------------------------------------------------------------------------------------ LastName
-- a LastName which is not the person's.
-- no rows: the view repeats Person.LastName.
SELECT v.BusinessEntityID, v.LastName, p.LastName
FROM Person.vAdditionalContactInfo v
         JOIN Person.Person p ON p.BusinessEntityID = v.BusinessEntityID
WHERE v.LastName <> p.LastName
;


-- ----------------------------------------------------------------------------------------------------- TelephoneNumber
-- how many rows have a TelephoneNumber, and how long it is; 5, 12 characters each.
SELECT COUNT(TelephoneNumber), MIN(LEN(TelephoneNumber)), MAX(LEN(TelephoneNumber))
FROM Person.vAdditionalContactInfo
;


-- ---------------------------------------------------------------------------------------- TelephoneSpecialInstructions
-- how many rows have TelephoneSpecialInstructions, and how long they are; 3, 25 to 48 characters.
SELECT COUNT(TelephoneSpecialInstructions),
       MIN(LEN(TelephoneSpecialInstructions)),
       MAX(LEN(TelephoneSpecialInstructions))
FROM Person.vAdditionalContactInfo
;


-- -------------------------------------------------------------------------------------------------------------- Street
-- how many rows have a Street, and how long it is; 3, 7 to 14 characters.
SELECT COUNT(Street), MIN(LEN(Street)), MAX(LEN(Street))
FROM Person.vAdditionalContactInfo
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- the cities; NULL on 7 rows, and Edmonds, Redmond and Seattle on 1 each.
SELECT City, COUNT(*) AS persons
FROM Person.vAdditionalContactInfo
GROUP BY City
ORDER BY City ASC
;


-- ------------------------------------------------------------------------------------------------------- StateProvince
-- the state provinces, as the xml spells them, not a StateProvinceID; NULL on 7 rows and WA on 3.
SELECT StateProvince, COUNT(*) AS persons
FROM Person.vAdditionalContactInfo
GROUP BY StateProvince
ORDER BY StateProvince ASC
;


-- ---------------------------------------------------------------------------------------------------------- PostalCode
-- how many rows have a PostalCode, and how long it is; 3, 5 characters each.
SELECT COUNT(PostalCode), MIN(LEN(PostalCode)), MAX(LEN(PostalCode))
FROM Person.vAdditionalContactInfo
;


-- ------------------------------------------------------------------------------------------------------- CountryRegion
-- the countries and regions, as the xml spells them; NULL on 7 rows and USA on 3, not the US CountryRegion uses.
SELECT CountryRegion, COUNT(*) AS persons
FROM Person.vAdditionalContactInfo
GROUP BY CountryRegion
ORDER BY CountryRegion ASC
;


-- -------------------------------------------------------------------------------------- HomeAddressSpecialInstructions
-- how many rows have HomeAddressSpecialInstructions, and how long they are; 3, 34 to 61 characters.
SELECT COUNT(HomeAddressSpecialInstructions),
       MIN(LEN(HomeAddressSpecialInstructions)),
       MAX(LEN(HomeAddressSpecialInstructions))
FROM Person.vAdditionalContactInfo
;


-- -------------------------------------------------------------------------------------------------------- EMailAddress
-- how many rows have an EMailAddress, and how long it is; 5, 14 to 20 characters.
SELECT COUNT(EMailAddress), MIN(LEN(EMailAddress)), MAX(LEN(EMailAddress))
FROM Person.vAdditionalContactInfo
;

-- the domains; sample.com on all 5, none of the person's EmailAddress at adventure-works.com.
SELECT SUBSTRING(EMailAddress, CHARINDEX('@', EMailAddress) + 1, 128) AS domain, COUNT(*) AS persons
FROM Person.vAdditionalContactInfo
WHERE EMailAddress IS NOT NULL
GROUP BY SUBSTRING(EMailAddress, CHARINDEX('@', EMailAddress) + 1, 128)
;


-- -------------------------------------------------------------------------------------------- EMailSpecialInstructions
-- how many rows have EMailSpecialInstructions, and how long they are; 4, 32 to 79 characters.
SELECT COUNT(EMailSpecialInstructions), MIN(LEN(EMailSpecialInstructions)), MAX(LEN(EMailSpecialInstructions))
FROM Person.vAdditionalContactInfo
;


-- ------------------------------------------------------------------------------------------------ EMailTelephoneNumber
-- how many rows have an EMailTelephoneNumber; 1, 425-555-1111 of 291.
SELECT BusinessEntityID, EMailTelephoneNumber
FROM Person.vAdditionalContactInfo
WHERE EMailTelephoneNumber IS NOT NULL
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- a rowguid which is not the person's.
-- no rows: the view repeats Person.rowguid.
SELECT v.BusinessEntityID
FROM Person.vAdditionalContactInfo v
         JOIN Person.Person p ON p.BusinessEntityID = v.BusinessEntityID
WHERE v.rowguid <> p.rowguid
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the rows carry; 2015-04-15 16:33:33.060 to 2015-04-15 16:33:33.123, the persons' own.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.vAdditionalContactInfo
;

