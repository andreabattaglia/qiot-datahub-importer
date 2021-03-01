package io.qiot.covid19.datahub.importer.domain.relational;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

@Entity
@Table(name = "rawtelemetryhistory", indexes = {@Index(name="search_idx", columnList = "date, country, city, specie", unique = false)})
@RegisterForReflection
public class RawTelemetryHistory extends PanacheEntityBase {
    
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Type(type="pg-uuid")
    public UUID id;
    @Column(nullable = true, columnDefinition = "DATE")
    public Date date;
    @Column(nullable = true)
    public String country;
    @Column(nullable = true)
    public String city;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    public RawTelemetryHistoryType specie;
    @Column(nullable = true)
    public int count;
    @Column(nullable = true)
    public double min;
    @Column(nullable = true)
    public double max;
    @Column(nullable = true)
    public double median;
    @Column(nullable = true)
    public double variance;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((specie == null) ? 0 : specie.hashCode());
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
        RawTelemetryHistory other = (RawTelemetryHistory) obj;
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
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (specie != other.specie)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MeasurementHistory [date=" + date + ", country=" + country
                + ", city=" + city + ", specie=" + specie + ", count=" + count
                + ", min=" + min + ", max=" + max + ", median=" + median
                + ", variance=" + variance + "]";
    }

}
