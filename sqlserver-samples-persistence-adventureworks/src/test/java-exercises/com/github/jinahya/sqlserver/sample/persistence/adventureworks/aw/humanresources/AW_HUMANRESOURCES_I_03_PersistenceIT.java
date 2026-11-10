package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.humanresources;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeeDepartmentHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeeDepartmentHistory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.row;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_HUMANRESOURCES_I_03} of
 * {@code src/test/sql-exercises/_HumanResources/02_intermediate.sql} with JPQL and with the
 * Criteria API, or with a native query where neither can.
 *
 * <p>The person is selected, not grouped by: {@code GROUP BY p} lists every column of
 * {@code Person.Person}, and SQL Server does not group by its {@code xml} columns. A subquery
 * counts the departments instead.
 */
@DisplayName("AW_HUMANRESOURCES_I_03: The employees who worked in more than one department")
class AW_HUMANRESOURCES_I_03_PersistenceIT {

    private void verify(final List<Object[]> found) {
        assertThat(found).extracting(r -> {
            final var p = (Person) r[0];
            return row(p.getFirstName(), p.getLastName(), r[1]);
        }).containsExactly(
                "David | Bradley | 2",
                "Laura | Norman | 2",
                "William | Vong | 2",
                "Rob | Walters | 2",
                "Sheela | Word | 3"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT p,
                               (SELECT COUNT(DISTINCT h.departmentId)
                                FROM HumanResourcesEmployeeDepartmentHistory h
                                WHERE h.businessEntityId = p.businessEntityId) AS departments
                        FROM PersonPerson p
                        WHERE (SELECT COUNT(DISTINCT h.departmentId)
                               FROM HumanResourcesEmployeeDepartmentHistory h
                               WHERE h.businessEntityId = p.businessEntityId) > 1
                        ORDER BY p.lastName, p.firstName, p.businessEntityId""",
                Object[].class
        ).getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var p = query.from(Person.class);
            final var departments = query.subquery(Long.class);
            final var h = departments.from(EmployeeDepartmentHistory.class);
            departments.select(
                    builder.countDistinct(h.get(EmployeeDepartmentHistory_.departmentId)));
            departments.where(builder.equal(h.get(EmployeeDepartmentHistory_.businessEntityId),
                                            p.get(Person_.businessEntityId)));
            query.select(builder.array(p, departments));
            query.where(builder.gt(departments, 1L));
            query.orderBy(builder.asc(p.get(Person_.lastName)),
                          builder.asc(p.get(Person_.firstName)),
                          builder.asc(p.get(Person_.businessEntityId)));
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
