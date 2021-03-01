package io.qiot.covid19.datahub.importer.service;

import java.util.List;

import io.qiot.covid19.datahub.importer.domain.dto.HistoricalDataPeriod;
import io.qiot.covid19.datahub.importer.domain.dto.TelemetryImportUploadResult;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;

/**
 * The Interface TelemetryImportService.
 *
 * @author andreabattaglia
 */
public interface TelemetryImportService {

    /**
     * Import all available telemetry.
     *
     * @return the list
     * @throws DataServiceException the data service exception
     */
    List<TelemetryImportUploadResult> importAllAvailableTelemetry()
            throws DataServiceException;

    /**
     * Import telemetry history.
     *
     * @param br the br
     * @return the telemetry import upload result
     * @throws DataServiceException the data service exception
     */
    TelemetryImportUploadResult importSingleTelemetry(
            HistoricalDataPeriod perion) throws DataServiceException;
}