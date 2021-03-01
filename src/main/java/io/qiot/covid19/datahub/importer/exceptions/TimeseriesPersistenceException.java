package io.qiot.covid19.datahub.importer.exceptions;

public class TimeseriesPersistenceException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 3488131954013875686L;

    public TimeseriesPersistenceException() {
        super();
    }

    public TimeseriesPersistenceException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TimeseriesPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeseriesPersistenceException(String message) {
        super(message);
    }

    public TimeseriesPersistenceException(Throwable cause) {
        super(cause);
    }

}
