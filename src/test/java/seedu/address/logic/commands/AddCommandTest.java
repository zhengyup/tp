package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternApplications.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternTracker;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.application.InternApplication;
import seedu.address.testutil.InternApplicationBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        InternApplication validInternApplication = new InternApplicationBuilder().build();

        CommandResult commandResult = new AddCommand(validInternApplication).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validInternApplication)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validInternApplication), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        InternApplication validInternApplication = new InternApplicationBuilder().build();
        AddCommand addCommand = new AddCommand(validInternApplication);
        ModelStub modelStub = new ModelStubWithPerson(validInternApplication);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        InternApplication alice = new InternApplicationBuilder().withCompany("Alice").build();
        InternApplication bob = new InternApplicationBuilder().withCompany("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(InternApplication internApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(InternApplication internApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(InternApplication target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(InternApplication target, InternApplication editedInternApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<InternApplication> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<InternApplication> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final InternApplication internApplication;

        ModelStubWithPerson(InternApplication internApplication) {
            requireNonNull(internApplication);
            this.internApplication = internApplication;
        }

        @Override
        public boolean hasPerson(InternApplication internApplication) {
            requireNonNull(internApplication);
            return this.internApplication.isSameApplication(internApplication);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<InternApplication> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(InternApplication internApplication) {
            requireNonNull(internApplication);
            return personsAdded.stream().anyMatch(internApplication::isSameApplication);
        }

        @Override
        public void addPerson(InternApplication internApplication) {
            requireNonNull(internApplication);
            personsAdded.add(internApplication);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new InternTracker();
        }
    }

}
