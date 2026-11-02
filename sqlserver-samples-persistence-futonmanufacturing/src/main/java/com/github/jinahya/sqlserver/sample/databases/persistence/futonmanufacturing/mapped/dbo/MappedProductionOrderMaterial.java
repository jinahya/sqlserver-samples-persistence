package com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.ProductionOrderMaterial} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCTION_ORDER_ID}, {@value #COLUMN_NAME_ITEM_ID} columns are mapped read-only, as a
 * foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedProductionOrderMaterial {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductionOrderMaterial";

    // --------------------------------------------------------------------------------------------- ProdOrderMaterialID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_ORDER_MATERIAL_ID = "ProdOrderMaterialID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ORDER_MATERIAL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ORDER_MATERIAL_ID = "prodOrderMaterialId";

    // ----------------------------------------------------------------------------------------------- ProductionOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCTION_ORDER_ID = "ProductionOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCTION_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCTION_ORDER_ID = "productionOrderId";

    // ---------------------------------------------------------------------------------------------------------- ItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_ID = "ItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_ID = "itemId";

    // ------------------------------------------------------------------------------------------------ RequiredQuantity

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REQUIRED_QUANTITY = "RequiredQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REQUIRED_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REQUIRED_QUANTITY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REQUIRED_QUANTITY = "requiredQuantity";

    // -------------------------------------------------------------------------------------------------- IssuedQuantity

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ISSUED_QUANTITY = "IssuedQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_ISSUED_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ISSUED_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_ISSUED_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ISSUED_QUANTITY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ISSUED_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ISSUED_QUANTITY = "issuedQuantity";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductionOrderMaterial() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "prodOrderMaterialId=" + prodOrderMaterialId
               + ",productionOrderId=" + productionOrderId
               + ",itemId=" + itemId
               + ",requiredQuantity=" + requiredQuantity
               + ",issuedQuantity=" + issuedQuantity
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
        if (!(obj instanceof MappedProductionOrderMaterial that)) {
            return false;
        }
        return Objects.equals(prodOrderMaterialId, that.prodOrderMaterialId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(prodOrderMaterialId);
    }

    // --------------------------------------------------------------------------------------------- prodOrderMaterialId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_ORDER_MATERIAL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_ORDER_MATERIAL_ID} attribute.
     */
    public Integer getProdOrderMaterialId() {
        return prodOrderMaterialId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_ORDER_MATERIAL_ID} attribute with the specified value.
     *
     * @param prodOrderMaterialId new value for {@value #ATTRIBUTE_NAME_PROD_ORDER_MATERIAL_ID} attribute.
     */
    protected void setProdOrderMaterialId(final Integer prodOrderMaterialId) {
        this.prodOrderMaterialId = prodOrderMaterialId;
    }

    // ----------------------------------------------------------------------------------------------- productionOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute.
     */
    public Integer getProductionOrderId() {
        return productionOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param productionOrderId new value for {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute.
     */
    protected void setProductionOrderId(final Integer productionOrderId) {
        this.productionOrderId = productionOrderId;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param itemId new value for {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    protected void setItemId(final Integer itemId) {
        this.itemId = itemId;
    }

    // ------------------------------------------------------------------------------------------------ requiredQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute.
     */
    public BigDecimal getRequiredQuantity() {
        return requiredQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute with the specified value.
     *
     * @param requiredQuantity new value for {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute.
     */
    public void setRequiredQuantity(final BigDecimal requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    // -------------------------------------------------------------------------------------------------- issuedQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ISSUED_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ISSUED_QUANTITY} attribute.
     */
    public BigDecimal getIssuedQuantity() {
        return issuedQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ISSUED_QUANTITY} attribute with the specified value.
     *
     * @param issuedQuantity new value for {@value #ATTRIBUTE_NAME_ISSUED_QUANTITY} attribute.
     */
    public void setIssuedQuantity(final BigDecimal issuedQuantity) {
        this.issuedQuantity = issuedQuantity;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PROD_ORDER_MATERIAL_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer prodOrderMaterialId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCTION_ORDER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer productionOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer itemId;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_REQUIRED_QUANTITY - COLUMN_SCALE_REQUIRED_QUANTITY,
            fraction = COLUMN_SCALE_REQUIRED_QUANTITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REQUIRED_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_REQUIRED_QUANTITY,
            scale = COLUMN_SCALE_REQUIRED_QUANTITY
    )
    private BigDecimal requiredQuantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_ISSUED_QUANTITY - COLUMN_SCALE_ISSUED_QUANTITY,
            fraction = COLUMN_SCALE_ISSUED_QUANTITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ISSUED_QUANTITY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_ISSUED_QUANTITY,
            scale = COLUMN_SCALE_ISSUED_QUANTITY
    )
    private BigDecimal issuedQuantity;
}

