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
-- Profiling queries on Sales.SalesOrderDetail, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SalesOrderDetail.sql
--


-- -------------------------------------------------------------------------------------------------------- SalesOrderID
-- how many rows and orders, and the fewest and the most lines of an order; 121317 lines of 31465 orders, 1 to 72.
SELECT COUNT(*) AS lines, COUNT(DISTINCT SalesOrderID) AS orders, MIN(lines), MAX(lines)
FROM (SELECT SalesOrderID, COUNT(*) OVER (PARTITION BY SalesOrderID) AS lines
      FROM Sales.SalesOrderDetail) t
;


-- -------------------------------------------------------------------------------------------------- SalesOrderDetailID
-- the smallest and the largest SalesOrderDetailID, an identity across all orders; 1 to 121317.
SELECT MIN(SalesOrderDetailID), MAX(SalesOrderDetailID)
FROM Sales.SalesOrderDetail
;

-- a SalesOrderDetailID whose next value is missing, below the largest.
-- no rows: SalesOrderDetailID runs from 1 without a gap.
SELECT a.SalesOrderDetailID + 1 AS missing_from
FROM Sales.SalesOrderDetail a
WHERE a.SalesOrderDetailID < (SELECT MAX(SalesOrderDetailID) FROM Sales.SalesOrderDetail)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderDetail b
                  WHERE b.SalesOrderDetailID = a.SalesOrderDetailID + 1)
;

-- an order whose lines do not take consecutive SalesOrderDetailIDs.
-- no rows: the lines of each order are numbered in a single run.
SELECT SalesOrderID, MIN(SalesOrderDetailID), MAX(SalesOrderDetailID), COUNT(*) AS lines
FROM Sales.SalesOrderDetail
GROUP BY SalesOrderID
HAVING MAX(SalesOrderDetailID) - MIN(SalesOrderDetailID) + 1 <> COUNT(*)
;


-- ----------------------------------------------------------------------------------------------- CarrierTrackingNumber
-- CarrierTrackingNumber is nullable; how many lines have one, online or not. All the 60919 lines not online, none of
-- the 60398 online.
SELECT h.OnlineOrderFlag, COUNT(*) AS lines, COUNT(d.CarrierTrackingNumber) AS tracked
FROM Sales.SalesOrderDetail d
         JOIN Sales.SalesOrderHeader h ON h.SalesOrderID = d.SalesOrderID
GROUP BY h.OnlineOrderFlag
ORDER BY h.OnlineOrderFlag ASC
;

-- a CarrierTrackingNumber which is not of the form XXXX-XXXX-XX in hexadecimal digits.
-- no rows: every one is 12 characters of that form.
SELECT SalesOrderID, SalesOrderDetailID, CarrierTrackingNumber
FROM Sales.SalesOrderDetail
WHERE CarrierTrackingNumber NOT LIKE '____-____-__'
   OR REPLACE(CarrierTrackingNumber, '-', '') LIKE '%[^0-9A-F]%'
;

-- an order whose lines carry more than one CarrierTrackingNumber.
-- no rows: an order ships under a single number.
SELECT SalesOrderID, COUNT(DISTINCT CarrierTrackingNumber) AS numbers
FROM Sales.SalesOrderDetail
WHERE CarrierTrackingNumber IS NOT NULL
GROUP BY SalesOrderID
HAVING COUNT(DISTINCT CarrierTrackingNumber) > 1
;


-- ------------------------------------------------------------------------------------------------------------ OrderQty
-- the range of OrderQty, online or not, of the above 0 CK_SalesOrderDetail_OrderQty asks; 1 to 44 not online, and
-- always 1 online.
SELECT h.OnlineOrderFlag, MIN(d.OrderQty), MAX(d.OrderQty)
FROM Sales.SalesOrderDetail d
         JOIN Sales.SalesOrderHeader h ON h.SalesOrderID = d.SalesOrderID
GROUP BY h.OnlineOrderFlag
ORDER BY h.OnlineOrderFlag ASC
;


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- how many products were sold; 266.
SELECT COUNT(DISTINCT ProductID) AS products
FROM Sales.SalesOrderDetail
;

-- the schema has no unique key on SalesOrderID and ProductID; a product on more than one line of an order.
-- no rows: an order has a product once.
SELECT SalesOrderID, ProductID, COUNT(*) AS lines
FROM Sales.SalesOrderDetail
GROUP BY SalesOrderID, ProductID
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------ SpecialOfferID
-- how many lines each SpecialOfferID has; 12 of the 16 offers, 115884 with 1 (No Discount), and none with 6, 10, 12
-- and 15.
SELECT SpecialOfferID, COUNT(*) AS lines
FROM Sales.SalesOrderDetail
GROUP BY SpecialOfferID
ORDER BY SpecialOfferID ASC
;

-- a line ordered outside the dates of its offer.
-- no rows: every line falls in its offer's StartDate and EndDate.
SELECT d.SalesOrderID, d.SalesOrderDetailID, h.OrderDate, o.StartDate, o.EndDate
FROM Sales.SalesOrderDetail d
         JOIN Sales.SalesOrderHeader h ON h.SalesOrderID = d.SalesOrderID
         JOIN Sales.SpecialOffer o ON o.SpecialOfferID = d.SpecialOfferID
WHERE h.OrderDate NOT BETWEEN o.StartDate AND o.EndDate
;

-- a line whose OrderQty is outside its offer's MinQty and MaxQty; 2118 lines of offer 2 (Volume Discount 11 to 14),
-- each of a single unit.
SELECT d.SpecialOfferID, o.MinQty, o.MaxQty, MIN(d.OrderQty), MAX(d.OrderQty), COUNT(*) AS lines
FROM Sales.SalesOrderDetail d
         JOIN Sales.SpecialOffer o ON o.SpecialOfferID = d.SpecialOfferID
WHERE d.OrderQty < o.MinQty
   OR d.OrderQty > o.MaxQty
GROUP BY d.SpecialOfferID, o.MinQty, o.MaxQty
ORDER BY d.SpecialOfferID ASC
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- the range of UnitPrice; 1.3282 to 3578.27.
SELECT MIN(UnitPrice), MAX(UnitPrice)
FROM Sales.SalesOrderDetail
;

-- a line online whose UnitPrice is not the product's list price on the order day.
-- no rows: the online lines sell at the Production.ProductListPriceHistory price.
SELECT d.SalesOrderID, d.SalesOrderDetailID, d.ProductID, d.UnitPrice
FROM Sales.SalesOrderDetail d
         JOIN Sales.SalesOrderHeader h ON h.SalesOrderID = d.SalesOrderID
WHERE h.OnlineOrderFlag = 1
  AND NOT EXISTS (SELECT 1
                  FROM Production.ProductListPriceHistory l
                  WHERE l.ProductID = d.ProductID
                    AND h.OrderDate >= l.StartDate
                    AND (l.EndDate IS NULL OR h.OrderDate <= l.EndDate)
                    AND l.ListPrice = d.UnitPrice)
;

-- the share of the list price the lines not online sell at; .2 to .6 of it.
SELECT MIN(d.UnitPrice / l.ListPrice), MAX(d.UnitPrice / l.ListPrice)
FROM Sales.SalesOrderDetail d
         JOIN Sales.SalesOrderHeader h ON h.SalesOrderID = d.SalesOrderID
         JOIN Production.ProductListPriceHistory l ON l.ProductID = d.ProductID
    AND h.OrderDate >= l.StartDate
    AND (l.EndDate IS NULL OR h.OrderDate <= l.EndDate)
WHERE h.OnlineOrderFlag = 0
;


-- --------------------------------------------------------------------------------------------------- UnitPriceDiscount
-- how many lines each UnitPriceDiscount has; 9 values, .00 to .40, 118035 lines with none.
SELECT UnitPriceDiscount, COUNT(*) AS lines
FROM Sales.SalesOrderDetail
GROUP BY UnitPriceDiscount
ORDER BY UnitPriceDiscount ASC
;

-- a line whose UnitPriceDiscount is not its offer's DiscountPct; 2151 lines with no discount, 2118 of offer 2, 20 of
-- 13 and 13 of 14.
SELECT d.SpecialOfferID, o.DiscountPct, d.UnitPriceDiscount, COUNT(*) AS lines
FROM Sales.SalesOrderDetail d
         JOIN Sales.SpecialOffer o ON o.SpecialOfferID = d.SpecialOfferID
WHERE d.UnitPriceDiscount <> o.DiscountPct
GROUP BY d.SpecialOfferID, o.DiscountPct, d.UnitPriceDiscount
ORDER BY d.SpecialOfferID ASC
;


-- ----------------------------------------------------------------------------------------------------------- LineTotal
-- LineTotal is computed, UnitPrice less its discount, times OrderQty; one which is not.
-- no rows: every LineTotal follows its line.
SELECT SalesOrderID, SalesOrderDetailID, UnitPrice, UnitPriceDiscount, OrderQty, LineTotal
FROM Sales.SalesOrderDetail
WHERE LineTotal <> UnitPrice * (1.0 - UnitPriceDiscount) * OrderQty
;

-- the range of LineTotal; 1.374 to 27893.619.
SELECT MIN(LineTotal), MAX(LineTotal)
FROM Sales.SalesOrderDetail
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_SalesOrderDetail_rowguid keeps rowguid unique.
-- no rows: no two lines share a rowguid.
SELECT rowguid, COUNT(*) AS lines
FROM Sales.SalesOrderDetail
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- a ModifiedDate which is not the OrderDate of its order.
-- no rows: every line was last modified on the day of its order, unlike the header, modified on its ShipDate.
SELECT d.SalesOrderID, d.SalesOrderDetailID, h.OrderDate, d.ModifiedDate
FROM Sales.SalesOrderDetail d
         JOIN Sales.SalesOrderHeader h ON h.SalesOrderID = d.SalesOrderID
WHERE d.ModifiedDate <> h.OrderDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a line whose product and offer are no Sales.SpecialOfferProduct; the foreign key forbids it.
-- no rows: every line refers to a SpecialOfferProduct.
SELECT d.SalesOrderID, d.SalesOrderDetailID, d.SpecialOfferID, d.ProductID
FROM Sales.SalesOrderDetail d
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SpecialOfferProduct p
                  WHERE p.SpecialOfferID = d.SpecialOfferID
                    AND p.ProductID = d.ProductID)
;

