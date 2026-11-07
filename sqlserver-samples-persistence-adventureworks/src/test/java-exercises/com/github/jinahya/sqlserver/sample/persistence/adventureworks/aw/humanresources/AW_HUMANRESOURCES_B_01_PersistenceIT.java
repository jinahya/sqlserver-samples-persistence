package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.humanresources;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Department_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeeDepartmentHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeeDepartmentHistory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_HUMANRESOURCES_B_01} of
 * {@code src/test/sql-exercises/_HumanResources/01_basic.sql} with JPQL and with the Criteria API,
 * or with a native query where neither can.
 */
@DisplayName("AW_HUMANRESOURCES_B_01: The current employees of each department")
class AW_HUMANRESOURCES_B_01_PersistenceIT {

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
