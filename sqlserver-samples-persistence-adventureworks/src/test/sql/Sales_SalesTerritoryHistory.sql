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
-- Profiling queries on Sales.SalesTerritoryHistory, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SalesTerritoryHistory.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows and sales persons; 17 rows of 14 sales persons, the 3 managers having none.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM Sales.SalesTerritoryHistory
;

-- a sales person with no history.
-- 274, 285 and 287, the managers with no territory.
SELECT s.BusinessEntityID, s.TerritoryID
FROM Sales.SalesPerson s
WHERE NOT EXISTS (SELECT 1
                  FROM Sales.SalesTerritoryHistory h
                  WHERE h.BusinessEntityID = s.BusinessEntityID)
ORDER BY s.BusinessEntityID ASC
;


-- --------------------------------------------------------------------------------------------------------- TerritoryID
-- a sales person whose current TerritoryID is not that of the history row still open; 5: 275 (2, history 3), 277
-- (3, history 2), 282 (6, history 10) and 289 (10, history 6) swapped, and 280 (1) whose history ended.
SELECT s.BusinessEntityID, s.TerritoryID, h.TerritoryID AS open_territory
FROM Sales.SalesPerson s
         LEFT JOIN Sales.SalesTerritoryHistory h ON h.BusinessEntityID = s.BusinessEntityID
    AND h.EndDate IS NULL
WHERE s.TerritoryID IS NOT NULL
  AND (h.TerritoryID IS NULL OR h.TerritoryID <> s.TerritoryID)
ORDER BY s.BusinessEntityID ASC
;


-- ----------------------------------------------------------------------------------------------------------- StartDate
-- the range of StartDate; 2022-05-30 to 2024-05-29, 9 rows on the first day.
SELECT MIN(StartDate), MAX(StartDate), SUM(CASE WHEN StartDate = '2022-05-30' THEN 1 ELSE 0 END) AS first_day
FROM Sales.SalesTerritoryHistory
;


-- ------------------------------------------------------------------------------------------------------------- EndDate
-- EndDate is nullable; how many rows are still open, and the range of the others. 13; 2023-05-29 to 2023-11-29.
SELECT COUNT(*) - COUNT(EndDate) AS open_rows, MIN(EndDate), MAX(EndDate)
FROM Sales.SalesTerritoryHistory
;

-- two rows of a sales person whose dates overlap.
-- no rows: a sales person is in one territory at a time.
SELECT a.BusinessEntityID, a.StartDate, a.EndDate, b.StartDate, b.EndDate
FROM Sales.SalesTerritoryHistory a
         JOIN Sales.SalesTerritoryHistory b ON b.BusinessEntityID = a.BusinessEntityID
    AND b.StartDate > a.StartDate
WHERE a.EndDate IS NULL
   OR a.EndDate >= b.StartDate
;

-- a closed row not followed the next day by another of the same sales person; 280, whose row ended 2023-09-29.
SELECT a.BusinessEntityID, a.TerritoryID, a.EndDate
FROM Sales.SalesTerritoryHistory a
WHERE a.EndDate IS NOT NULL
  AND NOT EXISTS (SELECT 1
                  FROM Sales.SalesTerritoryHistory b
                  WHERE b.BusinessEntityID = a.BusinessEntityID
                    AND b.StartDate = DATEADD(day, 1, a.EndDate))
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_SalesTerritoryHistory_rowguid keeps rowguid unique.
-- no rows: no two rows share a rowguid.
SELECT rowguid, COUNT(*) AS records
FROM Sales.SalesTerritoryHistory
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- how many days a row was modified after the later of its dates; -7 for all 17, each a week before.
SELECT DATEDIFF(day, COALESCE(EndDate, StartDate), ModifiedDate) AS days, COUNT(*) AS records
FROM Sales.SalesTerritoryHistory
GROUP BY DATEDIFF(day, COALESCE(EndDate, StartDate), ModifiedDate)
;

