package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CycleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cycle(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Cycle(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Cycle.isValidCycle(null));

        // blank email
        assertFalse(Cycle.isValidCycle("")); // empty string
        assertFalse(Cycle.isValidCycle(" ")); // spaces only

        // missing parts
        assertFalse(Cycle.isValidCycle("@example.com")); // missing local part
        assertFalse(Cycle.isValidCycle("peterjackexample.com")); // missing '@' symbol
        assertFalse(Cycle.isValidCycle("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Cycle.isValidCycle("peterjack@-")); // invalid domain name
        assertFalse(Cycle.isValidCycle("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Cycle.isValidCycle("peter jack@example.com")); // spaces in local part
        assertFalse(Cycle.isValidCycle("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Cycle.isValidCycle(" peterjack@example.com")); // leading space
        assertFalse(Cycle.isValidCycle("peterjack@example.com ")); // trailing space
        assertFalse(Cycle.isValidCycle("peterjack@@example.com")); // double '@' symbol
        assertFalse(Cycle.isValidCycle("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Cycle.isValidCycle("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Cycle.isValidCycle("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Cycle.isValidCycle("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(Cycle.isValidCycle("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Cycle.isValidCycle("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Cycle.isValidCycle("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Cycle.isValidCycle("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Cycle.isValidCycle("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Cycle.isValidCycle("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(Cycle.isValidCycle("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Cycle.isValidCycle("PeterJack.1190@example.com")); // period in local part
        assertTrue(Cycle.isValidCycle("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Cycle.isValidCycle("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Cycle.isValidCycle("a@bc")); // minimal
        assertTrue(Cycle.isValidCycle("test@localhost")); // alphabets only
        assertTrue(Cycle.isValidCycle("123@145")); // numeric local part and domain name
        assertTrue(Cycle.isValidCycle("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Cycle.isValidCycle("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Cycle.isValidCycle("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Cycle.isValidCycle("e1234567@u.nus.edu")); // more than one period in domain
    }

    @Test
    public void equals() {
        Cycle cycle = new Cycle("Summer 2024");

        // same values -> returns true
        assertTrue(cycle.equals(new Cycle("Summer 2024")));

        // same object -> returns true
        assertTrue(cycle.equals(cycle));

        // null -> returns false
        assertFalse(cycle.equals(null));

        // different types -> returns false
        assertFalse(cycle.equals(5.0f));

        // different values -> returns false
        assertFalse(cycle.equals(new Cycle("Summer 2023")));
    }
}
