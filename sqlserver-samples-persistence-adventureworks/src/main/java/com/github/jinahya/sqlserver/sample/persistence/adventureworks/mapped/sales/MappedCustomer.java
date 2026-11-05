package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;

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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsComputedColumn;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.Customer} table.
 * <p>
 * The {@value #COLUMN_NAME_PERSON_ID}, {@value #COLUMN_NAME_STORE_ID},
 * {@value #COLUMN_NAME_TERRITORY_ID} columns are mapped read-only, as a foreign key; the extending
 * entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.Customer}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code CustomerID}</th><td>Primary key.</td></tr>
 * <tr><th scope="row">{@code PersonID}</th><td>Foreign key to Person.BusinessEntityID</td></tr>
 * <tr><th scope="row">{@code StoreID}</th><td>Foreign key to Store.BusinessEntityID</td></tr>
 * <tr><th scope="row">{@code TerritoryID}</th>
 *     <td>ID of the territory in which the customer is located. Foreign key to
 *     SalesTerritory.SalesTerritoryID.</td></tr>
 * <tr><th scope="row">{@code AccountNumber}</th>
 *     <td>Unique number identifying the customer assigned by the accounting system.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.Customer}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code rowguid}</th><td>{@code (newid())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Computed columns of {@code Sales.Customer}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th><th scope="col">Persisted</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code AccountNumber}</th>
 *     <td>{@code (isnull('AW'+[dbo].[ufnLeadingZeros]([CustomerID]),''))}</td><td>{@code false}
 *     </td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/newid-transact-sql">NEWID</a>
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/isnull-transact-sql">ISNULL</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedCustomer.TABLE_SCHEMA,
        table = MappedCustomer.TABLE_NAME,
        value = "Current customer information. Also see the Person and Store tables."
)
public abstract class MappedCustomer {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Customer";

    // ---------------------------------------------------------------------------------- CustomerID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ------------------------------------------------------------------------------------ PersonID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PERSON_ID = "PersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERSON_ID = "personId";

    // ------------------------------------------------------------------------------------- StoreID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "StoreID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    // --------------------------------------------------------------------------------- TerritoryID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_ID = "TerritoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

    // ------------------------------------------------------------------------------- AccountNumber

    /**
     * The name of the
     * {@code varchar(10), computed as (isnull('AW'+[dbo].[ufnLeadingZeros]([CustomerID]),'')), NOT
     * NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACCOUNT_NUMBER = "AccountNumber";

    /**
     * The length of the {@value #COLUMN_NAME_ACCOUNT_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ACCOUNT_NUMBER = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACCOUNT_NUMBER} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACCOUNT_NUMBER = "accountNumber";

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
    protected MappedCustomer() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "customerId=" + customerId
               + ",personId=" + personId
               + ",storeId=" + storeId
               + ",territoryId=" + territoryId
               + ",accountNumber=" + accountNumber
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
        if (!(obj instanceof MappedCustomer that)) {
            return false;
        }
        return getCustomerId() != null
               && getCustomerId().equals(that.getCustomerId());
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
        return MappedCustomer.class.hashCode();
    }

    // ---------------------------------------------------------------------------------- customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified
     * value.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    // ------------------------------------------------------------------------------------ personId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERSON_ID} attribute.
     */
    protected Integer getPersonId() {
        return personId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERSON_ID} attribute with the specified
     * value. The attribute is read-only; the extending entity calls this to keep it in step with
     * the association which writes the column.
     *
     * @param personId new value for {@value #ATTRIBUTE_NAME_PERSON_ID} attribute.
     */
    protected void setPersonId(final Integer personId) {
        this.personId = personId;
    }

    // ------------------------------------------------------------------------------------- storeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    protected Integer getStoreId() {
        return storeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute with the specified
     * value. The attribute is read-only; the extending entity calls this to keep it in step with
     * the association which writes the column.
     *
     * @param storeId new value for {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    protected void setStoreId(final Integer storeId) {
        this.storeId = storeId;
    }

    // --------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified
     * value. The attribute is read-only; the extending entity calls this to keep it in step with
     * the association which writes the column.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(final Integer territoryId) {
        this.territoryId = territoryId;
    }

    // ------------------------------------------------------------------------------- accountNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute with the
     * specified value. The database writes the column; this is for the provider and the extending
     * class only.
     *
     * @param accountNumber new value for {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute.
     */
    protected void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
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
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false,
            comment = "Primary key.")
    private Integer customerId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PERSON_ID, nullable = true, insertable = false, updatable = false,
            comment = "Foreign key to Person.BusinessEntityID")
    private Integer personId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = true, insertable = false, updatable = false,
            comment = "Foreign key to Store.BusinessEntityID")
    private Integer storeId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TERRITORY_ID, nullable = true, insertable = false, updatable = false,
            comment = "ID of the territory in which the customer is located. Foreign key to "
                      + "SalesTerritory.SalesTerritoryID.")
    private Integer territoryId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Size(max = COLUMN_LENGTH_ACCOUNT_NUMBER)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ACCOUNT_NUMBER, nullable = false, insertable = false,
            updatable = false, length = COLUMN_LENGTH_ACCOUNT_NUMBER,
            comment = "Unique number identifying the customer assigned by the accounting system.")
    @MsComputedColumn("(isnull('AW'+[dbo].[ufnLeadingZeros]([CustomerID]),''))")
    private String accountNumber;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false,
            comment = "ROWGUIDCOL number uniquely identifying the record. Used to support a merge "
                      + "replication sample.")
    @MsDefault(name = "DF_Customer_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_Customer_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

