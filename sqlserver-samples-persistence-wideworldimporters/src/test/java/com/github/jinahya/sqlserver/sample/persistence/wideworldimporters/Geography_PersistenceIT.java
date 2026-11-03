package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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

import com.github.jinahya.sqlserver.sample.persistence.common.Geography;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.City;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Country;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.StateProvince;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.SystemParameter;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Checks the {@code geography} columns, mapped as {@link Geography}, against what SQL Server returns for them.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class Geography_PersistenceIT {

    private static final Pattern NUMBER = Pattern.compile("-?\\d+(?:\\.\\d+)?(?:E[-+]?\\d+)?");

    private static double[] coordinates(final String text) {
        return NUMBER.matcher(text).results().mapToDouble(r -> Double.parseDouble(r.group())).toArray();
    }

    /**
     * Asserts that the specified Well-Known Texts are the same but for how their coordinates are written; SQL Server
     * writes each with 15 significant digits, or 17, so a coordinate is the same within 15 significant digits.
     */
    private static void assertSameText(final String actual, final String expected, final Object description) {
        assertThat(NUMBER.matcher(actual).replaceAll("#")).as("the text of %s", description)
                .isEqualTo(NUMBER.matcher(expected).replaceAll("#"));
        final var actuals = coordinates(actual);
        final var expecteds = coordinates(expected);
        assertThat(actuals).as("the coordinates of %s", description).hasSameSizeAs(expecteds);
        for (int i = 0; i < actuals.length; i++) {
            assertThat(actuals[i]).as("the coordinate %d of %s", i, description)
                    .isCloseTo(expecteds[i], within(Math.abs(expecteds[i]) * 1.0E-14));
        }
    }

    // ---------------------------------------------------------------------------------------------
    private static <T> Arguments entity(final String table, final String idColumn, final String column,
                                        final Class<T> entityClass, final Function<T, Integer> id,
                                        final Function<T, Geography> geography) {
        return Arguments.of(table + "." + column, table, idColumn, column, entityClass, id, geography);
    }

    private static Stream<Arguments> entities() {
        return Stream.of(
                entity("Application.Cities", "CityID", "Location", City.class, City::getCityId,
                       City::getLocation),
                entity("Application.Countries", "CountryID", "Border", Country.class, Country::getCountryId,
                       Country::getBorder),
                entity("Application.StateProvinces", "StateProvinceID", "Border", StateProvince.class,
                       StateProvince::getStateProvinceId, StateProvince::getBorder),
                entity("Application.SystemParameters", "SystemParameterID", "DeliveryLocation",
                       SystemParameter.class, SystemParameter::getSystemParameterId,
                       SystemParameter::getDeliveryLocation),
                entity("Purchasing.Suppliers", "SupplierID", "DeliveryLocation", Supplier.class,
                       Supplier::getSupplierId, Supplier::getDeliveryLocation),
                entity("Sales.Customers", "CustomerID", "DeliveryLocation", Customer.class, Customer::getCustomerId,
                       Customer::getDeliveryLocation)
        );
    }

    @DisplayName("every installed value of an entity's geography attribute maps to what STAsText and STSrid return")
    @ParameterizedTest(name = "{0}")
    @MethodSource("entities")
    @SuppressWarnings({"unchecked", "rawtypes"})
    void entity(final String name, final String table, final String idColumn, final String column,
                final Class entityClass, final Function id, final Function geography) {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            final List<Object[]> rows = em.createNativeQuery(
                            "SELECT t." + idColumn + ", t." + column + ".STAsText(), t." + column + ".STSrid"
                            + " FROM " + table + " AS t WHERE t." + column + " IS NOT NULL")
                    .getResultList();
            assumeThat(rows).isNotEmpty();
            final var query = em.getCriteriaBuilder().createQuery(entityClass);
            query.select(query.from(entityClass));
            final Map<Object, Object> instances = ((Stream<Object>) em.createQuery(query).getResultStream())
                    .collect(Collectors.toMap(i -> id.apply(i), Function.identity()));
            for (final var row : rows) {
                final var value = (Geography) geography.apply(instances.get(((Number) row[0]).intValue()));
                assertSameText(value.toString(), (String) row[1], name + " of " + row[0]);
                assertThat(value.getSrid()).isEqualTo(((Number) row[2]).intValue());
            }
            return null;
        });
    }

    // the archive tables and the views, which no entity maps; their classes decode what a native query reads
    @DisplayName("every installed value of a geography column decodes to what STAsText returns, and encodes back")
    @ParameterizedTest
    @ValueSource(strings = {
            "Application.Cities_Archive.Location",
            "Application.Countries_Archive.Border",
            "Application.StateProvinces_Archive.Border",
            "Purchasing.Suppliers_Archive.DeliveryLocation",
            "Sales.Customers_Archive.DeliveryLocation",
            "Website.Customers.DeliveryLocation",
            "Website.Suppliers.DeliveryLocation"
    })
    void keyless(final String qualified) {
        final var table = qualified.substring(0, qualified.lastIndexOf('.'));
        final var column = qualified.substring(qualified.lastIndexOf('.') + 1);
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            @SuppressWarnings("unchecked")
            final List<Object[]> rows = em.createNativeQuery(
                            "SELECT CAST(t." + column + " AS varbinary(max)), t." + column + ".STAsText()"
                            + " FROM " + table + " AS t WHERE t." + column + " IS NOT NULL")
                    .getResultList();
            assumeThat(rows).isNotEmpty();
            for (final var row : rows) {
                final var bytes = (byte[]) row[0];
                final var value = Geography.decode(bytes);
                assertSameText(value.toString(), (String) row[1], qualified);
                assertThat(value.getBytes()).isEqualTo(bytes);
            }
            return null;
        });
    }

    @DisplayName("a Border set through the entity is written as SQL Server reads it, and so is a null")
    @Test
    void write() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            final var ids = em.createNativeQuery("""
                                                         SELECT TOP (2) t.StateProvinceID
                                                         FROM Application.StateProvinces AS t
                                                         WHERE t.Border IS NOT NULL
                                                         ORDER BY t.StateProvinceID""")
                    .getResultStream()
                    .map(i -> ((Number) i).intValue())
                    .toList();
            assumeThat(ids).hasSize(2);
            // the border of the other state, a multipolygon of thousands of points
            final var border = em.find(StateProvince.class, ids.get(1)).getBorder();
            em.find(StateProvince.class, ids.get(0)).setBorder(border);
            em.find(StateProvince.class, ids.get(1)).setBorder(null);
            em.flush();
            final var read = em.createNativeQuery("""
                                                          SELECT CAST(t.Border AS varbinary(max))
                                                          FROM Application.StateProvinces AS t
                                                          WHERE t.StateProvinceID = ?1""");
            assertThat((byte[]) read.setParameter(1, ids.get(0)).getSingleResult()).isEqualTo(border.getBytes());
            assertThat(read.setParameter(1, ids.get(1)).getSingleResult()).isNull();
            return null;
        });
    }
}
