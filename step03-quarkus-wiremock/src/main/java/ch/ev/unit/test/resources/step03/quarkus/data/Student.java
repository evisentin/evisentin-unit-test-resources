package ch.ev.unit.test.resources.step03.quarkus.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
}
