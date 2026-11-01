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
-- Profiling queries on Purchasing.ShipMethod, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Purchasing_ShipMethod.sql
--


-- -------------------------------------------------------------------------------------------------------- ShipMethodID
-- how many rows, and the smallest and the largest ShipMethodID; 5 methods, 1 to 5.
SELECT COUNT(*), MIN(ShipMethodID), MAX(ShipMethodID)
FROM Purchasing.ShipMethod
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the methods, in ShipMethodID order, with their rates; XRQ - TRUCK GROUND 3.95 and 0.99, ZY - EXPRESS 9.95 and 1.99,
-- OVERSEAS - DELUXE 29.95 and 2.99, OVERNIGHT J-FAST 21.95 and 1.29, CARGO TRANSPORT 5 8.99 and 1.49.
SELECT ShipMethodID, Name, ShipBase, ShipRate
FROM Purchasing.ShipMethod
ORDER BY ShipMethodID ASC
;

-- a Name which is blank, or carries leading or trailing spaces.
-- no rows: every Name is trimmed and not blank.
SELECT ShipMethodID, '[' + Name + ']' AS Name
FROM Purchasing.ShipMethod
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_ShipMethod_Name keeps Name unique.
-- no rows: no two methods share a Name.
SELECT Name, COUNT(*) AS methods
FROM Purchasing.ShipMethod
GROUP BY Name
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ ShipBase
-- the smallest and the largest ShipBase; CK_ShipMethod_ShipBase keeps it above 0.00; 3.9500 and 29.9500.
SELECT MIN(ShipBase), MAX(ShipBase)
FROM Purchasing.ShipMethod
;


-- ------------------------------------------------------------------------------------------------------------ ShipRate
-- the smallest and the largest ShipRate; CK_ShipMethod_ShipRate keeps it above 0.00; 0.9900 and 2.9900.
SELECT MIN(ShipRate), MAX(ShipRate)
FROM Purchasing.ShipMethod
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_ShipMethod_rowguid keeps rowguid unique.
-- no rows: no two methods share a rowguid.
SELECT rowguid, COUNT(*) AS methods
FROM Purchasing.ShipMethod
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Purchasing.ShipMethod
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the purchase orders and the sales orders shipped by each method; every method ships purchase orders, CARGO TRANSPORT
-- 5 the most with 1523; sales orders use only XRQ - TRUCK GROUND (27659) and CARGO TRANSPORT 5 (3806).
SELECT m.ShipMethodID, m.Name,
       (SELECT COUNT(*) FROM Purchasing.PurchaseOrderHeader h WHERE h.ShipMethodID = m.ShipMethodID) AS purchases,
       (SELECT COUNT(*) FROM Sales.SalesOrderHeader h WHERE h.ShipMethodID = m.ShipMethodID) AS sales
FROM Purchasing.ShipMethod m
ORDER BY m.ShipMethodID ASC
;

