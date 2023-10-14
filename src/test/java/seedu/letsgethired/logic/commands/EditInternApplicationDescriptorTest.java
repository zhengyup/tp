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
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.letsgethired.testutil.EditPersonDescriptorBuilder;

public class EditInternApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_A);
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
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_A)
                .withCompany(VALID_COMPANY_B).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different role -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_A).withRole(VALID_ROLE_B).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_A).withCycle(VALID_CYCLE_B).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different status -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_A).withStatus(VALID_STATUS_B).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_A).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_A.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        String expected = EditPersonDescriptor.class.getCanonicalName() + "{company="
                + editPersonDescriptor.getCompany().orElse(null) + ", role="
                + editPersonDescriptor.getRole().orElse(null) + ", cycle="
                + editPersonDescriptor.getCycle().orElse(null) + ", status="
                + editPersonDescriptor.getStatus().orElse(null) + ", tags="
                + editPersonDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}
