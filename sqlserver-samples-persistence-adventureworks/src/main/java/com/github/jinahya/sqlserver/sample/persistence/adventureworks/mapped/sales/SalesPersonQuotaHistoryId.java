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
 * The identifier class of the {@code Sales.SalesPersonQuotaHistory} table, whose rows are identified by
 * {@code BusinessEntityID}, {@code QuotaDate}.
 * <p>
 * An entity which extends {@link MappedSalesPersonQuotaHistory} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class SalesPersonQuotaHistoryId {

    /**
     * The name of the attribute which maps the
     * {@value MappedSalesPersonQuotaHistory#COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    /**
     * The name of the attribute which maps the
     * {@value MappedSalesPersonQuotaHistory#COLUMN_NAME_QUOTA_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUOTA_DATE = "quotaDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public SalesPersonQuotaHistoryId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",quotaDate=" + quotaDate
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesPersonQuotaHistoryId that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getQuotaDate(), that.getQuotaDate());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getQuotaDate());
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

    // ----------------------------------------------------------------------------------- quotaDate

    /**
     * Returns current value of the {@code quotaDate} attribute.
     *
     * @return current value of the {@code quotaDate} attribute.
     */
    public LocalDateTime getQuotaDate() {
        return quotaDate;
    }

    /**
     * Replaces current value of the {@code quotaDate} attribute with the specified value.
     *
     * @param quotaDate new value for the {@code quotaDate} attribute.
     */
    public void setQuotaDate(final LocalDateTime quotaDate) {
        this.quotaDate = quotaDate;
    }

    // ---------------------------------------------------------------------------------------------
    /**
     * The value of the {@code businessEntityId} attribute.
     */
    @NotNull
    private Integer businessEntityId;

    /**
     * The value of the {@code quotaDate} attribute.
     */
    @NotNull
    private LocalDateTime quotaDate;
}

