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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedAddress;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.MappedShipMethod;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedCreditCard;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedCurrencyRate;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesPerson;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesTerritory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Address;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.ShipMethod;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Sales.SalesOrderHeader} table.
 */
@Entity(name = "SalesSalesOrderHeader")
@Table(schema = MappedSalesOrderHeader.TABLE_SCHEMA,
       name = MappedSalesOrderHeader.TABLE_NAME,
       comment = "General sales order information.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_SalesOrderHeader_rowguid",
                       columnNames = {
                               SalesOrderHeader.COLUMN_NAME_ROWGUID
                       }
               ),
               @UniqueConstraint(
                       name = "AK_SalesOrderHeader_SalesOrderNumber",
                       columnNames = {
                               SalesOrderHeader.COLUMN_NAME_SALES_ORDER_NUMBER
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_SalesOrderHeader_DueDate",
                       constraint = "([DueDate]>=[OrderDate])"
               ),
               @CheckConstraint(
                       name = "CK_SalesOrderHeader_Freight",
                       constraint = "([Freight]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_SalesOrderHeader_ShipDate",
                       constraint = "([ShipDate]>=[OrderDate] OR [ShipDate] IS NULL)"
               ),
               @CheckConstraint(
                       name = "CK_SalesOrderHeader_Status",
                       constraint = "([Status]>=(0) AND [Status]<=(8))"
               ),
               @CheckConstraint(
                       name = "CK_SalesOrderHeader_SubTotal",
                       constraint = "([SubTotal]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_SalesOrderHeader_TaxAmt",
                       constraint = "([TaxAmt]>=(0.00))"
               )
       }
)
public class SalesOrderHeader
        extends MappedSalesOrderHeader {

    /**
     * The name of the attribute of the {@code FK_SalesOrderHeader_Address_BillToAddressID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BILL_TO_ADDRESS = "billToAddress";

    /**
     * The name of the attribute of the {@code FK_SalesOrderHeader_Address_ShipToAddressID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_TO_ADDRESS = "shipToAddress";

    /**
     * The name of the attribute of the {@code FK_SalesOrderHeader_CreditCard_CreditCardID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_CARD = "creditCard";

    /**
     * The name of the attribute of the {@code FK_SalesOrderHeader_CurrencyRate_CurrencyRateID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENCY_RATE = "currencyRate";

    /**
     * The name of the attribute of the {@code FK_SalesOrderHeader_Customer_CustomerID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute of the {@code FK_SalesOrderHeader_SalesPerson_SalesPersonID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_PERSON = "salesPerson";

    /**
     * The name of the attribute of the {@code FK_SalesOrderHeader_ShipMethod_ShipMethodID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_METHOD = "shipMethod";

    /**
     * The name of the attribute of the {@code FK_SalesOrderHeader_SalesTerritory_TerritoryID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY = "territory";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesOrderHeader() {
        super();
    }

    // ------------------------------------------------------------------------------- billToAddress

    /**
     * Returns the {@code Address} which the {@code FK_SalesOrderHeader_Address_BillToAddressID}
     * foreign key references.
     *
     * @return the {@code Address} referenced; {@code null} when none is.
     */
    public Address getBillToAddress() {
        return billToAddress;
    }

    /**
     * Replaces the {@code Address} which the {@code FK_SalesOrderHeader_Address_BillToAddressID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param billToAddress the {@code Address} to reference; {@code null} for none.
     */
    public void setBillToAddress(final Address billToAddress) {
        this.billToAddress = billToAddress;
        setBillToAddressId(billToAddress == null ? null : billToAddress.getAddressId());
    }

    // ------------------------------------------------------------------------------- shipToAddress

    /**
     * Returns the {@code Address} which the {@code FK_SalesOrderHeader_Address_ShipToAddressID}
     * foreign key references.
     *
     * @return the {@code Address} referenced; {@code null} when none is.
     */
    public Address getShipToAddress() {
        return shipToAddress;
    }

    /**
     * Replaces the {@code Address} which the {@code FK_SalesOrderHeader_Address_ShipToAddressID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param shipToAddress the {@code Address} to reference; {@code null} for none.
     */
    public void setShipToAddress(final Address shipToAddress) {
        this.shipToAddress = shipToAddress;
        setShipToAddressId(shipToAddress == null ? null : shipToAddress.getAddressId());
    }

    // ---------------------------------------------------------------------------------- creditCard

    /**
     * Returns the {@code CreditCard} which the {@code FK_SalesOrderHeader_CreditCard_CreditCardID}
     * foreign key references.
     *
     * @return the {@code CreditCard} referenced; {@code null} when none is.
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Replaces the {@code CreditCard} which the {@code FK_SalesOrderHeader_CreditCard_CreditCardID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param creditCard the {@code CreditCard} to reference; {@code null} for none.
     */
    public void setCreditCard(final CreditCard creditCard) {
        this.creditCard = creditCard;
        setCreditCardId(creditCard == null ? null : creditCard.getCreditCardId());
    }

    // -------------------------------------------------------------------------------- currencyRate

    /**
     * Returns the {@code CurrencyRate} which the
     * {@code FK_SalesOrderHeader_CurrencyRate_CurrencyRateID} foreign key references.
     *
     * @return the {@code CurrencyRate} referenced; {@code null} when none is.
     */
    public CurrencyRate getCurrencyRate() {
        return currencyRate;
    }

    /**
     * Replaces the {@code CurrencyRate} which the
     * {@code FK_SalesOrderHeader_CurrencyRate_CurrencyRateID} foreign key references, and the value
     * of the column it consists of.
     *
     * @param currencyRate the {@code CurrencyRate} to reference; {@code null} for none.
     */
    public void setCurrencyRate(final CurrencyRate currencyRate) {
        this.currencyRate = currencyRate;
        setCurrencyRateId(currencyRate == null ? null : currencyRate.getCurrencyRateId());
    }

    // ------------------------------------------------------------------------------------ customer

    /**
     * Returns the {@code Customer} which the {@code FK_SalesOrderHeader_Customer_CustomerID}
     * foreign key references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_SalesOrderHeader_Customer_CustomerID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param customer the {@code Customer} to reference; {@code null} for none.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
        setCustomerId(customer == null ? null : customer.getCustomerId());
    }

    // --------------------------------------------------------------------------------- salesPerson

    /**
     * Returns the {@code SalesPerson} which the
     * {@code FK_SalesOrderHeader_SalesPerson_SalesPersonID} foreign key references.
     *
     * @return the {@code SalesPerson} referenced; {@code null} when none is.
     */
    public SalesPerson getSalesPerson() {
        return salesPerson;
    }

    /**
     * Replaces the {@code SalesPerson} which the
     * {@code FK_SalesOrderHeader_SalesPerson_SalesPersonID} foreign key references, and the value
     * of the column it consists of.
     *
     * @param salesPerson the {@code SalesPerson} to reference; {@code null} for none.
     */
    public void setSalesPerson(final SalesPerson salesPerson) {
        this.salesPerson = salesPerson;
        setSalesPersonId(salesPerson == null ? null : salesPerson.getBusinessEntityId());
    }

    // ---------------------------------------------------------------------------------- shipMethod

    /**
     * Returns the {@code ShipMethod} which the {@code FK_SalesOrderHeader_ShipMethod_ShipMethodID}
     * foreign key references.
     *
     * @return the {@code ShipMethod} referenced; {@code null} when none is.
     */
    public ShipMethod getShipMethod() {
        return shipMethod;
    }

    /**
     * Replaces the {@code ShipMethod} which the {@code FK_SalesOrderHeader_ShipMethod_ShipMethodID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param shipMethod the {@code ShipMethod} to reference; {@code null} for none.
     */
    public void setShipMethod(final ShipMethod shipMethod) {
        this.shipMethod = shipMethod;
        setShipMethodId(shipMethod == null ? null : shipMethod.getShipMethodId());
    }

    // ----------------------------------------------------------------------------------- territory

    /**
     * Returns the {@code SalesTerritory} which the
     * {@code FK_SalesOrderHeader_SalesTerritory_TerritoryID} foreign key references.
     *
     * @return the {@code SalesTerritory} referenced; {@code null} when none is.
     */
    public SalesTerritory getTerritory() {
        return territory;
    }

    /**
     * Replaces the {@code SalesTerritory} which the
     * {@code FK_SalesOrderHeader_SalesTerritory_TerritoryID} foreign key references, and the value
     * of the column it consists of.
     *
     * @param territory the {@code SalesTerritory} to reference; {@code null} for none.
     */
    public void setTerritory(final SalesTerritory territory) {
        this.territory = territory;
        setTerritoryId(territory == null ? null : territory.getTerritoryId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BILL_TO_ADDRESS_ID,
                referencedColumnName = MappedAddress.COLUMN_NAME_ADDRESS_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Address billToAddress;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIP_TO_ADDRESS_ID,
                referencedColumnName = MappedAddress.COLUMN_NAME_ADDRESS_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Address shipToAddress;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CREDIT_CARD_ID,
                referencedColumnName = MappedCreditCard.COLUMN_NAME_CREDIT_CARD_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private CreditCard creditCard;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CURRENCY_RATE_ID,
                referencedColumnName = MappedCurrencyRate.COLUMN_NAME_CURRENCY_RATE_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private CurrencyRate currencyRate;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID,
                referencedColumnName = MappedCustomer.COLUMN_NAME_CUSTOMER_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Customer customer;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_PERSON_ID,
                referencedColumnName = MappedSalesPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private SalesPerson salesPerson;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIP_METHOD_ID,
                referencedColumnName = MappedShipMethod.COLUMN_NAME_SHIP_METHOD_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private ShipMethod shipMethod;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TERRITORY_ID,
                referencedColumnName = MappedSalesTerritory.COLUMN_NAME_TERRITORY_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private SalesTerritory territory;
}

