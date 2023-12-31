package ch.ev.unit.test.resources.step02.spring.exceptions;

/**
 * <p>Thrown when a User cannot be found</p>
 *
 * @author enrico
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a UserNotFoundException with an automatic message built for the given userName.
     *
     * @param userName the student name
     */
    public UserNotFoundException(final String userName) {
        super(String.format("User '%s' not found.", userName));
    }
}
