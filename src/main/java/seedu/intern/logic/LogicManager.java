package seedu.intern.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.intern.commons.core.GuiSettings;
import seedu.intern.commons.core.LogsCenter;
import seedu.intern.logic.commands.Command;
import seedu.intern.logic.commands.CommandResult;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.logic.parser.InternTrackerParser;
import seedu.intern.logic.parser.exceptions.ParseException;
import seedu.intern.model.Model;
import seedu.intern.model.ReadOnlyInternBook;
import seedu.intern.model.application.InternApplication;
import seedu.intern.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final InternTrackerParser internBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        internBookParser = new InternTrackerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = internBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveInternTracker(model.getInternBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInternBook getInternBook() {
        return model.getInternBook();
    }

    @Override
    public ObservableList<InternApplication> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getInternBookFilePath() {
        return model.getInternBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
