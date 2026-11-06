package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Department_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeeDepartmentHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeeDepartmentHistory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_HumanResources/01_basic.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _HumanResources_Exercises_01_Basic_PersistenceIT {

    @DisplayName("AW_HUMANRESOURCES_B_01: the current employees of each department")
    @Nested
    class AW_HUMANRESOURCES_B_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "Production | Manufacturing | 179",
                    "Sales | Sales and Marketing | 18",
                    "Purchasing | Inventory Management | 12",
                    "Finance | Executive General and Administration | 10",
                    "Information Services | Executive General and Administration | 10",
                    "Marketing | Sales and Marketing | 9",
                    "Facilities and Maintenance | Executive General and Administration | 7",
                    "Engineering | Research and Development | 6",
                    "Human Resources | Executive General and Administration | 6",
                    "Production Control | Manufacturing | 6",
                    "Quality Assurance | Quality Assurance | 6",
                    "Shipping and Receiving | Inventory Management | 6",
                    "Document Control | Quality Assurance | 5",
                    "Research and Development | Research and Development | 4",
                    "Tool Design | Research and Development | 4",
                    "Executive | Executive General and Administration | 2"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT d.name, d.groupName, COUNT(h)
                            FROM HumanResourcesEmployeeDepartmentHistory h
                                     JOIN h.department d
                            WHERE h.endDate IS NULL
                            GROUP BY d.id_, d.name, d.groupName
                            ORDER BY COUNT(h) DESC, d.name""",
                    Object[].class
            ).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var h = query.from(EmployeeDepartmentHistory.class);
                final var d = h.join(EmployeeDepartmentHistory_.department);
                final var employees = builder.count(h);
                query.multiselect(d.get(Department_.name), d.get(Department_.groupName), employees);
                query.where(builder.isNull(h.get(EmployeeDepartmentHistory_.endDate)));
                query.groupBy(d.get(Department_.id_), d.get(Department_.name),
                              d.get(Department_.groupName));
                query.orderBy(builder.desc(employees), builder.asc(d.get(Department_.name)));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }

    @DisplayName("AW_HUMANRESOURCES_B_02: the employees at each level of the organization")
    @Nested
    class AW_HUMANRESOURCES_B_02_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "NULL | 1",
                    "1 | 6",
                    "2 | 27",
                    "3 | 66",
                    "4 | 190"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT e.organizationLevel, COUNT(e)
                            FROM HumanResourcesEmployee e
                            GROUP BY e.organizationLevel
                            ORDER BY e.organizationLevel""",
                    Object[].class
            ).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var e = query.from(Employee.class);
                final var level = e.get(Employee_.organizationLevel);
                query.multiselect(level, builder.count(e));
                query.groupBy(level);
                query.orderBy(builder.asc(level));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }
}
