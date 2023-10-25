package ch.ev.unit.test.resources.step01.junit5;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// in JUNIT 5 test classes can be package private
class CalculatorTest implements WithAssertions {

    Calculator testObject;

    // in JUNIT 5 test methods can be package private
    @Test
    @DisplayName("Addition fails when the first operand is null")
    void add__fails__on_first_operand_null() {

        // here we are checking for a failure, we are expecting a specific exception with a specific message.

        assertThatThrownBy(() -> testObject.add(null, null)) // this is the call we expect to throw an exception
                .isInstanceOf(NullPointerException.class) // we expect it to be an NullPointerException
                .hasMessage("'a' cannot be null!"); // and we expect it to have a specific message
    }

    // -------------------------------------------------------------------------------------------------------------
    // the naming convention used here is <method_name>__[fails|succeeds]__<condition>
    // this is just a suggestion, there are many other naming conventions that are just as valid and effective.
    // The important thing is to choose one and keep the choice consistent.
    // -------------------------------------------------------------------------------------------------------------

    // in JUNIT 5 test methods can be package private
    @Test
    @DisplayName("Addition fails when the second operand is null")
    void add__fails__on_second_operand_null() {

        // here we are checking for a failure, we are expecting a specific exception with a specific message.

        assertThatThrownBy(() -> testObject.add(10, null)) // this is the call we expect to throw an exception
                .isInstanceOf(NullPointerException.class) // we expect it to be an NullPointerException
                .hasMessage("'b' cannot be null!"); // and we expect it to have a specific message
    }

    // in JUNIT 5 test methods can be package private
    @Test
    @DisplayName("Addition succeeds and the result is correct")
    void add__succeeds() {

        final Integer result = testObject.add(10, 20);

        assertThat(result)
                .as("result is not null").isNotNull()
                .as("result has the correct value").isEqualTo(30);
    }

    @BeforeEach
    void beforeTest() {
        testObject = new Calculator();
    }

}
