package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.person;

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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.UuidStringAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Person.Person} table.
 */
@MappedSuperclass
public abstract class MappedPerson {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Person";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Person";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ------------------------------------------------------------------------------------------------------ PersonType

    /**
     * The name of the {@code nchar(2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PERSON_TYPE = "PersonType";

    /**
     * The length of the {@value #COLUMN_NAME_PERSON_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PERSON_TYPE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PERSON_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERSON_TYPE = "personType";

    // ------------------------------------------------------------------------------------------------------- NameStyle

    /**
     * The name of the {@code NameStyle (bit), DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_STYLE = "NameStyle";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_STYLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_STYLE = "nameStyle";

    // ----------------------------------------------------------------------------------------------------------- Title

    /**
     * The name of the {@code nvarchar(8)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TITLE = "Title";

    /**
     * The length of the {@value #COLUMN_NAME_TITLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TITLE = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TITLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TITLE = "title";

    // ------------------------------------------------------------------------------------------------------- FirstName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FIRST_NAME = "FirstName";

    /**
     * The length of the {@value #COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FIRST_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    // ------------------------------------------------------------------------------------------------------ MiddleName

    /**
     * The name of the {@code Name (nvarchar)(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MIDDLE_NAME = "MiddleName";

    /**
     * The length of the {@value #COLUMN_NAME_MIDDLE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MIDDLE_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MIDDLE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MIDDLE_NAME = "middleName";

    // -------------------------------------------------------------------------------------------------------- LastName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_NAME = "LastName";

    /**
     * The length of the {@value #COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LAST_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    // ---------------------------------------------------------------------------------------------------------- Suffix

    /**
     * The name of the {@code nvarchar(10)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUFFIX = "Suffix";

    /**
     * The length of the {@value #COLUMN_NAME_SUFFIX} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUFFIX = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUFFIX} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUFFIX = "suffix";

    // -------------------------------------------------------------------------------------------------- EmailPromotion

    /**
     * The name of the {@code int, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_PROMOTION = "EmailPromotion";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL_PROMOTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL_PROMOTION = "emailPromotion";

    // ------------------------------------------------------------------------------------------- AdditionalContactInfo

    /**
     * The name of the {@code xml} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDITIONAL_CONTACT_INFO = "AdditionalContactInfo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDITIONAL_CONTACT_INFO} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDITIONAL_CONTACT_INFO = "additionalContactInfo";

    // ---------------------------------------------------------------------------------------------------- Demographics

    /**
     * The name of the {@code xml} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEMOGRAPHICS = "Demographics";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEMOGRAPHICS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEMOGRAPHICS = "demographics";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

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
    protected MappedPerson() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",personType=" + personType
               + ",nameStyle=" + nameStyle
               + ",title=" + title
               + ",firstName=" + firstName
               + ",middleName=" + middleName
               + ",lastName=" + lastName
               + ",suffix=" + suffix
               + ",emailPromotion=" + emailPromotion
               + ",additionalContactInfo=" + additionalContactInfo
               + ",demographics=" + demographics
               + ",rowguid=" + rowguid
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
        if (!(obj instanceof MappedPerson that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(businessEntityId);
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ------------------------------------------------------------------------------------------------------ personType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERSON_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERSON_TYPE} attribute.
     */
    public String getPersonType() {
        return personType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERSON_TYPE} attribute with the specified value.
     *
     * @param personType new value for {@value #ATTRIBUTE_NAME_PERSON_TYPE} attribute.
     */
    public void setPersonType(final String personType) {
        this.personType = personType;
    }

    // ------------------------------------------------------------------------------------------------------- nameStyle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_STYLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_STYLE} attribute.
     */
    public Boolean getNameStyle() {
        return nameStyle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_STYLE} attribute with the specified value.
     *
     * @param nameStyle new value for {@value #ATTRIBUTE_NAME_NAME_STYLE} attribute.
     */
    public void setNameStyle(final Boolean nameStyle) {
        this.nameStyle = nameStyle;
    }

    // ----------------------------------------------------------------------------------------------------------- title

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TITLE} attribute.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TITLE} attribute with the specified value.
     *
     * @param title new value for {@value #ATTRIBUTE_NAME_TITLE} attribute.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    // ------------------------------------------------------------------------------------------------------- firstName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute with the specified value.
     *
     * @param firstName new value for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    // ------------------------------------------------------------------------------------------------------ middleName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute with the specified value.
     *
     * @param middleName new value for {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    // -------------------------------------------------------------------------------------------------------- lastName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute with the specified value.
     *
     * @param lastName new value for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    // ---------------------------------------------------------------------------------------------------------- suffix

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute with the specified value.
     *
     * @param suffix new value for {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     */
    public void setSuffix(final String suffix) {
        this.suffix = suffix;
    }

    // -------------------------------------------------------------------------------------------------- emailPromotion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute.
     */
    public Integer getEmailPromotion() {
        return emailPromotion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute with the specified value.
     *
     * @param emailPromotion new value for {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute.
     */
    public void setEmailPromotion(final Integer emailPromotion) {
        this.emailPromotion = emailPromotion;
    }

    // ------------------------------------------------------------------------------------------- additionalContactInfo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDITIONAL_CONTACT_INFO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDITIONAL_CONTACT_INFO} attribute.
     */
    public String getAdditionalContactInfo() {
        return additionalContactInfo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDITIONAL_CONTACT_INFO} attribute with the specified value.
     *
     * @param additionalContactInfo new value for {@value #ATTRIBUTE_NAME_ADDITIONAL_CONTACT_INFO} attribute.
     */
    public void setAdditionalContactInfo(final String additionalContactInfo) {
        this.additionalContactInfo = additionalContactInfo;
    }

    // ---------------------------------------------------------------------------------------------------- demographics

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute.
     */
    public String getDemographics() {
        return demographics;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute with the specified value.
     *
     * @param demographics new value for {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute.
     */
    public void setDemographics(final String demographics) {
        this.demographics = demographics;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(final UUID rowguid) {
        this.rowguid = rowguid;
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
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PERSON_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PERSON_TYPE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PERSON_TYPE
    )
    private String personType;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME_STYLE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean nameStyle;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_TITLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TITLE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TITLE
    )
    private String title;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_FIRST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FIRST_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FIRST_NAME
    )
    private String firstName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_MIDDLE_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MIDDLE_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_MIDDLE_NAME
    )
    private String middleName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_LAST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LAST_NAME
    )
    private String lastName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SUFFIX)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUFFIX,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUFFIX
    )
    private String suffix;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EMAIL_PROMOTION,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer emailPromotion;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDITIONAL_CONTACT_INFO,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String additionalContactInfo;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DEMOGRAPHICS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String demographics;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

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

