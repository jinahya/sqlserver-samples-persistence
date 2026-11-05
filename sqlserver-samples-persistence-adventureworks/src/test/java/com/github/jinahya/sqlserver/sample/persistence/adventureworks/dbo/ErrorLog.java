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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.dbo.MappedErrorLog;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code dbo.ErrorLog} table.
 */
@Entity(name = "DboErrorLog")
@Table(schema = MappedErrorLog.TABLE_SCHEMA, name = MappedErrorLog.TABLE_NAME,
       comment = "Audit table tracking errors in the the AdventureWorks database that are caught "
                 + "by the CATCH block of a TRY...CATCH construct. Data is inserted by stored "
                 + "procedure dbo.uspLogError when it is executed from inside the CATCH block of a "
                 + "TRY...CATCH construct.")
public class ErrorLog
        extends MappedErrorLog {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ErrorLog() {
        super();
    }
}

