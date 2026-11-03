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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Random_TestUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link SalesOrderHeader}; the generated identifier, what SQL Server fills or derives, and what it
 * references, which the persister sets, are left out.
 */
class SalesOrderHeader_Randomizer
        extends PodamObjectRandomizer<SalesOrderHeader> {

    SalesOrderHeader_Randomizer() {
        super(SalesOrderHeader.class, List.of(
                SalesOrderHeader.ATTRIBUTE_NAME_SALES_ORDER_ID,
                SalesOrderHeader.ATTRIBUTE_NAME_SALES_ORDER_NUMBER,
                SalesOrderHeader.ATTRIBUTE_NAME_TOTAL_DUE,
                SalesOrderHeader.ATTRIBUTE_NAME_CUSTOMER_ID,
                SalesOrderHeader.ATTRIBUTE_NAME_SALES_PERSON_ID,
                SalesOrderHeader.ATTRIBUTE_NAME_TERRITORY_ID,
                SalesOrderHeader.ATTRIBUTE_NAME_BILL_TO_ADDRESS_ID,
                SalesOrderHeader.ATTRIBUTE_NAME_SHIP_TO_ADDRESS_ID,
                SalesOrderHeader.ATTRIBUTE_NAME_SHIP_METHOD_ID,
                SalesOrderHeader.ATTRIBUTE_NAME_CREDIT_CARD_ID,
                SalesOrderHeader.ATTRIBUTE_NAME_CURRENCY_RATE_ID,
                SalesOrderHeader.ATTRIBUTE_NAME_CUSTOMER,
                SalesOrderHeader.ATTRIBUTE_NAME_SALES_PERSON,
                SalesOrderHeader.ATTRIBUTE_NAME_TERRITORY,
                SalesOrderHeader.ATTRIBUTE_NAME_BILL_TO_ADDRESS,
                SalesOrderHeader.ATTRIBUTE_NAME_SHIP_TO_ADDRESS,
                SalesOrderHeader.ATTRIBUTE_NAME_SHIP_METHOD,
                SalesOrderHeader.ATTRIBUTE_NAME_CREDIT_CARD,
                SalesOrderHeader.ATTRIBUTE_NAME_CURRENCY_RATE,
                SalesOrderHeader.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has amounts within their columns and CHECK constraints, its dates in order, and
     *         room in its revision number for the updates its details make.
     */
    @Override
    public SalesOrderHeader get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // RevisionNumber, a tinyint, which uSalesOrderHeader increments on each update, as each detail inserted makes
        instance.setRevisionNumber(random.nextInt(100));
        // SubTotal, TaxAmt and Freight, all money, >= 0
        instance.setSubTotal(__Random_TestUtils.positive(5, 4));
        instance.setTaxAmt(__Random_TestUtils.positive(5, 4));
        instance.setFreight(__Random_TestUtils.positive(5, 4));
        // CK_SalesOrderHeader_DueDate and CK_SalesOrderHeader_ShipDate: DueDate, and ShipDate when any, >= OrderDate
        instance.setOrderDate(__Random_TestUtils.dateTime());
        instance.setDueDate(instance.getOrderDate().plusDays(random.nextLong(366L)));
        instance.setShipDate(random.nextBoolean() ? null : instance.getOrderDate().plusDays(random.nextLong(366L)));
        return instance;
    }
}
