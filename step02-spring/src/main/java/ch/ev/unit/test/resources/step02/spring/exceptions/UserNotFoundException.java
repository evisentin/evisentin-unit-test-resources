package ch.ev.unit.test.resources.step02.spring.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userName) {
        super(String.format("User '%s' not found.",userName));
    }
}
