package seedu.letsgethired.logic.commands;

import static seedu.letsgethired.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.letsgethired.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Note;

/**
 * Adds a note to an existing intern application in the interntracker.
 */
public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a note to the internship application "
            + "identified by the index number used in the last intern application listing. "
            + "Existing notes will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NOTE + "[NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE + "John Street is the leading market maker in the APAC region";
    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Intern Application";

    private final Index index;
    private final Note note;

    /**
     * @param index of the intern application in the filtered intern application list to edit
     * @param note note to add to the intern application
     */
    public NoteCommand(Index index, Note note) {
        requireAllNonNull(index, note);

        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<InternApplication> lastShownList = model.getFilteredInternApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
        }

        InternApplication internApplicationToEdit = lastShownList.get(index.getZeroBased());
        InternApplication editedInternApplication = internApplicationToEdit.addNote(note);

        model.setInternApplication(internApplicationToEdit, editedInternApplication);
        model.updateFilteredInternApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);

        return new CommandResult(MESSAGE_ADD_NOTE_SUCCESS, Messages.formatDisplay(editedInternApplication));
    }

//    private String generateSuccessMessage() {
//        String message = !note.value.isEmpty() ? MESSAGE_ADD_NOTE_SUCCESS : MESSAGE_ADD_NOTE_FAILURE;
//        return message;
//    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }

        NoteCommand e = (NoteCommand) other;
        return index.equals(e.index)
                && note.equals(e.note);
    }
}
