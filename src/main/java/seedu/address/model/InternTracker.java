package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.internApplication.InternApplication;
import seedu.address.model.internApplication.UniqueApplicationList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameApplication comparison)
 */
public class InternTracker implements ReadOnlyAddressBook {

    private final UniqueApplicationList internApplications;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        internApplications = new UniqueApplicationList();
    }

    public InternTracker() {}

    /**
     * Creates an AddressBook using the InternApplications in the {@code toBeCopied}
     */
    public InternTracker(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the application list with {@code internApplications}.
     * {@code internApplications} must not contain duplicate applications.
     */
    public void setInternApplications(List<InternApplication> internApplications) {
        this.internApplications.setApplications(internApplications);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setInternApplications(newData.getApplicationList());
    }

    //// person-level operations

    /**
     * Returns true if an application with the same identity as {@code internApplication} exists in the
     * address
     * book.
     */
    public boolean hasApplication(InternApplication internApplication) {
        requireNonNull(internApplication);
        return internApplications.contains(internApplication);
    }

    /**
     * Adds an application to the address book.
     * The application must not already exist in the address book.
     */
    public void addApplication(InternApplication a) {
        internApplications.add(a);
    }

    /**
     * Replaces the given application {@code target} in the list with {@code editedInternApplication}.
     * {@code target} must exist in the address book.
     * The application identity of {@code editedInternApplication} must not be the same as another existing
     * application in
     * the
     * address book.
     */
    public void setApplication(InternApplication target, InternApplication editedInternApplication) {
        requireNonNull(editedInternApplication);

        internApplications.setApplication(target, editedInternApplication);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeApplication(InternApplication key) {
        internApplications.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", internApplications)
                .toString();
    }

    @Override
    public ObservableList<InternApplication> getApplicationList() {
        return internApplications.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternTracker)) {
            return false;
        }

        InternTracker otherInternTracker = (InternTracker) other;
        return internApplications.equals(otherInternTracker.internApplications);
    }

    @Override
    public int hashCode() {
        return internApplications.hashCode();
    }
}
