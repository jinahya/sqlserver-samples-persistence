package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person;

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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Person.PersonPhone} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Person.PersonPhone}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code BusinessEntityID}</th>
 *     <td>Business entity identification number. Foreign key to Person.BusinessEntityID.</td></tr>
 * <tr><th scope="row">{@code PhoneNumber}</th><td>Telephone number identification number.</td></tr>
 * <tr><th scope="row">{@code PhoneNumberTypeID}</th>
 *     <td>Kind of phone number. Foreign key to PhoneNumberType.PhoneNumberTypeID.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Person.PersonPhone}</caption>
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
@MappedSuperclass
@MsDescription(
        schema = MappedPersonPhone.TABLE_SCHEMA,
        table = MappedPersonPhone.TABLE_NAME,
        value = "Telephone number and type of a person."
)
public abstract class MappedPersonPhone {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PersonPhone";

    // ---------------------------------------------------------------------------- BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_BUSINESS_ENTITY_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_BUSINESS_ENTITY_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // --------------------------------------------------------------------------------- PhoneNumber

    /**
     * The name of the {@code Phone (nvarchar)(25), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PHONE_NUMBER = "PhoneNumber";

    /**
     * The length of the {@value #COLUMN_NAME_PHONE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PHONE_NUMBER = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHONE_NUMBER} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE_NUMBER = "phoneNumber";

    // --------------------------------------------------------------------------- PhoneNumberTypeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PHONE_NUMBER_TYPE_ID = "PhoneNumberTypeID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PHONE_NUMBER_TYPE_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PHONE_NUMBER_TYPE_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PHONE_NUMBER_TYPE_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PHONE_NUMBER_TYPE_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHONE_NUMBER_TYPE_ID} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE_NUMBER_TYPE_ID = "phoneNumberTypeId";

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
    protected MappedPersonPhone() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",phoneNumber=" + phoneNumber
               + ",phoneNumberTypeId=" + phoneNumberTypeId
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
        if (!(obj instanceof MappedPersonPhone that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
               Objects.equals(getPhoneNumberTypeId(), that.getPhoneNumberTypeId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getPhoneNumber(), getPhoneNumberTypeId());
    }

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the
     * specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // --------------------------------------------------------------------------------- phoneNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute with the specified
     * value.
     *
     * @param phoneNumber new value for {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     */
    protected void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // --------------------------------------------------------------------------- phoneNumberTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER_TYPE_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getPhoneNumberTypeId() {
        return phoneNumberTypeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER_TYPE_ID} attribute with the
     * specified value.
     *
     * @param phoneNumberTypeId new value for {@value #ATTRIBUTE_NAME_PHONE_NUMBER_TYPE_ID}
     *                          attribute.
     */
    protected void setPhoneNumberTypeId(final Integer phoneNumberTypeId) {
        this.phoneNumberTypeId = phoneNumberTypeId;
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
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Max(ATTRIBUTE_MAX_BUSINESS_ENTITY_ID)
    @Min(ATTRIBUTE_MIN_BUSINESS_ENTITY_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                          value = "Business entity identification number. Foreign key to "
                                  + "Person.BusinessEntityID.")
    private Integer businessEntityId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Size(max = COLUMN_LENGTH_PHONE_NUMBER)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PHONE_NUMBER, nullable = false, insertable = true, updatable = false,
            length = COLUMN_LENGTH_PHONE_NUMBER)
    @MsDescription.Column(name = COLUMN_NAME_PHONE_NUMBER,
                          value = "Telephone number identification number.")
    private String phoneNumber;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Max(ATTRIBUTE_MAX_PHONE_NUMBER_TYPE_ID)
    @Min(ATTRIBUTE_MIN_PHONE_NUMBER_TYPE_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PHONE_NUMBER_TYPE_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PHONE_NUMBER_TYPE_ID,
                          value = "Kind of phone number. Foreign key to "
                                  + "PhoneNumberType.PhoneNumberTypeID.")
    private Integer phoneNumberTypeId;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_PersonPhone_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

