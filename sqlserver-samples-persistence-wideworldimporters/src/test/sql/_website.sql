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
-- Profiling queries on the Website schema as a whole, a section per subject. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/_website.sql
--


-- ---------------------------------------------------------------------------------------------------- tables and views
-- the tables and the views of the schema; 0 tables and 3 views, 3 in all.
SELECT SUM(IIF(o.type = 'U', 1, 0)) AS tables,
       SUM(IIF(o.type = 'V', 1, 0)) AS views,
       COUNT(*)                     AS total
FROM sys.objects o
WHERE o.schema_id = SCHEMA_ID('Website')
  AND o.type IN ('U', 'V')
  AND o.is_ms_shipped = 0
;

-- each of them, the tables first.
SELECT IIF(o.type = 'U', 'table', 'view') AS kind,
       o.name
FROM sys.objects o
WHERE o.schema_id = SCHEMA_ID('Website')
  AND o.type IN ('U', 'V')
  AND o.is_ms_shipped = 0
ORDER BY o.type ASC, o.name ASC
;

