package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_NOTE_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_NOTE_JANE_STREET;
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
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.testutil.InternApplicationBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for NoteCommand.
 */
public class NoteCommandTest {

    private Model model = new ModelManager(getTypicalInternTracker(), new UserPrefs());

    @Test
    public void execute_invalidInternApplicationIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternApplicationList().size() + 1);
        NoteCommand noteCommand = new NoteCommand(outOfBoundIndex,
                new InternApplicationBuilder().build().getNote());

        assertCommandFailure(noteCommand, model, Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validNoteCommandAdd_success() {
        InternApplication editedInternApplication = getTypicalInternApplications().get(0);
        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_APPLICATION,
                new InternApplicationBuilder().build().getNote());

        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS,
                Messages.format(editedInternApplication));

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());
        expectedModel.setInternApplication(model.getFilteredInternApplicationList().get(0), editedInternApplication);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNoteCommandDelete_success() {
        Note emptyNote = new Note("");

        InternApplication editedInternApplication = new InternApplicationBuilder(getTypicalInternApplications().get(0))
                .withNote("").build();
        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_APPLICATION, emptyNote);

        String expectedMessage = String.format(NoteCommand.MESSAGE_DELETE_NOTE_SUCCESS,
                Messages.format(editedInternApplication));

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());
        expectedModel.setInternApplication(model.getFilteredInternApplicationList().get(0), editedInternApplication);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final NoteCommand standardCommand = new NoteCommand(INDEX_FIRST_APPLICATION,
                new Note(VALID_NOTE_JANE_STREET));

        // same values -> returns true
        NoteCommand commandWithSameValues = new NoteCommand(INDEX_FIRST_APPLICATION,
                new Note(VALID_NOTE_JANE_STREET));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new NoteCommand(INDEX_SECOND_APPLICATION,
                new Note(VALID_NOTE_JANE_STREET))));

        // different note -> returns false
        assertFalse(standardCommand.equals(new NoteCommand(INDEX_FIRST_APPLICATION,
                new Note(VALID_NOTE_BYTEDANCE))));
    }
}
