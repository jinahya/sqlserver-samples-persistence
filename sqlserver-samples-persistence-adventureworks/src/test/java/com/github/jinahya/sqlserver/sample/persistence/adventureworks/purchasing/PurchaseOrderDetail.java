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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.MappedPurchaseOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.MappedPurchaseOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.PurchaseOrderDetailId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product;
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
 * An entity which maps the {@code Purchasing.PurchaseOrderDetail} table.
 */
@Entity(name = "PurchasingPurchaseOrderDetail")
@Table(schema = MappedPurchaseOrderDetail.TABLE_SCHEMA,
       name = MappedPurchaseOrderDetail.TABLE_NAME,
       comment = "Individual products associated with a specific purchase order. See "
                 + "PurchaseOrderHeader.",
       check = {
               @CheckConstraint(
                       name = "CK_PurchaseOrderDetail_OrderQty",
                       constraint = "([OrderQty]>(0))"
               ),
               @CheckConstraint(
                       name = "CK_PurchaseOrderDetail_ReceivedQty",
                       constraint = "([ReceivedQty]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_PurchaseOrderDetail_RejectedQty",
                       constraint = "([RejectedQty]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_PurchaseOrderDetail_UnitPrice",
                       constraint = "([UnitPrice]>=(0.00))"
               )
       }
)
@IdClass(PurchaseOrderDetailId.class)
public class PurchaseOrderDetail
        extends MappedPurchaseOrderDetail {

    /**
     * The name of the attribute of the {@code FK_PurchaseOrderDetail_Product_ProductID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute of the
     * {@code FK_PurchaseOrderDetail_PurchaseOrderHeader_PurchaseOrderID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER = "purchaseOrder";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code purchaseOrder}.
     *
     * @param purchaseOrder the {@code PurchaseOrderHeader} to reference.
     * @return a new instance which references {@code purchaseOrder}.
     * @throws NullPointerException if {@code purchaseOrder} is {@code null}.
     */
    public static PurchaseOrderDetail of(final PurchaseOrderHeader purchaseOrder) {
        Objects.requireNonNull(purchaseOrder, "purchaseOrder is null");
        final var instance = new PurchaseOrderDetail();
        instance.setPurchaseOrder(purchaseOrder);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PurchaseOrderDetail() {
        super();
    }

    // ------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_PurchaseOrderDetail_Product_ProductID}
     * foreign key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_PurchaseOrderDetail_Product_ProductID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    public void setProduct(final Product product) {
        this.product = product;
        setProductId(Optional.ofNullable(product).map(Product::getProductId).orElse(null));
    }

    // ------------------------------------------------------------------------------- purchaseOrder

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
     * {@code FK_PurchaseOrderDetail_PurchaseOrderHeader_PurchaseOrderID} foreign key references,
     * and the value of the column it consists of.
     *
     * @param purchaseOrder the {@code PurchaseOrderHeader} to reference; {@code null} for none.
     */
    private void setPurchaseOrder(final PurchaseOrderHeader purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        setPurchaseOrderId(
                Optional.ofNullable(purchaseOrder).map(PurchaseOrderHeader::getPurchaseOrderId)
                        .orElse(null));
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Product product;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PURCHASE_ORDER_ID,
                referencedColumnName = MappedPurchaseOrderHeader.COLUMN_NAME_PURCHASE_ORDER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private PurchaseOrderHeader purchaseOrder;
}

