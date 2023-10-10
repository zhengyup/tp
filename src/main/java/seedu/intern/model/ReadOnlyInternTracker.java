package seedu.intern.model;

import javafx.collections.ObservableList;
import seedu.intern.model.application.InternApplication;

/**
 * Unmodifiable view of an intern tracker
 */
public interface ReadOnlyInternTracker {

    /**
     * Returns an unmodifiable view of the intern application list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<InternApplication> getApplicationList();

}
