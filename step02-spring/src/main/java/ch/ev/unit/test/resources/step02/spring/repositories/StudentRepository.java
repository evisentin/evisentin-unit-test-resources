package ch.ev.unit.test.resources.step02.spring.repositories;

import ch.ev.unit.test.resources.step02.spring.data.Student;

import java.util.Optional;

/**
 * Repository for {@link Student} entities.
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
