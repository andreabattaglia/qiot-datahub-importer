package io.qiot.covid19.datahub.importer.service;

import java.util.List;

import io.qiot.covid19.datahub.importer.domain.dto.HistoricalDataPeriod;
import io.qiot.covid19.datahub.importer.domain.dto.TelemetryImportUploadResult;
import io.qiot.covid19.datahub.importer.domain.relational.RawTelemetryHistory;
import io.qiot.covid19.datahub.importer.domain.relational.RawTelemetryHistoryType;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;

/**
 * The Interface TelemetryImportService.
 *
 * @author andreabattaglia
 */
public interface TelemetryService {

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
     * @param perion the perion
     * @return the telemetry import upload result
     * @throws DataServiceException the data service exception
     */
    TelemetryImportUploadResult importSingleTelemetry(
            HistoricalDataPeriod perion) throws DataServiceException;

    /**
     * Find parameters.
     *
     * @param date the date
     * @param countryCode the country code
     * @param city the city
     * @param specie the specie
     * @return the raw telemetry history
     * @throws DataServiceException 
     */
    RawTelemetryHistory findParameters(String date, String countryCode, String city,
            RawTelemetryHistoryType specie) throws DataServiceException;
}