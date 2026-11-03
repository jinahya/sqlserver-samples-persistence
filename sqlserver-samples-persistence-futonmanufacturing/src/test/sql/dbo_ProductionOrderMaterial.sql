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
-- Profiling queries on dbo.ProductionOrderMaterial, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- The load scripts insert no production order, nor its materials, so the table is empty and every check below finds
-- nothing. They are kept for the rows an application would add.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_ProductionOrderMaterial.sql
--


-- ------------------------------------------------------------------------------------------------- ProdOrderMaterialID
-- how many rows, and the smallest and the largest ProdOrderMaterialID; 0 rows, NULL and NULL.
SELECT COUNT(*), MIN(ProdOrderMaterialID), MAX(ProdOrderMaterialID)
FROM dbo.ProductionOrderMaterial
;


-- --------------------------------------------------------------------------------------------------- ProductionOrderID
-- nothing keeps an item to one row per order.
-- no rows: the table is empty.
SELECT ProductionOrderID, ItemID, COUNT(*) AS materials
FROM dbo.ProductionOrderMaterial
GROUP BY ProductionOrderID, ItemID
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------------- ItemID
-- a material which is not a component of the ordered item in its bill of materials.
-- no rows: the table is empty.
SELECT m.ProdOrderMaterialID, po.ItemID AS parent, m.ItemID
FROM dbo.ProductionOrderMaterial m
         JOIN dbo.ProductionOrder po ON po.ProductionOrderID = m.ProductionOrderID
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.BillOfMaterials b
                  WHERE b.ParentItemID = po.ItemID
                    AND b.ComponentItemID = m.ItemID)
;


-- ---------------------------------------------------------------------------------------------------- RequiredQuantity
-- a RequiredQuantity of zero or less.
-- no rows: the table is empty.
SELECT ProdOrderMaterialID, RequiredQuantity
FROM dbo.ProductionOrderMaterial
WHERE RequiredQuantity <= 0
;


-- ------------------------------------------------------------------------------------------------------ IssuedQuantity
-- a material issued beyond what is required, or a negative IssuedQuantity.
-- no rows: the table is empty.
SELECT ProdOrderMaterialID, RequiredQuantity, IssuedQuantity
FROM dbo.ProductionOrderMaterial
WHERE IssuedQuantity > RequiredQuantity
   OR IssuedQuantity < 0
;

