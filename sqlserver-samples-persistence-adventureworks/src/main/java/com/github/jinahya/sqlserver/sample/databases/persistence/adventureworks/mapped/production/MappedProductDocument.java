package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.production;

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

import com.github.jinahya.sqlserver.sample.databases.persistence.common.Heirarchyid;
import com.github.jinahya.sqlserver.sample.databases.persistence.common.HeirarchyidAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductDocument} table.
 * <p>
 * Identifier: the primary key, ProductID, DocumentNode, includes a hierarchyid column, which no identifier type of
 * Jakarta Persistence holds, and no other unique key exists.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 */
@MappedSuperclass
public abstract class MappedProductDocument {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductDocument";

    // ------------------------------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ---------------------------------------------------------------------------------------------------- DocumentNode

    /**
     * The name of the {@code hierarchyid, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DOCUMENT_NODE = "DocumentNode";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOCUMENT_NODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DOCUMENT_NODE = "documentNode";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
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
    protected MappedProductDocument() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productId=" + productId
               + ",documentNode=" + documentNode
               + ",modifiedDate=" + modifiedDate
               + "}";
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(final Integer productId) {
        this.productId = productId;
    }

    // ---------------------------------------------------------------------------------------------------- documentNode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOCUMENT_NODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOCUMENT_NODE} attribute.
     */
    public Heirarchyid getDocumentNode() {
        return documentNode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOCUMENT_NODE} attribute with the specified value.
     *
     * @param documentNode new value for {@value #ATTRIBUTE_NAME_DOCUMENT_NODE} attribute.
     */
    protected void setDocumentNode(final Heirarchyid documentNode) {
        this.documentNode = documentNode;
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
    protected void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer productId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = HeirarchyidAttributeConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DOCUMENT_NODE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Heirarchyid documentNode;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

