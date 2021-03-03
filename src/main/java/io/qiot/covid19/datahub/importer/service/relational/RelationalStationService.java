package io.qiot.covid19.datahub.importer.service.relational;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.domain.dto.StationImportResult;
import io.qiot.covid19.datahub.importer.domain.relational.StationHistory;
import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;
import io.qiot.covid19.datahub.importer.persistence.relational.StationHistoryRepository;

/**
 * The Class StationImportService.
 *
 * @author andreabattaglia
 */
@ApplicationScoped
public class RelationalStationService {

    /** The logger. */
    @Inject
    Logger LOGGER;

    /** The url. */
    @ConfigProperty(name = "app.aqicn.stations.url")
    String url;

//    /** The gfactory. */
//    @Inject
//    GeometryFactory gfactory;

    /** The station repository. */
    /*
     * RELATIONAL
     */
    @Inject
    StationHistoryRepository stationRepository;

    /**
     * Import from url.
     *
     * @return the string
     * @throws DataServiceException the data service exception
     */
    public StationImportResult importFromUrl() throws DataServiceException {
        JsonObject jsonObject;
        StationImportResult results = null;
        JsonArray stationObjects = null;
        List<StationHistory> omss;
        URL website;
        try {
            website = new URL(url);
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(website.openStream()));
                    JsonReader jsonReader = Json.createReader(br);) {
                jsonObject = jsonReader.readObject();

                stationObjects = jsonObject.getJsonArray("data");
                omss = convertStations(stationObjects);

                results = new StationImportResult();
                stationRepository.persist(omss);
                results.successes += omss.size();
            } catch (MalformedURLException e) {
                throw new DataServiceException(
                        "An error occurred connecting to the historical data source",
                        e);
            } catch (IOException e1) {
                throw new DataServiceException(
                        "An error occurred reading the historical data source",
                        e1);
            }
        } catch (MalformedURLException e2) {
            throw new DataServiceException(
                    "An error occurred connecting to the historical data source",
                    e2);
        }
        return results;
    }

    /**
     * Convert stations.
     *
     * @param stationObjects
     *            the station objects
     * @return the list
     */
    private List<StationHistory> convertStations(JsonArray stationObjects) {
        List<StationHistory> omss;
        omss = new ArrayList<>(stationObjects.size());
        for (int i = 0; i < stationObjects.size(); i++) {
            JsonObject stationObject = stationObjects.getJsonObject(i);
            StationHistory oms = jsonObjectToPojo(stationObject);
            LOGGER.debug("Red measurement station {}", oms);
            omss.add(oms);
        }
        return omss;
    }

    /**
     * Json object to pojo.
     *
     * @param stationObject
     *            the station object
     * @return the station history
     */
    private StationHistory jsonObjectToPojo(JsonObject stationObject) {
        JsonObject stationPlace = null;
        JsonArray coordinates = null;
        StationHistory stationHistory = null;
        stationPlace = stationObject.getJsonObject("Place");
        coordinates = stationPlace.getJsonArray("geo");
        stationHistory = new StationHistory();
        stationHistory.country = stationPlace.getString("country");
        stationHistory.city = stationPlace.getString("name");
        // stationHistory.geometry = gfactory.createPoint(
        // new Coordinate(coordinates.getJsonNumber(1).doubleValue(),
        // coordinates.getJsonNumber(0).doubleValue()));
        stationHistory.longitude = coordinates.getJsonNumber(1).doubleValue();
        stationHistory.latitude = coordinates.getJsonNumber(0).doubleValue();

        return stationHistory;
    }

    /**
     * Find by location.
     *
     * @param countryCode the country code
     * @param city the city
     * @return the station history
     */
    public StationHistory findByLocation(String countryCode, String city) {
        throw new NotYetImplementedException();
    }

}
