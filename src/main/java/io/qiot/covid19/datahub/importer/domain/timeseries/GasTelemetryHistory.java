package io.qiot.covid19.datahub.importer.domain.timeseries;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * The Class GasTelemetryHistory.
 *
 * @author andreabattaglia
 */
@RegisterForReflection
@Measurement(name = "gashistory")
//@Entity
//@Table(name = "temporarygashistory")
public class GasTelemetryHistory extends AbstractTelemetryHistory {

    /** The adc. */
    @JsonProperty(value = "adc")
    @Column
    public Double adc;
    
    /** The nh 3. */
    @JsonProperty(value = "nh3")
    @Column
    public Double nh3;
    
    /** The oxidising. */
    @JsonProperty(value = "oxidising")
    @Column
    public Double oxidising;
    
    /** The reducing. */
    @JsonProperty(value = "reducing")
    @Column
    public Double reducing;

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GasTelemetryHistory [time=");
        builder.append(time);
        builder.append(", city=");
        builder.append(city);
        builder.append(", country=");
        builder.append(country);
//        builder.append(", longitude=");
//        builder.append(geometry.getX());
//        builder.append(", latitude=");
//        builder.append(geometry.getY());
        builder.append(", longitude=");
        builder.append(longitude);
        builder.append(", latitude=");
        builder.append(latitude);
        builder.append(", adc=");
        builder.append(adc);
        builder.append(", nh3=");
        builder.append(nh3);
        builder.append(", oxidising=");
        builder.append(oxidising);
        builder.append(", reducing=");
        builder.append(reducing);
        builder.append("]");
        return builder.toString();
    }

}
