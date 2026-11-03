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
-- Profiling queries on dbo.ReturnReason, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_ReturnReason.sql
--


-- ------------------------------------------------------------------------------------------------------ ReturnReasonID
-- how many rows, and the smallest and the largest ReturnReasonID; 8 reasons, 1 to 8.
SELECT COUNT(*), MIN(ReturnReasonID), MAX(ReturnReasonID)
FROM dbo.ReturnReason
;


-- ---------------------------------------------------------------------------------------------------------- ReasonCode
-- the codes, in the order of ReturnReasonID; DEFECT, DAMAGE, WRONG, NOFIT, EXPECT, CHANGE, LATE and OTHER.
SELECT ReturnReasonID, ReasonCode
FROM dbo.ReturnReason
ORDER BY ReturnReasonID ASC
;

-- the shortest and the longest ReasonCode; 4 and 6 characters, of the 20 the column allows.
SELECT MIN(LEN(ReasonCode)), MAX(LEN(ReasonCode))
FROM dbo.ReturnReason
;

-- a ReasonCode which is blank, or carries leading or trailing spaces.
-- no rows: every ReasonCode is trimmed and not blank.
SELECT ReturnReasonID, '[' + ReasonCode + ']' AS ReasonCode
FROM dbo.ReturnReason
WHERE TRIM(ReasonCode) = ''
   OR DATALENGTH(ReasonCode) <> DATALENGTH(TRIM(ReasonCode))
;

-- a unique constraint keeps ReasonCode unique.
-- no rows: every ReasonCode is distinct.
SELECT ReasonCode, COUNT(*) AS reasons
FROM dbo.ReturnReason
GROUP BY ReasonCode
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------- ReasonDescription
-- the shortest and the longest ReasonDescription; 12 and 31 characters, of the 255 the column allows.
SELECT MIN(LEN(ReasonDescription)), MAX(LEN(ReasonDescription))
FROM dbo.ReturnReason
;

-- a ReasonDescription which is blank, or carries leading or trailing spaces.
-- no rows: every ReasonDescription is trimmed and not blank.
SELECT ReturnReasonID, '[' + ReasonDescription + ']' AS ReasonDescription
FROM dbo.ReturnReason
WHERE TRIM(ReasonDescription) = ''
   OR DATALENGTH(ReasonDescription) <> DATALENGTH(TRIM(ReasonDescription))
;

-- nothing keeps ReasonDescription unique, yet no two rows share one.
-- no rows: every ReasonDescription is distinct.
SELECT ReasonDescription, COUNT(*) AS reasons
FROM dbo.ReturnReason
GROUP BY ReasonDescription
HAVING COUNT(*) > 1
;


-- ------------------------------------------------------------------------------------------------------------ IsActive
-- the values IsActive takes; all 8 reasons are active.
SELECT IsActive, COUNT(*) AS reasons
FROM dbo.ReturnReason
GROUP BY IsActive
ORDER BY IsActive ASC
;


-- ---------------------------------------------------------------------------------------------------------------------
-- the returns given each reason; only CHANGE (6) and DEFECT (1) are in use, once each.
SELECT r.ReturnReasonID, r.ReasonCode, COUNT(*) AS returns
FROM dbo.ReturnReason r
         JOIN dbo.SalesReturn sr ON sr.ReturnReasonID = r.ReturnReasonID
GROUP BY r.ReturnReasonID, r.ReasonCode
ORDER BY r.ReturnReasonID ASC
;

