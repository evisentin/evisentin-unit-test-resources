package ch.ev.unit.test.resources.step02.quarkus.services.impl;

import ch.ev.unit.test.resources.step02.quarkus.data.Student;
import ch.ev.unit.test.resources.step02.quarkus.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step02.quarkus.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step02.quarkus.repositories.StudentRepository;
import ch.ev.unit.test.resources.step02.quarkus.services.StudentService;
import ch.ev.unit.test.resources.step02.quarkus.services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StudentServiceImpl implements StudentService {

    @Inject
    UserService userService;

    @Inject
    StudentRepository studentRepository;

    public Student getById(String userName, Long id) {

        if (userName == null) throw new IllegalArgumentException("'userName' cannot be null!");

        if (id == null) throw new IllegalArgumentException("'id' cannot be null!");

        if (!userService.userExists(userName)) throw new UserNotFoundException(userName);

        return studentRepository.getById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }
}
