package ch.ev.unit.test.resources.step03.quarkus.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userName) {
        super(String.format("User '%s' not found.", userName));
    }
}
