package com.github.jinahya.sqlserver.sample.persistence.adventureworks.person;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.BusinessEntityAddressId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedAddress;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedAddressType;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedBusinessEntity;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedBusinessEntityAddress;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * An entity which maps the {@code Person.BusinessEntityAddress} table.
 */
@Entity(name = "PersonBusinessEntityAddress")
@Table(schema = MappedBusinessEntityAddress.TABLE_SCHEMA,
       name = MappedBusinessEntityAddress.TABLE_NAME,
       comment = "Cross-reference table mapping customers, vendors, and employees to their "
                 + "addresses.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_BusinessEntityAddress_rowguid",
                       columnNames = {
                               BusinessEntityAddress.COLUMN_NAME_ROWGUID
                       }
               )
       }
)
@IdClass(BusinessEntityAddressId.class)
public class BusinessEntityAddress
        extends MappedBusinessEntityAddress {

    /**
     * The name of the attribute of the {@code FK_BusinessEntityAddress_Address_AddressID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS = "address";

    /**
     * The name of the attribute of the {@code FK_BusinessEntityAddress_AddressType_AddressTypeID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS_TYPE = "addressType";

    /**
     * The name of the attribute of the
     * {@code FK_BusinessEntityAddress_BusinessEntity_BusinessEntityID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references the specified business entity, address and address
     * type.
     *
     * @param businessEntity the {@code BusinessEntity} to reference.
     * @param address        the {@code Address} to reference.
     * @param addressType    the {@code AddressType} to reference.
     * @return a new instance which references {@code businessEntity}, {@code address} and
     *         {@code addressType}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static BusinessEntityAddress of(final BusinessEntity businessEntity,
                                           final Address address,
                                           final AddressType addressType) {
        Objects.requireNonNull(businessEntity, "businessEntity is null");
        Objects.requireNonNull(address, "address is null");
        Objects.requireNonNull(addressType, "addressType is null");
        final var instance = new BusinessEntityAddress();
        instance.setBusinessEntity(businessEntity);
        instance.setAddress(address);
        instance.setAddressType(addressType);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected BusinessEntityAddress() {
        super();
    }

    // ------------------------------------------------------------------------------------- address

    /**
     * Returns the {@code Address} which the {@code FK_BusinessEntityAddress_Address_AddressID}
     * foreign key references.
     *
     * @return the {@code Address} referenced; {@code null} when none is.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Replaces the {@code Address} which the {@code FK_BusinessEntityAddress_Address_AddressID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param address the {@code Address} to reference; {@code null} for none.
     */
    private void setAddress(final Address address) {
        this.address = address;
        setAddressId(Optional.ofNullable(address).map(MappedAddress::getAddressId).orElse(null));
    }

    // --------------------------------------------------------------------------------- addressType

    /**
     * Returns the {@code AddressType} which the
     * {@code FK_BusinessEntityAddress_AddressType_AddressTypeID} foreign key references.
     *
     * @return the {@code AddressType} referenced; {@code null} when none is.
     */
    public AddressType getAddressType() {
        return addressType;
    }

    /**
     * Replaces the {@code AddressType} which the
     * {@code FK_BusinessEntityAddress_AddressType_AddressTypeID} foreign key references, and the
     * value of the column it consists of.
     *
     * @param addressType the {@code AddressType} to reference; {@code null} for none.
     */
    private void setAddressType(final AddressType addressType) {
        this.addressType = addressType;
        setAddressTypeId(
                Optional.ofNullable(addressType)
                        .map(MappedAddressType::getAddressTypeId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code BusinessEntity} which the
     * {@code FK_BusinessEntityAddress_BusinessEntity_BusinessEntityID} foreign key references.
     *
     * @return the {@code BusinessEntity} referenced; {@code null} when none is.
     */
    public BusinessEntity getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code BusinessEntity} which the
     * {@code FK_BusinessEntityAddress_BusinessEntity_BusinessEntityID} foreign key references, and
     * the value of the column it consists of.
     *
     * @param businessEntity the {@code BusinessEntity} to reference; {@code null} for none.
     */
    private void setBusinessEntity(final BusinessEntity businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(
                Optional.ofNullable(businessEntity)
                        .map(MappedBusinessEntity::getBusinessEntityId)
                        .orElse(null)
        );
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ADDRESS_ID,
                referencedColumnName = MappedAddress.COLUMN_NAME_ADDRESS_ID, nullable = false,
                insertable = false, updatable = false
    )
    private Address address;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ADDRESS_TYPE_ID,
                referencedColumnName = MappedAddressType.COLUMN_NAME_ADDRESS_TYPE_ID,
                nullable = false, insertable = false, updatable = false
    )
    private AddressType addressType;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedBusinessEntity.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false, insertable = false, updatable = false
    )
    private BusinessEntity businessEntity;
}

