package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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
import com.github.jinahya.sqlserver.sample.persistence.shared.__Random_TestUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link Invoice}; the identifier, which a sequence generates, what it references, which the persister
 * sets, and what SQL Server fills are left out.
 */
class Invoice_Randomizer
        extends PodamObjectRandomizer<Invoice> {

    Invoice_Randomizer() {
        super(Invoice.class, List.of(
                Invoice.ATTRIBUTE_NAME_INVOICE_ID,
                Invoice.ATTRIBUTE_NAME_CUSTOMER_ID,
                Invoice.ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID,
                Invoice.ATTRIBUTE_NAME_ORDER_ID,
                Invoice.ATTRIBUTE_NAME_DELIVERY_METHOD_ID,
                Invoice.ATTRIBUTE_NAME_CONTACT_PERSON_ID,
                Invoice.ATTRIBUTE_NAME_ACCOUNTS_PERSON_ID,
                Invoice.ATTRIBUTE_NAME_SALESPERSON_PERSON_ID,
                Invoice.ATTRIBUTE_NAME_PACKED_BY_PERSON_ID,
                Invoice.ATTRIBUTE_NAME_CONFIRMED_DELIVERY_TIME,
                Invoice.ATTRIBUTE_NAME_CONFIRMED_RECEIVED_BY,
                Invoice.ATTRIBUTE_NAME_LAST_EDITED_BY,
                Invoice.ATTRIBUTE_NAME_SALESPERSON_PERSON,
                Invoice.ATTRIBUTE_NAME_PACKED_BY_PERSON,
                Invoice.ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON,
                Invoice.ATTRIBUTE_NAME_CUSTOMER,
                Invoice.ATTRIBUTE_NAME_BILL_TO_CUSTOMER,
                Invoice.ATTRIBUTE_NAME_ORDER,
                Invoice.ATTRIBUTE_NAME_DELIVERY_METHOD,
                Invoice.ATTRIBUTE_NAME_CONTACT_PERSON,
                Invoice.ATTRIBUTE_NAME_ACCOUNTS_PERSON
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has, as its {@code ReturnedDeliveryData}, a JSON document of the shape the
     *         installed invoices have, which {@code CK_Sales_Invoices_ReturnedDeliveryData_Must_Be_Valid_JSON}
     *         requires.
     */
    @Override
    public Invoice get() {
        final var instance = super.get();
        instance.setReturnedDeliveryData(
                "{\"Events\": [{\"Event\": \"Ready for collection\", \"EventTime\": \"" + __Random_TestUtils.dateTime()
                + "\", \"ConNote\": \"EAN-" + ThreadLocalRandom.current().nextInt(1000) + "\"}]}");
        return instance;
    }
}

