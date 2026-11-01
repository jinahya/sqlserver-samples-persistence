-- -
-- #%L
-- mssql-sample-databases-persistence-adventureworks
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
-- Profiling queries on HumanResources.EmployeePayHistory, a section per column. Each says what it looks for and what
-- the result should be; 'no rows' marks a check that passes when nothing comes back.
--
-- Run the file as a whole:
--   docker exec -i mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -I -S localhost -U sa -P 'Passw0rd!' \
--     -d AdventureWorks -W -s ' | ' < src/test/sql/HumanResources_EmployeePayHistory.sql
--


-- ---------------------------------------------------------------------------------------------------- BusinessEntityID
-- how many rows, and how many employees; 316 changes of 290 employees.
SELECT COUNT(*), COUNT(DISTINCT BusinessEntityID)
FROM HumanResources.EmployeePayHistory
;

-- how many pay changes an employee has, and how many employees have that many; 277 employees have 1, 13 have 3.
SELECT changes, COUNT(*) AS employees
FROM (SELECT BusinessEntityID, COUNT(*) AS changes
      FROM HumanResources.EmployeePayHistory
      GROUP BY BusinessEntityID) p
GROUP BY changes
ORDER BY changes ASC
;


-- ------------------------------------------------------------------------------------------------------ RateChangeDate
-- the range of RateChangeDate; 2006-06-30 to 2013-07-14.
SELECT MIN(RateChangeDate), MAX(RateChangeDate)
FROM HumanResources.EmployeePayHistory
;

-- a RateChangeDate with a time of day; 0: every RateChangeDate is a date at midnight.
SELECT COUNT(*)
FROM HumanResources.EmployeePayHistory
WHERE CAST(RateChangeDate AS time) <> '00:00'
;

-- an employee whose first RateChangeDate is not the HireDate; 2 rows; 224 got its first rate on 2008-01-08, a year
-- before its HireDate of 2009-01-07, and 250 on 2011-02-21, four days before 2011-02-25.
SELECT p.BusinessEntityID, p.first_change, e.HireDate
FROM (SELECT BusinessEntityID, MIN(RateChangeDate) AS first_change
      FROM HumanResources.EmployeePayHistory
      GROUP BY BusinessEntityID) p
         JOIN HumanResources.Employee e ON e.BusinessEntityID = p.BusinessEntityID
WHERE CAST(p.first_change AS date) <> e.HireDate
ORDER BY p.BusinessEntityID ASC
;


-- ---------------------------------------------------------------------------------------------------------------- Rate
-- the smallest and the largest Rate; CK_EmployeePayHistory_Rate holds it between 6.50 and 200.00; 6.5000 and 125.5000.
SELECT MIN(Rate), MAX(Rate)
FROM HumanResources.EmployeePayHistory
;

-- a Rate which goes down from one change to the next.
-- no rows: no rate ever goes down.
SELECT p.BusinessEntityID, p.RateChangeDate, p.Rate, p.previous
FROM (SELECT BusinessEntityID, RateChangeDate, Rate,
             LAG(Rate) OVER (PARTITION BY BusinessEntityID ORDER BY RateChangeDate) AS previous
      FROM HumanResources.EmployeePayHistory) p
WHERE p.Rate < p.previous
;


-- -------------------------------------------------------------------------------------------------------- PayFrequency
-- the frequencies; CK_EmployeePayHistory_PayFrequency allows 1 (monthly) and 2 (biweekly); 180 monthly and 136
-- biweekly.
SELECT PayFrequency, COUNT(*) AS changes, MIN(Rate), MAX(Rate)
FROM HumanResources.EmployeePayHistory
GROUP BY PayFrequency
ORDER BY PayFrequency ASC
;

-- an employee whose PayFrequency changes; 0: an employee keeps a single frequency.
SELECT COUNT(*)
FROM (SELECT BusinessEntityID
      FROM HumanResources.EmployeePayHistory
      GROUP BY BusinessEntityID
      HAVING COUNT(DISTINCT PayFrequency) > 1) p
;

-- PayFrequency against SalariedFlag of the employee; salaried employees are all paid biweekly; hourly ones 180 monthly
-- and 78 biweekly.
SELECT e.SalariedFlag, p.PayFrequency, COUNT(*) AS changes
FROM HumanResources.EmployeePayHistory p
         JOIN HumanResources.Employee e ON e.BusinessEntityID = p.BusinessEntityID
GROUP BY e.SalariedFlag, p.PayFrequency
ORDER BY e.SalariedFlag ASC, p.PayFrequency ASC
;


-- -------------------------------------------------------------------------------------------------------- ModifiedDate
-- the range of ModifiedDate; 2007-11-21 to 2014-06-30.
SELECT MIN(ModifiedDate), MAX(ModifiedDate)
FROM HumanResources.EmployeePayHistory
;

-- a ModifiedDate earlier than its RateChangeDate, and by how many days; 39 rows, modified 14 or 15 days before the rate
-- they record took effect.
SELECT COUNT(*), MIN(DATEDIFF(DAY, ModifiedDate, RateChangeDate)), MAX(DATEDIFF(DAY, ModifiedDate, RateChangeDate))
FROM HumanResources.EmployeePayHistory
WHERE ModifiedDate < RateChangeDate
;


-- ---------------------------------------------------------------------------------------------------------------------
-- an employee with no pay history.
-- no rows: every employee has at least one rate.
SELECT e.BusinessEntityID, e.JobTitle
FROM HumanResources.Employee e
WHERE NOT EXISTS (SELECT 1
                  FROM HumanResources.EmployeePayHistory p
                  WHERE p.BusinessEntityID = e.BusinessEntityID)
;

