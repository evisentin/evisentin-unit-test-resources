package ch.ev.unit.test.resources.step03.quarkus.repositories;

import ch.ev.unit.test.resources.step03.quarkus.data.Student;

public interface StudentRepository {
    Student getById(Long id);
}
