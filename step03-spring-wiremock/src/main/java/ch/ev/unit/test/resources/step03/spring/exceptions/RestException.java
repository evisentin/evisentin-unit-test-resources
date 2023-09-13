package ch.ev.unit.test.resources.step03.spring.exceptions;

import org.springframework.http.HttpStatusCode;

public class RestException extends RuntimeException {
    public RestException(final HttpStatusCode statusCode) {
        super(String.format("Unexpected HTTP %d code", statusCode.value()));
    }
}
