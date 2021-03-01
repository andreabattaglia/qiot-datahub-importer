package io.qiot.covid19.datahub.importer.config;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.service.TelemetryImportService;
import io.qiot.covid19.datahub.importer.service.relational.RelationalTelemetryImportService;
import io.qiot.covid19.datahub.importer.service.timeseries.TimeseriesTelemetryImportService;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
public class TelemetryImporterServiceConfiguration {

    @Inject
    Logger LOGGER;

    @Inject
    RelationalTelemetryImportService relationalTelemetryImportService;
    @Inject
    TimeseriesTelemetryImportService timeseriesTelemetryImportService;

    @Produces
    @IfBuildProperty(name = "app.telemetry.historycal.timeseries",
            stringValue = "true")
    public TelemetryImportService timeseriesImportService() {
        return timeseriesTelemetryImportService;
    }

    @Produces
    @DefaultBean
    public TelemetryImportService relationalImportService() {
        return relationalTelemetryImportService;
    }
}