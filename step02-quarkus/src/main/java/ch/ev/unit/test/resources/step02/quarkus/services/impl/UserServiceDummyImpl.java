package ch.ev.unit.test.resources.step02.quarkus.services.impl;

import ch.ev.unit.test.resources.step02.quarkus.services.UserService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserServiceDummyImpl implements UserService {
    @Override
    public boolean userExists(String userName) {
        return false;
    }
}
