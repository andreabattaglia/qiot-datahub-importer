package io.qiot.covid19.datahub.importer.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.dto.HistoricalDataPeriod;
import io.qiot.covid19.datahub.importer.domain.dto.TelemetryImportUploadResult;
import io.qiot.covid19.datahub.importer.domain.relational.RawTelemetryHistory;
import io.qiot.covid19.datahub.importer.domain.relational.RawTelemetryHistoryType;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;
import io.qiot.covid19.datahub.importer.service.TelemetryService;

/**
 * The Class TelemetryImportResource.
 *
 * @author andreabattaglia
 */
@ApplicationScoped
@Path("/telemetry")
public class TelemetryResource {

    /** The base url. */
    @ConfigProperty(name = "app.aqicn.telemetry.baseurl")
    String baseUrl;

    /** The token. */
    @ConfigProperty(name = "app.aqicn.token")
    String token;

    /** The logger. */
    @Inject
    Logger LOGGER;

    /** The telemetry import service. */
    @Inject
    TelemetryService telemetryService;

    /**
     * Download and print.
     *
     * @param period            the period
     * @return the string
     * @throws DataServiceException the data service exception
     */
    @PUT
    @Path("/{period}")
    @Consumes({ MediaType.TEXT_PLAIN })
    @Produces({ MediaType.APPLICATION_JSON })
    public TelemetryImportUploadResult importSingleTelemetry(
            @PathParam("period") String period) throws DataServiceException {
        return telemetryService
                .importSingleTelemetry(HistoricalDataPeriod.valueOf(period));
    }

    /**
     * Download and print.
     *
     * @return the string
     * @throws DataServiceException the data service exception
     */
    @PUT
    @Produces({ MediaType.APPLICATION_JSON })
    public List<TelemetryImportUploadResult> importAllAvailableTelemetry()
            throws DataServiceException {
        return telemetryService.importAllAvailableTelemetry();
    }

    /**
     * Gets the by parameters.
     *
     * @param date the date
     * @param countryCode the country code
     * @param city the city
     * @param specie the specie
     * @return the by parameters
     * @throws DataServiceException the data service exception
     */
    @GET
    @Consumes({ MediaType.TEXT_PLAIN })
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    public RawTelemetryHistory getByParameters(@QueryParam("date") String date,
            @QueryParam("ccode") String countryCode,
            @QueryParam("city") String city,
            @QueryParam("specie") RawTelemetryHistoryType specie)
            throws DataServiceException {
        LOGGER.debug("getByParameters(Date date={}, String countryCode={}, String city={}, RawTelemetryHistoryType specie={}) - start", date, countryCode, city, specie);

        RawTelemetryHistory returnRawTelemetryHistory = telemetryService.findParameters(date, countryCode, city, specie);
        LOGGER.debug("getByParameters(Date date={}, String countryCode={}, String city={}, RawTelemetryHistoryType specie={}) - end - return value={}", date, countryCode, city, specie, returnRawTelemetryHistory);
        return returnRawTelemetryHistory;
    }

}