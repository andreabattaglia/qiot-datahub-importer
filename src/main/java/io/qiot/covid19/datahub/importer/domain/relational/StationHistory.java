package io.qiot.covid19.datahub.importer.domain.relational;

import java.time.LocalDateTime;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;

@Entity
@Cacheable
@Table(name = "stationhistory", indexes = {@Index(name="location_idx", columnList = "city, country", unique = true)})
@RegisterForReflection
public class StationHistory extends PanacheEntity {

    @JsonProperty(value = "city")
//    @com.influxdb.annotations.Column(tag = true)
    @Column(nullable = false)
    public String city;

    @JsonProperty(value = "country")
//    @com.influxdb.annotations.Column(tag = true)
    @Column(nullable = false)
    public String country;

    // only allow geometries with SRID 4326 and not null (be able to create
    // spatial indexes)
//     @Column(nullable = false, columnDefinition = "GEOMETRY SRID 4326")
//     public Point geometry;

    @JsonProperty(value = "longitude")
//    @com.influxdb.ann<otations.Column(tag = true)
    @Column(nullable = false)
    public double longitude;

    @JsonProperty(value = "latitude")
//    @com.influxdb.annotations.Column(tag = true)
    @Column(nullable = false)
    public double latitude;
    
    @Column(name = "imported_on", nullable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    public LocalDateTime importedOn;
}
