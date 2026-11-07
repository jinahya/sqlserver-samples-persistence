package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.humanresources;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_HUMANRESOURCES_B_02} of
 * {@code src/test/sql-exercises/_HumanResources/01_basic.sql} with JPQL and with the Criteria API,
 * or with a native query where neither can.
 */
@DisplayName("AW_HUMANRESOURCES_B_02: The employees at each level of the organization")
class AW_HUMANRESOURCES_B_02_PersistenceIT {

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
