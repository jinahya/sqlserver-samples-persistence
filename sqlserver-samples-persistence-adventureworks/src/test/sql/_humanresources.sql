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
-- Profiling queries on the HumanResources schema as a whole, a section per subject. Each says what it looks for and
-- what the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/_humanresources.sql
--


-- --------------------------------------------------------------------------------------------------------- alias types
-- the columns of the schema's tables and views which use an alias type, and the base type each maps as; 23 columns,
-- all of the dbo types: 5 in 3 tables (Department.Name and GroupName, and Shift.Name, dbo.Name; Employee.SalariedFlag
-- and CurrentFlag, dbo.Flag), and 18 in 3 views (dbo.Name, but vEmployee.PhoneNumber, dbo.Phone).
SELECT t.TABLE_TYPE, c.TABLE_NAME, c.COLUMN_NAME, c.DOMAIN_SCHEMA + '.' + c.DOMAIN_NAME AS alias, c.DATA_TYPE,
       c.CHARACTER_MAXIMUM_LENGTH, c.IS_NULLABLE
FROM INFORMATION_SCHEMA.COLUMNS c
         JOIN INFORMATION_SCHEMA.TABLES t ON t.TABLE_SCHEMA = c.TABLE_SCHEMA AND t.TABLE_NAME = c.TABLE_NAME
WHERE c.TABLE_SCHEMA = 'HumanResources'
  AND c.DOMAIN_NAME IS NOT NULL
ORDER BY t.TABLE_TYPE ASC, c.TABLE_NAME ASC, c.ORDINAL_POSITION ASC
;

