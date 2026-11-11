package com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales;

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
import com.github.jinahya.sqlserver.sample.persistence.shared._Keyless_PersistenceIT;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.IndividualSurveyStringAttributeConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;

class IndividualCustomer_PersistenceIT
        extends _Keyless_PersistenceIT<IndividualCustomer> {

    IndividualCustomer_PersistenceIT() {
        super(IndividualCustomer.class, ___PersistenceIT_Utils.PERSISTENCE_UNIT_NAME);
    }

    // -------------------------------------------------------------------------------- demographics
    // the view is keyless, and read by a native query, which returns the column's text; the
    // converter is what turns it into the IndividualSurvey the constructor takes
    @DisplayName("the converter binds every Demographics of the view, and back")
    @Test
    void demographics_converter() {
        final var converter = new IndividualSurveyStringAttributeConverter();
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            @SuppressWarnings("unchecked")
            final var texts = (List<String>) em.createNativeQuery("""
                                                                          SELECT CONVERT(nvarchar(max), Demographics)
                                                                          FROM Sales.vIndividualCustomer
                                                                          WHERE Demographics IS NOT NULL""")
                    .getResultList();
            assertThat(texts).isNotEmpty();
            for (final var text : texts) {
                final var value = converter.convertToEntityAttribute(text);
                assertThat(value).as("the value of %s", text).isNotNull();
                final var written = converter.convertToDatabaseColumn(value);
                assertThat(converter.convertToDatabaseColumn(
                        converter.convertToEntityAttribute(written)))
                        .as("the text written back of %s", text)
                        .isEqualTo(written);
            }
            return null;
        }));
    }
}
