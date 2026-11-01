-- -
-- #%L
-- mssql-sample-databases-persistence-adventureworks
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
-- Profiling queries on Sales.SalesOrderHeader, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SalesOrderHeader.sql
--


-- -------------------------------------------------------------------------------------------------------- SalesOrderID
-- how many rows, and the smallest and the largest SalesOrderID; 31465 orders, 43659 to 75123.
SELECT COUNT(*), MIN(SalesOrderID), MAX(SalesOrderID)
FROM Sales.SalesOrderHeader
;

-- a SalesOrderID whose next value is missing, below the largest.
-- no rows: SalesOrderID runs from 43659 without a gap.
SELECT a.SalesOrderID + 1 AS missing_from
FROM Sales.SalesOrderHeader a
WHERE a.SalesOrderID < (SELECT MAX(SalesOrderID) FROM Sales.SalesOrderHeader)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderHeader b
                  WHERE b.SalesOrderID = a.SalesOrderID + 1)
;


-- ------------------------------------------------------------------------------------------------------ RevisionNumber
-- how many orders each RevisionNumber has, online or not; 10 for all but 30 orders not online, which are of 11.
SELECT RevisionNumber, OnlineOrderFlag, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
GROUP BY RevisionNumber, OnlineOrderFlag
ORDER BY RevisionNumber ASC, OnlineOrderFlag ASC
;


-- ----------------------------------------------------------------------------------------------------------- OrderDate
-- the range of OrderDate, how many days it has, and how many carry a time of day; 2022-05-30 to 2025-06-29, on 1124
-- days, all midnights.
SELECT MIN(OrderDate), MAX(OrderDate), COUNT(DISTINCT OrderDate) AS days,
       SUM(CASE WHEN CAST(OrderDate AS time) <> '00:00' THEN 1 ELSE 0 END) AS timed
FROM Sales.SalesOrderHeader
;


-- ------------------------------------------------------------------------------------------------------------- DueDate
-- how many days after the order each is due; 12 days for all but 9 orders, of 13.
SELECT DATEDIFF(day, OrderDate, DueDate) AS days, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
GROUP BY DATEDIFF(day, OrderDate, DueDate)
ORDER BY days ASC
;


-- ------------------------------------------------------------------------------------------------------------ ShipDate
-- ShipDate is nullable; how many orders have none, and how many days after the order the others shipped. None; 7 days
-- for all but 9 orders, of 8.
SELECT DATEDIFF(day, OrderDate, ShipDate) AS days, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
GROUP BY DATEDIFF(day, OrderDate, ShipDate)
ORDER BY days ASC
;

-- an order which shipped after it was due.
-- no rows: every order shipped 5 days before its DueDate.
SELECT SalesOrderID, OrderDate, DueDate, ShipDate
FROM Sales.SalesOrderHeader
WHERE ShipDate > DueDate
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- how many orders each Status has, of the 0 to 8 CK_SalesOrderHeader_Status allows; all 31465 of 5, shipped.
SELECT Status, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
GROUP BY Status
ORDER BY Status ASC
;


-- ----------------------------------------------------------------------------------------------------- OnlineOrderFlag
-- how many orders each OnlineOrderFlag has, and how many of them have a sales person, a purchase order number, a
-- card and a rate; 27659 online orders with no sales person and no purchase order number, 3806 others with both.
SELECT OnlineOrderFlag, COUNT(*) AS orders, COUNT(SalesPersonID) AS sales_persons,
       COUNT(PurchaseOrderNumber) AS purchase_orders, COUNT(CreditCardID) AS cards, COUNT(CurrencyRateID) AS rates
FROM Sales.SalesOrderHeader
GROUP BY OnlineOrderFlag
ORDER BY OnlineOrderFlag ASC
;

-- an online order of a store, or an order not online of a person with no store.
-- no rows: the online orders are of individuals, the others of stores.
SELECT h.SalesOrderID, h.OnlineOrderFlag, c.PersonID, c.StoreID
FROM Sales.SalesOrderHeader h
         JOIN Sales.Customer c ON c.CustomerID = h.CustomerID
WHERE (h.OnlineOrderFlag = 1 AND c.StoreID IS NOT NULL)
   OR (h.OnlineOrderFlag = 0 AND c.StoreID IS NULL)
;


-- ---------------------------------------------------------------------------------------------------- SalesOrderNumber
-- SalesOrderNumber is computed, 'SO' and SalesOrderID; one which is not.
-- no rows: every SalesOrderNumber follows its SalesOrderID.
SELECT SalesOrderID, SalesOrderNumber
FROM Sales.SalesOrderHeader
WHERE SalesOrderNumber <> N'SO' + CAST(SalesOrderID AS nvarchar(10))
;


-- ------------------------------------------------------------------------------------------------- PurchaseOrderNumber
-- the shortest and the longest PurchaseOrderNumber; 10 and 13 characters, of the 25 the column allows.
SELECT MIN(LEN(PurchaseOrderNumber)), MAX(LEN(PurchaseOrderNumber))
FROM Sales.SalesOrderHeader
;

-- a PurchaseOrderNumber which is not 'PO' and digits.
-- no rows: every PurchaseOrderNumber is 'PO' and digits.
SELECT SalesOrderID, PurchaseOrderNumber
FROM Sales.SalesOrderHeader
WHERE PurchaseOrderNumber NOT LIKE 'PO%'
   OR SUBSTRING(PurchaseOrderNumber, 3, 23) LIKE '%[^0-9]%'
;

-- the schema has no unique key on PurchaseOrderNumber; one more than one order has.
-- no rows: every PurchaseOrderNumber is unique.
SELECT PurchaseOrderNumber, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
WHERE PurchaseOrderNumber IS NOT NULL
GROUP BY PurchaseOrderNumber
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------- AccountNumber
-- AccountNumber is nullable; how many orders have none, and how long the others are. None; 14 characters each.
SELECT COUNT(*) - COUNT(AccountNumber) AS without, MIN(LEN(AccountNumber)), MAX(LEN(AccountNumber))
FROM Sales.SalesOrderHeader
;

-- the forms of AccountNumber, which is not the customer's; 10-4020-nnnnnn for the 3806 orders not online, and
-- 10-4030-nnnnnn for the 27659 online.
SELECT LEFT(AccountNumber, 8) AS prefix, OnlineOrderFlag, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
WHERE AccountNumber LIKE '10-40[0-9]0-[0-9][0-9][0-9][0-9][0-9][0-9]'
GROUP BY LEFT(AccountNumber, 8), OnlineOrderFlag
ORDER BY prefix ASC
;

-- a customer whose orders carry more than one AccountNumber, or an AccountNumber of more than one customer.
-- no rows: AccountNumber pairs one to one with CustomerID.
SELECT CustomerID, MIN(AccountNumber), MAX(AccountNumber)
FROM Sales.SalesOrderHeader
GROUP BY CustomerID
HAVING COUNT(DISTINCT AccountNumber) > 1
UNION ALL
SELECT MIN(CustomerID), AccountNumber, AccountNumber
FROM Sales.SalesOrderHeader
GROUP BY AccountNumber
HAVING COUNT(DISTINCT CustomerID) > 1
;


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- how many customers ordered, and the most orders of one; 19119 customers, up to 28 orders.
SELECT COUNT(*) AS customers, MAX(orders) AS most
FROM (SELECT CustomerID, COUNT(*) AS orders
      FROM Sales.SalesOrderHeader
      GROUP BY CustomerID) t
;


-- ------------------------------------------------------------------------------------------------------- SalesPersonID
-- how many orders have a sales person, and how many sales persons; 3806 orders of 17.
SELECT COUNT(SalesPersonID) AS orders, COUNT(DISTINCT SalesPersonID) AS sales_persons
FROM Sales.SalesOrderHeader
;

-- how many orders are in another territory than their sales person's current one; 1326 orders of 6 sales persons,
-- not counting the 103 orders of the 3 who have no territory now.
SELECT h.SalesPersonID, COUNT(*) AS orders
FROM Sales.SalesOrderHeader h
         JOIN Sales.SalesPerson p ON p.BusinessEntityID = h.SalesPersonID
WHERE h.TerritoryID <> p.TerritoryID
GROUP BY h.SalesPersonID
ORDER BY h.SalesPersonID ASC
;

-- how many orders fall in no SalesTerritoryHistory of their sales person, for their territory on their OrderDate;
-- 676.
SELECT COUNT(*) AS orders
FROM Sales.SalesOrderHeader h
WHERE h.SalesPersonID IS NOT NULL
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesTerritoryHistory t
                  WHERE t.BusinessEntityID = h.SalesPersonID
                    AND t.TerritoryID = h.TerritoryID
                    AND h.OrderDate >= t.StartDate
                    AND (t.EndDate IS NULL OR h.OrderDate <= t.EndDate))
;


-- --------------------------------------------------------------------------------------------------------- TerritoryID
-- how many orders each TerritoryID has; all 10 territories, 352 (2) to 6843 (9), and no NULL.
SELECT TerritoryID, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
GROUP BY TerritoryID
ORDER BY TerritoryID ASC
;

-- an order shipped to an address whose state is in another territory.
-- no rows: every order is in the territory of its ShipToAddressID.
SELECT h.SalesOrderID, h.TerritoryID, s.TerritoryID
FROM Sales.SalesOrderHeader h
         JOIN Person.Address a ON a.AddressID = h.ShipToAddressID
         JOIN Person.StateProvince s ON s.StateProvinceID = a.StateProvinceID
WHERE s.TerritoryID <> h.TerritoryID
;


-- ----------------------------------------------------------------------------------------------------- BillToAddressID
-- a BillToAddressID which refers to no address; the foreign key forbids it.
-- no rows: every BillToAddressID refers to a Person.Address.
SELECT h.SalesOrderID, h.BillToAddressID
FROM Sales.SalesOrderHeader h
WHERE NOT EXISTS (SELECT 1
                  FROM Person.Address a
                  WHERE a.AddressID = h.BillToAddressID)
;


-- ----------------------------------------------------------------------------------------------------- ShipToAddressID
-- how many orders ship to another address than they bill to, online or not; 99, 69 not online and 30 online.
SELECT OnlineOrderFlag, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
WHERE BillToAddressID <> ShipToAddressID
GROUP BY OnlineOrderFlag
ORDER BY OnlineOrderFlag ASC
;


-- -------------------------------------------------------------------------------------------------------- ShipMethodID
-- how many orders each ShipMethodID has, online or not; the online ones by 1, the others by 5.
SELECT ShipMethodID, OnlineOrderFlag, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
GROUP BY ShipMethodID, OnlineOrderFlag
ORDER BY ShipMethodID ASC
;


-- -------------------------------------------------------------------------------------------------------- CreditCardID
-- how many orders have a card; 30334 of the 31465.
SELECT COUNT(CreditCardID) AS orders, COUNT(*) AS of_orders
FROM Sales.SalesOrderHeader
;


-- ---------------------------------------------------------------------------------------------- CreditCardApprovalCode
-- an order which has a card and no approval code, or an approval code and no card.
-- no rows: the two come together.
SELECT SalesOrderID, CreditCardID, CreditCardApprovalCode
FROM Sales.SalesOrderHeader
WHERE (CreditCardID IS NULL AND CreditCardApprovalCode IS NOT NULL)
   OR (CreditCardID IS NOT NULL AND CreditCardApprovalCode IS NULL)
;

-- the shortest and the longest CreditCardApprovalCode; 9 and 15 characters, of the 15 the column allows.
SELECT MIN(LEN(CreditCardApprovalCode)), MAX(LEN(CreditCardApprovalCode))
FROM Sales.SalesOrderHeader
;

-- a CreditCardApprovalCode which is not digits, 'Vi' and digits.
-- no rows: every code is of that form.
SELECT SalesOrderID, CreditCardApprovalCode
FROM Sales.SalesOrderHeader
WHERE CreditCardApprovalCode NOT LIKE '[0-9]%Vi[0-9]%'
   OR REPLACE(CreditCardApprovalCode, 'Vi', '') LIKE '%[^0-9]%'
;

-- the schema has no unique key on CreditCardApprovalCode; one more than one order has.
-- no rows: every code is unique.
SELECT CreditCardApprovalCode, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
WHERE CreditCardApprovalCode IS NOT NULL
GROUP BY CreditCardApprovalCode
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------ CurrencyRateID
-- the currencies the orders of each country are in; 12020 in the US and others with no rate, and 43 in a currency
-- the country does not use, 22 in GBP in FR and 21 in CAD in the US.
SELECT t.CountryRegionCode, r.ToCurrencyCode, COUNT(*) AS orders
FROM Sales.SalesOrderHeader h
         JOIN Sales.SalesTerritory t ON t.TerritoryID = h.TerritoryID
         LEFT JOIN Sales.CurrencyRate r ON r.CurrencyRateID = h.CurrencyRateID
GROUP BY t.CountryRegionCode, r.ToCurrencyCode
ORDER BY t.CountryRegionCode ASC, r.ToCurrencyCode ASC
;


-- ------------------------------------------------------------------------------------------------------------ SubTotal
-- the range of SubTotal; 1.374 to 163930.3943.
SELECT MIN(SubTotal), MAX(SubTotal)
FROM Sales.SalesOrderHeader
;

-- how far SubTotal is from the sum of its LineTotals; 695 orders differ, by at most .00005, LineTotal having six
-- decimals and money four.
SELECT SUM(CASE WHEN h.SubTotal <> d.total THEN 1 ELSE 0 END) AS orders, MAX(ABS(h.SubTotal - d.total)) AS largest
FROM Sales.SalesOrderHeader h
         JOIN (SELECT SalesOrderID, SUM(LineTotal) AS total
               FROM Sales.SalesOrderDetail
               GROUP BY SalesOrderID) d ON d.SalesOrderID = h.SalesOrderID
;


-- -------------------------------------------------------------------------------------------------------------- TaxAmt
-- the range of TaxAmt, and of its share of SubTotal; .1099 to 17948.5186, .0799 to .2880 of SubTotal.
SELECT MIN(TaxAmt), MAX(TaxAmt), MIN(TaxAmt / SubTotal), MAX(TaxAmt / SubTotal)
FROM Sales.SalesOrderHeader
;


-- ------------------------------------------------------------------------------------------------------------- Freight
-- the range of Freight; .0344 to 5608.9121.
SELECT MIN(Freight), MAX(Freight)
FROM Sales.SalesOrderHeader
;

-- a Freight which is not TaxAmt divided by 3.2, to the cent.
-- no rows: the installed data derives Freight from TaxAmt.
SELECT SalesOrderID, TaxAmt, Freight
FROM Sales.SalesOrderHeader
WHERE ABS(Freight - TaxAmt / 3.2) >= 0.01
;


-- ------------------------------------------------------------------------------------------------------------ TotalDue
-- TotalDue is computed, SubTotal, TaxAmt and Freight; one which is not their sum.
-- no rows: every TotalDue is the sum.
SELECT SalesOrderID, SubTotal, TaxAmt, Freight, TotalDue
FROM Sales.SalesOrderHeader
WHERE TotalDue <> SubTotal + TaxAmt + Freight
;


-- ------------------------------------------------------------------------------------------------------------- Comment
-- Comment is nullable; how many orders have one. None.
SELECT COUNT(Comment) AS orders
FROM Sales.SalesOrderHeader
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_SalesOrderHeader_rowguid keeps rowguid unique.
-- no rows: no two orders share a rowguid.
SELECT rowguid, COUNT(*) AS orders
FROM Sales.SalesOrderHeader
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- a ModifiedDate which is not the ShipDate.
-- no rows: every order was last modified on the day it shipped.
SELECT SalesOrderID, ShipDate, ModifiedDate
FROM Sales.SalesOrderHeader
WHERE ModifiedDate <> ShipDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an order with no line.
-- no rows: every order has at least one SalesOrderDetail.
SELECT h.SalesOrderID
FROM Sales.SalesOrderHeader h
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderDetail d
                  WHERE d.SalesOrderID = h.SalesOrderID)
;

-- how many orders have no sales reason, online or not; 8453, all the 3806 not online and 4647 online.
SELECT h.OnlineOrderFlag, COUNT(*) AS orders
FROM Sales.SalesOrderHeader h
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SalesOrderHeaderSalesReason r
                  WHERE r.SalesOrderID = h.SalesOrderID)
GROUP BY h.OnlineOrderFlag
ORDER BY h.OnlineOrderFlag ASC
;

