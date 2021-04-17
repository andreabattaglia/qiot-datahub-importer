package io.qiot.covid19.datahub.importer.config;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.service.TelemetryService;
import io.qiot.covid19.datahub.importer.service.relational.RelationalTelemetryService;
import io.qiot.covid19.datahub.importer.service.timeseries.TimeseriesTelemetryImportService;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.properties.IfBuildProperty;

/**
 * The Class TelemetryImporterServiceConfiguration.
 *
 * @author andreabattaglia
 */
@Dependent
public class TelemetryServiceConfiguration {

    /** The logger. */
    @Inject
    Logger LOGGER;

    /** The relational telemetry import service. */
    @Inject
    RelationalTelemetryService relationalTelemetryImportService;
    
    /** The timeseries telemetry import service. */
    @Inject
    TimeseriesTelemetryImportService timeseriesTelemetryImportService;

    /**
     * Timeseries import service.
     *
     * @return the telemetry service
     */
    @Produces
    @IfBuildProperty(name = "app.telemetry.historycal.timeseries",
            stringValue = "true")
    public TelemetryService timeseriesImportService() {
        return timeseriesTelemetryImportService;
    }

    /**
     * Relational import service.
     *
     * @return the telemetry service
     */
    @Produces
    @DefaultBean
    public TelemetryService relationalImportService() {
        return relationalTelemetryImportService;
    }
}