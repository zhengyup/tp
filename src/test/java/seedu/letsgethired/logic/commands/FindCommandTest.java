package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.letsgethired.logic.Messages.MESSAGE_INTERN_APPLICATIONS_LISTED_OVERVIEW;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.letsgethired.testutil.TypicalInternApplications.GOOGLE;
import static seedu.letsgethired.testutil.TypicalInternApplications.GRAB;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.UserPrefs;
import seedu.letsgethired.model.application.CompanyPartialMatchPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalInternTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternTracker(), new UserPrefs());

    @Test
    public void equals() {
        CompanyPartialMatchPredicate firstPredicate =
                new CompanyPartialMatchPredicate("first");
        CompanyPartialMatchPredicate secondPredicate =
                new CompanyPartialMatchPredicate("second");

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different intern application -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_longString_noInternApplicationFound() {
        String expectedMessage = String.format(MESSAGE_INTERN_APPLICATIONS_LISTED_OVERVIEW, 0);
        CompanyPartialMatchPredicate predicate = new CompanyPartialMatchPredicate("Lorem ipsum 1234");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredInternApplicationList());
    }

    @Test
    public void execute_multipleKeywords_multipleInternApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_INTERN_APPLICATIONS_LISTED_OVERVIEW, 2);
        CompanyPartialMatchPredicate predicate = new CompanyPartialMatchPredicate("G");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GOOGLE, GRAB), model.getFilteredInternApplicationList());
    }

    @Test
    public void toStringMethod() {
        CompanyPartialMatchPredicate predicate = new CompanyPartialMatchPredicate("Bytedance");
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}