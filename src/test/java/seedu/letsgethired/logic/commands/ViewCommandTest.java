package seedu.letsgethired.logic.commands;

import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.UserPrefs;
import seedu.letsgethired.model.application.InternApplication;

import org.junit.jupiter.api.Test;

import static seedu.letsgethired.logic.commands.CommandTestUtil.*;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalInternTracker(), new UserPrefs());
    @Test
    public void execute_validIndexUnfilteredList_success() {
        InternApplication internApplicationToView = model
                .getFilteredInternApplicationList()
                .get(INDEX_FIRST_APPLICATION
                        .getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_INTERN_APPLICATION_SUCCESS,
                Messages.format(internApplicationToView));

        ModelManager expectedModel = new ModelManager(model.getInternTracker(), new UserPrefs());
        expectedModel.updateCurrentApplication(internApplicationToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }
}
