package ch.ev.unit.test.resources.step02.quarkus.repositories;

import ch.ev.unit.test.resources.step02.quarkus.data.Student;

import java.util.Optional;

public interface StudentRepository {
    Optional<Student> getById(Long studentId);
}
