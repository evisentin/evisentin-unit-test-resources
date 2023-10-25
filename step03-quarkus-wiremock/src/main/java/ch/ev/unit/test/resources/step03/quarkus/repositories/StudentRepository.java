package ch.ev.unit.test.resources.step03.quarkus.repositories;

import ch.ev.unit.test.resources.step03.quarkus.data.Student;
import ch.ev.unit.test.resources.step03.quarkus.exceptions.StudentNotFoundException;

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
     * @throws StudentNotFoundException if no student is found.
     */
    Student getById(Long studentId);
}
