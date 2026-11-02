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
-- Profiling queries on Purchasing.PurchaseOrderHeader, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Purchasing_PurchaseOrderHeader.sql
--


-- ----------------------------------------------------------------------------------------------------- PurchaseOrderID
-- how many rows, and the smallest and the largest PurchaseOrderID; 4012 orders, 1 to 4012.
SELECT COUNT(*), MIN(PurchaseOrderID), MAX(PurchaseOrderID)
FROM Purchasing.PurchaseOrderHeader
;


-- ------------------------------------------------------------------------------------------------------ RevisionNumber
-- how many distinct RevisionNumber, the smallest, the largest and the most common; 10 values, 5 to 21; 5 on 3813
-- orders.
SELECT TOP (1) COUNT(*) OVER () AS distinct_values, MIN(RevisionNumber) OVER () AS smallest,
       MAX(RevisionNumber) OVER () AS largest, RevisionNumber AS most_common, COUNT(*) AS orders
FROM Purchasing.PurchaseOrderHeader
GROUP BY RevisionNumber
ORDER BY orders DESC, RevisionNumber ASC
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- the statuses; CK_PurchaseOrderHeader_Status allows 1 (pending), 2 (approved), 3 (rejected) and 4 (complete); 1 225, 2
-- 12, 3 86 and 4 3689.
SELECT Status, COUNT(*) AS orders
FROM Purchasing.PurchaseOrderHeader
GROUP BY Status
ORDER BY Status ASC
;


-- ---------------------------------------------------------------------------------------------------------- EmployeeID
-- how many employees place orders, and who they are; 9 Buyers with 3089 orders, 2 Purchasing Assistants with 763, and
-- the Purchasing Manager with 160.
SELECT e.JobTitle, COUNT(DISTINCT h.EmployeeID) AS employees, COUNT(*) AS orders
FROM Purchasing.PurchaseOrderHeader h
         JOIN HumanResources.Employee e ON e.BusinessEntityID = h.EmployeeID
GROUP BY e.JobTitle
ORDER BY e.JobTitle ASC
;

-- how many orders were placed by an employee who never worked in the Purchasing department; 0.
SELECT COUNT(*)
FROM Purchasing.PurchaseOrderHeader h
WHERE NOT EXISTS (SELECT 1
                  FROM HumanResources.EmployeeDepartmentHistory edh
                           JOIN HumanResources.Department d ON d.DepartmentID = edh.DepartmentID
                  WHERE edh.BusinessEntityID = h.EmployeeID
                    AND d.Name = 'Purchasing')
;


-- ------------------------------------------------------------------------------------------------------------ VendorID
-- how many vendors the orders go to; 86, every vendor that supplies a product.
SELECT COUNT(DISTINCT VendorID)
FROM Purchasing.PurchaseOrderHeader
;


-- -------------------------------------------------------------------------------------------------------- ShipMethodID
-- the shipping methods; 1 589, 2 655, 3 160, 4 1085 and 5 1523.
SELECT ShipMethodID, COUNT(*) AS orders
FROM Purchasing.PurchaseOrderHeader
GROUP BY ShipMethodID
ORDER BY ShipMethodID ASC
;


-- ----------------------------------------------------------------------------------------------------------- OrderDate
-- the range of OrderDate; 2022-04-15 to 2025-09-21.
SELECT MIN(OrderDate), MAX(OrderDate)
FROM Purchasing.PurchaseOrderHeader
;

-- an OrderDate with a time of day; 0: every OrderDate is a date at midnight.
SELECT COUNT(*)
FROM Purchasing.PurchaseOrderHeader
WHERE CAST(OrderDate AS time) <> '00:00'
;

-- the orders of each year; 2022 28, 2023 252, 2024 1323 and 2025 2409.
SELECT YEAR(OrderDate) AS year, COUNT(*) AS orders
FROM Purchasing.PurchaseOrderHeader
GROUP BY YEAR(OrderDate)
ORDER BY year ASC
;


-- ------------------------------------------------------------------------------------------------------------ ShipDate
-- how many rows leave ShipDate NULL, and its range; no NULL; 2022-04-24 to 2025-10-16.
SELECT COUNT(*) - COUNT(ShipDate) AS nulls, MIN(ShipDate), MAX(ShipDate)
FROM Purchasing.PurchaseOrderHeader
;

-- how many days an order takes to ship; CK_PurchaseOrderHeader_ShipDate keeps ShipDate from coming before OrderDate; 9
-- days for 4000 orders, 25 for 12.
SELECT DATEDIFF(DAY, OrderDate, ShipDate) AS days, COUNT(*) AS orders
FROM Purchasing.PurchaseOrderHeader
GROUP BY DATEDIFF(DAY, OrderDate, ShipDate)
ORDER BY days ASC
;

-- the days to ship against Status; the 12 orders of 25 days are the 12 approved (2); every other status ships in 9.
SELECT Status, DATEDIFF(DAY, OrderDate, ShipDate) AS days, COUNT(*) AS orders
FROM Purchasing.PurchaseOrderHeader
GROUP BY Status, DATEDIFF(DAY, OrderDate, ShipDate)
ORDER BY Status ASC
;


-- ------------------------------------------------------------------------------------------------------------ SubTotal
-- the smallest and the largest SubTotal; CK_PurchaseOrderHeader_SubTotal keeps it at 0.00 or more; 37.0755 and
-- 997680.0000.
SELECT MIN(SubTotal), MAX(SubTotal)
FROM Purchasing.PurchaseOrderHeader
;

-- a SubTotal which is not the sum of the LineTotal of its lines; 0: every SubTotal is the sum of its lines.
SELECT COUNT(*)
FROM Purchasing.PurchaseOrderHeader h
WHERE h.SubTotal <> (SELECT SUM(d.LineTotal)
                     FROM Purchasing.PurchaseOrderDetail d
                     WHERE d.PurchaseOrderID = h.PurchaseOrderID)
;


-- -------------------------------------------------------------------------------------------------------------- TaxAmt
-- the smallest and the largest TaxAmt; CK_PurchaseOrderHeader_TaxAmt keeps it at 0.00 or more; 2.9660 and 79814.4000.
SELECT MIN(TaxAmt), MAX(TaxAmt)
FROM Purchasing.PurchaseOrderHeader
;

-- TaxAmt as a share of SubTotal; 0.0799 to 0.0800, a flat 8% rounded.
SELECT MIN(TaxAmt / SubTotal), MAX(TaxAmt / SubTotal)
FROM Purchasing.PurchaseOrderHeader
;


-- ------------------------------------------------------------------------------------------------------------- Freight
-- the smallest and the largest Freight; CK_PurchaseOrderHeader_Freight keeps it at 0.00 or more; 0.9269 and 19953.6000.
SELECT MIN(Freight), MAX(Freight)
FROM Purchasing.PurchaseOrderHeader
;

-- Freight as a share of SubTotal; 0.0200 to 0.0250, 2% to 2.5%.
SELECT MIN(Freight / SubTotal), MAX(Freight / SubTotal)
FROM Purchasing.PurchaseOrderHeader
;


-- ------------------------------------------------------------------------------------------------------------ TotalDue
-- the smallest and the largest TotalDue, computed as SubTotal + TaxAmt + Freight; 40.9684 and 1097448.0000.
SELECT MIN(TotalDue), MAX(TotalDue)
FROM Purchasing.PurchaseOrderHeader
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range of ModifiedDate; 2022-04-24 00:00:00.000 to 2026-08-11 12:25:46.483.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Purchasing.PurchaseOrderHeader
;

-- ModifiedDate against OrderDate and ShipDate; 4000 modified on their ShipDate, none before the OrderDate, 12 after the
-- ShipDate.
SELECT COUNT(IIF(ModifiedDate = ShipDate, 1, NULL)) AS at_ship,
       COUNT(IIF(ModifiedDate < OrderDate, 1, NULL)) AS before_order,
       COUNT(IIF(ModifiedDate > ShipDate, 1, NULL)) AS after_ship
FROM Purchasing.PurchaseOrderHeader
;

-- the orders modified after they shipped; a finding: the 12 approved orders, 4001 to 4012, all modified at 2026-08-11
-- 12:25:46, well after the install of 2025-11-14.
SELECT Status, COUNT(*) AS orders, MIN(PurchaseOrderID), MAX(PurchaseOrderID), MIN(ModifiedDate), MAX(ModifiedDate)
FROM Purchasing.PurchaseOrderHeader
WHERE ModifiedDate > ShipDate
GROUP BY Status
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an order with no line.
-- no rows: every order has at least one line.
SELECT h.PurchaseOrderID
FROM Purchasing.PurchaseOrderHeader h
WHERE NOT EXISTS (SELECT 1
                  FROM Purchasing.PurchaseOrderDetail d
                  WHERE d.PurchaseOrderID = h.PurchaseOrderID)
;

