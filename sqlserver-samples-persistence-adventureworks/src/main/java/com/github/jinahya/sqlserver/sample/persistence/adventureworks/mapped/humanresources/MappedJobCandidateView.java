package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources;

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

import com.github.jinahya.sqlserver.sample.persistence.common.mapped.MappedWithIdentityId;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.vJobCandidate} view.
 * <p>
 * Identifier: the view declares no key; JobCandidateID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; some of its columns are derived or constant (error 4406); one of its columns is an identity column of its
 * base table, which no UPDATE sets (error 8102).
 */
// read-only: SQL Server does not write this view; see the class documentation
@AttributeOverride(name = MappedWithIdentityId.ATTRIBUTE_NAME_ID_,
                   column = @Column(name = MappedJobCandidateView.COLUMN_NAME_JOB_CANDIDATE_ID, nullable = false, insertable = false, updatable = false)
)
@MappedSuperclass
public abstract class MappedJobCandidateView
        extends MappedWithIdentityId {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vJobCandidate";

    // ------------------------------------------------------------------------------ JobCandidateID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_CANDIDATE_ID = "JobCandidateID";

    // ---------------------------------------------------------------------------- BusinessEntityID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // --------------------------------------------------------------------------------- Name.Prefix

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_PREFIX = "\"Name.Prefix\"";

    /**
     * The length of the {@value #COLUMN_NAME_NAME_PREFIX} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_PREFIX = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_PREFIX} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_PREFIX = "namePrefix";

    // ---------------------------------------------------------------------------------- Name.First

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_FIRST = "\"Name.First\"";

    /**
     * The length of the {@value #COLUMN_NAME_NAME_FIRST} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_FIRST = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_FIRST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_FIRST = "nameFirst";

    // --------------------------------------------------------------------------------- Name.Middle

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_MIDDLE = "\"Name.Middle\"";

    /**
     * The length of the {@value #COLUMN_NAME_NAME_MIDDLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_MIDDLE = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_MIDDLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_MIDDLE = "nameMiddle";

    // ----------------------------------------------------------------------------------- Name.Last

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_LAST = "\"Name.Last\"";

    /**
     * The length of the {@value #COLUMN_NAME_NAME_LAST} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_LAST = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_LAST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_LAST = "nameLast";

    // --------------------------------------------------------------------------------- Name.Suffix

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME_SUFFIX = "\"Name.Suffix\"";

    /**
     * The length of the {@value #COLUMN_NAME_NAME_SUFFIX} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_SUFFIX = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME_SUFFIX} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME_SUFFIX = "nameSuffix";

    // -------------------------------------------------------------------------------------- Skills

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SKILLS = "Skills";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SKILLS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SKILLS = "skills";

    // ----------------------------------------------------------------------------------- Addr.Type

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDR_TYPE = "\"Addr.Type\"";

    /**
     * The length of the {@value #COLUMN_NAME_ADDR_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDR_TYPE = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDR_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDR_TYPE = "addrType";

    // ---------------------------------------------------------------------- Addr.Loc.CountryRegion

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

    // ------------------------------------------------------------------------------ Addr.Loc.State

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDR_LOC_STATE = "\"Addr.Loc.State\"";

    /**
     * The length of the {@value #COLUMN_NAME_ADDR_LOC_STATE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDR_LOC_STATE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDR_LOC_STATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDR_LOC_STATE = "addrLocState";

    // ------------------------------------------------------------------------------- Addr.Loc.City

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDR_LOC_CITY = "\"Addr.Loc.City\"";

    /**
     * The length of the {@value #COLUMN_NAME_ADDR_LOC_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDR_LOC_CITY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDR_LOC_CITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDR_LOC_CITY = "addrLocCity";

    // ----------------------------------------------------------------------------- Addr.PostalCode

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDR_POSTAL_CODE = "\"Addr.PostalCode\"";

    /**
     * The length of the {@value #COLUMN_NAME_ADDR_POSTAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDR_POSTAL_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDR_POSTAL_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDR_POSTAL_CODE = "addrPostalCode";

    // --------------------------------------------------------------------------------------- EMail

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_E_MAIL = "EMail";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_E_MAIL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_E_MAIL = "eMail";

    // ------------------------------------------------------------------------------------- WebSite

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WEB_SITE = "WebSite";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEB_SITE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEB_SITE = "webSite";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJobCandidateView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "jobCandidateId=" + getJobCandidateId()
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
        return Objects.equals(getId_(), that.getId_());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getId_());
    }

    // ------------------------------------------------------------------------------ jobCandidateId

    /**
     * Returns current value of the identifier, which maps the {@value #COLUMN_NAME_JOB_CANDIDATE_ID} column.
     *
     * @return current value of the identifier.
     */
    public Long getJobCandidateId() {
        return getId_();
    }

    // ---------------------------------------------------------------------------- businessEntityId

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

    // ---------------------------------------------------------------------------------- namePrefix

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_PREFIX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_PREFIX} attribute.
     */
    public String getNamePrefix() {
        return namePrefix;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_PREFIX} attribute with the specified value.
     *
     * @param namePrefix new value for {@value #ATTRIBUTE_NAME_NAME_PREFIX} attribute.
     */
    protected void setNamePrefix(final String namePrefix) {
        this.namePrefix = namePrefix;
    }

    // ----------------------------------------------------------------------------------- nameFirst

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_FIRST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_FIRST} attribute.
     */
    public String getNameFirst() {
        return nameFirst;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_FIRST} attribute with the specified value.
     *
     * @param nameFirst new value for {@value #ATTRIBUTE_NAME_NAME_FIRST} attribute.
     */
    protected void setNameFirst(final String nameFirst) {
        this.nameFirst = nameFirst;
    }

    // ---------------------------------------------------------------------------------- nameMiddle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_MIDDLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_MIDDLE} attribute.
     */
    public String getNameMiddle() {
        return nameMiddle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_MIDDLE} attribute with the specified value.
     *
     * @param nameMiddle new value for {@value #ATTRIBUTE_NAME_NAME_MIDDLE} attribute.
     */
    protected void setNameMiddle(final String nameMiddle) {
        this.nameMiddle = nameMiddle;
    }

    // ------------------------------------------------------------------------------------ nameLast

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_LAST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_LAST} attribute.
     */
    public String getNameLast() {
        return nameLast;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_LAST} attribute with the specified value.
     *
     * @param nameLast new value for {@value #ATTRIBUTE_NAME_NAME_LAST} attribute.
     */
    protected void setNameLast(final String nameLast) {
        this.nameLast = nameLast;
    }

    // ---------------------------------------------------------------------------------- nameSuffix

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME_SUFFIX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME_SUFFIX} attribute.
     */
    public String getNameSuffix() {
        return nameSuffix;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME_SUFFIX} attribute with the specified value.
     *
     * @param nameSuffix new value for {@value #ATTRIBUTE_NAME_NAME_SUFFIX} attribute.
     */
    protected void setNameSuffix(final String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    // -------------------------------------------------------------------------------------- skills

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SKILLS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SKILLS} attribute.
     */
    public String getSkills() {
        return skills;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SKILLS} attribute with the specified value.
     *
     * @param skills new value for {@value #ATTRIBUTE_NAME_SKILLS} attribute.
     */
    protected void setSkills(final String skills) {
        this.skills = skills;
    }

    // ------------------------------------------------------------------------------------ addrType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDR_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDR_TYPE} attribute.
     */
    public String getAddrType() {
        return addrType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDR_TYPE} attribute with the specified value.
     *
     * @param addrType new value for {@value #ATTRIBUTE_NAME_ADDR_TYPE} attribute.
     */
    protected void setAddrType(final String addrType) {
        this.addrType = addrType;
    }

    // ------------------------------------------------------------------------ addrLocCountryRegion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_COUNTRY_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_COUNTRY_REGION} attribute.
     */
    public String getAddrLocCountryRegion() {
        return addrLocCountryRegion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_COUNTRY_REGION} attribute with the specified value.
     *
     * @param addrLocCountryRegion new value for {@value #ATTRIBUTE_NAME_ADDR_LOC_COUNTRY_REGION} attribute.
     */
    protected void setAddrLocCountryRegion(final String addrLocCountryRegion) {
        this.addrLocCountryRegion = addrLocCountryRegion;
    }

    // -------------------------------------------------------------------------------- addrLocState

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_STATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_STATE} attribute.
     */
    public String getAddrLocState() {
        return addrLocState;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_STATE} attribute with the specified value.
     *
     * @param addrLocState new value for {@value #ATTRIBUTE_NAME_ADDR_LOC_STATE} attribute.
     */
    protected void setAddrLocState(final String addrLocState) {
        this.addrLocState = addrLocState;
    }

    // --------------------------------------------------------------------------------- addrLocCity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_CITY} attribute.
     */
    public String getAddrLocCity() {
        return addrLocCity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDR_LOC_CITY} attribute with the specified value.
     *
     * @param addrLocCity new value for {@value #ATTRIBUTE_NAME_ADDR_LOC_CITY} attribute.
     */
    protected void setAddrLocCity(final String addrLocCity) {
        this.addrLocCity = addrLocCity;
    }

    // ------------------------------------------------------------------------------ addrPostalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDR_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDR_POSTAL_CODE} attribute.
     */
    public String getAddrPostalCode() {
        return addrPostalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDR_POSTAL_CODE} attribute with the specified value.
     *
     * @param addrPostalCode new value for {@value #ATTRIBUTE_NAME_ADDR_POSTAL_CODE} attribute.
     */
    protected void setAddrPostalCode(final String addrPostalCode) {
        this.addrPostalCode = addrPostalCode;
    }

    // --------------------------------------------------------------------------------------- eMail

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_E_MAIL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_E_MAIL} attribute.
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_E_MAIL} attribute with the specified value.
     *
     * @param eMail new value for {@value #ATTRIBUTE_NAME_E_MAIL} attribute.
     */
    protected void setEMail(final String eMail) {
        this.eMail = eMail;
    }

    // ------------------------------------------------------------------------------------- webSite

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEB_SITE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEB_SITE} attribute.
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEB_SITE} attribute with the specified value.
     *
     * @param webSite new value for {@value #ATTRIBUTE_NAME_WEB_SITE} attribute.
     */
    protected void setWebSite(final String webSite) {
        this.webSite = webSite;
    }

    // -------------------------------------------------------------------------------- modifiedDate

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
    protected void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID, nullable = true, insertable = false, updatable = false)
    private Integer businessEntityId;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_NAME_PREFIX)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NAME_PREFIX, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_NAME_PREFIX)
    private String namePrefix;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_NAME_FIRST)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NAME_FIRST, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_NAME_FIRST)
    private String nameFirst;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_NAME_MIDDLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NAME_MIDDLE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_NAME_MIDDLE)
    private String nameMiddle;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_NAME_LAST)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NAME_LAST, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_NAME_LAST)
    private String nameLast;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_NAME_SUFFIX)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NAME_SUFFIX, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_NAME_SUFFIX)
    private String nameSuffix;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SKILLS, nullable = true, insertable = false, updatable = false)
    private String skills;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ADDR_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDR_TYPE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_ADDR_TYPE)
    private String addrType;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ADDR_LOC_COUNTRY_REGION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDR_LOC_COUNTRY_REGION, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_ADDR_LOC_COUNTRY_REGION)
    private String addrLocCountryRegion;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ADDR_LOC_STATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDR_LOC_STATE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_ADDR_LOC_STATE)
    private String addrLocState;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ADDR_LOC_CITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDR_LOC_CITY, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_ADDR_LOC_CITY)
    private String addrLocCity;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ADDR_POSTAL_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDR_POSTAL_CODE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_ADDR_POSTAL_CODE)
    private String addrPostalCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_E_MAIL, nullable = true, insertable = false, updatable = false)
    private String eMail;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WEB_SITE, nullable = true, insertable = false, updatable = false)
    private String webSite;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false, updatable = false)
    private LocalDateTime modifiedDate;
}

