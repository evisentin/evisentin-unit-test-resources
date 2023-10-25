package ch.ev.unit.test.resources.step03.quarkus.exceptions;

import lombok.Getter;

/**
 * <p>Thrown when an unexpected response is received during a REST call</p>
 *
 * @author enrico
 */
@Getter
public class RestException extends RuntimeException {

    private final int statusCode;

    /**
     * @param statusCode must not be null
     * @param message    must not be null
     */
    public RestException(final int statusCode, final String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
