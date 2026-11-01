package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.Items} table.
 * <p>
 * The {@value #COLUMN_NAME_ITEM_TYPE_ID}, {@value #COLUMN_NAME_UNIT_ID} columns are mapped read-only, as a foreign key;
 * the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedItem {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Items";

    // ---------------------------------------------------------------------------------------------------------- ItemID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_ID = "ItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_ID = "itemId";

    // -------------------------------------------------------------------------------------------------------- ItemCode

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_CODE = "ItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_CODE = "itemCode";

    // -------------------------------------------------------------------------------------------------------- ItemName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_NAME = "ItemName";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_NAME = "itemName";

    // ------------------------------------------------------------------------------------------------------ ItemTypeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_TYPE_ID = "ItemTypeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_TYPE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_TYPE_ID = "itemTypeId";

    // ---------------------------------------------------------------------------------------------------------- UnitID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_ID = "UnitID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_ID = "unitId";

    // ----------------------------------------------------------------------------------------------------- Description

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // ---------------------------------------------------------------------------------------------------- StandardCost

    /**
     * The name of the {@code decimal(18, 4), DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STANDARD_COST = "StandardCost";

    /**
     * The precision of the {@value #COLUMN_NAME_STANDARD_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_STANDARD_COST = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_STANDARD_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_STANDARD_COST = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STANDARD_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STANDARD_COST = "standardCost";

    // ------------------------------------------------------------------------------------------------------- ListPrice

    /**
     * The name of the {@code decimal(18, 4), DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LIST_PRICE = "ListPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_LIST_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LIST_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_LIST_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LIST_PRICE = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LIST_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LIST_PRICE = "listPrice";

    // -------------------------------------------------------------------------------------------------------- IsActive

    /**
     * The name of the {@code bit, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ACTIVE = "IsActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ACTIVE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ACTIVE = "isActive";

    // ---------------------------------------------------------------------------------------------------- LeadTimeDays

    /**
     * The name of the {@code int, DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LEAD_TIME_DAYS = "LeadTimeDays";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LEAD_TIME_DAYS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LEAD_TIME_DAYS = "leadTimeDays";

    // ---------------------------------------------------------------------------------------------------- ReorderPoint

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REORDER_POINT = "ReorderPoint";

    /**
     * The precision of the {@value #COLUMN_NAME_REORDER_POINT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REORDER_POINT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_REORDER_POINT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REORDER_POINT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REORDER_POINT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REORDER_POINT = "reorderPoint";

    // ----------------------------------------------------------------------------------------------------- SafetyStock

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SAFETY_STOCK = "SafetyStock";

    /**
     * The precision of the {@value #COLUMN_NAME_SAFETY_STOCK} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SAFETY_STOCK = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_SAFETY_STOCK} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SAFETY_STOCK = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SAFETY_STOCK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SAFETY_STOCK = "safetyStock";

    // ----------------------------------------------------------------------------------------------------- CreatedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_DATE = "CreatedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_DATE = "createdDate";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
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
    protected MappedItem() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "itemId=" + itemId
               + ",itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",itemTypeId=" + itemTypeId
               + ",unitId=" + unitId
               + ",description=" + description
               + ",standardCost=" + standardCost
               + ",listPrice=" + listPrice
               + ",isActive=" + isActive
               + ",leadTimeDays=" + leadTimeDays
               + ",reorderPoint=" + reorderPoint
               + ",safetyStock=" + safetyStock
               + ",createdDate=" + createdDate
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
        if (!(obj instanceof MappedItem that)) {
            return false;
        }
        return Objects.equals(itemId, that.itemId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(itemId);
    }

    // ---------------------------------------------------------------------------------------------------------- itemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute with the specified value.
     *
     * @param itemId new value for {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    protected void setItemId(final Integer itemId) {
        this.itemId = itemId;
    }

    // -------------------------------------------------------------------------------------------------------- itemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute with the specified value.
     *
     * @param itemCode new value for {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    public void setItemCode(final String itemCode) {
        this.itemCode = itemCode;
    }

    // -------------------------------------------------------------------------------------------------------- itemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute with the specified value.
     *
     * @param itemName new value for {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    // ------------------------------------------------------------------------------------------------------ itemTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE_ID} attribute.
     */
    public Integer getItemTypeId() {
        return itemTypeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param itemTypeId new value for {@value #ATTRIBUTE_NAME_ITEM_TYPE_ID} attribute.
     */
    protected void setItemTypeId(final Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    // ---------------------------------------------------------------------------------------------------------- unitId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_ID} attribute.
     */
    public Integer getUnitId() {
        return unitId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param unitId new value for {@value #ATTRIBUTE_NAME_UNIT_ID} attribute.
     */
    protected void setUnitId(final Integer unitId) {
        this.unitId = unitId;
    }

    // ----------------------------------------------------------------------------------------------------- description

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

    // ---------------------------------------------------------------------------------------------------- standardCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     */
    public BigDecimal getStandardCost() {
        return standardCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute with the specified value.
     *
     * @param standardCost new value for {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     */
    public void setStandardCost(final BigDecimal standardCost) {
        this.standardCost = standardCost;
    }

    // ------------------------------------------------------------------------------------------------------- listPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute.
     */
    public BigDecimal getListPrice() {
        return listPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute with the specified value.
     *
     * @param listPrice new value for {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute.
     */
    public void setListPrice(final BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    // -------------------------------------------------------------------------------------------------------- isActive

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute with the specified value.
     *
     * @param isActive new value for {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    // ---------------------------------------------------------------------------------------------------- leadTimeDays

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     */
    public Integer getLeadTimeDays() {
        return leadTimeDays;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute with the specified value.
     *
     * @param leadTimeDays new value for {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     */
    public void setLeadTimeDays(final Integer leadTimeDays) {
        this.leadTimeDays = leadTimeDays;
    }

    // ---------------------------------------------------------------------------------------------------- reorderPoint

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     */
    public BigDecimal getReorderPoint() {
        return reorderPoint;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute with the specified value.
     *
     * @param reorderPoint new value for {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     */
    public void setReorderPoint(final BigDecimal reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    // ----------------------------------------------------------------------------------------------------- safetyStock

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK} attribute.
     */
    public BigDecimal getSafetyStock() {
        return safetyStock;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK} attribute with the specified value.
     *
     * @param safetyStock new value for {@value #ATTRIBUTE_NAME_SAFETY_STOCK} attribute.
     */
    public void setSafetyStock(final BigDecimal safetyStock) {
        this.safetyStock = safetyStock;
    }

    // ----------------------------------------------------------------------------------------------------- createdDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute with the specified value.
     *
     * @param createdDate new value for {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    public void setCreatedDate(final LocalDateTime createdDate) {
        this.createdDate = createdDate;
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
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ITEM_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer itemId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ITEM_CODE
    )
    private String itemCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ITEM_NAME
    )
    private String itemName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_TYPE_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer itemTypeId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer unitId;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DESCRIPTION,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String description;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_STANDARD_COST - COLUMN_SCALE_STANDARD_COST,
            fraction = COLUMN_SCALE_STANDARD_COST)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STANDARD_COST,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_STANDARD_COST,
            scale = COLUMN_SCALE_STANDARD_COST
    )
    private BigDecimal standardCost;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_LIST_PRICE - COLUMN_SCALE_LIST_PRICE, fraction = COLUMN_SCALE_LIST_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LIST_PRICE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_LIST_PRICE,
            scale = COLUMN_SCALE_LIST_PRICE
    )
    private BigDecimal listPrice;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ACTIVE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isActive;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LEAD_TIME_DAYS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer leadTimeDays;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_REORDER_POINT - COLUMN_SCALE_REORDER_POINT,
            fraction = COLUMN_SCALE_REORDER_POINT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REORDER_POINT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_REORDER_POINT,
            scale = COLUMN_SCALE_REORDER_POINT
    )
    private BigDecimal reorderPoint;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_SAFETY_STOCK - COLUMN_SCALE_SAFETY_STOCK, fraction = COLUMN_SCALE_SAFETY_STOCK)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SAFETY_STOCK,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SAFETY_STOCK,
            scale = COLUMN_SCALE_SAFETY_STOCK
    )
    private BigDecimal safetyStock;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime createdDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

