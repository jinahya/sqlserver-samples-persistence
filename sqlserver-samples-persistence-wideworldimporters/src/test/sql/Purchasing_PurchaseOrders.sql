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
-- Profiling queries on Purchasing.PurchaseOrders, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Purchasing_PurchaseOrders.sql
--


-- ----------------------------------------------------------------------------------------------------- PurchaseOrderID
-- how many rows, and the smallest and the largest PurchaseOrderID; 2074 orders, 1 to 2074.
SELECT COUNT(*), MIN(PurchaseOrderID), MAX(PurchaseOrderID)
FROM Purchasing.PurchaseOrders
;

-- a PurchaseOrderID whose next value is missing, below the largest.
-- no rows: PurchaseOrderID runs from 1 without a gap.
SELECT a.PurchaseOrderID + 1 AS missing_from
FROM Purchasing.PurchaseOrders a
WHERE a.PurchaseOrderID < (SELECT MAX(PurchaseOrderID) FROM Purchasing.PurchaseOrders)
  AND NOT EXISTS (SELECT 1
                  FROM Purchasing.PurchaseOrders b
                  WHERE b.PurchaseOrderID = a.PurchaseOrderID + 1)
;


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- the orders of each supplier; 7 of the 13 suppliers, Fabrikam, Inc. (4) with 1055 and Litware, Inc. (7) with 985.
SELECT o.SupplierID, s.SupplierName, COUNT(*) AS orders
FROM Purchasing.PurchaseOrders o
         JOIN Purchasing.Suppliers s ON s.SupplierID = o.SupplierID
GROUP BY o.SupplierID, s.SupplierName
ORDER BY o.SupplierID ASC
;


-- ----------------------------------------------------------------------------------------------------------- OrderDate
-- the range of OrderDate; 2013-01-01 to 2016-05-31, on 1064 days.
SELECT MIN(OrderDate), MAX(OrderDate), COUNT(DISTINCT OrderDate) AS days
FROM Purchasing.PurchaseOrders
;

-- the orders placed on a Sunday; the others are placed Monday to Saturday.
-- one row: 1824, on 2016-01-03.
SELECT PurchaseOrderID, OrderDate
FROM Purchasing.PurchaseOrders
WHERE DATEPART(weekday, OrderDate) = DATEPART(weekday, '2016-01-03')
;


-- ---------------------------------------------------------------------------------------------------- DeliveryMethodID
-- the methods the orders are delivered by; Road Freight (7) 1065, Courier (2) 985, Refrigerated Air Freight (10) 13,
-- Air Freight (8) 10, Refrigerated Road Freight (9) 1.
SELECT o.DeliveryMethodID, m.DeliveryMethodName, COUNT(*) AS orders
FROM Purchasing.PurchaseOrders o
         JOIN Application.DeliveryMethods m ON m.DeliveryMethodID = o.DeliveryMethodID
GROUP BY o.DeliveryMethodID, m.DeliveryMethodName
ORDER BY orders DESC, o.DeliveryMethodID ASC
;

-- an order delivered by another method than its supplier's.
-- no rows: every order takes the DeliveryMethodID of its supplier.
SELECT o.PurchaseOrderID, o.DeliveryMethodID, s.DeliveryMethodID AS supplier_method
FROM Purchasing.PurchaseOrders o
         JOIN Purchasing.Suppliers s ON s.SupplierID = o.SupplierID
WHERE o.DeliveryMethodID <> s.DeliveryMethodID
;


-- ----------------------------------------------------------------------------------------------------- ContactPersonID
-- the people the orders are placed by; Kayla Woodcock (2) for all 2074.
SELECT o.ContactPersonID, p.FullName, COUNT(*) AS orders
FROM Purchasing.PurchaseOrders o
         JOIN Application.People p ON p.PersonID = o.ContactPersonID
GROUP BY o.ContactPersonID, p.FullName
;


-- ------------------------------------------------------------------------------------------------ ExpectedDeliveryDate
-- ExpectedDeliveryDate is nullable; how many orders have none, and the range. None; 2013-01-15 to 2016-06-20.
SELECT COUNT(*) - COUNT(ExpectedDeliveryDate) AS without, MIN(ExpectedDeliveryDate), MAX(ExpectedDeliveryDate)
FROM Purchasing.PurchaseOrders
;

-- how many days after the order the delivery is expected; 3, 7, 12, 14 or 20, 20 for 1884 of the orders.
SELECT DATEDIFF(day, OrderDate, ExpectedDeliveryDate) AS days, COUNT(*) AS orders
FROM Purchasing.PurchaseOrders
GROUP BY DATEDIFF(day, OrderDate, ExpectedDeliveryDate)
ORDER BY days ASC
;


-- --------------------------------------------------------------------------------------------------- SupplierReference
-- SupplierReference is nullable; how many orders have none, and how long the others are. None; 6 to 9 characters.
SELECT COUNT(*) - COUNT(SupplierReference) AS without, MIN(LEN(SupplierReference)), MAX(LEN(SupplierReference))
FROM Purchasing.PurchaseOrders
;

-- an order whose SupplierReference is not its supplier's.
-- no rows: every order carries the SupplierReference of its supplier.
SELECT o.PurchaseOrderID, o.SupplierReference, s.SupplierReference AS supplier_reference
FROM Purchasing.PurchaseOrders o
         JOIN Purchasing.Suppliers s ON s.SupplierID = o.SupplierID
WHERE o.SupplierReference <> s.SupplierReference
;


-- ---------------------------------------------------------------------------------------------------- IsOrderFinalized
-- the orders not yet finalized; 2073 and 2074, both of 2016-05-31, the last day.
SELECT PurchaseOrderID, OrderDate
FROM Purchasing.PurchaseOrders
WHERE IsOrderFinalized = 0
;

-- a finalized order with a line not finalized, or an order not finalized with every line finalized.
-- no rows: an order is finalized exactly when all its lines are.
SELECT o.PurchaseOrderID, o.IsOrderFinalized
FROM Purchasing.PurchaseOrders o
WHERE o.IsOrderFinalized = 1
  AND EXISTS (SELECT 1
              FROM Purchasing.PurchaseOrderLines l
              WHERE l.PurchaseOrderID = o.PurchaseOrderID
                AND l.IsOrderLineFinalized = 0)
   OR o.IsOrderFinalized = 0
  AND NOT EXISTS (SELECT 1
                  FROM Purchasing.PurchaseOrderLines l
                  WHERE l.PurchaseOrderID = o.PurchaseOrderID
                    AND l.IsOrderLineFinalized = 0)
;


-- ------------------------------------------------------------------------------------------------------------ Comments
-- Comments is a nullable text; how many orders have one. None.
SELECT COUNT(Comments) AS with_comments
FROM Purchasing.PurchaseOrders
;


-- ---------------------------------------------------------------------------------------------------- InternalComments
-- InternalComments is a nullable text; how many orders have one. None.
SELECT COUNT(InternalComments) AS with_comments
FROM Purchasing.PurchaseOrders
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- how many people last edited the orders; 20, persons 2 to 20 for 87 to 141 each, and Data Conversion Only (1)
-- for the 2 not finalized.
SELECT COUNT(DISTINCT LastEditedBy) AS people,
       SUM(CASE WHEN LastEditedBy = 1 THEN 1 ELSE 0 END) AS by_data_conversion,
       SUM(CASE WHEN LastEditedBy = 1 AND IsOrderFinalized = 0 THEN 1 ELSE 0 END) AS of_them_open
FROM Purchasing.PurchaseOrders
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- the range of LastEditedWhen; 2013-01-02 07:00 to 2016-05-31 13:00.
SELECT MIN(LastEditedWhen), MAX(LastEditedWhen)
FROM Purchasing.PurchaseOrders
;

-- how many days after the order it was last edited; 0 to 3, a day after for 1432 of the orders.
SELECT DATEDIFF(day, OrderDate, LastEditedWhen) AS days, COUNT(*) AS orders
FROM Purchasing.PurchaseOrders
GROUP BY DATEDIFF(day, OrderDate, LastEditedWhen)
ORDER BY days ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an order with no line.
-- no rows: every order has at least one line.
SELECT o.PurchaseOrderID
FROM Purchasing.PurchaseOrders o
WHERE NOT EXISTS (SELECT 1
                  FROM Purchasing.PurchaseOrderLines l
                  WHERE l.PurchaseOrderID = o.PurchaseOrderID)
;

-- how many lines the orders have; 1 to 34, 6 for 876 of them.
SELECT n.lines, COUNT(*) AS orders
FROM (SELECT PurchaseOrderID, COUNT(*) AS lines
      FROM Purchasing.PurchaseOrderLines
      GROUP BY PurchaseOrderID) n
GROUP BY n.lines
ORDER BY n.lines ASC
;

-- an order with other than one supplier transaction.
-- 2 rows: 2073 and 2074, not finalized, with none yet.
SELECT o.PurchaseOrderID, COUNT(t.SupplierTransactionID) AS transactions
FROM Purchasing.PurchaseOrders o
         LEFT JOIN Purchasing.SupplierTransactions t ON t.PurchaseOrderID = o.PurchaseOrderID
GROUP BY o.PurchaseOrderID
HAVING COUNT(t.SupplierTransactionID) <> 1
;

