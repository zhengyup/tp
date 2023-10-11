package seedu.intern.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_CYCLE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.intern.testutil.Assert.assertThrows;
import static seedu.intern.testutil.TypicalInternApplications.BOB;
import static seedu.intern.testutil.TypicalInternApplications.JANE_STREET;

import org.junit.jupiter.api.Test;

import seedu.intern.testutil.InternApplicationBuilder;

public class InternApplicationTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        InternApplication internApplication = new InternApplicationBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internApplication.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(JANE_STREET.isSameApplication(JANE_STREET));

        // null -> returns false
        assertFalse(JANE_STREET.isSameApplication(null));

        // same name, all other attributes different -> returns true
        InternApplication editedAlice = new InternApplicationBuilder(JANE_STREET).withRole(VALID_ROLE_BOB)
                .withCycle(VALID_CYCLE_BOB)
                .withStatus(VALID_STATUS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(JANE_STREET.isSameApplication(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new InternApplicationBuilder(JANE_STREET).withCompany(VALID_COMPANY_BOB).build();
        assertFalse(JANE_STREET.isSameApplication(editedAlice));

        // name differs in case, all other attributes same -> returns false
        InternApplication editedBob = new InternApplicationBuilder(BOB)
                .withCompany(VALID_COMPANY_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameApplication(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_COMPANY_BOB + " ";
        editedBob = new InternApplicationBuilder(BOB).withCompany(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameApplication(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        InternApplication aliceCopy = new InternApplicationBuilder(JANE_STREET).build();
        assertTrue(JANE_STREET.equals(aliceCopy));

        // same object -> returns true
        assertTrue(JANE_STREET.equals(JANE_STREET));

        // null -> returns false
        assertFalse(JANE_STREET.equals(null));

        // different type -> returns false
        assertFalse(JANE_STREET.equals(5));

        // different person -> returns false
        assertFalse(JANE_STREET.equals(BOB));

        // different name -> returns false
        InternApplication editedAlice = new InternApplicationBuilder(JANE_STREET).withCompany(VALID_COMPANY_BOB)
                .build();
        assertFalse(JANE_STREET.equals(editedAlice));

        // different role -> returns false
        editedAlice = new InternApplicationBuilder(JANE_STREET).withRole(VALID_ROLE_BOB).build();
        assertFalse(JANE_STREET.equals(editedAlice));

        // different email -> returns false
        editedAlice = new InternApplicationBuilder(JANE_STREET).withCycle(VALID_CYCLE_BOB).build();
        assertFalse(JANE_STREET.equals(editedAlice));

        // different status -> returns false
        editedAlice = new InternApplicationBuilder(JANE_STREET).withStatus(VALID_STATUS_BOB).build();
        assertFalse(JANE_STREET.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new InternApplicationBuilder(JANE_STREET).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(JANE_STREET.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = InternApplication.class.getCanonicalName() + "{company=" + JANE_STREET.getCompany()
                + ", role=" + JANE_STREET.getRole()
                + ", cycle=" + JANE_STREET.getCycle() + ", status=" + JANE_STREET.getStatus() + ", tags="
                + JANE_STREET.getTags() + "}";
        assertEquals(expected, JANE_STREET.toString());
    }
}
