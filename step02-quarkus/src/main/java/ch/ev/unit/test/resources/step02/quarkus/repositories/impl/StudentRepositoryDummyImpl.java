package ch.ev.unit.test.resources.step02.quarkus.repositories.impl;

import ch.ev.unit.test.resources.step02.quarkus.data.Student;
import ch.ev.unit.test.resources.step02.quarkus.repositories.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

/**
 * <p>Dummy implementation of {@link StudentRepository}, this is needed by Quarkus to work properly</p>
 *
 * <p>This class is NOT used by the tests.</p>
 *
 * @author enrico
 */
@ApplicationScoped
public class StudentRepositoryDummyImpl implements StudentRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Student> getById(Long studentId) {
        return Optional.empty();
    }
}
