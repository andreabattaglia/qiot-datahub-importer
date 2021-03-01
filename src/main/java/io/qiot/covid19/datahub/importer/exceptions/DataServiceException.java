package io.qiot.covid19.datahub.importer.exceptions;

public class DataServiceException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 3488131954013875686L;

    public DataServiceException() {
        super();
    }

    public DataServiceException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DataServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataServiceException(String message) {
        super(message);
    }

    public DataServiceException(Throwable cause) {
        super(cause);
    }

}
