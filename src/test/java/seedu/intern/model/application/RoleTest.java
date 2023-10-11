package seedu.intern.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidRole));
    }

    @Test
    public void isValidRole() {
        // null role number
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid role numbers
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only

        // valid role numbers
        assertTrue(Role.isValidRole("Intern"));
        assertTrue(Role.isValidRole("Software Developer Intern"));
    }

    @Test
    public void equals() {
        Role role = new Role("Intern");

        // same values -> returns true
        assertTrue(role.equals(new Role("Intern")));

        // same object -> returns true
        assertTrue(role.equals(role));

        // null -> returns false
        assertFalse(role.equals(null));

        // different types -> returns false
        assertFalse(role.equals(5.0f));

        // different values -> returns false
        assertFalse(role.equals(new Role("SWE Intern")));
    }
}
