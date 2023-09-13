package ch.ev.unit.test.resources.step03.spring.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super(String.format("Student id:%d not found.",id));
    }
}
