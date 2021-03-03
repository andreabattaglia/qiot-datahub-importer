package io.qiot.covid19.datahub.importer.exceptions;

/**
 * The Class RawTelemetryTransformationException.
 *
 * @author andreabattaglia
 */
public class RawTelemetryTransformationException extends Exception {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3488131954013875686L;

    /**
     * Instantiates a new raw telemetry transformation exception.
     */
    public RawTelemetryTransformationException() {
        super();
    }

    /**
     * Instantiates a new raw telemetry transformation exception.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public RawTelemetryTransformationException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Instantiates a new raw telemetry transformation exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public RawTelemetryTransformationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new raw telemetry transformation exception.
     *
     * @param message the message
     */
    public RawTelemetryTransformationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new raw telemetry transformation exception.
     *
     * @param cause the cause
     */
    public RawTelemetryTransformationException(Throwable cause) {
        super(cause);
    }

}
