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
-- Profiling queries on Sales.SalesPersonQuotaHistory, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Sales_SalesPersonQuotaHistory.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows and sales persons; 163 quotas of all 17 sales persons.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM Sales.SalesPersonQuotaHistory
;

-- how many quotas each sales person has, and over which dates; 12 each for 274 to 283, from 2022-05-30, and 4 to 8
-- for the others, who joined later. All end at 2025-02-28.
SELECT BusinessEntityID, COUNT(*) AS quotas, MIN(QuotaDate), MAX(QuotaDate)
FROM Sales.SalesPersonQuotaHistory
GROUP BY BusinessEntityID
ORDER BY BusinessEntityID ASC
;


-- ----------------------------------------------------------------------------------------------------------- QuotaDate
-- how many quotas each QuotaDate has; 12 dates, a quarter apart from 2022-05-30 to 2025-02-28, 10 to 17 quotas each.
SELECT QuotaDate, COUNT(*) AS quotas
FROM Sales.SalesPersonQuotaHistory
GROUP BY QuotaDate
ORDER BY QuotaDate ASC
;

-- a QuotaDate with a time of day.
-- no rows: every QuotaDate is a midnight.
SELECT BusinessEntityID, QuotaDate
FROM Sales.SalesPersonQuotaHistory
WHERE CAST(QuotaDate AS time) <> '00:00'
;


-- ---------------------------------------------------------------------------------------------------------- SalesQuota
-- the range of SalesQuota, above 0 by CK_SalesPersonQuotaHistory_SalesQuota; 1000 to 1898000.
SELECT MIN(SalesQuota), MAX(SalesQuota)
FROM Sales.SalesPersonQuotaHistory
;

-- a SalesQuota which is not a whole thousand.
-- no rows: every SalesQuota is rounded to the thousand.
SELECT BusinessEntityID, QuotaDate, SalesQuota
FROM Sales.SalesPersonQuotaHistory
WHERE SalesQuota % 1000 <> 0
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_SalesPersonQuotaHistory_rowguid keeps rowguid unique.
-- no rows: no two quotas share a rowguid.
SELECT rowguid, COUNT(*) AS quotas
FROM Sales.SalesPersonQuotaHistory
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- how many days a quota was modified after its QuotaDate; -45 for all 163, each modified 45 days before its date.
SELECT DATEDIFF(day, QuotaDate, ModifiedDate) AS days, COUNT(*) AS quotas
FROM Sales.SalesPersonQuotaHistory
GROUP BY DATEDIFF(day, QuotaDate, ModifiedDate)
;

