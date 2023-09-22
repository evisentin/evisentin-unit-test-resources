package ch.ev.unit.test.resources.step03.spring.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String userName) {
        super(String.format("User '%s' not found.", userName));
    }
}