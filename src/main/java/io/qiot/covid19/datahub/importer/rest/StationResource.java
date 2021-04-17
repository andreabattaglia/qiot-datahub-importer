package io.qiot.covid19.datahub.importer.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.dto.StationImportResult;
import io.qiot.covid19.datahub.importer.domain.relational.StationHistory;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;
import io.qiot.covid19.datahub.importer.service.relational.RelationalStationService;

/**
 * The Class StationResource.
 *
 * @author andreabattaglia
 */
@ApplicationScoped
@Path("/station")
@Produces(MediaType.APPLICATION_JSON)
public class StationResource {

    /** The url. */
    @ConfigProperty(name = "app.aqicn.stations.url")
    String url;

    /** The logger. */
    @Inject
    Logger LOGGER;

    /** The station service. */
    @Inject
    RelationalStationService stationService;

    /**
     * Impost stations.
     *
     * @return the station import result
     * @throws DataServiceException the data service exception
     */
    @PUT
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    public StationImportResult impostStations() throws DataServiceException {
        return stationService.importFromUrl();
    }

    /**
     * Gets the by location.
     *
     * @param countryCode the country code
     * @param city the city
     * @return the by location
     * @throws DataServiceException the data service exception
     */
    @GET
    @Consumes({ MediaType.TEXT_PLAIN })
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    public StationHistory getByLocation(@QueryParam("ccode") String countryCode,
            @QueryParam("city") String city) throws DataServiceException {
        return stationService.findByLocation(countryCode, city);
    }

}