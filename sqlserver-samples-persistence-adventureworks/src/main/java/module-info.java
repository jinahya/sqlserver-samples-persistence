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

/**
 * Jakarta Persistence mappings for AdventureWorks, the classic OLTP sample database of SQL Server.
 * <p>
 * The module holds mapped superclasses, one per table and view, and an identifier class per
 * composite key, which an application extends with its own entities. The persistence and validation
 * APIs appear in their public signatures.
 * <p>
 * The packages are opened, not only exported, because a persistence provider and a validation
 * provider reach the mapped superclasses' private fields reflectively.
 */
module com.github.jinahya.sqlserver.sample.persistence.adventureworks {

    // HumanResources.Employee.OrganizationNode; MappedEmployee returns a Heirarchyid
    requires transitive com.github.jinahya.sqlserver.sample.persistence.common;
    requires transitive jakarta.persistence;
    requires transitive jakarta.validation;
    requires java.xml;
    // dbo.DatabaseLog.XmlEvent; EventInstanceStringAttributeConverter extends
    // JaxbAttributeConverter, and throws a
    // JAXBException
    requires transitive com.github.jinahya.persistence.more;
    requires io.github.jinahya.sqlserver.xml.schema.binding.b872ce;
    requires transitive jakarta.xml.bind;
    // Person.AdditionalContactInfoSchemaCollection; MappedEmployeeView returns an
    // AdditionalContactInfo
    requires transitive io.github.jinahya.sqlserver.xml.schema.binding.b7fdbe;
    requires io.github.jinahya.sqlserver.xml.schema.binding.b80e2e;
    requires io.github.jinahya.sqlserver.xml.schema.binding.b2e7ae;
    // Person.IndividualSurveySchemaCollection; MappedPerson returns an IndividualSurvey
    requires transitive io.github.jinahya.sqlserver.xml.schema.binding.b9c1be;
    // Production.ProductDescriptionSchemaCollection; the Warranty and the Maintenance of a ProductDescription
    requires transitive io.github.jinahya.sqlserver.xml.schema.binding.b4347e;
    // Production.ProductDescriptionSchemaCollection; MappedProductModel returns a ProductDescription
    requires transitive io.github.jinahya.sqlserver.xml.schema.binding.b0fd0e;
    // HumanResources.HRResumeSchemaCollection; MappedJobCandidate returns a ResumeType
    requires transitive io.github.jinahya.sqlserver.xml.schema.binding.b3da3e;
    // Sales.StoreSurveySchemaCollection; MappedStore returns a StoreSurvey
    requires transitive io.github.jinahya.sqlserver.xml.schema.binding.b95f5e;
    // Person.Person.Demographics, which the iuPerson trigger writes; MappedPerson reads it back with @Generated
    requires static org.hibernate.orm.core;

    exports com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped;
    exports com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.dbo;
    exports com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources;
    exports com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person;
    exports com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production;
    exports com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing;
    exports com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;

    opens com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped;
    opens com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.dbo;
    opens com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources;
    opens com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person;
    opens com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production;
    opens com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing;
    opens com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;
}
