package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;

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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedScrapReason;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedWorkOrder;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Optional;

/**
 * An entity which maps the {@code Production.WorkOrder} table.
 */
@Entity(name = "ProductionWorkOrder")
@Table(schema = MappedWorkOrder.TABLE_SCHEMA,
       name = MappedWorkOrder.TABLE_NAME,
       check = {
               @CheckConstraint(
                       name = "CK_WorkOrder_EndDate",
                       constraint = "([EndDate]>=[StartDate] OR [EndDate] IS NULL)"
               ),
               @CheckConstraint(
                       name = "CK_WorkOrder_OrderQty",
                       constraint = "([OrderQty]>(0))"
               ),
               @CheckConstraint(
                       name = "CK_WorkOrder_ScrappedQty",
                       constraint = "([ScrappedQty]>=(0))"
               )
       }
)
public class WorkOrder
        extends MappedWorkOrder {

    /**
     * The name of the attribute of the {@code FK_WorkOrder_Product_ProductID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute of the {@code FK_WorkOrder_ScrapReason_ScrapReasonID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCRAP_REASON = "scrapReason";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected WorkOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_WorkOrder_Product_ProductID} foreign key
     * references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_WorkOrder_Product_ProductID} foreign key
     * references, and the value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    public void setProduct(final Product product) {
        this.product = product;
        setProductId(product == null ? null : product.getProductId());
    }

    // --------------------------------------------------------------------------------- scrapReason

    /**
     * Returns the {@code ScrapReason} which the {@code FK_WorkOrder_ScrapReason_ScrapReasonID}
     * foreign key references.
     *
     * @return the {@code ScrapReason} referenced; {@code null} when none is.
     */
    public ScrapReason getScrapReason() {
        return scrapReason;
    }

    /**
     * Replaces the {@code ScrapReason} which the {@code FK_WorkOrder_ScrapReason_ScrapReasonID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param scrapReason the {@code ScrapReason} to reference; {@code null} for none.
     */
    public void setScrapReason(final ScrapReason scrapReason) {
        this.scrapReason = scrapReason;
        setScrapReasonId(
                Optional.ofNullable(scrapReason).map(ScrapReason::getScrapReasonId).orElse(null));
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Product product;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SCRAP_REASON_ID,
                referencedColumnName = MappedScrapReason.COLUMN_NAME_SCRAP_REASON_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private ScrapReason scrapReason;
}

