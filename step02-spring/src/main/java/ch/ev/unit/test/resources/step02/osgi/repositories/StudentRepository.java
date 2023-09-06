package ch.ev.unit.test.resources.step02.osgi.repositories;

import ch.ev.unit.test.resources.step02.osgi.data.Student;

import java.util.Optional;

public interface StudentRepository {
    Optional<Student> getById(Long id);
}
