package ch.ev.unit.test.resources.step02.osgi.services;

import ch.ev.unit.test.resources.step02.osgi.data.Student;
import ch.ev.unit.test.resources.step02.osgi.exceptions.StudentNotFoundException;

/**
 * <p>StudentService interface.</p>
 *
 * @author enrico
 */
public interface StudentService {

    /**
     * Retrieves student by its studentId.
     *
     * @param userName  the username making the request, must not be null
     * @param studentId must not be null
     * @return the student with the given studentId.
     * @throws StudentNotFoundException if no student is found.
     */
    Student getById(String userName, Long studentId);
}
