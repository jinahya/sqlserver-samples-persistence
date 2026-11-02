-- -
-- #%L
-- sql-server-samples-persistence-adventureworks
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
-- Profiling queries on Purchasing.ProductVendor, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Purchasing_ProductVendor.sql
--


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- how many rows, and how many products; 460 pairs of 265 products, ProductID 1 to 952.
SELECT COUNT(*), COUNT(DISTINCT ProductID), MIN(ProductID), MAX(ProductID)
FROM Purchasing.ProductVendor
;

-- how many vendors a product has, and how many products have that many; 113 products have 1 vendor, 109 have 2 and 43
-- have 3.
SELECT vendors, COUNT(*) AS products
FROM (SELECT ProductID, COUNT(*) AS vendors
      FROM Purchasing.ProductVendor
      GROUP BY ProductID) p
GROUP BY vendors
ORDER BY vendors ASC
;

-- MakeFlag and FinishedGoodsFlag of the products the vendors supply; none is made in house (MakeFlag 0); 182 are parts,
-- 83 finished goods.
SELECT p.MakeFlag, p.FinishedGoodsFlag, COUNT(*) AS products
FROM Production.Product p
WHERE EXISTS (SELECT 1 FROM Purchasing.ProductVendor pv WHERE pv.ProductID = p.ProductID)
GROUP BY p.MakeFlag, p.FinishedGoodsFlag
ORDER BY p.MakeFlag ASC, p.FinishedGoodsFlag ASC
;


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many vendors, and how many products a vendor has at most; 86 vendors, the busiest with 30 products.
SELECT COUNT(DISTINCT BusinessEntityID),
       (SELECT TOP (1) COUNT(*)
        FROM Purchasing.ProductVendor
        GROUP BY BusinessEntityID
        ORDER BY COUNT(*) DESC) AS most_products
FROM Purchasing.ProductVendor
;


-- ----------------------------------------------------------------------------------------------------- AverageLeadTime
-- how many distinct AverageLeadTime, the smallest, the largest and the most common; CK_ProductVendor_AverageLeadTime
-- keeps it at 1 or more; 13 values, 10 to 120 days; 17 the most common, on 109 rows.
SELECT TOP (1) COUNT(*) OVER () AS distinct_values, MIN(AverageLeadTime) OVER () AS smallest,
       MAX(AverageLeadTime) OVER () AS largest, AverageLeadTime AS most_common, COUNT(*) AS products
FROM Purchasing.ProductVendor
GROUP BY AverageLeadTime
ORDER BY products DESC, AverageLeadTime ASC
;


-- ------------------------------------------------------------------------------------------------------- StandardPrice
-- the smallest and the largest StandardPrice; CK_ProductVendor_StandardPrice keeps it above 0.00; 0.2000 and 78.8900.
SELECT MIN(StandardPrice), MAX(StandardPrice)
FROM Purchasing.ProductVendor
;

-- StandardPrice against the StandardCost of the product; 427 above the cost, 33 below it, none at it.
SELECT COUNT(*) AS products,
       COUNT(IIF(pv.StandardPrice > p.StandardCost, 1, NULL)) AS above_cost,
       COUNT(IIF(pv.StandardPrice < p.StandardCost, 1, NULL)) AS below_cost
FROM Purchasing.ProductVendor pv
         JOIN Production.Product p ON p.ProductID = pv.ProductID
;


-- ----------------------------------------------------------------------------------------------------- LastReceiptCost
-- how many rows leave LastReceiptCost NULL, and its range; CK_ProductVendor_LastReceiptCost keeps it above 0.00; no
-- NULL; 0.2100 to 82.8345.
SELECT COUNT(*) - COUNT(LastReceiptCost) AS nulls, MIN(LastReceiptCost), MAX(LastReceiptCost)
FROM Purchasing.ProductVendor
;

-- LastReceiptCost against StandardPrice; 406 of the 460 are exactly 5% above; the ratio runs from 0.3055 to 1.2822.
SELECT COUNT(*) AS products,
       COUNT(IIF(LastReceiptCost = StandardPrice * 1.05, 1, NULL)) AS five_percent_above,
       MIN(LastReceiptCost / StandardPrice), MAX(LastReceiptCost / StandardPrice)
FROM Purchasing.ProductVendor
;


-- ----------------------------------------------------------------------------------------------------- LastReceiptDate
-- how many rows leave LastReceiptDate NULL, and its range; no NULL; 2022-07-21 to 2025-10-21.
SELECT COUNT(*) - COUNT(LastReceiptDate) AS nulls, MIN(LastReceiptDate), MAX(LastReceiptDate)
FROM Purchasing.ProductVendor
;

-- a LastReceiptDate without a LastReceiptCost, or the other way round.
-- no rows: both are always set.
SELECT ProductID, BusinessEntityID, LastReceiptCost, LastReceiptDate
FROM Purchasing.ProductVendor
WHERE (LastReceiptCost IS NULL AND LastReceiptDate IS NOT NULL)
   OR (LastReceiptCost IS NOT NULL AND LastReceiptDate IS NULL)
;


-- --------------------------------------------------------------------------------------------------------- MinOrderQty
-- how many distinct MinOrderQty, the smallest, the largest and the most common; CK_ProductVendor_MinOrderQty keeps it
-- at 1 or more; 11 values, 1 to 5000; 1 the most common, on 285 rows.
SELECT TOP (1) COUNT(*) OVER () AS distinct_values, MIN(MinOrderQty) OVER () AS smallest,
       MAX(MinOrderQty) OVER () AS largest, MinOrderQty AS most_common, COUNT(*) AS products
FROM Purchasing.ProductVendor
GROUP BY MinOrderQty
ORDER BY products DESC, MinOrderQty ASC
;


-- --------------------------------------------------------------------------------------------------------- MaxOrderQty
-- how many distinct MaxOrderQty, the smallest, the largest and the most common; CK_ProductVendor_MaxOrderQty keeps it
-- at 1 or more; 16 values, 5 to 15000; 5 the most common, on 285 rows.
SELECT TOP (1) COUNT(*) OVER () AS distinct_values, MIN(MaxOrderQty) OVER () AS smallest,
       MAX(MaxOrderQty) OVER () AS largest, MaxOrderQty AS most_common, COUNT(*) AS products
FROM Purchasing.ProductVendor
GROUP BY MaxOrderQty
ORDER BY products DESC, MaxOrderQty ASC
;

-- a MaxOrderQty below the MinOrderQty, which no check forbids.
-- no rows: no MaxOrderQty falls below its MinOrderQty.
SELECT ProductID, BusinessEntityID, MinOrderQty, MaxOrderQty
FROM Purchasing.ProductVendor
WHERE MaxOrderQty < MinOrderQty
;


-- ---------------------------------------------------------------------------------------------------------- OnOrderQty
-- how many rows leave OnOrderQty NULL, and its range; CK_ProductVendor_OnOrderQty keeps it at 0 or more; 305 NULLs; the
-- 155 others run from 3 to 8000.
SELECT COUNT(*) - COUNT(OnOrderQty) AS nulls, MIN(OnOrderQty), MAX(OnOrderQty)
FROM Purchasing.ProductVendor
;

-- an OnOrderQty outside MinOrderQty and MaxOrderQty; 0 and 0: every OnOrderQty lies between the two.
SELECT COUNT(*) AS on_order,
       COUNT(IIF(OnOrderQty < MinOrderQty, 1, NULL)) AS below_min,
       COUNT(IIF(OnOrderQty > MaxOrderQty, 1, NULL)) AS above_max
FROM Purchasing.ProductVendor
WHERE OnOrderQty IS NOT NULL
;


-- ----------------------------------------------------------------------------------------------------- UnitMeasureCode
-- the units; 7 units; CTN Container 178, EA Each 154, CAN Canister 68, CS Case 38, GAL Gallon 10, DZ Dozen 7, PAK Pack
-- 5.
SELECT pv.UnitMeasureCode, u.Name, COUNT(*) AS products
FROM Purchasing.ProductVendor pv
         JOIN Production.UnitMeasure u ON u.UnitMeasureCode = pv.UnitMeasureCode
GROUP BY pv.UnitMeasureCode, u.Name
ORDER BY products DESC, pv.UnitMeasureCode ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range of ModifiedDate; 2022-07-21 00:00:00.000 to 2026-08-11 12:20:28.343.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Purchasing.ProductVendor
;

-- ModifiedDate against LastReceiptDate; 406 rows modified on their LastReceiptDate; the other 54, a finding, all at
-- 2026-08-11 12:20:28.330 to .343, well after the install of 2025-11-14, products 707 to 716 and 841 to 884.
SELECT COUNT(IIF(ModifiedDate = LastReceiptDate, 1, NULL)) AS same,
       COUNT(IIF(ModifiedDate > LastReceiptDate, 1, NULL)) AS later,
       MIN(IIF(ModifiedDate > LastReceiptDate, ModifiedDate, NULL)),
       MAX(IIF(ModifiedDate > LastReceiptDate, ModifiedDate, NULL))
FROM Purchasing.ProductVendor
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a product and vendor pair never ordered; 0: every pair has been ordered.
SELECT COUNT(*)
FROM Purchasing.ProductVendor pv
WHERE NOT EXISTS (SELECT 1
                  FROM Purchasing.PurchaseOrderDetail d
                           JOIN Purchasing.PurchaseOrderHeader h ON h.PurchaseOrderID = d.PurchaseOrderID
                  WHERE d.ProductID = pv.ProductID
                    AND h.VendorID = pv.BusinessEntityID)
;

-- how many purchase order lines are of a product their vendor does not supply; 0: every order line matches a product of
-- its vendor.
SELECT COUNT(*)
FROM Purchasing.PurchaseOrderDetail d
         JOIN Purchasing.PurchaseOrderHeader h ON h.PurchaseOrderID = d.PurchaseOrderID
WHERE NOT EXISTS (SELECT 1
                  FROM Purchasing.ProductVendor pv
                  WHERE pv.ProductID = d.ProductID
                    AND pv.BusinessEntityID = h.VendorID)
;

