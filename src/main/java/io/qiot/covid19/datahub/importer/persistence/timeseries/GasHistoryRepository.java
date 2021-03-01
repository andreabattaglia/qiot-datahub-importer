package io.qiot.covid19.datahub.importer.persistence.timeseries;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.influxdb.client.InfluxDBClientFactory;

import io.qiot.covid19.datahub.importer.domain.timeseries.GasTelemetryHistory;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class GasHistoryRepository extends AbstractRepository<GasTelemetryHistory> {


    @Inject
    Logger LOGGER;
    
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("Connecting to: {}, token: {}, org: {}, bucketId: {}",
                connectionUrl, token, orgId, bucketId);
        influxDBClient = InfluxDBClientFactory.create(connectionUrl,
                token.toCharArray(), orgId, bucketId);
        LOGGER.info("Connection successfull:\n{}",
                influxDBClient.health().toString());
    }
    
    @Override
    protected GasTelemetryHistory[] getEmptyArrayOfType() {
        return new GasTelemetryHistory[0];
    }

    void onStop(@Observes ShutdownEvent ev) {
        influxDBClient.close();
    }
}