package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.internApplication.InternApplication;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InternTracker internTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<InternApplication> filteredInternApplications;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.internTracker = new InternTracker(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredInternApplications = new FilteredList<>(this.internTracker.getApplicationList());
    }

    public ModelManager() {
        this(new InternTracker(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.internTracker.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return internTracker;
    }

    @Override
    public boolean hasPerson(InternApplication internApplication) {
        requireNonNull(internApplication);
        return internTracker.hasApplication(internApplication);
    }

    @Override
    public void deletePerson(InternApplication target) {
        internTracker.removeApplication(target);
    }

    @Override
    public void addPerson(InternApplication internApplication) {
        internTracker.addApplication(internApplication);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(InternApplication target, InternApplication editedInternApplication) {
        requireAllNonNull(target, editedInternApplication);

        internTracker.setApplication(target, editedInternApplication);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<InternApplication> getFilteredPersonList() {
        return filteredInternApplications;
    }

    @Override
    public void updateFilteredPersonList(Predicate<InternApplication> predicate) {
        requireNonNull(predicate);
        filteredInternApplications.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return internTracker.equals(otherModelManager.internTracker)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredInternApplications.equals(otherModelManager.filteredInternApplications);
    }

}
