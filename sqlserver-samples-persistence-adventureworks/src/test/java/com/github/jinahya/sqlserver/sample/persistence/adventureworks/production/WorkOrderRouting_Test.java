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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks._Entity_Test;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.WorkOrderRoutingId;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link WorkOrderRouting} entity class.
 */
class WorkOrderRouting_Test
        extends _Entity_Test<WorkOrderRouting, WorkOrderRoutingId> {

    WorkOrderRouting_Test() {
        super(WorkOrderRouting.class, WorkOrderRoutingId.class);
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link WorkOrderRouting#equals(Object) equals} compares only the {@code @Id}
     *         attributes, because every other attribute is mutable state.
     */
    @Override
    public SingleTypeEqualsVerifierApi<WorkOrderRouting> equals_verifier_() {
        return super.equals_verifier_()
                .suppress(Warning.SURROGATE_KEY);
    }
}
