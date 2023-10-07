package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Role.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Role.isValidPhone("")); // empty string
        assertFalse(Role.isValidPhone(" ")); // spaces only
        assertFalse(Role.isValidPhone("91")); // less than 3 numbers
        assertFalse(Role.isValidPhone("phone")); // non-numeric
        assertFalse(Role.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Role.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Role.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Role.isValidPhone("93121534"));
        assertTrue(Role.isValidPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        Role phone = new Role("999");

        // same values -> returns true
        assertTrue(phone.equals(new Role("999")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Role("995")));
    }
}
