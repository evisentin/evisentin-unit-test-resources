package ch.ev.unit.test.resources.step02.quarkus.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(final Long id) {
        super(String.format("Student id:%d not found.", id));
    }
}
