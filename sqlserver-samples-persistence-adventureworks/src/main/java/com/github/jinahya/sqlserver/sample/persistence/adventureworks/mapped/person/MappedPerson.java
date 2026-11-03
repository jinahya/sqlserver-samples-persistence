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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.AdditionalContactInfoStringAttributeConverter;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.IndividualSurveyStringAttributeConverter;
import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsTrigger;
import io.github.jinahya.sqlserver.xml.schema.binding.b7fdbe.AdditionalContactInfo;
import io.github.jinahya.sqlserver.xml.schema.binding.b9c1be.IndividualSurvey;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Person.Person} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Person.Person}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code BusinessEntityID}</th><td>Primary key for Person records.</td></tr>
 * <tr><th scope="row">{@code PersonType}</th>
 *     <td>Primary type of person: SC = Store Contact, IN = Individual (retail) customer, SP = Sales
 *     person, EM = Employee (non-sales), VC = Vendor contact, GC = General contact</td></tr>
 * <tr><th scope="row">{@code NameStyle}</th>
 *     <td>0 = The data in FirstName and LastName are stored in western style (first name, last
 *     name) order.  1 = Eastern style (last name, first name) order.</td></tr>
 * <tr><th scope="row">{@code Title}</th><td>A courtesy title. For example, Mr. or Ms.</td></tr>
 * <tr><th scope="row">{@code FirstName}</th><td>First name of the person.</td></tr>
 * <tr><th scope="row">{@code MiddleName}</th>
 *     <td>Middle name or middle initial of the person.</td></tr>
 * <tr><th scope="row">{@code LastName}</th><td>Last name of the person.</td></tr>
 * <tr><th scope="row">{@code Suffix}</th><td>Surname suffix. For example, Sr. or Jr.</td></tr>
 * <tr><th scope="row">{@code EmailPromotion}</th>
 *     <td>0 = Contact does not wish to receive e-mail promotions, 1 = Contact does wish to receive
 *     e-mail promotions from AdventureWorks, 2 = Contact does wish to receive e-mail promotions
 *     from AdventureWorks and selected partners.</td></tr>
 * <tr><th scope="row">{@code AdditionalContactInfo}</th>
 *     <td>Additional contact information about the person stored in xml format.</td></tr>
 * <tr><th scope="row">{@code Demographics}</th>
 *     <td>Personal information such as hobbies, and income collected from online shoppers. Used for
 *     sales analysis.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Person.Person}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code NameStyle}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code EmailPromotion}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code rowguid}</th><td>{@code (newid())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Triggers on {@code Person.Person}</caption>
 * <thead>
 * <tr><th scope="col">Trigger</th><th scope="col">Timing</th><th scope="col">Events</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code iuPerson}</th><td>{@code AFTER}</td><td>{@code INSERT}, {@code
 * UPDATE}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/newid-transact-sql">NEWID</a>
 * @see <a href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedPerson.TABLE_SCHEMA,
        table = MappedPerson.TABLE_NAME,
        value = "Human beings involved with AdventureWorks: employees, customer contacts, and "
                + "vendor contacts."
)
@MsTrigger(
        name = "iuPerson",
        events = {MsTrigger.Event.INSERT, MsTrigger.Event.UPDATE}
)
public abstract class MappedPerson {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Person";

    // ---------------------------------------------------------------------------- BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ---------------------------------------------------------------------------------- PersonType

    /**
     * The name of the {@code nchar(2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PERSON_TYPE = "PersonType";

    /**
     * The length of the {@value #COLUMN_NAME_PERSON_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PERSON_TYPE = 2;

    /**
     * A value of the {@value #COLUMN_NAME_PERSON_TYPE} column, for a store contact. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_PERSON_TYPE_STORE_CONTACT = "SC";

    /**
     * A value of the {@value #COLUMN_NAME_PERSON_TYPE} column, for an individual (retail) customer.
     * The value is {@value}.
     */
    public static final String COLUMN_VALUE_PERSON_TYPE_INDIVIDUAL_CUSTOMER = "IN";

    /**
     * A value of the {@value #COLUMN_NAME_PERSON_TYPE} column, for a sales person. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_PERSON_TYPE_SALES_PERSON = "SP";

    /**
     * A value of the {@value #COLUMN_NAME_PERSON_TYPE} column, for an employee (non-sales). The
     * value is {@value}.
     */
    public static final String COLUMN_VALUE_PERSON_TYPE_EMPLOYEE = "EM";

    /**
     * A value of the {@value #COLUMN_NAME_PERSON_TYPE} column, for a vendor contact. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_PERSON_TYPE_VENDOR_CONTACT = "VC";

    /**
     * A value of the {@value #COLUMN_NAME_PERSON_TYPE} column, for a general contact. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_PERSON_TYPE_GENERAL_CONTACT = "GC";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PERSON_TYPE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERSON_TYPE = "personType";

    /**
     * An enum for the {@value #ATTRIBUTE_NAME_PERSON_TYPE} attribute, of the values the
     * {@code CK_Person_PersonType} constraint allows.
     * {@snippet lang = "sql":
     * ([PersonType] IS NULL OR (upper([PersonType])='GC' OR upper([PersonType])='SP' OR upper
     * ([PersonType])='EM' OR upper([PersonType])='IN' OR upper([PersonType])='VC' OR upper
     * ([PersonType])='SC'))
     *} The constants are named after the column values, which are stored in upper case.
     */
    public enum PersonType {

        /**
         * A constant for the {@value MappedPerson#COLUMN_VALUE_PERSON_TYPE_STORE_CONTACT} value,
         * for a store contact.
         */
        SC,

        /**
         * A constant for the {@value MappedPerson#COLUMN_VALUE_PERSON_TYPE_INDIVIDUAL_CUSTOMER}
         * value, for an individual (retail) customer.
         */
        IN,

        /**
         * A constant for the {@value MappedPerson#COLUMN_VALUE_PERSON_TYPE_SALES_PERSON} value, for
         * a sales person.
         */
        SP,

        /**
         * A constant for the {@value MappedPerson#COLUMN_VALUE_PERSON_TYPE_EMPLOYEE} value, for an
         * employee (non-sales).
         */
        EM,

        /**
         * A constant for the {@value MappedPerson#COLUMN_VALUE_PERSON_TYPE_VENDOR_CONTACT} value,
         * for a vendor contact.
         */
        VC,

        /**
         * A constant for the {@value MappedPerson#COLUMN_VALUE_PERSON_TYPE_GENERAL_CONTACT} value,
         * for a general contact.
         */
        GC
    }

    // ----------------------------------------------------------------------------------- NameStyle

    /**
     * The name of the {@code NameStyle (bit), DEFAULT ((0)), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_NAME_STYLE = "NameStyle";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_STYLE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_STYLE = "nameStyle";

    // --------------------------------------------------------------------------------------- Title

    /**
     * The name of the {@code nvarchar(8)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TITLE = "Title";

    /**
     * The length of the {@value #COLUMN_NAME_TITLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TITLE = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TITLE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TITLE = "title";

    // ----------------------------------------------------------------------------------- FirstName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FIRST_NAME = "FirstName";

    /**
     * The length of the {@value #COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FIRST_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_NAME} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    // ---------------------------------------------------------------------------------- MiddleName

    /**
     * The name of the {@code Name (nvarchar)(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MIDDLE_NAME = "MiddleName";

    /**
     * The length of the {@value #COLUMN_NAME_MIDDLE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MIDDLE_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MIDDLE_NAME} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MIDDLE_NAME = "middleName";

    // ------------------------------------------------------------------------------------ LastName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_NAME = "LastName";

    /**
     * The length of the {@value #COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LAST_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    // -------------------------------------------------------------------------------------- Suffix

    /**
     * The name of the {@code nvarchar(10)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUFFIX = "Suffix";

    /**
     * The length of the {@value #COLUMN_NAME_SUFFIX} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUFFIX = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUFFIX} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUFFIX = "suffix";

    // ------------------------------------------------------------------------------ EmailPromotion

    /**
     * The name of the {@code int, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_PROMOTION = "EmailPromotion";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute, as the
     * {@code CK_Person_EmailPromotion} check constraint ({@code [EmailPromotion]>=(0)}) requires.
     * The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_EMAIL_PROMOTION = 0;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute, as the
     * {@code CK_Person_EmailPromotion} check constraint ({@code [EmailPromotion]<=(2)}) requires.
     * The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_EMAIL_PROMOTION = +2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL_PROMOTION} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL_PROMOTION = "emailPromotion";

    // ----------------------------------------------------------------------- AdditionalContactInfo

    /**
     * The name of the {@code xml} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDITIONAL_CONTACT_INFO = "AdditionalContactInfo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDITIONAL_CONTACT_INFO}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDITIONAL_CONTACT_INFO = "additionalContactInfo";

    // -------------------------------------------------------------------------------- Demographics

    /**
     * The name of the {@code xml} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEMOGRAPHICS = "Demographics";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEMOGRAPHICS} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEMOGRAPHICS = "demographics";

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

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
    protected MappedPerson() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the
     * specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ---------------------------------------------------------------------------------- personType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERSON_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERSON_TYPE} attribute.
     */
    public PersonType getPersonType() {
        return personType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERSON_TYPE} attribute with the specified
     * value.
     *
     * @param personType new value for {@value #ATTRIBUTE_NAME_PERSON_TYPE} attribute.
     */
    public void setPersonType(final PersonType personType) {
        this.personType = personType;
    }

    // ----------------------------------------------------------------------------------- nameStyle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_STYLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_STYLE} attribute.
     */
    public Boolean getNameStyle() {
        return nameStyle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_STYLE} attribute with the specified
     * value.
     *
     * @param nameStyle new value for {@value #ATTRIBUTE_NAME_NAME_STYLE} attribute.
     */
    public void setNameStyle(final Boolean nameStyle) {
        this.nameStyle = nameStyle;
    }

    // --------------------------------------------------------------------------------------- title

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

    // ----------------------------------------------------------------------------------- firstName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute with the specified
     * value.
     *
     * @param firstName new value for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    // ---------------------------------------------------------------------------------- middleName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute with the specified
     * value.
     *
     * @param middleName new value for {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    // ------------------------------------------------------------------------------------ lastName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute with the specified
     * value.
     *
     * @param lastName new value for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    // -------------------------------------------------------------------------------------- suffix

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute with the specified
     * value.
     *
     * @param suffix new value for {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     */
    public void setSuffix(final String suffix) {
        this.suffix = suffix;
    }

    // ------------------------------------------------------------------------------ emailPromotion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute.
     */
    public Integer getEmailPromotion() {
        return emailPromotion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute with the
     * specified value.
     *
     * @param emailPromotion new value for {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute.
     */
    public void setEmailPromotion(final Integer emailPromotion) {
        this.emailPromotion = emailPromotion;
    }

    // ----------------------------------------------------------------------- additionalContactInfo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDITIONAL_CONTACT_INFO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDITIONAL_CONTACT_INFO} attribute.
     */
    public AdditionalContactInfo getAdditionalContactInfo() {
        return additionalContactInfo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDITIONAL_CONTACT_INFO} attribute with the
     * specified value.
     *
     * @param additionalContactInfo new value for {@value #ATTRIBUTE_NAME_ADDITIONAL_CONTACT_INFO}
     *                              attribute.
     */
    public void setAdditionalContactInfo(final AdditionalContactInfo additionalContactInfo) {
        this.additionalContactInfo = additionalContactInfo;
    }

    // -------------------------------------------------------------------------------- demographics

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute.
     */
    public IndividualSurvey getDemographics() {
        return demographics;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute with the specified
     * value.
     *
     * @param demographics new value for {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute.
     */
    public void setDemographics(final IndividualSurvey demographics) {
        this.demographics = demographics;
    }

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
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
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                          value = "Primary key for Person records.")
    private Integer businessEntityId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PERSON_TYPE, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_PERSON_TYPE)
    @MsDescription.Column(name = COLUMN_NAME_PERSON_TYPE,
                          value = "Primary type of person: SC = Store Contact, IN = Individual "
                                  + "(retail) customer, SP = Sales person, EM = Employee "
                                  + "(non-sales), VC = Vendor contact, GC = General contact")
    private PersonType personType;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME_STYLE, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_NAME_STYLE,
                          value = "0 = The data in FirstName and LastName are stored in western "
                                  + "style (first name, last name) order.  1 = Eastern style "
                                  + "(last name, first name) order.")
    @MsDefault(name = "DF_Person_NameStyle", value = "((0))")
    private Boolean nameStyle;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_TITLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TITLE, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_TITLE)
    @MsDescription.Column(name = COLUMN_NAME_TITLE,
                          value = "A courtesy title. For example, Mr. or Ms.")
    private String title;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_FIRST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FIRST_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_FIRST_NAME)
    @MsDescription.Column(name = COLUMN_NAME_FIRST_NAME, value = "First name of the person.")
    private String firstName;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_MIDDLE_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MIDDLE_NAME, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_MIDDLE_NAME)
    @MsDescription.Column(name = COLUMN_NAME_MIDDLE_NAME,
                          value = "Middle name or middle initial of the person.")
    private String middleName;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_LAST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_LAST_NAME)
    @MsDescription.Column(name = COLUMN_NAME_LAST_NAME, value = "Last name of the person.")
    private String lastName;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SUFFIX)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUFFIX, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_SUFFIX)
    @MsDescription.Column(name = COLUMN_NAME_SUFFIX,
                          value = "Surname suffix. For example, Sr. or Jr.")
    private String suffix;

    // ---------------------------------------------------------------------------------------------
    // CK_Person_EmailPromotion: ([EmailPromotion]>=(0) AND [EmailPromotion]<=(2))
    @Max(ATTRIBUTE_MAX_EMAIL_PROMOTION)
    @Min(ATTRIBUTE_MIN_EMAIL_PROMOTION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EMAIL_PROMOTION, nullable = false, insertable = true,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_EMAIL_PROMOTION,
                          value = "0 = Contact does not wish to receive e-mail promotions, 1 = "
                                  + "Contact does wish to receive e-mail promotions from "
                                  + "AdventureWorks, 2 = Contact does wish to receive e-mail "
                                  + "promotions from AdventureWorks and selected partners.")
    @MsDefault(name = "DF_Person_EmailPromotion", value = "((0))")
    private Integer emailPromotion;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @Convert(converter = AdditionalContactInfoStringAttributeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDITIONAL_CONTACT_INFO, nullable = true, insertable = true,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_ADDITIONAL_CONTACT_INFO,
                          value = "Additional contact information about the person stored in "
                                  + "xml format.")
    private AdditionalContactInfo additionalContactInfo;

    // ---------------------------------------------------------------------------------------------
    // the iuPerson trigger, after an INSERT or an UPDATE, puts a TotalPurchaseYTD into it, or a
    // whole survey when it
    // is NULL; Hibernate ORM writes the value and reads back what the trigger left
    @Valid
    @Convert(converter = IndividualSurveyStringAttributeConverter.class)
    @org.hibernate.annotations.Generated(
            event = {
                    org.hibernate.generator.EventType.INSERT,
                    org.hibernate.generator.EventType.UPDATE
            },
            writable = true
    )
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DEMOGRAPHICS, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_DEMOGRAPHICS,
                          value = "Personal information such as hobbies, and income collected "
                                  + "from online shoppers. Used for sales analysis.")
    private IndividualSurvey demographics;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_ROWGUID,
                          value = "ROWGUIDCOL number uniquely identifying the record. Used to "
                                  + "support a merge replication sample.")
    @MsDefault(name = "DF_Person_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_Person_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

