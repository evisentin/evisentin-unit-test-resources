package ch.ev.unit.test.resources.step01.junit4;

/**
 * Base calculator class for the testing example.
 */
public class Calculator {

    /**
     * Adds the two operands, and returns the addition result.
     *
     * @param a  - operand 'a'
     * @param b- operand 'b'
     * @return the addition result.
     * @throws IllegalArgumentException if any of the operands is null.
     */
    public Integer add(final Integer a, final Integer b) {
        failOnNull(a, "'a' cannot be null!");
        failOnNull(b, "'b' cannot be null!");

        return a + b;
    }

    private void failOnNull(final Object object, final String message) {
        if (object == null) throw new IllegalArgumentException(message);
    }
}
