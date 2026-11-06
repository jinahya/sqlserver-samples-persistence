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
---
--
-- Exercises at the Advanced level on the Sales schema of WideWorldImporters, and on the Website view over it.
--
-- These are the SQL solutions of the 'Sales' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- ------------------------------------------------------------------------------------------------------ WWI_SALES_A_01
-- The sales manager ranks the salespeople of each year by the invoiced extended price and wants the top three places of
-- each year, all of those tied for a place included, ordered by `PersonID` within a place. `RANK() OVER (...)`, a
-- window function, is SQL only; a tie for third can return more than three rows.
WITH yearly AS (SELECT YEAR(i.InvoiceDate) AS SalesYear, i.SalespersonPersonID, SUM(il.ExtendedPrice) AS Revenue
                FROM Sales.Invoices i
                         JOIN Sales.InvoiceLines il ON il.InvoiceID = i.InvoiceID
                GROUP BY YEAR(i.InvoiceDate), i.SalespersonPersonID),
     ranked AS (SELECT y.*, RANK() OVER (PARTITION BY y.SalesYear ORDER BY y.Revenue DESC) AS SalesRank
                FROM yearly y)
SELECT r.SalesYear, r.SalesRank, p.PersonID, p.FullName, r.Revenue
FROM ranked r
         JOIN Application.People p ON p.PersonID = r.SalespersonPersonID
WHERE r.SalesRank <= 3
ORDER BY r.SalesYear, r.SalesRank, p.PersonID
;

-- ------------------------------------------------------------------------------------------------------ WWI_SALES_A_02
-- Accounts receivable wants the customers whose credit limit is not what it was at a past moment, a limit set or
-- removed since then included, with the limit then and now. `NOT EXISTS (SELECT then INTERSECT SELECT now)` compares
-- the two with NULLs as equal, which `<>` does not.
DECLARE @asOf datetime2 = '2015-01-01';
SELECT c.CustomerID, c.CustomerName, h.CreditLimit AS CreditLimitThen, c.CreditLimit AS CreditLimitNow
FROM Sales.Customers c
         JOIN Sales.Customers FOR SYSTEM_TIME AS OF @asOf h ON h.CustomerID = c.CustomerID
WHERE NOT EXISTS (SELECT h.CreditLimit
                  INTERSECT
                  SELECT c.CreditLimit)
ORDER BY c.CustomerID
;

-- ------------------------------------------------------------------------------------------------------ WWI_SALES_A_03
-- The warehouse wants the five oldest backorders which were never invoiced, with the customer and the order which
-- backordered them.
SELECT TOP(5) o.OrderID, o.OrderDate, c.CustomerName, p.OrderID AS BackorderedBy
FROM Sales.Orders p
         JOIN Sales.Orders o ON o.OrderID = p.BackorderOrderID
         JOIN Sales.Customers c ON c.CustomerID = o.CustomerID
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.Invoices i
                  WHERE i.OrderID = o.OrderID)
ORDER BY o.OrderDate, o.OrderID
;

