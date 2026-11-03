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
-- Profiling queries on the view Sales.vIndividualCustomer, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_vIndividualCustomer.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows and persons; 18508 rows of 18484 persons, 1699 to 20777, the view having a row per address.
SELECT COUNT(*),
       COUNT(DISTINCT BusinessEntityID),
       MIN(BusinessEntityID),
       MAX(BusinessEntityID)
FROM Sales.vIndividualCustomer
;

-- a person who appears more than once, and why; 24 persons with a Home and a Shipping address, twice each.
SELECT COUNT(*) AS persons, MIN(addresses), MAX(addresses)
FROM (SELECT BusinessEntityID, COUNT(*) AS addresses
      FROM Sales.vIndividualCustomer
      GROUP BY BusinessEntityID
      HAVING COUNT(*) > 1) t
;

-- a customer with a person and no store who is not in the view, or a person of the view who is no such customer.
-- no rows: the view holds exactly the 18484 individual customers.
SELECT c.PersonID, 'missing' AS problem
FROM Sales.Customer c
WHERE c.StoreID IS NULL
  AND NOT EXISTS (SELECT 1 FROM Sales.vIndividualCustomer v WHERE v.BusinessEntityID = c.PersonID)
UNION ALL
SELECT v.BusinessEntityID, 'extra'
FROM Sales.vIndividualCustomer v
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Customer c
                  WHERE c.PersonID = v.BusinessEntityID AND c.StoreID IS NULL)
;


-- --------------------------------------------------------------------------------------------------------------- Title
-- how many rows each Title has; NULL for 18407, then Mr. 52, Ms. 41, Sr. 5, Mrs. 2, and 1 Ms with no period.
SELECT Title, COUNT(*) AS customers
FROM Sales.vIndividualCustomer
GROUP BY Title
ORDER BY customers DESC, Title ASC
;


-- ----------------------------------------------------------------------------------------------------------- FirstName
-- the shortest and the longest FirstName; 2 and 11 characters.
SELECT MIN(LEN(FirstName)), MAX(LEN(FirstName))
FROM Sales.vIndividualCustomer
;


-- ---------------------------------------------------------------------------------------------------------- MiddleName
-- how many rows have no MiddleName, and how long the others are; 7842; 1 to 10 characters.
SELECT COUNT(*) - COUNT(MiddleName) AS without, MIN(LEN(MiddleName)), MAX(LEN(MiddleName))
FROM Sales.vIndividualCustomer
;


-- ------------------------------------------------------------------------------------------------------------ LastName
-- the shortest and the longest LastName; 2 and 16 characters.
SELECT MIN(LEN(LastName)), MAX(LEN(LastName))
FROM Sales.vIndividualCustomer
;

-- a FirstName, MiddleName or LastName which carries leading or trailing spaces.
-- no rows: every name is trimmed.
SELECT BusinessEntityID, FirstName, MiddleName, LastName
FROM Sales.vIndividualCustomer
WHERE DATALENGTH(FirstName) <> DATALENGTH(TRIM(FirstName))
   OR DATALENGTH(MiddleName) <> DATALENGTH(TRIM(MiddleName))
   OR DATALENGTH(LastName) <> DATALENGTH(TRIM(LastName))
;


-- -------------------------------------------------------------------------------------------------------------- Suffix
-- how many rows each Suffix has; NULL for 18505, Jr. for 3.
SELECT Suffix, COUNT(*) AS customers
FROM Sales.vIndividualCustomer
GROUP BY Suffix
ORDER BY customers DESC
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- how many rows have no PhoneNumber, and how long the others are; none; 12 to 19 characters.
SELECT COUNT(*) - COUNT(PhoneNumber) AS without,
       MIN(LEN(PhoneNumber)),
       MAX(LEN(PhoneNumber))
FROM Sales.vIndividualCustomer
;


-- ----------------------------------------------------------------------------------------------------- PhoneNumberType
-- how many rows each PhoneNumberType has; Cell 9354 and Home 9154, and no NULL.
SELECT PhoneNumberType, COUNT(*) AS customers
FROM Sales.vIndividualCustomer
GROUP BY PhoneNumberType
ORDER BY PhoneNumberType ASC
;


-- -------------------------------------------------------------------------------------------------------- EmailAddress
-- how many rows have no EmailAddress, and how long the others are; none; 22 to 33 characters.
SELECT COUNT(*) - COUNT(EmailAddress) AS without,
       MIN(LEN(EmailAddress)),
       MAX(LEN(EmailAddress))
FROM Sales.vIndividualCustomer
;

-- an EmailAddress which does not look like one.
-- no rows: every EmailAddress has a local part, an @ and a dotted domain.
SELECT BusinessEntityID, EmailAddress
FROM Sales.vIndividualCustomer
WHERE EmailAddress NOT LIKE '%_@_%._%'
;


-- ------------------------------------------------------------------------------------------------------ EmailPromotion
-- how many rows each EmailPromotion has; 0 for 10341, 1 for 4673 and 2 for 3494.
SELECT EmailPromotion, COUNT(*) AS customers
FROM Sales.vIndividualCustomer
GROUP BY EmailPromotion
ORDER BY EmailPromotion ASC
;


-- --------------------------------------------------------------------------------------------------------- AddressType
-- how many rows each AddressType has; Home 18484, one per person, and Shipping 24.
SELECT AddressType, COUNT(*) AS customers
FROM Sales.vIndividualCustomer
GROUP BY AddressType
ORDER BY AddressType ASC
;


-- -------------------------------------------------------------------------------------------------------- AddressLine1
-- the shortest and the longest AddressLine1; 8 and 33 characters.
SELECT MIN(LEN(AddressLine1)), MAX(LEN(AddressLine1))
FROM Sales.vIndividualCustomer
;


-- -------------------------------------------------------------------------------------------------------- AddressLine2
-- how many rows have an AddressLine2; 314.
SELECT COUNT(AddressLine2) AS customers
FROM Sales.vIndividualCustomer
;


-- ---------------------------------------------------------------------------------------------------------------- City
-- how many cities, and the shortest and the longest; 274, 3 and 21 characters.
SELECT COUNT(DISTINCT City), MIN(LEN(City)), MAX(LEN(City))
FROM Sales.vIndividualCustomer
;


-- --------------------------------------------------------------------------------------------------- StateProvinceName
-- how many states and provinces; 54.
SELECT COUNT(DISTINCT StateProvinceName)
FROM Sales.vIndividualCustomer
;


-- ---------------------------------------------------------------------------------------------------------- PostalCode
-- how many postal codes, and the shortest and the longest; 328, 2 and 10 characters.
SELECT COUNT(DISTINCT PostalCode), MIN(LEN(PostalCode)), MAX(LEN(PostalCode))
FROM Sales.vIndividualCustomer
;


-- --------------------------------------------------------------------------------------------------- CountryRegionName
-- how many rows each CountryRegionName has; 6 countries, United States the most with 7843.
SELECT CountryRegionName, COUNT(*) AS customers
FROM Sales.vIndividualCustomer
GROUP BY CountryRegionName
ORDER BY CountryRegionName ASC
;


-- -------------------------------------------------------------------------------------------------------- Demographics
-- how many rows have no Demographics, and its size in bytes; none; 1271 to 1327.
SELECT COUNT(*) - COUNT(Demographics) AS without,
       MIN(DATALENGTH(Demographics)),
       MAX(DATALENGTH(Demographics))
FROM Sales.vIndividualCustomer
;

