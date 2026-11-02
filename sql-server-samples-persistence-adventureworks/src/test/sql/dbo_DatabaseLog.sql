-- -
-- #%L
-- sql-server-samples-persistence-adventureworks
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
-- Profiling queries on dbo.DatabaseLog, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/dbo_DatabaseLog.sql
--


-- ------------------------------------------------------------------------------------------------------- DatabaseLogID
-- how many rows, and the smallest and the largest DatabaseLogID; 927 events, 1 to 927.
SELECT COUNT(*), MIN(DatabaseLogID), MAX(DatabaseLogID)
FROM dbo.DatabaseLog
;

-- a DatabaseLogID missing between the smallest and the largest.
-- no rows: the DatabaseLogIDs run without a gap.
SELECT l.DatabaseLogID + 1 AS missing
FROM dbo.DatabaseLog l
WHERE l.DatabaseLogID < (SELECT MAX(DatabaseLogID) FROM dbo.DatabaseLog)
  AND NOT EXISTS (SELECT 1 FROM dbo.DatabaseLog n WHERE n.DatabaseLogID = l.DatabaseLogID + 1)
;


-- ------------------------------------------------------------------------------------------------------------ PostTime
-- the range of PostTime; 2025-11-14 13:01:06.967 to 13:05:34.813, the four and a half minutes the install script ran.
SELECT MIN(PostTime), MAX(PostTime)
FROM dbo.DatabaseLog
;

-- PostTime against DatabaseLogID: a later row posted earlier than the one before it.
-- no rows: PostTime never goes back as DatabaseLogID goes up.
SELECT TOP (5) l.DatabaseLogID, l.PostTime, p.PostTime AS previous
FROM dbo.DatabaseLog l
         JOIN dbo.DatabaseLog p ON p.DatabaseLogID = l.DatabaseLogID - 1
WHERE l.PostTime < p.PostTime
ORDER BY l.DatabaseLogID ASC
;


-- -------------------------------------------------------------------------------------------------------- DatabaseUser
-- the users who ran the logged statements; dbo alone, with all 927.
SELECT DatabaseUser, COUNT(*) AS events
FROM dbo.DatabaseLog
GROUP BY DatabaseUser
ORDER BY DatabaseUser ASC
;


-- --------------------------------------------------------------------------------------------------------------- Event
-- the kinds of event, the most frequent first; 17 kinds; CREATE_EXTENDED_PROPERTY with 538, then ALTER_TABLE with 117;
-- CREATE_FULLTEXT_CATALOG the rarest with 1.
SELECT Event, COUNT(*) AS events
FROM dbo.DatabaseLog
GROUP BY Event
ORDER BY events DESC, Event ASC
;


-- -------------------------------------------------------------------------------------------------------------- Schema
-- how many rows leave Schema NULL, and how many schemas; 1 NULL, and 8 schemas counting the blank one.
SELECT COUNT(*) - COUNT([Schema]) AS nulls, COUNT(DISTINCT [Schema]) AS schemas
FROM dbo.DatabaseLog
;

-- the events which leave Schema NULL; CREATE_FULLTEXT_CATALOG, the only event with no schema.
SELECT Event, COUNT(*) AS events
FROM dbo.DatabaseLog
WHERE [Schema] IS NULL
GROUP BY Event
ORDER BY Event ASC
;

-- the schemas, the most frequent first; Production with 276 and Sales with 247 lead; sys has 21, and 10 rows carry a
-- blank Schema.
SELECT [Schema], COUNT(*) AS events
FROM dbo.DatabaseLog
WHERE [Schema] IS NOT NULL
GROUP BY [Schema]
ORDER BY events DESC, [Schema] ASC
;

-- a Schema which is blank, or carries leading or trailing spaces; 10 rows, blank and not NULL; the 2
-- ALTER_AUTHORIZATION_DATABASE and 8 CREATE_EXTENDED_PROPERTY, on the database or on a schema itself.
SELECT Event, COUNT(*) AS events
FROM dbo.DatabaseLog
WHERE TRIM([Schema]) = ''
   OR DATALENGTH([Schema]) <> DATALENGTH(TRIM([Schema]))
GROUP BY Event
ORDER BY Event ASC
;


-- -------------------------------------------------------------------------------------------------------------- Object
-- how many rows leave Object NULL, and the longest Object; 22 NULLs; the longest Object has 68 characters.
SELECT COUNT(*) - COUNT(Object) AS nulls, MAX(LEN(Object)) AS longest
FROM dbo.DatabaseLog
;

-- a row which names a Schema but no Object, or an Object but no Schema; CREATE_FULLTEXT_CATALOG names an Object but no
-- Schema, and the 22 UPDATE_STATISTICS name a Schema but no Object.
SELECT Event, COUNT(*) AS events
FROM dbo.DatabaseLog
WHERE ([Schema] IS NULL AND Object IS NOT NULL)
   OR ([Schema] IS NOT NULL AND Object IS NULL)
GROUP BY Event
ORDER BY Event ASC
;

-- an Object which is blank, or carries leading or trailing spaces.
-- no rows: every Object is trimmed and not blank.
SELECT DatabaseLogID, '[' + Object + ']' AS Object
FROM dbo.DatabaseLog
WHERE TRIM(Object) = ''
   OR DATALENGTH(Object) <> DATALENGTH(TRIM(Object))
;


-- ---------------------------------------------------------------------------------------------------------------- TSQL
-- the shortest and the longest TSQL; 39 and 6752 characters.
SELECT MIN(LEN(TSQL)), MAX(LEN(TSQL))
FROM dbo.DatabaseLog
;


-- ------------------------------------------------------------------------------------------------------------ XmlEvent
-- the smallest and the largest XmlEvent in bytes; 906 and 14463 bytes.
SELECT MIN(DATALENGTH(XmlEvent)), MAX(DATALENGTH(XmlEvent))
FROM dbo.DatabaseLog
;

-- an XmlEvent whose EventType differs from Event.
-- no rows: the EventType in every XmlEvent is the Event of its row.
SELECT DatabaseLogID, Event, XmlEvent.value('(/EVENT_INSTANCE/EventType)[1]', 'nvarchar(128)') AS EventType
FROM dbo.DatabaseLog
WHERE XmlEvent.value('(/EVENT_INSTANCE/EventType)[1]', 'nvarchar(128)') <> Event
;

