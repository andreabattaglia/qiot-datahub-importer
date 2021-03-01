package io.qiot.covid19.datahub.importer.domain.dto;

import javax.json.bind.annotation.JsonbProperty;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public abstract class AbstractImportResult {
    @JsonbProperty
    public int successes;
    @JsonbProperty
    public int errors;
    @JsonbProperty
    public int ignored;
    @JsonbProperty
    public int duplicates;
}
