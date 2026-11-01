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

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * A plain object which holds the value of the {@value MappedDatabaseLog#COLUMN_NAME_XML_EVENT}
 * column of the {@code dbo.DatabaseLog} table: the {@code EVENT_INSTANCE} document of a DDL
 * trigger's {@code EVENTDATA()}.
 * <p>
 * The elements are the ones the installed rows carry. Every row has {@code EventType},
 * {@code PostTime}, {@code SPID}, {@code ServerName}, {@code LoginName}, {@code UserName},
 * {@code DatabaseName}, {@code ObjectType} and {@code TSQLCommand}; the others depend on the event.
 * An element which is absent leaves its property {@code null}, and an empty element, such as
 * {@code <SchemaName/>} of a database-level event, sets it to an empty string.
 *
 * @see <a
 *         href="https://learn.microsoft.com/sql/t-sql/functions/eventdata-transact-sql">EVENTDATA
 *         (Transact-SQL)</a>
 */
public class DatabaseLogXmlEvent
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 5398177256936106312L;

    /**
     * The name of the document element. The value is {@value}.
     */
    public static final String ELEMENT_NAME_EVENT_INSTANCE = "EVENT_INSTANCE";

    // ---------------------------------------------------------------------------------------------------- NESTED TYPES

    /**
     * The {@code TSQLCommand} element: the options in effect and the text of the statement.
     */
    public static class TsqlCommand
            implements Serializable {

        @Serial
        private static final long serialVersionUID = -7577635574138875717L;

        // ------------------------------------------------------------------------------------------------ CONSTRUCTORS

        /**
         * Creates a new instance.
         */
        public TsqlCommand() {
            super();
        }

        // -------------------------------------------------------------------------------------------- java.lang.Object

        @Override
        public String toString() {
            return super.toString() + "{"
                   + "ansiNulls=" + ansiNulls
                   + ",ansiNullDefault=" + ansiNullDefault
                   + ",ansiPadding=" + ansiPadding
                   + ",quotedIdentifier=" + quotedIdentifier
                   + ",encrypted=" + encrypted
                   + ",commandText=" + commandText
                   + "}";
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TsqlCommand that)) {
                return false;
            }
            return Objects.equals(ansiNulls, that.ansiNulls)
                   && Objects.equals(ansiNullDefault, that.ansiNullDefault)
                   && Objects.equals(ansiPadding, that.ansiPadding)
                   && Objects.equals(quotedIdentifier, that.quotedIdentifier)
                   && Objects.equals(encrypted, that.encrypted)
                   && Objects.equals(commandText, that.commandText);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ansiNulls, ansiNullDefault, ansiPadding, quotedIdentifier,
                                encrypted, commandText);
        }

        // --------------------------------------------------------------------------------------------------- ansiNulls

        /**
         * Returns the {@code ANSI_NULLS} attribute of the {@code SetOptions} element.
         *
         * @return the {@code ANSI_NULLS} option; {@code null} when the attribute is absent.
         */
        public Boolean getAnsiNulls() {
            return ansiNulls;
        }

        /**
         * Replaces the {@code ANSI_NULLS} option with the specified value.
         *
         * @param ansiNulls new value for the {@code ANSI_NULLS} option.
         */
        public void setAnsiNulls(final Boolean ansiNulls) {
            this.ansiNulls = ansiNulls;
        }

        // --------------------------------------------------------------------------------------------- ansiNullDefault

        /**
         * Returns the {@code ANSI_NULL_DEFAULT} attribute of the {@code SetOptions} element.
         *
         * @return the {@code ANSI_NULL_DEFAULT} option; {@code null} when the attribute is absent.
         */
        public Boolean getAnsiNullDefault() {
            return ansiNullDefault;
        }

        /**
         * Replaces the {@code ANSI_NULL_DEFAULT} option with the specified value.
         *
         * @param ansiNullDefault new value for the {@code ANSI_NULL_DEFAULT} option.
         */
        public void setAnsiNullDefault(final Boolean ansiNullDefault) {
            this.ansiNullDefault = ansiNullDefault;
        }

        // ------------------------------------------------------------------------------------------------- ansiPadding

        /**
         * Returns the {@code ANSI_PADDING} attribute of the {@code SetOptions} element.
         *
         * @return the {@code ANSI_PADDING} option; {@code null} when the attribute is absent.
         */
        public Boolean getAnsiPadding() {
            return ansiPadding;
        }

        /**
         * Replaces the {@code ANSI_PADDING} option with the specified value.
         *
         * @param ansiPadding new value for the {@code ANSI_PADDING} option.
         */
        public void setAnsiPadding(final Boolean ansiPadding) {
            this.ansiPadding = ansiPadding;
        }

        // -------------------------------------------------------------------------------------------- quotedIdentifier

        /**
         * Returns the {@code QUOTED_IDENTIFIER} attribute of the {@code SetOptions} element.
         *
         * @return the {@code QUOTED_IDENTIFIER} option; {@code null} when the attribute is absent.
         */
        public Boolean getQuotedIdentifier() {
            return quotedIdentifier;
        }

        /**
         * Replaces the {@code QUOTED_IDENTIFIER} option with the specified value.
         *
         * @param quotedIdentifier new value for the {@code QUOTED_IDENTIFIER} option.
         */
        public void setQuotedIdentifier(final Boolean quotedIdentifier) {
            this.quotedIdentifier = quotedIdentifier;
        }

        // --------------------------------------------------------------------------------------------------- encrypted

        /**
         * Returns the {@code ENCRYPTED} attribute of the {@code SetOptions} element.
         *
         * @return the {@code ENCRYPTED} option; {@code null} when the attribute is absent.
         */
        public Boolean getEncrypted() {
            return encrypted;
        }

        /**
         * Replaces the {@code ENCRYPTED} option with the specified value.
         *
         * @param encrypted new value for the {@code ENCRYPTED} option.
         */
        public void setEncrypted(final Boolean encrypted) {
            this.encrypted = encrypted;
        }

        // ------------------------------------------------------------------------------------------------- commandText

        /**
         * Returns the text of the {@code CommandText} element.
         *
         * @return the text of the statement.
         */
        public String getCommandText() {
            return commandText;
        }

        /**
         * Replaces the text of the statement with the specified value.
         *
         * @param commandText new value for the text of the statement.
         */
        public void setCommandText(final String commandText) {
            this.commandText = commandText;
        }

        // -------------------------------------------------------------------------------------------------------------
        private Boolean ansiNulls;

        private Boolean ansiNullDefault;

        private Boolean ansiPadding;

        private Boolean quotedIdentifier;

        private Boolean encrypted;

        private String commandText;
    }

    // ------------------------------------------------------------------------------------------
    // STATIC FACTORY METHODS

    /**
     * Parses the specified value of the {@value MappedDatabaseLog#COLUMN_NAME_XML_EVENT} column.
     *
     * @param xml the value to parse.
     * @return a new instance which holds what {@code xml} carries.
     * @throws IllegalArgumentException when {@code xml} is not an
     *                                  {@value #ELEMENT_NAME_EVENT_INSTANCE} document.
     */
    public static DatabaseLogXmlEvent valueOf(final String xml) {
        Objects.requireNonNull(xml, "xml is null");
        final Element root;
        try {
            final var factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            root = factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)))
                    .getDocumentElement();
        } catch (final ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalArgumentException("failed to parse xml", e);
        }
        if (!ELEMENT_NAME_EVENT_INSTANCE.equals(root.getTagName())) {
            throw new IllegalArgumentException("unknown document element: " + root.getTagName());
        }
        final var instance = new DatabaseLogXmlEvent();
        instance.eventType = text(root, "EventType");
        instance.postTime = map(text(root, "PostTime"), LocalDateTime::parse);
        instance.spid = map(text(root, "SPID"), Integer::valueOf);
        instance.serverName = text(root, "ServerName");
        instance.loginName = text(root, "LoginName");
        instance.userName = text(root, "UserName");
        instance.databaseName = text(root, "DatabaseName");
        instance.schemaName = text(root, "SchemaName");
        instance.objectName = text(root, "ObjectName");
        instance.objectType = text(root, "ObjectType");
        instance.ownerName = text(root, "OwnerName");
        instance.targetObjectName = text(root, "TargetObjectName");
        instance.targetObjectType = text(root, "TargetObjectType");
        instance.primaryXmlIndexName = text(root, "PrimaryXMLIndexName");
        instance.secondaryXmlIndexType = text(root, "SecondaryXMLIndexType");
        instance.propertyName = text(root, "PropertyName");
        instance.propertyValue = text(root, "PropertyValue");
        final var parameters = child(root, "Parameters");
        if (parameters != null) {
            instance.parameters = texts(parameters, "Param");
        }
        final var create = child(child(root, "AlterTableActionList"), "Create");
        if (create != null) {
            instance.createdConstraintNames = texts(child(create, "Constraints"), "Name");
        }
        final var command = child(root, "TSQLCommand");
        if (command != null) {
            final var tsqlCommand = new TsqlCommand();
            final var options = child(command, "SetOptions");
            if (options != null) {
                tsqlCommand.ansiNulls = option(options, "ANSI_NULLS");
                tsqlCommand.ansiNullDefault = option(options, "ANSI_NULL_DEFAULT");
                tsqlCommand.ansiPadding = option(options, "ANSI_PADDING");
                tsqlCommand.quotedIdentifier = option(options, "QUOTED_IDENTIFIER");
                tsqlCommand.encrypted = option(options, "ENCRYPTED");
            }
            tsqlCommand.commandText = text(command, "CommandText");
            instance.tsqlCommand = tsqlCommand;
        }
        return instance;
    }

    private static Element child(final Element parent, final String name) {
        if (parent == null) {
            return null;
        }
        for (var node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node.getNodeType() == Node.ELEMENT_NODE && name.equals(node.getNodeName())) {
                return (Element) node;
            }
        }
        return null;
    }

    private static String text(final Element parent, final String name) {
        final var child = child(parent, name);
        return child == null ? null : child.getTextContent();
    }

    private static List<String> texts(final Element parent, final String name) {
        final var texts = new ArrayList<String>();
        if (parent == null) {
            return texts;
        }
        for (var node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node.getNodeType() == Node.ELEMENT_NODE && name.equals(node.getNodeName())) {
                texts.add(node.getTextContent());
            }
        }
        return texts;
    }

    // the installed rows write ON for the ANSI_* and QUOTED_IDENTIFIER options, and FALSE for 
    // ENCRYPTED
    private static Boolean option(final Element element, final String name) {
        if (!element.hasAttribute(name)) {
            return null;
        }
        final var value = element.getAttribute(name);
        return switch (value) {
            case "ON", "TRUE" -> Boolean.TRUE;
            case "OFF", "FALSE" -> Boolean.FALSE;
            default ->
                    throw new IllegalArgumentException("unknown value of " + name + ": " + value);
        };
    }

    private static <T> T map(final String text, final Function<String, T> mapper) {
        return text == null || text.isEmpty() ? null : mapper.apply(text);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public DatabaseLogXmlEvent() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "eventType=" + eventType
               + ",postTime=" + postTime
               + ",spid=" + spid
               + ",serverName=" + serverName
               + ",loginName=" + loginName
               + ",userName=" + userName
               + ",databaseName=" + databaseName
               + ",schemaName=" + schemaName
               + ",objectName=" + objectName
               + ",objectType=" + objectType
               + ",ownerName=" + ownerName
               + ",targetObjectName=" + targetObjectName
               + ",targetObjectType=" + targetObjectType
               + ",primaryXmlIndexName=" + primaryXmlIndexName
               + ",secondaryXmlIndexType=" + secondaryXmlIndexType
               + ",propertyName=" + propertyName
               + ",propertyValue=" + propertyValue
               + ",parameters=" + parameters
               + ",createdConstraintNames=" + createdConstraintNames
               + ",tsqlCommand=" + tsqlCommand
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DatabaseLogXmlEvent that)) {
            return false;
        }
        return Objects.equals(eventType, that.eventType)
               && Objects.equals(postTime, that.postTime)
               && Objects.equals(spid, that.spid)
               && Objects.equals(serverName, that.serverName)
               && Objects.equals(loginName, that.loginName)
               && Objects.equals(userName, that.userName)
               && Objects.equals(databaseName, that.databaseName)
               && Objects.equals(schemaName, that.schemaName)
               && Objects.equals(objectName, that.objectName)
               && Objects.equals(objectType, that.objectType)
               && Objects.equals(ownerName, that.ownerName)
               && Objects.equals(targetObjectName, that.targetObjectName)
               && Objects.equals(targetObjectType, that.targetObjectType)
               && Objects.equals(primaryXmlIndexName, that.primaryXmlIndexName)
               && Objects.equals(secondaryXmlIndexType, that.secondaryXmlIndexType)
               && Objects.equals(propertyName, that.propertyName)
               && Objects.equals(propertyValue, that.propertyValue)
               && Objects.equals(parameters, that.parameters)
               && Objects.equals(createdConstraintNames, that.createdConstraintNames)
               && Objects.equals(tsqlCommand, that.tsqlCommand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, postTime, spid, serverName, loginName, userName,
                            databaseName, schemaName,
                            objectName, objectType, ownerName, targetObjectName, targetObjectType,
                            primaryXmlIndexName, secondaryXmlIndexType, propertyName, propertyValue,
                            parameters,
                            createdConstraintNames, tsqlCommand);
    }

    // ------------------------------------------------------------------------------------------------------- eventType

    /**
     * Returns the {@code EventType} element, such as {@code CREATE_TABLE}; the same as the
     * {@value MappedDatabaseLog#COLUMN_NAME_EVENT} column.
     *
     * @return the type of the event.
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Replaces the type of the event with the specified value.
     *
     * @param eventType new value for the type of the event.
     */
    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }

    // -------------------------------------------------------------------------------------------------------- postTime

    /**
     * Returns the {@code PostTime} element. It is up to 36 milliseconds earlier than the
     * {@value MappedDatabaseLog#COLUMN_NAME_POST_TIME} column, which the trigger fills in with
     * {@code GETDATE()} a moment later.
     *
     * @return the time the event was posted.
     */
    public LocalDateTime getPostTime() {
        return postTime;
    }

    /**
     * Replaces the time the event was posted with the specified value.
     *
     * @param postTime new value for the time the event was posted.
     */
    public void setPostTime(final LocalDateTime postTime) {
        this.postTime = postTime;
    }

    // ------------------------------------------------------------------------------------------------------------ spid

    /**
     * Returns the {@code SPID} element.
     *
     * @return the session id of the event.
     */
    public Integer getSpid() {
        return spid;
    }

    /**
     * Replaces the session id of the event with the specified value.
     *
     * @param spid new value for the session id of the event.
     */
    public void setSpid(final Integer spid) {
        this.spid = spid;
    }

    // ------------------------------------------------------------------------------------------------------ serverName

    /**
     * Returns the {@code ServerName} element.
     *
     * @return the name of the server.
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Replaces the name of the server with the specified value.
     *
     * @param serverName new value for the name of the server.
     */
    public void setServerName(final String serverName) {
        this.serverName = serverName;
    }

    // ------------------------------------------------------------------------------------------------------- loginName

    /**
     * Returns the {@code LoginName} element.
     *
     * @return the name of the login.
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Replaces the name of the login with the specified value.
     *
     * @param loginName new value for the name of the login.
     */
    public void setLoginName(final String loginName) {
        this.loginName = loginName;
    }

    // -------------------------------------------------------------------------------------------------------- userName

    /**
     * Returns the {@code UserName} element.
     *
     * @return the name of the database user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Replaces the name of the database user with the specified value.
     *
     * @param userName new value for the name of the database user.
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    // ---------------------------------------------------------------------------------------------------- databaseName

    /**
     * Returns the {@code DatabaseName} element.
     *
     * @return the name of the database.
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Replaces the name of the database with the specified value.
     *
     * @param databaseName new value for the name of the database.
     */
    public void setDatabaseName(final String databaseName) {
        this.databaseName = databaseName;
    }

    // ------------------------------------------------------------------------------------------------------ schemaName

    /**
     * Returns the {@code SchemaName} element; the same as the
     * {@value MappedDatabaseLog#COLUMN_NAME_SCHEMA} column.
     *
     * @return the name of the schema; empty for a database-level event.
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * Replaces the name of the schema with the specified value.
     *
     * @param schemaName new value for the name of the schema.
     */
    public void setSchemaName(final String schemaName) {
        this.schemaName = schemaName;
    }

    // ------------------------------------------------------------------------------------------------------ objectName

    /**
     * Returns the {@code ObjectName} element; the same as the
     * {@value MappedDatabaseLog#COLUMN_NAME_OBJECT} column.
     *
     * @return the name of the object; {@code null} when the event has none.
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * Replaces the name of the object with the specified value.
     *
     * @param objectName new value for the name of the object.
     */
    public void setObjectName(final String objectName) {
        this.objectName = objectName;
    }

    // ------------------------------------------------------------------------------------------------------ objectType

    /**
     * Returns the {@code ObjectType} element, such as {@code TABLE}.
     *
     * @return the type of the object.
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * Replaces the type of the object with the specified value.
     *
     * @param objectType new value for the type of the object.
     */
    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    // ------------------------------------------------------------------------------------------------------- ownerName

    /**
     * Returns the {@code OwnerName} element, which only an {@code ALTER_AUTHORIZATION_*} event
     * carries.
     *
     * @return the name of the new owner; {@code null} when the event has none.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Replaces the name of the new owner with the specified value.
     *
     * @param ownerName new value for the name of the new owner.
     */
    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }

    // ------------------------------------------------------------------------------------------------ targetObjectName

    /**
     * Returns the {@code TargetObjectName} element.
     *
     * @return the name of the target object; {@code null} when the event has none.
     */
    public String getTargetObjectName() {
        return targetObjectName;
    }

    /**
     * Replaces the name of the target object with the specified value.
     *
     * @param targetObjectName new value for the name of the target object.
     */
    public void setTargetObjectName(final String targetObjectName) {
        this.targetObjectName = targetObjectName;
    }

    // ------------------------------------------------------------------------------------------------ targetObjectType

    /**
     * Returns the {@code TargetObjectType} element.
     *
     * @return the type of the target object; {@code null} when the event has none.
     */
    public String getTargetObjectType() {
        return targetObjectType;
    }

    /**
     * Replaces the type of the target object with the specified value.
     *
     * @param targetObjectType new value for the type of the target object.
     */
    public void setTargetObjectType(final String targetObjectType) {
        this.targetObjectType = targetObjectType;
    }

    // --------------------------------------------------------------------------------------------- primaryXmlIndexName

    /**
     * Returns the {@code PrimaryXMLIndexName} element, which only a {@code CREATE_XML_INDEX} event
     * carries.
     *
     * @return the name of the primary XML index; {@code null} when the event has none.
     */
    public String getPrimaryXmlIndexName() {
        return primaryXmlIndexName;
    }

    /**
     * Replaces the name of the primary XML index with the specified value.
     *
     * @param primaryXmlIndexName new value for the name of the primary XML index.
     */
    public void setPrimaryXmlIndexName(final String primaryXmlIndexName) {
        this.primaryXmlIndexName = primaryXmlIndexName;
    }

    // ------------------------------------------------------------------------------------------- secondaryXmlIndexType

    /**
     * Returns the {@code SecondaryXMLIndexType} element, which only a {@code CREATE_XML_INDEX}
     * event carries.
     *
     * @return the type of the secondary XML index, such as {@code P}; empty for a primary XML
     *         index.
     */
    public String getSecondaryXmlIndexType() {
        return secondaryXmlIndexType;
    }

    /**
     * Replaces the type of the secondary XML index with the specified value.
     *
     * @param secondaryXmlIndexType new value for the type of the secondary XML index.
     */
    public void setSecondaryXmlIndexType(final String secondaryXmlIndexType) {
        this.secondaryXmlIndexType = secondaryXmlIndexType;
    }

    // ---------------------------------------------------------------------------------------------------- propertyName

    /**
     * Returns the {@code PropertyName} element, which an extended-property event carries.
     *
     * @return the name of the extended property; {@code null} when the event has none.
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Replaces the name of the extended property with the specified value.
     *
     * @param propertyName new value for the name of the extended property.
     */
    public void setPropertyName(final String propertyName) {
        this.propertyName = propertyName;
    }

    // --------------------------------------------------------------------------------------------------- propertyValue

    /**
     * Returns the {@code PropertyValue} element, which an extended-property event carries.
     *
     * @return the value of the extended property; {@code null} when the event has none.
     */
    public String getPropertyValue() {
        return propertyValue;
    }

    /**
     * Replaces the value of the extended property with the specified value.
     *
     * @param propertyValue new value for the value of the extended property.
     */
    public void setPropertyValue(final String propertyValue) {
        this.propertyValue = propertyValue;
    }

    // ------------------------------------------------------------------------------------------------------ parameters

    /**
     * Returns the {@code Param} elements of the {@code Parameters} element, which an
     * extended-property event carries: the eight arguments of {@code sp_addextendedproperty}, an
     * empty one standing for {@code NULL}.
     *
     * @return the parameters of the event; {@code null} when the event has none.
     */
    public List<String> getParameters() {
        return parameters;
    }

    /**
     * Replaces the parameters of the event with the specified value.
     *
     * @param parameters new value for the parameters of the event.
     */
    public void setParameters(final List<String> parameters) {
        this.parameters = parameters;
    }

    // ------------------------------------------------------------------------------------------
    // createdConstraintNames

    /**
     * Returns the {@code Name} elements of the {@code AlterTableActionList/Create/Constraints}
     * element, which an {@code ALTER_TABLE} event carries.
     *
     * @return the names of the constraints the statement adds; {@code null} when the event has
     *         none.
     */
    public List<String> getCreatedConstraintNames() {
        return createdConstraintNames;
    }

    /**
     * Replaces the names of the constraints the statement adds with the specified value.
     *
     * @param createdConstraintNames new value for the names of the constraints the statement adds.
     */
    public void setCreatedConstraintNames(final List<String> createdConstraintNames) {
        this.createdConstraintNames = createdConstraintNames;
    }

    // ----------------------------------------------------------------------------------------------------- tsqlCommand

    /**
     * Returns the {@code TSQLCommand} element.
     *
     * @return the statement of the event.
     */
    public TsqlCommand getTsqlCommand() {
        return tsqlCommand;
    }

    /**
     * Replaces the statement of the event with the specified value.
     *
     * @param tsqlCommand new value for the statement of the event.
     */
    public void setTsqlCommand(final TsqlCommand tsqlCommand) {
        this.tsqlCommand = tsqlCommand;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String eventType;

    private LocalDateTime postTime;

    private Integer spid;

    private String serverName;

    private String loginName;

    private String userName;

    private String databaseName;

    private String schemaName;

    private String objectName;

    private String objectType;

    private String ownerName;

    private String targetObjectName;

    private String targetObjectType;

    private String primaryXmlIndexName;

    private String secondaryXmlIndexType;

    private String propertyName;

    private String propertyValue;

    private List<String> parameters;

    private List<String> createdConstraintNames;

    private TsqlCommand tsqlCommand;
}
