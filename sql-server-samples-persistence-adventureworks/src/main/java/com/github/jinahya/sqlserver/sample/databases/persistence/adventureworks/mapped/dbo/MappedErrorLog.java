package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.dbo;

/*-
 * #%L
 * sql-server-samples-persistence-adventureworks
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

import com.github.jinahya.sqlserver.sample.databases.persistence.common.MappedWithIdentityId;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.ErrorLog} table.
 */
@AttributeOverride(name = MappedWithIdentityId.ATTRIBUTE_NAME_ID_,
                   column = @Column(name = MappedErrorLog.COLUMN_NAME_ERROR_LOG_ID,
                                    nullable = false,
                                    insertable = true,
                                    updatable = false
                   )
)
@MappedSuperclass
public abstract class MappedErrorLog
        extends MappedWithIdentityId {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ErrorLog";

    // ------------------------------------------------------------------------------------------------------ ErrorLogID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_LOG_ID = "ErrorLogID";

    // ------------------------------------------------------------------------------------------------------- ErrorTime

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_TIME = "ErrorTime";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_TIME} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_USER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_USER_NAME = "userName";

    // ----------------------------------------------------------------------------------------------------- ErrorNumber

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_NUMBER = "ErrorNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_NUMBER = "errorNumber";

    // --------------------------------------------------------------------------------------------------- ErrorSeverity

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_SEVERITY = "ErrorSeverity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_SEVERITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_SEVERITY = "errorSeverity";

    // ------------------------------------------------------------------------------------------------------ ErrorState

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_STATE = "ErrorState";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_STATE} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_PROCEDURE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ERROR_PROCEDURE = "errorProcedure";

    // ------------------------------------------------------------------------------------------------------- ErrorLine

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ERROR_LINE = "ErrorLine";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_LINE} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_ERROR_MESSAGE} column. The value is {@value}.
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
               + "errorLogId=" + getErrorLogId()
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

    // ------------------------------------------------------------------------------------------------------ errorLogId

    /**
     * Returns current value of the identifier, which maps the {@value #COLUMN_NAME_ERROR_LOG_ID} column.
     *
     * @return current value of the identifier.
     */
    public Long getErrorLogId() {
        return getId_();
    }

    // ------------------------------------------------------------------------------------------------------- errorTime

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_TIME} attribute.
     */
    public LocalDateTime getErrorTime() {
        return errorTime;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_TIME} attribute with the specified value.
     *
     * @param errorTime new value for {@value #ATTRIBUTE_NAME_ERROR_TIME} attribute.
     */
    public void setErrorTime(final LocalDateTime errorTime) {
        this.errorTime = errorTime;
    }

    // -------------------------------------------------------------------------------------------------------- userName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_USER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_USER_NAME} attribute.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_USER_NAME} attribute with the specified value.
     *
     * @param userName new value for {@value #ATTRIBUTE_NAME_USER_NAME} attribute.
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    // ----------------------------------------------------------------------------------------------------- errorNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_NUMBER} attribute.
     */
    public Integer getErrorNumber() {
        return errorNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_NUMBER} attribute with the specified value.
     *
     * @param errorNumber new value for {@value #ATTRIBUTE_NAME_ERROR_NUMBER} attribute.
     */
    public void setErrorNumber(final Integer errorNumber) {
        this.errorNumber = errorNumber;
    }

    // --------------------------------------------------------------------------------------------------- errorSeverity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_SEVERITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_SEVERITY} attribute.
     */
    public Integer getErrorSeverity() {
        return errorSeverity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_SEVERITY} attribute with the specified value.
     *
     * @param errorSeverity new value for {@value #ATTRIBUTE_NAME_ERROR_SEVERITY} attribute.
     */
    public void setErrorSeverity(final Integer errorSeverity) {
        this.errorSeverity = errorSeverity;
    }

    // ------------------------------------------------------------------------------------------------------ errorState

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_STATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_STATE} attribute.
     */
    public Integer getErrorState() {
        return errorState;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_STATE} attribute with the specified value.
     *
     * @param errorState new value for {@value #ATTRIBUTE_NAME_ERROR_STATE} attribute.
     */
    public void setErrorState(final Integer errorState) {
        this.errorState = errorState;
    }

    // -------------------------------------------------------------------------------------------------- errorProcedure

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_PROCEDURE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_PROCEDURE} attribute.
     */
    public String getErrorProcedure() {
        return errorProcedure;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_PROCEDURE} attribute with the specified value.
     *
     * @param errorProcedure new value for {@value #ATTRIBUTE_NAME_ERROR_PROCEDURE} attribute.
     */
    public void setErrorProcedure(final String errorProcedure) {
        this.errorProcedure = errorProcedure;
    }

    // ------------------------------------------------------------------------------------------------------- errorLine

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_LINE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_LINE} attribute.
     */
    public Integer getErrorLine() {
        return errorLine;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_LINE} attribute with the specified value.
     *
     * @param errorLine new value for {@value #ATTRIBUTE_NAME_ERROR_LINE} attribute.
     */
    public void setErrorLine(final Integer errorLine) {
        this.errorLine = errorLine;
    }

    // ---------------------------------------------------------------------------------------------------- errorMessage

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ERROR_MESSAGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ERROR_MESSAGE} attribute.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ERROR_MESSAGE} attribute with the specified value.
     *
     * @param errorMessage new value for {@value #ATTRIBUTE_NAME_ERROR_MESSAGE} attribute.
     */
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ERROR_TIME,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime errorTime;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_USER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_USER_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_USER_NAME
    )
    private String userName;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ERROR_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer errorNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ERROR_SEVERITY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer errorSeverity;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ERROR_STATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer errorState;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ERROR_PROCEDURE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ERROR_PROCEDURE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ERROR_PROCEDURE
    )
    private String errorProcedure;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ERROR_LINE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer errorLine;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ERROR_MESSAGE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ERROR_MESSAGE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ERROR_MESSAGE
    )
    private String errorMessage;
}

