package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.production;

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

import com.github.jinahya.sqlserver.sample.databases.persistence.common.Heirarchyid;
import com.github.jinahya.sqlserver.sample.databases.persistence.common.HeirarchyidAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.Document} table.
 * <p>
 * Identifier: the primary key, DocumentNode, includes a hierarchyid column, which no identifier type of Jakarta
 * Persistence holds; the unique rowguid identifies the row instead.
 * <p>
 * The {@value #COLUMN_NAME_OWNER} column is mapped read-only, as a foreign key; the extending entity owns the writable
 * mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedDocument {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Document";

    // ---------------------------------------------------------------------------------------------------- DocumentNode

    /**
     * The name of the {@code hierarchyid, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DOCUMENT_NODE = "DocumentNode";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOCUMENT_NODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DOCUMENT_NODE = "documentNode";

    // --------------------------------------------------------------------------------------------------- DocumentLevel

    /**
     * The name of the {@code smallint, computed as ([DocumentNode].[GetLevel]())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DOCUMENT_LEVEL = "DocumentLevel";

    /**
     * The minimum value of the {@value #COLUMN_NAME_DOCUMENT_LEVEL} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_DOCUMENT_LEVEL = Short.MIN_VALUE;

    /**
     * The maximum value of the {@value #COLUMN_NAME_DOCUMENT_LEVEL} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_DOCUMENT_LEVEL = Short.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOCUMENT_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DOCUMENT_LEVEL = "documentLevel";

    // ----------------------------------------------------------------------------------------------------------- Title

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TITLE = "Title";

    /**
     * The length of the {@value #COLUMN_NAME_TITLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TITLE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TITLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TITLE = "title";

    // ----------------------------------------------------------------------------------------------------------- Owner

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OWNER = "Owner";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OWNER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_OWNER = "owner";

    // ------------------------------------------------------------------------------------------------------ FolderFlag

    /**
     * The name of the {@code bit, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FOLDER_FLAG = "FolderFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FOLDER_FLAG} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FOLDER_FLAG = "folderFlag";

    // -------------------------------------------------------------------------------------------------------- FileName

    /**
     * The name of the {@code nvarchar(400), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FILE_NAME = "FileName";

    /**
     * The length of the {@value #COLUMN_NAME_FILE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FILE_NAME = 400;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FILE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FILE_NAME = "fileName";

    // --------------------------------------------------------------------------------------------------- FileExtension

    /**
     * The name of the {@code nvarchar(8), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FILE_EXTENSION = "FileExtension";

    /**
     * The length of the {@value #COLUMN_NAME_FILE_EXTENSION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FILE_EXTENSION = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FILE_EXTENSION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FILE_EXTENSION = "fileExtension";

    // -------------------------------------------------------------------------------------------------------- Revision

    /**
     * The name of the {@code nchar(5), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVISION = "Revision";

    /**
     * The length of the {@value #COLUMN_NAME_REVISION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REVISION = 5;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVISION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVISION = "revision";

    // ---------------------------------------------------------------------------------------------------- ChangeNumber

    /**
     * The name of the {@code int, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANGE_NUMBER = "ChangeNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANGE_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANGE_NUMBER = "changeNumber";

    // ---------------------------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code tinyint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATUS = "Status";

    /**
     * The minimum value of the {@value #COLUMN_NAME_STATUS} column, as the {@code CK_Document_Status} check
     * constraint ({@code [Status]>=(1)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MIN_STATUS = +1;

    /**
     * The maximum value of the {@value #COLUMN_NAME_STATUS} column, as the {@code CK_Document_Status} check
     * constraint ({@code [Status]<=(3)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MAX_STATUS = +3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATUS = "status";

    // ------------------------------------------------------------------------------------------------- DocumentSummary

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DOCUMENT_SUMMARY = "DocumentSummary";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOCUMENT_SUMMARY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DOCUMENT_SUMMARY = "documentSummary";

    // -------------------------------------------------------------------------------------------------------- Document

    /**
     * The name of the {@code varbinary(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DOCUMENT = "Document";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOCUMENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DOCUMENT = "document";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier(36), DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The length of the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ROWGUID = 36;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedDocument() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "documentNode=" + documentNode
               + ",documentLevel=" + documentLevel
               + ",title=" + title
               + ",owner=" + owner
               + ",folderFlag=" + folderFlag
               + ",fileName=" + fileName
               + ",fileExtension=" + fileExtension
               + ",revision=" + revision
               + ",changeNumber=" + changeNumber
               + ",status=" + status
               + ",documentSummary=" + documentSummary
               + ",document=" + (this.document == null ? null : this.document.length + " bytes")
               + ",rowguid=" + rowguid
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
        if (!(obj instanceof MappedDocument that)) {
            return false;
        }
        return Objects.equals(rowguid, that.rowguid);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(rowguid);
    }

    // ---------------------------------------------------------------------------------------------------- documentNode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOCUMENT_NODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOCUMENT_NODE} attribute.
     */
    public Heirarchyid getDocumentNode() {
        return documentNode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOCUMENT_NODE} attribute with the specified value.
     *
     * @param documentNode new value for {@value #ATTRIBUTE_NAME_DOCUMENT_NODE} attribute.
     */
    public void setDocumentNode(final Heirarchyid documentNode) {
        this.documentNode = documentNode;
    }

    // --------------------------------------------------------------------------------------------------- documentLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOCUMENT_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOCUMENT_LEVEL} attribute.
     */
    public Integer getDocumentLevel() {
        return documentLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOCUMENT_LEVEL} attribute with the specified value. The
     * database writes the column; this is for the provider and the extending class only.
     *
     * @param documentLevel new value for {@value #ATTRIBUTE_NAME_DOCUMENT_LEVEL} attribute.
     */
    protected void setDocumentLevel(final Integer documentLevel) {
        this.documentLevel = documentLevel;
    }

    // ----------------------------------------------------------------------------------------------------------- title

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TITLE} attribute.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TITLE} attribute with the specified value.
     *
     * @param title new value for {@value #ATTRIBUTE_NAME_TITLE} attribute.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    // ----------------------------------------------------------------------------------------------------------- owner

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OWNER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OWNER} attribute.
     */
    public Integer getOwner() {
        return owner;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OWNER} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param owner new value for {@value #ATTRIBUTE_NAME_OWNER} attribute.
     */
    protected void setOwner(final Integer owner) {
        this.owner = owner;
    }

    // ------------------------------------------------------------------------------------------------------ folderFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FOLDER_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FOLDER_FLAG} attribute.
     */
    public Boolean getFolderFlag() {
        return folderFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FOLDER_FLAG} attribute with the specified value.
     *
     * @param folderFlag new value for {@value #ATTRIBUTE_NAME_FOLDER_FLAG} attribute.
     */
    public void setFolderFlag(final Boolean folderFlag) {
        this.folderFlag = folderFlag;
    }

    // -------------------------------------------------------------------------------------------------------- fileName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FILE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FILE_NAME} attribute.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FILE_NAME} attribute with the specified value.
     *
     * @param fileName new value for {@value #ATTRIBUTE_NAME_FILE_NAME} attribute.
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    // --------------------------------------------------------------------------------------------------- fileExtension

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FILE_EXTENSION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FILE_EXTENSION} attribute.
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FILE_EXTENSION} attribute with the specified value.
     *
     * @param fileExtension new value for {@value #ATTRIBUTE_NAME_FILE_EXTENSION} attribute.
     */
    public void setFileExtension(final String fileExtension) {
        this.fileExtension = fileExtension;
    }

    // -------------------------------------------------------------------------------------------------------- revision

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVISION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVISION} attribute.
     */
    public String getRevision() {
        return revision;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVISION} attribute with the specified value.
     *
     * @param revision new value for {@value #ATTRIBUTE_NAME_REVISION} attribute.
     */
    public void setRevision(final String revision) {
        this.revision = revision;
    }

    // ---------------------------------------------------------------------------------------------------- changeNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANGE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANGE_NUMBER} attribute.
     */
    public Integer getChangeNumber() {
        return changeNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANGE_NUMBER} attribute with the specified value.
     *
     * @param changeNumber new value for {@value #ATTRIBUTE_NAME_CHANGE_NUMBER} attribute.
     */
    public void setChangeNumber(final Integer changeNumber) {
        this.changeNumber = changeNumber;
    }

    // ---------------------------------------------------------------------------------------------------------- status

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATUS} attribute with the specified value.
     *
     * @param status new value for {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    // ------------------------------------------------------------------------------------------------- documentSummary

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOCUMENT_SUMMARY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOCUMENT_SUMMARY} attribute.
     */
    public String getDocumentSummary() {
        return documentSummary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOCUMENT_SUMMARY} attribute with the specified value.
     *
     * @param documentSummary new value for {@value #ATTRIBUTE_NAME_DOCUMENT_SUMMARY} attribute.
     */
    public void setDocumentSummary(final String documentSummary) {
        this.documentSummary = documentSummary;
    }

    // -------------------------------------------------------------------------------------------------------- document

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOCUMENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOCUMENT} attribute.
     */
    public byte[] getDocument() {
        return document;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOCUMENT} attribute with the specified value.
     *
     * @param document new value for {@value #ATTRIBUTE_NAME_DOCUMENT} attribute.
     */
    public void setDocument(final byte[] document) {
        this.document = document;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public String getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(final String rowguid) {
        this.rowguid = rowguid;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

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
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = HeirarchyidAttributeConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DOCUMENT_NODE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Heirarchyid documentNode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Max(COLUMN_MAX_DOCUMENT_LEVEL)
    @Min(COLUMN_MIN_DOCUMENT_LEVEL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DOCUMENT_LEVEL,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer documentLevel;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_TITLE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TITLE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TITLE
    )
    private String title;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_OWNER,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer owner;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FOLDER_FLAG,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean folderFlag;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_FILE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FILE_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FILE_NAME
    )
    private String fileName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_FILE_EXTENSION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FILE_EXTENSION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FILE_EXTENSION
    )
    private String fileExtension;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_REVISION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REVISION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REVISION
    )
    private String revision;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANGE_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer changeNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_Document_Status: ([Status]>=(1) AND [Status]<=(3))
    @Max(COLUMN_MAX_STATUS)
    @Min(COLUMN_MIN_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer status;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DOCUMENT_SUMMARY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String documentSummary;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DOCUMENT,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] document;

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Size(max = COLUMN_LENGTH_ROWGUID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_ROWGUID
    )
    private String rowguid;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

