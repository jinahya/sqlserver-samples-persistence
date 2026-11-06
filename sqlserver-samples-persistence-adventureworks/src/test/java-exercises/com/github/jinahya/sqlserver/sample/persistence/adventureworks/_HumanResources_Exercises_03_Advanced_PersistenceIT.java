package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_HumanResources/03_advanced.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _HumanResources_Exercises_03_Advanced_PersistenceIT {

    @DisplayName("AW_HUMANRESOURCES_A_01: the chain of managers of an employee, by hierarchyid")
    @Nested
    class AW_HUMANRESOURCES_A_01_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found =
                    apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
                            """
                                    SELECT m.OrganizationNode.ToString() AS Node, m.OrganizationLevel, m.JobTitle,
                                           p.FirstName, p.LastName
                                    FROM HumanResources.Employee e
                                             JOIN HumanResources.Employee m
                                                  ON e.OrganizationNode.IsDescendantOf(m.OrganizationNode) = 1
                                             JOIN Person.Person p ON p.BusinessEntityID = m.BusinessEntityID
                                    WHERE e.BusinessEntityID = ?1
                                    ORDER BY m.OrganizationLevel"""
                    ).setParameter(1, 100).getResultList());
            assertThat(rows(found)).containsExactly(
                    "/3/ | 1 | Vice President of Production | James | Hamilton",
                    "/3/1/ | 2 | Production Control Manager | Peter | Krebs",
                    "/3/1/9/ | 3 | Production Supervisor - WC50 | Kok-Ho | Loh",
                    "/3/1/9/7/ | 4 | Production Technician - WC50 | Lolan | Song"
            );
        }
    }
}
