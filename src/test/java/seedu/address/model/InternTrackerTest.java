package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.internApplication.InternApplication;
import seedu.address.model.internApplication.exceptions.DuplicateApplicationException;
import seedu.address.testutil.PersonBuilder;

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
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        InternTracker newData = getTypicalAddressBook();
        internTracker.resetData(newData);
        assertEquals(newData, internTracker);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        InternApplication editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<InternApplication> newInternApplications = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newInternApplications);

        assertThrows(DuplicateApplicationException.class, () -> internTracker.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internTracker.hasApplication(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(internTracker.hasApplication(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        internTracker.addApplication(ALICE);
        assertTrue(internTracker.hasApplication(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        internTracker.addApplication(ALICE);
        InternApplication editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(internTracker.hasApplication(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internTracker.getApplicationList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = InternTracker.class.getCanonicalName() + "{persons=" + internTracker.getApplicationList() + "}";
        assertEquals(expected, internTracker.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<InternApplication> internApplications = FXCollections.observableArrayList();

        AddressBookStub(Collection<InternApplication> internApplications) {
            this.internApplications.setAll(internApplications);
        }

        @Override
        public ObservableList<InternApplication> getApplicationList() {
            return internApplications;
        }
    }

}
