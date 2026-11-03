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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SalesTerritoryHistory} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.SalesTerritoryHistory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code BusinessEntityID}</th>
 *     <td>Primary key. The sales rep.  Foreign key to SalesPerson.BusinessEntityID.</td></tr>
 * <tr><th scope="row">{@code TerritoryID}</th>
 *     <td>Primary key. Territory identification number. Foreign key to
 *     SalesTerritory.SalesTerritoryID.</td></tr>
 * <tr><th scope="row">{@code StartDate}</th>
 *     <td>Primary key. Date the sales representive started work in the territory.</td></tr>
 * <tr><th scope="row">{@code EndDate}</th>
 *     <td>Date the sales representative left work in the territory.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.SalesTerritoryHistory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
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
        schema = MappedSalesTerritoryHistory.TABLE_SCHEMA,
        table = MappedSalesTerritoryHistory.TABLE_NAME,
        value = "Sales representative transfers to other sales territories."
)
public abstract class MappedSalesTerritoryHistory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesTerritoryHistory";

    // ---------------------------------------------------------------------------- BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_BUSINESS_ENTITY_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_BUSINESS_ENTITY_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // --------------------------------------------------------------------------------- TerritoryID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_ID = "TerritoryID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_TERRITORY_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_TERRITORY_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

    // ----------------------------------------------------------------------------------- StartDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "StartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // ------------------------------------------------------------------------------------- EndDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "EndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

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
    protected MappedSalesTerritoryHistory() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",territoryId=" + territoryId
               + ",startDate=" + startDate
               + ",endDate=" + endDate
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
        if (!(obj instanceof MappedSalesTerritoryHistory that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getStartDate(), that.getStartDate()) &&
               Objects.equals(getTerritoryId(), that.getTerritoryId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getStartDate(), getTerritoryId());
    }

    // ---------------------------------------------------------------------------------- VALIDATION

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_END_DATE} attribute is {@code null} or not before
     * the {@value #ATTRIBUTE_NAME_START_DATE} attribute, as the table's
     * {@code CK_SalesTerritoryHistory_EndDate} constraint requires.
     * {@snippet lang = "sql":
     * ([EndDate]>=[StartDate] OR [EndDate] IS NULL)
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_END_DATE} attribute is {@code null} or
     *         not before the {@value #ATTRIBUTE_NAME_START_DATE} attribute, or if the
     *         {@value #ATTRIBUTE_NAME_START_DATE} attribute is {@code null}; {@code false}
     *         otherwise.
     */
    @AssertTrue(message = "endDate must be null or not before startDate")
    protected boolean isEndDateValid() {
        if (endDate == null || startDate == null) {
            return true;
        }
        return !endDate.isBefore(startDate);
    }

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the
     * specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // --------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified
     * value.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(final Integer territoryId) {
        this.territoryId = territoryId;
    }

    // ----------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified
     * value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    protected void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    // ------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute with the specified
     * value.
     *
     * @param endDate new value for {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
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
    @Max(ATTRIBUTE_MAX_BUSINESS_ENTITY_ID)
    @Min(ATTRIBUTE_MIN_BUSINESS_ENTITY_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                          value = "Primary key. The sales rep.  Foreign key to "
                                  + "SalesPerson.BusinessEntityID.")
    private Integer businessEntityId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Max(ATTRIBUTE_MAX_TERRITORY_ID)
    @Min(ATTRIBUTE_MIN_TERRITORY_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_TERRITORY_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_TERRITORY_ID,
                          value = "Primary key. Territory identification number. Foreign key to "
                                  + "SalesTerritory.SalesTerritoryID.")
    private Integer territoryId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_START_DATE, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_START_DATE,
                          value = "Primary key. Date the sales representive started work in the "
                                  + "territory.")
    private LocalDateTime startDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_END_DATE, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_END_DATE,
                          value = "Date the sales representative left work in the territory.")
    private LocalDateTime endDate;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_ROWGUID,
                          value = "ROWGUIDCOL number uniquely identifying the record. Used to "
                                  + "support a merge replication sample.")
    @MsDefault(name = "DF_SalesTerritoryHistory_rowguid", value = "(newid())")
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
    @MsDefault(name = "DF_SalesTerritoryHistory_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

