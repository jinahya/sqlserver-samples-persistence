-- -
-- #%L
-- sqlserver-samples-persistence-futonmanufacturing
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
-- Profiling queries on the FutonManufacturing database as a whole, a section per subject. Each says what it looks for
-- and what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/__.sql
--


-- ---------------------------------------------------------------------------------------------------- tables and views
-- the tables and the views of each schema, and of the database, the last row; 32 tables and 40 views, 72 in all.
-- Tables/views by schema: dbo 32/40.
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


-- -------------------------------------------------------------------------------------------------------- descriptions
-- SQL Server keeps a comment on a table, a view or a column as an extended property, which AdventureWorks names
-- MS_Description and WideWorldImporters Description.

-- the extended properties of the database, of any name and on anything.
-- no rows: FutonManufacturing describes nothing, so its classes carry no @MsDescription.
SELECT p.class_desc, p.name, COUNT(*) AS properties
FROM sys.extended_properties p
GROUP BY p.class_desc, p.name
;

