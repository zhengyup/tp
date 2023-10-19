package seedu.letsgethired.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.letsgethired.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setInternTrackerFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setInternTrackerFilePath(null));
    }

    @Test
    public void sameUserPrefsIsEqual() {
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(userPrefs, userPrefs);
    }

    @Test
    public void differentObjectIsNotEqual() {
        UserPrefs userPrefs = new UserPrefs();
        Object obj = new Object();
        assertFalse(userPrefs.equals(obj));
    }
}
