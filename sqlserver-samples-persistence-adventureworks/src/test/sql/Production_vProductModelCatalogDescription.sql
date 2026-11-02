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
-- Profiling queries on the view Production.vProductModelCatalogDescription, a section per column. Each says what it
-- looks for and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_vProductModelCatalogDescription.sql
--


-- ------------------------------------------------------------------------------------------------------ ProductModelID
-- how many rows, and the models they show, those with a CatalogDescription; 6 models, 19 to 35.
SELECT COUNT(*), MIN(ProductModelID), MAX(ProductModelID)
FROM Production.vProductModelCatalogDescription
;

-- a model with a CatalogDescription the view leaves out.
-- no rows: the view shows the 6 models with a CatalogDescription.
SELECT m.ProductModelID, m.Name
FROM Production.ProductModel m
WHERE m.CatalogDescription IS NOT NULL
  AND NOT EXISTS (SELECT 1
                  FROM Production.vProductModelCatalogDescription v
                  WHERE v.ProductModelID = m.ProductModelID)
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- every model the view shows; Mountain-100 (19), Mountain-500 (23), Road-150 (25), Road-450 (28), Touring-1000 (34) and
-- Touring-2000 (35).
SELECT ProductModelID, Name
FROM Production.vProductModelCatalogDescription
ORDER BY ProductModelID ASC
;


-- ------------------------------------------------------------------------------------------------------------- Summary
-- how many models have no Summary, and the shortest and the longest; none; 110 to 207 characters, the line breaks and
-- tabs counted.
SELECT COUNT(*) - COUNT(Summary) AS without, MIN(LEN(Summary)), MAX(LEN(Summary))
FROM Production.vProductModelCatalogDescription
;

-- a Summary which keeps the line breaks and the tabs of the XML it is read from; how many.
-- 6: all of them.
SELECT COUNT(*) AS laidOut
FROM Production.vProductModelCatalogDescription
WHERE Summary LIKE '%' + CHAR(10) + '%'
   OR Summary LIKE '%' + CHAR(9) + '%'
;


-- -------------------------------------------------------------------------------------------------------- Manufacturer
-- the values of Manufacturer, NULL for none; AdventureWorks for all 6.
SELECT Manufacturer, COUNT(*) AS productModels
FROM Production.vProductModelCatalogDescription
GROUP BY Manufacturer
ORDER BY Manufacturer ASC
;


-- ----------------------------------------------------------------------------------------------------------- Copyright
-- the values of Copyright, NULL for none; 2002 for all 6.
SELECT Copyright, COUNT(*) AS productModels
FROM Production.vProductModelCatalogDescription
GROUP BY Copyright
ORDER BY Copyright ASC
;


-- ---------------------------------------------------------------------------------------------------------- ProductURL
-- the values of ProductURL, NULL for none; HTTP://www.Adventure-works.com for all 6.
SELECT ProductURL, COUNT(*) AS productModels
FROM Production.vProductModelCatalogDescription
GROUP BY ProductURL
ORDER BY ProductURL ASC
;


-- ------------------------------------------------------------------------------------------------------ WarrantyPeriod
-- how many models have no WarrantyPeriod, and the shortest and the longest; none; 6 and 7 characters, 1 year to 4
-- years.
SELECT COUNT(*) - COUNT(WarrantyPeriod) AS without, MIN(LEN(WarrantyPeriod)), MAX(LEN(WarrantyPeriod))
FROM Production.vProductModelCatalogDescription
;


-- ------------------------------------------------------------------------------------------------- WarrantyDescription
-- the values of WarrantyDescription, NULL for none; parts and labor for all 6.
SELECT WarrantyDescription, COUNT(*) AS productModels
FROM Production.vProductModelCatalogDescription
GROUP BY WarrantyDescription
ORDER BY WarrantyDescription ASC
;


-- ----------------------------------------------------------------------------------------------------------- NoOfYears
-- how many models have no NoOfYears, and the shortest and the longest; none; 7 and 8 characters, 3 years to 10 years.
SELECT COUNT(*) - COUNT(NoOfYears) AS without, MIN(LEN(NoOfYears)), MAX(LEN(NoOfYears))
FROM Production.vProductModelCatalogDescription
;


-- ---------------------------------------------------------------------------------------------- MaintenanceDescription
-- how many models have no MaintenanceDescription, and the shortest and the longest; none; 44 and 88 characters.
SELECT COUNT(*) - COUNT(MaintenanceDescription) AS without,
       MIN(LEN(MaintenanceDescription)),
       MAX(LEN(MaintenanceDescription))
FROM Production.vProductModelCatalogDescription
;


-- --------------------------------------------------------------------------------------------------------------- Wheel
-- how many models have no Wheel, and the shortest and the longest; 1 has none, Touring-2000 (35); 24 and 97 characters.
SELECT COUNT(*) - COUNT(Wheel) AS without, MIN(LEN(Wheel)), MAX(LEN(Wheel))
FROM Production.vProductModelCatalogDescription
;


-- -------------------------------------------------------------------------------------------------------------- Saddle
-- how many models have no Saddle, and the shortest and the longest; none; 34 and 82 characters.
SELECT COUNT(*) - COUNT(Saddle) AS without, MIN(LEN(Saddle)), MAX(LEN(Saddle))
FROM Production.vProductModelCatalogDescription
;

-- a Saddle which carries leading or trailing spaces, or doubled ones. LEN and = ignore trailing spaces.
-- 3 rows: 25, 34 and 35 carry a doubled space inside.
SELECT ProductModelID, '[' + Saddle + ']' AS Saddle
FROM Production.vProductModelCatalogDescription
WHERE DATALENGTH(Saddle) <> DATALENGTH(TRIM(Saddle))
   OR Saddle LIKE '%  %'
;


-- --------------------------------------------------------------------------------------------------------------- Pedal
-- how many models have no Pedal, and the shortest and the longest; none; 34 and 76 characters.
SELECT COUNT(*) - COUNT(Pedal) AS without, MIN(LEN(Pedal)), MAX(LEN(Pedal))
FROM Production.vProductModelCatalogDescription
;


-- ----------------------------------------------------------------------------------------------------------- BikeFrame
-- how many models have no BikeFrame, and the shortest and the longest; none; 93 and 229 characters, the line breaks and
-- tabs counted.
SELECT COUNT(*) - COUNT(BikeFrame) AS without, MIN(LEN(BikeFrame)), MAX(LEN(BikeFrame))
FROM Production.vProductModelCatalogDescription
;

-- a BikeFrame which keeps the line breaks and the tabs of the XML it is read from; how many.
-- 5 of the 6.
SELECT COUNT(*) AS laidOut
FROM Production.vProductModelCatalogDescription
WHERE BikeFrame LIKE '%' + CHAR(10) + '%'
   OR BikeFrame LIKE '%' + CHAR(9) + '%'
;


-- ------------------------------------------------------------------------------------------------------------ Crankset
-- how many models have no Crankset, and the shortest and the longest; 3 have none, 25, 28 and 34; 21 and 56 characters,
-- the leading space counted.
SELECT COUNT(*) - COUNT(Crankset) AS without, MIN(LEN(Crankset)), MAX(LEN(Crankset))
FROM Production.vProductModelCatalogDescription
;

-- a Crankset which carries leading or trailing spaces, or doubled ones. LEN and = ignore trailing spaces.
-- 3 rows: 19, 23 and 35, each wrapped in a leading and a trailing space.
SELECT ProductModelID, '[' + Crankset + ']' AS Crankset
FROM Production.vProductModelCatalogDescription
WHERE DATALENGTH(Crankset) <> DATALENGTH(TRIM(Crankset))
   OR Crankset LIKE '%  %'
;


-- -------------------------------------------------------------------------------------------------------- PictureAngle
-- the values of PictureAngle, NULL for none; front for all 6.
SELECT PictureAngle, COUNT(*) AS productModels
FROM Production.vProductModelCatalogDescription
GROUP BY PictureAngle
ORDER BY PictureAngle ASC
;


-- --------------------------------------------------------------------------------------------------------- PictureSize
-- the values of PictureSize, NULL for none; small for all 6.
SELECT PictureSize, COUNT(*) AS productModels
FROM Production.vProductModelCatalogDescription
GROUP BY PictureSize
ORDER BY PictureSize ASC
;


-- ------------------------------------------------------------------------------------------------------ ProductPhotoID
-- the values of ProductPhotoID, NULL for none; 1, 111, 118 and 126 for one each, 87 for two.
SELECT ProductPhotoID, COUNT(*) AS productModels
FROM Production.vProductModelCatalogDescription
GROUP BY ProductPhotoID
ORDER BY ProductPhotoID ASC
;

-- a ProductPhotoID, text in the XML, which is not a number or names no photo.
-- no rows: each names an existing photo.
SELECT v.ProductModelID, v.ProductPhotoID
FROM Production.vProductModelCatalogDescription v
WHERE NOT EXISTS (SELECT 1
                  FROM Production.ProductPhoto f
                  WHERE f.ProductPhotoID = TRY_CAST(v.ProductPhotoID AS int))
;


-- ------------------------------------------------------------------------------------------------------------ Material
-- how many models have no Material, and the shortest and the longest; none; 8 and 15 characters.
SELECT COUNT(*) - COUNT(Material) AS without, MIN(LEN(Material)), MAX(LEN(Material))
FROM Production.vProductModelCatalogDescription
;

-- a Material which carries leading or trailing spaces, or doubled ones. LEN and = ignore trailing spaces.
-- one row: 34, Aluminum alloy with a trailing space.
SELECT ProductModelID, '[' + Material + ']' AS Material
FROM Production.vProductModelCatalogDescription
WHERE DATALENGTH(Material) <> DATALENGTH(TRIM(Material))
   OR Material LIKE '%  %'
;


-- --------------------------------------------------------------------------------------------------------------- Color
-- how many models have no Color, and the shortest and the longest; none; 24 and 40 characters.
SELECT COUNT(*) - COUNT(Color) AS without, MIN(LEN(Color)), MAX(LEN(Color))
FROM Production.vProductModelCatalogDescription
;


-- --------------------------------------------------------------------------------------------------------- ProductLine
-- the values of ProductLine, NULL for none; Mountain bike, Road bike and Touring bike, 2 each.
SELECT ProductLine, COUNT(*) AS productModels
FROM Production.vProductModelCatalogDescription
GROUP BY ProductLine
ORDER BY ProductLine ASC
;


-- --------------------------------------------------------------------------------------------------------------- Style
-- the values of Style, NULL for none; Men's for 1, Unisex for 5.
SELECT Style, COUNT(*) AS productModels
FROM Production.vProductModelCatalogDescription
GROUP BY Style
ORDER BY Style ASC
;


-- ----------------------------------------------------------------------------------------------------- RiderExperience
-- how many models have no RiderExperience, and the shortest and the longest; none; 25 and 35 characters.
SELECT COUNT(*) - COUNT(RiderExperience) AS without, MIN(LEN(RiderExperience)), MAX(LEN(RiderExperience))
FROM Production.vProductModelCatalogDescription
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- a rowguid other than the model's.
-- no rows: the view carries ProductModel.rowguid.
SELECT v.ProductModelID
FROM Production.vProductModelCatalogDescription v
         JOIN Production.ProductModel m ON m.ProductModelID = v.ProductModelID
WHERE v.rowguid <> m.rowguid
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the rows carry; 2022-04-30 00:00:00.000 to 2023-10-19 09:56:38.273.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.vProductModelCatalogDescription
;

