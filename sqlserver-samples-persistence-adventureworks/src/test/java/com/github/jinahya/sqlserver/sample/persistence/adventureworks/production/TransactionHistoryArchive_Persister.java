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

import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import jakarta.persistence.EntityManager;

import java.util.Optional;

/**
 * Persists a new {@link TransactionHistoryArchive}, which references nothing, with the next
 * identifier.
 */
class TransactionHistoryArchive_Persister
        extends AbstractEntityPersister<TransactionHistoryArchive> {

    TransactionHistoryArchive_Persister() {
        super(TransactionHistoryArchive.class);
    }

    // ---------------------------------------------------------------------------------------------
    // TransactionID is neither an identity nor generated: the archive takes the identifiers of
    // the rows moved to it
    @Override
    public TransactionHistoryArchive apply(final EntityManager entityManager,
                                           final TransactionHistoryArchive entityInstance) {
        final var max = entityManager.createQuery("""
                                                          SELECT MAX(t.transactionId)
                                                          FROM ProductionTransactionHistoryArchive t""",
                                                  Integer.class)
                .getSingleResult();
        entityInstance.setTransactionId(Optional.ofNullable(max).orElse(0) + 1);
        return super.apply(entityManager, entityInstance);
    }
}
