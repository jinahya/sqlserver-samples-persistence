package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.person;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.BusinessEntityContactId_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.BusinessEntityContact;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.BusinessEntityContact_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.ContactType_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.row;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_PERSON_B_03} of {@code src/test/sql-exercises/_Person/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_PERSON_B_03: An organization's contacts and their roles")
class AW_PERSON_B_03_PersistenceIT {

    private static final int BUSINESS_ENTITY_ID = 1492;

    // reads the lazy person and contact type, so it runs in the entity manager
    private void verify(final List<BusinessEntityContact> found) {
        assertThat(found).extracting(c -> row(c.getPerson().getBusinessEntityId(),
                                              c.getPerson().getFirstName(),
                                              c.getPerson().getLastName(),
                                              c.getContactType().getName()))
                .containsExactly(
                        "1491 | Paula | Moberly | Sales Associate",
                        "2054 | Charlene | Wojcik | Sales Manager",
                        "2080 | Jo | Zimmerman | Sales Agent"
                );
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            verify(entityManager.createQuery(
                    """
                            SELECT c
                            FROM PersonBusinessEntityContact c
                                     JOIN c.person p
                                     JOIN c.contactType t
                            WHERE c.id_.businessEntityId = :businessEntityId
                            ORDER BY p.lastName, p.firstName, p.businessEntityId,
                                     t.contactTypeId""",
                    BusinessEntityContact.class
            ).setParameter("businessEntityId", BUSINESS_ENTITY_ID).getResultList());
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(BusinessEntityContact.class);
            final var c = query.from(BusinessEntityContact.class);
            final var p = c.join(BusinessEntityContact_.person);
            final var t = c.join(BusinessEntityContact_.contactType);
            final var id = c.get(BusinessEntityContact_.id_);
            query.where(builder.equal(id.get(BusinessEntityContactId_.businessEntityId),
                                      BUSINESS_ENTITY_ID));
            query.orderBy(builder.asc(p.get(Person_.lastName)),
                          builder.asc(p.get(Person_.firstName)),
                          builder.asc(p.get(Person_.businessEntityId)),
                          builder.asc(t.get(ContactType_.contactTypeId)));
            verify(entityManager.createQuery(query).getResultList());
            return null;
        });
    }

}
