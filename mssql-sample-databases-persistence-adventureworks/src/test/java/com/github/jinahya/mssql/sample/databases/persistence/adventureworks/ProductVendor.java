package com.github.jinahya.mssql.sample.databases.persistence.adventureworks;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedProduct;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedProductVendor;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedProductVendorId;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedUnitMeasure;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedVendor;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Purchasing.ProductVendor} table.
 */
@Entity
@Table(schema = MappedProductVendor.TABLE_SCHEMA, name = MappedProductVendor.TABLE_NAME)
@IdClass(MappedProductVendorId.class)
public class ProductVendor extends MappedProductVendor {

    /**
     * The name of the attribute of the {@code FK_ProductVendor_Product_ProductID} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute of the {@code FK_ProductVendor_UnitMeasure_UnitMeasureCode} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_MEASURE = "unitMeasure";

    /**
     * The name of the attribute of the {@code FK_ProductVendor_Vendor_BusinessEntityID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductVendor() {
        super();
    }

    // --------------------------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_ProductVendor_Product_ProductID} foreign key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    @Nullable
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_ProductVendor_Product_ProductID} foreign key references, and the
     * value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    public void setProduct(@Nullable final Product product) {
        this.product = product;
        setProductId(product == null ? null : product.getProductId());
    }

    // ----------------------------------------------------------------------------------------------------- unitMeasure

    /**
     * Returns the {@code UnitMeasure} which the {@code FK_ProductVendor_UnitMeasure_UnitMeasureCode} foreign key
     * references.
     *
     * @return the {@code UnitMeasure} referenced; {@code null} when none is.
     */
    @Nullable
    public UnitMeasure getUnitMeasure() {
        return unitMeasure;
    }

    /**
     * Replaces the {@code UnitMeasure} which the {@code FK_ProductVendor_UnitMeasure_UnitMeasureCode} foreign key
     * references, and the
     * value of the column it consists of.
     *
     * @param unitMeasure the {@code UnitMeasure} to reference; {@code null} for none.
     */
    public void setUnitMeasure(@Nullable final UnitMeasure unitMeasure) {
        this.unitMeasure = unitMeasure;
        setUnitMeasureCode(unitMeasure == null ? null : unitMeasure.getUnitMeasureCode());
    }

    // -------------------------------------------------------------------------------------------------- businessEntity

    /**
     * Returns the {@code Vendor} which the {@code FK_ProductVendor_Vendor_BusinessEntityID} foreign key references.
     *
     * @return the {@code Vendor} referenced; {@code null} when none is.
     */
    @Nullable
    public Vendor getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Vendor} which the {@code FK_ProductVendor_Vendor_BusinessEntityID} foreign key references,
     * and the
     * value of the column it consists of.
     *
     * @param businessEntity the {@code Vendor} to reference; {@code null} for none.
     */
    public void setBusinessEntity(@Nullable final Vendor businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(businessEntity == null ? null : businessEntity.getBusinessEntityId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Product product;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_UNIT_MEASURE_CODE,
                referencedColumnName = MappedUnitMeasure.COLUMN_NAME_UNIT_MEASURE_CODE,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private UnitMeasure unitMeasure;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedVendor.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Vendor businessEntity;
}

