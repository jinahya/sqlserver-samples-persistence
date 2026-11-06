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
-- Exercises at the Basic level on the Sales schema of WideWorldImporters, and on the Website view over it.
--
-- These are the SQL solutions of the 'Sales' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions. Each problem below repeats the request it answers.
--
-- A parameter is a local variable, DECLAREd right above the query that reads it; run the two together.
--


-- ------------------------------------------------------------------------------------------------------ WWI_SALES_B_01
-- Count the customers of every buying group, a group without customers included, with the customers in no group as a
-- group of their own, NULL; the most first, then by name.
SELECT bg.BuyingGroupName, COUNT(c.CustomerID) AS Customers
FROM Sales.BuyingGroups bg
         LEFT JOIN Sales.Customers c ON c.BuyingGroupID = bg.BuyingGroupID
GROUP BY bg.BuyingGroupID, bg.BuyingGroupName
UNION ALL
SELECT CAST(NULL AS nvarchar(50)), COUNT(*)
FROM Sales.Customers
WHERE BuyingGroupID IS NULL
ORDER BY Customers DESC, BuyingGroupName
;

-- ------------------------------------------------------------------------------------------------------ WWI_SALES_B_02
-- Accounts receivable wants the customers who are billed for others: each bill-to customer, with the number of other
-- customers it is billed for, the most first; a tie by the identifier.
SELECT b.CustomerID, b.CustomerName, COUNT(*) AS Billed
FROM Sales.Customers c
         JOIN Sales.Customers b ON b.CustomerID = c.BillToCustomerID
WHERE c.BillToCustomerID <> c.CustomerID
GROUP BY b.CustomerID, b.CustomerName
ORDER BY Billed DESC, b.CustomerID
;

-- ------------------------------------------------------------------------------------------------------ WWI_SALES_B_03
-- The web team lists, from the Website.Customers view, the first five customers by name who belong to no buying
-- group, with their category, city and delivery method.
SELECT TOP(5) CustomerID, CustomerName, CustomerCategoryName, CityName, DeliveryMethod
FROM Website.Customers
WHERE BuyingGroupName IS NULL
ORDER BY CustomerName
;

