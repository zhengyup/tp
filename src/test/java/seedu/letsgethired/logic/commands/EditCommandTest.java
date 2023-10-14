package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.DESC_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.DESC_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.letsgethired.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.UserPrefs;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.testutil.EditPersonDescriptorBuilder;
import seedu.letsgethired.testutil.InternApplicationBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalInternTracker(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        InternApplication editedInternApplication = new InternApplicationBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedInternApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages
                .format(editedInternApplication));

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());
        expectedModel.setInternApplication(model.getFilteredInternApplicationList().get(0), editedInternApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredInternApplicationList().size());
        InternApplication lastInternApplication = model.getFilteredInternApplicationList()
                .get(indexLastPerson.getZeroBased());

        InternApplicationBuilder personInList = new InternApplicationBuilder(lastInternApplication);
        InternApplication editedInternApplication = personInList.withCompany(VALID_COMPANY_B)
                .withRole(VALID_ROLE_B)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withCompany(VALID_COMPANY_B)
                .withRole(VALID_ROLE_B).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedInternApplication));

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());
        expectedModel.setInternApplication(lastInternApplication, editedInternApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, new EditPersonDescriptor());
        InternApplication editedInternApplication = model.getFilteredInternApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages
                .format(editedInternApplication));

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_APPLICATION);

        InternApplication internApplicationInFilteredList = model.getFilteredInternApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        InternApplication editedInternApplication = new InternApplicationBuilder(internApplicationInFilteredList)
                .withCompany(VALID_COMPANY_B).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditPersonDescriptorBuilder().withCompany(VALID_COMPANY_B).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedInternApplication));

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());
        expectedModel.setInternApplication(model.getFilteredInternApplicationList().get(0), editedInternApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        InternApplication firstInternApplication = model.getFilteredInternApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstInternApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_APPLICATION, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_APPLICATION);

        // edit person in filtered list into a duplicate in intern tracker
        InternApplication internApplicationInList = model.getInternTracker().getApplicationList()
                .get(INDEX_SECOND_APPLICATION.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditPersonDescriptorBuilder(internApplicationInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternApplicationList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withCompany(VALID_COMPANY_B).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of intern tracker
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of intern tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternTracker().getApplicationList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withCompany(VALID_COMPANY_B).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_APPLICATION, DESC_A);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_A);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_APPLICATION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_APPLICATION, DESC_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_APPLICATION, DESC_B)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(index, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
