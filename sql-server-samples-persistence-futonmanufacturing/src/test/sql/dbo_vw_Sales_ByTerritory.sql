-- -
-- #%L
-- sql-server-samples-persistence-futonmanufacturing
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
-- Profiling queries on the view dbo.vw_Sales_ByTerritory, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_ByTerritory.sql
--


-- ------------------------------------------------------------------------------------------------------- TerritoryCode
-- how many rows, and how many territories; 6 and 6, a row per active territory.
SELECT COUNT(*), COUNT(DISTINCT TerritoryCode)
FROM dbo.vw_Sales_ByTerritory
;

-- an active territory with no row; the view starts from SalesTerritory and joins the rest with LEFT JOINs.
-- no rows: all 6 territories are active, and each has its row.
SELECT st.TerritoryID, st.TerritoryCode
FROM dbo.SalesTerritory st
WHERE st.IsActive = 1
  AND NOT EXISTS (SELECT 1
                  FROM dbo.vw_Sales_ByTerritory v
                  WHERE v.TerritoryCode = st.TerritoryCode)
;


-- ------------------------------------------------------------------------------------------------------- TerritoryName
-- the shortest and the longest TerritoryName; 9 and 19 characters, of the 100 the column allows.
SELECT MIN(LEN(TerritoryName)), MAX(LEN(TerritoryName))
FROM dbo.vw_Sales_ByTerritory
;

-- a TerritoryName which more than one row carries.
-- no rows: every territory has a name of its own.
SELECT TerritoryName, COUNT(*) AS rows
FROM dbo.vw_Sales_ByTerritory
GROUP BY TerritoryName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------------- Region
-- the regions and how many territories each holds; Central 1, East 2, West 3, none NULL.
SELECT Region, COUNT(*) AS territories
FROM dbo.vw_Sales_ByTerritory
GROUP BY Region
ORDER BY Region ASC
;


-- ----------------------------------------------------------------------------------------------------------- SalesReps
-- a territory whose SalesReps is not the count of its reps.
-- no rows: NW-01 has 3 reps, every other territory 1.
SELECT v.TerritoryCode, v.SalesReps
FROM dbo.vw_Sales_ByTerritory v
         JOIN dbo.SalesTerritory st ON st.TerritoryCode = v.TerritoryCode
WHERE v.SalesReps <> (SELECT COUNT(*)
                      FROM dbo.SalesRep sr
                      WHERE sr.TerritoryID = st.TerritoryID)
;


-- ------------------------------------------------------------------------------------------------------ TotalCustomers
-- the territories with customers; CA-01 with 2 and NW-01 with 6, every customer of the 8. The other 4 have none.
SELECT TerritoryCode, TotalCustomers
FROM dbo.vw_Sales_ByTerritory
WHERE TotalCustomers > 0
ORDER BY TerritoryCode ASC
;


-- --------------------------------------------------------------------------------------------------------- TotalOrders
-- a territory whose TotalOrders is not the count of its customers' orders.
-- no rows: CA-01 has 4 orders, NW-01 11, the rest 0.
SELECT v.TerritoryCode, v.TotalOrders
FROM dbo.vw_Sales_ByTerritory v
         JOIN dbo.SalesTerritory st ON st.TerritoryCode = v.TerritoryCode
WHERE v.TotalOrders <> (SELECT COUNT(*)
                        FROM dbo.SalesOrder so
                                 JOIN dbo.Customer c ON c.CustomerID = so.CustomerID
                        WHERE c.TerritoryID = st.TerritoryID)
;


-- ---------------------------------------------------------------------------------------------------------- GrossSales
-- the territories without an order have a NULL GrossSales, not 0; 4 of them.
SELECT COUNT(*) - COUNT(GrossSales)
FROM dbo.vw_Sales_ByTerritory
;

-- a territory whose GrossSales is not the Subtotal of its customers' orders. The view joins the reps and the
-- customers side by side, so each order repeats once per rep of its territory.
-- NW-01, 155693.58 against 51897.86: three reps, three times the sales. CA-01, with one rep, agrees.
SELECT v.TerritoryCode, v.SalesReps, v.GrossSales, o.subtotal
FROM dbo.vw_Sales_ByTerritory v
         JOIN dbo.SalesTerritory st ON st.TerritoryCode = v.TerritoryCode
         JOIN (SELECT c.TerritoryID, SUM(so.Subtotal) AS subtotal
               FROM dbo.SalesOrder so
                        JOIN dbo.Customer c ON c.CustomerID = so.CustomerID
               GROUP BY c.TerritoryID) o ON o.TerritoryID = st.TerritoryID
WHERE v.GrossSales <> o.subtotal
;


-- ----------------------------------------------------------------------------------------------------------- Discounts
-- a territory whose Discounts is not the DiscountAmount of its customers' orders; the same repetition.
-- NW-01, 14354.88 against 4784.96.
SELECT v.TerritoryCode, v.SalesReps, v.Discounts, o.discounts
FROM dbo.vw_Sales_ByTerritory v
         JOIN dbo.SalesTerritory st ON st.TerritoryCode = v.TerritoryCode
         JOIN (SELECT c.TerritoryID, SUM(so.DiscountAmount) AS discounts
               FROM dbo.SalesOrder so
                        JOIN dbo.Customer c ON c.CustomerID = so.CustomerID
               GROUP BY c.TerritoryID) o ON o.TerritoryID = st.TerritoryID
WHERE v.Discounts <> o.discounts
;


-- ------------------------------------------------------------------------------------------------------------ NetSales
-- a territory whose NetSales is not the NetAmount of its customers' orders; the same repetition.
-- NW-01, 136172.82 against 45390.94.
SELECT v.TerritoryCode, v.SalesReps, v.NetSales, o.net
FROM dbo.vw_Sales_ByTerritory v
         JOIN dbo.SalesTerritory st ON st.TerritoryCode = v.TerritoryCode
         JOIN (SELECT c.TerritoryID, SUM(so.NetAmount) AS net
               FROM dbo.SalesOrder so
                        JOIN dbo.Customer c ON c.CustomerID = so.CustomerID
               GROUP BY c.TerritoryID) o ON o.TerritoryID = st.TerritoryID
WHERE v.NetSales <> o.net
;


-- ------------------------------------------------------------------------------------------------------- AvgOrderValue
-- an AvgOrderValue which is not the real NetSales over TotalOrders; repeating every order alike leaves the average
-- as it is.
-- no rows: CA-01 2130.22, NW-01 4126.44, both right.
SELECT v.TerritoryCode, v.AvgOrderValue, v.NetSales / v.SalesReps / v.TotalOrders AS average
FROM dbo.vw_Sales_ByTerritory v
WHERE ABS(v.AvgOrderValue - v.NetSales / v.SalesReps / v.TotalOrders) >= 0.01
;


-- --------------------------------------------------------------------------------------------------------- SalesPerRep
-- a SalesPerRep which is not NetSales over SalesReps.
-- no rows: the formula holds. The repetition cancels here, so NW-01's 45390.94 is in fact the whole territory's.
SELECT TerritoryCode, NetSales, SalesReps, SalesPerRep
FROM dbo.vw_Sales_ByTerritory
WHERE ABS(SalesPerRep - NetSales / SalesReps) >= 0.01
;


-- ---------------------------------------------------------------------------------------------------- SalesPerCustomer
-- a SalesPerCustomer which is not NetSales over TotalCustomers.
-- no rows: the formula holds, NW-01's 22695.47 three times what its customers bought on average.
SELECT TerritoryCode, NetSales, TotalCustomers, SalesPerCustomer
FROM dbo.vw_Sales_ByTerritory
WHERE ABS(SalesPerCustomer - NetSales / TotalCustomers) >= 0.01
;


-- ---------------------------------------------------------------------------------------------------------- TopProduct
-- the territories without an order have no TopProduct; 4 NULLs.
SELECT COUNT(*) - COUNT(TopProduct)
FROM dbo.vw_Sales_ByTerritory
;

-- the items tied for the most units in each territory; TOP 1 picks one of a tie with no rule to settle it.
-- CA-01 has a tie at 4 units, Full Deluxe Futon - Microfiber Black and Queen Premium Futon - Velvet Emerald, so
-- its TopProduct may be either. NW-01's Full Economy Futon - Navy Canvas leads alone with 57.
SELECT st.TerritoryCode, i.ItemName, SUM(sod.Quantity) AS units
FROM dbo.SalesOrder so
         JOIN dbo.SalesOrderDetail sod ON sod.SalesOrderID = so.SalesOrderID
         JOIN dbo.Items i ON i.ItemID = sod.ItemID
         JOIN dbo.Customer c ON c.CustomerID = so.CustomerID
         JOIN dbo.SalesTerritory st ON st.TerritoryID = c.TerritoryID
GROUP BY st.TerritoryID, st.TerritoryCode, i.ItemName
HAVING SUM(sod.Quantity) = (SELECT MAX(t.units)
                            FROM (SELECT SUM(sod2.Quantity) AS units
                                  FROM dbo.SalesOrder so2
                                           JOIN dbo.SalesOrderDetail sod2 ON sod2.SalesOrderID = so2.SalesOrderID
                                           JOIN dbo.Customer c2 ON c2.CustomerID = so2.CustomerID
                                  WHERE c2.TerritoryID = st.TerritoryID
                                  GROUP BY sod2.ItemID) t)
ORDER BY st.TerritoryCode ASC, i.ItemName ASC
;

