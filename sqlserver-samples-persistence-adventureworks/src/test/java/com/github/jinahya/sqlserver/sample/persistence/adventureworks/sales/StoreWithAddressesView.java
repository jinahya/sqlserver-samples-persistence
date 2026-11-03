package com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedStoreWithAddressesView;

/**
 * A plain class which holds a row of the {@code Sales.vStoreWithAddresses} view, which no entity
 * can map: the view declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class StoreWithAddressesView
        extends MappedStoreWithAddressesView {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected StoreWithAddressesView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param businessEntityId  the value of the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column.
     * @param name              the value of the {@value #COLUMN_NAME_NAME} column.
     * @param addressType       the value of the {@value #COLUMN_NAME_ADDRESS_TYPE} column.
     * @param addressLine1      the value of the {@value #COLUMN_NAME_ADDRESS_LINE_1} column.
     * @param addressLine2      the value of the {@value #COLUMN_NAME_ADDRESS_LINE_2} column.
     * @param city              the value of the {@value #COLUMN_NAME_CITY} column.
     * @param stateProvinceName the value of the {@value #COLUMN_NAME_STATE_PROVINCE_NAME} column.
     * @param postalCode        the value of the {@value #COLUMN_NAME_POSTAL_CODE} column.
     * @param countryRegionName the value of the {@value #COLUMN_NAME_COUNTRY_REGION_NAME} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public StoreWithAddressesView(final Integer businessEntityId,
                                  final String name,
                                  final String addressType,
                                  final String addressLine1,
                                  final String addressLine2,
                                  final String city,
                                  final String stateProvinceName,
                                  final String postalCode,
                                  final String countryRegionName) {
        super();
        setBusinessEntityId(businessEntityId);
        setName(name);
        setAddressType(addressType);
        setAddressLine1(addressLine1);
        setAddressLine2(addressLine2);
        setCity(city);
        setStateProvinceName(stateProvinceName);
        setPostalCode(postalCode);
        setCountryRegionName(countryRegionName);
    }
}

