package io.qiot.covid19.datahub.importer.service.timeseries;

import java.io.BufferedReader;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.dto.TelemetryImportUploadResult;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;
import io.qiot.covid19.datahub.importer.service.AbstractTelemetryImportService;

@ApplicationScoped
@Typed(TimeseriesTelemetryImportService.class)
public class TimeseriesTelemetryImportService
        extends AbstractTelemetryImportService {

    @Inject
    Logger LOGGER;

    @Override
    protected TelemetryImportUploadResult importTelemetryHistory(
            BufferedReader br) throws DataServiceException {
        throw new NotYetImplementedException();
    }
    
    @Override
    protected int removeDuplicates() {
        throw new NotYetImplementedException();
    }

}
