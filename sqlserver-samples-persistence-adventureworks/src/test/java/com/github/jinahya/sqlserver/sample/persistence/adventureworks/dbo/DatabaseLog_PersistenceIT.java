package com.github.jinahya.sqlserver.sample.persistence.adventureworks.dbo;

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
import io.github.jinahya.sqlserver.xml.schema.binding.b872ce.EventInstances;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;

class DatabaseLog_PersistenceIT
        extends _Entity_PersistenceIT<DatabaseLog, Long> {

    DatabaseLog_PersistenceIT() {
        super(DatabaseLog.class, Long.class);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Does nothing; the {@code ddlDatabaseTriggerLog} database trigger inserts every row of
     * {@code dbo.DatabaseLog}, on each DDL statement, and nothing else inserts into it.
     */
    @Disabled("written by the ddlDatabaseTriggerLog trigger: nothing else inserts into it")
    @Override
    @Test
    protected void persistRandomizedEntityInstance__() {
        // empty
    }

    // ------------------------------------------------------------------------------------ xmlEvent
    @DisplayName(
            "every xmlEvent is bound to the class of its event, and a flush with none of them " +
            "changed writes nothing")
    @Test
    void xmlEvent() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var builder = em.getCriteriaBuilder();
            final var query = builder.createQuery(DatabaseLog.class);
            final var logs =
                    em.createQuery(query.select(query.from(DatabaseLog.class))).getResultList();
            assertThat(logs).isNotEmpty();
            for (final var log : logs) {
                assertThat(log.getXmlEvent())
                        .as("the xmlEvent of %s", log)
                        .isInstanceOf(EventInstances.typeOf(log.getEvent()));
                assertThat(log.getXmlEvent().getEventType())
                        .as("the EventType of the xmlEvent of %s", log)
                        .isEqualTo(log.getEvent());
            }
            em.flush();
            // the log records the current transaction wrote; an UPDATE writes some, a read none
            final var written = (Number) em.createNativeQuery("""
                                                                      SELECT COALESCE(SUM(database_transaction_log_record_count), 0)
                                                                      FROM sys.dm_tran_database_transactions
                                                                      WHERE transaction_id = CURRENT_TRANSACTION_ID()
                                                                        AND database_id = DB_ID()""")
                    .getSingleResult();
            assertThat(written.longValue())
                    .as("the log records written by a flush of %d unchanged entities", logs.size())
                    .isZero();
            return null;
        }));
    }
}

