package seedu.intern.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_CYCLE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.intern.testutil.Assert.assertThrows;
import static seedu.intern.testutil.TypicalInternApplications.ALICE;
import static seedu.intern.testutil.TypicalInternApplications.BOB;

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
        assertTrue(ALICE.isSameApplication(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameApplication(null));

        // same name, all other attributes different -> returns true
        InternApplication editedAlice = new InternApplicationBuilder(ALICE).withRole(VALID_ROLE_BOB)
                .withCycle(VALID_CYCLE_BOB)
                .withStatus(VALID_STATUS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameApplication(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new InternApplicationBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameApplication(editedAlice));

        // name differs in case, all other attributes same -> returns false
        InternApplication editedBob = new InternApplicationBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameApplication(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new InternApplicationBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameApplication(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        InternApplication aliceCopy = new InternApplicationBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        InternApplication editedAlice = new InternApplicationBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different role -> returns false
        editedAlice = new InternApplicationBuilder(ALICE).withRole(VALID_ROLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new InternApplicationBuilder(ALICE).withCycle(VALID_CYCLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different status -> returns false
        editedAlice = new InternApplicationBuilder(ALICE).withStatus(VALID_STATUS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new InternApplicationBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = InternApplication.class.getCanonicalName() + "{name=" + ALICE.getName()
                + ", role=" + ALICE.getRole()
                + ", cycle=" + ALICE.getCycle() + ", status=" + ALICE.getStatus() + ", tags="
                + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
