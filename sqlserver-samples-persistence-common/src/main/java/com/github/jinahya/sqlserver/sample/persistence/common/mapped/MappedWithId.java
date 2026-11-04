package com.github.jinahya.sqlserver.sample.persistence.common.mapped;

/*-
 * #%L
 * sqlserver-samples-persistence-common
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

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;

/**
 * A mapped superclass for a table whose key is an {@link EmbeddedId @EmbeddedId}, every column of
 * which an entity derives from an association with {@link jakarta.persistence.MapsId @MapsId}.
 * <p>
 * The key starts {@code null}; the provider creates it, and fills it from the associations, when
 * the entity is persisted.
 *
 * @param <T> the type of the key.
 */
@MappedSuperclass
public abstract class MappedWithId<T> {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedWithId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The key, which a subclass need not repeat; a subclass appends its own
     *         attributes to what this method returns.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "id=" + getId()
               + "}";
    }

    // ------------------------------------------------------------------------------------------ id

    protected abstract T getId();
}
