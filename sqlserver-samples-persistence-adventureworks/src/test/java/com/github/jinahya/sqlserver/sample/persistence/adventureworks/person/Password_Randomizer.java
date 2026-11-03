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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;

import java.util.List;

/**
 * Randomizes a new {@link Password}; the identifier and what it references, which the persister
 * sets, and what SQL Server fills are left out. The hash and the salt, which must not be
 * {@code null}, are set by {@link #get()} through {@link Password#setPassword()}.
 */
class Password_Randomizer
        extends PodamObjectRandomizer<Password> {

    Password_Randomizer() {
        super(Password.class, List.of(
                Password.ATTRIBUTE_NAME_BUSINESS_ENTITY_ID,
                Password.ATTRIBUTE_NAME_BUSINESS_ENTITY,
                Password.ATTRIBUTE_NAME_PASSWORD_HASH,
                Password.ATTRIBUTE_NAME_PASSWORD_SALT,
                Password.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a random hash and a random salt, which
     *         {@link Password#setPassword()} sets.
     */
    @Override
    public Password get() {
        final var instance = super.get();
        instance.setPassword();
        return instance;
    }
}
