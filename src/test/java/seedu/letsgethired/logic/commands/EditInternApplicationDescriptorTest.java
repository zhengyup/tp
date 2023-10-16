package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.DESC_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.DESC_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_B;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.logic.commands.EditCommand.EditInternApplicationDescriptor;
import seedu.letsgethired.testutil.EditInternApplicationDescriptorBuilder;

public class EditInternApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditInternApplicationDescriptor descriptorWithSameValues = new EditInternApplicationDescriptor(DESC_A);
        assertTrue(DESC_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_A.equals(DESC_A));

        // null -> returns false
        assertFalse(DESC_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_A.equals(5));

        // different values -> returns false
        assertFalse(DESC_A.equals(DESC_B));

        // different name -> returns false
        EditInternApplicationDescriptor editedInternApplication = new EditInternApplicationDescriptorBuilder(DESC_A)
                .withCompany(VALID_COMPANY_B).build();
        assertFalse(DESC_A.equals(editedInternApplication));

        // different role -> returns false
        editedInternApplication = new EditInternApplicationDescriptorBuilder(DESC_A).withRole(VALID_ROLE_B).build();
        assertFalse(DESC_A.equals(editedInternApplication));

        // different email -> returns false
        editedInternApplication = new EditInternApplicationDescriptorBuilder(DESC_A).withCycle(VALID_CYCLE_B).build();
        assertFalse(DESC_A.equals(editedInternApplication));

        // different status -> returns false
        editedInternApplication = new EditInternApplicationDescriptorBuilder(DESC_A).withStatus(VALID_STATUS_B).build();
        assertFalse(DESC_A.equals(editedInternApplication));

        // different tags -> returns false
        editedInternApplication = new EditInternApplicationDescriptorBuilder(DESC_A).build();
        assertFalse(DESC_A.equals(editedInternApplication));
    }

    @Test
    public void toStringMethod() {
        EditInternApplicationDescriptor editInternApplicationDescriptor = new EditInternApplicationDescriptor();
        String expected = EditInternApplicationDescriptor.class.getCanonicalName() + "{company="
                + editInternApplicationDescriptor.getCompany().orElse(null) + ", role="
                + editInternApplicationDescriptor.getRole().orElse(null) + ", cycle="
                + editInternApplicationDescriptor.getCycle().orElse(null) + ", status="
                + editInternApplicationDescriptor.getStatus().orElse(null) + "}";
        assertEquals(expected, editInternApplicationDescriptor.toString());
    }
}
