package ch.ev.unit.test.resources.step02.quarkus.services.impl;

import ch.ev.unit.test.resources.step02.quarkus.data.Student;
import ch.ev.unit.test.resources.step02.quarkus.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step02.quarkus.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step02.quarkus.repositories.StudentRepository;
import ch.ev.unit.test.resources.step02.quarkus.services.StudentService;
import ch.ev.unit.test.resources.step02.quarkus.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StudentServiceImpl implements StudentService {

    @Inject
    UserService userService;

    @Inject
    StudentRepository studentRepository;

    @Override
    public Student getById(final String userName, final Long id) {

        failOnNull(userName, "'userName' cannot be null!");
        failOnNull(id, "'id' cannot be null!");
        failOnNonExistingUser(userName);

        return studentRepository.getById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    private void failOnNonExistingUser(final String userName) {
        if (!userService.userExists(userName)) throw new UserNotFoundException(userName);
    }

    private void failOnNull(final Object object, final String message) {
        if (object == null) throw new IllegalArgumentException(message);
    }
}
