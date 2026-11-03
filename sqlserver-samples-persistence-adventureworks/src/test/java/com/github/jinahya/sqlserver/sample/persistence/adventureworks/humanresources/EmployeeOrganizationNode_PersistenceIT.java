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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils;
import com.github.jinahya.sqlserver.sample.persistence.common.Heirarchyid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Checks {@link Heirarchyid} against what SQL Server does with
 * {@code HumanResources.Employee.OrganizationNode} and with {@code hierarchyid::Parse}.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class EmployeeOrganizationNode_PersistenceIT {

    @DisplayName(
            "every installed OrganizationNode maps to what ToString, GetLevel and GetAncestor " +
            "return")
    @Test
    void decode() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            @SuppressWarnings("unchecked")
            final List<Object[]> rows = em.createNativeQuery("""
                                                                     SELECT e.BusinessEntityID,
                                                                            e.OrganizationNode.ToString(),
                                                                            e.OrganizationNode.GetLevel(),
                                                                            CAST(e.OrganizationNode.GetAncestor(1) AS varbinary(892))
                                                                     FROM HumanResources.Employee AS e
                                                                     WHERE e.OrganizationNode IS NOT NULL""")
                    .getResultList();
            assumeThat(rows).isNotEmpty();
            for (final var row : rows) {
                final var employee = em.find(Employee.class, ((Number) row[0]).intValue());
                final var node = employee.getOrganizationNode();
                assertThat(node).as("OrganizationNode of %s", row[0]).hasToString((String) row[1]);
                assertThat(node.getLevel()).isEqualTo(((Number) row[2]).intValue());
                assertThat(node.getAncestor(1)).isEqualTo(Heirarchyid.decode((byte[]) row[3]));
                assertThat(Heirarchyid.parse((String) row[1])).isEqualTo(node);
            }
            return null;
        });
    }

    @DisplayName("the installed OrganizationNodes sort as ORDER BY OrganizationNode does")
    @Test
    void order() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            @SuppressWarnings("unchecked")
            final List<byte[]> nodes = em.createNativeQuery("""
                                                                    SELECT CAST(e.OrganizationNode AS varbinary(892))
                                                                    FROM HumanResources.Employee AS e
                                                                    ORDER BY e.OrganizationNode""")
                    .getResultList();
            final var decoded = nodes.stream()
                    .map(b -> Optional.ofNullable(b).map(Heirarchyid::decode).orElse(null))
                    .toList();
            assumeThat(decoded).isNotEmpty();
            assertThat(decoded).isSortedAccordingTo(
                    Comparator.nullsFirst(Comparator.naturalOrder()));
            return null;
        });
    }

    // the bounds of every range, as a label, after a label, and in a dotted label
    @DisplayName("a value encodes to what hierarchyid::Parse returns")
    @ParameterizedTest
    @ValueSource(strings = {
            "/", "/1/", "/1/-2.18/", "/1.1/", "/0.3.-7/",
            "/-281479271682120/", "/-4294971465/", "/-4294971464/", "/-4169/", "/-4168/", "/-73/"
            , "/-72/",
            "/-9/", "/-8/", "/-1/", "/0/", "/3/", "/4/", "/7/", "/8/", "/15/", "/16/", "/79/",
            "/80/", "/1103/",
            "/1104/", "/5199/", "/5200/", "/4294972495/", "/4294972496/", "/281479271683151/",
            "/1/-281479271682121.0/", "/1/4294972495.7/2/", "/-73.-9.-1.3.7.15.79.1103.5199/"
    })
    void encode(final String string) {
        final var expected = ___PersistenceIT_Utils.applyInTransactionAndRollback(
                em -> (byte[]) em.createNativeQuery(
                                "SELECT CAST(hierarchyid::Parse(?1) AS varbinary(892))")
                        .setParameter(1, string)
                        .getSingleResult()
        );
        final var node = Heirarchyid.parse(string);
        assertThat(node.getBytes()).as("the bytes of %s", string).isEqualTo(expected);
        assertThat(Heirarchyid.decode(expected)).hasToString(string);
    }

    @DisplayName(
            "an OrganizationNode set through the entity is written as SQL Server reads it, and so" +
            " is a null")
    @Test
    void write() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            final var ids = em.createNativeQuery("""
                                                         SELECT TOP (2) e.BusinessEntityID
                                                         FROM HumanResources.Employee AS e
                                                         WHERE e.OrganizationNode IS NOT NULL
                                                         ORDER BY e.BusinessEntityID""")
                    .getResultStream()
                    .map(i -> ((Number) i).intValue())
                    .toList();
            assumeThat(ids).hasSize(2);
            // no installed node is this deep, so the unique index on the column takes it
            final var node = Heirarchyid.parse("/99/-7.13/4294972496/");
            em.find(Employee.class, ids.get(0)).setOrganizationNode(node);
            em.find(Employee.class, ids.get(1)).setOrganizationNode(null);
            em.flush();
            final var read = em.createNativeQuery("""
                                                          SELECT e.OrganizationNode.ToString()
                                                          FROM HumanResources.Employee AS e
                                                          WHERE e.BusinessEntityID = ?1""");
            assertThat(read.setParameter(1, ids.get(0)).getSingleResult()).isEqualTo(
                    node.toString());
            assertThat(read.setParameter(1, ids.get(1)).getSingleResult()).isNull();
            em.clear();
            assertThat(em.find(Employee.class, ids.get(0)).getOrganizationNode()).isEqualTo(node);
            return null;
        });
    }
}
