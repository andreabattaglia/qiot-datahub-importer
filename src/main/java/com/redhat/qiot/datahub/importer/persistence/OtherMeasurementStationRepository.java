package com.redhat.qiot.datahub.importer.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.bson.Document;
import org.slf4j.Logger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.redhat.qiot.datahub.importer.domain.OtherMeasurementStation;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class OtherMeasurementStationRepository {
    private final String OTHER_MEASUREMENT_STATION = "othermeasurementstation";

    @Inject
    Logger LOGGER;

    @Inject

    MongoClient mongoClient;
    MongoDatabase qiotDatabase;
    MongoCollection<Document> otherMeasurementStationCollection;

    void onStart(@Observes StartupEvent ev) {
    }

    @PostConstruct
    void init() {
        qiotDatabase = mongoClient.getDatabase("qiot");
        try {
            qiotDatabase.createCollection(OTHER_MEASUREMENT_STATION);
        } catch (Exception e) {
            LOGGER.info(
                    "Collection \"othermeasurementstation\" already exists");
        }
        otherMeasurementStationCollection = qiotDatabase
                .getCollection(OTHER_MEASUREMENT_STATION);
        /*
         * ensure indexes exist
         */
        ensureIndexes();
    }

    private void ensureIndexes() {
        IndexOptions indexOptions = new IndexOptions().unique(true);
//        otherMeasurementStationCollection
//                .createIndex(Indexes.ascending("serial"));
//        otherMeasurementStationCollection.createIndex(
//                Indexes.ascending("serial", "timestamp" + ""), indexOptions);
        otherMeasurementStationCollection
                .createIndex(Indexes.geo2dsphere("location"));
    }

    public void save(String country, String city, double longitude,
            double latitude) {

        Document document = null;

        document = new Document();
        document.put("country", country);
        document.put("city", city);
        document.put("location", new Point(new Position(longitude, latitude)));
        otherMeasurementStationCollection.insertOne(document);
    }

    public void saveBulk(List<OtherMeasurementStation> stations) {
        List<WriteModel<Document>> documents = new ArrayList<>();
        for (OtherMeasurementStation station : stations) {
            documents.add(new InsertOneModel<Document>(omsToDoc(station)));
        }
        otherMeasurementStationCollection.bulkWrite(documents);
    }

    private Document omsToDoc(OtherMeasurementStation station) {

        Document document = null;

        document = new Document();
        document.put("country", station.country);
        document.put("city", station.city);
        document.put("location",
                new Point(new Position(station.longitude, station.latitude)));

        return document;
    }
}
