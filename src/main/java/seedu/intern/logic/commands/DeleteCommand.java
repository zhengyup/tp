package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.intern.commons.core.index.Index;
import seedu.intern.commons.util.ToStringBuilder;
import seedu.intern.logic.Messages;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.application.InternApplication;

/**
 * Deletes a person identified using it's displayed index from the intern tracker.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternApplication> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        InternApplication internApplicationToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(internApplicationToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(internApplicationToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
