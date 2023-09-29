package ch.ev.unit.test.resources.step03.quarkus.services.impl;

import ch.ev.unit.test.resources.step03.quarkus.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserServiceDummyImpl implements UserService {
    @Override
    public boolean userExists(final String userName) {
        return false;
    }
}
