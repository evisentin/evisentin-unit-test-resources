package ch.ev.unit.test.resources.step03.quarkus.repositories.rest;

import ch.ev.unit.test.resources.step03.quarkus.data.Student;
import ch.ev.unit.test.resources.step03.quarkus.exceptions.RestException;
import ch.ev.unit.test.resources.step03.quarkus.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step03.quarkus.rest.client.StudentRestClient;
import ch.ev.unit.test.resources.step03.quarkus.services.impl.StudentWireMockExtensions;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import org.assertj.core.api.WithAssertions;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(StudentWireMockExtensions.class)
class StudentRestRepositoryTest implements WithAssertions {

    @RestClient
    StudentRestClient studentRestClient;

    @Inject
    StudentRestRepository repository;

    @BeforeEach
    void beforeTest() {

        repository.studentRestClient = studentRestClient;
    }

    @Test
    void exception_on_not_found() {

        assertThatThrownBy(() -> repository.getById(2L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Student id:2 not found.");

    }

    @Test
    void exception_on_not_authorized() {

        assertThatThrownBy(() -> repository.getById(3L))
                .isInstanceOf(RestException.class)
                .hasMessage("Unexpected HTTP %s code", 401);
    }

    @Test
    void exception_on_null_id() {

        // WHEN
        assertThatThrownBy(() -> repository.getById(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("id is marked non-null but is null");
    }

    private final Student expectedStudent = Student.builder()
            .id(1L)
            .firstName("Mark")
            .lastName("Twain")
            .build();

    @Test
    @SneakyThrows
    void success_on_student_found() {

        // WHEN
        final Student student = repository.getById(1L);

        // THEN
        assertThat(student).isEqualTo(expectedStudent);
    }

}
