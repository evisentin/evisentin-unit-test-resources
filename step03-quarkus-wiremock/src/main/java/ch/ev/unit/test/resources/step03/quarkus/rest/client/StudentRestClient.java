package ch.ev.unit.test.resources.step03.quarkus.rest.client;

import ch.ev.unit.test.resources.step03.quarkus.data.Student;
import ch.ev.unit.test.resources.step03.quarkus.exceptions.RestException;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * @see <a href="https://quarkus.io/guides/rest-client-reactive">Quarkus: Using The Rest Client</a>
 */
@Path("/api/student")
@RegisterRestClient(configKey = "ext-student-api")
public interface StudentRestClient {

    @GET
    @Path("{id}")
    Student getById(Long id);

    @ClientExceptionMapper
    static RuntimeException toException(final Response response) {

        if (response.getStatus() == 200) return null;

        if (response.getStatus() == 404) return new RestException(404, "NOT FOUND");

        return new RestException(response.getStatus());
    }

}
