package io.qiot.covid19.datahub.importer.persistence.relational;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.relational.StationHistory;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class StationHistoryRepository implements PanacheRepositoryBase<StationHistory, UUID> {

    @Inject
    Logger LOGGER;

//    /**
//     * @param id
//     * @return
//     */
//    public StationHistory findById(String id) {
//        LOGGER.debug("Searching for Measurement StationDTO with ID {}", id);
//        StationHistory ms = findById(UUID.fromString(id));
//        if (ms != null)
//            LOGGER.debug("Found StationDTO {}", ms);
//        return ms;
//    }
//
//    /**
//     * @param id
//     * @return
//     */
//    public StationHistory findByCityAndCountry(String city, String country) {
//        LOGGER.debug("Searching for Measurement StationDTO with ID {}", id);
//        StationHistory ms = findById(UUID.fromString(id));
//        if (ms != null)
//            LOGGER.debug("Found StationDTO {}", ms);
//        return ms;
//    }

    public List<StationHistory> findAllStations() {
        return findAll().list();
    }

}
