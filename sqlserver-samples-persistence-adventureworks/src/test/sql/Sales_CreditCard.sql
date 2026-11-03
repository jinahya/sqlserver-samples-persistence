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
-- Profiling queries on Sales.CreditCard, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_CreditCard.sql
--


-- -------------------------------------------------------------------------------------------------------- CreditCardID
-- how many rows, and the smallest and the largest CreditCardID; 19118 cards, 1 to 19237.
SELECT COUNT(*), MIN(CreditCardID), MAX(CreditCardID)
FROM Sales.CreditCard
;

-- how many CreditCardIDs are missing between the smallest and the largest; 119.
SELECT MAX(CreditCardID) - MIN(CreditCardID) + 1 - COUNT(*) AS missing
FROM Sales.CreditCard
;

-- the first five gaps in CreditCardID; from 16405, 16428, 16477, 16480 and 16482, none below 16405.
SELECT TOP (5) a.CreditCardID + 1 AS missing_from
FROM Sales.CreditCard a
WHERE a.CreditCardID < (SELECT MAX(CreditCardID) FROM Sales.CreditCard)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.CreditCard b
                  WHERE b.CreditCardID = a.CreditCardID + 1)
ORDER BY missing_from ASC
;


-- ------------------------------------------------------------------------------------------------------------ CardType
-- the shortest and the longest CardType; 5 and 13 characters, of the 50 the column allows.
SELECT MIN(LEN(CardType)), MAX(LEN(CardType))
FROM Sales.CreditCard
;

-- how many cards each CardType has; 4 types, 4665 (Vista) to 4839 (SuperiorCard).
SELECT CardType, COUNT(*) AS cards
FROM Sales.CreditCard
GROUP BY CardType
ORDER BY CardType ASC
;


-- ---------------------------------------------------------------------------------------------------------- CardNumber
-- a CardNumber which carries anything but digits.
-- no rows: every CardNumber is all digits.
SELECT CreditCardID, CardNumber
FROM Sales.CreditCard
WHERE CardNumber LIKE '%[^0-9]%'
;

-- the length and the leading digits of CardNumber per CardType; 14 digits each, and a prefix per type: ColonialVoice
-- 77, Distinguish 55, SuperiorCard 33 and Vista 11.
SELECT CardType, MIN(LEN(CardNumber)), MAX(LEN(CardNumber)), MIN(LEFT(CardNumber, 2)), MAX(LEFT(CardNumber, 2))
FROM Sales.CreditCard
GROUP BY CardType
ORDER BY CardType ASC
;

-- AK_CreditCard_CardNumber keeps CardNumber unique.
-- no rows: no two cards share a CardNumber.
SELECT CardNumber, COUNT(*) AS cards
FROM Sales.CreditCard
GROUP BY CardNumber
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ ExpMonth
-- how many cards each ExpMonth has; 1 to 12, 1545 to 1623 cards each.
SELECT ExpMonth, COUNT(*) AS cards
FROM Sales.CreditCard
GROUP BY ExpMonth
ORDER BY ExpMonth ASC
;


-- ------------------------------------------------------------------------------------------------------------- ExpYear
-- how many cards each ExpYear has; 2005 to 2008, 4736 to 4832 cards each.
SELECT ExpYear, COUNT(*) AS cards
FROM Sales.CreditCard
GROUP BY ExpYear
ORDER BY ExpYear ASC
;

-- the orders run from 2022 while ExpYear stops at 2008; how many orders were paid with a card which had expired by
-- the order. All 30334 orders which have a card.
SELECT COUNT(*) AS orders
FROM Sales.SalesOrderHeader h
         JOIN Sales.CreditCard c ON c.CreditCardID = h.CreditCardID
WHERE EOMONTH(DATEFROMPARTS(c.ExpYear, c.ExpMonth, 1)) < h.OrderDate
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry, and how many moments; 2017-06-22 to 2025-06-29, in 1279.
SELECT MIN(ModifiedDate), MAX(ModifiedDate), COUNT(DISTINCT ModifiedDate)
FROM Sales.CreditCard
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a card no person holds.
-- no rows: every card has its PersonCreditCard.
SELECT c.CreditCardID, c.CardType
FROM Sales.CreditCard c
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.PersonCreditCard p
                  WHERE p.CreditCardID = c.CreditCardID)
;

-- how many cards no order was paid with; 734.
SELECT COUNT(*) AS cards
FROM Sales.CreditCard c
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderHeader h
                  WHERE h.CreditCardID = c.CreditCardID)
;

-- how many cards carry another ModifiedDate than their PersonCreditCard; 734, as many as no order was paid with.
SELECT COUNT(*) AS cards
FROM Sales.CreditCard c
         JOIN Sales.PersonCreditCard p ON p.CreditCardID = c.CreditCardID
WHERE p.ModifiedDate <> c.ModifiedDate
;

-- a card which carries another ModifiedDate than its PersonCreditCard and which an order was paid with.
-- no rows: the 734 are the very cards no order was paid with.
SELECT c.CreditCardID, c.ModifiedDate, p.ModifiedDate
FROM Sales.CreditCard c
         JOIN Sales.PersonCreditCard p ON p.CreditCardID = c.CreditCardID
WHERE p.ModifiedDate <> c.ModifiedDate
  AND EXISTS (SELECT 1
              FROM Sales.SalesOrderHeader h
              WHERE h.CreditCardID = c.CreditCardID)
;

