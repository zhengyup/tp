package seedu.intern.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.intern.commons.exceptions.DataLoadingException;
import seedu.intern.model.InternTracker;
import seedu.intern.model.ReadOnlyInternBook;

/**
 * Represents a storage for {@link InternTracker}.
 */
public interface InternTrackerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternTrackerFilePath();

    /**
     * Returns InternTracker data as a {@link ReadOnlyInternBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyInternBook> readInternTracker() throws DataLoadingException;

    /**
     * @see #getInternTrackerFilePath()
     */
    Optional<ReadOnlyInternBook> readInternTracker(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyInternBook} to the storage.
     * @param internTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternTracker(ReadOnlyInternBook internTracker) throws IOException;

    /**
     * @see #saveInternTracker(ReadOnlyInternBook)
     */
    void saveInternTracker(ReadOnlyInternBook internTracker, Path filePath) throws IOException;

}
