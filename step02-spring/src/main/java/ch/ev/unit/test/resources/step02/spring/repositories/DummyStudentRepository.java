package ch.ev.unit.test.resources.step02.spring.repositories;

import ch.ev.unit.test.resources.step02.spring.data.Student;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DummyStudentRepository  implements StudentRepository{
    @Override
    public Optional<Student> getById(Long id) {
        return Optional.empty();
    }
}
