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
-- Profiling queries on HumanResources.JobCandidate, a section per column. Each says what it looks for and what the
-- result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i adventureworks /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_JobCandidate.sql
--


-- ------------------------------------------------------------------------------------------------------ JobCandidateID
-- how many rows, and the smallest and the largest JobCandidateID; 13 candidates, 1 to 13.
SELECT COUNT(*), MIN(JobCandidateID), MAX(JobCandidateID)
FROM HumanResources.JobCandidate
;


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many candidates were hired, a BusinessEntityID of an employee, and how many were not; 2 were hired, 11 were not.
SELECT COUNT(BusinessEntityID) AS hired, COUNT(*) - COUNT(BusinessEntityID) AS nulls
FROM HumanResources.JobCandidate
;

-- an employee who is two candidates.
-- no rows: no employee was a candidate twice.
SELECT BusinessEntityID, COUNT(*) AS candidates
FROM HumanResources.JobCandidate
WHERE BusinessEntityID IS NOT NULL
GROUP BY BusinessEntityID
HAVING COUNT(*) > 1
;

-- the hired candidates, against the employees they became; 4 became 274, a North American Sales Manager hired on
-- 2011-01-04, and 8 became 212, a Quality Assurance Supervisor hired on 2008-12-09.
SELECT c.JobCandidateID, c.BusinessEntityID, e.JobTitle, e.HireDate
FROM HumanResources.JobCandidate c
         JOIN HumanResources.Employee e ON e.BusinessEntityID = c.BusinessEntityID
ORDER BY c.JobCandidateID ASC
;


-- -------------------------------------------------------------------------------------------------------------- Resume
-- how many rows leave Resume NULL, and the smallest and the largest in bytes; every candidate has a Resume, of 3832 to
-- 8979 bytes.
SELECT COUNT(*) - COUNT(Resume) AS nulls, MIN(DATALENGTH(Resume)), MAX(DATALENGTH(Resume))
FROM HumanResources.JobCandidate
;

-- a Resume with no Name/Name.Last in the resume namespace.
-- no rows: every Resume names its candidate.
WITH XMLNAMESPACES (DEFAULT 'http://schemas.microsoft.com/sqlserver/2004/07/adventure-works/Resume')
         SELECT JobCandidateID
         FROM HumanResources.JobCandidate
         WHERE Resume.exist('/Resume/Name/Name.Last') = 0
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range of ModifiedDate; 2007-06-23 00:00:00.000 to 2013-12-22 18:32:21.313.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM HumanResources.JobCandidate
;

