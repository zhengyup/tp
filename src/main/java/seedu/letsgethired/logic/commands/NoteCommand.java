package seedu.letsgethired.logic.commands;

import static seedu.letsgethired.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.Note;

public class NoteCommand extends Command {

    private final Index index;
    private final Note note;

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a note to the internship application "
            + "identified by the index number used in the last intern application listing. "
            + "Existing notes will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NOTE + "[NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE + "John Street is the leading market maker in the APAC region";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Note: %2$s";

    public NoteCommand(Index index, Note note) {
        requireAllNonNull(index, note);

        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, index.getOneBased(), note));
    }

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
