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
-- Profiling queries on Sales.SpecialOfferProduct, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SpecialOfferProduct.sql
--


-- ------------------------------------------------------------------------------------------------------ SpecialOfferID
-- how many rows, offers and products; 538 pairs of 15 offers and 295 products.
SELECT COUNT(*), COUNT(DISTINCT SpecialOfferID), COUNT(DISTINCT ProductID)
FROM Sales.SpecialOfferProduct
;

-- how many products each offer has; 295 for 1 (No Discount), 111 for 2, down to 1 for 9, and none for 6.
SELECT o.SpecialOfferID, COUNT(p.ProductID) AS products
FROM Sales.SpecialOffer o
         LEFT JOIN Sales.SpecialOfferProduct p ON p.SpecialOfferID = o.SpecialOfferID
GROUP BY o.SpecialOfferID
ORDER BY o.SpecialOfferID ASC
;


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- a product on an offer which is not on offer 1, No Discount.
-- no rows: every product of an offer is also on offer 1.
SELECT t.SpecialOfferID, t.ProductID
FROM Sales.SpecialOfferProduct t
WHERE t.SpecialOfferID <> 1
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SpecialOfferProduct p
                  WHERE p.SpecialOfferID = 1
                    AND p.ProductID = t.ProductID)
;

-- the products on offer 1, finished goods or not; 295 of the 504, all finished goods and only those.
SELECT p.FinishedGoodsFlag, COUNT(*) AS products, COUNT(t.ProductID) AS on_offer
FROM Production.Product p
         LEFT JOIN Sales.SpecialOfferProduct t ON t.ProductID = p.ProductID
    AND t.SpecialOfferID = 1
GROUP BY p.FinishedGoodsFlag
ORDER BY p.FinishedGoodsFlag ASC
;

-- a product on an offer which runs wholly outside the product's SellStartDate and SellEndDate; 6, products 725 to
-- 730 on offer 12, LL Road Frame Sale, which starts the day after they stopped selling.
SELECT t.SpecialOfferID, t.ProductID, o.StartDate, o.EndDate, p.SellStartDate, p.SellEndDate
FROM Sales.SpecialOfferProduct t
         JOIN Sales.SpecialOffer o ON o.SpecialOfferID = t.SpecialOfferID
         JOIN Production.Product p ON p.ProductID = t.ProductID
WHERE o.StartDate > p.SellEndDate
   OR o.EndDate < p.SellStartDate
ORDER BY t.SpecialOfferID ASC, t.ProductID ASC
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_SpecialOfferProduct_rowguid keeps rowguid unique.
-- no rows: no two pairs share a rowguid.
SELECT rowguid, COUNT(*) AS pairs
FROM Sales.SpecialOfferProduct
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- a ModifiedDate which is not that of its offer.
-- no rows: every pair carries its offer's ModifiedDate.
SELECT t.SpecialOfferID, t.ProductID, t.ModifiedDate, o.ModifiedDate
FROM Sales.SpecialOfferProduct t
         JOIN Sales.SpecialOffer o ON o.SpecialOfferID = t.SpecialOfferID
WHERE t.ModifiedDate <> o.ModifiedDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the pairs no order line uses, per offer; 54: 29 of offer 1, 12 of 12, 7 of 15, 3 of 10, 2 of 16 and 1 of 4.
SELECT t.SpecialOfferID, COUNT(*) AS pairs
FROM Sales.SpecialOfferProduct t
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderDetail d
                  WHERE d.SpecialOfferID = t.SpecialOfferID
                    AND d.ProductID = t.ProductID)
GROUP BY t.SpecialOfferID
ORDER BY t.SpecialOfferID ASC
;

