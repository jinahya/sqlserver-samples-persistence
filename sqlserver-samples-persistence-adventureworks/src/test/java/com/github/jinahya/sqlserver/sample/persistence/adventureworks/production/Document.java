package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedDocument;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Production.Document} table.
 */
@Entity(name = "ProductionDocument")
@Table(schema = MappedDocument.TABLE_SCHEMA,
       name = MappedDocument.TABLE_NAME,
       comment = "Product maintenance documents.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_Document_DocumentLevel_DocumentNode",
                       columnNames = {
                               Document.COLUMN_NAME_DOCUMENT_LEVEL,
                               Document.COLUMN_NAME_DOCUMENT_NODE
                       }
               ),
               @UniqueConstraint(
                       name = "AK_Document_rowguid",
                       columnNames = {
                               Document.COLUMN_NAME_ROWGUID
                       }
               ),
               @UniqueConstraint(
                       columnNames = {
                               Document.COLUMN_NAME_ROWGUID
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_Document_Status",
                       constraint = "([Status]>=(1) AND [Status]<=(3))"
               )
       }
)
public class Document
        extends MappedDocument {

    /**
     * The name of the attribute of the {@code FK_Document_Employee_Owner} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_OWNER_EMPLOYEE = "ownerEmployee";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Document() {
        super();
    }

    // ------------------------------------------------------------------------------- ownerEmployee

    /**
     * Returns the {@code Employee} which the {@code FK_Document_Employee_Owner} foreign key
     * references.
     *
     * @return the {@code Employee} referenced; {@code null} when none is.
     */
    public Employee getOwnerEmployee() {
        return ownerEmployee;
    }

    /**
     * Replaces the {@code Employee} which the {@code FK_Document_Employee_Owner} foreign key
     * references, and the value of the column it consists of.
     *
     * @param ownerEmployee the {@code Employee} to reference; {@code null} for none.
     */
    public void setOwnerEmployee(final Employee ownerEmployee) {
        this.ownerEmployee = ownerEmployee;
        setOwner(ownerEmployee == null ? null : ownerEmployee.getBusinessEntityId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_OWNER,
                referencedColumnName = MappedEmployee.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Employee ownerEmployee;
}

