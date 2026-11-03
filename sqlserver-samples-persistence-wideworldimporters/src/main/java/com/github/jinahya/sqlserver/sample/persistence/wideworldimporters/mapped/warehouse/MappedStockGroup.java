package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse;

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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsTemporal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Warehouse.StockGroups} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and keeps every
 * former version of a row in {@code Warehouse.StockGroups_Archive}.
 * <p>
 * The {@value #COLUMN_NAME_LAST_EDITED_BY} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Warehouse.StockGroups}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StockGroupID}</th>
 *     <td>Numeric ID used for reference to a stock group within the database</td></tr>
 * <tr><th scope="row">{@code StockGroupName}</th>
 *     <td>Full name of groups used to categorize stock items</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Warehouse.StockGroups}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StockGroupID}</th><td>{@code (NEXT VALUE FOR [Sequences].[StockGroupID])}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedStockGroup.TABLE_SCHEMA,
        table = MappedStockGroup.TABLE_NAME,
        value = "Groups for categorizing stock items (ie: novelties, toys, edible novelties, etc.)"
)
@MsTemporal(
        historyTable = "Warehouse.StockGroups_Archive",
        periodStart = "ValidFrom",
        periodEnd = "ValidTo"
)
public abstract class MappedStockGroup {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "StockGroups";

    // -------------------------------------------------------------------------------- StockGroupID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.StockGroupID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_GROUP_ID = "StockGroupID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_GROUP_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_GROUP_ID = "stockGroupId";

    // ------------------------------------------------------------------------------ StockGroupName

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_GROUP_NAME = "StockGroupName";

    /**
     * The length of the {@value #COLUMN_NAME_STOCK_GROUP_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STOCK_GROUP_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_GROUP_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_GROUP_NAME = "stockGroupName";

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
    protected MappedStockGroup() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "stockGroupId=" + stockGroupId
               + ",stockGroupName=" + stockGroupName
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
        if (!(obj instanceof MappedStockGroup that)) {
            return false;
        }
        return Objects.equals(stockGroupId, that.stockGroupId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(stockGroupId);
    }

    // -------------------------------------------------------------------------------- stockGroupId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     */
    public Integer getStockGroupId() {
        return stockGroupId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute with the specified value.
     *
     * @param stockGroupId new value for {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     */
    protected void setStockGroupId(final Integer stockGroupId) {
        this.stockGroupId = stockGroupId;
    }

    // ------------------------------------------------------------------------------ stockGroupName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_NAME} attribute.
     */
    public String getStockGroupName() {
        return stockGroupName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_NAME} attribute with the specified value.
     *
     * @param stockGroupName new value for {@value #ATTRIBUTE_NAME_STOCK_GROUP_NAME} attribute.
     */
    public void setStockGroupName(final String stockGroupName) {
        this.stockGroupName = stockGroupName;
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
    @SequenceGenerator(name = "WideWorldImporters.Sequences.StockGroupID", schema = "Sequences",
                       sequenceName = "StockGroupID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.StockGroupID")
    @Column(name = COLUMN_NAME_STOCK_GROUP_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_STOCK_GROUP_ID,
                          value = "Numeric ID used for reference to a stock group within the "
                                  + "database")
    @MsDefault(name = "DF_Warehouse_StockGroups_StockGroupID", value = "(NEXT VALUE FOR [Sequences].[StockGroupID])")
    private Integer stockGroupId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STOCK_GROUP_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCK_GROUP_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_STOCK_GROUP_NAME)
    @MsDescription.Column(name = COLUMN_NAME_STOCK_GROUP_NAME,
                          value = "Full name of groups used to categorize stock items")
    private String stockGroupName;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
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

