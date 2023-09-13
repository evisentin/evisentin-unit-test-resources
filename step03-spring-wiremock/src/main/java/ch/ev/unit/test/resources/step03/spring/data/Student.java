package ch.ev.unit.test.resources.step03.spring.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
}
