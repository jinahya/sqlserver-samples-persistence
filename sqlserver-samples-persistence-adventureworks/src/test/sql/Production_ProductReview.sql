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
-- Profiling queries on Production.ProductReview, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_ProductReview.sql
--


-- ----------------------------------------------------------------------------------------------------- ProductReviewID
-- how many rows, and the smallest and the largest ProductReviewID; 4 reviews, 1 to 4.
SELECT COUNT(*), MIN(ProductReviewID), MAX(ProductReviewID)
FROM Production.ProductReview
;


-- ----------------------------------------------------------------------------------------------------------- ProductID
-- the products reviewed, and the reviews of each; 3 products; 709 and 798 with 1, 937 with 2.
SELECT ProductID, COUNT(*) AS reviews
FROM Production.ProductReview
GROUP BY ProductID
ORDER BY ProductID ASC
;


-- -------------------------------------------------------------------------------------------------------- ReviewerName
-- every reviewer; John Smith, David, Jill and Laura Norman.
SELECT ProductReviewID, ReviewerName
FROM Production.ProductReview
ORDER BY ProductReviewID ASC
;

-- a ReviewerName which is blank, or carries leading or trailing spaces.
-- no rows: every ReviewerName is trimmed and not blank.
SELECT ProductReviewID, '[' + ReviewerName + ']' AS ReviewerName
FROM Production.ProductReview
WHERE TRIM(ReviewerName) = ''
   OR DATALENGTH(ReviewerName) <> DATALENGTH(TRIM(ReviewerName))
;


-- ---------------------------------------------------------------------------------------------------------- ReviewDate
-- the range of ReviewDate; 2024-09-17 00:00:00.000 to 2024-11-14 00:00:00.000.
SELECT MIN(ReviewDate), MAX(ReviewDate)
FROM Production.ProductReview
;

-- a review modified before it was written.
-- no rows: ModifiedDate is never before ReviewDate.
SELECT ProductReviewID, ReviewDate, ModifiedDate
FROM Production.ProductReview
WHERE ModifiedDate < ReviewDate
;


-- -------------------------------------------------------------------------------------------------------- EmailAddress
-- an EmailAddress which is blank, padded, or not shaped like an address.
-- no rows: every EmailAddress has a local part, an @ and a dotted domain.
SELECT ProductReviewID, '[' + EmailAddress + ']' AS EmailAddress
FROM Production.ProductReview
WHERE DATALENGTH(EmailAddress) <> DATALENGTH(TRIM(EmailAddress))
   OR EmailAddress NOT LIKE '_%@_%._%'
;

-- the domains of the addresses; fourthcoffee.com, graphicdesigninstitute.com, margiestravel.com and treyresearch.net,
-- one each.
SELECT SUBSTRING(EmailAddress, CHARINDEX('@', EmailAddress) + 1, 50) AS domain, COUNT(*) AS reviews
FROM Production.ProductReview
GROUP BY SUBSTRING(EmailAddress, CHARINDEX('@', EmailAddress) + 1, 50)
;


-- -------------------------------------------------------------------------------------------------------------- Rating
-- the ratings CK_ProductReview_Rating keeps within 1 to 5; 2 for one review, 4 for one, 5 for two; none is 1 or 3.
SELECT Rating, COUNT(*) AS reviews
FROM Production.ProductReview
GROUP BY Rating
ORDER BY Rating ASC
;


-- ------------------------------------------------------------------------------------------------------------ Comments
-- Comments is nullable; how many reviews have none, and how long the others are; none; 252 to 3782 characters, of the
-- 3850 the column allows.
SELECT COUNT(*) - COUNT(Comments) AS without, MIN(LEN(Comments)), MAX(LEN(Comments))
FROM Production.ProductReview
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2024-09-17 00:00:00.000 to 2024-11-14 00:00:00.000, the same range as ReviewDate.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.ProductReview
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the products reviewed, with their names; Mountain Bike Socks, M (709), Road-550-W Yellow, 40 (798) and HL Mountain
-- Pedal (937).
SELECT DISTINCT p.ProductID, p.Name
FROM Production.ProductReview r
         JOIN Production.Product p ON p.ProductID = r.ProductID
ORDER BY p.ProductID ASC
;

