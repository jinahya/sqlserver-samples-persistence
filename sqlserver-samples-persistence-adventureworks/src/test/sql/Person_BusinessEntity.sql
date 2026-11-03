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
-- Profiling queries on Person.BusinessEntity, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Person_BusinessEntity.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and the smallest and the largest BusinessEntityID; 20777 business entities, 1 to 20777.
SELECT COUNT(*), MIN(BusinessEntityID), MAX(BusinessEntityID)
FROM Person.BusinessEntity
;

-- a BusinessEntityID whose next value is missing, below the largest.
-- no rows: BusinessEntityID runs from 1 without a gap.
SELECT a.BusinessEntityID + 1 AS missing_from
FROM Person.BusinessEntity a
WHERE a.BusinessEntityID < (SELECT MAX(BusinessEntityID) FROM Person.BusinessEntity)
  AND NOT EXISTS (SELECT 1
                  FROM Person.BusinessEntity b
                  WHERE b.BusinessEntityID = a.BusinessEntityID + 1)
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_BusinessEntity_rowguid keeps rowguid unique.
-- no rows: no two business entities share a rowguid.
SELECT rowguid, COUNT(*) AS business_entities
FROM Person.BusinessEntity
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2017-12-13 13:20:24.150 to 2017-12-13 13:47:22.467, the half hour of the load.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Person.BusinessEntity
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a business entity is a Person, a Store or a Vendor; how many of each, and the range of their ids. 19972 persons,
-- 1 to 20777; 701 stores, 292 to 2051; 104 vendors, 1492 to 1698. The ranges interleave.
SELECT CASE
           WHEN p.BusinessEntityID IS NOT NULL THEN 'Person'
           WHEN s.BusinessEntityID IS NOT NULL THEN 'Store'
           WHEN v.BusinessEntityID IS NOT NULL THEN 'Vendor'
           END                 AS kind,
       COUNT(*)                AS business_entities,
       MIN(b.BusinessEntityID) AS smallest,
       MAX(b.BusinessEntityID) AS largest
FROM Person.BusinessEntity b
         LEFT JOIN Person.Person p ON p.BusinessEntityID = b.BusinessEntityID
         LEFT JOIN Sales.Store s ON s.BusinessEntityID = b.BusinessEntityID
         LEFT JOIN Purchasing.Vendor v ON v.BusinessEntityID = b.BusinessEntityID
GROUP BY CASE
             WHEN p.BusinessEntityID IS NOT NULL THEN 'Person'
             WHEN s.BusinessEntityID IS NOT NULL THEN 'Store'
             WHEN v.BusinessEntityID IS NOT NULL THEN 'Vendor'
             END
ORDER BY kind ASC
;

-- a business entity which is more than one of Person, Store and Vendor, or none of them.
-- no rows: every business entity is exactly one of the three.
SELECT b.BusinessEntityID
FROM Person.BusinessEntity b
WHERE (SELECT COUNT(*) FROM Person.Person p WHERE p.BusinessEntityID = b.BusinessEntityID)
          + (SELECT COUNT(*) FROM Sales.Store s WHERE s.BusinessEntityID = b.BusinessEntityID)
          +
      (SELECT COUNT(*) FROM Purchasing.Vendor v WHERE v.BusinessEntityID = b.BusinessEntityID) <> 1
;

-- a business entity with no BusinessEntityAddress; 1198.
SELECT COUNT(*) AS without_address
FROM Person.BusinessEntity b
WHERE NOT EXISTS (SELECT 1
                  FROM Person.BusinessEntityAddress a
                  WHERE a.BusinessEntityID = b.BusinessEntityID)
;

