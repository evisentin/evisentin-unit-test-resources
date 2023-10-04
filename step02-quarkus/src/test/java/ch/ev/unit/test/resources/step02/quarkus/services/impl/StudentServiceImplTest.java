package ch.ev.unit.test.resources.step02.quarkus.services.impl;

import ch.ev.unit.test.resources.step02.quarkus.data.Student;
import ch.ev.unit.test.resources.step02.quarkus.exceptions.StudentNotFoundException;
import ch.ev.unit.test.resources.step02.quarkus.exceptions.UserNotFoundException;
import ch.ev.unit.test.resources.step02.quarkus.repositories.StudentRepository;
import ch.ev.unit.test.resources.step02.quarkus.services.UserService;
import lombok.NonNull;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest implements WithAssertions {

    public static final long ID_01 = 1L;
    public static final String USER_01 = "user01";

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
    @DisplayName("getById fails when the studentId is null")
    void getById__fails__on_null_id() {
        assertThatThrownBy(() -> studentService.getById(USER_01, null))
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
        givenUserExists(USER_01);
        giveStudentNotFound(StudentServiceImplTest.ID_01);

        // THEN
        assertThatThrownBy(() -> studentService.getById(USER_01, StudentServiceImplTest.ID_01))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Student id:%d not found.", ID_01);
    }

    @Test
    @DisplayName("getById fails when the user is not found")
    void getById__fails__on_user_not_found() {

        // GIVEN
        givenUserDoesNotExists(USER_01);

        // THEN
        assertThatThrownBy(() -> studentService.getById(USER_01, StudentServiceImplTest.ID_01))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User '%s' not found.", USER_01);
    }

    @Test
    @DisplayName("getById succeeds")
    void getById__succeeds() {

        final long studentID = StudentServiceImplTest.ID_01;
        final Student myStudent = Student.builder()
                .id(studentID)
                .firstName("Tom")
                .lastName("Sawyer")
                .build();

        // GIVEN
        givenUserExists(USER_01);
        givenStudentIsFound(myStudent);

        // WHEN
        final Student student = studentService.getById(USER_01, studentID);

        // THEN
        assertThat(student) // we expect the 'found' student
                .as("Student is not null").isNotNull() // not to be null
                .as("Student is equal to the expected one").isEqualTo(myStudent); // to be equal to myStudent
    }

    @Test
    @DisplayName("getById succeeds (with mockito's checks on mocks)")
    void getById__succeeds__with_verification() {

        final long studentID = StudentServiceImplTest.ID_01;
        final Student myStudent = Student.builder()
                .id(studentID)
                .firstName("Tom")
                .lastName("Sawyer")
                .build();

        // GIVEN
        givenUserExists(USER_01);
        givenStudentIsFound(myStudent);

        // WHEN
        final Student student = studentService.getById(USER_01, studentID);

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
        mocksInOrder.verify(userService, times(1)).userExists(USER_01);
        mocksInOrder.verify(studentRepository, times(1)).getById(studentID);
        mocksInOrder.verifyNoMoreInteractions();
    }

    private void giveStudentNotFound(long id) {
        when(studentRepository.getById(id)).thenReturn(Optional.empty());
    }

    private void givenStudentIsFound(@NonNull final Student myStudent) {
        when(studentRepository.getById(myStudent.getId())).thenReturn(Optional.of(myStudent));
    }

    private void givenUserDoesNotExists(@NonNull final String userName) {
        when(userService.userExists(userName)).thenReturn(false);
    }

    private void givenUserExists(@NonNull final String userName) {
        when(userService.userExists(userName)).thenReturn(true);
    }

}
