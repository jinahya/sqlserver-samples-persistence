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

/**
 * Jakarta Persistence mappings for AdventureWorks, the classic OLTP sample database of SQL Server.
 * <p>
 * The module holds mapped superclasses, one per table and view, and an identifier class per composite key, which an
 * application extends with its own entities. The persistence and validation APIs appear in their public signatures.
 * <p>
 * The packages are opened, not only exported, because a persistence provider and a validation provider reach the
 * mapped superclasses' private fields reflectively.
 */
module com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks {

    // HumanResources.Employee.OrganizationNode; MappedEmployee returns a Heirarchyid
    requires transitive com.github.jinahya.sqlserver.sample.databases.persistence.common;
    requires transitive jakarta.persistence;
    requires transitive jakarta.validation;
    requires java.xml;
    // dbo.DatabaseLog.XmlEvent; EventInstanceStringAttributeConverter extends JaxbAttributeConverter, and throws a
    // JAXBException
    requires transitive com.github.jinahya.persistence.more;
    requires io.github.jinahya.sqlserver.xml.schema.binding.b872ce;
    requires transitive jakarta.xml.bind;
    // Person.AdditionalContactInfoSchemaCollection; MappedEmployeeView returns an AdditionalContactInfo
    requires transitive io.github.jinahya.sqlserver.xml.schema.binding.b7fdbe;
    requires io.github.jinahya.sqlserver.xml.schema.binding.b80e2e;
    requires io.github.jinahya.sqlserver.xml.schema.binding.b2e7ae;

    exports com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.dbo;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.humanresources;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.person;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.production;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.purchasing;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales;

    opens com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.dbo;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.humanresources;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.person;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.production;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.purchasing;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales;
}
