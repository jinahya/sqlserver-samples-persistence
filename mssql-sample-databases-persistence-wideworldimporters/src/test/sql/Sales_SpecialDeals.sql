-- -
-- #%L
-- mssql-sample-databases-persistence-wideworldimporters
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
-- Profiling queries on Sales.SpecialDeals, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Sales_SpecialDeals.sql
--


-- ------------------------------------------------------------------------------------------------------- SpecialDealID
-- how many rows, and the smallest and the largest SpecialDealID; 2 deals, 1 and 2.
SELECT COUNT(*), MIN(SpecialDealID), MAX(SpecialDealID)
FROM Sales.SpecialDeals
;


-- --------------------------------------------------------------------------------------------------------- StockItemID
-- the deals on a single stock item, and those not; none is, StockItemID is NULL for both.
SELECT COUNT(StockItemID) AS single_item, COUNT(*) - COUNT(StockItemID) AS other
FROM Sales.SpecialDeals
;


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- the deals for a single customer, and those not; none is, CustomerID is NULL for both.
SELECT COUNT(CustomerID) AS single_customer, COUNT(*) - COUNT(CustomerID) AS other
FROM Sales.SpecialDeals
;


-- ------------------------------------------------------------------------------------------------------- BuyingGroupID
-- the deals of each buying group; one each, 1 for Tailspin Toys and 2 for Wingtip Toys.
SELECT d.BuyingGroupID, b.BuyingGroupName, COUNT(*) AS deals
FROM Sales.SpecialDeals d
         LEFT JOIN Sales.BuyingGroups b ON b.BuyingGroupID = d.BuyingGroupID
GROUP BY d.BuyingGroupID, b.BuyingGroupName
ORDER BY d.BuyingGroupID ASC
;


-- -------------------------------------------------------------------------------------------------- CustomerCategoryID
-- the deals for a customer category, and those not; none is, CustomerCategoryID is NULL for both.
SELECT COUNT(CustomerCategoryID) AS for_category, COUNT(*) - COUNT(CustomerCategoryID) AS other
FROM Sales.SpecialDeals
;


-- -------------------------------------------------------------------------------------------------------- StockGroupID
-- the deals of each stock group; both are on USB Novelties (7).
SELECT d.StockGroupID, g.StockGroupName, COUNT(*) AS deals
FROM Sales.SpecialDeals d
         LEFT JOIN Warehouse.StockGroups g ON g.StockGroupID = d.StockGroupID
GROUP BY d.StockGroupID, g.StockGroupName
ORDER BY d.StockGroupID ASC
;

-- a deal which names neither a stock item nor a stock group, or names both.
-- no rows: each deal is on a stock item or a stock group, never both.
SELECT SpecialDealID, StockItemID, StockGroupID
FROM Sales.SpecialDeals
WHERE (StockItemID IS NULL AND StockGroupID IS NULL)
   OR (StockItemID IS NOT NULL AND StockGroupID IS NOT NULL)
;


-- ----------------------------------------------------------------------------------------------------- DealDescription
-- the shortest and the longest DealDescription; 23 and 24 characters, of the 30 the column allows.
SELECT MIN(LEN(DealDescription)), MAX(LEN(DealDescription))
FROM Sales.SpecialDeals
;

-- a DealDescription which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- no rows: every DealDescription is trimmed and not blank.
SELECT SpecialDealID, '[' + DealDescription + ']' AS DealDescription
FROM Sales.SpecialDeals
WHERE TRIM(DealDescription) = ''
   OR DATALENGTH(DealDescription) <> DATALENGTH(TRIM(DealDescription))
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- the range of StartDate; 2016-01-01 and 2016-04-01, the first days of two quarters.
SELECT MIN(StartDate), MAX(StartDate)
FROM Sales.SpecialDeals
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- the range of EndDate; 2016-03-31 and 2016-06-30, the last days of the same quarters.
SELECT MIN(EndDate), MAX(EndDate)
FROM Sales.SpecialDeals
;

-- a deal which ends before it starts.
-- no rows: no EndDate is earlier than its StartDate.
SELECT SpecialDealID, StartDate, EndDate
FROM Sales.SpecialDeals
WHERE EndDate < StartDate
;


-- ------------------------------------------------------------------------------------------------------ DiscountAmount
-- the deals with a DiscountAmount; 0.
SELECT COUNT(DiscountAmount)
FROM Sales.SpecialDeals
;


-- -------------------------------------------------------------------------------------------------- DiscountPercentage
-- the deals with a DiscountPercentage, and its range; both, 10.000 and 15.000.
SELECT COUNT(DiscountPercentage), MIN(DiscountPercentage), MAX(DiscountPercentage)
FROM Sales.SpecialDeals
;


-- ----------------------------------------------------------------------------------------------------------- UnitPrice
-- the deals with a UnitPrice; 0.
SELECT COUNT(UnitPrice)
FROM Sales.SpecialDeals
;

-- CK_Sales_SpecialDeals_Exactly_One_NOT_NULL_Pricing_Option_Is_Required, and
-- CK_Sales_SpecialDeals_Unit_Price_Deal_Requires_Special_StockItem; a deal which breaks either.
-- no rows: each deal has exactly one pricing option, and a UnitPrice deal names a stock item.
SELECT SpecialDealID
FROM Sales.SpecialDeals
WHERE (CASE WHEN DiscountAmount IS NULL THEN 0 ELSE 1 END
           + CASE WHEN DiscountPercentage IS NULL THEN 0 ELSE 1 END
           + CASE WHEN UnitPrice IS NULL THEN 0 ELSE 1 END) <> 1
   OR (UnitPrice IS NOT NULL AND StockItemID IS NULL)
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the rows; person 2, for both.
SELECT LastEditedBy, COUNT(*) AS deals
FROM Sales.SpecialDeals
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ------------------------------------------------------------------------------------------------------ LastEditedWhen
-- the range of LastEditedWhen; 2015-12-31 16:00:00.0000000, a single moment.
SELECT MIN(LastEditedWhen), MAX(LastEditedWhen)
FROM Sales.SpecialDeals
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the deals which overlap another deal of the same buying group and stock group.
-- no rows: the two deals are for different buying groups.
SELECT a.SpecialDealID, b.SpecialDealID
FROM Sales.SpecialDeals a
         JOIN Sales.SpecialDeals b ON b.SpecialDealID > a.SpecialDealID
                  AND b.BuyingGroupID = a.BuyingGroupID
                  AND b.StockGroupID = a.StockGroupID
WHERE b.StartDate <= a.EndDate
  AND a.StartDate <= b.EndDate
;

