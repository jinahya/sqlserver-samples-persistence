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
 * A mapped superclass which holds the mappings of the {@code dbo.ErrorLog} table.
 */
@MappedSuperclass
public abstract class MappedErrorLog {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ErrorLog";

    // ------------------------------------------------------------------------------------------------------ ErrorLogID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_LOG_ID = "ErrorLogID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_LOG_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_LOG_ID = "errorLogId";

    // ------------------------------------------------------------------------------------------------------- ErrorTime

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_TIME = "ErrorTime";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_TIME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_TIME = "errorTime";

    // -------------------------------------------------------------------------------------------------------- UserName

    /**
     * The name of the {@code sysname(128), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_USER_NAME = "UserName";

    /**
     * The length of the {@value #COLUMN_NAME_USER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_USER_NAME = 128;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_USER_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_USER_NAME = "userName";

    // ----------------------------------------------------------------------------------------------------- ErrorNumber

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_NUMBER = "ErrorNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_NUMBER = "errorNumber";

    // --------------------------------------------------------------------------------------------------- ErrorSeverity

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_SEVERITY = "ErrorSeverity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_SEVERITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_SEVERITY = "errorSeverity";

    // ------------------------------------------------------------------------------------------------------ ErrorState

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_STATE = "ErrorState";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_STATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_STATE = "errorState";

    // -------------------------------------------------------------------------------------------------- ErrorProcedure

    /**
     * The name of the {@code nvarchar(126)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_PROCEDURE = "ErrorProcedure";

    /**
     * The length of the {@value #COLUMN_NAME_ERROR_PROCEDURE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ERROR_PROCEDURE = 126;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_PROCEDURE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_PROCEDURE = "errorProcedure";

    // ------------------------------------------------------------------------------------------------------- ErrorLine

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_LINE = "ErrorLine";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_LINE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_LINE = "errorLine";

    // ---------------------------------------------------------------------------------------------------- ErrorMessage

    /**
     * The name of the {@code nvarchar(4000), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_MESSAGE = "ErrorMessage";

    /**
     * The length of the {@value #COLUMN_NAME_ERROR_MESSAGE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ERROR_MESSAGE = 4000;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_MESSAGE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_MESSAGE = "errorMessage";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedErrorLog() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "errorLogId=" + errorLogId
               + ",errorTime=" + errorTime
               + ",userName=" + userName
               + ",errorNumber=" + errorNumber
               + ",errorSeverity=" + errorSeverity
               + ",errorState=" + errorState
               + ",errorProcedure=" + errorProcedure
               + ",errorLine=" + errorLine
               + ",errorMessage=" + errorMessage
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
        if (!(obj instanceof MappedErrorLog that)) {
            return false;
        }
        return Objects.equals(errorLogId, that.errorLogId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(errorLogId);
    }

    // ------------------------------------------------------------------------------------------------------ errorLogId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_LOG_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_LOG_ID} attribute.
     */
    @Nullable
    public Integer getErrorLogId() {
        return errorLogId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_LOG_ID} attribute with the specified value.
     *
     * @param errorLogId new value for {@value #ATTRIBUTE_NAME_ERROR_LOG_ID} attribute.
     */
    protected void setErrorLogId(@Nullable final Integer errorLogId) {
        this.errorLogId = errorLogId;
    }

    // ------------------------------------------------------------------------------------------------------- errorTime

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_TIME} attribute.
     */
    @Nonnull
    public LocalDateTime getErrorTime() {
        return errorTime;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_TIME} attribute with the specified value.
     *
     * @param errorTime new value for {@value #ATTRIBUTE_NAME_ERROR_TIME} attribute.
     */
    public void setErrorTime(@Nonnull final LocalDateTime errorTime) {
        this.errorTime = errorTime;
    }

    // -------------------------------------------------------------------------------------------------------- userName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_USER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_USER_NAME} attribute.
     */
    @Nonnull
    public String getUserName() {
        return userName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_USER_NAME} attribute with the specified value.
     *
     * @param userName new value for {@value #ATTRIBUTE_NAME_USER_NAME} attribute.
     */
    public void setUserName(@Nonnull final String userName) {
        this.userName = userName;
    }

    // ----------------------------------------------------------------------------------------------------- errorNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_NUMBER} attribute.
     */
    @Nonnull
    public Integer getErrorNumber() {
        return errorNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_NUMBER} attribute with the specified value.
     *
     * @param errorNumber new value for {@value #ATTRIBUTE_NAME_ERROR_NUMBER} attribute.
     */
    public void setErrorNumber(@Nonnull final Integer errorNumber) {
        this.errorNumber = errorNumber;
    }

    // --------------------------------------------------------------------------------------------------- errorSeverity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_SEVERITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_SEVERITY} attribute.
     */
    @Nullable
    public Integer getErrorSeverity() {
        return errorSeverity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_SEVERITY} attribute with the specified value.
     *
     * @param errorSeverity new value for {@value #ATTRIBUTE_NAME_ERROR_SEVERITY} attribute.
     */
    public void setErrorSeverity(@Nullable final Integer errorSeverity) {
        this.errorSeverity = errorSeverity;
    }

    // ------------------------------------------------------------------------------------------------------ errorState

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_STATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_STATE} attribute.
     */
    @Nullable
    public Integer getErrorState() {
        return errorState;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_STATE} attribute with the specified value.
     *
     * @param errorState new value for {@value #ATTRIBUTE_NAME_ERROR_STATE} attribute.
     */
    public void setErrorState(@Nullable final Integer errorState) {
        this.errorState = errorState;
    }

    // -------------------------------------------------------------------------------------------------- errorProcedure

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_PROCEDURE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_PROCEDURE} attribute.
     */
    @Nullable
    public String getErrorProcedure() {
        return errorProcedure;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_PROCEDURE} attribute with the specified value.
     *
     * @param errorProcedure new value for {@value #ATTRIBUTE_NAME_ERROR_PROCEDURE} attribute.
     */
    public void setErrorProcedure(@Nullable final String errorProcedure) {
        this.errorProcedure = errorProcedure;
    }

    // ------------------------------------------------------------------------------------------------------- errorLine

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_LINE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_LINE} attribute.
     */
    @Nullable
    public Integer getErrorLine() {
        return errorLine;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_LINE} attribute with the specified value.
     *
     * @param errorLine new value for {@value #ATTRIBUTE_NAME_ERROR_LINE} attribute.
     */
    public void setErrorLine(@Nullable final Integer errorLine) {
        this.errorLine = errorLine;
    }

    // ---------------------------------------------------------------------------------------------------- errorMessage

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_MESSAGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_MESSAGE} attribute.
     */
    @Nonnull
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_MESSAGE} attribute with the specified value.
     *
     * @param errorMessage new value for {@value #ATTRIBUTE_NAME_ERROR_MESSAGE} attribute.
     */
    public void setErrorMessage(@Nonnull final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ERROR_LOG_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer errorLogId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_ERROR_TIME,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime errorTime;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_USER_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_USER_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_USER_NAME
    )
    private String userName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_ERROR_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer errorNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ERROR_SEVERITY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer errorSeverity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ERROR_STATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer errorState;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_ERROR_PROCEDURE)
    @Column(name = COLUMN_NAME_ERROR_PROCEDURE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ERROR_PROCEDURE
    )
    private String errorProcedure;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ERROR_LINE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer errorLine;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_ERROR_MESSAGE)
    @NotNull
    @Column(name = COLUMN_NAME_ERROR_MESSAGE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ERROR_MESSAGE
    )
    private String errorMessage;
}

