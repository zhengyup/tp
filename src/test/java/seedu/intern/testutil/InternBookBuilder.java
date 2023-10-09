package seedu.intern.testutil;

import seedu.intern.model.InternTracker;
import seedu.intern.model.application.InternApplication;

/**
 * A utility class to help with building Internbook objects.
 * Example usage: <br>
 *     {@code InternBook ab = new InternBookBuilder().withPerson("John", "Doe").build();}
 */
public class InternBookBuilder {

    private InternTracker internTracker;

    public InternBookBuilder() {
        internTracker = new InternTracker();
    }

    public InternBookBuilder(InternTracker internTracker) {
        this.internTracker = internTracker;
    }

    /**
     * Adds a new {@code Person} to the {@code InternBook} that we are building.
     */
    public InternBookBuilder withPerson(InternApplication internApplication) {
        internTracker.addApplication(internApplication);
        return this;
    }

    public InternTracker build() {
        return internTracker;
    }
}
