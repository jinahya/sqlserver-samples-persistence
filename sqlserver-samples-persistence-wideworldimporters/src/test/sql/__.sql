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
-- Profiling queries on the WideWorldImporters database as a whole, a section per subject. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/__.sql
--


-- ---------------------------------------------------------------------------------------------------- tables and views
-- the tables and the views of each schema, and of the database, the last row; 48 tables and 3 views, 51 in all.
-- Tables/views by schema: Application 15/0, Purchasing 7/0, Sales 12/0, Warehouse 14/0, Website 0/3.
SELECT COALESCE(SCHEMA_NAME(o.schema_id), '(all)') AS [schema],
       SUM(IIF(o.type = 'U', 1, 0))                AS tables,
       SUM(IIF(o.type = 'V', 1, 0))                AS views,
       COUNT(*)                                    AS total
FROM sys.objects o
WHERE o.type IN ('U', 'V')
  AND o.is_ms_shipped = 0
GROUP BY ROLLUP (o.schema_id)
ORDER BY GROUPING(o.schema_id) ASC, [schema] ASC
;


-- -------------------------------------------------------------------------------------------------- table descriptions
-- SQL Server keeps a comment as an extended property, which WideWorldImporters names Description; @MsDescription
-- documents it in the code, as Jakarta Persistence's @Table(comment) and @Column(comment) would.

-- the description of each table and view; 31 of the 48 tables have one, all but the 17 *_Archive tables, and none
-- of the 3 views.
SELECT SCHEMA_NAME(o.schema_id)        AS [schema],
       o.name,
       o.type,
       CAST(p.value AS nvarchar(4000)) AS description
FROM sys.objects o
    LEFT JOIN sys.extended_properties p
ON p.class = 1
    AND p.major_id = o.object_id
    AND p.minor_id = 0
    AND p.name = N'Description'
WHERE o.type IN ('U', 'V')
  AND o.is_ms_shipped = 0
ORDER BY [schema] ASC, o.name ASC
;


-- ------------------------------------------------------------------------------------------------- column descriptions
-- the description of each column, in the order its table or view declares them; 292 of the 557 columns of the
-- tables have one, and none of the 32 of the views. The 265 without are the columns of the *_Archive tables and the
-- bookkeeping columns, LastEditedBy, LastEditedWhen, ValidFrom and ValidTo.
SELECT SCHEMA_NAME(o.schema_id)        AS [schema],
       o.name                          AS [table],
       c.name                          AS [column],
       CAST(p.value AS nvarchar(4000)) AS description
FROM sys.columns c
    JOIN sys.objects o
ON o.object_id = c.object_id
    LEFT JOIN sys.extended_properties p
ON p.class = 1
    AND p.major_id = c.object_id
    AND p.minor_id = c.column_id
    AND p.name = N'Description'
WHERE o.type IN ('U', 'V')
  AND o.is_ms_shipped = 0
ORDER BY [schema] ASC, [table] ASC, c.column_id ASC
;

