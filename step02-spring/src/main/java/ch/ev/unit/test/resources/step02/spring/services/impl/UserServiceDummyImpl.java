package ch.ev.unit.test.resources.step02.spring.services.impl;

import ch.ev.unit.test.resources.step02.spring.services.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>Dummy implementation of {@link UserService}, this is needed by Spring to work properly</p>
 *
 * <p>This class is NOT used by the tests.</p>
 *
 * @author enrico
 */
@Service
public class UserServiceDummyImpl implements UserService {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean userExists(final String userName) {
        return false;
    }
}
