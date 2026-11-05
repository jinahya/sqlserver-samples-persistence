package com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProduct;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedUnitMeasure;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.MappedProductVendor;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.MappedVendor;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.ProductVendorId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.UnitMeasure;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * An entity which maps the {@code Purchasing.ProductVendor} table.
 */
@Entity(name = "PurchasingProductVendor")
@Table(schema = MappedProductVendor.TABLE_SCHEMA,
       name = MappedProductVendor.TABLE_NAME,
       comment = "Cross-reference table mapping vendors with the products they supply.",
       check = {
               @CheckConstraint(
                       name = "CK_ProductVendor_AverageLeadTime",
                       constraint = "([AverageLeadTime]>=(1))"
               ),
               @CheckConstraint(
                       name = "CK_ProductVendor_LastReceiptCost",
                       constraint = "([LastReceiptCost]>(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_ProductVendor_MaxOrderQty",
                       constraint = "([MaxOrderQty]>=(1))"
               ),
               @CheckConstraint(
                       name = "CK_ProductVendor_MinOrderQty",
                       constraint = "([MinOrderQty]>=(1))"
               ),
               @CheckConstraint(
                       name = "CK_ProductVendor_OnOrderQty",
                       constraint = "([OnOrderQty]>=(0))"
               ),
               @CheckConstraint(
                       name = "CK_ProductVendor_StandardPrice",
                       constraint = "([StandardPrice]>(0.00))"
               )
       }
)
@IdClass(ProductVendorId.class)
public class ProductVendor
        extends MappedProductVendor {

    /**
     * The name of the attribute of the {@code FK_ProductVendor_Product_ProductID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute of the {@code FK_ProductVendor_UnitMeasure_UnitMeasureCode} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_MEASURE = "unitMeasure";

    /**
     * The name of the attribute of the {@code FK_ProductVendor_Vendor_BusinessEntityID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code product} and {@code businessEntity}.
     *
     * @param product        the {@code Product} to reference.
     * @param businessEntity the {@code Vendor} to reference.
     * @return a new instance which references {@code product} and {@code businessEntity}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static ProductVendor of(final Product product,
                                   final Vendor businessEntity) {
        Objects.requireNonNull(product, "product is null");
        Objects.requireNonNull(businessEntity, "businessEntity is null");
        final var instance = new ProductVendor();
        instance.setProduct(product);
        instance.setBusinessEntity(businessEntity);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductVendor() {
        super();
    }

    // ------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_ProductVendor_Product_ProductID} foreign key
     * references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_ProductVendor_Product_ProductID} foreign key
     * references, and the value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    private void setProduct(final Product product) {
        this.product = product;
        setProductId(Optional.ofNullable(product).map(Product::getProductId).orElse(null));
    }

    // --------------------------------------------------------------------------------- unitMeasure

    /**
     * Returns the {@code UnitMeasure} which the
     * {@code FK_ProductVendor_UnitMeasure_UnitMeasureCode} foreign key references.
     *
     * @return the {@code UnitMeasure} referenced; {@code null} when none is.
     */
    public UnitMeasure getUnitMeasure() {
        return unitMeasure;
    }

    /**
     * Replaces the {@code UnitMeasure} which the
     * {@code FK_ProductVendor_UnitMeasure_UnitMeasureCode} foreign key references, and the value of
     * the column it consists of.
     *
     * @param unitMeasure the {@code UnitMeasure} to reference; {@code null} for none.
     */
    public void setUnitMeasure(final UnitMeasure unitMeasure) {
        this.unitMeasure = unitMeasure;
        setUnitMeasureCode(
                Optional.ofNullable(unitMeasure).map(UnitMeasure::getUnitMeasureCode).orElse(null));
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code Vendor} which the {@code FK_ProductVendor_Vendor_BusinessEntityID} foreign
     * key references.
     *
     * @return the {@code Vendor} referenced; {@code null} when none is.
     */
    public Vendor getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Vendor} which the {@code FK_ProductVendor_Vendor_BusinessEntityID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param businessEntity the {@code Vendor} to reference; {@code null} for none.
     */
    private void setBusinessEntity(final Vendor businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(
                Optional.ofNullable(businessEntity).map(Vendor::getBusinessEntityId).orElse(null));
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Product product;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_UNIT_MEASURE_CODE,
                referencedColumnName = MappedUnitMeasure.COLUMN_NAME_UNIT_MEASURE_CODE,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private UnitMeasure unitMeasure;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedVendor.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Vendor businessEntity;
}

