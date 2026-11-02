package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.production;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;

import java.util.List;

/**
 * Randomizes a new {@link Illustration}; the generated identifier and what SQL Server fills are left out.
 */
class Illustration_Randomizer
        extends PodamObjectRandomizer<Illustration> {

    Illustration_Randomizer() {
        super(Illustration.class, List.of(
                Illustration.ATTRIBUTE_NAME_ILLUSTRATION_ID,
                Illustration.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }
}
