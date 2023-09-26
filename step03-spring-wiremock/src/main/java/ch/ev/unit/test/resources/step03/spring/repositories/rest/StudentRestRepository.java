package ch.ev.unit.test.resources.step03.spring.repositories.rest;

import ch.ev.unit.test.resources.step03.spring.data.Student;
import ch.ev.unit.test.resources.step03.spring.exceptions.RestException;
import ch.ev.unit.test.resources.step03.spring.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step03.spring.repositories.StudentRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.apache.commons.lang3.StringUtils.removeEnd;

@Slf4j
@Repository
public class StudentRestRepository implements StudentRepository {

    private final String serviceUrl;

    private final RestTemplate restTemplate;

    public StudentRestRepository(
            @Value("${app.services.external.student.base-url}") final @NonNull String serviceUrl,
            final RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
        this.serviceUrl = removeEnd(serviceUrl, "/");
    }

    @Override
    public Student getById(final @NonNull Long id) {

        try {
            final ResponseEntity<Student> response = restTemplate.getForEntity(serviceUrl + "/api/student/{id}", Student.class, id);

            final Student student = response.getBody();

            if (student == null) throw new RestException("empty body received");

            return student;


        } catch (HttpClientErrorException ex) {

            // on 404
            if (ex.getStatusCode().value() == 404) throw new StudentNotFoundException(id);

            throw new RestException(ex.getStatusCode().value());
        }
    }
}
