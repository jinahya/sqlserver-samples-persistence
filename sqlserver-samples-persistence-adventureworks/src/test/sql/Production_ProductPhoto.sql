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
-- Profiling queries on Production.ProductPhoto, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductPhoto.sql
--


-- ------------------------------------------------------------------------------------------------------ ProductPhotoID
-- how many rows, and the smallest and the largest ProductPhotoID; 101 photos, 1 to 181.
SELECT COUNT(*), MIN(ProductPhotoID), MAX(ProductPhotoID)
FROM Production.ProductPhoto
;

-- the gaps in ProductPhotoID, below the largest: how many values are missing; 80; 101 of the 181 values are used.
SELECT MAX(ProductPhotoID) - MIN(ProductPhotoID) + 1 - COUNT(*) AS missing
FROM Production.ProductPhoto
;


-- ------------------------------------------------------------------------------------------------------ ThumbNailPhoto
-- ThumbNailPhoto is a nullable varbinary(max); how many photos have none, and how large the others are; none; 1077 to
-- 17623 bytes.
SELECT COUNT(*) - COUNT(ThumbNailPhoto) AS without, MIN(DATALENGTH(ThumbNailPhoto)), MAX(DATALENGTH(ThumbNailPhoto))
FROM Production.ProductPhoto
;

-- the first bytes of the thumbnails, the format they are in; 474946, 'GIF', for all 101.
SELECT CONVERT(varchar(8), SUBSTRING(ThumbNailPhoto, 1, 3), 2) AS magic, COUNT(*) AS photos
FROM Production.ProductPhoto
GROUP BY CONVERT(varchar(8), SUBSTRING(ThumbNailPhoto, 1, 3), 2)
;


-- ---------------------------------------------------------------------------------------------- ThumbnailPhotoFileName
-- how many photos have no ThumbnailPhotoFileName, and the shortest and the longest; none; 14 and 35 characters, of the
-- 50 the column allows.
SELECT COUNT(*) - COUNT(ThumbnailPhotoFileName) AS without,
       MIN(LEN(ThumbnailPhotoFileName)),
       MAX(LEN(ThumbnailPhotoFileName))
FROM Production.ProductPhoto
;

-- the schema has no unique key on ThumbnailPhotoFileName; a name more than one photo has.
-- no rows: every thumbnail has a name of its own.
SELECT ThumbnailPhotoFileName, COUNT(*) AS photos
FROM Production.ProductPhoto
GROUP BY ThumbnailPhotoFileName
HAVING COUNT(*) > 1
;

-- the file extensions of the thumbnails; .gif for all 101.
SELECT RIGHT(ThumbnailPhotoFileName, 4) AS extension, COUNT(*) AS photos
FROM Production.ProductPhoto
GROUP BY RIGHT(ThumbnailPhotoFileName, 4)
;


-- ---------------------------------------------------------------------------------------------------------- LargePhoto
-- LargePhoto is a nullable varbinary(max); how many photos have none, and how large the others are; none; 1673 to 25925
-- bytes.
SELECT COUNT(*) - COUNT(LargePhoto) AS without, MIN(DATALENGTH(LargePhoto)), MAX(DATALENGTH(LargePhoto))
FROM Production.ProductPhoto
;


-- -------------------------------------------------------------------------------------------------- LargePhotoFileName
-- how many photos have no LargePhotoFileName, and the shortest and the longest; none; 14 and 35 characters, of the 50
-- the column allows.
SELECT COUNT(*) - COUNT(LargePhotoFileName) AS without, MIN(LEN(LargePhotoFileName)), MAX(LEN(LargePhotoFileName))
FROM Production.ProductPhoto
;

-- a large photo file not named after its thumbnail, '_small' turned into '_large'.
-- no rows: every large photo is its thumbnail's name with _large for _small.
SELECT ProductPhotoID, ThumbnailPhotoFileName, LargePhotoFileName
FROM Production.ProductPhoto
WHERE LargePhotoFileName <> REPLACE(ThumbnailPhotoFileName, '_small', '_large')
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-03-31 00:00:00.000 to 2024-04-29 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductPhoto
;


-- ---------------------------------------------------------------------------------------------------------------------
-- how many photos no product shows, and how many products each of the others shows, at most; 59 photos no product
-- shows; the busiest shows 346 products.
SELECT (SELECT COUNT(*)
        FROM Production.ProductPhoto f
        WHERE NOT EXISTS (SELECT 1
                          FROM Production.ProductProductPhoto pp
                          WHERE pp.ProductPhotoID = f.ProductPhotoID)) AS unused,
       (SELECT TOP (1) COUNT(*)
        FROM Production.ProductProductPhoto
        GROUP BY ProductPhotoID
        ORDER BY COUNT(*) DESC) AS mostProducts
;

