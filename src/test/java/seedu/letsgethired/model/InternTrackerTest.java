package seedu.letsgethired.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_B;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.exceptions.DuplicateInternApplicationException;
import seedu.letsgethired.testutil.InternApplicationBuilder;

public class InternTrackerTest {

    private final InternTracker internTracker = new InternTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internTracker.getApplicationList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInternTracker_replacesData() {
        InternTracker newData = getTypicalInternTracker();
        internTracker.resetData(newData);
        assertEquals(newData, internTracker);
    }

    @Test
    public void resetData_withDuplicateInternApplication_throwsDuplicateApplicationException() {
        // Two applications with the same identity fields
        InternApplication editedAlice = new InternApplicationBuilder(JANE_STREET)
                .withStatus(VALID_STATUS_B)
                .build();
        List<InternApplication> newInternApplications = Arrays.asList(JANE_STREET, editedAlice);
        InternTrackerStub newData = new InternTrackerStub(newInternApplications);

        assertThrows(DuplicateInternApplicationException.class, () -> internTracker.resetData(newData));
    }

    @Test
    public void hasInternApplication_nullInternApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internTracker.hasApplication(null));
    }

    @Test
    public void hasInternApplication_internApplicationNotInInternTracker_returnsFalse() {
        assertFalse(internTracker.hasApplication(JANE_STREET));
    }

    @Test
    public void hasInternApplication_internApplicationInInternTracker_returnsTrue() {
        internTracker.addApplication(JANE_STREET);
        assertTrue(internTracker.hasApplication(JANE_STREET));
    }

    @Test
    public void hasInternApplication_internApplicationWithSameIdentityFieldsInInternTracker_returnsTrue() {
        internTracker.addApplication(JANE_STREET);
        InternApplication editedAlice = new InternApplicationBuilder(JANE_STREET)
                .withStatus(VALID_STATUS_B)
                .build();
        assertTrue(internTracker.hasApplication(editedAlice));
    }

    @Test
    public void getInternApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internTracker.getApplicationList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = InternTracker.class.getCanonicalName() + "{applications=" + internTracker
                .getApplicationList() + "}";
        assertEquals(expected, internTracker.toString());
    }

    /**
     * A stub ReadOnlyInternTracker whose intern applications list can violate interface constraints.
     */
    private static class InternTrackerStub implements ReadOnlyInternTracker {
        private final ObservableList<InternApplication> internApplications = FXCollections.observableArrayList();

        InternTrackerStub(Collection<InternApplication> internApplications) {
            this.internApplications.setAll(internApplications);
        }

        @Override
        public ObservableList<InternApplication> getApplicationList() {
            return internApplications;
        }
    }

}
