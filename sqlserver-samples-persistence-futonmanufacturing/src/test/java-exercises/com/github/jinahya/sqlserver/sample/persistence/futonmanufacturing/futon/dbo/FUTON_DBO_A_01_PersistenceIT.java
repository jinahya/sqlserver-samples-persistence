package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.scale4;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_A_01} of {@code src/test/sql-exercises/_dbo/03_advanced.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_A_01: the raw materials ten futons need, through every level")
class FUTON_DBO_A_01_PersistenceIT {

    /**
     * Explodes the bill of materials of an item, a level at a time, with the specified function, which reads the
     * active rows whose parent is one of the specified items.
     */
    private Map<String, BigDecimal> explode(
            final EntityManager entityManager, final String itemCode, final BigDecimal quantity,
            final BiFunction<EntityManager, Collection<Integer>, List<BillOfMaterials>> children) {
        final var item = entityManager.createQuery(
                        "SELECT i FROM DboItem i WHERE i.itemCode = :itemCode", Item.class)
                .setParameter("itemCode", itemCode)
                .getSingleResult();
        final var leaves = new TreeMap<String, BigDecimal>();
        var level = Map.of(item.getItemId(), quantity);
        while (!level.isEmpty()) {
            final var rows = children.apply(entityManager, level.keySet());
            final var next = new HashMap<Integer, BigDecimal>();
            for (final var row : rows) {
                // the quantity of the parent, times the quantity of this row and its scrap
                final var required = level.get(row.getParentItem().getItemId())
                        .multiply(row.getQuantity())
                        .multiply(BigDecimal.ONE.add(row.getScrapRate().movePointLeft(2)));
                next.merge(row.getComponentItem().getItemId(), required, BigDecimal::add);
            }
            // an item with no row of its own is a raw material
            for (final var entry : level.entrySet()) {
                if (rows.stream().noneMatch(r -> r.getParentItem().getItemId().equals(entry.getKey()))) {
                    final var leaf = entityManager.find(Item.class, entry.getKey());
                    leaves.merge(leaf.getItemCode(), entry.getValue(), BigDecimal::add);
                }
            }
            level = next;
        }
        return leaves;
    }

    private void verify(final Map<String, BigDecimal> leaves) {
        assertThat(leaves.keySet()).containsExactly(
                "RM-FAB-004", "RM-FILL-002", "RM-FIN-001", "RM-FIN-003", "RM-HARD-001", "RM-METAL-001",
                "RM-METAL-002", "RM-WOOD-001", "RM-WOOD-002", "RM-WOOD-003");
        assertThat(leaves.values()).extracting(__Exercises_TestUtils::scale4)
                .containsExactly(new BigDecimal("130.9770"), new BigDecimal("351.6540"),
                                 new BigDecimal("8.2913"), new BigDecimal("8.2913"),
                                 new BigDecimal("10.0500"), new BigDecimal("81.2040"),
                                 new BigDecimal("20.3010"), new BigDecimal("21.1050"),
                                 new BigDecimal("21.1050"), new BigDecimal("105.5250"));
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var leaves = explode(entityManager, "FG-FUT-003", BigDecimal.TEN, (em, parentItemIds) ->
                    em.createQuery("""
                                           SELECT b
                                           FROM DboBillOfMaterials b
                                           WHERE b.parentItemId IN :parentItemIds
                                             AND b.isActive = TRUE""", BillOfMaterials.class)
                            .setParameter("parentItemIds", parentItemIds)
                            .getResultList());
            verify(leaves);
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        apply(entityManager -> {
            final var leaves = explode(entityManager, "FG-FUT-003", BigDecimal.TEN, (em, parentItemIds) -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(BillOfMaterials.class);
                final var b = query.from(BillOfMaterials.class);
                query.select(b).where(b.get(BillOfMaterials_.parentItemId).in(parentItemIds),
                                      builder.isTrue(b.get(BillOfMaterials_.isActive)));
                return em.createQuery(query).getResultList();
            });
            verify(leaves);
            return null;
        });
    }

}
