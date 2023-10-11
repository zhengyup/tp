package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Company.isValidName(null));

        // invalid name
        assertFalse(Company.isValidName("")); // empty string
        assertFalse(Company.isValidName(" ")); // spaces only
        assertFalse(Company.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Company.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Company.isValidName("peter jack")); // alphabets only
        assertTrue(Company.isValidName("12345")); // numbers only
        assertTrue(Company.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Company.isValidName("Capital Tan")); // with capital letters
        assertTrue(Company.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        Company company = new Company("Valid Name");

        // same values -> returns true
        assertTrue(company.equals(new Company("Valid Name")));

        // same object -> returns true
        assertTrue(company.equals(company));

        // null -> returns false
        assertFalse(company.equals(null));

        // different types -> returns false
        assertFalse(company.equals(5.0f));

        // different values -> returns false
        assertFalse(company.equals(new Company("Other Valid Name")));
    }
}
