package ch.ev.unit.test.resources.step02.osgi.repositories;

import ch.ev.unit.test.resources.step02.osgi.data.Student;

import java.util.Optional;

/**
 * <p>StudentRepository interface.</p>
 *
 * @author enrico
 */
public interface StudentRepository {

    Optional<Student> getById(Long studentId);
}
