-- -
-- #%L
-- sqlserver-samples-persistence-wideworldimporters
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
-- Profiling queries on Application.PaymentMethods, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Application_PaymentMethods.sql
--


-- ----------------------------------------------------------------------------------------------------- PaymentMethodID
-- how many rows, and the smallest and the largest PaymentMethodID; 4 methods, 1 to 4.
SELECT COUNT(*), MIN(PaymentMethodID), MAX(PaymentMethodID)
FROM Application.PaymentMethods
;


-- --------------------------------------------------------------------------------------------------- PaymentMethodName
-- the shortest and the longest PaymentMethodName; 3 and 11 characters, of the 50 the column allows.
SELECT MIN(LEN(PaymentMethodName)), MAX(LEN(PaymentMethodName))
FROM Application.PaymentMethods
;

-- a PaymentMethodName which is blank, or carries leading or trailing spaces.
-- no rows: every PaymentMethodName is trimmed and not blank.
SELECT PaymentMethodID, '[' + PaymentMethodName + ']' AS PaymentMethodName
FROM Application.PaymentMethods
WHERE TRIM(PaymentMethodName) = ''
   OR DATALENGTH(PaymentMethodName) <> DATALENGTH(TRIM(PaymentMethodName))
;

-- UQ_Application_PaymentMethods_PaymentMethodName keeps PaymentMethodName unique.
-- no rows: no two methods share a name.
SELECT PaymentMethodName, COUNT(*) AS methods
FROM Application.PaymentMethods
GROUP BY PaymentMethodName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who last edited the rows; person 1, Data Conversion Only, for 3 of them, and 9, Alica Fatnowna, for Credit-Card.
SELECT m.LastEditedBy, p.FullName, COUNT(*) AS methods
FROM Application.PaymentMethods m
         JOIN Application.People p ON p.PersonID = m.LastEditedBy
GROUP BY m.LastEditedBy, p.FullName
ORDER BY m.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the range of ValidFrom; 2013-01-01 for 3 methods, and 2016-01-01 16:00 for Credit-Card (3), renamed then.
SELECT ValidFrom, COUNT(*) AS methods
FROM Application.PaymentMethods
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- a current row of a system-versioned table is valid until the end of time.
-- no rows: every ValidTo is 9999-12-31 23:59:59.9999999.
SELECT PaymentMethodID, ValidTo
FROM Application.PaymentMethods
WHERE ValidTo <> '9999-12-31 23:59:59.9999999'
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the supplier and customer transactions of each method; only EFT (4) is used, by all 2438 supplier and
-- all 26637 customer transactions.
SELECT m.PaymentMethodID, m.PaymentMethodName,
       (SELECT COUNT(*) FROM Purchasing.SupplierTransactions s WHERE s.PaymentMethodID = m.PaymentMethodID) AS supplier,
       (SELECT COUNT(*) FROM Sales.CustomerTransactions c WHERE c.PaymentMethodID = m.PaymentMethodID) AS customer
FROM Application.PaymentMethods m
ORDER BY m.PaymentMethodID ASC
;

-- the history the archive keeps; Credit-Card (3) was Credit Card until 2016-01-01 16:00, where its row begins.
SELECT a.PaymentMethodID, a.PaymentMethodName, a.ValidFrom, a.ValidTo, m.PaymentMethodName AS now, m.ValidFrom
FROM Application.PaymentMethods_Archive a
         JOIN Application.PaymentMethods m ON m.PaymentMethodID = a.PaymentMethodID
ORDER BY a.PaymentMethodID ASC, a.ValidFrom ASC
;

