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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@QuarkusTest
@QuarkusTestResource(WireMockTestExtensions.class)
class StudentRestRepositoryTest implements WithAssertions {

    public static final long ID_01 = 1L;
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
    @DisplayName("getById fails when we receive a HTTP 401 UNAUTHORIZED response")
    void exception_on_not_authorized() {

        // GIVEN
        wireMockServer.stubFor(get(urlEqualTo("/api/student/1"))
                .willReturn(aResponse()
                        .withStatus(401) /* HTTP 401 = UNAUTHORIZED */
                        .withStatusMessage("UNAUTHORIZED")));

        // THEN
        assertThatThrownBy(() -> repository.getById(ID_01))
                .isInstanceOf(RestException.class)
                .hasMessage("Unauthorized");
    }

    @Test
    @DisplayName("getById fails when we receive a HTTP 404 NOT_FOUND response")
    void exception_on_not_found() {

        // GIVEN
        wireMockServer.stubFor(get(urlEqualTo("/api/student/1"))
                .willReturn(aResponse()
                        .withStatus(404) /* HTTP 404 = NOT_FOUND */
                        .withStatusMessage("NOT FOUND")));

        // THEN
        assertThatThrownBy(() -> repository.getById(ID_01))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Student id %d not found.", ID_01);

    }

    @Test
    @DisplayName("getById fails when the id is null")
    void exception_on_null_id() {

        // WHEN
        assertThatThrownBy(() -> repository.getById(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("id is marked non-null but is null");
    }

    @Test
    @SneakyThrows
    @DisplayName("getById succeeds")
    void success_on_student_found() {

        // GIVEN
        Student expectedStudent = Student.builder()
                .id(ID_01)
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
        final Student student = repository.getById(ID_01);

        // THEN
        assertThat(student).isEqualTo(expectedStudent);
    }

}
