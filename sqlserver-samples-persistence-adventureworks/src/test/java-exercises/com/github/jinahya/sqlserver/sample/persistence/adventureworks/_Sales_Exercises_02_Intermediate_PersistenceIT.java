package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderDetail_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesPerson;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesPerson_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesTerritory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SpecialOfferProduct_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SpecialOffer_;
import jakarta.persistence.criteria.JoinType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Sales/02_intermediate.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _Sales_Exercises_02_Intermediate_PersistenceIT {

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
                query.multiselect(t.get(SalesTerritory_.name), t.get(SalesTerritory_.group),
                                  builder.count(h),
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
                final var p = d.join(SalesOrderDetail_.specialOfferProduct)
                        .join(SpecialOfferProduct_.product);
                final var lineTotal = builder.sum(d.get(SalesOrderDetail_.lineTotal));
                query.multiselect(p.get(Product_.productNumber), p.get(Product_.name),
                                  builder.sum(d.get(SalesOrderDetail_.orderQty)), lineTotal);
                query.groupBy(p.get(Product_.productId), p.get(Product_.productNumber),
                              p.get(Product_.name));
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
                final var o = d.join(SalesOrderDetail_.specialOfferProduct)
                        .join(SpecialOfferProduct_.specialOffer);
                final var discount = builder.sum(builder.prod(
                        builder.prod(d.get(SalesOrderDetail_.unitPrice),
                                     d.get(SalesOrderDetail_.unitPriceDiscount)),
                        d.get(SalesOrderDetail_.orderQty)));
                query.multiselect(o.get(SpecialOffer_.specialOfferId),
                                  o.get(SpecialOffer_.description),
                                  o.get(SpecialOffer_.discountPct), builder.count(d), discount);
                query.where(
                        builder.gt(d.get(SalesOrderDetail_.unitPriceDiscount), BigDecimal.ZERO));
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
                query.multiselect(p.get(Person_.firstName), p.get(Person_.lastName),
                                  t.get(SalesTerritory_.name),
                                  sp.get(SalesPerson_.salesYtd));
                query.orderBy(builder.desc(sp.get(SalesPerson_.salesYtd)));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }

    @DisplayName("AW_SALES_I_05: The orders and their amounts in each currency")
    @Nested
    class AW_SALES_I_05_Test {
    }
}
