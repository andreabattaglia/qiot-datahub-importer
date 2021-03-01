package io.qiot.covid19.datahub.importer.domain.timeseries;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.influxdb.annotations.Column;

import io.qiot.covid19.datahub.importer.domain.relational.StationHistory;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public abstract class AbstractTelemetryHistory extends StationHistory {

    @JsonProperty(value = "instant")
    @Column(timestamp = true)
    public Instant time;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractTelemetryHistory other = (AbstractTelemetryHistory) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

    

}
