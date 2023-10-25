package ch.ev.unit.test.resources.step02.osgi.exceptions;

/**
 * <p>Thrown when a Student cannot be found</p>
 *
 * @author enrico
 */
public class StudentNotFoundException extends RuntimeException {

    /**
     * Constructs a StudentNotFoundException with an automatic message built for the given studentId
     *
     * @param studentId the student ID
     */
    public StudentNotFoundException(final Long studentId) {
        super(String.format("Student id %d not found.", studentId));
    }
}
