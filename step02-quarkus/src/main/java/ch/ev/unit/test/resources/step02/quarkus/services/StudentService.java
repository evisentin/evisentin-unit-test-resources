package ch.ev.unit.test.resources.step02.quarkus.services;

import ch.ev.unit.test.resources.step02.quarkus.data.Student;

public interface StudentService {
    Student getById(String userName, Long id);
}
