package ch.ev.unit.test.resources.step03.quarkus.services;

/**
 * <p>UserService interface.</p>
 *
 * @author enrico
 */
public interface UserService {

    /**
     * Returns whether a user with the given username exists.
     *
     * @param userName must not be null
     * @return true if a user with the given username exists, false otherwise.
     */
    boolean userExists(String userName);
}
