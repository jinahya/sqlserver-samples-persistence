package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesPerson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Sales/03_advanced.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _Sales_Exercises_03_Advanced_PersistenceIT {

    @DisplayName("AW_SALES_A_01: the sales of each sales person, a column per fiscal year")
    @Nested
    class AW_SALES_A_01_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found =
                    apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
                            """
                                    SELECT pvt.LastName, pvt.[2022], pvt.[2023], pvt.[2024], pvt.[2025]
                                    FROM (SELECT p.LastName, h.SubTotal, YEAR(DATEADD(MONTH, 6, h.OrderDate)) AS FiscalYear
                                          FROM Sales.SalesOrderHeader h
                                                   JOIN Person.Person p ON p.BusinessEntityID = h.SalesPersonID) AS s
                                             PIVOT (SUM(s.SubTotal) FOR s.FiscalYear IN ([2022], [2023], [2024], [2025])) AS pvt
                                    ORDER BY pvt.[2025] DESC"""
                    ).getResultList());
            assertThat(rows(found)).hasSize(17).startsWith(
                    "Pak | NULL | 963345.1079 | 4188307.0033 | 3351686.5345",
                    "Mitchell | 276440.8521 | 2742919.4886 | 4064078.2392 | 3283568.8466"
            ).endsWith(
                    "Abbas | NULL | NULL | NULL | 172524.4512"
            );
        }
    }

    @DisplayName("AW_SALES_A_02: the sales persons ranked within the group of their territory")
    @Nested
    class AW_SALES_A_02_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found =
                    apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
                            """
                                    SELECT t.[Group], t.Name AS Territory, p.LastName, sp.SalesYTD,
                                           RANK() OVER (PARTITION BY t.[Group] ORDER BY sp.SalesYTD DESC) AS GroupRank
                                    FROM Sales.SalesPerson sp
                                             JOIN Person.Person p ON p.BusinessEntityID = sp.BusinessEntityID
                                             JOIN Sales.SalesTerritory t ON t.TerritoryID = sp.TerritoryID
                                    ORDER BY t.[Group], GroupRank"""
                    ).getResultList());
            assertThat(rows(found)).hasSize(14).startsWith(
                    "Europe | United Kingdom | Pak | 4116871.2277 | 1",
                    "Europe | France | Varkey Chudukatil | 3121616.3202 | 2",
                    "Europe | Germany | Valdez | 1827066.7118 | 3",
                    "North America | Southwest | Mitchell | 4251368.5497 | 1"
            ).endsWith(
                    "Pacific | Australia | Tsoflias | 1421810.9242 | 1"
            );
        }
    }
}
