# STEP 03 - How to test a Service Class with external REST collaborators

_This version of step 03 makes use of the [Spring Boot](https://spring.io/projects/spring-boot) framework._

---

This step describes how to test a service class, which makes use of external REST collaborators to complete its task.

This type of test is very important because it illustrates how (and why) the collaborators of the class under test must
be simulated in order to drive its behaviour. In particular, this test shows how to mock an external (remote) REST
service.

---

The classes under test here are `ch.ev.unit.test.resources.step03.spring.services.impl.StudentServiceImpl`
and `ch.ev.unit.test.resources.step03.spring.repositories.rest.StudentRestRepository`

Class `ch.ev.unit.test.resources.step03.spring.services.impl.StudentServiceImpl` provides a method named `getById`,
which takes as parameters the username of the person making the request, and the
identifier of the student to be found.

- the method fails if any of the parameters (or both) are null.
- the method fails if the user corresponding to the username is not found.
- the method fails if the student corresponding to identifier is not found.
- the method returns a `ch.ev.unit.test.resources.step03.spring.data.Student` instance of the found student.

Class `ch.ev.unit.test.resources.step03.spring.repositories.rest.StudentRestRepository` provides a method
named `getById`, which takes as parameter the identifier of the student to be found.

- the method fails if any of the parameter is null.
- the method `Optional.empty()` if the student corresponding to identifier is not found.
- the method returns a `ch.ev.unit.test.resources.step03.spring.data.Student` instance of the found student.

---

**PLEASE NOTE:** although the code to be tested changes depending on the framework used (**OSGI**, **Spring**, **Quarkus
**, etc.),
**<ins>the source code of the tests remains unchanged</ins>**.
