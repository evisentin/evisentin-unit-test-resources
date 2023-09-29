package ch.ev.unit.test.resources.step03.quarkus.test.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Slf4j
public class WireMockTestExtensions implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void inject(final TestInjector testInjector) {
        testInjector.injectIntoFields(wireMockServer, new TestInjector.AnnotatedAndMatchesType(InjectWireMock.class, WireMockServer.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> start() {
        log.info("Starting WireMock Server...");
        wireMockServer = new WireMockServer(options().dynamicPort());
        wireMockServer.start();
        log.info("WireMock Server started, address is {}", wireMockServer.baseUrl());

        // >>>> VERY IMPORTANT <<<<
        // Here we "redefine" property quarkus.rest-client.ext-student-api.url for the test
        // with the wireMockServer's baseUrl (it is dynamic, so the test class must be configured here)
        return Map.of("quarkus.rest-client.ext-student-api.url", wireMockServer.baseUrl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}
