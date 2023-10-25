package ch.ev.unit.test.resources.step02.quarkus.services.impl;

import ch.ev.unit.test.resources.step02.quarkus.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * <p>Dummy implementation of {@link UserService}, this is needed by Quarkus to work properly</p>
 *
 * <p>This class is NOT used by the tests.</p>
 *
 * @author enrico
 */
@ApplicationScoped
public class UserServiceDummyImpl implements UserService {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean userExists(final String userName) {
        return false;
    }
}
