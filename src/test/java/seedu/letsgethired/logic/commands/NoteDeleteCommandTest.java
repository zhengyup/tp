package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternApplications;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.UserPrefs;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Contains integration tests (interaction with the Model) and unit tests for NoteInsertCommand.
 */
public class NoteDeleteCommandTest {

    private Model model = new ModelManager(getTypicalInternTracker(), new UserPrefs());

    @Test
    public void execute_invalidInternApplicationIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternApplicationList().size() + 1);
        NoteDeleteCommand noteCommand = new NoteDeleteCommand(outOfBoundIndex, 0);

        assertCommandFailure(noteCommand, model, Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validNoteCommandDelete_success() {
        InternApplication internApplication = getTypicalInternApplications().get(0);
        InternApplication editedInternApplication = internApplication.deleteNote(1);
        NoteDeleteCommand noteCommand = new NoteDeleteCommand(INDEX_FIRST_APPLICATION, 1);

        CommandResult expectedResult = new CommandResult(NoteDeleteCommand.MESSAGE_ADD_NOTE_SUCCESS,
                Messages.formatDisplay(editedInternApplication));

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());
        expectedModel.setInternApplication(model.getFilteredInternApplicationList().get(0), editedInternApplication);

        assertCommandSuccess(noteCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void equals() {
        final NoteDeleteCommand standardCommand = new NoteDeleteCommand(INDEX_FIRST_APPLICATION, 1);

        // same values -> returns true
        NoteDeleteCommand commandWithSameValues = new NoteDeleteCommand(INDEX_FIRST_APPLICATION, 1);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new NoteDeleteCommand(INDEX_SECOND_APPLICATION, 1)));

        // different targetIndexes -> returns false
        assertFalse(standardCommand.equals(new NoteDeleteCommand(INDEX_FIRST_APPLICATION, 2)));
    }
}
