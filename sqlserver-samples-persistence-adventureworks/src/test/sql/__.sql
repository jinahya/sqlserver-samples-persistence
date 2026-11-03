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
-- Profiling queries on the AdventureWorks database as a whole, a section per subject. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/__.sql
--


-- --------------------------------------------------------------------------------------------------------- alias types
-- An alias type belongs to a database, and in it to a schema; columns of any schema in the same database can use it.
-- sys.types gives max_length in bytes, so an nvarchar alias holds half as many characters.

-- the alias types, and the base type each is defined on; 6, all in dbo:
--   AccountNumber nvarchar(15) NULL, Flag bit NOT NULL, Name nvarchar(50) NULL, NameStyle bit NOT NULL,
--   OrderNumber nvarchar(25) NULL, Phone nvarchar(25) NULL.
SELECT SCHEMA_NAME(t.schema_id) AS [schema],
       t.name,
       b.name                   AS base,
       t.max_length,
       t.precision,
       t.scale,
       t.is_nullable
FROM sys.types t
    JOIN sys.types b
ON b.user_type_id = t.system_type_id
WHERE t.is_user_defined = 1
  AND t.is_table_type = 0
ORDER BY [schema] ASC, t.name ASC
;

-- the columns, of the tables and the views, which use each alias type; Name the most, with 90 columns in 40 objects of
-- 5 schemas, and NameStyle and OrderNumber the least, with 1 column each.
SELECT c.DOMAIN_SCHEMA,
       c.DOMAIN_NAME,
       COUNT(*)                                            AS columns,
       COUNT(DISTINCT c.TABLE_SCHEMA + '.' + c.TABLE_NAME) AS objects,
       COUNT(DISTINCT c.TABLE_SCHEMA)                      AS schemas
FROM INFORMATION_SCHEMA.COLUMNS c
WHERE c.DOMAIN_NAME IS NOT NULL
GROUP BY c.DOMAIN_SCHEMA, c.DOMAIN_NAME
ORDER BY c.DOMAIN_SCHEMA ASC, c.DOMAIN_NAME ASC
;

-- an alias type which no column uses.
-- no rows: every alias type is in use.
SELECT SCHEMA_NAME(t.schema_id) AS [schema], t.name
FROM sys.types t
WHERE t.is_user_defined = 1
  AND t.is_table_type = 0
  AND NOT EXISTS (SELECT 1
    FROM sys.columns c
    WHERE c.user_type_id = t.user_type_id)
;



-- --------------------------------------------------------------------------------------------------- varbinary columns
-- A varbinary(max) holds up to 2^31-1 bytes, which a mapped byte[] reads whole; the size of the largest value says
-- whether that matters. A column's sizes take a query of its own table, so the statement is built from sys.columns.

-- each varbinary column of the tables, its declared length, its rows, its values, and its largest value in bytes; 3,
-- all max, in Production:
--   Document.Document 12 rows, 8 values, 46592 bytes; ProductPhoto.ThumbNailPhoto 101 rows, 101 values, 17623 bytes;
--   ProductPhoto.LargePhoto 101 rows, 101 values, 25925 bytes.
DECLARE @varbinary_sql nvarchar(max);
SELECT @varbinary_sql = STRING_AGG(
               CAST(N'SELECT N' + QUOTENAME(s.name, '''') + N' AS [schema], N' + QUOTENAME(t.name, '''')
                    + N' AS [table], N' + QUOTENAME(c.name, '''') + N' AS [column], '
                    + N'''' + IIF(c.max_length = -1, N'max', CAST(c.max_length AS nvarchar(4))) + N''''
                    + N' AS declared, COUNT(*) AS [rows], COUNT(' + QUOTENAME(c.name) + N') AS non_null, '
                    + N'MAX(DATALENGTH(' + QUOTENAME(c.name) + N')) AS max_bytes FROM '
                    + QUOTENAME(s.name) + N'.' + QUOTENAME(t.name) AS nvarchar(max)),
               N' UNION ALL ')
           WITHIN GROUP (ORDER BY s.name, t.name, c.column_id)
FROM sys.columns c
    JOIN sys.tables t
ON t.object_id = c.object_id
    JOIN sys.schemas s ON s.schema_id = t.schema_id
WHERE c.system_type_id = TYPE_ID('varbinary')
  AND t.is_ms_shipped = 0
;
EXEC sp_executesql @varbinary_sql
;
