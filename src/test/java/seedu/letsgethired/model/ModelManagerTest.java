package seedu.letsgethired.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;
import static seedu.letsgethired.testutil.TypicalInternApplications.OPTIVER;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.core.GuiSettings;
import seedu.letsgethired.model.application.CompanyContainsKeywordsPredicate;
import seedu.letsgethired.testutil.InternTrackerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new InternTracker(), new InternTracker(modelManager.getInternTracker()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInternTrackerFilePath(Paths.get("intern/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInternTrackerFilePath(Paths.get("new/intern/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setInternTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInternTrackerFilePath(null));
    }

    @Test
    public void setInternTrackerFilePath_validPath_setsInternTrackerFilePath() {
        Path path = Paths.get("intern/book/file/path");
        modelManager.setInternTrackerFilePath(path);
        assertEquals(path, modelManager.getInternTrackerFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInInternTracker_returnsFalse() {
        assertFalse(modelManager.hasPerson(JANE_STREET));
    }

    @Test
    public void hasPerson_personInInternTracker_returnsTrue() {
        modelManager.addPerson(JANE_STREET);
        assertTrue(modelManager.hasPerson(JANE_STREET));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        InternTracker internTracker = new InternTrackerBuilder().withInternApplication(JANE_STREET).withInternApplication(OPTIVER).build();
        InternTracker differentInternTracker = new InternTracker();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(internTracker, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(internTracker, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different internBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInternTracker, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = JANE_STREET.getCompany().companyName.split("\\s+");
        modelManager.updateFilteredPersonList(new CompanyContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(internTracker, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInternTrackerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(internTracker, differentUserPrefs)));
    }
}
