package ch.ev.unit.test.resources.step03.quarkus.services;

import ch.ev.unit.test.resources.step03.quarkus.data.Student;

public interface StudentService {
    Student getById(String userName, Long id);
}
