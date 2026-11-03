package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;

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

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The identifier class of the {@code Sales.SalesTerritoryHistory} table, whose rows are identified by
 * {@code BusinessEntityID}, {@code StartDate}, {@code TerritoryID}.
 * <p>
 * An entity which extends {@link MappedSalesTerritoryHistory} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class SalesTerritoryHistoryId {

    /**
     * The name of the attribute which maps the
     * {@value MappedSalesTerritoryHistory#COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    /**
     * The name of the attribute which maps the
     * {@value MappedSalesTerritoryHistory#COLUMN_NAME_START_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    /**
     * The name of the attribute which maps the
     * {@value MappedSalesTerritoryHistory#COLUMN_NAME_TERRITORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public SalesTerritoryHistoryId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",startDate=" + startDate
               + ",territoryId=" + territoryId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesTerritoryHistoryId that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getStartDate(), that.getStartDate()) &&
               Objects.equals(getTerritoryId(), that.getTerritoryId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getStartDate(), getTerritoryId());
    }

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of the {@code businessEntityId} attribute.
     *
     * @return current value of the {@code businessEntityId} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of the {@code businessEntityId} attribute with the specified value.
     *
     * @param businessEntityId new value for the {@code businessEntityId} attribute.
     */
    public void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ----------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of the {@code startDate} attribute.
     *
     * @return current value of the {@code startDate} attribute.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of the {@code startDate} attribute with the specified value.
     *
     * @param startDate new value for the {@code startDate} attribute.
     */
    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    // --------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of the {@code territoryId} attribute.
     *
     * @return current value of the {@code territoryId} attribute.
     */
    public Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of the {@code territoryId} attribute with the specified value.
     *
     * @param territoryId new value for the {@code territoryId} attribute.
     */
    public void setTerritoryId(final Integer territoryId) {
        this.territoryId = territoryId;
    }

    // ---------------------------------------------------------------------------------------------
    /**
     * The value of the {@code businessEntityId} attribute.
     */
    @NotNull
    private Integer businessEntityId;

    /**
     * The value of the {@code startDate} attribute.
     */
    @NotNull
    private LocalDateTime startDate;

    /**
     * The value of the {@code territoryId} attribute.
     */
    @NotNull
    private Integer territoryId;
}

