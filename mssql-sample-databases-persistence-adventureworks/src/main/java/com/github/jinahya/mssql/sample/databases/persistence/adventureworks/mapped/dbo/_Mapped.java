package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.dbo;

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

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.AWBuildVersion} table.
 */
@MappedSuperclass
public abstract class _Mapped {

    /**
     * The name of the identifier column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ID_ = "id_";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ID_} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_ = "id_";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _Mapped() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // Equal by the surrogate key alone, read through the getter so that a lazy proxy answers with its identifier; an
    // EqualsVerifier check suppresses Warning.SURROGATE_KEY.
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof _Mapped that)) {
            return false;
        }
        return Objects.equals(getId_(), that.getId_());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId_());
    }

    // ------------------------------------------------------------------------------------------------------------- id_
    // not final, for a lazy proxy overrides it

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    public Long getId_() {
        return id_;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID_} attribute with the specified value.
     *
     * @param id_ new value for {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    protected void setId_(final Long id_) {
        this.id_ = id_;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ID_,
            nullable = false,
            insertable = true, // EclipseLink
            updatable = false
    )
    private Long id_;
}

