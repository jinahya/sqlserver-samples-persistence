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
-- Profiling queries on Sales.Store, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_Store.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and the smallest and the largest BusinessEntityID; 701 stores, 292 to 2051.
SELECT COUNT(*), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM Sales.Store
;

-- a store which is also a person or a vendor.
-- no rows: the business entities of the stores are theirs alone.
SELECT s.BusinessEntityID
FROM Sales.Store s
WHERE EXISTS (SELECT 1 FROM Person.Person p WHERE p.BusinessEntityID = s.BusinessEntityID)
   OR EXISTS (SELECT 1 FROM Purchasing.Vendor v WHERE v.BusinessEntityID = s.BusinessEntityID)
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 9 and 41 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Sales.Store
;

-- a Name which is blank, or carries leading or trailing spaces; 4 with a trailing space, 318 (Great Bikes), 826
-- (Bike Goods), 860 (Sleek Bikes) and 1950 (Helpful Sales and Repair Service).
SELECT BusinessEntityID, '[' + Name + ']' AS Name
FROM Sales.Store
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
ORDER BY BusinessEntityID ASC
;

-- the schema has no unique key on Name; one more than one store has. Friendly Bike Shop and Sports Products Store,
-- 2 each.
SELECT Name, COUNT(*) AS stores
FROM Sales.Store
GROUP BY Name
HAVING COUNT(*) > 1
ORDER BY Name ASC
;


-- ------------------------------------------------------------------------------------------------------- SalesPersonID
-- SalesPersonID is nullable; how many stores have none, and how many sales persons look after the others. None; 13.
SELECT COUNT(*) - COUNT(SalesPersonID) AS without, COUNT(DISTINCT SalesPersonID) AS sales_persons
FROM Sales.Store
;

-- a store in another territory than its sales person's; 84 stores of 4 sales persons, 277 with 38 the most.
SELECT p.BusinessEntityID, p.TerritoryID, COUNT(*) AS stores
FROM Sales.Store s
         JOIN Sales.SalesPerson p ON p.BusinessEntityID = s.SalesPersonID
         JOIN Sales.Customer c ON c.StoreID = s.BusinessEntityID
    AND c.PersonID IS NULL
WHERE c.TerritoryID <> p.TerritoryID
GROUP BY p.BusinessEntityID, p.TerritoryID
ORDER BY p.BusinessEntityID ASC
;


-- -------------------------------------------------------------------------------------------------------- Demographics
-- Demographics is a nullable xml; how many stores have none, and its size in bytes. None; 968 to 1000.
SELECT COUNT(*) - COUNT(Demographics) AS without,
       MIN(DATALENGTH(Demographics)),
       MAX(DATALENGTH(Demographics))
FROM Sales.Store
;

-- a Demographics with no StoreSurvey root.
-- no rows: every one is a StoreSurvey, which Sales.vStoreWithDemographics reads.
SELECT BusinessEntityID
FROM Sales.Store
WHERE Demographics.exist('/*:StoreSurvey') = 0
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_Store_rowguid keeps rowguid unique.
-- no rows: no two stores share a rowguid.
SELECT rowguid, COUNT(*) AS stores
FROM Sales.Store
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2025-09-11 11:15:07.497, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Sales.Store
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a store with no contact.
-- no rows: every store has at least one Person.BusinessEntityContact.
SELECT s.BusinessEntityID, s.Name
FROM Sales.Store s
WHERE NOT EXISTS (SELECT 1
                  FROM Person.BusinessEntityContact b
                  WHERE b.BusinessEntityID = s.BusinessEntityID)
;

-- a store with no address.
-- no rows: every store has at least one Person.BusinessEntityAddress.
SELECT s.BusinessEntityID, s.Name
FROM Sales.Store s
WHERE NOT EXISTS (SELECT 1
                  FROM Person.BusinessEntityAddress b
                  WHERE b.BusinessEntityID = s.BusinessEntityID)
;

