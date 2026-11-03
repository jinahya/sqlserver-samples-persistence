-- -
-- #%L
-- sqlserver-samples-persistence-futonmanufacturing
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
-- Profiling queries on the view dbo.vw_Sales_CustomerSegmentation, a section per column. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_vw_Sales_CustomerSegmentation.sql
--


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many rows, and the smallest and the largest CustomerID; 7 customers, 1 to 8.
SELECT COUNT(*), MIN(CustomerID), MAX(CustomerID)
FROM dbo.vw_Sales_CustomerSegmentation
;

-- the view groups by customer; a customer makes a row on its own.
-- no rows: no two rows share a CustomerID.
SELECT CustomerID, COUNT(*) AS rows
FROM dbo.vw_Sales_CustomerSegmentation
GROUP BY CustomerID
HAVING COUNT(*) > 1
;

-- the customers with no row. The view filters the LEFT JOINed orders on Status = 'Delivered' in its WHERE, which
-- makes the join an inner one, so a customer with no delivered order drops out.
-- CUST-002, whose single order is Shipped.
SELECT c.CustomerID, c.CustomerCode
FROM dbo.Customer c
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.vw_Sales_CustomerSegmentation v
                  WHERE v.CustomerID = c.CustomerID)
;


-- -------------------------------------------------------------------------------------------------------- CustomerCode
-- a CustomerCode which is not the customer's.
-- no rows: every code agrees.
SELECT v.CustomerID, v.CustomerCode
FROM dbo.vw_Sales_CustomerSegmentation v
         JOIN dbo.Customer c ON c.CustomerID = v.CustomerID
WHERE v.CustomerCode <> c.CustomerCode
;


-- -------------------------------------------------------------------------------------------------------- CustomerName
-- the shortest and the longest CustomerName; 17 and 24 characters, of the 255 the column allows.
SELECT MIN(LEN(CustomerName)), MAX(LEN(CustomerName))
FROM dbo.vw_Sales_CustomerSegmentation
;


-- -------------------------------------------------------------------------------------------------------- CustomerType
-- the customer types and how many customers in each; Online 1, Retail 3, Wholesale 3.
SELECT CustomerType, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerSegmentation
GROUP BY CustomerType
ORDER BY CustomerType ASC
;


-- ------------------------------------------------------------------------------------------------------------- Recency
-- a Recency which is not the days from the customer's last delivered order to today; the value grows with the clock.
-- no rows: every value agrees.
SELECT v.CustomerID, v.Recency
FROM dbo.vw_Sales_CustomerSegmentation v
WHERE v.Recency <> (SELECT DATEDIFF(DAY, MAX(so.OrderDate), GETDATE())
                    FROM dbo.SalesOrder so
                    WHERE so.CustomerID = v.CustomerID
                      AND so.Status = 'Delivered')
;

-- a Recency which more than one customer has; NTILE would split a tie at random.
-- no rows: the last deliveries all fall on different days.
SELECT Recency, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerSegmentation
GROUP BY Recency
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- Frequency
-- the frequencies and how many customers have each; 1 for 5 customers, 2 for 2.
SELECT Frequency, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerSegmentation
GROUP BY Frequency
ORDER BY Frequency ASC
;

-- a Frequency which is not the count of the customer's delivered orders.
-- no rows: every count agrees.
SELECT v.CustomerID, v.Frequency
FROM dbo.vw_Sales_CustomerSegmentation v
WHERE v.Frequency <> (SELECT COUNT(*)
                      FROM dbo.SalesOrder so
                      WHERE so.CustomerID = v.CustomerID
                        AND so.Status = 'Delivered')
;


-- ------------------------------------------------------------------------------------------------------------ Monetary
-- Monetary over the view against NetAmount over the Delivered orders; 28293.01 both.
SELECT SUM(Monetary), (SELECT SUM(NetAmount) FROM dbo.SalesOrder WHERE Status = 'Delivered') AS delivered
FROM dbo.vw_Sales_CustomerSegmentation
;


-- ------------------------------------------------------------------------------------------------------------- R_Score
-- the scores and how many customers in each; NTILE(5) over 7 rows makes tiles of 2, 2, 1, 1 and 1.
SELECT R_Score, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerSegmentation
GROUP BY R_Score
ORDER BY R_Score ASC
;

-- the customer bought from last; the view orders the tiles by Recency ascending, so the most recent buyer scores
-- 1, while CustomerSegment reads 4 and 5 as recent.
-- CUST-001, with an R_Score of 1.
SELECT TOP (1) CustomerCode, R_Score
FROM dbo.vw_Sales_CustomerSegmentation
ORDER BY Recency ASC
;


-- ------------------------------------------------------------------------------------------------------------- F_Score
-- the scores each Frequency gets, and how many customers; the two customers with 2 orders fill the first tile,
-- and the five with 1 order share the rest, 2 to 5. NTILE splits the tie in no fixed order, so which of the five
-- scores what may change from run to run.
SELECT Frequency, MIN(F_Score), MAX(F_Score), COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerSegmentation
GROUP BY Frequency
ORDER BY Frequency ASC
;


-- ------------------------------------------------------------------------------------------------------------- M_Score
-- the customer who spent the most; the tiles go by Monetary descending, so the largest spender scores 1.
-- CUST-004, 8499.68, with an M_Score of 1.
SELECT TOP (1) CustomerCode, Monetary, M_Score
FROM dbo.vw_Sales_CustomerSegmentation
ORDER BY Monetary DESC
;

-- a Monetary which more than one customer has.
-- no rows: every customer spent a different amount.
SELECT Monetary, COUNT(*) AS customers
FROM dbo.vw_Sales_CustomerSegmentation
GROUP BY Monetary
HAVING COUNT(*) > 1
;


-- ----------------------------------------------------------------------------------------------------------- RFM_Score
-- an RFM_Score which is not the mean of the three scores.
-- no rows: the formula holds.
SELECT CustomerID, R_Score, F_Score, M_Score, RFM_Score
FROM dbo.vw_Sales_CustomerSegmentation
WHERE RFM_Score <> (R_Score + F_Score + M_Score) / 3.0
;


-- ----------------------------------------------------------------------------------------------------- CustomerSegment
-- a CustomerSegment which does not follow from the three scores. Which segment a customer falls in moves with the
-- F_Score tie above, so the counts per segment are left out.
-- no rows: every segment agrees with its scores.
SELECT CustomerID, R_Score, F_Score, M_Score, CustomerSegment
FROM dbo.vw_Sales_CustomerSegmentation
WHERE CustomerSegment <> CASE
                             WHEN R_Score >= 4 AND F_Score >= 4 AND M_Score >= 4 THEN 'Champions'
                             WHEN R_Score >= 3 AND F_Score >= 3 AND M_Score >= 3 THEN 'Loyal Customers'
                             WHEN R_Score >= 4 AND F_Score <= 2 THEN 'New Customers'
                             WHEN R_Score <= 2 AND F_Score >= 3 THEN 'At Risk'
                             WHEN R_Score <= 2 AND F_Score <= 2 THEN 'Lost'
                             WHEN M_Score >= 4 THEN 'Big Spenders'
                             ELSE 'Others'
                         END
;


-- --------------------------------------------------------------------------------------------------- RecommendedAction
-- a segment with more than one RecommendedAction; the two CASEs share their conditions.
-- no rows: a segment goes with a single action.
SELECT CustomerSegment, COUNT(DISTINCT RecommendedAction) AS actions
FROM dbo.vw_Sales_CustomerSegmentation
GROUP BY CustomerSegment
HAVING COUNT(DISTINCT RecommendedAction) > 1
;

