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
-- Profiling queries on Sales.ShoppingCartItem, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_ShoppingCartItem.sql
--


-- -------------------------------------------------------------------------------------------------- ShoppingCartItemID
-- how many rows, and the smallest and the largest ShoppingCartItemID; 3 items, 2 to 5.
SELECT COUNT(*), MIN(ShoppingCartItemID), MAX(ShoppingCartItemID)
FROM Sales.ShoppingCartItem
;


-- ------------------------------------------------------------------------------------------------------ ShoppingCartID
-- how many items each cart has; cart 14951 with 1 and 20621 with 2.
SELECT ShoppingCartID, COUNT(*) AS items
FROM Sales.ShoppingCartItem
GROUP BY ShoppingCartID
ORDER BY ShoppingCartID ASC
;

-- a ShoppingCartID which carries anything but digits, though the column is an nvarchar(50).
-- no rows: every ShoppingCartID is all digits.
SELECT ShoppingCartItemID, ShoppingCartID
FROM Sales.ShoppingCartItem
WHERE ShoppingCartID LIKE '%[^0-9]%'
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the range of Quantity, 1 or more by CK_ShoppingCartItem_Quantity; 3 to 7.
SELECT MIN(Quantity), MAX(Quantity)
FROM Sales.ShoppingCartItem
;


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- IX_ShoppingCartItem_ShoppingCartID_ProductID is not unique; a product more than once in a cart.
-- no rows: a cart has a product once.
SELECT ShoppingCartID, ProductID, COUNT(*) AS items
FROM Sales.ShoppingCartItem
GROUP BY ShoppingCartID, ProductID
HAVING COUNT(*) > 1
;

-- a ProductID which refers to no product; the foreign key forbids it.
-- no rows: every ProductID refers to a Production.Product.
SELECT t.ShoppingCartItemID, t.ProductID
FROM Sales.ShoppingCartItem t
WHERE NOT EXISTS (SELECT 1
                  FROM Production.Product p
                  WHERE p.ProductID = t.ProductID)
;


-- --------------------------------------------------------------------------------------------------------- DateCreated
-- the range of DateCreated; 2024-11-08 17:54:07.603, a single moment.
SELECT MIN(DateCreated), MAX(DateCreated)
FROM Sales.ShoppingCartItem
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- a ModifiedDate which is not the DateCreated.
-- no rows: no item was modified after it was created.
SELECT ShoppingCartItemID, DateCreated, ModifiedDate
FROM Sales.ShoppingCartItem
WHERE ModifiedDate <> DateCreated
;

