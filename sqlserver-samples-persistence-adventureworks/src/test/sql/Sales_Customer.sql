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
-- Profiling queries on Sales.Customer, a section per column. Each says what it looks for and what the result should be;
-- 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_Customer.sql
--


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many rows, and the smallest and the largest CustomerID; 19820 customers, 1 to 30118.
SELECT COUNT(*), MIN(CustomerID), MAX(CustomerID)
FROM Sales.Customer
;

-- the gaps in CustomerID; a single one, from 702 to 10999.
SELECT a.CustomerID + 1                                                                       AS missing_from,
       (SELECT MIN(b.CustomerID) FROM Sales.Customer b WHERE b.CustomerID > a.CustomerID) -
       1                                                                                      AS missing_to
FROM Sales.Customer a
WHERE a.CustomerID < (SELECT MAX(CustomerID) FROM Sales.Customer)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.Customer b
                  WHERE b.CustomerID = a.CustomerID + 1)
;

-- the two ranges the gap leaves, and whom they hold; 1 to 701 the 701 stores, 11000 to 30118 the 19119 persons.
SELECT CASE WHEN CustomerID < 11000 THEN 'below' ELSE 'above' END AS range,
       MIN(CustomerID),
       MAX(CustomerID),
       COUNT(*)                                                   AS customers,
       COUNT(PersonID)                                            AS persons,
       COUNT(StoreID)                                             AS stores
FROM Sales.Customer
GROUP BY CASE WHEN CustomerID < 11000 THEN 'below' ELSE 'above' END
ORDER BY MIN(CustomerID) ASC
;


-- ------------------------------------------------------------------------------------------------------------ PersonID
-- which of PersonID and StoreID a customer has; 18484 a person alone, 701 a store alone and 635 both. None has
-- neither.
SELECT CASE WHEN PersonID IS NULL THEN 'no' ELSE 'yes' END AS person,
       CASE WHEN StoreID IS NULL THEN 'no' ELSE 'yes' END  AS store,
       COUNT(*)                                            AS customers
FROM Sales.Customer
GROUP BY CASE WHEN PersonID IS NULL THEN 'no' ELSE 'yes' END,
         CASE WHEN StoreID IS NULL THEN 'no' ELSE 'yes' END
ORDER BY person ASC, store ASC
;

-- the schema has no unique key on PersonID; a person who is more than one customer.
-- no rows: a person is a customer at most once.
SELECT PersonID, COUNT(*) AS customers
FROM Sales.Customer
WHERE PersonID IS NOT NULL
GROUP BY PersonID
HAVING COUNT(*) > 1
;

-- the types of the persons, with or without a store; 18484 individual customers (IN) without, 635 store contacts
-- (SC) with.
SELECT p.PersonType,
       CASE WHEN c.StoreID IS NULL THEN 'no' ELSE 'yes' END AS store,
       COUNT(*)                                             AS customers
FROM Sales.Customer c
         JOIN Person.Person p ON p.BusinessEntityID = c.PersonID
GROUP BY p.PersonType, CASE WHEN c.StoreID IS NULL THEN 'no' ELSE 'yes' END
ORDER BY p.PersonType ASC
;


-- ------------------------------------------------------------------------------------------------------------- StoreID
-- a store which is the customer, with no person, more than once.
-- no rows: each store is a customer of its own once.
SELECT StoreID, COUNT(*) AS customers
FROM Sales.Customer
WHERE StoreID IS NOT NULL
  AND PersonID IS NULL
GROUP BY StoreID
HAVING COUNT(*) > 1
;

-- a store which is not a customer of its own.
-- no rows: every Sales.Store has its customer with no person.
SELECT s.BusinessEntityID, s.Name
FROM Sales.Store s
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Customer c
                  WHERE c.StoreID = s.BusinessEntityID
                    AND c.PersonID IS NULL)
;

-- a customer with both a person and a store whose person is no contact of the store.
-- no rows: each of the 635 is a Person.BusinessEntityContact of its store.
SELECT c.CustomerID, c.PersonID, c.StoreID
FROM Sales.Customer c
WHERE c.PersonID IS NOT NULL
  AND c.StoreID IS NOT NULL
  AND NOT EXISTS (SELECT 1
                  FROM Person.BusinessEntityContact b
                  WHERE b.BusinessEntityID = c.StoreID
                    AND b.PersonID = c.PersonID)
;


-- --------------------------------------------------------------------------------------------------------- TerritoryID
-- how many customers each TerritoryID has; all 10 territories, 113 (2) to 4696 (4), and no NULL.
SELECT TerritoryID, COUNT(*) AS customers
FROM Sales.Customer
GROUP BY TerritoryID
ORDER BY TerritoryID ASC
;


-- ------------------------------------------------------------------------------------------------------- AccountNumber
-- AccountNumber is computed, 'AW' and CustomerID padded with zeros to 8 digits; one which is not.
-- no rows: every AccountNumber follows its CustomerID.
SELECT CustomerID, AccountNumber
FROM Sales.Customer
WHERE AccountNumber <> 'AW' + RIGHT ('0000000' + CAST (CustomerID AS varchar (10)), 8)
;

-- the smallest and the largest AccountNumber, and their lengths; AW00000001 to AW00030118, 10 characters each.
SELECT MIN(AccountNumber),
       MAX(AccountNumber),
       MIN(LEN(AccountNumber)),
       MAX(LEN(AccountNumber))
FROM Sales.Customer
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_Customer_rowguid keeps rowguid unique.
-- no rows: no two customers share a rowguid.
SELECT rowguid, COUNT(*) AS customers
FROM Sales.Customer
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2025-09-11 11:15:07.263, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Sales.Customer
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the customers who never ordered; 701, every one of them a store with no person.
SELECT CASE WHEN c.PersonID IS NULL THEN 'no' ELSE 'yes' END AS person,
       CASE WHEN c.StoreID IS NULL THEN 'no' ELSE 'yes' END  AS store,
       COUNT(*)                                              AS customers
FROM Sales.Customer c
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderHeader h
                  WHERE h.CustomerID = c.CustomerID)
GROUP BY CASE WHEN c.PersonID IS NULL THEN 'no' ELSE 'yes' END,
         CASE WHEN c.StoreID IS NULL THEN 'no' ELSE 'yes' END
;

-- an order whose TerritoryID is not that of its customer.
-- no rows: every order is in its customer's territory.
SELECT h.SalesOrderID, h.TerritoryID, c.TerritoryID
FROM Sales.SalesOrderHeader h
         JOIN Sales.Customer c ON c.CustomerID = h.CustomerID
WHERE h.TerritoryID <> c.TerritoryID
;

