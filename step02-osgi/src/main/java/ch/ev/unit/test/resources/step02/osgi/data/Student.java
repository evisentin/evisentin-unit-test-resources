package ch.ev.unit.test.resources.step02.osgi.data;

import lombok.Builder;
import lombok.Data;

/**
 * <p>Student class.</p>
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
