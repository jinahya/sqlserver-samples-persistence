package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Department_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeeDepartmentHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeeDepartmentHistory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeePayHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.EmployeePayHistory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Address;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Address_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.CountryRegion_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.StateProvince_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductCategory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductInventory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductInventory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductSubcategory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ScrapReason_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.WorkOrder;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.WorkOrder_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.ProductVendor;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.ProductVendor_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderDetail_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderHeader_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.Vendor_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.Customer;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.Customer_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.PersonDemographicsView;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.PersonDemographicsView_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderDetail_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesPerson;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesPerson_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesTerritory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SpecialOfferProduct_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SpecialOffer_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.JoinType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The exercises of {@code EXERCISES.asciidoc}, answered in the query language and with the criteria API, each against
 * the rows its SQL, in {@code src/test/sql/exercises}, returns from the installed database.
 * <p>
 * The exercises which need what only SQL Server's SQL has -- the methods of {@code hierarchyid}, a recursive common
 * table expression, {@code PIVOT}, a window function -- or which read a class that is no entity, are answered with a
 * native query instead, which is the exercise's SQL.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class AdventureWorks_Exercises_PersistenceIT {

    // ---------------------------------------------------------------------------------------------
    private static <R> R apply(final Function<? super EntityManager, ? extends R> function) {
        return ___Persistence_TestUtils.applyInTransactionAndRollback(function);
    }

    /**
     * Returns a row's values as the SQL's output shows them, separated by {@code " | "}; a decimal without its trailing
     * zeros, a {@code null} as {@code NULL}.
     */
    private static String row(final Object... values) {
        return Arrays.stream(values)
                .map(v -> switch (v) {
                    case null -> "NULL";
                    case BigDecimal d -> d.stripTrailingZeros().toPlainString();
                    case byte[] b -> b.length + " bytes";
                    default -> String.valueOf(v);
                })
                .collect(Collectors.joining(" | "));
    }

    private static List<String> rows(final List<Object[]> found) {
        return found.stream().map(r -> row((Object[]) r)).toList();
    }

    // ------------------------------------------------------------------------------ HumanResources
    @DisplayName("AW_HUMANRESOURCES_B_01: the current employees of each department")
    @Nested
    class AW_HUMANRESOURCES_B_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "Production | Manufacturing | 179",
                    "Sales | Sales and Marketing | 18",
                    "Purchasing | Inventory Management | 12",
                    "Finance | Executive General and Administration | 10",
                    "Information Services | Executive General and Administration | 10",
                    "Marketing | Sales and Marketing | 9",
                    "Facilities and Maintenance | Executive General and Administration | 7",
                    "Engineering | Research and Development | 6",
                    "Human Resources | Executive General and Administration | 6",
                    "Production Control | Manufacturing | 6",
                    "Quality Assurance | Quality Assurance | 6",
                    "Shipping and Receiving | Inventory Management | 6",
                    "Document Control | Quality Assurance | 5",
                    "Research and Development | Research and Development | 4",
                    "Tool Design | Research and Development | 4",
                    "Executive | Executive General and Administration | 2"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT d.name, d.groupName, COUNT(h)
                            FROM HumanResourcesEmployeeDepartmentHistory h
                                     JOIN h.department d
                            WHERE h.endDate IS NULL
                            GROUP BY d.id_, d.name, d.groupName
                            ORDER BY COUNT(h) DESC, d.name""",
                    Object[].class
            ).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var h = query.from(EmployeeDepartmentHistory.class);
                final var d = h.join(EmployeeDepartmentHistory_.department);
                final var employees = builder.count(h);
                query.multiselect(d.get(Department_.name), d.get(Department_.groupName), employees);
                query.where(builder.isNull(h.get(EmployeeDepartmentHistory_.endDate)));
                query.groupBy(d.get(Department_.id_), d.get(Department_.name), d.get(Department_.groupName));
                query.orderBy(builder.desc(employees), builder.asc(d.get(Department_.name)));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }

    @DisplayName("AW_HUMANRESOURCES_B_02: the employees at each level of the organization")
    @Nested
    class AW_HUMANRESOURCES_B_02_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "NULL | 1",
                    "1 | 6",
                    "2 | 27",
                    "3 | 66",
                    "4 | 190"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT e.organizationLevel, COUNT(e)
                            FROM HumanResourcesEmployee e
                            GROUP BY e.organizationLevel
                            ORDER BY e.organizationLevel""",
                    Object[].class
            ).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var e = query.from(Employee.class);
                final var level = e.get(Employee_.organizationLevel);
                query.multiselect(level, builder.count(e));
                query.groupBy(level);
                query.orderBy(builder.asc(level));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }

    @DisplayName("AW_HUMANRESOURCES_I_01: the ten highest current hourly rates")
    @Nested
    class AW_HUMANRESOURCES_I_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "Ken | Sánchez | Chief Executive Officer | 125.5 | 2009-01-14T00:00",
                    "James | Hamilton | Vice President of Production | 84.1346 | 2009-02-03T00:00",
                    "Brian | Welcker | Vice President of Sales | 72.1154 | 2011-02-15T00:00",
                    "Terri | Duffy | Vice President of Engineering | 63.4615 | 2008-01-31T00:00",
                    "Laura | Norman | Chief Financial Officer | 60.0962 | 2012-01-29T00:00",
                    "Dylan | Miller | Research and Development Manager | 50.4808 | 2009-02-08T00:00",
                    "Jean | Trenary | Information Services Manager | 50.4808 | 2008-12-11T00:00",
                    "Stephen | Jiang | North American Sales Manager | 48.101 | 2011-01-04T00:00",
                    "Syed | Abbas | Pacific Sales Manager | 48.101 | 2013-03-14T00:00",
                    "Amy | Alberts | European Sales Manager | 48.101 | 2012-04-16T00:00"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT p.firstName, p.lastName, e.jobTitle, ph.rate, ph.rateChangeDate
                            FROM HumanResourcesEmployeePayHistory ph
                                     JOIN ph.businessEntity e
                                     JOIN e.businessEntity p
                            WHERE ph.rateChangeDate = (SELECT MAX(ph2.rateChangeDate)
                                                       FROM HumanResourcesEmployeePayHistory ph2
                                                       WHERE ph2.businessEntityId = ph.businessEntityId)
                            ORDER BY ph.rate DESC, e.businessEntityId""",
                    Object[].class
            ).setMaxResults(10).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var ph = query.from(EmployeePayHistory.class);
                final var e = ph.join(EmployeePayHistory_.businessEntity);
                final var p = e.join(Employee_.businessEntity);
                final var latest = query.subquery(LocalDateTime.class);
                final var ph2 = latest.from(EmployeePayHistory.class);
                latest.select(builder.greatest(ph2.get(EmployeePayHistory_.rateChangeDate)));
                latest.where(builder.equal(ph2.get(EmployeePayHistory_.businessEntityId),
                                           ph.get(EmployeePayHistory_.businessEntityId)));
                query.multiselect(p.get(Person_.firstName), p.get(Person_.lastName), e.get(Employee_.jobTitle),
                                  ph.get(EmployeePayHistory_.rate), ph.get(EmployeePayHistory_.rateChangeDate));
                query.where(builder.equal(ph.get(EmployeePayHistory_.rateChangeDate), latest));
                query.orderBy(builder.desc(ph.get(EmployeePayHistory_.rate)),
                              builder.asc(e.get(Employee_.businessEntityId)));
                return entityManager.createQuery(query).setMaxResults(10).getResultList();
            }));
        }
    }

    @DisplayName("AW_HUMANRESOURCES_I_02: the direct reports of a manager, by hierarchyid")
    @Nested
    class AW_HUMANRESOURCES_I_02_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found = apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
                    """
                            SELECT e.OrganizationNode.ToString() AS Node, e.JobTitle, p.FirstName, p.LastName
                            FROM HumanResources.Employee e
                                     JOIN Person.Person p ON p.BusinessEntityID = e.BusinessEntityID
                            WHERE e.OrganizationNode.GetAncestor(1) = (SELECT m.OrganizationNode
                                                                       FROM HumanResources.Employee m
                                                                       WHERE m.BusinessEntityID = ?1)
                            ORDER BY e.OrganizationNode"""
            ).setParameter(1, 25).getResultList());
            assertThat(rows(found)).containsExactly(
                    "/3/1/ | Production Control Manager | Peter | Krebs",
                    "/3/2/ | Quality Assurance Manager | Hazem | Abolrous",
                    "/3/3/ | Master Scheduler | A. Scott | Wright",
                    "/3/4/ | Facilities Manager | Gary | Altman"
            );
        }
    }

    @DisplayName("AW_HUMANRESOURCES_A_01: the chain of managers of an employee, by hierarchyid")
    @Nested
    class AW_HUMANRESOURCES_A_01_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found = apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
                    """
                            SELECT m.OrganizationNode.ToString() AS Node, m.OrganizationLevel, m.JobTitle,
                                   p.FirstName, p.LastName
                            FROM HumanResources.Employee e
                                     JOIN HumanResources.Employee m
                                          ON e.OrganizationNode.IsDescendantOf(m.OrganizationNode) = 1
                                     JOIN Person.Person p ON p.BusinessEntityID = m.BusinessEntityID
                            WHERE e.BusinessEntityID = ?1
                            ORDER BY m.OrganizationLevel"""
            ).setParameter(1, 100).getResultList());
            assertThat(rows(found)).containsExactly(
                    "/3/ | 1 | Vice President of Production | James | Hamilton",
                    "/3/1/ | 2 | Production Control Manager | Peter | Krebs",
                    "/3/1/9/ | 3 | Production Supervisor - WC50 | Kok-Ho | Loh",
                    "/3/1/9/7/ | 4 | Production Technician - WC50 | Lolan | Song"
            );
        }
    }

    // -------------------------------------------------------------------------------------- Person
    @DisplayName("AW_PERSON_B_01: the persons of each type")
    @Nested
    class AW_PERSON_B_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "IN | 18484",
                    "SC | 753",
                    "GC | 289",
                    "EM | 273",
                    "VC | 156",
                    "SP | 17"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT p.personType, COUNT(p)
                            FROM PersonPerson p
                            GROUP BY p.personType
                            ORDER BY COUNT(p) DESC""",
                    Object[].class
            ).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var p = query.from(Person.class);
                final var persons = builder.count(p);
                query.multiselect(p.get(Person_.personType), persons);
                query.groupBy(p.get(Person_.personType));
                query.orderBy(builder.desc(persons));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }

    @DisplayName("AW_PERSON_I_01: the five countries with the most addresses")
    @Nested
    class AW_PERSON_I_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "US | United States | 8668",
                    "AU | Australia | 3632",
                    "GB | United Kingdom | 1954",
                    "FR | France | 1851",
                    "DE | Germany | 1821"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT c.countryRegionCode, c.name, COUNT(a)
                            FROM PersonAddress a
                                     JOIN a.stateProvince s
                                     JOIN s.countryRegion c
                            GROUP BY c.countryRegionCode, c.name
                            ORDER BY COUNT(a) DESC""",
                    Object[].class
            ).setMaxResults(5).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var a = query.from(Address.class);
                final var c = a.join(Address_.stateProvince).join(StateProvince_.countryRegion);
                final var addresses = builder.count(a);
                query.multiselect(c.get(CountryRegion_.countryRegionCode), c.get(CountryRegion_.name), addresses);
                query.groupBy(c.get(CountryRegion_.countryRegionCode), c.get(CountryRegion_.name));
                query.orderBy(builder.desc(addresses));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }

    @DisplayName("AW_PERSON_A_01: the individual customers of each yearly income band")
    @Nested
    class AW_PERSON_A_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "0-25000 | 2922",
                    "25001-50000 | 5704",
                    "50001-75000 | 5476",
                    "75001-100000 | 2755",
                    "greater than 100000 | 1627"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT v.yearlyIncome, COUNT(v)
                            FROM SalesPersonDemographicsView v
                                     JOIN PersonPerson p ON p.businessEntityId = v.businessEntityId
                            WHERE p.personType = :personType
                            GROUP BY v.yearlyIncome
                            ORDER BY v.yearlyIncome""",
                    Object[].class
            ).setParameter("personType", Person.PersonType.IN).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var v = query.from(PersonDemographicsView.class);
                final var p = v.join(Person.class);
                p.on(builder.equal(p.get(Person_.businessEntityId), v.get(PersonDemographicsView_.businessEntityId)));
                final var yearlyIncome = v.get(PersonDemographicsView_.yearlyIncome);
                query.multiselect(yearlyIncome, builder.count(v));
                query.where(builder.equal(p.get(Person_.personType), Person.PersonType.IN));
                query.groupBy(yearlyIncome);
                query.orderBy(builder.asc(yearlyIncome));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }

    // ---------------------------------------------------------------------------------- Production
    @DisplayName("AW_PRODUCTION_B_01: the products on sale of each category, and their list prices")
    @Nested
    class AW_PRODUCTION_B_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "Accessories | 22 | 2.29 | 159",
                    "Bikes | 60 | 539.99 | 2443.35",
                    "Clothing | 20 | 8.99 | 69.99",
                    "Components | 95 | 20.24 | 1431.5"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT c.name, COUNT(p), MIN(p.listPrice), MAX(p.listPrice)
                            FROM ProductionProduct p
                                     JOIN p.productSubcategory s
                                     JOIN s.productCategory c
                            WHERE p.sellEndDate IS NULL
                            GROUP BY c.productCategoryId, c.name
                            ORDER BY c.name""",
                    Object[].class
            ).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var p = query.from(Product.class);
                final var c = p.join(Product_.productSubcategory).join(ProductSubcategory_.productCategory);
                query.multiselect(c.get(ProductCategory_.name), builder.count(p),
                                  builder.min(p.get(Product_.listPrice)), builder.max(p.get(Product_.listPrice)));
                query.where(builder.isNull(p.get(Product_.sellEndDate)));
                query.groupBy(c.get(ProductCategory_.productCategoryId), c.get(ProductCategory_.name));
                query.orderBy(builder.asc(c.get(ProductCategory_.name)));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }

    @DisplayName("AW_PRODUCTION_I_01: the products whose stock is less than half their safety stock level")
    @Nested
    class AW_PRODUCTION_I_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "853 | Women's Tights, M | 4 | 0",
                    "859 | Half-Finger Gloves, M | 4 | 0",
                    "876 | Hitch Rack - 4-Bike | 4 | 0",
                    "882 | Short-Sleeve Classic Jersey, M | 4 | 0"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT p.productId, p.name, p.safetyStockLevel, SUM(i.quantity)
                            FROM ProductionProductInventory i
                                     JOIN i.product p
                            GROUP BY p.productId, p.name, p.safetyStockLevel
                            HAVING SUM(i.quantity) * 2 < p.safetyStockLevel
                            ORDER BY SUM(i.quantity), p.productId""",
                    Object[].class
            ).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var i = query.from(ProductInventory.class);
                final var p = i.join(ProductInventory_.product);
                final var quantity = builder.sum(i.get(ProductInventory_.quantity));
                query.multiselect(p.get(Product_.productId), p.get(Product_.name), p.get(Product_.safetyStockLevel),
                                  quantity);
                query.groupBy(p.get(Product_.productId), p.get(Product_.name), p.get(Product_.safetyStockLevel));
                query.having(builder.lt(builder.prod(quantity, 2), p.get(Product_.safetyStockLevel)));
                query.orderBy(builder.asc(quantity), builder.asc(p.get(Product_.productId)));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }

    @DisplayName("AW_PRODUCTION_I_02: the five scrap reasons with the most scrapped quantity")
    @Nested
    class AW_PRODUCTION_I_02_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "Paint process failed | 37 | 1271",
                    "Trim length too long | 52 | 981",
                    "Thermoform temperature too low | 63 | 876",
                    "Drill size too small | 44 | 826",
                    "Wheel misaligned | 51 | 804"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT r.name, COUNT(w), SUM(w.scrappedQty)
                            FROM ProductionWorkOrder w
                                     JOIN w.scrapReason r
                            GROUP BY r.scrapReasonId, r.name
                            ORDER BY SUM(w.scrappedQty) DESC""",
                    Object[].class
            ).setMaxResults(5).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var w = query.from(WorkOrder.class);
                final var r = w.join(WorkOrder_.scrapReason);
                final var scrappedQty = builder.sum(w.get(WorkOrder_.scrappedQty));
                query.multiselect(r.get(ScrapReason_.name), builder.count(w), scrappedQty);
                query.groupBy(r.get(ScrapReason_.scrapReasonId), r.get(ScrapReason_.name));
                query.orderBy(builder.desc(scrappedQty));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }

    @DisplayName("AW_PRODUCTION_A_01: the parts of a product, by its bill of materials")
    @Nested
    class AW_PRODUCTION_A_01_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found = apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
                    """
                            WITH Parts (AssemblyID, ComponentID, PerAssemblyQty, BomLevel) AS (
                                SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty, 1
                                FROM Production.BillOfMaterials b
                                WHERE b.ProductAssemblyID = ?1
                                  AND b.EndDate IS NULL
                                UNION ALL
                                SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty, p.BomLevel + 1
                                FROM Production.BillOfMaterials b
                                         JOIN Parts p ON p.ComponentID = b.ProductAssemblyID
                                WHERE b.EndDate IS NULL
                            )
                            SELECT p.AssemblyID, p.ComponentID, c.Name, p.PerAssemblyQty, p.BomLevel
                            FROM Parts p
                                     JOIN Production.Product c ON c.ProductID = p.ComponentID
                            ORDER BY p.BomLevel, p.AssemblyID, p.ComponentID"""
            ).setParameter(1, 818).getResultList());
            assertThat(rows(found)).containsExactly(
                    "818 | 400 | LL Hub | 1 | 1",
                    "818 | 490 | LL Nipple | 36 | 1",
                    "818 | 506 | Reflector | 2 | 1",
                    "818 | 510 | LL Road Rim | 1 | 1",
                    "818 | 527 | Spokes | 36 | 1",
                    "818 | 922 | Road Tire Tube | 1 | 1",
                    "818 | 931 | LL Road Tire | 1 | 1",
                    "400 | 523 | LL Spindle/Axle | 1 | 2",
                    "400 | 525 | LL Shell | 1 | 2"
            );
        }
    }

    @DisplayName("AW_PRODUCTION_A_02: the documents under a folder, and the products of each")
    @Nested
    class AW_PRODUCTION_A_02_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found = apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
                    """
                            SELECT d.DocumentNode.ToString() AS Node, d.Title, COUNT(pd.ProductID) AS Products
                            FROM Production.Document d
                                     LEFT JOIN Production.ProductDocument pd ON pd.DocumentNode = d.DocumentNode
                            WHERE d.DocumentNode.IsDescendantOf(hierarchyid::Parse(?1)) = 1
                              AND d.FolderFlag = 0
                            GROUP BY d.DocumentNode, d.Title
                            ORDER BY d.DocumentNode"""
            ).setParameter(1, "/3/").getResultList());
            assertThat(rows(found)).containsExactly(
                    "/3/1/ | Front Reflector Bracket and Reflector Assembly 3 | 1",
                    "/3/2/ | Front Reflector Bracket Installation | 1",
                    "/3/3/ | Installing Replacement Pedals | 7",
                    "/3/4/ | Seat Assembly | 9"
            );
        }
    }

    // ---------------------------------------------------------------------------------- Purchasing
    @DisplayName("AW_PURCHASING_B_01: the vendors of a product, the cheapest first")
    @Nested
    class AW_PURCHASING_B_01_Test {

        private static final int PRODUCT_ID = 322;

        private void verify(final List<ProductVendor> found) {
            assertThat(found)
                    .extracting(pv -> row(pv.getBusinessEntity().getName(), pv.getStandardPrice(),
                                          pv.getAverageLeadTime(), pv.getMinOrderQty()))
                    .containsExactly(
                            "Beaumont Bikes | 24.21 | 17 | 20",
                            "Bike Satellite Inc. | 25.11 | 17 | 20",
                            "Training Systems | 27.33 | 19 | 20"
                    );
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                verify(entityManager.createQuery(
                        """
                                SELECT pv
                                FROM PurchasingProductVendor pv
                                         JOIN pv.businessEntity v
                                WHERE pv.productId = :productId
                                ORDER BY pv.standardPrice, v.name""",
                        ProductVendor.class
                ).setParameter("productId", PRODUCT_ID).getResultList());
                return null;
            });
        }

        @Test
        void __CriteriaApi() {
            apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(ProductVendor.class);
                final var pv = query.from(ProductVendor.class);
                final var v = pv.join(ProductVendor_.businessEntity);
                query.select(pv);
                query.where(builder.equal(pv.get(ProductVendor_.productId), PRODUCT_ID));
                query.orderBy(builder.asc(pv.get(ProductVendor_.standardPrice)), builder.asc(v.get(Vendor_.name)));
                verify(entityManager.createQuery(query).getResultList());
                return null;
            });
        }
    }

    @DisplayName("AW_PURCHASING_I_01: the five vendors the company spent the most with")
    @Nested
    class AW_PURCHASING_I_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "SUPERIOR0001 | Superior Bicycles | 50 | 5034266.74",
                    "PROFESSI0001 | Professional Athletic Consultants | 50 | 3379946.3215",
                    "CHICAGO0001 | Chicago City Saddles | 51 | 3347165.1965",
                    "JACKSON0001 | Jackson Authority | 51 | 2821333.516",
                    "VISIONC0001 | Vision Cycles, Inc. | 50 | 2777684.911"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT v.accountNumber, v.name, COUNT(h), SUM(h.totalDue)
                            FROM PurchasingPurchaseOrderHeader h
                                     JOIN h.vendor v
                            GROUP BY v.businessEntityId, v.accountNumber, v.name
                            ORDER BY SUM(h.totalDue) DESC""",
                    Object[].class
            ).setMaxResults(5).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var h = query.from(PurchaseOrderHeader.class);
                final var v = h.join(PurchaseOrderHeader_.vendor);
                final var totalDue = builder.sum(h.get(PurchaseOrderHeader_.totalDue));
                query.multiselect(v.get(Vendor_.accountNumber), v.get(Vendor_.name), builder.count(h), totalDue);
                query.groupBy(v.get(Vendor_.businessEntityId), v.get(Vendor_.accountNumber), v.get(Vendor_.name));
                query.orderBy(builder.desc(totalDue));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }

    @DisplayName("AW_PURCHASING_I_02: the five vendors whose goods were rejected the most")
    @Nested
    class AW_PURCHASING_I_02_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found.stream().map(r -> Arrays.copyOf(r, 3)).toList())).containsExactly(
                    "International | 153 | 9",
                    "Anderson's Custom Bikes | 27558 | 1510",
                    "Signature Cycles | 54455 | 2940",
                    "Crowley Sport | 55637 | 2967",
                    "Capital Road Cycles | 55664 | 2967"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT v.name, SUM(d.receivedQty), SUM(d.rejectedQty),
                                   SUM(d.rejectedQty) / SUM(d.receivedQty) AS rejected
                            FROM PurchasingPurchaseOrderDetail d
                                     JOIN d.purchaseOrder h
                                     JOIN h.vendor v
                            GROUP BY v.businessEntityId, v.name
                            ORDER BY rejected DESC, v.name""",
                    Object[].class
            ).setMaxResults(5).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var d = query.from(PurchaseOrderDetail.class);
                final var v = d.join(PurchaseOrderDetail_.purchaseOrder).join(PurchaseOrderHeader_.vendor);
                final var receivedQty = builder.sum(d.get(PurchaseOrderDetail_.receivedQty));
                final var rejectedQty = builder.sum(d.get(PurchaseOrderDetail_.rejectedQty));
                final var rejected = builder.quot(rejectedQty, receivedQty);
                query.multiselect(v.get(Vendor_.name), receivedQty, rejectedQty, rejected);
                query.groupBy(v.get(Vendor_.businessEntityId), v.get(Vendor_.name));
                query.orderBy(builder.desc(rejected), builder.asc(v.get(Vendor_.name)));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }

    // --------------------------------------------------------------------------------------- Sales
    @DisplayName("AW_SALES_B_01: an order, by its order number")
    @Nested
    class AW_SALES_B_01_Test {

        private static final String SALES_ORDER_NUMBER = "SO43659";

        private void verify(final SalesOrderHeader found) {
            assertThat(row(found.getSalesOrderId(), found.getSalesOrderNumber(), found.getSubTotal(),
                           found.getTaxAmt(), found.getFreight(), found.getTotalDue()))
                    .isEqualTo("43659 | SO43659 | 20565.6206 | 1971.5149 | 616.0984 | 23153.2339");
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT h
                            FROM SalesSalesOrderHeader h
                            WHERE h.salesOrderNumber = :salesOrderNumber""",
                    SalesOrderHeader.class
            ).setParameter("salesOrderNumber", SALES_ORDER_NUMBER).getSingleResult()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(SalesOrderHeader.class);
                final var h = query.from(SalesOrderHeader.class);
                query.select(h);
                query.where(builder.equal(h.get(SalesOrderHeader_.salesOrderNumber), SALES_ORDER_NUMBER));
                return entityManager.createQuery(query).getSingleResult();
            }));
        }
    }

    @DisplayName("AW_SALES_B_02: the customers, the persons and the stores among them")
    @Nested
    class AW_SALES_B_02_Test {

        private void verify(final Object[] found) {
            assertThat(row(found)).isEqualTo("19820 | 19119 | 1336");
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT COUNT(c), COUNT(c.personId), COUNT(c.storeId)
                            FROM SalesCustomer c""",
                    Object[].class
            ).getSingleResult()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var c = query.from(Customer.class);
                query.multiselect(builder.count(c), builder.count(c.get(Customer_.personId)),
                                  builder.count(c.get(Customer_.storeId)));
                return entityManager.createQuery(query).getSingleResult();
            }));
        }
    }

    @DisplayName("AW_SALES_I_01: the orders of each territory")
    @Nested
    class AW_SALES_I_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "Southwest | North America | 6224 | 27150594.5893",
                    "Canada | North America | 4067 | 18398929.188",
                    "Northwest | North America | 4594 | 18061660.371",
                    "Australia | Pacific | 6843 | 11814376.0952",
                    "Central | North America | 385 | 8913299.2473",
                    "Southeast | North America | 486 | 8884099.3669",
                    "United Kingdom | Europe | 3219 | 8574048.7082",
                    "France | Europe | 2672 | 8119749.346",
                    "Northeast | North America | 352 | 7820209.6285",
                    "Germany | Europe | 2623 | 5479819.5755"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT t.name, t.group, COUNT(h), SUM(h.totalDue)
                            FROM SalesSalesOrderHeader h
                                     JOIN h.territory t
                            GROUP BY t.territoryId, t.name, t.group
                            ORDER BY SUM(h.totalDue) DESC""",
                    Object[].class
            ).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var h = query.from(SalesOrderHeader.class);
                final var t = h.join(SalesOrderHeader_.territory);
                final var totalDue = builder.sum(h.get(SalesOrderHeader_.totalDue));
                query.multiselect(t.get(SalesTerritory_.name), t.get(SalesTerritory_.group), builder.count(h),
                                  totalDue);
                query.groupBy(t.get(SalesTerritory_.territoryId), t.get(SalesTerritory_.name),
                              t.get(SalesTerritory_.group));
                query.orderBy(builder.desc(totalDue));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }

    @DisplayName("AW_SALES_I_02: the five products which brought in the most")
    @Nested
    class AW_SALES_I_02_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "BK-M68B-38 | Mountain-200 Black, 38 | 2977 | 4400592.8004",
                    "BK-M68B-42 | Mountain-200 Black, 42 | 2664 | 4009494.761841",
                    "BK-M68S-38 | Mountain-200 Silver, 38 | 2394 | 3693678.025272",
                    "BK-M68S-42 | Mountain-200 Silver, 42 | 2234 | 3438478.860423",
                    "BK-M68S-46 | Mountain-200 Silver, 46 | 2216 | 3434256.941928"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT p.productNumber, p.name, SUM(d.orderQty), SUM(d.lineTotal)
                            FROM SalesSalesOrderDetail d
                                     JOIN d.specialOfferProduct sop
                                     JOIN sop.product p
                            GROUP BY p.productId, p.productNumber, p.name
                            ORDER BY SUM(d.lineTotal) DESC""",
                    Object[].class
            ).setMaxResults(5).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var d = query.from(SalesOrderDetail.class);
                final var p = d.join(SalesOrderDetail_.specialOfferProduct).join(SpecialOfferProduct_.product);
                final var lineTotal = builder.sum(d.get(SalesOrderDetail_.lineTotal));
                query.multiselect(p.get(Product_.productNumber), p.get(Product_.name),
                                  builder.sum(d.get(SalesOrderDetail_.orderQty)), lineTotal);
                query.groupBy(p.get(Product_.productId), p.get(Product_.productNumber), p.get(Product_.name));
                query.orderBy(builder.desc(lineTotal));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }

    @DisplayName("AW_SALES_I_03: the discount each special offer gave")
    @Nested
    class AW_SALES_I_03_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "14 | Touring-1000 Promotion | 0.2 | 231 | 145332.9072",
                    "7 | Mountain-100 Clearance Sale | 0.35 | 137 | 135114.9021",
                    "13 | Touring-3000 Promotion | 0.15 | 504 | 78219.5246",
                    "2 | Volume Discount 11 to 14 | 0.02 | 1310 | 59004.7372",
                    "3 | Volume Discount 15 to 24 | 0.05 | 606 | 54612.6983",
                    "9 | Road-650 Overstock | 0.3 | 61 | 21422.6064",
                    "16 | Mountain-500 Silver Clearance Sale | 0.4 | 169 | 17266.0944",
                    "4 | Volume Discount 25 to 40 | 0.1 | 80 | 13794.2823",
                    "11 | Sport Helmet Discount-2003 | 0.15 | 84 | 1606.024",
                    "8 | Sport Helmet Discount-2002 | 0.1 | 98 | 827.6424",
                    "5 | Volume Discount 41 to 60 | 0.15 | 2 | 306.5255"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT o.specialOfferId, o.description, o.discountPct, COUNT(d),
                                   SUM(d.unitPrice * d.unitPriceDiscount * d.orderQty) AS discount
                            FROM SalesSalesOrderDetail d
                                     JOIN d.specialOfferProduct sop
                                     JOIN sop.specialOffer o
                            WHERE d.unitPriceDiscount > 0
                            GROUP BY o.specialOfferId, o.description, o.discountPct
                            ORDER BY discount DESC""",
                    Object[].class
            ).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var d = query.from(SalesOrderDetail.class);
                final var o = d.join(SalesOrderDetail_.specialOfferProduct).join(SpecialOfferProduct_.specialOffer);
                final var discount = builder.sum(builder.prod(
                        builder.prod(d.get(SalesOrderDetail_.unitPrice), d.get(SalesOrderDetail_.unitPriceDiscount)),
                        d.get(SalesOrderDetail_.orderQty)));
                query.multiselect(o.get(SpecialOffer_.specialOfferId), o.get(SpecialOffer_.description),
                                  o.get(SpecialOffer_.discountPct), builder.count(d), discount);
                query.where(builder.gt(d.get(SalesOrderDetail_.unitPriceDiscount), BigDecimal.ZERO));
                query.groupBy(o.get(SpecialOffer_.specialOfferId), o.get(SpecialOffer_.description),
                              o.get(SpecialOffer_.discountPct));
                query.orderBy(builder.desc(discount));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }

    @DisplayName("AW_SALES_I_04: the five sales persons with the most sales this year")
    @Nested
    class AW_SALES_I_04_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "Linda | Mitchell | Southwest | 4251368.5497",
                    "Jae | Pak | United Kingdom | 4116871.2277",
                    "Michael | Blythe | Northeast | 3763178.1787",
                    "Jillian | Carson | Central | 3189418.3662",
                    "Ranjit | Varkey Chudukatil | France | 3121616.3202"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT p.firstName, p.lastName, t.name, sp.salesYtd
                            FROM SalesSalesPerson sp
                                     JOIN sp.businessEntity e
                                     JOIN e.businessEntity p
                                     LEFT JOIN sp.territory t
                            ORDER BY sp.salesYtd DESC""",
                    Object[].class
            ).setMaxResults(5).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var sp = query.from(SalesPerson.class);
                final var p = sp.join(SalesPerson_.businessEntity).join(Employee_.businessEntity);
                final var t = sp.join(SalesPerson_.territory, JoinType.LEFT);
                query.multiselect(p.get(Person_.firstName), p.get(Person_.lastName), t.get(SalesTerritory_.name),
                                  sp.get(SalesPerson_.salesYtd));
                query.orderBy(builder.desc(sp.get(SalesPerson_.salesYtd)));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }

    @DisplayName("AW_SALES_A_01: the sales of each sales person, a column per fiscal year")
    @Nested
    class AW_SALES_A_01_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found = apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
                    """
                            SELECT pvt.LastName, pvt.[2022], pvt.[2023], pvt.[2024], pvt.[2025]
                            FROM (SELECT p.LastName, h.SubTotal, YEAR(DATEADD(MONTH, 6, h.OrderDate)) AS FiscalYear
                                  FROM Sales.SalesOrderHeader h
                                           JOIN Person.Person p ON p.BusinessEntityID = h.SalesPersonID) AS s
                                     PIVOT (SUM(s.SubTotal) FOR s.FiscalYear IN ([2022], [2023], [2024], [2025])) AS pvt
                            ORDER BY pvt.[2025] DESC"""
            ).getResultList());
            assertThat(rows(found)).hasSize(17).startsWith(
                    "Pak | NULL | 963345.1079 | 4188307.0033 | 3351686.5345",
                    "Mitchell | 276440.8521 | 2742919.4886 | 4064078.2392 | 3283568.8466"
            ).endsWith(
                    "Abbas | NULL | NULL | NULL | 172524.4512"
            );
        }
    }

    @DisplayName("AW_SALES_A_02: the sales persons ranked within the group of their territory")
    @Nested
    class AW_SALES_A_02_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found = apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
                    """
                            SELECT t.[Group], t.Name AS Territory, p.LastName, sp.SalesYTD,
                                   RANK() OVER (PARTITION BY t.[Group] ORDER BY sp.SalesYTD DESC) AS GroupRank
                            FROM Sales.SalesPerson sp
                                     JOIN Person.Person p ON p.BusinessEntityID = sp.BusinessEntityID
                                     JOIN Sales.SalesTerritory t ON t.TerritoryID = sp.TerritoryID
                            ORDER BY t.[Group], GroupRank"""
            ).getResultList());
            assertThat(rows(found)).hasSize(14).startsWith(
                    "Europe | United Kingdom | Pak | 4116871.2277 | 1",
                    "Europe | France | Varkey Chudukatil | 3121616.3202 | 2",
                    "Europe | Germany | Valdez | 1827066.7118 | 3",
                    "North America | Southwest | Mitchell | 4251368.5497 | 1"
            ).endsWith(
                    "Pacific | Australia | Tsoflias | 1421810.9242 | 1"
            );
        }
    }
}
