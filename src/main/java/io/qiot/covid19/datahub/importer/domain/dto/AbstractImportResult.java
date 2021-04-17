package io.qiot.covid19.datahub.importer.domain.dto;

import javax.json.bind.annotation.JsonbProperty;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * The Class AbstractImportResult.
 *
 * @author andreabattaglia
 */
@RegisterForReflection
public abstract class AbstractImportResult {
    
    /** The successes. */
    @JsonbProperty
    public int successes;
    
    /** The errors. */
    @JsonbProperty
    public int errors;
    
    /** The ignored. */
    @JsonbProperty
    public int ignored;
    
    /** The duplicates. */
    @JsonbProperty
    public int duplicates;
}
