package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.humanresources;

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

import com.github.jinahya.mssql.sample.databases.persistence.common.MappedWithIdentityId;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.Shift} table.
 */
// not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
// the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
@AttributeOverride(name = MappedWithIdentityId.ATTRIBUTE_NAME_ID_,
                   column = @Column(name = MappedShift.COLUMN_NAME_SHIFT_ID,
                                    nullable = false,
                                    insertable = true,
                                    updatable = false
                   )
)
@MappedSuperclass
public abstract class MappedShift
        extends MappedWithIdentityId {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Shift";

    // --------------------------------------------------------------------------------------------------------- ShiftID

    /**
     * The name of the {@code tinyint, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIFT_ID = "ShiftID";

    // ------------------------------------------------------------------------------------------------------------ Name

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "Name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // ------------------------------------------------------------------------------------------------------- StartTime

    /**
     * The name of the {@code time, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_TIME = "StartTime";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_TIME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_TIME = "startTime";

    // --------------------------------------------------------------------------------------------------------- EndTime

    /**
     * The name of the {@code time, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_TIME = "EndTime";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_TIME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_TIME = "endTime";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedShift() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "shiftId=" + getShiftId()
               + ",name=" + name
               + ",startTime=" + startTime
               + ",endTime=" + endTime
               + ",modifiedDate=" + modifiedDate
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedShift that)) {
            return false;
        }
        return Objects.equals(getId_(), that.getId_());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getId_());
    }

    // --------------------------------------------------------------------------------------------------------- shiftId

    /**
     * Returns current value of the identifier, which maps the {@value #COLUMN_NAME_SHIFT_ID} column.
     *
     * @return current value of the identifier.
     */
    public Long getShiftId() {
        return getId_();
    }

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME} attribute with the specified value.
     *
     * @param name new value for {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public void setName(final String name) {
        this.name = name;
    }

    // ------------------------------------------------------------------------------------------------------- startTime

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_TIME} attribute.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_TIME} attribute with the specified value.
     *
     * @param startTime new value for {@value #ATTRIBUTE_NAME_START_TIME} attribute.
     */
    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }

    // --------------------------------------------------------------------------------------------------------- endTime

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_TIME} attribute.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_TIME} attribute with the specified value.
     *
     * @param endTime new value for {@value #ATTRIBUTE_NAME_END_TIME} attribute.
     */
    public void setEndTime(final LocalTime endTime) {
        this.endTime = endTime;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME
    )
    private String name;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_START_TIME,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalTime startTime;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_TIME,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalTime endTime;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

