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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
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

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Warehouse.StockItemStockGroups} table.
 * <p>
 * The {@value #COLUMN_NAME_STOCK_ITEM_ID}, {@value #COLUMN_NAME_STOCK_GROUP_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY}
 * columns are mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Warehouse.StockItemStockGroups}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StockItemStockGroupID}</th>
 *     <td>Internal reference for this linking row</td></tr>
 * <tr><th scope="row">{@code StockItemID}</th>
 *     <td>Stock item assigned to this stock group (FK indexed via unique constraint)</td></tr>
 * <tr><th scope="row">{@code StockGroupID}</th>
 *     <td>StockGroup assigned to this stock item (FK indexed via unique constraint)</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Warehouse.StockItemStockGroups}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StockItemStockGroupID}</th>
 *     <td>{@code (NEXT VALUE FOR [Sequences].[StockItemStockGroupID])}</td></tr>
 * <tr><th scope="row">{@code LastEditedWhen}</th><td>{@code (sysdatetime())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/sysdatetime-transact-sql">SYSDATETIME</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedStockItemStockGroup.TABLE_SCHEMA,
        table = MappedStockItemStockGroup.TABLE_NAME,
        value = "Which stock items are in which stock groups"
)
public abstract class MappedStockItemStockGroup {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "StockItemStockGroups";

    // ----------------------------------------------------------------------- StockItemStockGroupID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.StockItemStockGroupID, NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_STOCK_GROUP_ID = "StockItemStockGroupID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_STOCK_GROUP_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_STOCK_GROUP_ID = "stockItemStockGroupId";

    // --------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_ID = "StockItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_ID = "stockItemId";

    // -------------------------------------------------------------------------------- StockGroupID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_GROUP_ID = "StockGroupID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_GROUP_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_GROUP_ID = "stockGroupId";

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
    protected MappedStockItemStockGroup() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "stockItemStockGroupId=" + stockItemStockGroupId
               + ",stockItemId=" + stockItemId
               + ",stockGroupId=" + stockGroupId
               + ",lastEditedBy=" + lastEditedBy
               + ",lastEditedWhen=" + lastEditedWhen
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
        if (!(obj instanceof MappedStockItemStockGroup that)) {
            return false;
        }
        return getStockItemStockGroupId() != null
               && getStockItemStockGroupId().equals(that.getStockItemStockGroupId());
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
        return MappedStockItemStockGroup.class.hashCode();
    }

    // ----------------------------------------------------------------------- stockItemStockGroupId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_STOCK_GROUP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_STOCK_GROUP_ID} attribute.
     */
    public Integer getStockItemStockGroupId() {
        return stockItemStockGroupId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_STOCK_GROUP_ID} attribute with the specified value.
     *
     * @param stockItemStockGroupId new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_STOCK_GROUP_ID} attribute.
     */
    protected void setStockItemStockGroupId(final Integer stockItemStockGroupId) {
        this.stockItemStockGroupId = stockItemStockGroupId;
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

    // -------------------------------------------------------------------------------- stockGroupId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     */
    protected Integer getStockGroupId() {
        return stockGroupId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param stockGroupId new value for {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     */
    protected void setStockGroupId(final Integer stockGroupId) {
        this.stockGroupId = stockGroupId;
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
    @SequenceGenerator(name = "WideWorldImporters.Sequences.StockItemStockGroupID", schema = "Sequences",
                       sequenceName = "StockItemStockGroupID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "WideWorldImporters.Sequences.StockItemStockGroupID")
    @Column(name = COLUMN_NAME_STOCK_ITEM_STOCK_GROUP_ID, nullable = false, insertable = true, updatable = false,
            comment = "Internal reference for this linking row")
    @MsDefault(name = "DF_Warehouse_StockItemStockGroups_StockItemStockGroupID",
               value = "(NEXT VALUE FOR [Sequences].[StockItemStockGroupID])")
    private Integer stockItemStockGroupId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID, nullable = false, insertable = false, updatable = false,
            comment = "Stock item assigned to this stock group (FK indexed via unique constraint)")
    private Integer stockItemId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCK_GROUP_ID, nullable = false, insertable = false, updatable = false,
            comment = "StockGroup assigned to this stock item (FK indexed via unique constraint)")
    private Integer stockGroupId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (sysdatetime()) fills it on an INSERT; the date and time the record
    // was last updated, which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN, nullable = false, insertable = false, updatable = true)
    @MsDefault(name = "DF_Warehouse_StockItemStockGroups_LastEditedWhen", value = "(sysdatetime())")
    private LocalDateTime lastEditedWhen;
}

