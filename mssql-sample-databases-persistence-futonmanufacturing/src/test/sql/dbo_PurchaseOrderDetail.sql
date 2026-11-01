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
-- Profiling queries on dbo.PurchaseOrderDetail, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_PurchaseOrderDetail.sql
--


-- ---------------------------------------------------------------------------------------------------------- PODetailID
-- how many rows, and the smallest and the largest PODetailID; 10 lines, 1 to 10.
SELECT COUNT(*), MIN(PODetailID), MAX(PODetailID)
FROM dbo.PurchaseOrderDetail
;


-- ----------------------------------------------------------------------------------------------------- PurchaseOrderID
-- the fewest and the most lines of an order; 2 and 2, every order has two.
SELECT MIN(lines), MAX(lines)
FROM (SELECT COUNT(*) AS lines
      FROM dbo.PurchaseOrderDetail
      GROUP BY PurchaseOrderID) c
;


-- ---------------------------------------------------------------------------------------------------------- LineNumber
-- nothing keeps a LineNumber unique within an order.
-- no rows: no order repeats one.
SELECT PurchaseOrderID, LineNumber, COUNT(*) AS lines
FROM dbo.PurchaseOrderDetail
GROUP BY PurchaseOrderID, LineNumber
HAVING COUNT(*) > 1
;

-- an order whose LineNumbers do not run 1, 2, ... without a gap.
-- no rows: every order numbers its lines from 1.
SELECT PurchaseOrderID, MIN(LineNumber), MAX(LineNumber), COUNT(*) AS lines
FROM dbo.PurchaseOrderDetail
GROUP BY PurchaseOrderID
HAVING MIN(LineNumber) <> 1
    OR MAX(LineNumber) <> COUNT(*)
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- the types of the items bought; only raw materials (1), all 10 lines.
SELECT i.ItemTypeID, COUNT(*) AS lines
FROM dbo.PurchaseOrderDetail d
         JOIN dbo.Items i ON i.ItemID = d.ItemID
GROUP BY i.ItemTypeID
;

-- nothing keeps an item to one line per order.
-- no rows: no order buys an item twice.
SELECT PurchaseOrderID, ItemID, COUNT(*) AS lines
FROM dbo.PurchaseOrderDetail
GROUP BY PurchaseOrderID, ItemID
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ Quantity
-- the smallest and the largest Quantity, and how many are zero or less; 50.00, 500.00 and none.
SELECT MIN(Quantity), MAX(Quantity), SUM(CASE WHEN Quantity <= 0 THEN 1 ELSE 0 END)
FROM dbo.PurchaseOrderDetail
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- the smallest and the largest UnitPrice; 2.5000 and 14.2500.
SELECT MIN(UnitPrice), MAX(UnitPrice)
FROM dbo.PurchaseOrderDetail
;


-- ---------------------------------------------------------------------------------------------------- QuantityReceived
-- how many QuantityReceived values are NULL, and how many differ from the Quantity; none, and none: every line was
-- received in full.
SELECT SUM(CASE WHEN QuantityReceived IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN QuantityReceived <> Quantity THEN 1 ELSE 0 END)
FROM dbo.PurchaseOrderDetail
;


-- ----------------------------------------------------------------------------------------------------------- LineTotal
-- LineTotal is computed, the Quantity at the UnitPrice.
-- no rows: every line computes it.
SELECT PODetailID, Quantity, UnitPrice, LineTotal
FROM dbo.PurchaseOrderDetail
WHERE LineTotal IS NULL
   OR LineTotal <> Quantity * UnitPrice
;

-- the smallest and the largest LineTotal, and their sum; 500.000000, 2400.000000 and 10395.000000.
SELECT MIN(LineTotal), MAX(LineTotal), SUM(LineTotal)
FROM dbo.PurchaseOrderDetail
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a received line no quality inspection refers to, by the PONumber and the item; the second line of each of the 5
-- orders, only the first was inspected.
SELECT d.PODetailID, po.PONumber, d.ItemID
FROM dbo.PurchaseOrderDetail d
         JOIN dbo.PurchaseOrder po ON po.PurchaseOrderID = d.PurchaseOrderID
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.QualityInspection q
                  WHERE q.ReferenceType = 'PO'
                    AND q.ReferenceNumber = po.PONumber
                    AND q.ItemID = d.ItemID)
ORDER BY d.PODetailID ASC
;

