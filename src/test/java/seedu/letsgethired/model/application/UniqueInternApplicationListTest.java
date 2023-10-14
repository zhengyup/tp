package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalInternApplications.BOB;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.model.application.exceptions.ApplicationNotFoundException;
import seedu.letsgethired.model.application.exceptions.DuplicateApplicationException;
import seedu.letsgethired.testutil.InternApplicationBuilder;

public class UniqueInternApplicationListTest {

    private final UniqueApplicationList uniqueApplicationList = new UniqueApplicationList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueApplicationList.contains(JANE_STREET));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueApplicationList.add(JANE_STREET);
        assertTrue(uniqueApplicationList.contains(JANE_STREET));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueApplicationList.add(JANE_STREET);
        InternApplication editedAlice = new InternApplicationBuilder(JANE_STREET)
                .withStatus(VALID_STATUS_B).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueApplicationList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueApplicationList.add(JANE_STREET);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList.add(JANE_STREET));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplication(null, JANE_STREET));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplication(JANE_STREET,
                null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(ApplicationNotFoundException.class, () -> uniqueApplicationList
                .setApplication(JANE_STREET, JANE_STREET));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueApplicationList.add(JANE_STREET);
        uniqueApplicationList.setApplication(JANE_STREET, JANE_STREET);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(JANE_STREET);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueApplicationList.add(JANE_STREET);
        InternApplication editedAlice = new InternApplicationBuilder(JANE_STREET).withStatus(VALID_STATUS_B)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueApplicationList.setApplication(JANE_STREET, editedAlice);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(editedAlice);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueApplicationList.add(JANE_STREET);
        uniqueApplicationList.setApplication(JANE_STREET, BOB);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(BOB);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueApplicationList.add(JANE_STREET);
        uniqueApplicationList.add(BOB);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList.setApplication(JANE_STREET, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(ApplicationNotFoundException.class, () -> uniqueApplicationList.remove(JANE_STREET));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueApplicationList.add(JANE_STREET);
        uniqueApplicationList.remove(JANE_STREET);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList
                .setApplications((UniqueApplicationList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueApplicationList.add(JANE_STREET);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(BOB);
        uniqueApplicationList.setApplications(expectedUniqueApplicationList);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList
                .setApplications((List<InternApplication>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueApplicationList.add(JANE_STREET);
        List<InternApplication> internApplicationList = Collections.singletonList(BOB);
        uniqueApplicationList.setApplications(internApplicationList);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(BOB);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<InternApplication> listWithDuplicateInternApplications = Arrays.asList(JANE_STREET, JANE_STREET);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList
                .setApplications(listWithDuplicateInternApplications));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueApplicationList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueApplicationList.asUnmodifiableObservableList().toString(),
                uniqueApplicationList.toString());
    }
}
