package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.production;

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

import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.production.MappedProductAndDescriptionView;
import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.production.MappedProductAndDescriptionViewId;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Production.vProductAndDescription} view.
 * <p>
 * A view is read, not written; the entity maps no association.
 */
@Entity(name = "ProductionProductAndDescriptionView")
@Table(schema = MappedProductAndDescriptionView.TABLE_SCHEMA, name = MappedProductAndDescriptionView.TABLE_NAME)
@IdClass(MappedProductAndDescriptionViewId.class)
public class ProductAndDescriptionView extends MappedProductAndDescriptionView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductAndDescriptionView() {
        super();
    }
}

