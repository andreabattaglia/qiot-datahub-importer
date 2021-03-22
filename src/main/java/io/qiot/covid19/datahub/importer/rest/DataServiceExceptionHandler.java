package io.qiot.covid19.datahub.importer.rest;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;

import io.qiot.covid19.datahub.importer.exceptions.DataServiceException;

/**
 * The Class DataServiceExceptionHandler.
 *
 * @author andreabattaglia
 */
@Provider
public class DataServiceExceptionHandler
        implements ExceptionMapper<DataServiceException> {

    /** The logger. */
    @Inject
    Logger LOGGER;

    /**
     * To response.
     *
     * @param exception
     *            the exception
     * @return the response
     */
    @Override
    public Response toResponse(DataServiceException exception) {
        LOGGER.error("An error has occurred serving the request", exception);
        return Response.status(Status.BAD_REQUEST)
                .entity(exception.getMessage()).build();
    }
}