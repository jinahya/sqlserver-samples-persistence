package com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedJobCandidate;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code HumanResources.JobCandidate} table.
 */
@Entity(name = "HumanResourcesJobCandidate")
@Table(schema = MappedJobCandidate.TABLE_SCHEMA, name = MappedJobCandidate.TABLE_NAME,
       comment = "Résumés submitted to Human Resources by job applicants.")
public class JobCandidate
        extends MappedJobCandidate {

    /**
     * The name of the attribute of the {@code FK_JobCandidate_Employee_BusinessEntityID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected JobCandidate() {
        super();
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code Employee} which the {@code FK_JobCandidate_Employee_BusinessEntityID}
     * foreign key references.
     *
     * @return the {@code Employee} referenced; {@code null} when none is.
     */
    public Employee getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Employee} which the {@code FK_JobCandidate_Employee_BusinessEntityID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param businessEntity the {@code Employee} to reference; {@code null} for none.
     */
    public void setBusinessEntity(final Employee businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(businessEntity == null ? null : businessEntity.getBusinessEntityId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedEmployee.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Employee businessEntity;
}

