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
 * A mapped superclass which holds the mappings of the {@code dbo.DatabaseLog} table.
 */
@MappedSuperclass
public abstract class MappedDatabaseLog {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "DatabaseLog";

    // --------------------------------------------------------------------------------------------------- DatabaseLogID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DATABASE_LOG_ID = "DatabaseLogID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DATABASE_LOG_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DATABASE_LOG_ID = "databaseLogId";

    // -------------------------------------------------------------------------------------------------------- PostTime

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POST_TIME = "PostTime";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POST_TIME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_POST_TIME = "postTime";

    // ---------------------------------------------------------------------------------------------------- DatabaseUser

    /**
     * The name of the {@code sysname(128), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DATABASE_USER = "DatabaseUser";

    /**
     * The length of the {@value #COLUMN_NAME_DATABASE_USER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DATABASE_USER = 128;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DATABASE_USER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DATABASE_USER = "databaseUser";

    // ----------------------------------------------------------------------------------------------------------- Event

    /**
     * The name of the {@code sysname(128), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EVENT = "Event";

    /**
     * The length of the {@value #COLUMN_NAME_EVENT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EVENT = 128;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EVENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EVENT = "event";

    // ---------------------------------------------------------------------------------------------------------- Schema

    /**
     * The name of the {@code sysname(128)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCHEMA = "\"Schema\"";

    /**
     * The length of the {@value #COLUMN_NAME_SCHEMA} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SCHEMA = 128;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCHEMA} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCHEMA = "schema";

    // ---------------------------------------------------------------------------------------------------------- Object

    /**
     * The name of the {@code sysname(128)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OBJECT = "Object";

    /**
     * The length of the {@value #COLUMN_NAME_OBJECT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_OBJECT = 128;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OBJECT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_OBJECT = "object";

    // ------------------------------------------------------------------------------------------------------------ TSQL

    /**
     * The name of the {@code nvarchar(max), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TSQL = "TSQL";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TSQL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TSQL = "tsql";

    // -------------------------------------------------------------------------------------------------------- XmlEvent

    /**
     * The name of the {@code xml, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_XML_EVENT = "XmlEvent";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_XML_EVENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_XML_EVENT = "xmlEvent";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedDatabaseLog() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "databaseLogId=" + databaseLogId
               + ",postTime=" + postTime
               + ",databaseUser=" + databaseUser
               + ",event=" + event
               + ",schema=" + schema
               + ",object=" + object
               + ",tsql=" + tsql
               + ",xmlEvent=" + xmlEvent
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
        if (!(obj instanceof MappedDatabaseLog that)) {
            return false;
        }
        return Objects.equals(databaseLogId, that.databaseLogId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(databaseLogId);
    }

    // --------------------------------------------------------------------------------------------------- databaseLogId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DATABASE_LOG_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DATABASE_LOG_ID} attribute.
     */
    @Nullable
    public Integer getDatabaseLogId() {
        return databaseLogId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DATABASE_LOG_ID} attribute with the specified value.
     *
     * @param databaseLogId new value for {@value #ATTRIBUTE_NAME_DATABASE_LOG_ID} attribute.
     */
    protected void setDatabaseLogId(@Nullable final Integer databaseLogId) {
        this.databaseLogId = databaseLogId;
    }

    // -------------------------------------------------------------------------------------------------------- postTime

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POST_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POST_TIME} attribute.
     */
    @Nonnull
    public LocalDateTime getPostTime() {
        return postTime;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POST_TIME} attribute with the specified value.
     *
     * @param postTime new value for {@value #ATTRIBUTE_NAME_POST_TIME} attribute.
     */
    public void setPostTime(@Nonnull final LocalDateTime postTime) {
        this.postTime = postTime;
    }

    // ---------------------------------------------------------------------------------------------------- databaseUser

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DATABASE_USER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DATABASE_USER} attribute.
     */
    @Nonnull
    public String getDatabaseUser() {
        return databaseUser;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DATABASE_USER} attribute with the specified value.
     *
     * @param databaseUser new value for {@value #ATTRIBUTE_NAME_DATABASE_USER} attribute.
     */
    public void setDatabaseUser(@Nonnull final String databaseUser) {
        this.databaseUser = databaseUser;
    }

    // ----------------------------------------------------------------------------------------------------------- event

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EVENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EVENT} attribute.
     */
    @Nonnull
    public String getEvent() {
        return event;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EVENT} attribute with the specified value.
     *
     * @param event new value for {@value #ATTRIBUTE_NAME_EVENT} attribute.
     */
    public void setEvent(@Nonnull final String event) {
        this.event = event;
    }

    // ---------------------------------------------------------------------------------------------------------- schema

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCHEMA} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCHEMA} attribute.
     */
    @Nullable
    public String getSchema() {
        return schema;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCHEMA} attribute with the specified value.
     *
     * @param schema new value for {@value #ATTRIBUTE_NAME_SCHEMA} attribute.
     */
    public void setSchema(@Nullable final String schema) {
        this.schema = schema;
    }

    // ---------------------------------------------------------------------------------------------------------- object

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OBJECT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OBJECT} attribute.
     */
    @Nullable
    public String getObject() {
        return object;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OBJECT} attribute with the specified value.
     *
     * @param object new value for {@value #ATTRIBUTE_NAME_OBJECT} attribute.
     */
    public void setObject(@Nullable final String object) {
        this.object = object;
    }

    // ------------------------------------------------------------------------------------------------------------ tsql

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TSQL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TSQL} attribute.
     */
    @Nonnull
    public String getTsql() {
        return tsql;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TSQL} attribute with the specified value.
     *
     * @param tsql new value for {@value #ATTRIBUTE_NAME_TSQL} attribute.
     */
    public void setTsql(@Nonnull final String tsql) {
        this.tsql = tsql;
    }

    // -------------------------------------------------------------------------------------------------------- xmlEvent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_XML_EVENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_XML_EVENT} attribute.
     */
    @Nonnull
    public String getXmlEvent() {
        return xmlEvent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_XML_EVENT} attribute with the specified value.
     *
     * @param xmlEvent new value for {@value #ATTRIBUTE_NAME_XML_EVENT} attribute.
     */
    public void setXmlEvent(@Nonnull final String xmlEvent) {
        this.xmlEvent = xmlEvent;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_DATABASE_LOG_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer databaseLogId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_POST_TIME,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime postTime;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_DATABASE_USER)
    @NotNull
    @Column(name = COLUMN_NAME_DATABASE_USER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DATABASE_USER
    )
    private String databaseUser;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_EVENT)
    @NotNull
    @Column(name = COLUMN_NAME_EVENT,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EVENT
    )
    private String event;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_SCHEMA)
    @Column(name = COLUMN_NAME_SCHEMA,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SCHEMA
    )
    private String schema;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_OBJECT)
    @Column(name = COLUMN_NAME_OBJECT,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_OBJECT
    )
    private String object;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_TSQL,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private String tsql;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_XML_EVENT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private String xmlEvent;
}

