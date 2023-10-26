package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_SUMMER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_WINTER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_BACK_END;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_FULL_STACK;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_REJECTED;
import static seedu.letsgethired.testutil.TypicalInternApplications.B;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.testutil.InternApplicationBuilder;

public class InternApplicationTest {

    private void assertSameAttributes(InternApplication originalApplication,
                                      InternApplication clonedApplication) {
        assertEquals(originalApplication.getCompany(), clonedApplication.getCompany());
        assertEquals(originalApplication.getRole(), clonedApplication.getRole());
        assertEquals(originalApplication.getCycle(), clonedApplication.getCycle());
        assertEquals(originalApplication.getNote(), clonedApplication.getNote());
        assertEquals(originalApplication.getStatus(), clonedApplication.getStatus());
    }
    @Test
    public void isSameInternApplication() {
        // same object -> returns true
        assertTrue(JANE_STREET.isSameApplication(JANE_STREET));

        // null -> returns false
        assertFalse(JANE_STREET.isSameApplication(null));

        // same company, role, cycle -> returns true
        InternApplication editedInternApplication = new InternApplicationBuilder(JANE_STREET)
                .withRole(VALID_ROLE_FULL_STACK)
                .withCycle(VALID_CYCLE_SUMMER)
                .withStatus(VALID_STATUS_REJECTED)
                .withDeadline(VALID_DEADLINE)
                .withNote("")
                .build();
        assertTrue(JANE_STREET.isSameApplication(editedInternApplication));

        // different name, all other attributes same -> returns false
        editedInternApplication = new InternApplicationBuilder(JANE_STREET)
                .withCompany(VALID_COMPANY_BYTEDANCE)
                .build();
        assertFalse(JANE_STREET.isSameApplication(editedInternApplication));

        // name differs in case, all other attributes same -> returns false
        InternApplication editedOtherInternApplication = new InternApplicationBuilder(B)
                .withCompany(VALID_COMPANY_BYTEDANCE.toLowerCase()).build();
        assertFalse(B.isSameApplication(editedOtherInternApplication));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_COMPANY_BYTEDANCE + " ";
        editedOtherInternApplication = new InternApplicationBuilder(B).withCompany(nameWithTrailingSpaces).build();
        assertFalse(B.isSameApplication(editedOtherInternApplication));
    }

    @Test
    public void testClone() {
        // Create an instance of InternApplication for testing
        InternApplication originalApplication = JANE_STREET;

        // Call the clone method
        InternApplication clonedApplication = originalApplication.clone();

        // Verify that the cloned object is not the same reference
        assertNotSame(originalApplication, clonedApplication);

        // Verify that the cloned object has the same values
        assertSameAttributes(originalApplication,clonedApplication);
    }

    @Test
    public void equals() {
        // same values -> returns true
        InternApplication internApplicationCopy = new InternApplicationBuilder(JANE_STREET).build();
        assertTrue(JANE_STREET.equals(internApplicationCopy));

        // same object -> returns true
        assertTrue(JANE_STREET.equals(JANE_STREET));

        // null -> returns false
        assertFalse(JANE_STREET.equals(null));

        // different type -> returns false
        assertFalse(JANE_STREET.equals(5));

        // different intern application -> returns false
        assertFalse(JANE_STREET.equals(B));

        // different name -> returns false
        InternApplication editedInternApplication = new InternApplicationBuilder(JANE_STREET)
                .withCompany(VALID_COMPANY_BYTEDANCE)
                .build();
        assertFalse(JANE_STREET.equals(editedInternApplication));

        // different role -> returns false
        editedInternApplication = new InternApplicationBuilder(JANE_STREET).withRole(VALID_ROLE_BACK_END).build();
        assertFalse(JANE_STREET.equals(editedInternApplication));

        // different cycle -> returns false
        editedInternApplication = new InternApplicationBuilder(JANE_STREET).withCycle(VALID_CYCLE_WINTER).build();
        assertFalse(JANE_STREET.equals(editedInternApplication));

        // different status -> returns true
        editedInternApplication = new InternApplicationBuilder(JANE_STREET).withStatus(VALID_STATUS_REJECTED).build();
        assertTrue(JANE_STREET.equals(editedInternApplication));

        // different deadline -> returns true
        editedInternApplication = new InternApplicationBuilder(JANE_STREET).withDeadline(VALID_DEADLINE).build();
        assertTrue(JANE_STREET.equals(editedInternApplication));

        // different note -> returns true
        editedInternApplication = new InternApplicationBuilder(JANE_STREET).withNote("").build();
        assertTrue(JANE_STREET.equals(editedInternApplication));
    }

    @Test
    public void toStringMethod() {
        String expected = InternApplication.class.getCanonicalName()
                + "{company=" + JANE_STREET.getCompany()
                + ", role=" + JANE_STREET.getRole()
                + ", cycle=" + JANE_STREET.getCycle()
                + ", note=" + JANE_STREET.getNote()
                + ", status=" + JANE_STREET.getStatus()
                + ", deadline=" + JANE_STREET.getDeadline()
                + "}";
        assertEquals(expected, JANE_STREET.toString());
    }
}
