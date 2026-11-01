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
-- Profiling queries on Production.BillOfMaterials, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_BillOfMaterials.sql
--


-- --------------------------------------------------------------------------------------------------- BillOfMaterialsID
-- how many rows, and the smallest and the largest BillOfMaterialsID; 2679 rows, 1 to 3482.
SELECT COUNT(*), MIN(BillOfMaterialsID), MAX(BillOfMaterialsID)
FROM Production.BillOfMaterials
;

-- the gaps in BillOfMaterialsID, below the largest: how many values are missing; 803; 2679 of the 3482 values are used.
SELECT MAX(BillOfMaterialsID) - MIN(BillOfMaterialsID) + 1 - COUNT(*) AS missing
FROM Production.BillOfMaterials
;


-- --------------------------------------------------------------------------------------------------- ProductAssemblyID
-- how many rows have no assembly, the top of a bill, and how many assemblies the others name; 103 tops, under 238
-- assemblies.
SELECT COUNT(*) - COUNT(ProductAssemblyID) AS tops, COUNT(DISTINCT ProductAssemblyID) AS assemblies
FROM Production.BillOfMaterials
;

-- CK_BillOfMaterials_ProductAssemblyID keeps a product out of its own assembly.
-- no rows: no row names the same product twice.
SELECT BillOfMaterialsID, ProductAssemblyID, ComponentID
FROM Production.BillOfMaterials
WHERE ProductAssemblyID = ComponentID
;


-- --------------------------------------------------------------------------------------------------------- ComponentID
-- the components the rows name; 325 components, 1 to 999.
SELECT COUNT(DISTINCT ComponentID), MIN(ComponentID), MAX(ComponentID)
FROM Production.BillOfMaterials
;

-- AK_BillOfMaterials_ProductAssemblyID_ComponentID_StartDate keeps a component once per assembly and start date.
-- no rows: no two rows share an assembly, a component and a start date.
SELECT ProductAssemblyID, ComponentID, StartDate, COUNT(*) AS boms
FROM Production.BillOfMaterials
GROUP BY ProductAssemblyID, ComponentID, StartDate
HAVING COUNT(*) > 1
;

-- a component in an assembly more than once, at different start dates; how many pairs.
-- 195.
SELECT COUNT(*) AS pairs
FROM (SELECT ProductAssemblyID, ComponentID
      FROM Production.BillOfMaterials
      GROUP BY ProductAssemblyID, ComponentID
      HAVING COUNT(*) > 1) x
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- the range of StartDate; 2021-03-03 00:00:00.000 to 2021-12-22 00:00:00.000, 19 dates.
SELECT MIN(StartDate), MAX(StartDate), COUNT(DISTINCT StartDate)
FROM Production.BillOfMaterials
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- how many rows are current, and the range of the others' EndDate; 2480 current; 2021-05-02 00:00:00.000 to 2021-11-13
-- 00:00:00.000.
SELECT COUNT(*) - COUNT(EndDate) AS current_, MIN(EndDate), MAX(EndDate)
FROM Production.BillOfMaterials
;

-- CK_BillOfMaterials_EndDate keeps EndDate after StartDate.
-- no rows: every ended row ends after it starts.
SELECT BillOfMaterialsID, StartDate, EndDate
FROM Production.BillOfMaterials
WHERE EndDate <= StartDate
;


-- ----------------------------------------------------------------------------------------------------- UnitMeasureCode
-- the units the quantities are in; EA for 2578, IN for 9, OZ for 92.
SELECT UnitMeasureCode, COUNT(*) AS boms
FROM Production.BillOfMaterials
GROUP BY UnitMeasureCode
ORDER BY UnitMeasureCode ASC
;


-- ------------------------------------------------------------------------------------------------------------ BOMLevel
-- the levels, and the rows of each; 0 for 103, 1 for 1548, 2 for 993, 3 for 31, 4 for 4.
SELECT BOMLevel, COUNT(*) AS boms
FROM Production.BillOfMaterials
GROUP BY BOMLevel
ORDER BY BOMLevel ASC
;

-- CK_BillOfMaterials_BOMLevel: level 0 has no assembly and a quantity of 1, every other level has an assembly.
-- no rows: every row keeps the rule.
SELECT BillOfMaterialsID, ProductAssemblyID, BOMLevel, PerAssemblyQty
FROM Production.BillOfMaterials
WHERE NOT ((ProductAssemblyID IS NULL AND BOMLevel = 0 AND PerAssemblyQty = 1.00)
    OR (ProductAssemblyID IS NOT NULL AND BOMLevel >= 1))
;

-- a current row whose level is not one below its assembly's current level as a component; the schema does not keep
-- the levels in step.
-- 0: the current levels step one at a time.
SELECT COUNT(*) AS offLevel
FROM Production.BillOfMaterials c
         JOIN Production.BillOfMaterials a ON a.ComponentID = c.ProductAssemblyID AND a.EndDate IS NULL
WHERE c.EndDate IS NULL
  AND c.BOMLevel <> a.BOMLevel + 1
;


-- ------------------------------------------------------------------------------------------------------ PerAssemblyQty
-- the smallest and the largest PerAssemblyQty, which CK_BillOfMaterials_PerAssemblyQty keeps at least 1; 1.00 and
-- 41.00, 12 values.
SELECT MIN(PerAssemblyQty), MAX(PerAssemblyQty), COUNT(DISTINCT PerAssemblyQty)
FROM Production.BillOfMaterials
;

-- a PerAssemblyQty which is not whole; 0: every quantity is whole.
SELECT COUNT(*) AS fractional
FROM Production.BillOfMaterials
WHERE PerAssemblyQty <> FLOOR(PerAssemblyQty)
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2021-02-17 00:00:00.000 to 2021-12-08 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.BillOfMaterials
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a top-level product which is also a component somewhere.
-- 0: no top of a bill is a component of another.
SELECT COUNT(DISTINCT t.ComponentID) AS both_
FROM Production.BillOfMaterials t
WHERE t.ProductAssemblyID IS NULL
  AND EXISTS (SELECT 1
              FROM Production.BillOfMaterials c
              WHERE c.ComponentID = t.ComponentID
                AND c.ProductAssemblyID IS NOT NULL)
;

-- an assembly that is not a made product, MakeFlag 0.
-- 0: every assembly is made in house.
SELECT COUNT(DISTINCT b.ProductAssemblyID) AS bought
FROM Production.BillOfMaterials b
         JOIN Production.Product p ON p.ProductID = b.ProductAssemblyID
WHERE p.MakeFlag = 0
;

