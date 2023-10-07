package seedu.address.testutil;

import seedu.address.model.InternTracker;
import seedu.address.model.application.InternApplication;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private InternTracker internTracker;

    public AddressBookBuilder() {
        internTracker = new InternTracker();
    }

    public AddressBookBuilder(InternTracker internTracker) {
        this.internTracker = internTracker;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(InternApplication internApplication) {
        internTracker.addApplication(internApplication);
        return this;
    }

    public InternTracker build() {
        return internTracker;
    }
}
