package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;

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
import com.github.jinahya.sqlserver.sample.persistence.common.Heirarchyid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Checks the {@code hierarchyid} DocumentNode columns of {@code Production.Document} and
 * {@code Production.ProductDocument} against what SQL Server returns for them.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class DocumentNode_PersistenceIT {

    @DisplayName("every installed Document.DocumentNode maps to what ToString and GetLevel return")
    @Test
    void document() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            @SuppressWarnings("unchecked")
            final List<Object[]> rows = em.createNativeQuery("""
                                                                     SELECT CAST(d.rowguid AS char(36)), d.DocumentNode.ToString(), d.DocumentLevel
                                                                     FROM Production.Document AS d""")
                    .getResultList();
            assumeThat(rows).isNotEmpty();
            for (final var row : rows) {
                final var document = em.find(Document.class, row[0]);
                assertThat(document).as("the Document of %s", row[0]).isNotNull();
                final var node = document.getDocumentNode();
                assertThat(node).as("DocumentNode of %s", row[0]).hasToString((String) row[1]);
                assertThat(node.getLevel()).isEqualTo(((Number) row[2]).intValue());
            }
            return null;
        });
    }

    @DisplayName("every installed ProductDocument.DocumentNode decodes to what ToString returns")
    @Test
    void productDocument() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            @SuppressWarnings("unchecked")
            final List<Object[]> rows = em.createNativeQuery("""
                                                                     SELECT pd.ProductID,
                                                                            CAST(pd.DocumentNode AS varbinary(892)),
                                                                            pd.ModifiedDate,
                                                                            pd.DocumentNode.ToString()
                                                                     FROM Production.ProductDocument AS pd""")
                    .getResultList();
            assumeThat(rows).isNotEmpty();
            for (final var row : rows) {
                final var productDocument = new ProductDocument(
                        ((Number) row[0]).intValue(), (byte[]) row[1],
                        row[2] instanceof Timestamp t ? t.toLocalDateTime() :
                                (LocalDateTime) row[2]);
                assertThat(productDocument.getDocumentNode()).hasToString((String) row[3]);
                assertThat(productDocument.getDocumentNode()).isEqualTo(
                        Heirarchyid.parse((String) row[3]));
            }
            return null;
        });
    }
}
