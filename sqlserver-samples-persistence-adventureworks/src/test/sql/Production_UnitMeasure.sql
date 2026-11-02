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
-- Profiling queries on Production.UnitMeasure, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_UnitMeasure.sql
--


-- ----------------------------------------------------------------------------------------------------- UnitMeasureCode
-- how many rows, and the smallest and the largest UnitMeasureCode; 38 units, BOX to PT.
SELECT COUNT(*), MIN(UnitMeasureCode), MAX(UnitMeasureCode)
FROM Production.UnitMeasure
;

-- the length of the codes, trailing padding of the nchar(3) aside; 4 of 1 character, 19 of 2 and 15 of 3.
SELECT LEN(UnitMeasureCode) AS length, COUNT(*) AS codes
FROM Production.UnitMeasure
GROUP BY LEN(UnitMeasureCode)
ORDER BY length ASC
;

-- a UnitMeasureCode which is blank, or starts with a space.
-- no rows: every code starts with a character.
SELECT '[' + UnitMeasureCode + ']' AS UnitMeasureCode
FROM Production.UnitMeasure
WHERE TRIM(UnitMeasureCode) = ''
   OR UnitMeasureCode LIKE ' %'
;

-- a UnitMeasureCode with a lower case letter.
-- no rows: every code is upper case.
SELECT UnitMeasureCode, Name
FROM Production.UnitMeasure
WHERE UnitMeasureCode COLLATE Latin1_General_BIN <> UPPER(UnitMeasureCode)
;


-- ---------------------------------------------------------------------------------------------------------------- Name
-- the shortest and the longest Name; 4 and 20 characters, of the 50 the column allows.
SELECT MIN(LEN(Name)), MAX(LEN(Name))
FROM Production.UnitMeasure
;

-- a Name which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Name is trimmed and not blank.
SELECT UnitMeasureCode, '[' + Name + ']' AS Name
FROM Production.UnitMeasure
WHERE TRIM(Name) = ''
   OR DATALENGTH(Name) <> DATALENGTH(TRIM(Name))
;

-- AK_UnitMeasure_Name keeps Name unique.
-- no rows: no two units share a Name.
SELECT Name, COUNT(*) AS units
FROM Production.UnitMeasure
GROUP BY Name
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2019-04-30 00:00:00.000, a single moment.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.UnitMeasure
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the units in use, and by what; 6 units; CM for 176 sizes, G and LB for 29 and 176 weights, EA, IN and OZ for 2578, 9
-- and 92 bills of materials.
SELECT u.UnitMeasureCode,
       (SELECT COUNT(*) FROM Production.Product p WHERE p.SizeUnitMeasureCode = u.UnitMeasureCode) AS sizes,
       (SELECT COUNT(*) FROM Production.Product p WHERE p.WeightUnitMeasureCode = u.UnitMeasureCode) AS weights,
       (SELECT COUNT(*) FROM Production.BillOfMaterials b WHERE b.UnitMeasureCode = u.UnitMeasureCode) AS boms
FROM Production.UnitMeasure u
WHERE EXISTS (SELECT 1 FROM Production.Product p WHERE p.SizeUnitMeasureCode = u.UnitMeasureCode)
   OR EXISTS (SELECT 1 FROM Production.Product p WHERE p.WeightUnitMeasureCode = u.UnitMeasureCode)
   OR EXISTS (SELECT 1 FROM Production.BillOfMaterials b WHERE b.UnitMeasureCode = u.UnitMeasureCode)
ORDER BY u.UnitMeasureCode ASC
;

-- how many units nothing refers to; 32 of the 38.
SELECT COUNT(*) AS unused
FROM Production.UnitMeasure u
WHERE NOT EXISTS (SELECT 1 FROM Production.Product p WHERE p.SizeUnitMeasureCode = u.UnitMeasureCode)
  AND NOT EXISTS (SELECT 1 FROM Production.Product p WHERE p.WeightUnitMeasureCode = u.UnitMeasureCode)
  AND NOT EXISTS (SELECT 1 FROM Production.BillOfMaterials b WHERE b.UnitMeasureCode = u.UnitMeasureCode)
;

