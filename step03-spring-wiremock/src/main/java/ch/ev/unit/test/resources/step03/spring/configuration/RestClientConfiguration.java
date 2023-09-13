package ch.ev.unit.test.resources.step03.spring.configuration;

import ch.ev.unit.test.resources.step03.spring.rest.RestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder,
                                     RestTemplateResponseErrorHandler responseErrorHandler) {

        return restTemplateBuilder.errorHandler(responseErrorHandler).build();
    }
}
