package ch.ev.unit.test.resources.step03.spring.repositories.rest;

import ch.ev.unit.test.resources.step03.spring.data.Student;
import ch.ev.unit.test.resources.step03.spring.exceptions.RestException;
import ch.ev.unit.test.resources.step03.spring.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.removeEnd;

@Slf4j
@Repository
public class StudentRestRepository implements StudentRepository {

    private final String serviceUrl;
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public StudentRestRepository(
            @Value("${app.services.external.student.base-url}") final @NonNull String serviceUrl,
            final OkHttpClient httpClient, final ObjectMapper objectMapper) {

        this.serviceUrl = removeEnd(serviceUrl, "/");
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<Student> getById(final @NonNull Long id) {

        final String url = serviceUrl + "/api/student/" + id;
        final Request request = new Request.Builder().url(url).build();

        try (final Response response = httpClient.newCall(request).execute()) {

            if (response.isSuccessful()) {
                return asStudent(response);
            }

            if (response.code() == 404) return Optional.empty();

            throw new RestException(response.code());

        } catch (IOException e) {
            throw new RestException(e);
        }
    }

    private Optional<Student> asStudent(final @NonNull Response response) throws IOException {
        final ResponseBody body = response.body();
        if (body == null) return Optional.empty();
        return Optional.ofNullable(objectMapper.readValue(body.bytes(), Student.class));
    }
}
