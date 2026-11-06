package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Production/03_advanced.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _Production_Exercises_03_Advanced_PersistenceIT {

    @DisplayName("AW_PRODUCTION_A_01: the parts of a product, by its bill of materials")
    @Nested
    class AW_PRODUCTION_A_01_Test {

        @Test
        @SuppressWarnings({"unchecked"})
        void __NativeQuery() {
            final var found =
                    apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
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
            final var found =
                    apply(entityManager -> (List<Object[]>) entityManager.createNativeQuery(
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
}
