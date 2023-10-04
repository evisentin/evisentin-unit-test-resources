package ch.ev.unit.test.resources.step02.spring.services.impl;

import ch.ev.unit.test.resources.step02.spring.data.Student;
import ch.ev.unit.test.resources.step02.spring.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step02.spring.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step02.spring.repositories.StudentRepository;
import ch.ev.unit.test.resources.step02.spring.services.StudentService;
import ch.ev.unit.test.resources.step02.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getById(final String userName, final Long id) {

        Objects.requireNonNull(userName, "'userName' cannot be null!");
        Objects.requireNonNull(id, "'id' cannot be null!");
        failOnNonExistingUser(userName);

        return studentRepository.getById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    private void failOnNonExistingUser(final String userName) {
        if (!userService.userExists(userName)) throw new UserNotFoundException(userName);
    }

}
