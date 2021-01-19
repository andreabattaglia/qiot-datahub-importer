package org.qiot.covid19.datahub.importer.rest;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.qiot.covid19.datahub.importer.service.UploadService;

@ApplicationScoped
@Path("/")
public class ExampleResource {

    @Inject
    UploadService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/stations")
    public String stations() throws IOException {
        return Integer.toString(service.uploadOtherStations());
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/upload")
    public String upload() throws IOException {
        return Integer.toString(service.uploadOldMeasurements());
    }
}