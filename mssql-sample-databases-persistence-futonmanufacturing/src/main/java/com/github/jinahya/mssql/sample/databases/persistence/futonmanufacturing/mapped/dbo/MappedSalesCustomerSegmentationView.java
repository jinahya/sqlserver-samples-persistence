package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_CustomerSegmentation} view.
 * <p>
 * Identifier: the view declares no key; CustomerID is unique, and not null, in every row it returns.
 */
@MappedSuperclass
public abstract class MappedSalesCustomerSegmentationView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_CustomerSegmentation";

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ---------------------------------------------------------------------------------------------------- CustomerCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_CODE = "CustomerCode";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CODE = "customerCode";

    // ---------------------------------------------------------------------------------------------------- CustomerName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_NAME = "CustomerName";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_NAME = "customerName";

    // ---------------------------------------------------------------------------------------------------- CustomerType

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_TYPE = "CustomerType";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_TYPE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_TYPE = "customerType";

    // --------------------------------------------------------------------------------------------------------- Recency

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RECENCY = "Recency";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RECENCY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RECENCY = "recency";

    // ------------------------------------------------------------------------------------------------------- Frequency

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FREQUENCY = "Frequency";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FREQUENCY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FREQUENCY = "frequency";

    // -------------------------------------------------------------------------------------------------------- Monetary

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MONETARY = "Monetary";

    /**
     * The precision of the {@value #COLUMN_NAME_MONETARY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MONETARY = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_MONETARY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MONETARY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MONETARY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MONETARY = "monetary";

    // --------------------------------------------------------------------------------------------------------- R_Score

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_R_SCORE = "R_Score";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_R_SCORE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_R_SCORE = "rScore";

    // --------------------------------------------------------------------------------------------------------- F_Score

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_F_SCORE = "F_Score";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_F_SCORE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_F_SCORE = "fScore";

    // --------------------------------------------------------------------------------------------------------- M_Score

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_M_SCORE = "M_Score";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_M_SCORE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_M_SCORE = "mScore";

    // ------------------------------------------------------------------------------------------------------- RFM_Score

    /**
     * The name of the {@code numeric(26, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RFM_SCORE = "RFM_Score";

    /**
     * The precision of the {@value #COLUMN_NAME_RFM_SCORE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_RFM_SCORE = 26;

    /**
     * The scale of the {@value #COLUMN_NAME_RFM_SCORE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_RFM_SCORE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RFM_SCORE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RFM_SCORE = "rfmScore";

    // ------------------------------------------------------------------------------------------------- CustomerSegment

    /**
     * The name of the {@code varchar(15), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_SEGMENT = "CustomerSegment";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_SEGMENT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_SEGMENT = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_SEGMENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_SEGMENT = "customerSegment";

    // ----------------------------------------------------------------------------------------------- RecommendedAction

    /**
     * The name of the {@code varchar(44), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RECOMMENDED_ACTION = "RecommendedAction";

    /**
     * The length of the {@value #COLUMN_NAME_RECOMMENDED_ACTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_RECOMMENDED_ACTION = 44;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RECOMMENDED_ACTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RECOMMENDED_ACTION = "recommendedAction";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesCustomerSegmentationView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "customerId=" + customerId
               + ",customerCode=" + customerCode
               + ",customerName=" + customerName
               + ",customerType=" + customerType
               + ",recency=" + recency
               + ",frequency=" + frequency
               + ",monetary=" + monetary
               + ",rScore=" + rScore
               + ",fScore=" + fScore
               + ",mScore=" + mScore
               + ",rfmScore=" + rfmScore
               + ",customerSegment=" + customerSegment
               + ",recommendedAction=" + recommendedAction
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
        if (!(obj instanceof MappedSalesCustomerSegmentationView that)) {
            return false;
        }
        return Objects.equals(customerId, that.customerId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(customerId);
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    // ---------------------------------------------------------------------------------------------------- customerCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute with the specified value.
     *
     * @param customerCode new value for {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     */
    protected void setCustomerCode(final String customerCode) {
        this.customerCode = customerCode;
    }

    // ---------------------------------------------------------------------------------------------------- customerName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute with the specified value.
     *
     * @param customerName new value for {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    protected void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    // ---------------------------------------------------------------------------------------------------- customerType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute with the specified value.
     *
     * @param customerType new value for {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     */
    protected void setCustomerType(final String customerType) {
        this.customerType = customerType;
    }

    // --------------------------------------------------------------------------------------------------------- recency

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RECENCY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RECENCY} attribute.
     */
    public Integer getRecency() {
        return recency;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RECENCY} attribute with the specified value.
     *
     * @param recency new value for {@value #ATTRIBUTE_NAME_RECENCY} attribute.
     */
    protected void setRecency(final Integer recency) {
        this.recency = recency;
    }

    // ------------------------------------------------------------------------------------------------------- frequency

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FREQUENCY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FREQUENCY} attribute.
     */
    public Integer getFrequency() {
        return frequency;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FREQUENCY} attribute with the specified value.
     *
     * @param frequency new value for {@value #ATTRIBUTE_NAME_FREQUENCY} attribute.
     */
    protected void setFrequency(final Integer frequency) {
        this.frequency = frequency;
    }

    // -------------------------------------------------------------------------------------------------------- monetary

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MONETARY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MONETARY} attribute.
     */
    public BigDecimal getMonetary() {
        return monetary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MONETARY} attribute with the specified value.
     *
     * @param monetary new value for {@value #ATTRIBUTE_NAME_MONETARY} attribute.
     */
    protected void setMonetary(final BigDecimal monetary) {
        this.monetary = monetary;
    }

    // ---------------------------------------------------------------------------------------------------------- rScore

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_R_SCORE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_R_SCORE} attribute.
     */
    public Long getRScore() {
        return rScore;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_R_SCORE} attribute with the specified value.
     *
     * @param rScore new value for {@value #ATTRIBUTE_NAME_R_SCORE} attribute.
     */
    protected void setRScore(final Long rScore) {
        this.rScore = rScore;
    }

    // ---------------------------------------------------------------------------------------------------------- fScore

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_F_SCORE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_F_SCORE} attribute.
     */
    public Long getFScore() {
        return fScore;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_F_SCORE} attribute with the specified value.
     *
     * @param fScore new value for {@value #ATTRIBUTE_NAME_F_SCORE} attribute.
     */
    protected void setFScore(final Long fScore) {
        this.fScore = fScore;
    }

    // ---------------------------------------------------------------------------------------------------------- mScore

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_M_SCORE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_M_SCORE} attribute.
     */
    public Long getMScore() {
        return mScore;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_M_SCORE} attribute with the specified value.
     *
     * @param mScore new value for {@value #ATTRIBUTE_NAME_M_SCORE} attribute.
     */
    protected void setMScore(final Long mScore) {
        this.mScore = mScore;
    }

    // -------------------------------------------------------------------------------------------------------- rfmScore

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RFM_SCORE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RFM_SCORE} attribute.
     */
    public BigDecimal getRfmScore() {
        return rfmScore;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RFM_SCORE} attribute with the specified value.
     *
     * @param rfmScore new value for {@value #ATTRIBUTE_NAME_RFM_SCORE} attribute.
     */
    protected void setRfmScore(final BigDecimal rfmScore) {
        this.rfmScore = rfmScore;
    }

    // ------------------------------------------------------------------------------------------------- customerSegment

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_SEGMENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_SEGMENT} attribute.
     */
    public String getCustomerSegment() {
        return customerSegment;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_SEGMENT} attribute with the specified value.
     *
     * @param customerSegment new value for {@value #ATTRIBUTE_NAME_CUSTOMER_SEGMENT} attribute.
     */
    protected void setCustomerSegment(final String customerSegment) {
        this.customerSegment = customerSegment;
    }

    // ----------------------------------------------------------------------------------------------- recommendedAction

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RECOMMENDED_ACTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RECOMMENDED_ACTION} attribute.
     */
    public String getRecommendedAction() {
        return recommendedAction;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RECOMMENDED_ACTION} attribute with the specified value.
     *
     * @param recommendedAction new value for {@value #ATTRIBUTE_NAME_RECOMMENDED_ACTION} attribute.
     */
    protected void setRecommendedAction(final String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer customerId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUSTOMER_CODE
    )
    private String customerCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUSTOMER_NAME
    )
    private String customerName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUSTOMER_TYPE
    )
    private String customerType;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RECENCY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer recency;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_FREQUENCY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer frequency;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_MONETARY - COLUMN_SCALE_MONETARY, fraction = COLUMN_SCALE_MONETARY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MONETARY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_MONETARY,
            scale = COLUMN_SCALE_MONETARY
    )
    private BigDecimal monetary;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_R_SCORE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long rScore;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_F_SCORE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long fScore;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_M_SCORE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long mScore;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_RFM_SCORE - COLUMN_SCALE_RFM_SCORE, fraction = COLUMN_SCALE_RFM_SCORE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RFM_SCORE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_RFM_SCORE,
            scale = COLUMN_SCALE_RFM_SCORE
    )
    private BigDecimal rfmScore;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_SEGMENT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_SEGMENT,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUSTOMER_SEGMENT
    )
    private String customerSegment;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_RECOMMENDED_ACTION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RECOMMENDED_ACTION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_RECOMMENDED_ACTION
    )
    private String recommendedAction;
}

