package ch.ev.unit.test.resources.step02.osgi.repositories;

import ch.ev.unit.test.resources.step02.osgi.data.Student;

import java.util.Optional;

/**
 * <p>StudentRepository interface.</p>
 *
 * @author enrico
 */
public interface StudentRepository {

    /**
     * Retrieves student by its studentId.
     *
     * @param studentId must not be null
     * @return the student with the given studentId or Optional#empty() if none found.
     */
    Optional<Student> getById(Long studentId);
}
