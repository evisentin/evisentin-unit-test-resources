package ch.ev.unit.test.resources.step03.spring.repositories.rest;

import ch.ev.unit.test.resources.step03.spring.data.Student;
import ch.ev.unit.test.resources.step03.spring.exceptions.RestException;
import ch.ev.unit.test.resources.step03.spring.repositories.StudentRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.util.Optional;

@Slf4j
@Repository
public class StudentRestRepository implements StudentRepository {

    private final RestTemplate restTemplate;
    private final DefaultUriBuilderFactory uriBuilderFactory;

    public StudentRestRepository(
            @Value("${app.services.external.student.base-url}") final @NonNull String serviceUrl,
            final RestTemplate restTemplate) {

        uriBuilderFactory = new DefaultUriBuilderFactory(serviceUrl);
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Student> getById(final @NonNull Long id) {

        URI uri = uriBuilderFactory.builder().pathSegment("api", "student", "{id}").build(id);

        final ResponseEntity<Student> response = restTemplate.getForEntity(uri, Student.class);

        if (response.getStatusCode().is2xxSuccessful())
            return Optional.ofNullable(response.getBody());

        if (response.getStatusCode().value() == HttpStatus.NOT_FOUND.value())
            return Optional.empty();

        throw new RestException(response.getStatusCode());
    }
}
