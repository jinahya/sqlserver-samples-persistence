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
 * The {@value #COLUMN_NAME_OWNER} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedDocument {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOCUMENT_NODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DOCUMENT_NODE = "documentNode";

    // --------------------------------------------------------------------------------------------------- DocumentLevel

    /**
     * The name of the {@code smallint, computed as ([DocumentNode].[GetLevel]())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DOCUMENT_LEVEL = "DocumentLevel";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOCUMENT_LEVEL} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_TITLE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TITLE = "title";

    // ----------------------------------------------------------------------------------------------------------- Owner

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OWNER = "Owner";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OWNER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_OWNER = "owner";

    // ------------------------------------------------------------------------------------------------------ FolderFlag

    /**
     * The name of the {@code bit, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FOLDER_FLAG = "FolderFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FOLDER_FLAG} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_FILE_NAME} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_FILE_EXTENSION} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVISION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVISION = "revision";

    // ---------------------------------------------------------------------------------------------------- ChangeNumber

    /**
     * The name of the {@code int, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANGE_NUMBER = "ChangeNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANGE_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANGE_NUMBER = "changeNumber";

    // ---------------------------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code tinyint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATUS = "Status";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATUS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATUS = "status";

    // ------------------------------------------------------------------------------------------------- DocumentSummary

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DOCUMENT_SUMMARY = "DocumentSummary";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOCUMENT_SUMMARY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DOCUMENT_SUMMARY = "documentSummary";

    // -------------------------------------------------------------------------------------------------------- Document

    /**
     * The name of the {@code varbinary(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DOCUMENT = "Document";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOCUMENT} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
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
    protected MappedDocument() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "documentNode=" + (this.documentNode == null ? null : this.documentNode.length + " bytes")
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
    @Nonnull
    public byte[] getDocumentNode() {
        return documentNode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOCUMENT_NODE} attribute with the specified value.
     *
     * @param documentNode new value for {@value #ATTRIBUTE_NAME_DOCUMENT_NODE} attribute.
     */
    public void setDocumentNode(@Nonnull final byte[] documentNode) {
        this.documentNode = documentNode;
    }

    // --------------------------------------------------------------------------------------------------- documentLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOCUMENT_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOCUMENT_LEVEL} attribute.
     */
    @Nullable
    public Short getDocumentLevel() {
        return documentLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOCUMENT_LEVEL} attribute with the specified value. The
     * database writes the column; this is for the provider and the extending class only.
     *
     * @param documentLevel new value for {@value #ATTRIBUTE_NAME_DOCUMENT_LEVEL} attribute.
     */
    protected void setDocumentLevel(@Nullable final Short documentLevel) {
        this.documentLevel = documentLevel;
    }

    // ----------------------------------------------------------------------------------------------------------- title

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TITLE} attribute.
     */
    @Nonnull
    public String getTitle() {
        return title;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TITLE} attribute with the specified value.
     *
     * @param title new value for {@value #ATTRIBUTE_NAME_TITLE} attribute.
     */
    public void setTitle(@Nonnull final String title) {
        this.title = title;
    }

    // ----------------------------------------------------------------------------------------------------------- owner

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OWNER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OWNER} attribute.
     */
    @Nonnull
    public Integer getOwner() {
        return owner;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OWNER} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param owner new value for {@value #ATTRIBUTE_NAME_OWNER} attribute.
     */
    protected void setOwner(@Nonnull final Integer owner) {
        this.owner = owner;
    }

    // ------------------------------------------------------------------------------------------------------ folderFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FOLDER_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FOLDER_FLAG} attribute.
     */
    @Nonnull
    public Boolean getFolderFlag() {
        return folderFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FOLDER_FLAG} attribute with the specified value.
     *
     * @param folderFlag new value for {@value #ATTRIBUTE_NAME_FOLDER_FLAG} attribute.
     */
    public void setFolderFlag(@Nonnull final Boolean folderFlag) {
        this.folderFlag = folderFlag;
    }

    // -------------------------------------------------------------------------------------------------------- fileName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FILE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FILE_NAME} attribute.
     */
    @Nonnull
    public String getFileName() {
        return fileName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FILE_NAME} attribute with the specified value.
     *
     * @param fileName new value for {@value #ATTRIBUTE_NAME_FILE_NAME} attribute.
     */
    public void setFileName(@Nonnull final String fileName) {
        this.fileName = fileName;
    }

    // --------------------------------------------------------------------------------------------------- fileExtension

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FILE_EXTENSION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FILE_EXTENSION} attribute.
     */
    @Nonnull
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FILE_EXTENSION} attribute with the specified value.
     *
     * @param fileExtension new value for {@value #ATTRIBUTE_NAME_FILE_EXTENSION} attribute.
     */
    public void setFileExtension(@Nonnull final String fileExtension) {
        this.fileExtension = fileExtension;
    }

    // -------------------------------------------------------------------------------------------------------- revision

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVISION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVISION} attribute.
     */
    @Nonnull
    public String getRevision() {
        return revision;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVISION} attribute with the specified value.
     *
     * @param revision new value for {@value #ATTRIBUTE_NAME_REVISION} attribute.
     */
    public void setRevision(@Nonnull final String revision) {
        this.revision = revision;
    }

    // ---------------------------------------------------------------------------------------------------- changeNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANGE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANGE_NUMBER} attribute.
     */
    @Nonnull
    public Integer getChangeNumber() {
        return changeNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANGE_NUMBER} attribute with the specified value.
     *
     * @param changeNumber new value for {@value #ATTRIBUTE_NAME_CHANGE_NUMBER} attribute.
     */
    public void setChangeNumber(@Nonnull final Integer changeNumber) {
        this.changeNumber = changeNumber;
    }

    // ---------------------------------------------------------------------------------------------------------- status

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    @Nonnull
    public Short getStatus() {
        return status;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATUS} attribute with the specified value.
     *
     * @param status new value for {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public void setStatus(@Nonnull final Short status) {
        this.status = status;
    }

    // ------------------------------------------------------------------------------------------------- documentSummary

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOCUMENT_SUMMARY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOCUMENT_SUMMARY} attribute.
     */
    @Nullable
    public String getDocumentSummary() {
        return documentSummary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOCUMENT_SUMMARY} attribute with the specified value.
     *
     * @param documentSummary new value for {@value #ATTRIBUTE_NAME_DOCUMENT_SUMMARY} attribute.
     */
    public void setDocumentSummary(@Nullable final String documentSummary) {
        this.documentSummary = documentSummary;
    }

    // -------------------------------------------------------------------------------------------------------- document

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOCUMENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOCUMENT} attribute.
     */
    @Nullable
    public byte[] getDocument() {
        return document;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOCUMENT} attribute with the specified value.
     *
     * @param document new value for {@value #ATTRIBUTE_NAME_DOCUMENT} attribute.
     */
    public void setDocument(@Nullable final byte[] document) {
        this.document = document;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    @Nonnull
    public String getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(@Nonnull final String rowguid) {
        this.rowguid = rowguid;
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
    public void setModifiedDate(@Nonnull final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_DOCUMENT_NODE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private byte[] documentNode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_DOCUMENT_LEVEL,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Short documentLevel;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_TITLE)
    @NotNull
    @Column(name = COLUMN_NAME_TITLE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TITLE
    )
    private String title;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_OWNER,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer owner;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_FOLDER_FLAG,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean folderFlag;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_FILE_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_FILE_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FILE_NAME
    )
    private String fileName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_FILE_EXTENSION)
    @NotNull
    @Column(name = COLUMN_NAME_FILE_EXTENSION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FILE_EXTENSION
    )
    private String fileExtension;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_REVISION)
    @NotNull
    @Column(name = COLUMN_NAME_REVISION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REVISION
    )
    private String revision;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_CHANGE_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer changeNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(255)
    @Min(0)
    @NotNull
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short status;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_DOCUMENT_SUMMARY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String documentSummary;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_DOCUMENT,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] document;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
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
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

