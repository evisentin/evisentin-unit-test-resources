package ch.ev.unit.test.resources.step03.quarkus.services.impl;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class StudentWireMockExtensions implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(options().dynamicPort());
        wireMockServer.start();

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

        wireMockServer.stubFor(get(urlEqualTo("/api/student/2"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withStatusMessage("NOT FOUND")));

        wireMockServer.stubFor(get(urlEqualTo("/api/student/3"))
                .willReturn(aResponse()
                        .withStatus(401)
                        .withStatusMessage("UNAUTHORIZED")));

        return Map.of("quarkus.rest-client.ext-student-api.url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}
