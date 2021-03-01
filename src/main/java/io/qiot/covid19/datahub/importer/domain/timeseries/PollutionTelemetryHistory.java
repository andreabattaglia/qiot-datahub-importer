package io.qiot.covid19.datahub.importer.domain.timeseries;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import io.quarkus.runtime.annotations.RegisterForReflection;

/*
 {
"stationId":int,
"instant":long,
"pm1_0":int,
"pm2_5":int,
"pm10":int,
"pm1_0_atm":int,
“pm2_5_atm":int,
"pm10_atm":int,
"gt0_3um":int,
"gt0_5um":int,
"gt1_0um":int,
"gt2_5um":int,
"gt5_0um":int
"gt10um":int
}
 */
@RegisterForReflection
@Measurement(name = "pollutionhistory")
//@Entity
//@Table(name = "temporarypollutionhistory")
public class PollutionTelemetryHistory extends AbstractTelemetryHistory {

    @JsonProperty(value = "pm1_0")
    @Column
    public int pm1_0;
    @JsonProperty(value = "pm2_5")
    @Column
    public int pm2_5;
    @JsonProperty(value = "pm10")
    @Column
    public int pm10;
    @JsonProperty(value = "pm1_0_atm")
    @Column
    public int pm1_0_atm;
    @JsonProperty(value = "pm2_5_atm")
    @Column
    public int pm2_5_atm;
    @JsonProperty(value = "pm10_atm")
    @Column
    public int pm10_atm;
    @JsonProperty(value = "gt0_3um")
    @Column
    public int gt0_3um;
    @JsonProperty(value = "gt0_5um")
    @Column
    public int gt0_5um;
    @JsonProperty(value = "gt1_0um")
    @Column
    public int gt1_0um;
    @JsonProperty(value = "gt2_5um")
    @Column
    public int gt2_5um;
    @JsonProperty(value = "gt5_0um")
    @Column
    public int gt5_0um;
    @JsonProperty(value = "gt10um")
    @Column
    public int gt10um;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PollutionTelemetryHistory [time=");
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
        builder.append(", pm1_0=");
        builder.append(pm1_0);
        builder.append(", pm2_5=");
        builder.append(pm2_5);
        builder.append(", pm10=");
        builder.append(pm10);
        builder.append(", pm1_0_atm=");
        builder.append(pm1_0_atm);
        builder.append(", pm2_5_atm=");
        builder.append(pm2_5_atm);
        builder.append(", pm10_atm=");
        builder.append(pm10_atm);
        builder.append(", gt0_3um=");
        builder.append(gt0_3um);
        builder.append(", gt0_5um=");
        builder.append(gt0_5um);
        builder.append(", gt1_0um=");
        builder.append(gt1_0um);
        builder.append(", gt2_5um=");
        builder.append(gt2_5um);
        builder.append(", gt5_0um=");
        builder.append(gt5_0um);
        builder.append(", gt10um=");
        builder.append(gt10um);
        builder.append("]");
        return builder.toString();
    }

}
