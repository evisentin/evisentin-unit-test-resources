# STEP 02 - How to test a Service Class with collaborators

_This version of step 02 makes use of the [Quarkus](https://quarkus.io/) framework._

---

This step describes how to test a service class, which makes use of collaborators to complete its task.

This type of test is very important because it illustrates how (and why) the collaborators of the class under test must
be simulated in order to drive its behaviour.

---

The class under test here is `ch.ev.unit.test.resources.step02.quarkus.services.impl.StudentServiceImpl`

It provides a method named `getById`, which takes as parameters the username of the person making the request, and the
identifier of the student to be found.

- the method fails if any of the parameters (or both) are null.
- the method fails if the user corresponding to the username is not found.
- the method fails if the student corresponding to identifier is not found.
- the method returns a `ch.ev.unit.test.resources.step02.quarkus.data.Student` instance of the found student.

---
**PLEASE NOTE:** Quarkus requires real implementation of the components to be present at compile time, so I had to
create `ch.ev.unit.test.resources.step02.quarkus.repositories.impl.StudentRepositoryDummyImpl`
and `ch.ev.unit.test.resources.step02.quarkus.services.impl.UserServiceDummyImpl`, these two classes are NOT USED in te
tests, please ignore them.

**PLEASE NOTE:** although the code to be tested changes depending on the framework used (**OSGI**, **Spring**, **Quarkus
**, etc.),
**<ins>the source code of the tests remains unchanged</ins>**.
