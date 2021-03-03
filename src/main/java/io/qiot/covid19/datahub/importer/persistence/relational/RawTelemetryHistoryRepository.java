package io.qiot.covid19.datahub.importer.persistence.relational;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.relational.RawTelemetryHistory;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

/**
 * The Class RawTelemetryHistoryRepository.
 *
 * @author andreabattaglia
 */
@ApplicationScoped
public class RawTelemetryHistoryRepository
        implements PanacheRepositoryBase<RawTelemetryHistory, UUID> {

    /** The logger. */
    @Inject
    Logger LOGGER;

    /** The em. */
    @Inject
    EntityManager em;

    /**
     * Dedup.
     *
     * @return the int
     */
    public int dedup() {
        Query q = em.createNativeQuery("DELETE FROM rawtelemetryhistory rt1 USING rawtelemetryhistory rt2 \n"
                + "WHERE \n"
                + "    rt1.id < rt2.id \n"
                + "    AND rt1.date = rt2.date \n"
                + "    AND rt1.country = rt2.country \n"
                + "    AND rt1.city = rt2.city \n"
                + "    AND rt1.specie = rt2.specie;");
        return q.executeUpdate();
    }
}
