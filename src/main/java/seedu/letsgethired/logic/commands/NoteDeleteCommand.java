package seedu.letsgethired.logic.commands;

import static seedu.letsgethired.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.letsgethired.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Deletes a note to an existing intern application in the interntracker.
 */
public class NoteDeleteCommand extends NoteCommand {
    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Deleted note from Intern Application";
    private final Index index;
    private final Integer targetIndex;

    /**
     * @param index of the intern application in the filtered intern application list to edit
     * @param targetIndex  index number of the application in the list to delete
     */
    public NoteDeleteCommand(Index index, Integer targetIndex) {
        requireAllNonNull(index, targetIndex);

        this.index = index;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<InternApplication> lastShownList = model.getFilteredInternApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
        }

        InternApplication internApplicationToEdit = lastShownList.get(index.getZeroBased());

        if (targetIndex > internApplicationToEdit.getNote().size()) { //targetIndex is oneBased
            throw new CommandException(NoteCommand.INVALID_NOTE_DISPLAYED_INDEX);
        }

        InternApplication editedInternApplication = internApplicationToEdit.deleteNote(targetIndex);

        model.setInternApplication(internApplicationToEdit, editedInternApplication);
        model.updateFilteredInternApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);

        return new CommandResult(MESSAGE_ADD_NOTE_SUCCESS, Messages.formatDisplay(editedInternApplication));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteDeleteCommand)) {
            return false;
        }

        NoteDeleteCommand e = (NoteDeleteCommand) other;
        return index.equals(e.index)
                && targetIndex.equals(e.targetIndex);
    }
}
