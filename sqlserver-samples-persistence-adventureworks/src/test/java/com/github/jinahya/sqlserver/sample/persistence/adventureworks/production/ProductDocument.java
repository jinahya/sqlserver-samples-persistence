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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductDocument;
import com.github.jinahya.sqlserver.sample.persistence.common.Heirarchyid;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * A plain class which holds a row of the {@code Production.ProductDocument} table, which no entity can map: the primary
 * key, ProductID, DocumentNode, includes a hierarchyid column, which no identifier type of Jakarta Persistence holds,
 * and no other unique key exists.
 * <p>
 * The public constructor takes every column, in the order the table declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class ProductDocument extends MappedProductDocument {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected ProductDocument() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param productId    the value of the {@value #COLUMN_NAME_PRODUCT_ID} column.
     * @param documentNode the value of the {@value #COLUMN_NAME_DOCUMENT_NODE} column, in its binary form.
     * @param modifiedDate the value of the {@value #COLUMN_NAME_MODIFIED_DATE} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public ProductDocument(final Integer productId,
                           final byte[] documentNode,
                           final LocalDateTime modifiedDate) {
        super();
        setProductId(productId);
        setDocumentNode(Optional.ofNullable(documentNode).map(Heirarchyid::decode).orElse(null));
        setModifiedDate(modifiedDate);
    }
}

