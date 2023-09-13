package ch.ev.unit.test.resources.step03.spring.services.impl;

import ch.ev.unit.test.resources.step03.spring.data.Student;
import ch.ev.unit.test.resources.step03.spring.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step03.spring.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step03.spring.repositories.StudentRepository;
import ch.ev.unit.test.resources.step03.spring.services.StudentService;
import ch.ev.unit.test.resources.step03.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentRepository studentRepository;

    public Student getById(String userName, Long id) {

        if (userName == null) throw new IllegalArgumentException("'userName' cannot be null!");

        if (id == null) throw new IllegalArgumentException("'id' cannot be null!");

        if (!userService.userExists(userName)) throw new UserNotFoundException(userName);

        return studentRepository.getById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }
}
