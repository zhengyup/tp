package seedu.intern.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.intern.commons.core.GuiSettings;
import seedu.intern.logic.commands.CommandResult;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.logic.parser.exceptions.ParseException;
import seedu.intern.model.ReadOnlyInternTracker;
import seedu.intern.model.application.InternApplication;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the InternTracker.
     *
     * @see seedu.intern.model.Model#getInternTracker()
     */
    ReadOnlyInternTracker getInternTracker();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<InternApplication> getFilteredPersonList();

    /**
     * Returns the user prefs' intern tracker file path.
     */
    Path getInternTrackerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
