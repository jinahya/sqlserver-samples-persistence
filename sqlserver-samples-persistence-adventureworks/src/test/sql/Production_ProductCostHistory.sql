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
-- Profiling queries on Production.ProductCostHistory, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductCostHistory.sql
--


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- how many rows, and how many products they cover; 395 rows, on 293 products, 707 to 999.
SELECT COUNT(*), COUNT(DISTINCT ProductID), MIN(ProductID), MAX(ProductID)
FROM Production.ProductCostHistory
;

-- the products by how many periods they have; 216 with 1 period, 52 with 2, 25 with 3.
SELECT x.periods, COUNT(*) AS products
FROM (SELECT ProductID, COUNT(*) AS periods
      FROM Production.ProductCostHistory
      GROUP BY ProductID) x
GROUP BY x.periods
ORDER BY x.periods ASC
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- the start dates, and the rows of each; 2022-05-30 for 72, 2023-05-30 for 128, 2024-05-29 for 195.
SELECT StartDate, COUNT(*) AS periods
FROM Production.ProductCostHistory
GROUP BY StartDate
ORDER BY StartDate ASC
;

-- a StartDate with a time of day.
-- no rows: every StartDate is midnight.
SELECT ProductID, StartDate
FROM Production.ProductCostHistory
WHERE StartDate <> CAST(CAST(StartDate AS date) AS datetime)
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- the end dates, and the rows of each, NULL for the current periods; 195 open, 72 ending 2023-05-29 and 128 ending
-- 2024-05-28.
SELECT EndDate, COUNT(*) AS periods
FROM Production.ProductCostHistory
GROUP BY EndDate
ORDER BY EndDate ASC
;

-- a product with more than one current period.
-- no rows: at most one period of a product has no EndDate.
SELECT ProductID, COUNT(*) AS current_
FROM Production.ProductCostHistory
WHERE EndDate IS NULL
GROUP BY ProductID
HAVING COUNT(*) > 1
;

-- a product whose periods end otherwise than its sale does: an open period with a SellEndDate, or a last EndDate
-- other than the SellEndDate.
-- no rows: the 195 products on sale have an open period, and the 98 off sale end on their SellEndDate.
SELECT x.ProductID, x.lastEnd, p.SellEndDate
FROM (SELECT ProductID, MAX(EndDate) AS lastEnd, COUNT(*) - COUNT(EndDate) AS open_
      FROM Production.ProductCostHistory
      GROUP BY ProductID) x
         JOIN Production.Product p ON p.ProductID = x.ProductID
WHERE (x.open_ = 1 AND p.SellEndDate IS NOT NULL)
   OR (x.open_ = 0 AND (p.SellEndDate IS NULL OR x.lastEnd <> p.SellEndDate))
;

-- a period which does not start the day after the previous one ends, or overlaps it.
-- no rows: each period starts the day after the previous one ends.
SELECT a.ProductID, a.EndDate, b.StartDate
FROM Production.ProductCostHistory a
         JOIN Production.ProductCostHistory b ON b.ProductID = a.ProductID
WHERE b.StartDate = (SELECT MIN(c.StartDate)
                     FROM Production.ProductCostHistory c
                     WHERE c.ProductID = a.ProductID
                       AND c.StartDate > a.StartDate)
  AND DATEADD(DAY, 1, a.EndDate) <> b.StartDate
ORDER BY a.ProductID ASC
;


-- -------------------------------------------------------------------------------------------------------- StandardCost
-- the smallest and the largest StandardCost, which CK_ProductCostHistory_StandardCost keeps at least 0; 0.8565 and
-- 2171.2942.
SELECT MIN(StandardCost), MAX(StandardCost)
FROM Production.ProductCostHistory
;

-- a current StandardCost which is not the product's StandardCost.
-- no rows: the open period carries the product's StandardCost.
SELECT h.ProductID, h.StandardCost, p.StandardCost
FROM Production.ProductCostHistory h
         JOIN Production.Product p ON p.ProductID = h.ProductID
WHERE h.EndDate IS NULL
  AND h.StandardCost <> p.StandardCost
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2023-05-29 00:00:00.000 to 2024-05-28 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductCostHistory
;

