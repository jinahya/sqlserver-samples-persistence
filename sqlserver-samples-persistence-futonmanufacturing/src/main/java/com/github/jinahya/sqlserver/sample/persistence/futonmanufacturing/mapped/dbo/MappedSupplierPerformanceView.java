package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_SupplierPerformance} view.
 * <p>
 * Identifier: the view declares no key; SupplierCode is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSupplierPerformanceView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_SupplierPerformance";

    // -------------------------------------------------------------------------------- SupplierCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_CODE = "SupplierCode";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_CODE = "supplierCode";

    // -------------------------------------------------------------------------------- SupplierName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_NAME = "SupplierName";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_NAME = "supplierName";

    // ------------------------------------------------------------------------------ SupplierRating

    /**
     * The name of the {@code decimal(3, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_RATING = "SupplierRating";

    /**
     * The precision of the {@value #COLUMN_NAME_SUPPLIER_RATING} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SUPPLIER_RATING = 3;

    /**
     * The scale of the {@value #COLUMN_NAME_SUPPLIER_RATING} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SUPPLIER_RATING = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_RATING} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_RATING = "supplierRating";

    // --------------------------------------------------------------------------------- TotalOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_ORDERS = "TotalOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_ORDERS = "totalOrders";

    // -------------------------------------------------------------------------- TotalPurchaseValue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_PURCHASE_VALUE = "TotalPurchaseValue";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_PURCHASE_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_PURCHASE_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_PURCHASE_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_PURCHASE_VALUE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_PURCHASE_VALUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_PURCHASE_VALUE = "totalPurchaseValue";

    // ----------------------------------------------------------------------------- AvgDeliveryDays

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_DELIVERY_DAYS = "AvgDeliveryDays";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_DELIVERY_DAYS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_DELIVERY_DAYS = "avgDeliveryDays";

    // ---------------------------------------------------------------------------- OnTimeDeliveries

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ON_TIME_DELIVERIES = "OnTimeDeliveries";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ON_TIME_DELIVERIES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ON_TIME_DELIVERIES = "onTimeDeliveries";

    // ------------------------------------------------------------------------- CompletedDeliveries

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPLETED_DELIVERIES = "CompletedDeliveries";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPLETED_DELIVERIES} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPLETED_DELIVERIES = "completedDeliveries";

    // -------------------------------------------------------------------------- OnTimeDeliveryRate

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ON_TIME_DELIVERY_RATE = "OnTimeDeliveryRate";

    /**
     * The precision of the {@value #COLUMN_NAME_ON_TIME_DELIVERY_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ON_TIME_DELIVERY_RATE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_ON_TIME_DELIVERY_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ON_TIME_DELIVERY_RATE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ON_TIME_DELIVERY_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE = "onTimeDeliveryRate";

    // ---------------------------------------------------------------------------- PerformanceGrade

    /**
     * The name of the {@code varchar(9), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PERFORMANCE_GRADE = "PerformanceGrade";

    /**
     * The length of the {@value #COLUMN_NAME_PERFORMANCE_GRADE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PERFORMANCE_GRADE = 9;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PERFORMANCE_GRADE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERFORMANCE_GRADE = "performanceGrade";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSupplierPerformanceView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "supplierCode=" + supplierCode
               + ",supplierName=" + supplierName
               + ",supplierRating=" + supplierRating
               + ",totalOrders=" + totalOrders
               + ",totalPurchaseValue=" + totalPurchaseValue
               + ",avgDeliveryDays=" + avgDeliveryDays
               + ",onTimeDeliveries=" + onTimeDeliveries
               + ",completedDeliveries=" + completedDeliveries
               + ",onTimeDeliveryRate=" + onTimeDeliveryRate
               + ",performanceGrade=" + performanceGrade
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
        if (!(obj instanceof MappedSupplierPerformanceView that)) {
            return false;
        }
        return Objects.equals(supplierCode, that.supplierCode);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(supplierCode);
    }

    // -------------------------------------------------------------------------------- supplierCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CODE} attribute.
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CODE} attribute with the specified value.
     *
     * @param supplierCode new value for {@value #ATTRIBUTE_NAME_SUPPLIER_CODE} attribute.
     */
    protected void setSupplierCode(final String supplierCode) {
        this.supplierCode = supplierCode;
    }

    // -------------------------------------------------------------------------------- supplierName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute with the specified value.
     *
     * @param supplierName new value for {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     */
    protected void setSupplierName(final String supplierName) {
        this.supplierName = supplierName;
    }

    // ------------------------------------------------------------------------------ supplierRating

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_RATING} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_RATING} attribute.
     */
    public BigDecimal getSupplierRating() {
        return supplierRating;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_RATING} attribute with the specified value.
     *
     * @param supplierRating new value for {@value #ATTRIBUTE_NAME_SUPPLIER_RATING} attribute.
     */
    protected void setSupplierRating(final BigDecimal supplierRating) {
        this.supplierRating = supplierRating;
    }

    // --------------------------------------------------------------------------------- totalOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     */
    public Integer getTotalOrders() {
        return totalOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute with the specified value.
     *
     * @param totalOrders new value for {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     */
    protected void setTotalOrders(final Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    // -------------------------------------------------------------------------- totalPurchaseValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_PURCHASE_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_PURCHASE_VALUE} attribute.
     */
    public BigDecimal getTotalPurchaseValue() {
        return totalPurchaseValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_PURCHASE_VALUE} attribute with the specified value.
     *
     * @param totalPurchaseValue new value for {@value #ATTRIBUTE_NAME_TOTAL_PURCHASE_VALUE} attribute.
     */
    protected void setTotalPurchaseValue(final BigDecimal totalPurchaseValue) {
        this.totalPurchaseValue = totalPurchaseValue;
    }

    // ----------------------------------------------------------------------------- avgDeliveryDays

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_DELIVERY_DAYS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_DELIVERY_DAYS} attribute.
     */
    public Integer getAvgDeliveryDays() {
        return avgDeliveryDays;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_DELIVERY_DAYS} attribute with the specified value.
     *
     * @param avgDeliveryDays new value for {@value #ATTRIBUTE_NAME_AVG_DELIVERY_DAYS} attribute.
     */
    protected void setAvgDeliveryDays(final Integer avgDeliveryDays) {
        this.avgDeliveryDays = avgDeliveryDays;
    }

    // ---------------------------------------------------------------------------- onTimeDeliveries

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERIES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERIES} attribute.
     */
    public Integer getOnTimeDeliveries() {
        return onTimeDeliveries;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERIES} attribute with the specified value.
     *
     * @param onTimeDeliveries new value for {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERIES} attribute.
     */
    protected void setOnTimeDeliveries(final Integer onTimeDeliveries) {
        this.onTimeDeliveries = onTimeDeliveries;
    }

    // ------------------------------------------------------------------------- completedDeliveries

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPLETED_DELIVERIES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPLETED_DELIVERIES} attribute.
     */
    public Integer getCompletedDeliveries() {
        return completedDeliveries;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPLETED_DELIVERIES} attribute with the specified value.
     *
     * @param completedDeliveries new value for {@value #ATTRIBUTE_NAME_COMPLETED_DELIVERIES} attribute.
     */
    protected void setCompletedDeliveries(final Integer completedDeliveries) {
        this.completedDeliveries = completedDeliveries;
    }

    // -------------------------------------------------------------------------- onTimeDeliveryRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE} attribute.
     */
    public BigDecimal getOnTimeDeliveryRate() {
        return onTimeDeliveryRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE} attribute with the specified value.
     *
     * @param onTimeDeliveryRate new value for {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE} attribute.
     */
    protected void setOnTimeDeliveryRate(final BigDecimal onTimeDeliveryRate) {
        this.onTimeDeliveryRate = onTimeDeliveryRate;
    }

    // ---------------------------------------------------------------------------- performanceGrade

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERFORMANCE_GRADE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERFORMANCE_GRADE} attribute.
     */
    public String getPerformanceGrade() {
        return performanceGrade;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERFORMANCE_GRADE} attribute with the specified value.
     *
     * @param performanceGrade new value for {@value #ATTRIBUTE_NAME_PERFORMANCE_GRADE} attribute.
     */
    protected void setPerformanceGrade(final String performanceGrade) {
        this.performanceGrade = performanceGrade;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SUPPLIER_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_SUPPLIER_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SUPPLIER_CODE
    )
    private String supplierCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SUPPLIER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SUPPLIER_NAME
    )
    private String supplierName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_SUPPLIER_RATING - COLUMN_SCALE_SUPPLIER_RATING,
            fraction = COLUMN_SCALE_SUPPLIER_RATING)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUPPLIER_RATING,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_SUPPLIER_RATING,
            scale = COLUMN_SCALE_SUPPLIER_RATING
    )
    private BigDecimal supplierRating;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_ORDERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer totalOrders;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_PURCHASE_VALUE - COLUMN_SCALE_TOTAL_PURCHASE_VALUE,
            fraction = COLUMN_SCALE_TOTAL_PURCHASE_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_PURCHASE_VALUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_PURCHASE_VALUE,
            scale = COLUMN_SCALE_TOTAL_PURCHASE_VALUE
    )
    private BigDecimal totalPurchaseValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_DELIVERY_DAYS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer avgDeliveryDays;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ON_TIME_DELIVERIES,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer onTimeDeliveries;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMPLETED_DELIVERIES,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer completedDeliveries;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_ON_TIME_DELIVERY_RATE - COLUMN_SCALE_ON_TIME_DELIVERY_RATE,
            fraction = COLUMN_SCALE_ON_TIME_DELIVERY_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ON_TIME_DELIVERY_RATE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_ON_TIME_DELIVERY_RATE,
            scale = COLUMN_SCALE_ON_TIME_DELIVERY_RATE
    )
    private BigDecimal onTimeDeliveryRate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PERFORMANCE_GRADE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PERFORMANCE_GRADE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PERFORMANCE_GRADE
    )
    private String performanceGrade;
}

