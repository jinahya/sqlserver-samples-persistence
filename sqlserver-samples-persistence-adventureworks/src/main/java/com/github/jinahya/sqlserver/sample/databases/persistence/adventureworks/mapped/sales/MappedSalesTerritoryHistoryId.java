package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales;

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

import java.io.Serializable;
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
public class MappedSalesTerritoryHistoryId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedSalesTerritoryHistoryId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",startDate=" + startDate
               + ",territoryId=" + territoryId
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedSalesTerritoryHistoryId that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId) &&
               Objects.equals(startDate, that.startDate) &&
               Objects.equals(territoryId, that.territoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessEntityId, startDate, territoryId);
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

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

    // ------------------------------------------------------------------------------------------------------- startDate

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

    // ----------------------------------------------------------------------------------------------------- territoryId

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

    // -----------------------------------------------------------------------------------------------------------------
    private Integer businessEntityId;

    private LocalDateTime startDate;

    private Integer territoryId;
}

