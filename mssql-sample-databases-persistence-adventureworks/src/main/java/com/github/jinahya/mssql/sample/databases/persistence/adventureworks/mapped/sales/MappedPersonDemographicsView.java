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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.vPersonDemographics} view.
 * <p>
 * Identifier: the view declares no key; BusinessEntityID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable,
 * and none but the identifier, of which EclipseLink requires a writable mapping, is insertable; some of its columns
 * are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedPersonDemographicsView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vPersonDemographics";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ------------------------------------------------------------------------------------------------ TotalPurchaseYTD

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_PURCHASE_YTD = "TotalPurchaseYTD";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_PURCHASE_YTD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_PURCHASE_YTD = "totalPurchaseYtd";

    // ----------------------------------------------------------------------------------------------- DateFirstPurchase

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DATE_FIRST_PURCHASE = "DateFirstPurchase";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DATE_FIRST_PURCHASE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DATE_FIRST_PURCHASE = "dateFirstPurchase";

    // ------------------------------------------------------------------------------------------------------- BirthDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BIRTH_DATE = "BirthDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BIRTH_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BIRTH_DATE = "birthDate";

    // --------------------------------------------------------------------------------------------------- MaritalStatus

    /**
     * The name of the {@code nvarchar(1)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MARITAL_STATUS = "MaritalStatus";

    /**
     * The length of the {@value #COLUMN_NAME_MARITAL_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MARITAL_STATUS = 1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MARITAL_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MARITAL_STATUS = "maritalStatus";

    // ---------------------------------------------------------------------------------------------------- YearlyIncome

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YEARLY_INCOME = "YearlyIncome";

    /**
     * The length of the {@value #COLUMN_NAME_YEARLY_INCOME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_YEARLY_INCOME = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YEARLY_INCOME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_YEARLY_INCOME = "yearlyIncome";

    // ---------------------------------------------------------------------------------------------------------- Gender

    /**
     * The name of the {@code nvarchar(1)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GENDER = "Gender";

    /**
     * The length of the {@value #COLUMN_NAME_GENDER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_GENDER = 1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GENDER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GENDER = "gender";

    // --------------------------------------------------------------------------------------------------- TotalChildren

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_CHILDREN = "TotalChildren";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_CHILDREN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_CHILDREN = "totalChildren";

    // -------------------------------------------------------------------------------------------- NumberChildrenAtHome

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NUMBER_CHILDREN_AT_HOME = "NumberChildrenAtHome";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NUMBER_CHILDREN_AT_HOME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NUMBER_CHILDREN_AT_HOME = "numberChildrenAtHome";

    // ------------------------------------------------------------------------------------------------------- Education

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDUCATION = "Education";

    /**
     * The length of the {@value #COLUMN_NAME_EDUCATION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDUCATION = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDUCATION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDUCATION = "education";

    // ------------------------------------------------------------------------------------------------------ Occupation

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OCCUPATION = "Occupation";

    /**
     * The length of the {@value #COLUMN_NAME_OCCUPATION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_OCCUPATION = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OCCUPATION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_OCCUPATION = "occupation";

    // --------------------------------------------------------------------------------------------------- HomeOwnerFlag

    /**
     * The name of the {@code bit} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_HOME_OWNER_FLAG = "HomeOwnerFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_HOME_OWNER_FLAG} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_HOME_OWNER_FLAG = "homeOwnerFlag";

    // ------------------------------------------------------------------------------------------------- NumberCarsOwned

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NUMBER_CARS_OWNED = "NumberCarsOwned";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NUMBER_CARS_OWNED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NUMBER_CARS_OWNED = "numberCarsOwned";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPersonDemographicsView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",totalPurchaseYtd=" + totalPurchaseYtd
               + ",dateFirstPurchase=" + dateFirstPurchase
               + ",birthDate=" + birthDate
               + ",maritalStatus=" + maritalStatus
               + ",yearlyIncome=" + yearlyIncome
               + ",gender=" + gender
               + ",totalChildren=" + totalChildren
               + ",numberChildrenAtHome=" + numberChildrenAtHome
               + ",education=" + education
               + ",occupation=" + occupation
               + ",homeOwnerFlag=" + homeOwnerFlag
               + ",numberCarsOwned=" + numberCarsOwned
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
        if (!(obj instanceof MappedPersonDemographicsView that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(businessEntityId);
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ------------------------------------------------------------------------------------------------ totalPurchaseYtd

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_PURCHASE_YTD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_PURCHASE_YTD} attribute.
     */
    public BigDecimal getTotalPurchaseYtd() {
        return totalPurchaseYtd;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_PURCHASE_YTD} attribute with the specified value.
     *
     * @param totalPurchaseYtd new value for {@value #ATTRIBUTE_NAME_TOTAL_PURCHASE_YTD} attribute.
     */
    protected void setTotalPurchaseYtd(final BigDecimal totalPurchaseYtd) {
        this.totalPurchaseYtd = totalPurchaseYtd;
    }

    // ----------------------------------------------------------------------------------------------- dateFirstPurchase

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DATE_FIRST_PURCHASE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DATE_FIRST_PURCHASE} attribute.
     */
    public LocalDateTime getDateFirstPurchase() {
        return dateFirstPurchase;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DATE_FIRST_PURCHASE} attribute with the specified value.
     *
     * @param dateFirstPurchase new value for {@value #ATTRIBUTE_NAME_DATE_FIRST_PURCHASE} attribute.
     */
    protected void setDateFirstPurchase(final LocalDateTime dateFirstPurchase) {
        this.dateFirstPurchase = dateFirstPurchase;
    }

    // ------------------------------------------------------------------------------------------------------- birthDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute.
     */
    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute with the specified value.
     *
     * @param birthDate new value for {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute.
     */
    protected void setBirthDate(final LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    // --------------------------------------------------------------------------------------------------- maritalStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute.
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute with the specified value.
     *
     * @param maritalStatus new value for {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute.
     */
    protected void setMaritalStatus(final String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    // ---------------------------------------------------------------------------------------------------- yearlyIncome

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YEARLY_INCOME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YEARLY_INCOME} attribute.
     */
    public String getYearlyIncome() {
        return yearlyIncome;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YEARLY_INCOME} attribute with the specified value.
     *
     * @param yearlyIncome new value for {@value #ATTRIBUTE_NAME_YEARLY_INCOME} attribute.
     */
    protected void setYearlyIncome(final String yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }

    // ---------------------------------------------------------------------------------------------------------- gender

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GENDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GENDER} attribute.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GENDER} attribute with the specified value.
     *
     * @param gender new value for {@value #ATTRIBUTE_NAME_GENDER} attribute.
     */
    protected void setGender(final String gender) {
        this.gender = gender;
    }

    // --------------------------------------------------------------------------------------------------- totalChildren

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_CHILDREN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_CHILDREN} attribute.
     */
    public Integer getTotalChildren() {
        return totalChildren;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_CHILDREN} attribute with the specified value.
     *
     * @param totalChildren new value for {@value #ATTRIBUTE_NAME_TOTAL_CHILDREN} attribute.
     */
    protected void setTotalChildren(final Integer totalChildren) {
        this.totalChildren = totalChildren;
    }

    // -------------------------------------------------------------------------------------------- numberChildrenAtHome

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NUMBER_CHILDREN_AT_HOME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NUMBER_CHILDREN_AT_HOME} attribute.
     */
    public Integer getNumberChildrenAtHome() {
        return numberChildrenAtHome;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NUMBER_CHILDREN_AT_HOME} attribute with the specified value.
     *
     * @param numberChildrenAtHome new value for {@value #ATTRIBUTE_NAME_NUMBER_CHILDREN_AT_HOME} attribute.
     */
    protected void setNumberChildrenAtHome(final Integer numberChildrenAtHome) {
        this.numberChildrenAtHome = numberChildrenAtHome;
    }

    // ------------------------------------------------------------------------------------------------------- education

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDUCATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDUCATION} attribute.
     */
    public String getEducation() {
        return education;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDUCATION} attribute with the specified value.
     *
     * @param education new value for {@value #ATTRIBUTE_NAME_EDUCATION} attribute.
     */
    protected void setEducation(final String education) {
        this.education = education;
    }

    // ------------------------------------------------------------------------------------------------------ occupation

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OCCUPATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OCCUPATION} attribute.
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OCCUPATION} attribute with the specified value.
     *
     * @param occupation new value for {@value #ATTRIBUTE_NAME_OCCUPATION} attribute.
     */
    protected void setOccupation(final String occupation) {
        this.occupation = occupation;
    }

    // --------------------------------------------------------------------------------------------------- homeOwnerFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_HOME_OWNER_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_HOME_OWNER_FLAG} attribute.
     */
    public Boolean getHomeOwnerFlag() {
        return homeOwnerFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HOME_OWNER_FLAG} attribute with the specified value.
     *
     * @param homeOwnerFlag new value for {@value #ATTRIBUTE_NAME_HOME_OWNER_FLAG} attribute.
     */
    protected void setHomeOwnerFlag(final Boolean homeOwnerFlag) {
        this.homeOwnerFlag = homeOwnerFlag;
    }

    // ------------------------------------------------------------------------------------------------- numberCarsOwned

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NUMBER_CARS_OWNED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NUMBER_CARS_OWNED} attribute.
     */
    public Integer getNumberCarsOwned() {
        return numberCarsOwned;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NUMBER_CARS_OWNED} attribute with the specified value.
     *
     * @param numberCarsOwned new value for {@value #ATTRIBUTE_NAME_NUMBER_CARS_OWNED} attribute.
     */
    protected void setNumberCarsOwned(final Integer numberCarsOwned) {
        this.numberCarsOwned = numberCarsOwned;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only but for an INSERT: SQL Server does not write this view, and EclipseLink requires a writable mapping
    // of every identifier column; see the class documentation
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_PURCHASE_YTD,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private BigDecimal totalPurchaseYtd;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DATE_FIRST_PURCHASE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDateTime dateFirstPurchase;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BIRTH_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDateTime birthDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_MARITAL_STATUS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MARITAL_STATUS,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_MARITAL_STATUS
    )
    private String maritalStatus;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_YEARLY_INCOME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEARLY_INCOME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_YEARLY_INCOME
    )
    private String yearlyIncome;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_GENDER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GENDER,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_GENDER
    )
    private String gender;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_CHILDREN,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer totalChildren;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NUMBER_CHILDREN_AT_HOME,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer numberChildrenAtHome;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_EDUCATION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EDUCATION,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_EDUCATION
    )
    private String education;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_OCCUPATION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_OCCUPATION,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_OCCUPATION
    )
    private String occupation;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_HOME_OWNER_FLAG,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Boolean homeOwnerFlag;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NUMBER_CARS_OWNED,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer numberCarsOwned;
}

