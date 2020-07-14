package com.redhat.qiot.datahub.importer.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.redhat.qiot.datahub.importer.domain.MeasurementHistory;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class MeasurementHistoryRepository {
    private static final String MEASUREMENTHISTORY = "measurementhistory";

    @Inject
    Logger LOGGER;

    @Inject
    MongoClient mongoClient;
    MongoDatabase qiotDatabase;
    MongoCollection<MeasurementHistory> measurementHistoryCollection;
    CodecProvider pojoCodecProvider;
    CodecRegistry pojoCodecRegistry;

    void onStart(@Observes StartupEvent ev) {
    }

    @PostConstruct
    void init() {

        qiotDatabase = mongoClient.getDatabase("qiot");
        try {
            qiotDatabase.createCollection(MEASUREMENTHISTORY);
        } catch (Exception e) {
            LOGGER.info("Collection \"measurementhistory\" already exists");
        }
        measurementHistoryCollection = qiotDatabase
                .getCollection(MEASUREMENTHISTORY, MeasurementHistory.class);
        /*
         * ensure indexes exist
         */
        ensureIndexes();

        // Create a CodecRegistry containing the PojoCodecProvider instance.
        pojoCodecProvider = PojoCodecProvider.builder()
                .register("com.redhat.qiot.datahub.importer.domain")
                .automatic(true).build();
        pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider));
        measurementHistoryCollection = measurementHistoryCollection
                .withCodecRegistry(pojoCodecRegistry);
    }

    private void ensureIndexes() {
        // IndexOptions indexOptions = new IndexOptions().unique(true);
        // measurementHistoryCollection.createIndex(
        // Indexes.ascending("date", "country", "city", "specie"),
        // indexOptions);
    }

    // public void save(String country, String city, double longitude,
    // double latitude) {
    //
    // Document document = null;
    //
    // document = new Document();
    // document.put("country", country);
    // document.put("city", city);
    // document.put("location", new Point(new Position(longitude, latitude)));
    // measurementHistoryCollection.insertOne(document);
    // }
    //
    // public void saveBulk(List<MeasurementHistory> measurements) {
    // List<WriteModel<Document>> documents = new ArrayList<>();
    // for (MeasurementHistory measurement : measurements) {
    // documents.add(new InsertOneModel<Document>(mhToDoc(measurement)));
    // }
    // measurementHistoryCollection.bulkWrite(documents);
    // }

    // private Document mhToDoc(MeasurementHistory measurement) {
    //
    // Document document = null;
    //
    // document = new Document();
    // document.put("date", measurement.date);
    // document.put("country", measurement.country);
    // document.put("city", measurement.city);
    //
    // document.put("specie", measurement.specie);
    // document.put("count", measurement.count);
    // document.put("min", measurement.min);
    // document.put("max", measurement.max);
    // document.put("median", measurement.median);
    // document.put("variance", measurement.variance);
    // return document;
    // }

    public void saveBulk(List<MeasurementHistory> measurements) {
        measurementHistoryCollection.insertMany(measurements);
    }
}
