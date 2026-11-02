package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.dbo;

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

import com.github.jinahya.mssql.sample.databases.persistence.common.MappedWithIdentityId;

import io.github.jinahya.sqlserver.xml.schema.binding.b872ce.EventInstance;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.DatabaseLog} table.
 * <p>
 * Read-only: the database DDL trigger ddlDatabaseTriggerLog, ON DATABASE FOR DDL_DATABASE_LEVEL_EVENTS, inserts every
 * row of this table, out of EVENTDATA(), and nothing in the database updates one; so no column of it is insertable or
 * updatable, but for the identifier, of which EclipseLink requires a writable mapping.
 */
@AttributeOverride(name = MappedWithIdentityId.ATTRIBUTE_NAME_ID_,
                   column = @Column(name = MappedDatabaseLog.COLUMN_NAME_DATABASE_LOG_ID,
                                    nullable = false,
                                    insertable = true,
                                    updatable = false
                   )
)
@MappedSuperclass
public abstract class MappedDatabaseLog
        extends MappedWithIdentityId {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "DatabaseLog";

    // --------------------------------------------------------------------------------------------------- DatabaseLogID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DATABASE_LOG_ID = "DatabaseLogID";

    // -------------------------------------------------------------------------------------------------------- PostTime

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POST_TIME = "PostTime";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POST_TIME} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_DATABASE_USER} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_EVENT} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCHEMA} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_OBJECT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_OBJECT = "object";

    // ------------------------------------------------------------------------------------------------------------ TSQL

    /**
     * The name of the {@code nvarchar(max), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TSQL = "TSQL";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TSQL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TSQL = "tsql";

    // -------------------------------------------------------------------------------------------------------- XmlEvent

    /**
     * The name of the {@code xml, NOT NULL} column. The value is {@value}.
     * <p>
     * The column is untyped {@code xml}, so it takes content as well as a document, and nothing in the column holds
     * its value to any shape. What fixes the shape is its one writer: the database DDL trigger
     * {@code ddlDatabaseTriggerLog}, {@code ON DATABASE FOR DDL_DATABASE_LEVEL_EVENTS}, inserts a row for each DDL
     * statement, with {@code EVENTDATA()} in this column. That is one {@code <EVENT_INSTANCE>} document, a snapshot of
     * the event, never changed afterwards, which the other columns of the row are read out of.
     * <p>
     * The children of the root depend on the event type, each of which has one layout, the complex type
     * {@code EVENT_INSTANCE_<event type>} of the {@code EVENTDATA()} schema, {@code events.xsd}, which SQL Server on
     * Windows installs under {@code Tools\Binn\schemas\sqlserver\2006\11\events}; see
     * <a href="https://learn.microsoft.com/sql/t-sql/functions/eventdata-transact-sql">EVENTDATA (Transact-SQL)</a>.
     * {@code EventType}, {@code PostTime}, {@code SPID}, {@code ServerName}, {@code LoginName}, {@code UserName} and
     * {@code DatabaseName} come first, then the elements of the event type, {@code SchemaName}, {@code ObjectName} and
     * {@code ObjectType} for a {@code CREATE_TABLE}, say, and {@code TSQLCommand}, the statement, last. A new version
     * of SQL Server may add elements and event types; read the value by element names, not by positions.
     * <p>
     * The attribute is the document bound to the class of its event type, through
     * {@link EventInstanceStringAttributeConverter}. It is read-only: the trigger, its one writer, inserts it, and
     * nothing updates it; and a provider which compares a snapshot of it by {@code equals}, which the bound classes do
     * not override, would otherwise update every row it loads.
     */
    public static final String COLUMN_NAME_XML_EVENT = "XmlEvent";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_XML_EVENT} column. The value is {@value}.
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
               + "databaseLogId=" + getDatabaseLogId()
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

    // --------------------------------------------------------------------------------------------------- databaseLogId

    /**
     * Returns current value of the identifier, which maps the {@value #COLUMN_NAME_DATABASE_LOG_ID} column.
     *
     * @return current value of the identifier.
     */
    public Long getDatabaseLogId() {
        return getId_();
    }

    // -------------------------------------------------------------------------------------------------------- postTime

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POST_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POST_TIME} attribute.
     */
    public LocalDateTime getPostTime() {
        return postTime;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POST_TIME} attribute with the specified value.
     *
     * @param postTime new value for {@value #ATTRIBUTE_NAME_POST_TIME} attribute.
     */
    public void setPostTime(final LocalDateTime postTime) {
        this.postTime = postTime;
    }

    // ---------------------------------------------------------------------------------------------------- databaseUser

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DATABASE_USER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DATABASE_USER} attribute.
     */
    public String getDatabaseUser() {
        return databaseUser;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DATABASE_USER} attribute with the specified value.
     *
     * @param databaseUser new value for {@value #ATTRIBUTE_NAME_DATABASE_USER} attribute.
     */
    public void setDatabaseUser(final String databaseUser) {
        this.databaseUser = databaseUser;
    }

    // ----------------------------------------------------------------------------------------------------------- event

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EVENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EVENT} attribute.
     */
    public String getEvent() {
        return event;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EVENT} attribute with the specified value.
     *
     * @param event new value for {@value #ATTRIBUTE_NAME_EVENT} attribute.
     */
    public void setEvent(final String event) {
        this.event = event;
    }

    // ---------------------------------------------------------------------------------------------------------- schema

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCHEMA} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCHEMA} attribute.
     */
    public String getSchema() {
        return schema;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCHEMA} attribute with the specified value.
     *
     * @param schema new value for {@value #ATTRIBUTE_NAME_SCHEMA} attribute.
     */
    public void setSchema(final String schema) {
        this.schema = schema;
    }

    // ---------------------------------------------------------------------------------------------------------- object

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OBJECT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OBJECT} attribute.
     */
    public String getObject() {
        return object;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OBJECT} attribute with the specified value.
     *
     * @param object new value for {@value #ATTRIBUTE_NAME_OBJECT} attribute.
     */
    public void setObject(final String object) {
        this.object = object;
    }

    // ------------------------------------------------------------------------------------------------------------ tsql

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TSQL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TSQL} attribute.
     */
    public String getTsql() {
        return tsql;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TSQL} attribute with the specified value.
     *
     * @param tsql new value for {@value #ATTRIBUTE_NAME_TSQL} attribute.
     */
    public void setTsql(final String tsql) {
        this.tsql = tsql;
    }

    // -------------------------------------------------------------------------------------------------------- xmlEvent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_XML_EVENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_XML_EVENT} attribute; an instance of the class
     * {@link io.github.jinahya.sqlserver.xml.schema.binding.b872ce.EventInstances#typeOf(String)} names for its event
     * type.
     */
    public EventInstance getXmlEvent() {
        return xmlEvent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_XML_EVENT} attribute with the specified value.
     *
     * @param xmlEvent new value for {@value #ATTRIBUTE_NAME_XML_EVENT} attribute.
     */
    public void setXmlEvent(final EventInstance xmlEvent) {
        this.xmlEvent = xmlEvent;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the database DDL trigger ddlDatabaseTriggerLog inserts it, and nothing updates it; see the
    // class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POST_TIME,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime postTime;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the database DDL trigger ddlDatabaseTriggerLog inserts it, and nothing updates it; see the
    // class documentation
    @Size(max = COLUMN_LENGTH_DATABASE_USER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DATABASE_USER,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_DATABASE_USER
    )
    private String databaseUser;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the database DDL trigger ddlDatabaseTriggerLog inserts it, and nothing updates it; see the
    // class documentation
    @Size(max = COLUMN_LENGTH_EVENT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EVENT,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_EVENT
    )
    private String event;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the database DDL trigger ddlDatabaseTriggerLog inserts it, and nothing updates it; see the
    // class documentation
    @Size(max = COLUMN_LENGTH_SCHEMA)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SCHEMA,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SCHEMA
    )
    private String schema;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the database DDL trigger ddlDatabaseTriggerLog inserts it, and nothing updates it; see the
    // class documentation
    @Size(max = COLUMN_LENGTH_OBJECT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_OBJECT,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_OBJECT
    )
    private String object;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the database DDL trigger ddlDatabaseTriggerLog inserts it, and nothing updates it; see the
    // class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TSQL,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private String tsql;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the database DDL trigger ddlDatabaseTriggerLog inserts it, and nothing updates it; see the
    // documentation of COLUMN_NAME_XML_EVENT
    @NotNull
    @Convert(converter = EventInstanceStringAttributeConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_XML_EVENT,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private EventInstance xmlEvent;
}

