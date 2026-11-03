package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales;

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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.InvoiceLines} table.
 * <p>
 * The {@value #COLUMN_NAME_INVOICE_ID}, {@value #COLUMN_NAME_STOCK_ITEM_ID}, {@value #COLUMN_NAME_PACKAGE_TYPE_ID},
 * {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.InvoiceLines}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code InvoiceLineID}</th>
 *     <td>Numeric ID used for reference to a line on an invoice within the database</td></tr>
 * <tr><th scope="row">{@code InvoiceID}</th><td>Invoice that this line is associated with</td></tr>
 * <tr><th scope="row">{@code StockItemID}</th><td>Stock item for this invoice line</td></tr>
 * <tr><th scope="row">{@code Description}</th>
 *     <td>Description of the item supplied (Usually the stock item name but can be
 *     overridden)</td></tr>
 * <tr><th scope="row">{@code PackageTypeID}</th><td>Type of package supplied</td></tr>
 * <tr><th scope="row">{@code Quantity}</th><td>Quantity supplied</td></tr>
 * <tr><th scope="row">{@code UnitPrice}</th><td>Unit price charged</td></tr>
 * <tr><th scope="row">{@code TaxRate}</th><td>Tax rate to be applied</td></tr>
 * <tr><th scope="row">{@code TaxAmount}</th><td>Tax amount calculated</td></tr>
 * <tr><th scope="row">{@code LineProfit}</th>
 *     <td>Profit made on this line item at current cost price</td></tr>
 * <tr><th scope="row">{@code ExtendedPrice}</th><td>Extended line price charged</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.InvoiceLines}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code InvoiceLineID}</th><td>{@code (NEXT VALUE FOR [Sequences].[InvoiceLineID])}</td></tr>
 * <tr><th scope="row">{@code LastEditedWhen}</th><td>{@code (sysdatetime())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/sysdatetime-transact-sql">SYSDATETIME</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedInvoiceLine.TABLE_SCHEMA,
        table = MappedInvoiceLine.TABLE_NAME,
        value = "Detail lines from customer invoices"
)
public abstract class MappedInvoiceLine {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "InvoiceLines";

    // ------------------------------------------------------------------------------- InvoiceLineID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.InvoiceLineID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INVOICE_LINE_ID = "InvoiceLineID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVOICE_LINE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVOICE_LINE_ID = "invoiceLineId";

    // ----------------------------------------------------------------------------------- InvoiceID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INVOICE_ID = "InvoiceID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVOICE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVOICE_ID = "invoiceId";

    // --------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_ID = "StockItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_ID = "stockItemId";

    // --------------------------------------------------------------------------------- Description

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    /**
     * The length of the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DESCRIPTION = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // ------------------------------------------------------------------------------- PackageTypeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PACKAGE_TYPE_ID = "PackageTypeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PACKAGE_TYPE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PACKAGE_TYPE_ID = "packageTypeId";

    // ------------------------------------------------------------------------------------ Quantity

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ----------------------------------------------------------------------------------- UnitPrice

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UnitPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_PRICE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ------------------------------------------------------------------------------------- TaxRate

    /**
     * The name of the {@code decimal(18, 3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAX_RATE = "TaxRate";

    /**
     * The precision of the {@value #COLUMN_NAME_TAX_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TAX_RATE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_TAX_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TAX_RATE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_RATE = "taxRate";

    // ----------------------------------------------------------------------------------- TaxAmount

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAX_AMOUNT = "TaxAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TAX_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TAX_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_AMOUNT = "taxAmount";

    // ---------------------------------------------------------------------------------- LineProfit

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_PROFIT = "LineProfit";

    /**
     * The precision of the {@value #COLUMN_NAME_LINE_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LINE_PROFIT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_LINE_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LINE_PROFIT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_PROFIT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_PROFIT = "lineProfit";

    // ------------------------------------------------------------------------------- ExtendedPrice

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXTENDED_PRICE = "ExtendedPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_EXTENDED_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_EXTENDED_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_EXTENDED_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_EXTENDED_PRICE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXTENDED_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXTENDED_PRICE = "extendedPrice";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------ LastEditedWhen

    /**
     * The name of the {@code datetime2, DEFAULT (sysdatetime()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_WHEN = "LastEditedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_WHEN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_WHEN = "lastEditedWhen";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedInvoiceLine() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "invoiceLineId=" + invoiceLineId
               + ",invoiceId=" + invoiceId
               + ",stockItemId=" + stockItemId
               + ",description=" + description
               + ",packageTypeId=" + packageTypeId
               + ",quantity=" + quantity
               + ",unitPrice=" + unitPrice
               + ",taxRate=" + taxRate
               + ",taxAmount=" + taxAmount
               + ",lineProfit=" + lineProfit
               + ",extendedPrice=" + extendedPrice
               + ",lastEditedBy=" + lastEditedBy
               + ",lastEditedWhen=" + lastEditedWhen
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
        if (!(obj instanceof MappedInvoiceLine that)) {
            return false;
        }
        return Objects.equals(invoiceLineId, that.invoiceLineId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(invoiceLineId);
    }

    // ------------------------------------------------------------------------------- invoiceLineId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVOICE_LINE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVOICE_LINE_ID} attribute.
     */
    public Integer getInvoiceLineId() {
        return invoiceLineId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INVOICE_LINE_ID} attribute with the specified value.
     *
     * @param invoiceLineId new value for {@value #ATTRIBUTE_NAME_INVOICE_LINE_ID} attribute.
     */
    protected void setInvoiceLineId(final Integer invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    // ----------------------------------------------------------------------------------- invoiceId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     */
    protected Integer getInvoiceId() {
        return invoiceId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param invoiceId new value for {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     */
    protected void setInvoiceId(final Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    // --------------------------------------------------------------------------------- stockItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    protected Integer getStockItemId() {
        return stockItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param stockItemId new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    protected void setStockItemId(final Integer stockItemId) {
        this.stockItemId = stockItemId;
    }

    // --------------------------------------------------------------------------------- description

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with the specified value.
     *
     * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    // ------------------------------------------------------------------------------- packageTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     */
    protected Integer getPackageTypeId() {
        return packageTypeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param packageTypeId new value for {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     */
    protected void setPackageTypeId(final Integer packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    // ------------------------------------------------------------------------------------ quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    // ----------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // ------------------------------------------------------------------------------------- taxRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute with the specified value.
     *
     * @param taxRate new value for {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     */
    public void setTaxRate(final BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    // ----------------------------------------------------------------------------------- taxAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute with the specified value.
     *
     * @param taxAmount new value for {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     */
    public void setTaxAmount(final BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    // ---------------------------------------------------------------------------------- lineProfit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINE_PROFIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINE_PROFIT} attribute.
     */
    public BigDecimal getLineProfit() {
        return lineProfit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINE_PROFIT} attribute with the specified value.
     *
     * @param lineProfit new value for {@value #ATTRIBUTE_NAME_LINE_PROFIT} attribute.
     */
    public void setLineProfit(final BigDecimal lineProfit) {
        this.lineProfit = lineProfit;
    }

    // ------------------------------------------------------------------------------- extendedPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXTENDED_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXTENDED_PRICE} attribute.
     */
    public BigDecimal getExtendedPrice() {
        return extendedPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXTENDED_PRICE} attribute with the specified value.
     *
     * @param extendedPrice new value for {@value #ATTRIBUTE_NAME_EXTENDED_PRICE} attribute.
     */
    public void setExtendedPrice(final BigDecimal extendedPrice) {
        this.extendedPrice = extendedPrice;
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

    // ------------------------------------------------------------------------------ lastEditedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public LocalDateTime getLastEditedWhen() {
        return lastEditedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute with the specified value.
     *
     * @param lastEditedWhen new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public void setLastEditedWhen(final LocalDateTime lastEditedWhen) {
        this.lastEditedWhen = lastEditedWhen;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.InvoiceLineID", schema = "Sequences",
                       sequenceName = "InvoiceLineID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.InvoiceLineID")
    @Column(name = COLUMN_NAME_INVOICE_LINE_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_INVOICE_LINE_ID,
                          value = "Numeric ID used for reference to a line on an invoice within "
                                  + "the database")
    @MsDefault(name = "DF_Sales_InvoiceLines_InvoiceLineID", value = "(NEXT VALUE FOR [Sequences].[InvoiceLineID])")
    private Integer invoiceLineId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_INVOICE_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_INVOICE_ID,
                          value = "Invoice that this line is associated with")
    private Integer invoiceId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_STOCK_ITEM_ID,
                          value = "Stock item for this invoice line")
    private Integer stockItemId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DESCRIPTION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DESCRIPTION, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_DESCRIPTION)
    @MsDescription.Column(name = COLUMN_NAME_DESCRIPTION,
                          value = "Description of the item supplied (Usually the stock item "
                                  + "name but can be overridden)")
    private String description;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PACKAGE_TYPE_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PACKAGE_TYPE_ID, value = "Type of package supplied")
    private Integer packageTypeId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_QUANTITY, value = "Quantity supplied")
    private Integer quantity;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_UNIT_PRICE - COLUMN_SCALE_UNIT_PRICE, fraction = COLUMN_SCALE_UNIT_PRICE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_UNIT_PRICE, scale = COLUMN_SCALE_UNIT_PRICE)
    @MsDescription.Column(name = COLUMN_NAME_UNIT_PRICE, value = "Unit price charged")
    private BigDecimal unitPrice;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_TAX_RATE - COLUMN_SCALE_TAX_RATE, fraction = COLUMN_SCALE_TAX_RATE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TAX_RATE, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_TAX_RATE, scale = COLUMN_SCALE_TAX_RATE)
    @MsDescription.Column(name = COLUMN_NAME_TAX_RATE, value = "Tax rate to be applied")
    private BigDecimal taxRate;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_TAX_AMOUNT - COLUMN_SCALE_TAX_AMOUNT, fraction = COLUMN_SCALE_TAX_AMOUNT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TAX_AMOUNT, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_TAX_AMOUNT, scale = COLUMN_SCALE_TAX_AMOUNT)
    @MsDescription.Column(name = COLUMN_NAME_TAX_AMOUNT, value = "Tax amount calculated")
    private BigDecimal taxAmount;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_LINE_PROFIT - COLUMN_SCALE_LINE_PROFIT, fraction = COLUMN_SCALE_LINE_PROFIT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LINE_PROFIT, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_LINE_PROFIT, scale = COLUMN_SCALE_LINE_PROFIT)
    @MsDescription.Column(name = COLUMN_NAME_LINE_PROFIT,
                          value = "Profit made on this line item at current cost price")
    private BigDecimal lineProfit;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_EXTENDED_PRICE - COLUMN_SCALE_EXTENDED_PRICE,
            fraction = COLUMN_SCALE_EXTENDED_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EXTENDED_PRICE, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_EXTENDED_PRICE, scale = COLUMN_SCALE_EXTENDED_PRICE)
    @MsDescription.Column(name = COLUMN_NAME_EXTENDED_PRICE, value = "Extended line price charged")
    private BigDecimal extendedPrice;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN, nullable = false, insertable = true, updatable = true)
    @MsDefault(name = "DF_Sales_InvoiceLines_LastEditedWhen", value = "(sysdatetime())")
    private LocalDateTime lastEditedWhen;
}

