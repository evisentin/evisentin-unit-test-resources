package ch.ev.unit.test.resources.step02.spring.services;

import ch.ev.unit.test.resources.step02.spring.data.Student;

public interface StudentService {
    Student getById(String userName, Long id);
}
