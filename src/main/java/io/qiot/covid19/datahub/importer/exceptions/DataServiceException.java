package io.qiot.covid19.datahub.importer.exceptions;

/**
 * The Class DataServiceException.
 *
 * @author andreabattaglia
 */
public class DataServiceException extends Exception {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3488131954013875686L;

    /**
     * Instantiates a new data service exception.
     */
    public DataServiceException() {
        super();
    }

    /**
     * Instantiates a new data service exception.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public DataServiceException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Instantiates a new data service exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public DataServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new data service exception.
     *
     * @param message the message
     */
    public DataServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new data service exception.
     *
     * @param cause the cause
     */
    public DataServiceException(Throwable cause) {
        super(cause);
    }

}
