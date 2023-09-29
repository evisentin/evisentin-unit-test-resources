package ch.ev.unit.test.resources.step02.osgi.services;

import ch.ev.unit.test.resources.step02.osgi.data.Student;

public interface StudentService {
    Student getById(String userName, Long id);
}
