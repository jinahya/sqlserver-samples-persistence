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
---
--
-- Exercises at the Basic level on the Sales schema, a problem per query; some read the other schemas too.
--
-- These are the SQL solutions of the 'Sales' section of EXERCISES.asciidoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are T-SQL variables (@name), declared with the value the book runs; each problem is a batch of its own,
-- ended by GO, so sqlcmd and the IDEs run them one by one.
--


-- ------------------------------------------------------------------------------------------------------- AW_SALES_B_01
-- A customer calls with an order number. Show the order, with its amounts.
DECLARE
@salesOrderNumber nvarchar(25) = N'SO43659';
SELECT h.SalesOrderID, h.SalesOrderNumber, h.SubTotal, h.TaxAmt, h.Freight, h.TotalDue
FROM Sales.SalesOrderHeader h
WHERE h.SalesOrderNumber = @salesOrderNumber
;
GO

-- ------------------------------------------------------------------------------------------------------- AW_SALES_B_02
-- Sales counts its customers. Show how many customers there are, how many of them are persons buying for themselves,
-- how many are stores, and how many are persons buying for a store; the three kinds add up to the total.
SELECT COUNT(*)                                                                         AS Customers,
       SUM(CASE WHEN c.PersonID IS NOT NULL AND c.StoreID IS NULL THEN 1 ELSE 0 END)     AS Persons,
       SUM(CASE WHEN c.PersonID IS NULL AND c.StoreID IS NOT NULL THEN 1 ELSE 0 END)     AS Stores,
       SUM(CASE WHEN c.PersonID IS NOT NULL AND c.StoreID IS NOT NULL THEN 1 ELSE 0 END) AS StoreContacts
FROM Sales.Customer c
;
GO


-- ------------------------------------------------------------------------------------------------------- AW_SALES_B_03
-- Marketing asks why customers buy. Show, for each sales reason and its type, how many orders gave it, the most first,
-- a reason no order gave included.
SELECT r.ReasonType, r.Name, COUNT(hr.SalesOrderID) AS Orders
FROM Sales.SalesReason r
         LEFT JOIN Sales.SalesOrderHeaderSalesReason hr ON hr.SalesReasonID = r.SalesReasonID
GROUP BY r.SalesReasonID, r.ReasonType, r.Name
ORDER BY Orders DESC, r.Name
;
GO

