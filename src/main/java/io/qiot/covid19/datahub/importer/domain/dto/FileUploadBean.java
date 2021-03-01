package io.qiot.covid19.datahub.importer.domain.dto;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FileUploadBean {
    @FormParam("filename")
    @PartType(MediaType.TEXT_PLAIN)
    public String fileName = null;

    @FormParam("file")
    public byte[] content = null;
}
