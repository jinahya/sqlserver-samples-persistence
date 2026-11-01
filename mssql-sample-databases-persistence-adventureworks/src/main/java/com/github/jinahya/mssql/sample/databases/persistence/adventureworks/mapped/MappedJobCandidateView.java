package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.vJobCandidate} view.
 * <p>
 * Identifier: the view declares no key; JobCandidateID is unique, and not null, in every row it returns.
 */
@MappedSuperclass
public abstract class MappedJobCandidateView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "HumanResources";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vJobCandidate";

    // -------------------------------------------------------------------------------------------------- JobCandidateID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_CANDIDATE_ID = "JobCandidateID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_CANDIDATE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_CANDIDATE_ID = "jobCandidateId";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ----------------------------------------------------------------------------------------------------- Name.Prefix

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_PREFIX = "\"Name.Prefix\"";

    /**
     * The length of the {@value #COLUMN_NAME_NAME_PREFIX} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_PREFIX = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_PREFIX} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_PREFIX = "namePrefix";

    // ------------------------------------------------------------------------------------------------------ Name.First

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_FIRST = "\"Name.First\"";

    /**
     * The length of the {@value #COLUMN_NAME_NAME_FIRST} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_FIRST = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_FIRST} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_FIRST = "nameFirst";

    // ----------------------------------------------------------------------------------------------------- Name.Middle

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_MIDDLE = "\"Name.Middle\"";

    /**
     * The length of the {@value #COLUMN_NAME_NAME_MIDDLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_MIDDLE = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_MIDDLE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_MIDDLE = "nameMiddle";

    // ------------------------------------------------------------------------------------------------------- Name.Last

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_LAST = "\"Name.Last\"";

    /**
     * The length of the {@value #COLUMN_NAME_NAME_LAST} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_LAST = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_LAST} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_LAST = "nameLast";

    // ----------------------------------------------------------------------------------------------------- Name.Suffix

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_SUFFIX = "\"Name.Suffix\"";

    /**
     * The length of the {@value #COLUMN_NAME_NAME_SUFFIX} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_SUFFIX = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_SUFFIX} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_SUFFIX = "nameSuffix";

    // ---------------------------------------------------------------------------------------------------------- Skills

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SKILLS = "Skills";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SKILLS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SKILLS = "skills";

    // ------------------------------------------------------------------------------------------------------- Addr.Type

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDR_TYPE = "\"Addr.Type\"";

    /**
     * The length of the {@value #COLUMN_NAME_ADDR_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDR_TYPE = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDR_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDR_TYPE = "addrType";

    // ------------------------------------------------------------------------------------------ Addr.Loc.CountryRegion

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDR_LOC_COUNTRY_REGION = "\"Addr.Loc.CountryRegion\"";

    /**
     * The length of the {@value #COLUMN_NAME_ADDR_LOC_COUNTRY_REGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDR_LOC_COUNTRY_REGION = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDR_LOC_COUNTRY_REGION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDR_LOC_COUNTRY_REGION = "addrLocCountryRegion";

    // -------------------------------------------------------------------------------------------------- Addr.Loc.State

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDR_LOC_STATE = "\"Addr.Loc.State\"";

    /**
     * The length of the {@value #COLUMN_NAME_ADDR_LOC_STATE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDR_LOC_STATE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDR_LOC_STATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDR_LOC_STATE = "addrLocState";

    // --------------------------------------------------------------------------------------------------- Addr.Loc.City

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDR_LOC_CITY = "\"Addr.Loc.City\"";

    /**
     * The length of the {@value #COLUMN_NAME_ADDR_LOC_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDR_LOC_CITY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDR_LOC_CITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDR_LOC_CITY = "addrLocCity";

    // ------------------------------------------------------------------------------------------------- Addr.PostalCode

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDR_POSTAL_CODE = "\"Addr.PostalCode\"";

    /**
     * The length of the {@value #COLUMN_NAME_ADDR_POSTAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDR_POSTAL_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDR_POSTAL_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDR_POSTAL_CODE = "addrPostalCode";

    // ----------------------------------------------------------------------------------------------------------- EMail

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_E_MAIL = "EMail";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_E_MAIL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_E_MAIL = "eMail";

    // --------------------------------------------------------------------------------------------------------- WebSite

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WEB_SITE = "WebSite";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEB_SITE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEB_SITE = "webSite";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJobCandidateView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "jobCandidateId=" + jobCandidateId
               + ",businessEntityId=" + businessEntityId
               + ",namePrefix=" + namePrefix
               + ",nameFirst=" + nameFirst
               + ",nameMiddle=" + nameMiddle
               + ",nameLast=" + nameLast
               + ",nameSuffix=" + nameSuffix
               + ",skills=" + skills
               + ",addrType=" + addrType
               + ",addrLocCountryRegion=" + addrLocCountryRegion
               + ",addrLocState=" + addrLocState
               + ",addrLocCity=" + addrLocCity
               + ",addrPostalCode=" + addrPostalCode
               + ",eMail=" + eMail
               + ",webSite=" + webSite
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
        if (!(obj instanceof MappedJobCandidateView that)) {
            return false;
        }
        return Objects.equals(jobCandidateId, that.jobCandidateId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(jobCandidateId);
    }

    // -------------------------------------------------------------------------------------------------- jobCandidateId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     */
    @Nullable
    public Integer getJobCandidateId() {
        return jobCandidateId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute with the specified value.
     *
     * @param jobCandidateId new value for {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     */
    protected void setJobCandidateId(@Nullable final Integer jobCandidateId) {
        this.jobCandidateId = jobCandidateId;
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    @Nullable
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(@Nullable final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ------------------------------------------------------------------------------------------------------ namePrefix

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_PREFIX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_PREFIX} attribute.
     */
    @Nullable
    public String getNamePrefix() {
        return namePrefix;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_PREFIX} attribute with the specified value.
     *
     * @param namePrefix new value for {@value #ATTRIBUTE_NAME_NAME_PREFIX} attribute.
     */
    protected void setNamePrefix(@Nullable final String namePrefix) {
        this.namePrefix = namePrefix;
    }

    // ------------------------------------------------------------------------------------------------------- nameFirst

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_FIRST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_FIRST} attribute.
     */
    @Nullable
    public String getNameFirst() {
        return nameFirst;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_FIRST} attribute with the specified value.
     *
     * @param nameFirst new value for {@value #ATTRIBUTE_NAME_NAME_FIRST} attribute.
     */
    protected void setNameFirst(@Nullable final String nameFirst) {
        this.nameFirst = nameFirst;
    }

    // ------------------------------------------------------------------------------------------------------ nameMiddle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_MIDDLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_MIDDLE} attribute.
     */
    @Nullable
    public String getNameMiddle() {
        return nameMiddle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_MIDDLE} attribute with the specified value.
     *
     * @param nameMiddle new value for {@value #ATTRIBUTE_NAME_NAME_MIDDLE} attribute.
     */
    protected void setNameMiddle(@Nullable final String nameMiddle) {
        this.nameMiddle = nameMiddle;
    }

    // -------------------------------------------------------------------------------------------------------- nameLast

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_LAST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_LAST} attribute.
     */
    @Nullable
    public String getNameLast() {
        return nameLast;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_LAST} attribute with the specified value.
     *
     * @param nameLast new value for {@value #ATTRIBUTE_NAME_NAME_LAST} attribute.
     */
    protected void setNameLast(@Nullable final String nameLast) {
        this.nameLast = nameLast;
    }

    // ------------------------------------------------------------------------------------------------------ nameSuffix

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_SUFFIX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_SUFFIX} attribute.
     */
    @Nullable
    public String getNameSuffix() {
        return nameSuffix;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_SUFFIX} attribute with the specified value.
     *
     * @param nameSuffix new value for {@value #ATTRIBUTE_NAME_NAME_SUFFIX} attribute.
     */
    protected void setNameSuffix(@Nullable final String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    // ---------------------------------------------------------------------------------------------------------- skills

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SKILLS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SKILLS} attribute.
     */
    @Nullable
    public String getSkills() {
        return skills;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SKILLS} attribute with the specified value.
     *
     * @param skills new value for {@value #ATTRIBUTE_NAME_SKILLS} attribute.
     */
    protected void setSkills(@Nullable final String skills) {
        this.skills = skills;
    }

    // -------------------------------------------------------------------------------------------------------- addrType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDR_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDR_TYPE} attribute.
     */
    @Nullable
    public String getAddrType() {
        return addrType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDR_TYPE} attribute with the specified value.
     *
     * @param addrType new value for {@value #ATTRIBUTE_NAME_ADDR_TYPE} attribute.
     */
    protected void setAddrType(@Nullable final String addrType) {
        this.addrType = addrType;
    }

    // -------------------------------------------------------------------------------------------- addrLocCountryRegion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_COUNTRY_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_COUNTRY_REGION} attribute.
     */
    @Nullable
    public String getAddrLocCountryRegion() {
        return addrLocCountryRegion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_COUNTRY_REGION} attribute with the specified value.
     *
     * @param addrLocCountryRegion new value for {@value #ATTRIBUTE_NAME_ADDR_LOC_COUNTRY_REGION} attribute.
     */
    protected void setAddrLocCountryRegion(@Nullable final String addrLocCountryRegion) {
        this.addrLocCountryRegion = addrLocCountryRegion;
    }

    // ---------------------------------------------------------------------------------------------------- addrLocState

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_STATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_STATE} attribute.
     */
    @Nullable
    public String getAddrLocState() {
        return addrLocState;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_STATE} attribute with the specified value.
     *
     * @param addrLocState new value for {@value #ATTRIBUTE_NAME_ADDR_LOC_STATE} attribute.
     */
    protected void setAddrLocState(@Nullable final String addrLocState) {
        this.addrLocState = addrLocState;
    }

    // ----------------------------------------------------------------------------------------------------- addrLocCity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_CITY} attribute.
     */
    @Nullable
    public String getAddrLocCity() {
        return addrLocCity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_CITY} attribute with the specified value.
     *
     * @param addrLocCity new value for {@value #ATTRIBUTE_NAME_ADDR_LOC_CITY} attribute.
     */
    protected void setAddrLocCity(@Nullable final String addrLocCity) {
        this.addrLocCity = addrLocCity;
    }

    // -------------------------------------------------------------------------------------------------- addrPostalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDR_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDR_POSTAL_CODE} attribute.
     */
    @Nullable
    public String getAddrPostalCode() {
        return addrPostalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDR_POSTAL_CODE} attribute with the specified value.
     *
     * @param addrPostalCode new value for {@value #ATTRIBUTE_NAME_ADDR_POSTAL_CODE} attribute.
     */
    protected void setAddrPostalCode(@Nullable final String addrPostalCode) {
        this.addrPostalCode = addrPostalCode;
    }

    // ----------------------------------------------------------------------------------------------------------- eMail

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_E_MAIL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_E_MAIL} attribute.
     */
    @Nullable
    public String getEMail() {
        return eMail;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_E_MAIL} attribute with the specified value.
     *
     * @param eMail new value for {@value #ATTRIBUTE_NAME_E_MAIL} attribute.
     */
    protected void setEMail(@Nullable final String eMail) {
        this.eMail = eMail;
    }

    // --------------------------------------------------------------------------------------------------------- webSite

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEB_SITE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEB_SITE} attribute.
     */
    @Nullable
    public String getWebSite() {
        return webSite;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEB_SITE} attribute with the specified value.
     *
     * @param webSite new value for {@value #ATTRIBUTE_NAME_WEB_SITE} attribute.
     */
    protected void setWebSite(@Nullable final String webSite) {
        this.webSite = webSite;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    protected void setModifiedDate(@Nonnull final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_JOB_CANDIDATE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer jobCandidateId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_NAME_PREFIX)
    @Column(name = COLUMN_NAME_NAME_PREFIX,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_PREFIX
    )
    private String namePrefix;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_NAME_FIRST)
    @Column(name = COLUMN_NAME_NAME_FIRST,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_FIRST
    )
    private String nameFirst;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_NAME_MIDDLE)
    @Column(name = COLUMN_NAME_NAME_MIDDLE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_MIDDLE
    )
    private String nameMiddle;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_NAME_LAST)
    @Column(name = COLUMN_NAME_NAME_LAST,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_LAST
    )
    private String nameLast;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_NAME_SUFFIX)
    @Column(name = COLUMN_NAME_NAME_SUFFIX,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_SUFFIX
    )
    private String nameSuffix;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_SKILLS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String skills;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_ADDR_TYPE)
    @Column(name = COLUMN_NAME_ADDR_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ADDR_TYPE
    )
    private String addrType;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_ADDR_LOC_COUNTRY_REGION)
    @Column(name = COLUMN_NAME_ADDR_LOC_COUNTRY_REGION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ADDR_LOC_COUNTRY_REGION
    )
    private String addrLocCountryRegion;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_ADDR_LOC_STATE)
    @Column(name = COLUMN_NAME_ADDR_LOC_STATE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ADDR_LOC_STATE
    )
    private String addrLocState;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_ADDR_LOC_CITY)
    @Column(name = COLUMN_NAME_ADDR_LOC_CITY,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ADDR_LOC_CITY
    )
    private String addrLocCity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_ADDR_POSTAL_CODE)
    @Column(name = COLUMN_NAME_ADDR_POSTAL_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ADDR_POSTAL_CODE
    )
    private String addrPostalCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_E_MAIL,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String eMail;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_WEB_SITE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String webSite;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

