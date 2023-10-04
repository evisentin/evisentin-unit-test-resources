package ch.ev.unit.test.resources.step03.spring.exceptions;

/**
 * <p>Thrown when an unexpected response is received during a REST call</p>
 *
 * @author enrico
 */
public class RestException extends RuntimeException {

    /**
     * Constructs a RestException with an automatic message built for the given status code
     *
     * @param statusCode the status code
     */
    public RestException(final int statusCode) {
        super(String.format("Unexpected HTTP %d code", statusCode));
    }

    /**
     * Constructs a RestException with the given message
     *
     * @param message the message
     */
    public RestException(final String message) {
        super(message);
    }
}
