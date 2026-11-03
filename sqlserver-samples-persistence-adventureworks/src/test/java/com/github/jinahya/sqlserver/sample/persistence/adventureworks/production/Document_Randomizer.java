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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import com.github.jinahya.sqlserver.sample.persistence.common.Heirarchyid;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link Document}; the node and the identifier, which Podam can not build, the
 * owner, which the persister sets, and what SQL Server fills or derives are left out.
 */
class Document_Randomizer
        extends PodamObjectRandomizer<Document> {

    Document_Randomizer() {
        super(Document.class, List.of(
                Document.ATTRIBUTE_NAME_DOCUMENT_NODE,
                Document.ATTRIBUTE_NAME_DOCUMENT_LEVEL,
                Document.ATTRIBUTE_NAME_OWNER,
                Document.ATTRIBUTE_NAME_OWNER_EMPLOYEE,
                Document.ATTRIBUTE_NAME_ROWGUID,
                Document.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a node no installed document has, and a new
     *         identifier.
     */
    @Override
    public Document get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // no installed node is this deep
        instance.setDocumentNode(Heirarchyid.parse(
                "/" + random.nextLong(1L, Integer.MAX_VALUE) + "/" +
                random.nextLong(1L, Integer.MAX_VALUE) + "/"));
        instance.setRowguid(UUID.randomUUID().toString());
        return instance;
    }
}
