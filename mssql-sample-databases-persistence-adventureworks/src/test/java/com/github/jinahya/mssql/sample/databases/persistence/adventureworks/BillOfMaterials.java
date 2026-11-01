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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedBillOfMaterials;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedProduct;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedUnitMeasure;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Production.BillOfMaterials} table.
 */
@Entity
@Table(schema = MappedBillOfMaterials.TABLE_SCHEMA, name = MappedBillOfMaterials.TABLE_NAME)
public class BillOfMaterials extends MappedBillOfMaterials {

    /**
     * The name of the attribute of the {@code FK_BillOfMaterials_Product_ProductAssemblyID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ASSEMBLY = "productAssembly";

    /**
     * The name of the attribute of the {@code FK_BillOfMaterials_Product_ComponentID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPONENT = "component";

    /**
     * The name of the attribute of the {@code FK_BillOfMaterials_UnitMeasure_UnitMeasureCode} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_MEASURE = "unitMeasure";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected BillOfMaterials() {
        super();
    }

    // ------------------------------------------------------------------------------------------------- productAssembly

    /**
     * Returns the {@code Product} which the {@code FK_BillOfMaterials_Product_ProductAssemblyID} foreign key
     * references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    @Nullable
    public Product getProductAssembly() {
        return productAssembly;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_BillOfMaterials_Product_ProductAssemblyID} foreign key
     * references, and the
     * value of the column it consists of.
     *
     * @param productAssembly the {@code Product} to reference; {@code null} for none.
     */
    public void setProductAssembly(@Nullable final Product productAssembly) {
        this.productAssembly = productAssembly;
        setProductAssemblyId(productAssembly == null ? null : productAssembly.getProductId());
    }

    // ------------------------------------------------------------------------------------------------------- component

    /**
     * Returns the {@code Product} which the {@code FK_BillOfMaterials_Product_ComponentID} foreign key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    @Nullable
    public Product getComponent() {
        return component;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_BillOfMaterials_Product_ComponentID} foreign key references,
     * and the
     * value of the column it consists of.
     *
     * @param component the {@code Product} to reference; {@code null} for none.
     */
    public void setComponent(@Nullable final Product component) {
        this.component = component;
        setComponentId(component == null ? null : component.getProductId());
    }

    // ----------------------------------------------------------------------------------------------------- unitMeasure

    /**
     * Returns the {@code UnitMeasure} which the {@code FK_BillOfMaterials_UnitMeasure_UnitMeasureCode} foreign key
     * references.
     *
     * @return the {@code UnitMeasure} referenced; {@code null} when none is.
     */
    @Nullable
    public UnitMeasure getUnitMeasure() {
        return unitMeasure;
    }

    /**
     * Replaces the {@code UnitMeasure} which the {@code FK_BillOfMaterials_UnitMeasure_UnitMeasureCode} foreign key
     * references, and the
     * value of the column it consists of.
     *
     * @param unitMeasure the {@code UnitMeasure} to reference; {@code null} for none.
     */
    public void setUnitMeasure(@Nullable final UnitMeasure unitMeasure) {
        this.unitMeasure = unitMeasure;
        setUnitMeasureCode(unitMeasure == null ? null : unitMeasure.getUnitMeasureCode());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ASSEMBLY_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Product productAssembly;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_COMPONENT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Product component;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_UNIT_MEASURE_CODE,
                referencedColumnName = MappedUnitMeasure.COLUMN_NAME_UNIT_MEASURE_CODE,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private UnitMeasure unitMeasure;
}

