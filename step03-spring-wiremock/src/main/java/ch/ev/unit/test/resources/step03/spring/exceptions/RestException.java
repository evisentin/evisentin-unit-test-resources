package ch.ev.unit.test.resources.step03.spring.exceptions;

import java.io.IOException;

public class RestException extends RuntimeException {

    public RestException(final int statusCode) {
        super(String.format("Unexpected HTTP %d code", statusCode));
    }

    public RestException(final IOException e) {
        super("Unexpected exception %s", e);
    }
}
