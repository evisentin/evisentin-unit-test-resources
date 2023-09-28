package ch.ev.unit.test.resources.step03.quarkus.repositories.rest;

import ch.ev.unit.test.resources.step03.quarkus.data.Student;
import ch.ev.unit.test.resources.step03.quarkus.exceptions.RestException;
import ch.ev.unit.test.resources.step03.quarkus.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step03.quarkus.rest.client.StudentRestClient;
import ch.ev.unit.test.resources.step03.quarkus.test.wiremock.InjectWireMock;
import ch.ev.unit.test.resources.step03.quarkus.test.wiremock.WireMockTestExtensions;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import org.assertj.core.api.WithAssertions;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@QuarkusTest
@QuarkusTestResource(WireMockTestExtensions.class)
class StudentRestRepositoryTest implements WithAssertions {

    @InjectWireMock
    WireMockServer wireMockServer;

    @RestClient
    StudentRestClient studentRestClient;

    @Inject
    StudentRestRepository repository;

    @BeforeEach
    void beforeTest() {
        repository.studentRestClient = studentRestClient;
    }

    @Test
    void exception_on_not_authorized() {

        // GIVEN
        wireMockServer.stubFor(get(urlEqualTo("/api/student/1"))
                .willReturn(aResponse()
                        .withStatus(401)
                        .withStatusMessage("UNAUTHORIZED")));

        // THEN
        assertThatThrownBy(() -> repository.getById(1L))
                .isInstanceOf(RestException.class)
                .hasMessage("Unexpected HTTP %s code", 401);
    }

    @Test
    void exception_on_not_found() {

        // GIVEN
        wireMockServer.stubFor(get(urlEqualTo("/api/student/1"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withStatusMessage("NOT FOUND")));

        // THEN
        assertThatThrownBy(() -> repository.getById(1L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Student id:1 not found.");

    }

    @Test
    void exception_on_null_id() {

        // WHEN
        assertThatThrownBy(() -> repository.getById(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("id is marked non-null but is null");
    }

    @Test
    @SneakyThrows
    void success_on_student_found() {

        // GIVEN
        Student expectedStudent = Student.builder()
                .id(1L)
                .firstName("Mark")
                .lastName("Twain")
                .build();

        wireMockServer.stubFor(get(urlEqualTo("/api/student/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                "{\n" +
                                        "\"id\": 1,\n" +
                                        "\"firstName\": \"Mark\",\n" +
                                        "\"lastName\": \"Twain\"\n" +
                                        "}"
                        )));

        // WHEN
        final Student student = repository.getById(1L);

        // THEN
        assertThat(student).isEqualTo(expectedStudent);
    }

}
