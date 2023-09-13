package ch.ev.unit.test.resources.step03.spring.repositories;

import ch.ev.unit.test.resources.step03.spring.data.Student;

import java.util.Optional;

public interface StudentRepository {
    Optional<Student> getById(Long id);
}
