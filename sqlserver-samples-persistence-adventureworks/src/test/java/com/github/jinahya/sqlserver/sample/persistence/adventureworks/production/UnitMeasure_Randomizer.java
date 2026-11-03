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

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Randomizes a new {@link UnitMeasure}; the generated identifier and what SQL Server fills are left out.
 */
class UnitMeasure_Randomizer
        extends PodamObjectRandomizer<UnitMeasure> {

    /**
     * The number of the next code. The codes go through every number of the column's digits before one comes again, so
     * that the units which one transaction persists, two for a product, take different codes; a code drawn at random
     * would now and then be taken twice.
     */
    private static final AtomicInteger NEXT_CODE = new AtomicInteger(ThreadLocalRandom.current().nextInt(1000));

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    UnitMeasure_Randomizer() {
        super(UnitMeasure.class, List.of(
                UnitMeasure.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a code of digits, which no installed unit has, the installed codes being of
     *         letters, and which no instance this randomizer returned of the last thousand has.
     */
    @Override
    public UnitMeasure get() {
        final var instance = super.get();
        final var length = UnitMeasure.COLUMN_LENGTH_UNIT_MEASURE_CODE;
        final var bound = (int) Math.pow(10, length);
        instance.setUnitMeasureCode(String.format("%0" + length + "d", Math.floorMod(NEXT_CODE.getAndIncrement(), bound)));
        return instance;
    }
}
