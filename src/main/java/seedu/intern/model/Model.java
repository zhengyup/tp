package seedu.intern.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.intern.commons.core.GuiSettings;
import seedu.intern.model.application.InternApplication;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<InternApplication> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' intern book file path.
     */
    Path getInternBookFilePath();

    /**
     * Sets the user prefs' intern book file path.
     */
    void setInternBookFilePath(Path internBookFilePath);

    /**
     * Replaces intern book data with the data in {@code internBookBook}.
     */
    void setInternBook(ReadOnlyInternBook internBookBook);

    /** Returns the InternBook */
    ReadOnlyInternBook getInternBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the intern book.
     */
    boolean hasPerson(InternApplication internApplication);

    /**
     * Deletes the given person.
     * The person must exist in the intern book.
     */
    void deletePerson(InternApplication target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the intern book.
     */
    void addPerson(InternApplication internApplication);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the intern book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the intern book.
     */
    void setPerson(InternApplication target, InternApplication editedInternApplication);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<InternApplication> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<InternApplication> predicate);
}
