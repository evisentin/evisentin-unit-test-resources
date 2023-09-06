package ch.ev.unit.test.resources.step02.quarkus.services.impl;

import ch.ev.unit.test.resources.step02.quarkus.data.Student;
import ch.ev.unit.test.resources.step02.quarkus.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step02.quarkus.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step02.quarkus.repositories.StudentRepository;
import ch.ev.unit.test.resources.step02.quarkus.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    // This annotation makes Mockito automatically create a 'mock' object for this type.
    @Mock
    private UserService userService;

    // This annotation makes Mockito automatically create a 'mock' object for this type.
    @Mock
    private StudentRepository studentRepository;

    // This annotation 'does the magic', i.e. it creates the object to be tested and automatically 'injects' into it
    // the 'mock' objects created above.
    @InjectMocks
    private StudentServiceImpl studentService;

    // -------------------------------------------------------------------------------------------------------------
    // the naming convention used here is <method_name>__[fails|succeeds]__<condition>
    // this is just a suggestion, there are many other named conventions that are just as valid and effective.
    // The important thing is to choose one and keep the choice consistent.
    // -------------------------------------------------------------------------------------------------------------

    @Test
    void getById__fails__on_null_userName() {
        assertThatThrownBy(() -> studentService.getById(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'userName' cannot be null!");
    }

    @Test
    void getById__fails__on_null_id() {
        assertThatThrownBy(() -> studentService.getById("user01", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'id' cannot be null!");
    }

    @Test
    void getById__fails__on_user_not_found() {

        // GIVEN
        // - user01 does not exist
        Mockito.when(userService.userExists("user01")).thenReturn(false);

        assertThatThrownBy(() -> studentService.getById("user01", 1L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User 'user01' not found.");
    }

    @Test
    void getById__fails__on_student_not_found() {

        // GIVEN
        // - user01 exists
        Mockito.when(userService.userExists("user01")).thenReturn(true);

        // - student (id=1) not found
        Mockito.when(studentRepository.getById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getById("user01", 1L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Student  id:1 not found.");
    }

    @Test
    void getById__succeeds() {

        Student myStudent = Student.builder()
                .id(1L)
                .firstName("Tom")
                .lastName("Sawyer")
                .build();

        // GIVEN
        // - user01 exists
        Mockito.when(userService.userExists("user01")).thenReturn(true);

        // - student (id=1) is found
        Mockito.when(studentRepository.getById(1L)).thenReturn(Optional.of(myStudent));

        final Student student = studentService.getById("user01", 1L);

        assertThat(student) // we expect the 'found' student
                .isNotNull() // not to be null
                .isEqualTo(myStudent); // to be equal to myStudent
    }

    @Test
    void getById__succeeds__with_verification() {

        Student myStudent = Student.builder()
                .id(1L)
                .firstName("Tom")
                .lastName("Sawyer")
                .build();

        // GIVEN
        // - user01 exists
        when(userService.userExists("user01")).thenReturn(true);

        // - student (id=1) is found
        when(studentRepository.getById(1L)).thenReturn(Optional.of(myStudent));

        final Student student = studentService.getById("user01", 1L);

        assertThat(student) // we expect the 'found' student
                .isNotNull() // not to be null
                .isEqualTo(myStudent); // to be equal to myStudent

        // --------------------------------------------------------------------------------------------
        // Here we use the Mockito's 'InOrder' class, which takes care of the order of method calls
        // that the mock is going to make in due course of its action.
        //
        // --> https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html (6. Verification in order )
        // --> https://www.baeldung.com/mockito-verify
        // --------------------------------------------------------------------------------------------

        InOrder mocksInOrder = inOrder(userService, studentRepository);

        // check for the expected interactions to happen in the expected order
        mocksInOrder.verify(userService, times(1)).userExists("user01");
        mocksInOrder.verify(studentRepository, times(1)).getById(1L);
        mocksInOrder.verifyNoMoreInteractions();
    }

}
