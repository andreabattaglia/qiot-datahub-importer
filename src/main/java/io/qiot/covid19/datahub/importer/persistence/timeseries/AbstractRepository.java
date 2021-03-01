package io.qiot.covid19.datahub.importer.persistence.timeseries;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.exceptions.InfluxException;

import io.qiot.covid19.datahub.importer.domain.timeseries.AbstractTelemetryHistory;
import io.qiot.covid19.datahub.importer.exceptions.TimeseriesPersistenceException;

public abstract class AbstractRepository<T extends AbstractTelemetryHistory> {

    @Inject
    Logger LOGGER;

    // public static final Long DATA_RETENTION_DAYS = -14L;
    protected InfluxDBClient influxDBClient;
    @ConfigProperty(name = "influxdb.connectionUrl")
    public String connectionUrl;
    @ConfigProperty(name = "influxdb.token")
    public String token;
    @ConfigProperty(name = "influxdb.orgId")
    public String orgId;
    @ConfigProperty(name = "influxdb.data.bucketId")
    public String bucketId;
    @ConfigProperty(name = "influxdb.data.bucketName")
    public String bucketName;

    public void save(List<T> data) throws TimeseriesPersistenceException {
        try (WriteApi writeApi = influxDBClient.getWriteApi()) {

            LOGGER.info("Persisting enriched telemetry {}", Arrays.toString(data.toArray(getEmptyArrayOfType())));
            writeApi.writeMeasurements(WritePrecision.NS, data);
        } catch (InfluxException ie) {
            throw new TimeseriesPersistenceException(
                    "Error while writing data to Influx: " + ie.getMessage());
        }
    }

    protected abstract T[] getEmptyArrayOfType();
}