package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production;

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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.Illustration} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Production.Illustration}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code IllustrationID}</th>
 *     <td>Primary key for Illustration records.</td></tr>
 * <tr><th scope="row">{@code Diagram}</th>
 *     <td>Illustrations used in manufacturing instructions. Stored as XML.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.Illustration}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedIllustration.TABLE_SCHEMA,
        table = MappedIllustration.TABLE_NAME,
        value = "Bicycle assembly diagrams."
)
public abstract class MappedIllustration {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Illustration";

    // ------------------------------------------------------------------------------ IllustrationID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ILLUSTRATION_ID = "IllustrationID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_ILLUSTRATION_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_ILLUSTRATION_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_ILLUSTRATION_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_ILLUSTRATION_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ILLUSTRATION_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ILLUSTRATION_ID = "illustrationId";

    // ------------------------------------------------------------------------------------- Diagram

    /**
     * The name of the {@code xml} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DIAGRAM = "Diagram";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DIAGRAM} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DIAGRAM = "diagram";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedIllustration() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "illustrationId=" + illustrationId
               + ",diagram=" + diagram
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
        if (!(obj instanceof MappedIllustration that)) {
            return false;
        }
        return Objects.equals(illustrationId, that.illustrationId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(illustrationId);
    }

    // ------------------------------------------------------------------------------ illustrationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ILLUSTRATION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ILLUSTRATION_ID} attribute.
     */
    public Integer getIllustrationId() {
        return illustrationId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ILLUSTRATION_ID} attribute with the
     * specified value.
     *
     * @param illustrationId new value for {@value #ATTRIBUTE_NAME_ILLUSTRATION_ID} attribute.
     */
    protected void setIllustrationId(final Integer illustrationId) {
        this.illustrationId = illustrationId;
    }

    // ------------------------------------------------------------------------------------- diagram

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DIAGRAM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DIAGRAM} attribute.
     */
    public String getDiagram() {
        return diagram;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DIAGRAM} attribute with the specified
     * value.
     *
     * @param diagram new value for {@value #ATTRIBUTE_NAME_DIAGRAM} attribute.
     */
    public void setDiagram(final String diagram) {
        this.diagram = diagram;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified
     * value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the
    // identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Max(ATTRIBUTE_MAX_ILLUSTRATION_ID)
    @Min(ATTRIBUTE_MIN_ILLUSTRATION_ID)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ILLUSTRATION_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_ILLUSTRATION_ID,
                          value = "Primary key for Illustration records.")
    private Integer illustrationId;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DIAGRAM, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_DIAGRAM,
                          value = "Illustrations used in manufacturing instructions. Stored as "
                                  + "XML.")
    private String diagram;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_Illustration_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

