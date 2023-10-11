package seedu.intern.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid statuses
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only

        // valid statuses
        assertTrue(Status.isValidStatus("Blk 456, Den Road, #01-355"));
        assertTrue(Status.isValidStatus("-")); // one character
        assertTrue(Status.isValidStatus("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long status
    }

    @Test
    public void equals() {
        Status status = new Status("Valid Status");

        // same values -> returns true
        assertTrue(status.equals(new Status("Valid Status")));

        // same object -> returns true
        assertTrue(status.equals(status));

        // null -> returns false
        assertFalse(status.equals(null));

        // different types -> returns false
        assertFalse(status.equals(5.0f));

        // different values -> returns false
        assertFalse(status.equals(new Status("Other Valid Status")));
    }
}
