package io.qiot.covid19.datahub.importer.service.timeseries;

import java.io.BufferedReader;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.dto.TelemetryImportUploadResult;
import io.qiot.covid19.datahub.importer.domain.relational.RawTelemetryHistory;
import io.qiot.covid19.datahub.importer.domain.relational.RawTelemetryHistoryType;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;
import io.qiot.covid19.datahub.importer.service.AbstractTelemetryService;

/**
 * The Class TimeseriesTelemetryImportService.
 *
 * @author andreabattaglia
 */
@ApplicationScoped
@Typed(TimeseriesTelemetryImportService.class)
public class TimeseriesTelemetryImportService extends AbstractTelemetryService {

    /** The logger. */
    @Inject
    Logger LOGGER;

    /**
     * Import telemetry history.
     *
     * @param br the br
     * @return the telemetry import upload result
     * @throws DataServiceException the data service exception
     */
    @Override
    protected TelemetryImportUploadResult importTelemetryHistory(
            BufferedReader br) throws DataServiceException {
        throw new NotYetImplementedException();
    }

    /**
     * Removes the duplicates.
     *
     * @return the int
     */
    @Override
    protected int removeDuplicates() {
        throw new NotYetImplementedException();
    }


    @Override
    public RawTelemetryHistory findParameters(String date, String countryCode,
            String city, RawTelemetryHistoryType specie)
            throws DataServiceException {
        throw new NotYetImplementedException();
    }
}
