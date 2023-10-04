package ch.ev.unit.test.resources.step01.junit4;

import org.assertj.core.api.WithAssertions;
import org.junit.Before;
import org.junit.Test;

// in JUNIT 4 test classes MUST be public
public class CalculatorTest implements WithAssertions {

    Calculator testObject;

    // in JUNIT 4 test methods MUST be public
    @Test
    public void add__fails__on_first_operand_null() {

        // here we are checking for a failure, we are expecting a specific exception with a specific message.

        assertThatThrownBy(() -> testObject.add(null, null)) // this is the call we expect to throw an exception
                .isInstanceOf(NullPointerException.class) // we expect it to be an NullPointerException
                .hasMessage("'a' cannot be null!"); // and we expect it to have a specific message
    }

    // -------------------------------------------------------------------------------------------------------------
    // the naming convention used here is <method_name>__[fails|succeeds]__<condition>
    // this is just a suggestion, there are many other named conventions that are just as valid and effective.
    // The important thing is to choose one and keep the choice consistent.
    // -------------------------------------------------------------------------------------------------------------

    // in JUNIT 4 test methods MUST be public
    @Test
    public void add__fails__on_second_operand_null() {

        // here we are checking for a failure, we are expecting a specific exception with a specific message.

        assertThatThrownBy(() -> testObject.add(10, null)) // this is the call we expect to throw an exception
                .isInstanceOf(NullPointerException.class) // we expect it to be an NullPointerException
                .hasMessage("'b' cannot be null!"); // and we expect it to have a specific message
    }

    // in JUNIT 4 test methods MUST be public
    @Test
    public void add__succeeds() {

        final Integer result = testObject.add(10, 20);

        assertThat(result)
                .as("result is not null").isNotNull()
                .as("result has the correct value").isEqualTo(30);

    }

    // in JUNIT 4 test methods MUST be public
    @Before
    public void beforeTest() {
        testObject = new Calculator();
    }

}
