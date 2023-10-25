package ch.ev.unit.test.resources.step03.spring.services.impl;

import ch.ev.unit.test.resources.step03.spring.data.Student;
import ch.ev.unit.test.resources.step03.spring.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step03.spring.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step03.spring.repositories.StudentRepository;
import ch.ev.unit.test.resources.step03.spring.services.UserService;
import lombok.NonNull;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest implements WithAssertions {

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
    // this is just a suggestion, there are many other naming conventions that are just as valid and effective.
    // The important thing is to choose one and keep the choice consistent.
    // -------------------------------------------------------------------------------------------------------------

    @Test
    @DisplayName("getById fails when the studentId is null")
    void getById__fails__on_null_id() {
        assertThatThrownBy(() -> studentService.getById("user01", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("'id' cannot be null!");
    }

    @Test
    @DisplayName("getById fails when the userName is null")
    void getById__fails__on_null_userName() {
        assertThatThrownBy(() -> studentService.getById(null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("'userName' cannot be null!");
    }

    @Test
    @DisplayName("getById fails when the student is not found")
    void getById__fails__on_student_not_found() {

        // GIVEN
        givenUserExists("user01");
        giveStudentNotFound(1L);

        // THEN
        assertThatThrownBy(() -> studentService.getById("user01", 1L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Student id %d not found.", 1L);
    }

    @Test
    @DisplayName("getById fails when the user is not found")
    void getById__fails__on_user_not_found() {

        // GIVEN
        givenUserDoesNotExists("user01");

        // THEN
        assertThatThrownBy(() -> studentService.getById("user01", 1L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User 'user01' not found.");
    }

    @Test
    @DisplayName("getById succeeds")
    void getById__succeeds() {

        final long studentID = 1L;
        final Student myStudent = Student.builder()
                .id(studentID)
                .firstName("Tom")
                .lastName("Sawyer")
                .build();

        // GIVEN
        givenUserExists("user01");
        givenStudentIsFound(myStudent);

        // WHEN
        final Student student = studentService.getById("user01", studentID);

        // THEN
        assertThat(student) // we expect the 'found' student
                .as("Student is not null").isNotNull() // not to be null
                .as("Student is equal to the expected one").isEqualTo(myStudent); // to be equal to myStudent
    }

    @Test
    @DisplayName("getById succeeds (with mockito's checks on mocks)")
    void getById__succeeds__with_verification() {

        final long studentID = 1L;
        final Student myStudent = Student.builder()
                .id(studentID)
                .firstName("Tom")
                .lastName("Sawyer")
                .build();

        // GIVEN
        givenUserExists("user01");
        givenStudentIsFound(myStudent);

        // WHEN
        final Student student = studentService.getById("user01", studentID);

        // THEN
        assertThat(student) // we expect the 'found' student
                .as("Student is not null").isNotNull() // not to be null
                .as("Student is equal to the expected one").isEqualTo(myStudent); // to be equal to myStudent

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
        mocksInOrder.verify(studentRepository, times(1)).getById(studentID);
        mocksInOrder.verifyNoMoreInteractions();
    }

    private void giveStudentNotFound(long id) {
        when(studentRepository.getById(id)).thenThrow(new StudentNotFoundException(id));
    }

    private void givenStudentIsFound(@NonNull final Student myStudent) {
        when(studentRepository.getById(myStudent.getId())).thenReturn(myStudent);
    }

    private void givenUserDoesNotExists(@NonNull final String userName) {
        when(userService.userExists(userName)).thenReturn(false);
    }

    private void givenUserExists(@NonNull final String userName) {
        when(userService.userExists(userName)).thenReturn(true);
    }

}
