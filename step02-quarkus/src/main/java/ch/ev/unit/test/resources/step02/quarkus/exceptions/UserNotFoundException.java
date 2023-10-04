package ch.ev.unit.test.resources.step02.quarkus.exceptions;

/**
 * <p>UserNotFoundException class.</p>
 *
 * @author enrico
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String userName) {
        super(String.format("User '%s' not found.", userName));
    }
}
