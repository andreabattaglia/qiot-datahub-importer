package io.qiot.covid19.datahub.importer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.dto.HistoricalDataPeriod;
import io.qiot.covid19.datahub.importer.domain.dto.TelemetryImportUploadResult;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;

public abstract class AbstractTelemetryImportService
        implements TelemetryImportService {
    protected final DateFormat df = new SimpleDateFormat("yyyy-MM-dd",
            Locale.ENGLISH);

    protected final String SEPARATOR = ",";

    /** The base url. */
    @ConfigProperty(name = "app.aqicn.telemetry.baseurl")
    protected String baseUrl;

    /** The token. */
    @ConfigProperty(name = "app.aqicn.token")
    protected String token;

    @Inject
    Logger LOGGER;

    @Override
    public List<TelemetryImportUploadResult> importAllAvailableTelemetry()
            throws DataServiceException {
        List<TelemetryImportUploadResult> results = new ArrayList<>();
        for (HistoricalDataPeriod period : HistoricalDataPeriod.values())
            results.add(importSingleTelemetry(period));
        return results;
    }

    @Override
    public TelemetryImportUploadResult importSingleTelemetry(
            HistoricalDataPeriod period) throws DataServiceException {
        URL website;
        try {
            String onlineSourceURL = baseUrl + "/" + token + "/"
                    + period.getPeriod();
            LOGGER.info("importing raw telemetry from source {}",
                    onlineSourceURL);
            website = new URL(onlineSourceURL);
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(website.openStream()));) {
                TelemetryImportUploadResult importResult = importTelemetryHistory(
                        br);
                importResult.duplicates = removeDuplicates();
                return importResult;
            } catch (IOException e) {
                throw new DataServiceException(
                        "An error occurred reading the historical data source",
                        e);
            } catch (DataServiceException e) {
                throw new DataServiceException(
                        "An error occurred importing the historical data source",
                        e);
            }
        } catch (MalformedURLException e) {
            throw new DataServiceException(
                    "An error occurred connecting to the historical data source",
                    e);
        }
    }

    @Transactional
    protected abstract TelemetryImportUploadResult importTelemetryHistory(
            BufferedReader br) throws DataServiceException;

    @Transactional
    protected abstract int removeDuplicates();
}
