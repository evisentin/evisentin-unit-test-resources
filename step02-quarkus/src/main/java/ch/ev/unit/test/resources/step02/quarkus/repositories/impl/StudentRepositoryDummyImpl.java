package ch.ev.unit.test.resources.step02.quarkus.repositories.impl;

import ch.ev.unit.test.resources.step02.quarkus.data.Student;
import ch.ev.unit.test.resources.step02.quarkus.repositories.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class StudentRepositoryDummyImpl implements StudentRepository {
    @Override
    public Optional<Student> getById(Long id) {
        return Optional.empty();
    }
}
