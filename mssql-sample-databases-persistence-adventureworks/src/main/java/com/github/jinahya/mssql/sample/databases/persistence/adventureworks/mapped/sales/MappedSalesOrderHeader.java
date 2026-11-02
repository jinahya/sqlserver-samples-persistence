package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.sales;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SalesOrderHeader} table.
 * <p>
 * The {@value #COLUMN_NAME_CUSTOMER_ID}, {@value #COLUMN_NAME_SALES_PERSON_ID}, {@value #COLUMN_NAME_TERRITORY_ID},
 * {@value #COLUMN_NAME_BILL_TO_ADDRESS_ID}, {@value #COLUMN_NAME_SHIP_TO_ADDRESS_ID},
 * {@value #COLUMN_NAME_SHIP_METHOD_ID}, {@value #COLUMN_NAME_CREDIT_CARD_ID}, {@value #COLUMN_NAME_CURRENCY_RATE_ID}
 * columns are mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSalesOrderHeader {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesOrderHeader";

    // ---------------------------------------------------------------------------------------------------- SalesOrderID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_ORDER_ID = "SalesOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_ID = "salesOrderId";

    // -------------------------------------------------------------------------------------------------- RevisionNumber

    /**
     * The name of the {@code tinyint, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVISION_NUMBER = "RevisionNumber";

    /**
     * The minimum value of the {@value #COLUMN_NAME_REVISION_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_REVISION_NUMBER = 0;

    /**
     * The maximum value of the {@value #COLUMN_NAME_REVISION_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_REVISION_NUMBER = +255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVISION_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVISION_NUMBER = "revisionNumber";

    // ------------------------------------------------------------------------------------------------------- OrderDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_DATE = "OrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_DATE = "orderDate";

    // --------------------------------------------------------------------------------------------------------- DueDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DUE_DATE = "DueDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DUE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DUE_DATE = "dueDate";

    // -------------------------------------------------------------------------------------------------------- ShipDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_DATE = "ShipDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_DATE = "shipDate";

    // ---------------------------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code tinyint, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATUS = "Status";

    /**
     * The minimum value of the {@value #COLUMN_NAME_STATUS} column, as the {@code CK_SalesOrderHeader_Status} check
     * constraint ({@code [Status]>=(0)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MIN_STATUS = 0;

    /**
     * The maximum value of the {@value #COLUMN_NAME_STATUS} column, as the {@code CK_SalesOrderHeader_Status} check
     * constraint ({@code [Status]<=(8)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MAX_STATUS = +8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATUS = "status";

    // ------------------------------------------------------------------------------------------------- OnlineOrderFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ONLINE_ORDER_FLAG = "OnlineOrderFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ONLINE_ORDER_FLAG} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ONLINE_ORDER_FLAG = "onlineOrderFlag";

    // ------------------------------------------------------------------------------------------------ SalesOrderNumber

    /**
     * The name of the
     * {@code nvarchar(25), computed as (isnull(N'SO'+CONVERT([nvarchar](23),[SalesOrderID]),N'*** ERROR ***')), NOT
     * NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_ORDER_NUMBER = "SalesOrderNumber";

    /**
     * The length of the {@value #COLUMN_NAME_SALES_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SALES_ORDER_NUMBER = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_NUMBER = "salesOrderNumber";

    // --------------------------------------------------------------------------------------------- PurchaseOrderNumber

    /**
     * The name of the {@code OrderNumber (nvarchar)(25)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_NUMBER = "PurchaseOrderNumber";

    /**
     * The length of the {@value #COLUMN_NAME_PURCHASE_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PURCHASE_ORDER_NUMBER = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_NUMBER = "purchaseOrderNumber";

    // --------------------------------------------------------------------------------------------------- AccountNumber

    /**
     * The name of the {@code AccountNumber (nvarchar)(15)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACCOUNT_NUMBER = "AccountNumber";

    /**
     * The length of the {@value #COLUMN_NAME_ACCOUNT_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ACCOUNT_NUMBER = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACCOUNT_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACCOUNT_NUMBER = "accountNumber";

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // --------------------------------------------------------------------------------------------------- SalesPersonID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_PERSON_ID = "SalesPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_PERSON_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_PERSON_ID = "salesPersonId";

    // ----------------------------------------------------------------------------------------------------- TerritoryID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_ID = "TerritoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

    // ------------------------------------------------------------------------------------------------- BillToAddressID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BILL_TO_ADDRESS_ID = "BillToAddressID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BILL_TO_ADDRESS_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BILL_TO_ADDRESS_ID = "billToAddressId";

    // ------------------------------------------------------------------------------------------------- ShipToAddressID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_TO_ADDRESS_ID = "ShipToAddressID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_TO_ADDRESS_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_TO_ADDRESS_ID = "shipToAddressId";

    // ---------------------------------------------------------------------------------------------------- ShipMethodID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_METHOD_ID = "ShipMethodID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_METHOD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_METHOD_ID = "shipMethodId";

    // ---------------------------------------------------------------------------------------------------- CreditCardID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREDIT_CARD_ID = "CreditCardID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREDIT_CARD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_CARD_ID = "creditCardId";

    // ------------------------------------------------------------------------------------------ CreditCardApprovalCode

    /**
     * The name of the {@code varchar(15)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREDIT_CARD_APPROVAL_CODE = "CreditCardApprovalCode";

    /**
     * The length of the {@value #COLUMN_NAME_CREDIT_CARD_APPROVAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CREDIT_CARD_APPROVAL_CODE = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREDIT_CARD_APPROVAL_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_CARD_APPROVAL_CODE = "creditCardApprovalCode";

    // -------------------------------------------------------------------------------------------------- CurrencyRateID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CURRENCY_RATE_ID = "CurrencyRateID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CURRENCY_RATE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENCY_RATE_ID = "currencyRateId";

    // -------------------------------------------------------------------------------------------------------- SubTotal

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUB_TOTAL = "SubTotal";

    /**
     * The minimum value of the {@value #COLUMN_NAME_SUB_TOTAL} column, as the {@code CK_SalesOrderHeader_SubTotal}
     * check constraint ({@code [SubTotal]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_SUB_TOTAL = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUB_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUB_TOTAL = "subTotal";

    // ---------------------------------------------------------------------------------------------------------- TaxAmt

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAX_AMT = "TaxAmt";

    /**
     * The minimum value of the {@value #COLUMN_NAME_TAX_AMT} column, as the {@code CK_SalesOrderHeader_TaxAmt} check
     * constraint ({@code [TaxAmt]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_TAX_AMT = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_AMT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_AMT = "taxAmt";

    // --------------------------------------------------------------------------------------------------------- Freight

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FREIGHT = "Freight";

    /**
     * The minimum value of the {@value #COLUMN_NAME_FREIGHT} column, as the {@code CK_SalesOrderHeader_Freight} check
     * constraint ({@code [Freight]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_FREIGHT = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FREIGHT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FREIGHT = "freight";

    // -------------------------------------------------------------------------------------------------------- TotalDue

    /**
     * The name of the {@code money, computed as (isnull(([SubTotal]+[TaxAmt])+[Freight],(0))), NOT NULL} column. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_DUE = "TotalDue";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_DUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_DUE = "totalDue";

    // --------------------------------------------------------------------------------------------------------- Comment

    /**
     * The name of the {@code nvarchar(128)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMMENT = "Comment";

    /**
     * The length of the {@value #COLUMN_NAME_COMMENT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COMMENT = 128;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMENT = "comment";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesOrderHeader() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "salesOrderId=" + salesOrderId
               + ",revisionNumber=" + revisionNumber
               + ",orderDate=" + orderDate
               + ",dueDate=" + dueDate
               + ",shipDate=" + shipDate
               + ",status=" + status
               + ",onlineOrderFlag=" + onlineOrderFlag
               + ",salesOrderNumber=" + salesOrderNumber
               + ",purchaseOrderNumber=" + purchaseOrderNumber
               + ",accountNumber=" + accountNumber
               + ",customerId=" + customerId
               + ",salesPersonId=" + salesPersonId
               + ",territoryId=" + territoryId
               + ",billToAddressId=" + billToAddressId
               + ",shipToAddressId=" + shipToAddressId
               + ",shipMethodId=" + shipMethodId
               + ",creditCardId=" + creditCardId
               + ",creditCardApprovalCode=" + creditCardApprovalCode
               + ",currencyRateId=" + currencyRateId
               + ",subTotal=" + subTotal
               + ",taxAmt=" + taxAmt
               + ",freight=" + freight
               + ",totalDue=" + totalDue
               + ",comment=" + comment
               + ",rowguid=" + rowguid
               + ",modifiedDate=" + modifiedDate
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedSalesOrderHeader that)) {
            return false;
        }
        return Objects.equals(salesOrderId, that.salesOrderId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(salesOrderId);
    }

    // ------------------------------------------------------------------------------------------------------ VALIDATION

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_DUE_DATE} attribute is not before the
     * {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute, as the table's {@code CK_SalesOrderHeader_DueDate} constraint
     * requires.
     * {@snippet lang = "sql":
     * ([DueDate]>=[OrderDate])
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_DUE_DATE} attribute is not before the
     *         {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute, or if either attribute is {@code null}; {@code false}
     *         otherwise.
     */
    @AssertTrue(message = "dueDate must not be before orderDate")
    protected boolean isDueDateValid() {
        if (dueDate == null || orderDate == null) {
            return true;
        }
        return !dueDate.isBefore(orderDate);
    }

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute is {@code null} or not before the
     * {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute, as the table's {@code CK_SalesOrderHeader_ShipDate} constraint
     * requires.
     * {@snippet lang = "sql":
     * ([ShipDate]>=[OrderDate] OR [ShipDate] IS NULL)
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute is {@code null} or not before the
     *         {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute, or if the {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute is
     *         {@code null}; {@code false} otherwise.
     */
    @AssertTrue(message = "shipDate must be null or not before orderDate")
    protected boolean isShipDateValid() {
        if (shipDate == null || orderDate == null) {
            return true;
        }
        return !shipDate.isBefore(orderDate);
    }

    // ---------------------------------------------------------------------------------------------------- salesOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute with the specified value.
     *
     * @param salesOrderId new value for {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    protected void setSalesOrderId(final Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    // -------------------------------------------------------------------------------------------------- revisionNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute.
     */
    public Integer getRevisionNumber() {
        return revisionNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute with the specified value.
     *
     * @param revisionNumber new value for {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute.
     */
    public void setRevisionNumber(final Integer revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    // ------------------------------------------------------------------------------------------------------- orderDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute with the specified value.
     *
     * @param orderDate new value for {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    public void setOrderDate(final LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    // --------------------------------------------------------------------------------------------------------- dueDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute with the specified value.
     *
     * @param dueDate new value for {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     */
    public void setDueDate(final LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    // -------------------------------------------------------------------------------------------------------- shipDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     */
    public LocalDateTime getShipDate() {
        return shipDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute with the specified value.
     *
     * @param shipDate new value for {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     */
    public void setShipDate(final LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    // ---------------------------------------------------------------------------------------------------------- status

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATUS} attribute with the specified value.
     *
     * @param status new value for {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    // ------------------------------------------------------------------------------------------------- onlineOrderFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ONLINE_ORDER_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ONLINE_ORDER_FLAG} attribute.
     */
    public Boolean getOnlineOrderFlag() {
        return onlineOrderFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ONLINE_ORDER_FLAG} attribute with the specified value.
     *
     * @param onlineOrderFlag new value for {@value #ATTRIBUTE_NAME_ONLINE_ORDER_FLAG} attribute.
     */
    public void setOnlineOrderFlag(final Boolean onlineOrderFlag) {
        this.onlineOrderFlag = onlineOrderFlag;
    }

    // ------------------------------------------------------------------------------------------------ salesOrderNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_NUMBER} attribute.
     */
    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_NUMBER} attribute with the specified value. The
     * database writes the column; this is for the provider and the extending class only.
     *
     * @param salesOrderNumber new value for {@value #ATTRIBUTE_NAME_SALES_ORDER_NUMBER} attribute.
     */
    protected void setSalesOrderNumber(final String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    // --------------------------------------------------------------------------------------------- purchaseOrderNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_NUMBER} attribute.
     */
    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_NUMBER} attribute with the specified value.
     *
     * @param purchaseOrderNumber new value for {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_NUMBER} attribute.
     */
    public void setPurchaseOrderNumber(final String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    // --------------------------------------------------------------------------------------------------- accountNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute with the specified value.
     *
     * @param accountNumber new value for {@value #ATTRIBUTE_NAME_ACCOUNT_NUMBER} attribute.
     */
    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    // --------------------------------------------------------------------------------------------------- salesPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_PERSON_ID} attribute.
     */
    public Integer getSalesPersonId() {
        return salesPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param salesPersonId new value for {@value #ATTRIBUTE_NAME_SALES_PERSON_ID} attribute.
     */
    protected void setSalesPersonId(final Integer salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    // ----------------------------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    public Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(final Integer territoryId) {
        this.territoryId = territoryId;
    }

    // ------------------------------------------------------------------------------------------------- billToAddressId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BILL_TO_ADDRESS_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BILL_TO_ADDRESS_ID} attribute.
     */
    public Integer getBillToAddressId() {
        return billToAddressId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BILL_TO_ADDRESS_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param billToAddressId new value for {@value #ATTRIBUTE_NAME_BILL_TO_ADDRESS_ID} attribute.
     */
    protected void setBillToAddressId(final Integer billToAddressId) {
        this.billToAddressId = billToAddressId;
    }

    // ------------------------------------------------------------------------------------------------- shipToAddressId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_TO_ADDRESS_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_TO_ADDRESS_ID} attribute.
     */
    public Integer getShipToAddressId() {
        return shipToAddressId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIP_TO_ADDRESS_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param shipToAddressId new value for {@value #ATTRIBUTE_NAME_SHIP_TO_ADDRESS_ID} attribute.
     */
    protected void setShipToAddressId(final Integer shipToAddressId) {
        this.shipToAddressId = shipToAddressId;
    }

    // ---------------------------------------------------------------------------------------------------- shipMethodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     */
    public Integer getShipMethodId() {
        return shipMethodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param shipMethodId new value for {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     */
    protected void setShipMethodId(final Integer shipMethodId) {
        this.shipMethodId = shipMethodId;
    }

    // ---------------------------------------------------------------------------------------------------- creditCardId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREDIT_CARD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREDIT_CARD_ID} attribute.
     */
    public Integer getCreditCardId() {
        return creditCardId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREDIT_CARD_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param creditCardId new value for {@value #ATTRIBUTE_NAME_CREDIT_CARD_ID} attribute.
     */
    protected void setCreditCardId(final Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

    // ------------------------------------------------------------------------------------------ creditCardApprovalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREDIT_CARD_APPROVAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREDIT_CARD_APPROVAL_CODE} attribute.
     */
    public String getCreditCardApprovalCode() {
        return creditCardApprovalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREDIT_CARD_APPROVAL_CODE} attribute with the specified value.
     *
     * @param creditCardApprovalCode new value for {@value #ATTRIBUTE_NAME_CREDIT_CARD_APPROVAL_CODE} attribute.
     */
    public void setCreditCardApprovalCode(final String creditCardApprovalCode) {
        this.creditCardApprovalCode = creditCardApprovalCode;
    }

    // -------------------------------------------------------------------------------------------------- currencyRateId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute.
     */
    public Integer getCurrencyRateId() {
        return currencyRateId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param currencyRateId new value for {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute.
     */
    protected void setCurrencyRateId(final Integer currencyRateId) {
        this.currencyRateId = currencyRateId;
    }

    // -------------------------------------------------------------------------------------------------------- subTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute.
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute with the specified value.
     *
     * @param subTotal new value for {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute.
     */
    public void setSubTotal(final BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    // ---------------------------------------------------------------------------------------------------------- taxAmt

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_AMT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_AMT} attribute.
     */
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_AMT} attribute with the specified value.
     *
     * @param taxAmt new value for {@value #ATTRIBUTE_NAME_TAX_AMT} attribute.
     */
    public void setTaxAmt(final BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    // --------------------------------------------------------------------------------------------------------- freight

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FREIGHT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FREIGHT} attribute.
     */
    public BigDecimal getFreight() {
        return freight;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FREIGHT} attribute with the specified value.
     *
     * @param freight new value for {@value #ATTRIBUTE_NAME_FREIGHT} attribute.
     */
    public void setFreight(final BigDecimal freight) {
        this.freight = freight;
    }

    // -------------------------------------------------------------------------------------------------------- totalDue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute.
     */
    public BigDecimal getTotalDue() {
        return totalDue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param totalDue new value for {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute.
     */
    protected void setTotalDue(final BigDecimal totalDue) {
        this.totalDue = totalDue;
    }

    // --------------------------------------------------------------------------------------------------------- comment

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMMENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMMENT} attribute.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMMENT} attribute with the specified value.
     *
     * @param comment new value for {@value #ATTRIBUTE_NAME_COMMENT} attribute.
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(final UUID rowguid) {
        this.rowguid = rowguid;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SALES_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer salesOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Max(COLUMN_MAX_REVISION_NUMBER)
    @Min(COLUMN_MIN_REVISION_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REVISION_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer revisionNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime orderDate;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DUE_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime dueDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SHIP_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime shipDate;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesOrderHeader_Status: ([Status]>=(0) AND [Status]<=(8))
    @Max(COLUMN_MAX_STATUS)
    @Min(COLUMN_MIN_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer status;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ONLINE_ORDER_FLAG,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean onlineOrderFlag;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Size(max = COLUMN_LENGTH_SALES_ORDER_NUMBER)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_ORDER_NUMBER,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SALES_ORDER_NUMBER
    )
    private String salesOrderNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PURCHASE_ORDER_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PURCHASE_ORDER_NUMBER
    )
    private String purchaseOrderNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ACCOUNT_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ACCOUNT_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ACCOUNT_NUMBER
    )
    private String accountNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer customerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_PERSON_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer salesPersonId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TERRITORY_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer territoryId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BILL_TO_ADDRESS_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer billToAddressId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SHIP_TO_ADDRESS_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer shipToAddressId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SHIP_METHOD_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer shipMethodId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREDIT_CARD_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer creditCardId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CREDIT_CARD_APPROVAL_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREDIT_CARD_APPROVAL_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CREDIT_CARD_APPROVAL_CODE
    )
    private String creditCardApprovalCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CURRENCY_RATE_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer currencyRateId;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesOrderHeader_SubTotal: ([SubTotal]>=(0.00))
    @DecimalMin(COLUMN_MIN_SUB_TOTAL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUB_TOTAL,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal subTotal;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesOrderHeader_TaxAmt: ([TaxAmt]>=(0.00))
    @DecimalMin(COLUMN_MIN_TAX_AMT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TAX_AMT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal taxAmt;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesOrderHeader_Freight: ([Freight]>=(0.00))
    @DecimalMin(COLUMN_MIN_FREIGHT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FREIGHT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal freight;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TOTAL_DUE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private BigDecimal totalDue;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_COMMENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMMENT,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COMMENT
    )
    private String comment;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

