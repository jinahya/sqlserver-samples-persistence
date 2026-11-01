-- -
-- #%L
-- mssql-sample-databases-persistence-futonmanufacturing
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
-- Profiling queries on dbo.InventoryTransaction, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The load scripts insert no transaction, so the table is empty and every check below finds nothing. They are
-- kept for the rows an application would add.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_InventoryTransaction.sql
--


-- ------------------------------------------------------------------------------------------------------- TransactionID
-- how many rows, and the smallest and the largest TransactionID; 0 transactions, NULL and NULL.
SELECT COUNT(*), MIN(TransactionID), MAX(TransactionID)
FROM dbo.InventoryTransaction
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- a transaction of an item in a warehouse which has no Inventory row for it.
-- no rows: the table is empty.
SELECT t.TransactionID, t.ItemID, t.WarehouseID
FROM dbo.InventoryTransaction t
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.Inventory v
                  WHERE v.ItemID = t.ItemID
                    AND v.WarehouseID = t.WarehouseID)
;


-- --------------------------------------------------------------------------------------------------------- WarehouseID
-- the transactions of each warehouse.
-- no rows: the table is empty.
SELECT WarehouseID, COUNT(*) AS transactions
FROM dbo.InventoryTransaction
GROUP BY WarehouseID
ORDER BY WarehouseID ASC
;


-- --------------------------------------------------------------------------------------------------- TransactionTypeID
-- the transactions of each type, and the sign of their quantities; a receipt should add, an issue take away.
-- no rows: the table is empty.
SELECT t.TransactionTypeID, tt.TypeCode, COUNT(*) AS transactions, MIN(t.Quantity), MAX(t.Quantity)
FROM dbo.InventoryTransaction t
         JOIN dbo.TransactionType tt ON tt.TransactionTypeID = t.TransactionTypeID
GROUP BY t.TransactionTypeID, tt.TypeCode
ORDER BY t.TransactionTypeID ASC
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- a transaction of no quantity.
-- no rows: the table is empty.
SELECT TransactionID, Quantity
FROM dbo.InventoryTransaction
WHERE Quantity = 0
;


-- ------------------------------------------------------------------------------------------------------------ UnitCost
-- a UnitCost which is negative.
-- no rows: the table is empty.
SELECT TransactionID, UnitCost
FROM dbo.InventoryTransaction
WHERE UnitCost < 0
;


-- ----------------------------------------------------------------------------------------------------- ReferenceNumber
-- a ReferenceNumber which is blank, or carries leading or trailing spaces.
-- no rows: the table is empty.
SELECT TransactionID, '[' + ReferenceNumber + ']' AS ReferenceNumber
FROM dbo.InventoryTransaction
WHERE TRIM(ReferenceNumber) = ''
   OR DATALENGTH(ReferenceNumber) <> DATALENGTH(TRIM(ReferenceNumber))
;


-- ------------------------------------------------------------------------------------------------------- ReferenceType
-- the values ReferenceType takes, NULL for none.
-- no rows: the table is empty.
SELECT ReferenceType, COUNT(*) AS transactions
FROM dbo.InventoryTransaction
GROUP BY ReferenceType
ORDER BY ReferenceType ASC
;


-- --------------------------------------------------------------------------------------------------------------- Notes
-- a Notes which is blank.
-- no rows: the table is empty.
SELECT TransactionID
FROM dbo.InventoryTransaction
WHERE TRIM(Notes) = ''
;


-- ----------------------------------------------------------------------------------------------------- TransactionDate
-- how many TransactionDate values are NULL, and the earliest and the latest; 0, NULL and NULL.
SELECT COUNT(*) - COUNT(TransactionDate), MIN(TransactionDate), MAX(TransactionDate)
FROM dbo.InventoryTransaction
;


-- ----------------------------------------------------------------------------------------------------------- CreatedBy
-- the values CreatedBy takes, NULL for none.
-- no rows: the table is empty.
SELECT CreatedBy, COUNT(*) AS transactions
FROM dbo.InventoryTransaction
GROUP BY CreatedBy
ORDER BY CreatedBy ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an item whose QuantityOnHand is not the sum of its transactions; with no transaction, every item's stock is an
-- opening balance the load script wrote straight into Inventory; 45 items, 1 to 45.
SELECT COUNT(*), MIN(v.ItemID), MAX(v.ItemID)
FROM dbo.Inventory v
WHERE v.QuantityOnHand <> ISNULL((SELECT SUM(t.Quantity)
                                  FROM dbo.InventoryTransaction t
                                  WHERE t.ItemID = v.ItemID
                                    AND t.WarehouseID = v.WarehouseID), 0)
;

