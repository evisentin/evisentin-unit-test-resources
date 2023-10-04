package ch.ev.unit.test.resources.step01.junit5;

import java.util.Objects;

/**
 * <p>Calculator class.</p>
 * <p>Base calculator class for the testing example.</p>
 *
 * @author enrico
 */
public class Calculator {

    /**
     * Adds the two operands, and returns the addition result.
     *
     * @param a operand 'a'
     * @param b operand 'b'
     * @return the addition result.
     * @throws IllegalArgumentException if any of the operands is null.
     */
    public Integer add(final Integer a, final Integer b) {
        failOnNull(a, "'a' cannot be null!");
        failOnNull(b, "'b' cannot be null!");

        return a + b;
    }

    private void failOnNull(final Object object, final String message) {
        Objects.requireNonNull(object, message);
    }
}
