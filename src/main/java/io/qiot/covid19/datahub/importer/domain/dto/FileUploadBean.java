package io.qiot.covid19.datahub.importer.domain.dto;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 * The Class FileUploadBean.
 *
 * @author andreabattaglia
 */
public class FileUploadBean {
    
    /** The file name. */
    @FormParam("filename")
    @PartType(MediaType.TEXT_PLAIN)
    public String fileName = null;

    /** The content. */
    @FormParam("file")
    public byte[] content = null;
}
