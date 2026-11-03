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
-- Profiling queries on the view Sales.vStoreWithContacts, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_vStoreWithContacts.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows and stores; 753 contacts of all 701 stores.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM Sales.vStoreWithContacts
;

-- how many stores have more than one contact; 52.
SELECT COUNT(*) AS stores
FROM (SELECT BusinessEntityID
      FROM Sales.vStoreWithContacts
      GROUP BY BusinessEntityID
      HAVING COUNT(*) > 1) t
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- a Name which is not that of Sales.Store.
-- no rows: the view copies Name.
SELECT v.BusinessEntityID, v.Name, s.Name
FROM Sales.vStoreWithContacts v
         JOIN Sales.Store s ON s.BusinessEntityID = v.BusinessEntityID
WHERE v.Name <> s.Name
;


-- --------------------------------------------------------------------------------------------------------- ContactType
-- how many rows each ContactType has; Owner 266, Purchasing Manager 245 and Purchasing Agent 242.
SELECT ContactType, COUNT(*) AS contacts
FROM Sales.vStoreWithContacts
GROUP BY ContactType
ORDER BY contacts DESC
;


-- --------------------------------------------------------------------------------------------------------------- Title
-- how many rows each Title has; Mr. 428, Ms. 308, Sr. 6, Sra. 3, and NULL 8.
SELECT Title, COUNT(*) AS contacts
FROM Sales.vStoreWithContacts
GROUP BY Title
ORDER BY contacts DESC
;


-- ----------------------------------------------------------------------------------------------------------- FirstName
-- the shortest and the longest FirstName; 2 and 24 characters.
SELECT MIN(LEN(FirstName)), MAX(LEN(FirstName))
FROM Sales.vStoreWithContacts
;


-- ---------------------------------------------------------------------------------------------------------- MiddleName
-- how many rows have a MiddleName, and how long; 442; 1 to 9 characters.
SELECT COUNT(MiddleName), MIN(LEN(MiddleName)), MAX(LEN(MiddleName))
FROM Sales.vStoreWithContacts
;


-- ------------------------------------------------------------------------------------------------------------ LastName
-- the shortest and the longest LastName; 2 and 22 characters.
SELECT MIN(LEN(LastName)), MAX(LEN(LastName))
FROM Sales.vStoreWithContacts
;


-- -------------------------------------------------------------------------------------------------------------- Suffix
-- how many rows each Suffix has; NULL 714, then Jr. 23, II 5, Sr. 5, IV 3, PhD 2 and III 1.
SELECT Suffix, COUNT(*) AS contacts
FROM Sales.vStoreWithContacts
GROUP BY Suffix
ORDER BY contacts DESC, Suffix ASC
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- how many rows have no PhoneNumber, and how long the others are; none; 12 to 19 characters.
SELECT COUNT(*) - COUNT(PhoneNumber) AS without, MIN(LEN(PhoneNumber)), MAX(LEN(PhoneNumber))
FROM Sales.vStoreWithContacts
;


-- ----------------------------------------------------------------------------------------------------- PhoneNumberType
-- how many rows each PhoneNumberType has; Cell 395 and Work 358.
SELECT PhoneNumberType, COUNT(*) AS contacts
FROM Sales.vStoreWithContacts
GROUP BY PhoneNumberType
ORDER BY PhoneNumberType ASC
;


-- -------------------------------------------------------------------------------------------------------- EmailAddress
-- how many rows have no EmailAddress, and how long the others are; none; 22 to 43 characters.
SELECT COUNT(*) - COUNT(EmailAddress) AS without, MIN(LEN(EmailAddress)), MAX(LEN(EmailAddress))
FROM Sales.vStoreWithContacts
;


-- ------------------------------------------------------------------------------------------------------ EmailPromotion
-- how many rows each EmailPromotion has; 0 for 416, 1 for 192 and 2 for 145.
SELECT EmailPromotion, COUNT(*) AS contacts
FROM Sales.vStoreWithContacts
GROUP BY EmailPromotion
ORDER BY EmailPromotion ASC
;

