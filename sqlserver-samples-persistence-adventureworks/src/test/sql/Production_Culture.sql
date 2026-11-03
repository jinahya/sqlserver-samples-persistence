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
-- Profiling queries on Production.Culture, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_Culture.sql
--


-- ----------------------------------------------------------------------------------------------------------- CultureID
-- how many rows, and the smallest and the largest CultureID; 8 cultures, the blank one to zh-cht.
SELECT COUNT(*), MIN(CultureID), MAX(CultureID)
FROM Production.Culture
;

-- every culture, by CultureID; the invariant culture, blank, then ar, en, es, fr, he, th and zh-cht.
SELECT '[' + CultureID + ']' AS CultureID, Name
FROM Production.Culture
ORDER BY CultureID ASC
;

-- a CultureID which is blank. nchar(6) pads every code with spaces, which = and LEN ignore.
-- one row: the invariant culture, whose CultureID is all spaces.
SELECT '[' + CultureID + ']' AS CultureID, Name
FROM Production.Culture
WHERE TRIM(CultureID) = ''
;

-- a CultureID, other than the blank one, which starts with a space.
-- no rows: every other code starts with a letter.
SELECT '[' + CultureID + ']' AS CultureID, Name
FROM Production.Culture
WHERE TRIM(CultureID) <> ''
  AND CultureID LIKE ' %'
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 4 and 38 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Production.Culture
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT CultureID, '[' + Name + ']' AS Name
FROM Production.Culture
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_Culture_Name keeps Name unique.
-- no rows: no two cultures share a Name.
SELECT Name, COUNT(*) AS cultures
FROM Production.Culture
GROUP BY Name
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.Culture
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a culture no product model description is written in.
-- two rows: the invariant culture and es (Spanish).
SELECT c.CultureID, c.Name
FROM Production.Culture c
WHERE NOT EXISTS (SELECT 1
                  FROM Production.ProductModelProductDescriptionCulture m
                  WHERE m.CultureID = c.CultureID)
;

-- the descriptions written in each culture; 6 cultures, ar, en, fr, he, th and zh-cht, with 127 each.
SELECT c.CultureID, COUNT(*) AS descriptions
FROM Production.Culture c
         JOIN Production.ProductModelProductDescriptionCulture m ON m.CultureID = c.CultureID
GROUP BY c.CultureID
ORDER BY c.CultureID ASC
;

