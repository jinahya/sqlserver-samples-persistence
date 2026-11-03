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
-- Profiling queries on dbo.SalesReturn, a section per column. Each says what it looks for and what the result should
-- be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i futonmanufacturing /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d FutonManufacturing -W -s ' | ' < src/test/sql/dbo_SalesReturn.sql
--


-- ------------------------------------------------------------------------------------------------------------ ReturnID
-- how many rows, and the smallest and the largest ReturnID; 2 returns, 1 and 2.
SELECT COUNT(*), MIN(ReturnID), MAX(ReturnID)
FROM dbo.SalesReturn
;


-- -------------------------------------------------------------------------------------------------------- ReturnNumber
-- a ReturnNumber not in the form RET-YYYY-NNN, the year of the ReturnDate, or whose number is not the ReturnID.
-- no rows: every number is RET-2024- and the ReturnID in three digits.
SELECT ReturnID, ReturnNumber, ReturnDate
FROM dbo.SalesReturn
WHERE ReturnNumber NOT LIKE 'RET-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]'
   OR SUBSTRING(ReturnNumber, 5, 4) <> CAST(YEAR(ReturnDate) AS char(4))
   OR CAST(RIGHT(ReturnNumber, 3) AS int) <> ReturnID
;

-- the shortest and the longest ReturnNumber; 12 and 12 characters, of the 50 the column allows.
SELECT MIN(LEN(ReturnNumber)), MAX(LEN(ReturnNumber))
FROM dbo.SalesReturn
;

-- a ReturnNumber which is blank, or carries leading or trailing spaces.
-- no rows: every ReturnNumber is trimmed and not blank.
SELECT ReturnID, '[' + ReturnNumber + ']' AS ReturnNumber
FROM dbo.SalesReturn
WHERE TRIM(ReturnNumber) = ''
   OR DATALENGTH(ReturnNumber) <> DATALENGTH(TRIM(ReturnNumber))
;

-- a unique constraint keeps ReturnNumber unique.
-- no rows: every ReturnNumber is distinct.
SELECT ReturnNumber, COUNT(*) AS returns
FROM dbo.SalesReturn
GROUP BY ReturnNumber
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- SalesOrderID
-- the order each return is of, and its Status; SO-2024-1001 and SO-2024-1003, both Delivered.
SELECT r.ReturnID, o.OrderNumber, o.Status
FROM dbo.SalesReturn r
         JOIN dbo.SalesOrder o ON o.SalesOrderID = r.SalesOrderID
ORDER BY r.ReturnID ASC
;


-- ---------------------------------------------------------------------------------------------------------- CustomerID
-- a return whose CustomerID is not the customer of the order; nothing but the data keeps the two in step.
-- no rows: every return comes from the order's customer.
SELECT r.ReturnID, r.CustomerID, o.CustomerID AS order_customer
FROM dbo.SalesReturn r
         JOIN dbo.SalesOrder o ON o.SalesOrderID = r.SalesOrderID
WHERE r.CustomerID <> o.CustomerID
;


-- ---------------------------------------------------------------------------------------------------------- ReturnDate
-- the days from the ShipDate of the order to the ReturnDate; 6 for both.
SELECT r.ReturnID, o.ShipDate, r.ReturnDate, DATEDIFF(DAY, o.ShipDate, r.ReturnDate) AS days
FROM dbo.SalesReturn r
         JOIN dbo.SalesOrder o ON o.SalesOrderID = r.SalesOrderID
ORDER BY r.ReturnID ASC
;


-- ------------------------------------------------------------------------------------------------------ ReturnReasonID
-- the reason of each return; CHANGE for 1 and DEFECT for 2.
SELECT r.ReturnID, rr.ReasonCode
FROM dbo.SalesReturn r
         JOIN dbo.ReturnReason rr ON rr.ReturnReasonID = r.ReturnReasonID
ORDER BY r.ReturnID ASC
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- the values Status takes; Refunded for both.
SELECT Status, COUNT(*) AS returns
FROM dbo.SalesReturn
GROUP BY Status
;


-- -------------------------------------------------------------------------------------------------------- RefundAmount
-- a return whose RefundAmount is not the sum of the RefundAmount of its lines less the RestockingFee.
-- no rows: every RefundAmount adds up.
SELECT r.ReturnID, r.RefundAmount, r.RestockingFee, SUM(d.RefundAmount) AS lines
FROM dbo.SalesReturn r
         JOIN dbo.SalesReturnDetail d ON d.ReturnID = r.ReturnID
GROUP BY r.ReturnID, r.RefundAmount, r.RestockingFee
HAVING r.RefundAmount IS NULL
    OR r.RefundAmount <> SUM(d.RefundAmount) - r.RestockingFee
;


-- ------------------------------------------------------------------------------------------------------- RestockingFee
-- the values RestockingFee takes, NULL for none; .00 for both, even the change of mind.
SELECT RestockingFee, COUNT(*) AS returns
FROM dbo.SalesReturn
GROUP BY RestockingFee
;


-- --------------------------------------------------------------------------------------------------------------- Notes
-- how many Notes values are NULL, of all the rows; 2 of 2.
SELECT SUM(CASE WHEN Notes IS NULL THEN 1 ELSE 0 END), COUNT(*)
FROM dbo.SalesReturn
;


-- ---------------------------------------------------------------------------------------------------------- ApprovedBy
-- the values ApprovedBy takes, NULL for none; Manager1 and Manager2, one each.
SELECT ApprovedBy, COUNT(*) AS returns
FROM dbo.SalesReturn
GROUP BY ApprovedBy
ORDER BY ApprovedBy ASC
;


-- -------------------------------------------------------------------------------------------------------- ApprovedDate
-- a return approved on another day than it was made, or before.
-- no rows: every return was approved the day it was made.
SELECT ReturnID, ReturnDate, ApprovedDate
FROM dbo.SalesReturn
WHERE ApprovedDate IS NULL
   OR CAST(ApprovedDate AS date) <> ReturnDate
;


-- --------------------------------------------------------------------------------------------------------- CreatedDate
-- how many CreatedDate values are NULL, and how many fall outside the five minutes after the database was created;
-- none, and none: the default GETDATE() filled every one in while the load script ran, so it is the moment of
-- the load, not of a business event, and moves with every load.
SELECT SUM(CASE WHEN x.CreatedDate IS NULL THEN 1 ELSE 0 END),
       SUM(CASE WHEN x.CreatedDate NOT BETWEEN d.create_date AND DATEADD(MINUTE, 5, d.create_date) THEN 1 ELSE 0 END)
FROM dbo.SalesReturn x
         CROSS JOIN sys.databases d
WHERE d.name = DB_NAME()
;


-- ---------------------------------------------------------------------------------------------------------------------
-- a return with no line.
-- no rows: every return has lines.
SELECT r.ReturnID
FROM dbo.SalesReturn r
WHERE NOT EXISTS (SELECT 1
                  FROM dbo.SalesReturnDetail d
                  WHERE d.ReturnID = r.ReturnID)
;

