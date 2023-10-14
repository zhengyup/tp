package seedu.letsgethired.logic.commands;

import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.UserPrefs;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.testutil.InternApplicationBuilder;

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
        expectedModel.addInternApplication(validInternApplication);

        assertCommandSuccess(new AddCommand(validInternApplication), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validInternApplication)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        InternApplication internApplicationInList = model.getInternTracker().getApplicationList().get(0);
        assertCommandFailure(new AddCommand(internApplicationInList), model,
                AddCommand.MESSAGE_DUPLICATE_INTERN_APPLICATION);
    }

}
