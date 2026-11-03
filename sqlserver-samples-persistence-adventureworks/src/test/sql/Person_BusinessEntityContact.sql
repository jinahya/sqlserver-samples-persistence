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
-- Profiling queries on Person.BusinessEntityContact, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_BusinessEntityContact.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and how many business entities they cover; 909 contacts, of 805 business entities.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM Person.BusinessEntityContact
;

-- the kind of business entity the contacts are of; 753 contacts of 701 stores and 156 of 104 vendors, no person.
SELECT CASE
           WHEN s.BusinessEntityID IS NOT NULL THEN 'Store'
           WHEN v.BusinessEntityID IS NOT NULL THEN 'Vendor'
           ELSE 'other'
           END                           AS kind,
       COUNT(*)                          AS contacts,
       COUNT(DISTINCT c.BusinessEntityID) AS business_entities
FROM Person.BusinessEntityContact c
         LEFT JOIN Sales.Store s ON s.BusinessEntityID = c.BusinessEntityID
         LEFT JOIN Purchasing.Vendor v ON v.BusinessEntityID = c.BusinessEntityID
GROUP BY CASE
             WHEN s.BusinessEntityID IS NOT NULL THEN 'Store'
             WHEN v.BusinessEntityID IS NOT NULL THEN 'Vendor'
             ELSE 'other'
             END
ORDER BY kind ASC
;

-- how many contacts a business entity has; 723 have 1, 60 have 2 and 22 have 3.
SELECT b.contacts, COUNT(*) AS business_entities
FROM (SELECT BusinessEntityID, COUNT(*) AS contacts
      FROM Person.BusinessEntityContact
      GROUP BY BusinessEntityID) b
GROUP BY b.contacts
ORDER BY b.contacts ASC
;


-- ------------------------------------------------------------------------------------------------------------ PersonID
-- a person who is the contact of more than one business entity, or more than once.
-- no rows: each of the 909 persons is a contact once.
SELECT PersonID, COUNT(*) AS contacts
FROM Person.BusinessEntityContact
GROUP BY PersonID
HAVING COUNT(*) > 1
;

-- the PersonType of the contacts; SC (store contact) on 753 and VC (vendor contact) on 156.
SELECT p.PersonType, COUNT(*) AS contacts
FROM Person.BusinessEntityContact c
         JOIN Person.Person p ON p.BusinessEntityID = c.PersonID
GROUP BY p.PersonType
ORDER BY p.PersonType ASC
;

-- a contact whose PersonType does not match the kind of its business entity: SC for a store, VC for a vendor.
-- no rows: every store contact is SC and every vendor contact VC.
SELECT c.BusinessEntityID, c.PersonID, p.PersonType
FROM Person.BusinessEntityContact c
         JOIN Person.Person p ON p.BusinessEntityID = c.PersonID
WHERE (p.PersonType = 'SC' AND NOT EXISTS (SELECT 1 FROM Sales.Store s WHERE s.BusinessEntityID = c.BusinessEntityID))
   OR (p.PersonType = 'VC' AND NOT EXISTS (SELECT 1
                                           FROM Purchasing.Vendor v
                                           WHERE v.BusinessEntityID = c.BusinessEntityID))
   OR p.PersonType NOT IN ('SC', 'VC')
;


-- ------------------------------------------------------------------------------------------------------- ContactTypeID
-- the contact types the rows use; 7 of the 20, Owner (11) the most with 266.
SELECT c.ContactTypeID, t.Name, COUNT(*) AS contacts
FROM Person.BusinessEntityContact c
         JOIN Person.ContactType t ON t.ContactTypeID = c.ContactTypeID
GROUP BY c.ContactTypeID, t.Name
ORDER BY c.ContactTypeID ASC
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_BusinessEntityContact_rowguid keeps rowguid unique.
-- no rows: no two contacts share a rowguid.
SELECT rowguid, COUNT(*) AS contacts
FROM Person.BusinessEntityContact
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2017-12-13 13:21:02.243 to 2023-02-17 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.BusinessEntityContact
;

-- how many rows each year; 753 in 2017, 63 in 2022 and 93 in 2023.
SELECT YEAR(ModifiedDate) AS year, COUNT(*) AS contacts
FROM Person.BusinessEntityContact
GROUP BY YEAR(ModifiedDate)
ORDER BY year ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a store or a vendor with no contact.
-- no rows: every store and every vendor has at least one.
SELECT e.BusinessEntityID
FROM (SELECT BusinessEntityID FROM Sales.Store
      UNION ALL
      SELECT BusinessEntityID FROM Purchasing.Vendor) e
WHERE NOT EXISTS (SELECT 1
                  FROM Person.BusinessEntityContact c
                  WHERE c.BusinessEntityID = e.BusinessEntityID)
;

