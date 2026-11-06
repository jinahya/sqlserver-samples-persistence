package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeePayHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeePayHistory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_HumanResources/02_intermediate.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _HumanResources_Exercises_02_Intermediate_PersistenceIT {

    @DisplayName("AW_HUMANRESOURCES_I_01: the ten highest current hourly rates")
    @Nested
    class AW_HUMANRESOURCES_I_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "Ken | Sánchez | Chief Executive Officer | 125.5 | 2009-01-14T00:00",
                    "James | Hamilton | Vice President of Production | 84.1346 | 2009-02-03T00:00",
                    "Brian | Welcker | Vice President of Sales | 72.1154 | 2011-02-15T00:00",
                    "Terri | Duffy | Vice President of Engineering | 63.4615 | 2008-01-31T00:00",
                    "Laura | Norman | Chief Financial Officer | 60.0962 | 2012-01-29T00:00",
                    "Dylan | Miller | Research and Development Manager | 50.4808 | " +
                    "2009-02-08T00:00",
                    "Jean | Trenary | Information Services Manager | 50.4808 | 2008-12-11T00:00",
                    "Stephen | Jiang | North American Sales Manager | 48.101 | 2011-01-04T00:00",
                    "Syed | Abbas | Pacific Sales Manager | 48.101 | 2013-03-14T00:00",
                    "Amy | Alberts | European Sales Manager | 48.101 | 2012-04-16T00:00"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT p.firstName, p.lastName, e.jobTitle, ph.rate, ph.rateChangeDate
                            FROM HumanResourcesEmployeePayHistory ph
                                     JOIN ph.businessEntity e
                                     JOIN e.businessEntity p
                            WHERE ph.rateChangeDate = (SELECT MAX(ph2.rateChangeDate)
                                                       FROM HumanResourcesEmployeePayHistory ph2
                                                       WHERE ph2.businessEntityId = ph.businessEntityId)
                            ORDER BY ph.rate DESC, e.businessEntityId""",
                    Object[].class
            ).setMaxResults(10).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var ph = query.from(EmployeePayHistory.class);
                final var e = ph.join(EmployeePayHistory_.businessEntity);
                final var p = e.join(Employee_.businessEntity);
                final var latest = query.subquery(LocalDateTime.class);
                final var ph2 = latest.from(EmployeePayHistory.class);
                latest.select(builder.greatest(ph2.get(EmployeePayHistory_.rateChangeDate)));
                latest.where(builder.equal(ph2.get(EmployeePayHistory_.businessEntityId),
                                           ph.get(EmployeePayHistory_.businessEntityId)));
                query.multiselect(p.get(Person_.firstName), p.get(Person_.lastName),
                                  e.get(Employee_.jobTitle),
                                  ph.get(EmployeePayHistory_.rate),
                                  ph.get(EmployeePayHistory_.rateChangeDate));
                query.where(builder.equal(ph.get(EmployeePayHistory_.rateChangeDate), latest));
                query.orderBy(builder.desc(ph.get(EmployeePayHistory_.rate)),
                              builder.asc(e.get(Employee_.businessEntityId)));
                return entityManager.createQuery(query).setMaxResults(10).getResultList();
            }));
        }
    }

    @DisplayName("AW_HUMANRESOURCES_I_02: the direct reports of a manager, by hierarchyid")
    @Nested
    class AW_HUMANRESOURCES_I_02_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found =
                    apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
                            """
                                    SELECT e.OrganizationNode.ToString() AS Node, e.JobTitle, p.FirstName, p.LastName
                                    FROM HumanResources.Employee e
                                             JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
                                    WHERE e.OrganizationNode.GetAncestor(1) = (SELECT m.OrganizationNode
                                                                               FROM HumanResources.Employee m
                                                                               WHERE m.BusinessEntityID = ?1)
                                    ORDER BY e.OrganizationNode"""
                    ).setParameter(1, 25).getResultList());
            assertThat(rows(found)).containsExactly(
                    "/3/1/ | Production Control Manager | Peter | Krebs",
                    "/3/2/ | Quality Assurance Manager | Hazem | Abolrous",
                    "/3/3/ | Master Scheduler | A. Scott | Wright",
                    "/3/4/ | Facilities Manager | Gary | Altman"
            );
        }
    }

    @DisplayName("AW_HUMANRESOURCES_I_03: The employees who worked in more than one department")
    @Nested
    class AW_HUMANRESOURCES_I_03_Test {
    }

    @DisplayName("AW_HUMANRESOURCES_I_04: Shift lengths across midnight")
    @Nested
    class AW_HUMANRESOURCES_I_04_Test {
    }
}
