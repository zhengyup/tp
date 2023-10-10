package seedu.intern.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.intern.commons.core.LogsCenter;
import seedu.intern.commons.exceptions.DataLoadingException;
import seedu.intern.model.ReadOnlyInternTracker;
import seedu.intern.model.ReadOnlyUserPrefs;
import seedu.intern.model.UserPrefs;

/**
 * Manages storage of InternTracker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InternTrackerStorage internBookStorageBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InternTrackerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InternTrackerStorage internBookStorageBookStorage, UserPrefsStorage userPrefsStorage) {
        this.internBookStorageBookStorage = internBookStorageBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ InternTracker methods ==============================

    @Override
    public Path getInternTrackerFilePath() {
        return internBookStorageBookStorage.getInternTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyInternTracker> readInternTracker() throws DataLoadingException {
        return readInternTracker(internBookStorageBookStorage.getInternTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyInternTracker> readInternTracker(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internBookStorageBookStorage.readInternTracker(filePath);
    }

    @Override
    public void saveInternTracker(ReadOnlyInternTracker internTracker) throws IOException {
        saveInternTracker(internTracker, internBookStorageBookStorage.getInternTrackerFilePath());
    }

    @Override
    public void saveInternTracker(ReadOnlyInternTracker internBookBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internBookStorageBookStorage.saveInternTracker(internBookBook, filePath);
    }

}
