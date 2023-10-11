package seedu.intern.logic.commands;

import static seedu.intern.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intern.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intern.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.intern.logic.Messages;
import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.UserPrefs;
import seedu.intern.model.application.InternApplication;
import seedu.intern.testutil.InternApplicationBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternTracker(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        InternApplication validInternApplication = new InternApplicationBuilder().build();

        Model expectedModel = new ModelManager(model.getInternTracker(), new UserPrefs());
        expectedModel.addPerson(validInternApplication);

        assertCommandSuccess(new AddCommand(validInternApplication), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validInternApplication)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        InternApplication internApplicationInList = model.getInternTracker().getApplicationList().get(0);
        assertCommandFailure(new AddCommand(internApplicationInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
