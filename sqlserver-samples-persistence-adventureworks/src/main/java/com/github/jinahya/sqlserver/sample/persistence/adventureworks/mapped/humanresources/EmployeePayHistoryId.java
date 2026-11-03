package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources;

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
 * The identifier class of the {@code HumanResources.EmployeePayHistory} table, whose rows are
 * identified by {@code BusinessEntityID}, {@code RateChangeDate}.
 * <p>
 * An entity which extends {@link MappedEmployeePayHistory} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped
 * superclass's {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class EmployeePayHistoryId {

    /**
     * The name of the attribute which maps the
     * {@value MappedEmployeePayHistory#COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    /**
     * The name of the attribute which maps the
     * {@value MappedEmployeePayHistory#COLUMN_NAME_RATE_CHANGE_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RATE_CHANGE_DATE = "rateChangeDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public EmployeePayHistoryId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",rateChangeDate=" + rateChangeDate
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmployeePayHistoryId that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getRateChangeDate(), that.getRateChangeDate());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getRateChangeDate());
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

    // ------------------------------------------------------------------------------ rateChangeDate

    /**
     * Returns current value of the {@code rateChangeDate} attribute.
     *
     * @return current value of the {@code rateChangeDate} attribute.
     */
    public LocalDateTime getRateChangeDate() {
        return rateChangeDate;
    }

    /**
     * Replaces current value of the {@code rateChangeDate} attribute with the specified value.
     *
     * @param rateChangeDate new value for the {@code rateChangeDate} attribute.
     */
    public void setRateChangeDate(final LocalDateTime rateChangeDate) {
        this.rateChangeDate = rateChangeDate;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code businessEntityId} attribute.
     */
    @NotNull
    private Integer businessEntityId;

    /**
     * The value of the {@code rateChangeDate} attribute.
     */
    @NotNull
    private LocalDateTime rateChangeDate;
}

