package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person;

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
import java.util.Objects;

/**
 * The identifier class of the {@code Person.BusinessEntityAddress} table, whose rows are identified by
 * {@code BusinessEntityID}, {@code AddressID}, {@code AddressTypeID}.
 * <p>
 * An entity which extends {@link MappedBusinessEntityAddress} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedBusinessEntityAddressId implements Serializable {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedBusinessEntityAddressId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",addressId=" + addressId
               + ",addressTypeId=" + addressTypeId
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedBusinessEntityAddressId that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId) &&
               Objects.equals(addressId, that.addressId) &&
               Objects.equals(addressTypeId, that.addressTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessEntityId, addressId, addressTypeId);
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

    // ----------------------------------------------------------------------------------- addressId

    /**
     * Returns current value of the {@code addressId} attribute.
     *
     * @return current value of the {@code addressId} attribute.
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * Replaces current value of the {@code addressId} attribute with the specified value.
     *
     * @param addressId new value for the {@code addressId} attribute.
     */
    public void setAddressId(final Integer addressId) {
        this.addressId = addressId;
    }

    // ------------------------------------------------------------------------------- addressTypeId

    /**
     * Returns current value of the {@code addressTypeId} attribute.
     *
     * @return current value of the {@code addressTypeId} attribute.
     */
    public Integer getAddressTypeId() {
        return addressTypeId;
    }

    /**
     * Replaces current value of the {@code addressTypeId} attribute with the specified value.
     *
     * @param addressTypeId new value for the {@code addressTypeId} attribute.
     */
    public void setAddressTypeId(final Integer addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    // ---------------------------------------------------------------------------------------------
    /**
     * The value of the {@code businessEntityId} attribute.
     */
    private Integer businessEntityId;

    /**
     * The value of the {@code addressId} attribute.
     */
    private Integer addressId;

    /**
     * The value of the {@code addressTypeId} attribute.
     */
    private Integer addressTypeId;
}

