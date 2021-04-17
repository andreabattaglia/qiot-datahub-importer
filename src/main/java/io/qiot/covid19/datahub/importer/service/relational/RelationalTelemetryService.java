package io.qiot.covid19.datahub.importer.service.relational;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.dto.TelemetryImportUploadResult;
import io.qiot.covid19.datahub.importer.domain.relational.RawTelemetryHistory;
import io.qiot.covid19.datahub.importer.domain.relational.RawTelemetryHistoryType;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;
import io.qiot.covid19.datahub.importer.exceptions.RawTelemetryTransformationException;
import io.qiot.covid19.datahub.importer.persistence.relational.RawTelemetryHistoryRepository;
import io.qiot.covid19.datahub.importer.service.AbstractTelemetryService;

/**
 * The Class RelationalTelemetryService.
 *
 * @author andreabattaglia
 */
@ApplicationScoped
@Typed(RelationalTelemetryService.class)
public class RelationalTelemetryService extends AbstractTelemetryService {

    /** The logger. */
    @Inject
    Logger LOGGER;

    /** The raw telemetry repository. */
    @Inject
    RawTelemetryHistoryRepository rawTelemetryRepository;

    /**
     * Import telemetry history.
     *
     * @param br
     *            the br
     * @return the telemetry import upload result
     * @throws DataServiceException
     *             the data service exception
     */
    @Override
    protected TelemetryImportUploadResult importTelemetryHistory(
            BufferedReader br) throws DataServiceException {
        TelemetryImportUploadResult result = new TelemetryImportUploadResult();
        int counter = 0;

        List<RawTelemetryHistory> rawTelemetryHistories;
        String line = "";
        rawTelemetryHistories = new ArrayList<>();

        for (int i = 0; i < 5; i++)
            try {
                br.readLine();
            } catch (IOException e) {
            }

        try {
            while ((line = br.readLine()) != null) {
                try {
                    rawTelemetryHistories.add(lineToPojo(line));
                    counter++;
                    if (counter % 10000 == 0) {
                        rawTelemetryRepository.persist(rawTelemetryHistories);
                        rawTelemetryRepository.flush();
                        LOGGER.info("Measurement History uploaded so far: {}",
                                counter);
                        rawTelemetryHistories.clear();
                    }
                } catch (ParseException e) {
                    LOGGER.error("An error occurred reading the curernt line: "
                            + line, e);
                    result.errors++;
                } catch (RawTelemetryTransformationException e) {
                    result.ignored++;
                }
            }
        } catch (IOException e) {
            LOGGER.error(
                    "An error occurred reading the next line from the stream.",
                    e);
            result.errors++;
        }
        rawTelemetryRepository.persist(rawTelemetryHistories);
        result.successes = counter;
        rawTelemetryHistories.clear();

        return result;
    }

    /**
     * Removes the duplicates.
     *
     * @return the int
     */
    @Override
    protected int removeDuplicates() {
        LOGGER.info("Cleaning up duplicates...");
        int dedup = rawTelemetryRepository.dedup();
        LOGGER.info("removed {} duplicates from raw telemetry table", dedup);
        return dedup;
    }

    /**
     * Line to pojo.
     *
     * @param line
     *            the line
     * @return the raw telemetry history
     * @throws RawTelemetryTransformationException
     *             the raw telemetry transformation exception
     * @throws ParseException
     *             the parse exception
     */
    private RawTelemetryHistory lineToPojo(String line)
            throws RawTelemetryTransformationException, ParseException {

        RawTelemetryHistory rth = new RawTelemetryHistory();

        int i = 0;
        String[] data = line.split(SEPARATOR);
        rth.date = df.parse(data[i++]);
        rth.country = data[i++];
        rth.city = data[i++];
        try {
            rth.specie = RawTelemetryHistoryType.valueOf(data[i++]);
        } catch (IllegalArgumentException e) {
            throw new RawTelemetryTransformationException(e);
        }
        rth.count = Integer.parseInt(data[i++]);
        rth.min = Double.parseDouble(data[i++]);
        rth.max = Double.parseDouble(data[i++]);
        rth.median = Double.parseDouble(data[i++]);
        rth.variance = Double.parseDouble(data[i++]);
        return rth;

    }

    @Override
    public RawTelemetryHistory findParameters(String date, String countryCode,
            String city, RawTelemetryHistoryType specie)
            throws DataServiceException {
        try {
            return rawTelemetryRepository.find(
                    "from RawTelemetryHistory where date = ?1 and country = ?2 and city = ?3 and specie = ?4",
                    df.parse(date), countryCode, city, specie).singleResult();
        } catch (ParseException e) {
            throw new DataServiceException(e);
        }
    }

}
