//package io.qiot.covid19.datahub.importer.persistence.timeseries;
//
//import java.util.Arrays;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.eclipse.microprofile.config.inject.ConfigProperty;
//import org.slf4j.Logger;
//
//import com.influxdb.client.InfluxDBClient;
//import com.influxdb.client.WriteApi;
//import com.influxdb.client.domain.WritePrecision;
//import com.influxdb.exceptions.InfluxException;
//
//import io.qiot.covid19.datahub.importer.domain.timeseries.AbstractTelemetryHistory;
//import io.qiot.covid19.datahub.importer.exceptions.TimeseriesPersistenceException;
//
///**
// * The Class AbstractRepository.
// *
// * @author andreabattaglia
// * @param <T> the generic type
// */
//public abstract class AbstractRepository<T extends AbstractTelemetryHistory> {
//
//    /** The logger. */
//    @Inject
//    Logger LOGGER;
//
//    /** The influx DB client. */
//    // public static final Long DATA_RETENTION_DAYS = -14L;
//    protected InfluxDBClient influxDBClient;
//    
//    /** The connection url. */
//    @ConfigProperty(name = "influxdb.connectionUrl")
//    public String connectionUrl;
//    
//    /** The token. */
//    @ConfigProperty(name = "influxdb.token")
//    public String token;
//    
//    /** The org id. */
//    @ConfigProperty(name = "influxdb.orgId")
//    public String orgId;
//    
//    /** The bucket id. */
//    @ConfigProperty(name = "influxdb.data.bucketId")
//    public String bucketId;
//    
//    /** The bucket name. */
//    @ConfigProperty(name = "influxdb.data.bucketName")
//    public String bucketName;
//
//    /**
//     * Save.
//     *
//     * @param data the data
//     * @throws TimeseriesPersistenceException the timeseries persistence exception
//     */
//    public void save(List<T> data) throws TimeseriesPersistenceException {
//        try (WriteApi writeApi = influxDBClient.getWriteApi()) {
//
//            LOGGER.info("Persisting enriched telemetry {}", Arrays.toString(data.toArray(getEmptyArrayOfType())));
//            writeApi.writeMeasurements(WritePrecision.NS, data);
//        } catch (InfluxException ie) {
//            throw new TimeseriesPersistenceException(
//                    "Error while writing data to Influx: " + ie.getMessage());
//        }
//    }
//
//    /**
//     * Gets the empty array of type.
//     *
//     * @return the empty array of type
//     */
//    protected abstract T[] getEmptyArrayOfType();
//}