-- -
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
-- -
--
-- Profiling queries on Warehouse.PackageTypes, a section per column. Each says what it looks for and what the result
-- should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Warehouse_PackageTypes.sql
--


-- ------------------------------------------------------------------------------------------------------- PackageTypeID
-- how many rows, and the smallest and the largest PackageTypeID; 14 package types, 1 to 14.
SELECT COUNT(*), MIN(PackageTypeID), MAX(PackageTypeID)
FROM Warehouse.PackageTypes
;

-- a PackageTypeID whose next value is missing, below the largest.
-- no rows: PackageTypeID runs from 1 without a gap.
SELECT a.PackageTypeID + 1 AS missing_from
FROM Warehouse.PackageTypes a
WHERE a.PackageTypeID < (SELECT MAX(PackageTypeID) FROM Warehouse.PackageTypes)
  AND NOT EXISTS (SELECT 1
                  FROM Warehouse.PackageTypes b
                  WHERE b.PackageTypeID = a.PackageTypeID + 1)
;


-- ----------------------------------------------------------------------------------------------------- PackageTypeName
-- the shortest and the longest PackageTypeName; 2 and 6 characters, of the 50 the column allows.
-- LEN ignores the trailing space of Tub.
SELECT MIN(LEN(PackageTypeName)), MAX(LEN(PackageTypeName))
FROM Warehouse.PackageTypes
;

-- a PackageTypeName which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces,
-- DATALENGTH does not.
-- 1 row, a finding: Tub (13) is stored as [Tub ], with a trailing space.
SELECT PackageTypeID, '[' + PackageTypeName + ']' AS PackageTypeName
FROM Warehouse.PackageTypes
WHERE TRIM(PackageTypeName) = ''
   OR DATALENGTH(PackageTypeName) <> DATALENGTH(TRIM(PackageTypeName))
;

-- UQ_Warehouse_PackageTypes_PackageTypeName keeps PackageTypeName unique.
-- no rows: no two package types share a name.
SELECT PackageTypeName, COUNT(*) AS package_types
FROM Warehouse.PackageTypes
GROUP BY PackageTypeName
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- the people who last edited the rows; person 1, for all 14.
SELECT LastEditedBy, COUNT(*) AS package_types
FROM Warehouse.PackageTypes
GROUP BY LastEditedBy
ORDER BY LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the moments the current versions began; 2013-01-01 00:00:00.0000000, for all 14.
SELECT ValidFrom, COUNT(*) AS package_types
FROM Warehouse.PackageTypes
GROUP BY ValidFrom
ORDER BY ValidFrom ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- the end of each current version; 9999-12-31 23:59:59.9999999, the largest datetime2, for all 14.
SELECT ValidTo, COUNT(*) AS package_types
FROM Warehouse.PackageTypes
GROUP BY ValidTo
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the past versions kept in Warehouse.PackageTypes_Archive; none, no package type has been changed.
SELECT COUNT(*)
FROM Warehouse.PackageTypes_Archive
;

-- a package type no stock item, order line, invoice line or purchase order line uses.
-- 9 rows: only Bag, Each, Packet, Pair and Carton are in use; Block, Bottle, Box, Can, Kg, Pallet, Tray,
-- Tub and Tube are not.
SELECT a.PackageTypeID, '[' + a.PackageTypeName + ']' AS PackageTypeName
FROM Warehouse.PackageTypes a
WHERE NOT EXISTS (SELECT 1
                  FROM Warehouse.StockItems s
                  WHERE s.UnitPackageID = a.PackageTypeID
                     OR s.OuterPackageID = a.PackageTypeID)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.OrderLines o
                  WHERE o.PackageTypeID = a.PackageTypeID)
  AND NOT EXISTS (SELECT 1
                  FROM Sales.InvoiceLines i
                  WHERE i.PackageTypeID = a.PackageTypeID)
  AND NOT EXISTS (SELECT 1
                  FROM Purchasing.PurchaseOrderLines p
                  WHERE p.PackageTypeID = a.PackageTypeID)
ORDER BY a.PackageTypeID ASC
;

