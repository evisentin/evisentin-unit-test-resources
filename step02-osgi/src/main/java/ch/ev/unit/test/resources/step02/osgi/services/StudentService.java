package ch.ev.unit.test.resources.step02.osgi.services;

import ch.ev.unit.test.resources.step02.osgi.data.Student;

/**
 * <p>StudentService interface.</p>
 *
 * @author enrico
 */
public interface StudentService {

    Student getById(String userName, Long studentId);
}
