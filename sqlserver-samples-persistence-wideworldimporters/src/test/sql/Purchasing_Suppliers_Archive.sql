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
-- Profiling queries on Purchasing.Suppliers_Archive, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i wideworldimporters /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d WideWorldImporters -W -s ' | ' < src/test/sql/Purchasing_Suppliers_Archive.sql
--


-- ---------------------------------------------------------------------------------------------------------- SupplierID
-- how many rows, how many suppliers they keep the history of, and the smallest and the largest SupplierID;
-- 13 versions of 13 suppliers, 1 to 13, one each.
SELECT COUNT(*), COUNT(DISTINCT SupplierID), MIN(SupplierID), MAX(SupplierID)
FROM Purchasing.Suppliers_Archive
;

-- an archived SupplierID with no current row.
-- no rows: every archived SupplierID is still current.
SELECT a.SupplierID
FROM Purchasing.Suppliers_Archive a
WHERE NOT EXISTS (SELECT 1
                  FROM Purchasing.Suppliers s
                  WHERE s.SupplierID = a.SupplierID)
;


-- -------------------------------------------------------------------------------------------------------- SupplierName
-- a SupplierName which is blank, or carries leading or trailing spaces.
-- no rows: every SupplierName is trimmed and not blank.
SELECT SupplierID, '[' + SupplierName + ']' AS SupplierName
FROM Purchasing.Suppliers_Archive
WHERE TRIM(SupplierName) = ''
   OR DATALENGTH(SupplierName) <> DATALENGTH(TRIM(SupplierName))
;


-- -------------------------------------------------------------------------------------------------- SupplierCategoryID
-- how many categories the versions are of; 8.
SELECT COUNT(DISTINCT SupplierCategoryID)
FROM Purchasing.Suppliers_Archive
;


-- ---------------------------------------------------------------------------------------------- PrimaryContactPersonID
-- the range of the primary contacts; person 21 to 45.
SELECT MIN(PrimaryContactPersonID), MAX(PrimaryContactPersonID)
FROM Purchasing.Suppliers_Archive
;


-- -------------------------------------------------------------------------------------------- AlternateContactPersonID
-- the range of the alternate contacts; person 22 to 46.
SELECT MIN(AlternateContactPersonID), MAX(AlternateContactPersonID)
FROM Purchasing.Suppliers_Archive
;


-- ---------------------------------------------------------------------------------------------------- DeliveryMethodID
-- DeliveryMethodID is nullable; how many versions have none. 4.
SELECT COUNT(*) - COUNT(DeliveryMethodID) AS without
FROM Purchasing.Suppliers_Archive
;


-- ------------------------------------------------------------------------------------------------------ DeliveryCityID
-- how many distinct cities the versions deliver from; 12.
SELECT COUNT(DISTINCT DeliveryCityID)
FROM Purchasing.Suppliers_Archive
;


-- -------------------------------------------------------------------------------------------------------- PostalCityID
-- a PostalCityID other than the DeliveryCityID.
-- no rows: every version posts and delivers in the same city.
SELECT SupplierID, DeliveryCityID, PostalCityID
FROM Purchasing.Suppliers_Archive
WHERE PostalCityID <> DeliveryCityID
;


-- --------------------------------------------------------------------------------------------------- SupplierReference
-- SupplierReference is nullable; how many versions have none. None.
SELECT COUNT(*) - COUNT(SupplierReference) AS without
FROM Purchasing.Suppliers_Archive
;


-- ----------------------------------------------------------------------------------------------------- BankAccountName
-- BankAccountName is nullable; how many versions have none. None.
SELECT COUNT(*) - COUNT(BankAccountName) AS without
FROM Purchasing.Suppliers_Archive
;


-- --------------------------------------------------------------------------------------------------- BankAccountBranch
-- BankAccountBranch is nullable; how many versions have none. None.
SELECT COUNT(*) - COUNT(BankAccountBranch) AS without
FROM Purchasing.Suppliers_Archive
;


-- ----------------------------------------------------------------------------------------------------- BankAccountCode
-- BankAccountCode is nullable; how many versions have none. None.
SELECT COUNT(*) - COUNT(BankAccountCode) AS without
FROM Purchasing.Suppliers_Archive
;


-- --------------------------------------------------------------------------------------------------- BankAccountNumber
-- BankAccountNumber is nullable; how many versions have none. None.
SELECT COUNT(*) - COUNT(BankAccountNumber) AS without
FROM Purchasing.Suppliers_Archive
;


-- ----------------------------------------------------------------------------------------------- BankInternationalCode
-- BankInternationalCode is nullable; how many versions have none. None.
SELECT COUNT(*) - COUNT(BankInternationalCode) AS without
FROM Purchasing.Suppliers_Archive
;


-- --------------------------------------------------------------------------------------------------------- PaymentDays
-- how many versions have each PaymentDays; 7, 14 and 30 days, as in the current rows.
SELECT PaymentDays, COUNT(*) AS versions
FROM Purchasing.Suppliers_Archive
GROUP BY PaymentDays
ORDER BY PaymentDays ASC
;


-- ---------------------------------------------------------------------------------------------------- InternalComments
-- InternalComments is nullable; how many versions have one. 2.
SELECT COUNT(InternalComments) AS with_comments
FROM Purchasing.Suppliers_Archive
;


-- --------------------------------------------------------------------------------------------------------- PhoneNumber
-- a PhoneNumber which is not of the form (999) 999-9999.
-- no rows: every PhoneNumber has that form.
SELECT SupplierID, PhoneNumber
FROM Purchasing.Suppliers_Archive
WHERE PhoneNumber NOT LIKE '([0-9][0-9][0-9]) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'
;


-- ----------------------------------------------------------------------------------------------------------- FaxNumber
-- a FaxNumber which is not of the form (999) 999-9999.
-- no rows: every FaxNumber has that form.
SELECT SupplierID, FaxNumber
FROM Purchasing.Suppliers_Archive
WHERE FaxNumber NOT LIKE '([0-9][0-9][0-9]) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'
;


-- ---------------------------------------------------------------------------------------------------------- WebsiteURL
-- a WebsiteURL which does not start with http://www.
-- no rows: every one does.
SELECT SupplierID, WebsiteURL
FROM Purchasing.Suppliers_Archive
WHERE WebsiteURL NOT LIKE 'http://www.%'
;


-- ------------------------------------------------------------------------------------------------ DeliveryAddressLine1
-- DeliveryAddressLine1 is NOT NULL; how many versions have it empty. 4, as in the current rows.
SELECT COUNT(*) AS versions
FROM Purchasing.Suppliers_Archive
WHERE DeliveryAddressLine1 = ''
;


-- ------------------------------------------------------------------------------------------------ DeliveryAddressLine2
-- DeliveryAddressLine2 is nullable; how many versions have none. None.
SELECT COUNT(*) - COUNT(DeliveryAddressLine2) AS without
FROM Purchasing.Suppliers_Archive
;


-- -------------------------------------------------------------------------------------------------- DeliveryPostalCode
-- a DeliveryPostalCode which is not five digits.
-- no rows: every code is five digits.
SELECT SupplierID, DeliveryPostalCode
FROM Purchasing.Suppliers_Archive
WHERE DeliveryPostalCode NOT LIKE '[0-9][0-9][0-9][0-9][0-9]'
;


-- ---------------------------------------------------------------------------------------------------- DeliveryLocation
-- DeliveryLocation is a nullable geography; how many versions have none. All 13; the current rows added it.
SELECT COUNT(*) - COUNT(DeliveryLocation) AS without
FROM Purchasing.Suppliers_Archive
;


-- -------------------------------------------------------------------------------------------------- PostalAddressLine1
-- a PostalAddressLine1 which is not of the form PO Box 9...
-- one row: Trey Research (11), PO  Box 595, with two spaces, as in the current row.
SELECT SupplierID, SupplierName, PostalAddressLine1
FROM Purchasing.Suppliers_Archive
WHERE PostalAddressLine1 NOT LIKE 'PO Box [0-9]%'
;


-- -------------------------------------------------------------------------------------------------- PostalAddressLine2
-- PostalAddressLine2 is nullable; how many versions have none. None.
SELECT COUNT(*) - COUNT(PostalAddressLine2) AS without
FROM Purchasing.Suppliers_Archive
;


-- ---------------------------------------------------------------------------------------------------- PostalPostalCode
-- a PostalPostalCode other than the DeliveryPostalCode.
-- no rows: every version posts and delivers under the same code.
SELECT SupplierID, DeliveryPostalCode, PostalPostalCode
FROM Purchasing.Suppliers_Archive
WHERE PostalPostalCode <> DeliveryPostalCode
;


-- -------------------------------------------------------------------------------------------------------- LastEditedBy
-- who edited the archived versions; person 1, Data Conversion Only, for all 13.
SELECT a.LastEditedBy, p.FullName, COUNT(*) AS versions
FROM Purchasing.Suppliers_Archive a
         JOIN Application.People p ON p.PersonID = a.LastEditedBy
GROUP BY a.LastEditedBy, p.FullName
ORDER BY a.LastEditedBy ASC
;


-- ----------------------------------------------------------------------------------------------------------- ValidFrom
-- the periods the archived versions were valid for; all 13 from 2013-01-01 00:00 to 00:05.
SELECT ValidFrom, ValidTo, COUNT(*) AS versions
FROM Purchasing.Suppliers_Archive
GROUP BY ValidFrom, ValidTo
ORDER BY ValidFrom ASC, ValidTo ASC
;


-- ------------------------------------------------------------------------------------------------------------- ValidTo
-- an archived version whose ValidTo is not where the current row of its supplier begins.
-- no rows: each version meets its current row without a gap or an overlap.
SELECT a.SupplierID, a.ValidTo, s.ValidFrom
FROM Purchasing.Suppliers_Archive a
         JOIN Purchasing.Suppliers s ON s.SupplierID = a.SupplierID
WHERE a.ValidTo <> s.ValidFrom
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an archived version which differs from the current row in any column but DeliveryLocation and the period.
-- no rows: the one change of every supplier, at 2013-01-01 00:05, set its DeliveryLocation.
SELECT a.SupplierID
FROM Purchasing.Suppliers_Archive a
         JOIN Purchasing.Suppliers s ON s.SupplierID = a.SupplierID
WHERE a.SupplierName <> s.SupplierName
   OR a.SupplierCategoryID <> s.SupplierCategoryID
   OR a.PrimaryContactPersonID <> s.PrimaryContactPersonID
   OR a.AlternateContactPersonID <> s.AlternateContactPersonID
   OR ISNULL(a.DeliveryMethodID, -1) <> ISNULL(s.DeliveryMethodID, -1)
   OR a.DeliveryCityID <> s.DeliveryCityID
   OR a.PostalCityID <> s.PostalCityID
   OR a.SupplierReference <> s.SupplierReference
   OR a.BankAccountName <> s.BankAccountName
   OR a.BankAccountBranch <> s.BankAccountBranch
   OR a.BankAccountCode <> s.BankAccountCode
   OR a.BankAccountNumber <> s.BankAccountNumber
   OR a.BankInternationalCode <> s.BankInternationalCode
   OR a.PaymentDays <> s.PaymentDays
   OR ISNULL(a.InternalComments, N'') <> ISNULL(s.InternalComments, N'')
   OR a.PhoneNumber <> s.PhoneNumber
   OR a.FaxNumber <> s.FaxNumber
   OR a.WebsiteURL <> s.WebsiteURL
   OR a.DeliveryAddressLine1 <> s.DeliveryAddressLine1
   OR a.DeliveryAddressLine2 <> s.DeliveryAddressLine2
   OR a.DeliveryPostalCode <> s.DeliveryPostalCode
   OR a.PostalAddressLine1 <> s.PostalAddressLine1
   OR a.PostalAddressLine2 <> s.PostalAddressLine2
   OR a.PostalPostalCode <> s.PostalPostalCode
   OR a.LastEditedBy <> s.LastEditedBy
;

