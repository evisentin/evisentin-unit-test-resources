package ch.ev.unit.test.resources.step02.quarkus.services.impl;

import ch.ev.unit.test.resources.step02.quarkus.data.Student;
import ch.ev.unit.test.resources.step02.quarkus.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step02.quarkus.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step02.quarkus.repositories.StudentRepository;
import ch.ev.unit.test.resources.step02.quarkus.services.StudentService;
import ch.ev.unit.test.resources.step02.quarkus.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Objects;

/**
 * <p>StudentService implementation.</p>
 *
 * @author enrico
 */
@ApplicationScoped
public class StudentServiceImpl implements StudentService {

    @Inject
    UserService userService;

    @Inject
    StudentRepository studentRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Student getById(final String userName, final Long studentId) {

        failOnNull(userName, "'userName' cannot be null!");
        failOnNull(studentId, "'studentId' cannot be null!");
        failOnNonExistingUser(userName);

        return studentRepository.getById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    private void failOnNonExistingUser(final String userName) {
        if (!userService.userExists(userName)) throw new UserNotFoundException(userName);
    }

    private void failOnNull(final Object object, final String message) {
        Objects.requireNonNull(object, message);
    }
}
