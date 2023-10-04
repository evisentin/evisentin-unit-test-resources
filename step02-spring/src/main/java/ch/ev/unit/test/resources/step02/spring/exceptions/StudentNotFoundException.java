package ch.ev.unit.test.resources.step02.spring.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(final Long studentId) {
        super(String.format("Student id:%d not found.", studentId));
    }
}
