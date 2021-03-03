package io.qiot.covid19.datahub.importer.exceptions;

/**
 * The Class TimeseriesPersistenceException.
 *
 * @author andreabattaglia
 */
public class TimeseriesPersistenceException extends Exception {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3488131954013875686L;

    /**
     * Instantiates a new timeseries persistence exception.
     */
    public TimeseriesPersistenceException() {
        super();
    }

    /**
     * Instantiates a new timeseries persistence exception.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public TimeseriesPersistenceException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Instantiates a new timeseries persistence exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public TimeseriesPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new timeseries persistence exception.
     *
     * @param message the message
     */
    public TimeseriesPersistenceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new timeseries persistence exception.
     *
     * @param cause the cause
     */
    public TimeseriesPersistenceException(Throwable cause) {
        super(cause);
    }

}
