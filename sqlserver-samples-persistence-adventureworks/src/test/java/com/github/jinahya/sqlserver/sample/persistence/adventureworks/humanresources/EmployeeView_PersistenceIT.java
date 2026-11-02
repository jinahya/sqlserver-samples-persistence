package com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks._Entity_PersistenceIT;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___Persistence_TestUtils;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.AdditionalContactInfoStringAttributeConverter;
import io.github.jinahya.sqlserver.xml.schema.binding.b80e2e.ContactRecord;
import jakarta.xml.bind.JAXBElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeView_PersistenceIT extends _Entity_PersistenceIT<EmployeeView> {

    EmployeeView_PersistenceIT() {
        super(EmployeeView.class);
    }

    // ------------------------------------------------------------------------------------------- additionalContactInfo
    @DisplayName("every additionalContactInfo is read, and a flush with none of them changed writes nothing")
    @Test
    void additionalContactInfo() {
        ___Persistence_TestUtils.applyInTransactionAndRollback(em -> {
            final var builder = em.getCriteriaBuilder();
            final var query = builder.createQuery(EmployeeView.class);
            final var views = em.createQuery(query.select(query.from(EmployeeView.class))).getResultList();
            assertThat(views).isNotEmpty();
            em.flush();
            // the log records the current transaction wrote; an UPDATE writes some, a read none
            final var written = (Number) em.createNativeQuery("""
                    SELECT COALESCE(SUM(database_transaction_log_record_count), 0)
                    FROM sys.dm_tran_database_transactions
                    WHERE transaction_id = CURRENT_TRANSACTION_ID()
                      AND database_id = DB_ID()""").getSingleResult();
            assertThat(written.longValue())
                    .as("the log records written by a flush of %d unchanged entities", views.size())
                    .isZero();
            return null;
        });
    }

    // no employee has an AdditionalContactInfo, so the view's own column holds no value to convert; the column it reads
    // from, Person.Person.AdditionalContactInfo, does
    @DisplayName("the converter binds every Person.Person.AdditionalContactInfo, its content to the classes, and back")
    @Test
    void additionalContactInfo_converter() {
        final var converter = new AdditionalContactInfoStringAttributeConverter();
        ___Persistence_TestUtils.applyInTransactionAndRollback(em -> {
            @SuppressWarnings("unchecked")
            final var texts = (List<String>) em.createNativeQuery("""
                    SELECT CONVERT(nvarchar(max), AdditionalContactInfo)
                    FROM Person.Person
                    WHERE AdditionalContactInfo IS NOT NULL""").getResultList();
            assertThat(texts).isNotEmpty();
            for (final var text : texts) {
                final var value = converter.convertToEntityAttribute(text);
                assertThat(value.getContent())
                        .as("the content of %s", text)
                        .allSatisfy(c -> assertThat(c).isInstanceOfAny(
                                String.class, JAXBElement.class, ContactRecord.class));
                final var written = converter.convertToDatabaseColumn(value);
                assertThat(converter.convertToDatabaseColumn(converter.convertToEntityAttribute(written)))
                        .as("the text written back of %s", text)
                        .isEqualTo(written);
            }
            return null;
        });
    }
}
