package com.redhat.qiot.datahub.importer.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.slf4j.Logger;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.redhat.qiot.datahub.importer.domain.MeasurementHistory;
import com.redhat.qiot.datahub.importer.domain.MeasurementHistoryId;
import com.redhat.qiot.datahub.importer.domain.MeasurementHistoryType;
import com.redhat.qiot.datahub.importer.domain.OtherMeasurementStation;
import com.redhat.qiot.datahub.importer.persistence.MeasurementHistoryRepository;
import com.redhat.qiot.datahub.importer.persistence.OtherMeasurementStationRepository;

@ApplicationScoped
public class UploadService {

    private final String TMP_QIOT_MEASUREMENTS_FOLDER = "/tmp/qiot/measurements";
    private final String TMP_QIOT_STATIONS_FOLDER = "/tmp/qiot/stations";

    @Inject
    Logger LOGGER;

    @Inject
    MeasurementHistoryRepository mhRepository;
    @Inject
    OtherMeasurementStationRepository omsRepository;

    public int uploadOtherStations() {
        int total = 0;
        for (Path jsonFilePath : getArchives(TMP_QIOT_STATIONS_FOLDER)) {
            JsonObject jsonObject = null;
            JsonArray stations = null;
            JsonObject stationPlace = null;
            JsonArray coordinates = null;
            List<OtherMeasurementStation> omss;

            try (JsonReader reader = Json
                    .createReader(new FileReader(jsonFilePath.toFile()));) {
                jsonObject = reader.readObject();
            } catch (FileNotFoundException e) {
                return total;
            }
            stations = jsonObject.getJsonArray("data");
            omss = new ArrayList<>(stations.size());
            for (int i = 0; i < stations.size(); i++) {
                stationPlace = stations.getJsonObject(i).getJsonObject("Place");
                coordinates = stationPlace.getJsonArray("geo");
                OtherMeasurementStation oms = new OtherMeasurementStation();
                oms.country = stationPlace.getString("country");
                oms.city = stationPlace.getString("name");
                oms.location = new Point(new Position(Arrays.asList(
                        coordinates.getJsonNumber(1).doubleValue(),
                        coordinates.getJsonNumber(0).doubleValue())));
                LOGGER.debug("Red measurement station {}", oms);
                omss.add(oms);
            }

            omsRepository.saveBulk(omss);
            total += omss.size();
        }
        return total;
    }

    public int uploadOldMeasurements() {

        String line = "";
        String cvsSplitBy = ",";
        int counter = 0, fileTotal = 0, total = 0, i;
        MeasurementHistory mh = null;
        MeasurementHistoryId mhi = null;

        List<MeasurementHistory> mhs;

        for (Path csvFilePath : getArchives(TMP_QIOT_MEASUREMENTS_FOLDER)) {
            LOGGER.info("Now uploading file {}", csvFilePath);
            // df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            try (BufferedReader br = new BufferedReader(
                    new FileReader(csvFilePath.toFile()))) {
                mhs = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    i = 0;
                    // use comma as separator
                    String[] data = line.split(cvsSplitBy);

                    mh = new MeasurementHistory();
                    // mh.date = df.parse(data[i++]);
                    // mh.country = data[i++];
                    // mh.city = data[i++];
                    // mh.specie = data[i++];
                    mhi = new MeasurementHistoryId();
                    mhi.date = data[i++];
                    mhi.country = data[i++];
                    mhi.city = data[i++];
                    try {
                        mhi.specie = MeasurementHistoryType.valueOf(data[i++]);
                    } catch (IllegalArgumentException e) {
                        continue;
                    }
                    mh.id = mhi;
                    mh.count = Integer.parseInt(data[i++]);
                    mh.min = Double.parseDouble(data[i++]);
                    mh.max = Double.parseDouble(data[i++]);
                    mh.median = Double.parseDouble(data[i++]);
                    mh.variance = Double.parseDouble(data[i++]);

                    LOGGER.debug("Red measurement history {}", mh);

                    mhs.add(mh);

                    if (++counter == 10000) {
                        mhRepository.saveBulk(mhs);
                        fileTotal += counter;
                        LOGGER.info("Measurement History uploaded so far: {}",
                                fileTotal);
                        mhs.clear();
                        counter = 0;
                    }

                }
                mhRepository.saveBulk(mhs);
                fileTotal += counter;
                LOGGER.info("Measurement History uploaded so far: {}",
                        fileTotal);
                mhs.clear();
                LOGGER.info(
                        "Measurement History upload process for file {} complete. Uploaded {} documents.",
                        csvFilePath, fileTotal);
                total += fileTotal;
                fileTotal = 0;
            } catch (IOException e) {
                LOGGER.error(
                        "An error occurred parsing MeasurementHistpory archives",
                        e);
            }
        }
        LOGGER.info(
                "Measurement History upload process complete! uploaded {} documents",
                total);
        return total;
    }

    private List<Path> getArchives(String folderPath) {
        try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
            return paths.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error(
                    "An error occurred while retrieving the list of files from "
                            + folderPath,
                    e);
        }
        return new ArrayList<>();
    }

}
