package ch.ev.unit.test.resources.step02.osgi.services.impl;

import ch.ev.unit.test.resources.step02.osgi.data.Student;
import ch.ev.unit.test.resources.step02.osgi.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step02.osgi.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step02.osgi.repositories.StudentRepository;
import ch.ev.unit.test.resources.step02.osgi.services.StudentService;
import ch.ev.unit.test.resources.step02.osgi.services.UserService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * <p>StudentServiceImpl class.</p>
 *
 * @author enrico
 */
@Component
public class StudentServiceImpl implements StudentService {

    @Reference
    private UserService userService;

    @Reference
    private StudentRepository studentRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Student getById(final String userName, final Long studentId) {

        failOnNull(userName, "'userName' cannot be null!");
        failOnNull(studentId, "'id' cannot be null!");
        failOnNonExistingUser(userName);

        return studentRepository.getById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    private void failOnNonExistingUser(final String userName) {
        if (!userService.userExists(userName)) throw new UserNotFoundException(userName);
    }

    private void failOnNull(final Object object, final String message) {
        if (object == null) throw new IllegalArgumentException(message);
    }

}
