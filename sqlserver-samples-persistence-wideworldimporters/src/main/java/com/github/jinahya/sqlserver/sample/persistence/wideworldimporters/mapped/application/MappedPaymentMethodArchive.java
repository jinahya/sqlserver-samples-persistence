package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Application.PaymentMethods_Archive} table.
 * <p>
 * Identifier: a history table of a system-versioned temporal table, which has no key.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server rejects an INSERT (error 13559) and an UPDATE (error 13561) of a temporal history table; only
 * the system versioning of {@code Application.PaymentMethods} writes it.
 */
@MappedSuperclass
public abstract class MappedPaymentMethodArchive {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PaymentMethods_Archive";

    // ----------------------------------------------------------------------------- PaymentMethodID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PAYMENT_METHOD_ID = "PaymentMethodID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PAYMENT_METHOD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAYMENT_METHOD_ID = "paymentMethodId";

    // --------------------------------------------------------------------------- PaymentMethodName

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PAYMENT_METHOD_NAME = "PaymentMethodName";

    /**
     * The length of the {@value #COLUMN_NAME_PAYMENT_METHOD_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PAYMENT_METHOD_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PAYMENT_METHOD_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAYMENT_METHOD_NAME = "paymentMethodName";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ----------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // ------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPaymentMethodArchive() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "paymentMethodId=" + paymentMethodId
               + ",paymentMethodName=" + paymentMethodName
               + ",lastEditedBy=" + lastEditedBy
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
               + "}";
    }

    // ----------------------------------------------------------------------------- paymentMethodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_ID} attribute.
     */
    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_ID} attribute with the specified value.
     *
     * @param paymentMethodId new value for {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_ID} attribute.
     */
    protected void setPaymentMethodId(final Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    // --------------------------------------------------------------------------- paymentMethodName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_NAME} attribute.
     */
    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_NAME} attribute with the specified value.
     *
     * @param paymentMethodName new value for {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_NAME} attribute.
     */
    protected void setPaymentMethodName(final String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    public Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ----------------------------------------------------------------------------------- validFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value.
     *
     * @param validFrom new value for {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    protected void setValidFrom(final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    // ------------------------------------------------------------------------------------- validTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PAYMENT_METHOD_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer paymentMethodId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_PAYMENT_METHOD_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PAYMENT_METHOD_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PAYMENT_METHOD_NAME
    )
    private String paymentMethodName;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validFrom;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validTo;
}

