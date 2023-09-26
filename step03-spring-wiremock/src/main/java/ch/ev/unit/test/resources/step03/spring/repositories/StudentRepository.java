package ch.ev.unit.test.resources.step03.spring.repositories;

import ch.ev.unit.test.resources.step03.spring.data.Student;

public interface StudentRepository {
    Student getById(Long id);
}
