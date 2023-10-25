package seedu.letsgethired.model;

import java.util.Stack;

import seedu.letsgethired.model.application.UniqueApplicationList;

/**
 * Wraps all data at the intern-tracker level
 * Duplicates are not allowed (by .isSameApplication comparison)
 * Stores previous state history in savedStates to enable undo actions
 */
public class VersionedInternTracker extends InternTracker {
    private Stack<UniqueApplicationList> savedStates;

    /**
     * Creates an InternTracker with no entries
     */
    public VersionedInternTracker() {
        super();
        this.savedStates = new Stack<>();
    }

    /**
     * Creates an InternTracker using the InternApplications in the {@code toBeCopied}
     */
    public VersionedInternTracker(ReadOnlyInternTracker toBeCopied) {
        this();
        this.savedStates = new Stack<>();
        resetData(toBeCopied);
    }

    /**
     * Saves the current internApplications state
     */
    public void commit() {
        UniqueApplicationList currentTasks = super.getInternApplications().clone();
        savedStates.add(currentTasks);
    }

    /**
     * Deletes all existing intern applications being tracked
     */
    public void clear() {
        saveTasks();
        super.setInternApplications(new UniqueApplicationList());
    }
}

