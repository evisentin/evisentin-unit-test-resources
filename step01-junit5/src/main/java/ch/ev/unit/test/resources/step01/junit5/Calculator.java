package ch.ev.unit.test.resources.step01.junit5;

public class Calculator {

    public Integer add(Integer a, Integer b) {
        if (a == null) throw new IllegalArgumentException("'a' cannot be null!");
        if (b == null) throw new IllegalArgumentException("'b' cannot be null!");
        return a + b;
    }
}
