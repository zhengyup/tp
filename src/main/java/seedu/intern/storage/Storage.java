package seedu.intern.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.intern.commons.exceptions.DataLoadingException;
import seedu.intern.model.ReadOnlyInternBook;
import seedu.intern.model.ReadOnlyUserPrefs;
import seedu.intern.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends InternTrackerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getInternTrackerFilePath();

    @Override
    Optional<ReadOnlyInternBook> readInternTracker() throws DataLoadingException;

    @Override
    void saveInternTracker(ReadOnlyInternBook internTracker) throws IOException;

}
