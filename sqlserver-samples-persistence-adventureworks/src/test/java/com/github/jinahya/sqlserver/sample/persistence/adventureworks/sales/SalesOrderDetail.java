package com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSpecialOfferProduct;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.SalesOrderDetailId;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * An entity which maps the {@code Sales.SalesOrderDetail} table.
 */
@Entity(name = "SalesSalesOrderDetail")
@Table(schema = MappedSalesOrderDetail.TABLE_SCHEMA,
       name = MappedSalesOrderDetail.TABLE_NAME,
       comment = "Individual products associated with a specific sales order. See SalesOrderHeader.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_SalesOrderDetail_rowguid",
                       columnNames = {
                               SalesOrderDetail.COLUMN_NAME_ROWGUID
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_SalesOrderDetail_OrderQty",
                       constraint = "([OrderQty]>(0))"
               ),
               @CheckConstraint(
                       name = "CK_SalesOrderDetail_UnitPrice",
                       constraint = "([UnitPrice]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_SalesOrderDetail_UnitPriceDiscount",
                       constraint = "([UnitPriceDiscount]>=(0.00))"
               )
       }
)
@IdClass(SalesOrderDetailId.class)
public class SalesOrderDetail
        extends MappedSalesOrderDetail {

    /**
     * The name of the attribute of the {@code FK_SalesOrderDetail_SalesOrderHeader_SalesOrderID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER = "salesOrder";

    /**
     * The name of the attribute of the
     * {@code FK_SalesOrderDetail_SpecialOfferProduct_SpecialOfferIDProductID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SPECIAL_OFFER_PRODUCT = "specialOfferProduct";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code salesOrder}.
     *
     * @param salesOrder the {@code SalesOrderHeader} to reference.
     * @return a new instance which references {@code salesOrder}.
     * @throws NullPointerException if {@code salesOrder} is {@code null}.
     */
    public static SalesOrderDetail of(final SalesOrderHeader salesOrder) {
        Objects.requireNonNull(salesOrder, "salesOrder is null");
        final var instance = new SalesOrderDetail();
        instance.setSalesOrder(salesOrder);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesOrderDetail() {
        super();
    }

    // ---------------------------------------------------------------------------------- salesOrder

    /**
     * Returns the {@code SalesOrderHeader} which the
     * {@code FK_SalesOrderDetail_SalesOrderHeader_SalesOrderID} foreign key references.
     *
     * @return the {@code SalesOrderHeader} referenced; {@code null} when none is.
     */
    public SalesOrderHeader getSalesOrder() {
        return salesOrder;
    }

    /**
     * Replaces the {@code SalesOrderHeader} which the
     * {@code FK_SalesOrderDetail_SalesOrderHeader_SalesOrderID} foreign key references, and the
     * value of the column it consists of.
     *
     * @param salesOrder the {@code SalesOrderHeader} to reference; {@code null} for none.
     */
    private void setSalesOrder(final SalesOrderHeader salesOrder) {
        this.salesOrder = salesOrder;
        setSalesOrderId(Optional.ofNullable(salesOrder).map(SalesOrderHeader::getSalesOrderId)
                                .orElse(null));
    }

    // ------------------------------------------------------------------------- specialOfferProduct

    /**
     * Returns the {@code SpecialOfferProduct} which the
     * {@code FK_SalesOrderDetail_SpecialOfferProduct_SpecialOfferIDProductID} foreign key
     * references.
     *
     * @return the {@code SpecialOfferProduct} referenced; {@code null} when none is.
     */
    public SpecialOfferProduct getSpecialOfferProduct() {
        return specialOfferProduct;
    }

    /**
     * Replaces the {@code SpecialOfferProduct} which the
     * {@code FK_SalesOrderDetail_SpecialOfferProduct_SpecialOfferIDProductID} foreign key
     * references, and the value of the columns it consists of.
     *
     * @param specialOfferProduct the {@code SpecialOfferProduct} to reference; {@code null} for
     *                            none.
     */
    public void setSpecialOfferProduct(final SpecialOfferProduct specialOfferProduct) {
        this.specialOfferProduct = specialOfferProduct;
        setSpecialOfferId(
                Optional.ofNullable(specialOfferProduct).map(SpecialOfferProduct::getSpecialOfferId)
                        .orElse(null));
        setProductId(Optional.ofNullable(specialOfferProduct).map(SpecialOfferProduct::getProductId)
                             .orElse(null));
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_ORDER_ID,
                referencedColumnName = MappedSalesOrderHeader.COLUMN_NAME_SALES_ORDER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private SalesOrderHeader salesOrder;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = COLUMN_NAME_SPECIAL_OFFER_ID,
                        referencedColumnName =
                                MappedSpecialOfferProduct.COLUMN_NAME_SPECIAL_OFFER_ID,
                        nullable = false,
                        insertable = true,
                        updatable = true
            ),
            @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                        referencedColumnName = MappedSpecialOfferProduct.COLUMN_NAME_PRODUCT_ID,
                        nullable = false,
                        insertable = true,
                        updatable = true
            )
    })
    private SpecialOfferProduct specialOfferProduct;
}

