package seedu.letsgethired.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.letsgethired.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.exceptions.IllegalValueException;
import seedu.letsgethired.commons.util.JsonUtil;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.testutil.TypicalInternApplications;

public class JsonSerializableInternTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInternTrackerTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsInternTracker.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonInternTracker.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonInternTracker.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableInternTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableInternTracker.class).get();
        InternTracker internTrackerFromFile = dataFromFile.toModelType();
        InternTracker typicalPersonsInternTracker = TypicalInternApplications.getTypicalInternTracker();
        assertEquals(internTrackerFromFile, typicalPersonsInternTracker);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternTracker dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableInternTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableInternTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableInternTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternTracker.MESSAGE_DUPLICATE_INTERN_APPLICATION,
                dataFromFile::toModelType);
    }

}
