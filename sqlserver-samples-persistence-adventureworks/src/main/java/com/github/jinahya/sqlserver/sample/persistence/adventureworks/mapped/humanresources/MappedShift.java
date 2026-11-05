package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources;

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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import com.github.jinahya.sqlserver.sample.persistence.common.mapped.MappedWithIdentityId;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.Shift} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code HumanResources.Shift}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ShiftID}</th><td>Primary key for Shift records.</td></tr>
 * <tr><th scope="row">{@code Name}</th><td>Shift description.</td></tr>
 * <tr><th scope="row">{@code StartTime}</th><td>Shift start time.</td></tr>
 * <tr><th scope="row">{@code EndTime}</th><td>Shift end time.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code HumanResources.Shift}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
// read-only: an identity column, which SQL Server assigns on an INSERT and does not update
// (error 8102)
@AttributeOverride(name = MappedWithIdentityId.ATTRIBUTE_NAME_ID_,
                   column = @Column(name = MappedShift.COLUMN_NAME_SHIFT_ID, nullable = false,
                                    insertable = false, updatable = false)
)
@MappedSuperclass
@MsDescription(
        schema = MappedShift.TABLE_SCHEMA,
        table = MappedShift.TABLE_NAME,
        value = "Work shift lookup table."
)
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

    // ------------------------------------------------------------------------------------- ShiftID

    /**
     * The name of the {@code tinyint, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIFT_ID = "ShiftID";

    // ---------------------------------------------------------------------------------------- Name

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "Name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // ----------------------------------------------------------------------------------- StartTime

    /**
     * The name of the {@code time, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_TIME = "StartTime";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_TIME} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_TIME = "startTime";

    // ------------------------------------------------------------------------------------- EndTime

    /**
     * The name of the {@code time, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_TIME = "EndTime";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_TIME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_TIME = "endTime";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedShift() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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
     * @implSpec Equality is by the identifier, once it is assigned; an instance whose identifier is
     *         not yet assigned, which persist does, equals only itself.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedShift that)) {
            return false;
        }
        return getId_() != null
               && getId_().equals(that.getId_());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is the same for every instance, so that it does not change when
     *         persist assigns the identifier; consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return MappedShift.class.hashCode();
    }

    // ------------------------------------------------------------------------------------- shiftId

    /**
     * Returns current value of the identifier, which maps the {@value #COLUMN_NAME_SHIFT_ID}
     * column.
     *
     * @return current value of the identifier.
     */
    public Long getShiftId() {
        return getId_();
    }

    // ---------------------------------------------------------------------------------------- name

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

    // ----------------------------------------------------------------------------------- startTime

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_TIME} attribute.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_TIME} attribute with the specified
     * value.
     *
     * @param startTime new value for {@value #ATTRIBUTE_NAME_START_TIME} attribute.
     */
    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }

    // ------------------------------------------------------------------------------------- endTime

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_TIME} attribute.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_TIME} attribute with the specified
     * value.
     *
     * @param endTime new value for {@value #ATTRIBUTE_NAME_END_TIME} attribute.
     */
    public void setEndTime(final LocalTime endTime) {
        this.endTime = endTime;
    }

    // -------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified
     * value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_NAME)
    @MsDescription.Column(name = COLUMN_NAME_NAME, value = "Shift description.")
    private String name;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_START_TIME, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_START_TIME, value = "Shift start time.")
    private LocalTime startTime;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_TIME, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_END_TIME, value = "Shift end time.")
    private LocalTime endTime;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_Shift_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

