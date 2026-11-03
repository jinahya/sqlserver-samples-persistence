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
-- Profiling queries on Sales.SalesOrderHeaderSalesReason, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SalesOrderHeaderSalesReason.sql
--


-- -------------------------------------------------------------------------------------------------------- SalesOrderID
-- how many rows, orders and reasons; 27647 rows of 23012 orders and 7 reasons.
SELECT COUNT(*), COUNT(DISTINCT SalesOrderID), COUNT(DISTINCT SalesReasonID)
FROM Sales.SalesOrderHeaderSalesReason
;

-- how many reasons an order gives; 1 for 18530 orders, 2 for 4329 and 3 for 153.
SELECT reasons, COUNT(*) AS orders
FROM (SELECT SalesOrderID, COUNT(*) AS reasons
      FROM Sales.SalesOrderHeaderSalesReason
      GROUP BY SalesOrderID) t
GROUP BY reasons
ORDER BY reasons ASC
;

-- a reason of an order not online.
-- no rows: only the online orders give reasons.
SELECT r.SalesOrderID, r.SalesReasonID
FROM Sales.SalesOrderHeaderSalesReason r
         JOIN Sales.SalesOrderHeader h ON h.SalesOrderID = r.SalesOrderID
WHERE h.OnlineOrderFlag = 0
;


-- ------------------------------------------------------------------------------------------------------- SalesReasonID
-- how many orders give each SalesReasonID; 7 reasons, 1 (Price) the most with 17473, 4 the least with 722.
SELECT SalesReasonID, COUNT(*) AS orders
FROM Sales.SalesOrderHeaderSalesReason
GROUP BY SalesReasonID
ORDER BY SalesReasonID ASC
;

-- how many orders give reason 2, On Promotion, with no line on an offer; 2238 of the 3515.
SELECT COUNT(*) AS orders
FROM Sales.SalesOrderHeaderSalesReason r
WHERE r.SalesReasonID = 2
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderDetail d
                  WHERE d.SalesOrderID = r.SalesOrderID
                    AND d.SpecialOfferID <> 1)
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- a ModifiedDate which is not the OrderDate of its order.
-- no rows: every reason was last modified on the day of its order.
SELECT r.SalesOrderID, r.SalesReasonID, h.OrderDate, r.ModifiedDate
FROM Sales.SalesOrderHeaderSalesReason r
         JOIN Sales.SalesOrderHeader h ON h.SalesOrderID = r.SalesOrderID
WHERE r.ModifiedDate <> h.OrderDate
;

