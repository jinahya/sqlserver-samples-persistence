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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.SalesChannel} table.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.SalesChannel}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code IsActive}</th><td>{@code ((1))}</td></tr>
 * </tbody>
 * </table>
 */
@MappedSuperclass
public abstract class MappedSalesChannel {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesChannel";

    // ------------------------------------------------------------------------------ SalesChannelID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_CHANNEL_ID = "SalesChannelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_CHANNEL_ID = "salesChannelId";

    // --------------------------------------------------------------------------------- ChannelCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_CODE = "ChannelCode";

    /**
     * The length of the {@value #COLUMN_NAME_CHANNEL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CHANNEL_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_CODE = "channelCode";

    // --------------------------------------------------------------------------------- ChannelName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_NAME = "ChannelName";

    /**
     * The length of the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CHANNEL_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_NAME = "channelName";

    // --------------------------------------------------------------------------------- Description

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    /**
     * The length of the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DESCRIPTION = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // ------------------------------------------------------------------------------------ IsActive

    /**
     * The name of the {@code bit, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ACTIVE = "IsActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ACTIVE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ACTIVE = "isActive";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesChannel() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "salesChannelId=" + salesChannelId
               + ",channelCode=" + channelCode
               + ",channelName=" + channelName
               + ",description=" + description
               + ",isActive=" + isActive
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
        if (!(obj instanceof MappedSalesChannel that)) {
            return false;
        }
        return Objects.equals(salesChannelId, that.salesChannelId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(salesChannelId);
    }

    // ------------------------------------------------------------------------------ salesChannelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute.
     */
    public Integer getSalesChannelId() {
        return salesChannelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute with the specified value.
     *
     * @param salesChannelId new value for {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute.
     */
    protected void setSalesChannelId(final Integer salesChannelId) {
        this.salesChannelId = salesChannelId;
    }

    // --------------------------------------------------------------------------------- channelCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_CODE} attribute.
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_CODE} attribute with the specified value.
     *
     * @param channelCode new value for {@value #ATTRIBUTE_NAME_CHANNEL_CODE} attribute.
     */
    public void setChannelCode(final String channelCode) {
        this.channelCode = channelCode;
    }

    // --------------------------------------------------------------------------------- channelName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute with the specified value.
     *
     * @param channelName new value for {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    public void setChannelName(final String channelName) {
        this.channelName = channelName;
    }

    // --------------------------------------------------------------------------------- description

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with the specified value.
     *
     * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    // ------------------------------------------------------------------------------------ isActive

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute with the specified value.
     *
     * @param isActive new value for {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SALES_CHANNEL_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer salesChannelId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CHANNEL_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CHANNEL_CODE
    )
    private String channelCode;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CHANNEL_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CHANNEL_NAME
    )
    private String channelName;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DESCRIPTION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DESCRIPTION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DESCRIPTION
    )
    private String description;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ACTIVE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDefault("((1))")
    private Boolean isActive;
}

