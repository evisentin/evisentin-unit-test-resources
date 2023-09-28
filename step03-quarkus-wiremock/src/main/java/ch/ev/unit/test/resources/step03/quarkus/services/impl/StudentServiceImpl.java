package ch.ev.unit.test.resources.step03.quarkus.services.impl;

import ch.ev.unit.test.resources.step03.quarkus.data.Student;
import ch.ev.unit.test.resources.step03.quarkus.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step03.quarkus.repositories.StudentRepository;
import ch.ev.unit.test.resources.step03.quarkus.services.StudentService;
import ch.ev.unit.test.resources.step03.quarkus.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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

        return studentRepository.getById(id);
    }
}
