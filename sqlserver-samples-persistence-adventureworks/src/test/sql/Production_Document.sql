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
-- Profiling queries on Production.Document, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/Production_Document.sql
--


-- -------------------------------------------------------------------------------------------------------- DocumentNode
-- how many rows, and the smallest and the largest DocumentNode, as paths; 12 documents, / to /3/4/.
SELECT COUNT(*), MIN(DocumentNode).ToString(), MAX(DocumentNode).ToString()
FROM Production.Document
;

-- every node, in depth-first order; the root folder, three folders under it, and eight files under those.
SELECT DocumentNode.ToString() AS DocumentNode, DocumentLevel, FolderFlag, Title
FROM Production.Document
ORDER BY DocumentNode ASC
;

-- a node whose parent is missing, the root aside.
-- no rows: every node hangs under a node of the table.
SELECT d.DocumentNode.ToString() AS DocumentNode
FROM Production.Document d
WHERE d.DocumentNode <> hierarchyid::GetRoot()
  AND NOT EXISTS (SELECT 1
                  FROM Production.Document p
                  WHERE p.DocumentNode = d.DocumentNode.GetAncestor(1))
;


-- ------------------------------------------------------------------------------------------------------- DocumentLevel
-- DocumentLevel is computed, DocumentNode.GetLevel(); the documents at each level; the root at 0, 3 at 1, 8 at 2.
SELECT DocumentLevel, COUNT(*) AS documents
FROM Production.Document
GROUP BY DocumentLevel
ORDER BY DocumentLevel ASC
;


-- --------------------------------------------------------------------------------------------------------------- Title
-- the shortest and the longest Title; 8 and 48 characters, of the 50 the column allows.
SELECT MIN(LEN(Title)), MAX(LEN(Title))
FROM Production.Document
;

-- a Title which is blank, or carries leading or trailing spaces. LEN and = ignore trailing spaces, DATALENGTH does not.
-- no rows: every Title is trimmed and not blank.
SELECT DocumentNode.ToString() AS DocumentNode, '[' + Title + ']' AS Title
FROM Production.Document
WHERE TRIM(Title) = ''
   OR DATALENGTH(Title) <> DATALENGTH(TRIM(Title))
;

-- the schema has no unique key on Title; a Title more than one document has.
-- no rows: every Title is unique.
SELECT Title, COUNT(*) AS documents
FROM Production.Document
GROUP BY Title
HAVING COUNT(*) > 1
;


-- --------------------------------------------------------------------------------------------------------------- Owner
-- the owners, and the documents of each; 3 owners, 217, 219 and 220, with 4 each.
SELECT Owner, COUNT(*) AS documents
FROM Production.Document
GROUP BY Owner
ORDER BY Owner ASC
;


-- ---------------------------------------------------------------------------------------------------------- FolderFlag
-- the folders and the files; 8 files (0) and 4 folders (1).
SELECT FolderFlag, COUNT(*) AS documents
FROM Production.Document
GROUP BY FolderFlag
ORDER BY FolderFlag ASC
;

-- a file with a child node.
-- no rows: only folders have children.
SELECT d.DocumentNode.ToString() AS DocumentNode, d.Title
FROM Production.Document d
WHERE d.FolderFlag = 0
  AND EXISTS (SELECT 1
              FROM Production.Document c
              WHERE c.DocumentNode.GetAncestor(1) = d.DocumentNode)
;


-- ------------------------------------------------------------------------------------------------------------ FileName
-- the shortest and the longest FileName; 8 and 52 characters, of the 400 the column allows.
SELECT MIN(LEN(FileName)), MAX(LEN(FileName))
FROM Production.Document
;

-- a FileName which does not end with its FileExtension.
-- no rows: every FileName ends with its FileExtension.
SELECT DocumentNode.ToString() AS DocumentNode, FileName, FileExtension
FROM Production.Document
WHERE FileName NOT LIKE '%' + FileExtension
;


-- ------------------------------------------------------------------------------------------------------- FileExtension
-- the extensions, and the documents of each; 8 .doc files, and 4 folders with an empty extension.
SELECT '[' + FileExtension + ']' AS FileExtension, COUNT(*) AS documents
FROM Production.Document
GROUP BY FileExtension
ORDER BY FileExtension ASC
;


-- ------------------------------------------------------------------------------------------------------------ Revision
-- the revisions; 0 for 7 documents; 1, 2, 3, 4 and 8 for one each.
SELECT Revision, COUNT(*) AS documents
FROM Production.Document
GROUP BY Revision
ORDER BY Revision ASC
;

-- IX_Document_FileName_Revision is not unique; a FileName and Revision more than one document has.
-- no rows: no two documents share a FileName and Revision.
SELECT FileName, Revision, COUNT(*) AS documents
FROM Production.Document
GROUP BY FileName, Revision
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ChangeNumber
-- the smallest and the largest ChangeNumber; 0 and 55.
SELECT MIN(ChangeNumber), MAX(ChangeNumber)
FROM Production.Document
;


-- -------------------------------------------------------------------------------------------------------------- Status
-- the statuses CK_Document_Status keeps within 1 to 3; 1 for 2 documents, 2 for 10; none is 3.
SELECT Status, COUNT(*) AS documents
FROM Production.Document
GROUP BY Status
ORDER BY Status ASC
;


-- ----------------------------------------------------------------------------------------------------- DocumentSummary
-- DocumentSummary is a nullable nvarchar(max); how many documents have none, and how long the others are; 7 have none;
-- 206 to 392 characters.
SELECT COUNT(*) - COUNT(DocumentSummary) AS without,
       MIN(LEN(DocumentSummary)),
       MAX(LEN(DocumentSummary))
FROM Production.Document
;


-- ------------------------------------------------------------------------------------------------------------ Document
-- Document is a nullable varbinary(max); how many documents have none, and how large the others are; 4 have none, the
-- folders; 22528 to 46592 bytes.
SELECT COUNT(*) - COUNT(Document) AS without,
       MIN(DATALENGTH(Document)),
       MAX(DATALENGTH(Document))
FROM Production.Document
;

-- a folder with content, or a file without.
-- no rows: the folders have no content, and every file has.
SELECT DocumentNode.ToString() AS DocumentNode, FolderFlag, Title
FROM Production.Document
WHERE (FolderFlag = 1 AND Document IS NOT NULL)
   OR (FolderFlag = 0 AND Document IS NULL)
;


-- ------------------------------------------------------------------------------------------------------------- rowguid
-- AK_Document_rowguid keeps rowguid unique.
-- no rows: no two documents share a rowguid.
SELECT rowguid, COUNT(*) AS documents
FROM Production.Document
GROUP BY rowguid
HAVING COUNT(*) > 1
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range the installed rows carry; 2017-12-13 13:58:03.943 to 2024-05-29 00:00:00.000.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM Production.Document
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an owner who is not a current employee.
-- no rows: every owner is a current employee.
SELECT d.DocumentNode.ToString() AS DocumentNode, d.Owner
FROM Production.Document d
         JOIN HumanResources.Employee e ON e.BusinessEntityID = d.Owner
WHERE e.CurrentFlag = 0
;

-- the products of each document; the 8 files; /1/2/ and /2/2/ none, /2/1/ the most with 10.
SELECT d.DocumentNode.ToString() AS DocumentNode, d.Title, COUNT(pd.ProductID) AS products
FROM Production.Document d
         LEFT JOIN Production.ProductDocument pd ON pd.DocumentNode = d.DocumentNode
WHERE d.FolderFlag = 0
GROUP BY d.DocumentNode, d.Title
ORDER BY d.DocumentNode ASC
;

