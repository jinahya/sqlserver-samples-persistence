package com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.MappedShipMethod;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Purchasing.ShipMethod} table.
 */
@Entity(name = "PurchasingShipMethod")
@Table(schema = MappedShipMethod.TABLE_SCHEMA,
       name = MappedShipMethod.TABLE_NAME,
       comment = "Shipping company lookup table.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_ShipMethod_Name",
                       columnNames = {
                               ShipMethod.COLUMN_NAME_NAME
                       }
               ),
               @UniqueConstraint(
                       name = "AK_ShipMethod_rowguid",
                       columnNames = {
                               ShipMethod.COLUMN_NAME_ROWGUID
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_ShipMethod_ShipBase",
                       constraint = "([ShipBase]>(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_ShipMethod_ShipRate",
                       constraint = "([ShipRate]>(0.00))"
               )
       }
)
public class ShipMethod
        extends MappedShipMethod {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ShipMethod() {
        super();
    }
}

