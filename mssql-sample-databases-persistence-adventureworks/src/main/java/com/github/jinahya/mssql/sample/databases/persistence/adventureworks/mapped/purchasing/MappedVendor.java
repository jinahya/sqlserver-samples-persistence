package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.purchasing;

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
 * A mapped superclass which holds the mappings of the {@code Purchasing.Vendor} table.
 */
@MappedSuperclass
public abstract class MappedVendor {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Purchasing";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Vendor";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // --------------------------------------------------------------------------------------------------- AccountNumber

    /**
     * The name of the {@code AccountNumber (nvarchar)(15), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACCOUNT_NUMBER = "AccountNumber";

    /**
     * The length of the {@value #COLUMN_NAME_ACCOUNT_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ACCOUNT_NUMBER = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACCOUNT_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACCOUNT_NUMBER = "accountNumber";

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

    // ---------------------------------------------------------------------------------------------------- CreditRating

    /**
     * The name of the {@code tinyint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREDIT_RATING = "CreditRating";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREDIT_RATING} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_RATING = "creditRating";

    // ------------------------------------------------------------------------------------------- PreferredVendorStatus

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PREFERRED_VENDOR_STATUS = "PreferredVendorStatus";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PREFERRED_VENDOR_STATUS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PREFERRED_VENDOR_STATUS = "preferredVendorStatus";

    // ------------------------------------------------------------------------------------------------------ ActiveFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTIVE_FLAG = "ActiveFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTIVE_FLAG} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTIVE_FLAG = "activeFlag";

    // ----------------------------------------------------------------------------------------- PurchasingWebServiceURL

    /**
     * The name of the {@code nvarchar(1024)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASING_WEB_SERVICE_URL = "PurchasingWebServiceURL";

    /**
     * The length of the {@value #COLUMN_NAME_PURCHASING_WEB_SERVICE_URL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PURCHASING_WEB_SERVICE_URL = 1024;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASING_WEB_SERVICE_URL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASING_WEB_SERVICE_URL = "purchasingWebServiceUrl";

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
    protected MappedVendor() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",accountNumber=" + accountNumber
               + ",name=" + name
               + ",creditRating=" + creditRating
               + ",preferredVendorStatus=" + preferredVendorStatus
               + ",activeFlag=" + activeFlag
               + ",purchasingWebServiceUrl=" + purchasingWebServiceUrl
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
        if (!(obj instanceof MappedVendor that)) {
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

    // --------------------------------------------------------------------------------------------------- accountNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute with the specified value.
     *
     * @param accountNumber new value for {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute.
     */
    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
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

    // ---------------------------------------------------------------------------------------------------- creditRating

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREDIT_RATING} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREDIT_RATING} attribute.
     */
    public Short getCreditRating() {
        return creditRating;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREDIT_RATING} attribute with the specified value.
     *
     * @param creditRating new value for {@value #ATTRIBUTE_NAME_CREDIT_RATING} attribute.
     */
    public void setCreditRating(final Short creditRating) {
        this.creditRating = creditRating;
    }

    // ------------------------------------------------------------------------------------------- preferredVendorStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PREFERRED_VENDOR_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PREFERRED_VENDOR_STATUS} attribute.
     */
    public Boolean getPreferredVendorStatus() {
        return preferredVendorStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PREFERRED_VENDOR_STATUS} attribute with the specified value.
     *
     * @param preferredVendorStatus new value for {@value #ATTRIBUTE_NAME_PREFERRED_VENDOR_STATUS} attribute.
     */
    public void setPreferredVendorStatus(final Boolean preferredVendorStatus) {
        this.preferredVendorStatus = preferredVendorStatus;
    }

    // ------------------------------------------------------------------------------------------------------ activeFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTIVE_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTIVE_FLAG} attribute.
     */
    public Boolean getActiveFlag() {
        return activeFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTIVE_FLAG} attribute with the specified value.
     *
     * @param activeFlag new value for {@value #ATTRIBUTE_NAME_ACTIVE_FLAG} attribute.
     */
    public void setActiveFlag(final Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    // ----------------------------------------------------------------------------------------- purchasingWebServiceUrl

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PURCHASING_WEB_SERVICE_URL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PURCHASING_WEB_SERVICE_URL} attribute.
     */
    public String getPurchasingWebServiceUrl() {
        return purchasingWebServiceUrl;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PURCHASING_WEB_SERVICE_URL} attribute with the specified
     * value.
     *
     * @param purchasingWebServiceUrl new value for {@value #ATTRIBUTE_NAME_PURCHASING_WEB_SERVICE_URL} attribute.
     */
    public void setPurchasingWebServiceUrl(final String purchasingWebServiceUrl) {
        this.purchasingWebServiceUrl = purchasingWebServiceUrl;
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
    protected void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ACCOUNT_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ACCOUNT_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ACCOUNT_NUMBER
    )
    private String accountNumber;

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
    @Max(255)
    @Min(0)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CREDIT_RATING,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short creditRating;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PREFERRED_VENDOR_STATUS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean preferredVendorStatus;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ACTIVE_FLAG,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean activeFlag;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PURCHASING_WEB_SERVICE_URL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PURCHASING_WEB_SERVICE_URL,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PURCHASING_WEB_SERVICE_URL
    )
    private String purchasingWebServiceUrl;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (getdate()) fills it on an INSERT, and nothing documents it as changing
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime modifiedDate;
}

