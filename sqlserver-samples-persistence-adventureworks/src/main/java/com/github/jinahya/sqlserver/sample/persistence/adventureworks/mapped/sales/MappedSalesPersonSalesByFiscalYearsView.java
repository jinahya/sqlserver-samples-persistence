package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;

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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.vSalesPersonSalesByFiscalYears}
 * view.
 * <p>
 * Identifier: the view declares no key; SalesPersonID is unique, and not null, in every row it
 * returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of
 * it is updatable, nor insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error
 * 4403); some of its columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesPersonSalesByFiscalYearsView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vSalesPersonSalesByFiscalYears";

    // ------------------------------------------------------------------------------- SalesPersonID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_PERSON_ID = "SalesPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_PERSON_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_PERSON_ID = "salesPersonId";

    // ------------------------------------------------------------------------------------ FullName

    /**
     * The name of the {@code nvarchar(152)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FULL_NAME = "FullName";

    /**
     * The length of the {@value #COLUMN_NAME_FULL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FULL_NAME = 152;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FULL_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FULL_NAME = "fullName";

    // ------------------------------------------------------------------------------------ JobTitle

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_TITLE = "JobTitle";

    /**
     * The length of the {@value #COLUMN_NAME_JOB_TITLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_TITLE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_TITLE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_TITLE = "jobTitle";

    // ------------------------------------------------------------------------------ SalesTerritory

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_TERRITORY = "SalesTerritory";

    /**
     * The length of the {@value #COLUMN_NAME_SALES_TERRITORY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SALES_TERRITORY = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_TERRITORY} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_TERRITORY = "salesTerritory";

    // ---------------------------------------------------------------------------------------- 2002

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YEAR_2002 = "\"2002\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YEAR_2002} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_YEAR_2002 = "year2002";

    // ---------------------------------------------------------------------------------------- 2003

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YEAR_2003 = "\"2003\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YEAR_2003} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_YEAR_2003 = "year2003";

    // ---------------------------------------------------------------------------------------- 2004

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YEAR_2004 = "\"2004\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YEAR_2004} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_YEAR_2004 = "year2004";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesPersonSalesByFiscalYearsView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "salesPersonId=" + salesPersonId
               + ",fullName=" + fullName
               + ",jobTitle=" + jobTitle
               + ",salesTerritory=" + salesTerritory
               + ",year2002=" + year2002
               + ",year2003=" + year2003
               + ",year2004=" + year2004
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
        if (!(obj instanceof MappedSalesPersonSalesByFiscalYearsView that)) {
            return false;
        }
        return Objects.equals(getSalesPersonId(), that.getSalesPersonId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getSalesPersonId());
    }

    // ------------------------------------------------------------------------------- salesPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_PERSON_ID} attribute.
     */
    public Integer getSalesPersonId() {
        return salesPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_PERSON_ID} attribute with the
     * specified value.
     *
     * @param salesPersonId new value for {@value #ATTRIBUTE_NAME_SALES_PERSON_ID} attribute.
     */
    protected void setSalesPersonId(final Integer salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    // ------------------------------------------------------------------------------------ fullName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute with the specified
     * value.
     *
     * @param fullName new value for {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    protected void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    // ------------------------------------------------------------------------------------ jobTitle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute with the specified
     * value.
     *
     * @param jobTitle new value for {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     */
    protected void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // ------------------------------------------------------------------------------ salesTerritory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_TERRITORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_TERRITORY} attribute.
     */
    public String getSalesTerritory() {
        return salesTerritory;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_TERRITORY} attribute with the
     * specified value.
     *
     * @param salesTerritory new value for {@value #ATTRIBUTE_NAME_SALES_TERRITORY} attribute.
     */
    protected void setSalesTerritory(final String salesTerritory) {
        this.salesTerritory = salesTerritory;
    }

    // ------------------------------------------------------------------------------------ year2002

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YEAR_2002} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YEAR_2002} attribute.
     */
    public BigDecimal getYear2002() {
        return year2002;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YEAR_2002} attribute with the specified
     * value.
     *
     * @param year2002 new value for {@value #ATTRIBUTE_NAME_YEAR_2002} attribute.
     */
    protected void setYear2002(final BigDecimal year2002) {
        this.year2002 = year2002;
    }

    // ------------------------------------------------------------------------------------ year2003

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YEAR_2003} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YEAR_2003} attribute.
     */
    public BigDecimal getYear2003() {
        return year2003;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YEAR_2003} attribute with the specified
     * value.
     *
     * @param year2003 new value for {@value #ATTRIBUTE_NAME_YEAR_2003} attribute.
     */
    protected void setYear2003(final BigDecimal year2003) {
        this.year2003 = year2003;
    }

    // ------------------------------------------------------------------------------------ year2004

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YEAR_2004} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YEAR_2004} attribute.
     */
    public BigDecimal getYear2004() {
        return year2004;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YEAR_2004} attribute with the specified
     * value.
     *
     * @param year2004 new value for {@value #ATTRIBUTE_NAME_YEAR_2004} attribute.
     */
    protected void setYear2004(final BigDecimal year2004) {
        this.year2004 = year2004;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Id
    @Column(name = COLUMN_NAME_SALES_PERSON_ID, nullable = true, insertable = false,
            updatable = false)
    private Integer salesPersonId;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_FULL_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_FULL_NAME, nullable = true, insertable = false, updatable = false,
            length = COLUMN_LENGTH_FULL_NAME)
    private String fullName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_JOB_TITLE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_TITLE, nullable = false, insertable = false, updatable = false,
            length = COLUMN_LENGTH_JOB_TITLE)
    private String jobTitle;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SALES_TERRITORY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_TERRITORY, nullable = false, insertable = false,
            updatable = false, length = COLUMN_LENGTH_SALES_TERRITORY)
    private String salesTerritory;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(SqlServerConstants.MONEY_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEAR_2002, nullable = true, insertable = false, updatable = false, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE)
    private BigDecimal year2002;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(SqlServerConstants.MONEY_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEAR_2003, nullable = true, insertable = false, updatable = false, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE)
    private BigDecimal year2003;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(SqlServerConstants.MONEY_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEAR_2004, nullable = true, insertable = false, updatable = false, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE)
    private BigDecimal year2004;
}

