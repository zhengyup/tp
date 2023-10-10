package seedu.intern.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.intern.testutil.Assert.assertThrows;
import static seedu.intern.testutil.TypicalInternApplications.ALICE;
import static seedu.intern.testutil.TypicalInternApplications.getTypicalInternTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.intern.model.application.InternApplication;
import seedu.intern.model.application.exceptions.DuplicateApplicationException;
import seedu.intern.testutil.InternApplicationBuilder;

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
    public void resetData_withValidReadOnlyStatusBook_replacesData() {
        InternTracker newData = getTypicalInternTracker();
        internTracker.resetData(newData);
        assertEquals(newData, internTracker);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        InternApplication editedAlice = new InternApplicationBuilder(ALICE).withStatus(VALID_STATUS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        List<InternApplication> newInternApplications = Arrays.asList(ALICE, editedAlice);
        InternTrackerStub newData = new InternTrackerStub(newInternApplications);

        assertThrows(DuplicateApplicationException.class, () -> internTracker.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internTracker.hasApplication(null));
    }

    @Test
    public void hasPerson_personNotInInternTracker_returnsFalse() {
        assertFalse(internTracker.hasApplication(ALICE));
    }

    @Test
    public void hasPerson_personInInternTracker_returnsTrue() {
        internTracker.addApplication(ALICE);
        assertTrue(internTracker.hasApplication(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInInternTracker_returnsTrue() {
        internTracker.addApplication(ALICE);
        InternApplication editedAlice = new InternApplicationBuilder(ALICE).withStatus(VALID_STATUS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(internTracker.hasApplication(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internTracker.getApplicationList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = InternTracker.class.getCanonicalName() + "{persons=" + internTracker
                .getApplicationList() + "}";
        assertEquals(expected, internTracker.toString());
    }

    /**
     * A stub ReadOnlyInternTracker whose persons list can violate interface constraints.
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
