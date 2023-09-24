package ch.ev.unit.test.resources.step01.junit5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// in JUNIT 5 test classes can be package private
class CalculatorTest {

    Calculator testObject;

    // in JUNIT 5 test methods can be package private
    @Test
    void add__fails__on_first_operand_null() {

        // here we are checking for a failure, we are expecting a specific exception with a specific message.

        Assertions.assertThatThrownBy(() -> testObject.add(null, null))// this is the call we expect to throw an exception
                .isInstanceOf(IllegalArgumentException.class) // we expect it to be an IllegalArgumentException
                .hasMessage("'a' cannot be null!"); // and we expect it to have a specific message
    }

    // -------------------------------------------------------------------------------------------------------------
    // the naming convention used here is <method_name>__[fails|succeeds]__<condition>
    // this is just a suggestion, there are many other named conventions that are just as valid and effective.
    // The important thing is to choose one and keep the choice consistent.
    // -------------------------------------------------------------------------------------------------------------

    // in JUNIT 5 test methods can be package private
    @Test
    void add__fails__on_second_operand_null() {

        // here we are checking for a failure, we are expecting a specific exception with a specific message.

        Assertions.assertThatThrownBy(() -> testObject.add(10, null))// this is the call we expect to throw an exception
                .isInstanceOf(IllegalArgumentException.class) // we expect it to be an IllegalArgumentException
                .hasMessage("'b' cannot be null!"); // and we expect it to have a specific message
    }

    // in JUNIT 5 test methods can be package private
    @Test
    void add__succeeds() {

        final Integer result = testObject.add(10, 20);

        Assertions.assertThat(result) // here we are testng the result
                .isNotNull() // we expect it not to be NULL
                .isEqualTo(30); // AND we expect it to be a specific value
    }

    @BeforeEach
    void beforeTest() {
        testObject = new Calculator();
    }

}
