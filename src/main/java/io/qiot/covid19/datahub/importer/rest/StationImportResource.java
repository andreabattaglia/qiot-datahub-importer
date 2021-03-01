package io.qiot.covid19.datahub.importer.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.dto.StationImportResult;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;
import io.qiot.covid19.datahub.importer.service.relational.StationImportService;

@ApplicationScoped
@Path("/station")
@Produces(MediaType.APPLICATION_JSON)
public class StationImportResource {

    @ConfigProperty(name = "app.aqicn.stations.url")
    String url;

    @Inject
    Logger LOGGER;

    @Inject
    StationImportService stationImportService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    public StationImportResult impostStations() throws DataServiceException {
        return stationImportService.importFromUrl();
    }

}