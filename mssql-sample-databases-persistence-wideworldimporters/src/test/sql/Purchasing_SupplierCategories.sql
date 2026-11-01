-- -
-- #%L
-- mssql-sample-databases-persistence-wideworldimporters
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
-- Profiling queries on Purchasing.SupplierCategories, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Purchasing_SupplierCategories.sql
--


-- -------------------------------------------------------------------------------------------------- SupplierCategoryID
-- how many rows, and the smallest and the largest SupplierCategoryID; 9 categories, 1 to 9.
SELECT COUNT(*), MIN(SupplierCategoryID), MAX(SupplierCategoryID)
FROM Purchasing.SupplierCategories
;


-- ------------------------------------------------------------------------------------------------ SupplierCategoryName
-- the shortest and the longest SupplierCategoryName; 12 and 27 characters, of the 50 the column allows.
SELECT MIN(LEN(SupplierCategoryName)), MAX(LEN(SupplierCategoryName))
FROM Purchasing.SupplierCategories
;

-- a SupplierCategoryName which is blank, or carries leading or trailing spaces.
-- no rows: every SupplierCategoryName is trimmed and not blank.
SELECT SupplierCategoryID, '[' + SupplierCategoryName + ']' AS SupplierCategoryName
FROM Purchasing.SupplierCategories
WHERE TRIM(SupplierCategoryName) = ''
   OR DATALENGTH(SupplierCategoryName) <> DATALENGTH(TRIM(SupplierCategoryName))
;

-- UQ_Application_SupplierCategories_SupplierCategoryName keeps SupplierCategoryName unique.
-- no rows: no two categories share a name.
SELECT SupplierCategoryName, COUNT(*) AS categories
FROM Purchasing.SupplierCategories
GROUP BY SupplierCategoryName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who last edited the rows; person 1, Data Conversion Only, for 8 of them, and 16, Archer Lamble, for Courier Services
-- Supplier.
SELECT m.LastEditedBy, p.FullName, COUNT(*) AS categories
FROM Purchasing.SupplierCategories m
         JOIN Application.People p ON p.PersonID = m.LastEditedBy
GROUP BY m.LastEditedBy, p.FullName
ORDER BY m.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of ValidFrom; 2013-01-01 for 8 categories, and 2015-01-01 16:00 for Courier Services Supplier (6),
-- renamed then.
SELECT ValidFrom, COUNT(*) AS categories
FROM Purchasing.SupplierCategories
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a current row of a system-versioned table is valid until the end of time.
-- no rows: every ValidTo is 9999-12-31 23:59:59.9999999.
SELECT SupplierCategoryID, ValidTo
FROM Purchasing.SupplierCategories
WHERE ValidTo <> '9999-12-31 23:59:59.9999999'
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the suppliers of each category; Novelty Goods Supplier (2) has 6, Other Wholesaler (1) none, every other category
-- one.
SELECT c.SupplierCategoryID, c.SupplierCategoryName, COUNT(s.SupplierID) AS suppliers
FROM Purchasing.SupplierCategories c
         LEFT JOIN Purchasing.Suppliers s ON s.SupplierCategoryID = c.SupplierCategoryID
GROUP BY c.SupplierCategoryID, c.SupplierCategoryName
ORDER BY c.SupplierCategoryID ASC
;

-- the history the archive keeps; Courier Services Supplier (6) was Courier until 2015-01-01 16:00, where its row
-- begins.
SELECT a.SupplierCategoryID, a.SupplierCategoryName, a.ValidFrom, a.ValidTo, c.SupplierCategoryName AS now,
       c.ValidFrom
FROM Purchasing.SupplierCategories_Archive a
         JOIN Purchasing.SupplierCategories c ON c.SupplierCategoryID = a.SupplierCategoryID
ORDER BY a.SupplierCategoryID ASC, a.ValidFrom ASC
;

