package io.qiot.covid19.datahub.importer.domain.relational;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Cacheable;
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

/**
 * The Class RawTelemetryHistory.
 *
 * @author andreabattaglia
 */
@Entity
@Cacheable
@Table(name = "rawtelemetryhistory", indexes = {@Index(name="rawtelemetryhistory_search_idx", columnList = "date, country, city, specie", unique = false)})
@RegisterForReflection
public class RawTelemetryHistory extends PanacheEntityBase {
    
    /** The id. */
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Type(type="pg-uuid")
    public UUID id;
    
    /** The date. */
    @Column(nullable = true, columnDefinition = "DATE")
    public Date date;
    
    /** The country. */
    @Column(nullable = true)
    public String country;
    
    /** The city. */
    @Column(nullable = true)
    public String city;
    
    /** The specie. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    public RawTelemetryHistoryType specie;
    
    /** The count. */
    @Column(nullable = true)
    public int count;
    
    /** The min. */
    @Column(nullable = true)
    public double min;
    
    /** The max. */
    @Column(nullable = true)
    public double max;
    
    /** The median. */
    @Column(nullable = true)
    public double median;
    
    /** The variance. */
    @Column(nullable = true)
    public double variance;

    /**
     * Hash code.
     *
     * @return the int
     */
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

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
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

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "MeasurementHistory [date=" + date + ", country=" + country
                + ", city=" + city + ", specie=" + specie + ", count=" + count
                + ", min=" + min + ", max=" + max + ", median=" + median
                + ", variance=" + variance + "]";
    }

}
