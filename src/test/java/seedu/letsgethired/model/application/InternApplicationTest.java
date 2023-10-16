package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_B;
import static seedu.letsgethired.testutil.TypicalInternApplications.B;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.testutil.InternApplicationBuilder;

public class InternApplicationTest {
    @Test
    public void isSameInternApplication() {
        // same object -> returns true
        assertTrue(JANE_STREET.isSameApplication(JANE_STREET));

        // null -> returns false
        assertFalse(JANE_STREET.isSameApplication(null));

        // same name, all other attributes different -> returns true
        InternApplication editedInternApplication = new InternApplicationBuilder(JANE_STREET).withRole(VALID_ROLE_B)
                .withCycle(VALID_CYCLE_B)
                .withStatus(VALID_STATUS_B)
                .build();
        assertTrue(JANE_STREET.isSameApplication(editedInternApplication));

        // different name, all other attributes same -> returns false
        editedInternApplication = new InternApplicationBuilder(JANE_STREET).withCompany(VALID_COMPANY_B).build();
        assertFalse(JANE_STREET.isSameApplication(editedInternApplication));

        // name differs in case, all other attributes same -> returns false
        InternApplication editedOtherInternApplication = new InternApplicationBuilder(B)
                .withCompany(VALID_COMPANY_B.toLowerCase()).build();
        assertFalse(B.isSameApplication(editedOtherInternApplication));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_COMPANY_B + " ";
        editedOtherInternApplication = new InternApplicationBuilder(B).withCompany(nameWithTrailingSpaces).build();
        assertFalse(B.isSameApplication(editedOtherInternApplication));
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
                .withCompany(VALID_COMPANY_B)
                .build();
        assertFalse(JANE_STREET.equals(editedInternApplication));

        // different role -> returns false
        editedInternApplication = new InternApplicationBuilder(JANE_STREET).withRole(VALID_ROLE_B).build();
        assertFalse(JANE_STREET.equals(editedInternApplication));

        // different email -> returns false
        editedInternApplication = new InternApplicationBuilder(JANE_STREET).withCycle(VALID_CYCLE_B).build();
        assertFalse(JANE_STREET.equals(editedInternApplication));

        // different status -> returns false
        editedInternApplication = new InternApplicationBuilder(JANE_STREET).withStatus(VALID_STATUS_B).build();
        assertFalse(JANE_STREET.equals(editedInternApplication));
    }

    @Test
    public void toStringMethod() {
        String expected = InternApplication.class.getCanonicalName()
                + "{company=" + JANE_STREET.getCompany()
                + ", role=" + JANE_STREET.getRole()
                + ", cycle=" + JANE_STREET.getCycle()
                + ", status=" + JANE_STREET.getStatus() + "}";
        assertEquals(expected, JANE_STREET.toString());
    }
}
