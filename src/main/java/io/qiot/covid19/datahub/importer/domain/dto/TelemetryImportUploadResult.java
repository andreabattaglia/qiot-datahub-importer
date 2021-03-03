package io.qiot.covid19.datahub.importer.domain.dto;

import javax.json.bind.annotation.JsonbProperty;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * The Class TelemetryImportUploadResult.
 *
 * @author andreabattaglia
 */
@RegisterForReflection
public class TelemetryImportUploadResult extends AbstractImportResult {
    
    /** The gas. */
    @JsonbProperty
    public int gas;
    
    /** The pollution. */
    @JsonbProperty
    public int pollution;

}
