package seedu.letsgethired.model;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.letsgethired.commons.core.GuiSettings;
import seedu.letsgethired.commons.core.LogsCenter;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Represents the in-memory model of the intern tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InternTracker internTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<InternApplication> filteredInternApplications;

    /**
     * Initializes a ModelManager with the given internTracker and userPrefs.
     */
    public ModelManager(ReadOnlyInternTracker internTracker, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(internTracker, userPrefs);

        logger.fine("Initializing with intern tracker: " + internTracker + " and user prefs " + userPrefs);

        this.internTracker = new InternTracker(internTracker);
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
    public Path getInternTrackerFilePath() {
        return userPrefs.getInternTrackerFilePath();
    }

    @Override
    public void setInternTrackerFilePath(Path dataFilePath) {
        requireNonNull(dataFilePath);
        userPrefs.setInternTrackerFilePath(dataFilePath);
    }

    //=========== InternTracker ================================================================================
    @Override
    public void setInternTracker(ReadOnlyInternTracker internTracker) {
        this.internTracker.resetData(internTracker);
    }

    @Override
    public ReadOnlyInternTracker getInternTracker() {
        return internTracker;
    }

    @Override
    public boolean hasInternApplication(InternApplication internApplication) {
        requireNonNull(internApplication);
        return internTracker.hasApplication(internApplication);
    }

    @Override
    public void deleteInternApplication(InternApplication target) {
        internTracker.removeApplication(target);
    }

    @Override
    public void addInternApplication(InternApplication internApplication) {
        internTracker.addApplication(internApplication);
        updateFilteredInternApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
    }

    @Override
    public void setInternApplication(InternApplication target, InternApplication editedInternApplication) {
        requireAllNonNull(target, editedInternApplication);

        internTracker.setApplication(target, editedInternApplication);
    }

    //=========== Filtered Intern Application List Accessors =========================================================

    /**
     * Returns an unmodifiable view of the list of {@code InternApplication} backed by the internal list of
     * {@code versionedInternTracker}
     */
    @Override
    public ObservableList<InternApplication> getFilteredInternApplicationList() {
        return filteredInternApplications;
    }

    @Override
    public void updateFilteredInternApplicationList(Predicate<InternApplication> predicate) {
        requireNonNull(predicate);
        filteredInternApplications.setPredicate(predicate);
    }

    /**
     * Updates the internTracker to track the current selected application {@code target}
     */
    @Override
    public void updateCurrentApplication(InternApplication target) {
        internTracker.setCurrentApplication(target);
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
