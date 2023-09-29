package ch.ev.unit.test.resources.step02.osgi.services.impl;

import ch.ev.unit.test.resources.step02.osgi.data.Student;
import ch.ev.unit.test.resources.step02.osgi.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step02.osgi.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step02.osgi.repositories.StudentRepository;
import ch.ev.unit.test.resources.step02.osgi.services.StudentService;
import ch.ev.unit.test.resources.step02.osgi.services.UserService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class StudentServiceImpl implements StudentService {

    @Reference
    private UserService userService;

    @Reference
    private StudentRepository studentRepository;

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
