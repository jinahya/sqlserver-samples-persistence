package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.purchasing;

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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.production.MappedProduct;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.purchasing.MappedPurchaseOrderDetail;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.purchasing.MappedPurchaseOrderDetailId;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.purchasing.MappedPurchaseOrderHeader;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.production.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Purchasing.PurchaseOrderDetail} table.
 */
@Entity(name = "PurchasingPurchaseOrderDetail")
@Table(schema = MappedPurchaseOrderDetail.TABLE_SCHEMA, name = MappedPurchaseOrderDetail.TABLE_NAME)
@IdClass(MappedPurchaseOrderDetailId.class)
public class PurchaseOrderDetail extends MappedPurchaseOrderDetail {

    /**
     * The name of the attribute of the {@code FK_PurchaseOrderDetail_Product_ProductID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute of the {@code FK_PurchaseOrderDetail_PurchaseOrderHeader_PurchaseOrderID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER = "purchaseOrder";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PurchaseOrderDetail() {
        super();
    }

    // --------------------------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_PurchaseOrderDetail_Product_ProductID} foreign key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_PurchaseOrderDetail_Product_ProductID} foreign key references,
     * and the value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    public void setProduct(final Product product) {
        this.product = product;
        setProductId(product == null ? null : product.getProductId());
    }

    // --------------------------------------------------------------------------------------------------- purchaseOrder

    /**
     * Returns the {@code PurchaseOrderHeader} which the
     * {@code FK_PurchaseOrderDetail_PurchaseOrderHeader_PurchaseOrderID} foreign key references.
     *
     * @return the {@code PurchaseOrderHeader} referenced; {@code null} when none is.
     */
    public PurchaseOrderHeader getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Replaces the {@code PurchaseOrderHeader} which the
     * {@code FK_PurchaseOrderDetail_PurchaseOrderHeader_PurchaseOrderID} foreign key references, and the value of the
     * column it consists of.
     *
     * @param purchaseOrder the {@code PurchaseOrderHeader} to reference; {@code null} for none.
     */
    public void setPurchaseOrder(final PurchaseOrderHeader purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        setPurchaseOrderId(purchaseOrder == null ? null : purchaseOrder.getPurchaseOrderId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Product product;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PURCHASE_ORDER_ID,
                referencedColumnName = MappedPurchaseOrderHeader.COLUMN_NAME_PURCHASE_ORDER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private PurchaseOrderHeader purchaseOrder;
}

