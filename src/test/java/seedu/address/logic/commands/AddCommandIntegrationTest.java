package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.InternApplication;
import seedu.address.testutil.InternApplicationBuilder;

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

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validInternApplication);

        assertCommandSuccess(new AddCommand(validInternApplication), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validInternApplication)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        InternApplication internApplicationInList = model.getAddressBook().getApplicationList().get(0);
        assertCommandFailure(new AddCommand(internApplicationInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
