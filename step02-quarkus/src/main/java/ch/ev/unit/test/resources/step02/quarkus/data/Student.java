package ch.ev.unit.test.resources.step02.quarkus.data;

import lombok.Builder;
import lombok.Data;

/**
 * A basic representation of a student's information.
 *
 * @author enrico
 */
@Data
@Builder
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
}
