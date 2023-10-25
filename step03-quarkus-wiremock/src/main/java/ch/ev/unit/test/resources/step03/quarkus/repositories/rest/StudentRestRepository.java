package ch.ev.unit.test.resources.step03.quarkus.repositories.rest;

import ch.ev.unit.test.resources.step03.quarkus.data.Student;
import ch.ev.unit.test.resources.step03.quarkus.exceptions.RestException;
import ch.ev.unit.test.resources.step03.quarkus.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step03.quarkus.repositories.StudentRepository;
import ch.ev.unit.test.resources.step03.quarkus.rest.client.StudentRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NonNull;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * Rest implementation of {@link StudentRepository}
 */
@ApplicationScoped
public class StudentRestRepository implements StudentRepository {

    @RestClient
    StudentRestClient studentRestClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public Student getById(@NonNull final Long id) {

        try {
            return studentRestClient.getById(id);

        } catch (final RestException ex) {
            if (ex.getStatusCode() == 404) /* HTTP 404 = NOT_FOUND */
                throw new StudentNotFoundException(id);
            throw ex;
        }
    }
}
