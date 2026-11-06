package com.github.jinahya.sqlserver.sample.persistence.adventureworks.person;

/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
 * %%
 * Copyright (C) 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils;
import com.github.jinahya.sqlserver.sample.persistence.shared._Entity_PersistenceIT;
import com.github.jinahya.sqlserver.sample.persistence.shared.___Validation_TestUtils;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.EmailAddressId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;

class EmailAddress_PersistenceIT
        extends _Entity_PersistenceIT<EmailAddress, EmailAddressId> {

    EmailAddress_PersistenceIT() {
        super(EmailAddress.class, EmailAddressId.class, ___PersistenceIT_Utils.PERSISTENCE_UNIT_NAME);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Verifies that every installed e-mail address is one, as its {@link jakarta.validation.constraints.Email @Email}
     * requires, and that a string which is not one is rejected.
     */
    @DisplayName("every installed e-mail address is valid as @Email")
    @Test
    void emailAddress_Valid_Installed() {
        final var validator = ___Validation_TestUtils.applyValidator(v -> v);
        assertThat(validator.validateValue(EmailAddress.class, EmailAddress.ATTRIBUTE_NAME_EMAIL_ADDRESS, "not an address"))
                .isNotEmpty();
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var addresses = em.createQuery("""
                                                         SELECT e.emailAddress
                                                         FROM PersonEmailAddress e""", String.class)
                    .getResultList();
            assertThat(addresses).isNotEmpty();
            assertThat(addresses)
                    .filteredOn(a -> !validator.validateValue(
                            EmailAddress.class, EmailAddress.ATTRIBUTE_NAME_EMAIL_ADDRESS, a).isEmpty())
                    .isEmpty();
            return null;
        }));
    }
}
