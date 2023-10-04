package ch.ev.unit.test.resources.step02.osgi.exceptions;

/**
 * <p>StudentNotFoundException class.</p>
 *
 * @author enrico
 */
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(final Long studentId) {
        super(String.format("Student id:%d not found.", studentId));
    }
}
