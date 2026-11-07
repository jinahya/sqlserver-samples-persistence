package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.warehouse;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.website.VehicleTemperature;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.website.VehicleTemperature_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_WAREHOUSE_B_03} of {@code src/test/sql-exercises/_Warehouse/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("WWI_WAREHOUSE_B_03: The latest vehicle temperature readings")
class WWI_WAREHOUSE_B_03_PersistenceIT {

    private static String row(final VehicleTemperature v) {
        return v.getVehicleTemperatureId() + "," + v.getVehicleRegistration() + ","
               + v.getChillerSensorNumber() + "," + v.getRecordedWhen() + "," + v.getTemperature();
    }

    private void verify(final List<VehicleTemperature> found) {
        assertThat(found).extracting(WWI_WAREHOUSE_B_03_PersistenceIT::row).containsExactly(
                "65998,WWI-321-A,2,2016-05-31T15:57:19,4.08",
                "65997,WWI-321-A,1,2016-05-31T15:57:19,4.89",
                "65996,WWI-321-A,2,2016-05-31T15:54:50,3.12",
                "65995,WWI-321-A,1,2016-05-31T15:54:50,3.44",
                "65994,WWI-321-A,2,2016-05-31T15:53:27,4.18",
                "65993,WWI-321-A,1,2016-05-31T15:53:27,4.29",
                "65992,WWI-321-A,2,2016-05-31T15:51:18,3.89",
                "65991,WWI-321-A,1,2016-05-31T15:51:18,4.71",
                "65990,WWI-321-A,2,2016-05-31T15:50:17,3.42",
                "65989,WWI-321-A,1,2016-05-31T15:50:17,4.82"
        );
        assertThat(found.getFirst().getFullSensorData()).isEqualTo(
                "{\"Recordings\": [{\"type\":\"Feature\", \"geometry\": {\"type\":\"Point\", "
                + "\"coordinates\":[-110.0695943,61.6064746] }, \"properties\":{\"rego\":"
                + "\"WWI-321-A\",\"sensor\":\"2,\"when\":\"2016-05-31T15:57:19\","
                + "\"temp\":4.08}} ]");
    }

    @Test
    void __QueryLanguage() {
        verify(applyInTransactionAndRollback(entityManager -> entityManager.createQuery(
                        """
                                SELECT v
                                FROM WebsiteVehicleTemperature v
                                ORDER BY v.recordedWhen DESC, v.vehicleTemperatureId DESC""",
                        VehicleTemperature.class)
                .setMaxResults(10)
                .getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(VehicleTemperature.class);
            final var v = query.from(VehicleTemperature.class);
            query.select(v)
                    .orderBy(builder.desc(v.get(VehicleTemperature_.recordedWhen)),
                             builder.desc(v.get(VehicleTemperature_.vehicleTemperatureId)));
            return entityManager.createQuery(query).setMaxResults(10).getResultList();
        }));
    }

}
