package ch.ev.unit.test.resources.step03.quarkus.exceptions;

import lombok.Getter;

@Getter
public class RestException extends RuntimeException {

    private final int statusCode;

    public RestException(final int statusCode) {
        super(String.format("Unexpected HTTP %d code", statusCode));
        this.statusCode = statusCode;
    }

    public RestException(final int statusCode, final String message) {
        super(message);
        this.statusCode = statusCode;
    }


}
