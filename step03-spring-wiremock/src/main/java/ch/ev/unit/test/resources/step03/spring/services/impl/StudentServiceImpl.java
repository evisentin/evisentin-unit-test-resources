package ch.ev.unit.test.resources.step03.spring.services.impl;

import ch.ev.unit.test.resources.step03.spring.data.Student;
import ch.ev.unit.test.resources.step03.spring.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step03.spring.repositories.StudentRepository;
import ch.ev.unit.test.resources.step03.spring.services.StudentService;
import ch.ev.unit.test.resources.step03.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * <p>StudentService implementation.</p>
 *
 * @author enrico
 */
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Student getById(final String userName, final Long studentId) {

        Objects.requireNonNull(userName, "'userName' cannot be null!");
        Objects.requireNonNull(studentId, "'id' cannot be null!");
        failOnNonExistingUser(userName);

        return studentRepository.getById(studentId);
    }

    private void failOnNonExistingUser(final String userName) {
        if (!userService.userExists(userName)) throw new UserNotFoundException(userName);
    }
}
