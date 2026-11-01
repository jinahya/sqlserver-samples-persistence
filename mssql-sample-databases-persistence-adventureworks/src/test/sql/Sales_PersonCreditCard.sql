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
-- Profiling queries on Sales.PersonCreditCard, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_PersonCreditCard.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, persons and cards; 19118 of each, one card per person and one person per card.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID), COUNT(DISTINCT CreditCardID)
FROM Sales.PersonCreditCard
;

-- a BusinessEntityID which refers to no person; the foreign key forbids it.
-- no rows: every BusinessEntityID refers to a Person.Person.
SELECT t.BusinessEntityID, t.CreditCardID
FROM Sales.PersonCreditCard t
WHERE NOT EXISTS (SELECT 1
                  FROM Person.Person p
                  WHERE p.BusinessEntityID = t.BusinessEntityID)
;

-- the types of the persons who hold a card; 18484 individual customers (IN) and 634 store contacts (SC).
SELECT p.PersonType, COUNT(*) AS persons
FROM Sales.PersonCreditCard t
         JOIN Person.Person p ON p.BusinessEntityID = t.BusinessEntityID
GROUP BY p.PersonType
ORDER BY p.PersonType ASC
;


-- -------------------------------------------------------------------------------------------------------- CreditCardID
-- the schema has no unique key on CreditCardID alone; a card more than one person holds.
-- no rows: every card has a single holder.
SELECT CreditCardID, COUNT(*) AS persons
FROM Sales.PersonCreditCard
GROUP BY CreditCardID
HAVING COUNT(*) > 1
;

-- a CreditCardID which refers to no card; the foreign key forbids it.
-- no rows: every CreditCardID refers to a Sales.CreditCard.
SELECT t.BusinessEntityID, t.CreditCardID
FROM Sales.PersonCreditCard t
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.CreditCard p
                  WHERE p.CreditCardID = t.CreditCardID)
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry, and how many moments; 2022-05-30 to 2025-06-29, in 1124.
SELECT MIN(ModifiedDate), MAX(ModifiedDate), COUNT(DISTINCT ModifiedDate)
FROM Sales.PersonCreditCard
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an order paid with a card its customer's person does not hold.
-- no rows: every order with a card was paid by the card's holder.
SELECT h.SalesOrderID, h.CustomerID, h.CreditCardID
FROM Sales.SalesOrderHeader h
WHERE h.CreditCardID IS NOT NULL
  AND NOT EXISTS (SELECT 1
                  FROM Sales.Customer c
                           JOIN Sales.PersonCreditCard t ON t.BusinessEntityID = c.PersonID
                  WHERE c.CustomerID = h.CustomerID
                    AND t.CreditCardID = h.CreditCardID)
;

