package io.qiot.covid19.datahub.importer.domain.dto;

import javax.json.bind.annotation.JsonbProperty;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class TelemetryImportUploadResult extends AbstractImportResult {
    @JsonbProperty
    public int gas;
    @JsonbProperty
    public int pollution;

}
