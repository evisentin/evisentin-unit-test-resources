package ch.ev.unit.test.resources.step03.spring.exceptions;

public class RestException extends RuntimeException {

    public RestException(final int statusCode) {
        super(String.format("Unexpected HTTP %d code", statusCode));
    }

    public RestException(final String message) {
        super(message);
    }
}
