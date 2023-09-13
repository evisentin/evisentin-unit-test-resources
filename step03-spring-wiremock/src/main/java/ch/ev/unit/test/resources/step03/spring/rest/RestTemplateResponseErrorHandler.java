package ch.ev.unit.test.resources.step03.spring.rest;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    @Override
    public void handleError(final ClientHttpResponse httpResponse) {
    }

    @Override
    public boolean hasError(final ClientHttpResponse httpResponse)
            throws IOException {
        return httpResponse.getStatusCode().is5xxServerError();
    }
}
