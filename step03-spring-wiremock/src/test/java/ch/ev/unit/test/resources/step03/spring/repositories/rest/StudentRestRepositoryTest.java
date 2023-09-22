package ch.ev.unit.test.resources.step03.spring.repositories.rest;

import ch.ev.unit.test.resources.step03.spring.data.Student;
import ch.ev.unit.test.resources.step03.spring.exceptions.RestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Optional;

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
        this.testObject = new StudentRestRepository(wmRuntimeInfo.getHttpBaseUrl(), new OkHttpClient.Builder().build(), objectMapper);
    }

    @Test
    void empty_on_not_found() {

        // GIVEN
        stubFor(get(urlEqualTo("/api/student/10"))
                .willReturn(aResponse()
                        .withStatus(404)
                ));

        // WHEN
        final Optional<Student> student = testObject.getById(10L);

        // THEN
        assertThat(student).isEmpty();
    }

    @Test
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
    void exception_on_null_id() {

        // WHEN
        assertThatThrownBy(() -> testObject.getById(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("id is marked non-null but is null");
    }

    @Test
    @SneakyThrows
    void success_on_student_found() {

        // GIVEN
        stubFor(get(urlEqualTo("/api/student/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(expectedStudent))));

        // WHEN
        final Optional<Student> student = testObject.getById(1L);

        // THEN
        assertThat(student).isPresent().get().isEqualTo(expectedStudent);
    }


}
