package io.qiot.covid19.datahub.importer.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.dto.HistoricalDataPeriod;
import io.qiot.covid19.datahub.importer.domain.dto.TelemetryImportUploadResult;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;
import io.qiot.covid19.datahub.importer.service.TelemetryImportService;

/**
 * The Class TelemetryImportResource.
 *
 * @author andreabattaglia
 */
@ApplicationScoped
@Path("/telemetry")
public class TelemetryImportResource {

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
    TelemetryImportService telemetryImportService;

    /**
     * Download and print.
     *
     * @param period
     *            the period
     * @return the string
     * @throws DataServiceException
     */
    @GET
    @Path("/{period}")
    @Consumes({ MediaType.TEXT_PLAIN })
    @Produces({ MediaType.APPLICATION_JSON })
    public TelemetryImportUploadResult importSingleTelemetry(
            @PathParam("period") String period) throws DataServiceException {
        return telemetryImportService
                .importSingleTelemetry(HistoricalDataPeriod.valueOf(period));
    }

    /**
     * Download and print.
     *
     * @param period
     *            the period
     * @return the string
     * @throws DataServiceException
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<TelemetryImportUploadResult> importAllAvailableTelemetry()
            throws DataServiceException {
        return telemetryImportService.importAllAvailableTelemetry();
    }
}