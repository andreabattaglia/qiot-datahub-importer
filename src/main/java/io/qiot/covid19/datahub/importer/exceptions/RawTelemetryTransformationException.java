package io.qiot.covid19.datahub.importer.exceptions;

public class RawTelemetryTransformationException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 3488131954013875686L;

    public RawTelemetryTransformationException() {
        super();
    }

    public RawTelemetryTransformationException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RawTelemetryTransformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RawTelemetryTransformationException(String message) {
        super(message);
    }

    public RawTelemetryTransformationException(Throwable cause) {
        super(cause);
    }

}
