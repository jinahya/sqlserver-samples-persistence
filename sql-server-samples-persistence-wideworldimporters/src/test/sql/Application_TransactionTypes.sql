-- -
-- #%L
-- sql-server-samples-persistence-wideworldimporters
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
-- Profiling queries on Application.TransactionTypes, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_TransactionTypes.sql
--


-- --------------------------------------------------------------------------------------------------- TransactionTypeID
-- how many rows, and the smallest and the largest TransactionTypeID; 13 types, 1 to 13.
SELECT COUNT(*), MIN(TransactionTypeID), MAX(TransactionTypeID)
FROM Application.TransactionTypes
;


-- ------------------------------------------------------------------------------------------------- TransactionTypeName
-- the shortest and the longest TransactionTypeName; 11 and 29 characters, of the 50 the column allows.
SELECT MIN(LEN(TransactionTypeName)), MAX(LEN(TransactionTypeName))
FROM Application.TransactionTypes
;

-- a TransactionTypeName which is blank, or carries leading or trailing spaces.
-- no rows: every TransactionTypeName is trimmed and not blank.
SELECT TransactionTypeID, '[' + TransactionTypeName + ']' AS TransactionTypeName
FROM Application.TransactionTypes
WHERE TRIM(TransactionTypeName) = ''
   OR DATALENGTH(TransactionTypeName) <> DATALENGTH(TRIM(TransactionTypeName))
;

-- UQ_Application_TransactionTypes_TransactionTypeName keeps TransactionTypeName unique.
-- no rows: no two types share a name.
SELECT TransactionTypeName, COUNT(*) AS types
FROM Application.TransactionTypes
GROUP BY TransactionTypeName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who last edited the rows; person 1, Data Conversion Only, for 12 of them, and 9, Alica Fatnowna, for Customer Contra.
SELECT m.LastEditedBy, p.FullName, COUNT(*) AS types
FROM Application.TransactionTypes m
         JOIN Application.People p ON p.PersonID = m.LastEditedBy
GROUP BY m.LastEditedBy, p.FullName
ORDER BY m.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of ValidFrom; 2013-01-01 for 12 types, and 2016-01-01 16:05 for Customer Contra (13), renamed then.
SELECT ValidFrom, COUNT(*) AS types
FROM Application.TransactionTypes
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a current row of a system-versioned table is valid until the end of time.
-- no rows: every ValidTo is 9999-12-31 23:59:59.9999999.
SELECT TransactionTypeID, ValidTo
FROM Application.TransactionTypes
WHERE ValidTo <> '9999-12-31 23:59:59.9999999'
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the supplier, customer and stock item transactions of each type; 7 types have none, and each of the other 6 is
-- used by one table only: Supplier Invoice (5) 2072 and Supplier Payment Issued (7) 366, Customer Invoice (1) 70510
-- and Customer Payment Received (3) 26637, Stock Issue (10) 228265, Stock Receipt (11) 8358 and Stock Adjustment at
-- Stocktake (12) 44.
SELECT t.TransactionTypeID, t.TransactionTypeName,
       (SELECT COUNT(*) FROM Purchasing.SupplierTransactions s WHERE s.TransactionTypeID = t.TransactionTypeID)
           AS supplier,
       (SELECT COUNT(*) FROM Sales.CustomerTransactions c WHERE c.TransactionTypeID = t.TransactionTypeID)
           AS customer,
       (SELECT COUNT(*) FROM Warehouse.StockItemTransactions w WHERE w.TransactionTypeID = t.TransactionTypeID)
           AS stock_item
FROM Application.TransactionTypes t
ORDER BY t.TransactionTypeID ASC
;

-- the history the archive keeps; Customer Contra (13) was Contra from 2016-01-01 16:00 for five minutes.
SELECT a.TransactionTypeID, a.TransactionTypeName, a.ValidFrom, a.ValidTo, t.TransactionTypeName AS now, t.ValidFrom
FROM Application.TransactionTypes_Archive a
         JOIN Application.TransactionTypes t ON t.TransactionTypeID = a.TransactionTypeID
ORDER BY a.TransactionTypeID ASC, a.ValidFrom ASC
;

