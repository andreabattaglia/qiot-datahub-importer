package io.qiot.covid19.datahub.importer.domain.timeseries;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@Measurement(name = "gashistory")
//@Entity
//@Table(name = "temporarygashistory")
public class GasTelemetryHistory extends AbstractTelemetryHistory {

    @JsonProperty(value = "adc")
    @Column
    public Double adc;
    @JsonProperty(value = "nh3")
    @Column
    public Double nh3;
    @JsonProperty(value = "oxidising")
    @Column
    public Double oxidising;
    @JsonProperty(value = "reducing")
    @Column
    public Double reducing;

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
