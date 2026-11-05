package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing;

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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.ShipMethod} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Purchasing.ShipMethod}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ShipMethodID}</th><td>Primary key for ShipMethod records.</td></tr>
 * <tr><th scope="row">{@code Name}</th><td>Shipping company name.</td></tr>
 * <tr><th scope="row">{@code ShipBase}</th><td>Minimum shipping charge.</td></tr>
 * <tr><th scope="row">{@code ShipRate}</th><td>Shipping charge per pound.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Purchasing.ShipMethod}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ShipBase}</th><td>{@code ((0.00))}</td></tr>
 * <tr><th scope="row">{@code ShipRate}</th><td>{@code ((0.00))}</td></tr>
 * <tr><th scope="row">{@code rowguid}</th><td>{@code (newid())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/newid-transact-sql">NEWID</a>
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedShipMethod.TABLE_SCHEMA,
        table = MappedShipMethod.TABLE_NAME,
        value = "Shipping company lookup table."
)
public abstract class MappedShipMethod {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ShipMethod";

    // -------------------------------------------------------------------------------- ShipMethodID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_METHOD_ID = "ShipMethodID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_METHOD_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_METHOD_ID = "shipMethodId";

    // ---------------------------------------------------------------------------------------- Name

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "Name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // ------------------------------------------------------------------------------------ ShipBase

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_BASE = "ShipBase";

    /**
     * The exclusive minimum value of the {@value #ATTRIBUTE_NAME_SHIP_BASE} attribute, as the
     * {@code CK_ShipMethod_ShipBase} check constraint ({@code [ShipBase]>(0.00)}) requires. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SHIP_BASE_EXCLUSIVE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_BASE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_BASE = "shipBase";

    // ------------------------------------------------------------------------------------ ShipRate

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_RATE = "ShipRate";

    /**
     * The exclusive minimum value of the {@value #ATTRIBUTE_NAME_SHIP_RATE} attribute, as the
     * {@code CK_ShipMethod_ShipRate} check constraint ({@code [ShipRate]>(0.00)}) requires. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SHIP_RATE_EXCLUSIVE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_RATE = "shipRate";

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
    protected MappedShipMethod() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "shipMethodId=" + shipMethodId
               + ",name=" + name
               + ",shipBase=" + shipBase
               + ",shipRate=" + shipRate
               + ",rowguid=" + rowguid
               + ",modifiedDate=" + modifiedDate
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
        if (!(obj instanceof MappedShipMethod that)) {
            return false;
        }
        return getShipMethodId() != null
               && getShipMethodId().equals(that.getShipMethodId());
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
        return MappedShipMethod.class.hashCode();
    }

    // -------------------------------------------------------------------------------- shipMethodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     */
    public Integer getShipMethodId() {
        return shipMethodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute with the
     * specified value.
     *
     * @param shipMethodId new value for {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     */
    protected void setShipMethodId(final Integer shipMethodId) {
        this.shipMethodId = shipMethodId;
    }

    // ---------------------------------------------------------------------------------------- name

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

    // ------------------------------------------------------------------------------------ shipBase

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_BASE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_BASE} attribute.
     */
    public BigDecimal getShipBase() {
        return shipBase;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIP_BASE} attribute with the specified
     * value.
     *
     * @param shipBase new value for {@value #ATTRIBUTE_NAME_SHIP_BASE} attribute.
     */
    public void setShipBase(final BigDecimal shipBase) {
        this.shipBase = shipBase;
    }

    // ------------------------------------------------------------------------------------ shipRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_RATE} attribute.
     */
    public BigDecimal getShipRate() {
        return shipRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIP_RATE} attribute with the specified
     * value.
     *
     * @param shipRate new value for {@value #ATTRIBUTE_NAME_SHIP_RATE} attribute.
     */
    public void setShipRate(final BigDecimal shipRate) {
        this.shipRate = shipRate;
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
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SHIP_METHOD_ID, nullable = false, insertable = false,
            updatable = false,
            comment = "Primary key for ShipMethod records.")
    private Integer shipMethodId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_NAME,
            comment = "Shipping company name.")
    private String name;

    // ---------------------------------------------------------------------------------------------
    // CK_ShipMethod_ShipBase: ([ShipBase]>(0.00))
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_SHIP_BASE_EXCLUSIVE, inclusive = false)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SHIP_BASE, nullable = false, insertable = true, updatable = true, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE,
            comment = "Minimum shipping charge.")
    @MsDefault(name = "DF_ShipMethod_ShipBase", value = "((0.00))")
    private BigDecimal shipBase;

    // ---------------------------------------------------------------------------------------------
    // CK_ShipMethod_ShipRate: ([ShipRate]>(0.00))
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_SHIP_RATE_EXCLUSIVE, inclusive = false)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SHIP_RATE, nullable = false, insertable = true, updatable = true, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE,
            comment = "Shipping charge per pound.")
    @MsDefault(name = "DF_ShipMethod_ShipRate", value = "((0.00))")
    private BigDecimal shipRate;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false,
            comment = "ROWGUIDCOL number uniquely identifying the record. Used to support a merge "
                      + "replication sample.")
    @MsDefault(name = "DF_ShipMethod_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_ShipMethod_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

