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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsTemporal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Application.PaymentMethods} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and keeps every
 * former version of a row in {@code Application.PaymentMethods_Archive}.
 * <p>
 * The {@value #COLUMN_NAME_LAST_EDITED_BY} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Application.PaymentMethods}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PaymentMethodID}</th>
 *     <td>Numeric ID used for reference to a payment type within the database</td></tr>
 * <tr><th scope="row">{@code PaymentMethodName}</th>
 *     <td>Full name of ways that customers can make payments or that suppliers can be
 *     paid</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Application.PaymentMethods}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PaymentMethodID}</th><td>{@code (NEXT VALUE FOR [Sequences].[PaymentMethodID])}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedPaymentMethod.TABLE_SCHEMA,
        table = MappedPaymentMethod.TABLE_NAME,
        value = "Ways that payments can be made (ie: cash, check, EFT, etc."
)
@MsTemporal(
        historyTable = "Application.PaymentMethods_Archive",
        periodStart = "ValidFrom",
        periodEnd = "ValidTo"
)
public abstract class MappedPaymentMethod {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PaymentMethods";

    // ----------------------------------------------------------------------------- PaymentMethodID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.PaymentMethodID, NOT NULL} column. The value is {@value}.
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
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW START, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // ------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW END, NOT NULL} column. The value is {@value}.
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
    protected MappedPaymentMethod() {
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
        if (!(obj instanceof MappedPaymentMethod that)) {
            return false;
        }
        return Objects.equals(getPaymentMethodId(), that.getPaymentMethodId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getPaymentMethodId());
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
    public void setPaymentMethodName(final String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.PaymentMethodID", schema = "Sequences",
                       sequenceName = "PaymentMethodID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.PaymentMethodID")
    @Column(name = COLUMN_NAME_PAYMENT_METHOD_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PAYMENT_METHOD_ID,
                          value = "Numeric ID used for reference to a payment type within the "
                                  + "database")
    @MsDefault(name = "DF_Application_PaymentMethods_PaymentMethodID",
               value = "(NEXT VALUE FOR [Sequences].[PaymentMethodID])")
    private Integer paymentMethodId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PAYMENT_METHOD_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PAYMENT_METHOD_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_PAYMENT_METHOD_NAME)
    @MsDescription.Column(name = COLUMN_NAME_PAYMENT_METHOD_NAME,
                          value = "Full name of ways that customers can make payments or that "
                                  + "suppliers can be paid")
    private String paymentMethodName;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validFrom;

    // ---------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validTo;
}

