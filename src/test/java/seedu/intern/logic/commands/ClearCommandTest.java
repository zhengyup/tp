package seedu.intern.logic.commands;

import static seedu.intern.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intern.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.Test;

import seedu.intern.model.InternTracker;
import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyInternTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInternTracker_success() {
        Model model = new ModelManager(getTypicalInternTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalInternTracker(), new UserPrefs());
        expectedModel.setInternTracker(new InternTracker());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
