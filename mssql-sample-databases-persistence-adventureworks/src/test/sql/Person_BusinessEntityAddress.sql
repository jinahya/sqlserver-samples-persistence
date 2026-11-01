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
-- Profiling queries on Person.BusinessEntityAddress, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_BusinessEntityAddress.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and how many business entities they cover; 19614 rows, 19579 business entities.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM Person.BusinessEntityAddress
;

-- how many addresses a business entity has; 19544 have 1 and 35 have 2.
SELECT b.addresses, COUNT(*) AS business_entities
FROM (SELECT BusinessEntityID, COUNT(*) AS addresses
      FROM Person.BusinessEntityAddress
      GROUP BY BusinessEntityID) b
GROUP BY b.addresses
ORDER BY b.addresses ASC
;


-- ----------------------------------------------------------------------------------------------------------- AddressID
-- the schema keeps only the triple unique; an AddressID more than one row has.
-- no rows: no two business entities, nor two address types, share an address.
SELECT AddressID, COUNT(*) AS links
FROM Person.BusinessEntityAddress
GROUP BY AddressID
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------- AddressTypeID
-- the address types the rows use; Home (2) on 18774, Main Office (3) on 805 and Shipping (5) on 35.
SELECT AddressTypeID, COUNT(*) AS links
FROM Person.BusinessEntityAddress
GROUP BY AddressTypeID
ORDER BY AddressTypeID ASC
;

-- the address types by the kind of business entity. A person has Home (2) addresses, 18774, and Shipping (5), 24; a
-- store Main Office (3), 701, and Shipping (5), 11; a vendor Main Office (3) only, 104.
SELECT CASE
           WHEN p.BusinessEntityID IS NOT NULL THEN 'Person'
           WHEN s.BusinessEntityID IS NOT NULL THEN 'Store'
           WHEN v.BusinessEntityID IS NOT NULL THEN 'Vendor'
           END         AS kind,
       a.AddressTypeID,
       COUNT(*)        AS links
FROM Person.BusinessEntityAddress a
         LEFT JOIN Person.Person p ON p.BusinessEntityID = a.BusinessEntityID
         LEFT JOIN Sales.Store s ON s.BusinessEntityID = a.BusinessEntityID
         LEFT JOIN Purchasing.Vendor v ON v.BusinessEntityID = a.BusinessEntityID
GROUP BY CASE
             WHEN p.BusinessEntityID IS NOT NULL THEN 'Person'
             WHEN s.BusinessEntityID IS NOT NULL THEN 'Store'
             WHEN v.BusinessEntityID IS NOT NULL THEN 'Vendor'
             END,
         a.AddressTypeID
ORDER BY kind ASC, a.AddressTypeID ASC
;

-- a business entity with two addresses of the same type.
-- no rows: a second address is always of another type.
SELECT BusinessEntityID, AddressTypeID, COUNT(*) AS addresses
FROM Person.BusinessEntityAddress
GROUP BY BusinessEntityID, AddressTypeID
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_BusinessEntityAddress_rowguid keeps rowguid unique.
-- no rows: no two rows share a rowguid.
SELECT rowguid, COUNT(*) AS links
FROM Person.BusinessEntityAddress
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2022-04-24 00:00:00.000 to 2025-09-11 11:15:06.967.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.BusinessEntityAddress
;

-- how many rows each year; 1474 in 2022, 3048 in 2023, 8810 in 2024 and 6282 in 2025.
SELECT YEAR(ModifiedDate) AS year, COUNT(*) AS links
FROM Person.BusinessEntityAddress
GROUP BY YEAR(ModifiedDate)
ORDER BY year ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an Address no row links to.
-- no rows: every address belongs to a business entity.
SELECT a.AddressID
FROM Person.Address a
WHERE NOT EXISTS (SELECT 1
                  FROM Person.BusinessEntityAddress b
                  WHERE b.AddressID = a.AddressID)
;

-- a business entity with no address, by kind; 1198, all of them persons.
SELECT CASE
           WHEN p.BusinessEntityID IS NOT NULL THEN 'Person'
           WHEN s.BusinessEntityID IS NOT NULL THEN 'Store'
           WHEN v.BusinessEntityID IS NOT NULL THEN 'Vendor'
           END  AS kind,
       COUNT(*) AS without_address
FROM Person.BusinessEntity e
         LEFT JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
         LEFT JOIN Sales.Store s ON s.BusinessEntityID = e.BusinessEntityID
         LEFT JOIN Purchasing.Vendor v ON v.BusinessEntityID = e.BusinessEntityID
WHERE NOT EXISTS (SELECT 1
                  FROM Person.BusinessEntityAddress a
                  WHERE a.BusinessEntityID = e.BusinessEntityID)
GROUP BY CASE
             WHEN p.BusinessEntityID IS NOT NULL THEN 'Person'
             WHEN s.BusinessEntityID IS NOT NULL THEN 'Store'
             WHEN v.BusinessEntityID IS NOT NULL THEN 'Vendor'
             END
ORDER BY kind ASC
;

