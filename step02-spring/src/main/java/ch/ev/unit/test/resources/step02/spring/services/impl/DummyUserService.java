package ch.ev.unit.test.resources.step02.spring.services.impl;

import ch.ev.unit.test.resources.step02.spring.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService {
    @Override
    public boolean userExists(final String userName) {
        return false;
    }
}
