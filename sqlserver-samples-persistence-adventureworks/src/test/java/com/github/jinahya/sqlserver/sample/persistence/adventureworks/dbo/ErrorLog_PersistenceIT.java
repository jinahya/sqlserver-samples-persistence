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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ErrorLog_PersistenceIT extends _Entity_PersistenceIT<ErrorLog> {

    ErrorLog_PersistenceIT() {
        super(ErrorLog.class);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Does nothing; {@code dbo.uspLogError} inserts every row of {@code dbo.ErrorLog}, and nothing else inserts into
     * it.
     */
    @Disabled("written by dbo.uspLogError: nothing else inserts into it")
    @Override
    @Test
    protected void persistTargetInstance() {
        // empty
    }
}
