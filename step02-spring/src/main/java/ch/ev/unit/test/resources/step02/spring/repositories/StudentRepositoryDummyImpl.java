package ch.ev.unit.test.resources.step02.spring.repositories;

import ch.ev.unit.test.resources.step02.spring.data.Student;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>Dummy implementation of {@link StudentRepository}, this is needed by Spring to work properly</p>
 *
 * <p>This class is NOT used by the tests.</p>
 *
 * @author enrico
 */
@Repository
public class StudentRepositoryDummyImpl implements StudentRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Student> getById(Long studentId) {
        return Optional.empty();
    }
}
