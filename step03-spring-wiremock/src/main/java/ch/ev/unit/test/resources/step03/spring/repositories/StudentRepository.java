package ch.ev.unit.test.resources.step03.spring.repositories;

import ch.ev.unit.test.resources.step03.spring.data.Student;
import ch.ev.unit.test.resources.step03.spring.exceptions.StudentNotFoundException;

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
     * @return the student with the given studentId
     * @throws StudentNotFoundException if the student is not found
     */
    Student getById(Long studentId);
}
