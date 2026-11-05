package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.InventoryTransaction} table.
 * <p>
 * The {@value #COLUMN_NAME_ITEM_ID}, {@value #COLUMN_NAME_WAREHOUSE_ID}, {@value #COLUMN_NAME_TRANSACTION_TYPE_ID}
 * columns are mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.InventoryTransaction}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code TransactionDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
public abstract class MappedInventoryTransaction {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "InventoryTransaction";

    // ------------------------------------------------------------------------------- TransactionID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_ID = "TransactionID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_ID = "transactionId";

    // -------------------------------------------------------------------------------------- ItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_ID = "ItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_ID = "itemId";

    // --------------------------------------------------------------------------------- WarehouseID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_ID = "WarehouseID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_ID = "warehouseId";

    // --------------------------------------------------------------------------- TransactionTypeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_TYPE_ID = "TransactionTypeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_TYPE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE_ID = "transactionTypeId";

    // ------------------------------------------------------------------------------------ Quantity

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ------------------------------------------------------------------------------------ UnitCost

    /**
     * The name of the {@code decimal(18, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_COST = "UnitCost";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_COST = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_COST = 4;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_UNIT_COST} attribute, the minimum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_UNIT_COST = "-99999999999999.9999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_UNIT_COST} attribute, the maximum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_UNIT_COST = "99999999999999.9999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_COST = "unitCost";

    // ----------------------------------------------------------------------------- ReferenceNumber

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_NUMBER = "ReferenceNumber";

    /**
     * The length of the {@value #COLUMN_NAME_REFERENCE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REFERENCE_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_NUMBER = "referenceNumber";

    // ------------------------------------------------------------------------------- ReferenceType

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_TYPE = "ReferenceType";

    /**
     * The length of the {@value #COLUMN_NAME_REFERENCE_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REFERENCE_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_TYPE = "referenceType";

    // --------------------------------------------------------------------------------------- Notes

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NOTES = "Notes";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NOTES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NOTES = "notes";

    // ----------------------------------------------------------------------------- TransactionDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_DATE = "TransactionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_DATE = "transactionDate";

    // ----------------------------------------------------------------------------------- CreatedBy

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_BY = "CreatedBy";

    /**
     * The length of the {@value #COLUMN_NAME_CREATED_BY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CREATED_BY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_BY = "createdBy";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedInventoryTransaction() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "transactionId=" + transactionId
               + ",itemId=" + itemId
               + ",warehouseId=" + warehouseId
               + ",transactionTypeId=" + transactionTypeId
               + ",quantity=" + quantity
               + ",unitCost=" + unitCost
               + ",referenceNumber=" + referenceNumber
               + ",referenceType=" + referenceType
               + ",notes=" + notes
               + ",transactionDate=" + transactionDate
               + ",createdBy=" + createdBy
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
        if (!(obj instanceof MappedInventoryTransaction that)) {
            return false;
        }
        return getTransactionId() != null
               && getTransactionId().equals(that.getTransactionId());
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
        return MappedInventoryTransaction.class.hashCode();
    }

    // ------------------------------------------------------------------------------- transactionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute.
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute with the specified value.
     *
     * @param transactionId new value for {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute.
     */
    protected void setTransactionId(final Integer transactionId) {
        this.transactionId = transactionId;
    }

    // -------------------------------------------------------------------------------------- itemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    protected Integer getItemId() {
        return itemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param itemId new value for {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    protected void setItemId(final Integer itemId) {
        this.itemId = itemId;
    }

    // --------------------------------------------------------------------------------- warehouseId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    protected Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param warehouseId new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    protected void setWarehouseId(final Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    // --------------------------------------------------------------------------- transactionTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     */
    protected Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param transactionTypeId new value for {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     */
    protected void setTransactionTypeId(final Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    // ------------------------------------------------------------------------------------ quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(final BigDecimal quantity) {
        this.quantity = quantity;
    }

    // ------------------------------------------------------------------------------------ unitCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     */
    public BigDecimal getUnitCost() {
        return unitCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute with the specified value.
     *
     * @param unitCost new value for {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     */
    public void setUnitCost(final BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    // ----------------------------------------------------------------------------- referenceNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute.
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute with the specified value.
     *
     * @param referenceNumber new value for {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute.
     */
    public void setReferenceNumber(final String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    // ------------------------------------------------------------------------------- referenceType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute.
     */
    public String getReferenceType() {
        return referenceType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute with the specified value.
     *
     * @param referenceType new value for {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute.
     */
    public void setReferenceType(final String referenceType) {
        this.referenceType = referenceType;
    }

    // --------------------------------------------------------------------------------------- notes

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NOTES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NOTES} attribute.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NOTES} attribute with the specified value.
     *
     * @param notes new value for {@value #ATTRIBUTE_NAME_NOTES} attribute.
     */
    public void setNotes(final String notes) {
        this.notes = notes;
    }

    // ----------------------------------------------------------------------------- transactionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     */
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute with the specified value.
     *
     * @param transactionDate new value for {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     */
    public void setTransactionDate(final LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    // ----------------------------------------------------------------------------------- createdBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute with the specified value.
     *
     * @param createdBy new value for {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_TRANSACTION_ID, nullable = false, insertable = false, updatable = false)
    private Integer transactionId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_ID, nullable = false, insertable = false, updatable = false)
    private Integer itemId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_ID, nullable = false, insertable = false, updatable = false)
    private Integer warehouseId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TRANSACTION_TYPE_ID, nullable = false, insertable = false, updatable = false)
    private Integer transactionTypeId;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY, scale = COLUMN_SCALE_QUANTITY)
    private BigDecimal quantity;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_UNIT_COST)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_UNIT_COST)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIT_COST, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_UNIT_COST, scale = COLUMN_SCALE_UNIT_COST)
    private BigDecimal unitCost;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_REFERENCE_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REFERENCE_NUMBER, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_REFERENCE_NUMBER)
    private String referenceNumber;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_REFERENCE_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REFERENCE_TYPE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_REFERENCE_TYPE)
    private String referenceType;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: NULL; no row has a value
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NOTES, nullable = true, insertable = true, updatable = true)
    private String notes;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TRANSACTION_DATE, nullable = true, insertable = true, updatable = true)
    @MsDefault("(getdate())")
    private LocalDateTime transactionDate;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CREATED_BY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_BY, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_CREATED_BY)
    private String createdBy;
}

