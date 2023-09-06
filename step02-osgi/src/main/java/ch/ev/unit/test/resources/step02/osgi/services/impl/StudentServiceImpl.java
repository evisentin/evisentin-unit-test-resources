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

    public Student getById(String userName, Long id) {

        if (userName == null) throw new IllegalArgumentException("'userName' cannot be null!");

        if (id == null) throw new IllegalArgumentException("'id' cannot be null!");

        if (!userService.userExists(userName)) throw new UserNotFoundException(userName);

        return studentRepository.getById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }
}
