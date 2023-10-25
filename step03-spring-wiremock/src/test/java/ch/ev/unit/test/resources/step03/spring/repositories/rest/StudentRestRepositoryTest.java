package ch.ev.unit.test.resources.step03.spring.repositories.rest;

import ch.ev.unit.test.resources.step03.spring.data.Student;
import ch.ev.unit.test.resources.step03.spring.exceptions.RestException;
import ch.ev.unit.test.resources.step03.spring.exceptions.StudentNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.SneakyThrows;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest
class StudentRestRepositoryTest implements WithAssertions {

    private final Student expectedStudent = Student.builder()
            .id(1L)
            .firstName("Mark")
            .lastName("Twain")
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    private StudentRestRepository testObject;

    @BeforeEach
    void before(final WireMockRuntimeInfo wmRuntimeInfo) {
        this.testObject = new StudentRestRepository(wmRuntimeInfo.getHttpBaseUrl(), new RestTemplateBuilder());
    }

    @Test
    @DisplayName("getById fails when we receive a HTTP 401 UNAUTHORIZED response")
    void exception_on_not_authorized() {

        // GIVEN
        stubFor(get(urlEqualTo("/api/student/10"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.UNAUTHORIZED.value())
                ));

        // WHEN
        assertThatThrownBy(() -> testObject.getById(10L))
                .isInstanceOf(RestException.class)
                .hasMessage("Unexpected HTTP %s code", HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("getById fails when we receive a HTTP 404 NOT_FOUND response")
    void exception_on_not_found() {

        // GIVEN
        stubFor(get(urlEqualTo("/api/student/10"))
                .willReturn(aResponse()
                        .withStatus(404) /* HTTP 404 = NOT_FOUND */
                ));

        // WHEN
        assertThatThrownBy(() -> testObject.getById(10L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Student id %d not found.", 10L);


    }

    @Test
    @DisplayName("getById fails when the id is null")
    void exception_on_null_id() {

        // WHEN
        assertThatThrownBy(() -> testObject.getById(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("id is marked non-null but is null");
    }

    @Test
    @SneakyThrows
    @DisplayName("getById fails when we receive an empty json body")
    void fails_on_empty_body() {

        // GIVEN
        stubFor(get(urlEqualTo("/api/student/1"))
                .willReturn(aResponse()
                        .withStatus(200) /* HTTP 200 = OK */
                        .withHeader("Content-Type", "application/json")
                        .withBody("")));

        // WHEN
        assertThatThrownBy(() -> testObject.getById(1L))
                .isInstanceOf(RestException.class)
                .hasMessage("empty body received");
    }

    @Test
    @SneakyThrows
    @DisplayName("getById succeeds")
    void success_on_student_found() {

        // GIVEN
        stubFor(get(urlEqualTo("/api/student/1"))
                .willReturn(aResponse()
                        .withStatus(200) /* HTTP 200 = OK */
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(expectedStudent))));

        // WHEN
        final Student student = testObject.getById(1L);

        // THEN
        assertThat(student).isEqualTo(expectedStudent);
    }
}
