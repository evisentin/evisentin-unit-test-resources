package ch.ev.unit.test.resources.step03.quarkus.exceptions;

/**
 * <p>Thrown when a Student cannot be found</p>
 *
 * @author enrico
 */
public class StudentNotFoundException extends RuntimeException {

    /**
     * Constructs a StudentNotFoundException with an automatic message built for the given id
     *
     * @param studentId the student ID
     */
    public StudentNotFoundException(final Long studentId) {
        super(String.format("Student id %d not found.", studentId));
    }
}
